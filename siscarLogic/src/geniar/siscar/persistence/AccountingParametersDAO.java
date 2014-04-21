package geniar.siscar.persistence;

import geniar.siscar.model.AccountingParameters;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * AccountingParameters entities. Transaction control of the save(), update()
 * and delete() operations must be handled externally by senders of these
 * methods or must be manually added to each of these methods for data to be
 * persisted to the JPA datastore.
 * 
 * @see geniar.siscar.model.AccountingParameters
 * @author MyEclipse Persistence Tools
 */

public class AccountingParametersDAO implements IAccountingParametersDAO {
	// property constants
	public static final String ACC_DESCRIPCION = "accDescripcion";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved AccountingParameters
	 * entity. All subsequent persist actions of this entity should use the
	 * #update() method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * AccountingParametersDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            AccountingParameters entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(AccountingParameters entity) {
		EntityManagerHelper.log("saving AccountingParameters instance",
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
	 * Delete a persistent AccountingParameters entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * AccountingParametersDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            AccountingParameters entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(AccountingParameters entity) {
		EntityManagerHelper.log("deleting AccountingParameters instance",
				Level.INFO, null);
		try {
			entity = getEntityManager().getReference(
					AccountingParameters.class, entity.getAcpId());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved AccountingParameters entity and return it or a
	 * copy of it to the sender. A copy of the AccountingParameters entity
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
	 * entity = AccountingParametersDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            AccountingParameters entity to update
	 * @return AccountingParameters the persisted AccountingParameters entity
	 *         instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public AccountingParameters update(AccountingParameters entity) {
		EntityManagerHelper.log("updating AccountingParameters instance",
				Level.INFO, null);
		try {
			AccountingParameters result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public AccountingParameters findById(Long id) {
		EntityManagerHelper.log(
				"finding AccountingParameters instance with id: " + id,
				Level.INFO, null);
		try {
			AccountingParameters instance = getEntityManager().find(
					AccountingParameters.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all AccountingParameters entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the AccountingParameters property to query
	 * @param value
	 *            the property value to match
	 * @return List<AccountingParameters> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<AccountingParameters> findByProperty(String propertyName,
			final Object value) {
		EntityManagerHelper.log(
				"finding AccountingParameters instance with property: "
						+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from AccountingParameters model where model."
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

	public List<AccountingParameters> findByAccDescripcion(Object accDescripcion) {
		return findByProperty(ACC_DESCRIPCION, accDescripcion);
	}

	/**
	 * Find all AccountingParameters entities.
	 * 
	 * @return List<AccountingParameters> all AccountingParameters entities
	 */
	@SuppressWarnings("unchecked")
	public List<AccountingParameters> findAll() {
		EntityManagerHelper.log("finding all AccountingParameters instances",
				Level.INFO, null);
		try {
			final String queryString = "select model from AccountingParameters model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}