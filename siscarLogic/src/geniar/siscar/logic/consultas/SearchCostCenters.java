package geniar.siscar.logic.consultas;

import geniar.siscar.model.CostCentersFuel;
import geniar.siscar.model.CostsCenters;
import geniar.siscar.model.CostsCentersVehicles;
import geniar.siscar.model.VOCostCenters;
import geniar.siscar.model.VOCostCentersFuels;
import geniar.siscar.persistence.EntityManagerHelper;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SearchCostCenters {

	public static final String vhcPlacaDiplomatica = "vhcPlacaDiplomatica";
	private static final Log log = LogFactory.getLog(SearchCostCenters.class);

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	@SuppressWarnings("unchecked")
	public String consultarCentrosDeCosto() throws GWorkException {
		try {
			final String queryString = "SELECT model FROM CostsCenters model";
			Query query = getEntityManager().createQuery(queryString);
			StringBuffer buffer = new StringBuffer();
			List<CostsCenters> list = query.getResultList();

			if (list != null && !list.isEmpty()) {
				int i = 0;
				buffer.append("cc ");
				for (CostsCenters costsCenters : list) {
					buffer.append(costsCenters.getCocCodigo());
					buffer.append(" ");
					buffer.append(costsCenters.getCocNumero());
					buffer.append(" '" + i++ + "'");
					buffer.append(" ");
				}
				return buffer.toString();
			} else
				return null;
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.CAPNOENCONTRAD"));
		}
	}

	public static CostsCenters consultarCentroCosto(String centroCosto)
			throws GWorkException {

		try {

			final String queryString = "select model from CostsCenters model where model.cocNumero= :centroCosto";
			Query query = EntityManagerHelper.getEntityManager().createQuery(
					queryString);

			query.setParameter("centroCosto", centroCosto);

			if (query.getResultList() != null
					&& query.getResultList().size() > 0
					&& !query.getResultList().isEmpty())
				return (CostsCenters) query.getSingleResult();
			else
				return null;

		} catch (Exception e) {
			log.error("consultarCentroCosto", e);
		}
		return null;
	}

	public static VOCostCenters consultarCentroCostoVO(String centroCosto)
			throws GWorkException {

		final String queryString = "select model from CostsCenters model where model.cocNumero= :centroCosto";
		Query query = EntityManagerHelper.getEntityManager().createQuery(
				queryString);

		query.setParameter("centroCosto", centroCosto);

		if (query.getResultList() != null && query.getResultList().size() > 0
				&& !query.getResultList().isEmpty()) {
			CostsCenters costsCenters = (CostsCenters) query.getResultList()
					.get(0);
			VOCostCenters centers = new VOCostCenters();
			centers.setCocCodigo(costsCenters.getCocCodigo());
			centers.setCocNumero(costsCenters.getCocNumero());
			centers.setCocValorPrepago(costsCenters.getValorPrepago());
			return centers;
		} else
			return null;
	}

	public static void main(String[] args) throws GWorkException {
		CostsCenters cc = consultarCentroCosto("FG70A");
		System.out.println(new SearchCostCenters().consultarCentrosDeCosto());

	}

	@SuppressWarnings("unchecked")
	public static List<VOCostCentersFuels> consultarCostCenterFuelPorPlaca(
			String placa) throws GWorkException {
		try {
			List<VOCostCentersFuels> listVOCostCentersFuels = new ArrayList<VOCostCentersFuels>();
			List<CostCentersFuel> valor = new ArrayList<CostCentersFuel>();
			final String queryString = "select model from CostCentersFuel model " +
					"where model.vehiclesAssignation.vehicles.vhcPlacaDiplomatica = :placa " +
					"AND model.vehiclesAssignation.assignationsStates.assCodigo = 5 " +
					"AND model.ccfEstado = 'ACTIVO'";

			Query query = EntityManagerHelper.getEntityManager().createQuery(
					queryString);
			query.setParameter("placa", placa.trim().toUpperCase());

			valor = query.getResultList();

			if (valor != null && valor.size() > 0) {
				for (CostCentersFuel costsCenters : valor) {
					VOCostCentersFuels VOcostCentersFuels = new VOCostCentersFuels();
					VOcostCentersFuels = new VOCostCentersFuels();

					VOcostCentersFuels.setCostCenterNumber(costsCenters
							.getCostsCenters().getCocNumero());
					VOcostCentersFuels.setCcfId(costsCenters.getCostsCenters()
							.getCocCodigo());

					listVOCostCentersFuels.add(VOcostCentersFuels);
				}
			}

			return listVOCostCentersFuels;

		} catch (Exception re) {
			throw new GWorkException(Util
					.loadErrorMessageValue("search.not.found"));
		}
	}

	@SuppressWarnings("unchecked")
	public static List<CostsCentersVehicles> consultarDatosNovedadCentroCostosPorPlaca(
			String placa) throws GWorkException {
		try {

			List<CostsCentersVehicles> listCostsCentersNewness = null;

			final String queryString = "select model from CostsCentersVehicles model "
					+ "where model.vehiclesAssignation.vehicles.vhcPlacaDiplomatica = :placaVehiculo";
			Query query = EntityManagerHelper.getEntityManager().createQuery(
					queryString);
			query.setParameter("placaVehiculo", placa);

			listCostsCentersNewness = (List<CostsCentersVehicles>) query
					.getResultList();

			if (listCostsCentersNewness != null
					&& listCostsCentersNewness.size() > 0) {
				return listCostsCentersNewness;
			}
		} catch (Exception e) {
			throw new GWorkException(e.getMessage());
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public static String consultarCostCenterAsignacion(Long vhaCodigo)
			throws GWorkException {
		try {

			// List<Object[]> bilList = new ArrayList<Object[]>();
			// List<BillingAccountVO> bilListVO = new
			// ArrayList<BillingAccountVO>();
			StringBuffer buffer = new StringBuffer();

			List<VOCostCenters> listVOCostCenters = new ArrayList<VOCostCenters>();
			List<CostsCentersVehicles> valor;
			final String queryString = "select model "
					+ "	from CostsCentersVehicles model "
					+ "	where model.vehiclesAssignation.vhaCodigo = :vhaCodigo AND model.ccrEstado = 'ACTIVO'";

			Query query = EntityManagerHelper.getEntityManager().createQuery(
					queryString);
			query.setParameter("vhaCodigo", vhaCodigo);

			valor = query.getResultList();

			if (valor != null && valor.size() > 0) {
				buffer.append(" - ");
				for (CostsCentersVehicles costsCenters : valor) {
					VOCostCenters VOcostCenters = new VOCostCenters();
					VOcostCenters = new VOCostCenters();

					VOcostCenters.setCocNumero(costsCenters.getCostsCenters()
							.getCocNumero());
					VOcostCenters.setCocCodigo(costsCenters.getCostsCenters()
							.getCocCodigo());

					listVOCostCenters.add(VOcostCenters);

					buffer
							.append(costsCenters.getCostsCenters()
									.getCocNumero());
					buffer.append(" - ");
				}
			}
			return buffer.toString();

		} catch (Exception re) {
			throw new GWorkException(Util
					.loadErrorMessageValue("search.not.found"));
		}
	}

	/**
	 * @author alvaro.hincapie
	 * @param vhaCodigo
	 * @return
	 * @throws GWorkException
	 */
	@SuppressWarnings("unchecked")
	public static String consultarCostCenterFuel(Long vhaCodigo)
			throws GWorkException {
		try {

			StringBuffer buffer = new StringBuffer();

			List<VOCostCenters> listVOCostCenters = new ArrayList<VOCostCenters>();
			List<CostCentersFuel> valor;
			final String queryString = "select model "
					+ "	from CostCentersFuel model "
					+ "	where model.vehiclesAssignation.vhaCodigo = :vhaCodigo AND model.ccfEstado = 'ACTIVO'";

			Query query = EntityManagerHelper.getEntityManager().createQuery(
					queryString);
			query.setParameter("vhaCodigo", vhaCodigo);

			valor = query.getResultList();

			if (valor != null && valor.size() > 0) {
				buffer.append(" - ");
				for (CostCentersFuel costsCenters : valor) {
					VOCostCenters VOcostCenters = new VOCostCenters();
					VOcostCenters = new VOCostCenters();

					VOcostCenters.setCocNumero(costsCenters.getCostsCenters()
							.getCocNumero());
					VOcostCenters.setCocCodigo(costsCenters.getCostsCenters()
							.getCocCodigo());

					listVOCostCenters.add(VOcostCenters);

					buffer
							.append(costsCenters.getCostsCenters()
									.getCocNumero());
					buffer.append(" - ");
				}
			}
			return buffer.toString();

		} catch (Exception re) {
			throw new GWorkException(Util
					.loadErrorMessageValue("search.not.found"));
		}
	}

	@SuppressWarnings("unchecked")
	public static List<CostsCentersVehicles> consultarCCVehiculo(String placa)
			throws GWorkException {

		try {

			final String queryString = "select model from CostsCentersVehicles model "
					+ "where model.vehiclesAssignation.vehicles.vhcPlacaDiplomatica = :placaVehiculo "
					+ "and model.vehiclesAssignation.vhaFechaEntrega is not null "
					+ "and model.vehiclesAssignation.vhaFechaDev is null "
					+ "AND model.ccrEstado = :activo "
					+ "ORDER BY model.costsCenters.cocNumero";
			Query query = EntityManagerHelper.getEntityManager().createQuery(
					queryString);
			query.setParameter("placaVehiculo", placa);
			query
					.setParameter("activo", Util
							.loadMessageValue("ESTADO.ACTIVO"));

			if (query.getResultList() != null
					&& query.getResultList().size() > 0)
				return (List<CostsCentersVehicles>) query.getResultList();

		} catch (RuntimeException e) {
			log.error(e.getMessage());
		}
		return null;

	}

	@SuppressWarnings("unchecked")
	public static List<CostsCentersVehicles> consultarCCVehiculoEntrega(
			Long idAsignacion) throws GWorkException {

		try {

			final String queryString = "select model from CostsCentersVehicles model "
					+ "where model.vehiclesAssignation.vhaCodigo = :idAsignacion "
					+ "AND model.ccrEstado = :activo";
			Query query = EntityManagerHelper.getEntityManager().createQuery(
					queryString);
			query.setParameter("idAsignacion", idAsignacion);
			query
					.setParameter("activo", Util
							.loadMessageValue("ESTADO.ACTIVO"));

			if (query.getResultList() != null
					&& query.getResultList().size() > 0)
				return (List<CostsCentersVehicles>) query.getResultList();

		} catch (RuntimeException e) {
			log.error(e.getMessage());
		}
		return null;
	}

	// Verifcar que el centro de costo de combustible tenga solo una placa a su
	// cargo
	public static boolean veficarCCFAsociacionPlaca(String centroCosto)
			throws GWorkException {

		try {
			final String queryString = "select model from CostCentersFuel model "
					+ "where model.costsCenters.cocNumero = :centroCosto "
					+ "AND model.ccfEstado = :activo";
			Query query = EntityManagerHelper.getEntityManager().createQuery(
					queryString);
			query.setParameter("centroCosto", centroCosto.toUpperCase());
			query
					.setParameter("activo", Util
							.loadMessageValue("ESTADO.ACTIVO"));

			if (query.getResultList() != null
					&& query.getResultList().size() > 1)
				return false;
			else
				return true;

		} catch (RuntimeException e) {
			log.error(e.getMessage());
		}
		return false;
	}

	public static Double saldoConsumoPorPlaca(Date fechaIni, Date fechaFin,
			String placa, String centroCosto) {

		try {

			// Consumos

			final String queryString1 = "select sum(pc.prcValorConsumo) as saldo  from PrepaidConsumption pc "
					+ "WHERE pc.serviceRegistry.vehicles.vhcPlacaDiplomatica = :placa "
					+ "AND pc.costCentersFuel.costsCenters.cocNumero = :centroCosto "
					+ "AND pc.prcFecha BETWEEN :fechaIni AND :fechaFin";
			Query query1 = EntityManagerHelper.getEntityManager().createQuery(
					queryString1);
			query1.setParameter("centroCosto", centroCosto.toUpperCase());
			query1.setParameter("fechaIni", fechaIni);
			query1.setParameter("fechaFin", fechaFin);
			query1.setParameter("placa", placa);

			// Compras

			final String queryString2 = "select sum (p.preTotal) as saldo  from Prepaid p "
					+ "WHERE p.prePlaca = :placa "
					+ "AND p.costCentersFuel.costsCenters.cocNumero = :centroCosto "
					+ "AND p.preFechaini BETWEEN :fechaIni AND :fechaFin ";
			Query query2 = EntityManagerHelper.getEntityManager().createQuery(
					queryString2);
			query2.setParameter("fechaIni", fechaIni);
			query2.setParameter("fechaFin", fechaFin);
			query2.setParameter("centroCosto", centroCosto.toUpperCase());
			query2.setParameter("placa", placa);

			Double consumos = (Double) query1.getSingleResult();
			Double compras = (Double) query2.getSingleResult();

			System.out
					.println("Consumos: " + consumos + " Compras: " + compras);

			System.out.println("");

			if (consumos != null && compras != null)
				return compras - consumos;

		} catch (RuntimeException e) {
			log.error(e.getMessage());
		}
		return null;
	}
}
