package geniar.siscar.persistence;

import geniar.siscar.model.ParValoresparametros;

import java.util.List;

/**
 * Interface for ParValoresparametrosDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IParValoresparametrosDAO {
	/**
	 * Perform an initial save of a previously unsaved ParValoresparametros
	 * entity. All subsequent persist actions of this entity should use the
	 * #update() method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IParValoresparametrosDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            ParValoresparametros entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(ParValoresparametros entity);

	/**
	 * Delete a persistent ParValoresparametros entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IParValoresparametrosDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            ParValoresparametros entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(ParValoresparametros entity);

	/**
	 * Persist a previously saved ParValoresparametros entity and return it or a
	 * copy of it to the sender. A copy of the ParValoresparametros entity
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
	 * entity = IParValoresparametrosDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            ParValoresparametros entity to update
	 * @returns ParValoresparametros the persisted ParValoresparametros entity
	 *          instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public ParValoresparametros update(ParValoresparametros entity);

	public ParValoresparametros findById(Long id);

	/**
	 * Find all ParValoresparametros entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the ParValoresparametros property to query
	 * @param value
	 *            the property value to match
	 * @return List<ParValoresparametros> found by query
	 */
	public List<ParValoresparametros> findByProperty(String propertyName, Object value);

	public List<ParValoresparametros> findByValorinicial(Object valorinicial);

	public List<ParValoresparametros> findByValorfinal(Object valorfinal);

	/**
	 * Find all ParValoresparametros entities.
	 * 
	 * @return List<ParValoresparametros> all ParValoresparametros entities
	 */
	public List<ParValoresparametros> findAll();
}