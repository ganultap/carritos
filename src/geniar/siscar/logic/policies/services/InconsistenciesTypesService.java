package geniar.siscar.logic.policies.services;

import geniar.siscar.model.InconsistenciesTypes;
import gwork.exception.GWorkException;

public interface InconsistenciesTypesService {
	
	/**
	 * Consulta un objeto de tipo {@link InconsistenciesTypes} por su ID.
	 * @return un objeto de tipo {@link InconsistenciesTypes}.
	 * @throws GWorkException Manejador de excepciones.
	 */
	public InconsistenciesTypes consultarTipoInconsistenciaPorId(
			Long id) throws GWorkException;
	
}
