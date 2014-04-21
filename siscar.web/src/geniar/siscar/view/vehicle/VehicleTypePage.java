package geniar.siscar.view.vehicle;

import geniar.siscar.logic.vehicle.services.TypeVehicleService;
import geniar.siscar.model.VehiclesTypes;
import geniar.siscar.util.FacesUtils;
import geniar.siscar.util.PopupMessagePage;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import com.icesoft.faces.component.ext.HtmlInputText;
import com.icesoft.faces.component.ext.HtmlSelectOneMenu;

public class VehicleTypePage {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long idVehicleType;
	private String nombre;

	private HtmlInputText txtNombre;
	private HtmlSelectOneMenu cbxVehicleType;
	private TypeVehicleService typeVehicleService;
	private boolean activarConfirmacion;
	private static Integer ELIMINAR = 2;
	private static Integer MODIFICAR = 1;
	private Integer opcConfirmacion;

	public Integer getOpcConfirmacion() {
		return opcConfirmacion;
	}

	public void setOpcConfirmacion(Integer opcConfirmacion) {
		this.opcConfirmacion = opcConfirmacion;
	}

	public HtmlInputText getTxtNombre() {
		return txtNombre;
	}

	public void setTxtNombre(HtmlInputText txtNombre) {
		this.txtNombre = txtNombre;
	}

	public HtmlSelectOneMenu getCbxVehicleType() {
		return cbxVehicleType;
	}

	public void setCbxVehicleType(HtmlSelectOneMenu cbxVehicleType) {
		this.cbxVehicleType = cbxVehicleType;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Long getIdVehicleType() {
		return idVehicleType;
	}

	public void setIdVehicleType(Long idVehicleType) {
		this.idVehicleType = idVehicleType;
	}

	public void action_crearVehicleType(ActionEvent event) {
		try {
			validarNombre(nombre);
			typeVehicleService.crearTipoVehiculo(nombre.toUpperCase());
			mostrarMensaje(Util.loadMessageValue("EXITO"), false);
			limpiar();
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());

		}

	}

	public void action_modificarVehicleType(ActionEvent event) {

		try {
			validarNombre(nombre);
			activarConfirmacion = true;
			if (nombre == null || nombre.trim().length() == 0)
				throw new GWorkException(Util.loadErrorMessageValue("NOMBRETIPOVEHICULO"));
			setOpcConfirmacion(MODIFICAR);
			mostrarMensaje(Util.loadMessageValue("MENSAJE.ALERTA"), activarConfirmacion);

		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());

		}
	}

	public void modificarTipoVehiculo() {
		try {
			if (idVehicleType != null || nombre != null)
				typeVehicleService.modificarTipoVehiculo(idVehicleType, nombre.toUpperCase());
			limpiar();
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void eliminarTipoVehiculo() {
		try {
			if (nombre != null)
				typeVehicleService.eliminarTipoVehiculo(nombre);
			limpiar();
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void action_eliminarVehicleType(ActionEvent event) {
		try {
			validarNombre(nombre);
			activarConfirmacion = true;
			if (nombre == null || nombre.trim().length() == 0)
				throw new GWorkException(Util.loadErrorMessageValue("NOMBRETIPOVEHICULO"));
			setOpcConfirmacion(ELIMINAR);
			mostrarMensaje(Util.loadMessageValue("MENSAJE.ALERTA"), activarConfirmacion);
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void action_limpiar(ActionEvent event) {

		limpiar();
	}

	public void limpiar() {
		txtNombre.setValue("");
		cbxVehicleType.setValue(new Long("-1"));
		setNombre(null);
		setIdVehicleType(null);
	}

	public TypeVehicleService getTypeVehicleService() {
		return typeVehicleService;
	}

	public void setTypeVehicleService(TypeVehicleService typeVehicleService) {
		this.typeVehicleService = typeVehicleService;
	}

	public void listener_vehicleType(ValueChangeEvent event) {

		Long vhtCodigo = (Long) event.getNewValue();

		try {
			if (vhtCodigo != -1L) {
				VehiclesTypes vehiclesTypes = typeVehicleService.consultarTipoVehiculoById(vhtCodigo);
				txtNombre.setValue(vehiclesTypes.getVhtNombre());
			} else if (vhtCodigo.longValue() == -1L && txtNombre.getValue() != null) {
				limpiar();
			}
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());

		}
	}

	public void mostrarMensaje(String mensaje, boolean buttonCancel) {
		PopupMessagePage message = (PopupMessagePage) FacesUtils.getManagedBean("popupMessagePage");
		if (message != null)
			message.showPopup(mensaje, buttonCancel);
	}

	public void validarNombre(String nombre) throws GWorkException {
		boolean esValido = true;

		esValido = Util.validarCadenaCaracteresEspeciales(nombre);

		if (!esValido)
			throw new GWorkException(Util.loadErrorMessageValue("NOMBRES.NUMERICOS"));

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
}
