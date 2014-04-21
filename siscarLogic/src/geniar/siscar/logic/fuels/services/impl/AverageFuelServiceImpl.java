package geniar.siscar.logic.fuels.services.impl;

import geniar.siscar.logic.fuels.services.AverageFuelService;
import geniar.siscar.model.ServiceRegistry;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

import java.util.Date;
import java.util.List;

public class AverageFuelServiceImpl implements AverageFuelService {

	public List<ServiceRegistry> listaPromedioCombustible(Date fechaIni,
			Date fechaFin) throws GWorkException {

		List<ServiceRegistry> listaRegistroCombustible = new SearchParametersFuelServiceImp()
				.listaPromedioCombustible(fechaIni, fechaFin);

		if (listaRegistroCombustible == null
				|| listaRegistroCombustible.size() == 0
				|| listaRegistroCombustible.isEmpty())
			throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));
		return listaRegistroCombustible;
	}

	public List<Object[]> promedioPorTipoVehiculo(Date fechaIni, Date fechaFin)
			throws GWorkException {

		List<Object[]> listaPromedio = new SearchParametersFuelServiceImp()
				.promedioPorTipoVehiculo(fechaIni, fechaFin);

		if (listaPromedio == null || listaPromedio.size() == 0
				|| listaPromedio.isEmpty())
			throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));

		return listaPromedio;
	}

	public List<Object[]> detalleConsumoVHC(Date fechaIni, Date fechaFin,
			String idLinea, String combustible, String traccion)
			throws GWorkException {

		List<Object[]> listDetalleConsumo = new SearchParametersFuelServiceImp()
				.detalleConsumoVHC(fechaIni, fechaFin, idLinea, combustible,
						traccion);

		if (listDetalleConsumo == null || listDetalleConsumo.size() == 0
				|| listDetalleConsumo.isEmpty())
			throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));

		return listDetalleConsumo;
	}

	public List<ServiceRegistry> historialConsumoVehiculo(Date fechaIni,
			Date fechaFin, String placa) throws GWorkException {

		List<ServiceRegistry> historialConsumVehiculo = new SearchParametersFuelServiceImp()
				.historialConsumoVehiculo(fechaIni, fechaFin, placa);

		if (historialConsumVehiculo == null
				|| historialConsumVehiculo.size() == 0
				|| historialConsumVehiculo.isEmpty())
			throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));

		return historialConsumVehiculo;
	}

	public String totalGalones(Date fechaIni, Date fechaFin, String placa)
			throws GWorkException {

		return new SearchParametersFuelServiceImp().totalGalones(fechaIni,
				fechaFin, placa);
	}

	public Double totalKMCombustible(Date fechaIni, Date fechaFin, String placa)
			throws GWorkException {

		return new SearchParametersFuelServiceImp().totalKMCombustible(
				fechaIni, fechaFin, placa);
	}
}
