package geniar.siscar.persistence;

import geniar.siscar.model.Brands;

import java.util.List;

/**
 * Interface for BrandsDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IBrandsDAO {
	/**
	 * Perform an initial save of a previously unsaved Brands entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IBrandsDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Brands entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Brands entity);

	/**
	 * Delete a persistent Brands entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IBrandsDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Brands entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Brands entity);

	/**
	 * Persist a previously saved Brands entity and return it or a copy of it to
	 * the sender. A copy of the Brands entity parameter is returned when the
	 * JPA persistence mechanism has not previously been tracking the updated
	 * entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IBrandsDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Brands entity to update
	 * @returns Brands the persisted Brands entity instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Brands update(Brands entity);

	public Brands findById(Long id);

	/**
	 * Find all Brands entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Brands property to query
	 * @param value
	 *            the property value to match
	 * @return List<Brands> found by query
	 */
	public List<Brands> findByProperty(String propertyName, Object value);

	public List<Brands> findByBrnNombre(Object brnNombre);

	/**
	 * Find all Brands entities.
	 * 
	 * @return List<Brands> all Brands entities
	 */
	public List<Brands> findAll();
}