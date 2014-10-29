package geniar.siscar.persistence;

import geniar.siscar.model.Locations;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * Locations entities. Transaction control of the save(), update() and delete()
 * operations must be handled externally by senders of these methods or must be
 * manually added to each of these methods for data to be persisted to the JPA
 * datastore.
 * 
 * @see geniar.siscar.model.Locations
 * @author MyEclipse Persistence Tools
 */

public class LocationsDAO implements ILocationsDAO {
	// property constants
	public static final String LCN_DESCRIPCION = "lcnDescripcion";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved Locations entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * LocationsDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Locations entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Locations entity) {
		EntityManagerHelper.log("saving Locations instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent Locations entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * LocationsDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Locations entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Locations entity) {
		EntityManagerHelper.log("deleting Locations instance", Level.INFO, null);
		try {
			entity = getEntityManager().getReference(Locations.class, entity.getLcnCodigo());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved Locations entity and return it or a copy of it
	 * to the sender. A copy of the Locations entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = LocationsDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Locations entity to update
	 * @returns Locations the persisted Locations entity instance, may not be
	 *          the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Locations update(Locations entity) {
		EntityManagerHelper.log("updating Locations instance", Level.INFO, null);
		try {
			Locations result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Locations findById(Long id) {
		EntityManagerHelper.log("finding Locations instance with id: " + id, Level.INFO, null);
		try {
			Locations instance = getEntityManager().find(Locations.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all Locations entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Locations property to query
	 * @param value
	 *            the property value to match
	 * @return List<Locations> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Locations> findByProperty(String propertyName, final Object value) {
		EntityManagerHelper.log("finding Locations instance with property: " + propertyName + ", value: " + value,
				Level.INFO, null);
		try {
			final String queryString = "select model from Locations model where model." + propertyName
					+ "= :propertyValue";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("propertyValue", value);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find by property name failed", Level.SEVERE, re);
			throw re;
		}
	}

	public List<Locations> findByLcnDescripcion(Object lcnDescripcion) {
		return findByProperty(LCN_DESCRIPCION, lcnDescripcion);
	}

	/**
	 * Find all Locations entities.
	 * 
	 * @return List<Locations> all Locations entities
	 */
	@SuppressWarnings("unchecked")
	public List<Locations> findAll() {
		EntityManagerHelper.log("finding all Locations instances", Level.INFO, null);
		try {
			final String queryString = "select model from Locations model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}