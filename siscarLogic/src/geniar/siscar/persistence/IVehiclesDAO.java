package geniar.siscar.persistence;

import geniar.siscar.model.Vehicles;

import java.util.List;

/**
 * Interface for VehiclesDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IVehiclesDAO {
	/**
	 * Perform an initial save of a previously unsaved Vehicles entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IVehiclesDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Vehicles entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Vehicles entity);

	/**
	 * Delete a persistent Vehicles entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IVehiclesDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Vehicles entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Vehicles entity);

	/**
	 * Persist a previously saved Vehicles entity and return it or a copy of it
	 * to the sender. A copy of the Vehicles entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IVehiclesDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Vehicles entity to update
	 * @returns Vehicles the persisted Vehicles entity instance, may not be the
	 *          same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Vehicles update(Vehicles entity);

	public Vehicles findById(Long id);

	/**
	 * Find all Vehicles entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Vehicles property to query
	 * @param value
	 *            the property value to match
	 * @return List<Vehicles> found by query
	 */
	public List<Vehicles> findByProperty(String propertyName, Object value);

	public List<Vehicles> findByVhcCiuAduan(Object vhcCiuAduan);

	public List<Vehicles> findByVhcUnidadTaquim(Object vhcUnidadTaquim);

	public List<Vehicles> findByVhcValorAsignacion(Object vhcValorAsignacion);

	public List<Vehicles> findByVhcCatalogado(Object vhcCatalogado);

	public List<Vehicles> findByVhcPlacaDiplomatica(Object vhcPlacaDiplomatica);

	public List<Vehicles> findByVhcPlacaActivoFijo(Object vhcPlacaActivoFijo);

	public List<Vehicles> findByVhcEspecificTec(Object vhcEspecificTec);

	public List<Vehicles> findByVhcDocumentTrans(Object vhcDocumentTrans);

	public List<Vehicles> findByVhcNumeroFactura(Object vhcNumeroFactura);

	public List<Vehicles> findByVhcNumeroChasis(Object vhcNumeroChasis);

	public List<Vehicles> findByVhcOrderCompra(Object vhcOrderCompra);

	public List<Vehicles> findByVhcProveedor(Object vhcProveedor);

	public List<Vehicles> findByVhcCapacidad(Object vhcCapacidad);

	public List<Vehicles> findByVhcCapMaxTanq(Object vhcCapMaxTanq);

	public List<Vehicles> findByVhcValorComercial(Object vhcValorComercial);

	public List<Vehicles> findByVhcAnoValCom(Object vhcAnoValCom);

	public List<Vehicles> findByVhcNumeroManifiesto(Object vhcNumeroManifiesto);

	public List<Vehicles> findByVhcNumDeclImpor(Object vhcNumDeclImpor);

	public List<Vehicles> findByVhcNumeroTl(Object vhcNumeroTl);

	public List<Vehicles> findByVhcRemplazaA(Object vhcRemplazaA);

	public List<Vehicles> findByVhcPlRemVehi(Object vhcPlRemVehi);

	public List<Vehicles> findByVhcAtInicial(Object vhcAtInicial);

	public List<Vehicles> findByVhcValorCif(Object vhcValorCif);

	public List<Vehicles> findByVhcVidaUtil(Object vhcVidaUtil);

	public List<Vehicles> findByVhcValorFob(Object vhcValorFob);

	public List<Vehicles> findByVhcCargosImport(Object vhcCargosImport);

	public List<Vehicles> findByVhcObservaciones(Object vhcObservaciones);

	public List<Vehicles> findByVhcDescripcion(Object vhcDescripcion);

	public List<Vehicles> findByVhcAno(Object vhcAno);

	public List<Vehicles> findByVhcNumeroLevante(Object vhcNumeroLevante);

	public List<Vehicles> findByVhcNumReferCat(Object vhcNumReferCat);

	public List<Vehicles> findByVhcOdometro(Object vhcOdometro);

	public List<Vehicles> findByVhcCilindraje(Object vhcCilindraje);

	public List<Vehicles> findByVhcNumeroMotor(Object vhcNumeroMotor);

	public List<Vehicles> findByVhcAtfinal(Object vhcAtfinal);

	public List<Vehicles> findByVhcNumeroSerie(Object vhcNumeroSerie);

	public List<Vehicles> findByVhcModelo(Object vhcModelo);

	public List<Vehicles> findByVhcColor(Object vhcColor);

	public List<Vehicles> findByVhcPromedioTanqueo(Object vhcPromedioTanqueo);

	public List<Vehicles> findByVhcKilometrajeActual(Object vhcKilometrajeActual);

	public List<Vehicles> findByVhcAnofabricacion(Object vhcAnofabricacion);

	public List<Vehicles> findByVhcMesfabricacion(Object vhcMesfabricacion);

	/**
	 * Find all Vehicles entities.
	 * 
	 * @return List<Vehicles> all Vehicles entities
	 */
	public List<Vehicles> findAll();
}