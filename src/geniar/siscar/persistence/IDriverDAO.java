package geniar.siscar.persistence;

import geniar.siscar.model.Driver;

import java.util.List;

/**
 * Interface for DriverDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IDriverDAO {
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
	 * IDriverDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Driver entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Driver entity);

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
	 * IDriverDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Driver entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Driver entity);

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
	 * entity = IDriverDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Driver entity to update
	 * @return Driver the persisted Driver entity instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Driver update(Driver entity);

	public Driver findById(String id);

	/**
	 * Find all Driver entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Driver property to query
	 * @param value
	 *            the property value to match
	 * @return List<Driver> found by query
	 */
	public List<Driver> findByProperty(String propertyName, Object value);

	public List<Driver> findByDrvNombre(Object drvNombre);

	public List<Driver> findByDrvNumeroCarne(Object drvNumeroCarne);

	public List<Driver> findByDrvCargo(Object drvCargo);

	public List<Driver> findByDrvDireccion(Object drvDireccion);

	public List<Driver> findByDrvTelefono(Object drvTelefono);

	/**
	 * Find all Driver entities.
	 * 
	 * @return List<Driver> all Driver entities
	 */
	public List<Driver> findAll();
}