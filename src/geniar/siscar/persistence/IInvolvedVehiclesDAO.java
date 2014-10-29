package geniar.siscar.persistence;

import geniar.siscar.model.InvolvedVehicles;

import java.util.List;

/**
 * Interface for InvolvedVehiclesDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IInvolvedVehiclesDAO {
	/**
	 * Perform an initial save of a previously unsaved InvolvedVehicles entity.
	 * All subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IInvolvedVehiclesDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            InvolvedVehicles entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(InvolvedVehicles entity);

	/**
	 * Delete a persistent InvolvedVehicles entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IInvolvedVehiclesDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            InvolvedVehicles entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(InvolvedVehicles entity);

	/**
	 * Persist a previously saved InvolvedVehicles entity and return it or a
	 * copy of it to the sender. A copy of the InvolvedVehicles entity parameter
	 * is returned when the JPA persistence mechanism has not previously been
	 * tracking the updated entity. This operation must be performed within the
	 * a database transaction context for the entity's data to be permanently
	 * saved to the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IInvolvedVehiclesDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            InvolvedVehicles entity to update
	 * @returns InvolvedVehicles the persisted InvolvedVehicles entity instance,
	 *          may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public InvolvedVehicles update(InvolvedVehicles entity);

	public InvolvedVehicles findById(Long id);

	/**
	 * Find all InvolvedVehicles entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the InvolvedVehicles property to query
	 * @param value
	 *            the property value to match
	 * @return List<InvolvedVehicles> found by query
	 */
	public List<InvolvedVehicles> findByProperty(String propertyName, Object value);

	public List<InvolvedVehicles> findByHnvTipoVehiculo(Object hnvTipoVehiculo);

	public List<InvolvedVehicles> findByHnvPlaca(Object hnvPlaca);

	public List<InvolvedVehicles> findByHnvMarca(Object hnvMarca);

	public List<InvolvedVehicles> findByHnvConductor(Object hnvConductor);

	public List<InvolvedVehicles> findByHnvIdentifConduc(Object hnvIdentifConduc);

	public List<InvolvedVehicles> findByHnvTelefConduc(Object hnvTelefConduc);

	public List<InvolvedVehicles> findByHnvIdentifProp(Object hnvIdentifProp);

	public List<InvolvedVehicles> findByHnvPropietario(Object hnvPropietario);

	public List<InvolvedVehicles> findByHnvDireccionconductor(Object hnvDireccionconductor);

	/**
	 * Find all InvolvedVehicles entities.
	 * 
	 * @return List<InvolvedVehicles> all InvolvedVehicles entities
	 */
	public List<InvolvedVehicles> findAll();
}