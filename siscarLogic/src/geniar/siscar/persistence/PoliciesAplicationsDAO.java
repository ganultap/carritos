package geniar.siscar.persistence;

import geniar.siscar.model.PoliciesAplications;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * PoliciesAplications entities. Transaction control of the save(), update() and
 * delete() operations must be handled externally by senders of these methods or
 * must be manually added to each of these methods for data to be persisted to
 * the JPA datastore.
 * 
 * @see geniar.siscar.model.PoliciesAplications
 * @author MyEclipse Persistence Tools
 */

public class PoliciesAplicationsDAO implements IPoliciesAplicationsDAO {
	// property constants

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved PoliciesAplications
	 * entity. All subsequent persist actions of this entity should use the
	 * #update() method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * PoliciesAplicationsDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            PoliciesAplications entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(PoliciesAplications entity) {
		EntityManagerHelper.log("saving PoliciesAplications instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent PoliciesAplications entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * PoliciesAplicationsDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            PoliciesAplications entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(PoliciesAplications entity) {
		EntityManagerHelper.log("deleting PoliciesAplications instance", Level.INFO, null);
		try {
			entity = getEntityManager().getReference(PoliciesAplications.class, entity.getPolCodigo());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved PoliciesAplications entity and return it or a
	 * copy of it to the sender. A copy of the PoliciesAplications entity
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
	 * entity = PoliciesAplicationsDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            PoliciesAplications entity to update
	 * @returns PoliciesAplications the persisted PoliciesAplications entity
	 *          instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public PoliciesAplications update(PoliciesAplications entity) {
		EntityManagerHelper.log("updating PoliciesAplications instance", Level.INFO, null);
		try {
			PoliciesAplications result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public PoliciesAplications findById(Long id) {
		EntityManagerHelper.log("finding PoliciesAplications instance with id: " + id, Level.INFO, null);
		try {
			PoliciesAplications instance = getEntityManager().find(PoliciesAplications.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all PoliciesAplications entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the PoliciesAplications property to query
	 * @param value
	 *            the property value to match
	 * @return List<PoliciesAplications> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<PoliciesAplications> findByProperty(String propertyName, final Object value) {
		EntityManagerHelper.log("finding PoliciesAplications instance with property: " + propertyName + ", value: "
				+ value, Level.INFO, null);
		try {
			final String queryString = "select model from PoliciesAplications model where model." + propertyName
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
	 * Find all PoliciesAplications entities.
	 * 
	 * @return List<PoliciesAplications> all PoliciesAplications entities
	 */
	@SuppressWarnings("unchecked")
	public List<PoliciesAplications> findAll() {
		EntityManagerHelper.log("finding all PoliciesAplications instances", Level.INFO, null);
		try {
			final String queryString = "select model from PoliciesAplications model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}