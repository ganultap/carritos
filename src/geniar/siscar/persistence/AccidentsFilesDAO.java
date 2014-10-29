package geniar.siscar.persistence;

import geniar.siscar.model.AccidentsFiles;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * AccidentsFiles entities. Transaction control of the save(), update() and
 * delete() operations must be handled externally by senders of these methods or
 * must be manually added to each of these methods for data to be persisted to
 * the JPA datastore.
 * 
 * @see geniar.siscar.model.AccidentsFiles
 * @author MyEclipse Persistence Tools
 */

public class AccidentsFilesDAO implements IAccidentsFilesDAO {
	// property constants
	public static final String ACF_RUTA = "acfRuta";
	public static final String ACF_DESCRIPCION = "acfDescripcion";
	public static final String ACF_NOMBRE = "acfNombre";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved AccidentsFiles entity.
	 * All subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * AccidentsFilesDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            AccidentsFiles entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(AccidentsFiles entity) {
		EntityManagerHelper.log("saving AccidentsFiles instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent AccidentsFiles entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * AccidentsFilesDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            AccidentsFiles entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(AccidentsFiles entity) {
		EntityManagerHelper.log("deleting AccidentsFiles instance", Level.INFO, null);
		try {
			entity = getEntityManager().getReference(AccidentsFiles.class, entity.getAcfId());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved AccidentsFiles entity and return it or a copy
	 * of it to the sender. A copy of the AccidentsFiles entity parameter is
	 * returned when the JPA persistence mechanism has not previously been
	 * tracking the updated entity. This operation must be performed within the
	 * a database transaction context for the entity's data to be permanently
	 * saved to the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = AccidentsFilesDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            AccidentsFiles entity to update
	 * @returns AccidentsFiles the persisted AccidentsFiles entity instance, may
	 *          not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public AccidentsFiles update(AccidentsFiles entity) {
		EntityManagerHelper.log("updating AccidentsFiles instance", Level.INFO, null);
		try {
			AccidentsFiles result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public AccidentsFiles findById(Long id) {
		EntityManagerHelper.log("finding AccidentsFiles instance with id: " + id, Level.INFO, null);
		try {
			AccidentsFiles instance = getEntityManager().find(AccidentsFiles.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all AccidentsFiles entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the AccidentsFiles property to query
	 * @param value
	 *            the property value to match
	 * @return List<AccidentsFiles> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<AccidentsFiles> findByProperty(String propertyName, final Object value) {
		EntityManagerHelper.log("finding AccidentsFiles instance with property: " + propertyName + ", value: " + value,
				Level.INFO, null);
		try {
			final String queryString = "select model from AccidentsFiles model where model." + propertyName
					+ "= :propertyValue";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("propertyValue", value);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find by property name failed", Level.SEVERE, re);
			throw re;
		}
	}

	public List<AccidentsFiles> findByAcfRuta(Object acfRuta) {
		return findByProperty(ACF_RUTA, acfRuta);
	}

	public List<AccidentsFiles> findByAcfDescripcion(Object acfDescripcion) {
		return findByProperty(ACF_DESCRIPCION, acfDescripcion);
	}

	public List<AccidentsFiles> findByAcfNombre(Object acfNombre) {
		return findByProperty(ACF_NOMBRE, acfNombre);
	}

	/**
	 * Find all AccidentsFiles entities.
	 * 
	 * @return List<AccidentsFiles> all AccidentsFiles entities
	 */
	@SuppressWarnings("unchecked")
	public List<AccidentsFiles> findAll() {
		EntityManagerHelper.log("finding all AccidentsFiles instances", Level.INFO, null);
		try {
			final String queryString = "select model from AccidentsFiles model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}