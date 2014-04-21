package geniar.siscar.persistence;

import geniar.siscar.model.Users;

import java.util.List;

/**
 * Interface for UsersDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IUsersDAO {
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
	 * IUsersDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Users entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Users entity);

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
	 * IUsersDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Users entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Users entity);

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
	 * entity = IUsersDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Users entity to update
	 * @returns Users the persisted Users entity instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Users update(Users entity);

	public Users findById(Long id);

	/**
	 * Find all Users entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Users property to query
	 * @param value
	 *            the property value to match
	 * @return List<Users> found by query
	 */
	public List<Users> findByProperty(String propertyName, Object value);

	public List<Users> findByUsrIdentificacion(Object usrIdentificacion);

	public List<Users> findByUsrNombre(Object usrNombre);

	public List<Users> findByUsrApellido(Object usrApellido);

	public List<Users> findByUsrTelefono(Object usrTelefono);

	public List<Users> findByUsrDireccion(Object usrDireccion);

	public List<Users> findByUsrEmail(Object usrEmail);

	public List<Users> findByUsrLogin(Object usrLogin);

	public List<Users> findByDescripcion(Object descripcion);

	public List<Users> findByUsrEstado(Object usrEstado);

	/**
	 * Find all Users entities.
	 * 
	 * @return List<Users> all Users entities
	 */
	public List<Users> findAll();
}