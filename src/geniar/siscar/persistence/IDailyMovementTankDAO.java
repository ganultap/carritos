package geniar.siscar.persistence;

import geniar.siscar.model.DailyMovementTank;

import java.util.List;

/**
 * Interface for DailyMovementTankDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IDailyMovementTankDAO {
	/**
	 * Perform an initial save of a previously unsaved DailyMovementTank entity.
	 * All subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IDailyMovementTankDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            DailyMovementTank entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(DailyMovementTank entity);

	/**
	 * Delete a persistent DailyMovementTank entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IDailyMovementTankDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            DailyMovementTank entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(DailyMovementTank entity);

	/**
	 * Persist a previously saved DailyMovementTank entity and return it or a
	 * copy of it to the sender. A copy of the DailyMovementTank entity
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
	 * entity = IDailyMovementTankDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            DailyMovementTank entity to update
	 * @returns DailyMovementTank the persisted DailyMovementTank entity
	 *          instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public DailyMovementTank update(DailyMovementTank entity);

	public DailyMovementTank findById(Long id);

	/**
	 * Find all DailyMovementTank entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the DailyMovementTank property to query
	 * @param value
	 *            the property value to match
	 * @return List<DailyMovementTank> found by query
	 */
	public List<DailyMovementTank> findByProperty(String propertyName,
			Object value);

	public List<DailyMovementTank> findByDamEntrada(Object damEntrada);

	public List<DailyMovementTank> findByDamLectura(Object damLectura);

	/**
	 * Find all DailyMovementTank entities.
	 * 
	 * @return List<DailyMovementTank> all DailyMovementTank entities
	 */
	public List<DailyMovementTank> findAll();
}