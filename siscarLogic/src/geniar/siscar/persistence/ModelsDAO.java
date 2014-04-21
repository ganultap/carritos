package geniar.siscar.persistence;

import geniar.siscar.model.Models;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * Models entities. Transaction control of the save(), update() and delete()
 * operations must be handled externally by senders of these methods or must be
 * manually added to each of these methods for data to be persisted to the JPA
 * datastore.
 * 
 * @see geniar.siscar.model.Models
 * @author MyEclipse Persistence Tools
 */

public class ModelsDAO implements IModelsDAO {
	// property constants
	public static final String MDL_NOMBRE = "mdlNombre";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved Models entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ModelsDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Models entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Models entity) {
		EntityManagerHelper.log("saving Models instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent Models entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ModelsDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Models entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Models entity) {
		EntityManagerHelper.log("deleting Models instance", Level.INFO, null);
		try {
			entity = getEntityManager().getReference(Models.class, entity.getMdlId());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved Models entity and return it or a copy of it to
	 * the sender. A copy of the Models entity parameter is returned when the
	 * JPA persistence mechanism has not previously been tracking the updated
	 * entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = ModelsDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Models entity to update
	 * @returns Models the persisted Models entity instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Models update(Models entity) {
		EntityManagerHelper.log("updating Models instance", Level.INFO, null);
		try {
			Models result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Models findById(Long id) {
		EntityManagerHelper.log("finding Models instance with id: " + id, Level.INFO, null);
		try {
			Models instance = getEntityManager().find(Models.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all Models entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Models property to query
	 * @param value
	 *            the property value to match
	 * @return List<Models> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Models> findByProperty(String propertyName, final Object value) {
		EntityManagerHelper.log("finding Models instance with property: " + propertyName + ", value: " + value,
				Level.INFO, null);
		try {
			final String queryString = "select model from Models model where model." + propertyName
					+ "= :propertyValue";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("propertyValue", value);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find by property name failed", Level.SEVERE, re);
			throw re;
		}
	}

	public List<Models> findByMdlNombre(Object mdlNombre) {
		return findByProperty(MDL_NOMBRE, mdlNombre);
	}

	/**
	 * Find all Models entities.
	 * 
	 * @return List<Models> all Models entities
	 */
	@SuppressWarnings("unchecked")
	public List<Models> findAll() {
		EntityManagerHelper.log("finding all Models instances", Level.INFO, null);
		try {
			final String queryString = "select model from Models model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}