package geniar.siscar.persistence;

import geniar.siscar.model.DescriptionType;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * DescriptionType entities. Transaction control of the save(), update() and
 * delete() operations must be handled externally by senders of these methods or
 * must be manually added to each of these methods for data to be persisted to
 * the JPA datastore.
 * 
 * @see geniar.siscar.model.DescriptionType
 * @author MyEclipse Persistence Tools
 */

public class DescriptionTypeDAO implements IDescriptionTypeDAO {
	// property constants
	public static final String DST_VALOR = "dstValor";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved DescriptionType entity.
	 * All subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * DescriptionTypeDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            DescriptionType entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(DescriptionType entity) {
		EntityManagerHelper.log("saving DescriptionType instance", Level.INFO,
				null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent DescriptionType entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * DescriptionTypeDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            DescriptionType entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(DescriptionType entity) {
		EntityManagerHelper.log("deleting DescriptionType instance",
				Level.INFO, null);
		try {
			entity = getEntityManager().getReference(DescriptionType.class,
					entity.getDstId());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved DescriptionType entity and return it or a copy
	 * of it to the sender. A copy of the DescriptionType entity parameter is
	 * returned when the JPA persistence mechanism has not previously been
	 * tracking the updated entity. This operation must be performed within the
	 * a database transaction context for the entity's data to be permanently
	 * saved to the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = DescriptionTypeDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            DescriptionType entity to update
	 * @return DescriptionType the persisted DescriptionType entity instance,
	 *         may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public DescriptionType update(DescriptionType entity) {
		EntityManagerHelper.log("updating DescriptionType instance",
				Level.INFO, null);
		try {
			DescriptionType result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public DescriptionType findById(Long id) {
		EntityManagerHelper.log("finding DescriptionType instance with id: "
				+ id, Level.INFO, null);
		try {
			DescriptionType instance = getEntityManager().find(
					DescriptionType.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all DescriptionType entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the DescriptionType property to query
	 * @param value
	 *            the property value to match
	 * @return List<DescriptionType> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<DescriptionType> findByProperty(String propertyName,
			final Object value) {
		EntityManagerHelper.log(
				"finding DescriptionType instance with property: "
						+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from DescriptionType model where model."
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

	public List<DescriptionType> findByDstValor(Object dstValor) {
		return findByProperty(DST_VALOR, dstValor);
	}

	/**
	 * Find all DescriptionType entities.
	 * 
	 * @return List<DescriptionType> all DescriptionType entities
	 */
	@SuppressWarnings("unchecked")
	public List<DescriptionType> findAll() {
		EntityManagerHelper.log("finding all DescriptionType instances",
				Level.INFO, null);
		try {
			final String queryString = "select model from DescriptionType model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}