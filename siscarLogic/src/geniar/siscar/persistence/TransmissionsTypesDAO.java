package geniar.siscar.persistence;

import geniar.siscar.model.TransmissionsTypes;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * TransmissionsTypes entities. Transaction control of the save(), update() and
 * delete() operations must be handled externally by senders of these methods or
 * must be manually added to each of these methods for data to be persisted to
 * the JPA datastore.
 * 
 * @see geniar.siscar.model.TransmissionsTypes
 * @author MyEclipse Persistence Tools
 */

public class TransmissionsTypesDAO implements ITransmissionsTypesDAO {
	// property constants
	public static final String TNT_NOMBRE = "tntNombre";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved TransmissionsTypes
	 * entity. All subsequent persist actions of this entity should use the
	 * #update() method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * TransmissionsTypesDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            TransmissionsTypes entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(TransmissionsTypes entity) {
		EntityManagerHelper.log("saving TransmissionsTypes instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent TransmissionsTypes entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * TransmissionsTypesDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            TransmissionsTypes entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(TransmissionsTypes entity) {
		EntityManagerHelper.log("deleting TransmissionsTypes instance", Level.INFO, null);
		try {
			entity = getEntityManager().getReference(TransmissionsTypes.class, entity.getTntCodigo());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved TransmissionsTypes entity and return it or a
	 * copy of it to the sender. A copy of the TransmissionsTypes entity
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
	 * entity = TransmissionsTypesDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            TransmissionsTypes entity to update
	 * @returns TransmissionsTypes the persisted TransmissionsTypes entity
	 *          instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public TransmissionsTypes update(TransmissionsTypes entity) {
		EntityManagerHelper.log("updating TransmissionsTypes instance", Level.INFO, null);
		try {
			TransmissionsTypes result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public TransmissionsTypes findById(Long id) {
		EntityManagerHelper.log("finding TransmissionsTypes instance with id: " + id, Level.INFO, null);
		try {
			TransmissionsTypes instance = getEntityManager().find(TransmissionsTypes.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all TransmissionsTypes entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the TransmissionsTypes property to query
	 * @param value
	 *            the property value to match
	 * @return List<TransmissionsTypes> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<TransmissionsTypes> findByProperty(String propertyName, final Object value) {
		EntityManagerHelper.log("finding TransmissionsTypes instance with property: " + propertyName + ", value: "
				+ value, Level.INFO, null);
		try {
			final String queryString = "select model from TransmissionsTypes model where model." + propertyName
					+ "= :propertyValue";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("propertyValue", value);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find by property name failed", Level.SEVERE, re);
			throw re;
		}
	}

	public List<TransmissionsTypes> findByTntNombre(Object tntNombre) {
		return findByProperty(TNT_NOMBRE, tntNombre);
	}

	/**
	 * Find all TransmissionsTypes entities.
	 * 
	 * @return List<TransmissionsTypes> all TransmissionsTypes entities
	 */
	@SuppressWarnings("unchecked")
	public List<TransmissionsTypes> findAll() {
		EntityManagerHelper.log("finding all TransmissionsTypes instances", Level.INFO, null);
		try {
			final String queryString = "select model from TransmissionsTypes model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}