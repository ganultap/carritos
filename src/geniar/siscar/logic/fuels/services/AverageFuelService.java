package geniar.siscar.logic.fuels.services;

import geniar.siscar.model.ServiceRegistry;
import gwork.exception.GWorkException;

import java.util.Date;
import java.util.List;

public interface AverageFuelService {

	public List<ServiceRegistry> listaPromedioCombustible(Date fechaIni,
			Date fechaFin) throws GWorkException;

	public List<Object[]> promedioPorTipoVehiculo(Date fechaIni, Date fechaFin)
			throws GWorkException;

	public List<Object[]> detalleConsumoVHC(Date fechaIni, Date fechaFin,
			String idLinea, String combustible, String traccion)
			throws GWorkException;

	public List<ServiceRegistry> historialConsumoVehiculo(Date fechaIni,
			Date fechaFin, String placa) throws GWorkException;

	public String totalGalones(Date fechaIni, Date fechaFin, String placa)
			throws GWorkException;

	public Double totalKMCombustible(Date fechaIni, Date fechaFin, String placa)
			throws GWorkException;
}
