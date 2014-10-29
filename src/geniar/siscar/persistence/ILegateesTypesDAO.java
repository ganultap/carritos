package geniar.siscar.persistence;
// default package

import geniar.siscar.model.LegateesTypes;

import java.util.List;

/**
 * Interface for LegateesTypesDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface ILegateesTypesDAO {
	/**
	 * Perform an initial save of a previously unsaved LegateesTypes entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ILegateesTypesDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            LegateesTypes entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(LegateesTypes entity);

	/**
	 * Delete a persistent LegateesTypes entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ILegateesTypesDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            LegateesTypes entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(LegateesTypes entity);

	/**
	 * Persist a previously saved LegateesTypes entity and return it or a copy
	 * of it to the sender. A copy of the LegateesTypes entity parameter is
	 * returned when the JPA persistence mechanism has not previously been
	 * tracking the updated entity. This operation must be performed within the
	 * a database transaction context for the entity's data to be permanently
	 * saved to the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = ILegateesTypesDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            LegateesTypes entity to update
	 * @return LegateesTypes the persisted LegateesTypes entity instance, may
	 *         not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public LegateesTypes update(LegateesTypes entity);

	public LegateesTypes findById(Long id);

	/**
	 * Find all LegateesTypes entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the LegateesTypes property to query
	 * @param value
	 *            the property value to match
	 * @return List<LegateesTypes> found by query
	 */
	public List<LegateesTypes> findByProperty(String propertyName, Object value);

	public List<LegateesTypes> findByLgtNombre(Object lgtNombre);

	/**
	 * Find all LegateesTypes entities.
	 * 
	 * @return List<LegateesTypes> all LegateesTypes entities
	 */
	public List<LegateesTypes> findAll();
}