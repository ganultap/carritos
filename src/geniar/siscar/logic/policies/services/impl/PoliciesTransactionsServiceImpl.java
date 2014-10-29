/**
 * 
 */
package geniar.siscar.logic.policies.services.impl;

import geniar.siscar.logic.consultas.SearchMonthlyTransac;
import geniar.siscar.logic.consultas.SearchPolicies;
import geniar.siscar.logic.consultas.SearchPoliciesVehicles;
import geniar.siscar.logic.consultas.SearchVehicles;
import geniar.siscar.logic.policies.services.PoliciesTransactionsService;
import geniar.siscar.model.Inconsistencies;
import geniar.siscar.model.LegateesTypes;
import geniar.siscar.model.MonthTransacType;
import geniar.siscar.model.Policies;
import geniar.siscar.model.PoliciesHistoric;
import geniar.siscar.model.PoliciesInvoice;
import geniar.siscar.model.PoliciesVehicles;
import geniar.siscar.model.PoliciesVehiclesHistoric;
import geniar.siscar.model.PoliciesVehiclesId;
import geniar.siscar.model.Vehicles;
import geniar.siscar.model.VehiclesAssignation;
import geniar.siscar.persistence.EntityManagerHelper;
import geniar.siscar.persistence.LegateesTypesDAO;
import geniar.siscar.persistence.PoliciesDAO;
import geniar.siscar.persistence.PoliciesHistoricDAO;
import geniar.siscar.persistence.PoliciesVehiclesDAO;
import geniar.siscar.persistence.PoliciesVehiclesHistoricDAO;
import geniar.siscar.util.ParametersUtil;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @author Geniar
 * 
 */
public class PoliciesTransactionsServiceImpl implements
		PoliciesTransactionsService {

	/*
	 * (non-Javadoc)
	 * 
	 * @see geniar.siscar.logic.policies.services.PoliciesTransactionsService
	 *      #adicionVehiculoPoliza(java.lang.Long, java.lang.String,
	 *      java.lang.Long, java.util.Date, java.lang.Long)
	 */

	public List<PoliciesVehicles> ListaPolizasStandar = new ArrayList<PoliciesVehicles>();
	public List<PoliciesVehicles> ListaPolizasCredito = new ArrayList<PoliciesVehicles>();
	private Float valorFacturaStandar = 0F;
	private Float valorFacturaCredito = 0F;

	@SuppressWarnings("null")
	public void adicionVehiculoPoliza(Long idTipoTransacMensual,
			String placaVehiculo, Long idTipoPoliza, Date plcFechaInicioPol,
			Long plsNumero, Float valorPrima, Float valorIva,
			Float valorAsegurado, String numeroFactura, String login,
			LegateesTypes legateesTypes, String pvsCarnetAsignatario,
			Long estado, Long transaccionPantalla) throws GWorkException {

		Util.validarSession();
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

		aplicaParametros(placaVehiculo, pol, vehicles, NumFactura.longValue());

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

			if (transaccionPantalla.longValue() == ParametersUtil.ADICION_POLIZA || transaccionPantalla.longValue() == ParametersUtil.NOVEDAD_POLIZA) {
				//TODO:YYY
				EntityManagerHelper.getEntityManager().getTransaction().begin();
				EntityManagerHelper.getEntityManager().getTransaction()
						.commit();
			}

			// Util.limpiarSession();
		} catch (RuntimeException e) {
			// Util.limpiarSession();
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.GUARDARADICION"));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see geniar.siscar.logic.policies.services.
	 *      PoliciesTransactionsService#retiroVehiculoPoliza()
	 */
	public void retiroVehiculoPoliza(Long plsNumero, String placaVehiculo,
			Long idTipoTransac, String login, Date plcFechaFinPolVhc) throws GWorkException {
		Util.validarSession();
		if (plsNumero != null) {
			// Se consulta el vehiculo
			Vehicles vehicles = SearchVehicles
					.consultarVehiculosPorPlacaSinFiltros(placaVehiculo);
			Long estado = vehicles.getVehiclesStates().getVhsCodigo();

			if (estado.longValue() == 2L || estado.longValue() == 4L
					|| estado.longValue() == 5L) {
				// Se traen las polizas del vehiculo

				PoliciesVehicles pv = new SearchPoliciesVehicles()
						.consultarPolizaVehiculo(placaVehiculo, plsNumero);

				if (pv == null) {
					throw new GWorkException(Util
							.loadErrorMessageValue("ERROR.GUARDAR"));
				}

				MonthTransacType transacType = new SearchMonthlyTransac()
						.consultarMonthTransacType(idTipoTransac);

				try {
					pv.setMonthTransacType(transacType);
					pv.setPvsEstado(0L);
					pv.setPvsFechafin(plcFechaFinPolVhc);
					
					Policies p = new SearchPolicies().consultarPoliza(pv
							.getPolicies().getPlsNumero());
					if (p.getPoliciesTypes().getPltCodigo().longValue() == 10L) {
						p.setPlsEstado(0L);
					}

					PoliciesHistoric historic = new PoliciesHistoric();
					historic.setPlhFecha(new Date());
					historic.setPlsDocDos(p.getPlsDocDos());
					historic.setPlsDocUno(p.getPlsDocUno());
					historic.setPlsEstado(p.getPlsEstado());
					historic.setPlsFechafinCobertura(plcFechaFinPolVhc);
					historic.setPlsFechainicioCobertura(p
							.getPlsFechainicioCobertura());
					historic.setPlsNumero(p.getPlsNumero());
					historic.setPoliciesTypes(p.getPoliciesTypes());
					historic.setUsrLogin(login);

					new PoliciesVehiclesDAO().update(pv);
					new PoliciesDAO().update(p);
					new PoliciesHistoricDAO().save(historic);

					// Se guarda el historico
					PoliciesVehiclesHistoric pvh = new PoliciesVehiclesHistoric();

					pvh.setMonthTransacType(transacType);
					pvh.setPlsCodigo(pv.getPolicies().getPlsCodigo());
					pvh.setPvhFecha(new Date());
					pvh.setPvsEstado(0L);
					pvh.setPvsFechafin(pv.getPvsFechafin());
					pvh.setPvsFechaini(pv.getPvsFechaini());
					pvh.setUsrLogin(login);
					pvh.setVhcCodigo(vehicles.getVhcCodigo());
					pvh.setPvsValorAsegurado(pv.getPvsValorAsegurado());
					pvh.setPvsValorIva(pv.getPvsValorIva());
					pvh.setPvsValorPrima(pv.getPvsValorPrima());
					pvh.setPvsValorTotal(pv.getPvsValorTotal());
					pvh.setVhcCodigo(pv.getVehicles().getVhcCodigo());
					pvh.setLegateesTypes(pv.getLegateesTypes());
					pvh.setPvsCarnetAsignatario(pv.getPvsCarnetAsignatario());

					new PoliciesVehiclesHistoricDAO().save(pvh);
					EntityManagerHelper.getEntityManager().getTransaction()
							.begin();
					EntityManagerHelper.getEntityManager().getTransaction()
							.commit();
					// Util.limpiarSession();
				} catch (RuntimeException e) {
					// Util.limpiarSession();
					throw new GWorkException(Util
							.loadErrorMessageValue("ERROR.GUARDAR"));
				}
			} else
				throw new GWorkException(Util
						.loadErrorMessageValue("ERROR.ESTADOVHC"));

		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see geniar.siscar.logic.policies.services.
	 *      PoliciesTransactionsService#trasladoTipoPoliza()
	 */
	public void trasladoTipoPoliza(Long idTipoTransacMensual,
			String placaVehiculo, Date plcFechaInicioPolVhc,
			Date plcFechaFinPolVhc, Long idPolizaRetirada,
			Policies polizaAsociada, String login, PoliciesVehicles newPV,
			Float plsValorPrima, Float plsValorContribucion,
			Float plsValorTotal, Float plsValorAseg) throws GWorkException {

		Vehicles vehicles = SearchVehicles
				.consultarVehiculosPorPlacaSinFiltros(placaVehiculo);

		// Se consulta la poliza la cual se va a reitirar
		Policies polizaRetirada = new PoliciesDAO().findById(idPolizaRetirada);

		if (vehicles == null) {
			throw new GWorkException(Util
					.loadErrorMessageValue("PLACA.NOEXISTE"));
		}

		MonthTransacType monthTransacType = new SearchMonthlyTransac()
				.consultarMonthTransacType(idTipoTransacMensual);

		PoliciesVehicles pv = new SearchPoliciesVehicles()
				.consultarPolizaVehiculo(placaVehiculo, polizaRetirada
						.getPlsNumero());

		if (pv == null) {
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.GUARDAR"));
		}

		try {
			// Se retira el vehiculo de la póliza
			Util.validarSession();
			new PoliciesVehiclesDAO().delete(pv);

			// Se guarda el historico
			PoliciesVehiclesHistoric pvh = new PoliciesVehiclesHistoric();

			pvh.setMonthTransacType(monthTransacType);
			pvh.setPlsCodigo(polizaRetirada.getPlsCodigo());
			pvh.setPvhFecha(new Date());
			pvh.setPvsEstado(pv.getPvsEstado());
			pvh.setPvsFechafin(polizaRetirada.getPlsFechafinCobertura());
			pvh.setPvsFechaini(plcFechaInicioPolVhc);
			pvh.setUsrLogin(login);
			pvh.setVhcCodigo(vehicles.getVhcCodigo());
			pvh.setLegateesTypes(pv.getLegateesTypes());
			if (pv.getPvsNumeroFactura() != null)
				pvh.setPinNumeroFactura(pv.getPvsNumeroFactura());
			pvh.setPvsCarnetAsignatario(pv.getPvsCarnetAsignatario());
			pvh.setPvsValorAsegurado(pv.getPvsValorAsegurado());
			pvh.setPvsValorIva(pv.getPvsValorIva());
			pvh.setPvsValorPrima(pv.getPvsValorPrima());
			pvh.setPvsValorTotal(pv.getPvsValorTotal());

			new PoliciesVehiclesHistoricDAO().save(pvh);

			// Se asocia el vehiculo a la nueva poliza
			PoliciesVehicles pv1 = EntityManagerHelper.getEntityManager()
					.merge(newPV);

			vehicles = SearchVehicles
					.consultarVehiculosPorPlacaSinFiltros(placaVehiculo);
			//
			// aplicaParametros(placaVehiculo, polizaAsociada, vehicles, new
			// Long(
			// newPV.getPvsNumeroFactura()));

			new PoliciesVehiclesDAO().save(pv1);

			// Se guarda el historico
			PoliciesVehiclesHistoric pvh2 = new PoliciesVehiclesHistoric();

			pvh2.setMonthTransacType(monthTransacType);
			pvh2.setPlsCodigo(polizaAsociada.getPlsCodigo());
			pvh2.setPvhFecha(new Date());
			pvh2.setPvsEstado(pv1.getPvsEstado());
			pvh2.setPvsFechafin(polizaAsociada.getPlsFechafinCobertura());
			pvh2.setPvsFechaini(newPV.getPvsFechaini());
			pvh2.setUsrLogin(login);
			pvh2.setVhcCodigo(vehicles.getVhcCodigo());
			pvh2.setLegateesTypes(pv1.getLegateesTypes());
			pvh2.setPinNumeroFactura(pv1.getPvsNumeroFactura());
			pvh2.setPvsCarnetAsignatario(pv1.getPvsCarnetAsignatario());
			pvh2.setPvsValorAsegurado(pv1.getPvsValorAsegurado());
			pvh2.setPvsValorIva(pv1.getPvsValorIva());
			pvh2.setPvsValorPrima(pv1.getPvsValorPrima());
			pvh2.setPvsValorTotal(pv1.getPvsValorTotal());

			new PoliciesVehiclesHistoricDAO().save(pvh2);

			EntityManagerHelper.getEntityManager().getTransaction().begin();
			EntityManagerHelper.getEntityManager().getTransaction().commit();
			// Util.limpiarSession();
		} catch (GWorkException e) {
			// Util.limpiarSession();
			throw new GWorkException(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see geniar.siscar.logic.policies.services.PoliciesTransactionsService
	 *      #consultarPolizasVehiculo(java.lang.String)
	 */
	public List<Policies> consultarPolizasVehiculo(String placaVehiculo)
			throws GWorkException {

		List<Policies> listaPolizasVHC = new ArrayList<Policies>();

		Vehicles vehicles = SearchVehicles
				.consultarVehiculosPorPlacaSinFiltros(placaVehiculo);

		if (vehicles == null) {
			throw new GWorkException(Util
					.loadErrorMessageValue("PLACA.NOEXISTE"));
		}

		if (vehicles.getPoliciesVehicleses() == null
				|| vehicles.getPoliciesVehicleses().isEmpty()) {
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.NOPOLIASOCIADAS"));
		}

		Iterator<PoliciesVehicles> iterPV = vehicles.getPoliciesVehicleses()
				.iterator();
		while (iterPV.hasNext()) {
			PoliciesVehicles objPV = (PoliciesVehicles) iterPV.next();
			if (objPV.getPvsEstado() == 1) {
				listaPolizasVHC.add(objPV.getPolicies());
			}
		}
		return listaPolizasVHC;
	}

	/**
	 * Valida que la poliza que se ingresa para un vehiculo<br>
	 * aplique en terminos de ubicación y tipo de asignatario.
	 * 
	 * @param placaVehiculo
	 *            Placa del Vehiculo.
	 * @param policies
	 *            Poliza.
	 * @param vehicles
	 *            Vehiculo
	 * @throws GWorkException
	 *             Manejador de excepciones.
	 */
	@SuppressWarnings("null")
	public void aplicaParametros(String placaVehiculo, Policies policies,
			Vehicles vehicles, Long NumFactura) throws GWorkException {

		List<PoliciesVehicles> listPVs = new SearchPoliciesVehicles()
				.consultarPVs(placaVehiculo);

		if (listPVs != null || listPVs.size() > 0) {
			for (PoliciesVehicles policiesVehicles : listPVs) {

				// se pregunta si el vehiculo ya tiene una poliza del tipo
				// al que se va a adicionar.
				if (policiesVehicles.getPvsValorTotal().longValue() > 0) {
					if (policies.getPlsNumero().longValue() != NumFactura
							.longValue())
						if (policiesVehicles.getPolicies().getPoliciesTypes()
								.getPltCodigo().longValue() == policies
								.getPoliciesTypes().getPltCodigo().longValue()) {
							// si ya tiene una pòliza de ese tipo se genera un
							// excepción
								throw new GWorkException(
									Util.loadErrorMessageValue("ERROR.POLIGUALNUEVA")
											+ " :" + placaVehiculo);
						} else if (policiesVehicles.getPolicies()
								.getPoliciesTypes().getPltCodigo().longValue() == ParametersUtil.AMPAROS_BASICOS
								|| policiesVehicles.getPolicies()
										.getPoliciesTypes().getPltCodigo()
										.longValue() == ParametersUtil.FULL_COBERTURA)
							throw new GWorkException(
									Util
											.loadErrorMessageValue("ERROR.POLIZA.EXLCUYENTE")
											+ " :"
											+ policiesVehicles.getVehicles()
													.getVhcPlacaDiplomatica()
											+ " - "
											+ policiesVehicles.getPolicies()
													.getPoliciesTypes()
													.getPltNombre());

				}
			}// el vehiculo no tiene el tipo de poliza que se va a agregar
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see geniar.siscar.logic.policies.services.PoliciesTransactionsService#
	 *      guardarNovedades(java.util.List, java.util.List, java.lang.String)
	 */
	public void guardarNovedades(List<Inconsistencies> lstIncs,
			List<PoliciesVehicles> lstPvs, String login) throws GWorkException {

	}

	public List<PoliciesVehicles> getListaPolizasStandar() {
		return ListaPolizasStandar;
	}

	public void setListaPolizasStandar(
			List<PoliciesVehicles> listaPolizasStandar) {
		ListaPolizasStandar = listaPolizasStandar;
	}

	public List<PoliciesVehicles> getListaPolizasCredito() {
		return ListaPolizasCredito;
	}

	public void setListaPolizasCredito(
			List<PoliciesVehicles> listaPolizasCredito) {
		ListaPolizasCredito = listaPolizasCredito;
	}

	public Float getValorFacturaStandar() {
		return valorFacturaStandar;
	}

	public void setValorFacturaStandar(Float valorFacturaStandar) {
		this.valorFacturaStandar = valorFacturaStandar;
	}

	public Float getValorFacturaCredito() {
		return valorFacturaCredito;
	}

	public void setValorFacturaCredito(Float valorFacturaCredito) {
		this.valorFacturaCredito = valorFacturaCredito;
	}

}
