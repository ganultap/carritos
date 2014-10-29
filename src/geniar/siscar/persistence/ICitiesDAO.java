package geniar.siscar.persistence;

import geniar.siscar.model.Cities;

import java.util.List;

/**
 * Interface for CitiesDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface ICitiesDAO {
	/**
	 * Perform an initial save of a previously unsaved Cities entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ICitiesDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Cities entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Cities entity);

	/**
	 * Delete a persistent Cities entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ICitiesDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Cities entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Cities entity);

	/**
	 * Persist a previously saved Cities entity and return it or a copy of it to
	 * the sender. A copy of the Cities entity parameter is returned when the
	 * JPA persistence mechanism has not previously been tracking the updated
	 * entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = ICitiesDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Cities entity to update
	 * @returns Cities the persisted Cities entity instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Cities update(Cities entity);

	public Cities findById(Long id);

	/**
	 * Find all Cities entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Cities property to query
	 * @param value
	 *            the property value to match
	 * @return List<Cities> found by query
	 */
	public List<Cities> findByProperty(String propertyName, Object value);

	public List<Cities> findByCtsNombre(Object ctsNombre);

	/**
	 * Find all Cities entities.
	 * 
	 * @return List<Cities> all Cities entities
	 */
	public List<Cities> findAll();
}