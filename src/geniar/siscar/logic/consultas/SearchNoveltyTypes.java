/**
 * 
 */
package geniar.siscar.logic.consultas;

import geniar.siscar.model.NoveltyTypes;
import geniar.siscar.persistence.EntityManagerHelper;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * @author Mauricio Cuenca.
 *
 */
public class SearchNoveltyTypes{

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}
	
	/**
	 * Consulta un tipo de novedad por su identificador.
	 * @param idTipoNovedad Identificador de tipo de novedad.
	 * @return un objeto de tipo {@link NoveltyTypes}.
	 * @throws GWorkException Manejador de Excepciones.
	 */
	@SuppressWarnings("unchecked")
	public NoveltyTypes consultarTipoNovedadPorId(Long idTipoNovedad) throws GWorkException {
		try {
			String queryString = "";
			
			queryString = "select nt from NoveltyTypes nt where nt.ntId="+idTipoNovedad;
			
			Query query = getEntityManager().createQuery(queryString);

			List<NoveltyTypes> list = query.getResultList();
			if (list != null && list.size()>0) {
				return list.get(0);				
			}else 
				return null;

		} catch (RuntimeException re) {
			throw new GWorkException(Util.loadErrorMessageValue("search.not.found"));
		}
	}
	
	/**
	 * Consulta TODOS LOS TIPOS DE NOVEDADES ORDENADOS ASCENDENTEMENTE.
	 * @param idTipoNovedad Identificador de tipo de novedad.
	 * @return un objeto de tipo {@link NoveltyTypes}.
	 * @throws GWorkException Manejador de Excepciones.
	 */
	@SuppressWarnings("unchecked")
	public List<NoveltyTypes> consultarTodosTipoNovedad() throws GWorkException {
		try {
			String queryString = "";
			
			queryString = "select nt from NoveltyTypes nt ORDER BY nt.ntNombre ASC";
			
			Query query = getEntityManager().createQuery(queryString);

			List<NoveltyTypes> list = query.getResultList();
			if (list != null && list.size()>0) {
				return list;				
			}else 
				return null;

		} catch (RuntimeException re) {
			throw new GWorkException(Util.loadErrorMessageValue("search.not.found"));
		}
	}
}
