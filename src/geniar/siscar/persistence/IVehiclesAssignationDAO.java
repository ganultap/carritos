package geniar.siscar.persistence;

import geniar.siscar.model.VehiclesAssignation;

import java.util.List;

/**
 * Interface for VehiclesAssignationDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IVehiclesAssignationDAO {
	/**
	 * Perform an initial save of a previously unsaved VehiclesAssignation
	 * entity. All subsequent persist actions of this entity should use the
	 * #update() method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IVehiclesAssignationDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            VehiclesAssignation entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(VehiclesAssignation entity);

	/**
	 * Delete a persistent VehiclesAssignation entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IVehiclesAssignationDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            VehiclesAssignation entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(VehiclesAssignation entity);

	/**
	 * Persist a previously saved VehiclesAssignation entity and return it or a
	 * copy of it to the sender. A copy of the VehiclesAssignation entity
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
	 * entity = IVehiclesAssignationDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            VehiclesAssignation entity to update
	 * @returns VehiclesAssignation the persisted VehiclesAssignation entity
	 *          instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public VehiclesAssignation update(VehiclesAssignation entity);

	public VehiclesAssignation findById(Long id);

	/**
	 * Find all VehiclesAssignation entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the VehiclesAssignation property to query
	 * @param value
	 *            the property value to match
	 * @return List<VehiclesAssignation> found by query
	 */
	public List<VehiclesAssignation> findByProperty(String propertyName, Object value);

	public List<VehiclesAssignation> findByVhaNumeroSolicitud(Object vhaNumeroSolicitud);

	public List<VehiclesAssignation> findByVhaNumeroCarne(Object vhaNumeroCarne);

	public List<VehiclesAssignation> findByVhaKilomeActual(Object vhaKilomeActual);

	public List<VehiclesAssignation> findByVhaObservacion(Object vhaObservacion);

	public List<VehiclesAssignation> findByVhaKilomDev(Object vhaKilomDev);

	public List<VehiclesAssignation> findByVhaCobroCasaciat(Object vhaCobroCasaciat);

	public List<VehiclesAssignation> findByVhaCobro(Object vhaCobro);

	public List<VehiclesAssignation> findByVhaObservacionDev(Object vhaObservacionDev);

	/**
	 * Find all VehiclesAssignation entities.
	 * 
	 * @return List<VehiclesAssignation> all VehiclesAssignation entities
	 */
	public List<VehiclesAssignation> findAll();
}