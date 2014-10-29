package geniar.siscar.logic.policies.services.impl;

import geniar.siscar.logic.billing.services.impl.GenerateInvoicesProofInsuranceImpl;
import geniar.siscar.logic.consultas.SearchMonthlyTransac;
import geniar.siscar.logic.consultas.SearchPolicies;
import geniar.siscar.logic.consultas.SearchPoliciesVehicles;
import geniar.siscar.logic.consultas.SearchPolicyAssignementTypeControl;
import geniar.siscar.logic.consultas.SearchVehicles;
import geniar.siscar.logic.policies.services.PoliciesInvoiceService;
import geniar.siscar.mail.util.SendEmail;
import geniar.siscar.model.ControlAssignationPolicies;
import geniar.siscar.model.Inconsistencies;
import geniar.siscar.model.InconsistenciesTypes;
import geniar.siscar.model.InvoiceType;
import geniar.siscar.model.LegateesTypes;
import geniar.siscar.model.LocationsTypes;
import geniar.siscar.model.MonthTransacType;
import geniar.siscar.model.Policies;
import geniar.siscar.model.PoliciesInvoice;
import geniar.siscar.model.PoliciesInvoiceHistoric;
import geniar.siscar.model.PoliciesVehicles;
import geniar.siscar.model.PoliciesVehiclesHistoric;
import geniar.siscar.model.PoliciesVehiclesId;
import geniar.siscar.model.Rolls;
import geniar.siscar.model.VOPolicies;
import geniar.siscar.model.Vehicles;
import geniar.siscar.model.VehiclesAssignation;
import geniar.siscar.persistence.EntityManagerHelper;
import geniar.siscar.persistence.InconsistenciesDAO;
import geniar.siscar.persistence.InvoiceTypeDAO;
import geniar.siscar.persistence.LegateesTypesDAO;
import geniar.siscar.persistence.PoliciesInvoiceDAO;
import geniar.siscar.persistence.PoliciesInvoiceHistoricDAO;
import geniar.siscar.persistence.PoliciesVehiclesDAO;
import geniar.siscar.persistence.PoliciesVehiclesHistoricDAO;
import geniar.siscar.persistence.RollsDAO;
import geniar.siscar.util.ParametersUtil;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author geniar
 */
public class PoliciesInvoiceServiceImpl implements PoliciesInvoiceService, Runnable {

	private int soluciones;
	private static final Log log = LogFactory.getLog(SendEmail.class);

	List<PoliciesVehicles> listaPoliVehicles;
	List<Inconsistencies> listaInconsistencias;
	PoliciesInvoice policiesInvoice; 
	String Login;
	Long valorTotalFactura;
	
	public PoliciesInvoiceServiceImpl(){
		this.listaPoliVehicles = null;
		this.listaInconsistencias = null;
		this.policiesInvoice = null;
		this.Login = null;
		this.valorTotalFactura = null;
	}
	
	public PoliciesInvoiceServiceImpl(List<PoliciesVehicles> listaPoliVehicles,
			List<Inconsistencies> listaInconsistencias,
			PoliciesInvoice policiesInvoice, String Login,
			Long valorTotalFactura){
		
		this.listaPoliVehicles = listaPoliVehicles;
		this.listaInconsistencias = listaInconsistencias;
		this.policiesInvoice = policiesInvoice;
		this.Login = Login;
		this.valorTotalFactura = valorTotalFactura;
		
	}
	
	public PoliciesInvoice consultarFacturaPoliza(String numeroFactura)
			throws GWorkException {
		Util.validarSession();
		return new SearchPolicies()
				.consultarFacturaPorNumeroFactura(numeroFactura);
	}

	public void crearFacturaPoliza(Long numeroPoliza, String numeroFactura,
			Date fechaFactura, String conceptoFact, String login)
			throws GWorkException {

		Util.validarSession();

		if (login == null || login.trim().length() == 0) {
			throw new GWorkException(Util
					.loadErrorMessageValue("USUARIO.SESIONEXP"));
		}

		Policies policies = new SearchPolicies().consultarPoliza(numeroPoliza);

		PoliciesInvoice policiesInvoice = new SearchPolicies()
				.consultarFacturaPorNumeroFactura(numeroFactura);

		if (policies.getPoliciesTypes().getPltCodigo().longValue() != ParametersUtil.SOAT) {
			if (policiesInvoice != null) {
				throw new GWorkException(Util
						.loadErrorMessageValue("ERROR.FACTURAEXISTE"));
			}
		}

		try {
			policiesInvoice = new PoliciesInvoice();
			policiesInvoice.setPolicies(policies);
			policiesInvoice.setPinNumeroFactura(numeroFactura);
			policiesInvoice.setPinFechaFactura(fechaFactura);
			policiesInvoice.setPinConcepto(conceptoFact);
			policiesInvoice.setPinCargado(0L);

			EntityManagerHelper.beginTransaction();
			new PoliciesInvoiceDAO().save(policiesInvoice);
			EntityManagerHelper.commit();
			// Util.limpiarSession();

			PoliciesInvoiceHistoric invoiceHistoric = new PoliciesInvoiceHistoric();

			invoiceHistoric.setPihFecha(new Date());
			invoiceHistoric.setUsrLogin(login);
			invoiceHistoric.setPinNumeroFactura(numeroFactura);
			invoiceHistoric.setPinFechaFactura(fechaFactura);
			invoiceHistoric.setPinConcepto(conceptoFact);
			invoiceHistoric.setPlsCodigo(policies.getPlsCodigo());
			invoiceHistoric.setPinCargado(0L);

			Util.validarSession();
			EntityManagerHelper.beginTransaction();
			new PoliciesInvoiceHistoricDAO().save(invoiceHistoric);
			EntityManagerHelper.commit();
			// Util.limpiarSession();

		} catch (RuntimeException e) {
			e.printStackTrace();

			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.GUARDARFACPOL"));
		}
	}

	public void modificarFacturaPoliza(Long numeroPoliza, String numeroFactura,
			String numeroNuevaFactura, Date fechaFactura, String conceptoFact,
			String login) throws GWorkException {
		Util.validarSession();

		// Se valida la existencia del usuario
		if (login == null || login.trim().length() == 0) {
			throw new GWorkException(Util
					.loadErrorMessageValue("USUARIO.SESIONEXP"));
		}

		Policies policies = new SearchPolicies().consultarPoliza(numeroPoliza);

		if (policies == null) {
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.POLIZANEXISTE"));
		}

		// se busca la factura que se va a modificar
		PoliciesInvoice polInv = new SearchPolicies()
				.consultarFacturaPorNumeroFactura(numeroFactura);

		if (polInv == null) {
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.FACTURANOEXISTE"));
		}

		if (polInv.getPinNumeroFactura().equals(numeroFactura)) {
			try {

				if (numeroNuevaFactura != null
						&& numeroNuevaFactura.trim().length() > 0)
					polInv.setPinNumeroFactura(numeroNuevaFactura);
				else
					polInv.setPinNumeroFactura(numeroFactura);

				polInv.setPinFechaFactura(fechaFactura);
				polInv.setPinConcepto(conceptoFact);
				polInv.setPolicies(policies);

				new PoliciesInvoiceDAO().update(polInv);

				// Util.limpiarSession();

				PoliciesInvoiceHistoric invHist = new PoliciesInvoiceHistoric();

				invHist.setPihFecha(new Date());
				invHist.setUsrLogin(login);
				if (numeroNuevaFactura != null
						&& numeroNuevaFactura.trim().length() > 0)
					invHist.setPinNumeroFactura(numeroNuevaFactura);
				else
					invHist.setPinNumeroFactura(numeroFactura);
				invHist.setPinFechaFactura(fechaFactura);
				invHist.setPinConcepto(conceptoFact);
				invHist.setPlsCodigo(policies.getPlsCodigo());
				invHist.setPinCargado(polInv.getPinCargado());

				new PoliciesInvoiceHistoricDAO().save(invHist);

				// Util.limpiarSession();

				new InconsistenciesServiceImpl()
						.modificarInconsistenciasFactura(polInv);

				EntityManagerHelper.getEntityManager().getTransaction().begin();
				EntityManagerHelper.getEntityManager().getTransaction()
						.commit();

			} catch (RuntimeException e) {

				if (EntityManagerHelper.getEntityManager().getTransaction()
						.isActive())
					EntityManagerHelper.getEntityManager().getTransaction()
							.rollback();

				throw new GWorkException(Util
						.loadErrorMessageValue("ERROR.MODIFFACTU"));

			}
		}
	}

	public List<PoliciesVehicles> consultarPolizasSOATVehiculo(
			String placaVehiculo) throws GWorkException {
		return new SearchPolicies().consultarPolizasSOATVehiculo(placaVehiculo);
	}

	@SuppressWarnings("null")
	public void guardarDetalle(List<Inconsistencies> listaInconsistencias,
			List<PoliciesVehicles> listaPoliVehicles,
			PoliciesInvoice invoicePolicy, String login, Long valorTotalFactura)
			throws GWorkException {
		try {
			int countIcons = 0;
			System.out.println("Incosistencias: " + listaInconsistencias.size()
					+ "\n Lista de polizas: " + listaPoliVehicles.size());

			for (Inconsistencies inconsistencies : listaInconsistencias) {
				if ((inconsistencies.getInconsistenciesTypes().getIctId()
						.longValue() == 1L || inconsistencies.getIncEstado()
						.longValue() == 3L)
						&& inconsistencies.getIncEstado().longValue() == 1L)
					countIcons++;
			}
			Util.validarSession();
			if (listaPoliVehicles != null || !listaPoliVehicles.isEmpty()) {
				for (PoliciesVehicles pv : listaPoliVehicles) {
					new PoliciesTransactionsServiceImpl()
							.adicionVehiculoPoliza(pv.getMonthTransacType()
									.getMttId(), pv.getVehicles()
									.getVhcPlacaDiplomatica(), pv.getPolicies()
									.getPoliciesTypes().getPltCodigo(),
									new Date(),
									pv.getPolicies().getPlsNumero(), pv
											.getPvsValorPrima(), pv
											.getPvsValorIva(), pv
											.getPvsValorAsegurado(), pv
											.getPvsNumeroFactura(), login, pv
											.getLegateesTypes(), pv
											.getPvsCarnetAsignatario(), pv
											.getPvsEstado(),
									ParametersUtil.NOVEDAD_POLIZA);
				}
			}

			invoicePolicy.setPinCargado(1L);
			invoicePolicy.setInconsistencieses(new HashSet<Inconsistencies>(
					listaInconsistencias));

			PoliciesInvoiceHistoric invHist = new PoliciesInvoiceHistoric();
			invHist.setPihFecha(new Date());
			invHist.setUsrLogin(login);
			invHist.setPinNumeroFactura(invoicePolicy.getPinNumeroFactura());
			invHist.setPinFechaFactura(invoicePolicy.getPinFechaFactura());
			invHist.setPinConcepto(invoicePolicy.getPinConcepto());
			invHist.setPlsCodigo(invoicePolicy.getPolicies().getPlsCodigo());
			invHist.setPinCargado(invoicePolicy.getPinCargado());

			new PoliciesInvoiceDAO().update(invoicePolicy);
			new PoliciesInvoiceHistoricDAO().save(invHist);

//			TODO:YYY
			EntityManagerHelper.getEntityManager().getTransaction().begin();
			EntityManagerHelper.getEntityManager().getTransaction().commit();
			// Util.limpiarSession();

			try {
				String FechaVigencia = invoicePolicy.getPolicies()
						.getPlsFechainicioCobertura().toString()
						+ " - "
						+ invoicePolicy.getPolicies().getPlsFechafinCobertura()
								.toString();

				List<PoliciesVehicles> ListaPolicesVehicles = new SearchPoliciesVehicles()
						.consultarPoliciesVehiclesFactura(invoicePolicy
								.getPinNumeroFactura().toString());

				int cantidadRegistro = ListaPolicesVehicles.size() + countIcons;

				for (Inconsistencies inconsistencies : listaInconsistencias) {
					String PlacaInconsistencia = inconsistencies.getVhcPlaca()
							.toString();

					for (PoliciesVehicles policiesVehicles : listaPoliVehicles) {
						String PlacaPolicesVehicle = policiesVehicles
								.getVehicles().getVhcPlacaDiplomatica()
								.toString();

						if (PlacaInconsistencia.equals(PlacaPolicesVehicle)) {
							listaPoliVehicles.remove(policiesVehicles);
							break;
						}
					}
				}

				valorTotalFactura = CalcularTotalFactura(listaPoliVehicles,
						listaInconsistencias);

				enviarMailArchivoPlano(invoicePolicy.getPinNumeroFactura(),
						invoicePolicy.getPolicies().getPlsNumero().longValue(),
						invoicePolicy.getPolicies().getPoliciesTypes()
								.getPltNombre().toString(), FechaVigencia
								.toString(), cantidadRegistro, new BigDecimal(
								valorTotalFactura));
				// new InconsistenciesServiceImpl().enviarMail(
				// listaInconsistencias.size(), invoicePolicy
				// .getPinNumeroFactura());
			} catch (RuntimeException e) {
				log.error(e.getMessage(), e);
			}

		} catch (Exception e) {
			// Util.limpiarSession();
			log.error(e.getMessage(), e);
			throw new GWorkException(e.getMessage(), e);
		}
	}

	@SuppressWarnings("null")
	public void guardarDetalleNovedad(
			List<Inconsistencies> listaInconsistencias,
			List<PoliciesVehicles> listaPoliVehicles,
			PoliciesInvoice invoicePolicy, String login,
			Long valorTotalFactura, Long tipoPoliza) throws GWorkException,
			SQLException {
		Connection connection = null;

		try {
			// Util.validarSession();
			// Identificador de la factura
			invoicePolicy = EntityManagerHelper.getEntityManager().merge(
					invoicePolicy);
			Long idFactura = invoicePolicy.getPinId();
			
			List<PoliciesVehicles> lstPvsSinFacturar;

			// Se confirma que la factura exista
			PoliciesInvoice miFactura = new PoliciesInvoiceDAO()
					.findById(idFactura);

			// Con la factura se obtiene la poliza y
			// luego las asociaciones

			if (tipoPoliza.longValue() == ParametersUtil.SOAT)
				lstPvsSinFacturar = new SearchPoliciesVehicles()
						.consultarPoliciesVehiclesNoFactSAOT(invoicePolicy
								.getPinId());
			else
				lstPvsSinFacturar = new SearchPoliciesVehicles()
						.consultarPoliciesVehiclesNoFact(miFactura
								.getPolicies().getPlsNumero());

			// Si existen vehiculos para asociar a una poliza..
			if ((listaPoliVehicles != null || !listaPoliVehicles.isEmpty())
					&& tipoPoliza.longValue() != ParametersUtil.SOAT) {
				for (PoliciesVehicles pv : listaPoliVehicles) {
					// Si el tipo de transacción es 3 (Adición)
					if (pv.getMonthTransacType().getMttId().longValue() == 3L) {
						// Se asocia cada vehiculo a la poliza

						if (!SearchPoliciesVehicles
								.consultarPoliciesVehiclesFact(new Long(pv
										.getPolicies().getPlsNumero()), pv
										.getVehicles().getVhcPlacaDiplomatica())) {
							adicionVehiculoPoliza(pv
									.getMonthTransacType().getMttId(), pv
									.getVehicles().getVhcPlacaDiplomatica(), pv
									.getPolicies().getPoliciesTypes()
									.getPltCodigo(), pv.getPvsFechaini(), pv
									.getPolicies().getPlsNumero(), pv
									.getPvsValorPrima(), pv.getPvsValorIva(),
									pv.getPvsValorAsegurado(), pv
											.getPvsNumeroFactura(), login, pv
											.getLegateesTypes(), pv
											.getPvsCarnetAsignatario(), pv
											.getPvsEstado(),
									ParametersUtil.NOVEDAD_POLIZA);
						}
						// Fin asociación
					}
				}
			}

			// verificarInconsistenciasFacturas(idFactura);

			List<Inconsistencies> newInconsList = new ArrayList<Inconsistencies>();
			List<Inconsistencies> newInconsStandar = new ArrayList<Inconsistencies>();
			List<Inconsistencies> newInconsCredito = new ArrayList<Inconsistencies>();

			miFactura = new PoliciesInvoiceDAO().findById(idFactura);

			Iterator<Inconsistencies> iter = miFactura.getInconsistencieses()
					.iterator();

			while (iter.hasNext()) {
				Inconsistencies i = iter.next();
				newInconsList.add(i);
			}

			// for (Inconsistencies i : listaInconsistencias) {
			// newInconsList.add(i);
			// }

			invoicePolicy.setInconsistencieses(new HashSet<Inconsistencies>(
					newInconsList));

			invoicePolicy.setPinCargado(ParametersUtil.FacturaEnviadaAP
					.longValue());

			if (tipoPoliza.longValue() != ParametersUtil.SOAT) {
				PoliciesInvoiceHistoric invHist = new PoliciesInvoiceHistoric();
				invHist.setPihFecha(new Date());
				invHist.setUsrLogin(login);
				invHist
						.setPinNumeroFactura(invoicePolicy
								.getPinNumeroFactura());
				invHist.setPinFechaFactura(invoicePolicy.getPinFechaFactura());
				invHist.setPinConcepto(invoicePolicy.getPinConcepto());
				invHist
						.setPlsCodigo(invoicePolicy.getPolicies()
								.getPlsCodigo());
				invHist.setPinCargado(invoicePolicy.getPinCargado());

				// Util.validarSession();
				// EntityManagerHelper.beginTransaction();
				// for (PoliciesVehicles pv : lstPolVHCTemp) {
				// new PoliciesVehiclesDAO().update(pv);
				// }
				new PoliciesInvoiceDAO().update(invoicePolicy);
				new PoliciesInvoiceHistoricDAO().save(invHist);

			}
			// listaPoliVehicles = PoliciesTransactionsServiceImpl
			// .getListaPolizasStandar();

			Long valorTotalFacturaIncStandar = 0L;
			Long valorTotalFacturaIncCredito = 0L;

			for (Inconsistencies inconsistencies : listaInconsistencias) {

				// validar que se ingresen solo inconsistencias de tipo 1 y 3s

				if (inconsistencies.getInconsistenciesTypes().getIctId()
						.longValue() == 1L
						|| inconsistencies.getInconsistenciesTypes().getIctId()
								.longValue() == 3L) {
					// Se genera la lista de incosistencias credito
					if (inconsistencies.getIncValorPrima() < 0) {
						newInconsCredito.add(inconsistencies);
						valorTotalFacturaIncCredito += inconsistencies
								.getIncValorPrima().longValue()
								+ inconsistencies.getIncValorIva().longValue();
					}

					// se genera la lista de inconsistencias debito
					if (inconsistencies.getIncValorPrima() > 0) {

						newInconsStandar.add(inconsistencies);
						valorTotalFacturaIncStandar += inconsistencies
								.getIncValorPrima().longValue()
								+ inconsistencies.getIncValorIva().longValue();
					}
				}

				// if (tipoPoliza.longValue() != ParametersUtil.SOAT) {
				//
				// PoliciesVehicles policiesVehicles = new
				// SearchPoliciesVehicles()
				// .consultarPolizaVehiculo(inconsistencies
				// .getVhcPlaca(), invoicePolicy.getPolicies()
				// .getPlsNumero());
				//
				// if (policiesVehicles != null) {
				// policiesVehicles.setPvsValorAsegurado(inconsistencies
				// .getIncValorAsegurado());
				//
				// policiesVehicles.setPvsValorIva(inconsistencies
				// .getIncValorIva());
				//
				// policiesVehicles.setPvsValorPrima(inconsistencies
				// .getIncValorPrima());
				//
				// policiesVehicles.setPvsValorTotal(inconsistencies
				// .getIncValorTotal());
				//
				// new PoliciesVehiclesDAO().update(policiesVehicles);
				// }
				// }
			}

			List<PoliciesVehicles> polizasDebito = new ArrayList<PoliciesVehicles>();
			Long totalDebitoFactura = 0L;

			for (PoliciesVehicles pvDebito : listaPoliVehicles) {
				if (pvDebito.getPvsValorPrima() > 0) {
					polizasDebito.add(pvDebito);
					totalDebitoFactura = totalDebitoFactura
							+ pvDebito.getPvsValorPrima().longValue()
							+ pvDebito.getPvsValorIva().longValue();
				}
			}

			if ((polizasDebito != null && polizasDebito.size() > 0)
					|| (newInconsStandar != null && newInconsStandar.size() > 0)) {
				InvoiceType IntCodigo = new InvoiceTypeDAO().findByIntNombre(
						"STANDARD").get(0);

				totalDebitoFactura = totalDebitoFactura
						+ valorTotalFacturaIncStandar;
				connection = new GenerateInvoicesProofInsuranceImpl()
						.generarComprobanteDetalle(polizasDebito,
								newInconsStandar, invoicePolicy, login,
								IntCodigo, totalDebitoFactura);
			}

			List<PoliciesVehicles> polizasCredito = new ArrayList<PoliciesVehicles>();
			Long totalCreditoFactura = 0L;
			for (PoliciesVehicles pvCredito : listaPoliVehicles) {
				if (pvCredito.getPvsValorPrima() < 0) {
					polizasCredito.add(pvCredito);
					totalCreditoFactura += pvCredito.getPvsValorPrima().longValue()
							+ pvCredito.getPvsValorIva().longValue();
				}
			}

			if ((polizasCredito != null && polizasCredito.size() > 0)
					|| (newInconsCredito != null && newInconsCredito.size() > 0)) {
				InvoiceType IntCodigo = new InvoiceTypeDAO().findByIntNombre(
						"CREDIT MEMO").get(0);
				totalCreditoFactura = totalCreditoFactura
						+ valorTotalFacturaIncCredito;
				connection = new GenerateInvoicesProofInsuranceImpl()
						.generarComprobanteDetalle(polizasCredito,
								newInconsCredito, invoicePolicy, login,
								IntCodigo, totalCreditoFactura);
			}

			new PoliciesInvoiceDAO().update(invoicePolicy);

			if (connection != null)
				connection.commit();

			EntityManagerHelper.getEntityManager().getTransaction().begin();
			EntityManagerHelper.getEntityManager().getTransaction().commit();
			// Util.limpiarSession();

			// try {
			// new InconsistenciesServiceImpl().enviarMail(newInconsList
			// .size(), invoicePolicy.getPinNumeroFactura());
			// } catch (RuntimeException e) {
			// e.printStackTrace();
			// }

		} catch (Exception e) {

			if (connection != null)
				connection.rollback();

			if (EntityManagerHelper.getEntityManager().getTransaction()
					.isActive())
				EntityManagerHelper.getEntityManager().getTransaction()
						.rollback();

			e.printStackTrace();
			throw new GWorkException(e.getMessage());
		}
	}

	/**
	 * Verifica que inconsistencias de la factura ya se resolvieron.
	 * 
	 * @param miFactura
	 */
	public void verificarInconsistenciasFacturas(Long idFactura)
			throws GWorkException {

		// Se confirma que la factura exista
		PoliciesInvoice miFactura = new PoliciesInvoiceDAO()
				.findById(idFactura);

		// Inconsistencias anteriores
		Iterator<Inconsistencies> setPreInconsis = miFactura
				.getInconsistencieses().iterator();

		List<Inconsistencies> lstPreInconList = new ArrayList<Inconsistencies>();

		while (setPreInconsis.hasNext()) {
			Inconsistencies inc = setPreInconsis.next();
			lstPreInconList.add(inc);
		}

		soluciones = 0;

		for (Inconsistencies inc : lstPreInconList) {
			if (inc.getIncEstado().longValue() != 0L) {
				Vehicles vehicles = SearchVehicles
						.consultarVehiculosPorPlacaSinFiltros(inc.getVhcPlaca()
								.toUpperCase());

				// Si el tipo de inconsistencia es:
				// 1: El vehiculo no existe en la base de datos
				if (inc.getInconsistenciesTypes().getIctId().longValue() == 1L) {

					if (vehicles != null) {
						inc.setIncEstado(0L);
						// Util.validarSession();
						// EntityManagerHelper.beginTransaction();
						new InconsistenciesDAO().update(inc);

						// Como se corrigio la inconsistencia se asocia el
						// vehiculo a la poliza
						new PoliciesVehiclesDAO().save(construirAsociacion(inc,
								vehicles));
						// EntityManagerHelper.commit();
						// //Util.limpiarSession();

						soluciones++;
					}
				}

				// Si el tipo de inconsistencia es:
				// 2: El vehiculo aparece repetido en la factura.
				if (inc.getInconsistenciesTypes().getIctId().longValue() == 2L) {
					// Se soluciona cargando nuevamente la factura
				}

				// Si el tipo de inconsistencia es:
				// 3: Vehiculo Inactivo
				if (inc.getInconsistenciesTypes().getIctId().longValue() == 3L) {

					// Se valida que el vehiculo se encuentre en un estado
					// permitido
					if (vehicles.getVehiclesStates().getVhsCodigo() != 2
							&& vehicles.getVehiclesStates().getVhsCodigo() == 4
							&& vehicles.getVehiclesStates().getVhsCodigo() == 5) {

						inc.setIncEstado(0L);
						// Util.validarSession();
						// EntityManagerHelper.beginTransaction();
						new InconsistenciesDAO().update(inc);

						// Como se corrigio la inconsistencia se asocia el
						// vehiculo a la poliza
						new PoliciesVehiclesDAO().save(construirAsociacion(inc,
								vehicles));
						// EntityManagerHelper.commit();
						// //Util.limpiarSession();
						soluciones++;
					}

				}

				// Si el tipo de inconsistencia es:
				// 4: Polizas excluyentes
				if (inc.getInconsistenciesTypes().getIctId().longValue() == 4L) {
					// Se puede resolver al cargar otra factura
				}

				// Si el tipo de inconsistencia es:
				// 5: Asignación 'PERSONAL' con poliza basica
				if (inc.getInconsistenciesTypes().getIctId().longValue() == 5L) {
					VehiclesAssignation m = SearchVehicles
							.consultarAsignacionVehiculo(inc.getVhcPlaca()
									.toUpperCase());

					LegateesTypes legateesTypes = null;

					if (m != null) {
						legateesTypes = m.getRequests().getLegateesTypes();
					} else {
						legateesTypes = new LegateesTypesDAO().findById(8L);
					}

					List<PoliciesVehicles> lstPvs = new SearchPoliciesVehicles()
							.consultarPVs(inc.getVhcPlaca());

					for (PoliciesVehicles pv : lstPvs) {
						Policies policies = pv.getPolicies();
						Long idTipoPoliza = policies.getPoliciesTypes()
								.getPltCodigo();

						if (idTipoPoliza.longValue() == 1L) {
							Long codLegatee = legateesTypes.getLgtCodigo();
							if (codLegatee.longValue() != 6L) {

								inc.setIncEstado(0L);
								// Util.validarSession();
								// EntityManagerHelper.beginTransaction();
								new InconsistenciesDAO().update(inc);

								// Como se corrigio la inconsistencia se asocia
								// el
								// vehiculo a la poliza
								new PoliciesVehiclesDAO()
										.save(construirAsociacion(inc, vehicles));
								// EntityManagerHelper.commit();
								// //Util.limpiarSession();

								soluciones++;
							}
						}
					}
				}

				// Si el tipo de inconsistencia es:
				// 6: Vehiculo fuera de sede con poliza basica
				if (inc.getInconsistenciesTypes().getIctId().longValue() == 6L) {

					List<PoliciesVehicles> lstPvs = new SearchPoliciesVehicles()
							.consultarPVs(inc.getVhcPlaca());

					for (PoliciesVehicles pv : lstPvs) {
						Policies policies = pv.getPolicies();
						Long idTipoPoliza = policies.getPoliciesTypes()
								.getPltCodigo();

						if (idTipoPoliza.longValue() != 12L) {
							if (vehicles.getLocations().getLocationsTypes()
									.getLctCodigo().longValue() != 1L) {

								inc.setIncEstado(0L);
								// Util.validarSession();
								// EntityManagerHelper.beginTransaction();
								new InconsistenciesDAO().update(inc);

								// Como se corrigio la inconsistencia se asocia
								// el
								// vehiculo a la poliza
								new PoliciesVehiclesDAO()
										.save(construirAsociacion(inc, vehicles));
								// EntityManagerHelper.commit();
								// //Util.limpiarSession();

								soluciones++;

							}
						}
					}

				}

				// Si el tipo de inconsistencia es:
				// 7: Vehiculo sin soat no ubicado en exteriores
				if (inc.getInconsistenciesTypes().getIctId().longValue() == 7L) {

					if (vehicles.getLocations().getLocationsTypes()
							.getLctCodigo().longValue() != 3L) {

						List<PoliciesVehicles> lstPVs = new SearchPolicies()
								.consultarPolizasSOATVehiculo(inc.getVhcPlaca());

						if (lstPVs != null) {
							if (!lstPVs.isEmpty()) {

								inc.setIncEstado(0L);
								// Util.validarSession();
								// EntityManagerHelper.beginTransaction();
								new InconsistenciesDAO().update(inc);

								// Como se corrigio la inconsistencia se asocia
								// el
								// vehiculo a la poliza
								new PoliciesVehiclesDAO()
										.save(construirAsociacion(inc, vehicles));
								// EntityManagerHelper.commit();
								// //Util.limpiarSession();

								soluciones++;
							}
						}

					} else {
						if (vehicles.getLocations().getLocationsTypes()
								.getLctCodigo().longValue() == 3L) {
							inc.setIncEstado(0L);
							// Util.validarSession();
							// EntityManagerHelper.beginTransaction();
							new InconsistenciesDAO().update(inc);

							// Como se corrigio la inconsistencia se asocia el
							// vehiculo a la poliza
							new PoliciesVehiclesDAO().save(construirAsociacion(
									inc, vehicles));
							// EntityManagerHelper.commit();
							// //Util.limpiarSession();

							soluciones++;
						}
					}

				}
			}
		}
	}

	/**
	 * Crea un nuevo objeto de tipo {@link PoliciesVehicles}
	 * 
	 * @param inc
	 *            Inconsistencia que se corrigio.
	 * @param vehicles
	 *            Vehiculo que se va a asociar.
	 * @return {@link PoliciesVehicles}
	 */
	private PoliciesVehicles construirAsociacion(Inconsistencies inc,
			Vehicles vehicles) {
		PoliciesVehicles pv = new PoliciesVehicles();

		PoliciesVehiclesId id = new PoliciesVehiclesId();
		id.setPlsCodigo(inc.getPoliciesInvoice().getPolicies().getPlsCodigo());
		id.setVhcCodigo(vehicles.getVhcCodigo());

		pv.setId(id);
		pv.setLegateesTypes(inc.getLegateesTypes());
		pv.setMonthTransacType(inc.getMonthTransacType());
		pv.setPolicies(inc.getPoliciesInvoice().getPolicies());
		pv.setPvsCarnetAsignatario(inc.getPvsCarnetAsignatario());
		pv.setPvsEstado(1L);
		pv.setPvsFechafin(inc.getPoliciesInvoice().getPolicies()
				.getPlsFechafinCobertura());
		pv.setPvsFechaini(new Date());
		pv.setPvsNumeroFactura(inc.getPoliciesInvoice().getPinNumeroFactura());
		pv.setPvsValorAsegurado(inc.getIncValorAsegurado());
		pv.setPvsValorIva(inc.getIncValorIva());
		pv.setPvsValorPrima(inc.getIncValorPrima());
		pv.setPvsValorTotal(inc.getIncValorTotal());
		pv.setVehicles(vehicles);

		return pv;
	}

	/**
	 * Modifica el detalle de la factura. Elimina las inconsistencias cargadas
	 * previamente. Guarda las nuevas inconsistencia encontradas en el nuevo
	 * archivo. Elimina las asociaciones previas. Se guardan las nuevas
	 * asociaciones.
	 */
	public void modificarDetalle(List<Inconsistencies> listaInconsistencias,
			List<PoliciesVehicles> listaPoliVehicles,
			PoliciesInvoice invoicePolicy, String login, Long valorTotalFactura)
			throws GWorkException {

		if (invoicePolicy != null) {
			try {
				if (invoicePolicy.getInconsistencieses() != null) {
					if (!invoicePolicy.getInconsistencieses().isEmpty()) {
						Iterator<Inconsistencies> i = invoicePolicy
								.getInconsistencieses().iterator();

						while (i.hasNext()) {
							Inconsistencies inc = i.next();
							/*
							 * Se elimina cada una de las inconsistencias
							 * guardadas anteriormente
							 */
							new InconsistenciesServiceImpl()
									.eliminarInconsistencia(inc.getIncId());
						}
					}
				}

				Policies p = invoicePolicy.getPolicies();

				if (p.getPoliciesVehicleses() != null) {
					if (!p.getPoliciesVehicleses().isEmpty()) {
						Iterator<PoliciesVehicles> pvs = p
								.getPoliciesVehicleses().iterator();

						while (pvs.hasNext()) {
							PoliciesVehicles pv = pvs.next();

							// Se actualizan los pv existentes
							// cambiando su estado a inactivo
							MonthTransacType mtt = new MonthTransacTypeServiceImpl()
									.consultarMonthTransacType(2L);

							pv.setMonthTransacType(mtt);
							pv.setPvsEstado(0L);

							Util.validarSession();
							EntityManagerHelper.beginTransaction();

							new PoliciesVehiclesDAO().update(pv);

							// Se guarda el histórico de la modificación
							new PoliciesVehiclesHistoricDAO()
									.save(crearHistoricoPolVHC(pv, login));

							EntityManagerHelper.commit();
							// Util.limpiarSession();
						}
					}
				}

				guardarDetalle(listaInconsistencias, listaPoliVehicles,
						invoicePolicy, login, valorTotalFactura);
			} catch (Exception e) {
				throw new GWorkException(Util
						.loadErrorMessageValue("ERROR.RENEWARCHIVO1"));
			}
		}
	}

	/**
	 * Se crea un objeto con el historico de la asociación del vehiculo con la
	 * poliza.
	 * 
	 * @param pv
	 *            {@link PoliciesVehicles} Asociación del vehiculo con la
	 *            poliza.
	 * @param login
	 *            Login de usuario que ejecuta la acción.
	 * @return {@link PoliciesVehiclesHistoric} Objeto con el historico.
	 */
	private PoliciesVehiclesHistoric crearHistoricoPolVHC(PoliciesVehicles pv,
			String login) {
		PoliciesVehiclesHistoric historic = new PoliciesVehiclesHistoric();
		historic.setMonthTransacType(pv.getMonthTransacType());
		historic.setPlsCodigo(pv.getPolicies().getPlsCodigo());
		historic.setPvhFecha(new Date());
		historic.setPvsFechaini(pv.getPvsFechaini());
		historic.setPvsFechafin(pv.getPvsFechafin());
		historic.setUsrLogin(login);
		historic.setPvsEstado(pv.getPvsEstado());
		historic.setPinNumeroFactura(pv.getPvsNumeroFactura());
		historic.setPvsValorAsegurado(pv.getPvsValorAsegurado());
		historic.setPvsValorIva(pv.getPvsValorIva());
		historic.setPvsValorPrima(pv.getPvsValorPrima());
		historic.setPvsValorTotal(pv.getPvsValorTotal());
		historic.setVhcCodigo(pv.getVehicles().getVhcCodigo());

		return historic;
	}

	public List<PoliciesInvoice> consultarFacturas() throws GWorkException {
		return new SearchPolicies().consultarFacturas();
	}

	public void generarInconsitenciasFactura(
			List<PoliciesVehicles> listPoliciesVehicles, String Login,
			PoliciesInvoice policiesInvoice) throws GWorkException {

		try{
			Policies policies = null;
			Vehicles vehicles = null;
			Long idTipoPoliza = null;
			Float valorIva = null;
			Float valorPrima = null;
			Float valorTotal = null;
			Float valorAsegurado = null;
			String placaVehiculo = null;
			VehiclesAssignation vehiclesAssignation = null;
			LegateesTypes legateesTypes = null;
			String pvsCarnetAsignatario = null;
			LocationsTypes locationsTypes = null;
			int CantidadInConsistencias = 0;
	
			Util.validarSession();
			int i=-1;
			for (PoliciesVehicles policiesVehicles : listPoliciesVehicles) {
				i++;
				EntityManagerHelper.log("Iterando la póliza de vehículos No. " + i + " de la coleccion", Level.INFO, null );
				
				policies = policiesVehicles.getPolicies();
				vehicles = policiesVehicles.getVehicles();
	
				Inconsistencies inconsistencies = new SearchPoliciesVehicles()
						.consultarInconsistenciaPlacaPoliza(vehicles
								.getVhcPlacaDiplomatica().toString(),
								policiesInvoice.getPinId().longValue());
				if (inconsistencies == null) {
					CantidadInConsistencias++;
					idTipoPoliza = policiesVehicles.getPolicies()
							.getPoliciesTypes().getPltCodigo().longValue();
					valorIva = policiesVehicles.getPvsValorIva().floatValue();
					valorPrima = policiesVehicles.getPvsValorPrima().floatValue();
					valorTotal = policiesVehicles.getPvsValorTotal().floatValue();
					valorAsegurado = policiesVehicles.getPvsValorAsegurado()
							.floatValue();
					placaVehiculo = vehicles.getVhcPlacaDiplomatica().toString();
					vehiclesAssignation = SearchVehicles
							.consultarAsignacionVehiculo(placaVehiculo);
	
					if (vehiclesAssignation != null) {
						legateesTypes = vehiclesAssignation.getRequests()
								.getLegateesTypes();
						pvsCarnetAsignatario = vehiclesAssignation.getRequests()
								.getRqsCarnetAsignatario().toString();
					}
					if (legateesTypes == null)
						legateesTypes = new LegateesTypesDAO().findById(8L);
	
					if (idTipoPoliza == 12L) {
						Long codLegatee = legateesTypes.getLgtCodigo();
						if (codLegatee.longValue() == 6L) {
							agregarInconsistenciaLista(policies, TiposInconsis
									.getASIG_PER_AMP_BASIC(), Login, valorIva,
									valorPrima, valorTotal, valorAsegurado,
									placaVehiculo, legateesTypes,
									pvsCarnetAsignatario, policiesInvoice);
						}
					}
					Long estado = vehicles.getVehiclesStates().getVhsCodigo();
					if (estado.longValue() == 2L || estado.longValue() == 5L) {
						agregarInconsistenciaLista(policies, TiposInconsis
								.getVHC_INACTIVO(), Login, valorIva, valorPrima,
								valorTotal, valorAsegurado, placaVehiculo,
								legateesTypes, pvsCarnetAsignatario,
								policiesInvoice);
					} else {
						locationsTypes = vehicles.getLocations()
								.getLocationsTypes();
	
						List<PoliciesVehicles> listaPoliciesVehicleSoat = consultarPolizasSOATVehiculo(placaVehiculo);
	
						if (locationsTypes.getLctCodigo().longValue() != 3L) {
							if (listaPoliciesVehicleSoat == null
									|| listaPoliciesVehicleSoat.isEmpty()
									|| listaPoliciesVehicleSoat.size() <= 0) {
								agregarInconsistenciaLista(policies, TiposInconsis
										.getVHC_NOSOAT_EXTERIOR(), Login, valorIva,
										valorPrima, valorTotal, valorAsegurado,
										placaVehiculo, legateesTypes,
										pvsCarnetAsignatario, policiesInvoice);
							} else {
								PoliciesVehicles pv = null;
								for (PoliciesVehicles policiesVehiclesTemp : listaPoliciesVehicleSoat) {
									pv = policiesVehiclesTemp;
									// Se busca cual es el soat activo
									if (pv.getPvsEstado() == 1) {
										break;
									}
								}
								// Si no se encuentra un soat activo
								if (pv == null) {
									// Se genera una inconsistencia
									agregarInconsistenciaLista(policies,
											TiposInconsis.getVHC_NOSOAT_EXTERIOR(),
											Login, valorIva, valorPrima,
											valorTotal, valorAsegurado,
											placaVehiculo, legateesTypes,
											pvsCarnetAsignatario, policiesInvoice);
								}
							}
						}
	
						if (locationsTypes.getLctCodigo().longValue() != 1L
								&& idTipoPoliza.longValue() == 12L) {
							agregarInconsistenciaLista(policies, TiposInconsis
									.getVHC_SEDE_POL_BASIC(), Login, valorIva,
									valorPrima, valorTotal, valorAsegurado,
									placaVehiculo, legateesTypes,
									pvsCarnetAsignatario, policiesInvoice);
						}
	
						// se valida contra la tabla de parametros para ver si
						// aplica o
						// no la poliza segun en tipo de ubicación del vehiculo y el
						// asignatario correspondiente
	
						// Se determina el estado del vehiculo
						if (estado.longValue() == 1L || // Asignacion
								estado.longValue() == 7L || // Alquilado
								estado.longValue() == 8L)// Prestado
						{
							Iterator<VehiclesAssignation> assignations = vehicles
									.getVehiclesAssignations().iterator();
	
							VehiclesAssignation assign = null;
	
							if (assignations != null) {
								// Se busca cual es la asignacion actual/vigente
								while (assignations.hasNext()) {
									VehiclesAssignation TempAssign = (VehiclesAssignation) assignations
											.next();
	
									Date fechaActual = new Date();
	
									// Se valida que la asignacion de turno sea la
									// activa en terminos de las fechas de inicio y
									// fin
									if (fechaActual.after(TempAssign
											.getVhaFechaInicio())
											&& fechaActual.before(TempAssign
													.getVhaFechaTermina())) {
										assign = TempAssign;
										break;
									} else {
										agregarInconsistenciaLista(
												policies,
												TiposInconsis
														.getVHC_ASIGNACION_VENCIDA(),
												Login, valorIva, valorPrima,
												valorTotal, valorAsegurado,
												placaVehiculo, legateesTypes,
												pvsCarnetAsignatario,
												policiesInvoice);
										break;
									}
	
								}
	
								if (assign != null) {
									// Si se encuentra una asignación
									// se trae el tipo de asignatario
									if (assign.getRequests().getLegateesTypes() != null) {
										legateesTypes = assign.getRequests()
												.getLegateesTypes();
	
										Long legateeTypeId = legateesTypes
												.getLgtCodigo();
										Long locationTypeId = locationsTypes
												.getLctCodigo();
	
										// se consulta la tabla de parametros donde
										// se
										// especifica que tipos de seguros aplican
										// segun
										// el tipo de ubicación el tipo de
										// asignatario
	
										if (locationTypeId != 1L
												&& legateeTypeId >= 5L) {
											List<ControlAssignationPolicies> caps = new SearchPolicyAssignementTypeControl()
													.consultarTodosCAPPorFiltro(
															legateeTypeId,
															locationTypeId);
	
											if (caps == null || caps.size() == 0) {
												throw new GWorkException(
														Util
																.loadErrorMessageValue("ERROR.CAPTRANSNF"));
											}
	
											boolean paramAplica = false;
	
											// Se busca si para el tipo de
											// asignatario y
											// de ubicacion se encuentra registrada
											// el
											// tipo de póliza al que se va a
											// adicionar el vehiculo
											for (ControlAssignationPolicies cap : caps) {
												// id del tipo de poliza que está
												// registrada en el parametro
												Long pltCodigo = cap
														.getPoliciesTypes()
														.getPltCodigo();
	
												// id del tipo de poliza que fue
												// seleccionada desde la vista
												Long pltCodigoVista = policies
														.getPoliciesTypes()
														.getPltCodigo();
	
												if (pltCodigo.longValue() == pltCodigoVista
														.longValue()) {
													paramAplica = true;
												}
											}
											if (!paramAplica) {
												agregarInconsistenciaLista(
														policies,
														TiposInconsis
																.getVHC_POLIZA_DIFERENTE(),
														Login, valorIva,
														valorPrima, valorTotal,
														valorAsegurado,
														placaVehiculo,
														legateesTypes,
														pvsCarnetAsignatario,
														policiesInvoice);
											}
										}
									}
								}
							}
						}
					}
				}
			}
			if (CantidadInConsistencias > 0) {
				EntityManagerHelper.getEntityManager().getTransaction().begin();
				EntityManagerHelper.getEntityManager().getTransaction().commit();
				// Util.limpiarSession();
			}
		}catch(Exception e){
			log.error(e.getMessage(), e);
			throw new GWorkException(e.getMessage(), e);
		}
	}

	public void agregarInconsistenciaLista(Policies poliza,
			Long tipoInconsistencia, String usrLogin, Float iva, Float prima,
			Float total, Float valorAsegurado, String placa,
			LegateesTypes legateesTypes, String pvsCarnet,
			PoliciesInvoice invoicePolicy) throws GWorkException {
		Inconsistencies inconsistencies = new Inconsistencies();
		inconsistencies.setIncEstado(1L);
		inconsistencies.setIncFechaCargue(new Date());
		inconsistencies.setUsrLogin(usrLogin);
		inconsistencies.setMonthTransacType(new SearchMonthlyTransac()
				.consultarMonthTransacType(3L));
		InconsistenciesTypes inconsistenciesTypes = new InconsistenciesTypeServiceImpl()
				.consultarTipoInconsistenciaPorId(tipoInconsistencia);
		inconsistencies.setInconsistenciesTypes(inconsistenciesTypes);

		inconsistencies.setPoliciesInvoice(invoicePolicy);
		inconsistencies.setVhcPlaca(placa);
		inconsistencies.setIncValorIva(iva.floatValue());
		inconsistencies.setIncValorPrima(prima);
		inconsistencies.setIncValorTotal(total);
		inconsistencies.setIncValorAsegurado(valorAsegurado);
		inconsistencies.setLegateesTypes(legateesTypes);
		inconsistencies.setPvsCarnetAsignatario(pvsCarnet);

		new InconsistenciesDAO().save(inconsistencies);
	}

	public void GuardarFacturaAp(List<PoliciesVehicles> listaPoliVehicles,
			List<Inconsistencies> listaInconsistencias,
			PoliciesInvoice policiesInvoice, String Login,
			Long valorTotalFactura) throws GWorkException {
		try {
			
			//En algunos casos viene la lista de inconsistencias en null
			//Se inicia en coleccion vacia para que las iteraciones no lancen NullPointerException
			//No se cambia desde la consulta debido a que se desconoce el impacto de realizarlo
			
			listaInconsistencias = listaInconsistencias==null?new ArrayList<Inconsistencies>():listaInconsistencias;
			
			for (Inconsistencies inconsistencies : listaInconsistencias) {
				String PlacaInconsistencia = inconsistencies.getVhcPlaca()
						.toString();

				for (PoliciesVehicles policiesVehicles : listaPoliVehicles) {
					String PlacaPolicesVehicle = policiesVehicles.getVehicles()
							.getVhcPlacaDiplomatica().toString();

					if (PlacaInconsistencia.equals(PlacaPolicesVehicle)) {
						listaPoliVehicles.remove(policiesVehicles);
						break;
					}
				}
			}

			valorTotalFactura = CalcularTotalFactura(listaPoliVehicles,
					listaInconsistencias);

			Util.validarSession();

			InvoiceType IntCodigo = new InvoiceTypeDAO().findByIntNombre(
					"STANDARD").get(0);

			Connection connection = new GenerateInvoicesProofInsuranceImpl()
					.generarComprobanteDetalle(listaPoliVehicles,
							listaInconsistencias, policiesInvoice, Login,
							IntCodigo, valorTotalFactura);

			policiesInvoice.setPinCargado(ParametersUtil.FacturaEnviadaAP
					.longValue());

			new PoliciesInvoiceDAO().update(policiesInvoice);

			EntityManagerHelper.getEntityManager().getTransaction().begin();
			EntityManagerHelper.getEntityManager().getTransaction().commit();

			if (connection != null)
				connection.commit();

			// Util.limpiarSession();

		} catch (Exception e) {
			// Util.limpiarSession();
			EntityManagerHelper.log(e.getMessage(), Level.INFO, e);
			throw new GWorkException(e.getMessage(), e);
		}
	}

	public void enviarMailArchivoPlano(String numFactura, Long NumeroPoliza,
			String TipoPoliza, String FechaVigencia, int CantRegistrosCargados,
			BigDecimal TotalValorFactura) throws GWorkException {

		Rolls rolls = new RollsDAO().findById(new Long(Util
				.loadParametersValue("MAIL.ADMINSEGUROS")));

		String server = Util.loadParametersValue("MAIL.HOST");
		String fromUser = rolls.getRlsMail();
		String toUser = rolls.getRlsMail();
		String subject = Util.loadParametersValue("MSJ.SEGURO.ARCHIVO");
		String body = "<br /><br /><br />Cordial saludo, "
				+ "<br /><br /> Se le informa que el archivo plano ha sido "
				+ "cargado y verificado correctamente. "
				+ "<br /><br /> Factura numero: " + numFactura
				+ "<br /> Póliza Nro. " + NumeroPoliza
				+ "<br /> Tipo de póliza: " + TipoPoliza
				+ "<br /> Vigencia Póliza: " + FechaVigencia
				+ "<br /> Registros Cargados: " + CantRegistrosCargados
				+ "<br /> Valor Total Factura: " + TotalValorFactura;

		SendEmail mail = new SendEmail(toUser, fromUser, server, "false",
				subject, body);

		if (mail.SendMessage().equals("SUCCESS"))
			log.info("Mensaje enviado exitosamente");
		else {
			log.info("Eror Enviando el mensaje");
			throw new GWorkException(Util
					.loadErrorMessageValue("NOTIFICACION.ERROR"));
		}
	}

	public void enviarMailErrorArchivoPlano(String Error) throws GWorkException {

		Rolls rolls = new RollsDAO().findById(new Long(Util
				.loadParametersValue("MAIL.ADMINSEGUROS")));

		String server = Util.loadParametersValue("MAIL.HOST");
		String fromUser = rolls.getRlsMail();
		String toUser = rolls.getRlsMail();
		String subject = Util.loadParametersValue("MSJ.SEGURO.ARCHIVO");
		String body = "<br /><br /><br />Cordial saludo, "
				+ "<br /><br /> Se le informa que el archivo plano no ha sido "
				+ "cargado y verificado correctamente."
				+ "<br /><br /> por favor notificar el siguiente ERROR: "
				+ Error;

		SendEmail mail = new SendEmail(toUser, fromUser, server, "false",
				subject, body);

		if (mail.SendMessage().equals("SUCCESS"))
			log.info("Mensaje enviado exitosamente");
		else {
			log.info("Eror Enviando el mensaje");
			throw new GWorkException(Util
					.loadErrorMessageValue("NOTIFICACION.ERROR"));
		}
	}

	public void CorregirInconsistencias(Inconsistencies inconsistencies,
			String incObservacion) throws GWorkException {
		try {
			Util.validarSession();

			inconsistencies.setIncEstado(0L);
			inconsistencies.setIncObservacion(incObservacion);

			new InconsistenciesDAO().update(inconsistencies);

			EntityManagerHelper.getEntityManager().getTransaction().begin();
			EntityManagerHelper.getEntityManager().getTransaction().commit();
			// Util.limpiarSession();
		} catch (Exception e) {
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.GUARDAROBSERVACIONES"));
		}
	}

	public Long CalcularTotalFactura(List<PoliciesVehicles> listaPoliVehicles,
			List<Inconsistencies> listaInconsistencias) {

		Long valorTotalFactura = 0L;
		String PlacaDiplomaticaTemporal = null;
		String PlacaDiplomatica = null;
		for (PoliciesVehicles policiesVehicles : listaPoliVehicles)
			valorTotalFactura += policiesVehicles.getPvsValorTotal()
					.longValue();

		for (Inconsistencies inconsistencies : listaInconsistencias) {
			PlacaDiplomatica = inconsistencies.getVhcPlaca().toString();

			if (!PlacaDiplomatica.equals(PlacaDiplomaticaTemporal))
				valorTotalFactura += inconsistencies.getIncValorTotal()
						.longValue();

			PlacaDiplomaticaTemporal = PlacaDiplomatica;
		}

		return valorTotalFactura;
	}

	public Long CalcularTotalFull(List<PoliciesVehicles> listaPoliVehicles,
			List<Inconsistencies> listaInconsistencias) {

		Long valorTotalFactura = 0L;
		String PlacaDiplomaticaTemporal = null;
		String PlacaDiplomatica = null;
		for (PoliciesVehicles policiesVehicles : listaPoliVehicles)
			valorTotalFactura += policiesVehicles.getPvsValorTotal()
					.longValue();

		for (Inconsistencies inconsistencies : listaInconsistencias) {
			PlacaDiplomatica = inconsistencies.getVhcPlaca().toString();

			if (!PlacaDiplomatica.equals(PlacaDiplomaticaTemporal))
				valorTotalFactura += inconsistencies.getIncValorTotal()
						.longValue();

			PlacaDiplomaticaTemporal = PlacaDiplomatica;
		}

		return valorTotalFactura;
	}

	/**
	 * @return the soluciones
	 */
	public int getSoluciones() {
		return soluciones;
	}

	/**
	 * @param soluciones
	 *            the soluciones to set
	 */
	public void setSoluciones(int soluciones) {
		this.soluciones = soluciones;
	}

	public void crearFacturaPolizaSOAT(List<VOPolicies> listPolicesVehicles,
			String numeroFactura, Date fechaFactura, String conceptoFact,
			String login) throws GWorkException {

		// valida que haya sesion de un usuario
		if (login == null || login.trim().length() == 0) {
			throw new GWorkException(Util
					.loadErrorMessageValue("USUARIO.SESIONEXP"));
		}
		PoliciesInvoice policiesInvoice = null;

		policiesInvoice = new SearchPolicies()
				.consultarFacturaPorNumeroFactura(numeroFactura);

		if (policiesInvoice != null) {
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.FACTURAEXISTE"));
		}

		try {

			// Creacion de factura
			policiesInvoice = new PoliciesInvoice();
			policiesInvoice.setPinNumeroFactura(numeroFactura);
			policiesInvoice.setPinFechaFactura(fechaFactura);
			policiesInvoice.setPinConcepto(conceptoFact);
			policiesInvoice.setPinCargado(0L);

			// Crear referencia de cada una de las polizas de vehiculos a la
			// factura creada
			for (VOPolicies policiesVO : listPolicesVehicles) {

				PoliciesVehicles policiesVehicles = new PoliciesVehicles();
				policiesVehicles = SearchPolicies.polizaSoatByPlaca(policiesVO
						.getVehicles().getVhcCodigo(), policiesVO.getPolicies()
						.getPlsCodigo());

				policiesVehicles.setPoliciesInvoice(policiesInvoice);
				new PoliciesVehiclesDAO().update(policiesVehicles);

			}

			new PoliciesInvoiceDAO().save(policiesInvoice);

			PoliciesInvoiceHistoric invoiceHistoric = new PoliciesInvoiceHistoric();

			invoiceHistoric.setPihFecha(new Date());
			invoiceHistoric.setUsrLogin(login);
			invoiceHistoric.setPinNumeroFactura(numeroFactura);
			invoiceHistoric.setPinFechaFactura(fechaFactura);
			invoiceHistoric.setPinConcepto(conceptoFact);
			invoiceHistoric.setPinCargado(0L);

			new PoliciesInvoiceHistoricDAO().save(invoiceHistoric);

			// confimar la transaccion
			EntityManagerHelper.getEntityManager().getTransaction().begin();
			EntityManagerHelper.getEntityManager().getTransaction().commit();

		} catch (Exception e) {

			if (EntityManagerHelper.getEntityManager().getTransaction()
					.isActive())
				EntityManagerHelper.getEntityManager().getTransaction()
						.rollback();
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.GUARDARFACPOL"));
		}

	}

	/**
	 * Modifica las facturas creadas de tipo SOAT
	 */

	public void modificarFacturaPolizaSOAT(
			List<VOPolicies> listPolicesVehicles, String numeroFactura,
			String nuevoNumeroFactura, Date fechaFactura, String conceptoFact,
			String login) throws GWorkException {

		// valida que haya sesion de un usuario
		if (login == null || login.trim().length() == 0) {
			throw new GWorkException(Util
					.loadErrorMessageValue("USUARIO.SESIONEXP"));
		}
		PoliciesInvoice policiesInvoice = null;

		policiesInvoice = new SearchPolicies()
				.consultarFacturaPorNumeroFactura(nuevoNumeroFactura);

		if (policiesInvoice != null)
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.FACTURAEXISTE.MOD"));

		try {

			// Se consulta el objeto poliza factura para ser modificada
			policiesInvoice = new PoliciesInvoice();
			policiesInvoice = new SearchPolicies()
					.consultarFacturaPorNumeroFactura(numeroFactura);

			if (nuevoNumeroFactura != null
					&& nuevoNumeroFactura.trim().length() > 0)
				policiesInvoice.setPinNumeroFactura(nuevoNumeroFactura);
			else
				policiesInvoice.setPinNumeroFactura(numeroFactura);
			policiesInvoice.setPinFechaFactura(fechaFactura);
			policiesInvoice.setPinConcepto(conceptoFact);
			policiesInvoice.setPinCargado(0L);

			// Modificar referencia de polizas de vehiculos para la factura de
			// SOAT
			for (PoliciesVehicles policiesVH : policiesInvoice
					.getPoliciesVehicleses()) {

				PoliciesVehicles policiesVehicles = new PoliciesVehicles();
				policiesVehicles = SearchPolicies.polizaSoatByPlaca(policiesVH
						.getVehicles().getVhcCodigo(), policiesVH.getPolicies()
						.getPlsCodigo());

				policiesVehicles.setPoliciesInvoice(null);
				new PoliciesVehiclesDAO().update(policiesVehicles);

			}

			// Crea la nueva relacion de las polizas de vehiculo para la factura
			// de SOAT
			for (VOPolicies policiesVO : listPolicesVehicles) {

				PoliciesVehicles policiesVehicles = new PoliciesVehicles();
				policiesVehicles = SearchPolicies.polizaSoatByPlaca(policiesVO
						.getVehicles().getVhcCodigo(), policiesVO.getPolicies()
						.getPlsCodigo());

				policiesVehicles.setPoliciesInvoice(policiesInvoice);
				new PoliciesVehiclesDAO().update(policiesVehicles);

			}

			// Se actualiza el objeto factura Poliza de tipo SOAT
			new PoliciesInvoiceDAO().update(policiesInvoice);

			// confimar la transaccion
			EntityManagerHelper.getEntityManager().getTransaction().begin();
			EntityManagerHelper.getEntityManager().getTransaction().commit();

		} catch (Exception e) {

			if (EntityManagerHelper.getEntityManager().getTransaction()
					.isActive())
				EntityManagerHelper.getEntityManager().getTransaction()
						.rollback();
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.GUARDARFACPOL"));
		}

	}

	public List<PoliciesInvoice> consultarFacturasSOAT() throws GWorkException {
		return SearchPolicies.consultarFacturasSOAT();
	}
	
	/**
	 * Permite adicionar datos en las tablas POLICIES_VEHICLES y POLICIES_VEHICLES_HISTORIC 
	 * para una transacción mensual
	 * 
	 * @modifyby José Álvaro Hincapié Castillo
	 * 
	 * @param idTipoTransacMensual
	 * @param placaVehiculo
	 * @param idTipoPoliza
	 * @param plcFechaInicioPol
	 * @param plsNumero
	 * @param valorPrima
	 * @param valorIva
	 * @param valorAsegurado
	 * @param numeroFactura
	 * @param login
	 * @param legateesTypes
	 * @param pvsCarnetAsignatario
	 * @param estado
	 * @param transaccionPantalla
	 * @throws GWorkException
	 */
	@SuppressWarnings("null")
	public void adicionVehiculoPoliza(Long idTipoTransacMensual,
			String placaVehiculo, Long idTipoPoliza, Date plcFechaInicioPol,
			Long plsNumero, Float valorPrima, Float valorIva,
			Float valorAsegurado, String numeroFactura, String login,
			LegateesTypes legateesTypes, String pvsCarnetAsignatario,
			Long estado, Long transaccionPantalla) throws GWorkException {
		
		PoliciesInvoice policiesInvoice = null;
		// Se consulta si existe el vehiculo con la placa especificada
		Vehicles vehicles = SearchVehicles
				.consultarVehiculosPorPlacaSinFiltros(placaVehiculo);

		if (vehicles == null) {
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.VHC.NO.EXISTETMP"));
		}

		// Se consulta el tipo de poliza especificado
		Policies pol = new SearchPolicies().consultarPoliza(plsNumero);

		if (pol == null) {
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.POLINOEXISTE"));
		}

		Long NumFactura = 0L;
		if (numeroFactura != null) {
			NumFactura = new Long(numeroFactura);

			// Consulta la factura a la que se ha creado la nueva poliza
			policiesInvoice = new SearchPolicies()
					.consultarFacturaPorNumeroFactura(numeroFactura);
		}
		
		new PoliciesTransactionsServiceImpl().aplicaParametros(placaVehiculo, pol, vehicles, NumFactura.longValue());

		MonthTransacType monthTransacType = new SearchMonthlyTransac()
				.consultarMonthTransacType(idTipoTransacMensual);

		if (monthTransacType == null) {
			throw new GWorkException(Util
					.loadErrorMessageValue("search.not.found"));
		}

		PoliciesVehicles policiesVehicles = null;
		PoliciesVehiclesId id = new PoliciesVehiclesId();

		VehiclesAssignation m = SearchVehicles
				.consultarAsignacionVehiculo(placaVehiculo);

		if (m != null) {
			legateesTypes = m.getRequests().getLegateesTypes();
			pvsCarnetAsignatario = m.getRequests().getRqsCarnetAsignatario();
		}

		if (legateesTypes == null) {
			legateesTypes = new LegateesTypesDAO().findById(8L);
		}

		try {
			id.setPlsCodigo(pol.getPlsCodigo());
			id.setVhcCodigo(vehicles.getVhcCodigo());
			policiesVehicles = new PoliciesVehiclesDAO().findById(id);
			if (policiesVehicles == null) {
				policiesVehicles = new PoliciesVehicles();
				policiesVehicles.setId(id);
				policiesVehicles.setMonthTransacType(monthTransacType);
				policiesVehicles.setPolicies(pol);
				policiesVehicles.setVehicles(vehicles);
				policiesVehicles.setPvsFechaini(plcFechaInicioPol);
				policiesVehicles.setPvsFechafin(pol.getPlsFechafinCobertura());
				policiesVehicles.setPvsNumeroFactura(numeroFactura);
				policiesVehicles.setPvsEstado(estado);
				policiesVehicles.setPvsValorAsegurado(valorAsegurado);
				policiesVehicles.setPvsValorIva(valorIva);
				policiesVehicles.setPvsValorPrima(valorPrima);
				policiesVehicles.setPvsValorTotal(valorIva + valorPrima);
				policiesVehicles.setLegateesTypes(legateesTypes);
				policiesVehicles.setPvsCarnetAsignatario(pvsCarnetAsignatario);
				if (policiesInvoice != null)
					policiesVehicles.setPoliciesInvoice(policiesInvoice);
				new PoliciesVehiclesDAO().save(policiesVehicles);
				
				PoliciesVehicles pVExclusion = null;//poliza de vehiculo excluyente
				PoliciesVehiclesId idExclusion = new PoliciesVehiclesId();//id poliza de vehiculo excluyente
								
				List<PoliciesVehicles> listaPVExcluyentes = new SearchPoliciesVehicles().consultarPVs(placaVehiculo);

				if (listaPVExcluyentes != null || listaPVExcluyentes.size() > 0) {
			
					for (PoliciesVehicles pove : listaPVExcluyentes) {
						
						if (policiesVehicles.getPolicies().getPoliciesTypes().getPltCodigo().toString().equals(ParametersUtil.AMPAROS_BASICOS.toString())
								&& pove.getPolicies().getPoliciesTypes().getPltCodigo().toString().equals(ParametersUtil.FULL_COBERTURA.toString())) {
							idExclusion.setPlsCodigo(pove.getPolicies().getPlsCodigo());
							idExclusion.setVhcCodigo(vehicles.getVhcCodigo());
							pVExclusion = new PoliciesVehiclesDAO().findById(idExclusion);
							new PoliciesVehiclesDAO().delete(pVExclusion);
						} else if(policiesVehicles.getPolicies().getPoliciesTypes().getPltCodigo().toString().equals(ParametersUtil.FULL_COBERTURA.toString())
								&& pove.getPolicies().getPoliciesTypes().getPltCodigo().toString().equals(ParametersUtil.AMPAROS_BASICOS.toString())) {
							idExclusion.setPlsCodigo(pove.getPolicies().getPlsCodigo());
							idExclusion.setVhcCodigo(vehicles.getVhcCodigo());
							pVExclusion = new PoliciesVehiclesDAO().findById(idExclusion);
							new PoliciesVehiclesDAO().delete(pVExclusion);
						}
					}
				}
			} else {
				policiesVehicles.setId(id);
				policiesVehicles.setMonthTransacType(monthTransacType);
				policiesVehicles.setPolicies(pol);
				policiesVehicles.setVehicles(vehicles);
				policiesVehicles.setPvsFechaini(plcFechaInicioPol);
				policiesVehicles.setPvsFechafin(pol.getPlsFechafinCobertura());
				policiesVehicles.setPvsNumeroFactura(numeroFactura);
				policiesVehicles.setPvsEstado(estado);
				policiesVehicles.setPvsValorAsegurado(valorAsegurado);
				policiesVehicles.setPvsValorIva(valorIva);
				policiesVehicles.setPvsValorPrima(valorPrima);
				policiesVehicles.setPvsValorTotal(valorIva + valorPrima);
				policiesVehicles.setLegateesTypes(legateesTypes);
				policiesVehicles.setPvsCarnetAsignatario(pvsCarnetAsignatario);
				new PoliciesVehiclesDAO().update(policiesVehicles);
			}

			// Se guarda el historico
			PoliciesVehiclesHistoric pvh = new PoliciesVehiclesHistoric();

			pvh.setMonthTransacType(monthTransacType);
			pvh.setPinNumeroFactura(numeroFactura);
			pvh.setPlsCodigo(pol.getPlsCodigo());
			pvh.setPvhFecha(new Date());
			pvh.setPvsEstado(estado);
			pvh.setPvsFechafin(pol.getPlsFechafinCobertura());
			pvh.setPvsFechaini(new Date());
			pvh.setPvsValorIva(valorIva);
			pvh.setPvsValorPrima(valorPrima);
			pvh.setPvsValorTotal(valorIva + valorPrima);
			pvh.setPvsValorAsegurado(valorAsegurado);
			pvh.setUsrLogin(login);
			pvh.setVhcCodigo(vehicles.getVhcCodigo());

			new PoliciesVehiclesHistoricDAO().save(pvh);

		} catch (RuntimeException e) {
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.GUARDARADICION"));
		}
	}
	
	public void run() {
		
		try {
			GuardarFacturaAp(
					listaPoliVehicles, listaInconsistencias, 
					policiesInvoice, Login, valorTotalFactura);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		
	}

}