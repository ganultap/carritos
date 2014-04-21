package geniar.siscar.persistence;

import geniar.siscar.model.Brands;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * Brands entities. Transaction control of the save(), update() and delete()
 * operations must be handled externally by senders of these methods or must be
 * manually added to each of these methods for data to be persisted to the JPA
 * datastore.
 * 
 * @see geniar.siscar.model.Brands
 * @author MyEclipse Persistence Tools
 */

public class BrandsDAO implements IBrandsDAO {
	// property constants
	public static final String BRN_NOMBRE = "brnNombre";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved Brands entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * BrandsDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Brands entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Brands entity) {
		EntityManagerHelper.log("saving Brands instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent Brands entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * BrandsDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Brands entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Brands entity) {
		EntityManagerHelper.log("deleting Brands instance", Level.INFO, null);
		try {
			entity = getEntityManager().getReference(Brands.class, entity.getBrnId());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved Brands entity and return it or a copy of it to
	 * the sender. A copy of the Brands entity parameter is returned when the
	 * JPA persistence mechanism has not previously been tracking the updated
	 * entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = BrandsDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Brands entity to update
	 * @returns Brands the persisted Brands entity instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Brands update(Brands entity) {
		EntityManagerHelper.log("updating Brands instance", Level.INFO, null);
		try {
			Brands result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Brands findById(Long id) {
		EntityManagerHelper.log("finding Brands instance with id: " + id, Level.INFO, null);
		try {
			Brands instance = getEntityManager().find(Brands.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all Brands entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Brands property to query
	 * @param value
	 *            the property value to match
	 * @return List<Brands> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Brands> findByProperty(String propertyName, final Object value) {
		EntityManagerHelper.log("finding Brands instance with property: " + propertyName + ", value: " + value,
				Level.INFO, null);
		try {
			final String queryString = "select model from Brands model where model." + propertyName
					+ "= :propertyValue";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("propertyValue", value);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find by property name failed", Level.SEVERE, re);
			throw re;
		}
	}

	public List<Brands> findByBrnNombre(Object brnNombre) {
		return findByProperty(BRN_NOMBRE, brnNombre);
	}

	/**
	 * Find all Brands entities.
	 * 
	 * @return List<Brands> all Brands entities
	 */
	@SuppressWarnings("unchecked")
	public List<Brands> findAll() {
		EntityManagerHelper.log("finding all Brands instances", Level.INFO, null);
		try {
			final String queryString = "select model from Brands model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}