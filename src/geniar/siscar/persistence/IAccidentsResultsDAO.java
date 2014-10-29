package geniar.siscar.persistence;

import geniar.siscar.model.AccidentsResults;

import java.util.List;

/**
 * Interface for AccidentsResultsDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IAccidentsResultsDAO {
	/**
	 * Perform an initial save of a previously unsaved AccidentsResults entity.
	 * All subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IAccidentsResultsDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            AccidentsResults entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(AccidentsResults entity);

	/**
	 * Delete a persistent AccidentsResults entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IAccidentsResultsDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            AccidentsResults entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(AccidentsResults entity);

	/**
	 * Persist a previously saved AccidentsResults entity and return it or a
	 * copy of it to the sender. A copy of the AccidentsResults entity parameter
	 * is returned when the JPA persistence mechanism has not previously been
	 * tracking the updated entity. This operation must be performed within the
	 * a database transaction context for the entity's data to be permanently
	 * saved to the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IAccidentsResultsDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            AccidentsResults entity to update
	 * @return AccidentsResults the persisted AccidentsResults entity instance,
	 *         may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public AccidentsResults update(AccidentsResults entity);

	public AccidentsResults findById(Long id);

	/**
	 * Find all AccidentsResults entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the AccidentsResults property to query
	 * @param value
	 *            the property value to match
	 * @return List<AccidentsResults> found by query
	 */
	public List<AccidentsResults> findByProperty(String propertyName,
			Object value);

	public List<AccidentsResults> findByAclNombre(Object aclNombre);

	/**
	 * Find all AccidentsResults entities.
	 * 
	 * @return List<AccidentsResults> all AccidentsResults entities
	 */
	public List<AccidentsResults> findAll();
}