package geniar.siscar.persistence;

import geniar.siscar.model.InjuredPersons;

import java.util.List;

/**
 * Interface for InjuredPersonsDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IInjuredPersonsDAO {
	/**
	 * Perform an initial save of a previously unsaved InjuredPersons entity.
	 * All subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IInjuredPersonsDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            InjuredPersons entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(InjuredPersons entity);

	/**
	 * Delete a persistent InjuredPersons entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IInjuredPersonsDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            InjuredPersons entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(InjuredPersons entity);

	/**
	 * Persist a previously saved InjuredPersons entity and return it or a copy
	 * of it to the sender. A copy of the InjuredPersons entity parameter is
	 * returned when the JPA persistence mechanism has not previously been
	 * tracking the updated entity. This operation must be performed within the
	 * a database transaction context for the entity's data to be permanently
	 * saved to the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IInjuredPersonsDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            InjuredPersons entity to update
	 * @returns InjuredPersons the persisted InjuredPersons entity instance, may
	 *          not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public InjuredPersons update(InjuredPersons entity);

	public InjuredPersons findById(Long id);

	/**
	 * Find all InjuredPersons entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the InjuredPersons property to query
	 * @param value
	 *            the property value to match
	 * @return List<InjuredPersons> found by query
	 */
	public List<InjuredPersons> findByProperty(String propertyName, Object value);

	public List<InjuredPersons> findByPivId(Object pivId);

	public List<InjuredPersons> findByPivNombre(Object pivNombre);

	public List<InjuredPersons> findByPivDireccion(Object pivDireccion);

	public List<InjuredPersons> findByPnvTelefono(Object pnvTelefono);

	public List<InjuredPersons> findByPivEdad(Object pivEdad);

	public List<InjuredPersons> findByPivSitioatencion(Object pivSitioatencion);

	/**
	 * Find all InjuredPersons entities.
	 * 
	 * @return List<InjuredPersons> all InjuredPersons entities
	 */
	public List<InjuredPersons> findAll();
}