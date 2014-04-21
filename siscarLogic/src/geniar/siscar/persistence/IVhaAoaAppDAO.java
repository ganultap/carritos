package geniar.siscar.persistence;

import geniar.siscar.model.VhaAoaApp;

import java.util.List;

/**
 * Interface for VhaAoaAppDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IVhaAoaAppDAO {
	/**
	 * Perform an initial save of a previously unsaved VhaAoaApp entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IVhaAoaAppDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            VhaAoaApp entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(VhaAoaApp entity);

	/**
	 * Delete a persistent VhaAoaApp entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IVhaAoaAppDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            VhaAoaApp entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(VhaAoaApp entity);

	/**
	 * Persist a previously saved VhaAoaApp entity and return it or a copy of it
	 * to the sender. A copy of the VhaAoaApp entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IVhaAoaAppDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            VhaAoaApp entity to update
	 * @returns VhaAoaApp the persisted VhaAoaApp entity instance, may not be
	 *          the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public VhaAoaApp update(VhaAoaApp entity);

	public VhaAoaApp findById(Long id);

	/**
	 * Find all VhaAoaApp entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the VhaAoaApp property to query
	 * @param value
	 *            the property value to match
	 * @return List<VhaAoaApp> found by query
	 */
	public List<VhaAoaApp> findByProperty(String propertyName, Object value);

	public List<VhaAoaApp> findByAoaObservacion(Object aoaObservacion);

	/**
	 * Find all VhaAoaApp entities.
	 * 
	 * @return List<VhaAoaApp> all VhaAoaApp entities
	 */
	public List<VhaAoaApp> findAll();
}