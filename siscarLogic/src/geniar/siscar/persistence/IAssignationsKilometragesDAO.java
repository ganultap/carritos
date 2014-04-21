package geniar.siscar.persistence;

import geniar.siscar.model.AssignationsKilometrages;

import java.util.List;

/**
 * Interface for AssignationsKilometragesDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IAssignationsKilometragesDAO {
	/**
	 * Perform an initial save of a previously unsaved AssignationsKilometrages
	 * entity. All subsequent persist actions of this entity should use the
	 * #update() method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IAssignationsKilometragesDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            AssignationsKilometrages entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(AssignationsKilometrages entity);

	/**
	 * Delete a persistent AssignationsKilometrages entity. This operation must
	 * be performed within the a database transaction context for the entity's
	 * data to be permanently deleted from the persistence store, i.e.,
	 * database. This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IAssignationsKilometragesDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            AssignationsKilometrages entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(AssignationsKilometrages entity);

	/**
	 * Persist a previously saved AssignationsKilometrages entity and return it
	 * or a copy of it to the sender. A copy of the AssignationsKilometrages
	 * entity parameter is returned when the JPA persistence mechanism has not
	 * previously been tracking the updated entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently saved to the persistence store, i.e., database. This
	 * method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IAssignationsKilometragesDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            AssignationsKilometrages entity to update
	 * @returns AssignationsKilometrages the persisted AssignationsKilometrages
	 *          entity instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public AssignationsKilometrages update(AssignationsKilometrages entity);

	public AssignationsKilometrages findById(Long id);

	/**
	 * Find all AssignationsKilometrages entities with a specific property
	 * value.
	 * 
	 * @param propertyName
	 *            the name of the AssignationsKilometrages property to query
	 * @param value
	 *            the property value to match
	 * @return List<AssignationsKilometrages> found by query
	 */
	public List<AssignationsKilometrages> findByProperty(String propertyName, Object value);

	public List<AssignationsKilometrages> findByAskKilomEntrega(Object askKilomEntrega);

	public List<AssignationsKilometrages> findByAskKilomDev(Object askKilomDev);

	/**
	 * Find all AssignationsKilometrages entities.
	 * 
	 * @return List<AssignationsKilometrages> all AssignationsKilometrages
	 *         entities
	 */
	public List<AssignationsKilometrages> findAll();
}