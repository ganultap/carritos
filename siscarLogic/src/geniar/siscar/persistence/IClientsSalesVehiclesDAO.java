package geniar.siscar.persistence;

import geniar.siscar.model.ClientsSalesVehicles;

import java.util.List;

/**
 * Interface for ClientsSalesVehiclesDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IClientsSalesVehiclesDAO {
	/**
	 * Perform an initial save of a previously unsaved ClientsSalesVehicles
	 * entity. All subsequent persist actions of this entity should use the
	 * #update() method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IClientsSalesVehiclesDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            ClientsSalesVehicles entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(ClientsSalesVehicles entity);

	/**
	 * Delete a persistent ClientsSalesVehicles entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IClientsSalesVehiclesDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            ClientsSalesVehicles entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(ClientsSalesVehicles entity);

	/**
	 * Persist a previously saved ClientsSalesVehicles entity and return it or a
	 * copy of it to the sender. A copy of the ClientsSalesVehicles entity
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
	 * entity = IClientsSalesVehiclesDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            ClientsSalesVehicles entity to update
	 * @returns ClientsSalesVehicles the persisted ClientsSalesVehicles entity
	 *          instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public ClientsSalesVehicles update(ClientsSalesVehicles entity);

	public ClientsSalesVehicles findById(Long id);

	/**
	 * Find all ClientsSalesVehicles entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the ClientsSalesVehicles property to query
	 * @param value
	 *            the property value to match
	 * @return List<ClientsSalesVehicles> found by query
	 */
	public List<ClientsSalesVehicles> findByProperty(String propertyName, Object value);

	public List<ClientsSalesVehicles> findByCsvIdentificacacion(Object csvIdentificacacion);

	public List<ClientsSalesVehicles> findByCsvNombre(Object csvNombre);

	public List<ClientsSalesVehicles> findByCsvTelefono(Object csvTelefono);

	public List<ClientsSalesVehicles> findByCsvDireccion(Object csvDireccion);

	public List<ClientsSalesVehicles> findByCsvMail(Object csvMail);

	public List<ClientsSalesVehicles> findByCsvValorVenta(Object csvValorVenta);

	public List<ClientsSalesVehicles> findByCsvAtFinal(Object csvAtFinal);

	public List<ClientsSalesVehicles> findByCsvNumeroLicitacion(Object csvNumeroLicitacion);

	public List<ClientsSalesVehicles> findByCsvPlacaIntra(Object csvPlacaIntra);

	public List<ClientsSalesVehicles> findByCsvObservaciones(Object csvObservaciones);

	/**
	 * Find all ClientsSalesVehicles entities.
	 * 
	 * @return List<ClientsSalesVehicles> all ClientsSalesVehicles entities
	 */
	public List<ClientsSalesVehicles> findAll();
}