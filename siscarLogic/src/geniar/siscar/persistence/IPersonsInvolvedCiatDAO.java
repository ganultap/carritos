package geniar.siscar.persistence;

import geniar.siscar.model.PersonsInvolvedCiat;

import java.util.List;

/**
 * Interface for PersonsInvolvedCiatDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IPersonsInvolvedCiatDAO {
	/**
	 * Perform an initial save of a previously unsaved PersonsInvolvedCiat
	 * entity. All subsequent persist actions of this entity should use the
	 * #update() method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IPersonsInvolvedCiatDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            PersonsInvolvedCiat entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(PersonsInvolvedCiat entity);

	/**
	 * Delete a persistent PersonsInvolvedCiat entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IPersonsInvolvedCiatDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            PersonsInvolvedCiat entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(PersonsInvolvedCiat entity);

	/**
	 * Persist a previously saved PersonsInvolvedCiat entity and return it or a
	 * copy of it to the sender. A copy of the PersonsInvolvedCiat entity
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
	 * entity = IPersonsInvolvedCiatDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            PersonsInvolvedCiat entity to update
	 * @returns PersonsInvolvedCiat the persisted PersonsInvolvedCiat entity
	 *          instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public PersonsInvolvedCiat update(PersonsInvolvedCiat entity);

	public PersonsInvolvedCiat findById(Long id);

	/**
	 * Find all PersonsInvolvedCiat entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the PersonsInvolvedCiat property to query
	 * @param value
	 *            the property value to match
	 * @return List<PersonsInvolvedCiat> found by query
	 */
	public List<PersonsInvolvedCiat> findByProperty(String propertyName, Object value);

	public List<PersonsInvolvedCiat> findByPicNombre(Object picNombre);

	public List<PersonsInvolvedCiat> findByPicDireccion(Object picDireccion);

	public List<PersonsInvolvedCiat> findByPicTelefono(Object picTelefono);

	public List<PersonsInvolvedCiat> findByPicEdad(Object picEdad);

	public List<PersonsInvolvedCiat> findByPicSitioAtencion(Object picSitioAtencion);

	/**
	 * Find all PersonsInvolvedCiat entities.
	 * 
	 * @return List<PersonsInvolvedCiat> all PersonsInvolvedCiat entities
	 */
	public List<PersonsInvolvedCiat> findAll();
}