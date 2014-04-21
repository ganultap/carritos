package geniar.siscar.persistence;

import geniar.siscar.model.SupplyingCatalogs;

import java.util.List;

/**
 * Interface for SupplyingCatalogsDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface ISupplyingCatalogsDAO {
	/**
	 * Perform an initial save of a previously unsaved SupplyingCatalogs entity.
	 * All subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ISupplyingCatalogsDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            SupplyingCatalogs entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(SupplyingCatalogs entity);

	/**
	 * Delete a persistent SupplyingCatalogs entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ISupplyingCatalogsDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            SupplyingCatalogs entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(SupplyingCatalogs entity);

	/**
	 * Persist a previously saved SupplyingCatalogs entity and return it or a
	 * copy of it to the sender. A copy of the SupplyingCatalogs entity
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
	 * entity = ISupplyingCatalogsDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            SupplyingCatalogs entity to update
	 * @returns SupplyingCatalogs the persisted SupplyingCatalogs entity
	 *          instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public SupplyingCatalogs update(SupplyingCatalogs entity);

	public SupplyingCatalogs findById(Long id);

	/**
	 * Find all SupplyingCatalogs entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the SupplyingCatalogs property to query
	 * @param value
	 *            the property value to match
	 * @return List<SupplyingCatalogs> found by query
	 */
	public List<SupplyingCatalogs> findByProperty(String propertyName, Object value);

	public List<SupplyingCatalogs> findBySupNumCatalogo(Object supNumCatalogo);

	/**
	 * Find all SupplyingCatalogs entities.
	 * 
	 * @return List<SupplyingCatalogs> all SupplyingCatalogs entities
	 */
	public List<SupplyingCatalogs> findAll();
}