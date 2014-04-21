package geniar.siscar.persistence;

import geniar.siscar.model.FuelTypeRequest;

import java.util.List;

/**
 * Interface for FuelTypeRequestDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IFuelTypeRequestDAO {
	/**
	 * Perform an initial save of a previously unsaved FuelTypeRequest entity.
	 * All subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IFuelTypeRequestDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            FuelTypeRequest entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(FuelTypeRequest entity);

	/**
	 * Delete a persistent FuelTypeRequest entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IFuelTypeRequestDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            FuelTypeRequest entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(FuelTypeRequest entity);

	/**
	 * Persist a previously saved FuelTypeRequest entity and return it or a copy
	 * of it to the sender. A copy of the FuelTypeRequest entity parameter is
	 * returned when the JPA persistence mechanism has not previously been
	 * tracking the updated entity. This operation must be performed within the
	 * a database transaction context for the entity's data to be permanently
	 * saved to the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IFuelTypeRequestDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            FuelTypeRequest entity to update
	 * @returns FuelTypeRequest the persisted FuelTypeRequest entity instance,
	 *          may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public FuelTypeRequest update(FuelTypeRequest entity);

	public FuelTypeRequest findById(Long id);

	/**
	 * Find all FuelTypeRequest entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the FuelTypeRequest property to query
	 * @param value
	 *            the property value to match
	 * @return List<FuelTypeRequest> found by query
	 */
	public List<FuelTypeRequest> findByProperty(String propertyName, Object value);

	public List<FuelTypeRequest> findByFtrNombre(Object ftrNombre);

	/**
	 * Find all FuelTypeRequest entities.
	 * 
	 * @return List<FuelTypeRequest> all FuelTypeRequest entities
	 */
	public List<FuelTypeRequest> findAll();
}