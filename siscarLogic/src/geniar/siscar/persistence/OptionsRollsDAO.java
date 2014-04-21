package geniar.siscar.persistence;

import geniar.siscar.model.OptionsRolls;
import geniar.siscar.model.OptionsRollsId;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * OptionsRolls entities. Transaction control of the save(), update() and
 * delete() operations must be handled externally by senders of these methods or
 * must be manually added to each of these methods for data to be persisted to
 * the JPA datastore.
 * 
 * @see geniar.siscar.model.OptionsRolls
 * @author MyEclipse Persistence Tools
 */

public class OptionsRollsDAO implements IOptionsRollsDAO {
	// property constants

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved OptionsRolls entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * OptionsRollsDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            OptionsRolls entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(OptionsRolls entity) {
		EntityManagerHelper.log("saving OptionsRolls instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent OptionsRolls entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * OptionsRollsDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            OptionsRolls entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(OptionsRolls entity) {
		EntityManagerHelper.log("deleting OptionsRolls instance", Level.INFO, null);
		try {
			entity = getEntityManager().getReference(OptionsRolls.class, entity.getId());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved OptionsRolls entity and return it or a copy of
	 * it to the sender. A copy of the OptionsRolls entity parameter is returned
	 * when the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = OptionsRollsDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            OptionsRolls entity to update
	 * @returns OptionsRolls the persisted OptionsRolls entity instance, may not
	 *          be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public OptionsRolls update(OptionsRolls entity) {
		EntityManagerHelper.log("updating OptionsRolls instance", Level.INFO, null);
		try {
			OptionsRolls result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public OptionsRolls findById(OptionsRollsId id) {
		EntityManagerHelper.log("finding OptionsRolls instance with id: " + id, Level.INFO, null);
		try {
			OptionsRolls instance = getEntityManager().find(OptionsRolls.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all OptionsRolls entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the OptionsRolls property to query
	 * @param value
	 *            the property value to match
	 * @return List<OptionsRolls> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<OptionsRolls> findByProperty(String propertyName, final Object value) {
		EntityManagerHelper.log("finding OptionsRolls instance with property: " + propertyName + ", value: " + value,
				Level.INFO, null);
		try {
			final String queryString = "select model from OptionsRolls model where model." + propertyName
					+ "= :propertyValue";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("propertyValue", value);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find by property name failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all OptionsRolls entities.
	 * 
	 * @return List<OptionsRolls> all OptionsRolls entities
	 */
	@SuppressWarnings("unchecked")
	public List<OptionsRolls> findAll() {
		EntityManagerHelper.log("finding all OptionsRolls instances", Level.INFO, null);
		try {
			final String queryString = "select model from OptionsRolls model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}