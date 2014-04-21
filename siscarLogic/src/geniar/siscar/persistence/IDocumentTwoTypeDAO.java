package geniar.siscar.persistence;

import geniar.siscar.model.DocumentTwoType;

import java.util.List;

/**
 * Interface for DocumentTwoTypeDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IDocumentTwoTypeDAO {
	/**
	 * Perform an initial save of a previously unsaved DocumentTwoType entity.
	 * All subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IDocumentTwoTypeDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            DocumentTwoType entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(DocumentTwoType entity);

	/**
	 * Delete a persistent DocumentTwoType entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IDocumentTwoTypeDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            DocumentTwoType entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(DocumentTwoType entity);

	/**
	 * Persist a previously saved DocumentTwoType entity and return it or a copy
	 * of it to the sender. A copy of the DocumentTwoType entity parameter is
	 * returned when the JPA persistence mechanism has not previously been
	 * tracking the updated entity. This operation must be performed within the
	 * a database transaction context for the entity's data to be permanently
	 * saved to the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IDocumentTwoTypeDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            DocumentTwoType entity to update
	 * @return DocumentTwoType the persisted DocumentTwoType entity instance,
	 *         may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public DocumentTwoType update(DocumentTwoType entity);

	public DocumentTwoType findById(Long id);

	/**
	 * Find all DocumentTwoType entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the DocumentTwoType property to query
	 * @param value
	 *            the property value to match
	 * @return List<DocumentTwoType> found by query
	 */
	public List<DocumentTwoType> findByProperty(String propertyName,
			Object value);

	public List<DocumentTwoType> findByDttName(Object dttName);

	public List<DocumentTwoType> findByDttDescripcion(Object dttDescripcion);

	/**
	 * Find all DocumentTwoType entities.
	 * 
	 * @return List<DocumentTwoType> all DocumentTwoType entities
	 */
	public List<DocumentTwoType> findAll();
}