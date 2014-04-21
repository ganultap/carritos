package geniar.siscar.persistence;

import geniar.siscar.model.TariffsTypes;

import java.util.List;

/**
 * Interface for TariffsTypesDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface ITariffsTypesDAO {
	/**
	 * Perform an initial save of a previously unsaved TariffsTypes entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ITariffsTypesDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            TariffsTypes entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(TariffsTypes entity);

	/**
	 * Delete a persistent TariffsTypes entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ITariffsTypesDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            TariffsTypes entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(TariffsTypes entity);

	/**
	 * Persist a previously saved TariffsTypes entity and return it or a copy of
	 * it to the sender. A copy of the TariffsTypes entity parameter is returned
	 * when the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = ITariffsTypesDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            TariffsTypes entity to update
	 * @returns TariffsTypes the persisted TariffsTypes entity instance, may not
	 *          be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public TariffsTypes update(TariffsTypes entity);

	public TariffsTypes findById(Long id);

	/**
	 * Find all TariffsTypes entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the TariffsTypes property to query
	 * @param value
	 *            the property value to match
	 * @return List<TariffsTypes> found by query
	 */
	public List<TariffsTypes> findByProperty(String propertyName, Object value);

	public List<TariffsTypes> findByTrtNombre(Object trtNombre);

	/**
	 * Find all TariffsTypes entities.
	 * 
	 * @return List<TariffsTypes> all TariffsTypes entities
	 */
	public List<TariffsTypes> findAll();
}