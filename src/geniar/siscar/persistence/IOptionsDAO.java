package geniar.siscar.persistence;

import geniar.siscar.model.Options;

import java.util.List;

/**
 * Interface for OptionsDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IOptionsDAO {
	/**
	 * Perform an initial save of a previously unsaved Options entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IOptionsDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Options entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Options entity);

	/**
	 * Delete a persistent Options entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IOptionsDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Options entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Options entity);

	/**
	 * Persist a previously saved Options entity and return it or a copy of it
	 * to the sender. A copy of the Options entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IOptionsDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Options entity to update
	 * @returns Options the persisted Options entity instance, may not be the
	 *          same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Options update(Options entity);

	public Options findById(Long id);

	/**
	 * Find all Options entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Options property to query
	 * @param value
	 *            the property value to match
	 * @return List<Options> found by query
	 */
	public List<Options> findByProperty(String propertyName, Object value);

	public List<Options> findByOptNombre(Object optNombre);

	public List<Options> findByOptLink(Object optLink);

	/**
	 * Find all Options entities.
	 * 
	 * @return List<Options> all Options entities
	 */
	public List<Options> findAll();
}