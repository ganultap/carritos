package geniar.siscar.persistence;


import geniar.siscar.model.PrepaidConsumption;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * PrepaidConsumption entities. Transaction control of the save(), update() and
 * delete() operations must be handled externally by senders of these methods or
 * must be manually added to each of these methods for data to be persisted to
 * the JPA datastore.
 * 
 * @see .PrepaidConsumption
 * @author MyEclipse Persistence Tools
 */

public class PrepaidConsumptionDAO implements IPrepaidConsumptionDAO {
	// property constants
	public static final String PRC_HORA = "prcHora";
	public static final String PRC_VALOR_CONSUMO = "prcValorConsumo";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved PrepaidConsumption
	 * entity. All subsequent persist actions of this entity should use the
	 * #update() method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * PrepaidConsumptionDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            PrepaidConsumption entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(PrepaidConsumption entity) {
		EntityManagerHelper.log("saving PrepaidConsumption instance",
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
	 * Delete a persistent PrepaidConsumption entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * PrepaidConsumptionDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            PrepaidConsumption entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(PrepaidConsumption entity) {
		EntityManagerHelper.log("deleting PrepaidConsumption instance",
				Level.INFO, null);
		try {
			entity = getEntityManager().getReference(PrepaidConsumption.class,
					entity.getPrcCodigo());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved PrepaidConsumption entity and return it or a
	 * copy of it to the sender. A copy of the PrepaidConsumption entity
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
	 * entity = PrepaidConsumptionDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            PrepaidConsumption entity to update
	 * @returns PrepaidConsumption the persisted PrepaidConsumption entity
	 *          instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public PrepaidConsumption update(PrepaidConsumption entity) {
		EntityManagerHelper.log("updating PrepaidConsumption instance",
				Level.INFO, null);
		try {
			PrepaidConsumption result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public PrepaidConsumption findById(Long id) {
		EntityManagerHelper.log("finding PrepaidConsumption instance with id: "
				+ id, Level.INFO, null);
		try {
			PrepaidConsumption instance = getEntityManager().find(
					PrepaidConsumption.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all PrepaidConsumption entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the PrepaidConsumption property to query
	 * @param value
	 *            the property value to match
	 * @return List<PrepaidConsumption>
	 *  found by query
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List<PrepaidConsumption> findByProperty(String propertyName,
			final Object value) {
		EntityManagerHelper.log(
				"finding PrepaidConsumption instance with property: "
						+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from PrepaidConsumption model where model."
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

	public List<PrepaidConsumption> findByPrcHora(Object prcHora) {
		return findByProperty(PRC_HORA, prcHora);
	}

	public List<PrepaidConsumption> findByPrcValorConsumo(Object prcValorConsumo) {
		return findByProperty(PRC_VALOR_CONSUMO, prcValorConsumo);
	}

	/**
	 * Find all PrepaidConsumption entities.
	 * 
	 * @return List<PrepaidConsumption>
	 *  all PrepaidConsumption entities
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List<PrepaidConsumption> findAll() {
		EntityManagerHelper.log("finding all PrepaidConsumption instances",
				Level.INFO, null);
		try {
			final String queryString = "select model from PrepaidConsumption model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}