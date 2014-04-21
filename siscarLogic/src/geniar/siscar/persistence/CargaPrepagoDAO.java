package geniar.siscar.persistence;

import geniar.siscar.model.CargaPrepago;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * CargaPrepago entities. Transaction control of the save(), update() and
 * delete() operations must be handled externally by senders of these methods or
 * must be manually added to each of these methods for data to be persisted to
 * the JPA datastore.
 * 
 * @see model.CargaPrepago
 * @author MyEclipse Persistence Tools
 */

public class CargaPrepagoDAO implements ICargaPrepagoDAO {
	// property constants
	public static final String PLACA = "placa";
	public static final String CONSUMO_PROMEDIO = "consumoPromedio";
	public static final String CENTRO_COSTO = "centroCosto";
	public static final String KM_ANUAL = "kmAnual";
	public static final String GALONES_ANO = "galonesAno";
	public static final String TIPO_COMBUSTIBLE = "tipoCombustible";
	public static final String VALOR_PREPAGO = "valorPrepago";
	public static final String TIPO_CARGO = "tipoCargo";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved CargaPrepago entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * CargaPrepagoDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            CargaPrepago entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(CargaPrepago entity) {
		EntityManagerHelper.log("saving CargaPrepago instance", Level.INFO,
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
	 * Delete a persistent CargaPrepago entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * CargaPrepagoDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            CargaPrepago entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(CargaPrepago entity) {
		EntityManagerHelper.log("deleting CargaPrepago instance", Level.INFO,
				null);
		try {
			entity = getEntityManager().getReference(CargaPrepago.class,
					entity.getIdCargaPrepago());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved CargaPrepago entity and return it or a copy of
	 * it to the sender. A copy of the CargaPrepago entity parameter is returned
	 * when the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = CargaPrepagoDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            CargaPrepago entity to update
	 * @return CargaPrepago the persisted CargaPrepago entity instance, may not
	 *         be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public CargaPrepago update(CargaPrepago entity) {
		EntityManagerHelper.log("updating CargaPrepago instance", Level.INFO,
				null);
		try {
			CargaPrepago result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public CargaPrepago findById(Long id) {
		EntityManagerHelper.log("finding CargaPrepago instance with id: " + id,
				Level.INFO, null);
		try {
			CargaPrepago instance = getEntityManager().find(CargaPrepago.class,
					id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all CargaPrepago entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the CargaPrepago property to query
	 * @param value
	 *            the property value to match
	 * @return List<CargaPrepago> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<CargaPrepago> findByProperty(String propertyName,
			final Object value) {
		EntityManagerHelper.log("finding CargaPrepago instance with property: "
				+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from CargaPrepago model where model."
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

	public List<CargaPrepago> findByPlaca(Object placa) {
		return findByProperty(PLACA, placa);
	}

	public List<CargaPrepago> findByConsumoPromedio(Object consumoPromedio) {
		return findByProperty(CONSUMO_PROMEDIO, consumoPromedio);
	}

	public List<CargaPrepago> findByCentroCosto(Object centroCosto) {
		return findByProperty(CENTRO_COSTO, centroCosto);
	}

	public List<CargaPrepago> findByKmAnual(Object kmAnual) {
		return findByProperty(KM_ANUAL, kmAnual);
	}

	public List<CargaPrepago> findByGalonesAno(Object galonesAno) {
		return findByProperty(GALONES_ANO, galonesAno);
	}

	public List<CargaPrepago> findByTipoCombustible(Object tipoCombustible) {
		return findByProperty(TIPO_COMBUSTIBLE, tipoCombustible);
	}

	public List<CargaPrepago> findByValorPrepago(Object valorPrepago) {
		return findByProperty(VALOR_PREPAGO, valorPrepago);
	}

	public List<CargaPrepago> findByTipoCargo(Object tipoCargo) {
		return findByProperty(TIPO_CARGO, tipoCargo);
	}

	/**
	 * Find all CargaPrepago entities.
	 * 
	 * @return List<CargaPrepago> all CargaPrepago entities
	 */
	@SuppressWarnings("unchecked")
	public List<CargaPrepago> findAll() {
		EntityManagerHelper.log("finding all CargaPrepago instances",
				Level.INFO, null);
		try {
			final String queryString = "select model from CargaPrepago model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}