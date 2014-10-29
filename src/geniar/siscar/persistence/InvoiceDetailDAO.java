package geniar.siscar.persistence;

import geniar.siscar.model.InvoiceDetail;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * InvoiceDetail entities. Transaction control of the save(), update() and
 * delete() operations must be handled externally by senders of these methods or
 * must be manually added to each of these methods for data to be persisted to
 * the JPA datastore.
 * 
 * @see geniar.siscar.model.InvoiceDetail
 * @author MyEclipse Persistence Tools
 */

public class InvoiceDetailDAO implements IInvoiceDetailDAO {
	// property constants
	public static final String _PINVOICE_ID = "PInvoiceId";
	public static final String _PLINE_NUM = "PLineNum";
	public static final String _PINVOICE_NUM = "PInvoiceNum";
	public static final String _PINVOICE_AMOUNT = "PInvoiceAmount";
	public static final String _PDESCRIPTION = "PDescription";
	public static final String _PUSER_ID = "PUserId";
	public static final String _PPLACA_VEH = "PPlacaVeh";
	public static final String _PCOMPANY = "PCompany";
	public static final String _PACCOUNT = "PAccount";
	public static final String _PCCENTER = "PCcenter";
	public static final String _PAUXILIARY = "PAuxiliary";
	public static final String _PORG_ID = "POrgId";
	public static final String _PLOCATION = "PLocation";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved InvoiceDetail entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * InvoiceDetailDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            InvoiceDetail entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(InvoiceDetail entity) {
		EntityManagerHelper.log("saving InvoiceDetail instance", Level.INFO,
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
	 * Delete a persistent InvoiceDetail entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * InvoiceDetailDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            InvoiceDetail entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(InvoiceDetail entity) {
		EntityManagerHelper.log("deleting InvoiceDetail instance", Level.INFO,
				null);
		try {
			entity = getEntityManager().getReference(InvoiceDetail.class,
					entity.getIndCodigo());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved InvoiceDetail entity and return it or a copy
	 * of it to the sender. A copy of the InvoiceDetail entity parameter is
	 * returned when the JPA persistence mechanism has not previously been
	 * tracking the updated entity. This operation must be performed within the
	 * a database transaction context for the entity's data to be permanently
	 * saved to the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = InvoiceDetailDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            InvoiceDetail entity to update
	 * @returns InvoiceDetail the persisted InvoiceDetail entity instance, may
	 *          not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public InvoiceDetail update(InvoiceDetail entity) {
		EntityManagerHelper.log("updating InvoiceDetail instance", Level.INFO,
				null);
		try {
			InvoiceDetail result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public InvoiceDetail findById(Long id) {
		EntityManagerHelper.log(
				"finding InvoiceDetail instance with id: " + id, Level.INFO,
				null);
		try {
			InvoiceDetail instance = getEntityManager().find(
					InvoiceDetail.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all InvoiceDetail entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the InvoiceDetail property to query
	 * @param value
	 *            the property value to match
	 * @return List<InvoiceDetail> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<InvoiceDetail> findByProperty(String propertyName,
			final Object value) {
		EntityManagerHelper.log(
				"finding InvoiceDetail instance with property: " + propertyName
						+ ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from InvoiceDetail model where model."
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

	public List<InvoiceDetail> findByPInvoiceId(Object PInvoiceId) {
		return findByProperty(_PINVOICE_ID, PInvoiceId);
	}

	public List<InvoiceDetail> findByPLineNum(Object PLineNum) {
		return findByProperty(_PLINE_NUM, PLineNum);
	}

	public List<InvoiceDetail> findByPInvoiceNum(Object PInvoiceNum) {
		return findByProperty(_PINVOICE_NUM, PInvoiceNum);
	}

	public List<InvoiceDetail> findByPInvoiceAmount(Object PInvoiceAmount) {
		return findByProperty(_PINVOICE_AMOUNT, PInvoiceAmount);
	}

	public List<InvoiceDetail> findByPDescription(Object PDescription) {
		return findByProperty(_PDESCRIPTION, PDescription);
	}

	public List<InvoiceDetail> findByPUserId(Object PUserId) {
		return findByProperty(_PUSER_ID, PUserId);
	}

	public List<InvoiceDetail> findByPPlacaVeh(Object PPlacaVeh) {
		return findByProperty(_PPLACA_VEH, PPlacaVeh);
	}

	public List<InvoiceDetail> findByPCompany(Object PCompany) {
		return findByProperty(_PCOMPANY, PCompany);
	}

	public List<InvoiceDetail> findByPAccount(Object PAccount) {
		return findByProperty(_PACCOUNT, PAccount);
	}

	public List<InvoiceDetail> findByPCcenter(Object PCcenter) {
		return findByProperty(_PCCENTER, PCcenter);
	}

	public List<InvoiceDetail> findByPAuxiliary(Object PAuxiliary) {
		return findByProperty(_PAUXILIARY, PAuxiliary);
	}

	public List<InvoiceDetail> findByPOrgId(Object POrgId) {
		return findByProperty(_PORG_ID, POrgId);
	}

	public List<InvoiceDetail> findByPLocation(Object PLocation) {
		return findByProperty(_PLOCATION, PLocation);
	}

	/**
	 * Find all InvoiceDetail entities.
	 * 
	 * @return List<InvoiceDetail> all InvoiceDetail entities
	 */
	@SuppressWarnings("unchecked")
	public List<InvoiceDetail> findAll() {
		EntityManagerHelper.log("finding all InvoiceDetail instances",
				Level.INFO, null);
		try {
			final String queryString = "select model from InvoiceDetail model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}