/**
 * 
 */
package geniar.siscar.logic.fuels.services.impl;

import geniar.siscar.consults.impl.ConsultsServiceImpl;
import geniar.siscar.logic.billing.services.impl.FlatFileFuelServiceImpl;
import geniar.siscar.logic.billing.services.impl.PrepaidProofBoughtServiceImpl;
import geniar.siscar.logic.consultas.SearchPrepaid;
import geniar.siscar.logic.consultas.SearchVehicles;
import geniar.siscar.logic.fuels.services.PrepaymentService;
import geniar.siscar.model.CostCentersFuel;
import geniar.siscar.model.CostsCenters;
import geniar.siscar.model.HeaderProof;
import geniar.siscar.model.Period;
import geniar.siscar.model.Prepaid;
import geniar.siscar.model.ServiceRegistry;
import geniar.siscar.model.VehiclesAssignation;
import geniar.siscar.persistence.CostsCentersDAO;
import geniar.siscar.persistence.EntityManagerHelper;
import geniar.siscar.persistence.ICostsCentersDAO;
import geniar.siscar.persistence.PrepaidDAO;
import geniar.siscar.util.ParametersUtil;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

import java.sql.Connection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @project SISCAR
 * @module Combustibles
 * @author mauricio.cuenca
 * @company Geniar Intelligence
 * @since 20/08/2008
 */
public class PrepaymentServiceImpl implements PrepaymentService {

	/*
	 * (non-Javadoc)
	 * 
	 * @see geniar.siscar.logic.fuels.services.PrepaymentService#comprarPrepago(java.util.Date,
	 *      java.util.List, java.lang.String, java.lang.String)
	 */
	public void comprarPrepago(Date fechaIni, List<CostCentersFuel> lstCCF,
			String prePlaca, String carneAsignatario, String login)
			throws GWorkException {

		Connection connection = null;
		
		try {
			connection = ConsultsServiceImpl.getConnection("jdbc/siscarFinanciero");
			
			EntityManagerHelper.getEntityManager().getTransaction().begin();
			
			for (CostCentersFuel costCentersFuel : lstCCF) {
				VehiclesAssignation vehiclesAssignation = null;
				Prepaid prepaid = updatePrepaid(costCentersFuel,
						carneAsignatario, fechaIni, prePlaca, 
						costCentersFuel.getCcfValor());

				Period period = FlatFileFuelServiceImpl.consultarPeriodoByNovedad(
						fechaIni,ParametersUtil.NOVEDAD_COMB);

				HeaderProof headerProof = null;

				vehiclesAssignation = SearchVehicles.consultarAsignacionVehiculo(prePlaca);

				if (vehiclesAssignation == null)
					throw new GWorkException(Util.loadErrorMessageValue("SOLICITUD.ASIGNACION")
							+ ": " + prePlaca);

				CostsCenters costsCenters = new CostsCentersDAO().findById(
						costCentersFuel.getCostsCenters().getCocCodigo().longValue());

				Float valorPrepago = 0F;

				if (costsCenters.getValorPrepago() == null)
					costsCenters.setValorPrepago(0F);

				valorPrepago = costCentersFuel.getCcfValor() + costsCenters.getValorPrepago();

				costsCenters.setValorPrepago(valorPrepago);
				new CostsCentersDAO().update(costsCenters);

				headerProof = new PrepaidProofBoughtServiceImpl()
						.generarCabeceraComprobante(
								ParametersUtil.COMPRANTE_COMBUSTIBLE, period,
								ParametersUtil.TRASACCTION_TYPE,
								ParametersUtil.DOLAR);

				String idMaster = new ConsultsServiceImpl().getIdMaster();
				Long idDetail = Long.valueOf(1);

				connection = new PrepaidProofBoughtServiceImpl().generarComprobanteDetalle(
						connection, 
						ParametersUtil.TRASACCTION_TYPE, login, 
						costCentersFuel.getCcfValor(), ParametersUtil.DEBITO,
						costCentersFuel.getCostsCenters().getCocNumero(),
						prePlaca, fechaIni, vehiclesAssignation, period,
						headerProof, idMaster, idDetail);

				idDetail++;
				connection = new PrepaidProofBoughtServiceImpl().generarComprobanteDetalle(
						connection, 
						ParametersUtil.TRASACCTION_TYPE, login,
						costCentersFuel.getCcfValor(),
						ParametersUtil.CREDITO, 
						costCentersFuel.getCostsCenters().getCocNumero(),
						prePlaca, fechaIni, vehiclesAssignation,
						period, headerProof, idMaster, idDetail);

				connection = ConsultsServiceImpl.insertTMaster(connection, idMaster, "P", idDetail.intValue());

				prepaid.setHeaderProof(headerProof);
				new PrepaidDAO().update(prepaid);

			}
			EntityManagerHelper.getEntityManager().getTransaction().commit();

			if (connection != null)
				connection.commit();

		} catch (Exception e) {
			EntityManagerHelper.getEntityManager().close();
			e.printStackTrace();
			
			throw new GWorkException(Util.loadErrorMessageValue("ERROR.GUARDAR") + 
					" - " + e.getMessage().toString(), e);
		} finally{
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

	public Set<CostCentersFuel> updateCostCenterFuel(
			List<CostCentersFuel> listaCCFuel, Date fechaIni) {

		Set<CostCentersFuel> set = new HashSet<CostCentersFuel>();
		ICostsCentersDAO daoCC = new CostsCentersDAO();

		for (CostCentersFuel costCentersFuel : listaCCFuel) {
			costCentersFuel.setCcfFechaInicio(fechaIni);

			List<CostsCenters> lstCC = daoCC.findByCocNumero(costCentersFuel
					.getCostsCenters().getCocNumero());

			CostsCenters costsCenters;
			if (lstCC != null && !lstCC.isEmpty()) {
				costsCenters = lstCC.get(0);
			} else {
				costsCenters = costCentersFuel.getCostsCenters();
				EntityManagerHelper.getEntityManager().getTransaction().begin();
				daoCC.save(costsCenters);
				EntityManagerHelper.getEntityManager().getTransaction()
						.commit();

			}

			costCentersFuel.setCostsCenters(costsCenters);
			set.add(costCentersFuel);
		}

		return set;

	}

	public Prepaid updatePrepaid(CostCentersFuel costCentersFuel,
			String carneAsignatario, Date fechaIni, String prePlaca,
			Float preTotal) {

		Prepaid prepaid = new Prepaid();
		prepaid.setCostCentersFuel(costCentersFuel);
		prepaid.setPreAsignatario(carneAsignatario);
		prepaid.setPreFechaini(fechaIni);
		prepaid.setPrePlaca(prePlaca);
		prepaid.setPreTotal(preTotal);

		return prepaid;
	}

	/**
	 * Retorno el promedio de consumo.
	 */
	public float obtenerPromedio(String placa) throws GWorkException {

		float promedio = 0f;

		List<ServiceRegistry> lstSR = new SearchPrepaid()
				.registrosServicioX_Vehiculo(placa);

		if (lstSR != null && !lstSR.isEmpty()) {
			int numConsumos = lstSR.size();
			float totalConsumo = 0f;
			for (ServiceRegistry sr : lstSR) {
				totalConsumo = totalConsumo + sr.getSerTotal();
			}
			promedio = totalConsumo / numConsumos;
		}
		return promedio;
	}
}