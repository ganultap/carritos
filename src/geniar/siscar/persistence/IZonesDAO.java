package geniar.siscar.persistence;

import geniar.siscar.model.Zones;

import java.util.List;

/**
 * Interface for ZonesDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IZonesDAO {
	/**
	 * Perform an initial save of a previously unsaved Zones entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IZonesDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Zones entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Zones entity);

	/**
	 * Delete a persistent Zones entity. This operation must be performed within
	 * the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IZonesDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Zones entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Zones entity);

	/**
	 * Persist a previously saved Zones entity and return it or a copy of it to
	 * the sender. A copy of the Zones entity parameter is returned when the JPA
	 * persistence mechanism has not previously been tracking the updated
	 * entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IZonesDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Zones entity to update
	 * @returns Zones the persisted Zones entity instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Zones update(Zones entity);

	public Zones findById(Long id);

	/**
	 * Find all Zones entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Zones property to query
	 * @param value
	 *            the property value to match
	 * @return List<Zones> found by query
	 */
	public List<Zones> findByProperty(String propertyName, Object value);

	public List<Zones> findByZnsNombre(Object znsNombre);

	public List<Zones> findByZnsDescripcion(Object znsDescripcion);

	public List<Zones> findByZnsKilometraje(Object znsKilometraje);

	/**
	 * Find all Zones entities.
	 * 
	 * @return List<Zones> all Zones entities
	 */
	public List<Zones> findAll();
}