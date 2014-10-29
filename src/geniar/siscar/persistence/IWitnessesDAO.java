package geniar.siscar.persistence;

import geniar.siscar.model.Witnesses;

import java.util.List;

/**
 * Interface for WitnessesDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IWitnessesDAO {
	/**
	 * Perform an initial save of a previously unsaved Witnesses entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IWitnessesDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Witnesses entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Witnesses entity);

	/**
	 * Delete a persistent Witnesses entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IWitnessesDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Witnesses entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Witnesses entity);

	/**
	 * Persist a previously saved Witnesses entity and return it or a copy of it
	 * to the sender. A copy of the Witnesses entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IWitnessesDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Witnesses entity to update
	 * @returns Witnesses the persisted Witnesses entity instance, may not be
	 *          the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Witnesses update(Witnesses entity);

	public Witnesses findById(Long id);

	/**
	 * Find all Witnesses entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Witnesses property to query
	 * @param value
	 *            the property value to match
	 * @return List<Witnesses> found by query
	 */
	public List<Witnesses> findByProperty(String propertyName, Object value);

	public List<Witnesses> findByWtnIdentificacion(Object wtnIdentificacion);

	public List<Witnesses> findByWtnNombre(Object wtnNombre);

	public List<Witnesses> findByWtnDireccion(Object wtnDireccion);

	public List<Witnesses> findByWtnTelefono(Object wtnTelefono);

	/**
	 * Find all Witnesses entities.
	 * 
	 * @return List<Witnesses> all Witnesses entities
	 */
	public List<Witnesses> findAll();
}