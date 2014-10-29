package geniar.siscar.view.vehicle;

import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import com.icesoft.faces.component.ext.HtmlInputText;
import com.icesoft.faces.component.ext.HtmlSelectOneMenu;
import geniar.siscar.logic.vehicle.services.TypeFuelsService;
import geniar.siscar.model.FuelsTypes;
import geniar.siscar.util.FacesUtils;
import geniar.siscar.util.PopupMessagePage;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

public class FuelsTypesPage {

	private HtmlInputText txtNombre;
	private HtmlSelectOneMenu cbxCombustible;

	private Long idTipoCombustible;
	private String tcNombre;
	private String fuelsTypesServiceImpl;
	private TypeFuelsService typeFuelsService;
	private FuelsTypes fuelsTypes;
	private boolean activarConfirmacion;
	private static Integer ELIMINAR = 2;
	private static Integer MODIFICAR = 1;
	private Integer opcConfirmacion;

	public HtmlInputText getTxtNombre() {
		return txtNombre;
	}

	public void setTxtNombre(HtmlInputText txtNombre) {
		this.txtNombre = txtNombre;
	}

	public void listener_fuelTypes(ValueChangeEvent event) {

		Long idCombustible = (Long) event.getNewValue();
		if (idCombustible != -1L) {

			fuelsTypes = typeFuelsService
					.consultarTipoCombustibleById(idCombustible);
			txtNombre.setValue(fuelsTypes.getFutNombre());
		}
	}

	public void action_grabarFuelsTypes(ActionEvent event) {

		try {
			validarNombre(tcNombre);
			typeFuelsService.crearTipoCombustible(tcNombre.toUpperCase());
			mostrarMensaje(Util.loadMessageValue("EXITO"), false);
			limpiar();
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());

		}

	}

	public void action_modificarFuelsTypes(ActionEvent event) {

		try {
			validarNombre(tcNombre);
			activarConfirmacion = true;
			setOpcConfirmacion(MODIFICAR);
			mostrarMensaje(Util.loadMessageValue("MENSAJE.ALERTA"),
					activarConfirmacion);

		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());

		}
	}

	public void modificarFuelTypes() {
		try {

			if (idTipoCombustible != null) {
				typeFuelsService.modificarTipoCombustible(idTipoCombustible,
						tcNombre.toUpperCase());
				limpiar();
				mostrarMensaje(Util.loadMessageValue("EXITO.MODIFICAR"), false);
			}

		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

	}

	public void action_elminarFuelType(ActionEvent event) {
		try {
			validarNombre(tcNombre);
			activarConfirmacion = true;
			setOpcConfirmacion(ELIMINAR);
			mostrarMensaje(Util.loadMessageValue("MENSAJE.ALERTA"),
					activarConfirmacion);

		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());

		}
	}

	public void elminarFuelTypes() {

		try {
			typeFuelsService.eliminarTipoCombustible(tcNombre);
			limpiar();
			mostrarMensaje(Util.loadMessageValue("EXITO.ELIMINAR"), false);
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

	}

	public void action_limpiar(ActionEvent event) {

		limpiar();
	}

	public void limpiar() {

		txtNombre.setValue("");
		cbxCombustible.setValue(new Long("-1"));
	}

	public Long getIdTipoCombustible() {
		return idTipoCombustible;
	}

	public void setIdTipoCombustible(Long idTipoCombustible) {
		this.idTipoCombustible = idTipoCombustible;
	}

	public String getTcNombre() {
		return tcNombre;
	}

	public void setTcNombre(String tcNombre) {
		this.tcNombre = tcNombre;
	}

	public String getFuelsTypesServiceImpl() {
		return fuelsTypesServiceImpl;
	}

	public void setFuelsTypesServiceImpl(String fuelsTypesServiceImpl) {
		this.fuelsTypesServiceImpl = fuelsTypesServiceImpl;
	}

	public HtmlSelectOneMenu getCbxCombustible() {
		return cbxCombustible;
	}

	public void setCbxCombustible(HtmlSelectOneMenu cbxCombustible) {
		this.cbxCombustible = cbxCombustible;
	}

	public TypeFuelsService getTypeFuelsService() {
		return typeFuelsService;
	}

	public void setTypeFuelsService(TypeFuelsService typeFuelsService) {
		this.typeFuelsService = typeFuelsService;
	}

	public void mostrarMensaje(String mensaje, boolean buttonCancel) {
		PopupMessagePage message = (PopupMessagePage) FacesUtils
				.getManagedBean("popupMessagePage");
		if (message != null)
			message.showPopup(mensaje, buttonCancel);
	}

	public void validarNombre(String nombre) throws GWorkException {
		boolean esValido = true;

		esValido = Util.validarCadenaCaracteresEspecialesNumLetrasGuion(nombre);

		if (!esValido)
			throw new GWorkException(Util
					.loadErrorMessageValue("CARACTER.NUMLETRA"));

	}

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

}
