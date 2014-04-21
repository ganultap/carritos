package geniar.siscar.persistence;

import geniar.siscar.model.CostsCentersNewness;

import java.util.List;

/**
 * Interface for CostsCentersNewnessDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface ICostsCentersNewnessDAO {
	/**
	 * Perform an initial save of a previously unsaved CostsCentersNewness
	 * entity. All subsequent persist actions of this entity should use the
	 * #update() method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ICostsCentersNewnessDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            CostsCentersNewness entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(CostsCentersNewness entity);

	/**
	 * Delete a persistent CostsCentersNewness entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ICostsCentersNewnessDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            CostsCentersNewness entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(CostsCentersNewness entity);

	/**
	 * Persist a previously saved CostsCentersNewness entity and return it or a
	 * copy of it to the sender. A copy of the CostsCentersNewness entity
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
	 * entity = ICostsCentersNewnessDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            CostsCentersNewness entity to update
	 * @returns CostsCentersNewness the persisted CostsCentersNewness entity
	 *          instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public CostsCentersNewness update(CostsCentersNewness entity);

	public CostsCentersNewness findById(Long id);

	/**
	 * Find all CostsCentersNewness entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the CostsCentersNewness property to query
	 * @param value
	 *            the property value to match
	 * @return List<CostsCentersNewness> found by query
	 */
	public List<CostsCentersNewness> findByProperty(String propertyName, Object value);

	public List<CostsCentersNewness> findByCcnDescripcion(Object ccnDescripcion);

	public List<CostsCentersNewness> findByUsrLogin(Object usrLogin);

	public List<CostsCentersNewness> findByCcrPorcentaje(Object ccrPorcentaje);

	public List<CostsCentersNewness> findByCcrValor(Object ccrValor);

	/**
	 * Find all CostsCentersNewness entities.
	 * 
	 * @return List<CostsCentersNewness> all CostsCentersNewness entities
	 */
	public List<CostsCentersNewness> findAll();
}