package geniar.siscar.logic.accidents.servicesImpl;

import geniar.siscar.model.Accidents;
import geniar.siscar.model.AccidentsFiles;
import geniar.siscar.model.AccidentsResults;
import geniar.siscar.model.AccidentsStates;
import geniar.siscar.model.Acts;
import geniar.siscar.model.Assistants;
import geniar.siscar.model.Cities;
import geniar.siscar.model.Driver;
import geniar.siscar.model.InjuredPersons;
import geniar.siscar.model.InvolvedVehicles;
import geniar.siscar.model.Responsibility;
import geniar.siscar.model.Severity;
import geniar.siscar.model.Witnesses;
import geniar.siscar.persistence.EntityManagerHelper;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;

import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SearchParametersAccidents {

	private static final Log log = LogFactory.getLog(SearchParametersAccidents.class);
	
	@SuppressWarnings("unchecked")
	public List<Responsibility> responsabilityOrder() {
		EntityManagerHelper.log("finding all Responsibility instances",
				Level.INFO, null);
		try {
			final String queryString = "select model from Responsibility model ORDER BY model.resNombre ASC";
			Query query = EntityManagerHelper.getEntityManager().createQuery(
					queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("responsabilityOrder", re);
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Severity> severityOrder() {
		EntityManagerHelper.log("finding all Severity instances", Level.INFO,
				null);
		try {
			final String queryString = "select model from Severity model ORDER BY model.sevNombre ASC";
			Query query = EntityManagerHelper.getEntityManager().createQuery(
					queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("severityOrder", re);
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Cities> ciudadesOrder() {
		EntityManagerHelper.log("finding all Cities instances", Level.INFO,
				null);
		try {
			final String queryString = "select model from Cities model WHERE model.countries=6 ORDER BY model.ctsNombre ASC";
			Query query = EntityManagerHelper.getEntityManager().createQuery(
					queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("ciudadesOrder", re);
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<AccidentsResults> resultsOrder() {
		EntityManagerHelper.log("finding all AccidentsResults instances",
				Level.INFO, null);
		try {
			final String queryString = "select model from AccidentsResults model ORDER BY model.aclNombre ASC";
			Query query = EntityManagerHelper.getEntityManager().createQuery(
					queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("resultsOrder", re);
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Accidents> findAccidentByPlaca(String placa, Date fechaInicia,
			Date fechaFinal) {

		EntityManagerHelper.log("finding all Accidents instances", Level.INFO,
				null);
		try {

			final String queryString = "select model from Accidents model WHERE model.vehicles.vhcPlacaDiplomatica= :placa"
					+ " AND model.accFechaAccidente BETWEEN :fechaInicia AND :fechaFinal";
			Query query = EntityManagerHelper.getEntityManager().createQuery(
					queryString);
			query.setParameter("placa", placa);
			query.setParameter("fechaInicia", fechaInicia);
			query.setParameter("fechaFinal", fechaFinal);
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findAccidentByPlaca", re);
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}

	}

	@SuppressWarnings("unchecked")
	public List<Accidents> findAccidentByNumeroSiniestro(Long numeroSiniestro,
			Date fechaInicia, Date fechaFinal) {

		EntityManagerHelper.log("finding all Accidents instances", Level.INFO,
				null);
		try {
			final String queryString = "select model from Accidents model WHERE model.accNumeroSiniestro= :numeroSiniestro"
					+ " OR model.accFechaAccidente BETWEEN :fechaInicia AND :fechaFinal";
			Query query = EntityManagerHelper.getEntityManager().createQuery(
					queryString);
			query.setParameter("numeroSiniestro", numeroSiniestro);
			query.setParameter("fechaInicia", fechaInicia);
			query.setParameter("fechaFinal", fechaFinal);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}

	}

	@SuppressWarnings("unchecked")
	public List<Accidents> findAccidentByEstado(Long idEstado,
			Date fechaInicia, Date fechaFinal) {

		EntityManagerHelper.log("finding all Accidents instances", Level.INFO,
				null);
		try {
			final String queryString = "select model from Accidents model WHERE model.accidentsStates.acsCode= :idEstado"
					+ " OR model.accFechaAccidente BETWEEN :fechaInicia AND :fechaFinal";
			Query query = EntityManagerHelper.getEntityManager().createQuery(
					queryString);
			query.setParameter("idEstado", idEstado);
			query.setParameter("fechaInicia", fechaInicia);
			query.setParameter("fechaFinal", fechaFinal);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}

	}

	@SuppressWarnings("unchecked")
	public List<AccidentsStates> accidentsStatesOrder() {
		EntityManagerHelper.log("finding all Responsibility instances",
				Level.INFO, null);
		try {
			final String queryString = "select model from AccidentsStates model ORDER BY model.acsNombre ASC";
			Query query = EntityManagerHelper.getEntityManager().createQuery(
					queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<InjuredPersons> lesionadosVehiculos(String placa) {

		try {
			final String queryString = "select model from InjuredPersons model where model.involvedVehicles.hnvPlaca= :placa";
			Query query = EntityManagerHelper.getEntityManager().createQuery(
					queryString);
			query.setParameter("placa", placa);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<InvolvedVehicles> listarVehiculosInvolucrados(Long idAccidente) {

		try {
			final String queryString = "select model from InvolvedVehicles model where model.accidents.accCodigo= :idAccidente";
			Query query = EntityManagerHelper.getEntityManager().createQuery(
					queryString);
			query.setParameter("idAccidente", idAccidente);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Witnesses> listarTestigos(Long idAccidente) {

		try {
			final String queryString = "select model from Witnesses model where model.accidents.accCodigo= :idAccidente";
			Query query = EntityManagerHelper.getEntityManager().createQuery(
					queryString);
			query.setParameter("idAccidente", idAccidente);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<AccidentsFiles> listarArchivos(Long idAccidente) {

		try {
			final String queryString = "select model from AccidentsFiles model where model.accidents.accCodigo= :idAccidente "
					+ "ORDER BY model.acfId ASC";
			Query query = EntityManagerHelper.getEntityManager().createQuery(
					queryString);
			query.setParameter("idAccidente", idAccidente);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<InjuredPersons> cantidadLesionados(Long idAccidente) {

		try {
			final String queryString = "select model from InjuredPersons model where "
					+ "model.involvedVehicles.accidents.accCodigo= :idAccidente";
			Query query = EntityManagerHelper.getEntityManager().createQuery(
					queryString);
			query.setParameter("idAccidente", idAccidente);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Acts> consultarActa(Long numeroActa, Date fechaIni,
			Date fechaFin) {

		try {
			final String queryString = "select model from Acts model where "
					+ "model.actCodigo= :numeroActa "
					+ "OR model.actFechaReunion BETWEEN :fechaIni AND :fechaFin";
			Query query = EntityManagerHelper.getEntityManager().createQuery(
					queryString);
			query.setParameter("numeroActa", numeroActa);
			query.setParameter("fechaIni", fechaIni);
			query.setParameter("fechaFin", fechaFin);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Accidents> accidentesByActa(Long numeroActa) {

		try {
			final String queryString = "select model from Accidents model where "
					+ "model.acts.actCodigo= :numeroActa";
			Query query = EntityManagerHelper.getEntityManager().createQuery(
					queryString);
			query.setParameter("numeroActa", numeroActa);

			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Driver> filtroConductores(String cedula, String nombre) {

		try {
			final String queryString = "select model from Driver model where "
					+ "model.drvCedula LIKE :cedula OR model.drvNombre LIKE "
					+ "'%" + nombre + "%'";
			Query query = EntityManagerHelper.getEntityManager().createQuery(
					queryString);
			query.setParameter("cedula", cedula);

			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Accidents> consultarAccidente(Long idAccidente) {

		try {
			final String queryString = "select model from Accidents model where "
					+ "model.accCodigo= :idAccidente";
			Query query = EntityManagerHelper.getEntityManager().createQuery(
					queryString);
			query.setParameter("idAccidente", idAccidente);

			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Assistants> cosultarAssistenteActa(Long idActa) {

		try {
			final String queryString = "select model from Assistants model where "
					+ "model.acts.actCodigo= :idActa";
			Query query = EntityManagerHelper.getEntityManager().createQuery(
					queryString);
			query.setParameter("idActa", idActa);

			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}
