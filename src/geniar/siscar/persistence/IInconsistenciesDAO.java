package geniar.siscar.persistence;

import geniar.siscar.model.Inconsistencies;

import java.util.List;

/**
 * Interface for InconsistenciesDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IInconsistenciesDAO {
	/**
	 * Perform an initial save of a previously unsaved Inconsistencies entity.
	 * All subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IInconsistenciesDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Inconsistencies entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Inconsistencies entity);

	/**
	 * Delete a persistent Inconsistencies entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IInconsistenciesDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Inconsistencies entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Inconsistencies entity);

	/**
	 * Persist a previously saved Inconsistencies entity and return it or a copy
	 * of it to the sender. A copy of the Inconsistencies entity parameter is
	 * returned when the JPA persistence mechanism has not previously been
	 * tracking the updated entity. This operation must be performed within the
	 * a database transaction context for the entity's data to be permanently
	 * saved to the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IInconsistenciesDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Inconsistencies entity to update
	 * @returns Inconsistencies the persisted Inconsistencies entity instance,
	 *          may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Inconsistencies update(Inconsistencies entity);

	public Inconsistencies findById(Long id);

	/**
	 * Find all Inconsistencies entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Inconsistencies property to query
	 * @param value
	 *            the property value to match
	 * @return List<Inconsistencies> found by query
	 */
	public List<Inconsistencies> findByProperty(String propertyName,
			Object value);

	public List<Inconsistencies> findByIncEstado(Object incEstado);

	public List<Inconsistencies> findByIncObservacion(Object incObservacion);

	public List<Inconsistencies> findByUsrLogin(Object usrLogin);

	public List<Inconsistencies> findByVhcPlaca(Object vhcPlaca);

	public List<Inconsistencies> findByIncValorIva(Object incValorIva);

	public List<Inconsistencies> findByIncValorPrima(Object incValorPrima);

	public List<Inconsistencies> findByIncValorTotal(Object incValorTotal);

	public List<Inconsistencies> findByIncValorAsegurado(
			Object incValorAsegurado);

	public List<Inconsistencies> findByPvsCarnetAsignatario(
			Object pvsCarnetAsignatario);

	/**
	 * Find all Inconsistencies entities.
	 * 
	 * @return List<Inconsistencies> all Inconsistencies entities
	 */
	public List<Inconsistencies> findAll();
}
