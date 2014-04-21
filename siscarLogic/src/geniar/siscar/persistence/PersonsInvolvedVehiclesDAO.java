package geniar.siscar.persistence;

import geniar.siscar.model.PersonsInvolvedVehicles;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * PersonsInvolvedVehicles entities. Transaction control of the save(), update()
 * and delete() operations must be handled externally by senders of these
 * methods or must be manually added to each of these methods for data to be
 * persisted to the JPA datastore.
 * 
 * @see geniar.siscar.model.PersonsInvolvedVehicles
 * @author MyEclipse Persistence Tools
 */

public class PersonsInvolvedVehiclesDAO implements IPersonsInvolvedVehiclesDAO {
	// property constants
	public static final String PIV_NOMBRE = "pivNombre";
	public static final String PIV_DIRECCION = "pivDireccion";
	public static final String PNV_TELEFONO = "pnvTelefono";
	public static final String PIV_EDAD = "pivEdad";
	public static final String PIV_SITIOATENCION = "pivSitioatencion";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved PersonsInvolvedVehicles
	 * entity. All subsequent persist actions of this entity should use the
	 * #update() method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * PersonsInvolvedVehiclesDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            PersonsInvolvedVehicles entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(PersonsInvolvedVehicles entity) {
		EntityManagerHelper.log("saving PersonsInvolvedVehicles instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent PersonsInvolvedVehicles entity. This operation must
	 * be performed within the a database transaction context for the entity's
	 * data to be permanently deleted from the persistence store, i.e.,
	 * database. This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * PersonsInvolvedVehiclesDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            PersonsInvolvedVehicles entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(PersonsInvolvedVehicles entity) {
		EntityManagerHelper.log("deleting PersonsInvolvedVehicles instance", Level.INFO, null);
		try {
			entity = getEntityManager().getReference(PersonsInvolvedVehicles.class, entity.getPivIdentificacion());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved PersonsInvolvedVehicles entity and return it
	 * or a copy of it to the sender. A copy of the PersonsInvolvedVehicles
	 * entity parameter is returned when the JPA persistence mechanism has not
	 * previously been tracking the updated entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently saved to the persistence store, i.e., database. This
	 * method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = PersonsInvolvedVehiclesDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            PersonsInvolvedVehicles entity to update
	 * @returns PersonsInvolvedVehicles the persisted PersonsInvolvedVehicles
	 *          entity instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public PersonsInvolvedVehicles update(PersonsInvolvedVehicles entity) {
		EntityManagerHelper.log("updating PersonsInvolvedVehicles instance", Level.INFO, null);
		try {
			PersonsInvolvedVehicles result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public PersonsInvolvedVehicles findById(Long id) {
		EntityManagerHelper.log("finding PersonsInvolvedVehicles instance with id: " + id, Level.INFO, null);
		try {
			PersonsInvolvedVehicles instance = getEntityManager().find(PersonsInvolvedVehicles.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all PersonsInvolvedVehicles entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the PersonsInvolvedVehicles property to query
	 * @param value
	 *            the property value to match
	 * @return List<PersonsInvolvedVehicles> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<PersonsInvolvedVehicles> findByProperty(String propertyName, final Object value) {
		EntityManagerHelper.log("finding PersonsInvolvedVehicles instance with property: " + propertyName + ", value: "
				+ value, Level.INFO, null);
		try {
			final String queryString = "select model from PersonsInvolvedVehicles model where model." + propertyName
					+ "= :propertyValue";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("propertyValue", value);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find by property name failed", Level.SEVERE, re);
			throw re;
		}
	}

	public List<PersonsInvolvedVehicles> findByPivNombre(Object pivNombre) {
		return findByProperty(PIV_NOMBRE, pivNombre);
	}

	public List<PersonsInvolvedVehicles> findByPivDireccion(Object pivDireccion) {
		return findByProperty(PIV_DIRECCION, pivDireccion);
	}

	public List<PersonsInvolvedVehicles> findByPnvTelefono(Object pnvTelefono) {
		return findByProperty(PNV_TELEFONO, pnvTelefono);
	}

	public List<PersonsInvolvedVehicles> findByPivEdad(Object pivEdad) {
		return findByProperty(PIV_EDAD, pivEdad);
	}

	public List<PersonsInvolvedVehicles> findByPivSitioatencion(Object pivSitioatencion) {
		return findByProperty(PIV_SITIOATENCION, pivSitioatencion);
	}

	/**
	 * Find all PersonsInvolvedVehicles entities.
	 * 
	 * @return List<PersonsInvolvedVehicles> all PersonsInvolvedVehicles
	 *         entities
	 */
	@SuppressWarnings("unchecked")
	public List<PersonsInvolvedVehicles> findAll() {
		EntityManagerHelper.log("finding all PersonsInvolvedVehicles instances", Level.INFO, null);
		try {
			final String queryString = "select model from PersonsInvolvedVehicles model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}