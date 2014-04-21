package geniar.siscar.view.vehicle;

import javax.faces.event.ActionEvent;

import geniar.siscar.logic.vehicle.services.VehiclesRetirementService;
import geniar.siscar.util.FacesUtils;
import geniar.siscar.util.PopupMessagePage;
import geniar.siscar.util.Util;
import geniar.siscar.view.autenticate.LoginPage;
import gwork.exception.GWorkException;

public class VehicleRetirementPage {

	private String vhcPlacaDiplomatica;
	private Long idMotivoRetiro;
	private String descripcion;
	private boolean confirmacion;
	private VehiclesRetirementService retirementService;

	public void retirarVehiculo() throws GWorkException {
		try {
			String idMotivo = null;
			String login = null;

			LoginPage loginPage = (LoginPage) FacesUtils
					.getManagedBean("loginPage");
			if (loginPage != null)
				login = loginPage.getLogin();

			if (idMotivoRetiro != null)
				idMotivo = idMotivoRetiro.toString();

			if (vhcPlacaDiplomatica != null
					&& vhcPlacaDiplomatica.trim().length() == 0)
				throw new GWorkException(Util.loadErrorMessageValue("PLACA"));

			if (idMotivo != null && idMotivo.equalsIgnoreCase("-1"))
				throw new GWorkException(Util
						.loadErrorMessageValue("ID.RETIRO"));

			if (descripcion != null && descripcion.trim().length() == 0)
				throw new GWorkException(Util
						.loadErrorMessageValue("DESCRIPCION.VACIA"));

			retirementService.RetiroVehiculo(vhcPlacaDiplomatica.trim()
					.toUpperCase(), idMotivoRetiro, descripcion, login);

			resetValues();
			mostrarMensaje(Util.loadMessageValue("EXITO.MODIFICAR"), false);
		} catch (final Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void mostrarConfirmacion(final ActionEvent event)
			throws GWorkException {
		try {
			setConfirmacion(true);
			mostrarMensaje(Util.loadMessageValue("MENSAJE.ALERTA"),
					isConfirmacion());

		} catch (final Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void mostrarMensaje(final String mensaje, final boolean buttonCancel) {
		final PopupMessagePage message = (PopupMessagePage) FacesUtils
				.getManagedBean("popupMessagePage");
		if (message != null)
			message.showPopup(mensaje, buttonCancel);
	}

	public void action_limpiar(final ActionEvent event) {
		resetValues();
	}

	public void resetValues() {
		setVhcPlacaDiplomatica(null);
		setDescripcion(null);
		setIdMotivoRetiro(null);
	}

	public String getVhcPlacaDiplomatica() {
		return vhcPlacaDiplomatica;
	}

	public void setVhcPlacaDiplomatica(final String vhcPlacaDiplomatica) {
		this.vhcPlacaDiplomatica = vhcPlacaDiplomatica;
	}

	public Long getIdMotivoRetiro() {
		return idMotivoRetiro;
	}

	public void setIdMotivoRetiro(final Long idMotivoRetiro) {
		this.idMotivoRetiro = idMotivoRetiro;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(final String descripcion) {
		this.descripcion = descripcion;
	}

	public VehiclesRetirementService getRetirementService() {
		return retirementService;
	}

	public void setRetirementService(
			final VehiclesRetirementService retirementService) {
		this.retirementService = retirementService;
	}

	public boolean isConfirmacion() {
		return confirmacion;
	}

	public void setConfirmacion(final boolean confirmacion) {
		this.confirmacion = confirmacion;
	}

}
