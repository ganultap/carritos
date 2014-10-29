package geniar.siscar.view.billing;

import java.text.ParseException;
import java.util.List;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import geniar.siscar.logic.billing.services.BillingAccountService;
import geniar.siscar.model.BillingAccountVO;
import geniar.siscar.model.Period;
import geniar.siscar.util.FacesUtils;
import geniar.siscar.util.PopupMessagePage;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;
import com.icesoft.faces.component.ext.HtmlSelectOneMenu;

public class BillingAccount {

	private BillingAccountService billingAccountService;

	private Long idPeriodo;
	private String ftaNombrePeriodo;
	private boolean disCrear;
	private String fechaInicio = "";
	private String fechaFin = "";

	private HtmlSelectOneMenu selectNombrePeriodo;

	private boolean activarConfirmacion;
	private Integer opcConfirmacion;

	public BillingAccount() {

		try {

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void listener_Periodo(ValueChangeEvent event) {

		try {
			if (event.getNewValue() != null) {
				setIdPeriodo(new Long("" + event.getNewValue()));
				consultar();
			}
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

	}

	public void action_Aceptar(ActionEvent event) throws GWorkException, ParseException {
		aceptar();
	}

	private void aceptar() throws GWorkException, ParseException {
		List<BillingAccountVO> vehiculos = billingAccountService
				.listVehiclesFlatFile(new Long(idPeriodo));
		
		billingAccountService.action_aceptar(vehiculos,idPeriodo);
		
	}

	public void action_limpiarFormaFuelTanksPage(
			javax.faces.event.ActionEvent event) {
		limpiar();
	}

	private void limpiar() {

		selectNombrePeriodo.setValue("-1");

	}

	private void consultar() throws GWorkException {

		if (idPeriodo == -1)
			return;

		Period period = billingAccountService.consultarPeriodById(idPeriodo);

		if (period == null)
			throw new GWorkException(Util
					.loadErrorMessageValue("search.not.found"));

		setFechaFin(period.getPerFechaFin().toString());
		setFechaInicio(period.getPerFechaIni().toString());

	}

	public void mostrarMensaje(String mensaje, boolean buttonCancel) {
		PopupMessagePage message = (PopupMessagePage) FacesUtils
				.getManagedBean("popupMessagePage");
		if (message != null)
			message.showPopup(mensaje, buttonCancel);
	}

	public BillingAccountService getBillingAccountService() {
		return billingAccountService;
	}

	public void setBillingAccountService(
			BillingAccountService billingAccountService) {
		this.billingAccountService = billingAccountService;
	}

	public String getFtaNombreNuevo() {
		return ftaNombrePeriodo;
	}

	public void setFtaNombrePeriodo(String ftaNombrePeriodo) {
		this.ftaNombrePeriodo = ftaNombrePeriodo;
	}

	public boolean isDisCrear() {
		return disCrear;
	}

	public void setDisCrear(boolean disCrear) {
		this.disCrear = disCrear;
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

	public HtmlSelectOneMenu getSelectNombrePeriodo() {
		return selectNombrePeriodo;
	}

	public void setSelectNombrePeriodo(HtmlSelectOneMenu selectNombrePeriodo) {
		this.selectNombrePeriodo = selectNombrePeriodo;
	}

	public Long getIdPeriodo() {
		return idPeriodo;
	}

	public void setIdPeriodo(Long idPeriodo) {
		this.idPeriodo = idPeriodo;
	}

	public String getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}
}
