package geniar.siscar.logic.consultas;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import geniar.siscar.model.Requests;
import geniar.siscar.model.VehiclesAssignation;
import geniar.siscar.persistence.EntityManagerHelper;
import geniar.siscar.persistence.RequestsDAO;
import geniar.siscar.util.ParametersUtil;

public class RequestConsultDAO extends RequestsDAO {

	private static EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	@SuppressWarnings("unchecked")
	public List<Requests> findByPropertyStates() {
		EntityManagerHelper
				.log(
						"finding Requests instance with property state Send or Reserve:",
						Level.INFO, null);
		try {
			final String queryString = "select model from Requests model where model.requestsStates.rqtCodigo = 2 or model.requestsStates.rqtCodigo =3 "
					+ "or model.requestsStates.rqtCodigo = 4 or model.requestsStates.rqtCodigo = 8"
					+ "ORDER BY model.rqsFechaInicial ASC";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find by property name failed",
					Level.SEVERE, re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Requests> findByPropertyFilter(Long claseSolicitud,
			Long estadoSolicitud, Date fechaDesde, Date fechaHasta) {
		EntityManagerHelper.log("finding Requests instance with filter:",
				Level.INFO, null);
		try {
			final String queryString = "select model from Requests model where model.requestsStates.rqtCodigo =:estado "
					+ "and model.requestsClasses.rqcCodigo=:clase and model.rqsFechaInicial>= :fechaDesde and model.rqsFechaFinal<= :fechaHasta";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("estado", estadoSolicitud);
			query.setParameter("clase", claseSolicitud);
			query.setParameter("fechaInicial", fechaDesde);
			query.setParameter("fechaFinal", fechaHasta);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find by property name failed",
					Level.SEVERE, re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Requests> findByPropertyStatesCreate(String carne) {
		EntityManagerHelper
				.log(
						"finding Requests instance with property state Send or Reserve:",
						Level.INFO, null);
		try {
			final String queryString = "select model from Requests model where  model.rqsCarneLogin =:carne AND "
					+ "(model.requestsStates.rqtCodigo = :creada or model.requestsStates.rqtCodigo = :enviada or "
					+ "model.requestsStates.rqtCodigo = :devuelta or model.requestsStates.rqtCodigo = :reservado"
					+ " or model.requestsStates.rqtCodigo = :alquilado "
					+ "or model.requestsStates.rqtCodigo = :asignado) "
					+ "ORDER BY model.rqsFechaInicial DESC";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("creada", ParametersUtil.RQS_CREADA);
			query.setParameter("enviada", ParametersUtil.RQS_ENVIADA);
			query.setParameter("reservado", ParametersUtil.RQS_RESERVADO);
			query.setParameter("alquilado", ParametersUtil.RQS_ALQUILADO);
			query.setParameter("asignado", ParametersUtil.RQS_ASIGNADO);
			query.setParameter("devuelta", ParametersUtil.RQS_DEVUELTA);
			query.setParameter("carne", carne);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find by property name failed",
					Level.SEVERE, re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Requests> findByPropertyStatesCumplidas(String carne) {
		EntityManagerHelper
				.log(
						"finding Requests instance with property state Send or Reserve:",
						Level.INFO, null);
		try {
			final String queryString = "select model from Requests model where  model.rqsCarneLogin =:carne AND "
					+ "model.requestsStates.rqtCodigo = :cumplida "
					+ "ORDER BY model.rqsFechaInicial DESC";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("cumplida", ParametersUtil.RQS_CUMPLIDA);
			query.setParameter("carne", carne);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find by property name failed",
					Level.SEVERE, re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Requests> findByUserStateCreate(String carneLogin,
			String carneFiltro) {

		EntityManagerHelper
				.log(
						"finding Requests instance with property state envida, creadas, reservadas, alquildas, devueltas, asignadas:",
						Level.INFO, null);

		try {
			final String queryString = "select model from Requests model where  model.rqsCarneLogin =:carneLogin AND "
					+ " (model.rqsCarnetAsignatario= :carneFiltro OR model.rqsCarnetEmpleado= :carneFiltro) AND"
					+ "(model.requestsStates.rqtCodigo = :creada or model.requestsStates.rqtCodigo = :enviada or "
					+ "model.requestsStates.rqtCodigo = :devuelta or model.requestsStates.rqtCodigo = :reservado"
					+ " or model.requestsStates.rqtCodigo = :alquilado "
					+ "or model.requestsStates.rqtCodigo = :asignado "
					+ "OR model.requestsStates.rqtCodigo = :cumplida)"
					+ "ORDER BY model.rqsFechaInicial DESC";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("creada", ParametersUtil.RQS_CREADA);
			query.setParameter("enviada", ParametersUtil.RQS_ENVIADA);
			query.setParameter("reservado", ParametersUtil.RQS_RESERVADO);
			query.setParameter("alquilado", ParametersUtil.RQS_ALQUILADO);
			query.setParameter("asignado", ParametersUtil.RQS_ASIGNADO);
			query.setParameter("devuelta", ParametersUtil.RQS_DEVUELTA);
			query.setParameter("cumplida", ParametersUtil.RQS_CUMPLIDA);
			query.setParameter("carneLogin", carneLogin);
			query.setParameter("carneFiltro", carneFiltro);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find by property name failed",
					Level.SEVERE, re);
			throw re;
		}

	}

	@SuppressWarnings("unchecked")
	public List<Requests> findByDateStatesCreate(Date fechaDesde,
			Date fechaHasta) {
		EntityManagerHelper
				.log(
						"finding Requests instance with property state Send or Reserve:",
						Level.INFO, null);
		try {
			final String queryString = "select model from Requests model where model.rqsFechaInicial>=:fechaInicial and model.rqsFechaFinal<= :fechaFinal "
					+ "and (model.requestsStates.rqtCodigo = 1 or model.requestsStates.rqtCodigo = 2 or model.requestsStates.rqtCodigo = 6 or "
					+ "model.requestsStates.rqtCodigo = 3 or model.requestsStates.rqtCodigo = 4 or model.requestsStates.rqtCodigo = 8) "
					+ "ORDER BY model.rqsFechaInicial ASC";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("fechaInicial", fechaDesde);
			query.setParameter("fechaFinal", fechaHasta);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find by property name failed",
					Level.SEVERE, re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Requests> consultarSolicitudByFechas(Date fechaDesde,
			Date fechaHasta) {
		EntityManagerHelper
				.log(
						"finding Requests instance with property state Send or Reserve:",
						Level.INFO, null);
		try {
			final String queryString = "select model from Requests model where model.rqsFechaInicial BETWEEN :fechaInicial and :fechaFinal "
					+ "and (model.requestsStates.rqtCodigo = 2 or "
					+ "model.requestsStates.rqtCodigo = 3 or model.requestsStates.rqtCodigo = 4 or model.requestsStates.rqtCodigo = 8) "
					+ "ORDER BY model.rqsFechaInicial ASC";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("fechaInicial", fechaDesde);
			query.setParameter("fechaFinal", fechaHasta);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find by property name failed",
					Level.SEVERE, re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Requests> consultarSolicitudByFechasCarne(String carne,
			Date fechaDesde, Date fechaHasta) {
		EntityManagerHelper
				.log(
						"finding Requests instance with property state Send or Reserve:",
						Level.INFO, null);
		try {
			final String queryString = "select model from Requests model where model.usersByRqsUser.usrIdentificacion= :carne and model.rqsFechaInicial "
					+ "BETWEEN :fechaInicial and :fechaFinal "
					+ "and (model.requestsStates.rqtCodigo = 2 or "
					+ "model.requestsStates.rqtCodigo = 3 or model.requestsStates.rqtCodigo = 4 "
					+ "or model.requestsStates.rqtCodigo = 8) "
					+ "ORDER BY model.rqsFechaInicial ASC";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("carne", carne);
			query.setParameter("fechaInicial", fechaDesde);
			query.setParameter("fechaFinal", fechaHasta);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find by property name failed",
					Level.SEVERE, re);
			throw re;
		}
	}

	public static void main(String[] args) {
		RequestConsultDAO consultDAO = new RequestConsultDAO();

		for (VehiclesAssignation vehiclesAssignation : consultDAO
				.assignationByPlaca("OI009")) {
			System.out.println(vehiclesAssignation.getVhaNumeroSolicitud());

		}
	}

	@SuppressWarnings("unchecked")
	public List<Requests> findByUserRequest(String carne) {

		EntityManagerHelper
				.log(
						"finding Requests instance with property state envida, creadas, reservadas, alquildas, devueltas, asignadas:",
						Level.INFO, null);

		try {
			final String queryString = "select model from Requests model where model.rqsCarnetAsignatario= :carne "
					+ "and (model.requestsStates.rqtCodigo = 1  or model.requestsStates.rqtCodigo = 2 or model.requestsStates.rqtCodigo = 6 or"
					+ " model.requestsStates.rqtCodigo = 3 or model.requestsStates.rqtCodigo = 4 or model.requestsStates.rqtCodigo = 8 )"
					+ "ORDER BY model.rqsFechaInicial ASC";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("carne", carne);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find by property name failed",
					Level.SEVERE, re);
			throw re;
		}

	}

	@SuppressWarnings("unchecked")
	public List<VehiclesAssignation> assignationByPlaca(String placa) {

		try {
			final String queryString = "select model from VehiclesAssignation model where model.vehicles.vhcPlacaDiplomatica= :placa "
					+ "and(model.vehicles.vehiclesStates.vhsCodigo=1 or model.vehicles.vehiclesStates.vhsCodigo=7) "
					+ "and model.vhaFechaEntrega is not null  and model.vhaFechaDev is null ";

			Query query = EntityManagerHelper.getEntityManager().createQuery(
					queryString);
			query.setParameter("placa", placa);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find by property name failed",
					Level.SEVERE, re);
			throw re;
		}

	}

	@SuppressWarnings("unchecked")
	public List<Requests> consultarSolicitudByFechasCarneUser(String carne,
			String carneLogin, Date fechaDesde, Date fechaHasta) {
		EntityManagerHelper
				.log(
						"finding Requests instance with property state Send or Reserve:",
						Level.INFO, null);
		try {
			final String queryString = "select model from Requests model where model.rqsCarneLogin= :carneLogin "
					+ "AND (model.rqsCarnetAsignatario = :carne or model.rqsCarnetEmpleado = :carne)"
					+ "and model.rqsFechaInicial BETWEEN :fechaInicial and :fechaFinal "
					+ "and (model.requestsStates.rqtCodigo = 1 or model.requestsStates.rqtCodigo = 2 or "
					+ "model.requestsStates.rqtCodigo = 3 or model.requestsStates.rqtCodigo = 4 or model.requestsStates.rqtCodigo = 6"
					+ "or model.requestsStates.rqtCodigo = 8 "
					+ "or model.requestsStates.rqtCodigo = 5) "
					+ "ORDER BY model.rqsFechaInicial ASC";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("carne", carne);
			query.setParameter("fechaInicial", fechaDesde);
			query.setParameter("fechaFinal", fechaHasta);
			query.setParameter("carneLogin", carneLogin);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find by property name failed",
					Level.SEVERE, re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Requests> consultarSolicitudByFechasUser(Date fechaDesde,
			Date fechaHasta, String carneLogin) {
		EntityManagerHelper
				.log(
						"finding Requests instance with property state Send or Reserve:",
						Level.INFO, null);
		try {
			final String queryString = "select model from Requests model where  model.rqsFechaInicial "
					+ "BETWEEN :fechaInicial and :fechaFinal "
					+ "and (model.requestsStates.rqtCodigo = 1 or model.requestsStates.rqtCodigo = 2 or "
					+ "model.requestsStates.rqtCodigo = 3 or model.requestsStates.rqtCodigo = 4 or model.requestsStates.rqtCodigo = 6"
					+ "or model.requestsStates.rqtCodigo = 8 "
					+ "OR model.requestsStates.rqtCodigo = 5) "
					+ "AND model.rqsCarneLogin= :carneLogin "
					+ "ORDER BY model.rqsFechaInicial DESC";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("fechaInicial", fechaDesde);
			query.setParameter("fechaFinal", fechaHasta);
			query.setParameter("carneLogin", carneLogin);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find by property name failed",
					Level.SEVERE, re);
			throw re;
		}
	}

	public Requests consultarRequest(Long idRequest) {
		EntityManagerHelper.log("finding Requests: ", Level.INFO, null);
		try {
			final String queryString = "select model from Requests model where  model.rqsCodigo = :idRequest ";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("idRequest", idRequest);

			if (query.getResultList() != null
					&& query.getResultList().size() > 0
					&& !query.getResultList().isEmpty())
				return (Requests) query.getSingleResult();
			else
				return null;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find by property name failed",
					Level.SEVERE, re);
			throw re;
		}
	}

	public VehiclesAssignation findRequestByVHA(Long idRequest) {
		EntityManagerHelper.log("finding Requests: ", Level.INFO, null);
		try {
			final String queryString = "select model from VehiclesAssignation model where  model.requests.rqsCodigo = :idRequest ";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("idRequest", idRequest);

			if (query.getResultList() != null
					&& query.getResultList().size() > 0
					&& !query.getResultList().isEmpty())
				return (VehiclesAssignation) query.getSingleResult();
			else
				return null;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find by property name failed",
					Level.SEVERE, re);
			throw re;
		}
	}

}
