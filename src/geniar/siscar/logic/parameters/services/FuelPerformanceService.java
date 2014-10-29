/**
 * 
 */
package geniar.siscar.logic.parameters.services;

import java.util.List;

import geniar.siscar.model.FuelPerformance;
import gwork.exception.GWorkException;

/**
 * @author Jorge
 *
 */
public interface FuelPerformanceService {
	
	/**
	 * Crea un nuevo Objeto FuelPerformance.
	 * @param idLinea Identificador de la linea del Vehiculo.
	 * @param idTipoTraccion Identificador del tipo de Traccion.
	 * @param idTipoCombust Identificador del tipo de combustible.
	 * @param valorRendim Valor del rendimiento de combustible en Km/Gal.
	 * @throws GWorkException Manejador de excepciones.
	 */
	public void crearFuelPerformance(Long idLinea, Long idTipoTraccion,
			Long idTipoCombust, Float valorRendim) throws GWorkException;
	
	/**
	 * 
	 * @param idLinea Identificador de la linea del Vehiculo.
	 * @param idTipoTraccion Identificador del tipo de Traccion.
	 * @param idTipoCombust Identificador del tipo de combustible.
	 * @param valorRendim Valor del rendimiento de combustible en Km/Gal.
	 * @throws GWorkException Manejador de excepciones.
	 */
	public void modificarFuelPerformance(Long idLinea, Long idTipoTraccion,
			Long idTipoCombust, Float valorRendim)throws GWorkException;
	
	/**
	 * Consulta todos los objetos de tipo FuelPerformance.
	 * @return Listado de objetos {@link FuelPerformance}
	 * @throws GWorkException
	 */
	public List<FuelPerformance> consultarTodosFuelPerformance() throws GWorkException;

	/**
	 * Consulta un objeto de tipo FuelPerformance con los parametros:
	 * @param idLineaVehiculo Identificador de la linea del Vehiculo.
	 * @param idTipoCombustible Identificador del tipo de combustible.
	 * @param idTipoTraccion Identificador del tipo de Traccion.
	 */
	public FuelPerformance consultarFuelPerformance(Long idLineaVehiculo,
			Long idTipoCombustible, Long idTipoTraccion) throws GWorkException;

}
