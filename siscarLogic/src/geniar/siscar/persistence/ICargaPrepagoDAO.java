package geniar.siscar.persistence;

import geniar.siscar.model.CargaPrepago;

import java.util.List;

/**
 * Interface for CargaPrepagoDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface ICargaPrepagoDAO {
	/**
	 * Perform an initial save of a previously unsaved CargaPrepago entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ICargaPrepagoDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            CargaPrepago entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(CargaPrepago entity);

	/**
	 * Delete a persistent CargaPrepago entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ICargaPrepagoDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            CargaPrepago entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(CargaPrepago entity);

	/**
	 * Persist a previously saved CargaPrepago entity and return it or a copy of
	 * it to the sender. A copy of the CargaPrepago entity parameter is returned
	 * when the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = ICargaPrepagoDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            CargaPrepago entity to update
	 * @return CargaPrepago the persisted CargaPrepago entity instance, may not
	 *         be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public CargaPrepago update(CargaPrepago entity);

	public CargaPrepago findById(Long id);

	/**
	 * Find all CargaPrepago entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the CargaPrepago property to query
	 * @param value
	 *            the property value to match
	 * @return List<CargaPrepago> found by query
	 */
	public List<CargaPrepago> findByProperty(String propertyName, Object value);

	public List<CargaPrepago> findByPlaca(Object placa);

	public List<CargaPrepago> findByConsumoPromedio(Object consumoPromedio);

	public List<CargaPrepago> findByCentroCosto(Object centroCosto);

	public List<CargaPrepago> findByKmAnual(Object kmAnual);

	public List<CargaPrepago> findByGalonesAno(Object galonesAno);

	public List<CargaPrepago> findByTipoCombustible(Object tipoCombustible);

	public List<CargaPrepago> findByValorPrepago(Object valorPrepago);

	public List<CargaPrepago> findByTipoCargo(Object tipoCargo);

	/**
	 * Find all CargaPrepago entities.
	 * 
	 * @return List<CargaPrepago> all CargaPrepago entities
	 */
	public List<CargaPrepago> findAll();
}