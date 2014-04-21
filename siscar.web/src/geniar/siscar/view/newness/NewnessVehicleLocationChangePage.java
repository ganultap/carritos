package geniar.siscar.view.newness;

import java.util.List;

import geniar.siscar.logic.newness.services.NewnessVehicleLocationService;
import geniar.siscar.model.Vehicles;
import geniar.siscar.model.VehiclesAssignation;
import geniar.siscar.persistence.VehiclesDAO;
import geniar.siscar.util.FacesUtils;
import geniar.siscar.util.PopupMessagePage;
import geniar.siscar.util.Util;
import geniar.siscar.view.BaseBean;
import geniar.siscar.view.autenticate.LoginPage;
import gwork.exception.GWorkException;

import javax.faces.component.html.HtmlCommandButton;
import javax.faces.event.ActionEvent;

public class NewnessVehicleLocationChangePage extends BaseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HtmlCommandButton buttonGuardar;
	private HtmlCommandButton buttonConsultar;
	private String placa;
	private String fechaFinalAsignacion;
	private String usrLogin;
	private String descripcionNovedad;
	private String asignatario;
	private String tipoAsignacion;
	private String fechaInicialAsignacion;
	private String tipoVehiculo;
	private String nombreCentro;
	private String porcentaje;
	private String ubicacion;
	private String tipoUbicacion;
	private boolean activeSave;
	private Long idUbicacion;
	private Long idTipoUbicacion;

	String login = null;
	private NewnessVehicleLocationService vehicleLocationService;

	public void consultarCentroCostos_action(ActionEvent event) throws GWorkException {

		try {
			validarPlaca(placa);
			removeIdSessionValues();
			String idVehiculo = null;
			String idAsignacion = null;

			VehiclesAssignation assignation = vehicleLocationService.consultarDatosAsignacion(placa.trim()
					.toUpperCase());

			List<Vehicles> listVehicles = new VehiclesDAO().findByVhcPlacaDiplomatica(placa.trim().toUpperCase());
			Vehicles vehicles = null;

			if (listVehicles != null && listVehicles.size() > 0)
				vehicles = listVehicles.get(0);

			if (vehicles == null)
				throw new GWorkException(Util.loadErrorMessageValue("PLACA.NOEXISTE"));

			if (assignation == null && vehicles != null)
				throw new GWorkException(Util.loadErrorMessageValue("DATOS.NULL"));

			asignatario = assignation.getRequests().getUsersByRqsUser().getUsrNombre();
			
			if (assignation.getRequests().getLegateesTypes() != null) 
				tipoAsignacion = assignation.getRequests().getLegateesTypes().getLgtNombre();	
						
			fechaInicialAsignacion = assignation.getVhaFechaInicio().toString();
			fechaFinalAsignacion = assignation.getVhaFechaTermina().toString();
			tipoVehiculo = assignation.getVehicles().getVehiclesTypes().getVhtNombre();

			idVehiculo = assignation.getVehicles().getVhcCodigo().toString();
			idAsignacion = assignation.getVhaCodigo().toString();
			ubicacion = assignation.getVehicles().getLocations().getCities().getCtsNombre();
			tipoUbicacion = assignation.getVehicles().getLocations().getLocationsTypes().getLctNombre();

			idUbicacion = assignation.getVehicles().getLocations().getLcnCodigo();

			FacesUtils.getSession().setAttribute("idVehiculoLocation", idVehiculo.toString());
			FacesUtils.getSession().setAttribute("idAsignacionLocation", idAsignacion.toString());

			setActiveSave(true);
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void validarPlaca(String placa) throws GWorkException {
		boolean esValido = true;

		if (placa != null && placa.trim().length() == 0)
			throw new GWorkException(Util.loadErrorMessageValue("PLACA"));

		if (placa != null)
			esValido = Util.validarPlaca(placa);

		if (!esValido)
			throw new GWorkException(Util.loadErrorMessageValue("PLACA.INCORRECTO"));

		if (placa != null && placa.trim().length() < 2)
			throw new GWorkException(Util.loadErrorMessageValue("PLACA.MINIMO"));

	}

	public void guardarUbicacion(ActionEvent event) throws GWorkException {
		try {
			LoginPage loginPage = (LoginPage) FacesUtils.getManagedBean("loginPage");
			if (loginPage != null)
				login = loginPage.getLogin();
			
			String ubicacion = null;
			String tipoUbicacion = null;
			validarPlaca(placa);

			if (idTipoUbicacion != null)
				tipoUbicacion = idTipoUbicacion.toString();

			if (tipoUbicacion.equalsIgnoreCase("-1"))
				throw new GWorkException(Util.loadErrorMessageValue("TIPO.UBICACION"));

			if (idUbicacion != null)
				ubicacion = idUbicacion.toString();

			if (ubicacion.equalsIgnoreCase("-1"))
				throw new GWorkException(Util.loadErrorMessageValue("UBICACION"));

			String idVehiculo = (String) FacesUtils.getSession().getAttribute("idVehiculoLocation");
			String idAsignacion = (String) FacesUtils.getSession().getAttribute("idAsignacionLocation");
			vehicleLocationService.actualizarTipoUbicacionVehiculo(login, "", new Long(idVehiculo), idUbicacion,idTipoUbicacion,
					new Long(idAsignacion));
			mostrarMensaje(Util.loadMessageValue("EXITO"), false);
			setActiveSave(false);
			limpiarValores();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void limpiar_action(ActionEvent event) {
		limpiarValores();
	}

	public void limpiarValores() {
		setPlaca(null);
		setPorcentaje(null);
		setAsignatario(null);
		setTipoAsignacion(null);
		setFechaFinalAsignacion(null);
		setFechaInicialAsignacion(null);
		setUbicacion(null);
		setTipoUbicacion(null);
		setTipoVehiculo(null);
		setIdTipoUbicacion(new Long("-1"));
		setIdUbicacion(new Long("-1"));
		removeIdSessionValues();
	}

	public void mostrarMensaje(String mensaje, boolean buttonCancel) {
		PopupMessagePage message = (PopupMessagePage) FacesUtils.getManagedBean("popupMessagePage");
		if (message != null)
			message.showPopup(mensaje, buttonCancel);
	}

	public void removeIdSessionValues() {
		FacesUtils.getSession().removeAttribute("idAsignacionLocation");
		FacesUtils.getSession().removeAttribute("idVehiculoLocation");
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getFechaFinalAsignacion() {
		return fechaFinalAsignacion;
	}

	public void setFechaFinalAsignacion(String fechaFinalAsignacion) {
		this.fechaFinalAsignacion = fechaFinalAsignacion;
	}

	public String getUsrLogin() {
		return usrLogin;
	}

	public void setUsrLogin(String usrLogin) {
		this.usrLogin = usrLogin;
	}

	public String getDescripcionNovedad() {
		return descripcionNovedad;
	}

	public void setDescripcionNovedad(String descripcionNovedad) {
		this.descripcionNovedad = descripcionNovedad;
	}

	public String getAsignatario() {
		return asignatario;
	}

	public void setAsignatario(String asignatario) {
		this.asignatario = asignatario;
	}

	public String getTipoAsignacion() {
		return tipoAsignacion;
	}

	public void setTipoAsignacion(String tipoAsignacion) {
		this.tipoAsignacion = tipoAsignacion;
	}

	public String getFechaInicialAsignacion() {
		return fechaInicialAsignacion;
	}

	public void setFechaInicialAsignacion(String fechaInicialAsignacion) {
		this.fechaInicialAsignacion = fechaInicialAsignacion;
	}

	public String getTipoVehiculo() {
		return tipoVehiculo;
	}

	public void setTipoVehiculo(String tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}

	public String getNombreCentro() {
		return nombreCentro;
	}

	public void setNombreCentro(String nombreCentro) {
		this.nombreCentro = nombreCentro;
	}

	public String getPorcentaje() {
		return porcentaje;
	}

	public void setPorcentaje(String porcentaje) {
		this.porcentaje = porcentaje;
	}

	public HtmlCommandButton getButtonGuardar() {
		return buttonGuardar;
	}

	public void setButtonGuardar(HtmlCommandButton buttonGuardar) {
		this.buttonGuardar = buttonGuardar;
	}

	public HtmlCommandButton getButtonConsultar() {
		return buttonConsultar;
	}

	public void setButtonConsultar(HtmlCommandButton buttonConsultar) {
		this.buttonConsultar = buttonConsultar;
	}

	public NewnessVehicleLocationService getVehicleLocationService() {
		return vehicleLocationService;
	}

	public void setVehicleLocationService(NewnessVehicleLocationService vehicleLocationService) {
		this.vehicleLocationService = vehicleLocationService;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public String getTipoUbicacion() {
		return tipoUbicacion;
	}

	public void setTipoUbicacion(String tipoUbicacion) {
		this.tipoUbicacion = tipoUbicacion;
	}

	public boolean isActiveSave() {
		return activeSave;
	}

	public void setActiveSave(boolean activeSave) {
		this.activeSave = activeSave;
	}

	public Long getIdUbicacion() {
		return idUbicacion;
	}

	public void setIdUbicacion(Long idUbicacion) {
		this.idUbicacion = idUbicacion;
	}

	public Long getIdTipoUbicacion() {
		return idTipoUbicacion;
	}

	public void setIdTipoUbicacion(Long idTipoUbicacion) {
		this.idTipoUbicacion = idTipoUbicacion;
	}
}
