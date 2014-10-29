package geniar.siscar.logic.parameters.services.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import geniar.siscar.logic.consultas.ConsultTariff;
import geniar.siscar.logic.parameters.services.TariffsHouseCiatService;
import geniar.siscar.model.MoneyType;
import geniar.siscar.model.Tariffs;
import geniar.siscar.model.TariffsTypes;
import geniar.siscar.model.Zones;
import geniar.siscar.persistence.EntityManagerHelper;
import geniar.siscar.persistence.IMoneyTypeDAO;
import geniar.siscar.persistence.ITariffsDAO;
import geniar.siscar.persistence.ITariffsTypesDAO;
import geniar.siscar.persistence.IZonesDAO;
import geniar.siscar.persistence.MoneyTypeDAO;
import geniar.siscar.persistence.TariffsDAO;
import geniar.siscar.persistence.TariffsTypesDAO;
import geniar.siscar.persistence.ZonesDAO;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

public class TariffsHouseCiatServiceImpl implements TariffsHouseCiatService {

	public Tariffs consultarTarifaCasaCiat(Long idTarifa) throws GWorkException {
		return null;
	}

	public void crearTarifaCasaCiat(Zones zona, MoneyType moneda,
			Float trfValor, Date fechaVigencia, Date periodo,
			TariffsTypes tipoTarifa) throws GWorkException {

		Tariffs tariffs = new Tariffs();
		ITariffsDAO tariffsDAO = new TariffsDAO();

		tariffs.setZones(zona);
		tariffs.setMoneyType(moneda);
		tariffs.setTariffsTypes(tipoTarifa);
		tariffs.setTrfValor(trfValor);
		tariffs.setTrfAñoVigencia(periodo);
		tariffs.setTrfFechaInicio(fechaVigencia);
		tariffsDAO.save(tariffs);
		EntityManagerHelper.commit();

	}

	public void eliminarTarifaCasaCiat(Long idTarifa) throws GWorkException {

	}

	public List<Tariffs> listTarifaCasaCiat() throws GWorkException {

		return new TariffsDAO().findAll();
	}

	public void modificarTarifaCasaCiat(Long idZona, Long idMoneda,
			Date fechaVigencia, Integer periodo, Float trfValor)
			throws GWorkException {

		if (EntityManagerHelper.getEntityManager().isOpen()) {
			EntityManagerHelper.getEntityManager().close();
		}

		/** Consultar el objeto zona */
		Zones zones = new Zones();
		IZonesDAO zonesDAO = new ZonesDAO();
		zones = zonesDAO.findById(idZona);

		if (zones == null)
			throw new GWorkException(Util.loadErrorMessageValue("ZONAEXISTEN"));

		/** Consultar el objeto tipo moneda */
		MoneyType moneyType = new MoneyType();
		IMoneyTypeDAO moneyTypeDAO = new MoneyTypeDAO();
		moneyType = moneyTypeDAO.findById(idMoneda);

		if (moneyType == null)
			throw new GWorkException(Util.loadErrorMessageValue("MONEYTYPE"));

		/** validar el periodo */
		Calendar fechaActual = Calendar.getInstance();
		if (periodo < fechaActual.get(Calendar.YEAR))
			throw new GWorkException(Util
					.loadErrorMessageValue("PERIODOINVALIDO"));

		Date periodoVigencia = fechaActual.getTime();

		/** Consultar tipo de tarifa alquiler */
		TariffsTypes tariffsTypes = new TariffsTypes();
		ITariffsTypesDAO tariffsTypesDAO = new TariffsTypesDAO();
		String tipoTarifaConsulta = Util.loadMessageValue("CASA_CIAT_CASA");

		/** Consulta que el tipo de tarifa exista */
		if (tariffsTypesDAO.findByTrtNombre(tipoTarifaConsulta).isEmpty())
			throw new GWorkException(Util
					.loadErrorMessageValue("TIPOTARIFAEXISTE"));
		tariffsTypes = tariffsTypesDAO.findByTrtNombre(tipoTarifaConsulta).get(
				0);

		/** Veficar que la fecha de vigencia no sea menor a la actual */
		// if (fechaVigencia.compareTo(new Date()) < 1)
		// throw new GWorkException(Util
		// .loadErrorMessageValue("FECHAVIGENCIA"));
		ConsultTariff consultTariff = new ConsultTariff();

		if (consultTariff.consultarTarifaActualCasaCiat(idZona, idMoneda)
				.isEmpty()) {
			EntityManagerHelper.beginTransaction();
			crearTarifaCasaCiat(zones, moneyType, trfValor, fechaVigencia,
					periodoVigencia, tariffsTypes);
		} else {

			Tariffs tariffs = consultTariff.consultarTarifaActualCasaCiat(
					idZona, idMoneda).get(0);
			ITariffsDAO tariffsDAO = new TariffsDAO();

			EntityManagerHelper.beginTransaction();
			tariffs.setTrfFechaFin(fechaVigencia);
			tariffsDAO.update(tariffs);

			crearTarifaCasaCiat(zones, moneyType, trfValor, fechaVigencia,
					periodoVigencia, tariffsTypes);
		}
	}

	public Tariffs consultarTarifaActualCasaCiat(Long idZona, Long idMoneda)
			throws GWorkException {
		ConsultTariff consultTariff = new ConsultTariff();

		if (consultTariff.consultarTarifaActualCasaCiat(idZona, idMoneda)
				.isEmpty())
			throw new GWorkException(Util
					.loadErrorMessageValue("TARIFAEXISTEN"));

		return consultTariff.consultarTarifaActualCasaCiat(idZona, idMoneda)
				.get(0);
	}

}
