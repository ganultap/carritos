package geniar.siscar.persistence;

import geniar.siscar.model.Colors;

import java.util.List;

/**
 * Interface for ColorsDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IColorsDAO {
	/**
	 * Perform an initial save of a previously unsaved Colors entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IColorsDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Colors entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Colors entity);

	/**
	 * Delete a persistent Colors entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IColorsDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Colors entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Colors entity);

	/**
	 * Persist a previously saved Colors entity and return it or a copy of it to
	 * the sender. A copy of the Colors entity parameter is returned when the
	 * JPA persistence mechanism has not previously been tracking the updated
	 * entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IColorsDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Colors entity to update
	 * @returns Colors the persisted Colors entity instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Colors update(Colors entity);

	public Colors findById(Long id);

	/**
	 * Find all Colors entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Colors property to query
	 * @param value
	 *            the property value to match
	 * @return List<Colors> found by query
	 */
	public List<Colors> findByProperty(String propertyName, Object value);

	public List<Colors> findByClNombre(Object clNombre);

	/**
	 * Find all Colors entities.
	 * 
	 * @return List<Colors> all Colors entities
	 */
	public List<Colors> findAll();
}