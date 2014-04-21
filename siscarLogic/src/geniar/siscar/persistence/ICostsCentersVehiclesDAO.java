package geniar.siscar.persistence;

import geniar.siscar.model.CostsCentersVehicles;

import java.util.List;

/**
 * Interface for CostsCentersVehiclesDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface ICostsCentersVehiclesDAO {
	/**
	 * Perform an initial save of a previously unsaved CostsCentersVehicles
	 * entity. All subsequent persist actions of this entity should use the
	 * #update() method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ICostsCentersVehiclesDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            CostsCentersVehicles entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(CostsCentersVehicles entity);

	/**
	 * Delete a persistent CostsCentersVehicles entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ICostsCentersVehiclesDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            CostsCentersVehicles entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(CostsCentersVehicles entity);

	/**
	 * Persist a previously saved CostsCentersVehicles entity and return it or a
	 * copy of it to the sender. A copy of the CostsCentersVehicles entity
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
	 * entity = ICostsCentersVehiclesDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            CostsCentersVehicles entity to update
	 * @returns CostsCentersVehicles the persisted CostsCentersVehicles entity
	 *          instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public CostsCentersVehicles update(CostsCentersVehicles entity);

	public CostsCentersVehicles findById(Long id);

	/**
	 * Find all CostsCentersVehicles entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the CostsCentersVehicles property to query
	 * @param value
	 *            the property value to match
	 * @return List<CostsCentersVehicles> found by query
	 */
	public List<CostsCentersVehicles> findByProperty(String propertyName, Object value);

	public List<CostsCentersVehicles> findByCcrPorcentaje(Object ccrPorcentaje);

	public List<CostsCentersVehicles> findByCcrValor(Object ccrValor);

	public List<CostsCentersVehicles> findByCcrLogin(Object ccrLogin);

	public List<CostsCentersVehicles> findByCcrEstado(Object ccrEstado);

	/**
	 * Find all CostsCentersVehicles entities.
	 * 
	 * @return List<CostsCentersVehicles> all CostsCentersVehicles entities
	 */
	public List<CostsCentersVehicles> findAll();
}