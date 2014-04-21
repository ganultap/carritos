package geniar.siscar.persistence;

import geniar.siscar.model.MoneyType;

import java.util.List;

/**
 * Interface for MoneyTypeDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IMoneyTypeDAO {
	/**
	 * Perform an initial save of a previously unsaved MoneyType entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IMoneyTypeDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            MoneyType entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(MoneyType entity);

	/**
	 * Delete a persistent MoneyType entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IMoneyTypeDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            MoneyType entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(MoneyType entity);

	/**
	 * Persist a previously saved MoneyType entity and return it or a copy of it
	 * to the sender. A copy of the MoneyType entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IMoneyTypeDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            MoneyType entity to update
	 * @returns MoneyType the persisted MoneyType entity instance, may not be
	 *          the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public MoneyType update(MoneyType entity);

	public MoneyType findById(Long id);

	/**
	 * Find all MoneyType entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the MoneyType property to query
	 * @param value
	 *            the property value to match
	 * @return List<MoneyType> found by query
	 */
	public List<MoneyType> findByProperty(String propertyName, Object value);

	public List<MoneyType> findByMneyNombre(Object mneyNombre);

	/**
	 * Find all MoneyType entities.
	 * 
	 * @return List<MoneyType> all MoneyType entities
	 */
	public List<MoneyType> findAll();
}