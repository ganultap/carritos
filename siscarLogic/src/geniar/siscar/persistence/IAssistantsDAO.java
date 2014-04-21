package geniar.siscar.persistence;

import geniar.siscar.model.Assistants;

import java.util.List;

/**
 * Interface for AssistantsDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IAssistantsDAO {
	/**
	 * Perform an initial save of a previously unsaved Assistants entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IAssistantsDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Assistants entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Assistants entity);

	/**
	 * Delete a persistent Assistants entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IAssistantsDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Assistants entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Assistants entity);

	/**
	 * Persist a previously saved Assistants entity and return it or a copy of
	 * it to the sender. A copy of the Assistants entity parameter is returned
	 * when the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IAssistantsDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Assistants entity to update
	 * @returns Assistants the persisted Assistants entity instance, may not be
	 *          the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Assistants update(Assistants entity);

	public Assistants findById(Long id);

	/**
	 * Find all Assistants entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Assistants property to query
	 * @param value
	 *            the property value to match
	 * @return List<Assistants> found by query
	 */
	public List<Assistants> findByProperty(String propertyName, Object value);

	public List<Assistants> findByAsiNombre(Object asiNombre);

	public List<Assistants> findByAsiEmail(Object asiEmail);

	public List<Assistants> findByAsiCodigoCiat(Object asiCodigoCiat);

	/**
	 * Find all Assistants entities.
	 * 
	 * @return List<Assistants> all Assistants entities
	 */
	public List<Assistants> findAll();
}