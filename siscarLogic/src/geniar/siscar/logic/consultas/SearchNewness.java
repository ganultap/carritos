package geniar.siscar.logic.consultas;

import geniar.siscar.model.CostCentersFuel;
import geniar.siscar.model.CostsCentersVehicles;
import geniar.siscar.model.Locations;
import geniar.siscar.model.LocationsNewness;
import geniar.siscar.model.Vehicles;
import geniar.siscar.model.VehiclesAssignation;
import geniar.siscar.persistence.EntityManagerHelper;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SearchNewness {

	private static final Log log = LogFactory.getLog(SearchVehicles.class);
	public static final String vhcCodigo = "vhcCodigo";
	public static final String vhaCodigo = "vhaCodigo";
	public static final String vhaFechaDev = "vhaFechaDev";
	public static final String vhaFechaEntrega = "vhaFechaEntrega";
	public static final String ccrFechaInicio = "ccrFechaInicio";

	private static EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	@SuppressWarnings("unchecked")
	public static VehiclesAssignation consultarDatosAsignacion(String placa)
			throws GWorkException {
		try {
			Long estadoRetirado = Long.valueOf(Util.loadMessageValue("ESTADO.5")); 
			Vehicles vehicles = SearchVehicles.consultarVehiculosPorPlacaYEstado(placa, estadoRetirado);

			Long idVehiculo = null;
			List<VehiclesAssignation> listVehiclesAssignation = null;
			VehiclesAssignation vehiclesAssignation = null;

			if (vehicles != null) {
				idVehiculo = vehicles.getVhcCodigo();
			}

			StringBuffer buffer = new StringBuffer();
			buffer
					.append("select model from VehiclesAssignation model where model.vehicles."
							+ vhcCodigo + " = :idVehiculo ");
			buffer.append(" and model." + vhaFechaDev + " is null and model."
					+ vhaFechaEntrega + " is not null");

			Query query = getEntityManager().createQuery(buffer.toString());
			query.setParameter("idVehiculo", idVehiculo);

			listVehiclesAssignation = query.getResultList();

			if (listVehiclesAssignation != null
					&& listVehiclesAssignation.size() > 0)
				vehiclesAssignation = listVehiclesAssignation.get(0);
			return vehiclesAssignation;

		} catch (RuntimeException re) {
			log.error("consultarDatosAsignacion", re);
		}
		return null;
	}

	public static List<CostsCentersVehicles> obtenerCentrosCostos(
			VehiclesAssignation assignation) {

		Set<CostsCentersVehicles> setAssignation = assignation
				.getCostsCentersVehicleses();
		List<CostsCentersVehicles> listaCostCenters = new ArrayList<CostsCentersVehicles>();
		for (CostsCentersVehicles costsCentersVehicles : setAssignation) {
			listaCostCenters.add(costsCentersVehicles);
		}
		return listaCostCenters;
	}

	/**
	 * Metodo para realizar la validacion de la fecha con los centros de costos
	 * actuales
	 * 
	 * @param codigo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static CostsCentersVehicles ValidarCentrosDeCostosActuales(
			Long idVehiculo, Long idAsignacion, Date fechaAsignacion)
			throws GWorkException {
		try {
			CostsCentersVehicles centersVehicles = null;
			List<CostsCentersVehicles> listCost = null;
			final String queryString = "select model from CostsCentersVehicles model where model.vehiclesAssignation."
					+ vhaCodigo
					+ "= :idAsignacion "
					+ " and model.vehicles."
					+ vhcCodigo
					+ " = :idVehiculo order by model."
					+ ccrFechaInicio + " DESC ";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("idAsignacion", idAsignacion);
			query.setParameter("idVehiculo", idVehiculo);

			listCost = query.getResultList();

			if (listCost != null && listCost.size() > 0) {
				centersVehicles = listCost.get(0);

				System.out.println("getCcrFechaInicio:"
						+ centersVehicles.getCcrFechaInicio()
						+ "/ fechaAsignacion:" + fechaAsignacion);
				if (centersVehicles.getCcrFechaInicio().compareTo(
						fechaAsignacion) > 0)
					return listCost.get(0);
			}

		} catch (RuntimeException re) {
			log.error("ValidarCentrosDeCostosActuales", re);
		}
		return null;
	}

	/**
	 * Metodo para realizar la consulta los centros de costos actuales
	 * 
	 * @param codigo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<CostsCentersVehicles> ConsultarCentrosDeCostosActuales(
			Long idVehiculo, Long idAsignacion) throws GWorkException {
		try {
			final String queryString = "select model from CostsCentersVehicles model where model.vehiclesAssignation."
					+ vhaCodigo
					+ "= :idAsignacion "
					+ " and model.ccrEstado = :estado"
					+ " and model.vehiclesAssignation.vehicles."
					+ vhcCodigo
					+ " = :idVehiculo";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("idAsignacion", idAsignacion);
			query.setParameter("idVehiculo", idVehiculo);
			query
					.setParameter("estado", Util
							.loadMessageValue("ESTADO.ACTIVO"));
			return (List<CostsCentersVehicles>) query.getResultList();
		} catch (RuntimeException re) {
			log.error("ConsultarSolicitudAsignacionAlquilerVehiculos", re);
		}
		return null;
	}

	/**
	 * Metodo para realizar la consulta los centros de costos actuales de
	 * combustible
	 * 
	 * @param codigo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<CostCentersFuel> ConsultarCentrosDeCostosActualesCombustible(
			Long idVehiculo, Long idAsignacion) throws GWorkException {
		try {
			final String queryString = "select model from CostCentersFuel model where model.vehiclesAssignation."
					+ vhaCodigo
					+ "= :idAsignacion "
					+ " and model.ccfEstado = :estado"
					+ " and model.vehiclesAssignation.vehicles."
					+ vhcCodigo
					+ " = :idVehiculo";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("idAsignacion", idAsignacion);
			query.setParameter("idVehiculo", idVehiculo);
			query
					.setParameter("estado", Util
							.loadMessageValue("ESTADO.ACTIVO"));
			return (List<CostCentersFuel>) query.getResultList();
		} catch (RuntimeException re) {
			log.error("ConsultarSolicitudAsignacionAlquilerVehiculos", re);
		}
		return null;
	}

	/**
	 * 
	 * @param placa
	 * @return
	 * @throws GWorkException
	 */
	@SuppressWarnings("unchecked")
	public static List<LocationsNewness> consultarDatosNovedadCambioUbicacionPorPlaca(
			String placa) throws GWorkException {
		try {

			List<LocationsNewness> listLocationsNewness = null;

			final String queryString = "SELECT model "
					+ "FROM LocationsNewness model "
					+ "WHERE model.vehicles.vhcPlacaDiplomatica = :placaVehiculo "
					+ "ORDER BY model.lcnFechaActual DESC";

			Query query = EntityManagerHelper.getEntityManager().createQuery(
					queryString);
			query.setParameter("placaVehiculo", placa);

			listLocationsNewness = query.getResultList();
			return listLocationsNewness;
		} catch (Exception e) {
			throw new GWorkException(e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	public static List<Object[]> consultarCiudadporUbicacion(String Ciudad)
			throws GWorkException {

		List<Object[]> city = null;
		try {
			final String queryString = "select cts.ctsNombre, cts.countries.cntNombre"
					+ " from Cities cts" + " where cts.ctsNombre = :ciudad";

			EntityManagerHelper.getEntityManager().clear();
			Query query = EntityManagerHelper.getEntityManager().createQuery(
					queryString);
			query.setParameter("ciudad", Ciudad.trim());

			city = query.getResultList();

			return city;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find by property name failed",
					Level.SEVERE, re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public static List<Locations> consultarDatosUbicacion(String Ciudad)
			throws GWorkException {
		try {

			List<Locations> location = null;

			final String queryString = "SELECT model "
					+ "FROM Locations model "
					+ "WHERE model.cities.ctsNombre = :Ciudad";

			EntityManagerHelper.getEntityManager().clear();
			Query query = EntityManagerHelper.getEntityManager().createQuery(
					queryString);
			query.setParameter("Ciudad", Ciudad.trim());

			location = (List<Locations>) query.getResultList();
			return location;
		} catch (Exception e) {
			throw new GWorkException(e.getMessage());
		}
	}

	public static void main(String[] args) throws GWorkException {
		// VehiclesAssignation vehiclesAssignation =
		// consultarDatosAsignacion(new Long("4"));
		// System.out.print(vehiclesAssignation.getVhaNumeroCarne());
	}

}
