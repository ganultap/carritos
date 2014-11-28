package geniar.siscar.logic.fuels.services.impl; 

import geniar.siscar.consults.impl.ConsultsServiceImpl;
import geniar.siscar.logic.billing.services.impl.BudgetFuelProofServiceImpl;
import geniar.siscar.logic.billing.services.impl.FlatFileFuelServiceImpl;
import geniar.siscar.logic.billing.services.impl.PrepaidProofBoughtServiceImpl;
import geniar.siscar.logic.billing.services.impl.SearchParametersBilling;
import geniar.siscar.logic.consultas.ConsultTariff;
import geniar.siscar.logic.consultas.SearchCostCenters;
import geniar.siscar.logic.consultas.SearchVehicles;
import geniar.siscar.logic.fuels.services.ServiceRegistryService;
import geniar.siscar.mail.util.SendEmail;
import geniar.siscar.model.AccountingParameters;
import geniar.siscar.model.ChargeTo;
import geniar.siscar.model.CostCentersFuel;
import geniar.siscar.model.CostsCenters;
import geniar.siscar.model.FlatFile;
import geniar.siscar.model.FuelTypeRequest;
import geniar.siscar.model.HeaderProof;
import geniar.siscar.model.Period;
import geniar.siscar.model.Prepaid;
import geniar.siscar.model.PrepaidConsumption;
import geniar.siscar.model.Pumps;
import geniar.siscar.model.Rolls;
import geniar.siscar.model.ServiceRegistry;
import geniar.siscar.model.Tariffs;
import geniar.siscar.model.VOCostCentersFuels;
import geniar.siscar.model.Vehicles;
import geniar.siscar.model.VehiclesAssignation;
import geniar.siscar.persistence.ChargeToDAO;
import geniar.siscar.persistence.CostsCentersDAO;
import geniar.siscar.persistence.EntityManagerHelper;
import geniar.siscar.persistence.FlatFileDAO;
import geniar.siscar.persistence.FuelTypeRequestDAO;
import geniar.siscar.persistence.FuelsTypesDAO;
import geniar.siscar.persistence.PrepaidConsumptionDAO;
import geniar.siscar.persistence.RollsDAO;
import geniar.siscar.persistence.ServiceRegistryDAO;
import geniar.siscar.util.ManipulacionFechas;
import geniar.siscar.util.ParametersUtil;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * The Class ServiceRegistryImp.
 */
public class ServiceRegistryImp implements ServiceRegistryService {
 
	/** The Constant VEHICULO_CIAT. */
	private static final Long VEHICULO_CIAT = 1L; 
	
	/** The Constant TRACTOR_POOL. */
	private static final Long TRACTOR_POOL = 2L;
	
	/** The Constant MOTO_CIAT. */
	private static final Long MOTO_CIAT = 3L;
	
	/** The Constant MEMO_CIAT. */
	private static final Long MEMO_CIAT = 4L;

	/** The Constant log. */
	private static final Log log = LogFactory.getLog(SendEmail.class);
	
	/** The mail. */
	private SendEmail mail;

	/**
	 * Gets the.
	 *
	 * @return true, if successful
	 */
	boolean get() {
		return true && true;
	}

	/**
	 * Service registry fuel.
	 *
	 * @param idFuelTypeRequest the id fuel type request
	 * @param placa the placa
	 * @param login the login
	 * @param nombreSolicitante the nombre solicitante
	 * @param carneAsignatario the carne asignatario
	 * @param carneSolicitante the carne solicitante
	 * @param numeroGalones the numero galones
	 * @param total the total
	 * @param observaciones the observaciones
	 * @param idPump the id pump
	 * @param kilometrajeActual the kilometraje actual
	 * @param numeroReciboPago the numero recibo pago
	 * @param NumeroCentroCosto the numero centro costo
	 * @param usoDisponible the uso disponible
	 * @param fuelType the fuel type
	 * @param capacidadTanque the capacidad tanque
	 * @param cargoA the cargo a
	 * @throws GWorkException the g work exception
	 */
	public void serviceRegistryFuel(Long idFuelTypeRequest, String placa,
			String login, String nombreSolicitante, String carneAsignatario,
			String carneSolicitante, Float numeroGalones, Float total,
			String observaciones, Long idPump, Long kilometrajeActual,
			Long numeroReciboPago, String NumeroCentroCosto,
			Long usoDisponible, Long fuelType, Float capacidadTanque,
			Long cargoA) throws GWorkException {
		try {
			VehiclesAssignation assignation = null;
			Vehicles vehicles = null;
			Tariffs tariffs = null;
			Float topeMinimo = null;
			Float totalPagar = null;
			Float promedioTanqueo = null;
			Float kilometrajeHistorico = new Float(0);
			Float tarifa = null;
			CostsCenters costsCenters = null;
			String placaDip = null;
			String cuenta = null;
			AccountingParameters parameters = null;
			Float valorPrepago = 0F;
			// almacena el valor del prepago
			Prepaid prepaid = null;
			Date fechaActual = null;
			Connection connection = null;
			Pumps pumps = null;
			ChargeTo chargeTo = null;

			if (placa != null)
				placaDip = placa.trim().toUpperCase();

			// Validar a partir del tipo de combustible a que surtidor se carga
			// el consumo de combustible
			if (fuelType.longValue() == ParametersUtil.GASOLINA.longValue())
				pumps = new PumpsServiceImpl().consultarPumpPorId(1L);

			else if (fuelType.longValue() == ParametersUtil.DIESEL.longValue())
				pumps = new PumpsServiceImpl().consultarPumpPorId(22L);

			if (numeroGalones != null && numeroGalones.longValue() > 200L)
				throw new GWorkException(Util
						.loadErrorMessageValue("CTRL.CAP.MAX.GLS"));

			// // poner en la sesion de hibernate el login del usuario loggeado
			// // en la pda para registrar el log de auditoria
			// if (login != null && login.length() > 0)
			// LogUserInfo.setCurrentUser(login);

			if (idFuelTypeRequest != null) {

				FuelTypeRequest fuelTypeRequest = new FuelTypeRequestDAO()
						.findById(idFuelTypeRequest);

				Long estadoRetirado = Long.valueOf(Util.loadMessageValue("ESTADO.5"));
				
				vehicles = SearchVehicles.consultarVehiculosPorPlacaYEstado(placaDip, estadoRetirado);
				if (vehicles != null) {
					assignation = SearchVehicles.consultarAsignacionVehiculo(placaDip);
				}
				if (cargoA != null)
					chargeTo = new ChargeToDAO().findById(cargoA);

				if (idFuelTypeRequest.longValue() == VEHICULO_CIAT
						|| idFuelTypeRequest.longValue() == TRACTOR_POOL
						|| idFuelTypeRequest.longValue() == MOTO_CIAT
						|| idFuelTypeRequest.longValue() == MEMO_CIAT)
					tariffs = new ConsultTariff().consultarTarifaTipoCombustible(fuelType);
				else
					tariffs = new ConsultTariff().consultarTarifaTipoCombustibleCali(fuelType);

				if (tariffs != null)
					tarifa = tariffs.getTrfValor();

				if (capacidadTanque != 0.0)
					topeMinimo = calcularTopeMinimo(capacidadTanque, tariffs
							.getTrfValor());

				totalPagar = numeroGalones * tarifa;
				if (NumeroCentroCosto != null) {
					costsCenters = SearchCostCenters
							.consultarCentroCosto(NumeroCentroCosto);
				}

				SimpleDateFormat dateFormat = new SimpleDateFormat("h:mm a");

				fechaActual = new Date();
				String hora = dateFormat.format(fechaActual);

				if (vehicles != null && vehicles.getVhcCapMaxTanq() != null) {
					capacidadTanque = vehicles.getVhcCapMaxTanq();

					if (numeroGalones.longValue() > capacidadTanque.longValue())
						throw new GWorkException(Util
								.loadErrorMessageValue("CTRL.TAM.CAP"));
				} else if (vehicles != null
						&& vehicles.getVhcCapMaxTanq() == null) {
					throw new GWorkException(Util
							.loadErrorMessageValue("CTRL.CAPACIDAD.TANQUE"));
				}

				if (usoDisponible != null
						&& usoDisponible.longValue() == ParametersUtil.USO_PRESUPUESTO
								.longValue()) {

					Integer anho = new Integer(ManipulacionFechas
							.getAgno(new Date()));

					if (cargoA != null && cargoA.longValue() == 1L) {
						parameters = SearchParametersBilling
								.consultarParemeter(ParametersUtil.DEBITO,
										ParametersUtil.CARGO_CC,
										ParametersUtil.TRASACCTION_TYPE,
										ParametersUtil.LGT_NO_APLICA);
						
						cuenta = parameters.getAccount().getAccNumeroCuenta();
					}
					if (idFuelTypeRequest.longValue() == TRACTOR_POOL
							|| idFuelTypeRequest.longValue() == MOTO_CIAT
							|| idFuelTypeRequest.longValue() == MEMO_CIAT) {
						String validarPresupuesto = new ConsultsServiceImpl()
								.validarPresupuesto(anho, NumeroCentroCosto,
										cuenta, null, new Double(totalPagar));

						if (validarPresupuesto.trim().equalsIgnoreCase("N"))
							throw new GWorkException(Util
									.loadErrorMessageValue("ERROR.NODISPPPTO"));
					} else {

						/*
						 * si es diferente a convenio se realiza validación
						 * presupuestal
						 */
						if (idFuelTypeRequest.longValue() == VEHICULO_CIAT
								&& assignation != null
								&& assignation.getRequests() != null
								&& assignation.getRequests().getLegateesTypes() != null
								&& assignation.getRequests().getLegateesTypes()
										.getLgtCodigo() != ParametersUtil.LGT_CONVENIO
										.longValue()
								&& assignation.getRequests().getLegateesTypes()
										.getLgtCodigo() != ParametersUtil.LGT_PROYECTO
										.longValue()
								&& NumeroCentroCosto != null) {
							String validarPresupuesto = new ConsultsServiceImpl()
									.validarPresupuesto(anho,
											NumeroCentroCosto, cuenta, null,
											new Double(totalPagar));

							if (validarPresupuesto.trim().equalsIgnoreCase("N"))
								throw new GWorkException(
										Util
												.loadErrorMessageValue("ERROR.NODISPPPTO"));

						} else {
							/*
							 * se consulta la parametrizacion contable Cuando es
							 * convenio ó tipo de solicitd es (OTROS Ó PARQUE
							 * CIENTIFICO) se toma el centro de costos de la
							 * parametrizacion contable
							 */
							if ((idFuelTypeRequest.longValue() == VEHICULO_CIAT
									&& assignation != null
									&& assignation.getRequests() != null
									&& assignation.getRequests()
											.getLegateesTypes() != null && assignation
									.getRequests().getLegateesTypes()
									.getLgtCodigo() == ParametersUtil.LGT_CONVENIO
									.longValue())
									|| (idFuelTypeRequest.longValue() == ParametersUtil.SOLICITUD_COMB_OTROS || idFuelTypeRequest
											.longValue() == ParametersUtil.SOLICITUD_COMB_PARQUE)) {
								NumeroCentroCosto = SearchParametersBilling
										.consultarParemeter(
												ParametersUtil.DEBITO,
												ParametersUtil.CARGO_TERCEROS,
												ParametersUtil.TRASACCTION_TYPE,
												ParametersUtil.LGT_NO_APLICA)
										.getCostsCenters().getCocNumero();
							}
						}
					}
				}
				// Si es para consumo de prepago
				if (usoDisponible != null
						&& usoDisponible.equals(ParametersUtil.USO_PREPAGO)
						&& assignation != null
						&& cargoA.longValue() == ParametersUtil.CARGO_A_CC) {

					List<CostCentersFuel> costCentersFuel = consultCostCentersFuelCodigo(NumeroCentroCosto);

					/*
					 * Si tiene asignación personal y el cargo a es carnet no se
					 * necesita validar nada relacionado con los centros de
					 * costos ó cuando el CargoA es Terceros, tampoco se
					 * necesita validar aqui si tiene disponibilidad
					 * presupuestal
					 */

					if (costCentersFuel == null || costCentersFuel.size() == 0
							|| costCentersFuel.isEmpty())
						throw new GWorkException(
								"No existe prepago para el centro de costo:"
										+ NumeroCentroCosto);

					if (costsCenters.getValorPrepago() != null
							&& totalPagar.floatValue() > costsCenters
									.getValorPrepago().floatValue())
						throw new GWorkException(
								"No tiene disponibilidad de prepago en el centro de costo :"
										+ NumeroCentroCosto + " Valor actual: "
										+ costsCenters.getValorPrepago());
					if (assignation == null)
						throw new GWorkException(Util
								.loadErrorMessageValue("SOLICITUD.ASIGNACION")
								+ ": " + placa);

					tariffs = new ConsultTariff()
							.consultarTarifaTipoCombustible(vehicles
									.getFuelsTypes().getFutCodigo());
					tarifa = tariffs.getTrfValor();

					if (capacidadTanque != null)
						topeMinimo = calcularTopeMinimo(capacidadTanque,
								tariffs.getTrfValor());
					// 23. Se valida que existan recursos en la disponibilidad
					// del
					// prepago
					// assignation.setCostsCentersVehicleses(new
					// AllocationReturnVehicleServiceImpl()
					// .validarDisponibilidadCentrosDeCosto(assignation,
					// tarifa));

					promedioTanqueo = (kilometrajeActual - kilometrajeHistorico)
							/ tarifa;

					/*
					 * Si tiene asignación personal y el cargo a es carnet no se
					 * necesita validar nada relacionado con los centros de
					 * costos
					 */
					PrepaidConsumption prepaidConsumption = new PrepaidConsumption();
					if (usoDisponible == ParametersUtil.USO_PREPAGO
							&& cargoA.longValue() == ParametersUtil.CARGO_A_CC) {

						if (costCentersFuel != null
								&& costCentersFuel.size() > 0)
							prepaidConsumption
									.setCostCentersFuel(costCentersFuel.get(0));

						if (costsCenters.getValorPrepago() != null)
							valorPrepago = costsCenters.getValorPrepago()
									- totalPagar;

						costsCenters.setValorPrepago(valorPrepago);

						prepaidConsumption.setPrcFecha(fechaActual);
						prepaidConsumption.setPrcHora(hora);
						prepaidConsumption.setPrcValorConsumo(totalPagar);

					}
					
					connection = guardarRegistro(pumps, fuelTypeRequest,
							assignation, hora, kilometrajeActual,
							kilometrajeHistorico, nombreSolicitante,
							numeroGalones, numeroReciboPago, observaciones,
							vehicles, numeroGalones, login, tariffs,
							totalPagar, placa, chargeTo, NumeroCentroCosto,
							usoDisponible, carneAsignatario, fechaActual,
							fuelType, carneSolicitante, costsCenters,
							prepaidConsumption);

					// para uso de prepago sin asignacion
				} else if (usoDisponible != null
						&& usoDisponible == ParametersUtil.USO_PREPAGO
						&& assignation == null
						&& cargoA.longValue() == ParametersUtil.CARGO_A_CC) {

					List<CostCentersFuel> costCentersFuel = consultCostCentersFuelCodigo(NumeroCentroCosto); 

					if (costCentersFuel == null || costCentersFuel.size() == 0
							|| costCentersFuel.isEmpty())
						throw new GWorkException(
								"No existe prepago para el centro de costo:"
										+ NumeroCentroCosto);

					if (costsCenters.getValorPrepago() != null
							&& totalPagar > costsCenters.getValorPrepago())
						throw new GWorkException(
								"No tiene disponibilidad de prepago en el centro de costo :"
										+ NumeroCentroCosto + " Valor actual: "
										+ costsCenters.getValorPrepago());

					// prepaid = costCentersFuel.get(0).getPrepaid();

					PrepaidConsumption prepaidConsumption = new PrepaidConsumption();
					if (costCentersFuel != null && costCentersFuel.size() > 0)
						prepaidConsumption.setCostCentersFuel(costCentersFuel
								.get(0));

					if (costsCenters.getValorPrepago() != null)
						valorPrepago = costsCenters.getValorPrepago()
								- totalPagar;

					costsCenters.setValorPrepago(valorPrepago);

					prepaidConsumption.setPrcFecha(fechaActual);
					prepaidConsumption.setPrcHora(hora);
					prepaidConsumption.setPrcValorConsumo(totalPagar);

					connection = guardarRegistro(pumps, fuelTypeRequest,
							assignation, hora, kilometrajeActual,
							kilometrajeHistorico, nombreSolicitante,
							numeroGalones, numeroReciboPago, observaciones,
							vehicles, numeroGalones, login, tariffs,
							totalPagar, placa, chargeTo, NumeroCentroCosto,
							usoDisponible, carneAsignatario, fechaActual,
							fuelType, carneSolicitante, costsCenters,
							prepaidConsumption);

				} else {
					if (cargoA != null)
						chargeTo = new ChargeToDAO().findById(cargoA);

					connection = guardarRegistro(pumps, fuelTypeRequest,
							assignation, hora, kilometrajeActual,
							kilometrajeHistorico, nombreSolicitante,
							numeroGalones, numeroReciboPago, observaciones,
							vehicles, numeroGalones, login, tariffs,
							totalPagar, placa, chargeTo, NumeroCentroCosto,
							usoDisponible, carneAsignatario, fechaActual,
							fuelType, carneSolicitante, null, null);
				}
			}
			

			EntityManagerHelper.getEntityManager().getTransaction().begin();
			EntityManagerHelper.getEntityManager().getTransaction().commit();

			if (connection != null)
				connection.commit();
			
			enviarNotificacionCombustible(getMail()); 

		} catch (Exception e) {
			log.error("serviceRegistryFuel -> " + e.getMessage(), e);
			throw new GWorkException(e.getMessage(), e);
		}

	}

	/**
	 * Consult cost center fuel by placa.
	 *
	 * @param placa the placa
	 * @return the vO cost centers fuels
	 * @throws GWorkException the g work exception
	 */
	@SuppressWarnings("unchecked")
	public VOCostCentersFuels consultCostCenterFuelByPlaca(String placa)
			throws GWorkException {
		try {
			VOCostCentersFuels costCentersFuels = null;
			List<CostCentersFuel> valor = null;
			CostCentersFuel costCentersFuel = null;
			final String queryString = "select model from CostCentersFuel model " +
					"where model.vehiclesAssignation.vehicles.vhcPlacaDiplomatica = :placa " +
					"AND model.vehiclesAssignation.assignationsStates.assCodigo = 5 " +
					"AND model.ccfEstado = 'ACTIVO'";

			Query query = EntityManagerHelper.getEntityManager().createQuery(
					queryString);
			query.setParameter("placa", placa.trim().toUpperCase());

			valor = query.getResultList();

			if (valor != null && valor.size() > 0) {
				costCentersFuel = valor.get(0);
				costCentersFuels = new VOCostCentersFuels();
				costCentersFuels.setCostCenterNumber(costCentersFuel
						.getCostsCenters().getCocNumero());
			}

			return costCentersFuels;

		} catch (Exception re) {
			log.error("consultCostCenterFuelByPlaca -> " + re.getMessage(), re);
			throw new GWorkException(Util
					.loadErrorMessageValue("search.not.found"), re);
		}
	}

	/**
	 * Guardar registro.
	 *
	 * @param pumps the pumps
	 * @param fuelTypeRequest the fuel type request
	 * @param assignation the assignation
	 * @param hora the hora
	 * @param kilometrajeActual the kilometraje actual
	 * @param kilometrajeHistorico the kilometraje historico
	 * @param nombreSolicitante the nombre solicitante
	 * @param numeroGalones the numero galones
	 * @param numeroRecibo the numero recibo
	 * @param observaciones the observaciones
	 * @param vehicles the vehicles
	 * @param totalCombustible the total combustible
	 * @param login the login
	 * @param tariffs the tariffs
	 * @param total the total
	 * @param placa the placa
	 * @param chargeTo the charge to
	 * @param centroCosto the centro costo
	 * @param usoDisponible the uso disponible
	 * @param carneAsignatario the carne asignatario
	 * @param fechaActual the fecha actual
	 * @param fuelType the fuel type
	 * @param carneSolicitante the carne solicitante
	 * @param costsCenters the costs centers
	 * @param prepaidConsumption the prepaid consumption
	 * @return the connection
	 * @throws GWorkException the g work exception
	 * @throws SQLException the sQL exception
	 */
	public Connection guardarRegistro(Pumps pumps,
			FuelTypeRequest fuelTypeRequest, VehiclesAssignation assignation,
			String hora, Long kilometrajeActual, Float kilometrajeHistorico,
			String nombreSolicitante, Float numeroGalones, Long numeroRecibo,
			String observaciones, Vehicles vehicles, Float totalCombustible,
			String login, Tariffs tariffs, Float total, String placa,
			ChargeTo chargeTo, String centroCosto, Long usoDisponible,
			String carneAsignatario, Date fechaActual, Long fuelType,
			String carneSolicitante, CostsCenters costsCenters,
			PrepaidConsumption prepaidConsumption) 
	throws GWorkException,
			SQLException 
			{

		Connection connection = null;
		try {
			
			log.info("** Inicia guardarRegistro **");
			
			connection = ConsultsServiceImpl.getConnection("jdbc/siscarFinanciero");
			
			FlatFile flatFile = null;
			total = new Float(Util.redondear(total, 2));
			//modificado el 24 de abril 2012 total = new Float(Util.redondear(total, 3));
			HeaderProof headerProof = null;

			ServiceRegistry registry = new ServiceRegistry();
			registry.setFuelsTypes(new FuelsTypesDAO().findById(fuelType));
			registry.setFuelTypeRequest(fuelTypeRequest);
			registry.setPumps(pumps);
			if (assignation != null) {
				registry.setSerCarneAsignatario(assignation.getRequests()
						.getRqsCarnetAsignatario());
				registry.setSerCarneSolicitante(carneSolicitante);
				registry.setSerCorreoAsignatario(assignation.getRequests()
						.getUsersByRqsUser().getUsrEmail());
			} else {
				registry.setSerCarneSolicitante(carneSolicitante);
				registry.setSerCarneAsignatario(carneAsignatario);
			}

			// Ingresar kilometraje anterior de la tanqueada
			if (lastServiceRegistryPlaca(placa) != null
					&& lastServiceRegistryPlaca(placa)
							.getSerKilometrajeAnterior() != null)
				kilometrajeHistorico = new Float(
						lastServiceRegistryPlaca(placa)
								.getSerKilometrajeActual());

			if (kilometrajeActual != null
					&& (kilometrajeHistorico == null || kilometrajeHistorico
							.floatValue() == 0F))
				registry
						.setSerKilometrajeAnterior(kilometrajeActual.toString());
			else if (kilometrajeHistorico != null)
				registry.setSerKilometrajeAnterior(kilometrajeHistorico
						.toString());

			registry.setSerFecha(fechaActual);
			registry.setSerHora(hora);
			if (kilometrajeActual != null)
				registry.setSerKilometrajeActual(kilometrajeActual.toString());
			registry.setSerNombreSolicitante(nombreSolicitante);
			registry.setSerNumeroGalones(numeroGalones);

			if (numeroRecibo != null)
				registry.setSerNumRebPag(numeroRecibo.toString());

			registry.setSerObservaciones(observaciones);
			registry.setSerTotal(totalCombustible);
			registry.setSerUsrLogin(login);
			registry.setTariffs(tariffs);
			registry.setChargeTo(chargeTo);

			// if (prepaid != null)
			// registry.setPrepaid(prepaid);

			if (assignation != null
					&& assignation.getRequests().getLegateesTypes() != null)
				registry.setLegateesTypes(assignation.getRequests()
						.getLegateesTypes());

			if (vehicles != null) {
				// Se solicitad no actualizar el kilometraje del vehículo
				// vehicles.setVhcKilometrajeActual(kilometrajeActual);
				registry.setVehicles(vehicles);
			}

			// Tipo solicitante PDA "OTROS"
			if (fuelTypeRequest.getFtrCodigo().longValue() == ParametersUtil.SOLICITUD_COMB_OTROS)
				registry.setSerPlacaIntra(placa);

			// Tipo solicitante PDA "PARQUE CIENTIFICO"
			if (fuelTypeRequest.getFtrCodigo().longValue() == ParametersUtil.SOLICITUD_COMB_PARQUE)
				registry.setSerPlacaIntra(placa);

			// Tipo solicitante PDA "MOTO_CIAT"
			if (fuelTypeRequest.getFtrCodigo().longValue() == ParametersUtil.SOLICITUD_COMB_MOTO_CIAT)
				registry.setSerPlacaIntra(placa);

			registry.setSerTotal(total);

			if ((assignation != null
					&& assignation.getRequests().getLegateesTypes() != null && assignation
					.getRequests().getLegateesTypes().getLgtCodigo()
					.longValue() == ParametersUtil.LGT_PERSONAL)
					|| (chargeTo.getChtCodigo().longValue() == ParametersUtil.CARGO_CARNE
							.longValue()))
				flatFile = new FlatFileFuelServiceImpl()
						.generarArchivoPlanoCombustible(carneAsignatario,
								fechaActual, total, numeroGalones, centroCosto,
								placa, login, tariffs);

			if (usoDisponible != null
					&& usoDisponible.longValue() == ParametersUtil.USO_PRESUPUESTO
					&& ((chargeTo.getChtCodigo().longValue() == ParametersUtil.CARGO_A_TERCEROS 
							|| chargeTo.getChtCodigo().longValue() == ParametersUtil.CARGO_A_CC) 
					&& fuelTypeRequest.getFtrCodigo().longValue() != ParametersUtil.SOLICITUD_COMB_OTROS)) {

				Long tipoCargo = null;
				Period period = FlatFileFuelServiceImpl.consultarPeriodoByNovedad(
						fechaActual,
						ParametersUtil.NOVEDAD_COMB);

				if (chargeTo.getChtCodigo().longValue() == ParametersUtil.CARGO_A_CC)
					tipoCargo = ParametersUtil.CARGO_CC;
				else
					tipoCargo = ParametersUtil.CARGO_TERCEROS;

				if(centroCosto != null){
				
					headerProof = new PrepaidProofBoughtServiceImpl().generarCabeceraComprobante( 
									ParametersUtil.COMPRANTE_COMBUSTIBLE, period,
									ParametersUtil.TRASACCTION_TYPE,
									ParametersUtil.DOLAR);
	
					String idMaster = new ConsultsServiceImpl().getIdMaster();
					
					Long idDetail = Long.valueOf(1);

					connection = new BudgetFuelProofServiceImpl().generarComprobanteDetalle(connection,
							carneAsignatario, ParametersUtil.COMPRANTE_COMBUSTIBLE,
							login, total, ParametersUtil.DEBITO, centroCosto,
							tipoCargo, placa, fechaActual, numeroGalones,
							fuelTypeRequest, period, headerProof,
							idMaster, idDetail);
					idDetail++;
					connection = new BudgetFuelProofServiceImpl().generarComprobanteDetalle(connection, 
							carneAsignatario,ParametersUtil.COMPRANTE_COMBUSTIBLE, 
							login, total, ParametersUtil.CREDITO, null, 
							tipoCargo, placa, fechaActual, numeroGalones,
							fuelTypeRequest, period, headerProof,
							idMaster, idDetail);
					
					connection = ConsultsServiceImpl.insertTMaster(connection, idMaster, "P", idDetail.intValue());
				}else {					
					throw new GWorkException("Centro de Costo nulo, por favor verificar.");
				}
				
			}
			// EntityManagerHelper.getEntityManager().getTransaction().begin();
			if (headerProof != null)
				registry.setHeaderProof(headerProof);

			if (flatFile != null) {
				new FlatFileDAO().save(flatFile);
				registry.setFlatFile(flatFile);
			}

			// if (vehicles != null)
			// new VehiclesDAO().update(vehicles);
			new ServiceRegistryDAO().save(registry);

			if (costsCenters != null)
				new CostsCentersDAO().update(costsCenters);

			if (prepaidConsumption != null) {
				prepaidConsumption.setServiceRegistry(registry);
				new PrepaidConsumptionDAO().save(prepaidConsumption);
			}

			total = Util.redondear(total.doubleValue(), 2).floatValue();
			numeroGalones = Util.redondear(numeroGalones, 2).floatValue();

			String cargoA = "";

			if (chargeTo.getChtCodigo().longValue() == ParametersUtil.CARGO_A_CC
					&& usoDisponible.longValue() == ParametersUtil.USO_PREPAGO)
				cargoA = centroCosto + "-" + Util.loadMessageValue("PREPAGO");

			else if (chargeTo.getChtCodigo().longValue() == ParametersUtil.CARGO_A_CC
					&& usoDisponible.longValue() == ParametersUtil.USO_PRESUPUESTO)
				cargoA = centroCosto + " - "
						+ Util.loadMessageValue("PRESUPUESTO");

			else if (chargeTo.getChtCodigo().longValue() == ParametersUtil.CARGO_A_CARNE)
				cargoA = carneAsignatario;
			else if (chargeTo.getChtCodigo().longValue() == ParametersUtil.CARGO_A_TERCEROS)
				cargoA = carneAsignatario;

			// /**
			// * notificacion para el administrador del POOL
			// */
			Rolls rolls = new RollsDAO().findById(new Long(Util
					.loadParametersValue("MAIL.ADMINISTRADOR")));

			String emailAdminitrador = rolls.getRlsMail();
			log.info("ADMINISTRADOR: " + rolls.getRlsMail());
			String emailCopia = "";
			String tipoAsignacion = "";
			String nombreAsignatario = "";
			String emailCencos = "";
			String centroCostoCombustible = "";
			List<CostCentersFuel> listaCetroCostoCombustible = null;

			listaCetroCostoCombustible = SearchPrepaidServiceImpl
					.listaCCFByPlaca(placa);

			if (listaCetroCostoCombustible != null
					&& listaCetroCostoCombustible.size() > 0)
				for (CostCentersFuel costCentersFuel : listaCetroCostoCombustible) {
					if (costCentersFuel.getCostsCenters().getCocNumero()
							.equalsIgnoreCase(centroCosto)) {
						centroCostoCombustible = costCentersFuel
								.getCostsCenters().getCocNumero();
						break;
					}
				}

			if (centroCosto != null && centroCosto.trim().length() > 0
					&& !centroCosto.equalsIgnoreCase(centroCostoCombustible))
				emailCencos = new ConsultsServiceImpl()
						.consultEmailResponsableCENCOS(centroCosto);

			// Valida si existe asignación para enviar copia de los mensajes
			if (assignation != null && assignation.getRequests() != null) {
				if (assignation.getRequests().getUsersByRqsUser() != null
						&& assignation.getRequests().getUsersByRqsUser()
								.getUsrEmail() != null
						&& assignation.getRequests().getUsersByRqsUser()
								.getUsrEmail().trim().length() > 0
						&& assignation.getRequests().getUsersByUsrCodigo() != null
						&& assignation.getRequests().getUsersByUsrCodigo()
								.getUsrEmail() != null
						&& assignation.getRequests().getUsersByUsrCodigo()
								.getUsrEmail().trim().length() > 0) {
					// Valida que el mail del asignatario sea diferente al mail
					// del centro de costo para no replicar el mensaje
					if (emailCencos != null
							&& emailCencos.equalsIgnoreCase(assignation
									.getRequests().getUsersByRqsUser()
									.getUsrEmail()))
						emailCopia = assignation.getRequests()
								.getUsersByRqsUser().getUsrEmail()
								+ ";"
								+ assignation.getRequests()
										.getUsersByUsrCodigo().getUsrEmail();
					else if (emailCencos != null
							&& emailCencos.trim().length() > 0)
						emailCopia = assignation.getRequests()
								.getUsersByRqsUser().getUsrEmail()
								+ ";"
								+ assignation.getRequests()
										.getUsersByUsrCodigo().getUsrEmail()
								+ ";" + emailCencos;
					else
						emailCopia = assignation.getRequests()
								.getUsersByRqsUser().getUsrEmail()
								+ ";"
								+ assignation.getRequests()
										.getUsersByUsrCodigo().getUsrEmail();

				} else if ((assignation.getRequests().getUsersByRqsUser() != null
						&& assignation.getRequests().getUsersByRqsUser()
								.getUsrEmail() != null && assignation
						.getRequests().getUsersByRqsUser().getUsrEmail().trim()
						.length() > 0)) {
					// Valida que el mail del asignatario sea diferente al mail
					// del centro de costo para no replicar el mensaje
					if (emailCencos != null
							&& emailCencos.equalsIgnoreCase(assignation
									.getRequests().getUsersByRqsUser()
									.getUsrEmail()))
						emailCopia = assignation.getRequests()
								.getUsersByRqsUser().getUsrEmail();
					else if (emailCencos != null
							&& emailCencos.trim().length() > 0)
						emailCopia = assignation.getRequests()
								.getUsersByRqsUser().getUsrEmail()
								+ ";" + emailCencos;

					else
						emailCopia = assignation.getRequests()
								.getUsersByRqsUser().getUsrEmail();
				}

				nombreAsignatario = assignation.getRequests()
						.getUsersByRqsUser().getUsrNombre();
			} else if (assignation == null && emailCencos != null
					&& emailCencos.trim().length() > 0) {
				emailCopia = emailCencos;
			}

			if (assignation != null
					&& assignation.getRequests().getLegateesTypes() != null)
				tipoAsignacion = assignation.getRequests().getLegateesTypes()
						.getLgtNombre();
			SendEmail mail = notificarServicioCombustible(placa, fechaActual,
					hora, numeroGalones, total, chargeTo, nombreSolicitante,
					assignation, observaciones, emailAdminitrador, emailCopia,
					cargoA, fuelTypeRequest.getFtrNombre(), kilometrajeActual,
					tipoAsignacion, nombreAsignatario);

			setMail(mail);

			return connection;

		} catch (Exception e) {
			log.error("guardarRegistro -> ", e);
			
			if (connection != null && !connection.isClosed())
				connection.rollback();
			
			throw new GWorkException(e.getMessage(), e);
		}
	}

	/**
	 * Guardar consumo prepago.
	 *
	 * @param prepaidConsumption the prepaid consumption
	 * @param costsCenters the costs centers
	 * @throws GWorkException the g work exception
	 */
	public void guardarConsumoPrepago(PrepaidConsumption prepaidConsumption,
			CostsCenters costsCenters) throws GWorkException {
		try {

			new CostsCentersDAO().save(costsCenters);
			new PrepaidConsumptionDAO().save(prepaidConsumption);
		} catch (Exception e) {
			log.error("guardarConsumoPrepago -> " + e.getMessage(), e);
			throw new GWorkException(e.getMessage(), e);
		}
	}

	/**
	 * Consult cost centers fuel.
	 *
	 * @param IdCodigoCosto the id codigo costo
	 * @return the list
	 * @throws GWorkException the g work exception
	 */
	@SuppressWarnings("unchecked")
	public List<CostCentersFuel> consultCostCentersFuel(Long IdCodigoCosto)
			throws GWorkException {
		try {
			List<CostCentersFuel> resultado = null;
			final String queryString = "select model from CostCentersFuel  model where model.costsCenters.cocCodigo = :codigoCosto order by model.ccfValor ASC";
			Query query = EntityManagerHelper.getEntityManager().createQuery(
					queryString);
			query.setParameter("codigoCosto", IdCodigoCosto);

			resultado = (List<CostCentersFuel>) query.getResultList();

			return resultado;

		} catch (Exception re) {
			log.error("consultCostCentersFuel -> " + re.getMessage(), re);
			throw new GWorkException(Util
					.loadErrorMessageValue("search.not.found"), re);
		}
	}

	/**
	 * Consult cost centers fuel codigo.
	 *
	 * @param codigoCosto the codigo costo
	 * @return the list
	 * @throws GWorkException the g work exception
	 */
	@SuppressWarnings("unchecked")
	public List<CostCentersFuel> consultCostCentersFuelCodigo(String codigoCosto)
			throws GWorkException {
		try {
			List<CostCentersFuel> resultado = null;
			final String queryString = "select model from CostCentersFuel  model where model.costsCenters.cocNumero = :codigoCosto order by model.ccfValor ASC";
			Query query = EntityManagerHelper.getEntityManager().createQuery(
					queryString);
			query.setParameter("codigoCosto", codigoCosto);

			resultado = (List<CostCentersFuel>) query.getResultList();

			return resultado;

		} catch (Exception re) {
			log.error("consultCostCentersFuelCodigo", re);
			throw new GWorkException(Util
					.loadErrorMessageValue("search.not.found"), re);
		}
	}

	// SELECT distinct tabla1.ccf_id, (tabla1.valor1 - tabla2.valor_consumo) as
	// resta, tabla1.valor1
	// FROM (SELECT ccf.ccf_id, ccf_valor valor1
	// FROM cost_centers_fuel ccf) tabla1,
	// (SELECT pco.ccf_id, SUM (prc_valor_consumo) valor_consumo
	// FROM prepaid_consumption pco
	// GROUP BY pco.ccf_id) tabla2
	// where tabla1.ccf_id= tabla2.ccf_id
	// order by resta

	/**
	 * Consult prepaid consumption2.
	 *
	 * @param IdCodigoCosto the id codigo costo
	 * @throws GWorkException the g work exception
	 */
	public void consultPrepaidConsumption2(Long IdCodigoCosto)
			throws GWorkException {
		try {

			Float resultado = null;
			StringBuffer buffer = new StringBuffer();
			buffer
					.append("SELECT distinct tabla1.ccf_id, (tabla1.valor1 -  tabla2.valor_consumo) as resta, tabla1.valor1");
			buffer.append("FROM (SELECT ccf.ccf_id, ccf_valor valor1");
			buffer.append("FROM CostCentersFuel ccf) tabla1,");
			buffer
					.append("(SELECT  pco.ccf_id, SUM (prc_valor_consumo) valor_consumo");
			buffer.append("FROM PrepaidConsumption pco");
			buffer.append(" GROUP BY pco.ccf_id) tabla2");
			buffer.append(" where tabla1.ccf_id= tabla2.ccf_id  ");
			buffer.append("order by resta");

			// final String queryString = "select SUM(model.prcValorConsumo)
			// from PrepaidConsumption model where
			// model.costCentersFuel.costsCenters.cocCodigo = :IdCodigoCosto";
			Query query = EntityManagerHelper.getEntityManager().createQuery(
					buffer.toString());
			query.setParameter("IdCodigoCosto", IdCodigoCosto);

			resultado = (Float) query.getSingleResult();

			// return resultado;

		} catch (Exception re) {
			log.error("consultPrepaidConsumption2 -> " + re.getMessage(), re);
			throw new GWorkException(Util
					.loadErrorMessageValue("search.not.found"), re);
		}
	}

	/**
	 * Consult prepaid consumption.
	 *
	 * @param IdCodigoCosto the id codigo costo
	 * @return the double
	 * @throws GWorkException the g work exception
	 */
	@SuppressWarnings("unchecked")
	public Double consultPrepaidConsumption(Long IdCodigoCosto)
			throws GWorkException {
		try {
			List<Double> list = null;
			Double resultado = null;
			final String queryString = "select SUM(model.prcValorConsumo) from PrepaidConsumption model where model.costCentersFuel.costsCenters.cocCodigo = :IdCodigoCosto";
			Query query = EntityManagerHelper.getEntityManager().createQuery(
					queryString);
			query.setParameter("IdCodigoCosto", IdCodigoCosto);

			list = (List<Double>) query.getResultList();

			if (list != null && list.size() > 0)
				resultado = (Double) list.get(0);

			return resultado;

		} catch (Exception re) {
			log.error("consultPrepaidConsumption -> " + re.getMessage(), re);
			throw new GWorkException(Util
					.loadErrorMessageValue("search.not.found"), re);
		}
	}

	/**
	 * Validar disponibilidad prepago.
	 *
	 * @param idCentroCostos the id centro costos
	 * @return the cost centers fuel
	 * @throws GWorkException the g work exception
	 */
	public CostCentersFuel validarDisponibilidadPrepago(Long idCentroCostos)
			throws GWorkException {
		Float valorCentroCosto = null;

		Double valorConsumoPrepago = consultPrepaidConsumption(idCentroCostos);
		List<CostCentersFuel> listCostCenter = consultCostCentersFuel(idCentroCostos);

		for (CostCentersFuel costCentersFuel : listCostCenter) {
			valorCentroCosto = costCentersFuel.getCcfValor();
			if (valorConsumoPrepago != null
					&& valorConsumoPrepago < valorCentroCosto)
				return costCentersFuel;
		}

		return null;
	}

	/**
	 * Last service registry placa.
	 *
	 * @param placa the placa
	 * @return the service registry
	 * @throws GWorkException the g work exception
	 */
	@SuppressWarnings("unchecked")
	public ServiceRegistry lastServiceRegistryPlaca(String placa)
			throws GWorkException {
		try {

			List<ServiceRegistry> valor = null;
			ServiceRegistry serviceRegistry = null;
			final String queryString = "select model from ServiceRegistry model where model.vehicles.vhcPlacaDiplomatica = :placa "
					+ "ORDER BY model.serCodigo DESC";

			Query query = EntityManagerHelper.getEntityManager().createQuery(
					queryString);
			query.setParameter("placa", placa);

			valor = query.getResultList();

			if (valor != null && valor.size() > 0)
				serviceRegistry = valor.get(0);

			return serviceRegistry;

		} catch (RuntimeException re) {
			log.error("lastServiceRegistryPlaca", re);
			throw new GWorkException(Util
					.loadErrorMessageValue("search.not.found"), re);
		}
	}

	/**
	 * Calcular tope minimo.
	 *
	 * @param capacidadTanque the capacidad tanque
	 * @param tarifa the tarifa
	 * @return the float
	 */
	public Float calcularTopeMinimo(Float capacidadTanque, Float tarifa) {
		return capacidadTanque * tarifa;
	}

	/**
	 * Notificar servicio combustible.
	 *
	 * @param placa the placa
	 * @param fecha the fecha
	 * @param hora the hora
	 * @param galones the galones
	 * @param valor the valor
	 * @param tipoCargo the tipo cargo
	 * @param solicitante the solicitante
	 * @param assignation the assignation
	 * @param observaciones the observaciones
	 * @param email the email
	 * @param emailCC the email cc
	 * @param cargoA the cargo a
	 * @param tipoSolicitud the tipo solicitud
	 * @param kilometrajeActual the kilometraje actual
	 * @param tipoAsignacion the tipo asignacion
	 * @param nombreAsignatario the nombre asignatario
	 * @return the send email
	 * @throws GWorkException the g work exception
	 */
	public SendEmail notificarServicioCombustible(String placa, Date fecha,
			String hora, Float galones, Float valor, ChargeTo tipoCargo,
			String solicitante, VehiclesAssignation assignation,
			String observaciones, String email, String emailCC, String cargoA,
			String tipoSolicitud, Long kilometrajeActual,
			String tipoAsignacion, String nombreAsignatario)
			throws GWorkException {

		try {

			@SuppressWarnings("unused")
			Rolls rolls = new RollsDAO().findById(new Long(Util
					.loadParametersValue("MAIL.ADMINISTRADOR")));

			String server = Util.loadParametersValue("MAIL.HOST");
			// String fromUser = rolls.getRlsMail();
			String fromUser = email;
			String toUser = email.toLowerCase();
			String subject = Util.loadParametersValue("SUBJECT_SERVICE_FUEL")
					+ " " + placa;
			StringBuffer mensaje;

			SimpleDateFormat fechaFormato = new SimpleDateFormat("dd-MMM-yyyy");

			String nota = Util.loadMessageValue("NINGUNO");

			if (tipoCargo.getChtCodigo().longValue() == ParametersUtil.CARGO_A_CARNE) {
				nota = Util.loadMessageValue("NOTA_PERSONAL");
			}

			StringBuffer detalleServicio = new StringBuffer();
			detalleServicio = detalleServicio.append(
					Util.loadMessageValue("SALUDO_MAIL")).append("<BR>")
					.append("<BR>").append(
							Util.loadMessageValue("TIPO_SOLICITUD")).append(
							tipoSolicitud).append("<br>").append(
							Util.loadMessageValue("PLACA")).append(placa)
					.append("<br>").append(Util.loadMessageValue("FECHA"))
					.append(fechaFormato.format(fecha)).append("<br>").append(
							Util.loadMessageValue("HORA")).append(hora).append(
							"<br>")
					.append(Util.loadMessageValue("GALONESFUEL")).append(
							galones).append("<br>").append(
							Util.loadMessageValue("VALOR") + "US$ ").append(
							valor).append("<br>").append(
							Util.loadMessageValue("KM_ACTUAL")).append(
							kilometrajeActual).append("<BR>").append(
							Util.loadMessageValue("TIPO_CARGO")).append(
							tipoCargo.getChtNombre()).append("<BR>").append(
							Util.loadMessageValue("CARGO_SERVICIO")).append(
							cargoA).append("<BR>").append(
							Util.loadMessageValue("TIPO_ASIGNACION")).append(
							tipoAsignacion).append("<BR>").append(
							Util.loadMessageValue("NOMBRE_ASIGN")).append(
							nombreAsignatario).append("<BR>").append(
							Util.loadMessageValue("SOLICITANTE")).append(
							solicitante).append("<br>").append(
							Util.loadMessageValue("OBSERVACIONES")).append(
							observaciones).append("<br>").append(
							Util.loadMessageValue("NOTA")).append(nota);

			SendEmail mail = new SendEmail(toUser, emailCC, fromUser, server,
					"false", subject, detalleServicio.toString());

			return mail;

		} catch (Exception e) {
			log.error("notificarServicioCombustible", e);
			throw new GWorkException(Util
					.loadErrorMessageValue("NOTIFICACION.ERROR")
					+ " " + e.getMessage(), e);
		}
	}

	/**
	 * Enviar notificacion combustible.
	 *
	 * @param mail the mail
	 * @throws GWorkException the g work exception
	 */
	void enviarNotificacionCombustible(SendEmail mail) throws GWorkException {

		try {
			String enviado = mail.SendMessage();
			if (enviado.equals("ERROR")) {
				log.info("No se pudo enviar el mensaje");
				throw new GWorkException("No se pudo enviar la notificación");
			} else
				log.info("Mensaje enviado exitosamente");
		} catch (Exception e) {
			log.error("enviarNotificacionCombustible", e);
			throw new GWorkException(e.getMessage() + "\n"
					+ e.getLocalizedMessage(), e);
		}

	}

	/**
	 * Gets the mail.
	 *
	 * @return the mail
	 */
	public SendEmail getMail() {
		return mail;
	}

	/**
	 * Sets the mail.
	 *
	 * @param mail the new mail
	 */
	public void setMail(SendEmail mail) {
		this.mail = mail;
	}
}
