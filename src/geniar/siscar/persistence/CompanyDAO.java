package geniar.siscar.persistence;

import geniar.siscar.model.Company;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * Company entities. Transaction control of the save(), update() and delete()
 * operations must be handled externally by senders of these methods or must be
 * manually added to each of these methods for data to be persisted to the JPA
 * datastore.
 * 
 * @see geniar.siscar.model.Company
 * @author MyEclipse Persistence Tools
 */

public class CompanyDAO implements ICompanyDAO {
	// property constants
	public static final String CMP_NOMBRE = "cmpNombre";
	public static final String CMP_DESCRIPCION = "cmpDescripcion";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved Company entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * CompanyDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Company entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Company entity) {
		EntityManagerHelper.log("saving Company instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent Company entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * CompanyDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Company entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Company entity) {
		EntityManagerHelper.log("deleting Company instance", Level.INFO, null);
		try {
			entity = getEntityManager().getReference(Company.class,
					entity.getCmpId());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved Company entity and return it or a copy of it
	 * to the sender. A copy of the Company entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = CompanyDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Company entity to update
	 * @return Company the persisted Company entity instance, may not be the
	 *         same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Company update(Company entity) {
		EntityManagerHelper.log("updating Company instance", Level.INFO, null);
		try {
			Company result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Company findById(Long id) {
		EntityManagerHelper.log("finding Company instance with id: " + id,
				Level.INFO, null);
		try {
			Company instance = getEntityManager().find(Company.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all Company entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Company property to query
	 * @param value
	 *            the property value to match
	 * @return List<Company> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Company> findByProperty(String propertyName, final Object value) {
		EntityManagerHelper.log("finding Company instance with property: "
				+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from Company model where model."
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

	public List<Company> findByCmpNombre(Object cmpNombre) {
		return findByProperty(CMP_NOMBRE, cmpNombre);
	}

	public List<Company> findByCmpDescripcion(Object cmpDescripcion) {
		return findByProperty(CMP_DESCRIPCION, cmpDescripcion);
	}

	/**
	 * Find all Company entities.
	 * 
	 * @return List<Company> all Company entities
	 */
	@SuppressWarnings("unchecked")
	public List<Company> findAll() {
		EntityManagerHelper.log("finding all Company instances", Level.INFO,
				null);
		try {
			final String queryString = "select model from Company model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}