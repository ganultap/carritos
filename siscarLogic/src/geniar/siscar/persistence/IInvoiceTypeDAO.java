package geniar.siscar.persistence;

import geniar.siscar.model.InvoiceType;

import java.util.List;

/**
 * Interface for InvoiceTypeDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IInvoiceTypeDAO {
	/**
	 * Perform an initial save of a previously unsaved InvoiceType entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IInvoiceTypeDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            InvoiceType entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(InvoiceType entity);

	/**
	 * Delete a persistent InvoiceType entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IInvoiceTypeDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            InvoiceType entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(InvoiceType entity);

	/**
	 * Persist a previously saved InvoiceType entity and return it or a copy of
	 * it to the sender. A copy of the InvoiceType entity parameter is returned
	 * when the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IInvoiceTypeDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            InvoiceType entity to update
	 * @returns InvoiceType the persisted InvoiceType entity instance, may not
	 *          be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public InvoiceType update(InvoiceType entity);

	public InvoiceType findById(Long id);

	/**
	 * Find all InvoiceType entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the InvoiceType property to query
	 * @param value
	 *            the property value to match
	 * @return List<InvoiceType> found by query
	 */
	public List<InvoiceType> findByProperty(String propertyName, Object value);

	public List<InvoiceType> findByIntNombre(Object intNombre);

	/**
	 * Find all InvoiceType entities.
	 * 
	 * @return List<InvoiceType> all InvoiceType entities
	 */
	public List<InvoiceType> findAll();
}