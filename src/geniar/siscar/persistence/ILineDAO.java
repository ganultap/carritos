package geniar.siscar.persistence;

import geniar.siscar.model.Line;

import java.util.List;

/**
 * Interface for LineDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface ILineDAO {
	/**
	 * Perform an initial save of a previously unsaved Line entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ILineDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Line entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Line entity);

	/**
	 * Delete a persistent Line entity. This operation must be performed within
	 * the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ILineDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Line entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Line entity);

	/**
	 * Persist a previously saved Line entity and return it or a copy of it to
	 * the sender. A copy of the Line entity parameter is returned when the JPA
	 * persistence mechanism has not previously been tracking the updated
	 * entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = ILineDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Line entity to update
	 * @return Line the persisted Line entity instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Line update(Line entity);

	public Line findById(Long id);

	/**
	 * Find all Line entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Line property to query
	 * @param value
	 *            the property value to match
	 * @return List<Line> found by query
	 */
	public List<Line> findByProperty(String propertyName, Object value);

	public List<Line> findByLinValor(Object linValor);

	/**
	 * Find all Line entities.
	 * 
	 * @return List<Line> all Line entities
	 */
	public List<Line> findAll();
}