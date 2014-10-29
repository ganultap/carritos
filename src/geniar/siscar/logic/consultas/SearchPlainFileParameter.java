package geniar.siscar.logic.consultas;

import geniar.siscar.model.PlainFileParameter;
import geniar.siscar.persistence.EntityManagerHelper;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class SearchPlainFileParameter {
	
	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}
	
	/**
	 * Consulta un objeto de tipo {@link PlainFileParameter} con la combina
	 * ción de identificadores especificadas en los parametros.
	 * @param idTipoMoneda Identificador de tipo de moneda.
	 * @param idTipoNovedad Identificador de tipo de novedad.
	 * @return Un objeto de tipo {@link PlainFileParameter}.
	 * @throws GWorkException
	 */
	@SuppressWarnings("unchecked")
	public PlainFileParameter consultar_PFP_PorId(Long idTipoMoneda,
			Long idTipoNovedad) throws GWorkException {
		try {
			String queryString = "";
			
			queryString = "select pfp from PlainFileParameter pfp " +
					"where pfp.currencyTypes.ctId="+idTipoMoneda+" AND " +
					"pfp.noveltyTypes.ntId="+idTipoNovedad;
			
			Query query = getEntityManager().createQuery(queryString);

			List<PlainFileParameter> list = query.getResultList();
			if (list != null && list.size()>0) {
				return list.get(0);				
			}else 
				return null;


		} catch (RuntimeException re) {
			throw new GWorkException(Util.loadErrorMessageValue("search.not.found"));
		}
	}
	
}
