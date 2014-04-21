package geniar.siscar.logic.parameters.services;

import geniar.siscar.model.FuelsTypes;
import geniar.siscar.model.Lines;
import geniar.siscar.model.Tariffs;
import geniar.siscar.model.TariffsTypes;
import geniar.siscar.model.TractionsTypes;
import gwork.exception.GWorkException;

import java.math.BigDecimal;
import java.util.Date;

/**
 * The Interface TarifaMantenimientoService.
 */
public interface TarifaMantenimientoService {

	/**
	 * Crear tarifa.
	 *
	 * @param lines the lines
	 * @param tipoTraccion the tipo traccion
	 * @param fuelTypes the fuel types
	 * @param valor the valor
	 * @param anhoVigencia the anho vigencia
	 * @param fechaInicio the fecha inicio
	 * @throws GWorkException the g work exception
	 */
	public void crearTarifa(Lines lines, TractionsTypes tipoTraccion,
			FuelsTypes fuelTypes, BigDecimal valor,
			Date anhoVigencia, Date fechaInicio) throws GWorkException;

	/**
	 * Modificar tarifa.
	 *
	 * @param idLine the id line
	 * @param idTipoTraccion the id tipo traccion
	 * @param idFuelTypes the id fuel types
	 * @param anhoVigencia the anho vigencia
	 * @param fechaInicio the fecha inicio
	 * @param valorMantenimiento the valor mantenimiento
	 * @throws GWorkException the g work exception
	 */
	public void modificarTarifa(Long idLine, Long idTipoTraccion,
			Long idFuelTypes,  Integer anhoVigencia,
			Date fechaInicio, BigDecimal valorMantenimiento) throws GWorkException;

	/**
	 * Consultar tipo tarifa.
	 *
	 * @param codeTypeTariff the code type tariff
	 * @return the tariffs types
	 * @throws GWorkException the g work exception
	 */
	public TariffsTypes consultarTipoTarifa(Long codeTypeTariff)
			throws GWorkException;

	/**
	 * Consulta assignment tariff.
	 *
	 * @param codeLine the code line
	 * @param codetypeTariff the codetype tariff
	 * @param codeFuelType the code fuel type
	 * @return the tariffs
	 */
	public Tariffs consultaAssignmentTariff(Long codeLine, Long codetypeTariff,
			Long codeFuelType);

	/**
	 * Consultar tarifa asignacion.
	 *
	 * @param idLinea the id linea
	 * @param idTipoCombustible the id tipo combustible
	 * @param idTipoTraccion the id tipo traccion
	 * @param idLocation the id location
	 * @return the tariffs
	 * @throws GWorkException the g work exception
	 */
	public Tariffs consultarTarifaAsignacion(Long idLinea,
			Long idTipoCombustible, Long idTipoTraccion, Long idLocation)
			throws GWorkException;

	/**
	 * Consultar tarifa asignacion mantenimiento.
	 *
	 * @param idLinea the id linea
	 * @param idTipoCombustible the id tipo combustible
	 * @param idTipoTraccion the id tipo traccion
	 * @return the tariffs
	 * @throws GWorkException the g work exception
	 */
	public Tariffs consultarTarifaAsignacionMantenimiento(Long idLinea,
			Long idTipoCombustible, Long idTipoTraccion)
			throws GWorkException;
}
