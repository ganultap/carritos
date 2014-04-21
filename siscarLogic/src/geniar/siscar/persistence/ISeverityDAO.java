package geniar.siscar.persistence;

import geniar.siscar.model.Severity;

import java.util.List;

/**
 * Interface for SeverityDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface ISeverityDAO {
	/**
	 * Perform an initial save of a previously unsaved Severity entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ISeverityDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Severity entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Severity entity);

	/**
	 * Delete a persistent Severity entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ISeverityDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Severity entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Severity entity);

	/**
	 * Persist a previously saved Severity entity and return it or a copy of it
	 * to the sender. A copy of the Severity entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = ISeverityDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Severity entity to update
	 * @returns Severity the persisted Severity entity instance, may not be the
	 *          same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Severity update(Severity entity);

	public Severity findById(Long id);

	/**
	 * Find all Severity entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Severity property to query
	 * @param value
	 *            the property value to match
	 * @return List<Severity> found by query
	 */
	public List<Severity> findByProperty(String propertyName, Object value);

	public List<Severity> findBySevNombre(Object sevNombre);

	/**
	 * Find all Severity entities.
	 * 
	 * @return List<Severity> all Severity entities
	 */
	public List<Severity> findAll();
}