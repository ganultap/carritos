package geniar.siscar.persistence;

import geniar.siscar.model.Auxiliar;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * Auxiliar entities. Transaction control of the save(), update() and delete()
 * operations must be handled externally by senders of these methods or must be
 * manually added to each of these methods for data to be persisted to the JPA
 * datastore.
 * 
 * @see geniar.siscar.model.Auxiliar
 * @author MyEclipse Persistence Tools
 */

public class AuxiliarDAO implements IAuxiliarDAO {
	// property constants
	public static final String AUX_VALOR = "auxValor";
	public static final String AUX_DESCRIPCION = "auxDescripcion";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved Auxiliar entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * AuxiliarDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Auxiliar entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Auxiliar entity) {
		EntityManagerHelper.log("saving Auxiliar instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent Auxiliar entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * AuxiliarDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Auxiliar entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Auxiliar entity) {
		EntityManagerHelper.log("deleting Auxiliar instance", Level.INFO, null);
		try {
			entity = getEntityManager().getReference(Auxiliar.class,
					entity.getAuxId());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved Auxiliar entity and return it or a copy of it
	 * to the sender. A copy of the Auxiliar entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = AuxiliarDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Auxiliar entity to update
	 * @return Auxiliar the persisted Auxiliar entity instance, may not be the
	 *         same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Auxiliar update(Auxiliar entity) {
		EntityManagerHelper.log("updating Auxiliar instance", Level.INFO, null);
		try {
			Auxiliar result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Auxiliar findById(Long id) {
		EntityManagerHelper.log("finding Auxiliar instance with id: " + id,
				Level.INFO, null);
		try {
			Auxiliar instance = getEntityManager().find(Auxiliar.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all Auxiliar entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Auxiliar property to query
	 * @param value
	 *            the property value to match
	 * @return List<Auxiliar> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Auxiliar> findByProperty(String propertyName, final Object value) {
		EntityManagerHelper.log("finding Auxiliar instance with property: "
				+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from Auxiliar model where model."
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

	public List<Auxiliar> findByAuxValor(Object auxValor) {
		return findByProperty(AUX_VALOR, auxValor);
	}

	public List<Auxiliar> findByAuxDescripcion(Object auxDescripcion) {
		return findByProperty(AUX_DESCRIPCION, auxDescripcion);
	}

	/**
	 * Find all Auxiliar entities.
	 * 
	 * @return List<Auxiliar> all Auxiliar entities
	 */
	@SuppressWarnings("unchecked")
	public List<Auxiliar> findAll() {
		EntityManagerHelper.log("finding all Auxiliar instances", Level.INFO,
				null);
		try {
			final String queryString = "select model from Auxiliar model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}