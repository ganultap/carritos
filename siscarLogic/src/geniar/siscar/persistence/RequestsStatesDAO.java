package geniar.siscar.persistence;

import geniar.siscar.model.RequestsStates;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * RequestsStates entities. Transaction control of the save(), update() and
 * delete() operations must be handled externally by senders of these methods or
 * must be manually added to each of these methods for data to be persisted to
 * the JPA datastore.
 * 
 * @see geniar.siscar.model.RequestsStates
 * @author MyEclipse Persistence Tools
 */

public class RequestsStatesDAO implements IRequestsStatesDAO {
	// property constants
	public static final String RQT_NOMBRE = "rqtNombre";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved RequestsStates entity.
	 * All subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * RequestsStatesDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            RequestsStates entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(RequestsStates entity) {
		EntityManagerHelper.log("saving RequestsStates instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent RequestsStates entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * RequestsStatesDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            RequestsStates entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(RequestsStates entity) {
		EntityManagerHelper.log("deleting RequestsStates instance", Level.INFO, null);
		try {
			entity = getEntityManager().getReference(RequestsStates.class, entity.getRqtCodigo());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved RequestsStates entity and return it or a copy
	 * of it to the sender. A copy of the RequestsStates entity parameter is
	 * returned when the JPA persistence mechanism has not previously been
	 * tracking the updated entity. This operation must be performed within the
	 * a database transaction context for the entity's data to be permanently
	 * saved to the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = RequestsStatesDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            RequestsStates entity to update
	 * @returns RequestsStates the persisted RequestsStates entity instance, may
	 *          not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public RequestsStates update(RequestsStates entity) {
		EntityManagerHelper.log("updating RequestsStates instance", Level.INFO, null);
		try {
			RequestsStates result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public RequestsStates findById(Long id) {
		EntityManagerHelper.log("finding RequestsStates instance with id: " + id, Level.INFO, null);
		try {
			RequestsStates instance = getEntityManager().find(RequestsStates.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all RequestsStates entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the RequestsStates property to query
	 * @param value
	 *            the property value to match
	 * @return List<RequestsStates> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<RequestsStates> findByProperty(String propertyName, final Object value) {
		EntityManagerHelper.log("finding RequestsStates instance with property: " + propertyName + ", value: " + value,
				Level.INFO, null);
		try {
			final String queryString = "select model from RequestsStates model where model." + propertyName
					+ "= :propertyValue";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("propertyValue", value);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find by property name failed", Level.SEVERE, re);
			throw re;
		}
	}

	public List<RequestsStates> findByRqtNombre(Object rqtNombre) {
		return findByProperty(RQT_NOMBRE, rqtNombre);
	}

	/**
	 * Find all RequestsStates entities.
	 * 
	 * @return List<RequestsStates> all RequestsStates entities
	 */
	@SuppressWarnings("unchecked")
	public List<RequestsStates> findAll() {
		EntityManagerHelper.log("finding all RequestsStates instances", Level.INFO, null);
		try {
			final String queryString = "select model from RequestsStates model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}