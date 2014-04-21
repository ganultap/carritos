package geniar.siscar.persistence;

import geniar.siscar.model.Users;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for Users
 * entities. Transaction control of the save(), update() and delete() operations
 * must be handled externally by senders of these methods or must be manually
 * added to each of these methods for data to be persisted to the JPA datastore.
 * 
 * @see geniar.siscar.model.Users
 * @author MyEclipse Persistence Tools
 */

public class UsersDAO implements IUsersDAO {
	// property constants
	public static final String USR_IDENTIFICACION = "usrIdentificacion";
	public static final String USR_NOMBRE = "usrNombre";
	public static final String USR_APELLIDO = "usrApellido";
	public static final String USR_TELEFONO = "usrTelefono";
	public static final String USR_DIRECCION = "usrDireccion";
	public static final String USR_EMAIL = "usrEmail";
	public static final String USR_LOGIN = "usrLogin";
	public static final String DESCRIPCION = "descripcion";
	public static final String USR_ESTADO = "usrEstado";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved Users entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * UsersDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Users entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Users entity) {
		EntityManagerHelper.log("saving Users instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent Users entity. This operation must be performed within
	 * the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * UsersDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Users entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Users entity) {
		EntityManagerHelper.log("deleting Users instance", Level.INFO, null);
		try {
			entity = getEntityManager().getReference(Users.class, entity.getUsrCodigo());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved Users entity and return it or a copy of it to
	 * the sender. A copy of the Users entity parameter is returned when the JPA
	 * persistence mechanism has not previously been tracking the updated
	 * entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = UsersDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Users entity to update
	 * @returns Users the persisted Users entity instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Users update(Users entity) {
		EntityManagerHelper.log("updating Users instance", Level.INFO, null);
		try {
			Users result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Users findById(Long id) {
		EntityManagerHelper.log("finding Users instance with id: " + id, Level.INFO, null);
		try {
			Users instance = getEntityManager().find(Users.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all Users entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Users property to query
	 * @param value
	 *            the property value to match
	 * @return List<Users> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Users> findByProperty(String propertyName, final Object value) {
		EntityManagerHelper.log("finding Users instance with property: " + propertyName + ", value: " + value,
				Level.INFO, null);
		try {
			final String queryString = "select model from Users model where model." + propertyName + "= :propertyValue";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("propertyValue", value);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find by property name failed", Level.SEVERE, re);
			throw re;
		}
	}

	public List<Users> findByUsrIdentificacion(Object usrIdentificacion) {
		return findByProperty(USR_IDENTIFICACION, usrIdentificacion);
	}

	public List<Users> findByUsrNombre(Object usrNombre) {
		return findByProperty(USR_NOMBRE, usrNombre);
	}

	public List<Users> findByUsrApellido(Object usrApellido) {
		return findByProperty(USR_APELLIDO, usrApellido);
	}

	public List<Users> findByUsrTelefono(Object usrTelefono) {
		return findByProperty(USR_TELEFONO, usrTelefono);
	}

	public List<Users> findByUsrDireccion(Object usrDireccion) {
		return findByProperty(USR_DIRECCION, usrDireccion);
	}

	public List<Users> findByUsrEmail(Object usrEmail) {
		return findByProperty(USR_EMAIL, usrEmail);
	}

	public List<Users> findByUsrLogin(Object usrLogin) {
		return findByProperty(USR_LOGIN, usrLogin);
	}

	public List<Users> findByDescripcion(Object descripcion) {
		return findByProperty(DESCRIPCION, descripcion);
	}

	public List<Users> findByUsrEstado(Object usrEstado) {
		return findByProperty(USR_ESTADO, usrEstado);
	}

	/**
	 * Find all Users entities.
	 * 
	 * @return List<Users> all Users entities
	 */
	@SuppressWarnings("unchecked")
	public List<Users> findAll() {
		EntityManagerHelper.log("finding all Users instances", Level.INFO, null);
		try {
			final String queryString = "select model from Users model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}