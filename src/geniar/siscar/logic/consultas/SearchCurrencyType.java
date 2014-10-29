package geniar.siscar.logic.consultas;

import geniar.siscar.model.CurrencyTypes;
import geniar.siscar.persistence.EntityManagerHelper;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class SearchCurrencyType {

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}
	
	/**
	 * Consulta un tipo de moneda por su identificador.
	 * @param idTipoMoneda Identificador del tipo de moneda. 
	 * @return un objeto de tipo {@link CurrencyTypes}.
	 * @throws GWorkException Manejador de Excepciones.
	 */
	@SuppressWarnings("unchecked")
	public CurrencyTypes consultarTipoMonedaPorId(Long idTipoMoneda) throws GWorkException {
		try {
			String queryString = "";
			
			queryString = "select ct from CurrencyTypes ct where ct.ctId="+idTipoMoneda;
			
			Query query = getEntityManager().createQuery(queryString);

			List<CurrencyTypes> list = query.getResultList();
			if (list != null && list.size()>0) {
				return list.get(0);				
			}else 
				return null;


		} catch (RuntimeException re) {
			throw new GWorkException(Util.loadErrorMessageValue("search.not.found"));
		}
	}
}
