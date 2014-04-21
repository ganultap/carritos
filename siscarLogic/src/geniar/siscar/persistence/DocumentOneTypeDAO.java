package geniar.siscar.persistence;

import geniar.siscar.model.DocumentOneType;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * DocumentOneType entities. Transaction control of the save(), update() and
 * delete() operations must be handled externally by senders of these methods or
 * must be manually added to each of these methods for data to be persisted to
 * the JPA datastore.
 * 
 * @see geniar.siscar.model.DocumentOneType
 * @author MyEclipse Persistence Tools
 */

public class DocumentOneTypeDAO implements IDocumentOneTypeDAO {
	// property constants
	public static final String DOT_NAME = "dotName";
	public static final String DOT_DESCRIPCION = "dotDescripcion";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved DocumentOneType entity.
	 * All subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * DocumentOneTypeDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            DocumentOneType entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(DocumentOneType entity) {
		EntityManagerHelper.log("saving DocumentOneType instance", Level.INFO,
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
	 * Delete a persistent DocumentOneType entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * DocumentOneTypeDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            DocumentOneType entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(DocumentOneType entity) {
		EntityManagerHelper.log("deleting DocumentOneType instance",
				Level.INFO, null);
		try {
			entity = getEntityManager().getReference(DocumentOneType.class,
					entity.getDotId());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved DocumentOneType entity and return it or a copy
	 * of it to the sender. A copy of the DocumentOneType entity parameter is
	 * returned when the JPA persistence mechanism has not previously been
	 * tracking the updated entity. This operation must be performed within the
	 * a database transaction context for the entity's data to be permanently
	 * saved to the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = DocumentOneTypeDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            DocumentOneType entity to update
	 * @return DocumentOneType the persisted DocumentOneType entity instance,
	 *         may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public DocumentOneType update(DocumentOneType entity) {
		EntityManagerHelper.log("updating DocumentOneType instance",
				Level.INFO, null);
		try {
			DocumentOneType result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public DocumentOneType findById(Long id) {
		EntityManagerHelper.log("finding DocumentOneType instance with id: "
				+ id, Level.INFO, null);
		try {
			DocumentOneType instance = getEntityManager().find(
					DocumentOneType.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all DocumentOneType entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the DocumentOneType property to query
	 * @param value
	 *            the property value to match
	 * @return List<DocumentOneType> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<DocumentOneType> findByProperty(String propertyName,
			final Object value) {
		EntityManagerHelper.log(
				"finding DocumentOneType instance with property: "
						+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from DocumentOneType model where model."
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

	public List<DocumentOneType> findByDotName(Object dotName) {
		return findByProperty(DOT_NAME, dotName);
	}

	public List<DocumentOneType> findByDotDescripcion(Object dotDescripcion) {
		return findByProperty(DOT_DESCRIPCION, dotDescripcion);
	}

	/**
	 * Find all DocumentOneType entities.
	 * 
	 * @return List<DocumentOneType> all DocumentOneType entities
	 */
	@SuppressWarnings("unchecked")
	public List<DocumentOneType> findAll() {
		EntityManagerHelper.log("finding all DocumentOneType instances",
				Level.INFO, null);
		try {
			final String queryString = "select model from DocumentOneType model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}