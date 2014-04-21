package geniar.siscar.view.vehicle;

import geniar.siscar.logic.vehicle.services.ReservesService;
import geniar.siscar.util.FacesUtils;
import geniar.siscar.view.BaseBean;
import gwork.exception.GWorkException;

import javax.faces.event.ActionEvent;

public class CancelReserveUserVehiclePage extends BaseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String numeroSolicitud;
	private String estadoSolicitud;
	private String descripcion;
	// Service
	private ReservesService reservesService;

	/**
	 * @param evento
	 */
	public void action_cancelarReserva(ActionEvent event) {
		try {
			reservesService.CancelarReservaVehiculoUsuario(numeroSolicitud, estadoSolicitud, descripcion);
			FacesUtils.addErrorMessage("Reserva Cancelada con exito");
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	/**
	 * @param evento
	 * 
	 */
	public void action_consultar(ActionEvent event) {

	}
	public String getNumeroSolicitud() {
		return numeroSolicitud;
	}

	public void setNumeroSolicitud(String numeroSolicitud) {
		this.numeroSolicitud = numeroSolicitud;
	}

	public String getEstadoSolicitud() {
		return estadoSolicitud;
	}

	public void setEstadoSolicitud(String estadoSolicitud) {
		this.estadoSolicitud = estadoSolicitud;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public ReservesService getReservesService() {
		return reservesService;
	}

	public void setReservesService(ReservesService reservesService) {
		this.reservesService = reservesService;
	}
}
