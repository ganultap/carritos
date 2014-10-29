package geniar.siscar.persistence;

import geniar.siscar.model.LocationsNewness;

import java.util.List;

/**
 * Interface for LocationsNewnessDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface ILocationsNewnessDAO {
	/**
	 * Perform an initial save of a previously unsaved LocationsNewness entity.
	 * All subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ILocationsNewnessDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            LocationsNewness entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(LocationsNewness entity);

	/**
	 * Delete a persistent LocationsNewness entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ILocationsNewnessDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            LocationsNewness entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(LocationsNewness entity);

	/**
	 * Persist a previously saved LocationsNewness entity and return it or a
	 * copy of it to the sender. A copy of the LocationsNewness entity parameter
	 * is returned when the JPA persistence mechanism has not previously been
	 * tracking the updated entity. This operation must be performed within the
	 * a database transaction context for the entity's data to be permanently
	 * saved to the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = ILocationsNewnessDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            LocationsNewness entity to update
	 * @returns LocationsNewness the persisted LocationsNewness entity instance,
	 *          may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public LocationsNewness update(LocationsNewness entity);

	public LocationsNewness findById(Long id);

	/**
	 * Find all LocationsNewness entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the LocationsNewness property to query
	 * @param value
	 *            the property value to match
	 * @return List<LocationsNewness> found by query
	 */
	public List<LocationsNewness> findByProperty(String propertyName, Object value);

	public List<LocationsNewness> findByUsrLogin(Object usrLogin);

	public List<LocationsNewness> findByLcnUbicacion(Object lcnUbicacion);

	public List<LocationsNewness> findByLcnDescripcion(Object lcnDescripcion);

	/**
	 * Find all LocationsNewness entities.
	 * 
	 * @return List<LocationsNewness> all LocationsNewness entities
	 */
	public List<LocationsNewness> findAll();
}