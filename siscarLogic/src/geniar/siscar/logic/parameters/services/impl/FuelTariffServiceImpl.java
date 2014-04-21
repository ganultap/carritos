package geniar.siscar.logic.parameters.services.impl;

import geniar.siscar.logic.consultas.ConsultTariff;
import geniar.siscar.logic.consultas.SearchFuelTariff;
import geniar.siscar.logic.parameters.services.FuelTariffService;
import geniar.siscar.model.FuelsTypes;
import geniar.siscar.model.Tariffs;
import geniar.siscar.model.TariffsTypes;
import geniar.siscar.persistence.EntityManagerHelper;
import geniar.siscar.persistence.TariffsDAO;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class FuelTariffServiceImpl implements FuelTariffService {

	/*
	 * (non-Javadoc)
	 * 
	 * @see geniar.siscar.logic.parameters.services.FuelTariffService
	 *      #consultarTarifaCombustible(java.lang.Long, java.lang.Long,
	 *      java.lang.String)
	 */
	public Tariffs consultarTarifaCombustible(Long idTipoCombustible,
			Long idTipoTarifa, String nombreTipoTarifa) throws GWorkException {
		validarSession();
		TariffsTypes tariffsTypes = new SearchFuelTariff()
				.consultarTariffsTypes(idTipoTarifa, nombreTipoTarifa);

		return new ConsultTariff().consultarTarifaActual(idTipoCombustible,
				idTipoTarifa, tariffsTypes.getTrtNombre(), null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see geniar.siscar.logic.parameters.services.FuelTariffService
	 *      #crearTarifaCombustible(java.lang.Long, java.lang.Long,
	 *      java.lang.String, java.util.Date, java.util.Date, java.lang.Long,
	 *      java.lang.Long)
	 */
	@SuppressWarnings("static-access")
	public void crearTarifaCombustible(Long idTipoCombustible,
			Long idTipoTarifa, String nombreTipoTarifa, Date fechaDesde,
			Date fechaHasta, Long vigencia, Float valorTarifa)
			throws GWorkException {

		validarSession();

		SearchFuelTariff fuelTariff = new SearchFuelTariff();
		FuelsTypes fuelsTypes = fuelTariff.consultarFuelType(idTipoCombustible);

		if (fuelsTypes == null) {
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.COMBUSNOEXISTE"));
		}

		TariffsTypes tariffsTypes = fuelTariff.consultarTariffsTypes(
				idTipoTarifa, nombreTipoTarifa);

		if (tariffsTypes == null) {
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.TIPOTARIFNOEXIS"));
		}

		Tariffs tariffs = null;

		tariffs = consultarTarifaCombustible(idTipoCombustible, idTipoTarifa,
				nombreTipoTarifa);

		if (tariffs != null) {
			tariffs.setTrfFechaFin(new Date());

			EntityManagerHelper.beginTransaction();
			new TariffsDAO().update(tariffs);
			EntityManagerHelper.commit();
		}

		Calendar calendar = Calendar.getInstance();
		calendar.set(calendar.YEAR, Integer.parseInt("" + vigencia));

		Tariffs nuevaTariffs = new Tariffs();

		nuevaTariffs.setFuelsTypes(fuelsTypes);
		nuevaTariffs.setTariffsTypes(tariffsTypes);
		nuevaTariffs.setTrfAñoVigencia(calendar.getTime());
		nuevaTariffs.setTrfValor(valorTarifa.floatValue());
		nuevaTariffs.setTrfFechaInicio(fechaDesde);

		try {
			EntityManagerHelper.beginTransaction();
			new TariffsDAO().update(nuevaTariffs);
			EntityManagerHelper.commit();
			// limpiarSession();
		} catch (RuntimeException e) {
			// limpiarSession();
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.ACTUALTARIFA"));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see geniar.siscar.logic.parameters.services.
	 *      FuelTariffService#consultarTiposTarifa()
	 */
	public List<TariffsTypes> consultarTiposTarifa() throws GWorkException {
		validarSession();
		return new ConsultTariff().consultarTarifasCombustible();
	}

	public void validarSession() {
		if (EntityManagerHelper.getEntityManager().isOpen())
			EntityManagerHelper.getEntityManager().close();
	}

	// public void limpiarSession() {
	// EntityManagerHelper.getEntityManager().clear();
	// EntityManagerHelper.closeEntityManager();
	// }
}
