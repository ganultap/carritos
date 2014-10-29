package geniar.siscar.persistence;

import geniar.siscar.model.Requests;

import java.util.List;

/**
 * Interface for RequestsDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IRequestsDAO {
	/**
	 * Perform an initial save of a previously unsaved Requests entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IRequestsDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Requests entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Requests entity);

	/**
	 * Delete a persistent Requests entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IRequestsDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Requests entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Requests entity);

	/**
	 * Persist a previously saved Requests entity and return it or a copy of it
	 * to the sender. A copy of the Requests entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IRequestsDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Requests entity to update
	 * @returns Requests the persisted Requests entity instance, may not be the
	 *          same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Requests update(Requests entity);

	public Requests findById(Long id);

	/**
	 * Find all Requests entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Requests property to query
	 * @param value
	 *            the property value to match
	 * @return List<Requests> found by query
	 */
	public List<Requests> findByProperty(String propertyName, Object value);

	public List<Requests> findByRqsMotivoCancelacion(Object rqsMotivoCancelacion);

	public List<Requests> findByRqsDescripcion(Object rqsDescripcion);

	public List<Requests> findByRqsCarnetEmpleado(Object rqsCarnetEmpleado);

	public List<Requests> findByRqsCarnetAsignatario(Object rqsCarnetAsignatario);

	public List<Requests> findByRqsNit(Object rqsNit);

	public List<Requests> findByRqsPlacaExtensionRemplazo(
			Object rqsPlacaExtensionRemplazo);

	/**
	 * Find all Requests entities.
	 * 
	 * @return List<Requests> all Requests entities
	 */
	public List<Requests> findAll();
}