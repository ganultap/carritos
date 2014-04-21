package geniar.siscar.persistence;

import geniar.siscar.model.ChargeType;

import java.util.List;

/**
 * Interface for ChargeTypeDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IChargeTypeDAO {
	/**
	 * Perform an initial save of a previously unsaved ChargeType entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IChargeTypeDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            ChargeType entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(ChargeType entity);

	/**
	 * Delete a persistent ChargeType entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IChargeTypeDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            ChargeType entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(ChargeType entity);

	/**
	 * Persist a previously saved ChargeType entity and return it or a copy of
	 * it to the sender. A copy of the ChargeType entity parameter is returned
	 * when the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IChargeTypeDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            ChargeType entity to update
	 * @return ChargeType the persisted ChargeType entity instance, may not be
	 *         the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public ChargeType update(ChargeType entity);

	public ChargeType findById(Long id);

	/**
	 * Find all ChargeType entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the ChargeType property to query
	 * @param value
	 *            the property value to match
	 * @return List<ChargeType> found by query
	 */
	public List<ChargeType> findByProperty(String propertyName, Object value);

	public List<ChargeType> findByCgtNombre(Object cgtNombre);

	public List<ChargeType> findByCgtDescripcion(Object cgtDescripcion);

	/**
	 * Find all ChargeType entities.
	 * 
	 * @return List<ChargeType> all ChargeType entities
	 */
	public List<ChargeType> findAll();
}