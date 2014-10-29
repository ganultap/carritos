package geniar.siscar.persistence;


import geniar.siscar.model.CostCentersFuel;

import java.util.List;

/**
 * Interface for CostCentersFuelDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface ICostCentersFuelDAO {
	/**
	 * Perform an initial save of a previously unsaved CostCentersFuel entity.
	 * All subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ICostCentersFuelDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            CostCentersFuel entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(CostCentersFuel entity);

	/**
	 * Delete a persistent CostCentersFuel entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ICostCentersFuelDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            CostCentersFuel entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(CostCentersFuel entity);

	/**
	 * Persist a previously saved CostCentersFuel entity and return it or a copy
	 * of it to the sender. A copy of the CostCentersFuel entity parameter is
	 * returned when the JPA persistence mechanism has not previously been
	 * tracking the updated entity. This operation must be performed within the
	 * a database transaction context for the entity's data to be permanently
	 * saved to the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = ICostCentersFuelDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            CostCentersFuel entity to update
	 * @returns CostCentersFuel the persisted CostCentersFuel entity instance,
	 *          may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public CostCentersFuel update(CostCentersFuel entity);

	public CostCentersFuel findById(Long id);

	/**
	 * Find all CostCentersFuel entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the CostCentersFuel property to query
	 * @param value
	 *            the property value to match
	 * @return List<CostCentersFuel> found by query
	 */
	public List<CostCentersFuel> findByProperty(String propertyName,
			Object value);

	public List<CostCentersFuel> findByCcfValor(Object ccfValor);

	/**
	 * Find all CostCentersFuel entities.
	 * 
	 * @return List<CostCentersFuel> all CostCentersFuel entities
	 */
	public List<CostCentersFuel> findAll();
}