package geniar.siscar.persistence;
// default package

import geniar.siscar.model.PoliciesHistoric;

import java.util.List;

/**
 * Interface for PoliciesHistoricDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IPoliciesHistoricDAO {
	/**
	 * Perform an initial save of a previously unsaved PoliciesHistoric entity.
	 * All subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IPoliciesHistoricDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            PoliciesHistoric entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(PoliciesHistoric entity);

	/**
	 * Delete a persistent PoliciesHistoric entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IPoliciesHistoricDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            PoliciesHistoric entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(PoliciesHistoric entity);

	/**
	 * Persist a previously saved PoliciesHistoric entity and return it or a
	 * copy of it to the sender. A copy of the PoliciesHistoric entity parameter
	 * is returned when the JPA persistence mechanism has not previously been
	 * tracking the updated entity. This operation must be performed within the
	 * a database transaction context for the entity's data to be permanently
	 * saved to the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IPoliciesHistoricDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            PoliciesHistoric entity to update
	 * @returns PoliciesHistoric the persisted PoliciesHistoric entity instance,
	 *          may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public PoliciesHistoric update(PoliciesHistoric entity);

	public PoliciesHistoric findById(Long id);

	/**
	 * Find all PoliciesHistoric entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the PoliciesHistoric property to query
	 * @param value
	 *            the property value to match
	 * @return List<PoliciesHistoric> found by query
	 */
	public List<PoliciesHistoric> findByProperty(String propertyName,
			Object value);

	public List<PoliciesHistoric> findByUsrLogin(Object usrLogin);

	public List<PoliciesHistoric> findByPlsNumero(Object plsNumero);

	public List<PoliciesHistoric> findByPlsDocUno(Object plsDocUno);

	public List<PoliciesHistoric> findByPlsDocDos(Object plsDocDos);

	public List<PoliciesHistoric> findByPlsEstado(Object plsEstado);

	/**
	 * Find all PoliciesHistoric entities.
	 * 
	 * @return List<PoliciesHistoric> all PoliciesHistoric entities
	 */
	public List<PoliciesHistoric> findAll();
}