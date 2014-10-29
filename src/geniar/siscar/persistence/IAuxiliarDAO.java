package geniar.siscar.persistence;

import geniar.siscar.model.Auxiliar;

import java.util.List;

/**
 * Interface for AuxiliarDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IAuxiliarDAO {
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
	 * IAuxiliarDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Auxiliar entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Auxiliar entity);

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
	 * IAuxiliarDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Auxiliar entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Auxiliar entity);

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
	 * entity = IAuxiliarDAO.update(entity);
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
	public Auxiliar update(Auxiliar entity);

	public Auxiliar findById(Long id);

	/**
	 * Find all Auxiliar entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Auxiliar property to query
	 * @param value
	 *            the property value to match
	 * @return List<Auxiliar> found by query
	 */
	public List<Auxiliar> findByProperty(String propertyName, Object value);

	public List<Auxiliar> findByAuxValor(Object auxValor);

	public List<Auxiliar> findByAuxDescripcion(Object auxDescripcion);

	/**
	 * Find all Auxiliar entities.
	 * 
	 * @return List<Auxiliar> all Auxiliar entities
	 */
	public List<Auxiliar> findAll();
}