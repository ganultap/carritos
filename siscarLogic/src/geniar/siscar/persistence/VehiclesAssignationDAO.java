package geniar.siscar.persistence;

import geniar.siscar.model.VehiclesAssignation;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * VehiclesAssignation entities. Transaction control of the save(), update() and
 * delete() operations must be handled externally by senders of these methods or
 * must be manually added to each of these methods for data to be persisted to
 * the JPA datastore.
 * 
 * @see geniar.siscar.model.VehiclesAssignation
 * @author MyEclipse Persistence Tools
 */

public class VehiclesAssignationDAO implements IVehiclesAssignationDAO {
	// property constants
	public static final String VHA_NUMERO_SOLICITUD = "vhaNumeroSolicitud";
	public static final String VHA_NUMERO_CARNE = "vhaNumeroCarne";
	public static final String VHA_KILOME_ACTUAL = "vhaKilomeActual";
	public static final String VHA_OBSERVACION = "vhaObservacion";
	public static final String VHA_KILOM_DEV = "vhaKilomDev";
	public static final String VHA_COBRO_CASACIAT = "vhaCobroCasaciat";
	public static final String VHA_COBRO = "vhaCobro";
	public static final String VHA_OBSERVACION_DEV = "vhaObservacionDev";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved VehiclesAssignation
	 * entity. All subsequent persist actions of this entity should use the
	 * #update() method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * VehiclesAssignationDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            VehiclesAssignation entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(VehiclesAssignation entity) {
		EntityManagerHelper.log("saving VehiclesAssignation instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent VehiclesAssignation entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * VehiclesAssignationDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            VehiclesAssignation entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(VehiclesAssignation entity) {
		EntityManagerHelper.log("deleting VehiclesAssignation instance", Level.INFO, null);
		try {
			entity = getEntityManager().getReference(VehiclesAssignation.class, entity.getVhaCodigo());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved VehiclesAssignation entity and return it or a
	 * copy of it to the sender. A copy of the VehiclesAssignation entity
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
	 * entity = VehiclesAssignationDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            VehiclesAssignation entity to update
	 * @returns VehiclesAssignation the persisted VehiclesAssignation entity
	 *          instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public VehiclesAssignation update(VehiclesAssignation entity) {
		EntityManagerHelper.log("updating VehiclesAssignation instance", Level.INFO, null);
		try {
			VehiclesAssignation result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public VehiclesAssignation findById(Long id) {
		EntityManagerHelper.log("finding VehiclesAssignation instance with id: " + id, Level.INFO, null);
		try {
			VehiclesAssignation instance = getEntityManager().find(VehiclesAssignation.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all VehiclesAssignation entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the VehiclesAssignation property to query
	 * @param value
	 *            the property value to match
	 * @return List<VehiclesAssignation> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<VehiclesAssignation> findByProperty(String propertyName, final Object value) {
		EntityManagerHelper.log("finding VehiclesAssignation instance with property: " + propertyName + ", value: "
				+ value, Level.INFO, null);
		try {
			final String queryString = "select model from VehiclesAssignation model where model." + propertyName
					+ "= :propertyValue";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("propertyValue", value);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find by property name failed", Level.SEVERE, re);
			throw re;
		}
	}

	public List<VehiclesAssignation> findByVhaNumeroSolicitud(Object vhaNumeroSolicitud) {
		return findByProperty(VHA_NUMERO_SOLICITUD, vhaNumeroSolicitud);
	}

	public List<VehiclesAssignation> findByVhaNumeroCarne(Object vhaNumeroCarne) {
		return findByProperty(VHA_NUMERO_CARNE, vhaNumeroCarne);
	}

	public List<VehiclesAssignation> findByVhaKilomeActual(Object vhaKilomeActual) {
		return findByProperty(VHA_KILOME_ACTUAL, vhaKilomeActual);
	}

	public List<VehiclesAssignation> findByVhaObservacion(Object vhaObservacion) {
		return findByProperty(VHA_OBSERVACION, vhaObservacion);
	}

	public List<VehiclesAssignation> findByVhaKilomDev(Object vhaKilomDev) {
		return findByProperty(VHA_KILOM_DEV, vhaKilomDev);
	}

	public List<VehiclesAssignation> findByVhaCobroCasaciat(Object vhaCobroCasaciat) {
		return findByProperty(VHA_COBRO_CASACIAT, vhaCobroCasaciat);
	}

	public List<VehiclesAssignation> findByVhaCobro(Object vhaCobro) {
		return findByProperty(VHA_COBRO, vhaCobro);
	}

	public List<VehiclesAssignation> findByVhaObservacionDev(Object vhaObservacionDev) {
		return findByProperty(VHA_OBSERVACION_DEV, vhaObservacionDev);
	}

	/**
	 * Find all VehiclesAssignation entities.
	 * 
	 * @return List<VehiclesAssignation> all VehiclesAssignation entities
	 */
	@SuppressWarnings("unchecked")
	public List<VehiclesAssignation> findAll() {
		EntityManagerHelper.log("finding all VehiclesAssignation instances", Level.INFO, null);
		try {
			final String queryString = "select model from VehiclesAssignation model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}