package geniar.siscar.logic.billing.services.impl;

import geniar.siscar.consults.ConsultsService;
import geniar.siscar.consults.impl.ConsultsServiceImpl;
import geniar.siscar.logic.consultas.ConsultTariff;
import geniar.siscar.logic.consultas.SearchVehicles;
import geniar.siscar.model.AccountingParameters;
import geniar.siscar.model.Vehicles;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

import java.util.Calendar;
import java.util.Date;

public class CheckCostCenterForce {

	/**
	 * Retorna un mensaje validando si el centro de costo esta actualmente
	 * vigente
	 */
	public static String consultarVigenciaCentroCosto(String centroCosto,
			Date fechaIni, Date fechaFin) throws GWorkException {

		String centroCostoActivo = null;

		ConsultsService consultsService = new ConsultsServiceImpl();
		centroCostoActivo = consultsService.consultCostCenterPeriodo(
				centroCosto, fechaIni, fechaFin);

		return centroCostoActivo;
	}

	public static String disponibilidadCombustibleCC(String centroCosto,
			String valorPorcentaje, String placa,
			AccountingParameters parameters) throws GWorkException {

		String mensajeDisponible = null;
		String valorTarifaCombustible = null;
		Double valorCentroCosto = null;
		Double porcentajeCentroCosto = new Double(valorPorcentaje);	

		String cuenta = null;
		Long estadoRetirado = Long.valueOf(Util.loadMessageValue("ESTADO.5")); 
		Vehicles vehicles = SearchVehicles.consultarVehiculosPorPlacaYEstado(placa, estadoRetirado);

		porcentajeCentroCosto = porcentajeCentroCosto / 100D;

		valorTarifaCombustible = new ConsultTariff()
				.consultarValorTarifaCombustible(vehicles.getFuelsTypes()
						.getFutCodigo().longValue());

		valorCentroCosto = new Double(valorTarifaCombustible)
				* porcentajeCentroCosto;

		Calendar calendario = Calendar.getInstance();
		calendario.setTime(new Date());
		int anho = calendario.get(Calendar.YEAR);

		cuenta = parameters.getAccount().getAccNumeroCuenta();

		String disponible = new ConsultsServiceImpl().validarPresupuesto(anho,
				centroCosto, cuenta, null, new Double(valorCentroCosto));

		if(disponible == null){
			mensajeDisponible = "La validación Presupuestal para el " + centroCosto + " con valor igual a " + valorCentroCosto + " fue nula";
		}else{
			if (disponible.equalsIgnoreCase("N"))
				mensajeDisponible = Util.loadErrorMessageValue("ERROR.NODISPPPTO");
		}
		return mensajeDisponible;
	}
}
