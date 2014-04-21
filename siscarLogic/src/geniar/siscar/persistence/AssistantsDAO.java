package geniar.siscar.persistence;

import geniar.siscar.model.Assistants;

import java.util.List;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * Assistants entities. Transaction control of the save(), update() and delete()
 * operations must be handled externally by senders of these methods or must be
 * manually added to each of these methods for data to be persisted to the JPA
 * datastore.
 * 
 * @see .Assistants
 * @author MyEclipse Persistence Tools
 */

public class AssistantsDAO implements IAssistantsDAO {
	// property constants
	public static final String ASI_NOMBRE = "asiNombre";
	public static final String ASI_EMAIL = "asiEmail";
	public static final String ASI_CODIGO_CIAT = "asiCodigoCiat";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved Assistants entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * AssistantsDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Assistants entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Assistants entity) {
		EntityManagerHelper.log("saving Assistants instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent Assistants entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * AssistantsDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Assistants entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Assistants entity) {
		EntityManagerHelper.log("deleting Assistants instance", Level.INFO,
				null);
		try {
			entity = getEntityManager().getReference(Assistants.class,
					entity.getAsiCodigo());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved Assistants entity and return it or a copy of
	 * it to the sender. A copy of the Assistants entity parameter is returned
	 * when the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = AssistantsDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Assistants entity to update
	 * @returns Assistants the persisted Assistants entity instance, may not be
	 *          the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Assistants update(Assistants entity) {
		EntityManagerHelper.log("updating Assistants instance", Level.INFO,
				null);
		try {
			Assistants result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Assistants findById(Long id) {
		EntityManagerHelper.log("finding Assistants instance with id: " + id,
				Level.INFO, null);
		try {
			Assistants instance = getEntityManager().find(Assistants.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all Assistants entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Assistants property to query
	 * @param value
	 *            the property value to match
	 * @return List<Assistants> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Assistants> findByProperty(String propertyName,
			final Object value) {
		EntityManagerHelper.log("finding Assistants instance with property: "
				+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from Assistants model where model."
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

	public List<Assistants> findByAsiNombre(Object asiNombre) {
		return findByProperty(ASI_NOMBRE, asiNombre);
	}

	public List<Assistants> findByAsiEmail(Object asiEmail) {
		return findByProperty(ASI_EMAIL, asiEmail);
	}

	public List<Assistants> findByAsiCodigoCiat(Object asiCodigoCiat) {
		return findByProperty(ASI_CODIGO_CIAT, asiCodigoCiat);
	}

	/**
	 * Find all Assistants entities.
	 * 
	 * @return List<Assistants> all Assistants entities
	 */
	@SuppressWarnings("unchecked")
	public List<Assistants> findAll() {
		EntityManagerHelper.log("finding all Assistants instances", Level.INFO,
				null);
		try {
			final String queryString = "select model from Assistants model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}