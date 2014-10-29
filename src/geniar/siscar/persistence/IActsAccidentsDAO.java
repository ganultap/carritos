package geniar.siscar.persistence;

import geniar.siscar.model.ActsAccidents;

import java.util.List;

/**
 * Interface for ActsAccidentsDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IActsAccidentsDAO {
	/**
	 * Perform an initial save of a previously unsaved ActsAccidents entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IActsAccidentsDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            ActsAccidents entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(ActsAccidents entity);

	/**
	 * Delete a persistent ActsAccidents entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IActsAccidentsDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            ActsAccidents entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(ActsAccidents entity);

	/**
	 * Persist a previously saved ActsAccidents entity and return it or a copy
	 * of it to the sender. A copy of the ActsAccidents entity parameter is
	 * returned when the JPA persistence mechanism has not previously been
	 * tracking the updated entity. This operation must be performed within the
	 * a database transaction context for the entity's data to be permanently
	 * saved to the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IActsAccidentsDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            ActsAccidents entity to update
	 * @return ActsAccidents the persisted ActsAccidents entity instance, may
	 *         not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public ActsAccidents update(ActsAccidents entity);

	public ActsAccidents findById(Long id);

	/**
	 * Find all ActsAccidents entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the ActsAccidents property to query
	 * @param value
	 *            the property value to match
	 * @return List<ActsAccidents> found by query
	 */
	public List<ActsAccidents> findByProperty(String propertyName, Object value);

	/**
	 * Find all ActsAccidents entities.
	 * 
	 * @return List<ActsAccidents> all ActsAccidents entities
	 */
	public List<ActsAccidents> findAll();
}