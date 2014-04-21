package geniar.siscar.logic.billing.services.impl;

import geniar.siscar.consults.ConsultsService;
import geniar.siscar.consults.impl.ConsultsServiceImpl;
import geniar.siscar.logic.billing.services.PrepaidFuelProofService;
import geniar.siscar.logic.consultas.SearchVehicles;
import geniar.siscar.model.AccountingParameters;
import geniar.siscar.model.ActualOthersApplications;
import geniar.siscar.model.CurrencyTypes;
import geniar.siscar.model.HeaderProof;
import geniar.siscar.model.Period;
import geniar.siscar.model.ProofState;
import geniar.siscar.model.ProofType;
import geniar.siscar.model.ServiceRegistry;
import geniar.siscar.model.PrepaidConsumption;
import geniar.siscar.model.TransactionType;
import geniar.siscar.model.VOprepaidFuelProof;
import geniar.siscar.model.Prepaid;
import geniar.siscar.model.CostCentersFuel;
import geniar.siscar.model.Users;
import geniar.siscar.model.CostsCenters;
import geniar.siscar.model.Tariffs;
import geniar.siscar.model.VehiclesAssignation;
import geniar.siscar.persistence.AccountingParametersDAO;
import geniar.siscar.persistence.ActualOthersApplicationsDAO;
import geniar.siscar.persistence.CurrencyTypesDAO;
import geniar.siscar.persistence.EntityManagerHelper;
import geniar.siscar.persistence.HeaderProofDAO;
import geniar.siscar.persistence.PeriodDAO;
import geniar.siscar.persistence.PrepaidDAO;
import geniar.siscar.persistence.ProofStateDAO;
import geniar.siscar.persistence.ProofTypeDAO;
import geniar.siscar.persistence.TransactionTypeDAO;
import geniar.siscar.persistence.UsersDAO;
import geniar.siscar.util.ManipulacionFechas;
import geniar.siscar.util.ParametersUtil;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import javax.persistence.Query;

public class PrepaidFuelProofServiceImpl implements PrepaidFuelProofService {

	private ConsultsService consultsService = new ConsultsServiceImpl();

	/*
	 * (non-Javadoc)
	 * 
	 * @see geniar.siscar.logic.parameters.services.
	 *      PlainFileParameterService#consultarPlainFileParameter
	 *      (java.lang.Long, java.lang.Long)
	 */

	@SuppressWarnings("unchecked")
	public List<VOprepaidFuelProof> listprepaidFuelVehicles(Period periodo)
			throws GWorkException {
		try {
			List<VOprepaidFuelProof> listVOprepaidFuelProof = new ArrayList<VOprepaidFuelProof>();
			List<Prepaid> listPrepaid;

			/* Obtener los prepagos de la fecha actual */
			Date fecha_ini_actual;
			Date fecha_fin_actual;

			fecha_ini_actual = periodo.getPerFechaIni();
			fecha_fin_actual = periodo.getPerFechaFin();

			String sql_prepaid = "SELECT model FROM Prepaid model "
					+ "WHERE model.preFechaini <= :fecha_fin_actual "
					+ "AND model.preFechaFin >= :fecha_ini_actual "
					+ "AND model.headerProof_dev.hepId IS NULL "
					+ "ORDER BY model.preCodigo";

			javax.persistence.EntityManager entityAux = EntityManagerHelper
					.getEntityManager();
			entityAux.clear();

			Query query = entityAux.createQuery(sql_prepaid);
			query.setParameter("fecha_ini_actual", fecha_ini_actual);
			query.setParameter("fecha_fin_actual", fecha_fin_actual);

			listPrepaid = (List<Prepaid>) query.getResultList();

			if (listPrepaid != null && listPrepaid.size() > 0) {

				/* Obtener la tarifa vigente */
				Double tarifaVigente = getTarifaActual(periodo).getTrfValor()
						.doubleValue();

				for (Prepaid objPrepaid : listPrepaid) {
					if (objPrepaid.getPreCodigo() == 278)
						System.out.println("inicia prueba");

					/* verificamos que la placa tenga asignación OF, OFS o PR */
					String[] lgtCodigos = new String[] {
							Util.loadParametersValue("BAC_VHC_OF"),
							Util.loadParametersValue("BAC_VHC_OFS"),
							Util.loadParametersValue("BAC_VHC_PRG"),
							Util.loadParametersValue("BAC_VHC_PRY"),
							Util.loadParametersValue("BAC_VHS_CNV") };

					VehiclesAssignation vehiclesAssignation = SearchVehicles
							.consultarAsignacionVehiculo(objPrepaid
									.getPrePlaca().trim().toUpperCase(),
									lgtCodigos);

					if (vehiclesAssignation != null) {

						/*
						 * Consulta inicial que busca los registros de servicio
						 * relacionados con el prepago
						 */
						String sql_serReg = "SELECT model FROM ServiceRegistry model "
								+ "WHERE model.prepaid.preCodigo = :preCodigo ";

						entityAux = null;
						entityAux = EntityManagerHelper.getEntityManager();
						entityAux.clear();

						query = null;
						query = entityAux.createQuery(sql_serReg);
						query.setParameter("preCodigo", objPrepaid
								.getPreCodigo());

						List<ServiceRegistry> listServiceRegistry = (List<ServiceRegistry>) query
								.getResultList();
						/* fin consulta serviceRegistry */

						/* Se obtienen los ccf que se relacionan con el prepago */
						List<CostCentersFuel> listCostCentersFuel = null;

						String sql = "SELECT model FROM CostCentersFuel model "
								+ "WHERE model.ccfId = :ccfId";

						entityAux = null;
						entityAux = EntityManagerHelper.getEntityManager();
						entityAux.clear();

						query = null;
						query = entityAux.createQuery(sql);
						
						query.setParameter("ccfId", objPrepaid
								.getCostCentersFuel().getCcfId());

						listCostCentersFuel = (List<CostCentersFuel>) query
								.getResultList();

						if (listCostCentersFuel != null
								&& listCostCentersFuel.size() > 0) {

							List<Object[]> listCC_validos = new ArrayList<Object[]>(); // Lista
							// para
							// CC's
							// vigentes
							List<Object[]> listCC_no_validos = new ArrayList<Object[]>(); // Lista
							// para
							// CC's
							// no
							// vigentes

							int cambio = 2;
							for (CostCentersFuel ccf : listCostCentersFuel) {
								Object[] aux = new Object[6]; // Array que
								// almacenará la
								// relacion entre
								// los datos de ccf,
								// prepaidConsumption,
								// serviceRegistry

								if (ccf.getCostsCenters() != null) {
									aux[0] = ccf.getCostsCenters(); // Centro de
									// costo (CC)

									/* Consulta de los consumos de prepago */
									Double sumaGalonesCCF = 0D;
									Long tanqueadas = 0l;

									/*
									 * Verificar que la lista del
									 * registryService obtenida no este vacía
									 */
									if (listServiceRegistry != null
											&& listServiceRegistry.size() > 0) {

										Double galones = 0D; // galones
										// obtenidos
										// por cada relacion
										// entre ccf, ppc y
										// svc_reg

										for (ServiceRegistry svc_reg : listServiceRegistry) {

											/*
											 * Debido a que posiblemente existan
											 * registros de prepago cuyas fechas
											 * inicial y final incluyan el
											 * respectivo año, se debería hacer
											 * la devolución de acuerdo a las
											 * tanqueadas que se encuentren
											 * dentro del año
											 */

											String sql_prepaid_comp = "SELECT model FROM PrepaidConsumption model "
													+ "WHERE model.serviceRegistry.serCodigo = :serCodigo "
													+ "AND model.costCentersFuel.ccfId = :ccfId "
													+ "AND model.serviceRegistry.serFecha "
													+ "BETWEEN :fecha_ini_actual AND :fecha_fin_actual";

											entityAux = null;
											entityAux = EntityManagerHelper
													.getEntityManager();

											query = null;
											query = entityAux
													.createQuery(sql_prepaid_comp);
											query.setParameter("serCodigo",
													svc_reg.getSerCodigo());
											query.setParameter("ccfId", ccf
													.getCcfId());
											query.setParameter(
													"fecha_ini_actual",
													fecha_ini_actual);
											query.setParameter(
													"fecha_fin_actual",
													fecha_fin_actual);

											/* Obtener galones de los CC's */
											List<PrepaidConsumption> listPrepaidConsumption = (List<PrepaidConsumption>) query
													.getResultList();

											if (listPrepaidConsumption != null
													&& listPrepaidConsumption
															.size() > 0) {
												tanqueadas++; // Si se obtiene
												// un
												// registro en
												// PrepaidConsumption
												// que
												// se relacione con el
												// serviceRegistry y con el CCF,
												// lo
												// contamos como tanqueo

												PrepaidConsumption ppc = listPrepaidConsumption
														.get(0);
												galones += (ppc
														.getPrcValorConsumo() * svc_reg
														.getSerNumeroGalones())
														/ svc_reg.getSerTotal();
											}
										}
										// Se adiciona a la suma de galones
										// totales
										// por CC
										sumaGalonesCCF = Util.redondear(
												galones, 2);
									}
									/* FIN */

									aux[2] = ccf.getCcfValor(); // valor real de
									// la
									// distribución del
									// prepago en el CC
									// (valor del
									// porcentaje)
									aux[3] = sumaGalonesCCF; // cantidad de
									// galones, de
									// acuerdo a la
									// cantidad de
									// registros de
									// servicio
									aux[4] = 0; // aqui se almacenará el valor
									// del
									// porcentaje que equivale el CC
									aux[5] = tanqueadas; // Numero de
									// tanqueadas
									// realizadas para el CC

									// Verificar que el centro de costos este
									// vigente (false=valido, true=invalido)
									/*
									 * boolean noDisponibilidad =
									 * disponibilidadCentrCosto
									 * (ccf.getCostsCenters().getCocNumero());
									 */

									if (cambio == 1) {// Disponibilidad del CC
										// -
										// !noDisponibilidad
										aux[1] = "No está vigente";
										listCC_no_validos.add(aux);
									} else {
										aux[1] = "Vigente";
										listCC_validos.add(aux);
									}
									if (cambio < 2)
										cambio++;
									else
										cambio = 1;
								}
							}

							/*
							 * Si la disponibilidad no es igual a válido, al
							 * valor del CC debe realizarle una distribución
							 * presupuestal entre los otros CC relacionados
							 * (siempre y cuando exista más de uno)
							 */

							// Sumamos y obtenemos el total(100%)
							Double sumaPerctTotal = 0D;
							for (int i = 0; i < listCC_validos.size(); i++)
								sumaPerctTotal += Double
										.parseDouble(((Object[]) listCC_validos
												.get(i))[2].toString());

							// Redefinimos los porcentajes de cada uno de los CC
							// vigentes
							for (int i = 0; i < listCC_validos.size(); i++) {
								((Object[]) listCC_validos.get(i))[4] = (Double
										.parseDouble(((Object[]) listCC_validos
												.get(i))[2].toString()) * 1.0)
										/ sumaPerctTotal;
							}

							for (int i = listCC_no_validos.size() - 1; i >= 0; i--) {
								Object[] temp = listCC_no_validos.get(i);
								CostsCenters cc_nv = (CostsCenters) temp[0];

								Double distribucion_cc;
								if (listCC_validos.size() > 0) { // Para
									// hacer la
									// distribución,
									// debe haber
									// por lo menos
									// un CC válido
									if (cc_nv.getValorPrepago() > 0) { // Si no
										// dispone
										// de valor
										// prepago,
										// no se
										// distribuye

										// la distribución se la agrego al valor
										// del
										// CCF de los CC válidos
										for (int j = 0; j < listCC_validos
												.size(); j++) {
											Object[] datosCC_valido = listCC_validos
													.get(j);

											distribucion_cc = Double
													.parseDouble(temp[2]
															.toString())
													* Double
															.parseDouble(datosCC_valido[4]
																	.toString());
											datosCC_valido[2] = Util
													.redondear(
															(Double
																	.parseDouble(datosCC_valido[2]
																			.toString()) + distribucion_cc),
															2);

											distribucion_cc = 0D; // borramos
											// los
											// datos
										}

										// remuevo de la lista de no válidos, ya
										// que
										// se ha distribuido su valor
										listCC_no_validos.remove(temp);
									}
								}
							}

							List<Users> usuario = (new UsersDAO())
									.findByUsrIdentificacion(objPrepaid
											.getPreAsignatario());

							/* crear la lista de datos - CC validos */
							setDataPrepaidFuelProof(listCC_validos, objPrepaid,
									usuario, listVOprepaidFuelProof,
									tarifaVigente, vehiclesAssignation);

							/* crear la lista de datos - CC no validos */
							setDataPrepaidFuelProof(listCC_no_validos,
									objPrepaid, usuario,
									listVOprepaidFuelProof, tarifaVigente,
									vehiclesAssignation);

						}
					}
				}
				return listVOprepaidFuelProof;
			} else
				throw new GWorkException(Util
						.loadErrorMessageValue("PREPAGO.VACIO"));
		} catch (Exception e1) {
			throw new GWorkException(e1.getMessage() + "\n"
					+ e1.getLocalizedMessage());
		}
	}

	public void setDataPrepaidFuelProof(List<Object[]> listCC,
			Prepaid objPrepaid, List<Users> usuario,
			List<VOprepaidFuelProof> listVOppfp, Double tarifaVigente,
			VehiclesAssignation vha) {

		for (int i = 0; i < listCC.size(); i++) {
			VOprepaidFuelProof datos = new VOprepaidFuelProof();

			if (usuario != null && usuario.size() > 0) {
				datos.setAsignatario(usuario.get(0).getUsrNombre());
				datos.setCarne(usuario.get(0).getUsrIdentificacion());
			}

			Object[] objCCF = listCC.get(i);
			if (objCCF[0] != null) {
				CostsCenters CC = (CostsCenters) objCCF[0];
				datos.setCocCencos(CC.getCocNumero());

				datos.setIdtipoCargo(vha.getRequests().getLegateesTypes()
						.getLgtCodigo());

				datos.setFechaFinal(objPrepaid.getPreFechaFin());
				datos.setFechaInicial(objPrepaid.getPreFechaini());
				datos.setPlacaDiplomatica(objPrepaid.getPrePlaca());
				datos.setPrepago(objPrepaid.getPreCodigo() + "");
				datos.setValidarCencos(objCCF[1].toString());
				datos.setValorDistribucion(Double.parseDouble(objCCF[2]
						.toString()));

				// obtener el valor de la devolución
				datos.setGalonesAno(Double.parseDouble(objCCF[3].toString()));
				datos.setObservacion("Devolución prepago combustible");
				Double cantidadTanqueadas = Double.parseDouble(objCCF[5]
						.toString()
						+ "D");
				Double sumaGalonesAnuales = Double.parseDouble(objCCF[3]
						.toString());

				Double consumoProm;
				if (cantidadTanqueadas.compareTo(0D) != 0
						&& sumaGalonesAnuales.compareTo(0D) != 0)
					consumoProm = Util.redondear(sumaGalonesAnuales
							/ cantidadTanqueadas, 2);
				else
					consumoProm = 0D;

				datos.setConsumoPromedio(consumoProm);
				Double promMensualAnual = consumoProm * tarifaVigente;

				if (CC.getValorPrepago() >= promMensualAnual) {
					datos.setValorDevolucionCencos(Util.redondear(datos
							.getValorDistribucion()
							- promMensualAnual, 2));
					// datos.setValorDevolucionCencos(CC.getValorPrepago()-promMensualAnual);
					if (CC.getValorPrepago() >= datos
							.getValorDevolucionCencos()) {
						datos
								.setValorCencos(CC.getValorPrepago()
										.doubleValue());
						listVOppfp.add(datos);
					}
				}
			}
		}
	}

	public boolean disponibilidadCentrCosto(String centroCosto)
			throws GWorkException {

		boolean centroCostoActivo = consultsService
				.validateCostCenter(centroCosto.toUpperCase());

		return centroCostoActivo;
	}

	@SuppressWarnings("unchecked")
	public Tariffs getTarifaActual(Period periodo) throws GWorkException {
		try {

			Date FechaInicio;
			Date FechaFinal;

			FechaInicio = periodo.getPerFechaIni();
			FechaFinal = periodo.getPerFechaFin();

			String sql_tarifa = "SELECT model FROM Tariffs model "
					+ "WHERE model.trfFechaFin IS NULL "
					+ "AND model.trfFechaInicio "
					+ "BETWEEN :FechaInicio AND :FechaFinal "
					+ "ORDER BY model.trfFechaInicio";

			javax.persistence.EntityManager entityAuxTf = EntityManagerHelper
					.getEntityManager();
			entityAuxTf.clear();

			Query queryTf = entityAuxTf.createQuery(sql_tarifa);
			queryTf.setParameter("FechaInicio", FechaInicio);
			queryTf.setParameter("FechaFinal", FechaFinal);

			List<Tariffs> listTariffs = queryTf.getResultList();

			if (listTariffs != null && listTariffs.size() > 0)
				return listTariffs.get(listTariffs.size() - 1);
			else
				throw new GWorkException("No existe una tarifa vigente");
		} catch (Exception e) {
			throw new GWorkException(e.getMessage());
		}
	}

	/* HeaderProof */
	public HeaderProof generarCabeceraComprobante(Long tipoComprobante,
			Period period, Long tipoMovimiento, Long tipoMoneda,
			AccountingParameters parameters) throws GWorkException {

		HeaderProof headerProof = new HeaderProof();

		ProofState proofState = new ProofStateDAO().findById(Long
				.parseLong(Util.loadParametersValue("ACTIVO_PREPAGO_DEVOL")));

		if (proofState == null)
			throw new GWorkException(Util.loadErrorMessageValue(""));

		headerProof.setProofState(proofState);

		ProofType proofType = new ProofTypeDAO().findById(tipoComprobante);

		if (proofType == null)
			throw new GWorkException(Util.loadErrorMessageValue(""));

		headerProof.setProofType(proofType);

		TransactionType transactionType = new TransactionTypeDAO()
				.findById(tipoMovimiento);

		if (transactionType == null)
			throw new GWorkException(Util.loadErrorMessageValue(""));

		if (period == null)
			throw new GWorkException(Util.loadErrorMessageValue(""));

		headerProof.setPeriod(period);

		headerProof.setTransactionType(transactionType);

		CurrencyTypes currencyTypes = new CurrencyTypesDAO()
				.findById(tipoMoneda);

		if (currencyTypes == null)
			throw new GWorkException(Util.loadErrorMessageValue(""));

		Date fechaActual = new Date();

		String fechaGroupId = ManipulacionFechas.getAgno(fechaActual)
				+ ManipulacionFechas.getMes(fechaActual)
				+ ManipulacionFechas.getDia(fechaActual);

		String groupId = fechaGroupId + "18"
				+ parameters.getTransactionType().getTstId();

		headerProof.setHepGroupId(groupId);
		headerProof.setCurrencyTypes(currencyTypes);

		try {
			EntityManagerHelper.getEntityManager().getTransaction().begin();
			new HeaderProofDAO().save(headerProof);
			EntityManagerHelper.getEntityManager().getTransaction().commit();
		} catch (Exception e) {
			EntityManagerHelper.getEntityManager().getTransaction().rollback();
			throw new GWorkException(Util.loadErrorMessageValue(""));
		}

		headerProof = new HeaderProofDAO().findById(new Long(generarGroupId()));
		String nuevoGroupId = groupId + generarGroupId();
		headerProof.setHepGroupId(nuevoGroupId);

		try {
			EntityManagerHelper.getEntityManager().getTransaction().begin();
			new HeaderProofDAO().update(headerProof);
			EntityManagerHelper.getEntityManager().getTransaction().commit();

		} catch (Exception e) {
			EntityManagerHelper.getEntityManager().getTransaction().rollback();
			throw new GWorkException(Util.loadErrorMessageValue(""));
		}
		return headerProof;
	}

	/* fin headerProof */

	public void generatePrepaidFuelProof(String login,
			List<VOprepaidFuelProof> listVOprepaidFuelProof, Period periodo)
			throws GWorkException {

		Long flagHP = -1l;
		Long generados = 0l; // debitos generados -> ingresados
		Double valorCredito = 0D; // total de todo lo devuelto
		Double valorPrepago = 0D; // Obtener el valor de devolución
		Double totalGalones = 0D;
		boolean generado = false; // si el comprobante fue creado

		Tariffs tariff = getTarifaActual(periodo);

		Double tarifaActual = 0D;
		if (tariff != null)
			tarifaActual = getTarifaActual(periodo).getTrfValor().doubleValue();

		Long tipoComprobante = Long.parseLong(Util
				.loadParametersValue("PROOF.TYPE.FUEL"));

		for (VOprepaidFuelProof prepaidFuelProof : listVOprepaidFuelProof) {

			valorPrepago = 0D;
			valorPrepago = prepaidFuelProof.getValorDevolucionCencos();

			generado = generarComprobanteDetalle(tipoComprobante, login,
					valorPrepago, Long.parseLong(Util
							.loadParametersValue("DEBITO_PREPAGO_DEVOL")),
					prepaidFuelProof.getCocCencos().trim(), prepaidFuelProof
							.getIdtipoCargo(), prepaidFuelProof
							.getPlacaDiplomatica().trim(), periodo.getPerId(),
					totalGalones, flagHP, prepaidFuelProof.getCarne(),
					prepaidFuelProof.getPrepago(), tarifaActual);

			flagHP++;
			if (generado) {
				valorCredito += valorPrepago;
				totalGalones += prepaidFuelProof.getGalonesAno();
				generados++;
			}

		}

		if (generados.equals(0l))
			throw new GWorkException("No se generaron comprobantes");
		else {
			VOprepaidFuelProof prepaidFuelProof = listVOprepaidFuelProof.get(0);

			generarComprobanteDetalle(tipoComprobante, login, valorCredito,
					Long.parseLong(Util
							.loadParametersValue("CREDITO_PREPAGO_DEVOL")),
					prepaidFuelProof.getCocCencos().trim(), prepaidFuelProof
							.getIdtipoCargo(), prepaidFuelProof
							.getPlacaDiplomatica().trim(), periodo.getPerId(),
					totalGalones, flagHP, prepaidFuelProof.getCarne(),
					prepaidFuelProof.getPrepago(), tarifaActual);
		}
	}

	public boolean generarComprobanteDetalle(Long tipoComprobante,
			String login, Double valorDevolucion, Long tipoMovimiento,
			String centroCosto, Long tipoCargo, String placa, Long periodo,
			Double galones, Long flagHP, String carne, String idPrepago,
			Double tarifaActual) throws GWorkException {

		Period period = new PeriodDAO().findById(periodo);
		ActualOthersApplications actualOthersApplications = new ActualOthersApplications();
		AccountingParameters parameters = new AccountingParameters();
		Date fechaActual = new Date();
		HeaderProof headerProof = null;
		VehiclesAssignation vehiclesAssignation = null;

		// Seleccione el tipo de moneda que se ingresa en el comprobante USD
		Long tipoMoneda = ParametersUtil.DOLAR;// consultsServiceImpl.counsultarTipoMonedaAsignatario(carne);

		// si la compra es en dolares
		actualOthersApplications.setPSob(new Long(Util
				.loadParametersValue("p.sob.dolar")));
		actualOthersApplications.setPCurr(Util
				.loadParametersValue("p.curr.dol"));

		String pSource = Util.loadParametersValue("p.source");
		actualOthersApplications.setPAccdate(fechaActual);

		if (login == null || login.length() < 1)
			login = "NO.LOGIN.DPFP";
		actualOthersApplications.setPUser(login);

		String pCategory = Util.loadParametersValue("p.category.comb");
		actualOthersApplications.setPCategory(pCategory);
		actualOthersApplications.setPSource(pSource);

		actualOthersApplications.setPConvDate(null);
		actualOthersApplications.setPConvRate(null);
		actualOthersApplications.setPConvType(null);

		String centroCostoDebi = Util.loadParametersValue("CC_DEBITO");
		String pCcenter = null;

		if (tipoMovimiento.longValue() == Long.parseLong(Util
				.loadParametersValue("DEBITO_PREPAGO_DEVOL")))
			pCcenter = centroCosto;

		else if (tipoMovimiento.longValue() == Long.parseLong(Util
				.loadParametersValue("CREDITO_PREPAGO_DEVOL")))
			pCcenter = centroCostoDebi;

		actualOthersApplications.setPCcenter(pCcenter);

		Float pEntDr = null;
		Float pEntCr = null;

		if (tipoMovimiento.longValue() == Long.parseLong(Util
				.loadParametersValue("DEBITO_PREPAGO_DEVOL")))
			pEntDr = valorDevolucion.floatValue();

		else if (tipoMovimiento.longValue() == Long.parseLong(Util
				.loadParametersValue("CREDITO_PREPAGO_DEVOL")))
			pEntCr = valorDevolucion.floatValue();

		actualOthersApplications.setPEntDr(pEntDr);
		actualOthersApplications.setPEntCr(pEntCr);

		Long cargoTercero = Long.parseLong(Util
				.loadParametersValue("BAC_VHS_CNV"));
		if (tipoCargo != null) {
			if (tipoMovimiento.longValue() == Long.parseLong(Util
					.loadParametersValue("DEBITO_PREPAGO_DEVOL"))) {
				if (tipoCargo.compareTo(cargoTercero) == 0) {
					parameters = (new AccountingParametersDAO()).findById(13L);
					actualOthersApplications.setPAuxiliary(carne);
				} else {
					parameters = (new AccountingParametersDAO()).findById(13L);

					String pAuxiliary = parameters.getAuxiliar().getAuxValor();// Util.loadParametersValue("p.auxiliary");
					actualOthersApplications.setPAuxiliary(pAuxiliary);
				}
			} else if (tipoMovimiento.longValue() == Long.parseLong(Util
					.loadParametersValue("CREDITO_PREPAGO_DEVOL"))) {
				if (tipoCargo.compareTo(cargoTercero) == 0)
					parameters = (new AccountingParametersDAO()).findById(16L);
				else
					parameters = (new AccountingParametersDAO()).findById(16L);

				String pAuxiliary = parameters.getAuxiliar().getAuxValor();// Util.loadParametersValue("p.auxiliary");
				actualOthersApplications.setPAuxiliary(pAuxiliary);
			}
		} else
			return false;

		String pCompany = parameters.getCompany().getCmpNombre();
		actualOthersApplications.setPCompany(pCompany);

		String pAccount = parameters.getAccount().getAccNumeroCuenta();
		actualOthersApplications.setPAccount(pAccount);

		String formatoFecha = ManipulacionFechas.getDia(fechaActual) + "-"
				+ ManipulacionFechas.getNombreMes(fechaActual) + "-"
				+ ManipulacionFechas.getAgno(fechaActual);

		// Se consulta la asignacion correspondiente para la placa
		vehiclesAssignation = SearchVehicles.consultarAsignacionVehiculo(placa
				.trim().toUpperCase());

		String PDescription = null;
		String tipoAsignacion = null;

		if (vehiclesAssignation == null)
			return false;

		if (vehiclesAssignation.getRequests().getLegateesTypes() != null) {
			tipoAsignacion = vehiclesAssignation.getRequests()
					.getLegateesTypes().getLgtNombre();
		}

		if (tipoMovimiento.longValue() == Long.parseLong(Util
				.loadParametersValue("CREDITO_PREPAGO_DEVOL"))) {
			PDescription = Util.loadParametersValue("COMBU_PREPAGO_DEVOL")
					+ "||" + tipoAsignacion + "||"
					+ Util.loadParametersValue("CANT_GALONES") + "||" + galones
					+ "||" + Util.loadParametersValue("TARIFA_APLICADA") + "||"
					+ tarifaActual;
		} else if (tipoMovimiento.longValue() == Long.parseLong(Util
				.loadParametersValue("DEBITO_PREPAGO_DEVOL"))) {
			PDescription = parameters.getDescriptionType().getDstValor() + "||"
					+ "Año:||" + ManipulacionFechas.getAgno(fechaActual) + " "; // +
			// Util.loadParametersValue("p.category.comb")
		}

		actualOthersApplications.setPDescription(PDescription);

		// ??
		actualOthersApplications.setPNit(null);

		String pAttribute7 = "";
		if (tipoMovimiento.longValue() == Long.parseLong(Util
				.loadParametersValue("DEBITO_PREPAGO_DEVOL")))
			pAttribute7 = vehiclesAssignation.getVhaCodigo().toString().trim();

		actualOthersApplications.setPAttribute2("");

		String pAttribute5 = null;
		if (tipoMovimiento.longValue() == Long.parseLong(Util
				.loadParametersValue("DEBITO_PREPAGO_DEVOL"))
				&& tipoCargo.compareTo(cargoTercero) == 0)
			pAttribute5 = "";
		else
			pAttribute5 = parameters.getDocumentOne().getDocumentOneType()
					.getDotName();

		actualOthersApplications.setPAttribute5(pAttribute5);
		actualOthersApplications.setPAttribute7(pAttribute7);
		actualOthersApplications.setPAttribute8(null);
		String pAttribute9 = parameters.getDocumentTwo().getDocumentTwoType()
				.getDttName();
		actualOthersApplications.setPAttribute9(pAttribute9);

		String pAttribute10 = ManipulacionFechas.getMes(fechaActual)
				+ ManipulacionFechas.getDia(fechaActual);

		String pAttribute6 = null;

		if (tipoMovimiento.longValue() == Long.parseLong(Util
				.loadParametersValue("DEBITO_PREPAGO_DEVOL"))) {
			if (tipoCargo.compareTo(cargoTercero) == 0) {
				pAttribute6 = "";
				pAttribute10 = pAttribute6;
			} else
				pAttribute6 = placa;
		} else
			pAttribute6 = pAttribute10;

		actualOthersApplications.setPAttribute6(pAttribute6);
		actualOthersApplications.setPAttribute10(pAttribute10);
		actualOthersApplications.setAoaState("ACTIVO");

		// Se crea el groupId que relaciona a cada uno de los comprobantes
		if (flagHP == -1)
			headerProof = generarCabeceraComprobante(tipoComprobante, period,
					Long
							.parseLong(Util
									.loadParametersValue("TRASACCTION_TYPE")),
					tipoMoneda, parameters);

		else if (flagHP != -1)
			headerProof = SearchParametersBilling
					.consultarCabeceraCmprbntByGroupId(new Long(
							generarGroupId()));

		// Se construye el PBName
		String pBname = null;
		String groupId = headerProof.getHepGroupId();

		if (tipoMovimiento.longValue() == Long.parseLong(Util
				.loadParametersValue("DEBITO_PREPAGO_DEVOL")))
			pBname = pCompany + "-" + login + "-" + pSource + "-"
					+ Util.loadParametersValue("p.category.comb") + "-"
					+ formatoFecha + "-"
					+ Util.loadParametersValue("PARAM_PREPAGO_DEVOL") + "-"
					+ groupId;

		else if (tipoMovimiento.longValue() == Long.parseLong(Util
				.loadParametersValue("CREDITO_PREPAGO_DEVOL")))
			pBname = pCompany + "-" + login + "-" + pSource + "-"
					+ Util.loadParametersValue("p.category.comb") + "-"
					+ formatoFecha + "-"
					+ Util.loadParametersValue("PARAM_PREPAGO_DEVOL") + "-"
					+ groupId;

		actualOthersApplications.setPBname(pBname);
		actualOthersApplications.setHeaderProof(headerProof);

		boolean generado = false;
		if (tipoMovimiento.longValue() == Long.parseLong(Util
				.loadParametersValue("DEBITO_PREPAGO_DEVOL"))) {

			if (vehiclesAssignation != null) {
				// luego de generar el comprobante de devolución, se hace el
				// reajuste
				// en el ccf
				generado = generarDevolucionPrepago(idPrepago, centroCosto,
						valorDevolucion, headerProof, actualOthersApplications,
						vehiclesAssignation, periodo);
			} else
				throw new GWorkException(Util
						.loadErrorMessageValue("PLACASINASIGNACION")
						+ placa.trim().toUpperCase());
		} else {
			try {
				EntityManagerHelper.getEntityManager().getTransaction().begin();
				new ActualOthersApplicationsDAO()
						.save(actualOthersApplications);
				EntityManagerHelper.getEntityManager().getTransaction()
						.commit();
				generado = true;
			} catch (Exception e) {
				throw new GWorkException(e.getMessage());
			}
		}
		return generado;
	}

	/*
	 * public void generarAsignacionComprobante(HeaderProof headerProof,
	 * VehiclesAssignation vehiclesAssignation, Long periodo) throws
	 * GWorkException{
	 * 
	 * Period period = new PeriodDAO().findById(periodo); VhaAoaApp
	 * asignacionComprobante = new VhaAoaApp();
	 * asignacionComprobante.setHeaderProof(headerProof);
	 * asignacionComprobante.setVehiclesAssignation(vehiclesAssignation);
	 * asignacionComprobante.setAoaFechaIni(period.getPerFechaIni());
	 * asignacionComprobante.setAoaFechaFin(period.getPerFechaFin());
	 * 
	 * try{ EntityManagerHelper.getEntityManager().getTransaction().begin(); new
	 * VhaAoaAppDAO().save(asignacionComprobante);
	 * EntityManagerHelper.getEntityManager().getTransaction().commit(); }
	 * catch(Exception e){
	 * EntityManagerHelper.getEntityManager().getTransaction().rollback(); throw
	 * new GWorkException(e.getMessage()); } }
	 */

	/* Obtener el periodo por el año */	
	public Period consultarPeriodByAno(String anoCobro) throws GWorkException {
		try {
			Date fecha_ini_actual;
			Date fecha_fin_actual;

			Calendar calendario = Calendar.getInstance();
			calendario.set(Integer.parseInt(anoCobro), 0, 1);
			fecha_ini_actual = calendario.getTime();

			calendario.set(Integer.parseInt(anoCobro), 11, 31);
			fecha_fin_actual = calendario.getTime();

			final String sql = "SELECT model FROM Period model"
					+ " WHERE model.perFechaIni = :fecha_ini_actual"
					+ " AND model.perFechaFin = :fecha_fin_actual";

			javax.persistence.EntityManager entityAux = EntityManagerHelper
					.getEntityManager();
			Query query = entityAux.createQuery(sql);

			query.setParameter("fecha_ini_actual", fecha_ini_actual);
			query.setParameter("fecha_fin_actual", fecha_fin_actual);

			Period objPeriod = (Period) query.getResultList();
			if (objPeriod != null)
				return objPeriod;
			else
				throw new GWorkException(Util
						.loadErrorMessageValue("ERROR.NOEXISTEPERIODO"));
		} catch (Exception e) {
			throw new GWorkException(e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	public boolean generarDevolucionPrepago(String idPrepago, String CocCencos,
			Double ValorDevolucionCencos, HeaderProof HP,
			ActualOthersApplications AOA, VehiclesAssignation vha, Long periodo)
			throws GWorkException {

		// Consultar el prepago y agregar el headerProof
		Prepaid prepaid = (new PrepaidDAO())
				.findById(Long.parseLong(idPrepago));

		if (prepaid != null) {
			// Si no se ha agregado el headerProof_dev al Prepago, debe
			// agregarlo
			if (prepaid.getHeaderProof_dev() == null
					|| !prepaid.getHeaderProof_dev().equals(HP)) {
				prepaid.setHeaderProof_dev(HP);
			}
			/*
			 * NOTA: la modificación se hace efectiva solo cuando se hace la
			 * modificación en el cost center
			 */

			// consultar el ccf & el cc
			final String sql = "SELECT model FROM CostCentersFuel model"
					+ " WHERE model.prepaid.preCodigo = :preCodigo"
					+ " AND model.costsCenters.cocNumero = :cocNumero";

			javax.persistence.EntityManager entityAux = EntityManagerHelper
					.getEntityManager();
			entityAux.clear();
			Query query = entityAux.createQuery(sql);

			query.setParameter("preCodigo", Long.parseLong(idPrepago));
			query.setParameter("cocNumero", CocCencos);

			List<CostCentersFuel> listCostCentersFuel = (List<CostCentersFuel>) query
					.getResultList();

			if (listCostCentersFuel != null && listCostCentersFuel.size() > 0) {
				CostCentersFuel costCentersFuel = listCostCentersFuel.get(0);

				CostsCenters costsCenters = costCentersFuel.getCostsCenters();

				// volver a verificar que se puede hacer la operacion
				if (costsCenters.getValorPrepago() != null
						&& costsCenters.getValorPrepago() >= ValorDevolucionCencos
								.doubleValue()) {

					// valorPrepago =
					// costsCenters.getValorPrepago()-ValorDevolucionCencos.floatValue();
					// costsCenters.setValorPrepago(valorPrepago);
					// costCentersFuel.setCostsCenters(costsCenters);

					/*
					 * Se relacion la cabecera del comprobante con una
					 * asignacion existente
					 */
					/*
					 * Period period = new PeriodDAO().findById(periodo);
					 * VhaAoaApp asignacionComprobante = new VhaAoaApp();
					 * asignacionComprobante.setHeaderProof(HP);
					 * asignacionComprobante.setVehiclesAssignation(vha);
					 * asignacionComprobante.setAoaFechaIni(period.getPerFechaIni());
					 * asignacionComprobante.setAoaFechaFin(period.getPerFechaFin());
					 */

					try {
						EntityManagerHelper.getEntityManager().getTransaction()
								.begin();
						(new ActualOthersApplicationsDAO()).save(AOA);
						(new PrepaidDAO()).update(prepaid);
						// (new CostsCentersDAO()).update(costsCenters);
						// (new VhaAoaAppDAO()).save(asignacionComprobante);
						EntityManagerHelper.getEntityManager().getTransaction()
								.commit();
					} catch (Exception e) {
						EntityManagerHelper.getEntityManager().getTransaction()
								.rollback();
						throw new GWorkException(e.getMessage());
					}

					ConsultsServiceImpl.insercionContable(AOA.getPSob(), AOA
							.getPAccdate(), AOA.getPCurr(), AOA.getPUser(), AOA
							.getPCategory(), AOA.getPSource(), AOA
							.getPConvDate(), AOA.getPConvType(), AOA
							.getPConvRate(), AOA.getPCompany(), AOA
							.getPAccount(), AOA.getPCcenter(), AOA
							.getPAuxiliary(), AOA.getPEntDr(), AOA.getPEntCr(),
							AOA.getPBname(), AOA.getPDescription(), AOA
									.getPNit(), AOA.getPAttribute2(), AOA
									.getPAttribute5(), AOA.getPAttribute6(),
							AOA.getPAttribute7(), AOA.getPAttribute8(), AOA
									.getPAttribute9(), AOA.getPAttribute10(),
							AOA.getHeaderProof().getHepGroupId());
					/*
					 * ConsultsServiceImpl.insercionContable(PSob, PAccdate,
					 * PCurr, PUser, PCategory, PSource, PConvDate, PConvType,
					 * PConvRate, PCompany, PAccount, PCcenter, PAuxiliary,
					 * PEntDr, PEntCr, PBname, PDescription, PNit, PAttribute2,
					 * PAttribute5, PAttribute6, PAttribute7, PAttribute8,
					 * PAttribute9, PAttribute10, headerProof.getHepGroupId());
					 */
					return true;
				}
				return false;
			}
			return false;
		}
		return false;
	}

	public static String generarGroupId() throws GWorkException {
		Long idHP = SearchParametersBilling.lastHeaderProof();
		return idHP.toString();
	}

	@SuppressWarnings("unchecked")
	public List<AccountingParameters> getListAccountingParameters(Long preCodigo) {
		try {
			/* obtener el chargeType */
			String sql_ChargeType = "SELECT model FROM AccountingParameters model "
					+ "WHERE model.costsCenters.cocCodigo = :cocCodigo ";

			javax.persistence.EntityManager entityAuxChargeType = EntityManagerHelper
					.getEntityManager();
			entityAuxChargeType.clear();

			Query query = entityAuxChargeType.createQuery(sql_ChargeType);
			query.setParameter("cocCodigo", preCodigo);

			return (List<AccountingParameters>) query.getResultList();
		} catch (Exception e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<AccountingParameters> getListAccountingParameters(
			Long cocCodigo, String[] chargeTypes) {
		try {
			/* Agregar códigos del tipo de cargo del prepago */
			String codigosChargeType = "(";
			for (int i = 0; i < chargeTypes.length; i++) {
				if ((i + 1) < chargeTypes.length)
					codigosChargeType += chargeTypes[i] + ",";
				else
					codigosChargeType += chargeTypes[i];
			}
			codigosChargeType += ")";

			/* obtener el chargeType */
			String sql_ChargeType = "SELECT model FROM AccountingParameters model "
					+ "WHERE model.costsCenters.cocCodigo = :cocCodigo "
					+ "AND model.chargeType.cgtId IN " + codigosChargeType;

			javax.persistence.EntityManager entityAuxChargeType = EntityManagerHelper
					.getEntityManager();
			entityAuxChargeType.clear();

			Query query = entityAuxChargeType.createQuery(sql_ChargeType);
			query.setParameter("cocCodigo", cocCodigo);

			return (List<AccountingParameters>) query.getResultList();
		} catch (Exception e) {
			return null;
		}
	}

}
