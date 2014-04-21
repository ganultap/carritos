package geniar.siscar.persistence;

import geniar.siscar.model.Colors;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * Colors entities. Transaction control of the save(), update() and delete()
 * operations must be handled externally by senders of these methods or must be
 * manually added to each of these methods for data to be persisted to the JPA
 * datastore.
 * 
 * @see geniar.siscar.model.Colors
 * @author MyEclipse Persistence Tools
 */

public class ColorsDAO implements IColorsDAO {
	// property constants
	public static final String CL_NOMBRE = "clNombre";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved Colors entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ColorsDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Colors entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Colors entity) {
		EntityManagerHelper.log("saving Colors instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent Colors entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ColorsDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Colors entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Colors entity) {
		EntityManagerHelper.log("deleting Colors instance", Level.INFO, null);
		try {
			entity = getEntityManager().getReference(Colors.class, entity.getClCodigo());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved Colors entity and return it or a copy of it to
	 * the sender. A copy of the Colors entity parameter is returned when the
	 * JPA persistence mechanism has not previously been tracking the updated
	 * entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = ColorsDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Colors entity to update
	 * @returns Colors the persisted Colors entity instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Colors update(Colors entity) {
		EntityManagerHelper.log("updating Colors instance", Level.INFO, null);
		try {
			Colors result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Colors findById(Long id) {
		EntityManagerHelper.log("finding Colors instance with id: " + id, Level.INFO, null);
		try {
			Colors instance = getEntityManager().find(Colors.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all Colors entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Colors property to query
	 * @param value
	 *            the property value to match
	 * @return List<Colors> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Colors> findByProperty(String propertyName, final Object value) {
		EntityManagerHelper.log("finding Colors instance with property: " + propertyName + ", value: " + value,
				Level.INFO, null);
		try {
			final String queryString = "select model from Colors model where model." + propertyName
					+ "= :propertyValue";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("propertyValue", value);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find by property name failed", Level.SEVERE, re);
			throw re;
		}
	}

	public List<Colors> findByClNombre(Object clNombre) {
		return findByProperty(CL_NOMBRE, clNombre);
	}

	/**
	 * Find all Colors entities.
	 * 
	 * @return List<Colors> all Colors entities
	 */
	@SuppressWarnings("unchecked")
	public List<Colors> findAll() {
		EntityManagerHelper.log("finding all Colors instances", Level.INFO, null);
		try {
			final String queryString = "select model from Colors model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}