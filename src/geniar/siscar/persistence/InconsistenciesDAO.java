package geniar.siscar.persistence;

//default package

import geniar.siscar.model.Inconsistencies;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * Inconsistencies entities. Transaction control of the save(), update() and
 * delete() operations must be handled externally by senders of these methods or
 * must be manually added to each of these methods for data to be persisted to
 * the JPA datastore.
 * 
 * @see .Inconsistencies
 * @author MyEclipse Persistence Tools
 */

public class InconsistenciesDAO implements IInconsistenciesDAO {
	// property constants
	public static final String INC_ESTADO = "incEstado";
	public static final String INC_OBSERVACION = "incObservacion";
	public static final String USR_LOGIN = "usrLogin";
	public static final String VHC_PLACA = "vhcPlaca";
	public static final String INC_VALOR_IVA = "incValorIva";
	public static final String INC_VALOR_PRIMA = "incValorPrima";
	public static final String INC_VALOR_TOTAL = "incValorTotal";
	public static final String INC_VALOR_ASEGURADO = "incValorAsegurado";
	public static final String PVS_CARNET_ASIGNATARIO = "pvsCarnetAsignatario";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved Inconsistencies entity.
	 * All subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * InconsistenciesDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Inconsistencies entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Inconsistencies entity) {
		EntityManagerHelper.log("saving Inconsistencies instance", Level.INFO,
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
	 * Delete a persistent Inconsistencies entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * InconsistenciesDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Inconsistencies entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Inconsistencies entity) {
		EntityManagerHelper.log("deleting Inconsistencies instance",
				Level.INFO, null);
		try {
			entity = getEntityManager().getReference(Inconsistencies.class,
					entity.getIncId());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved Inconsistencies entity and return it or a copy
	 * of it to the sender. A copy of the Inconsistencies entity parameter is
	 * returned when the JPA persistence mechanism has not previously been
	 * tracking the updated entity. This operation must be performed within the
	 * a database transaction context for the entity's data to be permanently
	 * saved to the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = InconsistenciesDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Inconsistencies entity to update
	 * @returns Inconsistencies the persisted Inconsistencies entity instance,
	 *          may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Inconsistencies update(Inconsistencies entity) {
		EntityManagerHelper.log("updating Inconsistencies instance",
				Level.INFO, null);
		try {
			Inconsistencies result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Inconsistencies findById(Long id) {
		EntityManagerHelper.log("finding Inconsistencies instance with id: "
				+ id, Level.INFO, null);
		try {
			Inconsistencies instance = getEntityManager().find(
					Inconsistencies.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all Inconsistencies entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Inconsistencies property to query
	 * @param value
	 *            the property value to match
	 * @return List<Inconsistencies> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Inconsistencies> findByProperty(String propertyName,
			final Object value) {
		EntityManagerHelper.log(
				"finding Inconsistencies instance with property: "
						+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from Inconsistencies model where model."
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

	public List<Inconsistencies> findByIncEstado(Object incEstado) {
		return findByProperty(INC_ESTADO, incEstado);
	}

	public List<Inconsistencies> findByIncObservacion(Object incObservacion) {
		return findByProperty(INC_OBSERVACION, incObservacion);
	}

	public List<Inconsistencies> findByUsrLogin(Object usrLogin) {
		return findByProperty(USR_LOGIN, usrLogin);
	}

	public List<Inconsistencies> findByVhcPlaca(Object vhcPlaca) {
		return findByProperty(VHC_PLACA, vhcPlaca);
	}

	public List<Inconsistencies> findByIncValorIva(Object incValorIva) {
		return findByProperty(INC_VALOR_IVA, incValorIva);
	}

	public List<Inconsistencies> findByIncValorPrima(Object incValorPrima) {
		return findByProperty(INC_VALOR_PRIMA, incValorPrima);
	}

	public List<Inconsistencies> findByIncValorTotal(Object incValorTotal) {
		return findByProperty(INC_VALOR_TOTAL, incValorTotal);
	}

	public List<Inconsistencies> findByIncValorAsegurado(
			Object incValorAsegurado) {
		return findByProperty(INC_VALOR_ASEGURADO, incValorAsegurado);
	}

	public List<Inconsistencies> findByPvsCarnetAsignatario(
			Object pvsCarnetAsignatario) {
		return findByProperty(PVS_CARNET_ASIGNATARIO, pvsCarnetAsignatario);
	}

	/**
	 * Find all Inconsistencies entities.
	 * 
	 * @return List<Inconsistencies> all Inconsistencies entities
	 */
	@SuppressWarnings("unchecked")
	public List<Inconsistencies> findAll() {
		EntityManagerHelper.log("finding all Inconsistencies instances",
				Level.INFO, null);
		try {
			final String queryString = "select model from Inconsistencies model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}