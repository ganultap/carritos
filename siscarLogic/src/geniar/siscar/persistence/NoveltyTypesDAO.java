package geniar.siscar.persistence;

import geniar.siscar.model.NoveltyTypes;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * NoveltyTypes entities. Transaction control of the save(), update() and
 * delete() operations must be handled externally by senders of these methods or
 * must be manually added to each of these methods for data to be persisted to
 * the JPA datastore.
 * 
 * @see geniar.siscar.model.NoveltyTypes
 * @author MyEclipse Persistence Tools
 */

public class NoveltyTypesDAO implements INoveltyTypesDAO {
	// property constants
	public static final String NT_NOMBRE = "ntNombre";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved NoveltyTypes entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * NoveltyTypesDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            NoveltyTypes entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(NoveltyTypes entity) {
		EntityManagerHelper.log("saving NoveltyTypes instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent NoveltyTypes entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * NoveltyTypesDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            NoveltyTypes entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(NoveltyTypes entity) {
		EntityManagerHelper.log("deleting NoveltyTypes instance", Level.INFO, null);
		try {
			entity = getEntityManager().getReference(NoveltyTypes.class, entity.getNtId());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved NoveltyTypes entity and return it or a copy of
	 * it to the sender. A copy of the NoveltyTypes entity parameter is returned
	 * when the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = NoveltyTypesDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            NoveltyTypes entity to update
	 * @returns NoveltyTypes the persisted NoveltyTypes entity instance, may not
	 *          be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public NoveltyTypes update(NoveltyTypes entity) {
		EntityManagerHelper.log("updating NoveltyTypes instance", Level.INFO, null);
		try {
			NoveltyTypes result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public NoveltyTypes findById(Long id) {
		EntityManagerHelper.log("finding NoveltyTypes instance with id: " + id, Level.INFO, null);
		try {
			NoveltyTypes instance = getEntityManager().find(NoveltyTypes.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all NoveltyTypes entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the NoveltyTypes property to query
	 * @param value
	 *            the property value to match
	 * @return List<NoveltyTypes> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<NoveltyTypes> findByProperty(String propertyName, final Object value) {
		EntityManagerHelper.log("finding NoveltyTypes instance with property: " + propertyName + ", value: " + value,
				Level.INFO, null);
		try {
			final String queryString = "select model from NoveltyTypes model where model." + propertyName
					+ "= :propertyValue";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("propertyValue", value);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find by property name failed", Level.SEVERE, re);
			throw re;
		}
	}

	public List<NoveltyTypes> findByNtNombre(Object ntNombre) {
		return findByProperty(NT_NOMBRE, ntNombre);
	}

	/**
	 * Find all NoveltyTypes entities.
	 * 
	 * @return List<NoveltyTypes> all NoveltyTypes entities
	 */
	@SuppressWarnings("unchecked")
	public List<NoveltyTypes> findAll() {
		EntityManagerHelper.log("finding all NoveltyTypes instances", Level.INFO, null);
		try {
			final String queryString = "select model from NoveltyTypes model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}