/**
 * 
 */
package geniar.siscar.view.parameters;

import java.util.Date;

import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import com.icesoft.faces.component.ext.HtmlInputText;
import com.icesoft.faces.component.ext.HtmlInputTextarea;
import com.icesoft.faces.component.ext.HtmlSelectOneMenu;
import com.icesoft.faces.component.selectinputdate.SelectInputDate;

import geniar.siscar.logic.parameters.services.ParametrosService;
import geniar.siscar.model.ParParametros;
import geniar.siscar.model.ParValoresparametros;
import geniar.siscar.util.FacesUtils;
import geniar.siscar.util.PopupMessagePage;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

/**
 * @author Jorge
 * 
 */
public class ParametersPage {

	private SelectInputDate txtFechaIni;
	private HtmlInputText txtNombre;
	private HtmlInputText txtValorFin;
	private HtmlInputTextarea txtDescripcion;
	private HtmlSelectOneMenu cbxParametro;
	private boolean activarConfirmacion;

	private Long idParametro;
	private String strNombre;
	private String descripcion;
	private Float strValorFin;
	private Date fechaIni;

	private ParametrosService parametrosService;

	public SelectInputDate getTxtFechaIni() {
		return txtFechaIni;
	}

	public void setTxtFechaIni(SelectInputDate txtFechaIni) {
		this.txtFechaIni = txtFechaIni;
	}

	public HtmlInputText getTxtNombre() {
		return txtNombre;
	}

	public void setTxtNombre(HtmlInputText txtNombre) {
		this.txtNombre = txtNombre;
	}

	public HtmlInputText getTxtValorFin() {
		return txtValorFin;
	}

	public void setTxtValorFin(HtmlInputText txtValorFin) {
		this.txtValorFin = txtValorFin;
	}

	public Long getIdParametro() {
		return idParametro;
	}

	public void setIdParametro(Long idParametro) {
		this.idParametro = idParametro;
	}

	public String getStrNombre() {
		return strNombre;
	}

	public void setStrNombre(String strNombre) {
		this.strNombre = strNombre;
	}

	public HtmlSelectOneMenu getCbxParametro() {
		return cbxParametro;
	}

	public void setCbxParametro(HtmlSelectOneMenu cbxParametro) {
		this.cbxParametro = cbxParametro;
	}

	public ParametrosService getParametrosService() {
		return parametrosService;
	}

	public void setParametrosService(ParametrosService parametrosService) {
		this.parametrosService = parametrosService;
	}

	public void action_limpiar(ActionEvent event) {
		limpiar();

	}

	public void limpiar() {
		txtNombre.setValue("");
		txtValorFin.setValue(new Float(0));
		txtDescripcion.setValue("");
		txtFechaIni.setValue(null);
		cbxParametro.setValue(new Long("-1"));
	}

	public HtmlInputTextarea getTxtDescripcion() {
		return txtDescripcion;
	}

	public void setTxtDescripcion(HtmlInputTextarea txtDescripcion) {
		this.txtDescripcion = txtDescripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void action_modificarParametro(ActionEvent event) {

		try {

			if (strNombre == null || strNombre.trim().length() == 0)
				throw new GWorkException(Util.loadErrorMessageValue("NOMBREPARAMETRO"));
			if (strValorFin == null || strValorFin.longValue() == 0)
				throw new GWorkException(Util.loadErrorMessageValue("VALORPARAMETRO"));
			if (descripcion == null || descripcion.trim().length() == 0)
				throw new GWorkException(Util.loadErrorMessageValue("DESCRIPCIONPARAMETRO"));
			validarNombre(strNombre);
			validarValor(strValorFin.toString());

			activarConfirmacion = true;
			mostrarMensaje(Util.loadMessageValue("MENSAJE.ALERTA"), activarConfirmacion);

		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
			System.out.println(e.getMessage());
		}
	}

	public void modificarParametro() {

		try {
			parametrosService.actualizarParametros(idParametro, strNombre.toUpperCase(), descripcion, strValorFin
					.toString());
			limpiar();
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

	}

	public void listener_parameter(ValueChangeEvent event) {

		if (event.getNewValue() != null) {
			Long idParametroNew = (Long) event.getNewValue();

			try {

				if (idParametroNew != -1L) {

					ParParametros parametros = new ParParametros();
					parametros = parametrosService.buscarParametroPorId(idParametroNew);

					ParValoresparametros parValoresparametros = new ParValoresparametros();

					parValoresparametros = parametrosService.consultarUltimoParametro(idParametroNew);

					txtNombre.setValue(parametros.getNombre());
					txtDescripcion.setValue(parametros.getDescripcion());
					txtValorFin.setValue(parValoresparametros.getValorinicial());
					txtFechaIni.setValue(parValoresparametros.getFechainicio());
				} else {
					limpiar();
				}
			} catch (Exception e) {
				FacesUtils.addErrorMessage(e.getMessage());
			}
		}
	}

	public Date getFechaIni() {
		return fechaIni;
	}

	public void setFechaIni(Date fechaIni) {
		this.fechaIni = fechaIni;
	}

	public void mostrarMensaje(String mensaje, boolean buttonCancel) {
		PopupMessagePage message = (PopupMessagePage) FacesUtils.getManagedBean("popupMessagePage");
		if (message != null)
			message.showPopup(mensaje, buttonCancel);
	}

	public void validarNombre(String nombre) throws GWorkException {
		boolean esValido = true;

		esValido = Util.validarCadenaCaracteres(nombre);

		if (!esValido)
			throw new GWorkException(Util.loadErrorMessageValue("NOMBRES.CARACTERES"));

	}

	public void validarValor(String valor) throws GWorkException {
		boolean esValido = true;

		esValido = Util.validarNumerosParametros(valor);

		if (!esValido)
			throw new GWorkException(Util.loadErrorMessageValue("CARACTER.NUMERO"));

	}

	public Float getStrValorFin() {
		return strValorFin;
	}

	public void setStrValorFin(Float strValorFin) {
		this.strValorFin = strValorFin;
	}

	public void validateMinLenght(FacesContext context, UIComponent validate, Object value) throws GWorkException {
		String inputText = (String) value;

		if (inputText.length() <= 1) {
			((UIInput) validate).setValid(false);
			FacesUtils.addErrorMessage(Util.loadErrorMessageValue("NOMBRE.MINTAMAÑO"));
		}
	}

	public boolean isActivarConfirmacion() {
		return activarConfirmacion;
	}

	public void setActivarConfirmacion(boolean activarConfirmacion) {
		this.activarConfirmacion = activarConfirmacion;
	}

}
