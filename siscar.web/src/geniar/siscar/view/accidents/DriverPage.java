package geniar.siscar.view.accidents;

import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import com.icesoft.faces.component.ext.HtmlCommandButton;
import com.icesoft.faces.component.ext.HtmlInputText;

import geniar.siscar.logic.accidents.services.DriversService;
import geniar.siscar.model.Driver;
import geniar.siscar.util.FacesUtils;
import geniar.siscar.util.PopupMessagePage;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

public class DriverPage {

	private DriversService driversService;
	private String drvCedula;
	private String drvNombre;
	private String drvNumeroCarne;
	private String drvCargo;
	private String drvDireccion;
	private String drvTelefono;

	private HtmlCommandButton btnCrear;
	private HtmlCommandButton btnModifcar;
	private HtmlCommandButton btnEliminar;

	private HtmlInputText txtcargo;
	private HtmlInputText txtNombre;
	private HtmlInputText txtDireccion;
	private HtmlInputText txtTelefono;
	private HtmlInputText txtCodigoCargo;
	private HtmlInputText txtCarne;

	private boolean activarConfirmacion;
	private static Integer ELIMINAR = 2;
	private static Integer MODIFICAR = 1;
	private Integer opcConfirmacion;

	public boolean isActivarConfirmacion() {
		return activarConfirmacion;
	}

	public void setActivarConfirmacion(boolean activarConfirmacion) {
		this.activarConfirmacion = activarConfirmacion;
	}

	public Integer getOpcConfirmacion() {
		return opcConfirmacion;
	}

	public void setOpcConfirmacion(Integer opcConfirmacion) {
		this.opcConfirmacion = opcConfirmacion;
	}

	public String getDrvCedula() {
		return drvCedula;
	}

	public void setDrvCedula(String drvCedula) {
		this.drvCedula = drvCedula;
	}

	public String getDrvNombre() {
		return drvNombre;
	}

	public void setDrvNombre(String drvNombre) {
		this.drvNombre = drvNombre;
	}

	public String getDrvCargo() {
		return drvCargo;
	}

	public void setDrvCargo(String drvCargo) {
		this.drvCargo = drvCargo;
	}

	public String getDrvDireccion() {
		return drvDireccion;
	}

	public void setDrvDireccion(String drvDireccion) {
		this.drvDireccion = drvDireccion;
	}

	public String getDrvTelefono() {
		return drvTelefono;
	}

	public void setDrvTelefono(String drvTelefono) {
		this.drvTelefono = drvTelefono;
	}

	public DriversService getDriversService() {
		return driversService;
	}

	public void setDriversService(DriversService driversService) {
		this.driversService = driversService;
	}

	static void validarDatos(String drvCedula, String drvNombre,
			String drvCargo, String drvNumeroCarne, String drvDireccion,
			String drvTelefono) throws GWorkException {

		if (drvCedula == null || drvCedula.trim().length() == 0)
			throw new GWorkException(Util
					.loadErrorMessageValue("CEDULACONDUCTORNULL"));
		if (drvCedula.length() < 2)
			throw new GWorkException(Util.loadErrorMessageValue("CEDULAMIN"));

		if (drvNombre == null || drvNombre.trim().length() == 0)
			throw new GWorkException(Util
					.loadErrorMessageValue("NOMBRECONDUCTORNULL"));

		if (drvNombre.length() < 4)
			throw new GWorkException(Util
					.loadErrorMessageValue("NOMBRECONDUCTORMIN"));

		if (drvNumeroCarne == null || drvNumeroCarne.length() == 0
				|| drvNumeroCarne.length() < 3)
			throw new GWorkException(Util
					.loadErrorMessageValue("NUMEROCARNEMIN"));

		if (drvCargo == null || drvCargo.trim().length() == 0)
			throw new GWorkException(Util
					.loadErrorMessageValue("CARGOCONDUCTORNULL"));

		if (drvCargo.length() < 4)
			throw new GWorkException(Util.loadErrorMessageValue("CARGOMIN"));

		if (drvDireccion == null || drvDireccion.trim().length() == 0)
			throw new GWorkException(Util
					.loadErrorMessageValue("DIRECCIONCONDUCTORNULL"));

		if (drvDireccion.length() < 4)
			throw new GWorkException(Util
					.loadErrorMessageValue("DIRECCIONCONDUCTORMIN"));

		if (drvTelefono == null || drvTelefono.trim().length() == 0)
			throw new GWorkException(Util
					.loadErrorMessageValue("TELEFONOCONDUCTRONULL"));

		if (drvTelefono.length() < 7)
			throw new GWorkException(Util
					.loadErrorMessageValue("TELEFONOCONDUCTORMIN"));
	}

	static void validarCaracteresEspeciales(String drvCedula, String drvNombre,
			String drvCargo, String drvNumeroCarne, String drvDireccion,
			String drvTelefono) throws GWorkException {

		if (!Util.validarCadenaCaracteresEspecialesNumLetrasGuion(drvCedula))
			throw new GWorkException(Util
					.loadErrorMessageValue("CEDULACARACTERES"));

		if (!Util.validarCadenaCaracteresEspeciales(drvNombre))
			throw new GWorkException(Util
					.loadErrorMessageValue("NOMBRECARACTERESCONDUCTOR"));

		if (!Util.validarNumerosParametros(drvNumeroCarne))
			throw new GWorkException(Util
					.loadErrorMessageValue("NUMEROCARNECARACTERES"));

		if (!Util.validarCadenaCaracteresEspecialesNumLetrasGuion(drvCargo))
			throw new GWorkException(Util
					.loadErrorMessageValue("CARGOCARACTERES"));

		if (!Util.validarCadenaCaracteresEspecialesNumLetrasGuion(drvDireccion))
			throw new GWorkException(Util
					.loadErrorMessageValue("DIRECCIONCARACTERES"));

		if (!Util.validarCadenaCaracteresEspecialesNumLetrasGuion(drvTelefono))
			throw new GWorkException(Util
					.loadErrorMessageValue("TELEFONOCARACTERES"));

	}

	public void action_crearConductor(ActionEvent actionEvent) {

		try {
			validarDatos(drvCedula, drvNombre, drvCargo, drvNumeroCarne,
					drvDireccion, drvTelefono);
			validarCaracteresEspeciales(drvCedula, drvNombre, drvCargo,
					drvNumeroCarne, drvDireccion, drvTelefono);

			driversService.crearConductor(drvCedula, drvNombre.toUpperCase(),
					drvNumeroCarne, drvCargo.toUpperCase(), drvDireccion,
					drvTelefono);
			mostrarMensaje(Util.loadMessageValue("EXITO"), false);
			limpiar();
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void action_modicarConductor(ActionEvent actionEvent) {
		try {
			validarDatos(drvCedula, drvNombre, drvCargo, drvNumeroCarne,
					drvDireccion, drvTelefono);
			validarCaracteresEspeciales(drvCedula, drvNombre, drvCargo,
					drvNumeroCarne, drvDireccion, drvTelefono);

			activarConfirmacion = true;
			setOpcConfirmacion(MODIFICAR);
			mostrarMensaje(Util.loadMessageValue("MENSAJE.ALERTA"),
					activarConfirmacion);

		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

	}

	public void modificarConductor() {

		try {
			driversService.modificarConductor(drvCedula, drvNombre
					.toUpperCase(), drvNumeroCarne, drvCargo.toUpperCase(),
					drvDireccion, drvTelefono);
			limpiar();
			mostrarMensaje(Util.loadMessageValue("EXITO.MODIFICAR"), false);
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void action_eliminarConductor(ActionEvent actionEvent) {

		try {
			if (drvCedula == null || drvCedula.trim().length() == 0)
				throw new GWorkException(Util
						.loadErrorMessageValue("CEDULACONDUCTORNULL"));

			activarConfirmacion = true;
			setOpcConfirmacion(ELIMINAR);
			mostrarMensaje(Util.loadMessageValue("MENSAJE.ALERTA"),
					activarConfirmacion);

		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void elminarConductor() {

		try {
			driversService.eliminarConductor(drvCedula);
			limpiar();
			mostrarMensaje(Util.loadMessageValue("EXITO.ELIMINAR"), false);
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public String getDrvNumeroCarne() {
		return drvNumeroCarne;
	}

	public void setDrvNumeroCarne(String drvNumeroCarne) {
		this.drvNumeroCarne = drvNumeroCarne;
	}

	public void mostrarMensaje(String mensaje, boolean buttonCancel) {
		PopupMessagePage message = (PopupMessagePage) FacesUtils
				.getManagedBean("popupMessagePage");
		if (message != null)
			message.showPopup(mensaje, buttonCancel);
	}

	public void limpiar() {

		setDrvCargo(null);
		setDrvCedula(null);
		setDrvDireccion(null);
		setDrvNombre(null);
		setDrvNumeroCarne(null);
		setDrvTelefono(null);
		btnCrear.setDisabled(false);
		btnModifcar.setDisabled(true);
		btnEliminar.setDisabled(true);
	}

	public void listener_cedula(ValueChangeEvent event) {

		try {
			String cedula = (String) event.getNewValue();
			Driver driver = driversService.consultarConductor(cedula);
			if (driver != null) {

				txtcargo.setValue(driver.getDrvCargo());
				txtDireccion.setValue(driver.getDrvDireccion());
				txtNombre.setValue(driver.getDrvNombre());
				String carne = null;
				if (driver.getDrvNumeroCarne() != null)
					carne = driver.getDrvNumeroCarne().toString();
				txtCarne.setValue(carne);
				txtTelefono.setValue(driver.getDrvTelefono());

				btnCrear.setDisabled(true);
				btnModifcar.setDisabled(false);
				btnEliminar.setDisabled(false);
			}
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public HtmlCommandButton getBtnCrear() {
		return btnCrear;
	}

	public void setBtnCrear(HtmlCommandButton btnCrear) {
		this.btnCrear = btnCrear;
	}

	public HtmlCommandButton getBtnModifcar() {
		return btnModifcar;
	}

	public void setBtnModifcar(HtmlCommandButton btnModifcar) {
		this.btnModifcar = btnModifcar;
	}

	public HtmlCommandButton getBtnEliminar() {
		return btnEliminar;
	}

	public void setBtnEliminar(HtmlCommandButton btnEliminar) {
		this.btnEliminar = btnEliminar;
	}

	public HtmlInputText getTxtcargo() {
		return txtcargo;
	}

	public void setTxtcargo(HtmlInputText txtcargo) {
		this.txtcargo = txtcargo;
	}

	public HtmlInputText getTxtNombre() {
		return txtNombre;
	}

	public void setTxtNombre(HtmlInputText txtNombre) {
		this.txtNombre = txtNombre;
	}

	public HtmlInputText getTxtDireccion() {
		return txtDireccion;
	}

	public void setTxtDireccion(HtmlInputText txtDireccion) {
		this.txtDireccion = txtDireccion;
	}

	public HtmlInputText getTxtTelefono() {
		return txtTelefono;
	}

	public void setTxtTelefono(HtmlInputText txtTelefono) {
		this.txtTelefono = txtTelefono;
	}

	public HtmlInputText getTxtCodigoCargo() {
		return txtCodigoCargo;
	}

	public void setTxtCodigoCargo(HtmlInputText txtCodigoCargo) {
		this.txtCodigoCargo = txtCodigoCargo;
	}

	public HtmlInputText getTxtCarne() {
		return txtCarne;
	}

	public void setTxtCarne(HtmlInputText txtCarne) {
		this.txtCarne = txtCarne;
	}

	public void action_limpiar(ActionEvent event) {
		limpiar();
	}
}
