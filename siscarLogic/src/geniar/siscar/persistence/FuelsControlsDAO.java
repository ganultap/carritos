package geniar.siscar.persistence;

import geniar.siscar.model.FuelsControls;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * FuelsControls entities. Transaction control of the save(), update() and
 * delete() operations must be handled externally by senders of these methods or
 * must be manually added to each of these methods for data to be persisted to
 * the JPA datastore.
 * 
 * @see geniar.siscar.model.FuelsControls
 * @author MyEclipse Persistence Tools
 */

public class FuelsControlsDAO implements IFuelsControlsDAO {
	// property constants
	public static final String FUC_NUMERO_GALONES = "fucNumeroGalones";
	public static final String FUC_NUMERO_CARNE = "fucNumeroCarne";
	public static final String FUC_NOMBRE = "fucNombre";
	public static final String FUC_PLACA = "fucPlaca";
	public static final String FUC_NUMERO_TL = "fucNumeroTl";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved FuelsControls entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * FuelsControlsDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            FuelsControls entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(FuelsControls entity) {
		EntityManagerHelper.log("saving FuelsControls instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent FuelsControls entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * FuelsControlsDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            FuelsControls entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(FuelsControls entity) {
		EntityManagerHelper.log("deleting FuelsControls instance", Level.INFO, null);
		try {
			entity = getEntityManager().getReference(FuelsControls.class, entity.getFucCodigo());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved FuelsControls entity and return it or a copy
	 * of it to the sender. A copy of the FuelsControls entity parameter is
	 * returned when the JPA persistence mechanism has not previously been
	 * tracking the updated entity. This operation must be performed within the
	 * a database transaction context for the entity's data to be permanently
	 * saved to the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = FuelsControlsDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            FuelsControls entity to update
	 * @returns FuelsControls the persisted FuelsControls entity instance, may
	 *          not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public FuelsControls update(FuelsControls entity) {
		EntityManagerHelper.log("updating FuelsControls instance", Level.INFO, null);
		try {
			FuelsControls result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public FuelsControls findById(Long id) {
		EntityManagerHelper.log("finding FuelsControls instance with id: " + id, Level.INFO, null);
		try {
			FuelsControls instance = getEntityManager().find(FuelsControls.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all FuelsControls entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the FuelsControls property to query
	 * @param value
	 *            the property value to match
	 * @return List<FuelsControls> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<FuelsControls> findByProperty(String propertyName, final Object value) {
		EntityManagerHelper.log("finding FuelsControls instance with property: " + propertyName + ", value: " + value,
				Level.INFO, null);
		try {
			final String queryString = "select model from FuelsControls model where model." + propertyName
					+ "= :propertyValue";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("propertyValue", value);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find by property name failed", Level.SEVERE, re);
			throw re;
		}
	}

	public List<FuelsControls> findByFucNumeroGalones(Object fucNumeroGalones) {
		return findByProperty(FUC_NUMERO_GALONES, fucNumeroGalones);
	}

	public List<FuelsControls> findByFucNumeroCarne(Object fucNumeroCarne) {
		return findByProperty(FUC_NUMERO_CARNE, fucNumeroCarne);
	}

	public List<FuelsControls> findByFucNombre(Object fucNombre) {
		return findByProperty(FUC_NOMBRE, fucNombre);
	}

	public List<FuelsControls> findByFucPlaca(Object fucPlaca) {
		return findByProperty(FUC_PLACA, fucPlaca);
	}

	public List<FuelsControls> findByFucNumeroTl(Object fucNumeroTl) {
		return findByProperty(FUC_NUMERO_TL, fucNumeroTl);
	}

	/**
	 * Find all FuelsControls entities.
	 * 
	 * @return List<FuelsControls> all FuelsControls entities
	 */
	@SuppressWarnings("unchecked")
	public List<FuelsControls> findAll() {
		EntityManagerHelper.log("finding all FuelsControls instances", Level.INFO, null);
		try {
			final String queryString = "select model from FuelsControls model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}