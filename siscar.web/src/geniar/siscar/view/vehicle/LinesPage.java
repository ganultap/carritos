package geniar.siscar.view.vehicle;

import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import com.icesoft.faces.component.ext.HtmlInputText;
import com.icesoft.faces.component.ext.HtmlSelectOneMenu;

import geniar.siscar.logic.vehicle.services.LinesService;
import geniar.siscar.model.Lines;
import geniar.siscar.util.FacesUtils;
import geniar.siscar.util.PopupMessagePage;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

public class LinesPage {

	private HtmlInputText txtNombreLine;
	private HtmlSelectOneMenu cbxBrand;
	private HtmlSelectOneMenu cbxLine;

	private Long lnsId;
	private Long brands;
	private String lnsNombre;
	private LinesService linesService;
	private boolean activarConfirmacion;
	private Integer opcConfirmacion;
	private static Integer ELIMINAR = 2;
	private static Integer MODIFICAR = 1;

	public Long getLnsId() {
		return lnsId;
	}

	public void setLnsId(Long lnsId) {
		this.lnsId = lnsId;
	}

	public Long getBrands() {
		return brands;
	}

	public void setBrands(Long brands) {
		this.brands = brands;
	}

	public String getLnsNombre() {
		return lnsNombre;
	}

	public void setLnsNombre(String lnsNombre) {
		this.lnsNombre = lnsNombre;
	}

	public LinesService getLinesService() {
		return linesService;
	}

	public void setLinesService(LinesService linesService) {
		this.linesService = linesService;
	}

	public HtmlInputText getTxtNombreLine() {
		return txtNombreLine;
	}

	public void setTxtNombreLine(HtmlInputText txtNombreLine) {
		this.txtNombreLine = txtNombreLine;
	}

	public HtmlSelectOneMenu getCbxBrand() {
		return cbxBrand;
	}

	public void setCbxBrand(HtmlSelectOneMenu cbxBrand) {
		this.cbxBrand = cbxBrand;
	}

	public HtmlSelectOneMenu getCbxLine() {
		return cbxLine;
	}

	public void setCbxLine(HtmlSelectOneMenu cbxLine) {
		this.cbxLine = cbxLine;
	}

	public void action_crearLine() {
		try {
			if (brands.longValue() == new Long("-1"))
				throw new GWorkException(Util
						.loadErrorMessageValue("MARCA.SEL"));
			validarNombre(lnsNombre);
			linesService.crearLineas(lnsNombre.toUpperCase(), brands);
			mostrarMensaje(Util.loadMessageValue("EXITO"), false);
			limpiar();
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());

		}

	}

	public void action_modificarLine() {

		try {

			if (lnsId != null) {

				linesService.modificarLinea(lnsId, lnsNombre.toUpperCase(),
						brands);
				limpiar();
				mostrarMensaje(Util.loadMessageValue("EXITO.MODIFICAR"), false);
			}
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());

		}

	}

	public void eliminarLinea() throws GWorkException {
		try {

			if (lnsId != null) {
				linesService.eliminarLineas(lnsId);
				limpiar();
				mostrarMensaje(Util.loadMessageValue("EXITO.ELIMINAR"), false);
			}

		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void mostrarConfirmacion(ActionEvent event) {
		try {
			validarNombre(lnsNombre);
			activarConfirmacion = true;
			if (lnsNombre == null || lnsNombre.trim().length() == 0)
				throw new GWorkException(Util
						.loadErrorMessageValue("NOMBRELINEA"));
			setOpcConfirmacion(ELIMINAR);
			mostrarMensaje(Util.loadMessageValue("MENSAJE.ALERTA"),
					activarConfirmacion);

		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());

		}

	}

	public void modificar_confirmacion(ActionEvent actionEvent) {

		try {
			validarNombre(lnsNombre);
			activarConfirmacion = true;
			if (lnsNombre == null || lnsNombre.trim().length() == 0)
				throw new GWorkException(Util
						.loadErrorMessageValue("NOMBRELINEA"));
			setOpcConfirmacion(MODIFICAR);
			mostrarMensaje(Util.loadMessageValue("MENSAJE.ALERTA"),
					activarConfirmacion);

		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());

		}
	}

	public void action_limpiar() {

		limpiar();
	}

	public void limpiar() {

		txtNombreLine.setValue("");
		cbxBrand.setValue(new Long("-1"));
		cbxLine.setValue(new Long("-1"));
		setLnsId(null);
	}

	public void listener_line(ValueChangeEvent event) {

		Long idLine = (Long) event.getNewValue();
		try {
			if (idLine != null) {
				Lines lines = linesService.consultarLineasById(idLine);

				txtNombreLine.setValue(lines.getLnsNombre());
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public void mostrarMensaje(String mensaje, boolean buttonCancel) {
		PopupMessagePage message = (PopupMessagePage) FacesUtils
				.getManagedBean("popupMessagePage");
		activarConfirmacion = true;
		if (message != null)
			message.showPopup(mensaje, buttonCancel);
	}

	public void validarNombre(String nombre) throws GWorkException {
		boolean esValido = true;

		esValido = Util.validarCadenaCaracteresEspecialesNumLetrasGuion(nombre);

		if (!esValido)
			throw new GWorkException(Util
					.loadErrorMessageValue("CARACTERESPECIALLINEA"));

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

	public void validateMinLenght(FacesContext context, UIComponent validate,
			Object value) throws GWorkException {
		String inputText = (String) value;

		if (inputText.length() <= 1) {
			((UIInput) validate).setValid(false);
			FacesUtils.addErrorMessage(Util
					.loadErrorMessageValue("NOMBRE.MINTAMAÑO"));
		}
	}

}
