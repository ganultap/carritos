package geniar.siscar.persistence;

import geniar.siscar.model.DocumentOneType;

import java.util.List;

/**
 * Interface for DocumentOneTypeDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IDocumentOneTypeDAO {
	/**
	 * Perform an initial save of a previously unsaved DocumentOneType entity.
	 * All subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IDocumentOneTypeDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            DocumentOneType entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(DocumentOneType entity);

	/**
	 * Delete a persistent DocumentOneType entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IDocumentOneTypeDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            DocumentOneType entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(DocumentOneType entity);

	/**
	 * Persist a previously saved DocumentOneType entity and return it or a copy
	 * of it to the sender. A copy of the DocumentOneType entity parameter is
	 * returned when the JPA persistence mechanism has not previously been
	 * tracking the updated entity. This operation must be performed within the
	 * a database transaction context for the entity's data to be permanently
	 * saved to the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IDocumentOneTypeDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            DocumentOneType entity to update
	 * @return DocumentOneType the persisted DocumentOneType entity instance,
	 *         may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public DocumentOneType update(DocumentOneType entity);

	public DocumentOneType findById(Long id);

	/**
	 * Find all DocumentOneType entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the DocumentOneType property to query
	 * @param value
	 *            the property value to match
	 * @return List<DocumentOneType> found by query
	 */
	public List<DocumentOneType> findByProperty(String propertyName,
			Object value);

	public List<DocumentOneType> findByDotName(Object dotName);

	public List<DocumentOneType> findByDotDescripcion(Object dotDescripcion);

	/**
	 * Find all DocumentOneType entities.
	 * 
	 * @return List<DocumentOneType> all DocumentOneType entities
	 */
	public List<DocumentOneType> findAll();
}