package geniar.siscar.persistence;
// default package


import geniar.siscar.model.Policies;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * Policies entities. Transaction control of the save(), update() and delete()
 * operations must be handled externally by senders of these methods or must be
 * manually added to each of these methods for data to be persisted to the JPA
 * datastore.
 * 
 * @see .Policies
 * @author MyEclipse Persistence Tools
 */

public class PoliciesDAO implements IPoliciesDAO {
	// property constants
	public static final String PLS_NUMERO = "plsNumero";
	public static final String PLS_DOC_UNO = "plsDocUno";
	public static final String PLS_DOC_DOS = "plsDocDos";
	public static final String PLS_ESTADO = "plsEstado";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved Policies entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * PoliciesDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Policies entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Policies entity) {
		EntityManagerHelper.log("saving Policies instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent Policies entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * PoliciesDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Policies entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Policies entity) {
		EntityManagerHelper.log("deleting Policies instance", Level.INFO, null);
		try {
			entity = getEntityManager().getReference(Policies.class,
					entity.getPlsCodigo());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved Policies entity and return it or a copy of it
	 * to the sender. A copy of the Policies entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = PoliciesDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Policies entity to update
	 * @returns Policies the persisted Policies entity instance, may not be the
	 *          same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Policies update(Policies entity) {
		EntityManagerHelper.log("updating Policies instance", Level.INFO, null);
		try {
			Policies result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Policies findById(Long id) {
		EntityManagerHelper.log("finding Policies instance with id: " + id,
				Level.INFO, null);
		try {
			Policies instance = getEntityManager().find(Policies.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all Policies entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Policies property to query
	 * @param value
	 *            the property value to match
	 * @return List<Policies> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Policies> findByProperty(String propertyName, final Object value) {
		EntityManagerHelper.log("finding Policies instance with property: "
				+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from Policies model where model."
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

	public List<Policies> findByPlsNumero(Object plsNumero) {
		return findByProperty(PLS_NUMERO, plsNumero);
	}

	public List<Policies> findByPlsDocUno(Object plsDocUno) {
		return findByProperty(PLS_DOC_UNO, plsDocUno);
	}

	public List<Policies> findByPlsDocDos(Object plsDocDos) {
		return findByProperty(PLS_DOC_DOS, plsDocDos);
	}

	public List<Policies> findByPlsEstado(Object plsEstado) {
		return findByProperty(PLS_ESTADO, plsEstado);
	}

	/**
	 * Find all Policies entities.
	 * 
	 * @return List<Policies> all Policies entities
	 */
	@SuppressWarnings("unchecked")
	public List<Policies> findAll() {
		EntityManagerHelper.log("finding all Policies instances", Level.INFO,
				null);
		try {
			final String queryString = "select model from Policies model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}