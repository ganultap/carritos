package geniar.siscar.persistence;

import geniar.siscar.model.ControlAssignationPolicies;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * ControlAssignationPolicies entities. Transaction control of the save(),
 * update() and delete() operations must be handled externally by senders of
 * these methods or must be manually added to each of these methods for data to
 * be persisted to the JPA datastore.
 * 
 * @see geniar.siscar.model.ControlAssignationPolicies
 * @author MyEclipse Persistence Tools
 */

public class ControlAssignationPoliciesDAO implements
		IControlAssignationPoliciesDAO {
	// property constants

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved
	 * ControlAssignationPolicies entity. All subsequent persist actions of this
	 * entity should use the #update() method. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently saved to the persistence store, i.e., database. This method
	 * uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ControlAssignationPoliciesDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            ControlAssignationPolicies entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(ControlAssignationPolicies entity) {
		EntityManagerHelper.log("saving ControlAssignationPolicies instance",
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
	 * Delete a persistent ControlAssignationPolicies entity. This operation
	 * must be performed within the a database transaction context for the
	 * entity's data to be permanently deleted from the persistence store, i.e.,
	 * database. This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ControlAssignationPoliciesDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            ControlAssignationPolicies entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(ControlAssignationPolicies entity) {
		EntityManagerHelper.log("deleting ControlAssignationPolicies instance",
				Level.INFO, null);
		try {
			entity = getEntityManager().getReference(
					ControlAssignationPolicies.class, entity.getCapId());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved ControlAssignationPolicies entity and return
	 * it or a copy of it to the sender. A copy of the
	 * ControlAssignationPolicies entity parameter is returned when the JPA
	 * persistence mechanism has not previously been tracking the updated
	 * entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = ControlAssignationPoliciesDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            ControlAssignationPolicies entity to update
	 * @return ControlAssignationPolicies the persisted
	 *         ControlAssignationPolicies entity instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public ControlAssignationPolicies update(ControlAssignationPolicies entity) {
		EntityManagerHelper.log("updating ControlAssignationPolicies instance",
				Level.INFO, null);
		try {
			ControlAssignationPolicies result = getEntityManager()
					.merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public ControlAssignationPolicies findById(Long id) {
		EntityManagerHelper.log(
				"finding ControlAssignationPolicies instance with id: " + id,
				Level.INFO, null);
		try {
			ControlAssignationPolicies instance = getEntityManager().find(
					ControlAssignationPolicies.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all ControlAssignationPolicies entities with a specific property
	 * value.
	 * 
	 * @param propertyName
	 *            the name of the ControlAssignationPolicies property to query
	 * @param value
	 *            the property value to match
	 * @return List<ControlAssignationPolicies> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<ControlAssignationPolicies> findByProperty(String propertyName,
			final Object value) {
		EntityManagerHelper.log(
				"finding ControlAssignationPolicies instance with property: "
						+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from ControlAssignationPolicies model where model."
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

	/**
	 * Find all ControlAssignationPolicies entities.
	 * 
	 * @return List<ControlAssignationPolicies> all ControlAssignationPolicies
	 *         entities
	 */
	@SuppressWarnings("unchecked")
	public List<ControlAssignationPolicies> findAll() {
		EntityManagerHelper.log(
				"finding all ControlAssignationPolicies instances", Level.INFO,
				null);
		try {
			final String queryString = "select model from ControlAssignationPolicies model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}