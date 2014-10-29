package geniar.siscar.persistence;

import geniar.siscar.model.Options;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * Options entities. Transaction control of the save(), update() and delete()
 * operations must be handled externally by senders of these methods or must be
 * manually added to each of these methods for data to be persisted to the JPA
 * datastore.
 * 
 * @see geniar.siscar.model.Options
 * @author MyEclipse Persistence Tools
 */

public class OptionsDAO implements IOptionsDAO {
	// property constants
	public static final String OPT_NOMBRE = "optNombre";
	public static final String OPT_LINK = "optLink";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved Options entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * OptionsDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Options entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Options entity) {
		EntityManagerHelper.log("saving Options instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent Options entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * OptionsDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Options entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Options entity) {
		EntityManagerHelper.log("deleting Options instance", Level.INFO, null);
		try {
			entity = getEntityManager().getReference(Options.class, entity.getOptCodigo());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved Options entity and return it or a copy of it
	 * to the sender. A copy of the Options entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = OptionsDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Options entity to update
	 * @returns Options the persisted Options entity instance, may not be the
	 *          same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Options update(Options entity) {
		EntityManagerHelper.log("updating Options instance", Level.INFO, null);
		try {
			Options result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Options findById(Long id) {
		EntityManagerHelper.log("finding Options instance with id: " + id, Level.INFO, null);
		try {
			Options instance = getEntityManager().find(Options.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all Options entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Options property to query
	 * @param value
	 *            the property value to match
	 * @return List<Options> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Options> findByProperty(String propertyName, final Object value) {
		EntityManagerHelper.log("finding Options instance with property: " + propertyName + ", value: " + value,
				Level.INFO, null);
		try {
			final String queryString = "select model from Options model where model." + propertyName
					+ "= :propertyValue";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("propertyValue", value);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find by property name failed", Level.SEVERE, re);
			throw re;
		}
	}

	public List<Options> findByOptNombre(Object optNombre) {
		return findByProperty(OPT_NOMBRE, optNombre);
	}

	public List<Options> findByOptLink(Object optLink) {
		return findByProperty(OPT_LINK, optLink);
	}

	/**
	 * Find all Options entities.
	 * 
	 * @return List<Options> all Options entities
	 */
	@SuppressWarnings("unchecked")
	public List<Options> findAll() {
		EntityManagerHelper.log("finding all Options instances", Level.INFO, null);
		try {
			final String queryString = "select model from Options model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}