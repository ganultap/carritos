package geniar.siscar.persistence;

import geniar.siscar.model.ClientsSalesVehicles;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * ClientsSalesVehicles entities. Transaction control of the save(), update()
 * and delete() operations must be handled externally by senders of these
 * methods or must be manually added to each of these methods for data to be
 * persisted to the JPA datastore.
 * 
 * @see geniar.siscar.model.ClientsSalesVehicles
 * @author MyEclipse Persistence Tools
 */

public class ClientsSalesVehiclesDAO implements IClientsSalesVehiclesDAO {
	// property constants
	public static final String CSV_IDENTIFICACACION = "csvIdentificacacion";
	public static final String CSV_NOMBRE = "csvNombre";
	public static final String CSV_TELEFONO = "csvTelefono";
	public static final String CSV_DIRECCION = "csvDireccion";
	public static final String CSV_MAIL = "csvMail";
	public static final String CSV_VALOR_VENTA = "csvValorVenta";
	public static final String CSV_AT_FINAL = "csvAtFinal";
	public static final String CSV_NUMERO_LICITACION = "csvNumeroLicitacion";
	public static final String CSV_PLACA_INTRA = "csvPlacaIntra";
	public static final String CSV_OBSERVACIONES = "csvObservaciones";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

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
	 * ClientsSalesVehiclesDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            ClientsSalesVehicles entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(ClientsSalesVehicles entity) {
		EntityManagerHelper.log("saving ClientsSalesVehicles instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

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
	 * ClientsSalesVehiclesDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            ClientsSalesVehicles entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(ClientsSalesVehicles entity) {
		EntityManagerHelper.log("deleting ClientsSalesVehicles instance", Level.INFO, null);
		try {
			entity = getEntityManager().getReference(ClientsSalesVehicles.class, entity.getCvsCodigo());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

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
	 * entity = ClientsSalesVehiclesDAO.update(entity);
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
	public ClientsSalesVehicles update(ClientsSalesVehicles entity) {
		EntityManagerHelper.log("updating ClientsSalesVehicles instance", Level.INFO, null);
		try {
			ClientsSalesVehicles result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public ClientsSalesVehicles findById(Long id) {
		EntityManagerHelper.log("finding ClientsSalesVehicles instance with id: " + id, Level.INFO, null);
		try {
			ClientsSalesVehicles instance = getEntityManager().find(ClientsSalesVehicles.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all ClientsSalesVehicles entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the ClientsSalesVehicles property to query
	 * @param value
	 *            the property value to match
	 * @return List<ClientsSalesVehicles> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<ClientsSalesVehicles> findByProperty(String propertyName, final Object value) {
		EntityManagerHelper.log("finding ClientsSalesVehicles instance with property: " + propertyName + ", value: "
				+ value, Level.INFO, null);
		try {
			final String queryString = "select model from ClientsSalesVehicles model where model." + propertyName
					+ "= :propertyValue";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("propertyValue", value);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find by property name failed", Level.SEVERE, re);
			throw re;
		}
	}

	public List<ClientsSalesVehicles> findByCsvIdentificacacion(Object csvIdentificacacion) {
		return findByProperty(CSV_IDENTIFICACACION, csvIdentificacacion);
	}

	public List<ClientsSalesVehicles> findByCsvNombre(Object csvNombre) {
		return findByProperty(CSV_NOMBRE, csvNombre);
	}

	public List<ClientsSalesVehicles> findByCsvTelefono(Object csvTelefono) {
		return findByProperty(CSV_TELEFONO, csvTelefono);
	}

	public List<ClientsSalesVehicles> findByCsvDireccion(Object csvDireccion) {
		return findByProperty(CSV_DIRECCION, csvDireccion);
	}

	public List<ClientsSalesVehicles> findByCsvMail(Object csvMail) {
		return findByProperty(CSV_MAIL, csvMail);
	}

	public List<ClientsSalesVehicles> findByCsvValorVenta(Object csvValorVenta) {
		return findByProperty(CSV_VALOR_VENTA, csvValorVenta);
	}

	public List<ClientsSalesVehicles> findByCsvAtFinal(Object csvAtFinal) {
		return findByProperty(CSV_AT_FINAL, csvAtFinal);
	}

	public List<ClientsSalesVehicles> findByCsvNumeroLicitacion(Object csvNumeroLicitacion) {
		return findByProperty(CSV_NUMERO_LICITACION, csvNumeroLicitacion);
	}

	public List<ClientsSalesVehicles> findByCsvPlacaIntra(Object csvPlacaIntra) {
		return findByProperty(CSV_PLACA_INTRA, csvPlacaIntra);
	}

	public List<ClientsSalesVehicles> findByCsvObservaciones(Object csvObservaciones) {
		return findByProperty(CSV_OBSERVACIONES, csvObservaciones);
	}

	/**
	 * Find all ClientsSalesVehicles entities.
	 * 
	 * @return List<ClientsSalesVehicles> all ClientsSalesVehicles entities
	 */
	@SuppressWarnings("unchecked")
	public List<ClientsSalesVehicles> findAll() {
		EntityManagerHelper.log("finding all ClientsSalesVehicles instances", Level.INFO, null);
		try {
			final String queryString = "select model from ClientsSalesVehicles model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}