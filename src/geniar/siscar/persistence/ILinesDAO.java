package geniar.siscar.persistence;

import geniar.siscar.model.Lines;

import java.util.List;

/**
 * Interface for LinesDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface ILinesDAO {
	/**
	 * Perform an initial save of a previously unsaved Lines entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ILinesDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Lines entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Lines entity);

	/**
	 * Delete a persistent Lines entity. This operation must be performed within
	 * the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ILinesDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Lines entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Lines entity);

	/**
	 * Persist a previously saved Lines entity and return it or a copy of it to
	 * the sender. A copy of the Lines entity parameter is returned when the JPA
	 * persistence mechanism has not previously been tracking the updated
	 * entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = ILinesDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Lines entity to update
	 * @returns Lines the persisted Lines entity instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Lines update(Lines entity);

	public Lines findById(Long id);

	/**
	 * Find all Lines entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Lines property to query
	 * @param value
	 *            the property value to match
	 * @return List<Lines> found by query
	 */
	public List<Lines> findByProperty(String propertyName, Object value);

	public List<Lines> findByLnsNombre(Object lnsNombre);

	/**
	 * Find all Lines entities.
	 * 
	 * @return List<Lines> all Lines entities
	 */
	public List<Lines> findAll();
}