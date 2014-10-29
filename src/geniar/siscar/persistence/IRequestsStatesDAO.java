package geniar.siscar.persistence;

import geniar.siscar.model.RequestsStates;

import java.util.List;

/**
 * Interface for RequestsStatesDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IRequestsStatesDAO {
	/**
	 * Perform an initial save of a previously unsaved RequestsStates entity.
	 * All subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IRequestsStatesDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            RequestsStates entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(RequestsStates entity);

	/**
	 * Delete a persistent RequestsStates entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IRequestsStatesDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            RequestsStates entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(RequestsStates entity);

	/**
	 * Persist a previously saved RequestsStates entity and return it or a copy
	 * of it to the sender. A copy of the RequestsStates entity parameter is
	 * returned when the JPA persistence mechanism has not previously been
	 * tracking the updated entity. This operation must be performed within the
	 * a database transaction context for the entity's data to be permanently
	 * saved to the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IRequestsStatesDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            RequestsStates entity to update
	 * @returns RequestsStates the persisted RequestsStates entity instance, may
	 *          not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public RequestsStates update(RequestsStates entity);

	public RequestsStates findById(Long id);

	/**
	 * Find all RequestsStates entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the RequestsStates property to query
	 * @param value
	 *            the property value to match
	 * @return List<RequestsStates> found by query
	 */
	public List<RequestsStates> findByProperty(String propertyName, Object value);

	public List<RequestsStates> findByRqtNombre(Object rqtNombre);

	/**
	 * Find all RequestsStates entities.
	 * 
	 * @return List<RequestsStates> all RequestsStates entities
	 */
	public List<RequestsStates> findAll();
}