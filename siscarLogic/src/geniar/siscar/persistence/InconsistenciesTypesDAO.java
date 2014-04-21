package geniar.siscar.persistence;

import geniar.siscar.model.InconsistenciesTypes;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * InconsistenciesTypes entities. Transaction control of the save(), update()
 * and delete() operations must be handled externally by senders of these
 * methods or must be manually added to each of these methods for data to be
 * persisted to the JPA datastore.
 * 
 * @see geniar.siscar.model.InconsistenciesTypes
 * @author MyEclipse Persistence Tools
 */

public class InconsistenciesTypesDAO implements IInconsistenciesTypesDAO {
	// property constants
	public static final String ICT_NOMBRE = "ictNombre";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved InconsistenciesTypes
	 * entity. All subsequent persist actions of this entity should use the
	 * #update() method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * InconsistenciesTypesDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            InconsistenciesTypes entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(InconsistenciesTypes entity) {
		EntityManagerHelper.log("saving InconsistenciesTypes instance",
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
	 * Delete a persistent InconsistenciesTypes entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * InconsistenciesTypesDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            InconsistenciesTypes entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(InconsistenciesTypes entity) {
		EntityManagerHelper.log("deleting InconsistenciesTypes instance",
				Level.INFO, null);
		try {
			entity = getEntityManager().getReference(
					InconsistenciesTypes.class, entity.getIctId());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved InconsistenciesTypes entity and return it or a
	 * copy of it to the sender. A copy of the InconsistenciesTypes entity
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
	 * entity = InconsistenciesTypesDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            InconsistenciesTypes entity to update
	 * @return InconsistenciesTypes the persisted InconsistenciesTypes entity
	 *         instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public InconsistenciesTypes update(InconsistenciesTypes entity) {
		EntityManagerHelper.log("updating InconsistenciesTypes instance",
				Level.INFO, null);
		try {
			InconsistenciesTypes result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public InconsistenciesTypes findById(Long id) {
		EntityManagerHelper.log(
				"finding InconsistenciesTypes instance with id: " + id,
				Level.INFO, null);
		try {
			InconsistenciesTypes instance = getEntityManager().find(
					InconsistenciesTypes.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all InconsistenciesTypes entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the InconsistenciesTypes property to query
	 * @param value
	 *            the property value to match
	 * @return List<InconsistenciesTypes> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<InconsistenciesTypes> findByProperty(String propertyName,
			final Object value) {
		EntityManagerHelper.log(
				"finding InconsistenciesTypes instance with property: "
						+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from InconsistenciesTypes model where model."
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

	public List<InconsistenciesTypes> findByIctNombre(Object ictNombre) {
		return findByProperty(ICT_NOMBRE, ictNombre);
	}

	/**
	 * Find all InconsistenciesTypes entities.
	 * 
	 * @return List<InconsistenciesTypes> all InconsistenciesTypes entities
	 */
	@SuppressWarnings("unchecked")
	public List<InconsistenciesTypes> findAll() {
		EntityManagerHelper.log("finding all InconsistenciesTypes instances",
				Level.INFO, null);
		try {
			final String queryString = "select model from InconsistenciesTypes model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}