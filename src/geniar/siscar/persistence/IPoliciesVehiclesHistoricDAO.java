package geniar.siscar.persistence;

// default package

import geniar.siscar.model.PoliciesVehiclesHistoric;

import java.util.List;

/**
 * Interface for PoliciesVehiclesHistoricDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IPoliciesVehiclesHistoricDAO {
	/**
	 * Perform an initial save of a previously unsaved PoliciesVehiclesHistoric
	 * entity. All subsequent persist actions of this entity should use the
	 * #update() method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IPoliciesVehiclesHistoricDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            PoliciesVehiclesHistoric entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(PoliciesVehiclesHistoric entity);

	/**
	 * Delete a persistent PoliciesVehiclesHistoric entity. This operation must
	 * be performed within the a database transaction context for the entity's
	 * data to be permanently deleted from the persistence store, i.e.,
	 * database. This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IPoliciesVehiclesHistoricDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            PoliciesVehiclesHistoric entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(PoliciesVehiclesHistoric entity);

	/**
	 * Persist a previously saved PoliciesVehiclesHistoric entity and return it
	 * or a copy of it to the sender. A copy of the PoliciesVehiclesHistoric
	 * entity parameter is returned when the JPA persistence mechanism has not
	 * previously been tracking the updated entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently saved to the persistence store, i.e., database. This
	 * method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IPoliciesVehiclesHistoricDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            PoliciesVehiclesHistoric entity to update
	 * @returns PoliciesVehiclesHistoric the persisted PoliciesVehiclesHistoric
	 *          entity instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public PoliciesVehiclesHistoric update(PoliciesVehiclesHistoric entity);

	public PoliciesVehiclesHistoric findById(Long id);

	/**
	 * Find all PoliciesVehiclesHistoric entities with a specific property
	 * value.
	 * 
	 * @param propertyName
	 *            the name of the PoliciesVehiclesHistoric property to query
	 * @param value
	 *            the property value to match
	 * @return List<PoliciesVehiclesHistoric> found by query
	 */
	public List<PoliciesVehiclesHistoric> findByProperty(String propertyName,
			Object value);

	public List<PoliciesVehiclesHistoric> findByPinNumeroFactura(
			Object pinNumeroFactura);

	public List<PoliciesVehiclesHistoric> findByUsrLogin(Object usrLogin);

	public List<PoliciesVehiclesHistoric> findByVhcCodigo(Object vhcCodigo);

	public List<PoliciesVehiclesHistoric> findByPlsCodigo(Object plsCodigo);

	public List<PoliciesVehiclesHistoric> findByPvsEstado(Object pvsEstado);

	public List<PoliciesVehiclesHistoric> findByPvsValorIva(Object pvsValorIva);

	public List<PoliciesVehiclesHistoric> findByPvsValorPrima(
			Object pvsValorPrima);

	public List<PoliciesVehiclesHistoric> findByPvsValorTotal(
			Object pvsValorTotal);

	public List<PoliciesVehiclesHistoric> findByPvsValorAsegurado(
			Object pvsValorAsegurado);

	public List<PoliciesVehiclesHistoric> findByPvsCarnetAsignatario(
			Object pvsCarnetAsignatario);

	/**
	 * Find all PoliciesVehiclesHistoric entities.
	 * 
	 * @return List<PoliciesVehiclesHistoric> all PoliciesVehiclesHistoric
	 *         entities
	 */
	public List<PoliciesVehiclesHistoric> findAll();
}