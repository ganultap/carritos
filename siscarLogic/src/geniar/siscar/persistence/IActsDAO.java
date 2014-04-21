package geniar.siscar.persistence;

import geniar.siscar.model.Acts;

import java.util.List;

/**
 * Interface for ActsDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IActsDAO {
	/**
	 * Perform an initial save of a previously unsaved Acts entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IActsDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Acts entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Acts entity);

	/**
	 * Delete a persistent Acts entity. This operation must be performed within
	 * the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IActsDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Acts entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Acts entity);

	/**
	 * Persist a previously saved Acts entity and return it or a copy of it to
	 * the sender. A copy of the Acts entity parameter is returned when the JPA
	 * persistence mechanism has not previously been tracking the updated
	 * entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IActsDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Acts entity to update
	 * @returns Acts the persisted Acts entity instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Acts update(Acts entity);

	public Acts findById(Long id);

	/**
	 * Find all Acts entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Acts property to query
	 * @param value
	 *            the property value to match
	 * @return List<Acts> found by query
	 */
	public List<Acts> findByProperty(String propertyName, Object value);

	public List<Acts> findByActDescripcion(Object actDescripcion);

	public List<Acts> findByActObservaciones(Object actObservaciones);

	public List<Acts> findByActSanciones(Object actSanciones);

	public List<Acts> findByActAprobacion(Object actAprobacion);

	public List<Acts> findByActNumeroOrden(Object actNumeroOrden);

	/**
	 * Find all Acts entities.
	 * 
	 * @return List<Acts> all Acts entities
	 */
	public List<Acts> findAll();
}