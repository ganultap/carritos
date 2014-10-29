package geniar.siscar.persistence;

import geniar.siscar.model.TariffsLegateeTypes;
import geniar.siscar.model.TariffsLegateeTypesId;

import java.util.List;

/**
 * Interface for TariffsLegateeTypesDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface ITariffsLegateeTypesDAO {
	/**
	 * Perform an initial save of a previously unsaved TariffsLegateeTypes
	 * entity. All subsequent persist actions of this entity should use the
	 * #update() method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ITariffsLegateeTypesDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            TariffsLegateeTypes entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(TariffsLegateeTypes entity);

	/**
	 * Delete a persistent TariffsLegateeTypes entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ITariffsLegateeTypesDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            TariffsLegateeTypes entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(TariffsLegateeTypes entity);

	/**
	 * Persist a previously saved TariffsLegateeTypes entity and return it or a
	 * copy of it to the sender. A copy of the TariffsLegateeTypes entity
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
	 * entity = ITariffsLegateeTypesDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            TariffsLegateeTypes entity to update
	 * @returns TariffsLegateeTypes the persisted TariffsLegateeTypes entity
	 *          instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public TariffsLegateeTypes update(TariffsLegateeTypes entity);

	public TariffsLegateeTypes findById(TariffsLegateeTypesId id);

	/**
	 * Find all TariffsLegateeTypes entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the TariffsLegateeTypes property to query
	 * @param value
	 *            the property value to match
	 * @return List<TariffsLegateeTypes> found by query
	 */
	public List<TariffsLegateeTypes> findByProperty(String propertyName, Object value);

	/**
	 * Find all TariffsLegateeTypes entities.
	 * 
	 * @return List<TariffsLegateeTypes> all TariffsLegateeTypes entities
	 */
	public List<TariffsLegateeTypes> findAll();
}