package geniar.siscar.persistence;

import geniar.siscar.model.AssignationsStates;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * AssignationsStates entities. Transaction control of the save(), update() and
 * delete() operations must be handled externally by senders of these methods or
 * must be manually added to each of these methods for data to be persisted to
 * the JPA datastore.
 * 
 * @see geniar.siscar.model.AssignationsStates
 * @author MyEclipse Persistence Tools
 */

public class AssignationsStatesDAO implements IAssignationsStatesDAO {
	// property constants
	public static final String ASS_NOMBRE = "assNombre";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved AssignationsStates
	 * entity. All subsequent persist actions of this entity should use the
	 * #update() method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * AssignationsStatesDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            AssignationsStates entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(AssignationsStates entity) {
		EntityManagerHelper.log("saving AssignationsStates instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent AssignationsStates entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * AssignationsStatesDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            AssignationsStates entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(AssignationsStates entity) {
		EntityManagerHelper.log("deleting AssignationsStates instance", Level.INFO, null);
		try {
			entity = getEntityManager().getReference(AssignationsStates.class, entity.getAssCodigo());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved AssignationsStates entity and return it or a
	 * copy of it to the sender. A copy of the AssignationsStates entity
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
	 * entity = AssignationsStatesDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            AssignationsStates entity to update
	 * @returns AssignationsStates the persisted AssignationsStates entity
	 *          instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public AssignationsStates update(AssignationsStates entity) {
		EntityManagerHelper.log("updating AssignationsStates instance", Level.INFO, null);
		try {
			AssignationsStates result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public AssignationsStates findById(Long id) {
		EntityManagerHelper.log("finding AssignationsStates instance with id: " + id, Level.INFO, null);
		try {
			AssignationsStates instance = getEntityManager().find(AssignationsStates.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all AssignationsStates entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the AssignationsStates property to query
	 * @param value
	 *            the property value to match
	 * @return List<AssignationsStates> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<AssignationsStates> findByProperty(String propertyName, final Object value) {
		EntityManagerHelper.log("finding AssignationsStates instance with property: " + propertyName + ", value: "
				+ value, Level.INFO, null);
		try {
			final String queryString = "select model from AssignationsStates model where model." + propertyName
					+ "= :propertyValue";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("propertyValue", value);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find by property name failed", Level.SEVERE, re);
			throw re;
		}
	}

	public List<AssignationsStates> findByAssNombre(Object assNombre) {
		return findByProperty(ASS_NOMBRE, assNombre);
	}

	/**
	 * Find all AssignationsStates entities.
	 * 
	 * @return List<AssignationsStates> all AssignationsStates entities
	 */
	@SuppressWarnings("unchecked")
	public List<AssignationsStates> findAll() {
		EntityManagerHelper.log("finding all AssignationsStates instances", Level.INFO, null);
		try {
			final String queryString = "select model from AssignationsStates model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}