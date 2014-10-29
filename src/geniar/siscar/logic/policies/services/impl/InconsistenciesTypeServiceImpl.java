/**
 * 
 */
package geniar.siscar.logic.policies.services.impl;

import geniar.siscar.logic.policies.services.InconsistenciesTypesService;
import geniar.siscar.model.InconsistenciesTypes;
import geniar.siscar.persistence.InconsistenciesTypesDAO;
import gwork.exception.GWorkException;

/**
 * @author Jorge
 *
 */
public class InconsistenciesTypeServiceImpl implements
		InconsistenciesTypesService {

	/*
	 * (non-Javadoc)
	 * @see geniar.siscar.logic.policies.services.
	 * InconsistenciesTypesService#consultarTipoInconsistenciaPorId(java.lang.Long)
	 */
	public InconsistenciesTypes consultarTipoInconsistenciaPorId(
			Long id) throws GWorkException {
		return new InconsistenciesTypesDAO().findById(id);
	}

}
