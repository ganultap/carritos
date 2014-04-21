package geniar.siscar.persistence;

import geniar.siscar.model.Rolls;

import java.util.List;

/**
 * Interface for RollsDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IRollsDAO {
	/**
	 * Perform an initial save of a previously unsaved Rolls entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IRollsDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Rolls entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Rolls entity);

	/**
	 * Delete a persistent Rolls entity. This operation must be performed within
	 * the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IRollsDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Rolls entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Rolls entity);

	/**
	 * Persist a previously saved Rolls entity and return it or a copy of it to
	 * the sender. A copy of the Rolls entity parameter is returned when the JPA
	 * persistence mechanism has not previously been tracking the updated
	 * entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IRollsDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Rolls entity to update
	 * @returns Rolls the persisted Rolls entity instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Rolls update(Rolls entity);

	public Rolls findById(Long id);

	/**
	 * Find all Rolls entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Rolls property to query
	 * @param value
	 *            the property value to match
	 * @return List<Rolls> found by query
	 */
	public List<Rolls> findByProperty(String propertyName, Object value);

	public List<Rolls> findByRlsNombre(Object rlsNombre);

	public List<Rolls> findByRlsDescripcion(Object rlsDescripcion);

	public List<Rolls> findByRlsMail(Object rlsMail);

	/**
	 * Find all Rolls entities.
	 * 
	 * @return List<Rolls> all Rolls entities
	 */
	public List<Rolls> findAll();
}