package geniar.siscar.persistence;

import geniar.siscar.model.ServiceRegistry;

import java.util.List;

/**
 * Interface for ServiceRegistryDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IServiceRegistryDAO {
	/**
	 * Perform an initial save of a previously unsaved ServiceRegistry entity.
	 * All subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IServiceRegistryDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            ServiceRegistry entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(ServiceRegistry entity);

	/**
	 * Delete a persistent ServiceRegistry entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IServiceRegistryDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            ServiceRegistry entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(ServiceRegistry entity);

	/**
	 * Persist a previously saved ServiceRegistry entity and return it or a copy
	 * of it to the sender. A copy of the ServiceRegistry entity parameter is
	 * returned when the JPA persistence mechanism has not previously been
	 * tracking the updated entity. This operation must be performed within the
	 * a database transaction context for the entity's data to be permanently
	 * saved to the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IServiceRegistryDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            ServiceRegistry entity to update
	 * @returns ServiceRegistry the persisted ServiceRegistry entity instance,
	 *          may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public ServiceRegistry update(ServiceRegistry entity);

	public ServiceRegistry findById(Long id);

	/**
	 * Find all ServiceRegistry entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the ServiceRegistry property to query
	 * @param value
	 *            the property value to match
	 * @return List<ServiceRegistry> found by query
	 */
	public List<ServiceRegistry> findByProperty(String propertyName, Object value);

	public List<ServiceRegistry> findBySerUsrLogin(Object serUsrLogin);

	public List<ServiceRegistry> findBySerCorreoAsignatario(Object serCorreoAsignatario);

	public List<ServiceRegistry> findBySerHora(Object serHora);

	public List<ServiceRegistry> findBySerCarneAsignatario(Object serCarneAsignatario);

	public List<ServiceRegistry> findBySerNumRebPag(Object serNumRebPag);

	public List<ServiceRegistry> findBySerNombreSolicitante(Object serNombreSolicitante);

	public List<ServiceRegistry> findBySerCarneSolicitante(Object serCarneSolicitante);

	public List<ServiceRegistry> findBySerKilometrajeAnterior(Object serKilometrajeAnterior);

	public List<ServiceRegistry> findBySerNumeroGalones(Object serNumeroGalones);

	public List<ServiceRegistry> findBySerTotal(Object serTotal);

	public List<ServiceRegistry> findBySerKilometrajeActual(Object serKilometrajeActual);

	public List<ServiceRegistry> findBySerObservaciones(Object serObservaciones);

	public List<ServiceRegistry> findBySerPlacaIntra(Object serPlacaIntra);

	public List<ServiceRegistry> findByAoaCodigo(Object aoaCodigo);

	/**
	 * Find all ServiceRegistry entities.
	 * 
	 * @return List<ServiceRegistry> all ServiceRegistry entities
	 */
	public List<ServiceRegistry> findAll();
}