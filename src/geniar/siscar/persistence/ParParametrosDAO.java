package geniar.siscar.persistence;

import geniar.siscar.model.ParParametros;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * ParParametros entities. Transaction control of the save(), update() and
 * delete() operations must be handled externally by senders of these methods or
 * must be manually added to each of these methods for data to be persisted to
 * the JPA datastore.
 * 
 * @see geniar.siscar.model.ParParametros
 * @author MyEclipse Persistence Tools
 */

public class ParParametrosDAO implements IParParametrosDAO {
	// property constants
	public static final String NOMBRE = "nombre";
	public static final String DESCRIPCION = "descripcion";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved ParParametros entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ParParametrosDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            ParParametros entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(ParParametros entity) {
		EntityManagerHelper.log("saving ParParametros instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent ParParametros entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ParParametrosDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            ParParametros entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(ParParametros entity) {
		EntityManagerHelper.log("deleting ParParametros instance", Level.INFO, null);
		try {
			entity = getEntityManager().getReference(ParParametros.class, entity.getIdparametro());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved ParParametros entity and return it or a copy
	 * of it to the sender. A copy of the ParParametros entity parameter is
	 * returned when the JPA persistence mechanism has not previously been
	 * tracking the updated entity. This operation must be performed within the
	 * a database transaction context for the entity's data to be permanently
	 * saved to the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = ParParametrosDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            ParParametros entity to update
	 * @returns ParParametros the persisted ParParametros entity instance, may
	 *          not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public ParParametros update(ParParametros entity) {
		EntityManagerHelper.log("updating ParParametros instance", Level.INFO, null);
		try {
			ParParametros result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public ParParametros findById(Long id) {
		EntityManagerHelper.log("finding ParParametros instance with id: " + id, Level.INFO, null);
		try {
			ParParametros instance = getEntityManager().find(ParParametros.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all ParParametros entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the ParParametros property to query
	 * @param value
	 *            the property value to match
	 * @return List<ParParametros> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<ParParametros> findByProperty(String propertyName, final Object value) {
		EntityManagerHelper.log("finding ParParametros instance with property: " + propertyName + ", value: " + value,
				Level.INFO, null);
		try {
			final String queryString = "select model from ParParametros model where model." + propertyName
					+ "= :propertyValue";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("propertyValue", value);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find by property name failed", Level.SEVERE, re);
			throw re;
		}
	}

	public List<ParParametros> findByNombre(Object nombre) {
		return findByProperty(NOMBRE, nombre);
	}

	public List<ParParametros> findByDescripcion(Object descripcion) {
		return findByProperty(DESCRIPCION, descripcion);
	}

	/**
	 * Find all ParParametros entities.
	 * 
	 * @return List<ParParametros> all ParParametros entities
	 */
	@SuppressWarnings("unchecked")
	public List<ParParametros> findAll() {
		EntityManagerHelper.log("finding all ParParametros instances", Level.INFO, null);
		try {
			final String queryString = "select model from ParParametros model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}