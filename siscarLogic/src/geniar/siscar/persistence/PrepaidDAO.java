package geniar.siscar.persistence;

import geniar.siscar.model.Prepaid;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * Prepaid entities. Transaction control of the save(), update() and delete()
 * operations must be handled externally by senders of these methods or must be
 * manually added to each of these methods for data to be persisted to the JPA
 * datastore.
 * 
 * @see modelo.Prepaid
 * @author MyEclipse Persistence Tools
 */

public class PrepaidDAO implements IPrepaidDAO {
	// property constants
	public static final String PRE_ASIGNATARIO = "preAsignatario";
	public static final String PRE_TOTAL = "preTotal";
	public static final String PRE_PLACA = "prePlaca";
	public static final String CHT_CODIGO = "chtCodigo";
	public static final String HEP_ID = "hepId";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved Prepaid entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * PrepaidDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Prepaid entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Prepaid entity) {
		EntityManagerHelper.log("saving Prepaid instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent Prepaid entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * PrepaidDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Prepaid entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Prepaid entity) {
		EntityManagerHelper.log("deleting Prepaid instance", Level.INFO, null);
		try {
			entity = getEntityManager().getReference(Prepaid.class,
					entity.getPreCodigo());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved Prepaid entity and return it or a copy of it
	 * to the sender. A copy of the Prepaid entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = PrepaidDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Prepaid entity to update
	 * @returns Prepaid the persisted Prepaid entity instance, may not be the
	 *          same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Prepaid update(Prepaid entity) {
		EntityManagerHelper.log("updating Prepaid instance", Level.INFO, null);
		try {
			Prepaid result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Prepaid findById(Long id) {
		EntityManagerHelper.log("finding Prepaid instance with id: " + id,
				Level.INFO, null);
		try {
			Prepaid instance = getEntityManager().find(Prepaid.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all Prepaid entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Prepaid property to query
	 * @param value
	 *            the property value to match
	 * @return List<Prepaid>
	 *  found by query
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List<Prepaid> findByProperty(String propertyName, final Object value) {
		EntityManagerHelper.log("finding Prepaid instance with property: "
				+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from Prepaid model where model."
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

	public List<Prepaid> findByPreAsignatario(Object preAsignatario) {
		return findByProperty(PRE_ASIGNATARIO, preAsignatario);
	}

	public List<Prepaid> findByPreTotal(Object preTotal) {
		return findByProperty(PRE_TOTAL, preTotal);
	}

	public List<Prepaid> findByPrePlaca(Object prePlaca) {
		return findByProperty(PRE_PLACA, prePlaca);
	}

	public List<Prepaid> findByChtCodigo(Object chtCodigo) {
		return findByProperty(CHT_CODIGO, chtCodigo);
	}

	public List<Prepaid> findByHepId(Object hepId) {
		return findByProperty(HEP_ID, hepId);
	}

	/**
	 * Find all Prepaid entities.
	 * 
	 * @return List<Prepaid>
	 *  all Prepaid entities
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List<Prepaid> findAll() {
		EntityManagerHelper.log("finding all Prepaid instances", Level.INFO,
				null);
		try {
			final String queryString = "select model from Prepaid model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}