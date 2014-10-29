/**
 * 
 */
package geniar.siscar.logic.parameters.services;

import geniar.siscar.model.Tariffs;
import geniar.siscar.model.TariffsTypes;
import geniar.siscar.model.VehiclesTypes;
import gwork.exception.GWorkException;

import java.util.Date;
import java.util.List;

/**
 * The Interface TariffRentService.
 *
 * @author Geniar
 */
public interface TariffRentService {
	
	/**
	 * Crear tarifa alquiler.
	 *
	 * @param IdTipoVehiculo the id tipo vehiculo
	 * @param tipoTarifa the tipo tarifa
	 * @param trfAñoVigencia the trf año vigencia
	 * @param trfFechaInicio the trf fecha inicio
	 * @param trfKmIncluido the trf km incluido
	 * @param trfKmValorAdicional the trf km valor adicional
	 * @param trfValor the trf valor
	 * @throws GWorkException the g work exception
	 */
	public void crearTarifaAlquiler(VehiclesTypes IdTipoVehiculo,
			TariffsTypes tipoTarifa, Date trfAñoVigencia, Date trfFechaInicio,
			Float trfKmIncluido, Float trfKmValorAdicional, Float trfValor)
			throws GWorkException;
	
	/**
	 * Modificar tarifa alquiler.
	 *
	 * @param idTipoVehiculo the id tipo vehiculo
	 * @param trfAnoVigencia the trf año vigencia
	 * @param trfFechaInicio the trf fecha inicio
	 * @param trfKmIncluido the trf km incluido
	 * @param trfValorAutoseguro the trf valor autoseguro
	 * @param trfValorDepreciacion the trf valor depreciacion
	 * @param trfValorEspacioFisico the trf valor espacio fisico
	 * @param trfValorMantenimiento the trf valor mantenimiento
	 * @param trfKmValorAutoseguro the trf km valor autoseguro
	 * @param trfKmValorDepreciacion the trf km valor depreciacion
	 * @param trfKmValorEspacioFisico the trf km valor espacio fisico
	 * @param trfKmValorMantenimiento the trf km valor mantenimiento
	 * @throws GWorkException the g work exception
	 */
	public void modificarTarifaAlquiler(Long idTipoVehiculo,
			Integer trfAnoVigencia, Date trfFechaInicio, Float trfKmIncluido,
			Float trfValorAutoseguro, Float trfValorDepreciacion,
			Float trfValorEspacioFisico, Float trfValorMantenimiento,
			Float trfKmValorAutoseguro, Float trfKmValorDepreciacion,
			Float trfKmValorEspacioFisico, Float trfKmValorMantenimiento)
			throws GWorkException;
	
	/**
	 * Consultar tarifa by id.
	 *
	 * @param trfId the trf id
	 * @return the tariffs
	 * @throws GWorkException the g work exception
	 */
	public Tariffs consultarTarifaById(Long trfId) throws GWorkException;
	
	/**
	 * Lista tarifas.
	 *
	 * @return the list
	 * @throws GWorkException the g work exception
	 */
	public List<Tariffs> listaTarifas() throws GWorkException;
	
	/**
	 * Consultar tarifa actual alquier por tipo tarifa y tipo vehiculo.
	 *
	 * @param idTipoVehiculo the id tipo vehiculo
	 * @param idTipoTarifa the id tipo tarifa
	 * @return the tariffs
	 * @throws GWorkException the g work exception
	 */
	public Tariffs consultarTarifaActualAlquilerPorTipoTarifaYTipoVehiculo(
			Long idTipoVehiculo, Long idTipoTarifa) throws GWorkException;
}