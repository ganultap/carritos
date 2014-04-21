package geniar.siscar.persistence;

import geniar.siscar.model.FuelPerformance;

import java.util.List;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * FuelPerformance entities. Transaction control of the save(), update() and
 * delete() operations must be handled externally by senders of these methods or
 * must be manually added to each of these methods for data to be persisted to
 * the JPA datastore.
 * 
 * @see geniar.siscar.model.FuelPerformance
 * @author MyEclipse Persistence Tools
 */

public class FuelPerformanceDAO implements IFuelPerformanceDAO {
	// property constants
	public static final String VALOR_RENDIMIENTO = "valorRendimiento";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved FuelPerformance entity.
	 * All subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * FuelPerformanceDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            FuelPerformance entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(FuelPerformance entity) {
		EntityManagerHelper.log("saving FuelPerformance instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent FuelPerformance entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * FuelPerformanceDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            FuelPerformance entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(FuelPerformance entity) {
		EntityManagerHelper.log("deleting FuelPerformance instance", Level.INFO, null);
		try {
			entity = getEntityManager().getReference(FuelPerformance.class, entity.getFpmCodigo());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved FuelPerformance entity and return it or a copy
	 * of it to the sender. A copy of the FuelPerformance entity parameter is
	 * returned when the JPA persistence mechanism has not previously been
	 * tracking the updated entity. This operation must be performed within the
	 * a database transaction context for the entity's data to be permanently
	 * saved to the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = FuelPerformanceDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            FuelPerformance entity to update
	 * @returns FuelPerformance the persisted FuelPerformance entity instance,
	 *          may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public FuelPerformance update(FuelPerformance entity) {
		EntityManagerHelper.log("updating FuelPerformance instance", Level.INFO, null);
		try {
			FuelPerformance result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public FuelPerformance findById(Long id) {
		EntityManagerHelper.log("finding FuelPerformance instance with id: " + id, Level.INFO, null);
		try {
			FuelPerformance instance = getEntityManager().find(FuelPerformance.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all FuelPerformance entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the FuelPerformance property to query
	 * @param value
	 *            the property value to match
	 * @return List<FuelPerformance> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<FuelPerformance> findByProperty(String propertyName, final Object value) {
		EntityManagerHelper.log(
				"finding FuelPerformance instance with property: " + propertyName + ", value: " + value, Level.INFO,
				null);
		try {
			final String queryString = "select model from FuelPerformance model where model." + propertyName
					+ "= :propertyValue";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("propertyValue", value);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find by property name failed", Level.SEVERE, re);
			throw re;
		}
	}

	public List<FuelPerformance> findByValorRendimiento(Object valorRendimiento) {
		return findByProperty(VALOR_RENDIMIENTO, valorRendimiento);
	}

	/**
	 * Find all FuelPerformance entities.
	 * 
	 * @return List<FuelPerformance> all FuelPerformance entities
	 */
	@SuppressWarnings("unchecked")
	public List<FuelPerformance> findAll() {
		EntityManagerHelper.log("finding all FuelPerformance instances", Level.INFO, null);
		try {
			final String queryString = "select model from FuelPerformance model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}