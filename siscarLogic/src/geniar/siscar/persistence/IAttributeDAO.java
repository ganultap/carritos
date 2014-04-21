package geniar.siscar.persistence;

import geniar.siscar.model.Attribute;

import java.util.List;

/**
 * Interface for AttributeDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IAttributeDAO {
	/**
	 * Perform an initial save of a previously unsaved Attribute entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IAttributeDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Attribute entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Attribute entity);

	/**
	 * Delete a persistent Attribute entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IAttributeDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Attribute entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Attribute entity);

	/**
	 * Persist a previously saved Attribute entity and return it or a copy of it
	 * to the sender. A copy of the Attribute entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IAttributeDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Attribute entity to update
	 * @return Attribute the persisted Attribute entity instance, may not be the
	 *         same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Attribute update(Attribute entity);

	public Attribute findById(Long id);

	/**
	 * Find all Attribute entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Attribute property to query
	 * @param value
	 *            the property value to match
	 * @return List<Attribute> found by query
	 */
	public List<Attribute> findByProperty(String propertyName, Object value);

	public List<Attribute> findByAtbValor(Object atbValor);

	/**
	 * Find all Attribute entities.
	 * 
	 * @return List<Attribute> all Attribute entities
	 */
	public List<Attribute> findAll();
}