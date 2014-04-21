package geniar.siscar.persistence;

import geniar.siscar.model.HeaderProof;

import java.util.List;

/**
 * Interface for HeaderProofDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IHeaderProofDAO {
	/**
	 * Perform an initial save of a previously unsaved HeaderProof entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IHeaderProofDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            HeaderProof entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(HeaderProof entity);

	/**
	 * Delete a persistent HeaderProof entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IHeaderProofDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            HeaderProof entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(HeaderProof entity);

	/**
	 * Persist a previously saved HeaderProof entity and return it or a copy of
	 * it to the sender. A copy of the HeaderProof entity parameter is returned
	 * when the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IHeaderProofDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            HeaderProof entity to update
	 * @returns HeaderProof the persisted HeaderProof entity instance, may not
	 *          be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public HeaderProof update(HeaderProof entity);

	public HeaderProof findById(Long id);

	/**
	 * Find all HeaderProof entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the HeaderProof property to query
	 * @param value
	 *            the property value to match
	 * @return List<HeaderProof> found by query
	 */
	public List<HeaderProof> findByProperty(String propertyName, Object value);

	public List<HeaderProof> findByHepGroupId(Object hepGroupId);

	/**
	 * Find all HeaderProof entities.
	 * 
	 * @return List<HeaderProof> all HeaderProof entities
	 */
	public List<HeaderProof> findAll();
}