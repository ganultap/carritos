package geniar.siscar.persistence;

import geniar.siscar.model.PlainFileParameter;

import java.util.List;

/**
 * Interface for PlainFileParameterDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IPlainFileParameterDAO {
	/**
	 * Perform an initial save of a previously unsaved PlainFileParameter
	 * entity. All subsequent persist actions of this entity should use the
	 * #update() method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IPlainFileParameterDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            PlainFileParameter entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(PlainFileParameter entity);

	/**
	 * Delete a persistent PlainFileParameter entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IPlainFileParameterDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            PlainFileParameter entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(PlainFileParameter entity);

	/**
	 * Persist a previously saved PlainFileParameter entity and return it or a
	 * copy of it to the sender. A copy of the PlainFileParameter entity
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
	 * entity = IPlainFileParameterDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            PlainFileParameter entity to update
	 * @returns PlainFileParameter the persisted PlainFileParameter entity
	 *          instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public PlainFileParameter update(PlainFileParameter entity);

	public PlainFileParameter findById(Long id);

	/**
	 * Find all PlainFileParameter entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the PlainFileParameter property to query
	 * @param value
	 *            the property value to match
	 * @return List<PlainFileParameter> found by query
	 */
	public List<PlainFileParameter> findByProperty(String propertyName, Object value);

	public List<PlainFileParameter> findByPfpConceptonomina(Object pfpConceptonomina);

	public List<PlainFileParameter> findByPfpDescripcion(Object pfpDescripcion);

	/**
	 * Find all PlainFileParameter entities.
	 * 
	 * @return List<PlainFileParameter> all PlainFileParameter entities
	 */
	public List<PlainFileParameter> findAll();
}