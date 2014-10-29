package geniar.siscar.persistence;

import geniar.siscar.model.Accidents;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * Accidents entities. Transaction control of the save(), update() and delete()
 * operations must be handled externally by senders of these methods or must be
 * manually added to each of these methods for data to be persisted to the JPA
 * datastore.
 * 
 * @see geniar.siscar.model.Accidents
 * @author MyEclipse Persistence Tools
 */

public class AccidentsDAO implements IAccidentsDAO {
	// property constants
	public static final String ACC_SANC_INTER_ACTA = "accSancInterActa";
	public static final String ACC_APROBADO_ACTA = "accAprobadoActa";
	public static final String ACC_ORDEN_TRABAJO_ACTA = "accOrdenTrabajoActa";
	public static final String ACC_RECLAMO = "accReclamo";
	public static final String ACC_TOTAL_TESTIGOS = "accTotalTestigos";
	public static final String ACC_DESCRIPCION = "accDescripcion";
	public static final String ACC_OBSERVACIONES = "accObservaciones";
	public static final String ACC_RECOMENDACIONES = "accRecomendaciones";
	public static final String ACC_SITIO_ACCIDENTE = "accSitioAccidente";
	public static final String ACC_USO = "accUso";
	public static final String ACC_NUMERO_SINIESTRO = "accNumeroSiniestro";
	public static final String ACC_TOTAL_MUERTOS = "accTotalMuertos";
	public static final String ACC_TOTAL_HERIDOS = "accTotalHeridos";
	public static final String ACC_VEHICULOS_INVOLUCRADOS = "accVehiculosInvolucrados";
	public static final String ACC_INTERVINO_TRANSITO = "accIntervinoTransito";
	public static final String ACC_CEDULA_CONDUC = "accCedulaConduc";
	public static final String ACC_NOMBRE_CONDUCT = "accNombreConduct";
	public static final String ACC_CARGO_CONDUCT = "accCargoConduct";
	public static final String ACC_COD_CARGO_ACC = "accCodCargoAcc";
	public static final String ACC_VALOR_DANO = "accValorDano";
	public static final String ACC_DEDUCIBLES_PESOS = "accDeduciblesPesos";
	public static final String ACC_DEDUCIBLES_DOLAR = "accDeduciblesDolar";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

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
	 * AccidentsDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Accidents entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Accidents entity) {
		EntityManagerHelper.log("saving Accidents instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

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
	 * AccidentsDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Accidents entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Accidents entity) {
		EntityManagerHelper.log("deleting Accidents instance", Level.INFO, null);
		try {
			entity = getEntityManager().getReference(Accidents.class, entity.getAccCodigo());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

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
	 * entity = AccidentsDAO.update(entity);
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
	public Accidents update(Accidents entity) {
		EntityManagerHelper.log("updating Accidents instance", Level.INFO, null);
		try {
			Accidents result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Accidents findById(Long id) {
		EntityManagerHelper.log("finding Accidents instance with id: " + id, Level.INFO, null);
		try {
			Accidents instance = getEntityManager().find(Accidents.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all Accidents entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Accidents property to query
	 * @param value
	 *            the property value to match
	 * @return List<Accidents> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Accidents> findByProperty(String propertyName, final Object value) {
		EntityManagerHelper.log("finding Accidents instance with property: " + propertyName + ", value: " + value,
				Level.INFO, null);
		try {
			final String queryString = "select model from Accidents model where model." + propertyName
					+ "= :propertyValue";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("propertyValue", value);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find by property name failed", Level.SEVERE, re);
			throw re;
		}
	}

	public List<Accidents> findByAccSancInterActa(Object accSancInterActa) {
		return findByProperty(ACC_SANC_INTER_ACTA, accSancInterActa);
	}

	public List<Accidents> findByAccAprobadoActa(Object accAprobadoActa) {
		return findByProperty(ACC_APROBADO_ACTA, accAprobadoActa);
	}

	public List<Accidents> findByAccOrdenTrabajoActa(Object accOrdenTrabajoActa) {
		return findByProperty(ACC_ORDEN_TRABAJO_ACTA, accOrdenTrabajoActa);
	}

	public List<Accidents> findByAccReclamo(Object accReclamo) {
		return findByProperty(ACC_RECLAMO, accReclamo);
	}

	public List<Accidents> findByAccTotalTestigos(Object accTotalTestigos) {
		return findByProperty(ACC_TOTAL_TESTIGOS, accTotalTestigos);
	}

	public List<Accidents> findByAccDescripcion(Object accDescripcion) {
		return findByProperty(ACC_DESCRIPCION, accDescripcion);
	}

	public List<Accidents> findByAccObservaciones(Object accObservaciones) {
		return findByProperty(ACC_OBSERVACIONES, accObservaciones);
	}

	public List<Accidents> findByAccRecomendaciones(Object accRecomendaciones) {
		return findByProperty(ACC_RECOMENDACIONES, accRecomendaciones);
	}

	public List<Accidents> findByAccSitioAccidente(Object accSitioAccidente) {
		return findByProperty(ACC_SITIO_ACCIDENTE, accSitioAccidente);
	}

	public List<Accidents> findByAccUso(Object accUso) {
		return findByProperty(ACC_USO, accUso);
	}

	public List<Accidents> findByAccNumeroSiniestro(Object accNumeroSiniestro) {
		return findByProperty(ACC_NUMERO_SINIESTRO, accNumeroSiniestro);
	}

	public List<Accidents> findByAccTotalMuertos(Object accTotalMuertos) {
		return findByProperty(ACC_TOTAL_MUERTOS, accTotalMuertos);
	}

	public List<Accidents> findByAccTotalHeridos(Object accTotalHeridos) {
		return findByProperty(ACC_TOTAL_HERIDOS, accTotalHeridos);
	}

	public List<Accidents> findByAccVehiculosInvolucrados(Object accVehiculosInvolucrados) {
		return findByProperty(ACC_VEHICULOS_INVOLUCRADOS, accVehiculosInvolucrados);
	}

	public List<Accidents> findByAccIntervinoTransito(Object accIntervinoTransito) {
		return findByProperty(ACC_INTERVINO_TRANSITO, accIntervinoTransito);
	}

	public List<Accidents> findByAccCedulaConduc(Object accCedulaConduc) {
		return findByProperty(ACC_CEDULA_CONDUC, accCedulaConduc);
	}

	public List<Accidents> findByAccNombreConduct(Object accNombreConduct) {
		return findByProperty(ACC_NOMBRE_CONDUCT, accNombreConduct);
	}

	public List<Accidents> findByAccCargoConduct(Object accCargoConduct) {
		return findByProperty(ACC_CARGO_CONDUCT, accCargoConduct);
	}

	public List<Accidents> findByAccCodCargoAcc(Object accCodCargoAcc) {
		return findByProperty(ACC_COD_CARGO_ACC, accCodCargoAcc);
	}

	public List<Accidents> findByAccValorDano(Object accValorDano) {
		return findByProperty(ACC_VALOR_DANO, accValorDano);
	}

	public List<Accidents> findByAccDeduciblesPesos(Object accDeduciblesPesos) {
		return findByProperty(ACC_DEDUCIBLES_PESOS, accDeduciblesPesos);
	}

	public List<Accidents> findByAccDeduciblesDolar(Object accDeduciblesDolar) {
		return findByProperty(ACC_DEDUCIBLES_DOLAR, accDeduciblesDolar);
	}

	/**
	 * Find all Accidents entities.
	 * 
	 * @return List<Accidents> all Accidents entities
	 */
	@SuppressWarnings("unchecked")
	public List<Accidents> findAll() {
		EntityManagerHelper.log("finding all Accidents instances", Level.INFO, null);
		try {
			final String queryString = "select model from Accidents model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}