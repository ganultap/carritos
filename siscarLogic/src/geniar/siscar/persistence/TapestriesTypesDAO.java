package geniar.siscar.persistence;

import geniar.siscar.model.TapestriesTypes;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * TapestriesTypes entities. Transaction control of the save(), update() and
 * delete() operations must be handled externally by senders of these methods or
 * must be manually added to each of these methods for data to be persisted to
 * the JPA datastore.
 * 
 * @see geniar.siscar.model.TapestriesTypes
 * @author MyEclipse Persistence Tools
 */

public class TapestriesTypesDAO implements ITapestriesTypesDAO {
	// property constants
	public static final String TPTPC_NOMBRE = "tptpcNombre";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved TapestriesTypes entity.
	 * All subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * TapestriesTypesDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            TapestriesTypes entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(TapestriesTypes entity) {
		EntityManagerHelper.log("saving TapestriesTypes instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent TapestriesTypes entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * TapestriesTypesDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            TapestriesTypes entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(TapestriesTypes entity) {
		EntityManagerHelper.log("deleting TapestriesTypes instance", Level.INFO, null);
		try {
			entity = getEntityManager().getReference(TapestriesTypes.class, entity.getTptpcCodigo());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved TapestriesTypes entity and return it or a copy
	 * of it to the sender. A copy of the TapestriesTypes entity parameter is
	 * returned when the JPA persistence mechanism has not previously been
	 * tracking the updated entity. This operation must be performed within the
	 * a database transaction context for the entity's data to be permanently
	 * saved to the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = TapestriesTypesDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            TapestriesTypes entity to update
	 * @returns TapestriesTypes the persisted TapestriesTypes entity instance,
	 *          may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public TapestriesTypes update(TapestriesTypes entity) {
		EntityManagerHelper.log("updating TapestriesTypes instance", Level.INFO, null);
		try {
			TapestriesTypes result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public TapestriesTypes findById(Long id) {
		EntityManagerHelper.log("finding TapestriesTypes instance with id: " + id, Level.INFO, null);
		try {
			TapestriesTypes instance = getEntityManager().find(TapestriesTypes.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all TapestriesTypes entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the TapestriesTypes property to query
	 * @param value
	 *            the property value to match
	 * @return List<TapestriesTypes> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<TapestriesTypes> findByProperty(String propertyName, final Object value) {
		EntityManagerHelper.log(
				"finding TapestriesTypes instance with property: " + propertyName + ", value: " + value, Level.INFO,
				null);
		try {
			final String queryString = "select model from TapestriesTypes model where model." + propertyName
					+ "= :propertyValue";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("propertyValue", value);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find by property name failed", Level.SEVERE, re);
			throw re;
		}
	}

	public List<TapestriesTypes> findByTptpcNombre(Object tptpcNombre) {
		return findByProperty(TPTPC_NOMBRE, tptpcNombre);
	}

	/**
	 * Find all TapestriesTypes entities.
	 * 
	 * @return List<TapestriesTypes> all TapestriesTypes entities
	 */
	@SuppressWarnings("unchecked")
	public List<TapestriesTypes> findAll() {
		EntityManagerHelper.log("finding all TapestriesTypes instances", Level.INFO, null);
		try {
			final String queryString = "select model from TapestriesTypes model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}