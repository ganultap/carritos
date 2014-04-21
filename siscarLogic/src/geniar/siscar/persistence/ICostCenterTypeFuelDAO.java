package geniar.siscar.persistence;

import geniar.siscar.model.CostCenterTypeFuel;

import java.util.List;

/**
 * Interface for CostCenterTypeFuelDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface ICostCenterTypeFuelDAO {
	/**
	 * Perform an initial save of a previously unsaved CostCenterTypeFuel
	 * entity. All subsequent persist actions of this entity should use the
	 * #update() method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ICostCenterTypeFuelDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            CostCenterTypeFuel entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(CostCenterTypeFuel entity);

	/**
	 * Delete a persistent CostCenterTypeFuel entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ICostCenterTypeFuelDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            CostCenterTypeFuel entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(CostCenterTypeFuel entity);

	/**
	 * Persist a previously saved CostCenterTypeFuel entity and return it or a
	 * copy of it to the sender. A copy of the CostCenterTypeFuel entity
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
	 * entity = ICostCenterTypeFuelDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            CostCenterTypeFuel entity to update
	 * @returns CostCenterTypeFuel the persisted CostCenterTypeFuel entity
	 *          instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public CostCenterTypeFuel update(CostCenterTypeFuel entity);

	public CostCenterTypeFuel findById(Long id);

	/**
	 * Find all CostCenterTypeFuel entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the CostCenterTypeFuel property to query
	 * @param value
	 *            the property value to match
	 * @return List<CostCenterTypeFuel> found by query
	 */
	public List<CostCenterTypeFuel> findByProperty(String propertyName,
			Object value);

	public List<CostCenterTypeFuel> findByCctNombre(Object cctNombre);

	/**
	 * Find all CostCenterTypeFuel entities.
	 * 
	 * @return List<CostCenterTypeFuel> all CostCenterTypeFuel entities
	 */
	public List<CostCenterTypeFuel> findAll();
}