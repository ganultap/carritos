package geniar.siscar.view.vehicle;

import geniar.siscar.logic.allocation.services.ServiceAllocation;
import geniar.siscar.logic.consultas.SearchVehicles;
import geniar.siscar.logic.vehicle.services.VehicleService;
import geniar.siscar.model.Availability;
import geniar.siscar.model.CostsCentersVehicles;
import geniar.siscar.model.Requests;
import geniar.siscar.model.Vehicles;
import geniar.siscar.model.VehiclesAssignation;
import geniar.siscar.util.FacesUtils;
import geniar.siscar.util.PopupMessagePage;
import geniar.siscar.util.Util;
import geniar.siscar.util.ViewOptionUtil;
import gwork.exception.GWorkException;
import geniar.siscar.consults.ConsultsService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.event.ActionEvent;
import com.icesoft.faces.component.ext.HtmlCommandButton;
import com.icesoft.faces.component.ext.HtmlOutputText;
import com.icesoft.faces.component.ext.RowSelectorEvent;

/**
 * The Class ReservePage.
 */
public class ReservePage {

	/** The code request. */
	private String codeRequest;
	
	/** The tipo solicitud. */
	private String tipoSolicitud;
	
	/** The estado solicitud. */
	private String estadoSolicitud;
	
	/** The clase solicitud. */
	private String claseSolicitud;
	
	/** The fecha solicitud. */
	private Date fechaSolicitud;
	
	/** The fecha desde. */
	private Date fechaDesde;
	
	/** The fecha hasta. */
	private Date fechaHasta;
	
	/** The descripcion. */
	private String descripcion;
	
	/** The tipo asignacion. */
	private String tipoAsignacion;
	
	/** The tipo vehiculo. */
	private String tipoVehiculo;
	
	/** The nombre asig. */
	private String nombreAsig;
	
	/** The nombre asignatario. */
	private String nombreAsignatario;
	
	/** The placa remplazo. */
	private String placaRemplazo;
	
	/** The id tipo vehiculo. */
	private Long idTipoVehiculo;
	
	/** The list center costs. */
	private List<CostsCentersVehicles> listCenterCosts;
	
	/** The activar confirmacion. */
	private boolean activarConfirmacion;
	
	/** The RESERVAR. */
	private static Integer RESERVAR = 1;
	
	/** The REASIGNARESERVA. */
	private static Integer REASIGNARESERVA = 2;
	
	/** The opc confirmacion. */
	private Integer opcConfirmacion;
	
	/** The btn cancelar. */
	private HtmlCommandButton btnCancelar;

	/** The placa. */
	private String placa;

	// Placa ha desplegar
	/** The sbplaca. */
	private String sbplaca;
	
	/** The sb tipo vehiculo. */
	private String sbTipoVehiculo;

	/** The id type. */
	private Long idType;
	
	/** The show availability. */
	public boolean showAvailability = false;
	
	/** The vehicle service. */
	private VehicleService vehicleService;
	
	/** The list vehicles. */
	private List<Vehicles> listVehicles;
	
	/** The list availability. */
	private List<Availability> listAvailability = new ArrayList<Availability>();
	
	/** The txt id vehicle. */
	private HtmlOutputText txtIdVehicle;
	
	/** The service allocation. */
	private ServiceAllocation serviceAllocation;
	
	/** The requests. */
	private Requests requests;
	
	/** The consults service. */
	private ConsultsService consultsService;

	/**
	 * Busqueda de Vehiculo desde la lista cargada con anterioridad.
	 *
	 * @param placa the placa
	 * @return the vehicles
	 */
	private Vehicles getvehicles(String placa) {
		for (Vehicles vehicles : listVehicles) {
			if (vehicles != null && vehicles.getVhcPlacaDiplomatica() != null) {
				if (vehicles.getVhcPlacaDiplomatica().equals(placa))
					return vehicles;
			}
		}
		return null;
	}

	/**
	 * Evento de boton para realizar la reserva.
	 *
	 * @param actionEvent the action event
	 */
	public void action_reservar(ActionEvent actionEvent) {
		try {

			if (sbplaca == null || sbplaca.trim().length() == 0)
				throw new GWorkException(Util
						.loadErrorMessageValue("PLACARESERVA"));
			if (placa != null && placa.trim().length() < 2
					&& placa.trim().length() >= 1)
				throw new GWorkException(
						"El tamaño del campo placa para la consulta no es el adecuado");

			if (requests != null
					&& requests.getRequestsStates().getRqtCodigo().longValue() == ViewOptionUtil.ENVIADA
					|| requests.getRequestsStates().getRqtCodigo().longValue() == ViewOptionUtil.RESERVADO) {

				// Valida si el centro de costo tiene disponibildad presupuestal
				// para los alquileres
				if (idTipoVehiculo != null
						&& listCenterCosts != null
						&& requests.getRequestsClasses() != null
						&& listCenterCosts.size() > 0
						&& requests.getRequestsClasses().getRqcCodigo()
								.longValue() == ViewOptionUtil.ALQUILER)
					consultsService.consultarDisponibilidadAlquilerCC(
							listCenterCosts, idTipoVehiculo, 
							requests.getRequestsClasses().getRqcCodigo(),
							requests.getLegateesTypes().getLgtCodigo());

				SearchVehicles.validarFechasReservaVehiculo(sbplaca,
						fechaDesde, fechaHasta);
				activarConfirmacion = true;
				setOpcConfirmacion(RESERVAR);
				mostrarMensaje(Util.loadMessageValue("MENSAJE.ALERTA"),
						activarConfirmacion);
			}
		} catch (GWorkException e) {
			mostrarMensaje(e.getMessage(), false);
		}
	}
	
	/**
	 * Reservar.
	 */
	public void reservar() {
		try {
			if (requests != null) {
				requests.setVehicles(getvehicles(sbplaca));
				serviceAllocation.reservarVehiculo(requests);
				limpiar();
				mostrarMensajeTrayAdmin(Util.loadMessageValue("EXITO"));
			}
		} catch (GWorkException e) {
			mostrarMensaje(e.getMessage(), false);
		}

	}

	/**
	 * Action_reasignar reservar.
	 *
	 * @param actionEvent the action event
	 */
	public void action_reasignarReservar(ActionEvent actionEvent) {
		try {

			if (sbplaca == null || sbplaca.trim().length() == 0)
				throw new GWorkException(Util
						.loadErrorMessageValue("PLACARESERVA"));

			if (placa != null && placa.trim().length() < 2
					&& placa.trim().length() >= 1)
				throw new GWorkException(
						"El tamaño del campo placa para la consulta no es el adecuado");

			if (requests != null
					&& requests.getRequestsStates().getRqtCodigo().longValue() == ViewOptionUtil.ENVIADA
					|| requests.getRequestsStates().getRqtCodigo().longValue() == ViewOptionUtil.RESERVADO) {

				// Valida si el centro de costo tiene disponibildad presupuestal
				// para los alquileres
				if (idTipoVehiculo != null
						&& listCenterCosts != null
						&& requests.getRequestsClasses() != null
						&& listCenterCosts.size() > 0
						&& requests.getRequestsClasses().getRqcCodigo()
								.longValue() == ViewOptionUtil.ALQUILER)
					consultsService.consultarDisponibilidadAlquilerCC(
							listCenterCosts, idTipoVehiculo, 
							requests.getRequestsClasses().getRqcCodigo(),
							requests.getLegateesTypes().getLgtCodigo());

				SearchVehicles.validarFechasReservaVehiculo(sbplaca,
						fechaDesde, fechaHasta);
				activarConfirmacion = true;
				setOpcConfirmacion(REASIGNARESERVA);
				mostrarMensaje(Util.loadMessageValue("MENSAJE.ALERTA"),
						activarConfirmacion);

			}

		} catch (Exception e) {
			mostrarMensaje(e.getMessage(), false);

		}

	}

	/**
	 * Reasignar reserva.
	 */
	public void reasignarReserva() {

		try {

			if (requests != null) {
				requests.setVehicles(getvehicles(sbplaca));
				serviceAllocation.reasignarReserva(requests);
				limpiar();
				mostrarMensajeTrayAdmin(Util.loadMessageValue("EXITO"));

			}

		} catch (GWorkException e) {
			mostrarMensaje(e.getMessage(), false);
		}

	}

	/**
	 * Metodo para la seleccion desde la tabla del vehiculo a reservar.
	 *
	 * @param event the event
	 */
	public void rowSelectionListenerVehicle(RowSelectorEvent event) {
		Long codigoVehicle = (Long) txtIdVehicle.getValue();
		for (Vehicles vehicles : listVehicles) {
			if (vehicles.getVhcCodigo().equals(codigoVehicle)) {
				sbplaca = vehicles.getVhcPlacaDiplomatica();
				sbTipoVehiculo = vehicles.getVehiclesTypes().getVhtNombre();
				idTipoVehiculo = vehicles.getVehiclesTypes().getVhtCodigo()
						.longValue();
				listAvailability.clear();
				showAvailability = false;
				return;
			}
		}
	}

	/**
	 * Action_close availability.
	 *
	 * @param event the event
	 */
	public void action_closeAvailability(ActionEvent event) {
		listAvailability.clear();
		showAvailability = false;
	}

	/**
	 * Action_consultar availability.
	 *
	 * @param event the event
	 */
	public void action_consultarAvailability(ActionEvent event) {
		try {
			boolean esValido = true;
			if (placa != null && placa.trim().length() != 0)
				esValido = Util.validarPlaca(placa);

			if (!esValido)
				throw new GWorkException(Util
						.loadErrorMessageValue("PLACA.INCORRECTO"));

			if (placa != null && placa.trim().length() != 0
					&& placa.trim().length() < 2)
				throw new GWorkException(Util
						.loadErrorMessageValue("PLACA.CONSULTA"));

			VehiclesAssignation vehiclesAssignation = null;
			if (placa != null && placa.trim().length() != 0 && idType == -1) {
				Vehicles vehicles = vehicleService
						.consultVehiclesByPlacaDiplomatica(placa.trim()
								.toUpperCase());
				listVehicles = new ArrayList<Vehicles>();
				listVehicles.add(vehicles);
			}
			if (placa != null && placa.trim().length() == 0 && idType == -1) {
				listVehicles = vehicleService.consultVehiclesByTypes(true);
			} else {
				if (idType != -1)
					listVehicles = vehicleService.consultVehiclesByType(idType);
			}
			if (listVehicles != null && listVehicles.size() > 0) {
				for (Vehicles vehicles : listVehicles) {
					Availability availability = new Availability();

					if (!vehicles.getVehiclesStates().getVhsCodigo().equals(
							new Long(Util.loadMessageValue("ESTADO.6")))
							&& !vehicles
									.getVehiclesStates()
									.getVhsCodigo()
									.equals(
											new Long(
													Util
															.loadMessageValue("ESTADO.1")))) {

						vehiclesAssignation = vehicleService
								.consultarAsignacionVehiculo(vehicles
										.getVhcCodigo());
						if (vehiclesAssignation != null) {
							availability.setFechaInicial(vehiclesAssignation
									.getVhaFechaInicio());
							availability.setFechaFinal(vehiclesAssignation
									.getVhaFechaTermina());
							if (vehiclesAssignation.getRequests()
									.getUsersByRqsUser() != null) {
								availability
										.setNombreAsignatario(vehiclesAssignation
												.getRequests()
												.getUsersByRqsUser()
												.getUsrNombre());
							}
						}

					}
					
					if(vehicles.getFuelsTypes() != null ){
						availability.setTipoCombustible(vehicles
								.getFuelsTypes().getFutNombre());
					}
					
					availability.setCodigoVehiculo(vehicles.getVhcCodigo());
					availability.setPlacaVehiculo(vehicles
							.getVhcPlacaDiplomatica());
					availability.setMarca(vehicles.getLines().getBrands()
							.getBrnNombre());
					availability.setLinea(vehicles.getLines().getLnsNombre());
					availability.setEstadoVehiculo(vehicles.getVehiclesStates()
							.getVhsNombre());
					VehiclesAssignation assignation = SearchVehicles
							.consultarAsignacion(vehicles.getVhcCodigo());
					if (assignation != null) {
						if (assignation.getRequests() != null
								&& !vehicles.getVehiclesStates().getVhsCodigo()
										.equals(6L)) {
							availability.setEstadoVehiculo(assignation
									.getRequests().getRequestsClasses()
									.getRqcNombre());
						}
					}

					availability.setTipoVehiculo(vehicles.getVehiclesTypes()
							.getVhtNombre());
					listAvailability.add(availability);
				}
				showAvailability = true;
			}
		} catch (GWorkException e) {
			placa = "";
			mostrarMensaje(e.getMessage(), false);
		}
	}

	/**
	 * Constructor.
	 */
	public ReservePage() {

		Requests requests = (Requests) FacesUtils.getSession().getAttribute(
				"solicitud");

		if (requests != null) {
			setCodeRequest(requests.getRqsCodigo().toString());
			setTipoSolicitud(requests.getRequestsTypes().getRqyNombre());
			if (requests.getLegateesTypes() != null)
				setTipoAsignacion(requests.getLegateesTypes().getLgtNombre());
			setEstadoSolicitud(requests.getRequestsStates().getRqtNombre());
			setTipoVehiculo(requests.getVehiclesTypes().getVhtNombre());
			setNombreAsig(requests.getRqsCarnetAsignatario());
			setFechaSolicitud(requests.getRqsFechaSolicitud());
			setFechaDesde(requests.getRqsFechaInicial());
			setFechaHasta(requests.getRqsFechaFinal());
			setDescripcion(requests.getRqsDescripcion());
			if (requests.getRqsPlacaExtensionRemplazo() != null)
				setPlacaRemplazo(requests.getRqsPlacaExtensionRemplazo());
			setClaseSolicitud(requests.getRequestsClasses().getRqcNombre());
			setNombreAsignatario(requests.getUsersByRqsUser().getUsrNombre());
			if (requests.getCostsCentersVehicleses() != null
					&& requests.getCostsCentersVehicleses().size() > 0)
				setListCenterCosts(new ArrayList<CostsCentersVehicles>(requests
						.getCostsCentersVehicleses()));
			// Peticion
			this.requests = requests;
		}

	}

	/**
	 * Checks if is show availability.
	 *
	 * @return true, if is show availability
	 */
	public boolean isShowAvailability() {
		return showAvailability;
	}

	/**
	 * Sets the show availability.
	 *
	 * @param showAvailability the new show availability
	 */
	public void setShowAvailability(boolean showAvailability) {
		this.showAvailability = showAvailability;
	}

	/**
	 * Gets the nombre asig.
	 *
	 * @return the nombre asig
	 */
	public String getNombreAsig() {
		return nombreAsig;
	}

	/**
	 * Sets the nombre asig.
	 *
	 * @param nombreAsig the new nombre asig
	 */
	public void setNombreAsig(String nombreAsig) {
		this.nombreAsig = nombreAsig;
	}

	/**
	 * Gets the tipo vehiculo.
	 *
	 * @return the tipo vehiculo
	 */
	public String getTipoVehiculo() {
		return tipoVehiculo;
	}

	/**
	 * Sets the tipo vehiculo.
	 *
	 * @param tipoVehiculo the new tipo vehiculo
	 */
	public void setTipoVehiculo(String tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}

	/**
	 * Gets the code request.
	 *
	 * @return the code request
	 */
	public String getCodeRequest() {
		return codeRequest;
	}

	/**
	 * Sets the code request.
	 *
	 * @param codeRequest the new code request
	 */
	public void setCodeRequest(String codeRequest) {
		this.codeRequest = codeRequest;
	}

	/**
	 * Gets the tipo solicitud.
	 *
	 * @return the tipo solicitud
	 */
	public String getTipoSolicitud() {
		return tipoSolicitud;
	}

	/**
	 * Sets the tipo solicitud.
	 *
	 * @param tipoSolicitud the new tipo solicitud
	 */
	public void setTipoSolicitud(String tipoSolicitud) {
		this.tipoSolicitud = tipoSolicitud;
	}

	/**
	 * Gets the estado solicitud.
	 *
	 * @return the estado solicitud
	 */
	public String getEstadoSolicitud() {
		return estadoSolicitud;
	}

	/**
	 * Sets the estado solicitud.
	 *
	 * @param estadoSolicitud the new estado solicitud
	 */
	public void setEstadoSolicitud(String estadoSolicitud) {
		this.estadoSolicitud = estadoSolicitud;
	}

	/**
	 * Gets the descripcion.
	 *
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Sets the descripcion.
	 *
	 * @param descripcion the new descripcion
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Gets the tipo asignacion.
	 *
	 * @return the tipo asignacion
	 */
	public String getTipoAsignacion() {
		return tipoAsignacion;
	}

	/**
	 * Sets the tipo asignacion.
	 *
	 * @param tipoAsignacion the new tipo asignacion
	 */
	public void setTipoAsignacion(String tipoAsignacion) {
		this.tipoAsignacion = tipoAsignacion;
	}

	/**
	 * Gets the vehicle service.
	 *
	 * @return the vehicle service
	 */
	public VehicleService getVehicleService() {
		return vehicleService;
	}

	/**
	 * Sets the vehicle service.
	 *
	 * @param vehicleService the new vehicle service
	 */
	public void setVehicleService(VehicleService vehicleService) {
		this.vehicleService = vehicleService;
	}

	/**
	 * Gets the list vehicles.
	 *
	 * @return the list vehicles
	 */
	public List<Vehicles> getListVehicles() {

		return listVehicles;
	}

	/**
	 * Sets the list vehicles.
	 *
	 * @param listVehicles the new list vehicles
	 */
	public void setListVehicles(List<Vehicles> listVehicles) {
		this.listVehicles = listVehicles;
	}

	/**
	 * Gets the id type.
	 *
	 * @return the id type
	 */
	public Long getIdType() {
		return idType;
	}

	/**
	 * Sets the id type.
	 *
	 * @param idType the new id type
	 */
	public void setIdType(Long idType) {
		this.idType = idType;
	}

	/**
	 * Gets the sb tipo vehiculo.
	 *
	 * @return the sb tipo vehiculo
	 */
	public String getSbTipoVehiculo() {
		return sbTipoVehiculo;
	}

	/**
	 * Sets the sb tipo vehiculo.
	 *
	 * @param sbTipoVehiculo the new sb tipo vehiculo
	 */
	public void setSbTipoVehiculo(String sbTipoVehiculo) {
		this.sbTipoVehiculo = sbTipoVehiculo;
	}

	/**
	 * Gets the txt id vehicle.
	 *
	 * @return the txt id vehicle
	 */
	public HtmlOutputText getTxtIdVehicle() {
		return txtIdVehicle;
	}

	/**
	 * Sets the txt id vehicle.
	 *
	 * @param txtIdVehicle the new txt id vehicle
	 */
	public void setTxtIdVehicle(HtmlOutputText txtIdVehicle) {
		this.txtIdVehicle = txtIdVehicle;
	}

	/**
	 * Gets the clase solicitud.
	 *
	 * @return the clase solicitud
	 */
	public String getClaseSolicitud() {
		return claseSolicitud;
	}

	/**
	 * Sets the clase solicitud.
	 *
	 * @param claseSolicitud the new clase solicitud
	 */
	public void setClaseSolicitud(String claseSolicitud) {
		this.claseSolicitud = claseSolicitud;
	}

	/**
	 * Gets the list availability.
	 *
	 * @return the list availability
	 */
	public List<Availability> getListAvailability() {
		return listAvailability;
	}

	/**
	 * Sets the list availability.
	 *
	 * @param listAvailability the new list availability
	 */
	public void setListAvailability(List<Availability> listAvailability) {
		this.listAvailability = listAvailability;
	}

	/**
	 * Gets the service allocation.
	 *
	 * @return the service allocation
	 */
	public ServiceAllocation getServiceAllocation() {
		return serviceAllocation;
	}

	/**
	 * Sets the service allocation.
	 *
	 * @param serviceAllocation the new service allocation
	 */
	public void setServiceAllocation(ServiceAllocation serviceAllocation) {
		this.serviceAllocation = serviceAllocation;
	}

	/**
	 * Gets the placa.
	 *
	 * @return the placa
	 */
	public String getPlaca() {
		return placa;
	}

	/**
	 * Sets the placa.
	 *
	 * @param placa the new placa
	 */
	public void setPlaca(String placa) {
		this.placa = placa;
	}

	/**
	 * Gets the sbplaca.
	 *
	 * @return the sbplaca
	 */
	public String getSbplaca() {
		return sbplaca;
	}

	/**
	 * Sets the sbplaca.
	 *
	 * @param sbplaca the new sbplaca
	 */
	public void setSbplaca(String sbplaca) {
		this.sbplaca = sbplaca;
	}

	/**
	 * Gets the fecha desde.
	 *
	 * @return the fecha desde
	 */
	public Date getFechaDesde() {
		return fechaDesde;
	}

	/**
	 * Sets the fecha desde.
	 *
	 * @param fechaDesde the new fecha desde
	 */
	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	/**
	 * Gets the fecha hasta.
	 *
	 * @return the fecha hasta
	 */
	public Date getFechaHasta() {
		return fechaHasta;
	}

	/**
	 * Sets the fecha hasta.
	 *
	 * @param fechaHasta the new fecha hasta
	 */
	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	/**
	 * Gets the fecha solicitud.
	 *
	 * @return the fecha solicitud
	 */
	public Date getFechaSolicitud() {
		return fechaSolicitud;
	}

	/**
	 * Sets the fecha solicitud.
	 *
	 * @param fechaSolicitud the new fecha solicitud
	 */
	public void setFechaSolicitud(Date fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}

	/**
	 * Gets the nombre asignatario.
	 *
	 * @return the nombre asignatario
	 */
	public String getNombreAsignatario() {
		return nombreAsignatario;
	}

	/**
	 * Sets the nombre asignatario.
	 *
	 * @param nombreAsignatario the new nombre asignatario
	 */
	public void setNombreAsignatario(String nombreAsignatario) {
		this.nombreAsignatario = nombreAsignatario;
	}

	/**
	 * Gets the id tipo vehiculo.
	 *
	 * @return the id tipo vehiculo
	 */
	public Long getIdTipoVehiculo() {
		return idTipoVehiculo;
	}

	/**
	 * Sets the id tipo vehiculo.
	 *
	 * @param idTipoVehiculo the new id tipo vehiculo
	 */
	public void setIdTipoVehiculo(Long idTipoVehiculo) {
		this.idTipoVehiculo = idTipoVehiculo;
	}

	/**
	 * Gets the consults service.
	 *
	 * @return the consults service
	 */
	public ConsultsService getConsultsService() {
		return consultsService;
	}

	/**
	 * Sets the consults service.
	 *
	 * @param consultsService the new consults service
	 */
	public void setConsultsService(ConsultsService consultsService) {
		this.consultsService = consultsService;
	}

	/**
	 * Limpiar.
	 */
	public void limpiar() {

		if (listAvailability != null)
			listAvailability.clear();

		if (listCenterCosts != null)
			listCenterCosts.clear();

		if (listVehicles != null)
			listVehicles.clear();

		opcConfirmacion = null;

	}

	/**
	 * Checks if is activar confirmacion.
	 *
	 * @return true, if is activar confirmacion
	 */
	public boolean isActivarConfirmacion() {
		return activarConfirmacion;
	}

	/**
	 * Sets the activar confirmacion.
	 *
	 * @param activarConfirmacion the new activar confirmacion
	 */
	public void setActivarConfirmacion(boolean activarConfirmacion) {
		this.activarConfirmacion = activarConfirmacion;
	}

	/**
	 * Gets the opc confirmacion.
	 *
	 * @return the opc confirmacion
	 */
	public Integer getOpcConfirmacion() {
		return opcConfirmacion;
	}

	/**
	 * Sets the opc confirmacion.
	 *
	 * @param opcConfirmacion the new opc confirmacion
	 */
	public void setOpcConfirmacion(Integer opcConfirmacion) {
		this.opcConfirmacion = opcConfirmacion;
	}

	/**
	 * Mostrar mensaje.
	 *
	 * @param mensaje the mensaje
	 * @param buttonCancel the button cancel
	 */
	public void mostrarMensaje(String mensaje, boolean buttonCancel) {
		PopupMessagePage message = (PopupMessagePage) FacesUtils
				.getManagedBean("popupMessagePage");
		if (message != null)
			message.showPopup(mensaje, buttonCancel);
	}

	/**
	 * Gets the btn cancelar.
	 *
	 * @return the btn cancelar
	 */
	public HtmlCommandButton getBtnCancelar() {
		return btnCancelar;
	}

	/**
	 * Sets the btn cancelar.
	 *
	 * @param btnCancelar the new btn cancelar
	 */
	public void setBtnCancelar(HtmlCommandButton btnCancelar) {
		this.btnCancelar = btnCancelar;
	}

	/**
	 * Mostrar mensaje tray admin.
	 *
	 * @param mensaje the mensaje
	 */
	public void mostrarMensajeTrayAdmin(String mensaje) {
		PopupMessagePage message = (PopupMessagePage) FacesUtils
				.getManagedBean("popupMessagePage");
		if (message != null)
			message.showBtnTrayAdmin(mensaje);
	}

	/**
	 * Validar valor.
	 *
	 * @param valor the valor
	 * @throws GWorkException the g work exception
	 */
	public void validarValor(String valor) throws GWorkException {
		boolean esValido = true;

		esValido = Util.validarCadenaCaracteresEspecialesNumLetrasGuion(valor);

		if (!esValido)
			throw new GWorkException(Util
					.loadErrorMessageValue("PLACA.CARACTER"));

	}

	/**
	 * Gets the placa remplazo.
	 *
	 * @return the placa remplazo
	 */
	public String getPlacaRemplazo() {
		return placaRemplazo;
	}

	/**
	 * Sets the placa remplazo.
	 *
	 * @param placaRemplazo the new placa remplazo
	 */
	public void setPlacaRemplazo(String placaRemplazo) {
		this.placaRemplazo = placaRemplazo;
	}

	/**
	 * Gets the list center costs.
	 *
	 * @return the list center costs
	 */
	public List<CostsCentersVehicles> getListCenterCosts() {
		return listCenterCosts;
	}

	/**
	 * Sets the list center costs.
	 *
	 * @param listCenterCosts the new list center costs
	 */
	public void setListCenterCosts(List<CostsCentersVehicles> listCenterCosts) {
		this.listCenterCosts = listCenterCosts;
	}
}