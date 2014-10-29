package geniar.siscar.persistence;

import geniar.siscar.model.CostsCentersNewness;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * CostsCentersNewness entities. Transaction control of the save(), update() and
 * delete() operations must be handled externally by senders of these methods or
 * must be manually added to each of these methods for data to be persisted to
 * the JPA datastore.
 * 
 * @see geniar.siscar.model.CostsCentersNewness
 * @author MyEclipse Persistence Tools
 */

public class CostsCentersNewnessDAO implements ICostsCentersNewnessDAO {
	// property constants
	public static final String CCN_DESCRIPCION = "ccnDescripcion";
	public static final String USR_LOGIN = "usrLogin";
	public static final String CCR_PORCENTAJE = "ccrPorcentaje";
	public static final String CCR_VALOR = "ccrValor";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved CostsCentersNewness
	 * entity. All subsequent persist actions of this entity should use the
	 * #update() method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * CostsCentersNewnessDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            CostsCentersNewness entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(CostsCentersNewness entity) {
		EntityManagerHelper.log("saving CostsCentersNewness instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent CostsCentersNewness entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * CostsCentersNewnessDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            CostsCentersNewness entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(CostsCentersNewness entity) {
		EntityManagerHelper.log("deleting CostsCentersNewness instance", Level.INFO, null);
		try {
			entity = getEntityManager().getReference(CostsCentersNewness.class, entity.getCocCodigo());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved CostsCentersNewness entity and return it or a
	 * copy of it to the sender. A copy of the CostsCentersNewness entity
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
	 * entity = CostsCentersNewnessDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            CostsCentersNewness entity to update
	 * @returns CostsCentersNewness the persisted CostsCentersNewness entity
	 *          instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public CostsCentersNewness update(CostsCentersNewness entity) {
		EntityManagerHelper.log("updating CostsCentersNewness instance", Level.INFO, null);
		try {
			CostsCentersNewness result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public CostsCentersNewness findById(Long id) {
		EntityManagerHelper.log("finding CostsCentersNewness instance with id: " + id, Level.INFO, null);
		try {
			CostsCentersNewness instance = getEntityManager().find(CostsCentersNewness.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all CostsCentersNewness entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the CostsCentersNewness property to query
	 * @param value
	 *            the property value to match
	 * @return List<CostsCentersNewness> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<CostsCentersNewness> findByProperty(String propertyName, final Object value) {
		EntityManagerHelper.log("finding CostsCentersNewness instance with property: " + propertyName + ", value: "
				+ value, Level.INFO, null);
		try {
			final String queryString = "select model from CostsCentersNewness model where model." + propertyName
					+ "= :propertyValue";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("propertyValue", value);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find by property name failed", Level.SEVERE, re);
			throw re;
		}
	}

	public List<CostsCentersNewness> findByCcnDescripcion(Object ccnDescripcion) {
		return findByProperty(CCN_DESCRIPCION, ccnDescripcion);
	}

	public List<CostsCentersNewness> findByUsrLogin(Object usrLogin) {
		return findByProperty(USR_LOGIN, usrLogin);
	}

	public List<CostsCentersNewness> findByCcrPorcentaje(Object ccrPorcentaje) {
		return findByProperty(CCR_PORCENTAJE, ccrPorcentaje);
	}

	public List<CostsCentersNewness> findByCcrValor(Object ccrValor) {
		return findByProperty(CCR_VALOR, ccrValor);
	}

	/**
	 * Find all CostsCentersNewness entities.
	 * 
	 * @return List<CostsCentersNewness> all CostsCentersNewness entities
	 */
	@SuppressWarnings("unchecked")
	public List<CostsCentersNewness> findAll() {
		EntityManagerHelper.log("finding all CostsCentersNewness instances", Level.INFO, null);
		try {
			final String queryString = "select model from CostsCentersNewness model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}