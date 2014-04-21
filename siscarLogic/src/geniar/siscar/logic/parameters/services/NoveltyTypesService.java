package geniar.siscar.logic.parameters.services;

import java.util.List;

import geniar.siscar.model.NoveltyTypes;
import gwork.exception.GWorkException;

/**
 * 
 * @author Mauricio Cuenca N
 *
 */
public interface NoveltyTypesService {
	
	/**
	 * Consulta todos los tipos de novedad.
	 * @return Lista de todos lost tipos de novedad.
	 * @throws GWorkException Manejador de Excepciones.
	 */
	public List<NoveltyTypes> consultarTodosTiposNovedad() throws GWorkException;
	
}
