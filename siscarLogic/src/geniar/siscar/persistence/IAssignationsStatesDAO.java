package geniar.siscar.persistence;

import geniar.siscar.model.AssignationsStates;

import java.util.List;

/**
 * Interface for AssignationsStatesDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IAssignationsStatesDAO {
	/**
	 * Perform an initial save of a previously unsaved AssignationsStates
	 * entity. All subsequent persist actions of this entity should use the
	 * #update() method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IAssignationsStatesDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            AssignationsStates entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(AssignationsStates entity);

	/**
	 * Delete a persistent AssignationsStates entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IAssignationsStatesDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            AssignationsStates entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(AssignationsStates entity);

	/**
	 * Persist a previously saved AssignationsStates entity and return it or a
	 * copy of it to the sender. A copy of the AssignationsStates entity
	 * parameter is returned when the JPA persistence mechanism has not
	 * previously been tracking the updated entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently saved to the persistence store, i.e., database. This
	 * method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IAssignationsStatesDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            AssignationsStates entity to update
	 * @returns AssignationsStates the persisted AssignationsStates entity
	 *          instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public AssignationsStates update(AssignationsStates entity);

	public AssignationsStates findById(Long id);

	/**
	 * Find all AssignationsStates entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the AssignationsStates property to query
	 * @param value
	 *            the property value to match
	 * @return List<AssignationsStates> found by query
	 */
	public List<AssignationsStates> findByProperty(String propertyName, Object value);

	public List<AssignationsStates> findByAssNombre(Object assNombre);

	/**
	 * Find all AssignationsStates entities.
	 * 
	 * @return List<AssignationsStates> all AssignationsStates entities
	 */
	public List<AssignationsStates> findAll();
}