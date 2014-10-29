package geniar.siscar.logic.parameters.services;
import geniar.siscar.model.FuelsTypes;
import geniar.siscar.model.Lines;
import geniar.siscar.model.Locations;
import geniar.siscar.model.Tariffs;
import geniar.siscar.model.TariffsTypes;
import geniar.siscar.model.TractionsTypes;
import gwork.exception.GWorkException;

import java.math.BigDecimal;
import java.util.Date;

/**
 * The Interface TariffService.
 *
 * @author Geniar 
 *
 */
public interface TariffService {
   
	
	/**
	 * Crear tarifa.
	 *
	 * @param lines the lines
	 * @param tipoTraccion the tipo traccion
	 * @param fuelTypes the fuel types
	 * @param locations the locations
	 * @param tipoTarifa the tipo tarifa
	 * @param valor the valor
	 * @param anhoVigencia the anho vigencia
	 * @param fechaInicio the fecha inicio
	 * @throws GWorkException the g work exception
	 */
	public void crearTarifa(Lines lines, TractionsTypes tipoTraccion,FuelsTypes fuelTypes, Locations locations, TariffsTypes tipoTarifa,
				Float valor,Date anhoVigencia ,Date fechaInicio)throws GWorkException;

	/**
	 * Modificar tarifa.
	 *
	 * @param idLine the id line
	 * @param idTipoTraccion the id tipo traccion
	 * @param idFuelTypes the id fuel types
	 * @param idLocation the id location
	 * @param anhoVigencia the anho vigencia
	 * @param fechaInicio the fecha inicio
	 * @param valorDepreciacion the valor depreciacion
	 * @param valorMantenimiento the valor mantenimiento
	 * @param valorAutoSeguro the valor auto seguro
	 * @throws GWorkException the g work exception
	 */
	public void modificarTarifa(Long idLine, Long idTipoTraccion,Long idFuelTypes, Long idLocation, 
			Integer anhoVigencia ,Date fechaInicio, Float valorDepreciacion, 
			Float valorMantenimiento,Float valorAutoSeguro)throws GWorkException;
	
	/**
	 * Consultar tipo tarifa.
	 *
	 * @param codeTypeTariff the code type tariff
	 * @return the tariffs types
	 * @throws GWorkException the g work exception
	 */
	public TariffsTypes consultarTipoTarifa(Long codeTypeTariff)throws GWorkException;
		
	/**
	 * Consultar tarifa asignacion.
	 *
	 * @param idLinea the id linea
	 * @param idTipoCombustible the id tipo combustible
	 * @param idTipoTraccion the id tipo traccion
	 * @param idTipoTarifa the id tipo tarifa
	 * @return the tariffs
	 * @throws GWorkException the g work exception
	 */
	public  Tariffs consultarTarifaAsignacion(Long idLinea, Long idTipoCombustible, 
			Long idTipoTraccion, Long idTipoTarifa)throws GWorkException;
	
	/**
	 * Crear tarifa.
	 *
	 * @param lines the lines
	 * @param tipoTraccion the tipo traccion
	 * @param fuelTypes the fuel types
	 * @param valor the valor
	 * @param anhoVigencia the anho vigencia
	 * @param fechaInicio the fecha inicio
	 * @param tipoTarifa the tipo tarifa
	 * @throws GWorkException the g work exception
	 */
	public void crearTarifa(Lines lines, TractionsTypes tipoTraccion,
			FuelsTypes fuelTypes, BigDecimal valor,
			Date anhoVigencia, Date fechaInicio, Long tipoTarifa) throws GWorkException;
	
	/**
	 * Modificar tarifa.
	 *
	 * @param idLine the id line
	 * @param idTipoTraccion the id tipo traccion
	 * @param idFuelTypes the id fuel types
	 * @param anhoVigencia the anho vigencia
	 * @param fechaInicio the fecha inicio
	 * @param valor the valor
	 * @param tipoTarifa the tipo tarifa
	 * @throws GWorkException the g work exception
	 */
	public void modificarTarifa(Long idLine, Long idTipoTraccion,
			Long idFuelTypes,  Integer anhoVigencia,
			Date fechaInicio, BigDecimal valor, Long tipoTarifa) throws GWorkException;

	/**
	 * Servicio que permite consultar la tarifa de asignacion para el Espacio Físio.
	 *
	 * @param idLinea the id linea
	 * @param idTipoCombustible the id tipo combustible
	 * @param idTipoTraccion the id tipo traccion
	 * @return the tariffs
	 * @throws GWorkException the g work exception
	 */
	public Tariffs consultarTarifaAsignacionEspacioFisico(Long idLinea,
			Long idTipoCombustible, Long idTipoTraccion)
			throws GWorkException;
}
