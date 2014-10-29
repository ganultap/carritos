package geniar.siscar.logic.vehicle.services;

import geniar.siscar.model.Vehicles;
import gwork.exception.GWorkException;

import java.util.Date;

public interface AllocationReturnVehicleService {

	public String devolucionVehiculoAsignacion(Long idAsignacion,
			String vhcPlacaDiplomatica, Date fechaEntrega, Long kilometraje,
			Long tipoAsignatarios, String observacionDev, String login)
			throws GWorkException;

	public void devolucionVehiculoAlquiler(Long idAsignacion,
			String vhcPlacaDiplomatica, Date fechaEntrega, Long kilometraje,
			String observacionDev, String login) throws GWorkException;

	public int consultarEstadoAsignacion(Long estadoVehiculo);

	public Float consultarTarifaAsignacion(Vehicles vehicles, Long tipoUbicacion)
			throws GWorkException;

	public Float consultarTarifaVehiculoUbicacion(Vehicles vehicles)
			throws GWorkException;

	public void envioNotificacion(String PlacaDiplomatica,
			String TipoAsignacion, String NombreAsignatario,
			String CarnetAsignatario, Date FechaEntrega, Date FechaDevolucion,
			Long KilometrajeInicial, Long KilometrajeDevolucion,
			String Observacion, String email) throws GWorkException,
			RuntimeException;

}
