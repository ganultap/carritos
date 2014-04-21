package geniar.siscar.persistence;

import geniar.siscar.model.MovementType;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * MovementType entities. Transaction control of the save(), update() and
 * delete() operations must be handled externally by senders of these methods or
 * must be manually added to each of these methods for data to be persisted to
 * the JPA datastore.
 * 
 * @see geniar.siscar.model.MovementType
 * @author MyEclipse Persistence Tools
 */

public class MovementTypeDAO implements IMovementTypeDAO {
	// property constants
	public static final String MVM_NOMBRE = "mvmNombre";
	public static final String MVM_DESCRIPCION = "mvmDescripcion";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved MovementType entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * MovementTypeDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            MovementType entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(MovementType entity) {
		EntityManagerHelper.log("saving MovementType instance", Level.INFO,
				null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent MovementType entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * MovementTypeDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            MovementType entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(MovementType entity) {
		EntityManagerHelper.log("deleting MovementType instance", Level.INFO,
				null);
		try {
			entity = getEntityManager().getReference(MovementType.class,
					entity.getMvmId());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved MovementType entity and return it or a copy of
	 * it to the sender. A copy of the MovementType entity parameter is returned
	 * when the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = MovementTypeDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            MovementType entity to update
	 * @return MovementType the persisted MovementType entity instance, may not
	 *         be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public MovementType update(MovementType entity) {
		EntityManagerHelper.log("updating MovementType instance", Level.INFO,
				null);
		try {
			MovementType result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public MovementType findById(Long id) {
		EntityManagerHelper.log("finding MovementType instance with id: " + id,
				Level.INFO, null);
		try {
			MovementType instance = getEntityManager().find(MovementType.class,
					id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all MovementType entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the MovementType property to query
	 * @param value
	 *            the property value to match
	 * @return List<MovementType> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<MovementType> findByProperty(String propertyName,
			final Object value) {
		EntityManagerHelper.log("finding MovementType instance with property: "
				+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from MovementType model where model."
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

	public List<MovementType> findByMvmNombre(Object mvmNombre) {
		return findByProperty(MVM_NOMBRE, mvmNombre);
	}

	public List<MovementType> findByMvmDescripcion(Object mvmDescripcion) {
		return findByProperty(MVM_DESCRIPCION, mvmDescripcion);
	}

	/**
	 * Find all MovementType entities.
	 * 
	 * @return List<MovementType> all MovementType entities
	 */
	@SuppressWarnings("unchecked")
	public List<MovementType> findAll() {
		EntityManagerHelper.log("finding all MovementType instances",
				Level.INFO, null);
		try {
			final String queryString = "select model from MovementType model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}