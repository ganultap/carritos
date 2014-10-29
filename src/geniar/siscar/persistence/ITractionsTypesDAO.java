package geniar.siscar.persistence;

import geniar.siscar.model.TractionsTypes;

import java.util.List;

/**
 * Interface for TractionsTypesDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface ITractionsTypesDAO {
	/**
	 * Perform an initial save of a previously unsaved TractionsTypes entity.
	 * All subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ITractionsTypesDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            TractionsTypes entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(TractionsTypes entity);

	/**
	 * Delete a persistent TractionsTypes entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ITractionsTypesDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            TractionsTypes entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(TractionsTypes entity);

	/**
	 * Persist a previously saved TractionsTypes entity and return it or a copy
	 * of it to the sender. A copy of the TractionsTypes entity parameter is
	 * returned when the JPA persistence mechanism has not previously been
	 * tracking the updated entity. This operation must be performed within the
	 * a database transaction context for the entity's data to be permanently
	 * saved to the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = ITractionsTypesDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            TractionsTypes entity to update
	 * @returns TractionsTypes the persisted TractionsTypes entity instance, may
	 *          not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public TractionsTypes update(TractionsTypes entity);

	public TractionsTypes findById(Long id);

	/**
	 * Find all TractionsTypes entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the TractionsTypes property to query
	 * @param value
	 *            the property value to match
	 * @return List<TractionsTypes> found by query
	 */
	public List<TractionsTypes> findByProperty(String propertyName, Object value);

	public List<TractionsTypes> findByTctNombre(Object tctNombre);

	/**
	 * Find all TractionsTypes entities.
	 * 
	 * @return List<TractionsTypes> all TractionsTypes entities
	 */
	public List<TractionsTypes> findAll();
}