package geniar.siscar.persistence;

import geniar.siscar.model.Responsibility;

import java.util.List;

/**
 * Interface for ResponsibilityDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IResponsibilityDAO {
	/**
	 * Perform an initial save of a previously unsaved Responsibility entity.
	 * All subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IResponsibilityDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Responsibility entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Responsibility entity);

	/**
	 * Delete a persistent Responsibility entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IResponsibilityDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Responsibility entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Responsibility entity);

	/**
	 * Persist a previously saved Responsibility entity and return it or a copy
	 * of it to the sender. A copy of the Responsibility entity parameter is
	 * returned when the JPA persistence mechanism has not previously been
	 * tracking the updated entity. This operation must be performed within the
	 * a database transaction context for the entity's data to be permanently
	 * saved to the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IResponsibilityDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Responsibility entity to update
	 * @returns Responsibility the persisted Responsibility entity instance, may
	 *          not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Responsibility update(Responsibility entity);

	public Responsibility findById(Long id);

	/**
	 * Find all Responsibility entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Responsibility property to query
	 * @param value
	 *            the property value to match
	 * @return List<Responsibility> found by query
	 */
	public List<Responsibility> findByProperty(String propertyName, Object value);

	public List<Responsibility> findByResNombre(Object resNombre);

	/**
	 * Find all Responsibility entities.
	 * 
	 * @return List<Responsibility> all Responsibility entities
	 */
	public List<Responsibility> findAll();
}