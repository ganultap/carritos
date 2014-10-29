package geniar.siscar.persistence;

import geniar.siscar.model.RequestsTypes;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * RequestsTypes entities. Transaction control of the save(), update() and
 * delete() operations must be handled externally by senders of these methods or
 * must be manually added to each of these methods for data to be persisted to
 * the JPA datastore.
 * 
 * @see geniar.siscar.model.RequestsTypes
 * @author MyEclipse Persistence Tools
 */

public class RequestsTypesDAO implements IRequestsTypesDAO {
	// property constants
	public static final String RQY_NOMBRE = "rqyNombre";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved RequestsTypes entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * RequestsTypesDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            RequestsTypes entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(RequestsTypes entity) {
		EntityManagerHelper.log("saving RequestsTypes instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent RequestsTypes entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * RequestsTypesDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            RequestsTypes entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(RequestsTypes entity) {
		EntityManagerHelper.log("deleting RequestsTypes instance", Level.INFO, null);
		try {
			entity = getEntityManager().getReference(RequestsTypes.class, entity.getRqyCodigo());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved RequestsTypes entity and return it or a copy
	 * of it to the sender. A copy of the RequestsTypes entity parameter is
	 * returned when the JPA persistence mechanism has not previously been
	 * tracking the updated entity. This operation must be performed within the
	 * a database transaction context for the entity's data to be permanently
	 * saved to the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = RequestsTypesDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            RequestsTypes entity to update
	 * @returns RequestsTypes the persisted RequestsTypes entity instance, may
	 *          not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public RequestsTypes update(RequestsTypes entity) {
		EntityManagerHelper.log("updating RequestsTypes instance", Level.INFO, null);
		try {
			RequestsTypes result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public RequestsTypes findById(Long id) {
		EntityManagerHelper.log("finding RequestsTypes instance with id: " + id, Level.INFO, null);
		try {
			RequestsTypes instance = getEntityManager().find(RequestsTypes.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all RequestsTypes entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the RequestsTypes property to query
	 * @param value
	 *            the property value to match
	 * @return List<RequestsTypes> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<RequestsTypes> findByProperty(String propertyName, final Object value) {
		EntityManagerHelper.log("finding RequestsTypes instance with property: " + propertyName + ", value: " + value,
				Level.INFO, null);
		try {
			final String queryString = "select model from RequestsTypes model where model." + propertyName
					+ "= :propertyValue";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("propertyValue", value);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find by property name failed", Level.SEVERE, re);
			throw re;
		}
	}

	public List<RequestsTypes> findByRqyNombre(Object rqyNombre) {
		return findByProperty(RQY_NOMBRE, rqyNombre);
	}

	/**
	 * Find all RequestsTypes entities.
	 * 
	 * @return List<RequestsTypes> all RequestsTypes entities
	 */
	@SuppressWarnings("unchecked")
	public List<RequestsTypes> findAll() {
		EntityManagerHelper.log("finding all RequestsTypes instances", Level.INFO, null);
		try {
			final String queryString = "select model from RequestsTypes model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}