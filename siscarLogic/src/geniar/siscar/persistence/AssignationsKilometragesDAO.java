package geniar.siscar.persistence;

import geniar.siscar.model.AssignationsKilometrages;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * AssignationsKilometrages entities. Transaction control of the save(),
 * update() and delete() operations must be handled externally by senders of
 * these methods or must be manually added to each of these methods for data to
 * be persisted to the JPA datastore.
 * 
 * @see geniar.siscar.model.AssignationsKilometrages
 * @author MyEclipse Persistence Tools
 */

public class AssignationsKilometragesDAO implements IAssignationsKilometragesDAO {
	// property constants
	public static final String ASK_KILOM_ENTREGA = "askKilomEntrega";
	public static final String ASK_KILOM_DEV = "askKilomDev";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved AssignationsKilometrages
	 * entity. All subsequent persist actions of this entity should use the
	 * #update() method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * AssignationsKilometragesDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            AssignationsKilometrages entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(AssignationsKilometrages entity) {
		EntityManagerHelper.log("saving AssignationsKilometrages instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent AssignationsKilometrages entity. This operation must
	 * be performed within the a database transaction context for the entity's
	 * data to be permanently deleted from the persistence store, i.e.,
	 * database. This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * AssignationsKilometragesDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            AssignationsKilometrages entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(AssignationsKilometrages entity) {
		EntityManagerHelper.log("deleting AssignationsKilometrages instance", Level.INFO, null);
		try {
			entity = getEntityManager().getReference(AssignationsKilometrages.class, entity.getAskCodigo());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved AssignationsKilometrages entity and return it
	 * or a copy of it to the sender. A copy of the AssignationsKilometrages
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
	 * entity = AssignationsKilometragesDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            AssignationsKilometrages entity to update
	 * @returns AssignationsKilometrages the persisted AssignationsKilometrages
	 *          entity instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public AssignationsKilometrages update(AssignationsKilometrages entity) {
		EntityManagerHelper.log("updating AssignationsKilometrages instance", Level.INFO, null);
		try {
			AssignationsKilometrages result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public AssignationsKilometrages findById(Long id) {
		EntityManagerHelper.log("finding AssignationsKilometrages instance with id: " + id, Level.INFO, null);
		try {
			AssignationsKilometrages instance = getEntityManager().find(AssignationsKilometrages.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all AssignationsKilometrages entities with a specific property
	 * value.
	 * 
	 * @param propertyName
	 *            the name of the AssignationsKilometrages property to query
	 * @param value
	 *            the property value to match
	 * @return List<AssignationsKilometrages> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<AssignationsKilometrages> findByProperty(String propertyName, final Object value) {
		EntityManagerHelper.log("finding AssignationsKilometrages instance with property: " + propertyName
				+ ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from AssignationsKilometrages model where model." + propertyName
					+ "= :propertyValue";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("propertyValue", value);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find by property name failed", Level.SEVERE, re);
			throw re;
		}
	}

	public List<AssignationsKilometrages> findByAskKilomEntrega(Object askKilomEntrega) {
		return findByProperty(ASK_KILOM_ENTREGA, askKilomEntrega);
	}

	public List<AssignationsKilometrages> findByAskKilomDev(Object askKilomDev) {
		return findByProperty(ASK_KILOM_DEV, askKilomDev);
	}

	/**
	 * Find all AssignationsKilometrages entities.
	 * 
	 * @return List<AssignationsKilometrages> all AssignationsKilometrages
	 *         entities
	 */
	@SuppressWarnings("unchecked")
	public List<AssignationsKilometrages> findAll() {
		EntityManagerHelper.log("finding all AssignationsKilometrages instances", Level.INFO, null);
		try {
			final String queryString = "select model from AssignationsKilometrages model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}