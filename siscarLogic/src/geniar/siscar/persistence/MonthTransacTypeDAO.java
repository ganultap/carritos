package geniar.siscar.persistence;

import geniar.siscar.model.MonthTransacType;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * MonthTransacType entities. Transaction control of the save(), update() and
 * delete() operations must be handled externally by senders of these methods or
 * must be manually added to each of these methods for data to be persisted to
 * the JPA datastore.
 * 
 * @see geniar.siscar.model.MonthTransacType
 * @author MyEclipse Persistence Tools
 */

public class MonthTransacTypeDAO implements IMonthTransacTypeDAO {
	// property constants
	public static final String MTT_NOMBRE = "mttNombre";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved MonthTransacType entity.
	 * All subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * MonthTransacTypeDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            MonthTransacType entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(MonthTransacType entity) {
		EntityManagerHelper.log("saving MonthTransacType instance", Level.INFO,
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
	 * Delete a persistent MonthTransacType entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * MonthTransacTypeDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            MonthTransacType entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(MonthTransacType entity) {
		EntityManagerHelper.log("deleting MonthTransacType instance",
				Level.INFO, null);
		try {
			entity = getEntityManager().getReference(MonthTransacType.class,
					entity.getMttId());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved MonthTransacType entity and return it or a
	 * copy of it to the sender. A copy of the MonthTransacType entity parameter
	 * is returned when the JPA persistence mechanism has not previously been
	 * tracking the updated entity. This operation must be performed within the
	 * a database transaction context for the entity's data to be permanently
	 * saved to the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = MonthTransacTypeDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            MonthTransacType entity to update
	 * @return MonthTransacType the persisted MonthTransacType entity instance,
	 *         may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public MonthTransacType update(MonthTransacType entity) {
		EntityManagerHelper.log("updating MonthTransacType instance",
				Level.INFO, null);
		try {
			MonthTransacType result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public MonthTransacType findById(Long id) {
		EntityManagerHelper.log("finding MonthTransacType instance with id: "
				+ id, Level.INFO, null);
		try {
			MonthTransacType instance = getEntityManager().find(
					MonthTransacType.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all MonthTransacType entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the MonthTransacType property to query
	 * @param value
	 *            the property value to match
	 * @return List<MonthTransacType> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<MonthTransacType> findByProperty(String propertyName,
			final Object value) {
		EntityManagerHelper.log(
				"finding MonthTransacType instance with property: "
						+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from MonthTransacType model where model."
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

	public List<MonthTransacType> findByMttNombre(Object mttNombre) {
		return findByProperty(MTT_NOMBRE, mttNombre);
	}

	/**
	 * Find all MonthTransacType entities.
	 * 
	 * @return List<MonthTransacType> all MonthTransacType entities
	 */
	@SuppressWarnings("unchecked")
	public List<MonthTransacType> findAll() {
		EntityManagerHelper.log("finding all MonthTransacType instances",
				Level.INFO, null);
		try {
			final String queryString = "select model from MonthTransacType model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}