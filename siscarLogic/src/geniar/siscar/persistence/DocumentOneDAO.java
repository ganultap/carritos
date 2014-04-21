package geniar.siscar.persistence;

import geniar.siscar.model.DocumentOne;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * DocumentOne entities. Transaction control of the save(), update() and
 * delete() operations must be handled externally by senders of these methods or
 * must be manually added to each of these methods for data to be persisted to
 * the JPA datastore.
 * 
 * @see geniar.siscar.model.DocumentOne
 * @author MyEclipse Persistence Tools
 */

public class DocumentOneDAO implements IDocumentOneDAO {
	// property constants
	public static final String DCO_NUMERO = "dcoNumero";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved DocumentOne entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * DocumentOneDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            DocumentOne entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(DocumentOne entity) {
		EntityManagerHelper
				.log("saving DocumentOne instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent DocumentOne entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * DocumentOneDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            DocumentOne entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(DocumentOne entity) {
		EntityManagerHelper.log("deleting DocumentOne instance", Level.INFO,
				null);
		try {
			entity = getEntityManager().getReference(DocumentOne.class,
					entity.getDcoId());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved DocumentOne entity and return it or a copy of
	 * it to the sender. A copy of the DocumentOne entity parameter is returned
	 * when the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = DocumentOneDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            DocumentOne entity to update
	 * @return DocumentOne the persisted DocumentOne entity instance, may not be
	 *         the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public DocumentOne update(DocumentOne entity) {
		EntityManagerHelper.log("updating DocumentOne instance", Level.INFO,
				null);
		try {
			DocumentOne result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public DocumentOne findById(Long id) {
		EntityManagerHelper.log("finding DocumentOne instance with id: " + id,
				Level.INFO, null);
		try {
			DocumentOne instance = getEntityManager().find(DocumentOne.class,
					id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all DocumentOne entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the DocumentOne property to query
	 * @param value
	 *            the property value to match
	 * @return List<DocumentOne> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<DocumentOne> findByProperty(String propertyName,
			final Object value) {
		EntityManagerHelper.log("finding DocumentOne instance with property: "
				+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from DocumentOne model where model."
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

	public List<DocumentOne> findByDcoNumero(Object dcoNumero) {
		return findByProperty(DCO_NUMERO, dcoNumero);
	}

	/**
	 * Find all DocumentOne entities.
	 * 
	 * @return List<DocumentOne> all DocumentOne entities
	 */
	@SuppressWarnings("unchecked")
	public List<DocumentOne> findAll() {
		EntityManagerHelper.log("finding all DocumentOne instances",
				Level.INFO, null);
		try {
			final String queryString = "select model from DocumentOne model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}