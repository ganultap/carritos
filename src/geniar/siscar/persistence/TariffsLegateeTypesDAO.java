package geniar.siscar.persistence;

import geniar.siscar.model.TariffsLegateeTypes;
import geniar.siscar.model.TariffsLegateeTypesId;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * TariffsLegateeTypes entities. Transaction control of the save(), update() and
 * delete() operations must be handled externally by senders of these methods or
 * must be manually added to each of these methods for data to be persisted to
 * the JPA datastore.
 * 
 * @see geniar.siscar.model.TariffsLegateeTypes
 * @author MyEclipse Persistence Tools
 */

public class TariffsLegateeTypesDAO implements ITariffsLegateeTypesDAO {
	// property constants

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved TariffsLegateeTypes
	 * entity. All subsequent persist actions of this entity should use the
	 * #update() method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * TariffsLegateeTypesDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            TariffsLegateeTypes entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(TariffsLegateeTypes entity) {
		EntityManagerHelper.log("saving TariffsLegateeTypes instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent TariffsLegateeTypes entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * TariffsLegateeTypesDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            TariffsLegateeTypes entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(TariffsLegateeTypes entity) {
		EntityManagerHelper.log("deleting TariffsLegateeTypes instance", Level.INFO, null);
		try {
			entity = getEntityManager().getReference(TariffsLegateeTypes.class, entity.getId());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved TariffsLegateeTypes entity and return it or a
	 * copy of it to the sender. A copy of the TariffsLegateeTypes entity
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
	 * entity = TariffsLegateeTypesDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            TariffsLegateeTypes entity to update
	 * @returns TariffsLegateeTypes the persisted TariffsLegateeTypes entity
	 *          instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public TariffsLegateeTypes update(TariffsLegateeTypes entity) {
		EntityManagerHelper.log("updating TariffsLegateeTypes instance", Level.INFO, null);
		try {
			TariffsLegateeTypes result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public TariffsLegateeTypes findById(TariffsLegateeTypesId id) {
		EntityManagerHelper.log("finding TariffsLegateeTypes instance with id: " + id, Level.INFO, null);
		try {
			TariffsLegateeTypes instance = getEntityManager().find(TariffsLegateeTypes.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all TariffsLegateeTypes entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the TariffsLegateeTypes property to query
	 * @param value
	 *            the property value to match
	 * @return List<TariffsLegateeTypes> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<TariffsLegateeTypes> findByProperty(String propertyName, final Object value) {
		EntityManagerHelper.log("finding TariffsLegateeTypes instance with property: " + propertyName + ", value: "
				+ value, Level.INFO, null);
		try {
			final String queryString = "select model from TariffsLegateeTypes model where model." + propertyName
					+ "= :propertyValue";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("propertyValue", value);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find by property name failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all TariffsLegateeTypes entities.
	 * 
	 * @return List<TariffsLegateeTypes> all TariffsLegateeTypes entities
	 */
	@SuppressWarnings("unchecked")
	public List<TariffsLegateeTypes> findAll() {
		EntityManagerHelper.log("finding all TariffsLegateeTypes instances", Level.INFO, null);
		try {
			final String queryString = "select model from TariffsLegateeTypes model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}