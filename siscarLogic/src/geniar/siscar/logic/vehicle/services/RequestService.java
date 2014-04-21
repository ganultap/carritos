package geniar.siscar.logic.vehicle.services;

import geniar.siscar.model.CostsCentersVehicles;
import geniar.siscar.model.LegateesTypes;
import geniar.siscar.model.Requests;
import geniar.siscar.model.RequestsClasses;
import geniar.siscar.model.RequestsStates;
import geniar.siscar.model.RequestsTypes;
import geniar.siscar.model.VehiclesAssignation;
import gwork.exception.GWorkException;

import java.util.Date;
import java.util.List;

public interface RequestService {
	public void vehicleRequest(Long claseSolicitud, Long tipoSolicitud,
			String placaActual, Long tipoAsignacion, Date fechaDesde,
			Date fechaHasta, Long TipoVehiculo, String Descripcion,
			String carneAsignatario, String nombreAsig, String emailAsig,
			String carneAsistente, String nombreAsis, String emailAsis,
			List<CostsCentersVehicles> listCostsCentersRequests, String nit,
			Long state, Long idZones, String carneLogin) throws GWorkException;

	public void modificarSolicitud(Long idRequests, Long claseSolicitud,
			Long tipoSolicitud, String placaActual, Long tipoAsignacion,
			Date fechaDesde, Date fechaHasta, Long TipoVehiculo,
			String Descripcion, String carneAsignatario, String nombreAsig,
			String emailAsig, String carneAsistente, String nombreAsis,
			String emailAsis,
			List<CostsCentersVehicles> listCostsCentersRequests, String nit,
			Long state, Long idZones, String carneLogin) throws GWorkException;

	public List<RequestsClasses> listRequestsClasses() throws GWorkException;

	public List<RequestsTypes> listRequestsTypes() throws GWorkException;

	public List<RequestsStates> listRequestsStates() throws GWorkException;

	public List<LegateesTypes> listLegateesTypes() throws GWorkException;

	public List<Requests> listRequests() throws GWorkException;

	public List<Requests> listRequestsState() throws GWorkException;

	public List<Requests> listRequestUser(String carnetAsig)
			throws GWorkException;

	public List<Requests> listRequestFilter(Long claseSolicitud,
			Long estadoSolicitud, Date fechaDesde, Date fechaHasta)
			throws GWorkException;

	public List<Requests> findByPropertyStatesCreate(String carne)
			throws GWorkException;

	public List<Requests> findByUserStateCreate(String carne, String carneFiltro)
			throws GWorkException;

	public void devolverSolicitud(Requests requests, String motivoDevolucion)
			throws GWorkException;

	public void cancelarSolicitudAdministrador(Long rqsCodigo,
			String motivoCancelacion) throws GWorkException;

	public void cancelarSolicitudUsuario(Requests requests,
			String motivoCancelacion) throws GWorkException;

	public List<Requests> findByDateStatesCreate(Date fechaDesde,
			Date fechaHasta) throws GWorkException;

	public void enviarSolicitud(Requests requests) throws GWorkException;

	public Requests consultarRequest(Long idRequest) throws GWorkException;

	public List<Requests> findByUserRequest(String carne) throws GWorkException;

	public List<VehiclesAssignation> assignationByPlaca(String placa)
			throws GWorkException;

	public List<Requests> consultarSolicitudByFechas(Date fechaDesde,
			Date fechaHasta) throws GWorkException;

	public List<Requests> consultarSolicitudByFechasCarne(String carne,
			Date fechaDesde, Date fechaHasta) throws GWorkException;

	public List<Requests> consultarSolicitudByFechasCarneUser(String carne,
			String carneLogin, Date fechaDesde, Date fechaHasta)
			throws GWorkException;

	public List<Requests> consultarSolicitudByFechasUser(Date fechaDesde,
			Date fechaHasta, String carneLogin) throws GWorkException;

	public List<Requests> findByPropertyStatesCumplidas(String carne)
			throws GWorkException;

	public VehiclesAssignation findRequestByVHA(Long idRequest)
			throws GWorkException;
}
