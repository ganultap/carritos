package geniar.siscar.persistence;

import geniar.siscar.model.RequestsTypes;

import java.util.List;

/**
 * Interface for RequestsTypesDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IRequestsTypesDAO {
	/**
	 * Perform an initial save of a previously unsaved RequestsTypes entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IRequestsTypesDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            RequestsTypes entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(RequestsTypes entity);

	/**
	 * Delete a persistent RequestsTypes entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IRequestsTypesDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            RequestsTypes entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(RequestsTypes entity);

	/**
	 * Persist a previously saved RequestsTypes entity and return it or a copy
	 * of it to the sender. A copy of the RequestsTypes entity parameter is
	 * returned when the JPA persistence mechanism has not previously been
	 * tracking the updated entity. This operation must be performed within the
	 * a database transaction context for the entity's data to be permanently
	 * saved to the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IRequestsTypesDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            RequestsTypes entity to update
	 * @returns RequestsTypes the persisted RequestsTypes entity instance, may
	 *          not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public RequestsTypes update(RequestsTypes entity);

	public RequestsTypes findById(Long id);

	/**
	 * Find all RequestsTypes entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the RequestsTypes property to query
	 * @param value
	 *            the property value to match
	 * @return List<RequestsTypes> found by query
	 */
	public List<RequestsTypes> findByProperty(String propertyName, Object value);

	public List<RequestsTypes> findByRqyNombre(Object rqyNombre);

	/**
	 * Find all RequestsTypes entities.
	 * 
	 * @return List<RequestsTypes> all RequestsTypes entities
	 */
	public List<RequestsTypes> findAll();
}