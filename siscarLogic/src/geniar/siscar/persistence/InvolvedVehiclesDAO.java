package geniar.siscar.persistence;

import geniar.siscar.model.InvolvedVehicles;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * InvolvedVehicles entities. Transaction control of the save(), update() and
 * delete() operations must be handled externally by senders of these methods or
 * must be manually added to each of these methods for data to be persisted to
 * the JPA datastore.
 * 
 * @see geniar.siscar.model.InvolvedVehicles
 * @author MyEclipse Persistence Tools
 */

public class InvolvedVehiclesDAO implements IInvolvedVehiclesDAO {
	// property constants
	public static final String HNV_TIPO_VEHICULO = "hnvTipoVehiculo";
	public static final String HNV_PLACA = "hnvPlaca";
	public static final String HNV_MARCA = "hnvMarca";
	public static final String HNV_CONDUCTOR = "hnvConductor";
	public static final String HNV_IDENTIF_CONDUC = "hnvIdentifConduc";
	public static final String HNV_TELEF_CONDUC = "hnvTelefConduc";
	public static final String HNV_IDENTIF_PROP = "hnvIdentifProp";
	public static final String HNV_PROPIETARIO = "hnvPropietario";
	public static final String HNV_DIRECCIONCONDUCTOR = "hnvDireccionconductor";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved InvolvedVehicles entity.
	 * All subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * InvolvedVehiclesDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            InvolvedVehicles entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(InvolvedVehicles entity) {
		EntityManagerHelper.log("saving InvolvedVehicles instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent InvolvedVehicles entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * InvolvedVehiclesDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            InvolvedVehicles entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(InvolvedVehicles entity) {
		EntityManagerHelper.log("deleting InvolvedVehicles instance", Level.INFO, null);
		try {
			entity = getEntityManager().getReference(InvolvedVehicles.class, entity.getHnvCodigo());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved InvolvedVehicles entity and return it or a
	 * copy of it to the sender. A copy of the InvolvedVehicles entity parameter
	 * is returned when the JPA persistence mechanism has not previously been
	 * tracking the updated entity. This operation must be performed within the
	 * a database transaction context for the entity's data to be permanently
	 * saved to the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = InvolvedVehiclesDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            InvolvedVehicles entity to update
	 * @returns InvolvedVehicles the persisted InvolvedVehicles entity instance,
	 *          may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public InvolvedVehicles update(InvolvedVehicles entity) {
		EntityManagerHelper.log("updating InvolvedVehicles instance", Level.INFO, null);
		try {
			InvolvedVehicles result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public InvolvedVehicles findById(Long id) {
		EntityManagerHelper.log("finding InvolvedVehicles instance with id: " + id, Level.INFO, null);
		try {
			InvolvedVehicles instance = getEntityManager().find(InvolvedVehicles.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all InvolvedVehicles entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the InvolvedVehicles property to query
	 * @param value
	 *            the property value to match
	 * @return List<InvolvedVehicles> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<InvolvedVehicles> findByProperty(String propertyName, final Object value) {
		EntityManagerHelper.log("finding InvolvedVehicles instance with property: " + propertyName + ", value: "
				+ value, Level.INFO, null);
		try {
			final String queryString = "select model from InvolvedVehicles model where model." + propertyName
					+ "= :propertyValue";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("propertyValue", value);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find by property name failed", Level.SEVERE, re);
			throw re;
		}
	}

	public List<InvolvedVehicles> findByHnvTipoVehiculo(Object hnvTipoVehiculo) {
		return findByProperty(HNV_TIPO_VEHICULO, hnvTipoVehiculo);
	}

	public List<InvolvedVehicles> findByHnvPlaca(Object hnvPlaca) {
		return findByProperty(HNV_PLACA, hnvPlaca);
	}

	public List<InvolvedVehicles> findByHnvMarca(Object hnvMarca) {
		return findByProperty(HNV_MARCA, hnvMarca);
	}

	public List<InvolvedVehicles> findByHnvConductor(Object hnvConductor) {
		return findByProperty(HNV_CONDUCTOR, hnvConductor);
	}

	public List<InvolvedVehicles> findByHnvIdentifConduc(Object hnvIdentifConduc) {
		return findByProperty(HNV_IDENTIF_CONDUC, hnvIdentifConduc);
	}

	public List<InvolvedVehicles> findByHnvTelefConduc(Object hnvTelefConduc) {
		return findByProperty(HNV_TELEF_CONDUC, hnvTelefConduc);
	}

	public List<InvolvedVehicles> findByHnvIdentifProp(Object hnvIdentifProp) {
		return findByProperty(HNV_IDENTIF_PROP, hnvIdentifProp);
	}

	public List<InvolvedVehicles> findByHnvPropietario(Object hnvPropietario) {
		return findByProperty(HNV_PROPIETARIO, hnvPropietario);
	}

	public List<InvolvedVehicles> findByHnvDireccionconductor(Object hnvDireccionconductor) {
		return findByProperty(HNV_DIRECCIONCONDUCTOR, hnvDireccionconductor);
	}

	/**
	 * Find all InvolvedVehicles entities.
	 * 
	 * @return List<InvolvedVehicles> all InvolvedVehicles entities
	 */
	@SuppressWarnings("unchecked")
	public List<InvolvedVehicles> findAll() {
		EntityManagerHelper.log("finding all InvolvedVehicles instances", Level.INFO, null);
		try {
			final String queryString = "select model from InvolvedVehicles model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}