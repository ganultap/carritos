package geniar.siscar.persistence;

import geniar.siscar.model.RevisionHour;

import java.util.List;

/**
 * Interface for RevisionHourDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IRevisionHourDAO {
	/**
	 * Perform an initial save of a previously unsaved RevisionHour entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IRevisionHourDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            RevisionHour entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(RevisionHour entity);

	/**
	 * Delete a persistent RevisionHour entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IRevisionHourDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            RevisionHour entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(RevisionHour entity);

	/**
	 * Persist a previously saved RevisionHour entity and return it or a copy of
	 * it to the sender. A copy of the RevisionHour entity parameter is returned
	 * when the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IRevisionHourDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            RevisionHour entity to update
	 * @returns RevisionHour the persisted RevisionHour entity instance, may not
	 *          be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public RevisionHour update(RevisionHour entity);

	public RevisionHour findById(Long id);

	/**
	 * Find all RevisionHour entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the RevisionHour property to query
	 * @param value
	 *            the property value to match
	 * @return List<RevisionHour> found by query
	 */
	public List<RevisionHour> findByProperty(String propertyName, Object value);

	public List<RevisionHour> findByRhoHora(Object rhoHora);

	/**
	 * Find all RevisionHour entities.
	 * 
	 * @return List<RevisionHour> all RevisionHour entities
	 */
	public List<RevisionHour> findAll();
}