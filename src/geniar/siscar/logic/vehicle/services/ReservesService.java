package geniar.siscar.logic.vehicle.services;

import geniar.siscar.model.Requests;
import gwork.exception.GWorkException;

public interface ReservesService {

	public void CancelarReservaVehiculoUsuario(String numeroSolicitud, String estadoSolicitud, String descripcion)throws GWorkException;

	public Requests ConsultarSolicitudAsignacionAlquilerVehiculos(String numeroSolicitud)throws GWorkException;

}
