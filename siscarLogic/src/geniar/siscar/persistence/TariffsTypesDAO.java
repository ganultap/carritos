package geniar.siscar.persistence;

import geniar.siscar.model.TariffsTypes;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * TariffsTypes entities. Transaction control of the save(), update() and
 * delete() operations must be handled externally by senders of these methods or
 * must be manually added to each of these methods for data to be persisted to
 * the JPA datastore.
 * 
 * @see geniar.siscar.model.TariffsTypes
 * @author MyEclipse Persistence Tools
 */

public class TariffsTypesDAO implements ITariffsTypesDAO {
	// property constants
	public static final String TRT_NOMBRE = "trtNombre";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved TariffsTypes entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * TariffsTypesDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            TariffsTypes entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(TariffsTypes entity) {
		EntityManagerHelper.log("saving TariffsTypes instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent TariffsTypes entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * TariffsTypesDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            TariffsTypes entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(TariffsTypes entity) {
		EntityManagerHelper.log("deleting TariffsTypes instance", Level.INFO, null);
		try {
			entity = getEntityManager().getReference(TariffsTypes.class, entity.getTrtId());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved TariffsTypes entity and return it or a copy of
	 * it to the sender. A copy of the TariffsTypes entity parameter is returned
	 * when the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = TariffsTypesDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            TariffsTypes entity to update
	 * @returns TariffsTypes the persisted TariffsTypes entity instance, may not
	 *          be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public TariffsTypes update(TariffsTypes entity) {
		EntityManagerHelper.log("updating TariffsTypes instance", Level.INFO, null);
		try {
			TariffsTypes result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public TariffsTypes findById(Long id) {
		EntityManagerHelper.log("finding TariffsTypes instance with id: " + id, Level.INFO, null);
		try {
			TariffsTypes instance = getEntityManager().find(TariffsTypes.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all TariffsTypes entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the TariffsTypes property to query
	 * @param value
	 *            the property value to match
	 * @return List<TariffsTypes> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<TariffsTypes> findByProperty(String propertyName, final Object value) {
		EntityManagerHelper.log("finding TariffsTypes instance with property: " + propertyName + ", value: " + value,
				Level.INFO, null);
		try {
			final String queryString = "select model from TariffsTypes model where model." + propertyName
					+ "= :propertyValue";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("propertyValue", value);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find by property name failed", Level.SEVERE, re);
			throw re;
		}
	}

	public List<TariffsTypes> findByTrtNombre(Object trtNombre) {
		return findByProperty(TRT_NOMBRE, trtNombre);
	}

	/**
	 * Find all TariffsTypes entities.
	 * 
	 * @return List<TariffsTypes> all TariffsTypes entities
	 */
	@SuppressWarnings("unchecked")
	public List<TariffsTypes> findAll() {
		EntityManagerHelper.log("finding all TariffsTypes instances", Level.INFO, null);
		try {
			final String queryString = "select model from TariffsTypes model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}