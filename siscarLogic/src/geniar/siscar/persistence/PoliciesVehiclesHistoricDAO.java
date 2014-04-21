package geniar.siscar.persistence;

// default package

import geniar.siscar.model.PoliciesVehiclesHistoric;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * PoliciesVehiclesHistoric entities. Transaction control of the save(),
 * update() and delete() operations must be handled externally by senders of
 * these methods or must be manually added to each of these methods for data to
 * be persisted to the JPA datastore.
 * 
 * @see .PoliciesVehiclesHistoric
 * @author MyEclipse Persistence Tools
 */

public class PoliciesVehiclesHistoricDAO implements
		IPoliciesVehiclesHistoricDAO {
	// property constants
	public static final String PIN_NUMERO_FACTURA = "pinNumeroFactura";
	public static final String USR_LOGIN = "usrLogin";
	public static final String VHC_CODIGO = "vhcCodigo";
	public static final String PLS_CODIGO = "plsCodigo";
	public static final String PVS_ESTADO = "pvsEstado";
	public static final String PVS_VALOR_IVA = "pvsValorIva";
	public static final String PVS_VALOR_PRIMA = "pvsValorPrima";
	public static final String PVS_VALOR_TOTAL = "pvsValorTotal";
	public static final String PVS_VALOR_ASEGURADO = "pvsValorAsegurado";
	public static final String PVS_CARNET_ASIGNATARIO = "pvsCarnetAsignatario";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved PoliciesVehiclesHistoric
	 * entity. All subsequent persist actions of this entity should use the
	 * #update() method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * PoliciesVehiclesHistoricDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            PoliciesVehiclesHistoric entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(PoliciesVehiclesHistoric entity) {
		EntityManagerHelper.log("saving PoliciesVehiclesHistoric instance",
				Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent PoliciesVehiclesHistoric entity. This operation must
	 * be performed within the a database transaction context for the entity's
	 * data to be permanently deleted from the persistence store, i.e.,
	 * database. This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * PoliciesVehiclesHistoricDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            PoliciesVehiclesHistoric entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(PoliciesVehiclesHistoric entity) {
		EntityManagerHelper.log("deleting PoliciesVehiclesHistoric instance",
				Level.INFO, null);
		try {
			entity = getEntityManager().getReference(
					PoliciesVehiclesHistoric.class, entity.getPvhId());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved PoliciesVehiclesHistoric entity and return it
	 * or a copy of it to the sender. A copy of the PoliciesVehiclesHistoric
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
	 * entity = PoliciesVehiclesHistoricDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            PoliciesVehiclesHistoric entity to update
	 * @returns PoliciesVehiclesHistoric the persisted PoliciesVehiclesHistoric
	 *          entity instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public PoliciesVehiclesHistoric update(PoliciesVehiclesHistoric entity) {
		EntityManagerHelper.log("updating PoliciesVehiclesHistoric instance",
				Level.INFO, null);
		try {
			PoliciesVehiclesHistoric result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public PoliciesVehiclesHistoric findById(Long id) {
		EntityManagerHelper.log(
				"finding PoliciesVehiclesHistoric instance with id: " + id,
				Level.INFO, null);
		try {
			PoliciesVehiclesHistoric instance = getEntityManager().find(
					PoliciesVehiclesHistoric.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all PoliciesVehiclesHistoric entities with a specific property
	 * value.
	 * 
	 * @param propertyName
	 *            the name of the PoliciesVehiclesHistoric property to query
	 * @param value
	 *            the property value to match
	 * @return List<PoliciesVehiclesHistoric> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<PoliciesVehiclesHistoric> findByProperty(String propertyName,
			final Object value) {
		EntityManagerHelper.log(
				"finding PoliciesVehiclesHistoric instance with property: "
						+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from PoliciesVehiclesHistoric model where model."
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

	public List<PoliciesVehiclesHistoric> findByPinNumeroFactura(
			Object pinNumeroFactura) {
		return findByProperty(PIN_NUMERO_FACTURA, pinNumeroFactura);
	}

	public List<PoliciesVehiclesHistoric> findByUsrLogin(Object usrLogin) {
		return findByProperty(USR_LOGIN, usrLogin);
	}

	public List<PoliciesVehiclesHistoric> findByVhcCodigo(Object vhcCodigo) {
		return findByProperty(VHC_CODIGO, vhcCodigo);
	}

	public List<PoliciesVehiclesHistoric> findByPlsCodigo(Object plsCodigo) {
		return findByProperty(PLS_CODIGO, plsCodigo);
	}

	public List<PoliciesVehiclesHistoric> findByPvsEstado(Object pvsEstado) {
		return findByProperty(PVS_ESTADO, pvsEstado);
	}

	public List<PoliciesVehiclesHistoric> findByPvsValorIva(Object pvsValorIva) {
		return findByProperty(PVS_VALOR_IVA, pvsValorIva);
	}

	public List<PoliciesVehiclesHistoric> findByPvsValorPrima(
			Object pvsValorPrima) {
		return findByProperty(PVS_VALOR_PRIMA, pvsValorPrima);
	}

	public List<PoliciesVehiclesHistoric> findByPvsValorTotal(
			Object pvsValorTotal) {
		return findByProperty(PVS_VALOR_TOTAL, pvsValorTotal);
	}

	public List<PoliciesVehiclesHistoric> findByPvsValorAsegurado(
			Object pvsValorAsegurado) {
		return findByProperty(PVS_VALOR_ASEGURADO, pvsValorAsegurado);
	}

	public List<PoliciesVehiclesHistoric> findByPvsCarnetAsignatario(
			Object pvsCarnetAsignatario) {
		return findByProperty(PVS_CARNET_ASIGNATARIO, pvsCarnetAsignatario);
	}

	/**
	 * Find all PoliciesVehiclesHistoric entities.
	 * 
	 * @return List<PoliciesVehiclesHistoric> all PoliciesVehiclesHistoric
	 *         entities
	 */
	@SuppressWarnings("unchecked")
	public List<PoliciesVehiclesHistoric> findAll() {
		EntityManagerHelper.log(
				"finding all PoliciesVehiclesHistoric instances", Level.INFO,
				null);
		try {
			final String queryString = "select model from PoliciesVehiclesHistoric model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}