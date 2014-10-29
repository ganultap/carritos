package geniar.siscar.persistence;

// default package

import geniar.siscar.model.PoliciesVehicles;
import geniar.siscar.model.PoliciesVehiclesId;

import java.util.List;

/**
 * Interface for PoliciesVehiclesDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IPoliciesVehiclesDAO {
	/**
	 * Perform an initial save of a previously unsaved PoliciesVehicles entity.
	 * All subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IPoliciesVehiclesDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            PoliciesVehicles entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(PoliciesVehicles entity);

	/**
	 * Delete a persistent PoliciesVehicles entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IPoliciesVehiclesDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            PoliciesVehicles entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(PoliciesVehicles entity);

	/**
	 * Persist a previously saved PoliciesVehicles entity and return it or a
	 * copy of it to the sender. A copy of the PoliciesVehicles entity parameter
	 * is returned when the JPA persistence mechanism has not previously been
	 * tracking the updated entity. This operation must be performed within the
	 * a database transaction context for the entity's data to be permanently
	 * saved to the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IPoliciesVehiclesDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            PoliciesVehicles entity to update
	 * @returns PoliciesVehicles the persisted PoliciesVehicles entity instance,
	 *          may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public PoliciesVehicles update(PoliciesVehicles entity);

	public PoliciesVehicles findById(PoliciesVehiclesId id);

	/**
	 * Find all PoliciesVehicles entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the PoliciesVehicles property to query
	 * @param value
	 *            the property value to match
	 * @return List<PoliciesVehicles> found by query
	 */
	public List<PoliciesVehicles> findByProperty(String propertyName,
			Object value);

	public List<PoliciesVehicles> findByPvsEstado(Object pvsEstado);

	public List<PoliciesVehicles> findByPvsValorPrima(Object pvsValorPrima);

	public List<PoliciesVehicles> findByPvsValorIva(Object pvsValorIva);

	public List<PoliciesVehicles> findByPvsValorTotal(Object pvsValorTotal);

	public List<PoliciesVehicles> findByPvsValorAsegurado(
			Object pvsValorAsegurado);

	public List<PoliciesVehicles> findByPvsCarnetAsignatario(
			Object pvsCarnetAsignatario);

	public List<PoliciesVehicles> findByPvsNumeroFactura(
			Object pvsNumeroFactura);
	/**
	 * Find all PoliciesVehicles entities.
	 * 
	 * @return List<PoliciesVehicles> all PoliciesVehicles entities
	 */
	public List<PoliciesVehicles> findAll();
}