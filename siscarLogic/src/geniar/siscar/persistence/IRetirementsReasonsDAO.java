package geniar.siscar.persistence;

import geniar.siscar.model.RetirementsReasons;

import java.util.List;

/**
 * Interface for RetirementsReasonsDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IRetirementsReasonsDAO {
	/**
	 * Perform an initial save of a previously unsaved RetirementsReasons
	 * entity. All subsequent persist actions of this entity should use the
	 * #update() method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IRetirementsReasonsDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            RetirementsReasons entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(RetirementsReasons entity);

	/**
	 * Delete a persistent RetirementsReasons entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IRetirementsReasonsDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            RetirementsReasons entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(RetirementsReasons entity);

	/**
	 * Persist a previously saved RetirementsReasons entity and return it or a
	 * copy of it to the sender. A copy of the RetirementsReasons entity
	 * parameter is returned when the JPA persistence mechanism has not
	 * previously been tracking the updated entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently saved to the persistence store, i.e., database. This
	 * method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IRetirementsReasonsDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            RetirementsReasons entity to update
	 * @returns RetirementsReasons the persisted RetirementsReasons entity
	 *          instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public RetirementsReasons update(RetirementsReasons entity);

	public RetirementsReasons findById(Long id);

	/**
	 * Find all RetirementsReasons entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the RetirementsReasons property to query
	 * @param value
	 *            the property value to match
	 * @return List<RetirementsReasons> found by query
	 */
	public List<RetirementsReasons> findByProperty(String propertyName, Object value);

	public List<RetirementsReasons> findByRerLoginUsr(Object rerLoginUsr);

	public List<RetirementsReasons> findByRerDescripcion(Object rerDescripcion);

	/**
	 * Find all RetirementsReasons entities.
	 * 
	 * @return List<RetirementsReasons> all RetirementsReasons entities
	 */
	public List<RetirementsReasons> findAll();
}