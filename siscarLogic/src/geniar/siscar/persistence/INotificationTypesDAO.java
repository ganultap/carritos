package geniar.siscar.persistence;

import geniar.siscar.model.NotificationTypes;

import java.util.List;

/**
 * Interface for NotificationTypesDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface INotificationTypesDAO {
	/**
	 * Perform an initial save of a previously unsaved NotificationTypes entity.
	 * All subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * INotificationTypesDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            NotificationTypes entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(NotificationTypes entity);

	/**
	 * Delete a persistent NotificationTypes entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * INotificationTypesDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            NotificationTypes entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(NotificationTypes entity);

	/**
	 * Persist a previously saved NotificationTypes entity and return it or a
	 * copy of it to the sender. A copy of the NotificationTypes entity
	 * parameter is returned when the JPA persistence mechanism has not
	 * previously been tracking the updated entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently saved to the persistence store, i.e., database. This
	 * method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = INotificationTypesDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            NotificationTypes entity to update
	 * @returns NotificationTypes the persisted NotificationTypes entity
	 *          instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public NotificationTypes update(NotificationTypes entity);

	public NotificationTypes findById(Long id);

	/**
	 * Find all NotificationTypes entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the NotificationTypes property to query
	 * @param value
	 *            the property value to match
	 * @return List<NotificationTypes> found by query
	 */
	public List<NotificationTypes> findByProperty(String propertyName, Object value);

	public List<NotificationTypes> findByNttTexto(Object nttTexto);

	public List<NotificationTypes> findByNttDestinatario(Object nttDestinatario);

	public List<NotificationTypes> findByNttSolicitante(Object nttSolicitante);

	public List<NotificationTypes> findByNtfNombre(Object ntfNombre);

	/**
	 * Find all NotificationTypes entities.
	 * 
	 * @return List<NotificationTypes> all NotificationTypes entities
	 */
	public List<NotificationTypes> findAll();
}