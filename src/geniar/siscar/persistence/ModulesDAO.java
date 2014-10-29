package geniar.siscar.persistence;

import geniar.siscar.model.Modules;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * Modules entities. Transaction control of the save(), update() and delete()
 * operations must be handled externally by senders of these methods or must be
 * manually added to each of these methods for data to be persisted to the JPA
 * datastore.
 * 
 * @see geniar.siscar.model.Modules
 * @author MyEclipse Persistence Tools
 */

public class ModulesDAO implements IModulesDAO {
	// property constants
	public static final String MDL_NOMBRE = "mdlNombre";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved Modules entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ModulesDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Modules entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Modules entity) {
		EntityManagerHelper.log("saving Modules instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent Modules entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ModulesDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Modules entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Modules entity) {
		EntityManagerHelper.log("deleting Modules instance", Level.INFO, null);
		try {
			entity = getEntityManager().getReference(Modules.class, entity.getMdlCodigo());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved Modules entity and return it or a copy of it
	 * to the sender. A copy of the Modules entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = ModulesDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Modules entity to update
	 * @returns Modules the persisted Modules entity instance, may not be the
	 *          same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Modules update(Modules entity) {
		EntityManagerHelper.log("updating Modules instance", Level.INFO, null);
		try {
			Modules result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Modules findById(Long id) {
		EntityManagerHelper.log("finding Modules instance with id: " + id, Level.INFO, null);
		try {
			Modules instance = getEntityManager().find(Modules.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all Modules entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Modules property to query
	 * @param value
	 *            the property value to match
	 * @return List<Modules> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Modules> findByProperty(String propertyName, final Object value) {
		EntityManagerHelper.log("finding Modules instance with property: " + propertyName + ", value: " + value,
				Level.INFO, null);
		try {
			final String queryString = "select model from Modules model where model." + propertyName
					+ "= :propertyValue";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("propertyValue", value);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find by property name failed", Level.SEVERE, re);
			throw re;
		}
	}

	public List<Modules> findByMdlNombre(Object mdlNombre) {
		return findByProperty(MDL_NOMBRE, mdlNombre);
	}

	/**
	 * Find all Modules entities.
	 * 
	 * @return List<Modules> all Modules entities
	 */
	@SuppressWarnings("unchecked")
	public List<Modules> findAll() {
		EntityManagerHelper.log("finding all Modules instances", Level.INFO, null);
		try {
			final String queryString = "select model from Modules model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}