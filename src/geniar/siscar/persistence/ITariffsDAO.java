package geniar.siscar.persistence;

import geniar.siscar.model.Tariffs;

import java.util.List;

/**
 * Interface for TariffsDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface ITariffsDAO {
	/**
	 * Perform an initial save of a previously unsaved Tariffs entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ITariffsDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Tariffs entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Tariffs entity);

	/**
	 * Delete a persistent Tariffs entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ITariffsDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Tariffs entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Tariffs entity);

	/**
	 * Persist a previously saved Tariffs entity and return it or a copy of it
	 * to the sender. A copy of the Tariffs entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = ITariffsDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Tariffs entity to update
	 * @returns Tariffs the persisted Tariffs entity instance, may not be the
	 *          same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Tariffs update(Tariffs entity);

	public Tariffs findById(Long id);

	/**
	 * Find all Tariffs entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Tariffs property to query
	 * @param value
	 *            the property value to match
	 * @return List<Tariffs> found by query
	 */
	public List<Tariffs> findByProperty(String propertyName, Object value);

	public List<Tariffs> findByTrfValor(Object trfValor);

	public List<Tariffs> findByTrfKmIncluido(Object trfKmIncluido);

	public List<Tariffs> findByTrfKmValorAdicional(Object trfKmValorAdicional);

	/**
	 * Find all Tariffs entities.
	 * 
	 * @return List<Tariffs> all Tariffs entities
	 */
	public List<Tariffs> findAll();
}