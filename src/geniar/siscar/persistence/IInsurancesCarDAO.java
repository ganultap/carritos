package geniar.siscar.persistence;

import geniar.siscar.model.InsurancesCar;

import java.util.List;

/**
 * Interface for InsurancesCarDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IInsurancesCarDAO {
	/**
	 * Perform an initial save of a previously unsaved InsurancesCar entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IInsurancesCarDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            InsurancesCar entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(InsurancesCar entity);

	/**
	 * Delete a persistent InsurancesCar entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IInsurancesCarDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            InsurancesCar entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(InsurancesCar entity);

	/**
	 * Persist a previously saved InsurancesCar entity and return it or a copy
	 * of it to the sender. A copy of the InsurancesCar entity parameter is
	 * returned when the JPA persistence mechanism has not previously been
	 * tracking the updated entity. This operation must be performed within the
	 * a database transaction context for the entity's data to be permanently
	 * saved to the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IInsurancesCarDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            InsurancesCar entity to update
	 * @returns InsurancesCar the persisted InsurancesCar entity instance, may
	 *          not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public InsurancesCar update(InsurancesCar entity);

	public InsurancesCar findById(Long id);

	/**
	 * Find all InsurancesCar entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the InsurancesCar property to query
	 * @param value
	 *            the property value to match
	 * @return List<InsurancesCar> found by query
	 */
	public List<InsurancesCar> findByProperty(String propertyName, Object value);

	public List<InsurancesCar> findByInsNombre(Object insNombre);

	/**
	 * Find all InsurancesCar entities.
	 * 
	 * @return List<InsurancesCar> all InsurancesCar entities
	 */
	public List<InsurancesCar> findAll();
}