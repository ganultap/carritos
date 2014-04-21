package geniar.siscar.persistence;

import geniar.siscar.model.PoliciesAplications;

import java.util.List;

/**
 * Interface for PoliciesAplicationsDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IPoliciesAplicationsDAO {
	/**
	 * Perform an initial save of a previously unsaved PoliciesAplications
	 * entity. All subsequent persist actions of this entity should use the
	 * #update() method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IPoliciesAplicationsDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            PoliciesAplications entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(PoliciesAplications entity);

	/**
	 * Delete a persistent PoliciesAplications entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IPoliciesAplicationsDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            PoliciesAplications entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(PoliciesAplications entity);

	/**
	 * Persist a previously saved PoliciesAplications entity and return it or a
	 * copy of it to the sender. A copy of the PoliciesAplications entity
	 * parameter is returned when the JPA persistence mechanism has not
	 * previously been tracking the updated entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently saved to the persistence store, i.e., database. This
	 * method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IPoliciesAplicationsDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            PoliciesAplications entity to update
	 * @returns PoliciesAplications the persisted PoliciesAplications entity
	 *          instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public PoliciesAplications update(PoliciesAplications entity);

	public PoliciesAplications findById(Long id);

	/**
	 * Find all PoliciesAplications entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the PoliciesAplications property to query
	 * @param value
	 *            the property value to match
	 * @return List<PoliciesAplications> found by query
	 */
	public List<PoliciesAplications> findByProperty(String propertyName, Object value);

	/**
	 * Find all PoliciesAplications entities.
	 * 
	 * @return List<PoliciesAplications> all PoliciesAplications entities
	 */
	public List<PoliciesAplications> findAll();
}