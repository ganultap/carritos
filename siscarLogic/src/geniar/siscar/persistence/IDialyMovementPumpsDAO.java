package geniar.siscar.persistence;

import geniar.siscar.model.DialyMovementPumps;

import java.util.List;

/**
 * Interface for DialyMovementPumpsDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IDialyMovementPumpsDAO {
	/**
	 * Perform an initial save of a previously unsaved DialyMovementPumps
	 * entity. All subsequent persist actions of this entity should use the
	 * #update() method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IDialyMovementPumpsDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            DialyMovementPumps entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(DialyMovementPumps entity);

	/**
	 * Delete a persistent DialyMovementPumps entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IDialyMovementPumpsDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            DialyMovementPumps entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(DialyMovementPumps entity);

	/**
	 * Persist a previously saved DialyMovementPumps entity and return it or a
	 * copy of it to the sender. A copy of the DialyMovementPumps entity
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
	 * entity = IDialyMovementPumpsDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            DialyMovementPumps entity to update
	 * @returns DialyMovementPumps the persisted DialyMovementPumps entity
	 *          instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public DialyMovementPumps update(DialyMovementPumps entity);

	public DialyMovementPumps findById(Long id);

	/**
	 * Find all DialyMovementPumps entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the DialyMovementPumps property to query
	 * @param value
	 *            the property value to match
	 * @return List<DialyMovementPumps> found by query
	 */
	public List<DialyMovementPumps> findByProperty(String propertyName,
			Object value);

	public List<DialyMovementPumps> findByDmpLectura(Object dmpLectura);

	public List<DialyMovementPumps> findByDmpRecibosDia(Object dmpRecibosDia);

	public List<DialyMovementPumps> findByDmpRecibosNoche(Object dmpRecibosNoche);

	/**
	 * Find all DialyMovementPumps entities.
	 * 
	 * @return List<DialyMovementPumps> all DialyMovementPumps entities
	 */
	public List<DialyMovementPumps> findAll();
}