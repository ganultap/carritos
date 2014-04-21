package geniar.siscar.persistence;

import geniar.siscar.model.MoneyType;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * MoneyType entities. Transaction control of the save(), update() and delete()
 * operations must be handled externally by senders of these methods or must be
 * manually added to each of these methods for data to be persisted to the JPA
 * datastore.
 * 
 * @see geniar.siscar.model.MoneyType
 * @author MyEclipse Persistence Tools
 */

public class MoneyTypeDAO implements IMoneyTypeDAO {
	// property constants
	public static final String MNEY_NOMBRE = "mneyNombre";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved MoneyType entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * MoneyTypeDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            MoneyType entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(MoneyType entity) {
		EntityManagerHelper.log("saving MoneyType instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent MoneyType entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * MoneyTypeDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            MoneyType entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(MoneyType entity) {
		EntityManagerHelper.log("deleting MoneyType instance", Level.INFO, null);
		try {
			entity = getEntityManager().getReference(MoneyType.class, entity.getMneyId());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved MoneyType entity and return it or a copy of it
	 * to the sender. A copy of the MoneyType entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = MoneyTypeDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            MoneyType entity to update
	 * @returns MoneyType the persisted MoneyType entity instance, may not be
	 *          the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public MoneyType update(MoneyType entity) {
		EntityManagerHelper.log("updating MoneyType instance", Level.INFO, null);
		try {
			MoneyType result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public MoneyType findById(Long id) {
		EntityManagerHelper.log("finding MoneyType instance with id: " + id, Level.INFO, null);
		try {
			MoneyType instance = getEntityManager().find(MoneyType.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all MoneyType entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the MoneyType property to query
	 * @param value
	 *            the property value to match
	 * @return List<MoneyType> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<MoneyType> findByProperty(String propertyName, final Object value) {
		EntityManagerHelper.log("finding MoneyType instance with property: " + propertyName + ", value: " + value,
				Level.INFO, null);
		try {
			final String queryString = "select model from MoneyType model where model." + propertyName
					+ "= :propertyValue";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("propertyValue", value);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find by property name failed", Level.SEVERE, re);
			throw re;
		}
	}

	public List<MoneyType> findByMneyNombre(Object mneyNombre) {
		return findByProperty(MNEY_NOMBRE, mneyNombre);
	}

	/**
	 * Find all MoneyType entities.
	 * 
	 * @return List<MoneyType> all MoneyType entities
	 */
	@SuppressWarnings("unchecked")
	public List<MoneyType> findAll() {
		EntityManagerHelper.log("finding all MoneyType instances", Level.INFO, null);
		try {
			final String queryString = "select model from MoneyType model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}