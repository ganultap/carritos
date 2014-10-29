package geniar.siscar.persistence;

import geniar.siscar.model.InvoiceType;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * InvoiceType entities. Transaction control of the save(), update() and
 * delete() operations must be handled externally by senders of these methods or
 * must be manually added to each of these methods for data to be persisted to
 * the JPA datastore.
 * 
 * @see geniar.siscar.model.InvoiceType
 * @author MyEclipse Persistence Tools
 */

public class InvoiceTypeDAO implements IInvoiceTypeDAO {
	// property constants
	public static final String INT_NOMBRE = "intNombre";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved InvoiceType entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * InvoiceTypeDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            InvoiceType entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(InvoiceType entity) {
		EntityManagerHelper
				.log("saving InvoiceType instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent InvoiceType entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * InvoiceTypeDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            InvoiceType entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(InvoiceType entity) {
		EntityManagerHelper.log("deleting InvoiceType instance", Level.INFO,
				null);
		try {
			entity = getEntityManager().getReference(InvoiceType.class,
					entity.getIntCodigo());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved InvoiceType entity and return it or a copy of
	 * it to the sender. A copy of the InvoiceType entity parameter is returned
	 * when the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = InvoiceTypeDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            InvoiceType entity to update
	 * @returns InvoiceType the persisted InvoiceType entity instance, may not
	 *          be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public InvoiceType update(InvoiceType entity) {
		EntityManagerHelper.log("updating InvoiceType instance", Level.INFO,
				null);
		try {
			InvoiceType result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public InvoiceType findById(Long id) {
		EntityManagerHelper.log("finding InvoiceType instance with id: " + id,
				Level.INFO, null);
		try {
			InvoiceType instance = getEntityManager().find(InvoiceType.class,
					id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all InvoiceType entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the InvoiceType property to query
	 * @param value
	 *            the property value to match
	 * @return List<InvoiceType> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<InvoiceType> findByProperty(String propertyName,
			final Object value) {
		EntityManagerHelper.log("finding InvoiceType instance with property: "
				+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from InvoiceType model where model."
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

	public List<InvoiceType> findByIntNombre(Object intNombre) {
		return findByProperty(INT_NOMBRE, intNombre);
	}

	/**
	 * Find all InvoiceType entities.
	 * 
	 * @return List<InvoiceType> all InvoiceType entities
	 */
	@SuppressWarnings("unchecked")
	public List<InvoiceType> findAll() {
		EntityManagerHelper.log("finding all InvoiceType instances",
				Level.INFO, null);
		try {
			final String queryString = "select model from InvoiceType model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}