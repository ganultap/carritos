package geniar.siscar.logic.consultas;

import java.util.List;

import geniar.siscar.model.Tariffs;
import geniar.siscar.persistence.AccountingParametersDAO;
import geniar.siscar.persistence.VehiclesTypesDAO;
import geniar.siscar.util.ParametersUtil;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

/**
 * The Class DisponibilidadPresupuestalAlquiler.
 */
public class DisponibilidadPresupuestalAlquiler {

	/** The ALQUILER. */
	private static Long ALQUILER = 1L;
	
	/** The ALQUILE r_ terceros. */
	private static Long ALQUILER_TERCEROS = 3L;

	/**
	 * Consultar disponibilidad alquiler.
	 *
	 * @param idTipoVehiculo the id tipo vehiculo
	 * @return the double
	 * @throws GWorkException the g work exception
	 */
	public static Double consultarDisponibilidadAlquiler(Long idTipoVehiculo)
			throws GWorkException {

		Double valorCCAlquiler = null;
		ConsultTariff consultTariff = new ConsultTariff();

		List<Tariffs> listTariffsAlquiler = consultTariff.consultarTarifaActualAlquilerPorTipoTarifaYTipoVehiculo(
				idTipoVehiculo, ParametersUtil.TARIFA_ALQUILER_AUTOSEGURO);
		
		if (listTariffsAlquiler.isEmpty() || listTariffsAlquiler.size() == 0)
			throw new GWorkException(Util.loadErrorMessageValue("TARIFADISPONIBILIDAD")
					+ new VehiclesTypesDAO().findById(idTipoVehiculo).getVhtNombre());
		
		listTariffsAlquiler = consultTariff.consultarTarifaActualAlquilerPorTipoTarifaYTipoVehiculo(
				idTipoVehiculo, ParametersUtil.TARIFA_ALQUILER_DEPRECIACION);
		
		if (listTariffsAlquiler.isEmpty() || listTariffsAlquiler.size() == 0)
			throw new GWorkException(Util.loadErrorMessageValue("TARIFADISPONIBILIDAD")
					+ new VehiclesTypesDAO().findById(idTipoVehiculo).getVhtNombre());
		
		listTariffsAlquiler = consultTariff.consultarTarifaActualAlquilerPorTipoTarifaYTipoVehiculo(
				idTipoVehiculo, ParametersUtil.TARIFA_ALQUILER_ESPACIO_FISICO);
		
		if (listTariffsAlquiler.isEmpty() || listTariffsAlquiler.size() == 0)
			throw new GWorkException(Util.loadErrorMessageValue("TARIFADISPONIBILIDAD")
					+ new VehiclesTypesDAO().findById(idTipoVehiculo).getVhtNombre());
		
		listTariffsAlquiler = consultTariff.consultarTarifaActualAlquilerPorTipoTarifaYTipoVehiculo(
				idTipoVehiculo, ParametersUtil.TARIFA_ALQUILER_MANTENIMIENTO);
		
		if (listTariffsAlquiler.isEmpty() || listTariffsAlquiler.size() == 0)
			throw new GWorkException(Util.loadErrorMessageValue("TARIFADISPONIBILIDAD")
					+ new VehiclesTypesDAO().findById(idTipoVehiculo).getVhtNombre());
		
		listTariffsAlquiler = consultTariff.consultarTarifaActualAlquilerPorTipoTarifaYTipoVehiculo(
				idTipoVehiculo, ParametersUtil.TARIFA_ALQUILER);
		
		if (listTariffsAlquiler.isEmpty() || listTariffsAlquiler.size() == 0)
			throw new GWorkException(Util.loadErrorMessageValue("TARIFADISPONIBILIDAD")
					+ new VehiclesTypesDAO().findById(idTipoVehiculo).getVhtNombre());
		
		Tariffs tariffsAlquiler = listTariffsAlquiler.get(0);
		
		valorCCAlquiler = new Double(tariffsAlquiler.getTrfValor());
		
		return valorCCAlquiler;
	}

	/**
	 * Consultar parametro contable cuenta.
	 *
	 * @param tipoAsignacion the tipo asignacion
	 * @return the string
	 */
	public String consultarParametroContableCuenta(Long tipoAsignacion) {
		String cuenta = null;

		if (tipoAsignacion == ALQUILER)
			cuenta = new AccountingParametersDAO().findById(8L).getAccount()
					.getAccNumeroCuenta();

		if (tipoAsignacion == ALQUILER_TERCEROS)
			cuenta = new AccountingParametersDAO().findById(7L).getAccount()
					.getAccNumeroCuenta();

		return cuenta;
	}
}
