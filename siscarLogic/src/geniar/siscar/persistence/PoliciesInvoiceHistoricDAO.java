package geniar.siscar.persistence;
// default package


import geniar.siscar.model.PoliciesInvoiceHistoric;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * PoliciesInvoiceHistoric entities. Transaction control of the save(), update()
 * and delete() operations must be handled externally by senders of these
 * methods or must be manually added to each of these methods for data to be
 * persisted to the JPA datastore.
 * 
 * @see .PoliciesInvoiceHistoric
 * @author MyEclipse Persistence Tools
 */

public class PoliciesInvoiceHistoricDAO implements IPoliciesInvoiceHistoricDAO {
	// property constants
	public static final String USR_LOGIN = "usrLogin";
	public static final String PIN_NUMERO_FACTURA = "pinNumeroFactura";
	public static final String PIN_CONCEPTO = "pinConcepto";
	public static final String PLS_CODIGO = "plsCodigo";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved PoliciesInvoiceHistoric
	 * entity. All subsequent persist actions of this entity should use the
	 * #update() method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * PoliciesInvoiceHistoricDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            PoliciesInvoiceHistoric entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(PoliciesInvoiceHistoric entity) {
		EntityManagerHelper.log("saving PoliciesInvoiceHistoric instance",
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
	 * Delete a persistent PoliciesInvoiceHistoric entity. This operation must
	 * be performed within the a database transaction context for the entity's
	 * data to be permanently deleted from the persistence store, i.e.,
	 * database. This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * PoliciesInvoiceHistoricDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            PoliciesInvoiceHistoric entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(PoliciesInvoiceHistoric entity) {
		EntityManagerHelper.log("deleting PoliciesInvoiceHistoric instance",
				Level.INFO, null);
		try {
			entity = getEntityManager().getReference(
					PoliciesInvoiceHistoric.class, entity.getPihId());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved PoliciesInvoiceHistoric entity and return it
	 * or a copy of it to the sender. A copy of the PoliciesInvoiceHistoric
	 * entity parameter is returned when the JPA persistence mechanism has not
	 * previously been tracking the updated entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently saved to the persistence store, i.e., database. This
	 * method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = PoliciesInvoiceHistoricDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            PoliciesInvoiceHistoric entity to update
	 * @returns PoliciesInvoiceHistoric the persisted PoliciesInvoiceHistoric
	 *          entity instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public PoliciesInvoiceHistoric update(PoliciesInvoiceHistoric entity) {
		EntityManagerHelper.log("updating PoliciesInvoiceHistoric instance",
				Level.INFO, null);
		try {
			PoliciesInvoiceHistoric result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public PoliciesInvoiceHistoric findById(Long id) {
		EntityManagerHelper.log(
				"finding PoliciesInvoiceHistoric instance with id: " + id,
				Level.INFO, null);
		try {
			PoliciesInvoiceHistoric instance = getEntityManager().find(
					PoliciesInvoiceHistoric.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all PoliciesInvoiceHistoric entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the PoliciesInvoiceHistoric property to query
	 * @param value
	 *            the property value to match
	 * @return List<PoliciesInvoiceHistoric> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<PoliciesInvoiceHistoric> findByProperty(String propertyName,
			final Object value) {
		EntityManagerHelper.log(
				"finding PoliciesInvoiceHistoric instance with property: "
						+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from PoliciesInvoiceHistoric model where model."
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

	public List<PoliciesInvoiceHistoric> findByUsrLogin(Object usrLogin) {
		return findByProperty(USR_LOGIN, usrLogin);
	}

	public List<PoliciesInvoiceHistoric> findByPinNumeroFactura(
			Object pinNumeroFactura) {
		return findByProperty(PIN_NUMERO_FACTURA, pinNumeroFactura);
	}

	public List<PoliciesInvoiceHistoric> findByPinConcepto(Object pinConcepto) {
		return findByProperty(PIN_CONCEPTO, pinConcepto);
	}

	public List<PoliciesInvoiceHistoric> findByPlsCodigo(Object plsCodigo) {
		return findByProperty(PLS_CODIGO, plsCodigo);
	}

	/**
	 * Find all PoliciesInvoiceHistoric entities.
	 * 
	 * @return List<PoliciesInvoiceHistoric> all PoliciesInvoiceHistoric
	 *         entities
	 */
	@SuppressWarnings("unchecked")
	public List<PoliciesInvoiceHistoric> findAll() {
		EntityManagerHelper.log(
				"finding all PoliciesInvoiceHistoric instances", Level.INFO,
				null);
		try {
			final String queryString = "select model from PoliciesInvoiceHistoric model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}