package geniar.siscar.persistence;

import geniar.siscar.model.ProofType;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * ProofType entities. Transaction control of the save(), update() and delete()
 * operations must be handled externally by senders of these methods or must be
 * manually added to each of these methods for data to be persisted to the JPA
 * datastore.
 * 
 * @see geniar.siscar.model.ProofType
 * @author MyEclipse Persistence Tools
 */

public class ProofTypeDAO implements IProofTypeDAO {
	// property constants
	public static final String PRT_NOMBRE = "prtNombre";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved ProofType entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ProofTypeDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            ProofType entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(ProofType entity) {
		EntityManagerHelper.log("saving ProofType instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent ProofType entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ProofTypeDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            ProofType entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(ProofType entity) {
		EntityManagerHelper.log("deleting ProofType instance", Level.INFO, null);
		try {
			entity = getEntityManager().getReference(ProofType.class, entity.getPrtCodigo());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved ProofType entity and return it or a copy of it
	 * to the sender. A copy of the ProofType entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = ProofTypeDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            ProofType entity to update
	 * @returns ProofType the persisted ProofType entity instance, may not be
	 *          the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public ProofType update(ProofType entity) {
		EntityManagerHelper.log("updating ProofType instance", Level.INFO, null);
		try {
			ProofType result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public ProofType findById(Long id) {
		EntityManagerHelper.log("finding ProofType instance with id: " + id, Level.INFO, null);
		try {
			ProofType instance = getEntityManager().find(ProofType.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all ProofType entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the ProofType property to query
	 * @param value
	 *            the property value to match
	 * @return List<ProofType> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<ProofType> findByProperty(String propertyName, final Object value) {
		EntityManagerHelper.log("finding ProofType instance with property: " + propertyName + ", value: " + value, Level.INFO,
				null);
		try {
			final String queryString = "select model from ProofType model where model." + propertyName + "= :propertyValue";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("propertyValue", value);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find by property name failed", Level.SEVERE, re);
			throw re;
		}
	}

	public List<ProofType> findByPrtNombre(Object prtNombre) {
		return findByProperty(PRT_NOMBRE, prtNombre);
	}

	/**
	 * Find all ProofType entities.
	 * 
	 * @return List<ProofType> all ProofType entities
	 */
	@SuppressWarnings("unchecked")
	public List<ProofType> findAll() {
		EntityManagerHelper.log("finding all ProofType instances", Level.INFO, null);
		try {
			final String queryString = "select model from ProofType model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}