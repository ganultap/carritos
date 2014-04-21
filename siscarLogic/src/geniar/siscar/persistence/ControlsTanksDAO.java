package geniar.siscar.persistence;

import geniar.siscar.model.ControlsTanks;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * ControlsTanks entities. Transaction control of the save(), update() and
 * delete() operations must be handled externally by senders of these methods or
 * must be manually added to each of these methods for data to be persisted to
 * the JPA datastore.
 * 
 * @see geniar.siscar.model.ControlsTanks
 * @author MyEclipse Persistence Tools
 */

public class ControlsTanksDAO implements IControlsTanksDAO {
	// property constants
	public static final String COT_GALONES_ACTUALES = "cotGalonesActuales";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved ControlsTanks entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ControlsTanksDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            ControlsTanks entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(ControlsTanks entity) {
		EntityManagerHelper.log("saving ControlsTanks instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent ControlsTanks entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ControlsTanksDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            ControlsTanks entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(ControlsTanks entity) {
		EntityManagerHelper.log("deleting ControlsTanks instance", Level.INFO, null);
		try {
			entity = getEntityManager().getReference(ControlsTanks.class, entity.getCotCodigo());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved ControlsTanks entity and return it or a copy
	 * of it to the sender. A copy of the ControlsTanks entity parameter is
	 * returned when the JPA persistence mechanism has not previously been
	 * tracking the updated entity. This operation must be performed within the
	 * a database transaction context for the entity's data to be permanently
	 * saved to the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = ControlsTanksDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            ControlsTanks entity to update
	 * @returns ControlsTanks the persisted ControlsTanks entity instance, may
	 *          not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public ControlsTanks update(ControlsTanks entity) {
		EntityManagerHelper.log("updating ControlsTanks instance", Level.INFO, null);
		try {
			ControlsTanks result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public ControlsTanks findById(Long id) {
		EntityManagerHelper.log("finding ControlsTanks instance with id: " + id, Level.INFO, null);
		try {
			ControlsTanks instance = getEntityManager().find(ControlsTanks.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all ControlsTanks entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the ControlsTanks property to query
	 * @param value
	 *            the property value to match
	 * @return List<ControlsTanks> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<ControlsTanks> findByProperty(String propertyName, final Object value) {
		EntityManagerHelper.log("finding ControlsTanks instance with property: " + propertyName + ", value: " + value,
				Level.INFO, null);
		try {
			final String queryString = "select model from ControlsTanks model where model." + propertyName
					+ "= :propertyValue";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("propertyValue", value);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find by property name failed", Level.SEVERE, re);
			throw re;
		}
	}

	public List<ControlsTanks> findByCotGalonesActuales(Object cotGalonesActuales) {
		return findByProperty(COT_GALONES_ACTUALES, cotGalonesActuales);
	}

	/**
	 * Find all ControlsTanks entities.
	 * 
	 * @return List<ControlsTanks> all ControlsTanks entities
	 */
	@SuppressWarnings("unchecked")
	public List<ControlsTanks> findAll() {
		EntityManagerHelper.log("finding all ControlsTanks instances", Level.INFO, null);
		try {
			final String queryString = "select model from ControlsTanks model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}