package geniar.siscar.persistence;

import geniar.siscar.model.InvoiceHeader;

import java.util.List;

/**
 * Interface for InvoiceHeaderDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IInvoiceHeaderDAO {
	/**
	 * Perform an initial save of a previously unsaved InvoiceHeader entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IInvoiceHeaderDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            InvoiceHeader entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(InvoiceHeader entity);

	/**
	 * Delete a persistent InvoiceHeader entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IInvoiceHeaderDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            InvoiceHeader entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(InvoiceHeader entity);

	/**
	 * Persist a previously saved InvoiceHeader entity and return it or a copy
	 * of it to the sender. A copy of the InvoiceHeader entity parameter is
	 * returned when the JPA persistence mechanism has not previously been
	 * tracking the updated entity. This operation must be performed within the
	 * a database transaction context for the entity's data to be permanently
	 * saved to the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IInvoiceHeaderDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            InvoiceHeader entity to update
	 * @returns InvoiceHeader the persisted InvoiceHeader entity instance, may
	 *          not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public InvoiceHeader update(InvoiceHeader entity);

	public InvoiceHeader findById(Long id);

	/**
	 * Find all InvoiceHeader entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the InvoiceHeader property to query
	 * @param value
	 *            the property value to match
	 * @return List<InvoiceHeader> found by query
	 */
	public List<InvoiceHeader> findByProperty(String propertyName, Object value);

	public List<InvoiceHeader> findByPInvoiceNum(Object PInvoiceNum);

	public List<InvoiceHeader> findByPTipoFactura(Object PTipoFactura);

	public List<InvoiceHeader> findByPNit(Object PNit);

	public List<InvoiceHeader> findByPMonedaFactura(Object PMonedaFactura);

	public List<InvoiceHeader> findByPConvType(Object PConvType);

	public List<InvoiceHeader> findByPInvoiceAmount(Object PInvoiceAmount);

	public List<InvoiceHeader> findByPConvRate(Object PConvRate);

	public List<InvoiceHeader> findByPDescription(Object PDescription);

	public List<InvoiceHeader> findByPUser(Object PUser);

	public List<InvoiceHeader> findByPSource(Object PSource);

	/**
	 * Find all InvoiceHeader entities.
	 * 
	 * @return List<InvoiceHeader> all InvoiceHeader entities
	 */
	public List<InvoiceHeader> findAll();
}