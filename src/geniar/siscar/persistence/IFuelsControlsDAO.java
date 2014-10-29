package geniar.siscar.persistence;

import geniar.siscar.model.FuelsControls;

import java.util.List;

/**
 * Interface for FuelsControlsDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IFuelsControlsDAO {
	/**
	 * Perform an initial save of a previously unsaved FuelsControls entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IFuelsControlsDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            FuelsControls entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(FuelsControls entity);

	/**
	 * Delete a persistent FuelsControls entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IFuelsControlsDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            FuelsControls entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(FuelsControls entity);

	/**
	 * Persist a previously saved FuelsControls entity and return it or a copy
	 * of it to the sender. A copy of the FuelsControls entity parameter is
	 * returned when the JPA persistence mechanism has not previously been
	 * tracking the updated entity. This operation must be performed within the
	 * a database transaction context for the entity's data to be permanently
	 * saved to the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IFuelsControlsDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            FuelsControls entity to update
	 * @returns FuelsControls the persisted FuelsControls entity instance, may
	 *          not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public FuelsControls update(FuelsControls entity);

	public FuelsControls findById(Long id);

	/**
	 * Find all FuelsControls entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the FuelsControls property to query
	 * @param value
	 *            the property value to match
	 * @return List<FuelsControls> found by query
	 */
	public List<FuelsControls> findByProperty(String propertyName, Object value);

	public List<FuelsControls> findByFucNumeroGalones(Object fucNumeroGalones);

	public List<FuelsControls> findByFucNumeroCarne(Object fucNumeroCarne);

	public List<FuelsControls> findByFucNombre(Object fucNombre);

	public List<FuelsControls> findByFucPlaca(Object fucPlaca);

	public List<FuelsControls> findByFucNumeroTl(Object fucNumeroTl);

	/**
	 * Find all FuelsControls entities.
	 * 
	 * @return List<FuelsControls> all FuelsControls entities
	 */
	public List<FuelsControls> findAll();
}