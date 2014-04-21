package geniar.siscar.persistence;

import geniar.siscar.model.Company;

import java.util.List;

/**
 * Interface for CompanyDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface ICompanyDAO {
	/**
	 * Perform an initial save of a previously unsaved Company entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ICompanyDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Company entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Company entity);

	/**
	 * Delete a persistent Company entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ICompanyDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Company entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Company entity);

	/**
	 * Persist a previously saved Company entity and return it or a copy of it
	 * to the sender. A copy of the Company entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = ICompanyDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Company entity to update
	 * @return Company the persisted Company entity instance, may not be the
	 *         same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Company update(Company entity);

	public Company findById(Long id);

	/**
	 * Find all Company entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Company property to query
	 * @param value
	 *            the property value to match
	 * @return List<Company> found by query
	 */
	public List<Company> findByProperty(String propertyName, Object value);

	public List<Company> findByCmpNombre(Object cmpNombre);

	public List<Company> findByCmpDescripcion(Object cmpDescripcion);

	/**
	 * Find all Company entities.
	 * 
	 * @return List<Company> all Company entities
	 */
	public List<Company> findAll();
}