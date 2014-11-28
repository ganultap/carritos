package geniar.siscar.logic.newness.services.impl;

import geniar.siscar.consults.impl.ConsultsServiceImpl;
import geniar.siscar.logic.billing.services.impl.FlatFileFuelServiceImpl;
import geniar.siscar.logic.billing.services.impl.PrepaidDevolutionServiceImpl;
import geniar.siscar.logic.billing.services.impl.PrepaidProofBoughtServiceImpl;
import geniar.siscar.logic.consultas.SearchCostCenters;
import geniar.siscar.logic.newness.services.CostCentersService;
import geniar.siscar.model.CostCenterTypeFuel;
import geniar.siscar.model.CostCentersFuel;
import geniar.siscar.model.CostsCenters;
import geniar.siscar.model.CostsCentersNewness;
import geniar.siscar.model.CostsCentersVehicles;
import geniar.siscar.model.HeaderProof;
import geniar.siscar.model.Period;
import geniar.siscar.model.Requests;
import geniar.siscar.model.Vehicles;
import geniar.siscar.model.VehiclesAssignation;
import geniar.siscar.persistence.CostCenterTypeFuelDAO;
import geniar.siscar.persistence.CostCentersFuelDAO;
import geniar.siscar.persistence.CostsCentersDAO;
import geniar.siscar.persistence.CostsCentersNewnessDAO;
import geniar.siscar.persistence.CostsCentersVehiclesDAO;
import geniar.siscar.persistence.EntityManagerHelper;
import geniar.siscar.persistence.VehiclesAssignationDAO;
import geniar.siscar.persistence.VehiclesDAO;
import geniar.siscar.util.ParametersUtil;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

public class CostCentersImpl implements CostCentersService {

	public void actualizarCentrosDeCostos(List<CostsCentersVehicles> list,
			String login, Long idCodigoVehiculo, Date fechaIni,
			Date FechaAsignacion) throws GWorkException {
		try {
			for (CostsCentersVehicles costsCentersVehicles : list) {
				EntityManagerHelper.getEntityManager().getTransaction().begin();
				costsCentersVehicles.setCcrFechaFin(FechaAsignacion);
				costsCentersVehicles.setCcrEstado(Util
						.loadMessageValue("ESTADO.INNACTIVO"));
				new CostsCentersVehiclesDAO().update(costsCentersVehicles);

				EntityManagerHelper.getEntityManager().getTransaction()
						.commit();
				// EntityManagerHelper.getEntityManager().clear();

				crearNovedadCambioCentroCostos(login, costsCentersVehicles
						.getCcrPorcentaje(), fechaIni, FechaAsignacion,
						idCodigoVehiculo, null);
			}
		} catch (Exception e) {
			EntityManagerHelper.rollback();
			throw new GWorkException(e.getMessage());
		}
	}

	public void actualizarCentrosDeCostosCombustible(
			List<CostCentersFuel> list, String login, Long idCodigoVehiculo,
			Date fechaIni, Date FechaAsignacion) throws GWorkException {
		
		Connection connection = null;

		try {
			for (CostCentersFuel costsCentersFuel : list) {
				
				connection = ConsultsServiceImpl.getConnection("jdbc/siscarFinanciero");
				
				costsCentersFuel.setCcfFechaFin(FechaAsignacion);
				costsCentersFuel.setCcfEstado(Util
						.loadMessageValue("ESTADO.INNACTIVO"));
				new CostCentersFuelDAO().update(costsCentersFuel);

				// validar que el centro de costo tenga solo un vehículo
				// asociado
				// saldoFinalPlaca = SearchCostCenters.saldoConsumoPorPlaca(
				// ManipulacionFechas.getPrimerDiaAnho(), new Date(),
				// costsCentersFuel.getVehiclesAssignation().getVehicles()
				// .getVhcPlacaDiplomatica(), costsCentersFuel
				// .getCostsCenters().getCocNumero());

				CostsCenters costsCenters = new CostsCentersDAO()
						.findById(costsCentersFuel.getCostsCenters()
								.getCocCodigo().longValue());

				// Verifica que el centro de costo solo tenga asociado una placa
				if (SearchCostCenters.veficarCCFAsociacionPlaca(costsCenters.getCocNumero())
						&& costsCenters.getValorPrepago() != null) {

					Float saldoDevCenCos = costsCenters.getValorPrepago();

					Period periodo = FlatFileFuelServiceImpl.consultarPeriodoByNovedad(
							new Date(),
							ParametersUtil.NOVEDAD_COMB);

					String idMaster = new ConsultsServiceImpl().getIdMaster();
					Long idDetail = Long.valueOf(0);
					
					HeaderProof headerProof = null;
					headerProof = new PrepaidProofBoughtServiceImpl()
							.generarCabeceraComprobante(
									ParametersUtil.COMPRANTE_COMBUSTIBLE,
									periodo, ParametersUtil.TRASACCTION_TYPE,
									ParametersUtil.DOLAR);

					// Asignar los valores a los comprobantes
					//saldoDevCenCos = saldoDevCenCos * -1;

					PrepaidDevolutionServiceImpl devolutionServiceImpl = new PrepaidDevolutionServiceImpl();
					idDetail++;
					connection = devolutionServiceImpl.generarComprobanteDetalle(connection,
							ParametersUtil.PROOF_TYPE_COMBUSTIBLE, login,
							saldoDevCenCos, ParametersUtil.DEBITO,
							costsCentersFuel.getCostsCenters().getCocNumero().trim(), 
							periodo, headerProof,
							costsCentersFuel.getCostsCenters().getCocCodigo(),
							costsCentersFuel.getVehiclesAssignation().getVehicles().getVhcPlacaDiplomatica(),
							idMaster, idDetail);
					idDetail++;
					connection = devolutionServiceImpl
							.generarComprobanteDetalle(connection,
									ParametersUtil.PROOF_TYPE_COMBUSTIBLE,
									login, saldoDevCenCos,
									ParametersUtil.CREDITO, 
									costsCentersFuel.getCostsCenters().getCocNumero().trim(), 
									periodo, headerProof,
									costsCentersFuel.getCostsCenters().getCocCodigo(), 
									costsCentersFuel.getVehiclesAssignation().getVehicles().getVhcPlacaDiplomatica(),
									idMaster, idDetail);

					costsCenters.setValorPrepago(null);
					
					connection = ConsultsServiceImpl.insertTMaster(connection, idMaster, "P", idDetail.intValue());
				}

				crearNovedadCambioCentroCostos(login, costsCentersFuel
						.getCcfPorcentaje(), fechaIni, FechaAsignacion,
						idCodigoVehiculo, connection);
			}
		} catch (Exception e) {
			throw new GWorkException(e.getMessage());
		}finally{
			try{
				if (connection!=null && !connection.isClosed()){
					connection.close();
				}
			}catch(Exception ex2){
				//log.error("Error: " + ex2.getMessage(), ex2);
				throw new GWorkException(ex2.getMessage(), ex2);
			}
		}
	}

	public void crearNovedadCambioCentroCostos(String login, String porcentaje,
			Date fechaInicio, Date fechaFin, Long idCodigoVehiculo,
			Connection connection) throws GWorkException {

		try {
			EntityManagerHelper.getEntityManager().getTransaction().begin();
			Vehicles vehicles = new VehiclesDAO().findById(idCodigoVehiculo);
			if (vehicles != null) {
				CostsCentersNewness centersNewness = new CostsCentersNewness();
				centersNewness.setCcnFechaActual(new Date());
				centersNewness.setCcrFechaInicio(fechaInicio);
				centersNewness.setCcrFechaFin(fechaFin);
				centersNewness.setCcrPorcentaje(porcentaje);
				centersNewness.setUsrLogin(login);
				centersNewness.setVehicles(vehicles);

				new CostsCentersNewnessDAO().save(centersNewness);
				EntityManagerHelper.getEntityManager().getTransaction().commit();
				EntityManagerHelper.getEntityManager().clear();

				if (connection != null)
					connection.commit();
			}
		} catch (Exception e) {
			validarSession();
			EntityManagerHelper.getEntityManager().getTransaction().rollback();
			throw new GWorkException(e.getMessage());
		}
	}

	public void guardarCentroCostos(String nombre, String login,
			String porcentaje, String idVehiculo, String idAsginacion,
			Date fechaAsignacion, Date fechaTermina) throws GWorkException {

		Vehicles vehicles = new VehiclesDAO().findById(new Long(idVehiculo));
		VehiclesAssignation vehiclesAssignation = new VehiclesAssignationDAO()
				.findById(new Long(idAsginacion));

		CostsCenters centers = new CostsCenters();
		centers.setCocNumero(nombre.trim().toUpperCase());

		CostsCentersVehicles centersVehicles = new CostsCentersVehicles();
		centersVehicles.setCcrPorcentaje(porcentaje);
		centersVehicles.setCcrLogin(login);
		centersVehicles.setCcrFechaActual(new Date());
		centersVehicles.setCcrEstado(Util.loadMessageValue("ESTADO.ACTIVO"));
		fechaAsignacion = Util.traerFechaMasDias(1, fechaAsignacion);
		centersVehicles.setCcrFechaInicio(fechaAsignacion);
		centersVehicles.setCcrFechaFin(fechaTermina);
		centersVehicles.setCostsCenters(centers);

		if (vehicles != null)
			centersVehicles.setVehicles(vehicles);

		if (vehiclesAssignation != null)
			centersVehicles.setVehiclesAssignation(vehiclesAssignation);

		saveCentroDeCostos(centers);
		saveCentroDeCostosVehiculos(centersVehicles);
	}

	public void guardarCentroCostosCombustible(CostsCenters costsCenters,
			String porcentaje, String idVehiculo, String idAsginacion,
			Date fechaAsignacion, Date fechaTermina) throws GWorkException {

		VehiclesAssignation vehiclesAssignation = new VehiclesAssignationDAO()
				.findById(new Long(idAsginacion));

		CostCenterTypeFuel costCenterTypeFuel = new CostCenterTypeFuelDAO()
				.findById(1L);

		costsCenters.getValorPrepago();
		CostCentersFuel costCentersFuel = new CostCentersFuel();
		costCentersFuel.setCcfPorcentaje(porcentaje);
		costCentersFuel.setCcfEstado(Util.loadMessageValue("ESTADO.ACTIVO"));
		fechaAsignacion = Util.traerFechaMasDias(1, fechaAsignacion);
		costCentersFuel.setCcfFechaInicio(fechaAsignacion);
		costCentersFuel.setCcfFechaFin(fechaTermina);
		costCentersFuel.setCostsCenters(costsCenters);
		costCentersFuel.setCostCenterTypeFuel(costCenterTypeFuel);
		costCentersFuel.setCcfValor(null);

		if (vehiclesAssignation != null)
			costCentersFuel.setVehiclesAssignation(vehiclesAssignation);

		saveCentroDeCostosCombustible(costCentersFuel);
	}

	public void eliminarCentroCostosVehiculo(
			CostsCentersVehicles costsCentersVehicles) throws GWorkException {
		try {
			validarSession();
			EntityManagerHelper.beginTransaction();
			CostsCenters costsCenters = new CostsCentersDAO()
					.findById(costsCentersVehicles.getCostsCenters()
							.getCocCodigo());
			if (costsCenters != null) {
				new CostsCentersVehiclesDAO().delete(costsCentersVehicles);
				new CostsCentersDAO().delete(costsCenters);
				EntityManagerHelper.commit();
				limpiarSession();
			}
		} catch (Exception e) {

			EntityManagerHelper.rollback();
			throw new GWorkException(e.getMessage());
		}
	}

	public void saveCentroDeCostosVehiculos(CostsCentersVehicles centersVehicles)
			throws GWorkException {
		try {
			validarSession();
			EntityManagerHelper.getEntityManager().getTransaction().begin();
			new CostsCentersVehiclesDAO().save(centersVehicles);
			EntityManagerHelper.getEntityManager().getTransaction().commit();
			limpiarSession();
		} catch (Exception e) {
			EntityManagerHelper.getEntityManager().getTransaction().rollback();
			throw new GWorkException(e.getMessage());
		}
	}

	public void saveCentroDeCostosCombustible(CostCentersFuel costCentersFuel)
			throws GWorkException {
		try {
			validarSession();
			EntityManagerHelper.getEntityManager().getTransaction().begin();
			new CostCentersFuelDAO().save(costCentersFuel);
			EntityManagerHelper.getEntityManager().getTransaction().commit();
			limpiarSession();
		} catch (Exception e) {
			EntityManagerHelper.getEntityManager().getTransaction().rollback();
			throw new GWorkException(e.getMessage());
		}
	}

	public void saveCentroDeCostos(CostsCenters centers) throws GWorkException {
		try {
			validarSession();
			EntityManagerHelper.getEntityManager().getTransaction().begin();
			new CostsCentersDAO().save(centers);
			EntityManagerHelper.getEntityManager().getTransaction().commit();
			limpiarSession();
		} catch (Exception e) {
			limpiarSession();
			EntityManagerHelper.getEntityManager().getTransaction().rollback();
			throw new GWorkException(e.getMessage());
		}
	}

	public void limpiarSession() {
		EntityManagerHelper.getEntityManager().clear();
		EntityManagerHelper.closeEntityManager();
	}

	public void validarSession() {
		if (EntityManagerHelper.getEntityManager().isOpen())
			EntityManagerHelper.getEntityManager().close();
	}

	/* Alteración de metodo */
	public Integer guardarCentroCostos(CostsCenters costCenter, String login,
			String porcentaje, String idVehiculo, String idAsginacion,
			Date fechaAsignacion, Date fechaTermina) throws GWorkException {

		Vehicles vehicles = new VehiclesDAO().findById(new Long(idVehiculo));
		VehiclesAssignation vehiclesAssignation = new VehiclesAssignationDAO()
				.findById(new Long(idAsginacion));

		CostsCentersVehicles centersVehicles = new CostsCentersVehicles();
		centersVehicles.setCcrPorcentaje(porcentaje);
		centersVehicles.setCcrLogin(login);
		centersVehicles.setCcrFechaActual(new Date());
		centersVehicles.setCcrEstado(Util.loadMessageValue("ESTADO.ACTIVO"));
		fechaAsignacion = Util.traerFechaMasDias(1, fechaAsignacion);
		centersVehicles.setCcrFechaInicio(fechaAsignacion);
		centersVehicles.setCcrFechaFin(fechaTermina);
		centersVehicles.setCostsCenters(costCenter);

		List<Requests> requests = consultarRequest(idAsginacion);
		if (requests != null && requests.size() > 0) {
			Requests objReq = (Requests) requests.get(0);
			centersVehicles.setRequests(objReq);
		}

		if (vehicles != null)
			centersVehicles.setVehicles(vehicles);

		if (vehiclesAssignation != null)
			centersVehicles.setVehiclesAssignation(vehiclesAssignation);

		saveCentroDeCostosVehiculos(centersVehicles);
		return 1;
		
	}

	@SuppressWarnings("unchecked")
	public List<CostsCentersVehicles> consultarCostsCentersVehicles(
			CostsCentersVehicles centersVehicles) {
		List<CostsCentersVehicles> list = null;

		final String queryString = "select model from CostsCentersVehicles model "
				+ "where model.costsCenters.cocCodigo = :cocCodigo "
				+ "and model.ccrEstado = :ccrEstado and model.requests.rqsCodigo = :rqsCodigo";

		Query query = EntityManagerHelper.getEntityManager().createQuery(
				queryString);
		query.setParameter("cocCodigo", centersVehicles.getCostsCenters()
				.getCocCodigo());
		query.setParameter("ccrEstado", centersVehicles.getCcrEstado());
		query.setParameter("rqsCodigo", centersVehicles.getRequests()
				.getRqsCodigo());

		list = (List<CostsCentersVehicles>) query.getResultList();
		if (list != null && list.size() > 0)
			return list;
		else
			return null;
	}

	@SuppressWarnings("unchecked")
	public List<Requests> consultarRequest(String idAsginacion) {
		List<Requests> list = null;
		final String queryString = "select model.requests from VehiclesAssignation model "
				+ "where model.vhaCodigo = :idAsginacion";

		Query query = EntityManagerHelper.getEntityManager().createQuery(
				queryString);
		query.setParameter("idAsginacion", Long.parseLong((idAsginacion)));

		list = (List<Requests>) query.getResultList();
		if (list != null && list.size() > 0)
			return list;
		else
			return null;
	}

	public void actualizarCentrosDeCostos(List<CostsCentersVehicles> list,
			String login, Long idCodigoVehiculo, Date fechaIni,
			Date FechaAsignacion, Integer estado_ccv) throws GWorkException {
		try {
			for (CostsCentersVehicles costsCentersVehicles : list) {
				EntityManagerHelper.getEntityManager().getTransaction().begin();
				costsCentersVehicles.setCcrFechaFin(FechaAsignacion);

				if (estado_ccv == 0) {
					costsCentersVehicles.setCcrEstado(Util
							.loadMessageValue("ESTADO.INNACTIVO"));
				} else if (estado_ccv == 1) {
					costsCentersVehicles.setCcrEstado(Util
							.loadMessageValue("ESTADO.ACTIVO"));
				}

				new CostsCentersVehiclesDAO().update(costsCentersVehicles);

				EntityManagerHelper.getEntityManager().getTransaction()
						.commit();
				EntityManagerHelper.getEntityManager().clear();

				crearNovedadCambioCentroCostos(login, costsCentersVehicles
						.getCcrPorcentaje(), fechaIni, FechaAsignacion,
						idCodigoVehiculo, null);
			}
		} catch (Exception e) {
			EntityManagerHelper.getEntityManager().getTransaction().rollback();
			throw new GWorkException(e.getMessage());
		}
	}
}
