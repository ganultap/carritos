package geniar.siscar.persistence;

import geniar.siscar.model.ControlType;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * ControlType entities. Transaction control of the save(), update() and
 * delete() operations must be handled externally by senders of these methods or
 * must be manually added to each of these methods for data to be persisted to
 * the JPA datastore.
 * 
 * @see geniar.siscar.model.ControlType
 * @author MyEclipse Persistence Tools
 */

public class ControlTypeDAO implements IControlTypeDAO {
	// property constants
	public static final String CTT_VALOR = "cttValor";
	public static final String CTT_DESCRIPCION = "cttDescripcion";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved ControlType entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ControlTypeDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            ControlType entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(ControlType entity) {
		EntityManagerHelper
				.log("saving ControlType instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent ControlType entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ControlTypeDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            ControlType entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(ControlType entity) {
		EntityManagerHelper.log("deleting ControlType instance", Level.INFO,
				null);
		try {
			entity = getEntityManager().getReference(ControlType.class,
					entity.getCttId());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved ControlType entity and return it or a copy of
	 * it to the sender. A copy of the ControlType entity parameter is returned
	 * when the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = ControlTypeDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            ControlType entity to update
	 * @return ControlType the persisted ControlType entity instance, may not be
	 *         the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public ControlType update(ControlType entity) {
		EntityManagerHelper.log("updating ControlType instance", Level.INFO,
				null);
		try {
			ControlType result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public ControlType findById(Long id) {
		EntityManagerHelper.log("finding ControlType instance with id: " + id,
				Level.INFO, null);
		try {
			ControlType instance = getEntityManager().find(ControlType.class,
					id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all ControlType entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the ControlType property to query
	 * @param value
	 *            the property value to match
	 * @return List<ControlType> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<ControlType> findByProperty(String propertyName,
			final Object value) {
		EntityManagerHelper.log("finding ControlType instance with property: "
				+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from ControlType model where model."
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

	public List<ControlType> findByCttValor(Object cttValor) {
		return findByProperty(CTT_VALOR, cttValor);
	}

	public List<ControlType> findByCttDescripcion(Object cttDescripcion) {
		return findByProperty(CTT_DESCRIPCION, cttDescripcion);
	}

	/**
	 * Find all ControlType entities.
	 * 
	 * @return List<ControlType> all ControlType entities
	 */
	@SuppressWarnings("unchecked")
	public List<ControlType> findAll() {
		EntityManagerHelper.log("finding all ControlType instances",
				Level.INFO, null);
		try {
			final String queryString = "select model from ControlType model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}