package geniar.siscar.persistence;

import geniar.siscar.model.ActualOthersApplications;

import java.util.List;

/**
 * Interface for ActualOthersApplicationsDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IActualOthersApplicationsDAO {
	/**
	 * Perform an initial save of a previously unsaved ActualOthersApplications
	 * entity. All subsequent persist actions of this entity should use the
	 * #update() method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IActualOthersApplicationsDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            ActualOthersApplications entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(ActualOthersApplications entity);

	/**
	 * Delete a persistent ActualOthersApplications entity. This operation must
	 * be performed within the a database transaction context for the entity's
	 * data to be permanently deleted from the persistence store, i.e.,
	 * database. This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IActualOthersApplicationsDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            ActualOthersApplications entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(ActualOthersApplications entity);

	/**
	 * Persist a previously saved ActualOthersApplications entity and return it
	 * or a copy of it to the sender. A copy of the ActualOthersApplications
	 * entity parameter is returned when the JPA persistence mechanism has not
	 * previously been tracking the updated entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently saved to the persistence store, i.e., database. This
	 * method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IActualOthersApplicationsDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            ActualOthersApplications entity to update
	 * @returns ActualOthersApplications the persisted ActualOthersApplications
	 *          entity instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public ActualOthersApplications update(ActualOthersApplications entity);

	public ActualOthersApplications findById(Long id);

	/**
	 * Find all ActualOthersApplications entities with a specific property
	 * value.
	 * 
	 * @param propertyName
	 *            the name of the ActualOthersApplications property to query
	 * @param value
	 *            the property value to match
	 * @return List<ActualOthersApplications> found by query
	 */
	public List<ActualOthersApplications> findByProperty(String propertyName,
			Object value);

	public List<ActualOthersApplications> findByAoaState(Object aoaState);

	public List<ActualOthersApplications> findByPSob(Object PSob);

	public List<ActualOthersApplications> findByPCurr(Object PCurr);

	public List<ActualOthersApplications> findByPUser(Object PUser);

	public List<ActualOthersApplications> findByPCategory(Object PCategory);

	public List<ActualOthersApplications> findByPSource(Object PSource);

	public List<ActualOthersApplications> findByPConvType(Object PConvType);

	public List<ActualOthersApplications> findByPConvRate(Object PConvRate);

	public List<ActualOthersApplications> findByPCompany(Object PCompany);

	public List<ActualOthersApplications> findByPAccount(Object PAccount);

	public List<ActualOthersApplications> findByPCcenter(Object PCcenter);

	public List<ActualOthersApplications> findByPAuxiliary(Object PAuxiliary);

	public List<ActualOthersApplications> findByPEntDr(Object PEntDr);

	public List<ActualOthersApplications> findByPEntCr(Object PEntCr);

	public List<ActualOthersApplications> findByPBname(Object PBname);

	public List<ActualOthersApplications> findByPDescription(Object PDescription);

	public List<ActualOthersApplications> findByPNit(Object PNit);

	public List<ActualOthersApplications> findByPAttribute2(Object PAttribute2);

	public List<ActualOthersApplications> findByPAttribute5(Object PAttribute5);

	public List<ActualOthersApplications> findByPAttribute6(Object PAttribute6);

	public List<ActualOthersApplications> findByPAttribute7(Object PAttribute7);

	public List<ActualOthersApplications> findByPAttribute8(Object PAttribute8);

	public List<ActualOthersApplications> findByPAttribute9(Object PAttribute9);

	public List<ActualOthersApplications> findByPAttribute10(Object PAttribute10);

	/**
	 * Find all ActualOthersApplications entities.
	 * 
	 * @return List<ActualOthersApplications> all ActualOthersApplications
	 *         entities
	 */
	public List<ActualOthersApplications> findAll();
}