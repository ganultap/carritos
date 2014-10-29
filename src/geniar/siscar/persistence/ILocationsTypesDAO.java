package geniar.siscar.persistence;

import geniar.siscar.model.LocationsTypes;

import java.util.List;

/**
 * Interface for LocationsTypesDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface ILocationsTypesDAO {
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
	 * ILocationsTypesDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            LocationsTypes entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(LocationsTypes entity);

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
	 * ILocationsTypesDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            LocationsTypes entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(LocationsTypes entity);

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
	 * entity = ILocationsTypesDAO.update(entity);
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
	public LocationsTypes update(LocationsTypes entity);

	public LocationsTypes findById(Long id);

	/**
	 * Find all LocationsTypes entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the LocationsTypes property to query
	 * @param value
	 *            the property value to match
	 * @return List<LocationsTypes> found by query
	 */
	public List<LocationsTypes> findByProperty(String propertyName, Object value);

	public List<LocationsTypes> findByLctNombre(Object lctNombre);

	/**
	 * Find all LocationsTypes entities.
	 * 
	 * @return List<LocationsTypes> all LocationsTypes entities
	 */
	public List<LocationsTypes> findAll();
}