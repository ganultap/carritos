package geniar.siscar.logic.consultas;

import geniar.siscar.model.Inconsistencies;
import geniar.siscar.model.Locations;
import geniar.siscar.model.LocationsTypes;
import geniar.siscar.model.PoliciesVehicles;
import geniar.siscar.model.Vehicles;
import geniar.siscar.persistence.EntityManagerHelper;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class SearchPoliciesVehicles {

	private static EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	@SuppressWarnings("unchecked")
	public List<PoliciesVehicles> consultarPoliciesVehiclesNoFact(Long numPoliza)
			throws GWorkException {
		try {
			String queryString = "";

			queryString = "select model from PoliciesVehicles model"
					+ " where model.policies.plsNumero=" + numPoliza
					+ " AND model.pvsEstado=3";

			Query query = getEntityManager().createQuery(queryString);

			List<PoliciesVehicles> list = query.getResultList();
			if (list != null && list.size() > 0) {
				return list;
			} else
				return null;
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new GWorkException(Util
					.loadErrorMessageValue("search.not.found"));
		}
	}

	@SuppressWarnings("unchecked")
	public static boolean consultarPoliciesVehiclesFact(Long numPoliza,
			String placa) throws GWorkException {
		try {
			String queryString = "";

			queryString = "select model from PoliciesVehicles model"
					+ " where model.policies.plsNumero= :numPoliza"
					+ " AND model.vehicles.vhcPlacaDiplomatica = :placa";

			Query query = EntityManagerHelper.getEntityManager().createQuery(
					queryString);

			query.setParameter("numPoliza", numPoliza);
			query.setParameter("placa", placa);

			List<PoliciesVehicles> list = query.getResultList();
			if (list != null && list.size() > 0) {
				return true;
			} else
				return false;
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new GWorkException(Util
					.loadErrorMessageValue("search.not.found"));
		}
	}

	@SuppressWarnings("unchecked")
	public List<PoliciesVehicles> consultarPoliciesVehiclesNoFactSAOT(
			Long idFactura) throws GWorkException {
		try {
			String queryString = "";

			queryString = "select model from PoliciesVehicles model"
					+ " where model.policiesInvoice.pinId=" + idFactura
					+ " AND model.pvsEstado=3";

			Query query = getEntityManager().createQuery(queryString);

			List<PoliciesVehicles> list = query.getResultList();
			if (list != null && list.size() > 0) {
				return list;
			} else
				return null;
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new GWorkException(Util
					.loadErrorMessageValue("search.not.found"));
		}
	}

	@SuppressWarnings("unchecked")
	public PoliciesVehicles consultarPoliciesVehiclesActiva(String placa)
			throws GWorkException {
		try {
			String queryString = "";

			queryString = "select model from PoliciesVehicles model"
					+ " where model.vehicles.vhcPlacaDiplomatica='" + placa
					+ "' AND model.pvsEstado=1";// 1 Indica que esta activa

			Query query = getEntityManager().createQuery(queryString);

			List<PoliciesVehicles> list = query.getResultList();
			if (list != null && list.size() > 0) {
				return list.get(0);
			} else
				return null;

		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new GWorkException(Util
					.loadErrorMessageValue("search.not.found"));
		}
	}

	@SuppressWarnings("unchecked")
	public List<PoliciesVehicles> consultarPVs(String placa)
			throws GWorkException {
		try {
			String queryString = "";

			queryString = "select model from PoliciesVehicles model"
					+ " where model.vehicles.vhcPlacaDiplomatica='" + placa
					+ "' AND model.pvsEstado=1";// 1 Indica que esta activa

			Query query = getEntityManager().createQuery(queryString);

			List<PoliciesVehicles> list = query.getResultList();
			if (list != null) {
				return list;
			} else
				return null;

		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new GWorkException(Util
					.loadErrorMessageValue("search.not.found"));
		}
	}

	@SuppressWarnings("unchecked")
	public PoliciesVehicles consultarPolizaVehiculo(String placaVehiculo,
			Long numPoliza) throws GWorkException {
		try {
			String queryString = "";

			queryString = "select model from PoliciesVehicles model"
					+ " where model.vehicles.vhcPlacaDiplomatica='"
					+ placaVehiculo + "' AND model.policies.plsNumero="
					+ numPoliza;

			Query query = getEntityManager().createQuery(queryString);

			List<PoliciesVehicles> list = query.getResultList();
			if (list != null && list.size() > 0) {
				return list.get(0);
			} else
				return null;

		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new GWorkException(Util
					.loadErrorMessageValue("search.not.found"));
		}
	}

	@SuppressWarnings("unchecked")
	public Float consultarSumaPorFactura(String strFactura)
			throws GWorkException {
		try {
			String queryString = "";

			queryString = "select SUM(model.pvsValorTotal) from PoliciesVehicles model"
					+ " where model.pvsNumeroFactura='" + strFactura + "'";

			Query query = getEntityManager().createQuery(queryString);

			List<Double> list = query.getResultList();
			if (list != null && list.size() > 0) {
				return list.get(0).floatValue();
			} else
				return null;

		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new GWorkException(Util
					.loadErrorMessageValue("search.not.found"));
		}
	}

	@SuppressWarnings("unchecked")
	public Inconsistencies consultarInconsistenciaPlacaPoliza(String Placa,
			Long PinId) throws GWorkException {
		try {
			String queryString = "";

			queryString = "SELECT model FROM Inconsistencies model "
					+ "WHERE model.vhcPlaca=:placa "
					+ "AND model.policiesInvoice.pinId=:pinId "
					+ "AND model.inconsistenciesTypes.ictId BETWEEN 2 AND 7 "
					+ "OR model.inconsistenciesTypes.ictId BETWEEN 12 AND 13 ";

			Query query = getEntityManager().createQuery(queryString);

			query.setParameter("placa", Placa);
			query.setParameter("pinId", PinId);

			List<Inconsistencies> list = query.getResultList();
			if (list != null && list.size() > 0) {
				return list.get(0);
			} else
				return null;

		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new GWorkException(Util
					.loadErrorMessageValue("search.not.found"));
		}
	}

	@SuppressWarnings("unchecked")
	public List<PoliciesVehicles> consultarPoliciesVehiclesPoliza(Long numPoliza)
			throws GWorkException {
		try {
			String queryString = "";

			queryString = "SELECT model FROM PoliciesVehicles model "
					+ "WHERE model.policies.plsCodigo =:numPoliza "
					+ "ORDER BY model.vehicles.vhcPlacaDiplomatica ASC";

			Query query = getEntityManager().createQuery(queryString);

			query.setParameter("numPoliza", numPoliza);

			List<PoliciesVehicles> list = query.getResultList();
			if (list != null && list.size() > 0) {
				
				for (PoliciesVehicles pv : list){
					pv.getVehicles().getVhcPlacaDiplomatica();
					pv.getPolicies().getPlsCodigo();
				}
				
				return list;
			} else
				return null;
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new GWorkException(Util
					.loadErrorMessageValue("search.not.found"));
		}
	}

	@SuppressWarnings("unchecked")
	public Inconsistencies consultarInconsistenciaPlacaSoat(String Placa)
			throws GWorkException {
		try {
			String queryString = "";

			queryString = "SELECT model FROM Inconsistencies model "
					+ "WHERE model.vhcPlaca=:placa "
					+ "AND model.inconsistenciesTypes.ictId = 7 ";

			Query query = getEntityManager().createQuery(queryString);

			query.setParameter("placa", Placa);

			List<Inconsistencies> list = query.getResultList();
			if (list != null && list.size() > 0) {
				return list.get(0);
			} else
				return null;

		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new GWorkException(Util
					.loadErrorMessageValue("search.not.found"));
		}
	}

	@SuppressWarnings("unchecked")
	public List<PoliciesVehicles> consultarPoliciesVehiclesFactura(
			String numFactura) throws GWorkException {
		try {
			String queryString = "";

			queryString = "SELECT model FROM PoliciesVehicles model "
					+ "WHERE model.pvsNumeroFactura =:numFactura "
					+ "AND model.pvsEstado = 1 "
					+ "ORDER BY model.vehicles.vhcPlacaDiplomatica ASC";

			Query query = getEntityManager().createQuery(queryString);

			query.setParameter("numFactura", numFactura);

			List<PoliciesVehicles> list = query.getResultList();
			if (list != null && list.size() > 0) {
				return list;
			} else
				return null;
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new GWorkException(Util
					.loadErrorMessageValue("search.not.found"));
		}
	}

	@SuppressWarnings("unchecked")
	public List<PoliciesVehicles> consultarPoliciesVehiclesVhc(String placa)
			throws GWorkException {
		try {
			String queryString = "";

			queryString = "select model from PoliciesVehicles model"
					+ " where model.vehicles.vhcPlacaDiplomatica='" + placa
					+ "'";// 1 Indica que esta activa

			Query query = getEntityManager().createQuery(queryString);

			List<PoliciesVehicles> list = query.getResultList();
			if (list != null) {
				//Se hidrata el objeto 
				for (PoliciesVehicles policiesVehicles : list) {
					Vehicles v = policiesVehicles.getVehicles();
					if (v!=null){
						Locations l = v.getLocations();
						if (l != null){
							LocationsTypes lt = l.getLocationsTypes();
							if (lt != null){
								Long lctCodigo = lt.getLctCodigo();
							}
						}
					}
				}
				return list;
			} else
				return null;

		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new GWorkException(Util
					.loadErrorMessageValue("search.not.found"));
		}
	}
}