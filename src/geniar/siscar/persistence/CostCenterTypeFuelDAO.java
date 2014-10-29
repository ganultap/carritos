package geniar.siscar.persistence;


import geniar.siscar.model.CostCenterTypeFuel;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * CostCenterTypeFuel entities. Transaction control of the save(), update() and
 * delete() operations must be handled externally by senders of these methods or
 * must be manually added to each of these methods for data to be persisted to
 * the JPA datastore.
 * 
 * @see .CostCenterTypeFuel
 * @author MyEclipse Persistence Tools
 */

public class CostCenterTypeFuelDAO implements ICostCenterTypeFuelDAO {
	// property constants
	public static final String CCT_NOMBRE = "cctNombre";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved CostCenterTypeFuel
	 * entity. All subsequent persist actions of this entity should use the
	 * #update() method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * CostCenterTypeFuelDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            CostCenterTypeFuel entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(CostCenterTypeFuel entity) {
		EntityManagerHelper.log("saving CostCenterTypeFuel instance",
				Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent CostCenterTypeFuel entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * CostCenterTypeFuelDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            CostCenterTypeFuel entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(CostCenterTypeFuel entity) {
		EntityManagerHelper.log("deleting CostCenterTypeFuel instance",
				Level.INFO, null);
		try {
			entity = getEntityManager().getReference(CostCenterTypeFuel.class,
					entity.getCctCodigo());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved CostCenterTypeFuel entity and return it or a
	 * copy of it to the sender. A copy of the CostCenterTypeFuel entity
	 * parameter is returned when the JPA persistence mechanism has not
	 * previously been tracking the updated entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently saved to the persistence store, i.e., database. This
	 * method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = CostCenterTypeFuelDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            CostCenterTypeFuel entity to update
	 * @returns CostCenterTypeFuel the persisted CostCenterTypeFuel entity
	 *          instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public CostCenterTypeFuel update(CostCenterTypeFuel entity) {
		EntityManagerHelper.log("updating CostCenterTypeFuel instance",
				Level.INFO, null);
		try {
			CostCenterTypeFuel result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public CostCenterTypeFuel findById(Long id) {
		EntityManagerHelper.log("finding CostCenterTypeFuel instance with id: "
				+ id, Level.INFO, null);
		try {
			CostCenterTypeFuel instance = getEntityManager().find(
					CostCenterTypeFuel.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all CostCenterTypeFuel entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the CostCenterTypeFuel property to query
	 * @param value
	 *            the property value to match
	 * @return List<CostCenterTypeFuel> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<CostCenterTypeFuel> findByProperty(String propertyName,
			final Object value) {
		EntityManagerHelper.log(
				"finding CostCenterTypeFuel instance with property: "
						+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from CostCenterTypeFuel model where model."
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

	public List<CostCenterTypeFuel> findByCctNombre(Object cctNombre) {
		return findByProperty(CCT_NOMBRE, cctNombre);
	}

	/**
	 * Find all CostCenterTypeFuel entities.
	 * 
	 * @return List<CostCenterTypeFuel> all CostCenterTypeFuel entities
	 */
	@SuppressWarnings("unchecked")
	public List<CostCenterTypeFuel> findAll() {
		EntityManagerHelper.log("finding all CostCenterTypeFuel instances",
				Level.INFO, null);
		try {
			final String queryString = "select model from CostCenterTypeFuel model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}