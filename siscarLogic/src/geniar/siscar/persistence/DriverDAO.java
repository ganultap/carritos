package geniar.siscar.persistence;

import geniar.siscar.model.Driver;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * Driver entities. Transaction control of the save(), update() and delete()
 * operations must be handled externally by senders of these methods or must be
 * manually added to each of these methods for data to be persisted to the JPA
 * datastore.
 * 
 * @see geniar.siscar.model.Driver
 * @author MyEclipse Persistence Tools
 */

public class DriverDAO implements IDriverDAO {
	// property constants
	public static final String DRV_NOMBRE = "drvNombre";
	public static final String DRV_NUMERO_CARNE = "drvNumeroCarne";
	public static final String DRV_CARGO = "drvCargo";
	public static final String DRV_DIRECCION = "drvDireccion";
	public static final String DRV_TELEFONO = "drvTelefono";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved Driver entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * DriverDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Driver entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Driver entity) {
		EntityManagerHelper.log("saving Driver instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent Driver entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * DriverDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Driver entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Driver entity) {
		EntityManagerHelper.log("deleting Driver instance", Level.INFO, null);
		try {
			entity = getEntityManager().getReference(Driver.class,
					entity.getDrvCedula());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved Driver entity and return it or a copy of it to
	 * the sender. A copy of the Driver entity parameter is returned when the
	 * JPA persistence mechanism has not previously been tracking the updated
	 * entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = DriverDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Driver entity to update
	 * @return Driver the persisted Driver entity instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Driver update(Driver entity) {
		EntityManagerHelper.log("updating Driver instance", Level.INFO, null);
		try {
			Driver result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Driver findById(String id) {
		EntityManagerHelper.log("finding Driver instance with id: " + id,
				Level.INFO, null);
		try {
			Driver instance = getEntityManager().find(Driver.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all Driver entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Driver property to query
	 * @param value
	 *            the property value to match
	 * @return List<Driver> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Driver> findByProperty(String propertyName, final Object value) {
		EntityManagerHelper.log("finding Driver instance with property: "
				+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from Driver model where model."
					+ propertyName + "= :propertyValue";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("propertyValue", value);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find by property name failed",
					Level.SEVERE, re);
			throw re;
		}
	}

	public List<Driver> findByDrvNombre(Object drvNombre) {
		return findByProperty(DRV_NOMBRE, drvNombre);
	}

	public List<Driver> findByDrvNumeroCarne(Object drvNumeroCarne) {
		return findByProperty(DRV_NUMERO_CARNE, drvNumeroCarne);
	}

	public List<Driver> findByDrvCargo(Object drvCargo) {
		return findByProperty(DRV_CARGO, drvCargo);
	}

	public List<Driver> findByDrvDireccion(Object drvDireccion) {
		return findByProperty(DRV_DIRECCION, drvDireccion);
	}

	public List<Driver> findByDrvTelefono(Object drvTelefono) {
		return findByProperty(DRV_TELEFONO, drvTelefono);
	}

	/**
	 * Find all Driver entities.
	 * 
	 * @return List<Driver> all Driver entities
	 */
	@SuppressWarnings("unchecked")
	public List<Driver> findAll() {
		EntityManagerHelper.log("finding all Driver instances", Level.INFO,
				null);
		try {
			final String queryString = "select model from Driver model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}