package geniar.siscar.persistence;
// default package


import geniar.siscar.model.PoliciesInvoice;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * PoliciesInvoice entities. Transaction control of the save(), update() and
 * delete() operations must be handled externally by senders of these methods or
 * must be manually added to each of these methods for data to be persisted to
 * the JPA datastore.
 * 
 * @see .PoliciesInvoice
 * @author MyEclipse Persistence Tools
 */

public class PoliciesInvoiceDAO implements IPoliciesInvoiceDAO {
	// property constants
	public static final String PIN_NUMERO_FACTURA = "pinNumeroFactura";
	public static final String PIN_CONCEPTO = "pinConcepto";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved PoliciesInvoice entity.
	 * All subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * PoliciesInvoiceDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            PoliciesInvoice entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(PoliciesInvoice entity) {
		EntityManagerHelper.log("saving PoliciesInvoice instance", Level.INFO,
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
	 * Delete a persistent PoliciesInvoice entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * PoliciesInvoiceDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            PoliciesInvoice entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(PoliciesInvoice entity) {
		EntityManagerHelper.log("deleting PoliciesInvoice instance",
				Level.INFO, null);
		try {
			entity = getEntityManager().getReference(PoliciesInvoice.class,
					entity.getPinId());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved PoliciesInvoice entity and return it or a copy
	 * of it to the sender. A copy of the PoliciesInvoice entity parameter is
	 * returned when the JPA persistence mechanism has not previously been
	 * tracking the updated entity. This operation must be performed within the
	 * a database transaction context for the entity's data to be permanently
	 * saved to the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = PoliciesInvoiceDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            PoliciesInvoice entity to update
	 * @returns PoliciesInvoice the persisted PoliciesInvoice entity instance,
	 *          may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public PoliciesInvoice update(PoliciesInvoice entity) {
		EntityManagerHelper.log("updating PoliciesInvoice instance",
				Level.INFO, null);
		try {
			PoliciesInvoice result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public PoliciesInvoice findById(Long id) {
		EntityManagerHelper.log("finding PoliciesInvoice instance with id: "
				+ id, Level.INFO, null);
		try {
			PoliciesInvoice instance = getEntityManager().find(
					PoliciesInvoice.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all PoliciesInvoice entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the PoliciesInvoice property to query
	 * @param value
	 *            the property value to match
	 * @return List<PoliciesInvoice> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<PoliciesInvoice> findByProperty(String propertyName,
			final Object value) {
		EntityManagerHelper.log(
				"finding PoliciesInvoice instance with property: "
						+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from PoliciesInvoice model where model."
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

	public List<PoliciesInvoice> findByPinNumeroFactura(Object pinNumeroFactura) {
		return findByProperty(PIN_NUMERO_FACTURA, pinNumeroFactura);
	}

	public List<PoliciesInvoice> findByPinConcepto(Object pinConcepto) {
		return findByProperty(PIN_CONCEPTO, pinConcepto);
	}

	/**
	 * Find all PoliciesInvoice entities.
	 * 
	 * @return List<PoliciesInvoice> all PoliciesInvoice entities
	 */
	@SuppressWarnings("unchecked")
	public List<PoliciesInvoice> findAll() {
		EntityManagerHelper.log("finding all PoliciesInvoice instances",
				Level.INFO, null);
		try {
			final String queryString = "select model from PoliciesInvoice model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}