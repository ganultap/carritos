package geniar.siscar.persistence;

import geniar.siscar.model.RetirementsTypes;

import java.util.List;

/**
 * Interface for RetirementsTypesDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IRetirementsTypesDAO {
	/**
	 * Perform an initial save of a previously unsaved RetirementsTypes entity.
	 * All subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IRetirementsTypesDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            RetirementsTypes entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(RetirementsTypes entity);

	/**
	 * Delete a persistent RetirementsTypes entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IRetirementsTypesDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            RetirementsTypes entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(RetirementsTypes entity);

	/**
	 * Persist a previously saved RetirementsTypes entity and return it or a
	 * copy of it to the sender. A copy of the RetirementsTypes entity parameter
	 * is returned when the JPA persistence mechanism has not previously been
	 * tracking the updated entity. This operation must be performed within the
	 * a database transaction context for the entity's data to be permanently
	 * saved to the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IRetirementsTypesDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            RetirementsTypes entity to update
	 * @returns RetirementsTypes the persisted RetirementsTypes entity instance,
	 *          may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public RetirementsTypes update(RetirementsTypes entity);

	public RetirementsTypes findById(Long id);

	/**
	 * Find all RetirementsTypes entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the RetirementsTypes property to query
	 * @param value
	 *            the property value to match
	 * @return List<RetirementsTypes> found by query
	 */
	public List<RetirementsTypes> findByProperty(String propertyName, Object value);

	public List<RetirementsTypes> findByRetNombre(Object retNombre);

	/**
	 * Find all RetirementsTypes entities.
	 * 
	 * @return List<RetirementsTypes> all RetirementsTypes entities
	 */
	public List<RetirementsTypes> findAll();
}