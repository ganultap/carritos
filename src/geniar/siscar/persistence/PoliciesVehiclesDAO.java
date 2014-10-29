package geniar.siscar.persistence;

// default package

import geniar.siscar.model.PoliciesVehicles;
import geniar.siscar.model.PoliciesVehiclesId;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * PoliciesVehicles entities. Transaction control of the save(), update() and
 * delete() operations must be handled externally by senders of these methods or
 * must be manually added to each of these methods for data to be persisted to
 * the JPA datastore.
 * 
 * @see .PoliciesVehicles
 * @author MyEclipse Persistence Tools
 */

public class PoliciesVehiclesDAO implements IPoliciesVehiclesDAO {
	// property constants
	public static final String PVS_ESTADO = "pvsEstado";
	public static final String PVS_VALOR_PRIMA = "pvsValorPrima";
	public static final String PVS_VALOR_IVA = "pvsValorIva";
	public static final String PVS_VALOR_TOTAL = "pvsValorTotal";
	public static final String PVS_VALOR_ASEGURADO = "pvsValorAsegurado";
	public static final String PVS_CARNET_ASIGNATARIO = "pvsCarnetAsignatario";
	public static final String PVS_NUMERO_FACTURA = "pvsNumeroFactura";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved PoliciesVehicles entity.
	 * All subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * PoliciesVehiclesDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            PoliciesVehicles entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(PoliciesVehicles entity) {
		EntityManagerHelper.log("saving PoliciesVehicles instance", Level.INFO,
				null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent PoliciesVehicles entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * PoliciesVehiclesDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            PoliciesVehicles entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(PoliciesVehicles entity) {
		EntityManagerHelper.log("deleting PoliciesVehicles instance",
				Level.INFO, null);
		try {
			entity = getEntityManager().getReference(PoliciesVehicles.class,
					entity.getId());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved PoliciesVehicles entity and return it or a
	 * copy of it to the sender. A copy of the PoliciesVehicles entity parameter
	 * is returned when the JPA persistence mechanism has not previously been
	 * tracking the updated entity. This operation must be performed within the
	 * a database transaction context for the entity's data to be permanently
	 * saved to the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = PoliciesVehiclesDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            PoliciesVehicles entity to update
	 * @returns PoliciesVehicles the persisted PoliciesVehicles entity instance,
	 *          may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public PoliciesVehicles update(PoliciesVehicles entity) {
		EntityManagerHelper.log("updating PoliciesVehicles instance",
				Level.INFO, null);
		try {
			PoliciesVehicles result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public PoliciesVehicles findById(PoliciesVehiclesId id) {
		EntityManagerHelper.log("finding PoliciesVehicles instance with id: "
				+ id, Level.INFO, null);
		try {
			PoliciesVehicles instance = getEntityManager().find(
					PoliciesVehicles.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all PoliciesVehicles entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the PoliciesVehicles property to query
	 * @param value
	 *            the property value to match
	 * @return List<PoliciesVehicles> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<PoliciesVehicles> findByProperty(String propertyName,
			final Object value) {
		EntityManagerHelper.log(
				"finding PoliciesVehicles instance with property: "
						+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from PoliciesVehicles model where model."
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

	public List<PoliciesVehicles> findByPvsEstado(Object pvsEstado) {
		return findByProperty(PVS_ESTADO, pvsEstado);
	}

	public List<PoliciesVehicles> findByPvsValorPrima(Object pvsValorPrima) {
		return findByProperty(PVS_VALOR_PRIMA, pvsValorPrima);
	}

	public List<PoliciesVehicles> findByPvsValorIva(Object pvsValorIva) {
		return findByProperty(PVS_VALOR_IVA, pvsValorIva);
	}

	public List<PoliciesVehicles> findByPvsValorTotal(Object pvsValorTotal) {
		return findByProperty(PVS_VALOR_TOTAL, pvsValorTotal);
	}

	public List<PoliciesVehicles> findByPvsValorAsegurado(
			Object pvsValorAsegurado) {
		return findByProperty(PVS_VALOR_ASEGURADO, pvsValorAsegurado);
	}

	public List<PoliciesVehicles> findByPvsCarnetAsignatario(
			Object pvsCarnetAsignatario) {
		return findByProperty(PVS_CARNET_ASIGNATARIO, pvsCarnetAsignatario);
	}

	public List<PoliciesVehicles> findByPvsNumeroFactura(Object pvsNumeroFactura) {
		return findByProperty(PVS_NUMERO_FACTURA, pvsNumeroFactura);
	}

	/**
	 * Find all PoliciesVehicles entities.
	 * 
	 * @return List<PoliciesVehicles> all PoliciesVehicles entities
	 */
	@SuppressWarnings("unchecked")
	public List<PoliciesVehicles> findAll() {
		EntityManagerHelper.log("finding all PoliciesVehicles instances",
				Level.INFO, null);
		try {
			final String queryString = "select model from PoliciesVehicles model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}