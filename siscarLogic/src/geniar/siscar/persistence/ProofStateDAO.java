package geniar.siscar.persistence;

import geniar.siscar.model.ProofState;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * ProofState entities. Transaction control of the save(), update() and delete()
 * operations must be handled externally by senders of these methods or must be
 * manually added to each of these methods for data to be persisted to the JPA
 * datastore.
 * 
 * @see modelo.ProofState
 * @author MyEclipse Persistence Tools
 */

public class ProofStateDAO implements IProofStateDAO {
	// property constants
	public static final String PRS_NOMBRE = "prsNombre";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved ProofState entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ProofStateDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            ProofState entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(ProofState entity) {
		EntityManagerHelper.log("saving ProofState instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent ProofState entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ProofStateDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            ProofState entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(ProofState entity) {
		EntityManagerHelper.log("deleting ProofState instance", Level.INFO,
				null);
		try {
			entity = getEntityManager().getReference(ProofState.class,
					entity.getPrsCodigo());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved ProofState entity and return it or a copy of
	 * it to the sender. A copy of the ProofState entity parameter is returned
	 * when the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = ProofStateDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            ProofState entity to update
	 * @returns ProofState the persisted ProofState entity instance, may not be
	 *          the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public ProofState update(ProofState entity) {
		EntityManagerHelper.log("updating ProofState instance", Level.INFO,
				null);
		try {
			ProofState result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public ProofState findById(Long id) {
		EntityManagerHelper.log("finding ProofState instance with id: " + id,
				Level.INFO, null);
		try {
			ProofState instance = getEntityManager().find(ProofState.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all ProofState entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the ProofState property to query
	 * @param value
	 *            the property value to match
	 * @return List<ProofState> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<ProofState> findByProperty(String propertyName,
			final Object value) {
		EntityManagerHelper.log("finding ProofState instance with property: "
				+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from ProofState model where model."
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

	public List<ProofState> findByPrsNombre(Object prsNombre) {
		return findByProperty(PRS_NOMBRE, prsNombre);
	}

	/**
	 * Find all ProofState entities.
	 * 
	 * @return List<ProofState> all ProofState entities
	 */
	@SuppressWarnings("unchecked")
	public List<ProofState> findAll() {
		EntityManagerHelper.log("finding all ProofState instances", Level.INFO,
				null);
		try {
			final String queryString = "select model from ProofState model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}