package geniar.siscar.logic.consultas;

import geniar.siscar.model.LegateesTypes;
import geniar.siscar.model.RequestsClasses;
import geniar.siscar.model.RequestsStates;
import geniar.siscar.model.RequestsTypes;
import geniar.siscar.persistence.EntityManagerHelper;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.Query;
import javax.swing.text.Utilities;

/**
 * The Class SearchOrderRequest.
 */
public class SearchOrderRequest {

	/**
	 * Request states order.
	 *
	 * @return the list
	 */
	@SuppressWarnings("unchecked")
	public List<RequestsStates> requestStatesOrder() {
		EntityManagerHelper.log("finding all RequestsStates instances",
				Level.INFO, null);
		try {
			final String queryString = "select model from RequestsStates model ORDER BY model.rqtNombre ASC";
			Query query = EntityManagerHelper.getEntityManager().createQuery(
					queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Legatees types order.
	 *
	 * @return the list
	 * @throws GWorkException 
	 */
	@SuppressWarnings("unchecked")
	public List<LegateesTypes> legateesTypesOrder() throws GWorkException {
		EntityManagerHelper.log("finding all LegateesTypes instances",
				Level.INFO, null);
		try {
			String legatees;
		
			legatees=Util.loadParametersValue("LegateesTypes");

			final String queryString = "select model from LegateesTypes model WHERE model.lgtCodigo in ("+legatees+") "
					+ "ORDER BY model.lgtNombre ASC";
			Query query = EntityManagerHelper.getEntityManager().createQuery(
					queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}
	
	/**
	 * Request classes order.
	 *
	 * @return the list
	 */
	@SuppressWarnings("unchecked")
	public List<RequestsClasses> requestClassesOrder() {
		EntityManagerHelper.log("finding all RequestsClasses instances",
				Level.INFO, null);
		try {
			final String queryString = "select model from RequestsClasses model ORDER BY model.rqcNombre ASC";
			Query query = EntityManagerHelper.getEntityManager().createQuery(
					queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Request types order.
	 *
	 * @return the list
	 */
	@SuppressWarnings("unchecked")
	public List<RequestsTypes> requestTypesOrder() {
		EntityManagerHelper.log("finding all RequestsTypes instances",
				Level.INFO, null);
		try {
			final String queryString = "select model from RequestsTypes model ORDER BY model.rqyNombre ASC";
			Query query = EntityManagerHelper.getEntityManager().createQuery(
					queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}
}
