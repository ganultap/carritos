package geniar.siscar.persistence;

import geniar.siscar.model.OptionsRolls;
import geniar.siscar.model.OptionsRollsId;

import java.util.List;

/**
 * Interface for OptionsRollsDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IOptionsRollsDAO {
	/**
	 * Perform an initial save of a previously unsaved OptionsRolls entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IOptionsRollsDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            OptionsRolls entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(OptionsRolls entity);

	/**
	 * Delete a persistent OptionsRolls entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IOptionsRollsDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            OptionsRolls entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(OptionsRolls entity);

	/**
	 * Persist a previously saved OptionsRolls entity and return it or a copy of
	 * it to the sender. A copy of the OptionsRolls entity parameter is returned
	 * when the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IOptionsRollsDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            OptionsRolls entity to update
	 * @returns OptionsRolls the persisted OptionsRolls entity instance, may not
	 *          be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public OptionsRolls update(OptionsRolls entity);

	public OptionsRolls findById(OptionsRollsId id);

	/**
	 * Find all OptionsRolls entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the OptionsRolls property to query
	 * @param value
	 *            the property value to match
	 * @return List<OptionsRolls> found by query
	 */
	public List<OptionsRolls> findByProperty(String propertyName, Object value);

	/**
	 * Find all OptionsRolls entities.
	 * 
	 * @return List<OptionsRolls> all OptionsRolls entities
	 */
	public List<OptionsRolls> findAll();
}