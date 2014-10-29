package geniar.siscar.persistence;

import geniar.siscar.model.InsurancesCar;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * InsurancesCar entities. Transaction control of the save(), update() and
 * delete() operations must be handled externally by senders of these methods or
 * must be manually added to each of these methods for data to be persisted to
 * the JPA datastore.
 * 
 * @see geniar.siscar.model.InsurancesCar
 * @author MyEclipse Persistence Tools
 */

public class InsurancesCarDAO implements IInsurancesCarDAO {
	// property constants
	public static final String INS_NOMBRE = "insNombre";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved InsurancesCar entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * InsurancesCarDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            InsurancesCar entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(InsurancesCar entity) {
		EntityManagerHelper.log("saving InsurancesCar instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent InsurancesCar entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * InsurancesCarDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            InsurancesCar entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(InsurancesCar entity) {
		EntityManagerHelper.log("deleting InsurancesCar instance", Level.INFO, null);
		try {
			entity = getEntityManager().getReference(InsurancesCar.class, entity.getInsCodigo());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved InsurancesCar entity and return it or a copy
	 * of it to the sender. A copy of the InsurancesCar entity parameter is
	 * returned when the JPA persistence mechanism has not previously been
	 * tracking the updated entity. This operation must be performed within the
	 * a database transaction context for the entity's data to be permanently
	 * saved to the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = InsurancesCarDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            InsurancesCar entity to update
	 * @returns InsurancesCar the persisted InsurancesCar entity instance, may
	 *          not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public InsurancesCar update(InsurancesCar entity) {
		EntityManagerHelper.log("updating InsurancesCar instance", Level.INFO, null);
		try {
			InsurancesCar result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public InsurancesCar findById(Long id) {
		EntityManagerHelper.log("finding InsurancesCar instance with id: " + id, Level.INFO, null);
		try {
			InsurancesCar instance = getEntityManager().find(InsurancesCar.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all InsurancesCar entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the InsurancesCar property to query
	 * @param value
	 *            the property value to match
	 * @return List<InsurancesCar> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<InsurancesCar> findByProperty(String propertyName, final Object value) {
		EntityManagerHelper.log("finding InsurancesCar instance with property: " + propertyName + ", value: " + value,
				Level.INFO, null);
		try {
			final String queryString = "select model from InsurancesCar model where model." + propertyName
					+ "= :propertyValue";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("propertyValue", value);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find by property name failed", Level.SEVERE, re);
			throw re;
		}
	}

	public List<InsurancesCar> findByInsNombre(Object insNombre) {
		return findByProperty(INS_NOMBRE, insNombre);
	}

	/**
	 * Find all InsurancesCar entities.
	 * 
	 * @return List<InsurancesCar> all InsurancesCar entities
	 */
	@SuppressWarnings("unchecked")
	public List<InsurancesCar> findAll() {
		EntityManagerHelper.log("finding all InsurancesCar instances", Level.INFO, null);
		try {
			final String queryString = "select model from InsurancesCar model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}