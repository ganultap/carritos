package geniar.siscar.persistence;

import geniar.siscar.model.PersonsInvolvedVehicles;

import java.util.List;

/**
 * Interface for PersonsInvolvedVehiclesDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IPersonsInvolvedVehiclesDAO {
	/**
	 * Perform an initial save of a previously unsaved PersonsInvolvedVehicles
	 * entity. All subsequent persist actions of this entity should use the
	 * #update() method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IPersonsInvolvedVehiclesDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            PersonsInvolvedVehicles entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(PersonsInvolvedVehicles entity);

	/**
	 * Delete a persistent PersonsInvolvedVehicles entity. This operation must
	 * be performed within the a database transaction context for the entity's
	 * data to be permanently deleted from the persistence store, i.e.,
	 * database. This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IPersonsInvolvedVehiclesDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            PersonsInvolvedVehicles entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(PersonsInvolvedVehicles entity);

	/**
	 * Persist a previously saved PersonsInvolvedVehicles entity and return it
	 * or a copy of it to the sender. A copy of the PersonsInvolvedVehicles
	 * entity parameter is returned when the JPA persistence mechanism has not
	 * previously been tracking the updated entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently saved to the persistence store, i.e., database. This
	 * method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IPersonsInvolvedVehiclesDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            PersonsInvolvedVehicles entity to update
	 * @returns PersonsInvolvedVehicles the persisted PersonsInvolvedVehicles
	 *          entity instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public PersonsInvolvedVehicles update(PersonsInvolvedVehicles entity);

	public PersonsInvolvedVehicles findById(Long id);

	/**
	 * Find all PersonsInvolvedVehicles entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the PersonsInvolvedVehicles property to query
	 * @param value
	 *            the property value to match
	 * @return List<PersonsInvolvedVehicles> found by query
	 */
	public List<PersonsInvolvedVehicles> findByProperty(String propertyName, Object value);

	public List<PersonsInvolvedVehicles> findByPivNombre(Object pivNombre);

	public List<PersonsInvolvedVehicles> findByPivDireccion(Object pivDireccion);

	public List<PersonsInvolvedVehicles> findByPnvTelefono(Object pnvTelefono);

	public List<PersonsInvolvedVehicles> findByPivEdad(Object pivEdad);

	public List<PersonsInvolvedVehicles> findByPivSitioatencion(Object pivSitioatencion);

	/**
	 * Find all PersonsInvolvedVehicles entities.
	 * 
	 * @return List<PersonsInvolvedVehicles> all PersonsInvolvedVehicles
	 *         entities
	 */
	public List<PersonsInvolvedVehicles> findAll();
}