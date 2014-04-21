package geniar.siscar.persistence;

import geniar.siscar.model.CurrencyTypes;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * CurrencyTypes entities. Transaction control of the save(), update() and
 * delete() operations must be handled externally by senders of these methods or
 * must be manually added to each of these methods for data to be persisted to
 * the JPA datastore.
 * 
 * @see geniar.siscar.model.CurrencyTypes
 * @author MyEclipse Persistence Tools
 */

public class CurrencyTypesDAO implements ICurrencyTypesDAO {
	// property constants
	public static final String CT_NOMBRE = "ctNombre";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved CurrencyTypes entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * CurrencyTypesDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            CurrencyTypes entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(CurrencyTypes entity) {
		EntityManagerHelper.log("saving CurrencyTypes instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent CurrencyTypes entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * CurrencyTypesDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            CurrencyTypes entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(CurrencyTypes entity) {
		EntityManagerHelper.log("deleting CurrencyTypes instance", Level.INFO, null);
		try {
			entity = getEntityManager().getReference(CurrencyTypes.class, entity.getCtId());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved CurrencyTypes entity and return it or a copy
	 * of it to the sender. A copy of the CurrencyTypes entity parameter is
	 * returned when the JPA persistence mechanism has not previously been
	 * tracking the updated entity. This operation must be performed within the
	 * a database transaction context for the entity's data to be permanently
	 * saved to the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = CurrencyTypesDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            CurrencyTypes entity to update
	 * @returns CurrencyTypes the persisted CurrencyTypes entity instance, may
	 *          not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public CurrencyTypes update(CurrencyTypes entity) {
		EntityManagerHelper.log("updating CurrencyTypes instance", Level.INFO, null);
		try {
			CurrencyTypes result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public CurrencyTypes findById(Long id) {
		EntityManagerHelper.log("finding CurrencyTypes instance with id: " + id, Level.INFO, null);
		try {
			CurrencyTypes instance = getEntityManager().find(CurrencyTypes.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all CurrencyTypes entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the CurrencyTypes property to query
	 * @param value
	 *            the property value to match
	 * @return List<CurrencyTypes> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<CurrencyTypes> findByProperty(String propertyName, final Object value) {
		EntityManagerHelper.log("finding CurrencyTypes instance with property: " + propertyName + ", value: " + value,
				Level.INFO, null);
		try {
			final String queryString = "select model from CurrencyTypes model where model." + propertyName
					+ "= :propertyValue";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("propertyValue", value);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find by property name failed", Level.SEVERE, re);
			throw re;
		}
	}

	public List<CurrencyTypes> findByCtNombre(Object ctNombre) {
		return findByProperty(CT_NOMBRE, ctNombre);
	}

	/**
	 * Find all CurrencyTypes entities.
	 * 
	 * @return List<CurrencyTypes> all CurrencyTypes entities
	 */
	@SuppressWarnings("unchecked")
	public List<CurrencyTypes> findAll() {
		EntityManagerHelper.log("finding all CurrencyTypes instances", Level.INFO, null);
		try {
			final String queryString = "select model from CurrencyTypes model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}