package geniar.siscar.persistence;

import geniar.siscar.model.ProofState;

import java.util.List;

/**
 * Interface for ProofStateDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IProofStateDAO {
	/**
	 * Perform an initial save of a previously unsaved ProofState entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IProofStateDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            ProofState entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(ProofState entity);

	/**
	 * Delete a persistent ProofState entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IProofStateDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            ProofState entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(ProofState entity);

	/**
	 * Persist a previously saved ProofState entity and return it or a copy of
	 * it to the sender. A copy of the ProofState entity parameter is returned
	 * when the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IProofStateDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            ProofState entity to update
	 * @returns ProofState the persisted ProofState entity instance, may not be
	 *          the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public ProofState update(ProofState entity);

	public ProofState findById(Long id);

	/**
	 * Find all ProofState entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the ProofState property to query
	 * @param value
	 *            the property value to match
	 * @return List<ProofState> found by query
	 */
	public List<ProofState> findByProperty(String propertyName, Object value);

	public List<ProofState> findByPrsNombre(Object prsNombre);

	/**
	 * Find all ProofState entities.
	 * 
	 * @return List<ProofState> all ProofState entities
	 */
	public List<ProofState> findAll();
}