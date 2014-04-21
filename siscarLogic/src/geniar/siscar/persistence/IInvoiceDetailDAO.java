package geniar.siscar.persistence;

import geniar.siscar.model.InvoiceDetail;

import java.util.List;

/**
 * Interface for InvoiceDetailDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IInvoiceDetailDAO {
	/**
	 * Perform an initial save of a previously unsaved InvoiceDetail entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IInvoiceDetailDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            InvoiceDetail entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(InvoiceDetail entity);

	/**
	 * Delete a persistent InvoiceDetail entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IInvoiceDetailDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            InvoiceDetail entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(InvoiceDetail entity);

	/**
	 * Persist a previously saved InvoiceDetail entity and return it or a copy
	 * of it to the sender. A copy of the InvoiceDetail entity parameter is
	 * returned when the JPA persistence mechanism has not previously been
	 * tracking the updated entity. This operation must be performed within the
	 * a database transaction context for the entity's data to be permanently
	 * saved to the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IInvoiceDetailDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            InvoiceDetail entity to update
	 * @returns InvoiceDetail the persisted InvoiceDetail entity instance, may
	 *          not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public InvoiceDetail update(InvoiceDetail entity);

	public InvoiceDetail findById(Long id);

	/**
	 * Find all InvoiceDetail entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the InvoiceDetail property to query
	 * @param value
	 *            the property value to match
	 * @return List<InvoiceDetail> found by query
	 */
	public List<InvoiceDetail> findByProperty(String propertyName, Object value);

	public List<InvoiceDetail> findByPInvoiceId(Object PInvoiceId);

	public List<InvoiceDetail> findByPLineNum(Object PLineNum);

	public List<InvoiceDetail> findByPInvoiceNum(Object PInvoiceNum);

	public List<InvoiceDetail> findByPInvoiceAmount(Object PInvoiceAmount);

	public List<InvoiceDetail> findByPDescription(Object PDescription);

	public List<InvoiceDetail> findByPUserId(Object PUserId);

	public List<InvoiceDetail> findByPPlacaVeh(Object PPlacaVeh);

	public List<InvoiceDetail> findByPCompany(Object PCompany);

	public List<InvoiceDetail> findByPAccount(Object PAccount);

	public List<InvoiceDetail> findByPCcenter(Object PCcenter);

	public List<InvoiceDetail> findByPAuxiliary(Object PAuxiliary);

	public List<InvoiceDetail> findByPOrgId(Object POrgId);

	public List<InvoiceDetail> findByPLocation(Object PLocation);

	/**
	 * Find all InvoiceDetail entities.
	 * 
	 * @return List<InvoiceDetail> all InvoiceDetail entities
	 */
	public List<InvoiceDetail> findAll();
}