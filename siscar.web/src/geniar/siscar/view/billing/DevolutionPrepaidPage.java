package geniar.siscar.view.billing;

import geniar.siscar.logic.billing.services.PrepaidDevolutionService;
import geniar.siscar.model.VOCostCenters;
import geniar.siscar.util.FacesUtils;
import geniar.siscar.util.PopupMessagePage;
import geniar.siscar.util.Util;
import geniar.siscar.view.autenticate.LoginPage;
import gwork.exception.GWorkException;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;

public class DevolutionPrepaidPage {

	private List<VOCostCenters> listCostCenterFuelDevolution;

	private PrepaidDevolutionService prepaidDevolutionService;
	private boolean activarConfirmacion;
	private LoginPage loginPage = (LoginPage) FacesUtils
			.getManagedBean("loginPage");

	public void action_consultarDevolutionPrepaid(ActionEvent actionEvent) {

		try {
			setListCostCenterFuelDevolution(null);
			setListCostCenterFuelDevolution(prepaidDevolutionService
					.listaCostcenterFuelDevolution());

		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

	}

	public void action_generarComprobante(ActionEvent event) {

		try {

			validarDatos(this.listCostCenterFuelDevolution);

			activarConfirmacion = true;
			mostrarMensaje(Util.loadMessageValue("MENSAJE.ALERTA"),
					activarConfirmacion);

		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void generarComrobante() {

		try {
			List<VOCostCenters> listaVOSelecciondos = new ArrayList<VOCostCenters>();
			for (VOCostCenters cargaPrepago : this.listCostCenterFuelDevolution) {

				if (cargaPrepago.isSeleccion())
					listaVOSelecciondos.add(cargaPrepago);
			}

			prepaidDevolutionService.generarComprobanteDevolucionPrepago(
					loginPage.getLogin(), listaVOSelecciondos);
			limpiar();
			mostrarMensaje(Util.loadMessageValue("EXITO"), false);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public PrepaidDevolutionService getPrepaidDevolutionService() {
		return prepaidDevolutionService;
	}

	public void setPrepaidDevolutionService(
			PrepaidDevolutionService prepaidDevolutionService) {
		this.prepaidDevolutionService = prepaidDevolutionService;
	}

	public List<VOCostCenters> getListCostCenterFuelDevolution() {
		return listCostCenterFuelDevolution;
	}

	public void setListCostCenterFuelDevolution(
			List<VOCostCenters> listCostCenterFuelDevolution) {
		this.listCostCenterFuelDevolution = listCostCenterFuelDevolution;
	}

	public void limpiar() {

		setListCostCenterFuelDevolution(null);
	}

	public void action_limpiar(ActionEvent actionEvent) {
		limpiar();
	}

	public void validarDatos(List<VOCostCenters> listaVOCargaPrepago)
			throws GWorkException {

		if (listaVOCargaPrepago == null || listaVOCargaPrepago.size() == 0
				|| listaVOCargaPrepago.isEmpty())
			throw new GWorkException(Util
					.loadErrorMessageValue("LISTA.PREPAGO.NULO"));
	}

	public void mostrarMensaje(String mensaje, boolean buttonCancel) {
		PopupMessagePage message = (PopupMessagePage) FacesUtils
				.getManagedBean("popupMessagePage");
		if (message != null)
			message.showPopup(mensaje, buttonCancel);
	}

	public boolean isActivarConfirmacion() {
		return activarConfirmacion;
	}

	public void setActivarConfirmacion(boolean activarConfirmacion) {
		this.activarConfirmacion = activarConfirmacion;
	}

}
