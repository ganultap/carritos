package geniar.siscar.persistence;

import geniar.siscar.model.Pumps;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for Pumps
 * entities. Transaction control of the save(), update() and delete() operations
 * must be handled externally by senders of these methods or must be manually
 * added to each of these methods for data to be persisted to the JPA datastore.
 * 
 * @see .Pumps
 * @author MyEclipse Persistence Tools
 */

public class PumpsDAO implements IPumpsDAO {
	// property constants
	public static final String PUM_NOMBRE = "pumNombre";
	public static final String PUM_VECES_UTILIZADO = "pumVecesUtilizado";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved Pumps entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * PumpsDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Pumps entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Pumps entity) {
		EntityManagerHelper.log("saving Pumps instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent Pumps entity. This operation must be performed within
	 * the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * PumpsDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Pumps entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Pumps entity) {
		EntityManagerHelper.log("deleting Pumps instance", Level.INFO, null);
		try {
			entity = getEntityManager().getReference(Pumps.class,
					entity.getPumCodigo());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved Pumps entity and return it or a copy of it to
	 * the sender. A copy of the Pumps entity parameter is returned when the JPA
	 * persistence mechanism has not previously been tracking the updated
	 * entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = PumpsDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Pumps entity to update
	 * @returns Pumps the persisted Pumps entity instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Pumps update(Pumps entity) {
		EntityManagerHelper.log("updating Pumps instance", Level.INFO, null);
		try {
			Pumps result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Pumps findById(Long id) {
		EntityManagerHelper.log("finding Pumps instance with id: " + id,
				Level.INFO, null);
		try {
			Pumps instance = getEntityManager().find(Pumps.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all Pumps entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Pumps property to query
	 * @param value
	 *            the property value to match
	 * @return List<Pumps> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Pumps> findByProperty(String propertyName, final Object value) {
		EntityManagerHelper.log("finding Pumps instance with property: "
				+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from Pumps model where model."
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

	public List<Pumps> findByPumNombre(Object pumNombre) {
		return findByProperty(PUM_NOMBRE, pumNombre);
	}

	public List<Pumps> findByPumVecesUtilizado(Object pumVecesUtilizado) {
		return findByProperty(PUM_VECES_UTILIZADO, pumVecesUtilizado);
	}

	/**
	 * Find all Pumps entities.
	 * 
	 * @return List<Pumps> all Pumps entities
	 */
	@SuppressWarnings("unchecked")
	public List<Pumps> findAll() {
		EntityManagerHelper
				.log("finding all Pumps instances", Level.INFO, null);
		try {
			final String queryString = "select model from Pumps model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}