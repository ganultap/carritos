package geniar.siscar.logic.vehicle.services;

import gwork.exception.GWorkException;

import java.util.Date;

public interface ReturnRequestService {

	public void devolucionSolicitudVehiculo(String claseSolicitud, Date fechaIni, Date fechaFin, String descripcion) throws GWorkException;

}
