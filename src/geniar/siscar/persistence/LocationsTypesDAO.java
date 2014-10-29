package geniar.siscar.persistence;

import geniar.siscar.model.LocationsTypes;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * LocationsTypes entities. Transaction control of the save(), update() and
 * delete() operations must be handled externally by senders of these methods or
 * must be manually added to each of these methods for data to be persisted to
 * the JPA datastore.
 * 
 * @see geniar.siscar.model.LocationsTypes
 * @author MyEclipse Persistence Tools
 */

public class LocationsTypesDAO implements ILocationsTypesDAO {
	// property constants
	public static final String LCT_NOMBRE = "lctNombre";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved LocationsTypes entity.
	 * All subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * LocationsTypesDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            LocationsTypes entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(LocationsTypes entity) {
		EntityManagerHelper.log("saving LocationsTypes instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent LocationsTypes entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * LocationsTypesDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            LocationsTypes entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(LocationsTypes entity) {
		EntityManagerHelper.log("deleting LocationsTypes instance", Level.INFO, null);
		try {
			entity = getEntityManager().getReference(LocationsTypes.class, entity.getLctCodigo());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved LocationsTypes entity and return it or a copy
	 * of it to the sender. A copy of the LocationsTypes entity parameter is
	 * returned when the JPA persistence mechanism has not previously been
	 * tracking the updated entity. This operation must be performed within the
	 * a database transaction context for the entity's data to be permanently
	 * saved to the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = LocationsTypesDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            LocationsTypes entity to update
	 * @returns LocationsTypes the persisted LocationsTypes entity instance, may
	 *          not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public LocationsTypes update(LocationsTypes entity) {
		EntityManagerHelper.log("updating LocationsTypes instance", Level.INFO, null);
		try {
			LocationsTypes result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public LocationsTypes findById(Long id) {
		EntityManagerHelper.log("finding LocationsTypes instance with id: " + id, Level.INFO, null);
		try {
			LocationsTypes instance = getEntityManager().find(LocationsTypes.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all LocationsTypes entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the LocationsTypes property to query
	 * @param value
	 *            the property value to match
	 * @return List<LocationsTypes> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<LocationsTypes> findByProperty(String propertyName, final Object value) {
		EntityManagerHelper.log("finding LocationsTypes instance with property: " + propertyName + ", value: " + value,
				Level.INFO, null);
		try {
			final String queryString = "select model from LocationsTypes model where model." + propertyName
					+ "= :propertyValue";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("propertyValue", value);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find by property name failed", Level.SEVERE, re);
			throw re;
		}
	}

	public List<LocationsTypes> findByLctNombre(Object lctNombre) {
		return findByProperty(LCT_NOMBRE, lctNombre);
	}

	/**
	 * Find all LocationsTypes entities.
	 * 
	 * @return List<LocationsTypes> all LocationsTypes entities
	 */
	@SuppressWarnings("unchecked")
	public List<LocationsTypes> findAll() {
		EntityManagerHelper.log("finding all LocationsTypes instances", Level.INFO, null);
		try {
			final String queryString = "select model from LocationsTypes model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}