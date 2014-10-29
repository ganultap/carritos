package geniar.siscar.persistence;

import geniar.siscar.model.TransactionType;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * TransactionType entities. Transaction control of the save(), update() and
 * delete() operations must be handled externally by senders of these methods or
 * must be manually added to each of these methods for data to be persisted to
 * the JPA datastore.
 * 
 * @see geniar.siscar.model.TransactionType
 * @author MyEclipse Persistence Tools
 */

public class TransactionTypeDAO implements ITransactionTypeDAO {
	// property constants
	public static final String TST_NOMBRE = "tstNombre";
	public static final String TST_DESCRIPCION = "tstDescripcion";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved TransactionType entity.
	 * All subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * TransactionTypeDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            TransactionType entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(TransactionType entity) {
		EntityManagerHelper.log("saving TransactionType instance", Level.INFO,
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
	 * Delete a persistent TransactionType entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * TransactionTypeDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            TransactionType entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(TransactionType entity) {
		EntityManagerHelper.log("deleting TransactionType instance",
				Level.INFO, null);
		try {
			entity = getEntityManager().getReference(TransactionType.class,
					entity.getTstId());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved TransactionType entity and return it or a copy
	 * of it to the sender. A copy of the TransactionType entity parameter is
	 * returned when the JPA persistence mechanism has not previously been
	 * tracking the updated entity. This operation must be performed within the
	 * a database transaction context for the entity's data to be permanently
	 * saved to the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = TransactionTypeDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            TransactionType entity to update
	 * @return TransactionType the persisted TransactionType entity instance,
	 *         may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public TransactionType update(TransactionType entity) {
		EntityManagerHelper.log("updating TransactionType instance",
				Level.INFO, null);
		try {
			TransactionType result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public TransactionType findById(Long id) {
		EntityManagerHelper.log("finding TransactionType instance with id: "
				+ id, Level.INFO, null);
		try {
			TransactionType instance = getEntityManager().find(
					TransactionType.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all TransactionType entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the TransactionType property to query
	 * @param value
	 *            the property value to match
	 * @return List<TransactionType> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<TransactionType> findByProperty(String propertyName,
			final Object value) {
		EntityManagerHelper.log(
				"finding TransactionType instance with property: "
						+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from TransactionType model where model."
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

	public List<TransactionType> findByTstNombre(Object tstNombre) {
		return findByProperty(TST_NOMBRE, tstNombre);
	}

	public List<TransactionType> findByTstDescripcion(Object tstDescripcion) {
		return findByProperty(TST_DESCRIPCION, tstDescripcion);
	}

	/**
	 * Find all TransactionType entities.
	 * 
	 * @return List<TransactionType> all TransactionType entities
	 */
	@SuppressWarnings("unchecked")
	public List<TransactionType> findAll() {
		EntityManagerHelper.log("finding all TransactionType instances",
				Level.INFO, null);
		try {
			final String queryString = "select model from TransactionType model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}