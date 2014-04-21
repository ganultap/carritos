package geniar.siscar.view.billing;

import java.util.List;
import javax.faces.event.ActionEvent;
import com.icesoft.faces.component.ext.HtmlCommandButton;
import geniar.siscar.logic.billing.services.BillingAccountAutoInsuranceService;
import geniar.siscar.model.BillingAccountAutoInsuranceVO;
import geniar.siscar.model.Period;
import geniar.siscar.model.FlatFile;
import geniar.siscar.util.FacesUtils;
import geniar.siscar.util.PopupMessagePage;
import geniar.siscar.util.Util;
import geniar.siscar.view.autenticate.LoginPage;
import gwork.exception.GWorkException;

public class BillingAccountAutoInsurancePage {

	private String anoCobro;
	private Long idVehiculo;
	private String carne;
	private String plcDiplomatica;
	private String ftaNombrePeriodo;
	private boolean disCrear;
	private boolean activarConfirmacion;
	private Integer opcConfirmacion;
	private static int GUARDAR_DATOS = 1;
	private Period Periodo;

	private HtmlCommandButton botonAceptar;
	private HtmlCommandButton botonImprimir;

	private LoginPage loginPage = (LoginPage) FacesUtils
			.getManagedBean("loginPage");

	private List<BillingAccountAutoInsuranceVO> listVehicles;
	private List<FlatFile> listff;

	private boolean mostrarAceptar;
	private BillingAccountAutoInsuranceService billingAccountAutoInsuranceService;

	public BillingAccountAutoInsurancePage() {
		setAnoCobro((new java.text.SimpleDateFormat("yyyy"))
				.format(new java.util.Date()));
	}

	public void action_Aceptar(ActionEvent event) throws GWorkException {
		try {
			activarConfirmacion = true;
			setOpcConfirmacion(getGuardar());
			mostrarMensaje(Util.loadMessageValue("MENSAJE.ALERTA"),
					activarConfirmacion);
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void action_Consultar(ActionEvent event) {
		try {
			setListVehicles(null);
			desactivarBtnAceptar(true);
			Periodo = billingAccountAutoInsuranceService
					.consultarPeriodByAno(getAnoCobro());

			consultar();

			if (getListVehicles() != null && getListVehicles().size() > 0) {
				desactivarBtnAceptar(false);
				// desactivarBtnImprimir(false);
			}
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public List<BillingAccountAutoInsuranceVO> getListVehicles() {
		return listVehicles;
	}

	public void setListVehicles(List<BillingAccountAutoInsuranceVO> listVehicles) {
		this.listVehicles = listVehicles;
	}

	public void consultar() throws GWorkException {
		setListVehicles(billingAccountAutoInsuranceService
				.listVehiclesFlatFile(anoCobro, Periodo));

		if (getListVehicles() == null || getListVehicles().size() <= 0)
			mostrarMensaje(Util.loadMessageValue("AUTOSEGURO.NOCOBRO"), false);
	}

	public void aceptar() {
		try {
			if (getListVehicles() == null || getListVehicles().size() <= 0)
				mostrarMensaje(Util.loadMessageValue("AUTOSEGURO.NOCOBRO"),
						false);
			else {
				setListff(billingAccountAutoInsuranceService.action_aceptar(
						getListVehicles(), loginPage.getLogin(), anoCobro,
						Periodo));

				mostrarMensaje(Util.loadMessageValue("EXITO"), false);
				desactivarBtnAceptar(true);
			}
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public static Integer getGuardar() {
		return GUARDAR_DATOS;
	}

	public static void getGuardar(Integer Generar) {
		GUARDAR_DATOS = Generar;
	}

	public void mostrarMensaje(String mensaje, boolean buttonCancel) {
		PopupMessagePage message = (PopupMessagePage) FacesUtils
				.getManagedBean("popupMessagePage");
		if (message != null)
			message.showPopup(mensaje, buttonCancel);
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

	public String getAnoCobro() {
		return anoCobro;
	}

	public void setAnoCobro(String anoCobro) {
		this.anoCobro = anoCobro;
	}

	public Long getIdVehiculo() {
		return idVehiculo;
	}

	public void setIdVehiculo(Long idVehiculo) {
		this.idVehiculo = idVehiculo;
	}

	public String getCarne() {
		return carne;
	}

	public void setCarne(String carne) {
		this.carne = carne;
	}

	public String getPlcDiplomatica() {
		return plcDiplomatica;
	}

	public void setPlcDiplomatica(String plcDiplomatica) {
		this.plcDiplomatica = plcDiplomatica;
	}

	public String getFtaNombrePeriodo() {
		return ftaNombrePeriodo;
	}

	public boolean isMostrarAceptar() {
		return mostrarAceptar;
	}

	public void setMostrarAceptar(boolean mostrarAceptar) {
		this.mostrarAceptar = mostrarAceptar;
	}

	public BillingAccountAutoInsuranceService getBillingAccountAutoInsuranceService() {
		return billingAccountAutoInsuranceService;
	}

	public void setBillingAccountAutoInsuranceService(
			BillingAccountAutoInsuranceService billingAccountAutoInsuranceService) {
		this.billingAccountAutoInsuranceService = billingAccountAutoInsuranceService;
	}

	public void desactivarBtnAceptar(boolean bandera) {
		if (botonAceptar == null)
			botonAceptar = new HtmlCommandButton();
		botonAceptar.setDisabled(bandera);
	}

	public void desactivarBtnImprimir(boolean bandera) {
		if (botonImprimir == null)
			botonImprimir = new HtmlCommandButton();
		botonImprimir.setDisabled(bandera);
	}

	public HtmlCommandButton getBotonAceptar() {
		return botonAceptar;
	}

	public void setBotonAceptar(HtmlCommandButton botonAceptar) {
		this.botonAceptar = botonAceptar;
	}

	public LoginPage getLoginPage() {
		return loginPage;
	}

	public void setLoginPage(LoginPage loginPage) {
		this.loginPage = loginPage;
	}

	public HtmlCommandButton getBotonImprimir() {
		return botonImprimir;
	}

	public void setBotonImprimir(HtmlCommandButton botonImprimir) {
		this.botonImprimir = botonImprimir;
	}

	public List<FlatFile> getListff() {
		return listff;
	}

	public void setListff(List<FlatFile> listff) {
		this.listff = listff;
	}

	public Period getPeriodo() {
		return Periodo;
	}

	public void setPeriodo(Period periodo) {
		Periodo = periodo;
	}

}
