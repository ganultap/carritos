package geniar.siscar.persistence;

import geniar.siscar.model.RequestsClasses;

import java.util.List;

/**
 * Interface for RequestsClassesDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IRequestsClassesDAO {
	/**
	 * Perform an initial save of a previously unsaved RequestsClasses entity.
	 * All subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IRequestsClassesDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            RequestsClasses entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(RequestsClasses entity);

	/**
	 * Delete a persistent RequestsClasses entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IRequestsClassesDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            RequestsClasses entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(RequestsClasses entity);

	/**
	 * Persist a previously saved RequestsClasses entity and return it or a copy
	 * of it to the sender. A copy of the RequestsClasses entity parameter is
	 * returned when the JPA persistence mechanism has not previously been
	 * tracking the updated entity. This operation must be performed within the
	 * a database transaction context for the entity's data to be permanently
	 * saved to the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IRequestsClassesDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            RequestsClasses entity to update
	 * @returns RequestsClasses the persisted RequestsClasses entity instance,
	 *          may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public RequestsClasses update(RequestsClasses entity);

	public RequestsClasses findById(Long id);

	/**
	 * Find all RequestsClasses entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the RequestsClasses property to query
	 * @param value
	 *            the property value to match
	 * @return List<RequestsClasses> found by query
	 */
	public List<RequestsClasses> findByProperty(String propertyName, Object value);

	public List<RequestsClasses> findByRqcNombre(Object rqcNombre);

	/**
	 * Find all RequestsClasses entities.
	 * 
	 * @return List<RequestsClasses> all RequestsClasses entities
	 */
	public List<RequestsClasses> findAll();
}