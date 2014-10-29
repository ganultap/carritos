package geniar.siscar.persistence;

import geniar.siscar.model.AccidentsStates;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * AccidentsStates entities. Transaction control of the save(), update() and
 * delete() operations must be handled externally by senders of these methods or
 * must be manually added to each of these methods for data to be persisted to
 * the JPA datastore.
 * 
 * @see geniar.siscar.model.AccidentsStates
 * @author MyEclipse Persistence Tools
 */

public class AccidentsStatesDAO implements IAccidentsStatesDAO {
	// property constants
	public static final String ACS_NOMBRE = "acsNombre";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved AccidentsStates entity.
	 * All subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * AccidentsStatesDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            AccidentsStates entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(AccidentsStates entity) {
		EntityManagerHelper.log("saving AccidentsStates instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent AccidentsStates entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * AccidentsStatesDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            AccidentsStates entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(AccidentsStates entity) {
		EntityManagerHelper.log("deleting AccidentsStates instance", Level.INFO, null);
		try {
			entity = getEntityManager().getReference(AccidentsStates.class, entity.getAcsCode());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved AccidentsStates entity and return it or a copy
	 * of it to the sender. A copy of the AccidentsStates entity parameter is
	 * returned when the JPA persistence mechanism has not previously been
	 * tracking the updated entity. This operation must be performed within the
	 * a database transaction context for the entity's data to be permanently
	 * saved to the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = AccidentsStatesDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            AccidentsStates entity to update
	 * @returns AccidentsStates the persisted AccidentsStates entity instance,
	 *          may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public AccidentsStates update(AccidentsStates entity) {
		EntityManagerHelper.log("updating AccidentsStates instance", Level.INFO, null);
		try {
			AccidentsStates result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public AccidentsStates findById(Long id) {
		EntityManagerHelper.log("finding AccidentsStates instance with id: " + id, Level.INFO, null);
		try {
			AccidentsStates instance = getEntityManager().find(AccidentsStates.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all AccidentsStates entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the AccidentsStates property to query
	 * @param value
	 *            the property value to match
	 * @return List<AccidentsStates> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<AccidentsStates> findByProperty(String propertyName, final Object value) {
		EntityManagerHelper.log(
				"finding AccidentsStates instance with property: " + propertyName + ", value: " + value, Level.INFO,
				null);
		try {
			final String queryString = "select model from AccidentsStates model where model." + propertyName
					+ "= :propertyValue";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("propertyValue", value);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find by property name failed", Level.SEVERE, re);
			throw re;
		}
	}

	public List<AccidentsStates> findByAcsNombre(Object acsNombre) {
		return findByProperty(ACS_NOMBRE, acsNombre);
	}

	/**
	 * Find all AccidentsStates entities.
	 * 
	 * @return List<AccidentsStates> all AccidentsStates entities
	 */
	@SuppressWarnings("unchecked")
	public List<AccidentsStates> findAll() {
		EntityManagerHelper.log("finding all AccidentsStates instances", Level.INFO, null);
		try {
			final String queryString = "select model from AccidentsStates model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}