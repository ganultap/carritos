package geniar.siscar.persistence;

import geniar.siscar.model.DescriptionType;

import java.util.List;

/**
 * Interface for DescriptionTypeDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IDescriptionTypeDAO {
	/**
	 * Perform an initial save of a previously unsaved DescriptionType entity.
	 * All subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IDescriptionTypeDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            DescriptionType entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(DescriptionType entity);

	/**
	 * Delete a persistent DescriptionType entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IDescriptionTypeDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            DescriptionType entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(DescriptionType entity);

	/**
	 * Persist a previously saved DescriptionType entity and return it or a copy
	 * of it to the sender. A copy of the DescriptionType entity parameter is
	 * returned when the JPA persistence mechanism has not previously been
	 * tracking the updated entity. This operation must be performed within the
	 * a database transaction context for the entity's data to be permanently
	 * saved to the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IDescriptionTypeDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            DescriptionType entity to update
	 * @return DescriptionType the persisted DescriptionType entity instance,
	 *         may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public DescriptionType update(DescriptionType entity);

	public DescriptionType findById(Long id);

	/**
	 * Find all DescriptionType entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the DescriptionType property to query
	 * @param value
	 *            the property value to match
	 * @return List<DescriptionType> found by query
	 */
	public List<DescriptionType> findByProperty(String propertyName,
			Object value);

	public List<DescriptionType> findByDstValor(Object dstValor);

	/**
	 * Find all DescriptionType entities.
	 * 
	 * @return List<DescriptionType> all DescriptionType entities
	 */
	public List<DescriptionType> findAll();
}