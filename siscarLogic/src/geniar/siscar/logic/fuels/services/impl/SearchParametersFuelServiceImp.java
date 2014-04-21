package geniar.siscar.logic.fuels.services.impl;

import geniar.siscar.logic.fuels.services.SearchParametersFuelService;
import geniar.siscar.model.DailyMovementTank;
import geniar.siscar.model.DialyMovementPumps;
import geniar.siscar.model.Prepaid;
import geniar.siscar.model.PrepaidConsumption;
import geniar.siscar.model.RevisionHour;
import geniar.siscar.model.ServiceRegistry;
import geniar.siscar.persistence.EntityManagerHelper;
import geniar.siscar.persistence.RevisionHourDAO;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;

import javax.persistence.Query;

public class SearchParametersFuelServiceImp implements
		SearchParametersFuelService {

	public List<RevisionHour> listaHoraRevision() throws GWorkException {
		List<RevisionHour> listaHoraRevision = new RevisionHourDAO().findAll();

		if (listaHoraRevision == null || listaHoraRevision.size() == 0
				|| listaHoraRevision.isEmpty())
			throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));

		return listaHoraRevision;
	}

	public String entradasTanque(Long idTanque, Date fechaCarga) {

		try {
			Object entradas = null;
			final String queryString = "select sum(model.cotGalonesActuales) from ControlsTanks model WHERE "
					+ "model.fuelTanks.ftaCodigo= :idTanque AND model.cotFechaCarga = :fechaCarga";
			Query query = EntityManagerHelper.getEntityManager().createQuery(
					queryString);
			query.setParameter("idTanque", idTanque);
			query.setParameter("fechaCarga", fechaCarga);
			entradas = query.getSingleResult();
			if (entradas != null)
				return entradas.toString();
			return "";
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<DailyMovementTank> listaPlanillaTanque(Long idTanque,
			Date fechaIni, Date fechaFin) {
		EntityManagerHelper.log("finding all Severity instances", Level.INFO,
				null);
		try {
			final String queryString = "select model from DailyMovementTank model "
					+ "where model.fuelTanks.ftaCodigo= :idTanque and model.damFecha BETWEEN :fechaIni and :fechaFin ORDER BY model.damFecha ASC";
			Query query = EntityManagerHelper.getEntityManager().createQuery(
					queryString);
			query.setParameter("idTanque", idTanque);
			query.setParameter("fechaIni", fechaIni);
			query.setParameter("fechaFin", fechaFin);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<DailyMovementTank> registroPlanillaTanque(Long idTanque,
			Long horaRevision, Date fechaRevision) {

		try {
			List<DailyMovementTank> registroDiario = null;
			final String queryString = "select model from DailyMovementTank model "
					+ "where model.fuelTanks.ftaCodigo= :idTanque "
					+ "and model.revisionHour.rhoCodigo= :horaRevision "
					+ "and model.damFecha= :fechaRevision";
			Query query = EntityManagerHelper.getEntityManager().createQuery(
					queryString);
			query.setParameter("idTanque", idTanque);
			query.setParameter("horaRevision", horaRevision);
			query.setParameter("fechaRevision", fechaRevision);

			if (query.getResultList() != null
					&& query.getResultList().size() > 0)
				registroDiario = query.getResultList();

			return registroDiario;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<DialyMovementPumps> registroPlanillaSurtidor(Long idSurtidor,
			Long horaRevision, Date fechaRevision) {

		try {
			List<DialyMovementPumps> registroDiario = null;
			final String queryString = "select model from DialyMovementPumps model "
					+ "where model.pumps.pumCodigo= :idSurtidor "
					+ "and model.revisionHour.rhoCodigo= :horaRevision "
					+ "and model.dmpFecha= :fechaRevision";
			Query query = EntityManagerHelper.getEntityManager().createQuery(
					queryString);
			query.setParameter("idSurtidor", idSurtidor);
			query.setParameter("horaRevision", horaRevision);
			query.setParameter("fechaRevision", fechaRevision);

			if (query.getResultList() != null
					&& query.getResultList().size() > 0)
				registroDiario = query.getResultList();

			return registroDiario;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<DialyMovementPumps> listaPlanillaSurtidor(Long idSurtidor,
			Date fechaIni, Date fechaFin) {
		try {
			final String queryString = "select model from DialyMovementPumps model "
					+ "where model.pumps.pumCodigo= :idSurtidor and model.dmpFecha BETWEEN :fechaIni and :fechaFin ORDER BY model.dmpFecha ASC";
			Query query = EntityManagerHelper.getEntityManager().createQuery(
					queryString);
			query.setParameter("idSurtidor", idSurtidor);
			query.setParameter("fechaIni", fechaIni);
			query.setParameter("fechaFin", fechaFin);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<ServiceRegistry> listaPromedioCombustible(Date fechaIni,
			Date fechaFin) {

		try {
			final String queryString = "select model from ServiceRegistry model "
					+ "WHERE model.serFecha BETWEEN :fechaIni AND :fechaFin";
			Query query = EntityManagerHelper.getEntityManager().createQuery(
					queryString);
			query.setParameter("fechaIni", fechaIni);
			query.setParameter("fechaFin", fechaFin);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> promedioPorTipoVehiculo(Date fechaIni, Date fechaFin) {

		try {
			final String queryString = "select model.vehicles.lines.brands.brnNombre AS nomMarc,model.vehicles.lines.lnsNombre,"
					+ "model.vehicles.fuelsTypes.futNombre,model.vehicles.tractionsTypes.tctNombre, avg((model.serKilometrajeActual-model.serKilometrajeAnterior)/model.serNumeroGalones) AS  promedio "
					+ "from ServiceRegistry model WHERE model.serFecha BETWEEN :fechaIni AND :fechaFin "
					+ "GROUP BY model.vehicles.lines.brands.brnNombre, model.vehicles.lines.lnsNombre,"
					+ "model.vehicles.fuelsTypes.futNombre, model.vehicles.tractionsTypes.tctNombre";

			Query query = EntityManagerHelper.getEntityManager().createQuery(
					queryString);
			query.setParameter("fechaIni", fechaIni);
			query.setParameter("fechaFin", fechaFin);

			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> detalleConsumoVHC(Date fechaIni, Date fechaFin,
			String linea, String combustible, String traccion) {

		try {
			final String queryString = "select  model.vehicles.lines.brands.brnNombre, model.vehicles.lines.lnsNombre,"
					+ "model.vehicles.fuelsTypes.futNombre,model.vehicles.tractionsTypes.tctNombre,"
					+ "model.vehicles.vhcPlacaDiplomatica, avg((model.serKilometrajeActual-model.serKilometrajeAnterior)/model.serNumeroGalones) AS  promedio "
					+ "from ServiceRegistry model WHERE model.serFecha BETWEEN :fechaIni AND :fechaFin AND "
					+ "model.vehicles.lines.lnsNombre= :linea AND model.vehicles.fuelsTypes.futNombre = :combustible "
					+ "AND model.vehicles.tractionsTypes.tctNombre= :traccion "
					+ "GROUP BY model.vehicles.lines.brands.brnNombre, model.vehicles.lines.lnsNombre,"
					+ "model.vehicles.fuelsTypes.futNombre,model.vehicles.tractionsTypes.tctNombre,"
					+ "model.vehicles.vhcPlacaDiplomatica";
			Query query = EntityManagerHelper.getEntityManager().createQuery(
					queryString);
			query.setParameter("fechaIni", fechaIni);
			query.setParameter("fechaFin", fechaFin);
			query.setParameter("linea", linea);
			query.setParameter("combustible", combustible);
			query.setParameter("traccion", traccion);

			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<ServiceRegistry> historialConsumoVehiculo(Date fechaIni,
			Date fechaFin, String placa) {

		try {
			final String queryString = "select  model "
					+ "from ServiceRegistry model WHERE model.serFecha BETWEEN :fechaIni AND :fechaFin AND "
					+ "model.vehicles.vhcPlacaDiplomatica= :placa ORDER BY model.serFecha ASC";
			;
			Query query = EntityManagerHelper.getEntityManager().createQuery(
					queryString);
			query.setParameter("fechaIni", fechaIni);
			query.setParameter("fechaFin", fechaFin);
			query.setParameter("placa", placa);

			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

	public String totalGalones(Date fechaIni, Date fechaFin, String placa) {

		try {
			final String queryString = "select  sum(model.serNumeroGalones) "
					+ "from ServiceRegistry model WHERE model.serFecha BETWEEN :fechaIni AND :fechaFin AND "
					+ "model.vehicles.vhcPlacaDiplomatica= :placa";
			;
			Query query = EntityManagerHelper.getEntityManager().createQuery(
					queryString);
			query.setParameter("fechaIni", fechaIni);
			query.setParameter("fechaFin", fechaFin);
			query.setParameter("placa", placa);

			return query.getSingleResult().toString();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Double totalKMCombustible(Date fechaIni, Date fechaFin, String placa) {

		try {
			final String queryString = "select  sum(model.serTotal) "
					+ "from ServiceRegistry model WHERE model.serFecha BETWEEN :fechaIni AND :fechaFin AND "
					+ "model.vehicles.vhcPlacaDiplomatica= :placa";
			;
			Query query = EntityManagerHelper.getEntityManager().createQuery(
					queryString);
			query.setParameter("fechaIni", fechaIni);
			query.setParameter("fechaFin", fechaFin);
			query.setParameter("placa", placa);

			return (Double) query.getSingleResult();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Prepaid> consultarDisponibilidaPrepago(Date fechaIni,
			Date fechaFin, String placa) {

		try {
			final String queryString = "select  model "
					+ "from Prepaid model WHERE model.preFechaini BETWEEN :fechaIni AND :fechaFin "
					+ "AND model.costCentersFuel.vehiclesAssignation.vehicles.vhcPlacaDiplomatica= :placa";
			;
			Query query = EntityManagerHelper.getEntityManager().createQuery(
					queryString);
			query.setParameter("fechaIni", fechaIni);
			query.setParameter("fechaFin", fechaFin);
			query.setParameter("placa", placa);
			List<Prepaid> listaPrepago = query.getResultList();
			return listaPrepago;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> valorDisponiblePrepago(Date fechaIni, Date fechaFin,
			Long idPrepago, String centroCosto) {

		try {
			final String queryString = "select  model.costCentersFuel.costsCenters.cocNumero,model.costCentersFuel.ccfValor as compra, "
					+ "sum(model.prcValorConsumo) as consumo,(model.costCentersFuel.ccfValor-sum(model.prcValorConsumo)) as disponible "
					+ "from PrepaidConsumption model "
					+ "WHERE model.costCentersFuel.ccfFechaInicio BETWEEN :fechaIni AND :fechaFin "
					+ "AND model.costCentersFuel.prepaid.preCodigo= :idPrepago "
					+ "AND model.costCentersFuel.costsCenters.cocNumero= :centroCosto "
					+ "GROUP BY model.costCentersFuel.costsCenters.cocNumero, model.costCentersFuel.ccfValor";
			;
			Query query = EntityManagerHelper.getEntityManager().createQuery(
					queryString);
			query.setParameter("fechaIni", fechaIni);
			query.setParameter("fechaFin", fechaFin);
			query.setParameter("idPrepago", idPrepago);
			query.setParameter("centroCosto", centroCosto);

			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Prepaid> DisponibilidadPrepagoCCAsignatario(Date fechaIni,
			Date fechaFin, String centroCosto, String asignatario) {

		try {
			final String queryString = "select  model "
					+ "from Prepaid model WHERE model.preFechaini BETWEEN :fechaIni AND :fechaFin "
					+ "AND (model.costCentersFuel.costsCenters.cocNumero= :centroCosto "
					+ "OR model.preAsignatario= :asignatario)";
			;
			Query query = EntityManagerHelper.getEntityManager().createQuery(
					queryString);
			query.setParameter("fechaIni", fechaIni);
			query.setParameter("fechaFin", fechaFin);
			query.setParameter("centroCosto", centroCosto);
			query.setParameter("asignatario", asignatario);

			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<PrepaidConsumption> detalleConsumo(Long idCentroCosto,
			Date fechaIni, Date fechaFin) {
		try {
			final String queryString = "select  model "
					+ "from PrepaidConsumption model WHERE model.costCentersFuel.costsCenters.cocCodigo =:idCentroCosto "
					+ "AND model.prcFecha BETWEEN :fechaIni AND :fechaFin "
					+ "ORDER BY model.prcFecha DESC";

			Query query = EntityManagerHelper.getEntityManager().createQuery(
					queryString);
			query.setParameter("idCentroCosto", idCentroCosto);
			query.setParameter("fechaIni", fechaIni);
			query.setParameter("fechaFin", fechaFin);

			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public static List<PrepaidConsumption> consumoPrepagoByVehiculo(
			String idCentroCosto, String placa, Date fechaIni, Date fechaFin) {
		try {
			final String queryString = "select  model "
					+ "from PrepaidConsumption model WHERE model.costCentersFuel.costsCenters.cocNumero =:idCentroCosto "
					+ "AND model.serviceRegistry.vehicles.vhcPlacaDiplomatica = :placa "
					+ "AND model.prcFecha BETWEEN :fechaIni AND :fechaFin "
					+ "ORDER BY model.prcFecha DESC";

			Query query = EntityManagerHelper.getEntityManager().createQuery(
					queryString);
			query.setParameter("idCentroCosto", idCentroCosto);
			query.setParameter("placa", placa);
			query.setParameter("fechaIni", fechaIni);
			query.setParameter("fechaFin", fechaFin);

			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

	public String disponibleCC(String idCentroCosto) {
		try {
			final String queryString = "select model.costCentersFuel.costsCenters.valorPrepago"
					+ " from PrepaidConsumption model WHERE model.costCentersFuel.costsCenters.cocNumero = :idCentroCosto ";

			Query query = EntityManagerHelper.getEntityManager().createQuery(
					queryString);
			query.setParameter("idCentroCosto", idCentroCosto);

			if (query.getResultList() != null
					&& query.getResultList().get(0) != null)
				return query.getSingleResult().toString();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> consultarSurtidores() {
		try {
			// select p.pumCodigo, p.pumNombre, ft.futCodigo, ft.futNombre,
			// t.trfValor
			// from Pumps p
			// join p.fuelTanks fk
			// join fk.fuelsTypes ft
			// join (relacion con tafira) t
			// where
			// and t.trfId IN (6,7)
			// and t.trfFechaFin is null

			StringBuffer buffer = new StringBuffer();
			buffer
					.append(" select p.pumCodigo, p.pumNombre, ft.futCodigo, ft.futNombre, t.trfValor");

			buffer
					.append(" from Pumps p, FuelTanks fk, FuelsTypes ft, Tariffs t");

			buffer.append(" where fk.ftaCodigo = p.fuelTanks.ftaCodigo");

			buffer.append(" and ft.futCodigo = fk.fuelsTypes.futCodigo");

			buffer.append(" and t.trfId IN (6,7)");

			buffer.append(" and t.trfFechaFin is null");

			buffer.append(" and t.fuelsTypes.futCodigo =  ft.futCodigo");

			Query query = EntityManagerHelper.getEntityManager().createQuery(
					buffer.toString());
			List<Object[]> list = query.getResultList();
			return list;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

	public static Float promedioConsumoCombustible(String placa) {

		try {
			final String queryString = "select   avg(model.serviceRegistry.serNumeroGalones)"
					+ "from PrepaidConsumption model WHERE model.serviceRegistry.vehicles.vhcPlacaDiplomatica= :placa ";
			Query query = EntityManagerHelper.getEntityManager().createQuery(
					queryString);
			query.setParameter("placa", placa);

			if (query.getResultList() != null
					&& query.getResultList().size() > 0)
				return (Float) query.getSingleResult();

			else
				return new Float(0);

		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

	public static void main(String[] args) {

		promedioConsumoCombustible("OI");
	}
}
