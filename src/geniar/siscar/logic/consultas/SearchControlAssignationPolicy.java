package geniar.siscar.logic.consultas;

import geniar.siscar.model.ControlAssignationPolicies;
import geniar.siscar.persistence.EntityManagerHelper;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class SearchControlAssignationPolicy {
	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	@SuppressWarnings("unchecked")
	public List<ControlAssignationPolicies> consultarParametroContable(Long idParametro)
	throws GWorkException {
		try {
			final String queryString ="select model from ControlAssignationPolicies"+
					" model where model.acpId="+idParametro
				+" ORDER BY model.acpId ASC";
			Query query = getEntityManager().createQuery(queryString);
			
			List<ControlAssignationPolicies> list = query.getResultList();
			if (list != null) {
				return list;				
			}else 
				return null;			
		} catch (RuntimeException re) {
			throw new GWorkException(Util.loadErrorMessageValue(
					"search.not.found"));
		}
	}
}
