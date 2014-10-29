package geniar.siscar.persistence;

import geniar.siscar.model.VhaFf;

import java.util.List;

/**
 * Interface for VhaFfDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IVhaFfDAO {
	/**
	 * Perform an initial save of a previously unsaved VhaFf entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IVhaFfDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            VhaFf entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(VhaFf entity);

	/**
	 * Delete a persistent VhaFf entity. This operation must be performed within
	 * the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IVhaFfDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            VhaFf entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(VhaFf entity);

	/**
	 * Persist a previously saved VhaFf entity and return it or a copy of it to
	 * the sender. A copy of the VhaFf entity parameter is returned when the JPA
	 * persistence mechanism has not previously been tracking the updated
	 * entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IVhaFfDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            VhaFf entity to update
	 * @returns VhaFf the persisted VhaFf entity instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public VhaFf update(VhaFf entity);

	public VhaFf findById(Long id);

	/**
	 * Find all VhaFf entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the VhaFf property to query
	 * @param value
	 *            the property value to match
	 * @return List<VhaFf> found by query
	 */
	public List<VhaFf> findByProperty(String propertyName, Object value);

	public List<VhaFf> findByVhfObservacion(Object vhfObservacion);

	/**
	 * Find all VhaFf entities.
	 * 
	 * @return List<VhaFf> all VhaFf entities
	 */
	public List<VhaFf> findAll();
}