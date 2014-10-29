package geniar.siscar.persistence;

import geniar.siscar.model.Prepaid;

import java.util.List;

/**
 * Interface for PrepaidDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IPrepaidDAO {
	/**
	 * Perform an initial save of a previously unsaved Prepaid entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IPrepaidDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Prepaid entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Prepaid entity);

	/**
	 * Delete a persistent Prepaid entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IPrepaidDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Prepaid entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Prepaid entity);

	/**
	 * Persist a previously saved Prepaid entity and return it or a copy of it
	 * to the sender. A copy of the Prepaid entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IPrepaidDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Prepaid entity to update
	 * @returns Prepaid the persisted Prepaid entity instance, may not be the
	 *          same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Prepaid update(Prepaid entity);

	public Prepaid findById(Long id);

	/**
	 * Find all Prepaid entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Prepaid property to query
	 * @param value
	 *            the property value to match
	 * @return List<Prepaid>
	 *  found by query
	 * 
	 */
	public List<Prepaid> findByProperty(String propertyName, Object value);

	public List<Prepaid> findByPreAsignatario(Object preAsignatario);

	public List<Prepaid> findByPreTotal(Object preTotal);

	/**
	 * Find all Prepaid entities.
	 * 
	 * @return List<Prepaid>
	 *  all Prepaid entities
	 * 
	 */
	public List<Prepaid> findAll();
}