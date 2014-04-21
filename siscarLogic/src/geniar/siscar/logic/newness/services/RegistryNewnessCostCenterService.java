package geniar.siscar.logic.newness.services;

import geniar.siscar.model.CostCentersFuel;
import geniar.siscar.model.CostsCenters;
import geniar.siscar.model.CostsCentersVehicles;
import geniar.siscar.model.VehiclesAssignation;
import gwork.exception.GWorkException;
import java.util.Date;
import java.util.List;

public interface RegistryNewnessCostCenterService {

	public void RegistroNovedadesVehiculoCambioCentroCosto(String placa,
			Date fechaFinalAsignacion, Long idCentroCostos,
			List<List<String>> valoresCentroCosotos, String usrLogin,
			String descripcionNovedad) throws GWorkException, Exception;

	public VehiclesAssignation consultarDatosAsignacion(String placa)
			throws GWorkException;

	public void guardarCentroCostos(String nombre, String login,
			String porcentaje, String idVehiculo, String idAsginacion,
			Date FechaAsignacion, Date fechaTermina) throws GWorkException;

	public void guardarCentroCostosCombustible(CostsCenters costsCenters,
			String porcentaje, String idVehiculo, String idAsginacion,
			Date fechaAsignacion, Date fechaTermina) throws GWorkException;

	public void actualizarCentrosDeCostos(List<CostsCentersVehicles> list,
			String login, Long idCodigoVehiculo, Date fechaIni,
			Date fechaAsignacion) throws GWorkException;

	public void crearNovedadCambioCentroCostos(String login, String porcentaje,
			Date fechaInicio, Date fechaFin, Long idCodigoVehiculo)
			throws GWorkException;

	public void eliminarCentroCostosVehiculo(
			CostsCentersVehicles costsCentersVehicles) throws GWorkException;

	public void validarFechaFinalAsignacion(Date fechaAsignacion,
			Date fechaInicial, Date fechaTermina) throws GWorkException;

	public void ValidarCentrosDeCostosActuales(Long idVehiculo,
			Long idAsignacion, Date fechaAsignacion) throws GWorkException;

	public List<CostsCenters> consultarCentroCostos(String centroCostos)
			throws GWorkException;

	/* Nuevos */
	public Integer guardarCentroCostos(CostsCenters costCenter, String login,
			String porcentaje, String idVehiculo, String idAsginacion,
			Date FechaAsignacion, Date fechaTermina) throws GWorkException;

	public void actualizarCentrosDeCostos(List<CostsCentersVehicles> list,
			String login, Long idCodigoVehiculo, Date fechaIni,
			Date fechaAsignacion, Integer estado_ccv) throws GWorkException;

	public void actualizarCentrosDeCostosCombustible(
			List<CostCentersFuel> list, String login, Long idCodigoVehiculo,
			Date fechaIni, Date fechaAsignacion, Integer estado_ccv)
			throws GWorkException;
}
