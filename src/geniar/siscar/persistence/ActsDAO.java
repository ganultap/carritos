package geniar.siscar.persistence;

import geniar.siscar.model.Acts;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for Acts
 * entities. Transaction control of the save(), update() and delete() operations
 * must be handled externally by senders of these methods or must be manually
 * added to each of these methods for data to be persisted to the JPA datastore.
 * 
 * @see geniar.siscar.model.Acts
 * @author MyEclipse Persistence Tools
 */

public class ActsDAO implements IActsDAO {
	// property constants
	public static final String ACT_DESCRIPCION = "actDescripcion";
	public static final String ACT_OBSERVACIONES = "actObservaciones";
	public static final String ACT_SANCIONES = "actSanciones";
	public static final String ACT_APROBACION = "actAprobacion";
	public static final String ACT_NUMERO_ORDEN = "actNumeroOrden";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved Acts entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ActsDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Acts entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Acts entity) {
		EntityManagerHelper.log("saving Acts instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent Acts entity. This operation must be performed within
	 * the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ActsDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Acts entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Acts entity) {
		EntityManagerHelper.log("deleting Acts instance", Level.INFO, null);
		try {
			entity = getEntityManager().getReference(Acts.class, entity.getActCodigo());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved Acts entity and return it or a copy of it to
	 * the sender. A copy of the Acts entity parameter is returned when the JPA
	 * persistence mechanism has not previously been tracking the updated
	 * entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = ActsDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Acts entity to update
	 * @returns Acts the persisted Acts entity instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Acts update(Acts entity) {
		EntityManagerHelper.log("updating Acts instance", Level.INFO, null);
		try {
			Acts result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Acts findById(Long id) {
		EntityManagerHelper.log("finding Acts instance with id: " + id, Level.INFO, null);
		try {
			Acts instance = getEntityManager().find(Acts.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all Acts entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Acts property to query
	 * @param value
	 *            the property value to match
	 * @return List<Acts> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Acts> findByProperty(String propertyName, final Object value) {
		EntityManagerHelper.log("finding Acts instance with property: " + propertyName + ", value: " + value,
				Level.INFO, null);
		try {
			final String queryString = "select model from Acts model where model." + propertyName + "= :propertyValue";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("propertyValue", value);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find by property name failed", Level.SEVERE, re);
			throw re;
		}
	}

	public List<Acts> findByActDescripcion(Object actDescripcion) {
		return findByProperty(ACT_DESCRIPCION, actDescripcion);
	}

	public List<Acts> findByActObservaciones(Object actObservaciones) {
		return findByProperty(ACT_OBSERVACIONES, actObservaciones);
	}

	public List<Acts> findByActSanciones(Object actSanciones) {
		return findByProperty(ACT_SANCIONES, actSanciones);
	}

	public List<Acts> findByActAprobacion(Object actAprobacion) {
		return findByProperty(ACT_APROBACION, actAprobacion);
	}

	public List<Acts> findByActNumeroOrden(Object actNumeroOrden) {
		return findByProperty(ACT_NUMERO_ORDEN, actNumeroOrden);
	}

	/**
	 * Find all Acts entities.
	 * 
	 * @return List<Acts> all Acts entities
	 */
	@SuppressWarnings("unchecked")
	public List<Acts> findAll() {
		EntityManagerHelper.log("finding all Acts instances", Level.INFO, null);
		try {
			final String queryString = "select model from Acts model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}