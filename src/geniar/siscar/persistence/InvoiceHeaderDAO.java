package geniar.siscar.persistence;

import geniar.siscar.model.InvoiceHeader;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * InvoiceHeader entities. Transaction control of the save(), update() and
 * delete() operations must be handled externally by senders of these methods or
 * must be manually added to each of these methods for data to be persisted to
 * the JPA datastore.
 * 
 * @see geniar.siscar.model.InvoiceHeader
 * @author MyEclipse Persistence Tools
 */

public class InvoiceHeaderDAO implements IInvoiceHeaderDAO {
	// property constants
	public static final String _PINVOICE_NUM = "PInvoiceNum";
	public static final String _PTIPO_FACTURA = "PTipoFactura";
	public static final String _PNIT = "PNit";
	public static final String _PMONEDA_FACTURA = "PMonedaFactura";
	public static final String _PCONV_TYPE = "PConvType";
	public static final String _PINVOICE_AMOUNT = "PInvoiceAmount";
	public static final String _PCONV_RATE = "PConvRate";
	public static final String _PDESCRIPTION = "PDescription";
	public static final String _PUSER = "PUser";
	public static final String _PSOURCE = "PSource";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved InvoiceHeader entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * InvoiceHeaderDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            InvoiceHeader entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(InvoiceHeader entity) {
		EntityManagerHelper.log("saving InvoiceHeader instance", Level.INFO,
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
	 * Delete a persistent InvoiceHeader entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * InvoiceHeaderDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            InvoiceHeader entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(InvoiceHeader entity) {
		EntityManagerHelper.log("deleting InvoiceHeader instance", Level.INFO,
				null);
		try {
			entity = getEntityManager().getReference(InvoiceHeader.class,
					entity.getInhCodigo());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved InvoiceHeader entity and return it or a copy
	 * of it to the sender. A copy of the InvoiceHeader entity parameter is
	 * returned when the JPA persistence mechanism has not previously been
	 * tracking the updated entity. This operation must be performed within the
	 * a database transaction context for the entity's data to be permanently
	 * saved to the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = InvoiceHeaderDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            InvoiceHeader entity to update
	 * @returns InvoiceHeader the persisted InvoiceHeader entity instance, may
	 *          not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public InvoiceHeader update(InvoiceHeader entity) {
		EntityManagerHelper.log("updating InvoiceHeader instance", Level.INFO,
				null);
		try {
			InvoiceHeader result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public InvoiceHeader findById(Long id) {
		EntityManagerHelper.log(
				"finding InvoiceHeader instance with id: " + id, Level.INFO,
				null);
		try {
			InvoiceHeader instance = getEntityManager().find(
					InvoiceHeader.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all InvoiceHeader entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the InvoiceHeader property to query
	 * @param value
	 *            the property value to match
	 * @return List<InvoiceHeader> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<InvoiceHeader> findByProperty(String propertyName,
			final Object value) {
		EntityManagerHelper.log(
				"finding InvoiceHeader instance with property: " + propertyName
						+ ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from InvoiceHeader model where model."
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

	public List<InvoiceHeader> findByPInvoiceNum(Object PInvoiceNum) {
		return findByProperty(_PINVOICE_NUM, PInvoiceNum);
	}

	public List<InvoiceHeader> findByPTipoFactura(Object PTipoFactura) {
		return findByProperty(_PTIPO_FACTURA, PTipoFactura);
	}

	public List<InvoiceHeader> findByPNit(Object PNit) {
		return findByProperty(_PNIT, PNit);
	}

	public List<InvoiceHeader> findByPMonedaFactura(Object PMonedaFactura) {
		return findByProperty(_PMONEDA_FACTURA, PMonedaFactura);
	}

	public List<InvoiceHeader> findByPConvType(Object PConvType) {
		return findByProperty(_PCONV_TYPE, PConvType);
	}

	public List<InvoiceHeader> findByPInvoiceAmount(Object PInvoiceAmount) {
		return findByProperty(_PINVOICE_AMOUNT, PInvoiceAmount);
	}

	public List<InvoiceHeader> findByPConvRate(Object PConvRate) {
		return findByProperty(_PCONV_RATE, PConvRate);
	}

	public List<InvoiceHeader> findByPDescription(Object PDescription) {
		return findByProperty(_PDESCRIPTION, PDescription);
	}

	public List<InvoiceHeader> findByPUser(Object PUser) {
		return findByProperty(_PUSER, PUser);
	}

	public List<InvoiceHeader> findByPSource(Object PSource) {
		return findByProperty(_PSOURCE, PSource);
	}

	/**
	 * Find all InvoiceHeader entities.
	 * 
	 * @return List<InvoiceHeader> all InvoiceHeader entities
	 */
	@SuppressWarnings("unchecked")
	public List<InvoiceHeader> findAll() {
		EntityManagerHelper.log("finding all InvoiceHeader instances",
				Level.INFO, null);
		try {
			final String queryString = "select model from InvoiceHeader model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}