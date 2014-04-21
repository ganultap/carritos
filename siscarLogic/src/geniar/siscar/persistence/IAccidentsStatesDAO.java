package geniar.siscar.persistence;

import geniar.siscar.model.AccidentsStates;

import java.util.List;

/**
 * Interface for AccidentsStatesDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IAccidentsStatesDAO {
	/**
	 * Perform an initial save of a previously unsaved AccidentsStates entity.
	 * All subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IAccidentsStatesDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            AccidentsStates entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(AccidentsStates entity);

	/**
	 * Delete a persistent AccidentsStates entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IAccidentsStatesDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            AccidentsStates entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(AccidentsStates entity);

	/**
	 * Persist a previously saved AccidentsStates entity and return it or a copy
	 * of it to the sender. A copy of the AccidentsStates entity parameter is
	 * returned when the JPA persistence mechanism has not previously been
	 * tracking the updated entity. This operation must be performed within the
	 * a database transaction context for the entity's data to be permanently
	 * saved to the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IAccidentsStatesDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            AccidentsStates entity to update
	 * @returns AccidentsStates the persisted AccidentsStates entity instance,
	 *          may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public AccidentsStates update(AccidentsStates entity);

	public AccidentsStates findById(Long id);

	/**
	 * Find all AccidentsStates entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the AccidentsStates property to query
	 * @param value
	 *            the property value to match
	 * @return List<AccidentsStates> found by query
	 */
	public List<AccidentsStates> findByProperty(String propertyName, Object value);

	public List<AccidentsStates> findByAcsNombre(Object acsNombre);

	/**
	 * Find all AccidentsStates entities.
	 * 
	 * @return List<AccidentsStates> all AccidentsStates entities
	 */
	public List<AccidentsStates> findAll();
}