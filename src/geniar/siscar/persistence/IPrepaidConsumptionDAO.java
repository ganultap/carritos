package geniar.siscar.persistence;


import geniar.siscar.model.PrepaidConsumption;

import java.util.List;

/**
 * Interface for PrepaidConsumptionDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IPrepaidConsumptionDAO {
	/**
	 * Perform an initial save of a previously unsaved PrepaidConsumption
	 * entity. All subsequent persist actions of this entity should use the
	 * #update() method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IPrepaidConsumptionDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            PrepaidConsumption entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(PrepaidConsumption entity);

	/**
	 * Delete a persistent PrepaidConsumption entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IPrepaidConsumptionDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            PrepaidConsumption entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(PrepaidConsumption entity);

	/**
	 * Persist a previously saved PrepaidConsumption entity and return it or a
	 * copy of it to the sender. A copy of the PrepaidConsumption entity
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
	 * entity = IPrepaidConsumptionDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            PrepaidConsumption entity to update
	 * @returns PrepaidConsumption the persisted PrepaidConsumption entity
	 *          instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public PrepaidConsumption update(PrepaidConsumption entity);

	public PrepaidConsumption findById(Long id);

	/**
	 * Find all PrepaidConsumption entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the PrepaidConsumption property to query
	 * @param value
	 *            the property value to match
	 * @return List<PrepaidConsumption>
	 *  found by query
	 * 
	 */
	public List<PrepaidConsumption> findByProperty(String propertyName,
			Object value);

	public List<PrepaidConsumption> findByPrcHora(Object prcHora);

	public List<PrepaidConsumption> findByPrcValorConsumo(Object prcValorConsumo);

	/**
	 * Find all PrepaidConsumption entities.
	 * 
	 * @return List<PrepaidConsumption>
	 *  all PrepaidConsumption entities
	 * 
	 */
	public List<PrepaidConsumption> findAll();
}