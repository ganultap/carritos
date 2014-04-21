package geniar.siscar.persistence;

import geniar.siscar.model.NoveltyTypes;

import java.util.List;

/**
 * Interface for NoveltyTypesDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface INoveltyTypesDAO {
	/**
	 * Perform an initial save of a previously unsaved NoveltyTypes entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * INoveltyTypesDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            NoveltyTypes entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(NoveltyTypes entity);

	/**
	 * Delete a persistent NoveltyTypes entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * INoveltyTypesDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            NoveltyTypes entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(NoveltyTypes entity);

	/**
	 * Persist a previously saved NoveltyTypes entity and return it or a copy of
	 * it to the sender. A copy of the NoveltyTypes entity parameter is returned
	 * when the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = INoveltyTypesDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            NoveltyTypes entity to update
	 * @returns NoveltyTypes the persisted NoveltyTypes entity instance, may not
	 *          be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public NoveltyTypes update(NoveltyTypes entity);

	public NoveltyTypes findById(Long id);

	/**
	 * Find all NoveltyTypes entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the NoveltyTypes property to query
	 * @param value
	 *            the property value to match
	 * @return List<NoveltyTypes> found by query
	 */
	public List<NoveltyTypes> findByProperty(String propertyName, Object value);

	public List<NoveltyTypes> findByNtNombre(Object ntNombre);

	/**
	 * Find all NoveltyTypes entities.
	 * 
	 * @return List<NoveltyTypes> all NoveltyTypes entities
	 */
	public List<NoveltyTypes> findAll();
}