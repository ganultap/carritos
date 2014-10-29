package geniar.siscar.persistence;

import geniar.siscar.model.MonthTransacType;

import java.util.List;

/**
 * Interface for MonthTransacTypeDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IMonthTransacTypeDAO {
	/**
	 * Perform an initial save of a previously unsaved MonthTransacType entity.
	 * All subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IMonthTransacTypeDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            MonthTransacType entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(MonthTransacType entity);

	/**
	 * Delete a persistent MonthTransacType entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IMonthTransacTypeDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            MonthTransacType entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(MonthTransacType entity);

	/**
	 * Persist a previously saved MonthTransacType entity and return it or a
	 * copy of it to the sender. A copy of the MonthTransacType entity parameter
	 * is returned when the JPA persistence mechanism has not previously been
	 * tracking the updated entity. This operation must be performed within the
	 * a database transaction context for the entity's data to be permanently
	 * saved to the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IMonthTransacTypeDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            MonthTransacType entity to update
	 * @return MonthTransacType the persisted MonthTransacType entity instance,
	 *         may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public MonthTransacType update(MonthTransacType entity);

	public MonthTransacType findById(Long id);

	/**
	 * Find all MonthTransacType entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the MonthTransacType property to query
	 * @param value
	 *            the property value to match
	 * @return List<MonthTransacType> found by query
	 */
	public List<MonthTransacType> findByProperty(String propertyName,
			Object value);

	public List<MonthTransacType> findByMttNombre(Object mttNombre);

	/**
	 * Find all MonthTransacType entities.
	 * 
	 * @return List<MonthTransacType> all MonthTransacType entities
	 */
	public List<MonthTransacType> findAll();
}