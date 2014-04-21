package geniar.siscar.logic.consultas;

import geniar.siscar.model.ControlAssignationPolicies;
import geniar.siscar.persistence.EntityManagerHelper;
import gwork.exception.GWorkException;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class SearchPolicyAssignementTypeControl {
	
	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}
	
	/**
	 * Consulta un registro especifico con los parametros indicados.
	 * @param lgtCodigo identificador del tipo de asignatario.
	 * @param lctCodigo identificador del tipo de ubicación.
	 * @return objeto ControlAssignationPolicies.
	 * @throws GWorkException
	 */
	@SuppressWarnings({ "unchecked", "null" })
	public ControlAssignationPolicies consultarCAP(
			Long lgtCodigo, Long lctCodigo) throws GWorkException {
		try {
			final String queryString ="select cap from ControlAssignationPolicies cap " +
					"where cap.legateesTypes.lgtCodigo="+ lgtCodigo+
					 " AND cap.locationsTypes.lctCodigo="+lctCodigo;
			Query query = getEntityManager().createQuery(queryString);
			 
			List<ControlAssignationPolicies> list = query.getResultList();
			if (list != null || list.size()>0) {
				return (ControlAssignationPolicies) list.get(0);				
			}else 
				return null;
			
			
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}
	/**
	 * Consulta un registro especifico con los parametros indicados.
	 * @param lgtCodigo identificador del tipo de asignatario.
	 * @param lctCodigo identificador del tipo de ubicación.
	 * @return objeto ControlAssignationPolicies.
	 * @throws GWorkException
	 */
	@SuppressWarnings("unchecked")
	public ControlAssignationPolicies consultarCAP(
			Long lgtCodigo, Long lctCodigo, Long pltCodigo) throws GWorkException {
		try {
			final String queryString ="select cap from ControlAssignationPolicies cap " +
			"where cap.legateesTypes.lgtCodigo="+ lgtCodigo+
			" AND cap.locationsTypes.lctCodigo="+lctCodigo+
			" AND cap.policiesTypes.pltCodigo="+pltCodigo;
			Query query = getEntityManager().createQuery(queryString);
			
			List<ControlAssignationPolicies> list = query.getResultList();
			if (list != null && list.size()>0) {
				return list.get(0);				
			}else 
				return null;
			
			
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<ControlAssignationPolicies> consultarTodosCAPPorFiltro(
			Long lgtCodigo, Long lctCodigo) throws GWorkException {
		try {
			final String queryString ="select cap from ControlAssignationPolicies cap " +
			"where cap.legateesTypes.lgtCodigo="+ lgtCodigo+
			" AND cap.locationsTypes.lctCodigo="+lctCodigo;
			Query query = getEntityManager().createQuery(queryString);
			
			List<ControlAssignationPolicies> list = query.getResultList();
			if (list != null) {
				return list;				
			}else 
				return null;
			
			
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}
	
	/**
	 * Determina si la llave TipoAsignatario-TipoUbicacion existe. 
	 * @param lgtCodigo identificador del Tipo de Asignatario.
	 * @param lctCodigo identificador del tipo de Ubicación.
	 * @return boolean.
	 * @throws GWorkException Manejador de excepciones.
	 */
	public boolean consultarLlaveAsignatarioUbicacion(
			Long lgtCodigo, Long lctCodigo) throws GWorkException {
		try {
			final String queryString ="select cap from ControlAssignationPolicies cap " +
					"where cap.legateesTypes.lgtCodigo="+lgtCodigo+" AND cap.locationsTypes.lctCodigo="+lctCodigo;
			Query query = getEntityManager().createQuery(queryString);
			
			if (query.getResultList().size() == 1) {
				return true;				
			}else 
				return false;
			
			
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}
}
