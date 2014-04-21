package geniar.siscar.logic.consultas;

import geniar.siscar.model.MonthTransacType;
import geniar.siscar.persistence.EntityManagerHelper;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class SearchMonthlyTransac {

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}
	
	/**
	 * Consulta los tipos de transaccion mensual para polizas.
	 * @return Listado de {@link MonthTransacType}
	 * ordenado ascendentemente por nombre.
	 * @throws GWorkException Manejador de excepciones.
	 */
	@SuppressWarnings("unchecked")
	public List<MonthTransacType> consultarMonthTransacType() 
		throws GWorkException {
		try {
			final String queryString ="select model from MonthTransacType model " +
					"ORDER BY model.mttNombre ASC";
			Query query = getEntityManager().createQuery(queryString);

			List<MonthTransacType> list = query.getResultList();
			if (list.size()>0) {
				return list;				
			}else 
				return null;			
		} catch (RuntimeException re) {
			throw new GWorkException(Util.loadErrorMessageValue("search.not.found"));
		}
	}
	
	/**
	 * Consulta un objeto de tipo {@link MonthTransacType} por su ID.
	 * @param idTipoTransaccion Identificador del {@link MonthTransacType}.
	 * @return Un objeto de tipo {@link MonthTransacType}.
	 * @throws GWorkException Manejador de excepciones.
	 */
	@SuppressWarnings("unchecked")
	public MonthTransacType consultarMonthTransacType(Long idTipoTransaccion)
		throws GWorkException {
		try {
			final String queryString ="select model from MonthTransacType model " +
					"where model.mttId="+idTipoTransaccion;
			Query query = getEntityManager().createQuery(queryString);

			List<MonthTransacType> list = query.getResultList();
			if (list != null && list.size()>0) {
				return list.get(0);				
			}else 
				return null;			
		} catch (RuntimeException re) {
			throw new GWorkException(Util.loadErrorMessageValue(
					"search.not.found"));
		}
	}
	
}
