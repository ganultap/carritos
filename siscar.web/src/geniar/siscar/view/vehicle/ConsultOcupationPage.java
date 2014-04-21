package geniar.siscar.view.vehicle;

import geniar.siscar.logic.vehicle.services.VehicleService;
import geniar.siscar.model.Availability;
import geniar.siscar.model.Vehicles;
import geniar.siscar.model.VehiclesAssignation;
import geniar.siscar.util.FacesUtils;
import geniar.siscar.util.PopupMessagePage;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;

import com.icesoft.faces.component.ext.HtmlOutputText;

public class ConsultOcupationPage {

	private String placa;
	private Long idType;
	public boolean showAvailability;
	private HtmlOutputText txtIdVehicle;
	private String sbplaca;
	private String sbTipoVehiculo;
	private VehicleService vehicleService;	
	private List<Vehicles> listVehicles;
	private List<Availability> listAvailability = new ArrayList<Availability>();

	public void action_consultarAvailability(ActionEvent actionEvent) {
		try {
			setListAvailability(null);
			boolean esValido = true;
			Vehicles vehicles = null;
			if (placa != null && placa.trim().length() != 0)
				esValido = Util.validarPlaca(placa);

			if (!esValido)
				throw new GWorkException(Util
						.loadErrorMessageValue("PLACA.INCORRECTO"));

			if (placa != null && placa.trim().length() != 0
					&& placa.trim().length() < 2)
				throw new GWorkException(Util
						.loadErrorMessageValue("PLACA.MINIMO"));

			List<VehiclesAssignation> listVehiclesAssignation = null;
			if (placa != null && placa.trim().length() != 0)
				vehicles = vehicleService
						.consultVehiclesByPlacaDiplomaticaSinFiltros(placa
								.trim().toUpperCase());

			if (placa != null && placa.trim().length() == 0
					&& idType.equals(-1L))
				listVehiclesAssignation = vehicleService
						.consultarHistorialVehiculos(null, null);

			else if (placa != null && placa.trim().length() == 0
					&& !idType.equals(-1L))
				listVehiclesAssignation = vehicleService
						.consultarHistorialVehiculos(null, idType);

			else if (placa != null && placa.trim().length() != 0
					&& idType.equals(-1L))
				listVehiclesAssignation = vehicleService
						.consultarHistorialVehiculos(vehicles.getVhcCodigo(),
								null);

			else if (placa != null && placa.trim().length() != 0
					&& !idType.equals(-1L))
				listVehiclesAssignation = vehicleService
						.consultarHistorialVehiculos(vehicles.getVhcCodigo(),
								idType);

			if (listVehiclesAssignation != null
					&& listVehiclesAssignation.size() > 0) {
				listAvailability = new ArrayList<Availability>();
				for (VehiclesAssignation vehiclesAssignation2 : listVehiclesAssignation) {
					Availability availability = new Availability();
					availability.setFechaInicial(vehiclesAssignation2
							.getVhaFechaInicio());
					availability.setFechaFinal(vehiclesAssignation2
							.getVhaFechaTermina());
					if (vehiclesAssignation2.getRequests().getUsersByRqsUser() != null) {
						availability.setNombreAsignatario(vehiclesAssignation2
								.getRequests().getUsersByRqsUser()
								.getUsrNombre());
					}
					availability.setCodigoVehiculo(vehiclesAssignation2
							.getVehicles().getVhcCodigo());
					availability.setPlacaVehiculo(vehiclesAssignation2
							.getVehicles().getVhcPlacaDiplomatica());
					availability.setMarca(vehiclesAssignation2.getVehicles()
							.getLines().getBrands().getBrnNombre());
					availability.setLinea(vehiclesAssignation2.getVehicles()
							.getLines().getLnsNombre());
					availability.setEstadoVehiculo(vehiclesAssignation2
							.getAssignationsStates().getAssNombre());
					availability.setTipoVehiculo(vehiclesAssignation2
							.getVehicles().getVehiclesTypes().getVhtNombre());
					availability.setTipoAsignacion(vehiclesAssignation2
							.getAssignationsTypes().getAstNombre());

					/* Metodos nuevos */
					/*
					 * serVhc = new SearchVehicles(); List<Object[]>
					 * regServicios =
					 * serVhc.consultarRegServicios(vehiclesAssignation2.getVehicles().getVhcCodigo(),
					 * vehiclesAssignation2.getVhaFechaInicio(),
					 * vehiclesAssignation2.getVhaFechaTermina()); Float
					 * promedioKm = new Float(0.0f); Long numGalones = new
					 * Long(0L); Float sumaValor = new Float(0.0f);
					 * 
					 * for(Object[] objRegServ: regServicios){ promedioKm +=
					 * Float.parseFloat(objRegServ[4].toString()); numGalones +=
					 * Long.parseLong(objRegServ[2].toString()); sumaValor +=
					 * Float.parseFloat(objRegServ[6].toString()); } promedioKm =
					 * promedioKm/regServicios.size();
					 * 
					 * availability.setKmPromedio(promedioKm);
					 * availability.setSumaGalones(numGalones);
					 * availability.setValorC(sumaValor);
					 * availability.setCCosto(sumaValor);
					 */

					listAvailability.add(availability);
				}
				showAvailability = true;
			} else
				throw new GWorkException(Util
						.loadErrorMessageValue("search.not.found"));
		} catch (GWorkException e) {
			placa = "";
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void mostrarMensaje(String mensaje, boolean buttonCancel) {
		PopupMessagePage message = (PopupMessagePage) FacesUtils
				.getManagedBean("popupMessagePage");
		if (message != null)
			message.showPopup(mensaje, buttonCancel);
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
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

	public List<Availability> getListAvailability() {
		return listAvailability;
	}

	public void setListAvailability(List<Availability> listAvailability) {
		this.listAvailability = listAvailability;
	}

	public boolean isShowAvailability() {
		return showAvailability;
	}

	public void setShowAvailability(boolean showAvailability) {
		this.showAvailability = showAvailability;
	}

	public HtmlOutputText getTxtIdVehicle() {
		return txtIdVehicle;
	}

	public void setTxtIdVehicle(HtmlOutputText txtIdVehicle) {
		this.txtIdVehicle = txtIdVehicle;
	}

	public String getSbplaca() {
		return sbplaca;
	}

	public void setSbplaca(String sbplaca) {
		this.sbplaca = sbplaca;
	}

	public String getSbTipoVehiculo() {
		return sbTipoVehiculo;
	}

	public void setSbTipoVehiculo(String sbTipoVehiculo) {
		this.sbTipoVehiculo = sbTipoVehiculo;
	}

	void limpiar() {

		placa = null;
		setListAvailability(null);
		idType = -1L;
	}

	public void action_limpiar(ActionEvent actionEvent) {
		limpiar();
	}

}
