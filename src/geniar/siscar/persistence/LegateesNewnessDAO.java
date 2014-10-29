package geniar.siscar.persistence;

import geniar.siscar.model.LegateesNewness;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * LegateesNewness entities. Transaction control of the save(), update() and
 * delete() operations must be handled externally by senders of these methods or
 * must be manually added to each of these methods for data to be persisted to
 * the JPA datastore.
 * 
 * @see geniar.siscar.model.LegateesNewness
 * @author MyEclipse Persistence Tools
 */

public class LegateesNewnessDAO implements ILegateesNewnessDAO {
	// property constants
	public static final String LGN_DESCRIPCION = "lgnDescripcion";
	public static final String USR_LOGIN = "usrLogin";
	public static final String LGN_CARNE_ASIGNATARIO = "lgnCarneAsignatario";
	public static final String LGN_CARNE_ASISTENTE = "lgnCarneAsistente";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved LegateesNewness entity.
	 * All subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * LegateesNewnessDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            LegateesNewness entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(LegateesNewness entity) {
		EntityManagerHelper.log("saving LegateesNewness instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent LegateesNewness entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * LegateesNewnessDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            LegateesNewness entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(LegateesNewness entity) {
		EntityManagerHelper.log("deleting LegateesNewness instance", Level.INFO, null);
		try {
			entity = getEntityManager().getReference(LegateesNewness.class, entity.getLgnCodigo());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved LegateesNewness entity and return it or a copy
	 * of it to the sender. A copy of the LegateesNewness entity parameter is
	 * returned when the JPA persistence mechanism has not previously been
	 * tracking the updated entity. This operation must be performed within the
	 * a database transaction context for the entity's data to be permanently
	 * saved to the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = LegateesNewnessDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            LegateesNewness entity to update
	 * @returns LegateesNewness the persisted LegateesNewness entity instance,
	 *          may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public LegateesNewness update(LegateesNewness entity) {
		EntityManagerHelper.log("updating LegateesNewness instance", Level.INFO, null);
		try {
			LegateesNewness result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public LegateesNewness findById(Long id) {
		EntityManagerHelper.log("finding LegateesNewness instance with id: " + id, Level.INFO, null);
		try {
			LegateesNewness instance = getEntityManager().find(LegateesNewness.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all LegateesNewness entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the LegateesNewness property to query
	 * @param value
	 *            the property value to match
	 * @return List<LegateesNewness> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<LegateesNewness> findByProperty(String propertyName, final Object value) {
		EntityManagerHelper.log(
				"finding LegateesNewness instance with property: " + propertyName + ", value: " + value, Level.INFO,
				null);
		try {
			final String queryString = "select model from LegateesNewness model where model." + propertyName
					+ "= :propertyValue";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("propertyValue", value);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find by property name failed", Level.SEVERE, re);
			throw re;
		}
	}

	public List<LegateesNewness> findByLgnDescripcion(Object lgnDescripcion) {
		return findByProperty(LGN_DESCRIPCION, lgnDescripcion);
	}

	public List<LegateesNewness> findByUsrLogin(Object usrLogin) {
		return findByProperty(USR_LOGIN, usrLogin);
	}

	public List<LegateesNewness> findByLgnCarneAsignatario(Object lgnCarneAsignatario) {
		return findByProperty(LGN_CARNE_ASIGNATARIO, lgnCarneAsignatario);
	}

	public List<LegateesNewness> findByLgnCarneAsistente(Object lgnCarneAsistente) {
		return findByProperty(LGN_CARNE_ASISTENTE, lgnCarneAsistente);
	}

	/**
	 * Find all LegateesNewness entities.
	 * 
	 * @return List<LegateesNewness> all LegateesNewness entities
	 */
	@SuppressWarnings("unchecked")
	public List<LegateesNewness> findAll() {
		EntityManagerHelper.log("finding all LegateesNewness instances", Level.INFO, null);
		try {
			final String queryString = "select model from LegateesNewness model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}