package geniar.siscar.view.billing;

import java.util.List;
import javax.faces.event.ActionEvent;
import com.icesoft.faces.component.ext.HtmlCommandButton;

import geniar.siscar.model.Period;
import geniar.siscar.model.VOprepaidFuelProof;
import geniar.siscar.persistence.PeriodDAO;

import geniar.siscar.util.FacesUtils;
import geniar.siscar.util.PopupMessagePage;
import geniar.siscar.util.Util;
import geniar.siscar.view.autenticate.LoginPage;
import gwork.exception.GWorkException;

public class PrepaidFuelProofPage {

	private Integer opcConfirmacion;
	private static int GUARDAR_DATOS = 1;
	private boolean activarConfirmacion;
	private String anoCobro;
	private Period Periodo;

	private HtmlCommandButton botonAceptar;

	private LoginPage loginPage = (LoginPage) FacesUtils
			.getManagedBean("loginPage");

	private List<VOprepaidFuelProof> listPrepaidFuelProof;
	private geniar.siscar.logic.billing.services.PrepaidFuelProofService prepaidFuelProofService;

	public PrepaidFuelProofPage() {
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
		setAnoCobro((new java.text.SimpleDateFormat("yyyy"))
				.format(new java.util.Date()));
		try {
			consultar(getAnoCobro());
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
			limpiar();
		}
	}

	public void limpiar() {
		setListPrepaidFuelProof(null);
	}

	public void consultar(String ano) throws GWorkException {
		try {
			setListPrepaidFuelProof(prepaidFuelProofService
					.listprepaidFuelVehicles(new PeriodDAO().findById(1L)));
			if (getListPrepaidFuelProof() != null
					&& getListPrepaidFuelProof().size() > 0)
				desactivarBtnAceptar(false);
			else {
				desactivarBtnAceptar(true);
				FacesUtils.addErrorMessage(Util
						.loadErrorMessageValue("DATOS.PREPAGO.NULL"));
			}
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void aceptar() {

		try {
			Periodo = prepaidFuelProofService
					.consultarPeriodByAno(getAnoCobro());

		} catch (GWorkException e1) {
			FacesUtils.addErrorMessage(e1.getMessage());
		}

		try {
			prepaidFuelProofService.generatePrepaidFuelProof(loginPage
					.getLogin(), getListPrepaidFuelProof(), Periodo);

			mostrarMensaje(("Comprobantes generados exitosamente"), false);
			limpiar();
		} catch (GWorkException e1) {
			FacesUtils.addErrorMessage(e1.getMessage());
		}
	}

	public static int getGUARDAR_DATOS() {
		return GUARDAR_DATOS;
	}

	public static void setGUARDAR_DATOS(int guardar_datos) {
		GUARDAR_DATOS = guardar_datos;
	}

	public List<VOprepaidFuelProof> getListPrepaidFuelProof() {
		return listPrepaidFuelProof;
	}

	public void setListPrepaidFuelProof(
			List<VOprepaidFuelProof> listPrepaidFuelProof) {
		this.listPrepaidFuelProof = listPrepaidFuelProof;
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

	public Integer getOpcConfirmacion() {
		return opcConfirmacion;
	}

	public void setOpcConfirmacion(Integer opcConfirmacion) {
		this.opcConfirmacion = opcConfirmacion;
	}

	public void desactivarBtnAceptar(boolean bandera) {
		if (botonAceptar == null)
			botonAceptar = new HtmlCommandButton();
		botonAceptar.setDisabled(bandera);
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

	public geniar.siscar.logic.billing.services.PrepaidFuelProofService getPrepaidFuelProofService() {
		return prepaidFuelProofService;
	}

	public void setPrepaidFuelProofService(
			geniar.siscar.logic.billing.services.PrepaidFuelProofService prepaidFuelProofService) {
		this.prepaidFuelProofService = prepaidFuelProofService;
	}

	public boolean isActivarConfirmacion() {
		return activarConfirmacion;
	}

	public void setActivarConfirmacion(boolean activarConfirmacion) {
		this.activarConfirmacion = activarConfirmacion;
	}

	public String getAnoCobro() {
		return anoCobro;
	}

	public void setAnoCobro(String anoCobro) {
		this.anoCobro = anoCobro;
	}

	public Period getPeriodo() {
		return Periodo;
	}

	public void setPeriodo(Period periodo) {
		Periodo = periodo;
	}
}
