package geniar.siscar.persistence;

import geniar.siscar.model.InjuredPersons;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * InjuredPersons entities. Transaction control of the save(), update() and
 * delete() operations must be handled externally by senders of these methods or
 * must be manually added to each of these methods for data to be persisted to
 * the JPA datastore.
 * 
 * @see geniar.siscar.model.InjuredPersons
 * @author MyEclipse Persistence Tools
 */

public class InjuredPersonsDAO implements IInjuredPersonsDAO {
	// property constants
	public static final String PIV_ID = "pivId";
	public static final String PIV_NOMBRE = "pivNombre";
	public static final String PIV_DIRECCION = "pivDireccion";
	public static final String PNV_TELEFONO = "pnvTelefono";
	public static final String PIV_EDAD = "pivEdad";
	public static final String PIV_SITIOATENCION = "pivSitioatencion";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved InjuredPersons entity.
	 * All subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * InjuredPersonsDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            InjuredPersons entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(InjuredPersons entity) {
		EntityManagerHelper.log("saving InjuredPersons instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent InjuredPersons entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * InjuredPersonsDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            InjuredPersons entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(InjuredPersons entity) {
		EntityManagerHelper.log("deleting InjuredPersons instance", Level.INFO, null);
		try {
			entity = getEntityManager().getReference(InjuredPersons.class, entity.getPivIdentificacion());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved InjuredPersons entity and return it or a copy
	 * of it to the sender. A copy of the InjuredPersons entity parameter is
	 * returned when the JPA persistence mechanism has not previously been
	 * tracking the updated entity. This operation must be performed within the
	 * a database transaction context for the entity's data to be permanently
	 * saved to the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = InjuredPersonsDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            InjuredPersons entity to update
	 * @returns InjuredPersons the persisted InjuredPersons entity instance, may
	 *          not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public InjuredPersons update(InjuredPersons entity) {
		EntityManagerHelper.log("updating InjuredPersons instance", Level.INFO, null);
		try {
			InjuredPersons result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public InjuredPersons findById(Long id) {
		EntityManagerHelper.log("finding InjuredPersons instance with id: " + id, Level.INFO, null);
		try {
			InjuredPersons instance = getEntityManager().find(InjuredPersons.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all InjuredPersons entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the InjuredPersons property to query
	 * @param value
	 *            the property value to match
	 * @return List<InjuredPersons> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<InjuredPersons> findByProperty(String propertyName, final Object value) {
		EntityManagerHelper.log("finding InjuredPersons instance with property: " + propertyName + ", value: " + value,
				Level.INFO, null);
		try {
			final String queryString = "select model from InjuredPersons model where model." + propertyName
					+ "= :propertyValue";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("propertyValue", value);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find by property name failed", Level.SEVERE, re);
			throw re;
		}
	}

	public List<InjuredPersons> findByPivId(Object pivId) {
		return findByProperty(PIV_ID, pivId);
	}

	public List<InjuredPersons> findByPivNombre(Object pivNombre) {
		return findByProperty(PIV_NOMBRE, pivNombre);
	}

	public List<InjuredPersons> findByPivDireccion(Object pivDireccion) {
		return findByProperty(PIV_DIRECCION, pivDireccion);
	}

	public List<InjuredPersons> findByPnvTelefono(Object pnvTelefono) {
		return findByProperty(PNV_TELEFONO, pnvTelefono);
	}

	public List<InjuredPersons> findByPivEdad(Object pivEdad) {
		return findByProperty(PIV_EDAD, pivEdad);
	}

	public List<InjuredPersons> findByPivSitioatencion(Object pivSitioatencion) {
		return findByProperty(PIV_SITIOATENCION, pivSitioatencion);
	}

	/**
	 * Find all InjuredPersons entities.
	 * 
	 * @return List<InjuredPersons> all InjuredPersons entities
	 */
	@SuppressWarnings("unchecked")
	public List<InjuredPersons> findAll() {
		EntityManagerHelper.log("finding all InjuredPersons instances", Level.INFO, null);
		try {
			final String queryString = "select model from InjuredPersons model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}