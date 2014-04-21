package geniar.siscar.logic.newness.services;

import geniar.siscar.model.CostsCentersVehicles;
import geniar.siscar.model.VehiclesAssignation;
import gwork.exception.GWorkException;

import java.util.Date;
import java.util.List;

public interface NewnessLegateeChangeService {

	public void RegistroNovedadesVehiculoCambioAsignatario(String placa, Date fechaFinalAsignacion, Long idCentroCostos,
			List<List<String>> valoresCentroCosotos, String usrLogin, String descripcionNovedad) throws GWorkException, Exception;

	public VehiclesAssignation consultarDatosAsignacion(String placa);

	public void guardarCentroCostos(String nombre, String porcentaje, String idVehiculo, String idAsginacion)
			throws GWorkException;

	public void actualizarCentrosDeCostos(List<CostsCentersVehicles> list, String login, Long idCodigoVehiculo)
			throws GWorkException;

	public void crearNovedadCambioCentroCostos(String login, String porcentaje, Date fechaInicio, Date fechaFin,
			Long idCodigoVehiculo,Date fechaFinal) throws GWorkException;

	public void eliminarCentroCostosVehiculo(CostsCentersVehicles costsCentersVehicles) throws GWorkException;

	public void crearNovedadCambioAsignatario(String login, String descripcion, String carneAsignatario, String carneAsistente,
			String idSolicitud,Date fechaFinal) throws GWorkException;

}
