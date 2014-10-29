package geniar.siscar.persistence;

import geniar.siscar.model.Requests;

import java.util.List;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * Requests entities. Transaction control of the save(), update() and delete()
 * operations must be handled externally by senders of these methods or must be
 * manually added to each of these methods for data to be persisted to the JPA
 * datastore.
 * 
 * @see .Requests
 * @author MyEclipse Persistence Tools
 */

public class RequestsDAO implements IRequestsDAO {
	// property constants
	public static final String RQS_MOTIVO_CANCELACION = "rqsMotivoCancelacion";
	public static final String RQS_DESCRIPCION = "rqsDescripcion";
	public static final String RQS_CARNET_EMPLEADO = "rqsCarnetEmpleado";
	public static final String RQS_CARNET_ASIGNATARIO = "rqsCarnetAsignatario";
	public static final String RQS_NIT = "rqsNit";
	public static final String RQS_PLACA_EXTENSION_REMPLAZO = "rqsPlacaExtensionRemplazo";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved Requests entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * RequestsDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Requests entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Requests entity) {
		EntityManagerHelper.log("saving Requests instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent Requests entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * RequestsDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Requests entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Requests entity) {
		EntityManagerHelper.log("deleting Requests instance", Level.INFO, null);
		try {
			entity = getEntityManager().getReference(Requests.class,
					entity.getRqsCodigo());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved Requests entity and return it or a copy of it
	 * to the sender. A copy of the Requests entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = RequestsDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Requests entity to update
	 * @returns Requests the persisted Requests entity instance, may not be the
	 *          same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Requests update(Requests entity) {
		EntityManagerHelper.log("updating Requests instance", Level.INFO, null);
		try {
			Requests result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Requests findById(Long id) {
		EntityManagerHelper.log("finding Requests instance with id: " + id,
				Level.INFO, null);
		try {
			Requests instance = getEntityManager().find(Requests.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all Requests entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Requests property to query
	 * @param value
	 *            the property value to match
	 * @return List<Requests> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Requests> findByProperty(String propertyName, final Object value) {
		EntityManagerHelper.log("finding Requests instance with property: "
				+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from Requests model where model."
					+ propertyName + "= :propertyValue";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("propertyValue", value);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find by property name failed",
					Level.SEVERE, re);
			throw re;
		}
	}

	public List<Requests> findByRqsMotivoCancelacion(Object rqsMotivoCancelacion) {
		return findByProperty(RQS_MOTIVO_CANCELACION, rqsMotivoCancelacion);
	}

	public List<Requests> findByRqsDescripcion(Object rqsDescripcion) {
		return findByProperty(RQS_DESCRIPCION, rqsDescripcion);
	}

	public List<Requests> findByRqsCarnetEmpleado(Object rqsCarnetEmpleado) {
		return findByProperty(RQS_CARNET_EMPLEADO, rqsCarnetEmpleado);
	}

	public List<Requests> findByRqsCarnetAsignatario(Object rqsCarnetAsignatario) {
		return findByProperty(RQS_CARNET_ASIGNATARIO, rqsCarnetAsignatario);
	}

	public List<Requests> findByRqsNit(Object rqsNit) {
		return findByProperty(RQS_NIT, rqsNit);
	}

	public List<Requests> findByRqsPlacaExtensionRemplazo(
			Object rqsPlacaExtensionRemplazo) {
		return findByProperty(RQS_PLACA_EXTENSION_REMPLAZO,
				rqsPlacaExtensionRemplazo);
	}

	/**
	 * Find all Requests entities.
	 * 
	 * @return List<Requests> all Requests entities
	 */
	@SuppressWarnings("unchecked")
	public List<Requests> findAll() {
		EntityManagerHelper.log("finding all Requests instances", Level.INFO,
				null);
		try {
			final String queryString = "select model from Requests model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}