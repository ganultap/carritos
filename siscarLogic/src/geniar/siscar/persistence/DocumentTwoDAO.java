package geniar.siscar.persistence;

import geniar.siscar.model.DocumentTwo;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * DocumentTwo entities. Transaction control of the save(), update() and
 * delete() operations must be handled externally by senders of these methods or
 * must be manually added to each of these methods for data to be persisted to
 * the JPA datastore.
 * 
 * @see geniar.siscar.model.DocumentTwo
 * @author MyEclipse Persistence Tools
 */

public class DocumentTwoDAO implements IDocumentTwoDAO {
	// property constants
	public static final String DCT_NUMERO = "dctNumero";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved DocumentTwo entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * DocumentTwoDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            DocumentTwo entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(DocumentTwo entity) {
		EntityManagerHelper
				.log("saving DocumentTwo instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent DocumentTwo entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * DocumentTwoDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            DocumentTwo entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(DocumentTwo entity) {
		EntityManagerHelper.log("deleting DocumentTwo instance", Level.INFO,
				null);
		try {
			entity = getEntityManager().getReference(DocumentTwo.class,
					entity.getDctId());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved DocumentTwo entity and return it or a copy of
	 * it to the sender. A copy of the DocumentTwo entity parameter is returned
	 * when the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = DocumentTwoDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            DocumentTwo entity to update
	 * @return DocumentTwo the persisted DocumentTwo entity instance, may not be
	 *         the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public DocumentTwo update(DocumentTwo entity) {
		EntityManagerHelper.log("updating DocumentTwo instance", Level.INFO,
				null);
		try {
			DocumentTwo result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public DocumentTwo findById(Long id) {
		EntityManagerHelper.log("finding DocumentTwo instance with id: " + id,
				Level.INFO, null);
		try {
			DocumentTwo instance = getEntityManager().find(DocumentTwo.class,
					id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all DocumentTwo entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the DocumentTwo property to query
	 * @param value
	 *            the property value to match
	 * @return List<DocumentTwo> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<DocumentTwo> findByProperty(String propertyName,
			final Object value) {
		EntityManagerHelper.log("finding DocumentTwo instance with property: "
				+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from DocumentTwo model where model."
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

	public List<DocumentTwo> findByDctNumero(Object dctNumero) {
		return findByProperty(DCT_NUMERO, dctNumero);
	}

	/**
	 * Find all DocumentTwo entities.
	 * 
	 * @return List<DocumentTwo> all DocumentTwo entities
	 */
	@SuppressWarnings("unchecked")
	public List<DocumentTwo> findAll() {
		EntityManagerHelper.log("finding all DocumentTwo instances",
				Level.INFO, null);
		try {
			final String queryString = "select model from DocumentTwo model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}