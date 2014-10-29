package geniar.siscar.persistence;

import geniar.siscar.model.Notifications;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * Notifications entities. Transaction control of the save(), update() and
 * delete() operations must be handled externally by senders of these methods or
 * must be manually added to each of these methods for data to be persisted to
 * the JPA datastore.
 * 
 * @see geniar.siscar.model.Notifications
 * @author MyEclipse Persistence Tools
 */

public class NotificationsDAO implements INotificationsDAO {
	// property constants

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved Notifications entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * NotificationsDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Notifications entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Notifications entity) {
		EntityManagerHelper.log("saving Notifications instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent Notifications entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * NotificationsDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Notifications entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Notifications entity) {
		EntityManagerHelper.log("deleting Notifications instance", Level.INFO, null);
		try {
			entity = getEntityManager().getReference(Notifications.class, entity.getNtfCodigo());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved Notifications entity and return it or a copy
	 * of it to the sender. A copy of the Notifications entity parameter is
	 * returned when the JPA persistence mechanism has not previously been
	 * tracking the updated entity. This operation must be performed within the
	 * a database transaction context for the entity's data to be permanently
	 * saved to the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = NotificationsDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Notifications entity to update
	 * @returns Notifications the persisted Notifications entity instance, may
	 *          not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Notifications update(Notifications entity) {
		EntityManagerHelper.log("updating Notifications instance", Level.INFO, null);
		try {
			Notifications result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Notifications findById(Long id) {
		EntityManagerHelper.log("finding Notifications instance with id: " + id, Level.INFO, null);
		try {
			Notifications instance = getEntityManager().find(Notifications.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all Notifications entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Notifications property to query
	 * @param value
	 *            the property value to match
	 * @return List<Notifications> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Notifications> findByProperty(String propertyName, final Object value) {
		EntityManagerHelper.log("finding Notifications instance with property: " + propertyName + ", value: " + value,
				Level.INFO, null);
		try {
			final String queryString = "select model from Notifications model where model." + propertyName
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
	 * Find all Notifications entities.
	 * 
	 * @return List<Notifications> all Notifications entities
	 */
	@SuppressWarnings("unchecked")
	public List<Notifications> findAll() {
		EntityManagerHelper.log("finding all Notifications instances", Level.INFO, null);
		try {
			final String queryString = "select model from Notifications model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}