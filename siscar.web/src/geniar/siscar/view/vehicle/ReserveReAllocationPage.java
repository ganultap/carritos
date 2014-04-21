package geniar.siscar.view.vehicle;

import geniar.siscar.logic.allocation.services.ServiceAllocation;
import geniar.siscar.logic.vehicle.services.VehicleService;
import geniar.siscar.model.Availability;
import geniar.siscar.model.Requests;
import geniar.siscar.model.Vehicles;
import geniar.siscar.model.VehiclesAssignation;
import geniar.siscar.util.FacesUtils;
import geniar.siscar.util.PopupMessagePage;
import gwork.exception.GWorkException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.event.ActionEvent;

import com.icesoft.faces.component.ext.HtmlOutputText;
import com.icesoft.faces.component.ext.RowSelectorEvent;

public class ReserveReAllocationPage {
	
	private String codeRequest;
	private String tipoSolicitud;
	private String estadoSolicitud;
	private String claseSolicitud;
	private String fechaSolicitud;
	private String fechaDesde;
	private String fechaHasta;
	private String descripcion;
	private String tipoAsignacion;
	private String tipoVehiculo;
	private String nombreAsig;
    
	private String placa;
	
	//Placa ha desplegar 
	private String sbplaca;
	private String sbTipoVehiculo;

	private Long idType;
	public boolean showAvailability = false;

	private VehicleService vehicleService;

	private List<Vehicles>     listVehicles;
	private List<Availability> listAvailability = new ArrayList<Availability>();

	private HtmlOutputText txtIdVehicle;

	private ServiceAllocation serviceAllocation;

	private Requests requests;
	private PopupMessagePage messagePage;

	/**
	 * Busqueda de Vehiculo desde la lista cargada con anterioridad
	 * 
	 * @param placa
	 * @return
	 */
	private Vehicles getvehicles(String placa) {
		for (Vehicles vehicles : listVehicles) {
			if (vehicles.getVhcPlacaDiplomatica().equals(placa))
				return vehicles;
		}
		return null;
	}

	/**
	 * Evento de boton para realizar la reserva
	 * 
	 * @param event
	 */
	public void action_reservar(ActionEvent event) {
		try {
			if (sbplaca.trim().length() == 0)
				throw new GWorkException("Debe digitar la placa");
			requests.setVehicles(getvehicles(sbplaca));
			serviceAllocation.reservarVehiculo(requests);
			messagePage.showPopup("Resgistro guardado con exito",false);
		} catch (GWorkException e) {
			messagePage.setMensaje(e.getMessage());
		}
	}

	/**
	 * Metodo para la seleccion desde la tabla del vehiculo a reservar
	 * 
	 * @param event
	 */
	public void rowSelectionListenerVehicle(RowSelectorEvent event) {
		Long codigoVehicle = (Long) txtIdVehicle.getValue();
		for (Vehicles vehicles : listVehicles) {
			if (vehicles.getVhcCodigo().equals(codigoVehicle)) {
				sbplaca        = vehicles.getVhcPlacaDiplomatica();
				sbTipoVehiculo = vehicles.getVehiclesTypes().getVhtNombre();
				listAvailability.clear();
				showAvailability = false;
				return;
			}
		}
	}

	/**
	 * 
	 * @param event
	 */
	public void action_closeAvailability(ActionEvent event) {
		listAvailability.clear();
		showAvailability = false;
	}

	/**
	 * 
	 * @param event
	 */
	public void action_consultarAvailability(ActionEvent event) {
		try {
			
			if(idType<1 && idType==null && placa.trim().length()==0)
				throw new GWorkException("Debe seleccionar un tipo de vehiculo");
			
			if(placa.trim().length()!=0){
				Vehicles vehicles = vehicleService.consultarVehiculo(placa, "", "") ;	
				listVehicles  = new ArrayList<Vehicles>();
				listVehicles.add(vehicles);
			}else{
				listVehicles = vehicleService.consultVehiclesByType(idType);			
			}
			for (Vehicles vehicles : listVehicles) {
				Availability availability = new Availability();
				Iterator<VehiclesAssignation> iterator = vehicles
						.getVehiclesAssignations().iterator();
				if (iterator.hasNext()) {
					VehiclesAssignation vehiclesAssignation = vehicles
							.getVehiclesAssignations().iterator().next();
					availability.setFechaInicial(vehiclesAssignation
							.getVhaFechaInicio());
					availability.setFechaFinal(vehiclesAssignation
							.getVhaFechaTermina());
				}
				availability.setCodigoVehiculo(vehicles.getVhcCodigo());
				availability
						.setPlacaVehiculo(vehicles.getVhcPlacaDiplomatica());
				availability.setMarca(vehicles.getLines().getBrands()
						.getBrnNombre());
				availability.setLinea(vehicles.getLines().getLnsNombre());
				listAvailability.add(availability);
			}
			showAvailability = true;
		} catch (GWorkException e) {
			placa ="";
			messagePage.showPopup(e.getMessage(),false);
		}
	}

	/**
	 * Constructor
	 * 
	 */
	public ReserveReAllocationPage() {
		messagePage = (PopupMessagePage) FacesUtils.getManagedBean("popupMessagePage");
		Requests requests = (Requests) FacesUtils.getSession().getAttribute("solicitud");
		setCodeRequest(requests.getRqsCodigo().toString());
		setTipoSolicitud(requests.getRequestsTypes().getRqyNombre());
		setTipoAsignacion(requests.getLegateesTypes().getLgtNombre());
		setEstadoSolicitud(requests.getRequestsStates().getRqtNombre());
		setTipoVehiculo(requests.getVehiclesTypes().getVhtNombre());
		setNombreAsig(requests.getRqsCarnetAsignatario());
		setFechaSolicitud(requests.getRqsFechaSolicitud().toString());
		setFechaDesde(requests.getRqsFechaInicial().toString());
		setFechaHasta(requests.getRqsFechaFinal().toString());
		setDescripcion(requests.getRqsDescripcion());
		setClaseSolicitud(requests.getRequestsClasses().getRqcNombre());
		//Peticion 
		this.requests = requests;

	}

	public boolean isShowAvailability() {
		return showAvailability;
	}

	public void setShowAvailability(boolean showAvailability) {
		this.showAvailability = showAvailability;
	}

	public String getNombreAsig() {
		return nombreAsig;
	}

	public void setNombreAsig(String nombreAsig) {
		this.nombreAsig = nombreAsig;
	}

	public String getTipoVehiculo() {
		return tipoVehiculo;
	}

	public void setTipoVehiculo(String tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}

	public String getCodeRequest() {
		return codeRequest;
	}

	public void setCodeRequest(String codeRequest) {
		this.codeRequest = codeRequest;
	}

	public String getTipoSolicitud() {
		return tipoSolicitud;
	}

	public void setTipoSolicitud(String tipoSolicitud) {
		this.tipoSolicitud = tipoSolicitud;
	}

	public String getEstadoSolicitud() {
		return estadoSolicitud;
	}

	public void setEstadoSolicitud(String estadoSolicitud) {
		this.estadoSolicitud = estadoSolicitud;
	}

	public String getFechaSolicitud() {
		return fechaSolicitud;
	}

	public void setFechaSolicitud(String fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}

	public String getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(String fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public String getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(String fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getTipoAsignacion() {
		return tipoAsignacion;
	}

	public void setTipoAsignacion(String tipoAsignacion) {
		this.tipoAsignacion = tipoAsignacion;
	}

	public VehicleService getVehicleService() {
		return vehicleService;
	}

	public void setVehicleService(VehicleService vehicleService) {
		this.vehicleService = vehicleService;
	}

	public List<Vehicles> getListVehicles() {

		return listVehicles;
	}

	public void setListVehicles(List<Vehicles> listVehicles) {
		this.listVehicles = listVehicles;
	}

	public Long getIdType() {
		return idType;
	}

	public void setIdType(Long idType) {
		this.idType = idType;
	}

	

	public String getSbTipoVehiculo() {
		return sbTipoVehiculo;
	}

	public void setSbTipoVehiculo(String sbTipoVehiculo) {
		this.sbTipoVehiculo = sbTipoVehiculo;
	}

	public HtmlOutputText getTxtIdVehicle() {
		return txtIdVehicle;
	}

	public void setTxtIdVehicle(HtmlOutputText txtIdVehicle) {
		this.txtIdVehicle = txtIdVehicle;
	}

	public String getClaseSolicitud() {
		return claseSolicitud;
	}

	public void setClaseSolicitud(String claseSolicitud) {
		this.claseSolicitud = claseSolicitud;
	}

	public List<Availability> getListAvailability() {
		return listAvailability;
	}

	public void setListAvailability(List<Availability> listAvailability) {
		this.listAvailability = listAvailability;
	}

	public ServiceAllocation getServiceAllocation() {
		return serviceAllocation;
	}

	public void setServiceAllocation(ServiceAllocation serviceAllocation) {
		this.serviceAllocation = serviceAllocation;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getSbplaca() {
		return sbplaca;
	}

	public void setSbplaca(String sbplaca) {
		this.sbplaca = sbplaca;
	}

}