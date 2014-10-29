package geniar.siscar.persistence;

import geniar.siscar.model.Countries;

import java.util.List;

/**
 * Interface for CountriesDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface ICountriesDAO {
	/**
	 * Perform an initial save of a previously unsaved Countries entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ICountriesDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Countries entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Countries entity);

	/**
	 * Delete a persistent Countries entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ICountriesDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Countries entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Countries entity);

	/**
	 * Persist a previously saved Countries entity and return it or a copy of it
	 * to the sender. A copy of the Countries entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = ICountriesDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Countries entity to update
	 * @returns Countries the persisted Countries entity instance, may not be
	 *          the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Countries update(Countries entity);

	public Countries findById(Long id);

	/**
	 * Find all Countries entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Countries property to query
	 * @param value
	 *            the property value to match
	 * @return List<Countries> found by query
	 */
	public List<Countries> findByProperty(String propertyName, Object value);

	public List<Countries> findByCntNombre(Object cntNombre);

	/**
	 * Find all Countries entities.
	 * 
	 * @return List<Countries> all Countries entities
	 */
	public List<Countries> findAll();
}