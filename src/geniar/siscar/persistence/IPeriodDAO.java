package geniar.siscar.persistence;

import geniar.siscar.model.Period;

import java.util.List;

/**
 * Interface for PeriodDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IPeriodDAO {
	/**
	 * Perform an initial save of a previously unsaved Period entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IPeriodDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Period entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Period entity);

	/**
	 * Delete a persistent Period entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IPeriodDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Period entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Period entity);

	/**
	 * Persist a previously saved Period entity and return it or a copy of it to
	 * the sender. A copy of the Period entity parameter is returned when the
	 * JPA persistence mechanism has not previously been tracking the updated
	 * entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IPeriodDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Period entity to update
	 * @returns Period the persisted Period entity instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Period update(Period entity);

	public Period findById(Long id);

	/**
	 * Find all Period entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Period property to query
	 * @param value
	 *            the property value to match
	 * @return List<Period> found by query
	 */
	public List<Period> findByProperty(String propertyName, Object value);

	public List<Period> findByPerNombre(Object perNombre);

	public List<Period> findByPerObservacion(Object perObservacion);

	/**
	 * Find all Period entities.
	 * 
	 * @return List<Period> all Period entities
	 */
	public List<Period> findAll();
}