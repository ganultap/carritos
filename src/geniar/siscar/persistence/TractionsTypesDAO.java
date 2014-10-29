package geniar.siscar.persistence;

import geniar.siscar.model.TractionsTypes;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * TractionsTypes entities. Transaction control of the save(), update() and
 * delete() operations must be handled externally by senders of these methods or
 * must be manually added to each of these methods for data to be persisted to
 * the JPA datastore.
 * 
 * @see geniar.siscar.model.TractionsTypes
 * @author MyEclipse Persistence Tools
 */

public class TractionsTypesDAO implements ITractionsTypesDAO {
	// property constants
	public static final String TCT_NOMBRE = "tctNombre";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved TractionsTypes entity.
	 * All subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * TractionsTypesDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            TractionsTypes entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(TractionsTypes entity) {
		EntityManagerHelper.log("saving TractionsTypes instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent TractionsTypes entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * TractionsTypesDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            TractionsTypes entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(TractionsTypes entity) {
		EntityManagerHelper.log("deleting TractionsTypes instance", Level.INFO, null);
		try {
			entity = getEntityManager().getReference(TractionsTypes.class, entity.getTctCodigo());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved TractionsTypes entity and return it or a copy
	 * of it to the sender. A copy of the TractionsTypes entity parameter is
	 * returned when the JPA persistence mechanism has not previously been
	 * tracking the updated entity. This operation must be performed within the
	 * a database transaction context for the entity's data to be permanently
	 * saved to the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = TractionsTypesDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            TractionsTypes entity to update
	 * @returns TractionsTypes the persisted TractionsTypes entity instance, may
	 *          not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public TractionsTypes update(TractionsTypes entity) {
		EntityManagerHelper.log("updating TractionsTypes instance", Level.INFO, null);
		try {
			TractionsTypes result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public TractionsTypes findById(Long id) {
		EntityManagerHelper.log("finding TractionsTypes instance with id: " + id, Level.INFO, null);
		try {
			TractionsTypes instance = getEntityManager().find(TractionsTypes.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all TractionsTypes entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the TractionsTypes property to query
	 * @param value
	 *            the property value to match
	 * @return List<TractionsTypes> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<TractionsTypes> findByProperty(String propertyName, final Object value) {
		EntityManagerHelper.log("finding TractionsTypes instance with property: " + propertyName + ", value: " + value,
				Level.INFO, null);
		try {
			final String queryString = "select model from TractionsTypes model where model." + propertyName
					+ "= :propertyValue";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("propertyValue", value);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find by property name failed", Level.SEVERE, re);
			throw re;
		}
	}

	public List<TractionsTypes> findByTctNombre(Object tctNombre) {
		return findByProperty(TCT_NOMBRE, tctNombre);
	}

	/**
	 * Find all TractionsTypes entities.
	 * 
	 * @return List<TractionsTypes> all TractionsTypes entities
	 */
	@SuppressWarnings("unchecked")
	public List<TractionsTypes> findAll() {
		EntityManagerHelper.log("finding all TractionsTypes instances", Level.INFO, null);
		try {
			final String queryString = "select model from TractionsTypes model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}