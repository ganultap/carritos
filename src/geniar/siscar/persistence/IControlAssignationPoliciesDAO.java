package geniar.siscar.persistence;

import geniar.siscar.model.ControlAssignationPolicies;

import java.util.List;

/**
 * Interface for ControlAssignationPoliciesDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IControlAssignationPoliciesDAO {
	/**
	 * Perform an initial save of a previously unsaved
	 * ControlAssignationPolicies entity. All subsequent persist actions of this
	 * entity should use the #update() method. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently saved to the persistence store, i.e., database. This method
	 * uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IControlAssignationPoliciesDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            ControlAssignationPolicies entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(ControlAssignationPolicies entity);

	/**
	 * Delete a persistent ControlAssignationPolicies entity. This operation
	 * must be performed within the a database transaction context for the
	 * entity's data to be permanently deleted from the persistence store, i.e.,
	 * database. This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IControlAssignationPoliciesDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            ControlAssignationPolicies entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(ControlAssignationPolicies entity);

	/**
	 * Persist a previously saved ControlAssignationPolicies entity and return
	 * it or a copy of it to the sender. A copy of the
	 * ControlAssignationPolicies entity parameter is returned when the JPA
	 * persistence mechanism has not previously been tracking the updated
	 * entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IControlAssignationPoliciesDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            ControlAssignationPolicies entity to update
	 * @return ControlAssignationPolicies the persisted
	 *         ControlAssignationPolicies entity instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public ControlAssignationPolicies update(ControlAssignationPolicies entity);

	public ControlAssignationPolicies findById(Long id);

	/**
	 * Find all ControlAssignationPolicies entities with a specific property
	 * value.
	 * 
	 * @param propertyName
	 *            the name of the ControlAssignationPolicies property to query
	 * @param value
	 *            the property value to match
	 * @return List<ControlAssignationPolicies> found by query
	 */
	public List<ControlAssignationPolicies> findByProperty(String propertyName,
			Object value);

	/**
	 * Find all ControlAssignationPolicies entities.
	 * 
	 * @return List<ControlAssignationPolicies> all ControlAssignationPolicies
	 *         entities
	 */
	public List<ControlAssignationPolicies> findAll();
}