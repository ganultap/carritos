package geniar.siscar.persistence;

import geniar.siscar.model.ControlsTanks;

import java.util.List;

/**
 * Interface for ControlsTanksDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IControlsTanksDAO {
	/**
	 * Perform an initial save of a previously unsaved ControlsTanks entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IControlsTanksDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            ControlsTanks entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(ControlsTanks entity);

	/**
	 * Delete a persistent ControlsTanks entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IControlsTanksDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            ControlsTanks entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(ControlsTanks entity);

	/**
	 * Persist a previously saved ControlsTanks entity and return it or a copy
	 * of it to the sender. A copy of the ControlsTanks entity parameter is
	 * returned when the JPA persistence mechanism has not previously been
	 * tracking the updated entity. This operation must be performed within the
	 * a database transaction context for the entity's data to be permanently
	 * saved to the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IControlsTanksDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            ControlsTanks entity to update
	 * @returns ControlsTanks the persisted ControlsTanks entity instance, may
	 *          not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public ControlsTanks update(ControlsTanks entity);

	public ControlsTanks findById(Long id);

	/**
	 * Find all ControlsTanks entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the ControlsTanks property to query
	 * @param value
	 *            the property value to match
	 * @return List<ControlsTanks> found by query
	 */
	public List<ControlsTanks> findByProperty(String propertyName, Object value);

	public List<ControlsTanks> findByCotGalonesActuales(Object cotGalonesActuales);

	/**
	 * Find all ControlsTanks entities.
	 * 
	 * @return List<ControlsTanks> all ControlsTanks entities
	 */
	public List<ControlsTanks> findAll();
}