package geniar.siscar.persistence;

import geniar.siscar.model.TransactionType;

import java.util.List;

/**
 * Interface for TransactionTypeDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface ITransactionTypeDAO {
	/**
	 * Perform an initial save of a previously unsaved TransactionType entity.
	 * All subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ITransactionTypeDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            TransactionType entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(TransactionType entity);

	/**
	 * Delete a persistent TransactionType entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ITransactionTypeDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            TransactionType entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(TransactionType entity);

	/**
	 * Persist a previously saved TransactionType entity and return it or a copy
	 * of it to the sender. A copy of the TransactionType entity parameter is
	 * returned when the JPA persistence mechanism has not previously been
	 * tracking the updated entity. This operation must be performed within the
	 * a database transaction context for the entity's data to be permanently
	 * saved to the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = ITransactionTypeDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            TransactionType entity to update
	 * @return TransactionType the persisted TransactionType entity instance,
	 *         may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public TransactionType update(TransactionType entity);

	public TransactionType findById(Long id);

	/**
	 * Find all TransactionType entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the TransactionType property to query
	 * @param value
	 *            the property value to match
	 * @return List<TransactionType> found by query
	 */
	public List<TransactionType> findByProperty(String propertyName,
			Object value);

	public List<TransactionType> findByTstNombre(Object tstNombre);

	public List<TransactionType> findByTstDescripcion(Object tstDescripcion);

	/**
	 * Find all TransactionType entities.
	 * 
	 * @return List<TransactionType> all TransactionType entities
	 */
	public List<TransactionType> findAll();
}