package geniar.siscar.logic.parameters.services.impl;

import geniar.siscar.logic.consultas.ConsultTariff;
import geniar.siscar.logic.parameters.services.TariffService;
import geniar.siscar.model.FuelsTypes;
import geniar.siscar.model.Lines;
import geniar.siscar.model.Locations;
import geniar.siscar.model.Tariffs;
import geniar.siscar.model.TariffsTypes;
import geniar.siscar.model.TractionsTypes;
import geniar.siscar.persistence.EntityManagerHelper;
import geniar.siscar.persistence.FuelsTypesDAO;
import geniar.siscar.persistence.IFuelsTypesDAO;
import geniar.siscar.persistence.ILinesDAO;
import geniar.siscar.persistence.ILocationsDAO;
import geniar.siscar.persistence.ITariffsDAO;
import geniar.siscar.persistence.ITariffsTypesDAO;
import geniar.siscar.persistence.ITractionsTypesDAO;
import geniar.siscar.persistence.LinesDAO;
import geniar.siscar.persistence.LocationsDAO;
import geniar.siscar.persistence.TariffsDAO;
import geniar.siscar.persistence.TariffsTypesDAO;
import geniar.siscar.persistence.TractionsTypesDAO;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * The Class TariffServiceImpl.
 *
 * @author Geniar
 */
public class TariffServiceImpl implements TariffService {

	/* (non-Javadoc)
	 * @see geniar.siscar.logic.parameters.services.TariffService#crearTarifa(geniar.siscar.model.Lines, geniar.siscar.model.TractionsTypes, geniar.siscar.model.FuelsTypes, geniar.siscar.model.Locations, geniar.siscar.model.TariffsTypes, java.lang.Float, java.util.Date, java.util.Date)
	 */
	public void crearTarifa(Lines lines, TractionsTypes tipoTraccion,
			FuelsTypes fuelTypes, Locations locations, TariffsTypes tipoTarifa,
			Float valor, Date anhoVigencia, Date fechaInicio)
			throws GWorkException {
		
		Tariffs tariffs = new Tariffs();
		ITariffsDAO tariffsDAO = new TariffsDAO();
		
		tariffs.setLines(lines);
		tariffs.setTractionsTypes(tipoTraccion);
		tariffs.setFuelsTypes(fuelTypes);
		tariffs.setLocations(locations);
		tariffs.setTariffsTypes(tipoTarifa);
		tariffs.setTrfValor(valor);
		tariffs.setTrfAñoVigencia(anhoVigencia);
		tariffs.setTrfFechaInicio(fechaInicio);
		tariffsDAO.save(tariffs);
		EntityManagerHelper.commit();

	}

	/* (non-Javadoc)
	 * @see geniar.siscar.logic.parameters.services.TariffService#modificarTarifa(java.lang.Long, java.lang.Long, java.lang.Long, java.lang.Long, java.lang.Integer, java.util.Date, java.lang.Float, java.lang.Float, java.lang.Float)
	 */
	public void modificarTarifa(Long idLine, Long idTipoTraccion,
			Long idFuelTypes, Long idLocation, 
			Integer anhoVigencia, Date fechaInicio, Float valorDepreciacion,
			Float valorMantenimiento, Float valorAutoSeguro)
			throws GWorkException {

		validateData(idLine, idLocation, idTipoTraccion,
				anhoVigencia, fechaInicio);

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

		/** Cargar el objeto Ubicacion */
		Locations locations = new Locations();
		ILocationsDAO locationsDAO = new LocationsDAO();
		locations = locationsDAO.findById(idLocation);
		/** Consultar que la ubicacion exista */
		if (locations == null)
			throw new GWorkException(Util
					.loadErrorMessageValue("UBICACIONEXISTEN"));
		
		/** validar el periodo */
		Calendar fechaActual = Calendar.getInstance();
		if (anhoVigencia < fechaActual.get(Calendar.YEAR))
			throw new GWorkException(Util.loadErrorMessageValue("FECHAVALIDAN"));

		Date periodoVigencia = fechaActual.getTime();

		/** Veficar que la fecha de vigencia no sea menor a la actual */
		if (fechaInicio.compareTo(new Date()) < 1)
			throw new GWorkException(Util
					.loadErrorMessageValue("FECHAVIGENCIA"));

		crearTarifaMantenimiento(lines, tractionsTypes, fuelsTypes, locations, valorMantenimiento, periodoVigencia, fechaInicio);
		crearTarifaDepreciacion(lines, tractionsTypes, fuelsTypes, locations, valorDepreciacion, periodoVigencia, fechaInicio);
		crearTarifaAutoseguro(lines, tractionsTypes, fuelsTypes, locations, valorAutoSeguro, periodoVigencia, fechaInicio);
		

	}

	/**
	 * Validate data.
	 *
	 * @param idLine the id line
	 * @param idLocation the id location
	 * @param idTipoTraccion the id tipo traccion
	 * @param anhoVigencia the anho vigencia
	 * @param fechaInicio the fecha inicio
	 * @throws GWorkException the g work exception
	 */
	private void validateData(Long idLine, Long idLocation, 
			Long idTipoTraccion, Integer anhoVigencia, Date fechaInicio)
			throws GWorkException {

		if (idLine.longValue() == 0 || idLine == null) 
		
			throw new GWorkException(Util.loadErrorMessageValue("NOMBRELINEA"));
		
		if (idLocation.longValue() == 0 || idLocation == null) 
			
			throw new GWorkException(Util
					.loadErrorMessageValue("NOMBREUBICACION"));
	
		
		if (anhoVigencia == null || anhoVigencia.longValue() == 0) 
			
			throw new GWorkException(Util.loadErrorMessageValue("FECHAVALIDAN"));
		
		if (fechaInicio == null)
		
			throw new GWorkException(Util
					.loadErrorMessageValue("FECHAVIGENCIA"));
		

		if (idTipoTraccion.longValue() == 0 || idTipoTraccion == null) 
			throw new GWorkException(Util.loadErrorMessageValue("TRACCION"));
		

	}

	/* (non-Javadoc)
	 * @see geniar.siscar.logic.parameters.services.TariffService#consultarTipoTarifa(java.lang.Long)
	 */
	public TariffsTypes consultarTipoTarifa(Long codeTypeTariff)
			throws GWorkException {
		ITariffsTypesDAO tariffTypesDAO = new TariffsTypesDAO();
		TariffsTypes tariffTypes = tariffTypesDAO.findById(codeTypeTariff);
		if (tariffTypes == null) {
			throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));
		}
		return tariffTypes;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seegeniar.siscar.logic.parameters.services.TariffService#
	 * consultarTarifaAsignacion(java.lang.Long, java.lang.Long, java.lang.Long,
	 * java.lang.Long)
	 */
	public Tariffs consultarTarifaAsignacion(Long idLinea,
			Long idTipoCombustible, Long idTipoTraccion,
			Long idTipoTarifa)
			throws GWorkException {
		
		ConsultTariff  consultTariff = new ConsultTariff();
		List<Tariffs> listaTariffs = new ArrayList<Tariffs>();
		
		listaTariffs = consultTariff.consultarTarifaAsignacion(idLinea, idTipoCombustible, idTipoTraccion, idTipoTarifa);
		
		if(listaTariffs.isEmpty()){
			
//			Brands brands = new BrandsDAO().findById(idLinea);
			Lines lines = new LinesDAO().findById(idLinea);
			FuelsTypes fuelsTypes = new FuelsTypesDAO().findById(idTipoCombustible);
			TractionsTypes tractionsTypes = new TractionsTypesDAO().findById(idTipoTraccion);
			
			throw new GWorkException(Util
					.loadErrorMessageValue("TARIFAASIGNACION")
					+ " "
					+ Util.loadErrorMessageValue("MARCA.TARIF.ERROR")
					+ lines.getBrands().getBrnNombre()
					+ " "
					+ Util.loadErrorMessageValue("LINEA.TARIF.ERROR")
					+ lines.getLnsNombre()
					+ " "
					+ Util.loadErrorMessageValue("COMBUS.TARIF.ERROR")
					+ fuelsTypes.getFutNombre()
					+ " "
					+ Util.loadErrorMessageValue("TRACCION.TARIF.ERROR")
					+ tractionsTypes.getTctNombre());
		}
		
		return listaTariffs.get(0);
	}

	/**
	 * Crear tarifa mantenimiento.
	 *
	 * @param lines the lines
	 * @param tipoTraccion the tipo traccion
	 * @param fuelTypes the fuel types
	 * @param locations the locations
	 * @param valorMantenimiento the valor mantenimiento
	 * @param anhoVigencia the anho vigencia
	 * @param fechaInicio the fecha inicio
	 * @throws GWorkException the g work exception
	 */
	public void crearTarifaMantenimiento(Lines lines, TractionsTypes tipoTraccion,
			FuelsTypes fuelTypes, Locations locations, 
			Float valorMantenimiento, Date anhoVigencia, Date fechaInicio)
			throws GWorkException{
		
		/**Consultar tipo de tarifa Asignacion Mantenimiento*/
		TariffsTypes tariffsTypes = new TariffsTypes();
		ITariffsTypesDAO tariffsTypesDAO = new TariffsTypesDAO();

		String tipoTarifaConsulta = Util.loadMessageValue("ASIGNACIONMANTENIMIENTO");
		/**Consulta que el tipo de tarifa exista*/ 
		if (tariffsTypesDAO.findByTrtNombre(tipoTarifaConsulta).isEmpty())
			throw new GWorkException(Util
					.loadErrorMessageValue("TIPOTARIFAEXISTE"));
		tariffsTypes = tariffsTypesDAO.findByTrtNombre(tipoTarifaConsulta).get(0); 
		
		Tariffs tariffs = new Tariffs();
		ITariffsDAO tariffsDAO = new TariffsDAO();
		
		tariffs.setLines(lines);
		tariffs.setTractionsTypes(tipoTraccion);
		tariffs.setFuelsTypes(fuelTypes);
		tariffs.setLocations(locations);
		tariffs.setTariffsTypes(tariffsTypes);
		tariffs.setTrfValor(valorMantenimiento);
		tariffs.setTrfAñoVigencia(anhoVigencia);
		tariffs.setTrfFechaInicio(fechaInicio);
		tariffsDAO.save(tariffs);
		EntityManagerHelper.commit();
		
	}
	
	/**
	 * Crear tarifa autoseguro.
	 *
	 * @param lines the lines
	 * @param tipoTraccion the tipo traccion
	 * @param fuelTypes the fuel types
	 * @param locations the locations
	 * @param valorAutoseguro the valor autoseguro
	 * @param anhoVigencia the anho vigencia
	 * @param fechaInicio the fecha inicio
	 * @throws GWorkException the g work exception
	 */
	public void crearTarifaAutoseguro(Lines lines, TractionsTypes tipoTraccion,
			FuelsTypes fuelTypes, Locations locations, 
			Float valorAutoseguro, Date anhoVigencia, Date fechaInicio)
			throws GWorkException{
		
		/**Consultar tipo de tarifa Asignacion Autoseguro*/
		TariffsTypes tariffsTypes = new TariffsTypes();
		ITariffsTypesDAO tariffsTypesDAO = new TariffsTypesDAO();

		String tipoTarifaConsulta = Util.loadMessageValue("ASIGNACIONAUTOSEGURO");
		/**Consulta que el tipo de tarifa exista*/ 
		if (tariffsTypesDAO.findByTrtNombre(tipoTarifaConsulta).isEmpty())
			throw new GWorkException(Util
					.loadErrorMessageValue("TIPOTARIFAEXISTE"));
		tariffsTypes = tariffsTypesDAO.findByTrtNombre(tipoTarifaConsulta).get(0); 
		
		Tariffs tariffs = new Tariffs();
		ITariffsDAO tariffsDAO = new TariffsDAO();
		
		tariffs.setLines(lines);
		tariffs.setTractionsTypes(tipoTraccion);
		tariffs.setFuelsTypes(fuelTypes);
		tariffs.setLocations(locations);
		tariffs.setTariffsTypes(tariffsTypes);
		tariffs.setTrfValor(valorAutoseguro);
		tariffs.setTrfAñoVigencia(anhoVigencia);
		tariffs.setTrfFechaInicio(fechaInicio);
		tariffsDAO.save(tariffs);
		EntityManagerHelper.commit();
		
	}
	
	/**
	 * Crear tarifa depreciacion.
	 *
	 * @param lines the lines
	 * @param tipoTraccion the tipo traccion
	 * @param fuelTypes the fuel types
	 * @param locations the locations
	 * @param valorDepreciacion the valor depreciacion
	 * @param anhoVigencia the anho vigencia
	 * @param fechaInicio the fecha inicio
	 * @throws GWorkException the g work exception
	 */
	public void crearTarifaDepreciacion(Lines lines, TractionsTypes tipoTraccion,
			FuelsTypes fuelTypes, Locations locations, 
			Float valorDepreciacion, Date anhoVigencia, Date fechaInicio)
			throws GWorkException{
		
		/**Consultar tipo de tarifa Asignacion Depreciacion*/
		TariffsTypes tariffsTypes = new TariffsTypes();
		ITariffsTypesDAO tariffsTypesDAO = new TariffsTypesDAO();

		String tipoTarifaConsulta = Util.loadMessageValue("ASIGNACIONAUTOSEGURO");
		/**Consulta que el tipo de tarifa exista*/ 
		if (tariffsTypesDAO.findByTrtNombre(tipoTarifaConsulta).isEmpty())
			throw new GWorkException(Util
					.loadErrorMessageValue("TIPOTARIFAEXISTE"));
		tariffsTypes = tariffsTypesDAO.findByTrtNombre(tipoTarifaConsulta).get(0); 
		
		Tariffs tariffs = new Tariffs();
		ITariffsDAO tariffsDAO = new TariffsDAO();
		
		tariffs.setLines(lines);
		tariffs.setTractionsTypes(tipoTraccion);
		tariffs.setFuelsTypes(fuelTypes);
		tariffs.setLocations(locations);
		tariffs.setTariffsTypes(tariffsTypes);
		tariffs.setTrfValor(valorDepreciacion);
		tariffs.setTrfAñoVigencia(anhoVigencia);
		tariffs.setTrfFechaInicio(fechaInicio);
		tariffsDAO.save(tariffs);
		EntityManagerHelper.commit();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seegeniar.siscar.logic.parameters.services.TariffService#
	 * consultarTarifaAsignacionEspacioFisico(java.lang.Long, java.lang.Long,
	 * java.lang.Long)
	 */
	public Tariffs consultarTarifaAsignacionEspacioFisico(Long idLinea,
			Long idTipoCombustible, Long idTipoTraccion) throws GWorkException {
		ConsultTariff consultTariff = new ConsultTariff();
		if (consultTariff.consultarTarifaAsignacionEspacioFisico(idLinea,
				idTipoCombustible, idTipoTraccion).isEmpty())
			//throw new GWorkException(Util.loadErrorMessageValue("TARIFAASIGNACION"));
			return null;
		else
			return consultTariff.consultarTarifaAsignacionEspacioFisico(idLinea,
				idTipoCombustible, idTipoTraccion).get(0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * geniar.siscar.logic.parameters.services.TariffService#crearTarifa(geniar
	 * .siscar.model.Lines, geniar.siscar.model.TractionsTypes,
	 * geniar.siscar.model.FuelsTypes, java.math.BigDecimal, java.util.Date,
	 * java.util.Date, java.lang.Long)
	 */
	public void crearTarifa(Lines lines, TractionsTypes tipoTraccion,
			FuelsTypes fuelTypes, BigDecimal valor, Date anhoVigencia,
			Date fechaInicio, Long tipoTarifa) throws GWorkException {
	
		/** Consultar tipo de tarifa*/
		TariffsTypes tariffsTypes = null;
		ITariffsTypesDAO tariffsTypesDAO = new TariffsTypesDAO();

		/** Consulta que el tipo de tarifa exista */
		tariffsTypes = tariffsTypesDAO.findById(tipoTarifa);
		
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * geniar.siscar.logic.parameters.services.TariffService#modificarTarifa
	 * (java.lang.Long, java.lang.Long, java.lang.Long, java.lang.Integer,
	 * java.util.Date, java.math.BigDecimal, java.lang.Long)
	 */
	public void modificarTarifa(Long idLine, Long idTipoTraccion,
			Long idFuelTypes, Integer anhoVigencia, Date fechaInicio,
			BigDecimal valor, Long tipoTarifa) throws GWorkException {
		
		if (EntityManagerHelper.getEntityManager().isOpen())
			EntityManagerHelper.getEntityManager().close();

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

		/** Si no hay ninguna tarifa registrada */
		if (consultTariff.consultarTarifaAsignacionEspacioFisico(idLine,
				idFuelTypes, idTipoTraccion).isEmpty()) {
			EntityManagerHelper.beginTransaction();
			crearTarifa(lines, tractionsTypes, fuelsTypes, valor,
					periodoVigencia, fechaInicio, tipoTarifa);
		} else {

			Tariffs tariffs = consultTariff
					.consultarTarifaAsignacionEspacioFisico(idLine,
							idFuelTypes, idTipoTraccion).get(0);
			ITariffsDAO tariffsDAO = new TariffsDAO();

			EntityManagerHelper.beginTransaction();
			tariffs.setTrfFechaFin(fechaInicio);
			tariffsDAO.update(tariffs);
			crearTarifa(lines, tractionsTypes, fuelsTypes, valor,
					periodoVigencia, fechaInicio, tipoTarifa);
		}
	}
}
