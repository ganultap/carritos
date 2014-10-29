package geniar.siscar.persistence;

import geniar.siscar.model.PoliciesTypes;

import java.util.List;

/**
 * Interface for PoliciesTypesDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IPoliciesTypesDAO {
	/**
	 * Perform an initial save of a previously unsaved PoliciesTypes entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IPoliciesTypesDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            PoliciesTypes entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(PoliciesTypes entity);

	/**
	 * Delete a persistent PoliciesTypes entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IPoliciesTypesDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            PoliciesTypes entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(PoliciesTypes entity);

	/**
	 * Persist a previously saved PoliciesTypes entity and return it or a copy
	 * of it to the sender. A copy of the PoliciesTypes entity parameter is
	 * returned when the JPA persistence mechanism has not previously been
	 * tracking the updated entity. This operation must be performed within the
	 * a database transaction context for the entity's data to be permanently
	 * saved to the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IPoliciesTypesDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            PoliciesTypes entity to update
	 * @returns PoliciesTypes the persisted PoliciesTypes entity instance, may
	 *          not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public PoliciesTypes update(PoliciesTypes entity);

	public PoliciesTypes findById(Long id);

	/**
	 * Find all PoliciesTypes entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the PoliciesTypes property to query
	 * @param value
	 *            the property value to match
	 * @return List<PoliciesTypes> found by query
	 */
	public List<PoliciesTypes> findByProperty(String propertyName, Object value);

	public List<PoliciesTypes> findByPltNombre(Object pltNombre);

	/**
	 * Find all PoliciesTypes entities.
	 * 
	 * @return List<PoliciesTypes> all PoliciesTypes entities
	 */
	public List<PoliciesTypes> findAll();
}