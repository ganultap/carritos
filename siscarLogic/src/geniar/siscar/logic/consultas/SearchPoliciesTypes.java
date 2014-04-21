package geniar.siscar.logic.consultas;

import geniar.siscar.model.PoliciesTypes;
import geniar.siscar.persistence.EntityManagerHelper;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class SearchPoliciesTypes {
	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	@SuppressWarnings("unchecked")
	public List<PoliciesTypes> consultarPoliciesTypesTransac()
	throws GWorkException {
		try {
			final String queryString ="select model from PoliciesTypes"+
					" model where model.pltCodigo!=10 ORDER BY model.pltNombre ASC";
			Query query = getEntityManager().createQuery(queryString);
			
			List<PoliciesTypes> list = query.getResultList();
			if (list != null) {
				return list;				
			}else 
				return null;			
		} catch (RuntimeException re) {
			throw new GWorkException(Util.loadErrorMessageValue(
					"search.not.found"));
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<PoliciesTypes> consultarPoliciesTypesParam()
	throws GWorkException {
		try {
			final String queryString ="select model from PoliciesTypes"+
			" model where model.pltCodigo<3 ORDER BY model.pltNombre ASC";
			Query query = getEntityManager().createQuery(queryString);
			
			List<PoliciesTypes> list = query.getResultList();
			if (list != null) {
				return list;				
			}else 
				return null;			
		} catch (RuntimeException re) {
			throw new GWorkException(Util.loadErrorMessageValue(
			"search.not.found"));
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<PoliciesTypes> consultarPoliciesTypes()
	throws GWorkException {
		try {
			final String queryString ="select model from PoliciesTypes "+
			"model ORDER BY model.pltNombre ASC";
			Query query = getEntityManager().createQuery(queryString);
			
			List<PoliciesTypes> list = query.getResultList();
			if (list != null) {
				return list;				
			}else 
				return null;			
		} catch (RuntimeException re) {
			throw new GWorkException(Util.loadErrorMessageValue(
			"search.not.found"));
		}
	}
	
	@SuppressWarnings("unchecked")
	public PoliciesTypes consultarPoliciesTypes(Long idTipoPoliza)
	throws GWorkException {
		try {
			final String queryString ="select model from PoliciesTypes"+
			" model where model.pltCodigo="+idTipoPoliza+
			" ORDER BY model.pltNombre ASC";
			Query query = getEntityManager().createQuery(queryString);
			
			List<PoliciesTypes> list = query.getResultList();
			if (list != null && list.size()>0) {
				return list.get(0);				
			}else 
				return null;			
		} catch (RuntimeException re) {
			throw new GWorkException(Util.loadErrorMessageValue(
			"search.not.found"));
		}
	}
	
	/**
	 * Consulta un tipo de poliza por su nombre.
	 * @param nombre parametro de busqueda.
	 * @return Objeto de tipo {@link PoliciesTypes}.
	 * @throws GWorkException Manejador de excepciones.
	 */
	@SuppressWarnings("unchecked")
	public PoliciesTypes consultarPoliciesTypes(String nombre)
	throws GWorkException {
		try {
			final String queryString ="select model from PoliciesTypes"+
			" model where model.pltNombre='"+nombre.toUpperCase()+"'";
			Query query = getEntityManager().createQuery(queryString);
			
			List<PoliciesTypes> list = query.getResultList();
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
