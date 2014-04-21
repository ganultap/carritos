package geniar.siscar.persistence;

import geniar.siscar.model.DocumentOne;

import java.util.List;

/**
 * Interface for DocumentOneDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IDocumentOneDAO {
	/**
	 * Perform an initial save of a previously unsaved DocumentOne entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IDocumentOneDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            DocumentOne entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(DocumentOne entity);

	/**
	 * Delete a persistent DocumentOne entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IDocumentOneDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            DocumentOne entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(DocumentOne entity);

	/**
	 * Persist a previously saved DocumentOne entity and return it or a copy of
	 * it to the sender. A copy of the DocumentOne entity parameter is returned
	 * when the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IDocumentOneDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            DocumentOne entity to update
	 * @return DocumentOne the persisted DocumentOne entity instance, may not be
	 *         the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public DocumentOne update(DocumentOne entity);

	public DocumentOne findById(Long id);

	/**
	 * Find all DocumentOne entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the DocumentOne property to query
	 * @param value
	 *            the property value to match
	 * @return List<DocumentOne> found by query
	 */
	public List<DocumentOne> findByProperty(String propertyName, Object value);

	public List<DocumentOne> findByDcoNumero(Object dcoNumero);

	/**
	 * Find all DocumentOne entities.
	 * 
	 * @return List<DocumentOne> all DocumentOne entities
	 */
	public List<DocumentOne> findAll();
}