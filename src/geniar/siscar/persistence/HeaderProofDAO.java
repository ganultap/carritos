package geniar.siscar.persistence;

import geniar.siscar.model.HeaderProof;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * HeaderProof entities. Transaction control of the save(), update() and
 * delete() operations must be handled externally by senders of these methods or
 * must be manually added to each of these methods for data to be persisted to
 * the JPA datastore.
 * 
 * @see geniar.siscar.model.HeaderProof
 * @author MyEclipse Persistence Tools
 */

public class HeaderProofDAO implements IHeaderProofDAO {
	// property constants
	public static final String HEP_GROUP_ID = "hepGroupId";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved HeaderProof entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * HeaderProofDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            HeaderProof entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(HeaderProof entity) {
		EntityManagerHelper.log("saving HeaderProof instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent HeaderProof entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * HeaderProofDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            HeaderProof entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(HeaderProof entity) {
		EntityManagerHelper.log("deleting HeaderProof instance", Level.INFO, null);
		try {
			entity = getEntityManager().getReference(HeaderProof.class, entity.getHepId());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved HeaderProof entity and return it or a copy of
	 * it to the sender. A copy of the HeaderProof entity parameter is returned
	 * when the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = HeaderProofDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            HeaderProof entity to update
	 * @returns HeaderProof the persisted HeaderProof entity instance, may not
	 *          be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public HeaderProof update(HeaderProof entity) {
		EntityManagerHelper.log("updating HeaderProof instance", Level.INFO, null);
		try {
			HeaderProof result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public HeaderProof findById(Long id) {
		EntityManagerHelper.log("finding HeaderProof instance with id: " + id, Level.INFO, null);
		try {
			HeaderProof instance = getEntityManager().find(HeaderProof.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all HeaderProof entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the HeaderProof property to query
	 * @param value
	 *            the property value to match
	 * @return List<HeaderProof> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<HeaderProof> findByProperty(String propertyName, final Object value) {
		EntityManagerHelper.log("finding HeaderProof instance with property: " + propertyName + ", value: " + value, Level.INFO,
				null);
		try {
			final String queryString = "select model from HeaderProof model where model." + propertyName + "= :propertyValue";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("propertyValue", value);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find by property name failed", Level.SEVERE, re);
			throw re;
		}
	}

	public List<HeaderProof> findByHepGroupId(Object hepGroupId) {
		return findByProperty(HEP_GROUP_ID, hepGroupId);
	}

	/**
	 * Find all HeaderProof entities.
	 * 
	 * @return List<HeaderProof> all HeaderProof entities
	 */
	@SuppressWarnings("unchecked")
	public List<HeaderProof> findAll() {
		EntityManagerHelper.log("finding all HeaderProof instances", Level.INFO, null);
		try {
			final String queryString = "select model from HeaderProof model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}