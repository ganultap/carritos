package geniar.siscar.logic.billing.services.impl;

import geniar.siscar.consults.impl.ConsultsServiceImpl;
import geniar.siscar.logic.billing.services.BudgetFuelProofService;
import geniar.siscar.logic.consultas.SearchVehicles;
import geniar.siscar.model.AccountingParameters;
import geniar.siscar.model.ActualOthersApplications;
import geniar.siscar.model.FuelTypeRequest;
import geniar.siscar.model.HeaderProof;
import geniar.siscar.model.Period;
import geniar.siscar.model.VehiclesAssignation;
import geniar.siscar.persistence.ActualOthersApplicationsDAO;
import geniar.siscar.util.ManipulacionFechas;
import geniar.siscar.util.ParametersUtil;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

import java.sql.Connection;
import java.util.Date;

public class BudgetFuelProofServiceImpl implements BudgetFuelProofService {

	public Connection generarComprobanteDetalle(Connection connection, String carne,
			Long tipoComprobante, String login, Float valor,
			Long tipoMovimiento, String centroCosto, Long tipoCargo,
			String placa, Date fecha, Float galones,
			FuelTypeRequest fuelTypeRequest, Period periodo,
			HeaderProof headerProof, String idMaster, Long idDetail) throws GWorkException {

		ActualOthersApplications actualOthersApplications = new ActualOthersApplications();
		// Date fechaActual = new Date();
		AccountingParameters parameters = new AccountingParameters();
		VehiclesAssignation vehiclesAssignation = null;
		Long tipoMoneda = null;
		
		// Seleccione el tipo de moneda que se ingresa en el comprobante USD o
		// PESO
		// tipoMoneda = consultsService.counsultarTipoMonedaAsignatario(carne);

		tipoMoneda = 1L;
		String pCurr = null;

		if (tipoMoneda == 1L)
			pCurr = Util.loadParametersValue("p.curr.dol");

		if (tipoMoneda == 2L)
			pCurr = Util.loadParametersValue("p.curr.ps");

		// Consultar si se tiene alguna asignacion para la placa ingresada

		vehiclesAssignation = SearchVehicles.consultarAsignacionVehiculo(placa
				.trim().toUpperCase());

		String pCategory = Util.loadParametersValue("p.category.comb");

		String pCcenter = null;

		Float pEntDr = null;
		Float pEntCr = null;

		if (tipoMovimiento.longValue() == ParametersUtil.DEBITO.longValue())
			pEntDr = valor;

		if (tipoMovimiento.longValue() == ParametersUtil.CREDITO.longValue())
			pEntCr = valor;

		/*
		 * PARAMETRIZACION CONTABLE DEBITO TERCEROS
		 */
		if (tipoMovimiento.longValue() == ParametersUtil.DEBITO.longValue()
				&& tipoCargo.longValue() == ParametersUtil.CARGO_TERCEROS) {
			parameters = SearchParametersBilling.consultarParemeter(
					ParametersUtil.DEBITO, tipoCargo,
					ParametersUtil.TRASACCTION_TYPE,
					ParametersUtil.LGT_NO_APLICA);
			pCcenter = parameters.getCostsCenters().getCocNumero();
		}
		/*
		 * PARAMETRIZACION CONTABLE DEBITO CENTROS DE COSTOS
		 */
		else if (tipoMovimiento.longValue() == ParametersUtil.DEBITO
				.longValue()
				&& tipoCargo.longValue() == ParametersUtil.CARGO_CC) {
			parameters = SearchParametersBilling.consultarParemeter(
					ParametersUtil.DEBITO, tipoCargo,
					ParametersUtil.TRASACCTION_TYPE,
					ParametersUtil.LGT_NO_APLICA);
			pCcenter = centroCosto;
		}
		/*
		 * PARAMETRIZACION CONTABLE CREDITO TERCEROS
		 */
		if (tipoMovimiento.longValue() == ParametersUtil.CREDITO.longValue()
				&& tipoCargo.longValue() == ParametersUtil.CARGO_TERCEROS) {
			parameters = SearchParametersBilling.consultarParemeter(
					ParametersUtil.CREDITO, tipoCargo,
					ParametersUtil.TRASACCTION_TYPE,
					ParametersUtil.LGT_NO_APLICA);
			pCcenter = parameters.getCostsCenters().getCocNumero();
		}
		/*
		 * PARAMETRIZACION CONTABLE CREDITO CENTROS DE COSTOS
		 */
		else if (tipoMovimiento.longValue() == ParametersUtil.CREDITO
				.longValue()
				&& tipoCargo.longValue() == ParametersUtil.CARGO_CC) {
			parameters = SearchParametersBilling.consultarParemeter(
					ParametersUtil.CREDITO, tipoCargo,
					ParametersUtil.TRASACCTION_TYPE,
					ParametersUtil.LGT_NO_APLICA);
			pCcenter = parameters.getCostsCenters().getCocNumero();
		}

		String formatoFecha = ManipulacionFechas.getDia(fecha) + "-"
				+ ManipulacionFechas.getNombreMes(fecha) + "-"
				+ ManipulacionFechas.getAgno(fecha);

		String PDescription = null;
		String tipoAsignacion = null;

		if (tipoMovimiento.longValue() == ParametersUtil.CREDITO.longValue())
			pCcenter = parameters.getCostsCenters().getCocNumero();

		// Period periodo =
		// FlatFileFuelServiceImpl.consultarPeriodoByfecha(fecha);

		if (vehiclesAssignation != null
				&& vehiclesAssignation.getRequests().getLegateesTypes() != null)
			tipoAsignacion = vehiclesAssignation.getRequests()
					.getLegateesTypes().getLgtNombre();

		if (tipoMovimiento.longValue() == ParametersUtil.DEBITO) {
			String placaDec = null;
			if (tipoAsignacion == null)
				tipoAsignacion = "";

			if (placa == null || placa.trim().length() == 0)
				placaDec = fuelTypeRequest.getFtrNombre();
			else if (placa != null && placa.trim().length() > 0)
				placaDec = placa;

			PDescription = Util.loadParametersValue("COMBU_PRESUPUESTO")
					+ placaDec + " " + tipoAsignacion + " "
					+ Util.loadMessageValue("GALONES") + galones + " "
					+ Util.loadMessageValue("FECHA") + formatoFecha;
		}

		if (tipoMovimiento.longValue() == ParametersUtil.CREDITO)
			PDescription = Util.loadParametersValue("COBRO_PRESUPUESTO") + " "
					+ formatoFecha + " "
					+ Util.loadParametersValue("p.category.comb");

		// Se cargan los parametros de entrada

		String pAttribute2 = null;

		if (vehiclesAssignation != null)
			vehiclesAssignation.getVhaCodigo().toString();

		String pAttribute9 = parameters.getDocumentTwo().getDocumentTwoType()
				.getDttName();
		String pAttribute10 = ManipulacionFechas.getMes(fecha)
				+ ManipulacionFechas.getDia(fecha);
		String pCompany = parameters.getCompany().getCmpNombre();
		String pAccount = parameters.getAccount().getAccNumeroCuenta();
		String pSource = Util.loadParametersValue("p.source");
		String pAuxiliary = null;
		String pAttribute5 = null;

		// // Se construye la cabecera del comprobante
		// if (tipoMovimiento.longValue() == ParametersUtil.DEBITO.longValue())
		// headerProof = new FuelProofInitialServiceImpl()
		// .generarCabeceraComprobante(tipoComprobante, periodo,
		// ParametersUtil.TRASACCTION_TYPE, tipoMoneda,
		// parameters);
		//
		// if (tipoMovimiento.longValue() == ParametersUtil.CREDITO.longValue())
		// headerProof = SearchParametersBilling
		// .consultarCabeceraCmprbntByGroupId(SearchParametersBilling
		// .lastHeaderProof());

		if (tipoMovimiento.longValue() == ParametersUtil.DEBITO
				&& tipoCargo.longValue() == ParametersUtil.CARGO_TERCEROS)
			pAuxiliary = carne;
		else
			pAuxiliary = Util.loadParametersValue("p.auxiliary");

		// Se construye el PBName
		String pBname = null;
		String groupId = headerProof.getHepGroupId();

		if (tipoMovimiento.longValue() == ParametersUtil.DEBITO.longValue())
			pBname = pCompany + "-" + login + "-" + pSource + "-"
					+ Util.loadParametersValue("p.category.comb") + " "
					+ formatoFecha + "-" + groupId;

		if (tipoMovimiento.longValue() == ParametersUtil.CREDITO.longValue())
			pBname = pCompany + "-" + login + "-" + pSource + "-"
					+ Util.loadParametersValue("p.category.comb") + " "
					+ formatoFecha + "-" + groupId;

		String pAttribute6 = null;

		if (tipoMovimiento.longValue() == ParametersUtil.DEBITO.longValue()
				&& placa != null && placa.trim().length() > 0) {
			pAttribute5 = parameters.getDocumentOne().getDocumentOneType()
					.getDotName();
			pAttribute6 = placa;
		} else if (tipoMovimiento.longValue() == ParametersUtil.DEBITO
				.longValue()
				&& (placa == null || placa.trim().length() == 0)) {

			pAttribute5 = parameters.getDocumentTwo().getDocumentTwoType()
					.getDttName();
			pAttribute6 = pAttribute10;
		} else if (tipoMovimiento.longValue() == ParametersUtil.CREDITO
				.longValue()) {
			pAttribute6 = pAttribute10;
			pAttribute5 = parameters.getDocumentOne().getDocumentOneType()
					.getDotName();

		}

		// Se cargan los datos del objeto actuaOtherApplication

		actualOthersApplications.setPSob(tipoMoneda);
		actualOthersApplications.setPCurr(pCurr);
		actualOthersApplications.setPCcenter(pCcenter);
		actualOthersApplications.setPAuxiliary(pAuxiliary);
		actualOthersApplications.setPAccdate(fecha);
		actualOthersApplications.setPUser(login);
		actualOthersApplications.setPCompany(pCompany);
		actualOthersApplications.setPAccount(pAccount);
		actualOthersApplications.setPEntDr(pEntDr);
		actualOthersApplications.setPEntCr(pEntCr);
		actualOthersApplications.setPCategory(pCategory);
		actualOthersApplications.setPSource(pSource);
		actualOthersApplications.setPConvDate(null);
		actualOthersApplications.setPConvRate(null);
		actualOthersApplications.setPConvType(null);
		actualOthersApplications.setPDescription(PDescription);
		actualOthersApplications.setPAttribute2(pAttribute2);
		actualOthersApplications.setPNit(null);
		actualOthersApplications.setPAttribute5(pAttribute5);
		actualOthersApplications.setPAttribute6(pAttribute6);
		if (vehiclesAssignation == null) {
			actualOthersApplications.setPAttribute7(null);
		} else {
			actualOthersApplications.setPAttribute7(vehiclesAssignation
					.getVhaCodigo().toString());
		}
		actualOthersApplications.setPAttribute8(null);
		actualOthersApplications.setPAttribute9(pAttribute9);
		actualOthersApplications.setPAttribute10(pAttribute10);
		actualOthersApplications.setAoaState("ACTIVO");
		actualOthersApplications.setPBname(pBname);
		actualOthersApplications.setHeaderProof(headerProof);

		connection = ConsultsServiceImpl.insercionContableSinAutocommit(connection,
				tipoMoneda, fecha, pCurr, login, pCategory, pSource, null,
				null, null, pCompany, pAccount, pCcenter, pAuxiliary, pEntDr,
				pEntCr, pBname, PDescription, null, pAttribute2, pAttribute5,
				pAttribute6, null, null, pAttribute9, pAttribute10, 
				headerProof.getHepGroupId(),idMaster, idDetail);

		if (connection != null) {
			new ActualOthersApplicationsDAO().save(actualOthersApplications);
			return connection;

		} else if (connection == null)
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.GUARDAR"));
		return null;
	}
}