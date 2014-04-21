package geniar.siscar.persistence;

import geniar.siscar.model.SupplyingCatalogs;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * SupplyingCatalogs entities. Transaction control of the save(), update() and
 * delete() operations must be handled externally by senders of these methods or
 * must be manually added to each of these methods for data to be persisted to
 * the JPA datastore.
 * 
 * @see geniar.siscar.model.SupplyingCatalogs
 * @author MyEclipse Persistence Tools
 */

public class SupplyingCatalogsDAO implements ISupplyingCatalogsDAO {
	// property constants
	public static final String SUP_NUM_CATALOGO = "supNumCatalogo";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved SupplyingCatalogs entity.
	 * All subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * SupplyingCatalogsDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            SupplyingCatalogs entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(SupplyingCatalogs entity) {
		EntityManagerHelper.log("saving SupplyingCatalogs instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent SupplyingCatalogs entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * SupplyingCatalogsDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            SupplyingCatalogs entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(SupplyingCatalogs entity) {
		EntityManagerHelper.log("deleting SupplyingCatalogs instance", Level.INFO, null);
		try {
			entity = getEntityManager().getReference(SupplyingCatalogs.class, entity.getSupCodigo());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved SupplyingCatalogs entity and return it or a
	 * copy of it to the sender. A copy of the SupplyingCatalogs entity
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
	 * entity = SupplyingCatalogsDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            SupplyingCatalogs entity to update
	 * @returns SupplyingCatalogs the persisted SupplyingCatalogs entity
	 *          instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public SupplyingCatalogs update(SupplyingCatalogs entity) {
		EntityManagerHelper.log("updating SupplyingCatalogs instance", Level.INFO, null);
		try {
			SupplyingCatalogs result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public SupplyingCatalogs findById(Long id) {
		EntityManagerHelper.log("finding SupplyingCatalogs instance with id: " + id, Level.INFO, null);
		try {
			SupplyingCatalogs instance = getEntityManager().find(SupplyingCatalogs.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all SupplyingCatalogs entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the SupplyingCatalogs property to query
	 * @param value
	 *            the property value to match
	 * @return List<SupplyingCatalogs> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<SupplyingCatalogs> findByProperty(String propertyName, final Object value) {
		EntityManagerHelper.log("finding SupplyingCatalogs instance with property: " + propertyName + ", value: "
				+ value, Level.INFO, null);
		try {
			final String queryString = "select model from SupplyingCatalogs model where model." + propertyName
					+ "= :propertyValue";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("propertyValue", value);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find by property name failed", Level.SEVERE, re);
			throw re;
		}
	}

	public List<SupplyingCatalogs> findBySupNumCatalogo(Object supNumCatalogo) {
		return findByProperty(SUP_NUM_CATALOGO, supNumCatalogo);
	}

	/**
	 * Find all SupplyingCatalogs entities.
	 * 
	 * @return List<SupplyingCatalogs> all SupplyingCatalogs entities
	 */
	@SuppressWarnings("unchecked")
	public List<SupplyingCatalogs> findAll() {
		EntityManagerHelper.log("finding all SupplyingCatalogs instances", Level.INFO, null);
		try {
			final String queryString = "select model from SupplyingCatalogs model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}