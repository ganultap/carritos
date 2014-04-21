package geniar.siscar.persistence;

import geniar.siscar.model.LegateesNewness;

import java.util.List;

/**
 * Interface for LegateesNewnessDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface ILegateesNewnessDAO {
	/**
	 * Perform an initial save of a previously unsaved LegateesNewness entity.
	 * All subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ILegateesNewnessDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            LegateesNewness entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(LegateesNewness entity);

	/**
	 * Delete a persistent LegateesNewness entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ILegateesNewnessDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            LegateesNewness entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(LegateesNewness entity);

	/**
	 * Persist a previously saved LegateesNewness entity and return it or a copy
	 * of it to the sender. A copy of the LegateesNewness entity parameter is
	 * returned when the JPA persistence mechanism has not previously been
	 * tracking the updated entity. This operation must be performed within the
	 * a database transaction context for the entity's data to be permanently
	 * saved to the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = ILegateesNewnessDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            LegateesNewness entity to update
	 * @returns LegateesNewness the persisted LegateesNewness entity instance,
	 *          may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public LegateesNewness update(LegateesNewness entity);

	public LegateesNewness findById(Long id);

	/**
	 * Find all LegateesNewness entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the LegateesNewness property to query
	 * @param value
	 *            the property value to match
	 * @return List<LegateesNewness> found by query
	 */
	public List<LegateesNewness> findByProperty(String propertyName, Object value);

	public List<LegateesNewness> findByLgnDescripcion(Object lgnDescripcion);

	public List<LegateesNewness> findByUsrLogin(Object usrLogin);

	public List<LegateesNewness> findByLgnCarneAsignatario(Object lgnCarneAsignatario);

	public List<LegateesNewness> findByLgnCarneAsistente(Object lgnCarneAsistente);

	/**
	 * Find all LegateesNewness entities.
	 * 
	 * @return List<LegateesNewness> all LegateesNewness entities
	 */
	public List<LegateesNewness> findAll();
}