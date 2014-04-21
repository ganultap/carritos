package geniar.siscar.persistence;

import geniar.siscar.model.ParValoresparametros;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * ParValoresparametros entities. Transaction control of the save(), update()
 * and delete() operations must be handled externally by senders of these
 * methods or must be manually added to each of these methods for data to be
 * persisted to the JPA datastore.
 * 
 * @see geniar.siscar.model.ParValoresparametros
 * @author MyEclipse Persistence Tools
 */

public class ParValoresparametrosDAO implements IParValoresparametrosDAO {
	// property constants
	public static final String VALORINICIAL = "valorinicial";
	public static final String VALORFINAL = "valorfinal";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved ParValoresparametros
	 * entity. All subsequent persist actions of this entity should use the
	 * #update() method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ParValoresparametrosDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            ParValoresparametros entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(ParValoresparametros entity) {
		EntityManagerHelper.log("saving ParValoresparametros instance",
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
	 * Delete a persistent ParValoresparametros entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ParValoresparametrosDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            ParValoresparametros entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(ParValoresparametros entity) {
		EntityManagerHelper.log("deleting ParValoresparametros instance",
				Level.INFO, null);
		try {
			entity = getEntityManager().getReference(
					ParValoresparametros.class, entity.getIdvalorparametro());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved ParValoresparametros entity and return it or a
	 * copy of it to the sender. A copy of the ParValoresparametros entity
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
	 * entity = ParValoresparametrosDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            ParValoresparametros entity to update
	 * @returns ParValoresparametros the persisted ParValoresparametros entity
	 *          instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public ParValoresparametros update(ParValoresparametros entity) {
		EntityManagerHelper.log("updating ParValoresparametros instance",
				Level.INFO, null);
		try {
			ParValoresparametros result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public ParValoresparametros findById(Long id) {
		EntityManagerHelper.log(
				"finding ParValoresparametros instance with id: " + id,
				Level.INFO, null);
		try {
			ParValoresparametros instance = getEntityManager().find(
					ParValoresparametros.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all ParValoresparametros entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the ParValoresparametros property to query
	 * @param value
	 *            the property value to match
	 * @return List<ParValoresparametros> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<ParValoresparametros> findByProperty(String propertyName,
			final Object value) {
		EntityManagerHelper.log(
				"finding ParValoresparametros instance with property: "
						+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from ParValoresparametros model where model."
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

	public List<ParValoresparametros> findByValorinicial(Object valorinicial) {
		return findByProperty(VALORINICIAL, valorinicial);
	}

	public List<ParValoresparametros> findByValorfinal(Object valorfinal) {
		return findByProperty(VALORFINAL, valorfinal);
	}

	/**
	 * Find all ParValoresparametros entities.
	 * 
	 * @return List<ParValoresparametros> all ParValoresparametros entities
	 */
	@SuppressWarnings("unchecked")
	public List<ParValoresparametros> findAll() {
		EntityManagerHelper.log("finding all ParValoresparametros instances",
				Level.INFO, null);
		try {
			final String queryString = "select model from ParValoresparametros model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}
}