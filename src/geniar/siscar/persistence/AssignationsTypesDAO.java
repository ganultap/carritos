package geniar.siscar.persistence;

import geniar.siscar.model.AssignationsTypes;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * AssignationsTypes entities. Transaction control of the save(), update() and
 * delete() operations must be handled externally by senders of these methods or
 * must be manually added to each of these methods for data to be persisted to
 * the JPA datastore.
 * 
 * @see geniar.siscar.model.AssignationsTypes
 * @author MyEclipse Persistence Tools
 */

public class AssignationsTypesDAO implements IAssignationsTypesDAO {
	// property constants
	public static final String AST_NOMBRE = "astNombre";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved AssignationsTypes entity.
	 * All subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * AssignationsTypesDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            AssignationsTypes entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(AssignationsTypes entity) {
		EntityManagerHelper.log("saving AssignationsTypes instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent AssignationsTypes entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * AssignationsTypesDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            AssignationsTypes entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(AssignationsTypes entity) {
		EntityManagerHelper.log("deleting AssignationsTypes instance", Level.INFO, null);
		try {
			entity = getEntityManager().getReference(AssignationsTypes.class, entity.getAstCodigo());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved AssignationsTypes entity and return it or a
	 * copy of it to the sender. A copy of the AssignationsTypes entity
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
	 * entity = AssignationsTypesDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            AssignationsTypes entity to update
	 * @returns AssignationsTypes the persisted AssignationsTypes entity
	 *          instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public AssignationsTypes update(AssignationsTypes entity) {
		EntityManagerHelper.log("updating AssignationsTypes instance", Level.INFO, null);
		try {
			AssignationsTypes result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public AssignationsTypes findById(Long id) {
		EntityManagerHelper.log("finding AssignationsTypes instance with id: " + id, Level.INFO, null);
		try {
			AssignationsTypes instance = getEntityManager().find(AssignationsTypes.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all AssignationsTypes entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the AssignationsTypes property to query
	 * @param value
	 *            the property value to match
	 * @return List<AssignationsTypes> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<AssignationsTypes> findByProperty(String propertyName, final Object value) {
		EntityManagerHelper.log("finding AssignationsTypes instance with property: " + propertyName + ", value: "
				+ value, Level.INFO, null);
		try {
			final String queryString = "select model from AssignationsTypes model where model." + propertyName
					+ "= :propertyValue";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("propertyValue", value);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find by property name failed", Level.SEVERE, re);
			throw re;
		}
	}

	public List<AssignationsTypes> findByAstNombre(Object astNombre) {
		return findByProperty(AST_NOMBRE, astNombre);
	}

	/**
	 * Find all AssignationsTypes entities.
	 * 
	 * @return List<AssignationsTypes> all AssignationsTypes entities
	 */
	@SuppressWarnings("unchecked")
	public List<AssignationsTypes> findAll() {
		EntityManagerHelper.log("finding all AssignationsTypes instances", Level.INFO, null);
		try {
			final String queryString = "select model from AssignationsTypes model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}