package geniar.siscar.persistence;
// default package


import geniar.siscar.model.PoliciesInvoice;

import java.util.List;

/**
 * Interface for PoliciesInvoiceDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IPoliciesInvoiceDAO {
	/**
	 * Perform an initial save of a previously unsaved PoliciesInvoice entity.
	 * All subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IPoliciesInvoiceDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            PoliciesInvoice entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(PoliciesInvoice entity);

	/**
	 * Delete a persistent PoliciesInvoice entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IPoliciesInvoiceDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            PoliciesInvoice entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(PoliciesInvoice entity);

	/**
	 * Persist a previously saved PoliciesInvoice entity and return it or a copy
	 * of it to the sender. A copy of the PoliciesInvoice entity parameter is
	 * returned when the JPA persistence mechanism has not previously been
	 * tracking the updated entity. This operation must be performed within the
	 * a database transaction context for the entity's data to be permanently
	 * saved to the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IPoliciesInvoiceDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            PoliciesInvoice entity to update
	 * @returns PoliciesInvoice the persisted PoliciesInvoice entity instance,
	 *          may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public PoliciesInvoice update(PoliciesInvoice entity);

	public PoliciesInvoice findById(Long id);

	/**
	 * Find all PoliciesInvoice entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the PoliciesInvoice property to query
	 * @param value
	 *            the property value to match
	 * @return List<PoliciesInvoice> found by query
	 */
	public List<PoliciesInvoice> findByProperty(String propertyName,
			Object value);

	public List<PoliciesInvoice> findByPinNumeroFactura(Object pinNumeroFactura);

	public List<PoliciesInvoice> findByPinConcepto(Object pinConcepto);

	/**
	 * Find all PoliciesInvoice entities.
	 * 
	 * @return List<PoliciesInvoice> all PoliciesInvoice entities
	 */
	public List<PoliciesInvoice> findAll();
}