package geniar.siscar.persistence;

import geniar.siscar.model.FlatFile;

import java.util.List;

/**
 * Interface for FlatFileDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IFlatFileDAO {
	/**
	 * Perform an initial save of a previously unsaved FlatFile entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IFlatFileDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            FlatFile entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(FlatFile entity);

	/**
	 * Delete a persistent FlatFile entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IFlatFileDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            FlatFile entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(FlatFile entity);

	/**
	 * Persist a previously saved FlatFile entity and return it or a copy of it
	 * to the sender. A copy of the FlatFile entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IFlatFileDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            FlatFile entity to update
	 * @returns FlatFile the persisted FlatFile entity instance, may not be the
	 *          same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public FlatFile update(FlatFile entity);

	public FlatFile findById(Long id);

	/**
	 * Find all FlatFile entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the FlatFile property to query
	 * @param value
	 *            the property value to match
	 * @return List<FlatFile> found by query
	 */
	public List<FlatFile> findByProperty(String propertyName, Object value);

	public List<FlatFile> findByFfUsuario(Object ffUsuario);

	public List<FlatFile> findByFfCarne(Object ffCarne);

	public List<FlatFile> findByFfConcepto(Object ffConcepto);

	public List<FlatFile> findByFfUnidades(Object ffUnidades);

	public List<FlatFile> findByFfValor(Object ffValor);

	public List<FlatFile> findByFfFecha(Object ffFecha);

	public List<FlatFile> findByFfDocumento(Object ffDocumento);

	public List<FlatFile> findByFfMoneda(Object ffMoneda);

	public List<FlatFile> findByFfDescripcion(Object ffDescripcion);

	public List<FlatFile> findByFfCentroCosto(Object ffCentroCosto);

	/**
	 * Find all FlatFile entities.
	 * 
	 * @return List<FlatFile> all FlatFile entities
	 */
	public List<FlatFile> findAll();
}