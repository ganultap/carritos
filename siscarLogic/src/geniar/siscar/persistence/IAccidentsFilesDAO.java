package geniar.siscar.persistence;

import geniar.siscar.model.AccidentsFiles;

import java.util.List;

/**
 * Interface for AccidentsFilesDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IAccidentsFilesDAO {
	/**
	 * Perform an initial save of a previously unsaved AccidentsFiles entity.
	 * All subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IAccidentsFilesDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            AccidentsFiles entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(AccidentsFiles entity);

	/**
	 * Delete a persistent AccidentsFiles entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IAccidentsFilesDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            AccidentsFiles entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(AccidentsFiles entity);

	/**
	 * Persist a previously saved AccidentsFiles entity and return it or a copy
	 * of it to the sender. A copy of the AccidentsFiles entity parameter is
	 * returned when the JPA persistence mechanism has not previously been
	 * tracking the updated entity. This operation must be performed within the
	 * a database transaction context for the entity's data to be permanently
	 * saved to the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IAccidentsFilesDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            AccidentsFiles entity to update
	 * @returns AccidentsFiles the persisted AccidentsFiles entity instance, may
	 *          not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public AccidentsFiles update(AccidentsFiles entity);

	public AccidentsFiles findById(Long id);

	/**
	 * Find all AccidentsFiles entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the AccidentsFiles property to query
	 * @param value
	 *            the property value to match
	 * @return List<AccidentsFiles> found by query
	 */
	public List<AccidentsFiles> findByProperty(String propertyName, Object value);

	public List<AccidentsFiles> findByAcfRuta(Object acfRuta);

	public List<AccidentsFiles> findByAcfDescripcion(Object acfDescripcion);

	public List<AccidentsFiles> findByAcfNombre(Object acfNombre);

	/**
	 * Find all AccidentsFiles entities.
	 * 
	 * @return List<AccidentsFiles> all AccidentsFiles entities
	 */
	public List<AccidentsFiles> findAll();
}