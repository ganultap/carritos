package geniar.siscar.persistence;

import geniar.siscar.model.ChargeTo;

import java.util.List;

/**
 * Interface for ChargeToDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IChargeToDAO {
	/**
	 * Perform an initial save of a previously unsaved ChargeTo entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IChargeToDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            ChargeTo entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(ChargeTo entity);

	/**
	 * Delete a persistent ChargeTo entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IChargeToDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            ChargeTo entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(ChargeTo entity);

	/**
	 * Persist a previously saved ChargeTo entity and return it or a copy of it
	 * to the sender. A copy of the ChargeTo entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IChargeToDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            ChargeTo entity to update
	 * @returns ChargeTo the persisted ChargeTo entity instance, may not be the
	 *          same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public ChargeTo update(ChargeTo entity);

	public ChargeTo findById(Long id);

	/**
	 * Find all ChargeTo entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the ChargeTo property to query
	 * @param value
	 *            the property value to match
	 * @return List<ChargeTo> found by query
	 */
	public List<ChargeTo> findByProperty(String propertyName, Object value);

	public List<ChargeTo> findByChtNombre(Object chtNombre);

	/**
	 * Find all ChargeTo entities.
	 * 
	 * @return List<ChargeTo> all ChargeTo entities
	 */
	public List<ChargeTo> findAll();
}