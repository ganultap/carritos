package geniar.siscar.view.parameters;

import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import com.icesoft.faces.component.ext.HtmlInputText;
import com.icesoft.faces.component.ext.HtmlInputTextarea;
import com.icesoft.faces.component.ext.HtmlSelectOneMenu;

import geniar.siscar.logic.parameters.services.ZoneService;
import geniar.siscar.model.Zones;
import geniar.siscar.util.FacesUtils;
import geniar.siscar.util.PopupMessagePage;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

public class ZonesPage {

	private Long znsId;
	private String znsNombre;
	private String znsDescripcion;
	private String znsKilometraje;
	private HtmlInputText txtIdZone;
	private HtmlInputText txtNombre;
	private HtmlInputTextarea txtDescripcion;
	private HtmlInputText txtDistancia;
	private HtmlSelectOneMenu cbxZones;
	private ZoneService zoneService;
	private static Integer ELIMINAR = 2;
	private static Integer MODIFICAR = 1;
	private Integer opcConfirmacion;
	private boolean activarConfirmacion;

	public Long getZnsId() {
		return znsId;
	}

	public void setZnsId(Long znsId) {
		this.znsId = znsId;
	}

	public String getZnsNombre() {
		return znsNombre;
	}

	public void setZnsNombre(String znsNombre) {
		this.znsNombre = znsNombre;
	}

	public String getZnsDescripcion() {
		return znsDescripcion;
	}

	public void setZnsDescripcion(String znsDescripcion) {
		this.znsDescripcion = znsDescripcion;
	}

	public HtmlInputText getTxtIdZone() {
		return txtIdZone;
	}

	public void setTxtIdZone(HtmlInputText txtIdZone) {
		this.txtIdZone = txtIdZone;
	}

	public HtmlInputText getTxtNombre() {
		return txtNombre;
	}

	public void setTxtNombre(HtmlInputText txtNombre) {
		this.txtNombre = txtNombre;
	}

	public HtmlInputText getTxtDistancia() {
		return txtDistancia;
	}

	public void setTxtDistancia(HtmlInputText txtDistancia) {
		this.txtDistancia = txtDistancia;
	}

	public ZoneService getZoneService() {
		return zoneService;
	}

	public void setZoneService(ZoneService zoneService) {
		this.zoneService = zoneService;
	}

	public HtmlSelectOneMenu getCbxZones() {
		return cbxZones;
	}

	public void setCbxZones(HtmlSelectOneMenu cbxZones) {
		this.cbxZones = cbxZones;
	}

	public void action_CrearZona(ActionEvent event) {

		try {
			validarDatos(znsNombre, znsDescripcion, znsKilometraje);
			validarNombre(znsNombre);
			validarValor(znsKilometraje);
			zoneService.crearZona(znsNombre.toUpperCase(), znsDescripcion, znsKilometraje);
			mostrarMensaje(Util.loadMessageValue("EXITO"), false);

			limpiar();

		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());

		}
	}

	public void action_modificarZona(ActionEvent event) {

		try {

			validarDatos(znsNombre, znsDescripcion, znsKilometraje);
			validarNombre(znsNombre);
			validarValor(znsKilometraje);
			setOpcConfirmacion(MODIFICAR);
			activarConfirmacion = true;
			mostrarMensaje(Util.loadMessageValue("MENSAJE.ALERTA"), activarConfirmacion);

		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());

		}
	}

	public void modificarZona() {

		try {
			zoneService.modificarZona(znsId, znsNombre.toUpperCase(), znsDescripcion, znsKilometraje);
			limpiar();
			mostrarMensaje(Util.loadMessageValue("EXITO.MODIFICAR"), false);
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

	}

	public void action_eliminarZone(ActionEvent event) {

		try {
			if (znsNombre == null || znsNombre.trim().length() == 0)
				throw new GWorkException(Util.loadErrorMessageValue("NOMBREZONA"));
			validarNombre(znsNombre);
			setOpcConfirmacion(ELIMINAR);
			activarConfirmacion = true;
			mostrarMensaje(Util.loadMessageValue("MENSAJE.ALERTA"), activarConfirmacion);

		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());

		}
	}

	public void eliminarZona() {

		try {
			zoneService.eliminarZona(znsNombre);
			limpiar();
			mostrarMensaje(Util.loadMessageValue("EXITO.ELIMINAR"), false);

		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

	}

	public void action_limpiar(ActionEvent event) {

		limpiar();
	}

	public void listener_zones(ValueChangeEvent event) {

		Long idZone = (Long) event.getNewValue();
		Zones zones = new Zones();
		try {

			if (idZone != null && idZone != -1L) {
				zones = zoneService.consultarZone(idZone);
				txtNombre.setValue(zones.getZnsNombre());
				txtDescripcion.setValue(zones.getZnsDescripcion());
				txtDistancia.setValue(zones.getZnsKilometraje());
			} else if (idZone != null && idZone.longValue() == -1L && znsNombre != null) {
				limpiar();
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void mostrarMensaje(String mensaje, boolean buttonCancel) {
		PopupMessagePage message = (PopupMessagePage) FacesUtils.getManagedBean("popupMessagePage");
		if (message != null)
			message.showPopup(mensaje, buttonCancel);
	}

	public void limpiar() {

		txtNombre.setValue("");
		txtDescripcion.setValue("");
		txtDistancia.setValue(null);
		znsKilometraje = null;
		cbxZones.setValue(new Long(-1));

	}

	public void validarNombre(String nombre) throws GWorkException {
		boolean esValido = true;

		esValido = Util.validarCadenaCaracteresEspecialesNumLetrasGuion(nombre);

		if (!esValido)
			throw new GWorkException(Util.loadErrorMessageValue("NOMBRES.CARACTERES"));

	}

	public void validarValor(String valor) throws GWorkException {
		boolean esValido = true;

		esValido = Util.validarNumerosParametros(valor);

		if (!esValido)
			throw new GWorkException(Util.loadErrorMessageValue("CARACTER.NUMEROZONES"));

	}

	public void validarDatos(String nombre, String descripcion, String kilometraje) throws GWorkException {

		if (znsNombre.trim().length() == 0)
			throw new GWorkException(Util.loadErrorMessageValue("NOMBREZONA"));
		if (znsDescripcion.trim().length() == 0)
			throw new GWorkException(Util.loadErrorMessageValue("ZONADESCRIPCION"));
		if (znsKilometraje == null || znsKilometraje.trim().length() == 0)
			throw new GWorkException(Util.loadErrorMessageValue("ZONADISTANCIA"));
	}

	public Integer getOpcConfirmacion() {
		return opcConfirmacion;
	}

	public void setOpcConfirmacion(Integer opcConfirmacion) {
		this.opcConfirmacion = opcConfirmacion;
	}

	public boolean isActivarConfirmacion() {
		return activarConfirmacion;
	}

	public void setActivarConfirmacion(boolean activarConfirmacion) {
		this.activarConfirmacion = activarConfirmacion;
	}

	public void validateMinLenght(FacesContext context, UIComponent validate, Object value) throws GWorkException {
		String inputText = (String) value;

		if (inputText.length() <= 1) {
			((UIInput) validate).setValid(false);
			FacesUtils.addErrorMessage(Util.loadErrorMessageValue("NOMBRE.MINTAMAÑO"));
		}
	}

	public HtmlInputTextarea getTxtDescripcion() {
		return txtDescripcion;
	}

	public void setTxtDescripcion(HtmlInputTextarea txtDescripcion) {
		this.txtDescripcion = txtDescripcion;
	}

	public void validateMinLenghtDescrition(FacesContext context, UIComponent validate, Object value)
			throws GWorkException {
		String inputText = (String) value;

		if (inputText.length() <= 1 || inputText.length() > 100) {
			((UIInput) validate).setValid(false);
			FacesUtils.addErrorMessage(Util.loadErrorMessageValue("DESCRIPCION.LENGTH"));
		}
	}

	public String getZnsKilometraje() {
		return znsKilometraje;
	}

	public void setZnsKilometraje(String znsKilometraje) {
		this.znsKilometraje = znsKilometraje;
	}

}
