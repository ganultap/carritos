package geniar.siscar.persistence;

import geniar.siscar.model.ServiceRegistry;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * ServiceRegistry entities. Transaction control of the save(), update() and
 * delete() operations must be handled externally by senders of these methods or
 * must be manually added to each of these methods for data to be persisted to
 * the JPA datastore.
 * 
 * @see geniar.siscar.model.ServiceRegistry
 * @author MyEclipse Persistence Tools
 */

public class ServiceRegistryDAO implements IServiceRegistryDAO {
	// property constants
	public static final String SER_USR_LOGIN = "serUsrLogin";
	public static final String SER_CORREO_ASIGNATARIO = "serCorreoAsignatario";
	public static final String SER_HORA = "serHora";
	public static final String SER_CARNE_ASIGNATARIO = "serCarneAsignatario";
	public static final String SER_NUM_REB_PAG = "serNumRebPag";
	public static final String SER_NOMBRE_SOLICITANTE = "serNombreSolicitante";
	public static final String SER_CARNE_SOLICITANTE = "serCarneSolicitante";
	public static final String SER_KILOMETRAJE_ANTERIOR = "serKilometrajeAnterior";
	public static final String SER_NUMERO_GALONES = "serNumeroGalones";
	public static final String SER_TOTAL = "serTotal";
	public static final String SER_KILOMETRAJE_ACTUAL = "serKilometrajeActual";
	public static final String SER_OBSERVACIONES = "serObservaciones";
	public static final String SER_PLACA_INTRA = "serPlacaIntra";
	public static final String AOA_CODIGO = "aoaCodigo";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

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
	 * ServiceRegistryDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            ServiceRegistry entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(ServiceRegistry entity) {
		EntityManagerHelper.log("saving ServiceRegistry instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

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
	 * ServiceRegistryDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            ServiceRegistry entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(ServiceRegistry entity) {
		EntityManagerHelper.log("deleting ServiceRegistry instance", Level.INFO, null);
		try {
			entity = getEntityManager().getReference(ServiceRegistry.class, entity.getSerCodigo());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

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
	 * entity = ServiceRegistryDAO.update(entity);
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
	public ServiceRegistry update(ServiceRegistry entity) {
		EntityManagerHelper.log("updating ServiceRegistry instance", Level.INFO, null);
		try {
			ServiceRegistry result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public ServiceRegistry findById(Long id) {
		EntityManagerHelper.log("finding ServiceRegistry instance with id: " + id, Level.INFO, null);
		try {
			ServiceRegistry instance = getEntityManager().find(ServiceRegistry.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all ServiceRegistry entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the ServiceRegistry property to query
	 * @param value
	 *            the property value to match
	 * @return List<ServiceRegistry> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<ServiceRegistry> findByProperty(String propertyName, final Object value) {
		EntityManagerHelper.log("finding ServiceRegistry instance with property: " + propertyName + ", value: " + value,
				Level.INFO, null);
		try {
			final String queryString = "select model from ServiceRegistry model where model." + propertyName + "= :propertyValue";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("propertyValue", value);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find by property name failed", Level.SEVERE, re);
			throw re;
		}
	}

	public List<ServiceRegistry> findBySerUsrLogin(Object serUsrLogin) {
		return findByProperty(SER_USR_LOGIN, serUsrLogin);
	}

	public List<ServiceRegistry> findBySerCorreoAsignatario(Object serCorreoAsignatario) {
		return findByProperty(SER_CORREO_ASIGNATARIO, serCorreoAsignatario);
	}

	public List<ServiceRegistry> findBySerHora(Object serHora) {
		return findByProperty(SER_HORA, serHora);
	}

	public List<ServiceRegistry> findBySerCarneAsignatario(Object serCarneAsignatario) {
		return findByProperty(SER_CARNE_ASIGNATARIO, serCarneAsignatario);
	}

	public List<ServiceRegistry> findBySerNumRebPag(Object serNumRebPag) {
		return findByProperty(SER_NUM_REB_PAG, serNumRebPag);
	}

	public List<ServiceRegistry> findBySerNombreSolicitante(Object serNombreSolicitante) {
		return findByProperty(SER_NOMBRE_SOLICITANTE, serNombreSolicitante);
	}

	public List<ServiceRegistry> findBySerCarneSolicitante(Object serCarneSolicitante) {
		return findByProperty(SER_CARNE_SOLICITANTE, serCarneSolicitante);
	}

	public List<ServiceRegistry> findBySerKilometrajeAnterior(Object serKilometrajeAnterior) {
		return findByProperty(SER_KILOMETRAJE_ANTERIOR, serKilometrajeAnterior);
	}

	public List<ServiceRegistry> findBySerNumeroGalones(Object serNumeroGalones) {
		return findByProperty(SER_NUMERO_GALONES, serNumeroGalones);
	}

	public List<ServiceRegistry> findBySerTotal(Object serTotal) {
		return findByProperty(SER_TOTAL, serTotal);
	}

	public List<ServiceRegistry> findBySerKilometrajeActual(Object serKilometrajeActual) {
		return findByProperty(SER_KILOMETRAJE_ACTUAL, serKilometrajeActual);
	}

	public List<ServiceRegistry> findBySerObservaciones(Object serObservaciones) {
		return findByProperty(SER_OBSERVACIONES, serObservaciones);
	}

	public List<ServiceRegistry> findBySerPlacaIntra(Object serPlacaIntra) {
		return findByProperty(SER_PLACA_INTRA, serPlacaIntra);
	}

	public List<ServiceRegistry> findByAoaCodigo(Object aoaCodigo) {
		return findByProperty(AOA_CODIGO, aoaCodigo);
	}

	/**
	 * Find all ServiceRegistry entities.
	 * 
	 * @return List<ServiceRegistry> all ServiceRegistry entities
	 */
	@SuppressWarnings("unchecked")
	public List<ServiceRegistry> findAll() {
		EntityManagerHelper.log("finding all ServiceRegistry instances", Level.INFO, null);
		try {
			final String queryString = "select model from ServiceRegistry model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}