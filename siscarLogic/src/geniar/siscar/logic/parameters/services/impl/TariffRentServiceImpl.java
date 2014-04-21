package geniar.siscar.logic.parameters.services.impl;

import geniar.siscar.logic.consultas.ConsultTariff;
import geniar.siscar.logic.parameters.services.TariffRentService;
import geniar.siscar.model.Tariffs;
import geniar.siscar.model.TariffsTypes;
import geniar.siscar.model.VehiclesTypes;
import geniar.siscar.persistence.EntityManagerHelper;
import geniar.siscar.persistence.ITariffsDAO;
import geniar.siscar.persistence.ITariffsTypesDAO;
import geniar.siscar.persistence.IVehiclesTypesDAO;
import geniar.siscar.persistence.TariffsDAO;
import geniar.siscar.persistence.TariffsTypesDAO;
import geniar.siscar.persistence.VehiclesTypesDAO;
import geniar.siscar.util.ParametersUtil;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * The Class TariffRentServiceImpl.
 */
public class TariffRentServiceImpl implements TariffRentService {

	/** The consult tariff. */
	private ConsultTariff consultTariff = new ConsultTariff();
	
	/* (non-Javadoc)
	 * @see geniar.siscar.logic.parameters.services.TariffRentService#consultarTarifaById(java.lang.Long)
	 */
	public Tariffs consultarTarifaById(Long trfId) throws GWorkException {

		/** Validar que exista la tarifa */
		ITariffsDAO tariffsDAO = new TariffsDAO();
		if (tariffsDAO.findById(trfId) == null)
			throw new GWorkException(Util
					.loadErrorMessageValue("TARIFAEXISTEN"));

		return tariffsDAO.findById(trfId);
	}

	/* (non-Javadoc)
	 * @see geniar.siscar.logic.parameters.services.TariffRentService#crearTarifaAlquiler(geniar.siscar.model.VehiclesTypes, geniar.siscar.model.TariffsTypes, java.util.Date, java.util.Date, java.lang.Float, java.lang.Float, java.lang.Float)
	 */
	public void crearTarifaAlquiler(VehiclesTypes tipoVehiculo,
			TariffsTypes tipoTarifa, Date trfAñoVigencia, Date trfFechaInicio,
			Float trfKmIncluido, Float trfKmValorAdicional, Float trfValor)
			throws GWorkException {

		Tariffs tariffs = new Tariffs();
		ITariffsDAO tariffsDAO = new TariffsDAO();

		tariffs.setTariffsTypes(tipoTarifa);
		tariffs.setTrfValor(trfValor);
		tariffs.setTrfKmValorAdicional(trfKmValorAdicional);
		tariffs.setTrfKmIncluido(trfKmIncluido);
		tariffs.setTrfAñoVigencia(trfAñoVigencia);
		tariffs.setTrfFechaInicio(trfFechaInicio);
		tariffs.setVehiclesTypes(tipoVehiculo);
		tariffsDAO.save(tariffs);
	}

	/* (non-Javadoc)
	 * @see geniar.siscar.logic.parameters.services.TariffRentService#listaTarifas()
	 */
	public List<Tariffs> listaTarifas() throws GWorkException {

		ITariffsDAO tariffsDAO = new TariffsDAO();
		if (tariffsDAO.findAll().isEmpty())
			throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));

		return tariffsDAO.findAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seegeniar.siscar.logic.parameters.services.TariffRentService#
	 * modificarTarifaAlquiler(java.lang.Long, java.lang.Integer,
	 * java.util.Date, java.lang.Float, java.lang.Float, java.lang.Float,
	 * java.lang.Float, java.lang.Float, java.lang.Float, java.lang.Float,
	 * java.lang.Float, java.lang.Float)
	 */
	public void modificarTarifaAlquiler(Long idTipoVehiculo,
			Integer trfAnoVigencia, Date trfFechaInicio, Float trfKmIncluido,
			Float trfValorAutoseguro, Float trfValorDepreciacion,
			Float trfValorEspacioFisico, Float trfValorMantenimiento,
			Float trfKmValorAutoseguro, Float trfKmValorDepreciacion,
			Float trfKmValorEspacioFisico, Float trfKmValorMantenimiento) throws GWorkException {
		
		try {
			
		if (EntityManagerHelper.getEntityManager().isOpen())
			EntityManagerHelper.getEntityManager().close();

		/** Cargar el tipo de vehiculo */
		VehiclesTypes vehiclesTypes = new VehiclesTypes();
		IVehiclesTypesDAO vehiclesTypesDAO = new VehiclesTypesDAO();
		vehiclesTypes = vehiclesTypesDAO.findById(idTipoVehiculo);

		/** Verificar que exista el tipo de vehiculo */
		if (vehiclesTypes == null)
			throw new GWorkException(Util
					.loadErrorMessageValue("TIPOVEHICULOEXISTEN"));

		/** validar el periodo */
		Calendar fechaActual = Calendar.getInstance();
		if (trfAnoVigencia < fechaActual.get(Calendar.YEAR))
			throw new GWorkException(Util
					.loadErrorMessageValue("PERIODOINVALIDO"));

		Date periodoVigencia = fechaActual.getTime();

		/** Consultar tipo de tarifa alquiler */
		TariffsTypes tariffsTypes = null;
		ITariffsTypesDAO tariffsTypesDAO = new TariffsTypesDAO();

		EntityManagerHelper.beginTransaction();
		
		tariffsTypes = tariffsTypesDAO.findById(ParametersUtil.TARIFA_ALQUILER_AUTOSEGURO);
		
		if (tariffsTypes == null)
			throw new GWorkException(Util.loadErrorMessageValue("ERROR.TARIFAALQUILER.TIPOTARIFA_AUTOSEGURO"));
		
		modificarTarifaAlquiler(vehiclesTypes, tariffsTypes,
					periodoVigencia, trfFechaInicio, trfKmIncluido,
					trfKmValorAutoseguro, trfValorAutoseguro);
		
		tariffsTypes = tariffsTypesDAO.findById(ParametersUtil.TARIFA_ALQUILER_DEPRECIACION);
		
		if (tariffsTypes == null)
			throw new GWorkException(Util.loadErrorMessageValue("ERROR.TARIFAALQUILER.TIPOTARIFA_DEPRECIACION"));
		
		modificarTarifaAlquiler(vehiclesTypes, tariffsTypes,
					periodoVigencia, trfFechaInicio, trfKmIncluido,
					trfKmValorDepreciacion, trfValorDepreciacion);
		
		tariffsTypes = tariffsTypesDAO.findById(ParametersUtil.TARIFA_ALQUILER_ESPACIO_FISICO);
		
		if (tariffsTypes == null)
			throw new GWorkException(Util.loadErrorMessageValue("ERROR.TARIFAALQUILER.TIPOTARIFA_ESPACIO_FISICO"));
		
		modificarTarifaAlquiler(vehiclesTypes, tariffsTypes,
					periodoVigencia, trfFechaInicio, trfKmIncluido,
					trfKmValorEspacioFisico, trfValorEspacioFisico);
		
		tariffsTypes = tariffsTypesDAO.findById(ParametersUtil.TARIFA_ALQUILER_MANTENIMIENTO);
		
		if (tariffsTypes == null)
			throw new GWorkException(Util.loadErrorMessageValue("ERROR.TARIFAALQUILER.TIPOTARIFA_MANTENIMIENTO"));
		
		modificarTarifaAlquiler(vehiclesTypes, tariffsTypes,
					periodoVigencia, trfFechaInicio, trfKmIncluido,
					trfKmValorMantenimiento, trfValorMantenimiento);
		
		tariffsTypes = tariffsTypesDAO.findById(ParametersUtil.TARIFA_ALQUILER);
		
		if (tariffsTypes == null)
			throw new GWorkException(Util.loadErrorMessageValue("ERROR.TARIFAALQUILER.TIPOTARIFA_ALQUILER"));
		
		Float trfValor = trfValorAutoseguro + trfValorDepreciacion 
			+ trfValorEspacioFisico + trfValorMantenimiento; 
		
		Float trfValorKm = trfKmValorMantenimiento + trfKmValorEspacioFisico + trfKmValorDepreciacion + trfKmValorAutoseguro;
		
		modificarTarifaAlquiler(vehiclesTypes, tariffsTypes,
					periodoVigencia, trfFechaInicio, trfKmIncluido,
					trfValorKm, trfValor);
		
		EntityManagerHelper.commit();
		
		} catch (Exception e) {
			EntityManagerHelper.rollback();
		} finally{
			EntityManagerHelper.closeEntityManager();
		}
	}

	/**
	 * Modificar tarifa alquiler.
	 *
	 * @param vehiclesTypes the vehicles types
	 * @param tariffsTypes the tariffs types
	 * @param periodoVigencia the periodo vigencia
	 * @param trfFechaInicio the trf fecha inicio
	 * @param trfKmIncluido the trf km incluido
	 * @param trfKmValorAdicional the trf km valor adicional
	 * @param trfValor the trf valor
	 * @throws GWorkException the g work exception
	 */
	public void modificarTarifaAlquiler(VehiclesTypes vehiclesTypes, TariffsTypes tariffsTypes, Date periodoVigencia, 
			Date trfFechaInicio, Float trfKmIncluido, Float trfKmValorAdicional, Float trfValor) throws GWorkException {
		
		List<Tariffs> listTariffs = consultTariff.consultarTarifaActualAlquilerPorTipoTarifaYTipoVehiculo(
				vehiclesTypes.getVhtCodigo(), tariffsTypes.getTrtId());
		
		if (listTariffs.isEmpty()) {
			crearTarifaAlquiler(vehiclesTypes, tariffsTypes, periodoVigencia,
					trfFechaInicio, trfKmIncluido, trfKmValorAdicional,
					trfValor);
		} else {
			Tariffs tariffs = listTariffs.get(0);
			ITariffsDAO tariffsDAO = new TariffsDAO();

			tariffs.setTrfFechaFin(trfFechaInicio);
			tariffsDAO.update(tariffs);
			
			crearTarifaAlquiler(vehiclesTypes, tariffsTypes, periodoVigencia,
					trfFechaInicio, trfKmIncluido, trfKmValorAdicional,
					trfValor);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seegeniar.siscar.logic.parameters.services.TariffRentService#
	 * consultarTarifaActualAlquilerPorTipoTarifaYTipoVehiculo(java.lang.Long,
	 * java.lang.Long)
	 */
	public Tariffs consultarTarifaActualAlquilerPorTipoTarifaYTipoVehiculo(
			Long idTipoVehiculo, Long idTipoTarifa) throws GWorkException {	
		
		List<Tariffs> listTariffs = new ArrayList<Tariffs>();
		
		listTariffs = consultTariff
				.consultarTarifaActualAlquilerPorTipoTarifaYTipoVehiculo(
						idTipoVehiculo, idTipoTarifa);
		
		if (listTariffs.isEmpty())
			return null;
		else
			return listTariffs.get(0);
	}
}