package geniar.siscar.persistence;

import geniar.siscar.model.RetirementsReasons;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * RetirementsReasons entities. Transaction control of the save(), update() and
 * delete() operations must be handled externally by senders of these methods or
 * must be manually added to each of these methods for data to be persisted to
 * the JPA datastore.
 * 
 * @see geniar.siscar.model.RetirementsReasons
 * @author MyEclipse Persistence Tools
 */

public class RetirementsReasonsDAO implements IRetirementsReasonsDAO {
	// property constants
	public static final String RER_LOGIN_USR = "rerLoginUsr";
	public static final String RER_DESCRIPCION = "rerDescripcion";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved RetirementsReasons
	 * entity. All subsequent persist actions of this entity should use the
	 * #update() method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * RetirementsReasonsDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            RetirementsReasons entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(RetirementsReasons entity) {
		EntityManagerHelper.log("saving RetirementsReasons instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent RetirementsReasons entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * RetirementsReasonsDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            RetirementsReasons entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(RetirementsReasons entity) {
		EntityManagerHelper.log("deleting RetirementsReasons instance", Level.INFO, null);
		try {
			entity = getEntityManager().getReference(RetirementsReasons.class, entity.getRerCodigo());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved RetirementsReasons entity and return it or a
	 * copy of it to the sender. A copy of the RetirementsReasons entity
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
	 * entity = RetirementsReasonsDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            RetirementsReasons entity to update
	 * @returns RetirementsReasons the persisted RetirementsReasons entity
	 *          instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public RetirementsReasons update(RetirementsReasons entity) {
		EntityManagerHelper.log("updating RetirementsReasons instance", Level.INFO, null);
		try {
			RetirementsReasons result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public RetirementsReasons findById(Long id) {
		EntityManagerHelper.log("finding RetirementsReasons instance with id: " + id, Level.INFO, null);
		try {
			RetirementsReasons instance = getEntityManager().find(RetirementsReasons.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all RetirementsReasons entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the RetirementsReasons property to query
	 * @param value
	 *            the property value to match
	 * @return List<RetirementsReasons> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<RetirementsReasons> findByProperty(String propertyName, final Object value) {
		EntityManagerHelper.log("finding RetirementsReasons instance with property: " + propertyName + ", value: "
				+ value, Level.INFO, null);
		try {
			final String queryString = "select model from RetirementsReasons model where model." + propertyName
					+ "= :propertyValue";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("propertyValue", value);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find by property name failed", Level.SEVERE, re);
			throw re;
		}
	}

	public List<RetirementsReasons> findByRerLoginUsr(Object rerLoginUsr) {
		return findByProperty(RER_LOGIN_USR, rerLoginUsr);
	}

	public List<RetirementsReasons> findByRerDescripcion(Object rerDescripcion) {
		return findByProperty(RER_DESCRIPCION, rerDescripcion);
	}

	/**
	 * Find all RetirementsReasons entities.
	 * 
	 * @return List<RetirementsReasons> all RetirementsReasons entities
	 */
	@SuppressWarnings("unchecked")
	public List<RetirementsReasons> findAll() {
		EntityManagerHelper.log("finding all RetirementsReasons instances", Level.INFO, null);
		try {
			final String queryString = "select model from RetirementsReasons model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}