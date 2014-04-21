package geniar.siscar.view.parameters;

import java.util.Calendar;
import java.util.Date;
import javax.faces.event.ActionEvent;
import com.icesoft.faces.component.ext.HtmlInputText;
import com.icesoft.faces.component.ext.HtmlSelectOneMenu;
import com.icesoft.faces.component.selectinputdate.SelectInputDate;
import geniar.siscar.logic.parameters.services.TariffsHouseCiatService;
import geniar.siscar.model.Tariffs;
import geniar.siscar.util.FacesUtils;
import geniar.siscar.util.ManipulacionFechas;
import geniar.siscar.util.PopupMessagePage;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

public class TariffsHouseCiatPage {

	private Long idZona;
	private Long idMoneda;
	private Float trfValor;
	private Date fechaVigencia;
	private Integer periodo;

	private HtmlInputText txtPeriodo;
	private HtmlInputText txttrfValor;
	private SelectInputDate txtFechaVigencia;
	private HtmlSelectOneMenu cbxZonas;
	private HtmlSelectOneMenu cbxTipoCombustible;
	private HtmlSelectOneMenu cbxMoneda;
	private static Integer MODIFICAR = 1;
	private Integer opcConfirmacion;
	private boolean activarConfirmacion;

	private TariffsHouseCiatService tariffsHouseCiatService;

	public TariffsHouseCiatService getTariffsHouseCiatService() {
		return tariffsHouseCiatService;
	}

	public void setTariffsHouseCiatService(
			TariffsHouseCiatService tariffsHouseCiatService) {
		this.tariffsHouseCiatService = tariffsHouseCiatService;
	}

	public Long getIdZona() {
		return idZona;
	}

	public void setIdZona(Long idZona) {
		this.idZona = idZona;
	}

	public Date getFechaVigencia() {
		return fechaVigencia;
	}

	public void setFechaVigencia(Date fechaVigencia) {
		this.fechaVigencia = fechaVigencia;
	}

	public Integer getPeriodo() {
		return periodo;
	}

	public void setPeriodo(Integer periodo) {
		this.periodo = periodo;
	}

	public HtmlInputText getTxtPeriodo() {
		return txtPeriodo;
	}

	public void setTxtPeriodo(HtmlInputText txtPeriodo) {
		this.txtPeriodo = txtPeriodo;
	}

	public SelectInputDate getTxtFechaVigencia() {
		return txtFechaVigencia;
	}

	public void setTxtFechaVigencia(SelectInputDate txtFechaVigencia) {
		this.txtFechaVigencia = txtFechaVigencia;
	}

	public HtmlSelectOneMenu getCbxZonas() {
		return cbxZonas;
	}

	public void setCbxZonas(HtmlSelectOneMenu cbxZonas) {
		this.cbxZonas = cbxZonas;
	}

	public void limpiar() {

		cbxMoneda.setValue(new Long("-1"));
		cbxZonas.setValue(new Long("-1"));
		setFechaVigencia(null);
		txtFechaVigencia.setValue(null);
		txtPeriodo.setValue("");
		txttrfValor.setValue("");
	}

	public void action_limpiar(ActionEvent event) {

		limpiar();
	}

	public void mostrarMensaje(String mensaje, boolean buttonCancel) {
		PopupMessagePage message = (PopupMessagePage) FacesUtils
				.getManagedBean("popupMessagePage");
		if (message != null)
			message.showPopup(mensaje, buttonCancel);
	}

	public void action_CrearTarifaCasaCiat(ActionEvent event) {

		try {
			validarDatos(periodo, idZona, idMoneda, fechaVigencia, trfValor);
			validarTamanhoPeriodo(periodo.toString());
			ManipulacionFechas.validarAnhoFecha(fechaVigencia, "");

			setOpcConfirmacion(MODIFICAR);
			activarConfirmacion = true;
			mostrarMensaje(Util.loadMessageValue("MENSAJE.ALERTA"),
					activarConfirmacion);

		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());

		}
	}

	public void crearTarifaCasaCIAT() {

		try {
			tariffsHouseCiatService.modificarTarifaCasaCiat(idZona, idMoneda,
					fechaVigencia, periodo, Util.convertirDecimal(trfValor
							.toString()));
			limpiar();
			mostrarMensaje(Util.loadMessageValue("EXITO"), false);
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

	}

	public void action_consultarTarifaCasaCiat(ActionEvent event) {

		try {
			validarDatosConsulta(idZona, idMoneda);
			Tariffs tariffs = tariffsHouseCiatService
					.consultarTarifaActualCasaCiat(idZona, idMoneda);
			if (tariffs != null) {

				Date periodo = tariffs.getTrfAñoVigencia();
				Calendar calendario = Calendar.getInstance();
				calendario.setTime(periodo); // fecha es el Date de antes.
				int anho = calendario.get(Calendar.YEAR);

				txttrfValor.setValue(tariffs.getTrfValor());
				txtFechaVigencia.setValue(tariffs.getTrfFechaInicio());
				txtPeriodo.setValue(anho);

			}

		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());

		}
	}
	
	public Long getIdMoneda() {
		return idMoneda;
	}

	public void setIdMoneda(Long idMoneda) {
		this.idMoneda = idMoneda;
	}

	public HtmlSelectOneMenu getCbxTipoCombustible() {
		return cbxTipoCombustible;
	}

	public void setCbxTipoCombustible(HtmlSelectOneMenu cbxTipoCombustible) {
		this.cbxTipoCombustible = cbxTipoCombustible;
	}

	public HtmlSelectOneMenu getCbxMoneda() {
		return cbxMoneda;
	}

	public void setCbxMoneda(HtmlSelectOneMenu cbxMoneda) {
		this.cbxMoneda = cbxMoneda;
	}

	public HtmlInputText getTxttrfValor() {
		return txttrfValor;
	}

	public void setTxttrfValor(HtmlInputText txttrfValor) {
		this.txttrfValor = txttrfValor;
	}

	public void validarValor(String valor) throws GWorkException {
		boolean esValido = true;

		esValido = Util.validarNumerosParametros(valor);

		if (!esValido)
			throw new GWorkException(Util
					.loadErrorMessageValue("CARACTER.NUMERO"));

	}

	private void validarDatos(Integer periodo, Long idZona, Long idMoneda,
			Date idFechaVigencia, Float trfValor) throws GWorkException {

		if (idZona == null || idZona.longValue() == 0
				|| idZona.longValue() == new Long("-1"))
			throw new GWorkException(Util.loadErrorMessageValue("ZONES.SEL"));

		if (idMoneda == null || idMoneda.longValue() == 0
				|| idMoneda.longValue() == new Long("-1"))
			throw new GWorkException(Util.loadErrorMessageValue("MONEY.SEL"));
		if (fechaVigencia == null)
			throw new GWorkException(Util.loadErrorMessageValue("FECHAINICIO"));
		if (trfValor == null || trfValor.floatValue() == 0F)
			throw new GWorkException(Util.loadErrorMessageValue("VALORTARIFA"));
		if (periodo == null || periodo.intValue() == 0)
			throw new GWorkException(Util.loadErrorMessageValue("FECHATARIFA"));
	}

	private void validarDatosConsulta(Long idZona, Long idMoneda)
			throws GWorkException {

		if (idZona == null || idZona.longValue() == 0
				|| idZona.longValue() == new Long("-1"))
			throw new GWorkException(Util.loadErrorMessageValue("ZONES.SEL"));

		if (idMoneda == null || idMoneda.longValue() == 0
				|| idMoneda.longValue() == new Long("-1"))
			throw new GWorkException(Util.loadErrorMessageValue("MONEY.SEL"));

	}

	public Float getTrfValor() {
		return trfValor;
	}

	public void setTrfValor(Float trfValor) {
		this.trfValor = trfValor;
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

	public void validarTamanhoPeriodo(String periodo) throws GWorkException {

		if (periodo.length() <= 3)
			throw new GWorkException(Util
					.loadErrorMessageValue("PERIODO.MINLENGTH"));

	}
}
