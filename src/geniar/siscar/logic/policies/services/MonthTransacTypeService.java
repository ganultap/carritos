/**
 * 
 */
package geniar.siscar.logic.policies.services;

import geniar.siscar.model.MonthTransacType;
import gwork.exception.GWorkException;

import java.util.List;

/**
 * @author Geniar
 *
 */
public interface MonthTransacTypeService {
	
	/**
	 * Consulta todos los tipos de transacciones mensuales asociadas a seguros.
	 * @return Listado de todos los tipos de transacciones mensuales.
	 * @throws GWorkException Manejador de excepciones.
	 */
	public List<MonthTransacType> consultarTodosTiposTransacMensual()
		throws GWorkException;
	
	/**
	 * Consulta un objeto de tipo {@link MonthTransacType}.
	 * @param idTipoTransaccion Identificador del tipo de transaccion mensual.
	 * @return Un objeto de tipo {@link MonthTransacType}.
	 * @throws GWorkException manejador de Excepciones.
	 */
	public MonthTransacType consultarMonthTransacType(Long idTipoTransaccion)
		throws GWorkException;
}
