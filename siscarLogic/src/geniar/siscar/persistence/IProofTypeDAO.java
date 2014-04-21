package geniar.siscar.persistence;

import geniar.siscar.model.ProofType;

import java.util.List;

/**
 * Interface for ProofTypeDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IProofTypeDAO {
	/**
	 * Perform an initial save of a previously unsaved ProofType entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IProofTypeDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            ProofType entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(ProofType entity);

	/**
	 * Delete a persistent ProofType entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IProofTypeDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            ProofType entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(ProofType entity);

	/**
	 * Persist a previously saved ProofType entity and return it or a copy of it
	 * to the sender. A copy of the ProofType entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IProofTypeDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            ProofType entity to update
	 * @returns ProofType the persisted ProofType entity instance, may not be
	 *          the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public ProofType update(ProofType entity);

	public ProofType findById(Long id);

	/**
	 * Find all ProofType entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the ProofType property to query
	 * @param value
	 *            the property value to match
	 * @return List<ProofType> found by query
	 */
	public List<ProofType> findByProperty(String propertyName, Object value);

	public List<ProofType> findByPrtNombre(Object prtNombre);

	/**
	 * Find all ProofType entities.
	 * 
	 * @return List<ProofType> all ProofType entities
	 */
	public List<ProofType> findAll();
}