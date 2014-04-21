package geniar.siscar.persistence;
// default package

import geniar.siscar.model.PoliciesHistoric;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * PoliciesHistoric entities. Transaction control of the save(), update() and
 * delete() operations must be handled externally by senders of these methods or
 * must be manually added to each of these methods for data to be persisted to
 * the JPA datastore.
 * 
 * @see .PoliciesHistoric
 * @author MyEclipse Persistence Tools
 */

public class PoliciesHistoricDAO implements IPoliciesHistoricDAO {
	// property constants
	public static final String USR_LOGIN = "usrLogin";
	public static final String PLS_NUMERO = "plsNumero";
	public static final String PLS_DOC_UNO = "plsDocUno";
	public static final String PLS_DOC_DOS = "plsDocDos";
	public static final String PLS_ESTADO = "plsEstado";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved PoliciesHistoric entity.
	 * All subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * PoliciesHistoricDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            PoliciesHistoric entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(PoliciesHistoric entity) {
		EntityManagerHelper.log("saving PoliciesHistoric instance", Level.INFO,
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
	 * Delete a persistent PoliciesHistoric entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * PoliciesHistoricDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            PoliciesHistoric entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(PoliciesHistoric entity) {
		EntityManagerHelper.log("deleting PoliciesHistoric instance",
				Level.INFO, null);
		try {
			entity = getEntityManager().getReference(PoliciesHistoric.class,
					entity.getPlhId());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved PoliciesHistoric entity and return it or a
	 * copy of it to the sender. A copy of the PoliciesHistoric entity parameter
	 * is returned when the JPA persistence mechanism has not previously been
	 * tracking the updated entity. This operation must be performed within the
	 * a database transaction context for the entity's data to be permanently
	 * saved to the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = PoliciesHistoricDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            PoliciesHistoric entity to update
	 * @returns PoliciesHistoric the persisted PoliciesHistoric entity instance,
	 *          may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public PoliciesHistoric update(PoliciesHistoric entity) {
		EntityManagerHelper.log("updating PoliciesHistoric instance",
				Level.INFO, null);
		try {
			PoliciesHistoric result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public PoliciesHistoric findById(Long id) {
		EntityManagerHelper.log("finding PoliciesHistoric instance with id: "
				+ id, Level.INFO, null);
		try {
			PoliciesHistoric instance = getEntityManager().find(
					PoliciesHistoric.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all PoliciesHistoric entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the PoliciesHistoric property to query
	 * @param value
	 *            the property value to match
	 * @return List<PoliciesHistoric> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<PoliciesHistoric> findByProperty(String propertyName,
			final Object value) {
		EntityManagerHelper.log(
				"finding PoliciesHistoric instance with property: "
						+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from PoliciesHistoric model where model."
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

	public List<PoliciesHistoric> findByUsrLogin(Object usrLogin) {
		return findByProperty(USR_LOGIN, usrLogin);
	}

	public List<PoliciesHistoric> findByPlsNumero(Object plsNumero) {
		return findByProperty(PLS_NUMERO, plsNumero);
	}

	public List<PoliciesHistoric> findByPlsDocUno(Object plsDocUno) {
		return findByProperty(PLS_DOC_UNO, plsDocUno);
	}

	public List<PoliciesHistoric> findByPlsDocDos(Object plsDocDos) {
		return findByProperty(PLS_DOC_DOS, plsDocDos);
	}

	public List<PoliciesHistoric> findByPlsEstado(Object plsEstado) {
		return findByProperty(PLS_ESTADO, plsEstado);
	}

	/**
	 * Find all PoliciesHistoric entities.
	 * 
	 * @return List<PoliciesHistoric> all PoliciesHistoric entities
	 */
	@SuppressWarnings("unchecked")
	public List<PoliciesHistoric> findAll() {
		EntityManagerHelper.log("finding all PoliciesHistoric instances",
				Level.INFO, null);
		try {
			final String queryString = "select model from PoliciesHistoric model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}