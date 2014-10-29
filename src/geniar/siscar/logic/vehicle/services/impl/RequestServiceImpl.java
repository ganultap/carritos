package geniar.siscar.logic.vehicle.services.impl;

import geniar.siscar.logic.consultas.RequestConsultDAO;
import geniar.siscar.logic.consultas.SearchOrderRequest;
import geniar.siscar.logic.vehicle.services.RequestService;
import geniar.siscar.mail.util.SendEmail;
import geniar.siscar.model.CostsCenters;
import geniar.siscar.model.CostsCentersVehicles;
import geniar.siscar.model.LegateesTypes;
import geniar.siscar.model.Requests;
import geniar.siscar.model.RequestsClasses;
import geniar.siscar.model.RequestsStates;
import geniar.siscar.model.RequestsTypes;
import geniar.siscar.model.Rolls;
import geniar.siscar.model.Users;
import geniar.siscar.model.Vehicles;
import geniar.siscar.model.VehiclesAssignation;
import geniar.siscar.model.VehiclesTypes;
import geniar.siscar.persistence.CostsCentersDAO;
import geniar.siscar.persistence.CostsCentersVehiclesDAO;
import geniar.siscar.persistence.EntityManagerHelper;
import geniar.siscar.persistence.ICostsCentersDAO;
import geniar.siscar.persistence.IVehiclesDAO;
import geniar.siscar.persistence.LegateesTypesDAO;
import geniar.siscar.persistence.RequestsClassesDAO;
import geniar.siscar.persistence.RequestsDAO;
import geniar.siscar.persistence.RequestsStatesDAO;
import geniar.siscar.persistence.RequestsTypesDAO;
import geniar.siscar.persistence.RollsDAO;
import geniar.siscar.persistence.UsersDAO;
import geniar.siscar.persistence.VehiclesAssignationDAO;
import geniar.siscar.persistence.VehiclesDAO;
import geniar.siscar.persistence.VehiclesStatesDAO;
import geniar.siscar.persistence.VehiclesTypesDAO;
import geniar.siscar.persistence.ZonesDAO;
import geniar.siscar.util.ParametersUtil;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * The Class RequestServiceImpl.
 */
public class RequestServiceImpl implements RequestService {
		
	/** The REMPLAZO. */
	private static Long REMPLAZO = 2L;
	
	/** The EXTENSION. */
	private static Long EXTENSION = 3L;

	/** The Constant log. */
	private static final Log log = LogFactory.getLog(RequestServiceImpl.class);

	/* (non-Javadoc)
	 * @see geniar.siscar.logic.vehicle.services.RequestService#listRequestsClasses()
	 */
	public List<RequestsClasses> listRequestsClasses() throws GWorkException {
		EntityManagerHelper.getEntityManager().clear();
		List<RequestsClasses> listRequestsClasses = new SearchOrderRequest()
				.requestClassesOrder();
		if (listRequestsClasses == null)
			// TODO hacer Excepcion
			throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));
		return listRequestsClasses;
	}

	/* (non-Javadoc)
	 * @see geniar.siscar.logic.vehicle.services.RequestService#listRequestsTypes()
	 */
	public List<RequestsTypes> listRequestsTypes() throws GWorkException {
		EntityManagerHelper.getEntityManager().clear();
		List<RequestsTypes> listRequestsTypes = new SearchOrderRequest()
				.requestTypesOrder();
		if (listRequestsTypes == null)
			// TODO hacer Excepcion
			throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));
		return listRequestsTypes;
	}

	/* (non-Javadoc)
	 * @see geniar.siscar.logic.vehicle.services.RequestService#listRequestsStates()
	 */
	public List<RequestsStates> listRequestsStates() throws GWorkException {
		EntityManagerHelper.getEntityManager().clear();
		List<RequestsStates> listRequestsStates = new SearchOrderRequest()
				.requestStatesOrder();
		if (listRequestsStates == null)
			throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));
		return listRequestsStates;
	}

	/* (non-Javadoc)
	 * @see geniar.siscar.logic.vehicle.services.RequestService#listLegateesTypes()
	 */
	public List<LegateesTypes> listLegateesTypes() throws GWorkException {

		EntityManagerHelper.getEntityManager().clear();
		List<LegateesTypes> listLegateesTypes = new SearchOrderRequest()
				.legateesTypesOrder();
		if (listLegateesTypes == null)
			throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));
		return listLegateesTypes;
	}

	/* (non-Javadoc)
	 * @see geniar.siscar.logic.vehicle.services.RequestService#listRequests()
	 */
	public List<Requests> listRequests() throws GWorkException {
		EntityManagerHelper.getEntityManager().clear();
		List<Requests> listRequest = new RequestsDAO().findAll();
		if (listRequest == null)
			throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));
		return listRequest;
	}

	/* (non-Javadoc)
	 * @see geniar.siscar.logic.vehicle.services.RequestService#vehicleRequest(java.lang.Long, java.lang.Long, java.lang.String, java.lang.Long, java.util.Date, java.util.Date, java.lang.Long, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.util.List, java.lang.String, java.lang.Long, java.lang.Long, java.lang.String)
	 */
	@SuppressWarnings("null")
	public void vehicleRequest(Long claseSolicitud, Long tipoSolicitud,
			String placaActual, Long tipoAsignacion, Date fechaDesde,
			Date fechaHasta, Long tipoVehiculo, String descripcion,
			String carneAsignatario, String nombreAsig, String emailAsig,
			String carneAsistente, String nombreAsis, String emailAsis,
			List<CostsCentersVehicles> listCostsCentersRequests, String nit,
			Long state, Long idZones, String carneLogin) throws GWorkException {

		if (EntityManagerHelper.getEntityManager().isOpen())
			EntityManagerHelper.getEntityManager().close();

		try {
			RequestsClasses requestsClasses = new RequestsClassesDAO()
					.findById(claseSolicitud);

			if (requestsClasses == null)
				throw new GWorkException(Util
						.loadErrorMessageValue("CLASESOLICITUD"));

			RequestsTypes requestsTypes = new RequestsTypesDAO()
					.findById(tipoSolicitud);
			if (requestsTypes == null)
				throw new GWorkException(Util
						.loadErrorMessageValue("TIPOSOLICITUD.SEL"));
			VehiclesTypes vehiclesTypes = new VehiclesTypesDAO()
					.findById(tipoVehiculo);
			if (vehiclesTypes == null)
				throw new GWorkException(Util.loadErrorMessageValue("TIPO.VHC"));

			LegateesTypes legateesTypes = new LegateesTypesDAO()
					.findById(tipoAsignacion);
			if (requestsClasses.getRqcCodigo() == 1L && legateesTypes == null)
				throw new GWorkException(Util
						.loadErrorMessageValue("TIPOASIGNACION"));

			if (fechaDesde == null)
				throw new GWorkException(Util
						.loadErrorMessageValue("FECHAINICIO"));
			if (fechaHasta == null)
				throw new GWorkException(Util.loadErrorMessageValue("FECHAFIN"));

			if (fechaHasta.compareTo(fechaDesde) == -1)
				throw new GWorkException(Util
						.loadErrorMessageValue("FCH_INI_FCH_FIN"));

			if (tipoVehiculo == null)
				throw new GWorkException(Util.loadErrorMessageValue("TIPO.VHC"));
			if (descripcion == null || descripcion.trim().length() == 0)
				throw new GWorkException(Util
						.loadErrorMessageValue("DESCRIPCION"));

			List<Requests> listRequest = new RequestsDAO()
					.findByRqsPlacaExtensionRemplazo(placaActual);
			if ((tipoSolicitud.longValue() == REMPLAZO || tipoSolicitud
					.longValue() == EXTENSION)
					&& (listRequest != null && listRequest.size() > 0 && !listRequest
							.isEmpty()))
				throw new GWorkException(Util
						.loadErrorMessageValue("SOLICITUD.REMPLAZO"));

			if ((claseSolicitud.longValue() == ParametersUtil.CLASE_ALQUILER.longValue()
					&& legateesTypes.getLgtCodigo().longValue() != ParametersUtil.LGT_CONVENIO.longValue())
					|| (legateesTypes != null
							&& (legateesTypes.getLgtCodigo().longValue() == ParametersUtil.LGT_OF.longValue()
							|| legateesTypes.getLgtCodigo().longValue() == ParametersUtil.LGT_OFS.longValue()
							|| legateesTypes.getLgtCodigo().longValue() == ParametersUtil.LGT_PROGRAMAS.longValue() || legateesTypes
							.getLgtCodigo().longValue() == ParametersUtil.LGT_PROYECTO.longValue()))) {

				if (idZones == null
						|| idZones.longValue() == 0
						|| idZones.longValue() == -1L
						&& (legateesTypes != null 
								&& (legateesTypes.getLgtCodigo().longValue() == ParametersUtil.LGT_OF.longValue() 
								|| legateesTypes.getLgtCodigo().longValue() == ParametersUtil.LGT_OFS.longValue())))
					throw new GWorkException(Util
							.loadErrorMessageValue("ZONES.SEL"));

				Users usersAsig = null;
				Users usersAsis = null;
				
				if (carneAsignatario == null
						|| carneAsignatario.trim().length() == 0)
					throw new GWorkException(Util
							.loadErrorMessageValue("CARNET_ASIGNATARIO"));
				if (carneAsistente == null
						|| carneAsistente.trim().length() == 0)
					throw new GWorkException(Util
							.loadErrorMessageValue("CARNET_ASISTENTE"));

				/**
				 * Validar que se creen usuarios en el sistema, si ya existen
				 * consultarlos y referenciarlos a la solicitud
				 */
				if (new UsersDAO().findByUsrIdentificacion(carneAsignatario) == null
						|| new UsersDAO().findByUsrIdentificacion(
								carneAsignatario).isEmpty()
						|| new UsersDAO().findByUsrIdentificacion(
								carneAsignatario).size() == 0) {

					usersAsig = new Users();
					usersAsig.setUsrNombre(nombreAsig);
					usersAsig.setUsrEmail(emailAsig);
					usersAsig.setUsrIdentificacion(carneAsignatario);
					usersAsig.setRolls(new RollsDAO().findById(4L));
					EntityManagerHelper.beginTransaction();
					new UsersDAO().save(usersAsig);
					EntityManagerHelper.commit();
				} else if (new UsersDAO()
						.findByUsrIdentificacion(carneAsignatario) != null) {
					usersAsig = new UsersDAO().findByUsrIdentificacion(
							carneAsignatario).get(0);
				}

				if (new UsersDAO().findByUsrIdentificacion(carneAsistente) == null
						|| new UsersDAO().findByUsrIdentificacion(
								carneAsistente).isEmpty()
						|| new UsersDAO().findByUsrIdentificacion(
								carneAsistente).size() == 0) {

					usersAsis = new Users();
					usersAsis.setUsrNombre(nombreAsis);
					usersAsis.setUsrEmail(emailAsis);
					usersAsis.setUsrIdentificacion(carneAsistente);
					usersAsis.setRolls(new RollsDAO().findById(4L));
					EntityManagerHelper.beginTransaction();
					new UsersDAO().save(usersAsis);
					EntityManagerHelper.commit();

				} else if (new UsersDAO()
						.findByUsrIdentificacion(carneAsistente) != null
						|| !new UsersDAO().findByUsrIdentificacion(
								carneAsistente).isEmpty()) {
					usersAsis = new UsersDAO().findByUsrIdentificacion(
							carneAsistente).get(0);
				}

				if ((claseSolicitud.longValue() != ParametersUtil.CLASE_ALQUILER.longValue())
						&& (listCostsCentersRequests == null
								|| listCostsCentersRequests.isEmpty() || listCostsCentersRequests
								.size() == 0))
					throw new GWorkException(Util
							.loadErrorMessageValue("PORCENTAJE.ERROR"));

				if (requestsTypes.getRqyCodigo().longValue() == REMPLAZO
						|| requestsTypes.getRqyCodigo().longValue() == EXTENSION) {
					if (placaActual == null || placaActual.trim().length() == 0)
						throw new GWorkException(Util
								.loadErrorMessageValue("PLACA"));
					if (placaActual.trim().length() <= 1)
						throw new GWorkException(Util
								.loadErrorMessageValue("PLACA.MINIMO"));
					IVehiclesDAO vehiclesDAO = new VehiclesDAO();
					if (vehiclesDAO.findByVhcPlacaDiplomatica(placaActual) == null
							|| vehiclesDAO.findByVhcPlacaDiplomatica(
									placaActual).size() == 0
							|| vehiclesDAO.findByVhcPlacaDiplomatica(
									placaActual).isEmpty())
						throw new GWorkException(Util
								.loadErrorMessageValue("PLACA.EXISTEN"));
				}

				Requests requests = new Requests();
				requests.setRequestsClasses(requestsClasses);
				RequestsStates requestsStates = new RequestsStatesDAO()
						.findById(state);
				requests.setRequestsStates(requestsStates);
				requests.setRqsDescripcion(descripcion);
				requests.setRqsCarnetEmpleado(carneAsistente);
				requests.setRqsCarnetAsignatario(carneAsignatario);
				requests.setRqsFechaSolicitud(new Date());

				requests.setRqsFechaInicial(fechaDesde);
				requests.setRqsFechaFinal(fechaHasta);
				requests.setVehiclesTypes(new VehiclesTypesDAO()
						.findById(tipoVehiculo));
				requests.setLegateesTypes(legateesTypes);
				requests.setRequestsTypes(requestsTypes);
				requests.setRqsCarneLogin(carneLogin);

				if (idZones != null && idZones.longValue() != -1L)
					requests.setZones(new ZonesDAO().findById(idZones));

				if (placaActual != null && placaActual.trim().length() > 0)
					requests.setRqsPlacaExtensionRemplazo(placaActual);

				validarSession();
				EntityManagerHelper.beginTransaction();
				requests.setUsersByRqsUser(usersAsig);
				requests.setUsersByUsrCodigo(usersAsis);
				new RequestsDAO().save(requests);
				EntityManagerHelper.commit();

				validarSession();

				if (listCostsCentersRequests != null
						&& listCostsCentersRequests.size() > 0) {
					for (CostsCentersVehicles centersVehicles : listCostsCentersRequests) {
						CostsCentersVehicles centersVehicles2 = new CostsCentersVehicles();
						CostsCenters costsCenters;

						String codigoCC = centersVehicles.getCostsCenters()
								.getCocNumero();

						ICostsCentersDAO costsCentersDAO = new CostsCentersDAO();
						if (costsCentersDAO.findByCocNumero(codigoCC) == null
								|| costsCentersDAO.findByCocNumero(codigoCC)
										.isEmpty()
								|| costsCentersDAO.findByCocNumero(codigoCC)
										.size() == 0) {
							costsCenters = new CostsCenters();

							costsCenters.setCocNumero(centersVehicles
									.getCostsCenters().getCocNumero());

							centersVehicles2.setCcrPorcentaje(centersVehicles
									.getCcrPorcentaje());
							centersVehicles2.setCcrFechaInicio(fechaDesde);
							centersVehicles2.setCcrFechaFin(fechaHasta);
							centersVehicles2.setRequests(requests);
							centersVehicles2.setCcrValor(0L);
							centersVehicles2.setCostsCenters(costsCenters);

							EntityManagerHelper.beginTransaction();
							new CostsCentersDAO().save(costsCenters);
							new CostsCentersVehiclesDAO()
									.save(centersVehicles2);
							EntityManagerHelper.commit();

						} else if (costsCentersDAO.findByCocNumero(codigoCC) != null
								|| !costsCentersDAO.findByCocNumero(codigoCC)
										.isEmpty()
								|| costsCentersDAO.findByCocNumero(codigoCC)
										.size() > 0) {

							costsCenters = costsCentersDAO.findByCocNumero(
									codigoCC).get(0);

							centersVehicles2.setCcrPorcentaje(centersVehicles
									.getCcrPorcentaje());
							centersVehicles2.setCcrFechaInicio(fechaDesde);
							centersVehicles2.setCcrFechaFin(fechaHasta);
							centersVehicles2.setRequests(requests);
							centersVehicles2.setCcrValor(0L);
							centersVehicles2.setCostsCenters(costsCenters);

							EntityManagerHelper.beginTransaction();
							new CostsCentersVehiclesDAO()
									.save(centersVehicles2);
							EntityManagerHelper.commit();
						}
					}
				}
			} else if (legateesTypes != null
					&& (legateesTypes.getLgtCodigo().longValue() == ParametersUtil.LGT_PERSONAL.longValue())) {

				if (carneAsignatario == null
						|| carneAsignatario.trim().length() == 0)
					throw new GWorkException(Util
							.loadErrorMessageValue("CARNET_ASIGNATARIO"));
				if (carneAsistente == null
						|| carneAsistente.trim().length() == 0)
					throw new GWorkException(Util
							.loadErrorMessageValue("CARNET_ASISTENTE"));

				if (requestsTypes.getRqyCodigo().longValue() == REMPLAZO
						|| requestsTypes.getRqyCodigo().longValue() == EXTENSION) {
					if (placaActual.trim().length() == 0)
						throw new GWorkException(Util
								.loadErrorMessageValue("PLACA"));
					if (placaActual.trim().length() <= 1)
						throw new GWorkException(Util
								.loadErrorMessageValue("PLACA.MINIMO"));
					if (new VehiclesDAO()
							.findByVhcPlacaDiplomatica(placaActual) == null
							|| new VehiclesDAO().findByVhcPlacaDiplomatica(
									placaActual).size() == 0
							|| new VehiclesDAO().findByVhcPlacaDiplomatica(
									placaActual).isEmpty())
						throw new GWorkException(Util
								.loadErrorMessageValue("PLACA.EXISTEN"));
				}

				/**
				 * Validar que se creen usuarios en el sistema, si ya existen
				 * consultarlos y referenciarlos a la solicitud
				 */
				Users usersAsig = null;
				Users usersAsis = null;
				if (new UsersDAO().findByUsrIdentificacion(carneAsignatario) == null
						|| new UsersDAO().findByUsrIdentificacion(
								carneAsignatario).isEmpty()
						|| new UsersDAO().findByUsrIdentificacion(
								carneAsignatario).size() == 0) {

					usersAsig = new Users();
					usersAsig.setUsrNombre(nombreAsig);
					usersAsig.setUsrEmail(emailAsig);
					usersAsig.setUsrIdentificacion(carneAsignatario);
					usersAsig.setRolls(new RollsDAO().findById(4L));
					EntityManagerHelper.beginTransaction();
					new UsersDAO().save(usersAsig);
					EntityManagerHelper.commit();
				} else if (new UsersDAO()
						.findByUsrIdentificacion(carneAsignatario) != null) {
					usersAsig = new UsersDAO().findByUsrIdentificacion(
							carneAsignatario).get(0);
				}

				if (new UsersDAO().findByUsrIdentificacion(carneAsistente) == null
						|| new UsersDAO().findByUsrIdentificacion(
								carneAsistente).isEmpty()
						|| new UsersDAO().findByUsrIdentificacion(
								carneAsistente).size() == 0) {

					usersAsis = new Users();
					usersAsis.setUsrNombre(nombreAsis);
					usersAsis.setUsrEmail(emailAsis);
					usersAsis.setUsrIdentificacion(carneAsistente);
					usersAsis.setRolls(new RollsDAO().findById(4L));
					EntityManagerHelper.beginTransaction();
					new UsersDAO().save(usersAsis);
					EntityManagerHelper.commit();

				} else if (new UsersDAO()
						.findByUsrIdentificacion(carneAsistente) != null
						|| !new UsersDAO().findByUsrIdentificacion(
								carneAsistente).isEmpty()) {
					usersAsis = new UsersDAO().findByUsrIdentificacion(
							carneAsistente).get(0);
				}

				Requests requests = new Requests();
				requests.setRequestsClasses(requestsClasses);
				RequestsStates requestsStates = new RequestsStatesDAO()
						.findById(state);
				requests.setRequestsStates(requestsStates);
				requests.setRqsDescripcion(descripcion);
				requests.setRqsCarnetEmpleado(carneAsistente);
				requests.setRqsCarnetAsignatario(carneAsignatario);
				requests.setRqsFechaSolicitud(new Date());
				requests.setRqsFechaInicial(fechaDesde);
				requests.setRqsFechaFinal(fechaHasta);
				requests.setVehiclesTypes(new VehiclesTypesDAO()
						.findById(tipoVehiculo));
				requests.setLegateesTypes(legateesTypes);
				requests.setRequestsTypes(requestsTypes);
				requests.setRqsCarneLogin(carneLogin);
				if (placaActual != null && placaActual.trim().length() > 0)
					requests.setRqsPlacaExtensionRemplazo(placaActual);

				EntityManagerHelper.beginTransaction();
				requests.setUsersByRqsUser(usersAsig);
				requests.setUsersByUsrCodigo(usersAsis);
				new RequestsDAO().save(requests);
				EntityManagerHelper.commit();

			} else if (claseSolicitud.longValue() == ParametersUtil.CLASE_ALQUILER_TERCEROS.longValue()
					|| (legateesTypes != null && legateesTypes.getLgtCodigo()
							.longValue() == ParametersUtil.LGT_CONVENIO.longValue())) {
				if (nit == null || nit.trim().length() == 0)
					throw new GWorkException(Util.loadErrorMessageValue("NIT"));

				if (requestsTypes.getRqyCodigo() == REMPLAZO
						|| requestsTypes.getRqyCodigo().longValue() == EXTENSION) {
					if (placaActual.trim().length() == 0)
						throw new GWorkException(Util
								.loadErrorMessageValue("PLACA"));
					if (placaActual.trim().length() <= 1)
						throw new GWorkException(Util
								.loadErrorMessageValue("PLACA.MINIMO"));
					IVehiclesDAO vehiclesDAO = new VehiclesDAO();
					if (vehiclesDAO.findByVhcPlacaDiplomatica(placaActual) == null
							|| vehiclesDAO.findByVhcPlacaDiplomatica(
									placaActual).size() == 0
							|| vehiclesDAO.findByVhcPlacaDiplomatica(
									placaActual).isEmpty())
						throw new GWorkException(Util
								.loadErrorMessageValue("PLACA.EXISTEN"));
				}

				Users usertercero = null;

				if (new UsersDAO().findByUsrIdentificacion(nit) == null
						|| new UsersDAO().findByUsrIdentificacion(nit)
								.isEmpty()
						|| new UsersDAO().findByUsrIdentificacion(nit).size() == 0) {

					usertercero = new Users();
					usertercero.setUsrNombre(nombreAsig);
					usertercero.setUsrEmail(emailAsig);
					usertercero.setUsrIdentificacion(nit);
					usertercero.setRolls(new RollsDAO().findById(4L));
					EntityManagerHelper.beginTransaction();
					new UsersDAO().save(usertercero);
					EntityManagerHelper.commit();
				} else if (new UsersDAO().findByUsrIdentificacion(nit) != null
						|| !new UsersDAO().findByUsrIdentificacion(nit)
								.isEmpty()) {
					usertercero = new UsersDAO().findByUsrIdentificacion(nit)
							.get(0);
				}

				Requests requests = new Requests();
				requests.setRequestsClasses(requestsClasses);
				RequestsStates requestsStates = new RequestsStatesDAO()
						.findById(state);
				
				if (requestsClasses.getRqcCodigo().longValue() == ParametersUtil.CLASE_ALQUILER_TERCEROS) {
					legateesTypes = new LegateesTypesDAO().findById(ParametersUtil.LGT_TERCEROS);
				}

				if (legateesTypes.getLgtCodigo().longValue() == ParametersUtil.LGT_CONVENIO
						.longValue()) {
					legateesTypes = new LegateesTypesDAO().findById(ParametersUtil.LGT_CONVENIO);
				} else {
					legateesTypes = new LegateesTypesDAO().findById(ParametersUtil.LGT_TERCEROS);
				}

				requests.setRequestsStates(requestsStates);
				requests.setRqsDescripcion(descripcion);
				requests.setRqsFechaSolicitud(new Date());
				requests.setRqsFechaInicial(fechaDesde);
				requests.setRqsFechaFinal(fechaHasta);
				requests.setRqsCarnetAsignatario(carneAsignatario);
				requests.setVehiclesTypes(new VehiclesTypesDAO()
						.findById(tipoVehiculo));
				requests.setLegateesTypes(legateesTypes);
				requests.setRequestsTypes(requestsTypes);
				requests.setRqsCarnetAsignatario(nit);
				requests.setRqsCarneLogin(carneLogin);
				if (placaActual != null && placaActual.trim().length() > 0)
					requests.setRqsPlacaExtensionRemplazo(placaActual);
				EntityManagerHelper.beginTransaction();
				requests.setUsersByRqsUser(usertercero);
				new RequestsDAO().save(requests);
				EntityManagerHelper.commit();
			}
		} catch (Exception e) {
			throw new GWorkException(e.getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see geniar.siscar.logic.vehicle.services.RequestService#listRequestsState()
	 */
	public List<Requests> listRequestsState() throws GWorkException {
		EntityManagerHelper.getEntityManager().clear();
		List<Requests> listRequest = new RequestConsultDAO()
				.findByPropertyStates();
		if (listRequest == null || listRequest.size() == 0)
			throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));
		return listRequest;
	}

	/* (non-Javadoc)
	 * @see geniar.siscar.logic.vehicle.services.RequestService#listRequestUser(java.lang.String)
	 */
	public List<Requests> listRequestUser(String carnetAsig)
			throws GWorkException {
		EntityManagerHelper.getEntityManager().clear();
		List<Requests> listRequest = new RequestsDAO()
				.findByRqsCarnetAsignatario(carnetAsig);
		if (listRequest == null || listRequest.size() == 0)
			throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));
		return listRequest;
	}

	/* (non-Javadoc)
	 * @see geniar.siscar.logic.vehicle.services.RequestService#listRequestFilter(java.lang.Long, java.lang.Long, java.util.Date, java.util.Date)
	 */
	public List<Requests> listRequestFilter(Long claseSolicitud,
			Long estadoSolicitud, Date fechaDesde, Date fechaHasta)
			throws GWorkException {
		EntityManagerHelper.getEntityManager().clear();
		List<Requests> listRequest = new RequestConsultDAO()
				.findByPropertyFilter(claseSolicitud, estadoSolicitud,
						fechaDesde, fechaHasta);
		if (listRequest == null)
			throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));
		return listRequest;
	}

	/**
	 * Validar session.
	 */
	public void validarSession() {
		if (EntityManagerHelper.getEntityManager().isOpen()) {
			EntityManagerHelper.getEntityManager().close();
			EntityManagerHelper.getEntityManager().clear();
		}

	}

	/* (non-Javadoc)
	 * @see geniar.siscar.logic.vehicle.services.RequestService#devolverSolicitud(geniar.siscar.model.Requests, java.lang.String)
	 */
	public void devolverSolicitud(Requests requests, String motivoDevolucion)
			throws GWorkException {

		try {
			validarSession();
			requests.setRequestsStates(new RequestsStatesDAO().findById(6L));
			requests.setRqsDevolucion(motivoDevolucion);
			requests.setVehicles(null);

			List<VehiclesAssignation> listaAsignacion = new VehiclesAssignationDAO()
					.findByProperty("requests.rqsCodigo", requests
							.getRqsCodigo().longValue());

			if (listaAsignacion != null && listaAsignacion.size() == 1) {
				new VehiclesAssignationDAO().delete(listaAsignacion.get(0));
			}

			EntityManagerHelper.beginTransaction();
			new RequestsDAO().update(requests);
			EntityManagerHelper.commit();
			EntityManagerHelper.getEntityManager().clear();

		} catch (Exception e) {
			throw new GWorkException(Util
					.loadErrorMessageValue("REGISTRO.NOEXITOSO")
					+ " " + e.getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see geniar.siscar.logic.vehicle.services.RequestService#cancelarSolicitudAdministrador(java.lang.Long, java.lang.String)
	 */
	public void cancelarSolicitudAdministrador(Long rqsCodigo,
			String motivoCancelacion) throws GWorkException {

		try {

			Rolls rolls = new RollsDAO().findById(new Long(Util
					.loadParametersValue("MAIL.ADMINISTRADOR")));

			Requests requests = new RequestsDAO().findById(rqsCodigo
					.longValue());
			EntityManagerHelper.getEntityManager().refresh(requests);

			requests.setRqsMotivoCancelacion(motivoCancelacion);
			requests.setRequestsStates(new RequestsStatesDAO().findById(7L));
			requests.setRqsFechaCancelacion(new Date());

			EntityManagerHelper.getEntityManager().getTransaction().begin();
			new RequestsDAO().update(requests);
			EntityManagerHelper.getEntityManager().getTransaction().commit();

			if (requests.getVehiclesAssignations() != null
					&& requests.getVehiclesAssignations().size() > 0) {

				eliminarCentroCostos(requests.getRqsCodigo().longValue());
				eliminarAsignacion(requests.getRqsCodigo().longValue());

			}

			Requests requests2 = new RequestsDAO().findById(rqsCodigo);
			EntityManagerHelper.getEntityManager().refresh(requests2);

			// Se cambia el estado del vehiculo que estaba en asigna a pool
			IVehiclesDAO vehiclesDAO = new VehiclesDAO();
			Vehicles vehicles = null;

			if (requests.getVehicles() != null) {
				vehicles = vehiclesDAO.findById(requests.getVehicles()
						.getVhcCodigo().longValue());
				vehicles
						.setVehiclesStates(new VehiclesStatesDAO().findById(6L));
			}

			if (requests.getUsersByRqsUser().getUsrEmail() != null) {
				String server = Util.loadParametersValue("MAIL.HOST");
				String fromUser = rolls.getRlsMail();
				String toUser = requests.getUsersByRqsUser().getUsrEmail();
				String subject = Util
						.loadParametersValue("MSJ.CANCELACION.SOLICITUD.ADMIN");

				if (!EntityManagerHelper.getEntityManager().getTransaction()
						.isActive())
					EntityManagerHelper.getEntityManager().getTransaction()
							.begin();

				if (vehicles != null)
					new VehiclesDAO().update(vehicles);
				new RequestsDAO().update(requests2);
				EntityManagerHelper.getEntityManager().getTransaction()
						.commit();

				SendEmail mail = new SendEmail(toUser, fromUser, server,
						"false", subject, motivoCancelacion);

				if (mail.SendMessage().equals("SUCCESS"))
					log.info("Mensaje enviado exitosamente");
				else {
					log.info("Eror Enviando el mensaje");
					throw new GWorkException(Util
							.loadErrorMessageValue("NOTIFICACION.ERROR"));
				}
			}
		} catch (Exception e) {
			throw new GWorkException(Util
					.loadErrorMessageValue("REGISTRO.NOEXITOSO")
					+ " " + e.getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see geniar.siscar.logic.vehicle.services.RequestService#cancelarSolicitudUsuario(geniar.siscar.model.Requests, java.lang.String)
	 */
	public void cancelarSolicitudUsuario(Requests requests,
			String motivoCancelacion) throws GWorkException {
		try {

			Rolls rolls = new RollsDAO().findById(new Long(Util
					.loadParametersValue("MAIL.ADMINISTRADOR")));

			validarSession();
			requests.setRqsMotivoCancelacion(motivoCancelacion);
			requests.setRequestsStates(new RequestsStatesDAO().findById(7L));
			requests.setRqsFechaCancelacion(new Date());

			List<VehiclesAssignation> listaAsignacion = new VehiclesAssignationDAO()
					.findByProperty("requests.rqsCodigo", requests
							.getRqsCodigo().longValue());

			if (listaAsignacion != null && listaAsignacion.size() == 1) {
				new VehiclesAssignationDAO().delete(listaAsignacion.get(0));
			}

			EntityManagerHelper.beginTransaction();
			new RequestsDAO().update(requests);
			EntityManagerHelper.commit();

			if (requests.getUsersByRqsUser().getUsrEmail() != null) {
				String server = Util.loadParametersValue("MAIL.HOST");
				String fromUser = rolls.getRlsMail();
				String toUser = requests.getUsersByRqsUser().getUsrEmail();
				String subject = Util
						.loadParametersValue("MSJ.CANCELACION.SOLICITUD.USER");

				SendEmail mail = new SendEmail(toUser, fromUser, server,
						"false", subject, motivoCancelacion);

				if (mail.SendMessage().equals("SUCCESS"))
					log.info("Mensaje enviado exitosamente");
				else {
					log.info("Eror Enviando el mensaje");
					throw new GWorkException(Util
							.loadErrorMessageValue("NOTIFICACION.ERROR"));
				}
			}

			if (requests.getUsersByUsrCodigo().getUsrEmail() != null) {
				String server = Util.loadParametersValue("MAIL.HOST");
				String fromUser = rolls.getRlsMail();
				String toUser = requests.getUsersByUsrCodigo().getUsrEmail();
				String subject = Util
						.loadParametersValue("MSJ.CANCELACION.SOLICITUD.USER");

				SendEmail mail = new SendEmail(toUser, fromUser, server,
						"false", subject, motivoCancelacion);

				if (mail.SendMessage().equals("SUCCESS"))
					log.info("Mensaje enviado exitosamente");
				else {
					log.info("Eror Enviando el mensaje");
					throw new GWorkException(Util
							.loadErrorMessageValue("NOTIFICACION.ERROR"));
				}
			}
		} catch (Exception e) {
			throw new GWorkException(Util
					.loadErrorMessageValue("REGISTRO.NOEXITOSO")
					+ " " + e.getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see geniar.siscar.logic.vehicle.services.RequestService#findByPropertyStatesCreate(java.lang.String)
	 */
	public List<Requests> findByPropertyStatesCreate(String carne)
			throws GWorkException {

		EntityManagerHelper.getEntityManager().clear();
		List<Requests> requestStateCreate = new RequestConsultDAO()
				.findByPropertyStatesCreate(carne);
		if (requestStateCreate == null || requestStateCreate.isEmpty())
			throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));

		return requestStateCreate;
	}

	/* (non-Javadoc)
	 * @see geniar.siscar.logic.vehicle.services.RequestService#enviarSolicitud(geniar.siscar.model.Requests)
	 */
	public void enviarSolicitud(Requests requests) throws GWorkException {

		validarSession();

		requests.setRequestsStates(new RequestsStatesDAO().findById(2L));
		EntityManagerHelper.beginTransaction();
		new RequestsDAO().update(requests);
		EntityManagerHelper.commit();

	}

	/* (non-Javadoc)
	 * @see geniar.siscar.logic.vehicle.services.RequestService#findByUserStateCreate(java.lang.String, java.lang.String)
	 */
	public List<Requests> findByUserStateCreate(String carne, String carneFiltro)
			throws GWorkException {

		EntityManagerHelper.getEntityManager().clear();
		List<Requests> listRequestCreated = new RequestConsultDAO()
				.findByUserStateCreate(carne, carneFiltro);
		if (listRequestCreated == null || listRequestCreated.isEmpty())
			throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));

		return listRequestCreated;
	}

	/* (non-Javadoc)
	 * @see geniar.siscar.logic.vehicle.services.RequestService#findByDateStatesCreate(java.util.Date, java.util.Date)
	 */
	public List<Requests> findByDateStatesCreate(Date fechaDesde,
			Date fechaHasta) throws GWorkException {

		EntityManagerHelper.getEntityManager().clear();
		List<Requests> listRequestCreated = new RequestConsultDAO()
				.findByDateStatesCreate(fechaDesde, fechaHasta);
		if (listRequestCreated == null || listRequestCreated.isEmpty())
			throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));

		return listRequestCreated;
	}

	/* (non-Javadoc)
	 * @see geniar.siscar.logic.vehicle.services.RequestService#consultarRequest(java.lang.Long)
	 */
	public Requests consultarRequest(Long idRequest) throws GWorkException {

		Requests requests = new RequestConsultDAO().consultarRequest(idRequest);
		if (requests == null)
			throw new GWorkException("Solicitud no registrada");

		return requests;
	}

	/* (non-Javadoc)
	 * @see geniar.siscar.logic.vehicle.services.RequestService#modificarSolicitud(java.lang.Long, java.lang.Long, java.lang.Long, java.lang.String, java.lang.Long, java.util.Date, java.util.Date, java.lang.Long, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.util.List, java.lang.String, java.lang.Long, java.lang.Long, java.lang.String)
	 */
	public void modificarSolicitud(Long idRequests, Long claseSolicitud,
			Long tipoSolicitud, String placaActual, Long tipoAsignacion,
			Date fechaDesde, Date fechaHasta, Long tipoVehiculo,
			String descripcion, String carneAsignatario, String nombreAsig,
			String emailAsig, String carneAsistente, String nombreAsis,
			String emailAsis,
			List<CostsCentersVehicles> listCostsCentersRequests, String nit,
			Long state, Long idZones, String carneLogin) throws GWorkException {

		try {

			Requests requests = new RequestsDAO().findById(idRequests);
			LegateesTypes legateesTypes = null;
			if (requests == null)
				throw new GWorkException(Util
						.loadErrorMessageValue("SOLICITUD.EXISTEN"));
			RequestsClasses requestsClasses = new RequestsClassesDAO()
					.findById(claseSolicitud);
			if (requestsClasses == null)
				throw new GWorkException(Util
						.loadErrorMessageValue("CLASESOLICITUD"));

			RequestsTypes requestsTypes = new RequestsTypesDAO()
					.findById(tipoSolicitud);
			if (requestsTypes == null)
				throw new GWorkException(Util
						.loadErrorMessageValue("TIPOSSOLICITUD"));
			VehiclesTypes vehiclesTypes = new VehiclesTypesDAO()
					.findById(tipoVehiculo);
			if (vehiclesTypes == null)
				throw new GWorkException(Util.loadErrorMessageValue("TIPO.VHC"));

			if (tipoAsignacion != null)
				legateesTypes = new LegateesTypesDAO().findById(tipoAsignacion);

			if (legateesTypes == null && requestsClasses.getRqcCodigo() == 1L)
				throw new GWorkException(Util
						.loadErrorMessageValue("TIPOASIGNACION"));

			if (fechaDesde == null)
				throw new GWorkException(Util
						.loadErrorMessageValue("FECHAINICIO"));
			if (fechaHasta == null)
				throw new GWorkException(Util.loadErrorMessageValue("FECHAFIN"));

			if (fechaHasta == null)
				throw new GWorkException(Util.loadErrorMessageValue("FECHAFIN"));

			/**
			 * if (ManipulacionFechas.compararFechasSinHoras(fechaDesde, new
			 * Date())) throw new
			 * GWorkException(Util.loadErrorMessageValue("FECHAINICIO.INVALIDA"));
			 * 
			 * if (ManipulacionFechas.compararFechasSinHoras(fechaHasta,new
			 * Date())) throw new
			 * GWorkException(Util.loadErrorMessageValue("FECHAFIN.INVALIDA"));
			 */
			if (fechaHasta.compareTo(fechaDesde) == -1)
				throw new GWorkException(Util
						.loadErrorMessageValue("FCH_INI_FCH_FIN"));

			if (tipoVehiculo == null)
				throw new GWorkException(Util
						.loadErrorMessageValue("TIPOVEHICULO"));
			if (descripcion == null || descripcion.trim().length() == 0)
				throw new GWorkException(Util
						.loadErrorMessageValue("DESCRIPCION"));

			if (claseSolicitud == 2L
					|| (legateesTypes != null && (legateesTypes.getLgtCodigo()
							.longValue() == ParametersUtil.LGT_OF.longValue()
							|| legateesTypes.getLgtCodigo().longValue() == ParametersUtil.LGT_OFS.longValue()
							|| legateesTypes.getLgtCodigo().longValue() == ParametersUtil.LGT_PROGRAMAS.longValue() || legateesTypes
							.getLgtCodigo().longValue() == ParametersUtil.LGT_PROYECTO.longValue()))) {

				if (idZones == null
						|| idZones.longValue() == 0
						|| idZones.longValue() == -1L
						&& (legateesTypes != null && (legateesTypes
								.getLgtCodigo().longValue() == ParametersUtil.LGT_OF.longValue() 
								|| legateesTypes.getLgtCodigo().longValue() == ParametersUtil.LGT_OFS.longValue())))
					throw new GWorkException(Util
							.loadErrorMessageValue("ZONES.SEL"));

				if (carneAsignatario.trim().length() == 0)
					throw new GWorkException(Util
							.loadErrorMessageValue("CARNET_ASIGNATARIO"));
				if (carneAsistente.trim().length() == 0)
					throw new GWorkException(Util
							.loadErrorMessageValue("CARNET_ASISTENTE"));

				if ((claseSolicitud.longValue() != 2L)
						&& (listCostsCentersRequests == null
								|| listCostsCentersRequests.isEmpty() || listCostsCentersRequests
								.size() == 0))
					throw new GWorkException(Util
							.loadErrorMessageValue("PORCENTAJE.ERROR"));

				if (requestsTypes.getRqyCodigo().longValue() == REMPLAZO
						|| requestsTypes.getRqyCodigo().longValue() == EXTENSION) {
					if (placaActual == null || placaActual.trim().length() == 0)
						throw new GWorkException(Util
								.loadErrorMessageValue("PLACA"));
					if (placaActual.trim().length() <= 1)
						throw new GWorkException(Util
								.loadErrorMessageValue("PLACA.MINIMO"));
					IVehiclesDAO vehiclesDAO = new VehiclesDAO();
					if (vehiclesDAO.findByVhcPlacaDiplomatica(placaActual) == null
							|| vehiclesDAO.findByVhcPlacaDiplomatica(
									placaActual).size() == 0
							|| vehiclesDAO.findByVhcPlacaDiplomatica(
									placaActual).isEmpty())
						throw new GWorkException(Util
								.loadErrorMessageValue("PLACA.EXISTEN"));
				}

				Users usersAsig = null;
				Users usersAsis = null;
				if (new UsersDAO().findByUsrIdentificacion(carneAsignatario) == null
						|| new UsersDAO().findByUsrIdentificacion(
								carneAsignatario).isEmpty()
						|| new UsersDAO().findByUsrIdentificacion(
								carneAsignatario).size() == 0) {

					usersAsig = new Users();
					usersAsig.setUsrNombre(nombreAsig);
					usersAsig.setUsrEmail(emailAsig);
					usersAsig.setUsrIdentificacion(carneAsignatario);
					usersAsig.setRolls(new RollsDAO().findById(4L));
					EntityManagerHelper.beginTransaction();
					new UsersDAO().save(usersAsig);
					EntityManagerHelper.commit();
				} else if (new UsersDAO()
						.findByUsrIdentificacion(carneAsignatario) != null) {
					usersAsig = new UsersDAO().findByUsrIdentificacion(
							carneAsignatario).get(0);
				}

				if (new UsersDAO().findByUsrIdentificacion(carneAsistente) == null
						|| new UsersDAO().findByUsrIdentificacion(
								carneAsistente).isEmpty()
						|| new UsersDAO().findByUsrIdentificacion(
								carneAsistente).size() == 0) {

					usersAsis = new Users();
					usersAsis.setUsrNombre(nombreAsis);
					usersAsis.setUsrEmail(emailAsis);
					usersAsis.setUsrIdentificacion(carneAsistente);
					usersAsis.setRolls(new RollsDAO().findById(4L));
					EntityManagerHelper.beginTransaction();
					new UsersDAO().save(usersAsis);
					EntityManagerHelper.commit();

				} else if (new UsersDAO()
						.findByUsrIdentificacion(carneAsistente) != null
						|| !new UsersDAO().findByUsrIdentificacion(
								carneAsistente).isEmpty()) {
					usersAsis = new UsersDAO().findByUsrIdentificacion(
							carneAsistente).get(0);
				}

				requests.setRequestsClasses(requestsClasses);
				RequestsStates requestsStates = new RequestsStatesDAO()
						.findById(state);
				requests.setRequestsStates(requestsStates);
				requests.setRqsDescripcion(descripcion);
				requests.setRqsCarnetEmpleado(carneAsistente);
				requests.setRqsCarnetAsignatario(carneAsignatario);
				requests.setRqsFechaSolicitud(new Date());
				requests.setRqsFechaInicial(fechaDesde);
				requests.setRqsFechaFinal(fechaHasta);
				requests.setVehiclesTypes(new VehiclesTypesDAO()
						.findById(tipoVehiculo));
				requests.setLegateesTypes(legateesTypes);
				requests.setRequestsTypes(requestsTypes);
				requests.setRqsCarneLogin(carneLogin);

				if (idZones != null)
					requests.setZones(new ZonesDAO().findById(idZones));

				if (placaActual != null && placaActual.trim().length() > 0)
					requests.setRqsPlacaExtensionRemplazo(placaActual);

				EntityManagerHelper.beginTransaction();
				requests.setUsersByRqsUser(usersAsig);
				requests.setUsersByUsrCodigo(usersAsis);
				new RequestsDAO().update(requests);
				EntityManagerHelper.commit();

				eliminarCentroCostos(idRequests);
				if (listCostsCentersRequests != null
						&& listCostsCentersRequests.size() > 0) {
					for (CostsCentersVehicles costsCentersVehicles : listCostsCentersRequests) {
						CostsCentersVehicles centersVehicles = new CostsCentersVehicles();
						CostsCenters costsCenters;

						String codigoCC = costsCentersVehicles
								.getCostsCenters().getCocNumero();

						ICostsCentersDAO costsCentersDAO = new CostsCentersDAO();
						if (costsCentersDAO.findByCocNumero(codigoCC) == null
								|| costsCentersDAO.findByCocNumero(codigoCC)
										.isEmpty()
								|| costsCentersDAO.findByCocNumero(codigoCC)
										.size() == 0) {
							costsCenters = new CostsCenters();

							costsCenters.setCocNumero(costsCentersVehicles
									.getCostsCenters().getCocNumero());

							centersVehicles
									.setCcrPorcentaje(costsCentersVehicles
											.getCcrPorcentaje());
							centersVehicles.setCcrFechaInicio(fechaDesde);
							centersVehicles.setCcrFechaFin(fechaHasta);
							centersVehicles.setRequests(requests);
							centersVehicles.setCcrValor(0L);
							centersVehicles.setCostsCenters(costsCenters);

							EntityManagerHelper.beginTransaction();
							new CostsCentersDAO().save(costsCenters);
							new CostsCentersVehiclesDAO().save(centersVehicles);
							EntityManagerHelper.commit();

						} else if (costsCentersDAO.findByCocNumero(codigoCC) != null
								|| !costsCentersDAO.findByCocNumero(codigoCC)
										.isEmpty()
								|| costsCentersDAO.findByCocNumero(codigoCC)
										.size() > 0) {

							costsCenters = costsCentersDAO.findByCocNumero(
									codigoCC).get(0);

							centersVehicles
									.setCcrPorcentaje(costsCentersVehicles
											.getCcrPorcentaje());
							centersVehicles.setCcrFechaInicio(fechaDesde);
							centersVehicles.setCcrFechaFin(fechaHasta);
							centersVehicles.setRequests(requests);
							centersVehicles.setCcrValor(0L);
							centersVehicles.setCostsCenters(costsCenters);

							EntityManagerHelper.beginTransaction();
							new CostsCentersVehiclesDAO().save(centersVehicles);
							EntityManagerHelper.commit();

						}

					}
				}

			} else if (legateesTypes != null
					&& (legateesTypes.getLgtCodigo().longValue() == ParametersUtil.LGT_PERSONAL.longValue())) {

				if (carneAsignatario == null
						|| carneAsignatario.trim().length() == 0)
					throw new GWorkException(Util
							.loadErrorMessageValue("CARNET_ASIGNATARIO"));
				if (carneAsistente == null
						|| carneAsistente.trim().length() == 0)
					throw new GWorkException(Util
							.loadErrorMessageValue("CARNET_ASISTENTE"));

				if (requestsTypes.getRqyCodigo().longValue() == REMPLAZO
						|| requestsTypes.getRqyCodigo().longValue() == EXTENSION) {
					if (placaActual.trim().length() == 0)
						throw new GWorkException(Util
								.loadErrorMessageValue("PLACA"));
					if (placaActual.trim().length() <= 1)
						throw new GWorkException(Util
								.loadErrorMessageValue("PLACA.MINIMO"));
					IVehiclesDAO vehiclesDAO = new VehiclesDAO();
					if (vehiclesDAO.findByVhcPlacaDiplomatica(placaActual) == null
							|| vehiclesDAO.findByVhcPlacaDiplomatica(
									placaActual).size() == 0
							|| vehiclesDAO.findByVhcPlacaDiplomatica(
									placaActual).isEmpty())
						throw new GWorkException(Util
								.loadErrorMessageValue("PLACA.EXISTEN"));
				}

				Users usersAsig = null;
				Users usersAsis = null;
				if (new UsersDAO().findByUsrIdentificacion(carneAsignatario) == null
						|| new UsersDAO().findByUsrIdentificacion(
								carneAsignatario).isEmpty()
						|| new UsersDAO().findByUsrIdentificacion(
								carneAsignatario).size() == 0) {

					usersAsig = new Users();
					usersAsig.setUsrNombre(nombreAsig);
					usersAsig.setUsrEmail(emailAsig);
					usersAsig.setUsrIdentificacion(carneAsignatario);
					usersAsig.setRolls(new RollsDAO().findById(4L));
					EntityManagerHelper.beginTransaction();
					new UsersDAO().save(usersAsig);
					EntityManagerHelper.commit();
				} else if (new UsersDAO()
						.findByUsrIdentificacion(carneAsignatario) != null) {
					usersAsig = new UsersDAO().findByUsrIdentificacion(
							carneAsignatario).get(0);
				}

				if (new UsersDAO().findByUsrIdentificacion(carneAsistente) == null
						|| new UsersDAO().findByUsrIdentificacion(
								carneAsistente).isEmpty()
						|| new UsersDAO().findByUsrIdentificacion(
								carneAsistente).size() == 0) {

					usersAsis = new Users();
					usersAsis.setUsrNombre(nombreAsis);
					usersAsis.setUsrEmail(emailAsis);
					usersAsis.setUsrIdentificacion(carneAsistente);
					usersAsis.setRolls(new RollsDAO().findById(4L));
					EntityManagerHelper.beginTransaction();
					new UsersDAO().save(usersAsis);
					EntityManagerHelper.commit();

				} else if (new UsersDAO()
						.findByUsrIdentificacion(carneAsistente) != null
						|| !new UsersDAO().findByUsrIdentificacion(
								carneAsistente).isEmpty()) {
					usersAsis = new UsersDAO().findByUsrIdentificacion(
							carneAsistente).get(0);
				}

				requests.setRequestsClasses(requestsClasses);
				RequestsStates requestsStates = new RequestsStatesDAO()
						.findById(state);
				requests.setRequestsStates(requestsStates);
				requests.setRqsDescripcion(descripcion);
				requests.setRqsCarnetEmpleado(carneAsistente);
				requests.setRqsCarnetAsignatario(carneAsignatario);
				requests.setRqsFechaSolicitud(new Date());
				requests.setRqsFechaInicial(fechaDesde);
				requests.setRqsFechaFinal(fechaHasta);
				requests.setVehiclesTypes(new VehiclesTypesDAO()
						.findById(tipoVehiculo));
				requests.setLegateesTypes(legateesTypes);
				requests.setRequestsTypes(requestsTypes);
				requests.setRqsCarneLogin(carneLogin);
				if (placaActual != null && placaActual.trim().length() > 0)
					requests.setRqsPlacaExtensionRemplazo(placaActual);

				EntityManagerHelper.beginTransaction();
				requests.setUsersByRqsUser(usersAsig);
				requests.setUsersByUsrCodigo(usersAsis);
				new RequestsDAO().update(requests);
				EntityManagerHelper.commit();

			} else if (claseSolicitud == 3L
					|| (legateesTypes != null && legateesTypes.getLgtCodigo()
							.longValue() == ParametersUtil.LGT_CONVENIO.longValue())) {
				if (nit == null || nit.trim().length() == 0)
					throw new GWorkException(Util.loadErrorMessageValue("NIT"));

				if (requestsTypes.getRqyCodigo() == REMPLAZO
						|| requestsTypes.getRqyCodigo().longValue() == EXTENSION.longValue()) {
					if (placaActual.trim().length() == 0)
						throw new GWorkException(Util
								.loadErrorMessageValue("PLACA"));
					if (placaActual.trim().length() <= 1)
						throw new GWorkException(Util
								.loadErrorMessageValue("PLACA.MINIMO"));
					IVehiclesDAO vehiclesDAO = new VehiclesDAO();
					if (vehiclesDAO.findByVhcPlacaDiplomatica(placaActual) == null
							|| vehiclesDAO.findByVhcPlacaDiplomatica(
									placaActual).size() == 0
							|| vehiclesDAO.findByVhcPlacaDiplomatica(
									placaActual).isEmpty())
						throw new GWorkException(Util
								.loadErrorMessageValue("PLACA.EXISTEN"));
				}

				Users usertercero = null;

				if (new UsersDAO().findByUsrIdentificacion(carneAsignatario) == null
						|| new UsersDAO().findByUsrIdentificacion(
								carneAsignatario).isEmpty()
						|| new UsersDAO().findByUsrIdentificacion(
								carneAsignatario).size() == 0) {

					usertercero = new Users();
					usertercero.setUsrNombre(nombreAsig);
					usertercero.setUsrEmail(emailAsig);
					usertercero.setUsrIdentificacion(carneAsignatario);
					usertercero.setRolls(new RollsDAO().findById(4L));
					EntityManagerHelper.beginTransaction();
					new UsersDAO().save(usertercero);
					EntityManagerHelper.commit();
				} else if (new UsersDAO()
						.findByUsrIdentificacion(carneAsignatario) != null
						|| !new UsersDAO().findByUsrIdentificacion(
								carneAsignatario).isEmpty()) {
					usertercero = new UsersDAO().findByUsrIdentificacion(
							carneAsignatario).get(0);
				}

				requests.setRequestsClasses(requestsClasses);
				RequestsStates requestsStates = new RequestsStatesDAO()
						.findById(state);
				requests.setRequestsStates(requestsStates);
				requests.setRqsDescripcion(descripcion);
				requests.setRqsFechaSolicitud(new Date());
				requests.setRqsFechaInicial(fechaDesde);
				requests.setRqsCarnetAsignatario(carneAsignatario);
				requests.setRqsFechaFinal(fechaHasta);
				requests.setVehiclesTypes(new VehiclesTypesDAO().findById(tipoVehiculo));
				
				legateesTypes = new LegateesTypesDAO().findById(ParametersUtil.LGT_TERCEROS);
				
				requests.setLegateesTypes(legateesTypes);
				requests.setRequestsTypes(requestsTypes);
				requests.setRqsCarnetAsignatario(nit);
				requests.setRqsCarneLogin(carneLogin);
				if (placaActual != null && placaActual.trim().length() > 0)
					requests.setRqsPlacaExtensionRemplazo(placaActual);
				EntityManagerHelper.beginTransaction();
				requests.setUsersByRqsUser(usertercero);
				new RequestsDAO().update(requests);
				EntityManagerHelper.commit();
			}

		} catch (Exception e) {
			throw new GWorkException(e.getMessage());
		}
	}

	/**
	 * Eliminar centro costos.
	 *
	 * @param idRequest the id request
	 */
	private static void eliminarCentroCostos(Long idRequest) {

		try {
			if (!EntityManagerHelper.getEntityManager().getTransaction()
					.isActive())
				EntityManagerHelper.getEntityManager().getTransaction().begin();

			final String queryString = "delete from CostsCentersVehicles model where model.requests.rqsCodigo= :idRequest";
			javax.persistence.Query query = EntityManagerHelper
					.getEntityManager().createQuery(queryString);
			query.setParameter("idRequest", idRequest);
			query.executeUpdate();
			EntityManagerHelper.getEntityManager().getTransaction().commit();
			EntityManagerHelper.getEntityManager().clear();

		} catch (RuntimeException re) {
			re.printStackTrace();
		}

	}

	/**
	 * Eliminar asignacion.
	 *
	 * @param idRequest the id request
	 */
	private static void eliminarAsignacion(Long idRequest) {

		try {
			if (!EntityManagerHelper.getEntityManager().getTransaction()
					.isActive())
				EntityManagerHelper.getEntityManager().getTransaction().begin();

			final String queryString = "delete from VehiclesAssignation model where model.requests.rqsCodigo= :idRequest";
			javax.persistence.Query query = EntityManagerHelper
					.getEntityManager().createQuery(queryString);
			query.setParameter("idRequest", idRequest);
			query.executeUpdate();
			EntityManagerHelper.getEntityManager().getTransaction().commit();
			EntityManagerHelper.getEntityManager().clear();

		} catch (RuntimeException re) {
			re.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see geniar.siscar.logic.vehicle.services.RequestService#findByUserRequest(java.lang.String)
	 */
	public List<Requests> findByUserRequest(String carne) throws GWorkException {

		EntityManagerHelper.getEntityManager().clear();
		List<Requests> listRequestByUser = new RequestConsultDAO()
				.findByUserRequest(carne);
		if (listRequestByUser == null || listRequestByUser.isEmpty())
			throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));

		return listRequestByUser;
	}

	/* (non-Javadoc)
	 * @see geniar.siscar.logic.vehicle.services.RequestService#assignationByPlaca(java.lang.String)
	 */
	public List<VehiclesAssignation> assignationByPlaca(String placa)
			throws GWorkException {

		RequestConsultDAO requestConsultDAO = new RequestConsultDAO();
		List<VehiclesAssignation> listVehiclesAssignation = requestConsultDAO
				.assignationByPlaca(placa);

		if (listVehiclesAssignation == null
				|| listVehiclesAssignation.size() == 0
				|| listVehiclesAssignation.isEmpty())
			throw new GWorkException(Util
					.loadErrorMessageValue("SOLICITUD.ASIGNACION"));

		return listVehiclesAssignation;
	}

	/* (non-Javadoc)
	 * @see geniar.siscar.logic.vehicle.services.RequestService#consultarSolicitudByFechas(java.util.Date, java.util.Date)
	 */
	public List<Requests> consultarSolicitudByFechas(Date fechaDesde,
			Date fechaHasta) throws GWorkException {
		List<Requests> listRequestFechas = new RequestConsultDAO()
				.consultarSolicitudByFechas(fechaDesde, fechaHasta);

		if (listRequestFechas == null || listRequestFechas.isEmpty()
				|| listRequestFechas.size() == 0)
			throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));
		return listRequestFechas;
	}

	/* (non-Javadoc)
	 * @see geniar.siscar.logic.vehicle.services.RequestService#consultarSolicitudByFechasCarne(java.lang.String, java.util.Date, java.util.Date)
	 */
	public List<Requests> consultarSolicitudByFechasCarne(String carne,
			Date fechaDesde, Date fechaHasta) throws GWorkException {

		List<Requests> listRequestFechasCarne = new RequestConsultDAO()
				.consultarSolicitudByFechasCarne(carne, fechaDesde, fechaHasta);

		if (listRequestFechasCarne == null || listRequestFechasCarne.isEmpty()
				|| listRequestFechasCarne.size() == 0)
			throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));
		return listRequestFechasCarne;

	}

	/* (non-Javadoc)
	 * @see geniar.siscar.logic.vehicle.services.RequestService#consultarSolicitudByFechasCarneUser(java.lang.String, java.lang.String, java.util.Date, java.util.Date)
	 */
	public List<Requests> consultarSolicitudByFechasCarneUser(String carne,
			String carneLogin, Date fechaDesde, Date fechaHasta)
			throws GWorkException {

		List<Requests> listRequestFechasCarne = new RequestConsultDAO()
				.consultarSolicitudByFechasCarneUser(carne, carneLogin,
						fechaDesde, fechaHasta);

		if (listRequestFechasCarne == null || listRequestFechasCarne.isEmpty()
				|| listRequestFechasCarne.size() == 0)
			throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));
		return listRequestFechasCarne;

	}

	/* (non-Javadoc)
	 * @see geniar.siscar.logic.vehicle.services.RequestService#consultarSolicitudByFechasUser(java.util.Date, java.util.Date, java.lang.String)
	 */
	public List<Requests> consultarSolicitudByFechasUser(Date fechaDesde,
			Date fechaHasta, String carneLogin) throws GWorkException {

		List<Requests> listRequestFechasCarne = new RequestConsultDAO()
				.consultarSolicitudByFechasUser(fechaDesde, fechaHasta,
						carneLogin);
		if (listRequestFechasCarne == null || listRequestFechasCarne.isEmpty()
				|| listRequestFechasCarne.size() == 0)
			throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));
		return listRequestFechasCarne;
	}

	/**
	 * Consultar asignacion by rqs.
	 *
	 * @param rqsCodigo the rqs codigo
	 * @return the vehicles assignation
	 */
	public VehiclesAssignation consultarAsignacionByRqs(Long rqsCodigo) {
		final String queryString = "select model from VehiclesAssignation model "
				+ "where model.requests.rqsCodigo= :rqsCodigo";

		Query query = EntityManagerHelper.getEntityManager().createQuery(
				queryString);
		query.setParameter("rqsCodigo", rqsCodigo);

		if (query.getResultList() != null && query.getResultList().size() > 0)
			return (VehiclesAssignation) query.getSingleResult();
		else
			return null;
	}

	/**
	 * Consultar cc by vha.
	 *
	 * @param vhaCodigo the vha codigo
	 * @return the list
	 */
	@SuppressWarnings("unchecked")
	public List<CostsCentersVehicles> consultarCCByVha(Long vhaCodigo) {
		final String queryString = "select model from CostsCentersVehicles model "
				+ "where model.vehiclesAssignation.vhaCodigo= :vhaCodigo";

		Query query = EntityManagerHelper.getEntityManager().createQuery(
				queryString);
		query.setParameter("vhaCodigo", vhaCodigo);

		if (query.getResultList() != null && query.getResultList().size() > 0)
			return query.getResultList();
		else
			return null;
	}

	/* (non-Javadoc)
	 * @see geniar.siscar.logic.vehicle.services.RequestService#findByPropertyStatesCumplidas(java.lang.String)
	 */
	public List<Requests> findByPropertyStatesCumplidas(String carne)
			throws GWorkException {
		List<Requests> listRequestCumplida = new RequestConsultDAO()
				.findByPropertyStatesCumplidas(carne);

		if (listRequestCumplida == null || listRequestCumplida.size() == 0
				|| listRequestCumplida.isEmpty())
			throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));
		return listRequestCumplida;
	}

	/* (non-Javadoc)
	 * @see geniar.siscar.logic.vehicle.services.RequestService#findRequestByVHA(java.lang.Long)
	 */
	public VehiclesAssignation findRequestByVHA(Long idRequest)
			throws GWorkException {
		VehiclesAssignation vehiclesAssignation = null;

		vehiclesAssignation = new RequestConsultDAO()
				.findRequestByVHA(idRequest);
		return vehiclesAssignation;
	}
}