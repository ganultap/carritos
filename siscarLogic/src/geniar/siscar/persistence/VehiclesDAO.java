package geniar.siscar.persistence;

import geniar.siscar.model.Vehicles;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * Vehicles entities. Transaction control of the save(), update() and delete()
 * operations must be handled externally by senders of these methods or must be
 * manually added to each of these methods for data to be persisted to the JPA
 * datastore.
 * 
 * @see geniar.siscar.model.Vehicles
 * @author MyEclipse Persistence Tools
 */

public class VehiclesDAO implements IVehiclesDAO {
	// property constants
	public static final String VHC_CIU_ADUAN = "vhcCiuAduan";
	public static final String VHC_UNIDAD_TAQUIM = "vhcUnidadTaquim";
	public static final String VHC_VALOR_ASIGNACION = "vhcValorAsignacion";
	public static final String VHC_CATALOGADO = "vhcCatalogado";
	public static final String VHC_PLACA_DIPLOMATICA = "vhcPlacaDiplomatica";
	public static final String VHC_PLACA_ACTIVO_FIJO = "vhcPlacaActivoFijo";
	public static final String VHC_ESPECIFIC_TEC = "vhcEspecificTec";
	public static final String VHC_DOCUMENT_TRANS = "vhcDocumentTrans";
	public static final String VHC_NUMERO_FACTURA = "vhcNumeroFactura";
	public static final String VHC_NUMERO_CHASIS = "vhcNumeroChasis";
	public static final String VHC_ORDER_COMPRA = "vhcOrderCompra";
	public static final String VHC_PROVEEDOR = "vhcProveedor";
	public static final String VHC_CAPACIDAD = "vhcCapacidad";
	public static final String VHC_CAP_MAX_TANQ = "vhcCapMaxTanq";
	public static final String VHC_VALOR_COMERCIAL = "vhcValorComercial";
	public static final String VHC_ANO_VAL_COM = "vhcAnoValCom";
	public static final String VHC_NUMERO_MANIFIESTO = "vhcNumeroManifiesto";
	public static final String VHC_NUM_DECL_IMPOR = "vhcNumDeclImpor";
	public static final String VHC_NUMERO_TL = "vhcNumeroTl";
	public static final String VHC_REMPLAZA_A = "vhcRemplazaA";
	public static final String VHC_PL_REM_VEHI = "vhcPlRemVehi";
	public static final String VHC_AT_INICIAL = "vhcAtInicial";
	public static final String VHC_VALOR_CIF = "vhcValorCif";
	public static final String VHC_VIDA_UTIL = "vhcVidaUtil";
	public static final String VHC_VALOR_FOB = "vhcValorFob";
	public static final String VHC_CARGOS_IMPORT = "vhcCargosImport";
	public static final String VHC_OBSERVACIONES = "vhcObservaciones";
	public static final String VHC_DESCRIPCION = "vhcDescripcion";
	public static final String VHC_ANO = "vhcAno";
	public static final String VHC_NUMERO_LEVANTE = "vhcNumeroLevante";
	public static final String VHC_NUM_REFER_CAT = "vhcNumReferCat";
	public static final String VHC_ODOMETRO = "vhcOdometro";
	public static final String VHC_CILINDRAJE = "vhcCilindraje";
	public static final String VHC_NUMERO_MOTOR = "vhcNumeroMotor";
	public static final String VHC_ATFINAL = "vhcAtfinal";
	public static final String VHC_NUMERO_SERIE = "vhcNumeroSerie";
	public static final String VHC_MODELO = "vhcModelo";
	public static final String VHC_COLOR = "vhcColor";
	public static final String VHC_PROMEDIO_TANQUEO = "vhcPromedioTanqueo";
	public static final String VHC_KILOMETRAJE_ACTUAL = "vhcKilometrajeActual";
	public static final String VHC_ANOFABRICACION = "vhcAnofabricacion";
	public static final String VHC_MESFABRICACION = "vhcMesfabricacion";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

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
	 * VehiclesDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Vehicles entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Vehicles entity) {
		EntityManagerHelper.log("saving Vehicles instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

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
	 * VehiclesDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Vehicles entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Vehicles entity) {
		EntityManagerHelper.log("deleting Vehicles instance", Level.INFO, null);
		try {
			entity = getEntityManager().getReference(Vehicles.class, entity.getVhcCodigo());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

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
	 * entity = VehiclesDAO.update(entity);
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
	public Vehicles update(Vehicles entity) {
		EntityManagerHelper.log("updating Vehicles instance", Level.INFO, null);
		try {
			Vehicles result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Vehicles findById(Long id) {
		EntityManagerHelper.log("finding Vehicles instance with id: " + id, Level.INFO, null);
		try {
			Vehicles instance = getEntityManager().find(Vehicles.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all Vehicles entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Vehicles property to query
	 * @param value
	 *            the property value to match
	 * @return List<Vehicles> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Vehicles> findByProperty(String propertyName, final Object value) {
		EntityManagerHelper.log("finding Vehicles instance with property: " + propertyName + ", value: " + value,
				Level.INFO, null);
		try {
			final String queryString = "select model from Vehicles model where model." + propertyName
					+ "= :propertyValue";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("propertyValue", value);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find by property name failed", Level.SEVERE, re);
			throw re;
		}
	}

	public List<Vehicles> findByVhcCiuAduan(Object vhcCiuAduan) {
		return findByProperty(VHC_CIU_ADUAN, vhcCiuAduan);
	}

	public List<Vehicles> findByVhcUnidadTaquim(Object vhcUnidadTaquim) {
		return findByProperty(VHC_UNIDAD_TAQUIM, vhcUnidadTaquim);
	}

	public List<Vehicles> findByVhcValorAsignacion(Object vhcValorAsignacion) {
		return findByProperty(VHC_VALOR_ASIGNACION, vhcValorAsignacion);
	}

	public List<Vehicles> findByVhcCatalogado(Object vhcCatalogado) {
		return findByProperty(VHC_CATALOGADO, vhcCatalogado);
	}

	public List<Vehicles> findByVhcPlacaDiplomatica(Object vhcPlacaDiplomatica) {
		return findByProperty(VHC_PLACA_DIPLOMATICA, vhcPlacaDiplomatica);
	}

	public List<Vehicles> findByVhcPlacaActivoFijo(Object vhcPlacaActivoFijo) {
		return findByProperty(VHC_PLACA_ACTIVO_FIJO, vhcPlacaActivoFijo);
	}

	public List<Vehicles> findByVhcEspecificTec(Object vhcEspecificTec) {
		return findByProperty(VHC_ESPECIFIC_TEC, vhcEspecificTec);
	}

	public List<Vehicles> findByVhcDocumentTrans(Object vhcDocumentTrans) {
		return findByProperty(VHC_DOCUMENT_TRANS, vhcDocumentTrans);
	}

	public List<Vehicles> findByVhcNumeroFactura(Object vhcNumeroFactura) {
		return findByProperty(VHC_NUMERO_FACTURA, vhcNumeroFactura);
	}

	public List<Vehicles> findByVhcNumeroChasis(Object vhcNumeroChasis) {
		return findByProperty(VHC_NUMERO_CHASIS, vhcNumeroChasis);
	}

	public List<Vehicles> findByVhcOrderCompra(Object vhcOrderCompra) {
		return findByProperty(VHC_ORDER_COMPRA, vhcOrderCompra);
	}

	public List<Vehicles> findByVhcProveedor(Object vhcProveedor) {
		return findByProperty(VHC_PROVEEDOR, vhcProveedor);
	}

	public List<Vehicles> findByVhcCapacidad(Object vhcCapacidad) {
		return findByProperty(VHC_CAPACIDAD, vhcCapacidad);
	}

	public List<Vehicles> findByVhcCapMaxTanq(Object vhcCapMaxTanq) {
		return findByProperty(VHC_CAP_MAX_TANQ, vhcCapMaxTanq);
	}

	public List<Vehicles> findByVhcValorComercial(Object vhcValorComercial) {
		return findByProperty(VHC_VALOR_COMERCIAL, vhcValorComercial);
	}

	public List<Vehicles> findByVhcAnoValCom(Object vhcAnoValCom) {
		return findByProperty(VHC_ANO_VAL_COM, vhcAnoValCom);
	}

	public List<Vehicles> findByVhcNumeroManifiesto(Object vhcNumeroManifiesto) {
		return findByProperty(VHC_NUMERO_MANIFIESTO, vhcNumeroManifiesto);
	}

	public List<Vehicles> findByVhcNumDeclImpor(Object vhcNumDeclImpor) {
		return findByProperty(VHC_NUM_DECL_IMPOR, vhcNumDeclImpor);
	}

	public List<Vehicles> findByVhcNumeroTl(Object vhcNumeroTl) {
		return findByProperty(VHC_NUMERO_TL, vhcNumeroTl);
	}

	public List<Vehicles> findByVhcRemplazaA(Object vhcRemplazaA) {
		return findByProperty(VHC_REMPLAZA_A, vhcRemplazaA);
	}

	public List<Vehicles> findByVhcPlRemVehi(Object vhcPlRemVehi) {
		return findByProperty(VHC_PL_REM_VEHI, vhcPlRemVehi);
	}

	public List<Vehicles> findByVhcAtInicial(Object vhcAtInicial) {
		return findByProperty(VHC_AT_INICIAL, vhcAtInicial);
	}

	public List<Vehicles> findByVhcValorCif(Object vhcValorCif) {
		return findByProperty(VHC_VALOR_CIF, vhcValorCif);
	}

	public List<Vehicles> findByVhcVidaUtil(Object vhcVidaUtil) {
		return findByProperty(VHC_VIDA_UTIL, vhcVidaUtil);
	}

	public List<Vehicles> findByVhcValorFob(Object vhcValorFob) {
		return findByProperty(VHC_VALOR_FOB, vhcValorFob);
	}

	public List<Vehicles> findByVhcCargosImport(Object vhcCargosImport) {
		return findByProperty(VHC_CARGOS_IMPORT, vhcCargosImport);
	}

	public List<Vehicles> findByVhcObservaciones(Object vhcObservaciones) {
		return findByProperty(VHC_OBSERVACIONES, vhcObservaciones);
	}

	public List<Vehicles> findByVhcDescripcion(Object vhcDescripcion) {
		return findByProperty(VHC_DESCRIPCION, vhcDescripcion);
	}

	public List<Vehicles> findByVhcAno(Object vhcAno) {
		return findByProperty(VHC_ANO, vhcAno);
	}

	public List<Vehicles> findByVhcNumeroLevante(Object vhcNumeroLevante) {
		return findByProperty(VHC_NUMERO_LEVANTE, vhcNumeroLevante);
	}

	public List<Vehicles> findByVhcNumReferCat(Object vhcNumReferCat) {
		return findByProperty(VHC_NUM_REFER_CAT, vhcNumReferCat);
	}

	public List<Vehicles> findByVhcOdometro(Object vhcOdometro) {
		return findByProperty(VHC_ODOMETRO, vhcOdometro);
	}

	public List<Vehicles> findByVhcCilindraje(Object vhcCilindraje) {
		return findByProperty(VHC_CILINDRAJE, vhcCilindraje);
	}

	public List<Vehicles> findByVhcNumeroMotor(Object vhcNumeroMotor) {
		return findByProperty(VHC_NUMERO_MOTOR, vhcNumeroMotor);
	}

	public List<Vehicles> findByVhcAtfinal(Object vhcAtfinal) {
		return findByProperty(VHC_ATFINAL, vhcAtfinal);
	}

	public List<Vehicles> findByVhcNumeroSerie(Object vhcNumeroSerie) {
		return findByProperty(VHC_NUMERO_SERIE, vhcNumeroSerie);
	}

	public List<Vehicles> findByVhcModelo(Object vhcModelo) {
		return findByProperty(VHC_MODELO, vhcModelo);
	}

	public List<Vehicles> findByVhcColor(Object vhcColor) {
		return findByProperty(VHC_COLOR, vhcColor);
	}

	public List<Vehicles> findByVhcPromedioTanqueo(Object vhcPromedioTanqueo) {
		return findByProperty(VHC_PROMEDIO_TANQUEO, vhcPromedioTanqueo);
	}

	public List<Vehicles> findByVhcKilometrajeActual(Object vhcKilometrajeActual) {
		return findByProperty(VHC_KILOMETRAJE_ACTUAL, vhcKilometrajeActual);
	}

	public List<Vehicles> findByVhcAnofabricacion(Object vhcAnofabricacion) {
		return findByProperty(VHC_ANOFABRICACION, vhcAnofabricacion);
	}

	public List<Vehicles> findByVhcMesfabricacion(Object vhcMesfabricacion) {
		return findByProperty(VHC_MESFABRICACION, vhcMesfabricacion);
	}

	/**
	 * Find all Vehicles entities.
	 * 
	 * @return List<Vehicles> all Vehicles entities
	 */
	@SuppressWarnings("unchecked")
	public List<Vehicles> findAll() {
		EntityManagerHelper.log("finding all Vehicles instances", Level.INFO, null);
		try {
			final String queryString = "select model from Vehicles model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}