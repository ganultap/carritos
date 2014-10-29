package geniar.siscar.persistence;

import geniar.siscar.model.Responsibility;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * Responsibility entities. Transaction control of the save(), update() and
 * delete() operations must be handled externally by senders of these methods or
 * must be manually added to each of these methods for data to be persisted to
 * the JPA datastore.
 * 
 * @see geniar.siscar.model.Responsibility
 * @author MyEclipse Persistence Tools
 */

public class ResponsibilityDAO implements IResponsibilityDAO {
	// property constants
	public static final String RES_NOMBRE = "resNombre";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved Responsibility entity.
	 * All subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ResponsibilityDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Responsibility entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Responsibility entity) {
		EntityManagerHelper.log("saving Responsibility instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent Responsibility entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ResponsibilityDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Responsibility entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Responsibility entity) {
		EntityManagerHelper.log("deleting Responsibility instance", Level.INFO, null);
		try {
			entity = getEntityManager().getReference(Responsibility.class, entity.getResCodigo());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved Responsibility entity and return it or a copy
	 * of it to the sender. A copy of the Responsibility entity parameter is
	 * returned when the JPA persistence mechanism has not previously been
	 * tracking the updated entity. This operation must be performed within the
	 * a database transaction context for the entity's data to be permanently
	 * saved to the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = ResponsibilityDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Responsibility entity to update
	 * @returns Responsibility the persisted Responsibility entity instance, may
	 *          not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Responsibility update(Responsibility entity) {
		EntityManagerHelper.log("updating Responsibility instance", Level.INFO, null);
		try {
			Responsibility result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Responsibility findById(Long id) {
		EntityManagerHelper.log("finding Responsibility instance with id: " + id, Level.INFO, null);
		try {
			Responsibility instance = getEntityManager().find(Responsibility.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all Responsibility entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Responsibility property to query
	 * @param value
	 *            the property value to match
	 * @return List<Responsibility> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Responsibility> findByProperty(String propertyName, final Object value) {
		EntityManagerHelper.log("finding Responsibility instance with property: " + propertyName + ", value: " + value,
				Level.INFO, null);
		try {
			final String queryString = "select model from Responsibility model where model." + propertyName
					+ "= :propertyValue";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("propertyValue", value);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find by property name failed", Level.SEVERE, re);
			throw re;
		}
	}

	public List<Responsibility> findByResNombre(Object resNombre) {
		return findByProperty(RES_NOMBRE, resNombre);
	}

	/**
	 * Find all Responsibility entities.
	 * 
	 * @return List<Responsibility> all Responsibility entities
	 */
	@SuppressWarnings("unchecked")
	public List<Responsibility> findAll() {
		EntityManagerHelper.log("finding all Responsibility instances", Level.INFO, null);
		try {
			final String queryString = "select model from Responsibility model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}