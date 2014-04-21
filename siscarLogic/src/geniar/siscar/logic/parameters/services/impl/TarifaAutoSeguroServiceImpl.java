package geniar.siscar.logic.parameters.services.impl;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import geniar.siscar.logic.consultas.ConsultTariff;
import geniar.siscar.logic.parameters.services.TarifaAutoSeguroService;
import geniar.siscar.model.FuelsTypes;
import geniar.siscar.model.Lines;
import geniar.siscar.model.Tariffs;
import geniar.siscar.model.TariffsTypes;
import geniar.siscar.model.TractionsTypes;
import geniar.siscar.persistence.EntityManagerHelper;
import geniar.siscar.persistence.FuelsTypesDAO;
import geniar.siscar.persistence.IFuelsTypesDAO;
import geniar.siscar.persistence.ILinesDAO;
import geniar.siscar.persistence.ITariffsDAO;
import geniar.siscar.persistence.ITariffsTypesDAO;
import geniar.siscar.persistence.ITractionsTypesDAO;
import geniar.siscar.persistence.LinesDAO;
import geniar.siscar.persistence.TariffsDAO;
import geniar.siscar.persistence.TariffsTypesDAO;
import geniar.siscar.persistence.TractionsTypesDAO;
import geniar.siscar.util.ParametersUtil;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

public class TarifaAutoSeguroServiceImpl implements TarifaAutoSeguroService {

	public Tariffs consultaAssignmentTariff(Long codeLine, Long codetypeTariff,
			Long codeFuelType) {
		return null;
	}

	public Tariffs consultarTarifaAsignacion(Long idLinea,
			Long idTipoCombustible, Long idTipoTraccion, Long idLocation)
			throws GWorkException {
		return null;
	}

	public TariffsTypes consultarTipoTarifa(Long codeTypeTariff)
			throws GWorkException {
		return null;
	}

	public void crearTarifa(Lines lines, TractionsTypes tipoTraccion,
			FuelsTypes fuelTypes, BigDecimal valor, Date anhoVigencia,
			Date fechaInicio) throws GWorkException {

		/** Consultar tipo de tarifa Asignacion Autoseguro */
		TariffsTypes tariffsTypes = null;
		ITariffsTypesDAO tariffsTypesDAO = new TariffsTypesDAO();

		/** Consulta que el tipo de tarifa exista */
		tariffsTypes = tariffsTypesDAO
				.findById(ParametersUtil.TARIFA_AUTOSEGURO.longValue());
		if (tariffsTypes == null)
			throw new GWorkException(Util
					.loadErrorMessageValue("TIPOTARIFAEXISTE"));

		Tariffs tariffs = new Tariffs();
		ITariffsDAO tariffsDAO = new TariffsDAO();

		tariffs.setLines(lines);
		tariffs.setTractionsTypes(tipoTraccion);
		tariffs.setFuelsTypes(fuelTypes);
		tariffs.setTariffsTypes(tariffsTypes);
		tariffs.setTrfValor(valor.floatValue());
		tariffs.setTrfAñoVigencia(anhoVigencia);
		tariffs.setTrfFechaInicio(fechaInicio);
		tariffsDAO.save(tariffs);
		EntityManagerHelper.commit();

	}

	public void modificarTarifa(Long idLine, Long idTipoTraccion,
			Long idFuelTypes, Integer anhoVigencia, Date fechaInicio,
			BigDecimal valorAutoSeguro) throws GWorkException {

		/** Cargar el objeto linea */
		Lines lines = new Lines();
		ILinesDAO linesDAO = new LinesDAO();
		lines = linesDAO.findById(idLine);
		/** Consultar que la linea exista */
		if (lines == null)
			throw new GWorkException(Util
					.loadErrorMessageValue("LINEAEXISTENO"));

		/** Cargar el objeto tipoTraccion */
		TractionsTypes tractionsTypes = new TractionsTypes();
		ITractionsTypesDAO tractionsTypesDAO = new TractionsTypesDAO();
		tractionsTypes = tractionsTypesDAO.findById(idTipoTraccion);
		/** Consultar que el tipo de traccion exista */
		if (tractionsTypes == null)
			throw new GWorkException(Util
					.loadErrorMessageValue("TIPOTRACCIONEXISTEN"));

		/** Cargar el objeto tipoCombustible */
		FuelsTypes fuelsTypes = new FuelsTypes();
		IFuelsTypesDAO fuelsTypesDAO = new FuelsTypesDAO();
		fuelsTypes = fuelsTypesDAO.findById(idFuelTypes);
		/** Consultar que el tipoCombustible exista */
		if (fuelsTypes == null)
			throw new GWorkException(Util
					.loadErrorMessageValue("TIPOCOMBUSTIBLEEXISTEN"));

		/** validar el periodo */
		Calendar fechaActual = Calendar.getInstance();
		if (anhoVigencia < fechaActual.get(Calendar.YEAR))
			throw new GWorkException(Util
					.loadErrorMessageValue("PERIODOINVALIDO"));

		Date periodoVigencia = fechaActual.getTime();

		ConsultTariff consultTariff = new ConsultTariff();

		/** Veficar que la fecha de vigencia no sea menor a la actual */
		// if (fechaInicio.compareTo(new Date()) < 1)
		// throw new GWorkException(Util
		// .loadErrorMessageValue("FECHAVIGENCIA"));
		/** Si no hay ninguna tarifa registrada */
		if (consultTariff.consultarTarifaAsignacionAutoseguro(idLine,
				idFuelTypes, idTipoTraccion).isEmpty()) {
			EntityManagerHelper.beginTransaction();
			crearTarifa(lines, tractionsTypes, fuelsTypes, valorAutoSeguro,
					periodoVigencia, fechaInicio);
		} else {

			Tariffs tariffs = consultTariff
					.consultarTarifaAsignacionAutoseguro(idLine, idFuelTypes,
							idTipoTraccion).get(0);
			ITariffsDAO tariffsDAO = new TariffsDAO();

			EntityManagerHelper.beginTransaction();
			tariffs.setTrfFechaFin(fechaInicio);
			tariffsDAO.update(tariffs);
			crearTarifa(lines, tractionsTypes, fuelsTypes, valorAutoSeguro,
					periodoVigencia, fechaInicio);
		}

	}

	public Tariffs consultarTarifaAsignacionAutoseguro(Long idLinea,
			Long idTipoCombustible, Long idTipoTraccion) throws GWorkException {

		ConsultTariff consultTariff = new ConsultTariff();

		if (consultTariff.consultarTarifaAsignacionAutoseguro(idLinea,
				idTipoCombustible, idTipoTraccion).isEmpty())
			throw new GWorkException(Util.loadErrorMessageValue("TARIFAASIGNACION"));

		return consultTariff.consultarTarifaAsignacionAutoseguro(idLinea,
				idTipoCombustible, idTipoTraccion).get(0);
	}

}
