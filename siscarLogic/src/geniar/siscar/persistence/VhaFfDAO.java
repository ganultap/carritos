package geniar.siscar.persistence;

import geniar.siscar.model.VhaFf;

import java.util.List;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for VhaFf
 * entities. Transaction control of the save(), update() and delete() operations
 * must be handled externally by senders of these methods or must be manually
 * added to each of these methods for data to be persisted to the JPA datastore.
 * 
 * @see geniar.siscar.model.VhaFf
 * @author MyEclipse Persistence Tools
 */

public class VhaFfDAO implements IVhaFfDAO {
	// property constants
	public static final String VHF_OBSERVACION = "vhfObservacion";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved VhaFf entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * VhaFfDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            VhaFf entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(VhaFf entity) {
		EntityManagerHelper.log("saving VhaFf instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent VhaFf entity. This operation must be performed within
	 * the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * VhaFfDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            VhaFf entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(VhaFf entity) {
		EntityManagerHelper.log("deleting VhaFf instance", Level.INFO, null);
		try {
			entity = getEntityManager().getReference(VhaFf.class,
					entity.getVhfCodigo());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved VhaFf entity and return it or a copy of it to
	 * the sender. A copy of the VhaFf entity parameter is returned when the JPA
	 * persistence mechanism has not previously been tracking the updated
	 * entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = VhaFfDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            VhaFf entity to update
	 * @returns VhaFf the persisted VhaFf entity instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public VhaFf update(VhaFf entity) {
		EntityManagerHelper.log("updating VhaFf instance", Level.INFO, null);
		try {
			VhaFf result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public VhaFf findById(Long id) {
		EntityManagerHelper.log("finding VhaFf instance with id: " + id,
				Level.INFO, null);
		try {
			VhaFf instance = getEntityManager().find(VhaFf.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all VhaFf entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the VhaFf property to query
	 * @param value
	 *            the property value to match
	 * @return List<VhaFf> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<VhaFf> findByProperty(String propertyName, final Object value) {
		EntityManagerHelper.log("finding VhaFf instance with property: "
				+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from VhaFf model where model."
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

	public List<VhaFf> findByVhfObservacion(Object vhfObservacion) {
		return findByProperty(VHF_OBSERVACION, vhfObservacion);
	}

	/**
	 * Find all VhaFf entities.
	 * 
	 * @return List<VhaFf> all VhaFf entities
	 */
	@SuppressWarnings("unchecked")
	public List<VhaFf> findAll() {
		EntityManagerHelper
				.log("finding all VhaFf instances", Level.INFO, null);
		try {
			final String queryString = "select model from VhaFf model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}