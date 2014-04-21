package geniar.siscar.persistence;

import geniar.siscar.model.ParParametros;

import java.util.List;

/**
 * Interface for ParParametrosDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IParParametrosDAO {
	/**
	 * Perform an initial save of a previously unsaved ParParametros entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IParParametrosDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            ParParametros entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(ParParametros entity);

	/**
	 * Delete a persistent ParParametros entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IParParametrosDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            ParParametros entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(ParParametros entity);

	/**
	 * Persist a previously saved ParParametros entity and return it or a copy
	 * of it to the sender. A copy of the ParParametros entity parameter is
	 * returned when the JPA persistence mechanism has not previously been
	 * tracking the updated entity. This operation must be performed within the
	 * a database transaction context for the entity's data to be permanently
	 * saved to the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IParParametrosDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            ParParametros entity to update
	 * @returns ParParametros the persisted ParParametros entity instance, may
	 *          not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public ParParametros update(ParParametros entity);

	public ParParametros findById(Long id);

	/**
	 * Find all ParParametros entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the ParParametros property to query
	 * @param value
	 *            the property value to match
	 * @return List<ParParametros> found by query
	 */
	public List<ParParametros> findByProperty(String propertyName, Object value);

	public List<ParParametros> findByNombre(Object nombre);

	public List<ParParametros> findByDescripcion(Object descripcion);

	/**
	 * Find all ParParametros entities.
	 * 
	 * @return List<ParParametros> all ParParametros entities
	 */
	public List<ParParametros> findAll();
}