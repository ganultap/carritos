/**
 * 
 */
package geniar.siscar.logic.parameters.services.impl;

import java.util.List;

import geniar.siscar.logic.parameters.services.CurrencyTypesService;
import geniar.siscar.model.CurrencyTypes;
import geniar.siscar.persistence.CurrencyTypesDAO;
import gwork.exception.GWorkException;

/**
 * @author Mauricio Cuenca Narváez.
 *
 */
public class CurrencyTypesServiceImpl implements CurrencyTypesService {

	/* (non-Javadoc)
	 * @see geniar.siscar.logic.parameters.services.CurrencyTypesService
	 * #consultarTodosTiposMoneda()
	 */
	public List<CurrencyTypes> consultarTodosTiposMoneda()
			throws GWorkException {
		return new CurrencyTypesDAO().findAll();
	}

}
