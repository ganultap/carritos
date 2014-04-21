package geniar.siscar.persistence;

import geniar.siscar.model.RevisionHour;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * RevisionHour entities. Transaction control of the save(), update() and
 * delete() operations must be handled externally by senders of these methods or
 * must be manually added to each of these methods for data to be persisted to
 * the JPA datastore.
 * 
 * @see geniar.siscar.model.RevisionHour
 * @author MyEclipse Persistence Tools
 */

public class RevisionHourDAO implements IRevisionHourDAO {
	// property constants
	public static final String RHO_HORA = "rhoHora";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved RevisionHour entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * RevisionHourDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            RevisionHour entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(RevisionHour entity) {
		EntityManagerHelper.log("saving RevisionHour instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent RevisionHour entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * RevisionHourDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            RevisionHour entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(RevisionHour entity) {
		EntityManagerHelper.log("deleting RevisionHour instance", Level.INFO, null);
		try {
			entity = getEntityManager().getReference(RevisionHour.class, entity.getRhoCodigo());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved RevisionHour entity and return it or a copy of
	 * it to the sender. A copy of the RevisionHour entity parameter is returned
	 * when the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = RevisionHourDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            RevisionHour entity to update
	 * @returns RevisionHour the persisted RevisionHour entity instance, may not
	 *          be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public RevisionHour update(RevisionHour entity) {
		EntityManagerHelper.log("updating RevisionHour instance", Level.INFO, null);
		try {
			RevisionHour result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public RevisionHour findById(Long id) {
		EntityManagerHelper.log("finding RevisionHour instance with id: " + id, Level.INFO, null);
		try {
			RevisionHour instance = getEntityManager().find(RevisionHour.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all RevisionHour entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the RevisionHour property to query
	 * @param value
	 *            the property value to match
	 * @return List<RevisionHour> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<RevisionHour> findByProperty(String propertyName, final Object value) {
		EntityManagerHelper.log("finding RevisionHour instance with property: " + propertyName + ", value: " + value,
				Level.INFO, null);
		try {
			final String queryString = "select model from RevisionHour model where model." + propertyName
					+ "= :propertyValue";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("propertyValue", value);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find by property name failed", Level.SEVERE, re);
			throw re;
		}
	}

	public List<RevisionHour> findByRhoHora(Object rhoHora) {
		return findByProperty(RHO_HORA, rhoHora);
	}

	/**
	 * Find all RevisionHour entities.
	 * 
	 * @return List<RevisionHour> all RevisionHour entities
	 */
	@SuppressWarnings("unchecked")
	public List<RevisionHour> findAll() {
		EntityManagerHelper.log("finding all RevisionHour instances", Level.INFO, null);
		try {
			final String queryString = "select model from RevisionHour model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}