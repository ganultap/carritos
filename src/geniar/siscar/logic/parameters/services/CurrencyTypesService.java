package geniar.siscar.logic.parameters.services;

import geniar.siscar.model.CurrencyTypes;
import gwork.exception.GWorkException;

import java.util.List;

/**
 * 
 * @author Mauricio Cuenca Narváez
 *
 */
public interface CurrencyTypesService {
	
	/**
	 * Consulta todos los tipos de moneda.
	 * @return Una lista con todos los tipos de moneda.
	 * @throws GWorkException Manejador de Excepciones.
	 */
	public List<CurrencyTypes> consultarTodosTiposMoneda() throws GWorkException;
	
}
