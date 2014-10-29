package geniar.siscar.persistence;

import geniar.siscar.model.Future;

import java.util.List;

/**
 * Interface for FutureDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IFutureDAO {
	/**
	 * Perform an initial save of a previously unsaved Future entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IFutureDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Future entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Future entity);

	/**
	 * Delete a persistent Future entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IFutureDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Future entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Future entity);

	/**
	 * Persist a previously saved Future entity and return it or a copy of it to
	 * the sender. A copy of the Future entity parameter is returned when the
	 * JPA persistence mechanism has not previously been tracking the updated
	 * entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IFutureDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Future entity to update
	 * @return Future the persisted Future entity instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Future update(Future entity);

	public Future findById(Long id);

	/**
	 * Find all Future entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Future property to query
	 * @param value
	 *            the property value to match
	 * @return List<Future> found by query
	 */
	public List<Future> findByProperty(String propertyName, Object value);

	public List<Future> findByFreValor(Object freValor);

	public List<Future> findByFreDescripcion(Object freDescripcion);

	/**
	 * Find all Future entities.
	 * 
	 * @return List<Future> all Future entities
	 */
	public List<Future> findAll();
}