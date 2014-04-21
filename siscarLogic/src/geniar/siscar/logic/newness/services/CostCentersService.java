package geniar.siscar.logic.newness.services;

import geniar.siscar.model.CostCentersFuel;
import geniar.siscar.model.CostsCenters;
import geniar.siscar.model.CostsCentersVehicles;
import gwork.exception.GWorkException;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

public interface CostCentersService {

	public void guardarCentroCostos(String nombre, String login,
			String porcentaje, String idVehiculo, String idAsginacion,
			Date fechaAsignacion, Date fechaTermina) throws GWorkException;

	public void guardarCentroCostosCombustible(CostsCenters costsCenters,
			String porcentaje, String idVehiculo, String idAsginacion,
			Date fechaAsignacion, Date fechaTermina) throws GWorkException;

	public void actualizarCentrosDeCostos(List<CostsCentersVehicles> list,
			String login, Long idCodigoVehiculo, Date fechaIni,
			Date fechaAsignacion) throws GWorkException;

	public void actualizarCentrosDeCostosCombustible(
			List<CostCentersFuel> list, String login, Long idCodigoVehiculo,
			Date fechaIni, Date fechaAsignacion) throws GWorkException;

	public void crearNovedadCambioCentroCostos(String login, String porcentaje,
			Date fechaInicio, Date fechaFin, Long idCodigoVehiculo,
			Connection connection) throws GWorkException;

	public void eliminarCentroCostosVehiculo(
			CostsCentersVehicles costsCentersVehicles) throws GWorkException;

	/* Guardar centro de costos */
	public Integer guardarCentroCostos(CostsCenters costCenter, String login,
			String porcentaje, String idVehiculo, String idAsginacion,
			Date fechaAsignacion, Date fechaTermina) throws GWorkException;

	public void actualizarCentrosDeCostos(List<CostsCentersVehicles> list,
			String login, Long idCodigoVehiculo, Date fechaIni,
			Date fechaAsignacion, Integer estado_ccv) throws GWorkException;
}
