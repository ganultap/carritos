package geniar.siscar.persistence;

import geniar.siscar.model.DialyMovementPumps;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * DialyMovementPumps entities. Transaction control of the save(), update() and
 * delete() operations must be handled externally by senders of these methods or
 * must be manually added to each of these methods for data to be persisted to
 * the JPA datastore.
 * 
 * @see .DialyMovementPumps
 * @author MyEclipse Persistence Tools
 */

public class DialyMovementPumpsDAO implements IDialyMovementPumpsDAO {
	// property constants
	public static final String DMP_LECTURA = "dmpLectura";
	public static final String DMP_RECIBOS_DIA = "dmpRecibosDia";
	public static final String DMP_RECIBOS_NOCHE = "dmpRecibosNoche";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved DialyMovementPumps
	 * entity. All subsequent persist actions of this entity should use the
	 * #update() method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * DialyMovementPumpsDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            DialyMovementPumps entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(DialyMovementPumps entity) {
		EntityManagerHelper.log("saving DialyMovementPumps instance",
				Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent DialyMovementPumps entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * DialyMovementPumpsDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            DialyMovementPumps entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(DialyMovementPumps entity) {
		EntityManagerHelper.log("deleting DialyMovementPumps instance",
				Level.INFO, null);
		try {
			entity = getEntityManager().getReference(DialyMovementPumps.class,
					entity.getDmpCodigo());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved DialyMovementPumps entity and return it or a
	 * copy of it to the sender. A copy of the DialyMovementPumps entity
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
	 * entity = DialyMovementPumpsDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            DialyMovementPumps entity to update
	 * @returns DialyMovementPumps the persisted DialyMovementPumps entity
	 *          instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public DialyMovementPumps update(DialyMovementPumps entity) {
		EntityManagerHelper.log("updating DialyMovementPumps instance",
				Level.INFO, null);
		try {
			DialyMovementPumps result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public DialyMovementPumps findById(Long id) {
		EntityManagerHelper.log("finding DialyMovementPumps instance with id: "
				+ id, Level.INFO, null);
		try {
			DialyMovementPumps instance = getEntityManager().find(
					DialyMovementPumps.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all DialyMovementPumps entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the DialyMovementPumps property to query
	 * @param value
	 *            the property value to match
	 * @return List<DialyMovementPumps> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<DialyMovementPumps> findByProperty(String propertyName,
			final Object value) {
		EntityManagerHelper.log(
				"finding DialyMovementPumps instance with property: "
						+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from DialyMovementPumps model where model."
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

	public List<DialyMovementPumps> findByDmpLectura(Object dmpLectura) {
		return findByProperty(DMP_LECTURA, dmpLectura);
	}

	public List<DialyMovementPumps> findByDmpRecibosDia(Object dmpRecibosDia) {
		return findByProperty(DMP_RECIBOS_DIA, dmpRecibosDia);
	}

	public List<DialyMovementPumps> findByDmpRecibosNoche(Object dmpRecibosNoche) {
		return findByProperty(DMP_RECIBOS_NOCHE, dmpRecibosNoche);
	}

	/**
	 * Find all DialyMovementPumps entities.
	 * 
	 * @return List<DialyMovementPumps> all DialyMovementPumps entities
	 */
	@SuppressWarnings("unchecked")
	public List<DialyMovementPumps> findAll() {
		EntityManagerHelper.log("finding all DialyMovementPumps instances",
				Level.INFO, null);
		try {
			final String queryString = "select model from DialyMovementPumps model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}