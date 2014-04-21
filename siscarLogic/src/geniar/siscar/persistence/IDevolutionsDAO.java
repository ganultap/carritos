package geniar.siscar.persistence;

import geniar.siscar.model.Devolutions;

import java.util.List;

/**
 * Interface for DevolutionsDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IDevolutionsDAO {
	/**
	 * Perform an initial save of a previously unsaved Devolutions entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IDevolutionsDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Devolutions entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Devolutions entity);

	/**
	 * Delete a persistent Devolutions entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IDevolutionsDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Devolutions entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Devolutions entity);

	/**
	 * Persist a previously saved Devolutions entity and return it or a copy of
	 * it to the sender. A copy of the Devolutions entity parameter is returned
	 * when the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IDevolutionsDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Devolutions entity to update
	 * @returns Devolutions the persisted Devolutions entity instance, may not
	 *          be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Devolutions update(Devolutions entity);

	public Devolutions findById(Long id);

	/**
	 * Find all Devolutions entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Devolutions property to query
	 * @param value
	 *            the property value to match
	 * @return List<Devolutions> found by query
	 */
	public List<Devolutions> findByProperty(String propertyName, Object value);

	public List<Devolutions> findByDvlTipoAsignatario(Object dvlTipoAsignatario);

	public List<Devolutions> findByDvlKilometraje(Object dvlKilometraje);

	public List<Devolutions> findByDvlEstadoVeh(Object dvlEstadoVeh);

	public List<Devolutions> findByDvlDevTarifAsig(Object dvlDevTarifAsig);

	public List<Devolutions> findByDvlCobroTarifAsig(Object dvlCobroTarifAsig);

	/**
	 * Find all Devolutions entities.
	 * 
	 * @return List<Devolutions> all Devolutions entities
	 */
	public List<Devolutions> findAll();
}