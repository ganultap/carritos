package geniar.siscar.persistence;
// default package


import geniar.siscar.model.Policies;

import java.util.List;

/**
 * Interface for PoliciesDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IPoliciesDAO {
	/**
	 * Perform an initial save of a previously unsaved Policies entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IPoliciesDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Policies entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Policies entity);

	/**
	 * Delete a persistent Policies entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IPoliciesDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Policies entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Policies entity);

	/**
	 * Persist a previously saved Policies entity and return it or a copy of it
	 * to the sender. A copy of the Policies entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IPoliciesDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Policies entity to update
	 * @returns Policies the persisted Policies entity instance, may not be the
	 *          same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Policies update(Policies entity);

	public Policies findById(Long id);

	/**
	 * Find all Policies entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Policies property to query
	 * @param value
	 *            the property value to match
	 * @return List<Policies> found by query
	 */
	public List<Policies> findByProperty(String propertyName, Object value);

	public List<Policies> findByPlsNumero(Object plsNumero);

	public List<Policies> findByPlsDocUno(Object plsDocUno);

	public List<Policies> findByPlsDocDos(Object plsDocDos);

	public List<Policies> findByPlsEstado(Object plsEstado);

	/**
	 * Find all Policies entities.
	 * 
	 * @return List<Policies> all Policies entities
	 */
	public List<Policies> findAll();
}