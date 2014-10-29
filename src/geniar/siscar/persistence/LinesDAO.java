package geniar.siscar.persistence;

import geniar.siscar.model.Lines;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for Lines
 * entities. Transaction control of the save(), update() and delete() operations
 * must be handled externally by senders of these methods or must be manually
 * added to each of these methods for data to be persisted to the JPA datastore.
 * 
 * @see geniar.siscar.model.Lines
 * @author MyEclipse Persistence Tools
 */

public class LinesDAO implements ILinesDAO {
	// property constants
	public static final String LNS_NOMBRE = "lnsNombre";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved Lines entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * LinesDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Lines entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Lines entity) {
		EntityManagerHelper.log("saving Lines instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent Lines entity. This operation must be performed within
	 * the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * LinesDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Lines entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Lines entity) {
		EntityManagerHelper.log("deleting Lines instance", Level.INFO, null);
		try {
			entity = getEntityManager().getReference(Lines.class, entity.getLnsId());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved Lines entity and return it or a copy of it to
	 * the sender. A copy of the Lines entity parameter is returned when the JPA
	 * persistence mechanism has not previously been tracking the updated
	 * entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = LinesDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Lines entity to update
	 * @returns Lines the persisted Lines entity instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Lines update(Lines entity) {
		EntityManagerHelper.log("updating Lines instance", Level.INFO, null);
		try {
			Lines result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Lines findById(Long id) {
		EntityManagerHelper.log("finding Lines instance with id: " + id, Level.INFO, null);
		try {
			Lines instance = getEntityManager().find(Lines.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all Lines entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Lines property to query
	 * @param value
	 *            the property value to match
	 * @return List<Lines> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Lines> findByProperty(String propertyName, final Object value) {
		EntityManagerHelper.log("finding Lines instance with property: " + propertyName + ", value: " + value,
				Level.INFO, null);
		try {
			final String queryString = "select model from Lines model where model." + propertyName + "= :propertyValue";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("propertyValue", value);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find by property name failed", Level.SEVERE, re);
			throw re;
		}
	}

	public List<Lines> findByLnsNombre(Object lnsNombre) {
		return findByProperty(LNS_NOMBRE, lnsNombre);
	}

	/**
	 * Find all Lines entities.
	 * 
	 * @return List<Lines> all Lines entities
	 */
	@SuppressWarnings("unchecked")
	public List<Lines> findAll() {
		EntityManagerHelper.log("finding all Lines instances", Level.INFO, null);
		try {
			final String queryString = "select model from Lines model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}