package geniar.siscar.persistence;

import geniar.siscar.model.AssignationsTypes;

import java.util.List;

/**
 * Interface for AssignationsTypesDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IAssignationsTypesDAO {
	/**
	 * Perform an initial save of a previously unsaved AssignationsTypes entity.
	 * All subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IAssignationsTypesDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            AssignationsTypes entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(AssignationsTypes entity);

	/**
	 * Delete a persistent AssignationsTypes entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IAssignationsTypesDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            AssignationsTypes entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(AssignationsTypes entity);

	/**
	 * Persist a previously saved AssignationsTypes entity and return it or a
	 * copy of it to the sender. A copy of the AssignationsTypes entity
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
	 * entity = IAssignationsTypesDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            AssignationsTypes entity to update
	 * @returns AssignationsTypes the persisted AssignationsTypes entity
	 *          instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public AssignationsTypes update(AssignationsTypes entity);

	public AssignationsTypes findById(Long id);

	/**
	 * Find all AssignationsTypes entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the AssignationsTypes property to query
	 * @param value
	 *            the property value to match
	 * @return List<AssignationsTypes> found by query
	 */
	public List<AssignationsTypes> findByProperty(String propertyName, Object value);

	public List<AssignationsTypes> findByAstNombre(Object astNombre);

	/**
	 * Find all AssignationsTypes entities.
	 * 
	 * @return List<AssignationsTypes> all AssignationsTypes entities
	 */
	public List<AssignationsTypes> findAll();
}