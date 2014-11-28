package geniar.siscar.logic.billing.services.impl;

import geniar.siscar.consults.impl.ConsultsServiceImpl;
import geniar.siscar.logic.billing.services.PrepaidDevolutionService;
import geniar.siscar.logic.fuels.services.impl.SearchPrepaidServiceImpl;
import geniar.siscar.model.AccountingParameters;
import geniar.siscar.model.ActualOthersApplications;
import geniar.siscar.model.CostsCenters;
import geniar.siscar.model.HeaderProof;
import geniar.siscar.model.Period;
import geniar.siscar.model.VOCostCenters;
import geniar.siscar.persistence.ActualOthersApplicationsDAO;
import geniar.siscar.persistence.CostsCentersDAO;
import geniar.siscar.persistence.EntityManagerHelper;
import geniar.siscar.util.ManipulacionFechas;
import geniar.siscar.util.ParametersUtil;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PrepaidDevolutionServiceImpl implements PrepaidDevolutionService {

	public List<VOCostCenters> listaCostcenterFuelDevolution()
			throws GWorkException {

		List<CostsCenters> listaCostcenterFuelDevolution = null;
		List<VOCostCenters> listaPrepagoInicial = new ArrayList<VOCostCenters>();
		listaCostcenterFuelDevolution = SearchPrepaidServiceImpl
				.listDevolutionPrepaid();

		if (listaCostcenterFuelDevolution != null
				&& listaCostcenterFuelDevolution.size() > 0
				&& !listaCostcenterFuelDevolution.isEmpty()) {

			for (CostsCenters costCenters : listaCostcenterFuelDevolution) {

				VOCostCenters voCostCenter = new VOCostCenters();
				String observaciones = null;

				voCostCenter.setCocCodigo(costCenters.getCocCodigo()
						.longValue());
				voCostCenter.setCocNumero(costCenters.getCocNumero());
				voCostCenter.setCocValorPrepago(Util.redondear(
						new Double(costCenters.getValorPrepago()), 2)
						.floatValue());

				observaciones = new ConsultsServiceImpl()
						.consultCostCenter(costCenters.getCocNumero());

				if (observaciones != null && observaciones.trim().length() > 0) {
					voCostCenter.setObservacion(observaciones);
					voCostCenter.setVisible(true);
					voCostCenter.setSeleccion(true);
				} else
					voCostCenter.setSeleccion(true);

				// Agregar el objeto a la lista de prepago
				listaPrepagoInicial.add(voCostCenter);

			}

			return listaPrepagoInicial;
		}

		else
			throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));
	}

	public Connection generarComprobanteDetalle(Connection connection, Long tipoComprobante,
			String login, Float valor, Long tipoMovimiento, String centroCosto,
			Period period, HeaderProof headerProof, Long cocCodigo, String placa,
			String idMaster, Long idDetail)
			throws GWorkException {

		ActualOthersApplications actualOthersApplications = new ActualOthersApplications();
		AccountingParameters parameters = new AccountingParameters();
		Date fechaActual = new Date();
		
		// Seleccione el tipo de moneda que se ingresa en el comprobante USD o
		// PECO

		// tipoMoneda = consultsService
		// .counsultarTipoMonedaAsignatario(carnePrueba);
		Long tipoMoneda = 1L;
		String pCurr = Util.loadParametersValue("p.curr.dol");
		// si la compra es en dolares
		if (tipoMoneda != null && tipoMoneda.equals(1L)) {

			actualOthersApplications.setPSob(new Long(Util
					.loadParametersValue("p.sob.dolar")));
			actualOthersApplications.setPCurr(pCurr);
		}

		// si la compra es en pesos colombianos
		if (tipoMoneda != null && tipoMoneda.equals(2L)) {
			actualOthersApplications.setPSob(new Long(Util
					.loadParametersValue("p.sob.pesos")));

			actualOthersApplications.setPCurr(Util
					.loadParametersValue("p.curr.ps"));
		}
		String pSource = Util.loadParametersValue("p.source");
		actualOthersApplications.setPAccdate(fechaActual);
		actualOthersApplications.setPUser(login);
		String pCategory = Util.loadParametersValue("p.category.comb");
		actualOthersApplications.setPCategory(pCategory);
		actualOthersApplications.setPSource(pSource);

		actualOthersApplications.setPConvDate(null);
		actualOthersApplications.setPConvRate(null);
		actualOthersApplications.setPConvType(null);

		String centroCostoDebi = Util.loadParametersValue("CC_DEBITO");
		String pCcenter = null;

		if (tipoMovimiento.longValue() == ParametersUtil.DEBITO.longValue())
			pCcenter = centroCosto;

		if (tipoMovimiento.longValue() == ParametersUtil.CREDITO.longValue())
			pCcenter = centroCostoDebi;

		actualOthersApplications.setPCcenter(pCcenter);

		String pAuxiliary = Util.loadParametersValue("p.auxiliary");
		actualOthersApplications.setPAuxiliary(pAuxiliary);

		Float pEntDr = null;
		Float pEntCr = null;

		if (tipoMovimiento.longValue() == ParametersUtil.DEBITO.longValue())
			pEntDr = valor.floatValue();

		if (tipoMovimiento.longValue() == ParametersUtil.CREDITO.longValue())
			pEntCr = valor.floatValue();

		actualOthersApplications.setPEntDr(pEntDr);
		actualOthersApplications.setPEntCr(pEntCr);

		if (tipoMovimiento.longValue() == ParametersUtil.DEBITO.longValue())

			parameters = SearchParametersBilling.consultarParemeter(
					ParametersUtil.DEBITO, ParametersUtil.CARGO_CC,
					ParametersUtil.TRASACCTION_TYPE,
					ParametersUtil.LGT_NO_APLICA);

		if (tipoMovimiento.longValue() == ParametersUtil.CREDITO.longValue())
			parameters = SearchParametersBilling.consultarParemeter(
					ParametersUtil.CREDITO, ParametersUtil.CARGO_CC,
					ParametersUtil.TRASACCTION_TYPE,
					ParametersUtil.LGT_NO_APLICA);

		String pCompany = parameters.getCompany().getCmpNombre();
		actualOthersApplications.setPCompany(pCompany);

		String pAccount = parameters.getAccount().getAccNumeroCuenta();
		actualOthersApplications.setPAccount(pAccount);

		String formatoFecha = ManipulacionFechas.getDia(fechaActual) + "-"
				+ ManipulacionFechas.getNombreMes(fechaActual) + "-"
				+ ManipulacionFechas.getAgno(fechaActual);

		String PDescription = null;

		if (tipoMovimiento.longValue() == ParametersUtil.DEBITO)
			PDescription = Util.loadParametersValue("PARAM_PREPAGO_DEVOL")
					+ " " + centroCosto + " " + placa + " "
					+ Util.loadMessageValue("AÑO_DEVOLUCION")
					+ ManipulacionFechas.getAgno(fechaActual);

		if (tipoMovimiento.longValue() == ParametersUtil.CREDITO)
			PDescription = Util.loadMessageValue("DEVOLUCION_PREPAGO")
					+ ManipulacionFechas.getAgno(fechaActual) + " "
					+ Util.loadParametersValue("p.category.comb");

		actualOthersApplications.setPDescription(PDescription);

		// no veo este valor en la tabla accountig_parameters
		actualOthersApplications.setPNit(null);
		String pAttribute7 = headerProof.getHepGroupId();

		actualOthersApplications.setPAttribute2(null);

		String pAttribute5 = null;
		String pAttribute10 = ManipulacionFechas.getMes(fechaActual)
				+ ManipulacionFechas.getDia(fechaActual);
		String pAttribute6 = null;

		if (placa != null
				&& placa.trim().length() > 0
				&& tipoMovimiento.longValue() == ParametersUtil.DEBITO
						.longValue()) {
			pAttribute5 = parameters.getDocumentOne().getDocumentOneType()
					.getDotName();
			pAttribute6 = placa;

		} else {
			pAttribute5 = parameters.getDocumentTwo().getDocumentTwoType()
					.getDttName();
			pAttribute6 = pAttribute10;
		}

		actualOthersApplications.setPAttribute5(pAttribute5);
		actualOthersApplications.setPAttribute7(pAttribute7);
		actualOthersApplications.setPAttribute8(null);
		String pAttribute9 = parameters.getDocumentTwo().getDocumentTwoType()
				.getDttName();
		actualOthersApplications.setPAttribute9(pAttribute9);

		actualOthersApplications.setPAttribute10(pAttribute10);

		if (tipoMovimiento.longValue() == ParametersUtil.CREDITO.longValue())
			pAttribute6 = pAttribute10;

		actualOthersApplications.setPAttribute6(pAttribute6);
		actualOthersApplications.setAoaState("ACTIVO");

		// Se construye el PBName
		String pBname = null;
		String groupId = headerProof.getHepGroupId();

		if (tipoMovimiento.longValue() == ParametersUtil.DEBITO.longValue())
			pBname = pCompany + "-" + login + "-" + pSource + "-"
					+ Util.loadParametersValue("p.category.comb") + "-"
					+ formatoFecha + "-"
					+ Util.loadParametersValue("PARAM_PREPAGO_DEVOL") + "-"
					+ groupId;

		if (tipoMovimiento.longValue() == ParametersUtil.CREDITO.longValue())
			pBname = pCompany + "-" + login + "-" + pSource + "-"
					+ Util.loadParametersValue("p.category.comb") + "-"
					+ formatoFecha + "-"
					+ Util.loadParametersValue("PARAM_PREPAGO_DEVOL") + "-"
					+ groupId;

		actualOthersApplications.setPBname(pBname);
		actualOthersApplications.setHeaderProof(headerProof);

		new ActualOthersApplicationsDAO().save(actualOthersApplications);

		connection = ConsultsServiceImpl.insercionContableSinAutocommit(connection,
				tipoMoneda, fechaActual, pCurr, login.trim(), pCategory,
				pSource, null, null, null, pCompany, pAccount, pCcenter,
				pAuxiliary, pEntDr, pEntCr, pBname, PDescription, null,
				headerProof.getHepId().toString(), pAttribute5, pAttribute6,
				pAttribute7, null, pAttribute9, pAttribute10, 
				headerProof.getHepGroupId(), idMaster, idDetail);

		// Volver los saldos de prepago en cero
		if (tipoMovimiento.longValue() == ParametersUtil.DEBITO.longValue()) {
			CostsCenters costsCenters = new CostsCentersDAO()
					.findById(cocCodigo.longValue());
			costsCenters.setValorPrepago(null);
		}

		return connection;

	}

	public void generarComprobanteDevolucionPrepago(String login,
			List<VOCostCenters> listaVOCargaPrepago) throws GWorkException {
		
		Connection connection = null;
		
		try {
			
			int flagHP = -1;
			int flagCredito = 0;
			Period periodo = FlatFileFuelServiceImpl.consultarPeriodoByNovedad(
					new Date(), ParametersUtil.NOVEDAD_COMB);
			Float valorCredito = 0F;
			Float valorDebito = 0F;
			HeaderProof headerProof = null;
			
			connection = ConsultsServiceImpl.getConnection("jdbc/siscarFinanciero");
			
			headerProof = new PrepaidProofBoughtServiceImpl()
					.generarCabeceraComprobante(
							ParametersUtil.COMPRANTE_COMBUSTIBLE, periodo,
							ParametersUtil.TRASACCTION_TYPE, ParametersUtil.DOLAR);

			String idMaster = new ConsultsServiceImpl().getIdMaster();
			Long idDetail = Long.valueOf(0);
			
			for (VOCostCenters cargaPrepago : listaVOCargaPrepago) {
				idDetail++;
				valorDebito = cargaPrepago.getCocValorPrepago();// * -1;
				generarComprobanteDetalle(connection, ParametersUtil.PROOF_TYPE_COMBUSTIBLE,
						login, valorDebito, ParametersUtil.DEBITO, 
						cargaPrepago.getCocNumero().trim(), periodo, headerProof,
						cargaPrepago.getCocCodigo(), "",
						idMaster, idDetail);
				valorCredito = valorCredito + cargaPrepago.getCocValorPrepago();

				flagHP++;
				flagCredito++;
				if (listaVOCargaPrepago.size() == flagCredito) {
					//valorCredito = valorCredito * -1;
					idDetail++;
					connection = generarComprobanteDetalle(connection,
							ParametersUtil.PROOF_TYPE_COMBUSTIBLE, login,
							valorCredito, ParametersUtil.CREDITO, 
							cargaPrepago.getCocNumero().trim(), periodo, headerProof,
							cargaPrepago.getCocCodigo(), "",
							idMaster, idDetail);
				}
			}
			connection = ConsultsServiceImpl.insertTMaster(connection, idMaster, "P", idDetail.intValue());

			try {

				if (headerProof != null)
					EntityManagerHelper.getEntityManager().getTransaction().begin();
				EntityManagerHelper.getEntityManager().getTransaction().commit();

				if (connection != null)
					connection.commit();

			} catch (Exception e) {
				throw new GWorkException(e.getMessage(), e);
			}
			
		} catch (Exception e) {
			throw new GWorkException(e.getMessage(), e);
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
}
