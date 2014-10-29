package geniar.siscar.persistence;

import geniar.siscar.model.VehiclesStates;

import java.util.List;

/**
 * Interface for VehiclesStatesDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IVehiclesStatesDAO {
	/**
	 * Perform an initial save of a previously unsaved VehiclesStates entity.
	 * All subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IVehiclesStatesDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            VehiclesStates entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(VehiclesStates entity);

	/**
	 * Delete a persistent VehiclesStates entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IVehiclesStatesDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            VehiclesStates entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(VehiclesStates entity);

	/**
	 * Persist a previously saved VehiclesStates entity and return it or a copy
	 * of it to the sender. A copy of the VehiclesStates entity parameter is
	 * returned when the JPA persistence mechanism has not previously been
	 * tracking the updated entity. This operation must be performed within the
	 * a database transaction context for the entity's data to be permanently
	 * saved to the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IVehiclesStatesDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            VehiclesStates entity to update
	 * @returns VehiclesStates the persisted VehiclesStates entity instance, may
	 *          not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public VehiclesStates update(VehiclesStates entity);

	public VehiclesStates findById(Long id);

	/**
	 * Find all VehiclesStates entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the VehiclesStates property to query
	 * @param value
	 *            the property value to match
	 * @return List<VehiclesStates> found by query
	 */
	public List<VehiclesStates> findByProperty(String propertyName, Object value);

	public List<VehiclesStates> findByVhsNombre(Object vhsNombre);

	/**
	 * Find all VehiclesStates entities.
	 * 
	 * @return List<VehiclesStates> all VehiclesStates entities
	 */
	public List<VehiclesStates> findAll();
}