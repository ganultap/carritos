package geniar.siscar.persistence;

import geniar.siscar.model.Future;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * Future entities. Transaction control of the save(), update() and delete()
 * operations must be handled externally by senders of these methods or must be
 * manually added to each of these methods for data to be persisted to the JPA
 * datastore.
 * 
 * @see geniar.siscar.model.Future
 * @author MyEclipse Persistence Tools
 */

public class FutureDAO implements IFutureDAO {
	// property constants
	public static final String FRE_VALOR = "freValor";
	public static final String FRE_DESCRIPCION = "freDescripcion";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved Future entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * FutureDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Future entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Future entity) {
		EntityManagerHelper.log("saving Future instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent Future entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * FutureDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Future entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Future entity) {
		EntityManagerHelper.log("deleting Future instance", Level.INFO, null);
		try {
			entity = getEntityManager().getReference(Future.class,
					entity.getFreId());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved Future entity and return it or a copy of it to
	 * the sender. A copy of the Future entity parameter is returned when the
	 * JPA persistence mechanism has not previously been tracking the updated
	 * entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = FutureDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Future entity to update
	 * @return Future the persisted Future entity instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Future update(Future entity) {
		EntityManagerHelper.log("updating Future instance", Level.INFO, null);
		try {
			Future result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Future findById(Long id) {
		EntityManagerHelper.log("finding Future instance with id: " + id,
				Level.INFO, null);
		try {
			Future instance = getEntityManager().find(Future.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all Future entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Future property to query
	 * @param value
	 *            the property value to match
	 * @return List<Future> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Future> findByProperty(String propertyName, final Object value) {
		EntityManagerHelper.log("finding Future instance with property: "
				+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from Future model where model."
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

	public List<Future> findByFreValor(Object freValor) {
		return findByProperty(FRE_VALOR, freValor);
	}

	public List<Future> findByFreDescripcion(Object freDescripcion) {
		return findByProperty(FRE_DESCRIPCION, freDescripcion);
	}

	/**
	 * Find all Future entities.
	 * 
	 * @return List<Future> all Future entities
	 */
	@SuppressWarnings("unchecked")
	public List<Future> findAll() {
		EntityManagerHelper.log("finding all Future instances", Level.INFO,
				null);
		try {
			final String queryString = "select model from Future model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}