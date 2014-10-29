package geniar.siscar.persistence;

import geniar.siscar.model.TapestriesTypes;

import java.util.List;

/**
 * Interface for TapestriesTypesDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface ITapestriesTypesDAO {
	/**
	 * Perform an initial save of a previously unsaved TapestriesTypes entity.
	 * All subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ITapestriesTypesDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            TapestriesTypes entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(TapestriesTypes entity);

	/**
	 * Delete a persistent TapestriesTypes entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ITapestriesTypesDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            TapestriesTypes entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(TapestriesTypes entity);

	/**
	 * Persist a previously saved TapestriesTypes entity and return it or a copy
	 * of it to the sender. A copy of the TapestriesTypes entity parameter is
	 * returned when the JPA persistence mechanism has not previously been
	 * tracking the updated entity. This operation must be performed within the
	 * a database transaction context for the entity's data to be permanently
	 * saved to the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = ITapestriesTypesDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            TapestriesTypes entity to update
	 * @returns TapestriesTypes the persisted TapestriesTypes entity instance,
	 *          may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public TapestriesTypes update(TapestriesTypes entity);

	public TapestriesTypes findById(Long id);

	/**
	 * Find all TapestriesTypes entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the TapestriesTypes property to query
	 * @param value
	 *            the property value to match
	 * @return List<TapestriesTypes> found by query
	 */
	public List<TapestriesTypes> findByProperty(String propertyName, Object value);

	public List<TapestriesTypes> findByTptpcNombre(Object tptpcNombre);

	/**
	 * Find all TapestriesTypes entities.
	 * 
	 * @return List<TapestriesTypes> all TapestriesTypes entities
	 */
	public List<TapestriesTypes> findAll();
}