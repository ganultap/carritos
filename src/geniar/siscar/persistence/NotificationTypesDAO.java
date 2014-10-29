package geniar.siscar.persistence;

import geniar.siscar.model.NotificationTypes;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * NotificationTypes entities. Transaction control of the save(), update() and
 * delete() operations must be handled externally by senders of these methods or
 * must be manually added to each of these methods for data to be persisted to
 * the JPA datastore.
 * 
 * @see geniar.siscar.model.NotificationTypes
 * @author MyEclipse Persistence Tools
 */

public class NotificationTypesDAO implements INotificationTypesDAO {
	// property constants
	public static final String NTT_TEXTO = "nttTexto";
	public static final String NTT_DESTINATARIO = "nttDestinatario";
	public static final String NTT_SOLICITANTE = "nttSolicitante";
	public static final String NTF_NOMBRE = "ntfNombre";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved NotificationTypes entity.
	 * All subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * NotificationTypesDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            NotificationTypes entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(NotificationTypes entity) {
		EntityManagerHelper.log("saving NotificationTypes instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent NotificationTypes entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * NotificationTypesDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            NotificationTypes entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(NotificationTypes entity) {
		EntityManagerHelper.log("deleting NotificationTypes instance", Level.INFO, null);
		try {
			entity = getEntityManager().getReference(NotificationTypes.class, entity.getNttCodigo());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved NotificationTypes entity and return it or a
	 * copy of it to the sender. A copy of the NotificationTypes entity
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
	 * entity = NotificationTypesDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            NotificationTypes entity to update
	 * @returns NotificationTypes the persisted NotificationTypes entity
	 *          instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public NotificationTypes update(NotificationTypes entity) {
		EntityManagerHelper.log("updating NotificationTypes instance", Level.INFO, null);
		try {
			NotificationTypes result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public NotificationTypes findById(Long id) {
		EntityManagerHelper.log("finding NotificationTypes instance with id: " + id, Level.INFO, null);
		try {
			NotificationTypes instance = getEntityManager().find(NotificationTypes.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all NotificationTypes entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the NotificationTypes property to query
	 * @param value
	 *            the property value to match
	 * @return List<NotificationTypes> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<NotificationTypes> findByProperty(String propertyName, final Object value) {
		EntityManagerHelper.log("finding NotificationTypes instance with property: " + propertyName + ", value: "
				+ value, Level.INFO, null);
		try {
			final String queryString = "select model from NotificationTypes model where model." + propertyName
					+ "= :propertyValue";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("propertyValue", value);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find by property name failed", Level.SEVERE, re);
			throw re;
		}
	}

	public List<NotificationTypes> findByNttTexto(Object nttTexto) {
		return findByProperty(NTT_TEXTO, nttTexto);
	}

	public List<NotificationTypes> findByNttDestinatario(Object nttDestinatario) {
		return findByProperty(NTT_DESTINATARIO, nttDestinatario);
	}

	public List<NotificationTypes> findByNttSolicitante(Object nttSolicitante) {
		return findByProperty(NTT_SOLICITANTE, nttSolicitante);
	}

	public List<NotificationTypes> findByNtfNombre(Object ntfNombre) {
		return findByProperty(NTF_NOMBRE, ntfNombre);
	}

	/**
	 * Find all NotificationTypes entities.
	 * 
	 * @return List<NotificationTypes> all NotificationTypes entities
	 */
	@SuppressWarnings("unchecked")
	public List<NotificationTypes> findAll() {
		EntityManagerHelper.log("finding all NotificationTypes instances", Level.INFO, null);
		try {
			final String queryString = "select model from NotificationTypes model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}