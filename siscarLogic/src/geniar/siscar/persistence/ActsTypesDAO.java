package geniar.siscar.persistence;

import geniar.siscar.model.ActsTypes;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * ActsTypes entities. Transaction control of the save(), update() and delete()
 * operations must be handled externally by senders of these methods or must be
 * manually added to each of these methods for data to be persisted to the JPA
 * datastore.
 * 
 * @see geniar.siscar.model.ActsTypes
 * @author MyEclipse Persistence Tools
 */

public class ActsTypesDAO implements IActsTypesDAO {
	// property constants
	public static final String ATY_NOMBRE = "atyNombre";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved ActsTypes entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ActsTypesDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            ActsTypes entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(ActsTypes entity) {
		EntityManagerHelper.log("saving ActsTypes instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent ActsTypes entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ActsTypesDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            ActsTypes entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(ActsTypes entity) {
		EntityManagerHelper.log("deleting ActsTypes instance", Level.INFO, null);
		try {
			entity = getEntityManager().getReference(ActsTypes.class, entity.getAtyCodigo());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved ActsTypes entity and return it or a copy of it
	 * to the sender. A copy of the ActsTypes entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = ActsTypesDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            ActsTypes entity to update
	 * @returns ActsTypes the persisted ActsTypes entity instance, may not be
	 *          the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public ActsTypes update(ActsTypes entity) {
		EntityManagerHelper.log("updating ActsTypes instance", Level.INFO, null);
		try {
			ActsTypes result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public ActsTypes findById(Long id) {
		EntityManagerHelper.log("finding ActsTypes instance with id: " + id, Level.INFO, null);
		try {
			ActsTypes instance = getEntityManager().find(ActsTypes.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all ActsTypes entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the ActsTypes property to query
	 * @param value
	 *            the property value to match
	 * @return List<ActsTypes> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<ActsTypes> findByProperty(String propertyName, final Object value) {
		EntityManagerHelper.log("finding ActsTypes instance with property: " + propertyName + ", value: " + value,
				Level.INFO, null);
		try {
			final String queryString = "select model from ActsTypes model where model." + propertyName
					+ "= :propertyValue";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("propertyValue", value);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find by property name failed", Level.SEVERE, re);
			throw re;
		}
	}

	public List<ActsTypes> findByAtyNombre(Object atyNombre) {
		return findByProperty(ATY_NOMBRE, atyNombre);
	}

	/**
	 * Find all ActsTypes entities.
	 * 
	 * @return List<ActsTypes> all ActsTypes entities
	 */
	@SuppressWarnings("unchecked")
	public List<ActsTypes> findAll() {
		EntityManagerHelper.log("finding all ActsTypes instances", Level.INFO, null);
		try {
			final String queryString = "select model from ActsTypes model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}