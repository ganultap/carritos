package geniar.siscar.persistence;

import geniar.siscar.model.Severity;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * Severity entities. Transaction control of the save(), update() and delete()
 * operations must be handled externally by senders of these methods or must be
 * manually added to each of these methods for data to be persisted to the JPA
 * datastore.
 * 
 * @see geniar.siscar.model.Severity
 * @author MyEclipse Persistence Tools
 */

public class SeverityDAO implements ISeverityDAO {
	// property constants
	public static final String SEV_NOMBRE = "sevNombre";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved Severity entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * SeverityDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Severity entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Severity entity) {
		EntityManagerHelper.log("saving Severity instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent Severity entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * SeverityDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Severity entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Severity entity) {
		EntityManagerHelper.log("deleting Severity instance", Level.INFO, null);
		try {
			entity = getEntityManager().getReference(Severity.class, entity.getSevCodigo());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved Severity entity and return it or a copy of it
	 * to the sender. A copy of the Severity entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = SeverityDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Severity entity to update
	 * @returns Severity the persisted Severity entity instance, may not be the
	 *          same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Severity update(Severity entity) {
		EntityManagerHelper.log("updating Severity instance", Level.INFO, null);
		try {
			Severity result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Severity findById(Long id) {
		EntityManagerHelper.log("finding Severity instance with id: " + id, Level.INFO, null);
		try {
			Severity instance = getEntityManager().find(Severity.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all Severity entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Severity property to query
	 * @param value
	 *            the property value to match
	 * @return List<Severity> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Severity> findByProperty(String propertyName, final Object value) {
		EntityManagerHelper.log("finding Severity instance with property: " + propertyName + ", value: " + value,
				Level.INFO, null);
		try {
			final String queryString = "select model from Severity model where model." + propertyName
					+ "= :propertyValue";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("propertyValue", value);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find by property name failed", Level.SEVERE, re);
			throw re;
		}
	}

	public List<Severity> findBySevNombre(Object sevNombre) {
		return findByProperty(SEV_NOMBRE, sevNombre);
	}

	/**
	 * Find all Severity entities.
	 * 
	 * @return List<Severity> all Severity entities
	 */
	@SuppressWarnings("unchecked")
	public List<Severity> findAll() {
		EntityManagerHelper.log("finding all Severity instances", Level.INFO, null);
		try {
			final String queryString = "select model from Severity model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}