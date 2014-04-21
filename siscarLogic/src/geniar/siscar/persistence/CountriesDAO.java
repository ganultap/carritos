package geniar.siscar.persistence;

import geniar.siscar.model.Countries;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * Countries entities. Transaction control of the save(), update() and delete()
 * operations must be handled externally by senders of these methods or must be
 * manually added to each of these methods for data to be persisted to the JPA
 * datastore.
 * 
 * @see geniar.siscar.model.Countries
 * @author MyEclipse Persistence Tools
 */

public class CountriesDAO implements ICountriesDAO {
	// property constants
	public static final String CNT_NOMBRE = "cntNombre";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved Countries entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * CountriesDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Countries entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Countries entity) {
		EntityManagerHelper.log("saving Countries instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent Countries entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * CountriesDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Countries entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Countries entity) {
		EntityManagerHelper.log("deleting Countries instance", Level.INFO, null);
		try {
			entity = getEntityManager().getReference(Countries.class, entity.getCntId());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved Countries entity and return it or a copy of it
	 * to the sender. A copy of the Countries entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = CountriesDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Countries entity to update
	 * @returns Countries the persisted Countries entity instance, may not be
	 *          the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Countries update(Countries entity) {
		EntityManagerHelper.log("updating Countries instance", Level.INFO, null);
		try {
			Countries result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Countries findById(Long id) {
		EntityManagerHelper.log("finding Countries instance with id: " + id, Level.INFO, null);
		try {
			Countries instance = getEntityManager().find(Countries.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all Countries entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Countries property to query
	 * @param value
	 *            the property value to match
	 * @return List<Countries> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Countries> findByProperty(String propertyName, final Object value) {
		EntityManagerHelper.log("finding Countries instance with property: " + propertyName + ", value: " + value,
				Level.INFO, null);
		try {
			final String queryString = "select model from Countries model where model." + propertyName
					+ "= :propertyValue";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("propertyValue", value);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find by property name failed", Level.SEVERE, re);
			throw re;
		}
	}

	public List<Countries> findByCntNombre(Object cntNombre) {
		return findByProperty(CNT_NOMBRE, cntNombre);
	}

	/**
	 * Find all Countries entities.
	 * 
	 * @return List<Countries> all Countries entities
	 */
	@SuppressWarnings("unchecked")
	public List<Countries> findAll() {
		EntityManagerHelper.log("finding all Countries instances", Level.INFO, null);
		try {
			final String queryString = "select model from Countries model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}