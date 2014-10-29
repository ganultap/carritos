package geniar.siscar.persistence;

import geniar.siscar.model.Witnesses;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * Witnesses entities. Transaction control of the save(), update() and delete()
 * operations must be handled externally by senders of these methods or must be
 * manually added to each of these methods for data to be persisted to the JPA
 * datastore.
 * 
 * @see geniar.siscar.model.Witnesses
 * @author MyEclipse Persistence Tools
 */

public class WitnessesDAO implements IWitnessesDAO {
	// property constants
	public static final String WTN_IDENTIFICACION = "wtnIdentificacion";
	public static final String WTN_NOMBRE = "wtnNombre";
	public static final String WTN_DIRECCION = "wtnDireccion";
	public static final String WTN_TELEFONO = "wtnTelefono";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved Witnesses entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * WitnessesDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Witnesses entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Witnesses entity) {
		EntityManagerHelper.log("saving Witnesses instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent Witnesses entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * WitnessesDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Witnesses entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Witnesses entity) {
		EntityManagerHelper.log("deleting Witnesses instance", Level.INFO, null);
		try {
			entity = getEntityManager().getReference(Witnesses.class, entity.getWtnCodigo());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved Witnesses entity and return it or a copy of it
	 * to the sender. A copy of the Witnesses entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = WitnessesDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Witnesses entity to update
	 * @returns Witnesses the persisted Witnesses entity instance, may not be
	 *          the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Witnesses update(Witnesses entity) {
		EntityManagerHelper.log("updating Witnesses instance", Level.INFO, null);
		try {
			Witnesses result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Witnesses findById(Long id) {
		EntityManagerHelper.log("finding Witnesses instance with id: " + id, Level.INFO, null);
		try {
			Witnesses instance = getEntityManager().find(Witnesses.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all Witnesses entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Witnesses property to query
	 * @param value
	 *            the property value to match
	 * @return List<Witnesses> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Witnesses> findByProperty(String propertyName, final Object value) {
		EntityManagerHelper.log("finding Witnesses instance with property: " + propertyName + ", value: " + value,
				Level.INFO, null);
		try {
			final String queryString = "select model from Witnesses model where model." + propertyName
					+ "= :propertyValue";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("propertyValue", value);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find by property name failed", Level.SEVERE, re);
			throw re;
		}
	}

	public List<Witnesses> findByWtnIdentificacion(Object wtnIdentificacion) {
		return findByProperty(WTN_IDENTIFICACION, wtnIdentificacion);
	}

	public List<Witnesses> findByWtnNombre(Object wtnNombre) {
		return findByProperty(WTN_NOMBRE, wtnNombre);
	}

	public List<Witnesses> findByWtnDireccion(Object wtnDireccion) {
		return findByProperty(WTN_DIRECCION, wtnDireccion);
	}

	public List<Witnesses> findByWtnTelefono(Object wtnTelefono) {
		return findByProperty(WTN_TELEFONO, wtnTelefono);
	}

	/**
	 * Find all Witnesses entities.
	 * 
	 * @return List<Witnesses> all Witnesses entities
	 */
	@SuppressWarnings("unchecked")
	public List<Witnesses> findAll() {
		EntityManagerHelper.log("finding all Witnesses instances", Level.INFO, null);
		try {
			final String queryString = "select model from Witnesses model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}