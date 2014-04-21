package geniar.siscar.persistence;

import geniar.siscar.model.MovementType;

import java.util.List;

/**
 * Interface for MovementTypeDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IMovementTypeDAO {
	/**
	 * Perform an initial save of a previously unsaved MovementType entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IMovementTypeDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            MovementType entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(MovementType entity);

	/**
	 * Delete a persistent MovementType entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IMovementTypeDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            MovementType entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(MovementType entity);

	/**
	 * Persist a previously saved MovementType entity and return it or a copy of
	 * it to the sender. A copy of the MovementType entity parameter is returned
	 * when the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IMovementTypeDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            MovementType entity to update
	 * @return MovementType the persisted MovementType entity instance, may not
	 *         be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public MovementType update(MovementType entity);

	public MovementType findById(Long id);

	/**
	 * Find all MovementType entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the MovementType property to query
	 * @param value
	 *            the property value to match
	 * @return List<MovementType> found by query
	 */
	public List<MovementType> findByProperty(String propertyName, Object value);

	public List<MovementType> findByMvmNombre(Object mvmNombre);

	public List<MovementType> findByMvmDescripcion(Object mvmDescripcion);

	/**
	 * Find all MovementType entities.
	 * 
	 * @return List<MovementType> all MovementType entities
	 */
	public List<MovementType> findAll();
}