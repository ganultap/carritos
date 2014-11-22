package geniar.siscar.logic.vehicle.services.impl;

import geniar.siscar.consults.impl.ConsultsServiceImpl;
import geniar.siscar.logic.billing.services.RentProofService;
import geniar.siscar.logic.billing.services.impl.AssignationProofServiceImpl;
import geniar.siscar.logic.billing.services.impl.FlatFileFuelServiceImpl;
import geniar.siscar.logic.billing.services.impl.PrepaidProofBoughtServiceImpl;
import geniar.siscar.logic.billing.services.impl.RentProofServiceImpl;
import geniar.siscar.logic.billing.services.impl.SearchParametersBilling;
import geniar.siscar.logic.consultas.ConsultTariff;
import geniar.siscar.logic.consultas.SearchAccountingParameters;
import geniar.siscar.logic.consultas.SearchCostCenters;
import geniar.siscar.logic.consultas.SearchVehicles;
import geniar.siscar.logic.devolution.services.DevolutionService;
import geniar.siscar.logic.devolution.services.impl.DevolutionServiceImpl;
import geniar.siscar.logic.parameters.services.FindParameterService;
import geniar.siscar.logic.parameters.services.TariffService;
import geniar.siscar.logic.parameters.services.impl.TariffServiceImpl;
import geniar.siscar.logic.parametros.services.impl.FindParameterServiceImpl;
import geniar.siscar.logic.vehicle.services.AllocationReturnVehicleService;
import geniar.siscar.logic.vehicle.services.VehicleService;
import geniar.siscar.mail.util.SendEmail;
import geniar.siscar.model.AccountingParameters;
import geniar.siscar.model.ActualOthersApplications;
import geniar.siscar.model.AssignationsStates;
import geniar.siscar.model.CostsCentersVehicles;
import geniar.siscar.model.CurrencyTypes;
import geniar.siscar.model.HeaderProof;
import geniar.siscar.model.MovementType;
import geniar.siscar.model.Period;
import geniar.siscar.model.ProofState;
import geniar.siscar.model.ProofType;
import geniar.siscar.model.Requests;
import geniar.siscar.model.Rolls;
import geniar.siscar.model.Tariffs;
import geniar.siscar.model.TransactionType;
import geniar.siscar.model.Vehicles;
import geniar.siscar.model.VehiclesAssignation;
import geniar.siscar.model.VehiclesStates;
import geniar.siscar.model.VhaAoaApp;
import geniar.siscar.persistence.AccountingParametersDAO;
import geniar.siscar.persistence.ActualOthersApplicationsDAO;
import geniar.siscar.persistence.AssignationsStatesDAO;
import geniar.siscar.persistence.CurrencyTypesDAO;
import geniar.siscar.persistence.EntityManagerHelper;
import geniar.siscar.persistence.HeaderProofDAO;
import geniar.siscar.persistence.MovementTypeDAO;
import geniar.siscar.persistence.ProofStateDAO;
import geniar.siscar.persistence.ProofTypeDAO;
import geniar.siscar.persistence.RollsDAO;
import geniar.siscar.persistence.TransactionTypeDAO;
import geniar.siscar.persistence.VehiclesAssignationDAO;
import geniar.siscar.persistence.VehiclesDAO;
import geniar.siscar.persistence.VehiclesStatesDAO;
import geniar.siscar.persistence.VhaAoaAppDAO;
import geniar.siscar.util.ManipulacionFechas;
import geniar.siscar.util.ParametersUtil;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * The Class AllocationReturnVehicleServiceImpl.
 */
public class AllocationReturnVehicleServiceImpl implements
		AllocationReturnVehicleService {
	
	/** The Constant log. */
	private static final Log log = LogFactory.getLog(AllocationReturnVehicleServiceImpl.class);

	/**
	 * Crear devolucion vehiculo.
	 *
	 * @param vhcPlacaDiplomatica the vhc placa diplomatica
	 * @param fechaEntrega the fecha entrega
	 * @param kilometraje the kilometraje
	 * @param estadoVehiculo the estado vehiculo
	 * @param tipoAsignacion the tipo asignacion
	 * @param vehicles the vehicles
	 * @param cobroTarifaAsignacion the cobro tarifa asignacion
	 * @param devolucionValorAsignacion the devolucion valor asignacion
	 * @param connection the connection
	 * @throws GWorkException the g work exception
	 */
	public void crearDevolucionVehiculo(String vhcPlacaDiplomatica,
			Date fechaEntrega, Long kilometraje, Long estadoVehiculo,
			String tipoAsignacion, Vehicles vehicles,
			Float cobroTarifaAsignacion, Float devolucionValorAsignacion,
			Connection connection) throws GWorkException {
		try {
			DevolutionService devolutionService = new DevolutionServiceImpl();

			EntityManagerHelper.getEntityManager().getTransaction().begin();

			devolutionService.crearDevolucionVehiculo(fechaEntrega,
					kilometraje, estadoVehiculo, devolucionValorAsignacion,
					cobroTarifaAsignacion, vehicles, tipoAsignacion);
			
			EntityManagerHelper.getEntityManager().getTransaction().commit();

			if (connection != null)
				connection.commit();

		} catch (Exception e) {
			EntityManagerHelper.rollback();
			limpiarSession();
			validarSession();
			log.error("Error SISCAR: ", e);
			throw new GWorkException(e.getMessage());
		} finally{
			EntityManagerHelper.closeEntityManager();
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					log.error("Error SISCAR: ", e);
					throw new GWorkException(e.getMessage());
				}
			}
		}
	}

	/**
	 * Busca si la asignacion se encuentra en un periodo de fechas.
	 *
	 * @param vehiclesAssignation the vehicles assignation
	 * @return true, if successful
	 * @throws GWorkException the g work exception
	 */
	@SuppressWarnings("unchecked")
	public boolean consultarAsignacionPorPeriodoFechas(
			VehiclesAssignation vehiclesAssignation) throws GWorkException {
		try {

			StringBuffer buffer = new StringBuffer();
			buffer
					.append("select model from VhaAoaApp model where model.vehiclesAssignation.vhaCodigo = :idAsignacion");
			buffer
					.append(" and model.headerProof.period.perFechaIni >= :fechaIni");
			buffer
					.append(" and model.headerProof.period.perFechaFin >= :fechaFin");
			List<VhaAoaApp> listVhaAoaApp = null;

			Query query = EntityManagerHelper.getEntityManager().createQuery(
					buffer.toString());
			query.setParameter("idAsignacion", vehiclesAssignation
					.getVhaCodigo());
			query.setParameter("fechaIni", vehiclesAssignation
					.getVhaFechaInicio());
			query.setParameter("fechaFin", vehiclesAssignation
					.getVhaFechaTermina());
			listVhaAoaApp = (List<VhaAoaApp>) query.getResultList();

			if (listVhaAoaApp != null && !listVhaAoaApp.isEmpty())
				return true;

			return false;

		} catch (Exception e) {
			throw new GWorkException(e.getMessage());
		}
	}

	/**
	 * Consulta las tarifas actuales de asignación (depresiacion, mantenimiento,
	 * autoseguro) y saca el resultado total de las 3 el numero indica el tipo
	 * de ubicacion del vehíco debido a que esta tarifa depende de ello.
	 *
	 * @param vehicles the vehicles
	 * @param numero the numero
	 * @return the float
	 * @throws GWorkException the g work exception
	 */
	public Float consultarTarifaAsignacion(Vehicles vehicles, Long numero)
			throws GWorkException {

		ConsultTariff consultTariff = new ConsultTariff();
		Float valorAsignacionDepre = null;
		Float valorAsignacionMantenimiento = null;
		Float valorAsignacionAutoseguro = null;
		Float valoresSumaAsignacion = null;
		Tariffs tariffs = null;
		Long idLinea = null;
		Long idTipoCombustible = null;
		Long idTipoTraccion = null;
		List<Tariffs> listTariffs = null;

		if (vehicles != null && vehicles.getLines() != null
				&& vehicles.getFuelsTypes() != null
				&& vehicles.getTractionsTypes() != null) {

			idLinea = vehicles.getLines().getLnsId();
			idTipoCombustible = vehicles.getFuelsTypes().getFutCodigo();
			idTipoTraccion = vehicles.getTractionsTypes().getTctCodigo();

			// entra a consultar la tarifa si es sede.
			if (numero == 1) {
				listTariffs = consultTariff
						.consultarTarifaAsignacionMantenimiento(vehicles
								.getLines().getLnsId(), vehicles
								.getFuelsTypes().getFutCodigo(), vehicles
								.getTractionsTypes().getTctCodigo());

				if (listTariffs != null && listTariffs.size() > 0) {
					tariffs = listTariffs.get(0);

					if (tariffs.getTrfValor() != null)
						valorAsignacionMantenimiento = tariffs.getTrfValor();
					else
						valorAsignacionMantenimiento = new Float(0);
				}
			} else
				valorAsignacionMantenimiento = new Float(0);

			// esta tarifa se cobra estando el vehiculo en cualquier sede
			listTariffs = consultTariff.consultarTarifaAsignacionDepreciacion(
					idLinea, idTipoCombustible, idTipoTraccion);

			if (listTariffs != null && listTariffs.size() > 0) {
				tariffs = listTariffs.get(0);

				if (tariffs.getTrfValor() != null)
					valorAsignacionDepre = tariffs.getTrfValor();
				else
					valorAsignacionDepre = new Float(0);
			}
			// esta tarifa se cobra en caso de que sea sede o estacion
			if (numero == 1 || numero == 2) {
				listTariffs = consultTariff
						.consultarTarifaAsignacionAutoseguro(idLinea,
								idTipoCombustible, idTipoTraccion);

				if (listTariffs != null && listTariffs.size() > 0) {
					tariffs = listTariffs.get(0);

					if (tariffs.getTrfValor() != null)
						valorAsignacionAutoseguro = tariffs.getTrfValor();
					else
						valorAsignacionAutoseguro = new Float(0);
				}
			} else
				valorAsignacionAutoseguro = new Float(0);

			if (valorAsignacionAutoseguro != null
					&& valorAsignacionDepre != null
					&& valorAsignacionMantenimiento != null)
				valoresSumaAsignacion = valorAsignacionAutoseguro
						+ valorAsignacionDepre + valorAsignacionMantenimiento;

			if (valoresSumaAsignacion != null
					&& valoresSumaAsignacion.longValue() != 0)
				return valoresSumaAsignacion;
			else
				return new Float(0);

		}
		return valoresSumaAsignacion;
	}

	/**
	 * Validar disponibilidad centros de costo.
	 *
	 * @param assignation the assignation
	 * @param tarifaAsignacion the tarifa asignacion
	 * @return the sets the
	 * @throws GWorkException the g work exception
	 */
	public Set<CostsCentersVehicles> validarDisponibilidadCentrosDeCosto(
			VehiclesAssignation assignation, Float tarifaAsignacion)
			throws GWorkException {

		boolean isValid = false;
		Double porcentaje = new Double(0);
		Double valor = new Double(0);
		Set<CostsCentersVehicles> listaCentros = new HashSet<CostsCentersVehicles>();
		listaCentros = validarDistribucionPresupuestal(validarCentroCostos(assignation
				.getRequests().getCostsCentersVehicleses()));

		// El sistema consulta la distribución presupuestal por vehículo
		for (CostsCentersVehicles costsCentersVehicles : listaCentros) {

			porcentaje = new Double(costsCentersVehicles.getCcrPorcentaje()) / 100;
			valor = tarifaAsignacion * porcentaje;
			costsCentersVehicles.setCcrValor(valor.longValue());
			isValid = true;// consultsService.consultarDisponibiladaPresupuestal(new
			// Integer(ManipulacionFechas.getAgno(new Date())),
			// costsCentersVehicles.getCostsCenters().getCocNumero(),
			// "51410300", "",
			// costsCentersVehicles.getCcrValor().doubleValue());

			if (isValid) {
				listaCentros.add(costsCentersVehicles);

			}
		}
		return listaCentros;
	}

	/**
	 * Validar centro costos.
	 *
	 * @param list the list
	 * @return the sets the
	 * @throws GWorkException the g work exception
	 */
	public Set<CostsCentersVehicles> validarCentroCostos(
			Set<CostsCentersVehicles> list) throws GWorkException {
		Set<CostsCentersVehicles> set = new HashSet<CostsCentersVehicles>();
		for (CostsCentersVehicles costsCentersVehicles : list) {
			// if
			// (consultsServiceImpl.validateCostCenter(costsCentersVehicles.getCostsCenters().getCocNumero()))
			// {
			set.add(costsCentersVehicles);
			// }
		}
		return set;
	}

	/**
	 * Metodo que valida la distribucion presupuestal.
	 *
	 * @param listaCentros the lista centros
	 * @return the sets the
	 */
	public Set<CostsCentersVehicles> validarDistribucionPresupuestal(
			Set<CostsCentersVehicles> listaCentros) {

		Long suma = new Long(0);
		Long nuevoValor = new Long(0);
		Set<CostsCentersVehicles> lista = new HashSet<CostsCentersVehicles>();

		for (CostsCentersVehicles costsCentersVehicles : listaCentros) {
			suma += new Float(costsCentersVehicles.getCcrPorcentaje()).longValue();
		}

		for (CostsCentersVehicles costsCentersVehicles : listaCentros) {
			Long centroCosto = new Long(costsCentersVehicles.getCcrPorcentaje());
			nuevoValor = (centroCosto * 100) / suma;
			costsCentersVehicles.setCcrPorcentaje(nuevoValor.toString());
			lista.add(costsCentersVehicles);
		}
		return lista;

	}

	/* (non-Javadoc)
	 * @see geniar.siscar.logic.vehicle.services.AllocationReturnVehicleService#consultarTarifaVehiculoUbicacion(geniar.siscar.model.Vehicles)
	 */
	public Float consultarTarifaVehiculoUbicacion(Vehicles vehicles)
			throws GWorkException {
		Long numero = vehicles.getLocations().getLocationsTypes()
				.getLctCodigo();
		Float tarifaAsignacion = null;
		if (numero != null) {
			if (vehicles != null && numero.equals(1L))
				tarifaAsignacion = consultarTarifaAsignacion(vehicles, numero);

			if (vehicles != null && numero.equals(2L))
				tarifaAsignacion = consultarTarifaAsignacion(vehicles, numero);

			if (vehicles != null && numero.equals(3L))
				tarifaAsignacion = consultarTarifaAsignacion(vehicles, numero);
		}
		return tarifaAsignacion;
	}

	/**
	 * Metodo para validar los casos del comprobante de asignacion.
	 *
	 * @param assignation the assignation
	 * @param tarifaAsignacion the tarifa asignacion
	 * @return true, if successful
	 * @throws GWorkException the g work exception
	 */
	public boolean validarCasosGenerarComprobante(
			VehiclesAssignation assignation, Float tarifaAsignacion)
			throws GWorkException {

		if (assignation != null)
			if (assignation.getVhaCobro().equalsIgnoreCase("S")) {
				validarDisponibilidadCentrosDeCosto(assignation,
						tarifaAsignacion);
			}
		return true;
	}

	/**
	 * Generar cabecera comprobante.
	 *
	 * @param idProofState the id proof state
	 * @param idProofType the id proof type
	 * @param idTransactionType the id transaction type
	 * @param idCurrencyTypes the id currency types
	 * @param groupId the group id
	 * @return the header proof
	 * @throws GWorkException the g work exception
	 */
	public HeaderProof generarCabeceraComprobante(Long idProofState,
			Long idProofType, Long idTransactionType, Long idCurrencyTypes,
			String groupId) throws GWorkException {

		HeaderProof headerProof = new HeaderProof();

		ProofState proofState = new ProofStateDAO().findById(idProofState);

		if (proofState == null)
			throw new GWorkException(Util.loadErrorMessageValue(""));

		headerProof.setProofState(proofState);

		ProofType proofType = new ProofTypeDAO().findById(idProofType);

		if (proofType == null)
			throw new GWorkException(Util.loadErrorMessageValue(""));

		headerProof.setProofType(proofType);

		TransactionType transactionType = new TransactionTypeDAO()
				.findById(idTransactionType);

		if (transactionType == null)
			throw new GWorkException(Util.loadErrorMessageValue(""));

		headerProof.setTransactionType(transactionType);

		CurrencyTypes currencyTypes = new CurrencyTypesDAO()
				.findById(idCurrencyTypes);

		if (currencyTypes == null)
			throw new GWorkException(Util.loadErrorMessageValue(""));

		headerProof.setCurrencyTypes(currencyTypes);
		headerProof.setHepGroupId(groupId);
		return guardarCabeceraComprobante(headerProof);
	}

	/**
	 * Guardar cabecera comprobante.
	 *
	 * @param headerProof the header proof
	 * @return the header proof
	 * @throws GWorkException the g work exception
	 */
	public HeaderProof guardarCabeceraComprobante(HeaderProof headerProof)
			throws GWorkException {
		try {
			Util.validarSession();
			EntityManagerHelper.beginTransaction();
			new HeaderProofDAO().save(headerProof);
			EntityManagerHelper.commit();

			return headerProof;
		} catch (Exception e) {
			EntityManagerHelper.rollback();
			// Util.limpiarSession();
			throw new GWorkException(e.getMessage());
		}
	}

	/**
	 * Consultar parametrizacion contable asignatario.
	 *
	 * @param asignacion the asignacion
	 * @param idParametro the id parametro
	 * @return the accounting parameters
	 * @throws GWorkException the g work exception
	 */
	public AccountingParameters consultarParametrizacionContableAsignatario(
			VehiclesAssignation asignacion, Long idParametro)
			throws GWorkException {

		AccountingParameters parameters = null;
		if (asignacion.getRequests().getLegateesTypes().getLgtCodigo().equals(
				idParametro))
			parameters = new AccountingParametersDAO().findById(idParametro);

		if (asignacion.getRequests().getLegateesTypes().getLgtCodigo().equals(
				idParametro))
			parameters = new AccountingParametersDAO().findById(idParametro);

		if (asignacion.getRequests().getLegateesTypes().getLgtCodigo().equals(
				idParametro))
			parameters = new AccountingParametersDAO().findById(idParametro);

		if (asignacion.getRequests().getLegateesTypes().getLgtCodigo().equals(
				idParametro))
			parameters = new AccountingParametersDAO().findById(idParametro);

		return parameters;

	}

	/**
	 * Consultar parametrizacion contable.
	 *
	 * @param idParametro the id parametro
	 * @return the accounting parameters
	 * @throws GWorkException the g work exception
	 */
	public AccountingParameters consultarParametrizacionContable(
			Long idParametro) throws GWorkException {

		AccountingParameters parameters = new AccountingParametersDAO()
				.findById(idParametro);

		return parameters;

	}

	/**
	 * Generar estructura debito credito.
	 *
	 * @param asignacion the asignacion
	 * @param login the login
	 * @param tipoMoneda the tipo moneda
	 * @param tipoAsignacion the tipo asignacion
	 * @param tarifaAsignacion the tarifa asignacion
	 * @param tipoMovimiento the tipo movimiento
	 * @param proofType the proof type
	 * @param parameters the parameters
	 * @return the header proof
	 * @throws GWorkException the g work exception
	 */
	public HeaderProof generarEstructuraDebitoCredito(
			VehiclesAssignation asignacion, String login, Long tipoMoneda,
			String tipoAsignacion, Float tarifaAsignacion, Long tipoMovimiento,
			Long proofType, AccountingParameters parameters)
			throws GWorkException {

		Date fechaActual = new Date();
		// ConsultsService consultsService = new ConsultsServiceImpl();
		ActualOthersApplications actualOthersApplications = new ActualOthersApplications();
		actualOthersApplications.setAoaState("ACTIVO");

		String groupId = fechaActual + " || 18 || " + proofType + " "
				+ parameters.getAcpId();
		HeaderProof headerProof = generarCabeceraComprobante(new Long(Util
				.loadMessageValue("ESTADO.1")), proofType, parameters
				.getTransactionType().getTstId(), tipoMoneda, groupId);

		actualOthersApplications.setHeaderProof(headerProof);
		actualOthersApplications.setPAccdate(fechaActual);
		actualOthersApplications.setPAccount(parameters.getAccount()
				.getAccNumeroCuenta());
		actualOthersApplications.setPAttribute2(null);
		actualOthersApplications.setPAttribute5(parameters.getDocumentOne()
				.getDcoNumero());
		actualOthersApplications.setPAttribute6(asignacion.getVehicles()
				.getVhcPlacaDiplomatica());
		actualOthersApplications.setPAttribute7(null);
		actualOthersApplications.setPAttribute8(null);
		actualOthersApplications.setPAttribute9(parameters.getDocumentTwo()
				.getDctNumero());
		actualOthersApplications.setPAttribute10(ManipulacionFechas
				.getMes(fechaActual)
				+ " " + ManipulacionFechas.getDia(fechaActual));

		actualOthersApplications.setPAuxiliary(asignacion.getRequests()
				.getRqsCarnetEmpleado());

		actualOthersApplications.setPBname(parameters.getCompany()
				.getCmpNombre()
				+ " ||  ' - '  || "
				+ asignacion.getRequests().getUsersByRqsUser().getUsrNombre()
				+ " ||  ' - '  || " + parameters.getAccDescripcion());

		if (tipoAsignacion != null
				&& tipoAsignacion.equalsIgnoreCase(Util
						.loadParametersValue("p.category.asig")))
			actualOthersApplications.setPCategory(Util
					.loadParametersValue("p.category.asig"));

		if (tipoAsignacion != null
				&& tipoAsignacion.equalsIgnoreCase(Util
						.loadParametersValue("p.category.alq")))
			actualOthersApplications.setPCategory(Util
					.loadParametersValue("p.category.alq"));

		if (tipoAsignacion != null
				&& tipoAsignacion.equalsIgnoreCase(Util
						.loadParametersValue("p.category.comb")))
			actualOthersApplications.setPCategory(Util
					.loadParametersValue("p.category.comb"));

		actualOthersApplications.setPCcenter(Util
				.loadParametersValue("p.pcenter"));
		actualOthersApplications.setPCompany("1005225");// consultsService.consultCompany("1005225"));
		actualOthersApplications.setPAccount("1005225");// consultsService.consultAccount("1005225"));
		actualOthersApplications.setPConvDate(null);
		actualOthersApplications.setPConvRate(null);

		actualOthersApplications.setPConvType(null);
		actualOthersApplications.setPCurr(Util
				.loadParametersValue("p.curr.dol"));

		if (parameters.getMovementType().getMvmId().equals(1L)
				&& asignacion.getAssignationsTypes().getAstCodigo().equals(1L)
				|| asignacion.getAssignationsTypes().getAstCodigo().equals(3L))
			actualOthersApplications.setPDescription(parameters
					.getAccDescripcion()
					+ " || ' Ano:'  ||  "
					+ asignacion.getVehicles().getVhcPlacaDiplomatica()
					+ ManipulacionFechas.getAgno(fechaActual)
					+ "|| 'Tipo Asignacion:'  ||"
					+ asignacion.getAssignationsTypes().getAstNombre()
					+ " || 'Periodo:' || "
					+ asignacion.getVhaFechaInicio()
					+ " || " + asignacion.getVhaFechaTermina());

		if (parameters.getMovementType().getMvmId().equals(2L))
			actualOthersApplications.setPDescription(parameters
					.getAccDescripcion()
					+ " || "
					+ actualOthersApplications.getPCategory()
					+ " || 'Ano:'" + ManipulacionFechas.getAgno(fechaActual));

		if (parameters.getMovementType().getMvmId().equals(2L)) {
			if (tarifaAsignacion > 0)
				actualOthersApplications.setPEntCr(-tarifaAsignacion
						.floatValue());
			else
				actualOthersApplications.setPEntCr(tarifaAsignacion
						.floatValue());
		} else
			actualOthersApplications.setPEntCr(null);

		if (parameters.getMovementType().getMvmId().equals(1L))
			actualOthersApplications.setPEntDr(tarifaAsignacion.floatValue());
		else
			actualOthersApplications.setPEntDr(null);

		// no veo este valor en la tabla accountig_parameters
		actualOthersApplications.setPNit(null);

		if (tipoMoneda != null && tipoMoneda.equals(1L))
			actualOthersApplications.setPSob(new Long(Util
					.loadParametersValue("p.sob.dolar")));

		if (tipoMoneda != null && tipoMoneda.equals(2L))
			actualOthersApplications.setPSob(new Long(Util
					.loadParametersValue("p.sob.pesos")));

		actualOthersApplications.setPSource(Util
				.loadParametersValue("p.source"));
		actualOthersApplications.setPUser(login);
		guardarComprobante(actualOthersApplications);

		return headerProof;
	}

	/**
	 * Guardar comprobante.
	 *
	 * @param actualOthersApplications the actual others applications
	 * @throws GWorkException the g work exception
	 */
	public void guardarComprobante(
			ActualOthersApplications actualOthersApplications)
			throws GWorkException {
		try {
			Util.validarSession();
			EntityManagerHelper.beginTransaction();
			new ActualOthersApplicationsDAO().save(actualOthersApplications);
			EntityManagerHelper.commit();

		} catch (Exception e) {
			EntityManagerHelper.rollback();
			// Util.limpiarSession();
			throw new GWorkException(e.getMessage());
		}
	}

	/**
	 * Guardar comprobante asignacion.
	 *
	 * @param aoaApp the aoa app
	 * @throws GWorkException the g work exception
	 */
	public void guardarComprobanteAsignacion(VhaAoaApp aoaApp)
			throws GWorkException {
		try {
			Util.validarSession();
			EntityManagerHelper.beginTransaction();
			new VhaAoaAppDAO().save(aoaApp);
			EntityManagerHelper.commit();

		} catch (Exception e) {
			EntityManagerHelper.rollback();
			// Util.limpiarSession();
			throw new GWorkException(e.getMessage());
		}
	}

	/**
	 * Generar parametrizacion contable.
	 *
	 * @param numeroIni the numero ini
	 * @param numeroFin the numero fin
	 * @param asignacion the asignacion
	 * @param login the login
	 * @param tipoMoneda the tipo moneda
	 * @param tipoAsignacion the tipo asignacion
	 * @param tarifaAsignacion the tarifa asignacion
	 * @param tipoMovimiento the tipo movimiento
	 * @param proofType the proof type
	 * @throws GWorkException the g work exception
	 */
	public void generarParametrizacionContable(int numeroIni, int numeroFin,
			VehiclesAssignation asignacion, String login, Long tipoMoneda,
			String tipoAsignacion, Float tarifaAsignacion,
			MovementType tipoMovimiento, ProofType proofType)
			throws GWorkException {

		for (int i = numeroIni; i < numeroFin; i++) {
			AccountingParameters parameters = consultarParametrizacionContable(new Long(
					i));
			generarEstructuraDebitoCredito(asignacion, login, tipoMoneda,
					tipoAsignacion, tarifaAsignacion,
					tipoMovimiento.getMvmId(), proofType.getPrtCodigo(),
					parameters);
		}
	}

	/**
	 * Metodo encargado de generar la transaccion de generacion de comprobante y
	 * cabecera de comprobante.
	 *
	 * @param asignacion the asignacion
	 * @param cantidadDiasAño the cantidad dias año
	 * @param tasaConversion the tasa conversion
	 * @param fechaFinalAsignacion the fecha final asignacion
	 * @param fechaEntrega the fecha entrega
	 * @param numeroDias the numero dias
	 * @param tipoMoneda the tipo moneda
	 * @param tarifaAsignacion the tarifa asignacion
	 * @param parameters the parameters
	 * @param tipoMovimiento the tipo movimiento
	 * @param proofType the proof type
	 * @param headerProof the header proof
	 * @param login the login
	 * @param vehicles the vehicles
	 * @param tipoAsignacion the tipo asignacion
	 * @throws GWorkException the g work exception
	 */
	public void generarTransacionComprobantes(VehiclesAssignation asignacion,
			Float cantidadDiasAño, Float tasaConversion,
			Date fechaFinalAsignacion, Date fechaEntrega, long numeroDias,
			Long tipoMoneda, Float tarifaAsignacion,
			AccountingParameters parameters, MovementType tipoMovimiento,
			ProofType proofType, HeaderProof headerProof, String login,
			Vehicles vehicles, String tipoAsignacion) throws GWorkException {

		FindParameterService findParameterService = new FindParameterServiceImpl();
		cantidadDiasAño = findParameterService.findParameterById(new Long(Util
				.loadMessageValue("ESTADO.6")));

		if (asignacion != null) {

			validarCasosGenerarComprobante(asignacion, tarifaAsignacion);
			if (asignacion != null
					&& asignacion.getAssignationsTypes().getAstCodigo().equals(
							new Long(Util.loadMessageValue("ESTADO.1"))))
				parameters = consultarParametrizacionContableAsignatario(
						asignacion, new Long(Util.loadMessageValue("ESTADO.1")));

			if (asignacion != null
					&& asignacion.getAssignationsTypes().getAstCodigo().equals(
							new Long(Util.loadMessageValue("ESTADO.3"))))
				parameters = consultarParametrizacionContable(new Long(Util
						.loadMessageValue("ESTADO.8")));

			// Genera la estructura debito
			tipoMovimiento = new MovementTypeDAO().findById(new Long(Util
					.loadMessageValue("ESTADO.1")));
			proofType = new ProofTypeDAO().findById(new Long(Util
					.loadMessageValue("ESTADO.1")));

			if (fechaEntrega.compareTo(fechaFinalAsignacion) > 0) {
				tarifaAsignacion *= -1;
			}
			// headerProof = generarEstructuraDebitoCredito(asignacion, login,
			// tipoMoneda, tipoAsignacion, tarifaAsignacion,
			// tipoMovimiento.getMvmId(), proofType.getPrtCodigo(),
			// parameters);

			if (tipoAsignacion.equalsIgnoreCase(Util
					.loadParametersValue("p.category.alq"))) {
				tipoMovimiento = new MovementTypeDAO().findById(new Long(Util
						.loadMessageValue("ESTADO.1")));
				generarParametrizacionContable(8, 9, asignacion, login,
						tipoMoneda, tipoAsignacion, tarifaAsignacion,
						tipoMovimiento, proofType);
			}

			if (tipoAsignacion.equalsIgnoreCase(Util
					.loadParametersValue("p.category.alq"))) {
				tipoMovimiento = new MovementTypeDAO().findById(new Long(Util
						.loadMessageValue("ESTADO.2")));
				generarParametrizacionContable(11, 12, asignacion, login,
						tipoMoneda, tipoAsignacion, tarifaAsignacion,
						tipoMovimiento, proofType);
			}

			// Genera la estructura credito
			// valida los tipos de ubicacion para hacer los registros
			// credito
			if (vehicles != null
					&& vehicles
							.getLocations()
							.getLocationsTypes()
							.getLctCodigo()
							.equals(new Long(Util.loadMessageValue("ESTADO.1")))) {

				tipoMovimiento = new MovementTypeDAO().findById(new Long(Util
						.loadMessageValue("ESTADO.2")));
				if (tipoAsignacion.equalsIgnoreCase(Util
						.loadParametersValue("p.category.asig"))) {
					generarParametrizacionContable(5, 8, asignacion, login,
							tipoMoneda, tipoAsignacion, tarifaAsignacion,
							tipoMovimiento, proofType);
				}
			}

			if (vehicles != null
					&& vehicles
							.getLocations()
							.getLocationsTypes()
							.getLctCodigo()
							.equals(new Long(Util.loadMessageValue("ESTADO.2")))) {

				tipoMovimiento = new MovementTypeDAO().findById(new Long(Util
						.loadMessageValue("ESTADO.2")));
				if (tipoAsignacion.equalsIgnoreCase(Util
						.loadParametersValue("p.category.asig"))) {
					generarParametrizacionContable(5, 7, asignacion, login,
							tipoMoneda, tipoAsignacion, tarifaAsignacion,
							tipoMovimiento, proofType);
				}
			}

			if (vehicles != null
					&& vehicles
							.getLocations()
							.getLocationsTypes()
							.getLctCodigo()
							.equals(new Long(Util.loadMessageValue("ESTADO.3")))) {

				tipoMovimiento = new MovementTypeDAO().findById(new Long(Util
						.loadMessageValue("ESTADO.2")));
				if (tipoAsignacion.equalsIgnoreCase(Util
						.loadParametersValue("p.category.asig"))) {
					generarParametrizacionContable(7, 8, asignacion, login,
							tipoMoneda, tipoAsignacion, tarifaAsignacion,
							tipoMovimiento, proofType);
				}
			}
		}
	}

	/* (non-Javadoc)
	 * @see geniar.siscar.logic.vehicle.services.AllocationReturnVehicleService#devolucionVehiculoAsignacion(java.lang.Long, java.lang.String, java.util.Date, java.lang.Long, java.lang.Long, java.lang.String, java.lang.String)
	 */
	public String devolucionVehiculoAsignacion(Long codigoAsignacion,
			String vhcPlacaDiplomatica, Date fechaEntrega, Long kilometraje,
			Long tipoAsignatarios, String observacionDev, String login)
			throws GWorkException {

		Connection connection = null;
		
		try {
			Long statesVehicles = new Long(Util.loadMessageValue("ESTADO.6"));
			Long statesAssignation = new Long(Util.loadMessageValue("ESTADO.1"));
			String tipoAsignacion = Util.loadParametersValue("p.category.asig");
			String errorNotificacion = "";
			VehicleService vehicleService = new VehicleServiceImpl();
			float cobroTarifaAsignacion = 0;
			float devolucionValorAsignacion = 0;
			
			connection = ConsultsServiceImpl.getConnection(Util.loadParametersValue("DATASOURCE.FINANCIERO"));
						
			HeaderProof headerProof = null;
			Period period = FlatFileFuelServiceImpl.consultarPeriodoByNovedad(
					fechaEntrega, ParametersUtil.NOVEDAD_ASIG);

			if (vhcPlacaDiplomatica != null
					&& vhcPlacaDiplomatica.trim().length() == 0)
				throw new GWorkException(Util.loadErrorMessageValue("PLACA"));

			if (fechaEntrega == null)
				throw new GWorkException(Util
						.loadErrorMessageValue("FECHA_ENTREGA"));

			if (kilometraje == null)
				throw new GWorkException(Util
						.loadErrorMessageValue("KILOMETRAJE "));

			if (tipoAsignatarios == null)
				throw new GWorkException(Util
						.loadErrorMessageValue("ASIGNATARIO"));

			List<Vehicles> ListVehicles = new VehiclesDAO()
					.findByVhcPlacaDiplomatica(vhcPlacaDiplomatica.trim()
							.toUpperCase());

			if (ListVehicles != null && ListVehicles.size() > 0) {
				Vehicles vehicles = (Vehicles) ListVehicles.get(0);
				VehiclesAssignation asignacion = new VehiclesAssignationDAO()
						.findById(codigoAsignacion);
				Requests request = asignacion.getRequests();
				VehiclesStates vehiclesStates = new VehiclesStatesDAO()
						.findById(statesVehicles);

				if (request != null) {
					tipoAsignacion = request.getLegateesTypes().getLgtNombre();
				}

				Date fechaFinalAsignacion = asignacion.getVhaFechaTermina();

				boolean seCobra = asignacion.getVhaCobro().equals("S");

				long codigoTipoLocalizacion = vehicles.getLocations()
						.getLocationsTypes().getLctCodigo().longValue();

				AssignationProofServiceImpl servicioComprobante = new AssignationProofServiceImpl();
				Period periodo = FlatFileFuelServiceImpl
						.consultarPeriodoByNovedad(fechaEntrega,
								ParametersUtil.NOVEDAD_ASIG);

				if (fechaEntrega.compareTo(fechaFinalAsignacion) != 0
						&& seCobra
						&& SearchParametersBilling.validarPeriodoAsignacion(
								period.getPerId(), asignacion.getVhaCodigo(),
								ParametersUtil.PROOF_TYPE_ASIGNACION) == true) {

					List<AccountingParameters> listAccountingParameters = null;
					List<AccountingParameters> listAccountingParametersCredito = null;
					TariffService tariffService = new TariffServiceImpl();
					float valorMesMantenimiento = 0F;
					float valorMesDepreciacion = 0F;
					float valorMesAutoSeguro = 0F;
					float valorMesEspacioFisico = 0F;
					
					BigDecimal valorCif = null;
					BigDecimal vidaUtil = null;
					float valorOutposted = 0F;
					float cantidadDiasMes = 30;
					long numeroDias = 0;
					
					float valorDiaAutoSeguro = 0F;
					float valorDiaEspacioFisico = 0F;
					float valorDiaMantenimiento = 0F;
					float valorDiaDepreciacion = 0F;
					
					float valorDiferencialAutoSeguro = 0;
					float valorDiferencialMantenimiento = 0;
					float valorDiferencialDepreciacion = 0;
					float valorDiferencialEspacioFisico = 0;
					float valorDiferencialTotal = 0;
					
					SearchAccountingParameters searchAccountingParameters = new SearchAccountingParameters();

					if (vehicles.getLocations().getLocationsTypes()
							.getLctCodigo().longValue() == ParametersUtil.EXTERIORES.longValue()) {

						valorCif = new ConsultsServiceImpl().consultFuntions(vehicles.getVhcPlacaActivoFijo(), 1);

						if (valorCif == null)
							throw new GWorkException(Util.loadErrorMessageValue("VALORCIF.EXISTEN")
											+ vehicles.getVhcPlacaDiplomatica());

						vidaUtil = new ConsultsServiceImpl().consultFuntions(vehicles.getVhcPlacaActivoFijo(), 4);

						if (vidaUtil == null)
							throw new GWorkException(Util.loadErrorMessageValue("VIDAUTIL.EXISTEN")
										+ vehicles.getVhcPlacaDiplomatica());

						valorOutposted = Util.redondearFloat(valorCif
								.floatValue()
								/ (vidaUtil.longValue() * 12), 2);

						valorDiaDepreciacion = Util.redondearFloat(
								(valorOutposted / cantidadDiasMes), 2);

					}else{
						listAccountingParameters = searchAccountingParameters
								.consultarParametrizacionContableActivos(
										asignacion.getRequests().getLegateesTypes().getLgtCodigo(), 
										ParametersUtil.COMPRANTE_ASIGNACION,
										ParametersUtil.CREDITO, 
										vehicles.getLocations().getLocationsTypes().getLctCodigo());
						
						if(listAccountingParameters != null && listAccountingParameters.size() > 0){
							for (AccountingParameters accountingParameters : listAccountingParameters) {
								if(accountingParameters.getChargeType().getCgtId().longValue() == 
									ParametersUtil.CARGO_AUTOSEGURO.longValue()){
									
									Tariffs tariffsAutoSeguro = tariffService.consultarTarifaAsignacion(
											vehicles.getLines().getLnsId().longValue(),
											vehicles.getFuelsTypes().getFutCodigo().longValue(),
											vehicles.getTractionsTypes().getTctCodigo().longValue(),
											ParametersUtil.TARIFA_AUTOSEGURO);
									
									valorMesAutoSeguro = tariffsAutoSeguro.getTrfValor();
									
								}else if(accountingParameters.getChargeType().getCgtId().longValue() == 
									ParametersUtil.CARGO_DEPRECIACION.longValue()){
									
									Tariffs tariffsDepreciacion = tariffService.consultarTarifaAsignacion(
											vehicles.getLines().getLnsId().longValue(),
											vehicles.getFuelsTypes().getFutCodigo().longValue(),
											vehicles.getTractionsTypes().getTctCodigo().longValue(),
											ParametersUtil.TARIFA_DEPRECIACION);
									
									valorMesDepreciacion = tariffsDepreciacion.getTrfValor();
									
								}else if(accountingParameters.getChargeType().getCgtId().longValue() == 
									ParametersUtil.CARGO_MANTENIMIENTO.longValue()){
									
									Tariffs tariffsMantenimiento = tariffService.consultarTarifaAsignacion(
											vehicles.getLines().getLnsId().longValue(),
											vehicles.getFuelsTypes().getFutCodigo().longValue(),
											vehicles.getTractionsTypes().getTctCodigo().longValue(),
											ParametersUtil.TARIFA_MANTENIMIENTO);
									
									valorMesMantenimiento = tariffsMantenimiento.getTrfValor();
									
								}else if(accountingParameters.getChargeType().getCgtId().longValue() == 
									ParametersUtil.CARGO_ESPACIO_FISICO.longValue()){
									
									Tariffs tariffsEspacioFisco = tariffService.consultarTarifaAsignacion(
											vehicles.getLines().getLnsId().longValue(),
											vehicles.getFuelsTypes().getFutCodigo().longValue(),
											vehicles.getTractionsTypes().getTctCodigo().longValue(),
											ParametersUtil.TARIFA_ASIGNACION_ESPACIO_FISICO);
									
									valorMesEspacioFisico = tariffsEspacioFisco.getTrfValor();
								}
							}
						}
						
						valorDiaAutoSeguro = Util.redondearFloat((valorMesAutoSeguro
								/ cantidadDiasMes),2);
						
						valorDiaMantenimiento = Util.redondearFloat((valorMesMantenimiento
								/ cantidadDiasMes),2);
						
						valorDiaDepreciacion = Util.redondearFloat((valorMesDepreciacion
								/ cantidadDiasMes),2);

						valorDiaEspacioFisico = Util.redondearFloat((valorMesEspacioFisico
						/ cantidadDiasMes),2);
					}

					if (fechaEntrega.compareTo(fechaFinalAsignacion) < 0) {

						if (fechaFinalAsignacion.compareTo(periodo
								.getPerFechaFin()) > 0) {
							numeroDias = Util.compararNumeroDiasFechas(
									fechaEntrega, periodo.getPerFechaFin());
						}

						if (fechaFinalAsignacion.compareTo(periodo
								.getPerFechaFin()) < 0) {
							numeroDias = Util.compararNumeroDiasFechas(
									fechaEntrega, fechaFinalAsignacion);
						}

						valorDiferencialAutoSeguro = -1 *  Util.redondearFloat((numeroDias
								* valorDiaAutoSeguro),2);
						valorDiferencialMantenimiento = -1 * Util.redondearFloat((numeroDias
								* valorDiaMantenimiento),2);
						valorDiferencialDepreciacion = -1 * Util.redondearFloat((numeroDias
								* valorDiaDepreciacion),2);
						valorDiferencialEspacioFisico= -1 * Util.redondearFloat((numeroDias
								* valorDiaEspacioFisico),2);

						valorDiferencialTotal = valorDiferencialAutoSeguro
								+ valorDiferencialDepreciacion
								+ valorDiferencialMantenimiento
								+ valorDiferencialEspacioFisico;
						
						devolucionValorAsignacion = valorDiferencialTotal;
					}

					if (fechaEntrega.compareTo(fechaFinalAsignacion) > 0) {

						numeroDias = Util.compararNumeroDiasFechas(
								fechaEntrega, fechaFinalAsignacion);
						valorDiferencialAutoSeguro = Util.redondearFloat((numeroDias
								* valorDiaAutoSeguro),2);
						valorDiferencialMantenimiento = Util.redondearFloat((numeroDias
								* valorDiaMantenimiento),2);
						valorDiferencialDepreciacion = Util.redondearFloat((numeroDias
								* valorDiaDepreciacion),2);
						valorDiferencialEspacioFisico = Util.redondearFloat((numeroDias
						* valorDiaEspacioFisico),2);

						valorDiferencialTotal = valorDiferencialAutoSeguro
								+ valorDiferencialDepreciacion
								+ valorDiferencialMantenimiento
								+ valorDiferencialEspacioFisico;

						cobroTarifaAsignacion = valorDiferencialTotal;
					}

					float cargoCenco = 0;
					headerProof = new PrepaidProofBoughtServiceImpl()
							.generarCabeceraComprobante(
									ParametersUtil.COMPRANTE_ALQUILER, period,
									ParametersUtil.TRASACCTION_TYPE_RENT,
									ParametersUtil.DOLAR);

					List<CostsCentersVehicles> listCostsCentersVehicles = SearchCostCenters
							.consultarCCVehiculo(vhcPlacaDiplomatica.trim()
									.toUpperCase());

					if (listCostsCentersVehicles != null
							&& listCostsCentersVehicles.size() > 0) {
						
						float totalTemp = valorDiferencialTotal;
						
						int cantCencos = listCostsCentersVehicles.size();
						float total = 0; 
						float diferenciaTotal = 0;
						
						String idMaster = new ConsultsServiceImpl().getIdMaster();
						Long idDetail = Long.valueOf(listCostsCentersVehicles.size());
						listAccountingParametersCredito = searchAccountingParameters.consultarParametrizacionContableActivos(
								asignacion.getRequests().getLegateesTypes().getLgtCodigo(),
								ParametersUtil.COMPRANTE_ASIGNACION,
								ParametersUtil.CREDITO,
								codigoTipoLocalizacion);
						
						if(listAccountingParametersCredito != null && listAccountingParametersCredito.size() > 0){

							for (AccountingParameters parameters : listAccountingParametersCredito) {
								if (parameters.getChargeType().getCgtId().longValue() == ParametersUtil.CARGO_MANTENIMIENTO.longValue()) {
									idDetail++;
								} else if (parameters.getChargeType().getCgtId().longValue() == ParametersUtil.CARGO_DEPRECIACION.longValue()) {
									idDetail++;
								} else if (parameters.getChargeType().getCgtId().longValue() == ParametersUtil.CARGO_AUTOSEGURO.longValue()) {
									idDetail++;
								} else if (parameters.getChargeType().getCgtId().longValue() == ParametersUtil.CARGO_ESPACIO_FISICO.longValue()) {
									idDetail++;
								}
							}
						}

						for (CostsCentersVehicles costsCentersVehicles : listCostsCentersVehicles) {
							float porcentaje = Float.parseFloat(costsCentersVehicles
											.getCcrPorcentaje());
							
							float cargoMantenimiento = Util.redondearFloat(((valorDiferencialMantenimiento * porcentaje) / 100),2);
							float cargoAutoSeguro = Util.redondearFloat(((valorDiferencialAutoSeguro * porcentaje) / 100),2);
							float cargoEspacioFisico = Util.redondearFloat(((valorDiferencialEspacioFisico * porcentaje) / 100),2);
							float cargoDepreciacion = Util.redondearFloat(((valorDiferencialDepreciacion * porcentaje) / 100),2);
							
							valorDiferencialTotal = cargoMantenimiento
									+ cargoAutoSeguro
									+ cargoEspacioFisico
									+ cargoDepreciacion;
							
							total = total + valorDiferencialTotal;
							
							if(listCostsCentersVehicles.size() > 1 && (cantCencos - 1) == 0){
								if(total < totalTemp){
									diferenciaTotal = totalTemp - total;
								}else if(total > totalTemp){
									diferenciaTotal = totalTemp - total;
								}
								
								valorDiferencialTotal = valorDiferencialTotal + Util.redondearFloat(diferenciaTotal,2);
							}
							cantCencos = cantCencos - 1;
							cargoCenco = Util.redondearFloat(valorDiferencialTotal,2);
							
							listAccountingParameters = searchAccountingParameters
									.consultarParametrizacionContableActivos(
											asignacion.getRequests()
													.getLegateesTypes()
													.getLgtCodigo(),
											ParametersUtil.COMPRANTE_ASIGNACION,
											ParametersUtil.DEBITO,
											codigoTipoLocalizacion);
					
							if(listAccountingParameters != null && listAccountingParameters.size() > 0){
								for (AccountingParameters accountingParameters : listAccountingParameters) {

									connection = servicioComprobante
											.generarComprobanteDevolucionAsignacionDebito(
													connection,
													accountingParameters,
													ParametersUtil.COMPRANTE_ASIGNACION,
													login, fechaEntrega,
													asignacion, cargoCenco,
													costsCentersVehicles.getCostsCenters().getCocNumero(),
													headerProof,idMaster,idDetail);
								}
							}
						}

						if(listAccountingParametersCredito != null && listAccountingParametersCredito.size() > 0){

							for (AccountingParameters accountingParameters : listAccountingParametersCredito) {
								if (accountingParameters.getChargeType().getCgtId()
										.longValue() == ParametersUtil.CARGO_MANTENIMIENTO.longValue()) {
									
									connection = servicioComprobante
											.generarComprobanteDevolucionAsignacionCredito(
													connection,
													accountingParameters,
													ParametersUtil.COMPRANTE_ASIGNACION,
													login,
													fechaEntrega,
													asignacion,
													valorDiferencialMantenimiento,
													"", headerProof,idMaster,idDetail);
									
								} else if (accountingParameters.getChargeType().getCgtId()
										.longValue() == ParametersUtil.CARGO_DEPRECIACION.longValue()) {
								
									connection = servicioComprobante
											.generarComprobanteDevolucionAsignacionCredito(
													connection,
													accountingParameters,
													ParametersUtil.COMPRANTE_ASIGNACION,
													login,
													fechaEntrega,
													asignacion,
													valorDiferencialDepreciacion,
													"", headerProof,idMaster,idDetail);
									
								} else if (accountingParameters.getChargeType().getCgtId()
										.longValue() == ParametersUtil.CARGO_AUTOSEGURO.longValue()) {
									
									connection = servicioComprobante
											.generarComprobanteDevolucionAsignacionCredito(
													connection,
													accountingParameters,
													ParametersUtil.COMPRANTE_ASIGNACION,
													login,
													fechaEntrega, asignacion,
													valorDiferencialAutoSeguro,
													"", headerProof,idMaster,idDetail);

								} else if (accountingParameters.getChargeType().getCgtId()
										.longValue() == ParametersUtil.CARGO_ESPACIO_FISICO.longValue()) {

									connection = servicioComprobante
											.generarComprobanteDevolucionAsignacionCredito(
													connection,
													accountingParameters,
													ParametersUtil.COMPRANTE_ASIGNACION,
													login,
													fechaEntrega,
													asignacion,
													valorDiferencialEspacioFisico,
													"", headerProof,idMaster,idDetail);

								}
							}
						}
					}
				}

				long codigoAsignatario = asignacion.getRequests()
						.getLegateesTypes().getLgtCodigo().longValue();

				try {
					if (codigoAsignatario == ParametersUtil.LGT_OF
							|| codigoAsignatario == ParametersUtil.LGT_OFS) {
						envioNotificacionAutoSeguro(asignacion, vehicles,
								fechaEntrega);
					}
				} catch (RuntimeException e) {
					errorNotificacion = " - " + e.getMessage()
							+ " al area de RECURSOS HUMANOS";
				}
				AssignationsStates assignationsStates = new AssignationsStatesDAO()
						.findById(statesAssignation);

				if (assignationsStates != null) {
					asignacion.setAssignationsStates(assignationsStates);
					asignacion.setVhaFechaDev(fechaEntrega);
					asignacion.setVhaKilomDev(kilometraje);
					asignacion.setVhaObservacionDev(observacionDev);
					actualizarEstadoAsignacion(asignacion);
				}

				crearDevolucionVehiculo(vhcPlacaDiplomatica, fechaEntrega,
						kilometraje, statesVehicles, tipoAsignacion, vehicles,
						cobroTarifaAsignacion, devolucionValorAsignacion,
						connection);
				
				if (vehiclesStates != null) {
					vehicles.setVehiclesStates(vehiclesStates);
					vehicles.setVhcKilometrajeActual(kilometraje);
					vehicleService.actualizarVehiculo(vehicles);
				}
			}
			return errorNotificacion;
		} catch (Exception e) {
			log.error("Error: " + e.getMessage(), e);
			limpiarSession();
			validarSession();
			throw new GWorkException(e.getMessage());
		}finally{
			try{
				if (connection!=null && !connection.isClosed()){
					connection.close();
				}
			}catch(Exception ex2){
				log.error("Error: " + ex2.getMessage(), ex2);
				throw new GWorkException(ex2.getMessage(), ex2);
			}
		}
	}

	/**
	 * Envio notificacion auto seguro.
	 *
	 * @param asignacion the asignacion
	 * @param vehicles the vehicles
	 * @param fechaEntrega the fecha entrega
	 * @throws GWorkException the g work exception
	 */
	public void envioNotificacionAutoSeguro(VehiclesAssignation asignacion,
			Vehicles vehicles, Date fechaEntrega) throws GWorkException {

		float valorAutoSeguroAnual = SearchVehicles
				.ConsultarParametroAutoSeguro(new Long(Util
						.loadParametersValue("BAC_ID_PARAM_AUTOSEG")));

		/*
		 * FlatFileDAO flatFileDAO = new FlatFileDAO(); List<FlatFile> files =
		 * flatFileDAO.findByProperty( "tariff.trfId",
		 * tariffsAutoSeguro.getTrfId()); float test = 0; for (FlatFile file :
		 * files) { test = Float.parseFloat(file.getFfValor()); }
		 */
		Calendar finDeAno = Calendar.getInstance();
		finDeAno.set(Calendar.MONTH, Calendar.DECEMBER);
		finDeAno.set(Calendar.DAY_OF_MONTH, 31);
		SendEmail sendMail = null;

		long numeroDias = Util.compararNumeroDiasFechas(fechaEntrega, finDeAno
				.getTime());
		float valor = (valorAutoSeguroAnual / 225) * numeroDias;

		String placa = vehicles.getVhcPlacaDiplomatica();

		// Rolls rol = new RollsDAO()
		// .findById(ParametersUtil.ROL_RECURSOS_HUMANOS);

		String nombre = asignacion.getRequests().getUsersByRqsUser()
				.getUsrNombre();
		String apellido = asignacion.getRequests().getUsersByRqsUser()
				.getUsrApellido();

		String tipoAsignatario = asignacion.getRequests().getLegateesTypes()
				.getLgtNombre();

		if (apellido != null) {
			nombre += " " + apellido;
		}

		String sujeto = "Notificacion Devolucion AutoSeguro - Vehiculo "
				+ placa + " : ";

		valor = Util.redondear(valor, 2).floatValue();
		String textmessage = "Cordial saludo, se le informa que el vehiculo "
				+ placa
				+ " ha sido devuelto al pool."
				+ "<br/><br/> <b>Nombre Asignatario: </b>"
				+ nombre
				+ "<br/><b>Carnet Asignatario: </b>"
				+ asignacion.getVhaNumeroCarne()
				+ "<br/><b>Tipo Asignatario: </b>"
				+ tipoAsignatario
				+ "<br/><br/>Por favor devolver el valor proporcional al cobro anual del Autoseguro."
				+ "<br/><br/>Valor: "
				+ valor
				+ "<br/> <H2> <b> Nota: Si es una devolución definitiva del vehículo, es decir no se va a entregar otro vehículo a este mismo asignatario, "
				+ "por favor reenviar este mensaje al encargado de Recursos Humanos, para que le sea registrada la devolución de este valor en la próxima nomina.  "
				+ "Por lo contrario, si a este asignatario se le va a cambiar por otro vehículo, hacer caso omiso a este mensaje.  </b> </H2><br/>";

		// String email = rol.getRlsMail();
		Rolls rol = new RollsDAO().findById(new Long(Util
				.loadParametersValue("MAIL.ADMINISTRADOR")));
		String server = Util.loadParametersValue("MAIL.HOST");
		String fromUser = rol.getRlsMail();

		// envio de correo con copia
		if (valor > 0f)
			sendMail = new SendEmail(fromUser, fromUser, server, "false",
					sujeto, textmessage);

		Log log = LogFactory.getLog(SendEmail.class);
		if (sendMail != null && sendMail.SendMessage().equals("SUCCESS"))
			log.info("Mensaje enviado exitosamente");
		else
			log.info("Error Enviando el mensaje");
	}

	/**
	 * Validar session.
	 */
	public void validarSession() {
		if (EntityManagerHelper.getEntityManager().isOpen()) {
			EntityManagerHelper.getEntityManager().close();
		}
	}

	/**
	 * Limpiar session.
	 */
	public void limpiarSession() {
		EntityManagerHelper.getEntityManager().clear();
	}

	/**
	 * Actualizar estado asignacion.
	 *
	 * @param vehiclesAssignation the vehicles assignation
	 * @throws GWorkException the g work exception
	 */
	public void actualizarEstadoAsignacion(
			VehiclesAssignation vehiclesAssignation) throws GWorkException {
		try {

			new VehiclesAssignationDAO().update(vehiclesAssignation);

		} catch (Exception e) {
			throw new GWorkException(Util.loadErrorMessageValue(e.getMessage()));
		}
	}

	/* (non-Javadoc)
	 * @see geniar.siscar.logic.vehicle.services.AllocationReturnVehicleService#devolucionVehiculoAlquiler(java.lang.Long, java.lang.String, java.util.Date, java.lang.Long, java.lang.String, java.lang.String)
	 */
	public void devolucionVehiculoAlquiler(Long codigoAsignacion,
			String vhcPlacaDiplomatica, Date fechaEntrega, Long kilometraje,
			String observacionDev, String login) throws GWorkException {

		
		Connection connection = null;
		
		try {
			Long statesVehicles = Long.valueOf(Util.loadMessageValue("ESTADO.6"));
			Long statesAssignation = Long.valueOf(Util.loadMessageValue("ESTADO.1"));
			Vehicles vehicles = null;
			String tipoAsignacion = null;
			VehiclesAssignation asignacion = null;
			AssignationsStates assignationsStates = null;
			Float cobroTarifaAsignacion = null;
			Float devolucionValorAsignacion = null;
			VehiclesStates vehiclesStates = null;
			
			List<Tariffs> tariffsEspacioFisico = null;
			List<Tariffs> tariffsDepreciacion = null;
			List<Tariffs> tariffsMantenimiento = null;
			List<Tariffs> tariffsAutoSeguro = null;
			List<Tariffs> tariffsAlquiler = null;
			String nombreTipoVehiculo = "";
			Long tipoVehiculo = null;
			Long diasDiferenciaEntrega = 0L;
			Float kmIncluidoAlquiler = 0F;
			Long diasAdicional = null;
			List<CostsCentersVehicles> listCostsCentersVehicles = null;
			String msgKMAdicional = "";
			Float kmDiferencia = null;
			Float valor = null;
			Float valorEspacioFisico = null;
			Float valorDepreciacion = null;
			Float valorMantenimiento = null;
			Float valorAutoSeguro = null;
			
			Float valorDiasAdicional = null;
//			Float valorDiasAdicionalEspacioFisico = null;
//			Float valorDiasAdicionalDepreciacion = null;
//			Float valorDiasAdicionalAutoSeguro = null;
//			Float valorDiasAdicionalMantenimiento = null;
			Float valorKmAdicional = null;
			Float totalValorKMAdicional = null;
//			Float valorKmAdicionalEspacioFisico = null;
//			Float valorKmAdicionalDepreciacion = null;
//			Float valorKmAdicionalAutoSeguro = null;
//			Float valorKmAdicionalMantenimiento = null;
			
			Float valorDevolucion = null;
			Float valorDevolucionAutoSeguro = null;
			Float valorDevolucionDepreciacion = null;
			Float valorDevolucionEspacioFisico = null;
			Float valorDevolucionMantenimiento = null;
			
			connection = ConsultsServiceImpl.getConnection(Util.loadParametersValue("DATASOURCE.FINANCIERO").trim());

			SearchAccountingParameters searchAccountingParameters = new SearchAccountingParameters();
			RentProofService rentProofService = new RentProofServiceImpl();
			List<AccountingParameters> listAccountingParameters = new ArrayList<AccountingParameters>();
			
			if (statesVehicles != null)
				vehiclesStates = new VehiclesStatesDAO()
						.findById(statesVehicles);

			if (statesAssignation != null)
				assignationsStates = new AssignationsStatesDAO()
						.findById(statesAssignation);

			String estadoVehiculo = vehiclesStates.getVhsNombre();

			if (!estadoVehiculo.equalsIgnoreCase(Util
					.loadMessageValue("ESTADO_PRESTAMO"))) {

				List<Vehicles> ListVehicles = new VehiclesDAO()
						.findByVhcPlacaDiplomatica(vhcPlacaDiplomatica.trim()
								.toUpperCase());

				if (ListVehicles.size() > 0 && ListVehicles.get(0) != null) {
					vehicles = (Vehicles) ListVehicles.get(0);
					asignacion = new VehiclesAssignationDAO()
							.findById(codigoAsignacion);

					if (asignacion != null
							&& asignacion.getVhaCobro().equalsIgnoreCase(
									Util.loadMessageValue("ESTADO_COBRO_SI"))) {

						ConsultTariff consultTariff = new ConsultTariff();
						
						// Consultar el tipo de vehiculo asociado a el alquiler
						nombreTipoVehiculo = asignacion.getVehicles()
								.getVehiclesTypes().getVhtNombre();
						tipoVehiculo = asignacion.getVehicles()
								.getVehiclesTypes().getVhtCodigo();

						// Calcular la tarifa de alquier
						tariffsEspacioFisico = consultTariff.consultarTarifaActualAlquilerPorTipoTarifaYTipoVehiculo(
								tipoVehiculo, ParametersUtil.TARIFA_ALQUILER_ESPACIO_FISICO);
						if (tariffsEspacioFisico == null || tariffsEspacioFisico.size() == 0
								|| tariffsEspacioFisico.isEmpty()){
							throw new GWorkException(
								Util.loadErrorMessageValue("TARIFADISPONIBILIDAD")
										+ ":" + nombreTipoVehiculo);
						}
						
						tariffsDepreciacion = consultTariff.consultarTarifaActualAlquilerPorTipoTarifaYTipoVehiculo(
								tipoVehiculo, ParametersUtil.TARIFA_ALQUILER_DEPRECIACION);
						if (tariffsDepreciacion == null || tariffsDepreciacion.size() == 0
								|| tariffsDepreciacion.isEmpty()){
							throw new GWorkException(
								Util.loadErrorMessageValue("TARIFADISPONIBILIDAD")
										+ ":" + nombreTipoVehiculo);
						}
						
						tariffsAutoSeguro = consultTariff.consultarTarifaActualAlquilerPorTipoTarifaYTipoVehiculo(
								tipoVehiculo, ParametersUtil.TARIFA_ALQUILER_AUTOSEGURO);
						if (tariffsAutoSeguro == null || tariffsAutoSeguro.size() == 0
								|| tariffsAutoSeguro.isEmpty()){
							throw new GWorkException(
								Util.loadErrorMessageValue("TARIFADISPONIBILIDAD")
										+ ":" + nombreTipoVehiculo);
						}
						
						tariffsMantenimiento = consultTariff.consultarTarifaActualAlquilerPorTipoTarifaYTipoVehiculo(
								tipoVehiculo, ParametersUtil.TARIFA_ALQUILER_MANTENIMIENTO);
						if (tariffsMantenimiento == null || tariffsMantenimiento.size() == 0
								|| tariffsMantenimiento.isEmpty()){
							throw new GWorkException(
								Util.loadErrorMessageValue("TARIFADISPONIBILIDAD")
										+ ":" + nombreTipoVehiculo);
						}
						
						tariffsAlquiler = consultTariff.consultarTarifaActualAlquilerPorTipoTarifaYTipoVehiculo(
								tipoVehiculo, ParametersUtil.TARIFA_ALQUILER);
						if (tariffsAlquiler == null || tariffsAlquiler.size() == 0
								|| tariffsAlquiler.isEmpty()){
							throw new GWorkException(
								Util.loadErrorMessageValue("TARIFADISPONIBILIDAD")
										+ ":" + nombreTipoVehiculo);
						}
						
						diasDiferenciaEntrega = (Util.compararNumeroDiasFechas(
								asignacion.getVhaFechaEntrega(), fechaEntrega)) + 1L;

						kmIncluidoAlquiler = calcularKMincluido(
								tariffsAlquiler.get(0), diasDiferenciaEntrega);

						Float diferenciaKMEntrega = kilometraje.floatValue()
								- asignacion.getVhaKilomeActual().floatValue();

						if (fechaEntrega.compareTo(asignacion.getVhaFechaTermina()) == 0) {

							if(diferenciaKMEntrega <= kmIncluidoAlquiler){
								
								diasAdicional = Util.compararNumeroDiasFechas(
										fechaEntrega, asignacion
												.getVhaFechaTermina());

								if(diasAdicional == 0){
									
									valorAutoSeguro = Util.redondearFloat(
											tariffsAutoSeguro.get(0)
													.getTrfValor(), 2);
									valorDepreciacion = Util.redondearFloat(
											tariffsDepreciacion.get(0)
													.getTrfValor(), 2);
									valorEspacioFisico = Util.redondearFloat(
											tariffsEspacioFisico.get(0)
													.getTrfValor(), 2);
									valorMantenimiento = Util.redondearFloat(
											tariffsMantenimiento.get(0)
													.getTrfValor(), 2);
								}else{
								
									valorAutoSeguro = Util.redondearFloat(calcularValor(tariffsAutoSeguro.get(0),
											kmIncluidoAlquiler, diferenciaKMEntrega
													.floatValue(), diasAdicional),2);
									
									valorDepreciacion = Util.redondearFloat(calcularValor(tariffsDepreciacion.get(0),
											kmIncluidoAlquiler, diferenciaKMEntrega
													.floatValue(), diasAdicional),2);
	
									valorEspacioFisico = Util.redondearFloat(calcularValor(tariffsEspacioFisico.get(0),
											kmIncluidoAlquiler, diferenciaKMEntrega
													.floatValue(), diasAdicional),2);
	
									valorMantenimiento = Util.redondearFloat(calcularValor(tariffsMantenimiento.get(0),
											kmIncluidoAlquiler, diferenciaKMEntrega
													.floatValue(), diasAdicional),2);
								}
								
								valor = valorAutoSeguro + valorDepreciacion
										+ valorEspacioFisico
										+ valorMantenimiento;
						
								// adicionado para que actualice los
								// datos cuando hay dias adicionales
								if (asignacion.getVhaDiasAlquiler() != null
										&& asignacion.getVhaDiasAlquiler() > 0) {
									asignacion.setVhaDiasAlquiler(asignacion
											.getVhaDiasAlquiler()
											+ diasAdicional);
								}

							}else if(diferenciaKMEntrega > kmIncluidoAlquiler){
								
								valorAutoSeguro = Util.redondearFloat(calcularValor(tariffsAutoSeguro.get(0),
										kmIncluidoAlquiler, diferenciaKMEntrega
												.floatValue(), 0L),2);
								
								valorDepreciacion = Util.redondearFloat(calcularValor(tariffsDepreciacion.get(0),
										kmIncluidoAlquiler, diferenciaKMEntrega
												.floatValue(), 0L),2);
	
								valorEspacioFisico = Util.redondearFloat(calcularValor(tariffsEspacioFisico.get(0),
										kmIncluidoAlquiler, diferenciaKMEntrega
												.floatValue(), 0L),2);
	
								valorMantenimiento = Util.redondearFloat(calcularValor(tariffsMantenimiento.get(0),
										kmIncluidoAlquiler, diferenciaKMEntrega
												.floatValue(), 0L),2);
	
	//							valor = calcularValor(tariffsAlquiler.get(0),
	//									kmIncluidoAlquiler, diferenciaKMEntrega
	//											.floatValue(), 0L);							
	
								valor = valorAutoSeguro + valorDepreciacion
										+ valorEspacioFisico + valorMantenimiento; 
								
								kmDiferencia = diferenciaKMEntrega
										- kmIncluidoAlquiler;
								msgKMAdicional = "KM add: " + kmDiferencia;
							
							}
							
						} else if (ManipulacionFechas.compararSiFechaMenor(
								asignacion.getVhaFechaTermina(), fechaEntrega)
								&& diferenciaKMEntrega.floatValue() <= kmIncluidoAlquiler
										.floatValue()) {

							diasAdicional = Util.compararNumeroDiasFechas(
									fechaEntrega, asignacion
											.getVhaFechaTermina());

							valorAutoSeguro = Util.redondearFloat(calcularValor(tariffsAutoSeguro.get(0),
									kmIncluidoAlquiler, diferenciaKMEntrega
											.floatValue(), diasAdicional),2);
							
							valorDepreciacion = Util.redondearFloat(calcularValor(tariffsDepreciacion.get(0),
									kmIncluidoAlquiler, diferenciaKMEntrega
											.floatValue(), diasAdicional),2);

							valorEspacioFisico = Util.redondearFloat(calcularValor(tariffsEspacioFisico.get(0),
									kmIncluidoAlquiler, diferenciaKMEntrega
											.floatValue(), diasAdicional),2);

							valorMantenimiento = Util.redondearFloat(calcularValor(tariffsMantenimiento.get(0),
									kmIncluidoAlquiler, diferenciaKMEntrega
											.floatValue(), diasAdicional),2);

//							valor = calcularValor(tariffsAlquiler.get(0),
//									diferenciaKMEntrega, diferenciaKMEntrega
//											.floatValue(), diasAdicional);

							valor = valorAutoSeguro + valorDepreciacion
									+ valorEspacioFisico + valorMantenimiento;
							
							// adicionado para que actualice los
							// datos cuando hay dias adicionales
							if (asignacion.getVhaDiasAlquiler() != null
									&& asignacion.getVhaDiasAlquiler() > 0) {
								asignacion.setVhaDiasAlquiler(asignacion
										.getVhaDiasAlquiler()
										+ diasAdicional);
							}

							if (asignacion.getVhaValorAlquiler() != null
									&& asignacion.getVhaValorAlquiler() > 0) {
								asignacion.setVhaValorAlquiler(asignacion.getVhaValorAlquiler() + valor);
							}

						} else if (ManipulacionFechas.compararSiFechaMenor(
								asignacion.getVhaFechaTermina(), fechaEntrega)
								&& diferenciaKMEntrega > kmIncluidoAlquiler) {

							diasAdicional = Util.compararNumeroDiasFechas(
									fechaEntrega, asignacion
											.getVhaFechaTermina());

							float tempValorKmEntrega = 0;

//							valorDiasAdicionalAutoSeguro = calcularValor(
//									tariffsAutoSeguro.get(0),
//									tempValorKmEntrega, tempValorKmEntrega,
//									diasAdicional);
//
//							valorDiasAdicionalDepreciacion = calcularValor(
//									tariffsDepreciacion.get(0),
//									tempValorKmEntrega, tempValorKmEntrega,
//									diasAdicional);
//
//							valorDiasAdicionalEspacioFisico = calcularValor(
//									tariffsEspacioFisico.get(0),
//									tempValorKmEntrega, tempValorKmEntrega,
//									diasAdicional);
//
//							valorDiasAdicionalMantenimiento = calcularValor(
//									tariffsMantenimiento.get(0),
//									tempValorKmEntrega, tempValorKmEntrega,
//									diasAdicional);
							
							valorDiasAdicional = calcularValor(tariffsAlquiler.get(0),
									tempValorKmEntrega, tempValorKmEntrega,
									diasAdicional);

//							valorKmAdicionalAutoSeguro = calcularValor(tariffsAutoSeguro.get(0),
//									kmIncluidoAlquiler, diferenciaKMEntrega
//											.floatValue(), 0L);
//							
//							valorKmAdicionalDepreciacion = calcularValor(tariffsDepreciacion.get(0),
//									kmIncluidoAlquiler, diferenciaKMEntrega
//											.floatValue(), 0L);
//							
//							valorKmAdicionalEspacioFisico = calcularValor(tariffsEspacioFisico.get(0),
//									kmIncluidoAlquiler, diferenciaKMEntrega
//											.floatValue(), 0L);
//							
//							valorKmAdicionalMantenimiento = calcularValor(tariffsMantenimiento.get(0),
//									kmIncluidoAlquiler, diferenciaKMEntrega
//											.floatValue(), 0L);
							
							valorKmAdicional = calcularValor(tariffsAlquiler.get(0),
									kmIncluidoAlquiler, diferenciaKMEntrega
											.floatValue(), 0L);

							valorAutoSeguro = Util.redondearFloat(calcularValor(tariffsAutoSeguro.get(0),
									kmIncluidoAlquiler, diferenciaKMEntrega
											.floatValue(), diasAdicional),2);
							
							valorDepreciacion = Util.redondearFloat(calcularValor(tariffsDepreciacion.get(0),
									kmIncluidoAlquiler, diferenciaKMEntrega
											.floatValue(), diasAdicional),2);

							valorEspacioFisico = Util.redondearFloat(calcularValor(tariffsEspacioFisico.get(0),
									kmIncluidoAlquiler, diferenciaKMEntrega
											.floatValue(), diasAdicional),2);

							valorMantenimiento = Util.redondearFloat(calcularValor(tariffsMantenimiento.get(0),
									kmIncluidoAlquiler, diferenciaKMEntrega
											.floatValue(), diasAdicional),2);
							
//							valor = calcularValor(tariffsAlquiler.get(0),
//									kmIncluidoAlquiler, diferenciaKMEntrega
//											.floatValue(), diasAdicional);

							valor = valorAutoSeguro + valorDepreciacion
									+ valorEspacioFisico + valorMantenimiento;

							
							// adicionado para que actualice los
							// datos cuando hay dias adicionales y km adicional
							if (asignacion.getVhaDiasAlquiler() != null
									&& asignacion.getVhaDiasAlquiler() > 0) {
								asignacion.setVhaDiasAlquiler(asignacion
										.getVhaDiasAlquiler()
										+ diasAdicional);
							}

							if (asignacion.getVhaValorAlquiler() != null
									&& asignacion.getVhaValorAlquiler() > 0) {
								
								asignacion.setVhaValorAlquiler(asignacion
										.getVhaValorAlquiler()
										+ valorDiasAdicional);
							}

							kmDiferencia = diferenciaKMEntrega
									- kmIncluidoAlquiler;
							msgKMAdicional = "KM add: " + kmDiferencia;
							
						} else if (ManipulacionFechas.compararSiFechaMenor(
								fechaEntrega, asignacion.getVhaFechaTermina())) {

							diasDiferenciaEntrega = Util.compararNumeroDiasFechas(asignacion.getVhaFechaTermina(), fechaEntrega);

							valorDevolucionAutoSeguro = Util.redondearFloat((diasDiferenciaEntrega * tariffsAutoSeguro.get(0).getTrfValor()),2) * -1L;
							valorDevolucionDepreciacion = Util.redondearFloat((diasDiferenciaEntrega * tariffsDepreciacion.get(0).getTrfValor()),2) * -1L;
							valorDevolucionEspacioFisico = Util.redondearFloat((diasDiferenciaEntrega * tariffsEspacioFisico.get(0).getTrfValor()),2) * -1L;
							valorDevolucionMantenimiento = Util.redondearFloat((diasDiferenciaEntrega * tariffsMantenimiento.get(0).getTrfValor()),2) * -1L;
							
//							valorDevolucion = (diasDiferenciaEntrega * tariffsAlquiler.get(0).getTrfValor()) * -1L;
							valorDevolucion = valorDevolucionAutoSeguro
									+ valorDevolucionDepreciacion
									+ valorDevolucionEspacioFisico
									+ valorDevolucionMantenimiento;
							
							if (diferenciaKMEntrega > kmIncluidoAlquiler) {
								
								totalValorKMAdicional = calcularValor(tariffsAlquiler.get(0),
										kmIncluidoAlquiler, diferenciaKMEntrega.floatValue(), 0L);
								
								valorAutoSeguro = Util.redondearFloat((calcularValor(tariffsAutoSeguro.get(0),
										kmIncluidoAlquiler,diferenciaKMEntrega.floatValue(), 0L)
										+ valorDevolucionAutoSeguro),2);
								
								valorDepreciacion = Util.redondearFloat((calcularValor(tariffsDepreciacion.get(0),
										kmIncluidoAlquiler,diferenciaKMEntrega.floatValue(), 0L)
										+ valorDevolucionDepreciacion),2);
								
								valorEspacioFisico = Util.redondearFloat((calcularValor(tariffsEspacioFisico.get(0),
										kmIncluidoAlquiler,diferenciaKMEntrega.floatValue(), 0L)
										+ valorDevolucionEspacioFisico),2);
								
								valorMantenimiento = Util.redondearFloat((calcularValor(tariffsMantenimiento.get(0),
										kmIncluidoAlquiler,diferenciaKMEntrega.floatValue(), 0L)
										+ valorDevolucionMantenimiento),2);
								
//								valor = calcularValor(tariffsAlquiler.get(0),
//										kmIncluidoAlquiler,diferenciaKMEntrega.floatValue(), 0L)
//										+ valorDevolucion;
								
								valor = valorAutoSeguro + valorDepreciacion
										+ valorEspacioFisico
										+ valorMantenimiento + valorDevolucion;

								kmDiferencia = diferenciaKMEntrega - kmIncluidoAlquiler;
								if (valor > 0)
									msgKMAdicional = "KM add: " + kmDiferencia;
							} else{
								valor = valorDevolucion;
								valorAutoSeguro = valorDevolucionAutoSeguro;
								valorDepreciacion = valorDevolucionDepreciacion;
								valorEspacioFisico = valorDevolucionEspacioFisico;
								valorMantenimiento = valorDevolucionMantenimiento;
							}
							
							// adicionado para que actualice los datos cuando
							// el vehiculo se devolvio antes de la fecha de
							// entrega
							if (asignacion.getVhaDiasAlquiler() != null
									&& asignacion.getVhaDiasAlquiler() > 0) {
								asignacion.setVhaDiasAlquiler(asignacion.getVhaDiasAlquiler()
										- diasDiferenciaEntrega);
							}

							if (asignacion.getVhaValorAlquiler() != null
									&& asignacion.getVhaValorAlquiler() > 0) {
								asignacion.setVhaValorAlquiler(asignacion.getVhaValorAlquiler()
										+ valorDevolucion);
							}
						}

						// Generar comprobante devolucion

						listCostsCentersVehicles = SearchCostCenters.consultarCCVehiculo(vhcPlacaDiplomatica.trim().toUpperCase());
						HeaderProof headerProof = null;
						Period period = FlatFileFuelServiceImpl.consultarPeriodoByfecha(fechaEntrega);
						
						String idMaster = new ConsultsServiceImpl().getIdMaster();
						Long idDetail = Long.valueOf(0);

						listAccountingParameters = searchAccountingParameters.consultarParametrizacionContableActivos(
								asignacion.getRequests().getLegateesTypes().getLgtCodigo(), 
						ParametersUtil.COMPRANTE_ALQUILER,
						ParametersUtil.CREDITO,
						vehicles.getLocations().getLocationsTypes().getLctCodigo());

						/*if(listAccountingParameters == null || listAccountingParameters.size() == 0){
							throw new GWorkException(Util.loadErrorMessageValue(
									"ERROR.ALQUILER.NO_EXISTEN_PARAMETROS_CREDITO") 
									+ " Tipo de localización: " + vehicles.getLocations().getLocationsTypes().getLctNombre()
									+ " Tipo de asignatario: " + asignacion.getRequests().getLegateesTypes().getLgtNombre()
									+ ", Por favor verificar.");
						}else{
							for (AccountingParameters accountingParameters : listAccountingParameters) {
								if (accountingParameters.getChargeType().getCgtId().longValue() == ParametersUtil.CARGO_MANTENIMIENTO.longValue()) {
									idDetail++;
								} else if (accountingParameters.getChargeType().getCgtId().longValue() == ParametersUtil.CARGO_DEPRECIACION.longValue()) {
									idDetail++;
								} else if (accountingParameters.getChargeType().getCgtId().longValue() == ParametersUtil.CARGO_AUTOSEGURO.longValue()) {
									idDetail++;
								} else if (accountingParameters.getChargeType().getCgtId().longValue() == ParametersUtil.CARGO_ESPACIO_FISICO.longValue()) {
									idDetail++;
								}
							}
						}*/

						//idDetail = (idDetail * listCostsCentersVehicles.size()) + listCostsCentersVehicles.size();
						String status = "P";

						if (valor != null
								&& listCostsCentersVehicles != null
								&& listCostsCentersVehicles.size() > 0
								&& asignacion.getVhaCobro().equalsIgnoreCase("S")) {
							
							headerProof = new PrepaidProofBoughtServiceImpl()
									.generarCabeceraComprobante(
											ParametersUtil.COMPRANTE_ALQUILER,
											period,
											ParametersUtil.TRASACCTION_TYPE_RENT,
											ParametersUtil.DOLAR);
							
							for (CostsCentersVehicles costsCentersVehicles : listCostsCentersVehicles) {
								Float valorCC = 0F;
//								valorCC = (valorCC * Long
//										.valueOf(costsCentersVehicles
//												.getCcrPorcentaje())) / 100F;
								
								if(asignacion.getRequests().getLegateesTypes() == null){
									throw new GWorkException(Util.loadErrorMessageValue("ERROR.DEVOLUCION.NO_EXISTE_TIPOASIGNATARIO"));
								}
									for (AccountingParameters accountingParameters : listAccountingParameters) {
										if (accountingParameters.getChargeType().getCgtId().longValue() == ParametersUtil.CARGO_MANTENIMIENTO.longValue()) {
											idDetail++;
											Float valorTemp = Util.redondearFloat(((valorMantenimiento * Long.valueOf(
													costsCentersVehicles.getCcrPorcentaje())) / 100F),2);
											valorCC = Util.redondearFloat((valorCC + valorTemp),2); 
											connection = rentProofService
												.generarComprobanteDevolucionAlquiler(connection,
													ParametersUtil.COMPRANTE_ALQUILER,
													login, ParametersUtil.CREDITO,
													fechaEntrega, asignacion, valorTemp,
													"", msgKMAdicional, headerProof, accountingParameters,
													idMaster, idDetail);

										} else if (accountingParameters.getChargeType().getCgtId().longValue() == 
											ParametersUtil.CARGO_DEPRECIACION.longValue()) {
											idDetail++;
											Float valorTemp = Util.redondearFloat(((valorDepreciacion * Long.valueOf(
													costsCentersVehicles.getCcrPorcentaje())) / 100F),2);
											valorCC = Util.redondearFloat((valorCC + valorTemp),2);
											connection = rentProofService
												.generarComprobanteDevolucionAlquiler(connection,
												ParametersUtil.COMPRANTE_ALQUILER,
												login, ParametersUtil.CREDITO,
												fechaEntrega, asignacion, valorTemp,
												"", msgKMAdicional, headerProof, accountingParameters,
												idMaster, idDetail);
									
										} else if (accountingParameters.getChargeType().getCgtId().longValue() == 
											ParametersUtil.CARGO_AUTOSEGURO.longValue()) {
											idDetail++;
											Float valorTemp = Util.redondearFloat(((valorAutoSeguro * Long.valueOf(
													costsCentersVehicles.getCcrPorcentaje())) / 100F),2);
											valorCC = Util.redondearFloat((valorCC + valorTemp),2);
											connection = rentProofService
												.generarComprobanteDevolucionAlquiler(connection,
												ParametersUtil.COMPRANTE_ALQUILER,
												login, ParametersUtil.CREDITO,
												fechaEntrega, asignacion, valorTemp,
												"", msgKMAdicional, headerProof, accountingParameters,
												idMaster, idDetail);

										} else if (accountingParameters.getChargeType().getCgtId().longValue() == 
											ParametersUtil.CARGO_ESPACIO_FISICO.longValue()) {
											idDetail++;
											Float valorTemp = Util.redondearFloat(((valorEspacioFisico * Long.valueOf(
													costsCentersVehicles.getCcrPorcentaje())) / 100F),2);
											valorCC = Util.redondearFloat((valorCC + valorTemp),2);
											connection = rentProofService
												.generarComprobanteDevolucionAlquiler(connection,
												ParametersUtil.COMPRANTE_ALQUILER,
												login, ParametersUtil.CREDITO,
												fechaEntrega, asignacion, valorTemp,
												"", msgKMAdicional, headerProof, accountingParameters,
												idMaster, idDetail);
										}
									}
								

								listAccountingParameters = searchAccountingParameters
								.consultarParametrizacionContableActivos(
										asignacion.getRequests().getLegateesTypes().getLgtCodigo(),
									ParametersUtil.COMPRANTE_ALQUILER,
									ParametersUtil.DEBITO,
									vehicles.getLocations().getLocationsTypes().getLctCodigo());

								if(listAccountingParameters != null && listAccountingParameters.size() > 0){
									for (AccountingParameters accountingParameters : listAccountingParameters) {
										idDetail++;
										rentProofService.generarComprobanteDevolucionAlquiler(connection,
												ParametersUtil.COMPRANTE_ALQUILER,
												login, ParametersUtil.DEBITO,
												fechaEntrega, asignacion,valorCC, 
												costsCentersVehicles.getCostsCenters().getCocNumero(),
												msgKMAdicional, headerProof,
												accountingParameters,
												idMaster, idDetail);
									}
								}else{
									throw new GWorkException(Util.loadErrorMessageValue(
									"ERROR.ALQUILER.NO_EXISTEN_PARAMETROS_DEBITO") 
									+ " Tipo de localización: " + vehicles.getLocations().getLocationsTypes().getLctNombre()
									+ " Tipo de asignatario: " + asignacion.getRequests().getLegateesTypes().getLgtNombre()
									+ ", Por favor verificar.");
								}
							}

							connection = ConsultsServiceImpl.insertTMaster(connection, idMaster, status, idDetail.intValue());

						} else if ((asignacion.getRequests().getRequestsClasses().getRqcCodigo().longValue() == ParametersUtil.CLASE_ALQUILER_TERCEROS.longValue()
								|| (asignacion.getRequests().getRequestsClasses().getRqcCodigo().longValue() == ParametersUtil.CLASE_ALQUILER.longValue() 
										&& (asignacion.getRequests().getLegateesTypes().getLgtCodigo().longValue() == ParametersUtil.LGT_CONVENIO.longValue()))
										||(asignacion.getRequests().getLegateesTypes().getLgtCodigo().longValue() == ParametersUtil.LGT_PERSONAL.longValue()))
								&& asignacion.getVhaCobro().equalsIgnoreCase(
									Util.loadMessageValue("ESTADO_COBRO_SI"))) {
							
							headerProof = new PrepaidProofBoughtServiceImpl()
									.generarCabeceraComprobante(
											ParametersUtil.COMPRANTE_ALQUILER,
											period,
											ParametersUtil.TRASACCTION_TYPE_RENT,
											ParametersUtil.DOLAR);

							valor = 0F;
							idDetail = Long.valueOf(0);
							
							for (AccountingParameters accountingParameters : listAccountingParameters) {
								if (accountingParameters.getChargeType().getCgtId().longValue() == ParametersUtil.CARGO_MANTENIMIENTO.longValue()) {
								
									idDetail++;
									connection = rentProofService.generarComprobanteDevolucionAlquiler(
											connection,ParametersUtil.COMPRANTE_ALQUILER,
												login, ParametersUtil.CREDITO,
												fechaEntrega, asignacion, valorMantenimiento,
												"", msgKMAdicional, headerProof, accountingParameters, 
												idMaster, idDetail);
										
									valor = Util.redondearFloat(valor + valorMantenimiento,2);
										
								} else if (accountingParameters.getChargeType().getCgtId().longValue() == 
									ParametersUtil.CARGO_DEPRECIACION.longValue()) {
									idDetail++;
										connection = rentProofService
											.generarComprobanteDevolucionAlquiler(connection,
											ParametersUtil.COMPRANTE_ALQUILER,
											login, ParametersUtil.CREDITO,
											fechaEntrega, asignacion, valorDepreciacion,
											"", msgKMAdicional, headerProof, accountingParameters,
											idMaster, idDetail);
										
										valor = Util.redondearFloat(valor + valorDepreciacion,2);
								
									} else if (accountingParameters.getChargeType().getCgtId().longValue() == 
										ParametersUtil.CARGO_AUTOSEGURO.longValue()) {
										idDetail++;
										connection = rentProofService
											.generarComprobanteDevolucionAlquiler(connection,
											ParametersUtil.COMPRANTE_ALQUILER,
											login, ParametersUtil.CREDITO,
											fechaEntrega, asignacion, valorAutoSeguro,
											"", msgKMAdicional, headerProof, accountingParameters,
											idMaster, idDetail);

										valor = Util.redondearFloat(valor + valorAutoSeguro,2);
										
									} else if (accountingParameters.getChargeType().getCgtId().longValue() == 
										ParametersUtil.CARGO_ESPACIO_FISICO.longValue()) {
										idDetail++;
										connection = rentProofService
											.generarComprobanteDevolucionAlquiler(connection,
											ParametersUtil.COMPRANTE_ALQUILER,
											login, ParametersUtil.CREDITO,
											fechaEntrega, asignacion, valorEspacioFisico,
											"", msgKMAdicional, headerProof, accountingParameters,
											idMaster, idDetail);
										
										valor = Util.redondearFloat(valor + valorEspacioFisico,2);
									}
								}

							listAccountingParameters = searchAccountingParameters.consultarParametrizacionContableActivos(
								asignacion.getRequests().getLegateesTypes().getLgtCodigo(),
								ParametersUtil.COMPRANTE_ALQUILER,
								ParametersUtil.DEBITO,
								vehicles.getLocations().getLocationsTypes().getLctCodigo());

							if(listAccountingParameters != null && listAccountingParameters.size() > 0){

								for (AccountingParameters accountingParameters : listAccountingParameters) {
									idDetail++;
									rentProofService.generarComprobanteDevolucionAlquiler(connection,
											ParametersUtil.TRASACCTION_TYPE_RENT,
											login, ParametersUtil.DEBITO,
											fechaEntrega, asignacion, valor,
											"", msgKMAdicional, headerProof,
											accountingParameters, 
											idMaster, idDetail);
								}
							}else{
								throw new GWorkException(Util.loadErrorMessageValue(
								"ERROR.ALQUILER.NO_EXISTEN_PARAMETROS_DEBITO") 
								+ " Tipo de localización: " + vehicles.getLocations().getLocationsTypes().getLctNombre()
								+ " Tipo de asignatario: " + asignacion.getRequests().getLegateesTypes().getLgtNombre()
								+ ", Por favor verificar.");
							}
							
							connection = ConsultsServiceImpl.insertTMaster(connection, idMaster, status, idDetail.intValue());
							
							if (asignacion.getRequests()
									.getRequestsClasses().getRqcCodigo().longValue() == ParametersUtil.CLASE_ALQUILER
									&& valor != null
									&& (asignacion.getCostsCentersVehicleses() == null || asignacion.getCostsCentersVehicleses().size() == 0)
									&& asignacion.getRequests().getLegateesTypes().getLgtCodigo().longValue() == ParametersUtil.LGT_PERSONAL.longValue()) {

								new RentProofServiceImpl().generarAlquilerNomina(
										asignacion, valor, fechaEntrega, login,
										tariffsAlquiler.get(0));
							}
							
						} else if (asignacion.getRequests()
								.getRequestsClasses().getRqcCodigo()
								.longValue() == ParametersUtil.CLASE_ALQUILER
								&& valor != null
								&& (asignacion.getCostsCentersVehicleses() == null || asignacion
										.getCostsCentersVehicleses().size() == 0)) {

							new RentProofServiceImpl().generarAlquilerNomina(
									asignacion, valor, fechaEntrega, login,
									tariffsAlquiler.get(0));
						}
					}

					// Si hay kilometraje adicional se ingresa el valor cobrado
					// y la cantidad adicional del kilometraje
					if (kmDiferencia != null) {

						if (valorDiasAdicional != null
								&& valorDiasAdicional > 0) {
							asignacion.setVhaValorKmAdicional(valorKmAdicional);
						} else if(totalValorKMAdicional != null &&
								totalValorKMAdicional > 0){
							asignacion.setVhaValorKmAdicional(totalValorKMAdicional);
						} else {
							asignacion.setVhaValorKmAdicional(valor);
						}
						asignacion.setVhaKmAdicional(kmDiferencia.longValue());
					}

					asignacion.setAssignationsStates(assignationsStates);
					asignacion.setVhaFechaDev(fechaEntrega);
					asignacion.setVhaKilomDev(kilometraje);
					asignacion.setVhaObservacionDev(observacionDev);
					actualizarEstadoAsignacion(asignacion);

					if (vehiclesStates != null) {
						vehicles.setVehiclesStates(vehiclesStates);
						vehicles.setVhcKilometrajeActual(kilometraje);
						new VehiclesDAO().update(vehicles);
					}

					crearDevolucionVehiculo(vhcPlacaDiplomatica, fechaEntrega,
							kilometraje, statesVehicles, tipoAsignacion,
							vehicles, cobroTarifaAsignacion,
							devolucionValorAsignacion, connection);
				}
			}
		} catch (Exception e) {
			throw new GWorkException(e.getMessage());
		}finally{
			try{
				if (connection!=null && !connection.isClosed()){
					connection.close();
				}
			}catch(Exception ex2){
				log.error("Error: " + ex2.getMessage(), ex2);
				throw new GWorkException(ex2.getMessage(), ex2);
			}
		}
	}

	/* (non-Javadoc)
	 * @see geniar.siscar.logic.vehicle.services.AllocationReturnVehicleService#consultarEstadoAsignacion(java.lang.Long)
	 */
	public int consultarEstadoAsignacion(Long estadoVehiculo) {
		return SearchVehicles.consultarEstadoAsignacion(estadoVehiculo);
	}

	/**
	 * Calcular k mincluido.
	 *
	 * @param tariffs the tariffs
	 * @param diferenciaDias the diferencia dias
	 * @return the float
	 */
	public Float calcularKMincluido(Tariffs tariffs, Long diferenciaDias) {

		Float kilometrajeIncluido = diferenciaDias * tariffs.getTrfKmIncluido();

		return kilometrajeIncluido;
	}

	/**
	 * Calcular valor.
	 *
	 * @param tariffs the tariffs
	 * @param kmIncluido the km incluido
	 * @param kmEntrega the km entrega
	 * @param diasAdcionales the dias adcionales
	 * @return the float
	 */
	public Float calcularValor(Tariffs tariffs, Float kmIncluido,
			Float kmEntrega, Long diasAdcionales) {
		Float valor = null;
		Float kmCobro = Float.valueOf(0);
		
		if(kmIncluido < kmEntrega){
			kmCobro = kmEntrega - kmIncluido;
		}
		
		Float valorDiasAdcionales = diasAdcionales * tariffs.getTrfValor();

		valor = kmCobro * tariffs.getTrfKmValorAdicional()
				+ valorDiasAdcionales;
		valor = valor * 100F;
		valor = new Float(Math.round(valor.doubleValue()));
		return valor / 100F;
	}

	/* (non-Javadoc)
	 * @see geniar.siscar.logic.vehicle.services.AllocationReturnVehicleService#envioNotificacion(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.util.Date, java.util.Date, java.lang.Long, java.lang.Long, java.lang.String, java.lang.String)
	 */
	public void envioNotificacion(String PlacaDiplomatica,
			String TipoAsignacion, String NombreAsignatario,
			String CarnetAsignatario, Date FechaEntrega, Date FechaDevolucion,
			Long KilometrajeInicial, Long KilometrajeDevolucion,
			String Observacion, String email) throws GWorkException,
			RuntimeException {

		Rolls rolls = new RollsDAO().findById(new Long(Util
				.loadParametersValue("MAIL.ADMINISTRADOR")));

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		String strFechaEntrega = simpleDateFormat.format(FechaEntrega);
		String strFechaDevolucion = simpleDateFormat.format(FechaDevolucion);
		String SaludoMensaje = Util.loadParametersValue("SALUDO.MSJ.DEV.VEHI");
		// cuerpo de la notificacion
		String textmessage = SaludoMensaje
				+ "<br /><br /><br />Placa Vehiculo: " + PlacaDiplomatica
				+ "<br />Tipo Asignatario: " + TipoAsignacion
				+ "<br />Nombre Asignatario: " + NombreAsignatario
				+ "<br />Carnet Asignatario: " + CarnetAsignatario
				+ "<br />Fecha Entrega: " + strFechaEntrega
				+ "<br />Fecha Devolución: " + strFechaDevolucion
				+ "<br />Kilometraje Inicial: " + KilometrajeInicial
				+ "<br />Kilometraje Devolución: " + KilometrajeDevolucion
				+ "<br />Observación: " + Observacion;

		String subjectUser = "Devolución Vehículo: " + PlacaDiplomatica + " - "
				+ NombreAsignatario;

		String server = Util.loadParametersValue("MAIL.HOST");
		String fromUser = rolls.getRlsMail();

		SendEmail mail = new SendEmail(email, fromUser, server, "false",
				subjectUser, textmessage);

		if (mail.SendMessage().equals("SUCCESS"))
			log.info("Mensaje enviado exitosamente");
		else {
			log.info("Eror Enviando el mensaje");
			throw new RuntimeException(Util
					.loadErrorMessageValue("NOTIFICACION.ERROR"));
		}
	}
}