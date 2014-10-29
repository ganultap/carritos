package geniar.siscar.persistence;

import geniar.siscar.model.ControlType;

import java.util.List;

/**
 * Interface for ControlTypeDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IControlTypeDAO {
	/**
	 * Perform an initial save of a previously unsaved ControlType entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IControlTypeDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            ControlType entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(ControlType entity);

	/**
	 * Delete a persistent ControlType entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IControlTypeDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            ControlType entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(ControlType entity);

	/**
	 * Persist a previously saved ControlType entity and return it or a copy of
	 * it to the sender. A copy of the ControlType entity parameter is returned
	 * when the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IControlTypeDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            ControlType entity to update
	 * @return ControlType the persisted ControlType entity instance, may not be
	 *         the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public ControlType update(ControlType entity);

	public ControlType findById(Long id);

	/**
	 * Find all ControlType entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the ControlType property to query
	 * @param value
	 *            the property value to match
	 * @return List<ControlType> found by query
	 */
	public List<ControlType> findByProperty(String propertyName, Object value);

	public List<ControlType> findByCttValor(Object cttValor);

	public List<ControlType> findByCttDescripcion(Object cttDescripcion);

	/**
	 * Find all ControlType entities.
	 * 
	 * @return List<ControlType> all ControlType entities
	 */
	public List<ControlType> findAll();
}