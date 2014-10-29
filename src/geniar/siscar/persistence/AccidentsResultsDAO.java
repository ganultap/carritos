package geniar.siscar.persistence;

import geniar.siscar.model.AccidentsResults;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * AccidentsResults entities. Transaction control of the save(), update() and
 * delete() operations must be handled externally by senders of these methods or
 * must be manually added to each of these methods for data to be persisted to
 * the JPA datastore.
 * 
 * @see geniar.siscar.model.AccidentsResults
 * @author MyEclipse Persistence Tools
 */

public class AccidentsResultsDAO implements IAccidentsResultsDAO {
	// property constants
	public static final String ACL_NOMBRE = "aclNombre";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved AccidentsResults entity.
	 * All subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * AccidentsResultsDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            AccidentsResults entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(AccidentsResults entity) {
		EntityManagerHelper.log("saving AccidentsResults instance", Level.INFO,
				null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent AccidentsResults entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * AccidentsResultsDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            AccidentsResults entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(AccidentsResults entity) {
		EntityManagerHelper.log("deleting AccidentsResults instance",
				Level.INFO, null);
		try {
			entity = getEntityManager().getReference(AccidentsResults.class,
					entity.getAclCodigo());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved AccidentsResults entity and return it or a
	 * copy of it to the sender. A copy of the AccidentsResults entity parameter
	 * is returned when the JPA persistence mechanism has not previously been
	 * tracking the updated entity. This operation must be performed within the
	 * a database transaction context for the entity's data to be permanently
	 * saved to the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = AccidentsResultsDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            AccidentsResults entity to update
	 * @return AccidentsResults the persisted AccidentsResults entity instance,
	 *         may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public AccidentsResults update(AccidentsResults entity) {
		EntityManagerHelper.log("updating AccidentsResults instance",
				Level.INFO, null);
		try {
			AccidentsResults result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public AccidentsResults findById(Long id) {
		EntityManagerHelper.log("finding AccidentsResults instance with id: "
				+ id, Level.INFO, null);
		try {
			AccidentsResults instance = getEntityManager().find(
					AccidentsResults.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all AccidentsResults entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the AccidentsResults property to query
	 * @param value
	 *            the property value to match
	 * @return List<AccidentsResults> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<AccidentsResults> findByProperty(String propertyName,
			final Object value) {
		EntityManagerHelper.log(
				"finding AccidentsResults instance with property: "
						+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from AccidentsResults model where model."
					+ propertyName + "= :propertyValue";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("propertyValue", value);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find by property name failed",
					Level.SEVERE, re);
			throw re;
		}
	}

	public List<AccidentsResults> findByAclNombre(Object aclNombre) {
		return findByProperty(ACL_NOMBRE, aclNombre);
	}

	/**
	 * Find all AccidentsResults entities.
	 * 
	 * @return List<AccidentsResults> all AccidentsResults entities
	 */
	@SuppressWarnings("unchecked")
	public List<AccidentsResults> findAll() {
		EntityManagerHelper.log("finding all AccidentsResults instances",
				Level.INFO, null);
		try {
			final String queryString = "select model from AccidentsResults model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}