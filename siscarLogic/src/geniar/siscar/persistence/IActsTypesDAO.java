package geniar.siscar.persistence;

import geniar.siscar.model.ActsTypes;

import java.util.List;

/**
 * Interface for ActsTypesDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IActsTypesDAO {
	/**
	 * Perform an initial save of a previously unsaved ActsTypes entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IActsTypesDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            ActsTypes entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(ActsTypes entity);

	/**
	 * Delete a persistent ActsTypes entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IActsTypesDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            ActsTypes entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(ActsTypes entity);

	/**
	 * Persist a previously saved ActsTypes entity and return it or a copy of it
	 * to the sender. A copy of the ActsTypes entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IActsTypesDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            ActsTypes entity to update
	 * @returns ActsTypes the persisted ActsTypes entity instance, may not be
	 *          the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public ActsTypes update(ActsTypes entity);

	public ActsTypes findById(Long id);

	/**
	 * Find all ActsTypes entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the ActsTypes property to query
	 * @param value
	 *            the property value to match
	 * @return List<ActsTypes> found by query
	 */
	public List<ActsTypes> findByProperty(String propertyName, Object value);

	public List<ActsTypes> findByAtyNombre(Object atyNombre);

	/**
	 * Find all ActsTypes entities.
	 * 
	 * @return List<ActsTypes> all ActsTypes entities
	 */
	public List<ActsTypes> findAll();
}