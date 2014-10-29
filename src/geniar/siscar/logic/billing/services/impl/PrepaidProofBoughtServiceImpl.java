package geniar.siscar.logic.billing.services.impl;

import geniar.siscar.logic.billing.services.PrepaidProofBoughtService;
import geniar.siscar.model.AccountingParameters;
import geniar.siscar.model.CurrencyTypes;
import geniar.siscar.model.HeaderProof;
import geniar.siscar.model.Period;
import geniar.siscar.model.ProofState;
import geniar.siscar.model.ProofType;
import geniar.siscar.model.TransactionType;
import geniar.siscar.model.VehiclesAssignation;
import geniar.siscar.persistence.CurrencyTypesDAO;
import geniar.siscar.persistence.HeaderProofDAO;
import geniar.siscar.persistence.ProofStateDAO;
import geniar.siscar.persistence.ProofTypeDAO;
import geniar.siscar.persistence.TransactionTypeDAO;
import geniar.siscar.util.ManipulacionFechas;
import geniar.siscar.util.ParametersUtil;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class PrepaidProofBoughtServiceImpl implements PrepaidProofBoughtService {

	private static final Log log = LogFactory.getLog(PrepaidProofBoughtServiceImpl.class);
	
	public Connection generarComprobanteDetalle(Connection connection, Long tipoComprobante,
			String login, Float valor, Long tipoMovimiento, String centroCosto,
			String placa, Date fecha, VehiclesAssignation vehiclesAssignation,
			Period period, HeaderProof headerProof) throws GWorkException {

		AccountingParameters parameters = new AccountingParameters();

		if (tipoMovimiento.longValue() == ParametersUtil.DEBITO)
			parameters = SearchParametersBilling.consultarParemeter(
					ParametersUtil.DEBITO, ParametersUtil.CARGO_CC,
					ParametersUtil.TRASACCTION_TYPE,
					ParametersUtil.LGT_NO_APLICA);

		else if (tipoMovimiento.longValue() == ParametersUtil.CREDITO)
			parameters = SearchParametersBilling.consultarParemeter(
					ParametersUtil.CREDITO, ParametersUtil.CARGO_CC,
					ParametersUtil.TRASACCTION_TYPE,
					ParametersUtil.LGT_NO_APLICA);

		String aoaState = ParametersUtil.ESTADO_ACTIVO;
		Long pSob = 1L;
		Date pAccdate = fecha;
		String pCurr = Util.loadParametersValue("p.curr.dol");
		String pUser = login;
		String pCategory = Util.loadParametersValue("p.category.comb");
		String pSource = Util.loadParametersValue("p.source");
		String pCompany = parameters.getCompany().getCmpNombre();
		String pAccount = parameters.getAccount().getAccNumeroCuenta();
		String pCcenter = null;
		String pAuxiliary = Util.loadParametersValue("p.auxiliary");
		Float PEntDr = null;
		Float PEntCr = null;
		String pBname = "";
		String PDescription = "";
		String pAttribute5 = "";
		String pAttribute6 = "";
		String pAttribute9 = parameters.getDocumentTwo().getDocumentTwoType()
				.getDttName();
		String pAttribute10 = ManipulacionFechas.getMes(fecha)
				+ ManipulacionFechas.getDia(fecha);
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		
		if (tipoMovimiento.longValue() == ParametersUtil.DEBITO) {

			PEntDr = valor;
			pAttribute5 = parameters.getDocumentOne().getDocumentOneType()
					.getDotName();
			pAttribute6 = placa;
			pCcenter = centroCosto;
			PDescription = Util.loadParametersValue("COMBU_PREPAGO")
					+ placa
					+ " "
					+ vehiclesAssignation.getRequests().getLegateesTypes()
							.getLgtNombre() + " " + dateFormat.format(fecha)
					+ " " + Util.loadParametersValue("p.category.comb");
		}

		if (tipoMovimiento.longValue() == ParametersUtil.CREDITO) {

			PEntCr = valor;
			pAttribute5 = parameters.getDocumentOne().getDocumentOneType()
					.getDotName();
			pAttribute6 = pAttribute10;
			pCcenter = parameters.getCostsCenters().getCocNumero();
			PDescription = Util.loadParametersValue("COMBU_PREPAGO")
					+ placa
					+ " "
					+ vehiclesAssignation.getRequests().getLegateesTypes()
							.getLgtNombre() + " " + dateFormat.format(fecha)
					+ " " + Util.loadParametersValue("p.category.comb");

		}

		pBname = pCompany + "-" + pUser + "-" + pCategory + "-"
				+ dateFormat.format(pAccdate) + "-"
				+ headerProof.getHepGroupId();

		connection = new GenerateProofServiceImpl()
				.generarComprobanteDetallePrepago(connection, aoaState, pSob, pAccdate,
						pCurr, pUser, pCategory, pSource, null, null, null,
						pCompany, pAccount.trim(), pCcenter.trim(), pAuxiliary,
						PEntDr, PEntCr, pBname, PDescription, null, null,
						pAttribute5, pAttribute6.trim(), null, null,
						pAttribute9, pAttribute10.trim(), tipoMovimiento,
						tipoComprobante, parameters, headerProof);

		// Se relacion la cabecera del comprobante con una asignacion
		// existente

		if (vehiclesAssignation != null
				&& tipoMovimiento.longValue() == ParametersUtil.CREDITO)
			new FuelProofInitialServiceImpl()
					.generarAsignacionComprobantePrepago(headerProof,
							vehiclesAssignation, period.getPerId());

		return connection;
	}

	public HeaderProof generarCabeceraComprobante(Long tipoComprobante,
			Period period, Long tipoTransaccion, Long tipoMoneda)
			throws GWorkException {

		HeaderProof headerProof = new HeaderProof();

		ProofState proofState = new ProofStateDAO()
				.findById(ParametersUtil.ACTIVO);

		if (proofState == null)
			throw new GWorkException(Util.loadErrorMessageValue(""));

		headerProof.setProofState(proofState);

		ProofType proofType = new ProofTypeDAO().findById(tipoComprobante);

		if (proofType == null)
			throw new GWorkException(Util.loadErrorMessageValue(""));

		headerProof.setProofType(proofType);

		TransactionType transactionType = new TransactionTypeDAO()
				.findById(tipoTransaccion);

		if (transactionType == null)
			throw new GWorkException(Util.loadErrorMessageValue(""));

		if (period == null)
			throw new GWorkException(Util.loadErrorMessageValue(""));

		headerProof.setPeriod(period);

		headerProof.setTransactionType(transactionType);

		CurrencyTypes currencyTypes = new CurrencyTypesDAO()
				.findById(tipoMoneda);

		if (currencyTypes == null)
			throw new GWorkException(Util.loadErrorMessageValue(""));

		Date fechaActual = new Date();

		String fechaGroupId = ManipulacionFechas.getAgno(fechaActual)
				+ ManipulacionFechas.getMes(fechaActual)
				+ ManipulacionFechas.getDia(fechaActual);

		String groupId = fechaGroupId + "18" + transactionType.getTstId();

		headerProof.setHepGroupId(groupId);
		headerProof.setCurrencyTypes(currencyTypes);

		HeaderProof headerProofFinal = new HeaderProofDAO().update(headerProof);

		//String nuevoGroupId = groupId + headerProofFinal.getHepId();
		String nuevoGroupId = headerProofFinal.getHepId().toString().trim();
		headerProofFinal.setHepGroupId(nuevoGroupId);

		try {
			new HeaderProofDAO().update(headerProofFinal);

		} catch (Exception e) {
			log.error("generarCabeceraComprobante", e);
		}
		return headerProofFinal;

	}

	public static String generarGroupId() throws GWorkException {

		Long idHP = SearchParametersBilling.lastHeaderProof();
		return idHP.toString();
	}

}
