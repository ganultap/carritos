package geniar.siscar.persistence;

import geniar.siscar.model.FuelPerformance;

import java.util.List;

/**
 * Interface for FuelPerformanceDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IFuelPerformanceDAO {
	/**
	 * Perform an initial save of a previously unsaved FuelPerformance entity.
	 * All subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IFuelPerformanceDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            FuelPerformance entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(FuelPerformance entity);

	/**
	 * Delete a persistent FuelPerformance entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IFuelPerformanceDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            FuelPerformance entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(FuelPerformance entity);

	/**
	 * Persist a previously saved FuelPerformance entity and return it or a copy
	 * of it to the sender. A copy of the FuelPerformance entity parameter is
	 * returned when the JPA persistence mechanism has not previously been
	 * tracking the updated entity. This operation must be performed within the
	 * a database transaction context for the entity's data to be permanently
	 * saved to the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IFuelPerformanceDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            FuelPerformance entity to update
	 * @returns FuelPerformance the persisted FuelPerformance entity instance,
	 *          may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public FuelPerformance update(FuelPerformance entity);

	public FuelPerformance findById(Long id);

	/**
	 * Find all FuelPerformance entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the FuelPerformance property to query
	 * @param value
	 *            the property value to match
	 * @return List<FuelPerformance> found by query
	 */
	public List<FuelPerformance> findByProperty(String propertyName, Object value);

	public List<FuelPerformance> findByValorRendimiento(Object valorRendimiento);

	/**
	 * Find all FuelPerformance entities.
	 * 
	 * @return List<FuelPerformance> all FuelPerformance entities
	 */
	public List<FuelPerformance> findAll();
}