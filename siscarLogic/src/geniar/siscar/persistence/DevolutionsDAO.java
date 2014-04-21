package geniar.siscar.persistence;

import geniar.siscar.model.Devolutions;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * Devolutions entities. Transaction control of the save(), update() and
 * delete() operations must be handled externally by senders of these methods or
 * must be manually added to each of these methods for data to be persisted to
 * the JPA datastore.
 * 
 * @see geniar.siscar.model.Devolutions
 * @author MyEclipse Persistence Tools
 */

public class DevolutionsDAO implements IDevolutionsDAO {
	// property constants
	public static final String DVL_TIPO_ASIGNATARIO = "dvlTipoAsignatario";
	public static final String DVL_KILOMETRAJE = "dvlKilometraje";
	public static final String DVL_ESTADO_VEH = "dvlEstadoVeh";
	public static final String DVL_DEV_TARIF_ASIG = "dvlDevTarifAsig";
	public static final String DVL_COBRO_TARIF_ASIG = "dvlCobroTarifAsig";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved Devolutions entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * DevolutionsDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Devolutions entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Devolutions entity) {
		EntityManagerHelper.log("saving Devolutions instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent Devolutions entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * DevolutionsDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Devolutions entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Devolutions entity) {
		EntityManagerHelper.log("deleting Devolutions instance", Level.INFO, null);
		try {
			entity = getEntityManager().getReference(Devolutions.class, entity.getDvlCodigo());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved Devolutions entity and return it or a copy of
	 * it to the sender. A copy of the Devolutions entity parameter is returned
	 * when the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = DevolutionsDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Devolutions entity to update
	 * @returns Devolutions the persisted Devolutions entity instance, may not
	 *          be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Devolutions update(Devolutions entity) {
		EntityManagerHelper.log("updating Devolutions instance", Level.INFO, null);
		try {
			Devolutions result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Devolutions findById(Long id) {
		EntityManagerHelper.log("finding Devolutions instance with id: " + id, Level.INFO, null);
		try {
			Devolutions instance = getEntityManager().find(Devolutions.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all Devolutions entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Devolutions property to query
	 * @param value
	 *            the property value to match
	 * @return List<Devolutions> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Devolutions> findByProperty(String propertyName, final Object value) {
		EntityManagerHelper.log("finding Devolutions instance with property: " + propertyName + ", value: " + value,
				Level.INFO, null);
		try {
			final String queryString = "select model from Devolutions model where model." + propertyName
					+ "= :propertyValue";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("propertyValue", value);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find by property name failed", Level.SEVERE, re);
			throw re;
		}
	}

	public List<Devolutions> findByDvlTipoAsignatario(Object dvlTipoAsignatario) {
		return findByProperty(DVL_TIPO_ASIGNATARIO, dvlTipoAsignatario);
	}

	public List<Devolutions> findByDvlKilometraje(Object dvlKilometraje) {
		return findByProperty(DVL_KILOMETRAJE, dvlKilometraje);
	}

	public List<Devolutions> findByDvlEstadoVeh(Object dvlEstadoVeh) {
		return findByProperty(DVL_ESTADO_VEH, dvlEstadoVeh);
	}

	public List<Devolutions> findByDvlDevTarifAsig(Object dvlDevTarifAsig) {
		return findByProperty(DVL_DEV_TARIF_ASIG, dvlDevTarifAsig);
	}

	public List<Devolutions> findByDvlCobroTarifAsig(Object dvlCobroTarifAsig) {
		return findByProperty(DVL_COBRO_TARIF_ASIG, dvlCobroTarifAsig);
	}

	/**
	 * Find all Devolutions entities.
	 * 
	 * @return List<Devolutions> all Devolutions entities
	 */
	@SuppressWarnings("unchecked")
	public List<Devolutions> findAll() {
		EntityManagerHelper.log("finding all Devolutions instances", Level.INFO, null);
		try {
			final String queryString = "select model from Devolutions model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}