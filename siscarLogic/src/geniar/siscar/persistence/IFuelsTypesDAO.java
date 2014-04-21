package geniar.siscar.persistence;

import geniar.siscar.model.FuelsTypes;

import java.util.List;

/**
 * Interface for FuelsTypesDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IFuelsTypesDAO {
	/**
	 * Perform an initial save of a previously unsaved FuelsTypes entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IFuelsTypesDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            FuelsTypes entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(FuelsTypes entity);

	/**
	 * Delete a persistent FuelsTypes entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IFuelsTypesDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            FuelsTypes entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(FuelsTypes entity);

	/**
	 * Persist a previously saved FuelsTypes entity and return it or a copy of
	 * it to the sender. A copy of the FuelsTypes entity parameter is returned
	 * when the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IFuelsTypesDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            FuelsTypes entity to update
	 * @returns FuelsTypes the persisted FuelsTypes entity instance, may not be
	 *          the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public FuelsTypes update(FuelsTypes entity);

	public FuelsTypes findById(Long id);

	/**
	 * Find all FuelsTypes entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the FuelsTypes property to query
	 * @param value
	 *            the property value to match
	 * @return List<FuelsTypes> found by query
	 */
	public List<FuelsTypes> findByProperty(String propertyName, Object value);

	public List<FuelsTypes> findByFutNombre(Object futNombre);

	/**
	 * Find all FuelsTypes entities.
	 * 
	 * @return List<FuelsTypes> all FuelsTypes entities
	 */
	public List<FuelsTypes> findAll();
}