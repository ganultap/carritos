package geniar.siscar.persistence;


import geniar.siscar.model.CostCentersFuel;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * CostCentersFuel entities. Transaction control of the save(), update() and
 * delete() operations must be handled externally by senders of these methods or
 * must be manually added to each of these methods for data to be persisted to
 * the JPA datastore.
 * 
 * @see .CostCentersFuel
 * @author MyEclipse Persistence Tools
 */

public class CostCentersFuelDAO implements ICostCentersFuelDAO {
	// property constants
	public static final String CCF_VALOR = "ccfValor";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved CostCentersFuel entity.
	 * All subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * CostCentersFuelDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            CostCentersFuel entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(CostCentersFuel entity) {
		EntityManagerHelper.log("saving CostCentersFuel instance", Level.INFO,
				null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent CostCentersFuel entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * CostCentersFuelDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            CostCentersFuel entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(CostCentersFuel entity) {
		EntityManagerHelper.log("deleting CostCentersFuel instance",
				Level.INFO, null);
		try {
			entity = getEntityManager().getReference(CostCentersFuel.class,
					entity.getCcfId());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved CostCentersFuel entity and return it or a copy
	 * of it to the sender. A copy of the CostCentersFuel entity parameter is
	 * returned when the JPA persistence mechanism has not previously been
	 * tracking the updated entity. This operation must be performed within the
	 * a database transaction context for the entity's data to be permanently
	 * saved to the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = CostCentersFuelDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            CostCentersFuel entity to update
	 * @returns CostCentersFuel the persisted CostCentersFuel entity instance,
	 *          may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public CostCentersFuel update(CostCentersFuel entity) {
		EntityManagerHelper.log("updating CostCentersFuel instance",
				Level.INFO, null);
		try {
			CostCentersFuel result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public CostCentersFuel findById(Long id) {
		EntityManagerHelper.log("finding CostCentersFuel instance with id: "
				+ id, Level.INFO, null);
		try {
			CostCentersFuel instance = getEntityManager().find(
					CostCentersFuel.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all CostCentersFuel entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the CostCentersFuel property to query
	 * @param value
	 *            the property value to match
	 * @return List<CostCentersFuel> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<CostCentersFuel> findByProperty(String propertyName,
			final Object value) {
		EntityManagerHelper.log(
				"finding CostCentersFuel instance with property: "
						+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from CostCentersFuel model where model."
					+ propertyName + "= :propertyValue";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("propertyValue", value);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find by property name failed",
					Level.SEVERE, re);
			throw re;
		}
	}

	public List<CostCentersFuel> findByCcfValor(Object ccfValor) {
		return findByProperty(CCF_VALOR, ccfValor);
	}

	/**
	 * Find all CostCentersFuel entities.
	 * 
	 * @return List<CostCentersFuel> all CostCentersFuel entities
	 */
	@SuppressWarnings("unchecked")
	public List<CostCentersFuel> findAll() {
		EntityManagerHelper.log("finding all CostCentersFuel instances",
				Level.INFO, null);
		try {
			final String queryString = "select model from CostCentersFuel model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}