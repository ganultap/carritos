package geniar.siscar.persistence;

import geniar.siscar.model.DocumentTwoType;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * DocumentTwoType entities. Transaction control of the save(), update() and
 * delete() operations must be handled externally by senders of these methods or
 * must be manually added to each of these methods for data to be persisted to
 * the JPA datastore.
 * 
 * @see geniar.siscar.model.DocumentTwoType
 * @author MyEclipse Persistence Tools
 */

public class DocumentTwoTypeDAO implements IDocumentTwoTypeDAO {
	// property constants
	public static final String DTT_NAME = "dttName";
	public static final String DTT_DESCRIPCION = "dttDescripcion";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved DocumentTwoType entity.
	 * All subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * DocumentTwoTypeDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            DocumentTwoType entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(DocumentTwoType entity) {
		EntityManagerHelper.log("saving DocumentTwoType instance", Level.INFO,
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
	 * Delete a persistent DocumentTwoType entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * DocumentTwoTypeDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            DocumentTwoType entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(DocumentTwoType entity) {
		EntityManagerHelper.log("deleting DocumentTwoType instance",
				Level.INFO, null);
		try {
			entity = getEntityManager().getReference(DocumentTwoType.class,
					entity.getDttId());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved DocumentTwoType entity and return it or a copy
	 * of it to the sender. A copy of the DocumentTwoType entity parameter is
	 * returned when the JPA persistence mechanism has not previously been
	 * tracking the updated entity. This operation must be performed within the
	 * a database transaction context for the entity's data to be permanently
	 * saved to the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = DocumentTwoTypeDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            DocumentTwoType entity to update
	 * @return DocumentTwoType the persisted DocumentTwoType entity instance,
	 *         may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public DocumentTwoType update(DocumentTwoType entity) {
		EntityManagerHelper.log("updating DocumentTwoType instance",
				Level.INFO, null);
		try {
			DocumentTwoType result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public DocumentTwoType findById(Long id) {
		EntityManagerHelper.log("finding DocumentTwoType instance with id: "
				+ id, Level.INFO, null);
		try {
			DocumentTwoType instance = getEntityManager().find(
					DocumentTwoType.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all DocumentTwoType entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the DocumentTwoType property to query
	 * @param value
	 *            the property value to match
	 * @return List<DocumentTwoType> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<DocumentTwoType> findByProperty(String propertyName,
			final Object value) {
		EntityManagerHelper.log(
				"finding DocumentTwoType instance with property: "
						+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from DocumentTwoType model where model."
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

	public List<DocumentTwoType> findByDttName(Object dttName) {
		return findByProperty(DTT_NAME, dttName);
	}

	public List<DocumentTwoType> findByDttDescripcion(Object dttDescripcion) {
		return findByProperty(DTT_DESCRIPCION, dttDescripcion);
	}

	/**
	 * Find all DocumentTwoType entities.
	 * 
	 * @return List<DocumentTwoType> all DocumentTwoType entities
	 */
	@SuppressWarnings("unchecked")
	public List<DocumentTwoType> findAll() {
		EntityManagerHelper.log("finding all DocumentTwoType instances",
				Level.INFO, null);
		try {
			final String queryString = "select model from DocumentTwoType model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}