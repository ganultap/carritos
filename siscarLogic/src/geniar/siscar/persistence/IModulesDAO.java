package geniar.siscar.persistence;

import geniar.siscar.model.Modules;

import java.util.List;

/**
 * Interface for ModulesDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IModulesDAO {
	/**
	 * Perform an initial save of a previously unsaved Modules entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IModulesDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Modules entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Modules entity);

	/**
	 * Delete a persistent Modules entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IModulesDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Modules entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Modules entity);

	/**
	 * Persist a previously saved Modules entity and return it or a copy of it
	 * to the sender. A copy of the Modules entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IModulesDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Modules entity to update
	 * @returns Modules the persisted Modules entity instance, may not be the
	 *          same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Modules update(Modules entity);

	public Modules findById(Long id);

	/**
	 * Find all Modules entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Modules property to query
	 * @param value
	 *            the property value to match
	 * @return List<Modules> found by query
	 */
	public List<Modules> findByProperty(String propertyName, Object value);

	public List<Modules> findByMdlNombre(Object mdlNombre);

	/**
	 * Find all Modules entities.
	 * 
	 * @return List<Modules> all Modules entities
	 */
	public List<Modules> findAll();
}