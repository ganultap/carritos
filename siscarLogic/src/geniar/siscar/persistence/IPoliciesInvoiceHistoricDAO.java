package geniar.siscar.persistence;
// default package


import geniar.siscar.model.PoliciesInvoiceHistoric;

import java.util.List;

/**
 * Interface for PoliciesInvoiceHistoricDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IPoliciesInvoiceHistoricDAO {
	/**
	 * Perform an initial save of a previously unsaved PoliciesInvoiceHistoric
	 * entity. All subsequent persist actions of this entity should use the
	 * #update() method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IPoliciesInvoiceHistoricDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            PoliciesInvoiceHistoric entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(PoliciesInvoiceHistoric entity);

	/**
	 * Delete a persistent PoliciesInvoiceHistoric entity. This operation must
	 * be performed within the a database transaction context for the entity's
	 * data to be permanently deleted from the persistence store, i.e.,
	 * database. This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IPoliciesInvoiceHistoricDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            PoliciesInvoiceHistoric entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(PoliciesInvoiceHistoric entity);

	/**
	 * Persist a previously saved PoliciesInvoiceHistoric entity and return it
	 * or a copy of it to the sender. A copy of the PoliciesInvoiceHistoric
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
	 * entity = IPoliciesInvoiceHistoricDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            PoliciesInvoiceHistoric entity to update
	 * @returns PoliciesInvoiceHistoric the persisted PoliciesInvoiceHistoric
	 *          entity instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public PoliciesInvoiceHistoric update(PoliciesInvoiceHistoric entity);

	public PoliciesInvoiceHistoric findById(Long id);

	/**
	 * Find all PoliciesInvoiceHistoric entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the PoliciesInvoiceHistoric property to query
	 * @param value
	 *            the property value to match
	 * @return List<PoliciesInvoiceHistoric> found by query
	 */
	public List<PoliciesInvoiceHistoric> findByProperty(String propertyName,
			Object value);

	public List<PoliciesInvoiceHistoric> findByUsrLogin(Object usrLogin);

	public List<PoliciesInvoiceHistoric> findByPinNumeroFactura(
			Object pinNumeroFactura);

	public List<PoliciesInvoiceHistoric> findByPinConcepto(Object pinConcepto);

	public List<PoliciesInvoiceHistoric> findByPlsCodigo(Object plsCodigo);

	/**
	 * Find all PoliciesInvoiceHistoric entities.
	 * 
	 * @return List<PoliciesInvoiceHistoric> all PoliciesInvoiceHistoric
	 *         entities
	 */
	public List<PoliciesInvoiceHistoric> findAll();
}