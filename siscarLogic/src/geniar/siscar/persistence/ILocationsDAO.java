package geniar.siscar.persistence;

import geniar.siscar.model.Locations;

import java.util.List;

/**
 * Interface for LocationsDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface ILocationsDAO {
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
	 * ILocationsDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Locations entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Locations entity);

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
	 * ILocationsDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Locations entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Locations entity);

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
	 * entity = ILocationsDAO.update(entity);
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
	public Locations update(Locations entity);

	public Locations findById(Long id);

	/**
	 * Find all Locations entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Locations property to query
	 * @param value
	 *            the property value to match
	 * @return List<Locations> found by query
	 */
	public List<Locations> findByProperty(String propertyName, Object value);

	public List<Locations> findByLcnDescripcion(Object lcnDescripcion);

	/**
	 * Find all Locations entities.
	 * 
	 * @return List<Locations> all Locations entities
	 */
	public List<Locations> findAll();
}