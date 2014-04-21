package geniar.siscar.persistence;

import geniar.siscar.model.FuelsTypes;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * FuelsTypes entities. Transaction control of the save(), update() and delete()
 * operations must be handled externally by senders of these methods or must be
 * manually added to each of these methods for data to be persisted to the JPA
 * datastore.
 * 
 * @see geniar.siscar.model.FuelsTypes
 * @author MyEclipse Persistence Tools
 */

public class FuelsTypesDAO implements IFuelsTypesDAO {
	// property constants
	public static final String FUT_NOMBRE = "futNombre";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved FuelsTypes entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * FuelsTypesDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            FuelsTypes entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(FuelsTypes entity) {
		EntityManagerHelper.log("saving FuelsTypes instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent FuelsTypes entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * FuelsTypesDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            FuelsTypes entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(FuelsTypes entity) {
		EntityManagerHelper.log("deleting FuelsTypes instance", Level.INFO, null);
		try {
			entity = getEntityManager().getReference(FuelsTypes.class, entity.getFutCodigo());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved FuelsTypes entity and return it or a copy of
	 * it to the sender. A copy of the FuelsTypes entity parameter is returned
	 * when the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = FuelsTypesDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            FuelsTypes entity to update
	 * @returns FuelsTypes the persisted FuelsTypes entity instance, may not be
	 *          the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public FuelsTypes update(FuelsTypes entity) {
		EntityManagerHelper.log("updating FuelsTypes instance", Level.INFO, null);
		try {
			FuelsTypes result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public FuelsTypes findById(Long id) {
		EntityManagerHelper.log("finding FuelsTypes instance with id: " + id, Level.INFO, null);
		try {
			FuelsTypes instance = getEntityManager().find(FuelsTypes.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all FuelsTypes entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the FuelsTypes property to query
	 * @param value
	 *            the property value to match
	 * @return List<FuelsTypes> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<FuelsTypes> findByProperty(String propertyName, final Object value) {
		EntityManagerHelper.log("finding FuelsTypes instance with property: " + propertyName + ", value: " + value,
				Level.INFO, null);
		try {
			final String queryString = "select model from FuelsTypes model where model." + propertyName
					+ "= :propertyValue";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("propertyValue", value);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find by property name failed", Level.SEVERE, re);
			throw re;
		}
	}

	public List<FuelsTypes> findByFutNombre(Object futNombre) {
		return findByProperty(FUT_NOMBRE, futNombre);
	}

	/**
	 * Find all FuelsTypes entities.
	 * 
	 * @return List<FuelsTypes> all FuelsTypes entities
	 */
	@SuppressWarnings("unchecked")
	public List<FuelsTypes> findAll() {
		EntityManagerHelper.log("finding all FuelsTypes instances", Level.INFO, null);
		try {
			final String queryString = "select model from FuelsTypes model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}