package geniar.siscar.persistence;

import geniar.siscar.model.Pumps;

import java.util.List;

/**
 * Interface for PumpsDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IPumpsDAO {
	/**
	 * Perform an initial save of a previously unsaved Pumps entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IPumpsDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Pumps entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Pumps entity);

	/**
	 * Delete a persistent Pumps entity. This operation must be performed within
	 * the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IPumpsDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Pumps entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Pumps entity);

	/**
	 * Persist a previously saved Pumps entity and return it or a copy of it to
	 * the sender. A copy of the Pumps entity parameter is returned when the JPA
	 * persistence mechanism has not previously been tracking the updated
	 * entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IPumpsDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Pumps entity to update
	 * @returns Pumps the persisted Pumps entity instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Pumps update(Pumps entity);

	public Pumps findById(Long id);

	/**
	 * Find all Pumps entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Pumps property to query
	 * @param value
	 *            the property value to match
	 * @return List<Pumps> found by query
	 */
	public List<Pumps> findByProperty(String propertyName, Object value);

	public List<Pumps> findByPumNombre(Object pumNombre);

	public List<Pumps> findByPumVecesUtilizado(Object pumVecesUtilizado);

	/**
	 * Find all Pumps entities.
	 * 
	 * @return List<Pumps> all Pumps entities
	 */
	public List<Pumps> findAll();
}