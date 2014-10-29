package geniar.siscar.logic.newness.services;

import geniar.siscar.model.VehiclesAssignation;
import gwork.exception.GWorkException;

import java.util.Date;
import java.util.List;

public interface NewnessVehicleLocationService {

	public void RegistroNovedadesVehiculoCambioUbicacion(String placa, Date fechaFinalAsignacion, Long idCentroCostos,
			List<List<String>> valoresCentroCosotos, String usrLogin, String descripcionNovedad) throws GWorkException, Exception;

	public VehiclesAssignation consultarDatosAsignacion(String placa)throws GWorkException;

	public void actualizarTipoUbicacionVehiculo(String login,String descripcion,Long idCodigoVehiculo, Long idUbicacion,Long idTipoUbicacion,Long idAsignacion) throws GWorkException;

	public void guardarNovedadCambioUbicacionVehiculo(String login, String ubicacion, String descripcion, Long idVehiculo)
			throws GWorkException;

}
