package geniar.siscar.persistence;

import geniar.siscar.model.VhaAoaApp;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * VhaAoaApp entities. Transaction control of the save(), update() and delete()
 * operations must be handled externally by senders of these methods or must be
 * manually added to each of these methods for data to be persisted to the JPA
 * datastore.
 * 
 * @see modelo.VhaAoaApp
 * @author MyEclipse Persistence Tools
 */

public class VhaAoaAppDAO implements IVhaAoaAppDAO {
	// property constants
	public static final String AOA_OBSERVACION = "aoaObservacion";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved VhaAoaApp entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * VhaAoaAppDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            VhaAoaApp entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(VhaAoaApp entity) {
		EntityManagerHelper.log("saving VhaAoaApp instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent VhaAoaApp entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * VhaAoaAppDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            VhaAoaApp entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(VhaAoaApp entity) {
		EntityManagerHelper
				.log("deleting VhaAoaApp instance", Level.INFO, null);
		try {
			entity = getEntityManager().getReference(VhaAoaApp.class,
					entity.getVhaAoaAppId());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved VhaAoaApp entity and return it or a copy of it
	 * to the sender. A copy of the VhaAoaApp entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = VhaAoaAppDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            VhaAoaApp entity to update
	 * @returns VhaAoaApp the persisted VhaAoaApp entity instance, may not be
	 *          the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public VhaAoaApp update(VhaAoaApp entity) {
		EntityManagerHelper
				.log("updating VhaAoaApp instance", Level.INFO, null);
		try {
			VhaAoaApp result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public VhaAoaApp findById(Long id) {
		EntityManagerHelper.log("finding VhaAoaApp instance with id: " + id,
				Level.INFO, null);
		try {
			VhaAoaApp instance = getEntityManager().find(VhaAoaApp.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all VhaAoaApp entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the VhaAoaApp property to query
	 * @param value
	 *            the property value to match
	 * @return List<VhaAoaApp> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<VhaAoaApp> findByProperty(String propertyName,
			final Object value) {
		EntityManagerHelper.log("finding VhaAoaApp instance with property: "
				+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from VhaAoaApp model where model."
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

	public List<VhaAoaApp> findByAoaObservacion(Object aoaObservacion) {
		return findByProperty(AOA_OBSERVACION, aoaObservacion);
	}

	/**
	 * Find all VhaAoaApp entities.
	 * 
	 * @return List<VhaAoaApp> all VhaAoaApp entities
	 */
	@SuppressWarnings("unchecked")
	public List<VhaAoaApp> findAll() {
		EntityManagerHelper.log("finding all VhaAoaApp instances", Level.INFO,
				null);
		try {
			final String queryString = "select model from VhaAoaApp model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}