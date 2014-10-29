package geniar.siscar.persistence;

import geniar.siscar.model.CostsCenters;

import java.util.List;

/**
 * Interface for CostsCentersDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface ICostsCentersDAO {
	/**
	 * Perform an initial save of a previously unsaved CostsCenters entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ICostsCentersDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            CostsCenters entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(CostsCenters entity);

	/**
	 * Delete a persistent CostsCenters entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ICostsCentersDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            CostsCenters entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(CostsCenters entity);

	/**
	 * Persist a previously saved CostsCenters entity and return it or a copy of
	 * it to the sender. A copy of the CostsCenters entity parameter is returned
	 * when the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = ICostsCentersDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            CostsCenters entity to update
	 * @returns CostsCenters the persisted CostsCenters entity instance, may not
	 *          be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public CostsCenters update(CostsCenters entity);

	public CostsCenters findById(Long id);

	/**
	 * Find all CostsCenters entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the CostsCenters property to query
	 * @param value
	 *            the property value to match
	 * @return List<CostsCenters> found by query
	 */
	public List<CostsCenters> findByProperty(String propertyName, Object value);

	public List<CostsCenters> findByCocNumero(Object cocNumero);

	/**
	 * Find all CostsCenters entities.
	 * 
	 * @return List<CostsCenters> all CostsCenters entities
	 */
	public List<CostsCenters> findAll();
}