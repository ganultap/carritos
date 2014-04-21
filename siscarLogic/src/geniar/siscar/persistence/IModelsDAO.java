package geniar.siscar.persistence;

import geniar.siscar.model.Models;

import java.util.List;

/**
 * Interface for ModelsDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IModelsDAO {
	/**
	 * Perform an initial save of a previously unsaved Models entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IModelsDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Models entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Models entity);

	/**
	 * Delete a persistent Models entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IModelsDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Models entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Models entity);

	/**
	 * Persist a previously saved Models entity and return it or a copy of it to
	 * the sender. A copy of the Models entity parameter is returned when the
	 * JPA persistence mechanism has not previously been tracking the updated
	 * entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IModelsDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Models entity to update
	 * @returns Models the persisted Models entity instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Models update(Models entity);

	public Models findById(Long id);

	/**
	 * Find all Models entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Models property to query
	 * @param value
	 *            the property value to match
	 * @return List<Models> found by query
	 */
	public List<Models> findByProperty(String propertyName, Object value);

	public List<Models> findByMdlNombre(Object mdlNombre);

	/**
	 * Find all Models entities.
	 * 
	 * @return List<Models> all Models entities
	 */
	public List<Models> findAll();
}