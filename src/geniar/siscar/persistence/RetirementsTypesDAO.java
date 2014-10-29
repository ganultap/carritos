package geniar.siscar.persistence;

import geniar.siscar.model.RetirementsTypes;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * RetirementsTypes entities. Transaction control of the save(), update() and
 * delete() operations must be handled externally by senders of these methods or
 * must be manually added to each of these methods for data to be persisted to
 * the JPA datastore.
 * 
 * @see geniar.siscar.model.RetirementsTypes
 * @author MyEclipse Persistence Tools
 */

public class RetirementsTypesDAO implements IRetirementsTypesDAO {
	// property constants
	public static final String RET_NOMBRE = "retNombre";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved RetirementsTypes entity.
	 * All subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * RetirementsTypesDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            RetirementsTypes entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(RetirementsTypes entity) {
		EntityManagerHelper.log("saving RetirementsTypes instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent RetirementsTypes entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * RetirementsTypesDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            RetirementsTypes entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(RetirementsTypes entity) {
		EntityManagerHelper.log("deleting RetirementsTypes instance", Level.INFO, null);
		try {
			entity = getEntityManager().getReference(RetirementsTypes.class, entity.getRetCodigo());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved RetirementsTypes entity and return it or a
	 * copy of it to the sender. A copy of the RetirementsTypes entity parameter
	 * is returned when the JPA persistence mechanism has not previously been
	 * tracking the updated entity. This operation must be performed within the
	 * a database transaction context for the entity's data to be permanently
	 * saved to the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = RetirementsTypesDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            RetirementsTypes entity to update
	 * @returns RetirementsTypes the persisted RetirementsTypes entity instance,
	 *          may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public RetirementsTypes update(RetirementsTypes entity) {
		EntityManagerHelper.log("updating RetirementsTypes instance", Level.INFO, null);
		try {
			RetirementsTypes result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public RetirementsTypes findById(Long id) {
		EntityManagerHelper.log("finding RetirementsTypes instance with id: " + id, Level.INFO, null);
		try {
			RetirementsTypes instance = getEntityManager().find(RetirementsTypes.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all RetirementsTypes entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the RetirementsTypes property to query
	 * @param value
	 *            the property value to match
	 * @return List<RetirementsTypes> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<RetirementsTypes> findByProperty(String propertyName, final Object value) {
		EntityManagerHelper.log("finding RetirementsTypes instance with property: " + propertyName + ", value: "
				+ value, Level.INFO, null);
		try {
			final String queryString = "select model from RetirementsTypes model where model." + propertyName
					+ "= :propertyValue";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("propertyValue", value);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find by property name failed", Level.SEVERE, re);
			throw re;
		}
	}

	public List<RetirementsTypes> findByRetNombre(Object retNombre) {
		return findByProperty(RET_NOMBRE, retNombre);
	}

	/**
	 * Find all RetirementsTypes entities.
	 * 
	 * @return List<RetirementsTypes> all RetirementsTypes entities
	 */
	@SuppressWarnings("unchecked")
	public List<RetirementsTypes> findAll() {
		EntityManagerHelper.log("finding all RetirementsTypes instances", Level.INFO, null);
		try {
			final String queryString = "select model from RetirementsTypes model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}