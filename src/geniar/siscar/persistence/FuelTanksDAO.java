package geniar.siscar.persistence;

import geniar.siscar.model.FuelTanks;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * FuelTanks entities. Transaction control of the save(), update() and delete()
 * operations must be handled externally by senders of these methods or must be
 * manually added to each of these methods for data to be persisted to the JPA
 * datastore.
 * 
 * @see geniar.siscar.model.FuelTanks
 * @author MyEclipse Persistence Tools
 */

public class FuelTanksDAO implements IFuelTanksDAO {
	// property constants
	public static final String FTA_NOMBRE = "ftaNombre";
	public static final String FTA_CAPACIDAD_MAXIMA = "ftaCapacidadMaxima";
	public static final String FTA_GALONES_ACTUALES = "ftaGalonesActuales";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved FuelTanks entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * FuelTanksDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            FuelTanks entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(FuelTanks entity) {
		EntityManagerHelper.log("saving FuelTanks instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent FuelTanks entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * FuelTanksDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            FuelTanks entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(FuelTanks entity) {
		EntityManagerHelper.log("deleting FuelTanks instance", Level.INFO, null);
		try {
			entity = getEntityManager().getReference(FuelTanks.class, entity.getFtaCodigo());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved FuelTanks entity and return it or a copy of it
	 * to the sender. A copy of the FuelTanks entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = FuelTanksDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            FuelTanks entity to update
	 * @returns FuelTanks the persisted FuelTanks entity instance, may not be
	 *          the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public FuelTanks update(FuelTanks entity) {
		EntityManagerHelper.log("updating FuelTanks instance", Level.INFO, null);
		try {
			FuelTanks result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public FuelTanks findById(Long id) {
		EntityManagerHelper.log("finding FuelTanks instance with id: " + id, Level.INFO, null);
		try {
			FuelTanks instance = getEntityManager().find(FuelTanks.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all FuelTanks entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the FuelTanks property to query
	 * @param value
	 *            the property value to match
	 * @return List<FuelTanks> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<FuelTanks> findByProperty(String propertyName, final Object value) {
		EntityManagerHelper.log("finding FuelTanks instance with property: " + propertyName + ", value: " + value,
				Level.INFO, null);
		try {
			final String queryString = "select model from FuelTanks model where model." + propertyName
					+ "= :propertyValue";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("propertyValue", value);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find by property name failed", Level.SEVERE, re);
			throw re;
		}
	}

	public List<FuelTanks> findByFtaNombre(Object ftaNombre) {
		return findByProperty(FTA_NOMBRE, ftaNombre);
	}

	public List<FuelTanks> findByFtaCapacidadMaxima(Object ftaCapacidadMaxima) {
		return findByProperty(FTA_CAPACIDAD_MAXIMA, ftaCapacidadMaxima);
	}

	public List<FuelTanks> findByFtaGalonesActuales(Object ftaGalonesActuales) {
		return findByProperty(FTA_GALONES_ACTUALES, ftaGalonesActuales);
	}

	/**
	 * Find all FuelTanks entities.
	 * 
	 * @return List<FuelTanks> all FuelTanks entities
	 */
	@SuppressWarnings("unchecked")
	public List<FuelTanks> findAll() {
		EntityManagerHelper.log("finding all FuelTanks instances", Level.INFO, null);
		try {
			final String queryString = "select model from FuelTanks model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}