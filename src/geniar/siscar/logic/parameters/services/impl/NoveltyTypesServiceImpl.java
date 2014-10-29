/**
 * 
 */
package geniar.siscar.logic.parameters.services.impl;

import geniar.siscar.logic.consultas.SearchNoveltyTypes;
import geniar.siscar.logic.parameters.services.NoveltyTypesService;
import geniar.siscar.model.NoveltyTypes;
import gwork.exception.GWorkException;

import java.util.List;

/**
 * @author Mauricio Cuenca N
 *
 */
public class NoveltyTypesServiceImpl implements NoveltyTypesService {

	/* (non-Javadoc)
	 * @see geniar.siscar.logic.parameters.services.
	 * NoveltyTypesService#consultarTodosTiposNovedad()
	 */
	public List<NoveltyTypes> consultarTodosTiposNovedad()
			throws GWorkException {
		return new SearchNoveltyTypes().consultarTodosTipoNovedad();		
	}

}
