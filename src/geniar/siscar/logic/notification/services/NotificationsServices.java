package geniar.siscar.logic.notification.services;

import java.util.List;

import geniar.siscar.model.VehiclesAssignation;
import gwork.exception.GWorkException;

public interface NotificationsServices {

	public void notificacionFechaDevolucionAsignacion() throws GWorkException,
			Exception;

	public void notificacionReservasProximasAHacerseEfectivas()
			throws GWorkException;

	public void notificacionFechaDevolucionAlquiler() throws GWorkException;

	public void notificacionVehiculosReservadosNoRecogidos()
			throws GWorkException;

	public void notificacionSicarVsActivoFijo() throws GWorkException;

	public List<VehiclesAssignation> consultaVehiculosSicar()
			throws GWorkException;

}