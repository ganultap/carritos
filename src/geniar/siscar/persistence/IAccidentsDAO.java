package geniar.siscar.persistence;

import geniar.siscar.model.Accidents;

import java.util.List;

/**
 * Interface for AccidentsDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IAccidentsDAO {
	/**
	 * Perform an initial save of a previously unsaved Accidents entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IAccidentsDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Accidents entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Accidents entity);

	/**
	 * Delete a persistent Accidents entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IAccidentsDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Accidents entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Accidents entity);

	/**
	 * Persist a previously saved Accidents entity and return it or a copy of it
	 * to the sender. A copy of the Accidents entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IAccidentsDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Accidents entity to update
	 * @returns Accidents the persisted Accidents entity instance, may not be
	 *          the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Accidents update(Accidents entity);

	public Accidents findById(Long id);

	/**
	 * Find all Accidents entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Accidents property to query
	 * @param value
	 *            the property value to match
	 * @return List<Accidents> found by query
	 */
	public List<Accidents> findByProperty(String propertyName, Object value);

	public List<Accidents> findByAccSancInterActa(Object accSancInterActa);

	public List<Accidents> findByAccAprobadoActa(Object accAprobadoActa);

	public List<Accidents> findByAccOrdenTrabajoActa(Object accOrdenTrabajoActa);

	public List<Accidents> findByAccReclamo(Object accReclamo);

	public List<Accidents> findByAccTotalTestigos(Object accTotalTestigos);

	public List<Accidents> findByAccDescripcion(Object accDescripcion);

	public List<Accidents> findByAccObservaciones(Object accObservaciones);

	public List<Accidents> findByAccRecomendaciones(Object accRecomendaciones);

	public List<Accidents> findByAccSitioAccidente(Object accSitioAccidente);

	public List<Accidents> findByAccUso(Object accUso);

	public List<Accidents> findByAccNumeroSiniestro(Object accNumeroSiniestro);

	public List<Accidents> findByAccTotalMuertos(Object accTotalMuertos);

	public List<Accidents> findByAccTotalHeridos(Object accTotalHeridos);

	public List<Accidents> findByAccVehiculosInvolucrados(Object accVehiculosInvolucrados);

	public List<Accidents> findByAccIntervinoTransito(Object accIntervinoTransito);

	public List<Accidents> findByAccCedulaConduc(Object accCedulaConduc);

	public List<Accidents> findByAccNombreConduct(Object accNombreConduct);

	public List<Accidents> findByAccCargoConduct(Object accCargoConduct);

	public List<Accidents> findByAccCodCargoAcc(Object accCodCargoAcc);

	public List<Accidents> findByAccValorDano(Object accValorDano);

	public List<Accidents> findByAccDeduciblesPesos(Object accDeduciblesPesos);

	public List<Accidents> findByAccDeduciblesDolar(Object accDeduciblesDolar);

	/**
	 * Find all Accidents entities.
	 * 
	 * @return List<Accidents> all Accidents entities
	 */
	public List<Accidents> findAll();
}