package geniar.siscar.logic.billing.services.impl;

import geniar.siscar.consults.impl.ConsultsServiceImpl;
import geniar.siscar.logic.billing.services.FuelProofInitialService;
import geniar.siscar.logic.consultas.SearchCostCenters;
import geniar.siscar.model.AccountingParameters;
import geniar.siscar.model.ActualOthersApplications;
import geniar.siscar.model.CargaPrepago;
import geniar.siscar.model.CostCentersFuel;
import geniar.siscar.model.CostsCenters;
import geniar.siscar.model.CurrencyTypes;
import geniar.siscar.model.HeaderProof;
import geniar.siscar.model.Period;
import geniar.siscar.model.Prepaid;
import geniar.siscar.model.ProofState;
import geniar.siscar.model.ProofType;
import geniar.siscar.model.TransactionType;
import geniar.siscar.model.VOPrepagoInicial;
import geniar.siscar.model.VehiclesAssignation;
import geniar.siscar.model.VhaAoaApp;
import geniar.siscar.persistence.AccountingParametersDAO;
import geniar.siscar.persistence.ActualOthersApplicationsDAO;
import geniar.siscar.persistence.CostCentersFuelDAO;
import geniar.siscar.persistence.CostsCentersDAO;
import geniar.siscar.persistence.CurrencyTypesDAO;
import geniar.siscar.persistence.EntityManagerHelper;
import geniar.siscar.persistence.HeaderProofDAO;
import geniar.siscar.persistence.PeriodDAO;
import geniar.siscar.persistence.PrepaidDAO;
import geniar.siscar.persistence.ProofStateDAO;
import geniar.siscar.persistence.ProofTypeDAO;
import geniar.siscar.persistence.TransactionTypeDAO;
import geniar.siscar.persistence.VhaAoaAppDAO;
import geniar.siscar.util.ManipulacionFechas;
import geniar.siscar.util.ParametersUtil;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

import java.sql.Connection;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class FuelProofInitialServiceImpl implements FuelProofInitialService {

	private static final Long ACTIVO = 1L;
	private static final Long DEBITO = 1L;
	private static final Long CREDITO = 2L;
	
	public List<CargaPrepago> listaCargaPrepago() throws GWorkException {
		List<CargaPrepago> listaCargaPrepago = new SearchParametersBilling()
				.listaCargaPrepago();
		if (listaCargaPrepago == null || listaCargaPrepago.size() == 0
				|| listaCargaPrepago.isEmpty())
			throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));

		return listaCargaPrepago;
	}

	public boolean validarDisponibilidadCCPrepago(String centroCosto,
			Float valor) throws GWorkException {

		boolean validaCentroCosto = false;

		Calendar calendario = Calendar.getInstance();
		calendario.setTime(new Date());
		int anho = calendario.get(Calendar.YEAR);

		String cuenta = new AccountingParametersDAO().findById(15L)
				.getAccount().getAccNumeroCuenta();

		validaCentroCosto = new ConsultsServiceImpl()
				.consultarDisponibiladaPresupuestal(anho, centroCosto, cuenta,
						null, valor.doubleValue());

		return validaCentroCosto;
	}

	/*
	 * Genera la cabecera del comprobante de prepago
	 */
	public HeaderProof generarCabeceraComprobante(Long tipoComprobante,
			Period period, Long tipoMovimiento, Long tipoMoneda,
			AccountingParameters parameters) throws GWorkException {

		HeaderProof headerProof = new HeaderProof();

		ProofState proofState = new ProofStateDAO().findById(ACTIVO);

		if (proofState == null)
			throw new GWorkException(Util.loadErrorMessageValue(""));

		headerProof.setProofState(proofState);

		ProofType proofType = new ProofTypeDAO().findById(tipoComprobante);

		if (proofType == null)
			throw new GWorkException(Util.loadErrorMessageValue(""));

		headerProof.setProofType(proofType);

		TransactionType transactionType = new TransactionTypeDAO()
				.findById(tipoMovimiento);

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

		String groupId = fechaGroupId + "18"
				+ parameters.getTransactionType().getTstId();

		headerProof.setHepGroupId(groupId);
		headerProof.setCurrencyTypes(currencyTypes);

		
		EntityManagerHelper.getEntityManager().getTransaction().begin();
		new HeaderProofDAO().save(headerProof);
		EntityManagerHelper.getEntityManager().getTransaction().commit();

		headerProof = SearchParametersBilling.findHPById(new Long(
				generarGroupId()));

		EntityManagerHelper.getEntityManager().refresh(headerProof);
		String nuevoGroupId = groupId + generarGroupId();
		headerProof.setHepGroupId(nuevoGroupId);

		try {
						EntityManagerHelper.getEntityManager().getTransaction().begin();
			new HeaderProofDAO().update(headerProof);
			EntityManagerHelper.getEntityManager().getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return headerProof;

	}

	public Connection generarComprobanteDetalle(Connection connection,
			Long tipoComprobante,
			String login, Double valor, Long tipoMovimiento,
			String centroCosto, String placa, Period period,
			String tipoCombustible, HeaderProof headerProof,
			CostCentersFuel costCentersFuel,
			VehiclesAssignation vehiclesAssignation) throws GWorkException {

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

		if (tipoMovimiento.longValue() == DEBITO.longValue())
			pCcenter = centroCosto;

		if (tipoMovimiento.longValue() == CREDITO.longValue())
			pCcenter = centroCostoDebi;

		actualOthersApplications.setPCcenter(pCcenter);

		String pAuxiliary = Util.loadParametersValue("p.auxiliary");
		actualOthersApplications.setPAuxiliary(pAuxiliary);

		Float pEntDr = null;
		Float pEntCr = null;

		if (tipoMovimiento.longValue() == DEBITO.longValue())
			pEntDr = valor.floatValue();

		if (tipoMovimiento.longValue() == CREDITO.longValue())
			pEntCr = valor.floatValue();

		actualOthersApplications.setPEntDr(pEntDr);
		actualOthersApplications.setPEntCr(pEntCr);

		if (tipoMovimiento.longValue() == DEBITO.longValue())

			parameters = SearchParametersBilling.consultarParemeter(
					ParametersUtil.DEBITO, ParametersUtil.CARGO_CC,
					ParametersUtil.TRASACCTION_TYPE,
					ParametersUtil.LGT_NO_APLICA);

		if (tipoMovimiento.longValue() == CREDITO.longValue())
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
		String tipoAsignacion = null;
		System.out.println(placa);

		if (vehiclesAssignation.getRequests().getLegateesTypes() != null)
			tipoAsignacion = vehiclesAssignation.getRequests()
					.getLegateesTypes().getLgtNombre();

		if (tipoMovimiento.longValue() == DEBITO)
			PDescription = Util.loadParametersValue("COMBU_PREPAGO") + placa
					+ " " + tipoAsignacion + " " + period.getPerNombre();

		if (tipoMovimiento.longValue() == CREDITO)
			PDescription = parameters.getDescriptionType().getDstValor()
					+ ManipulacionFechas.getAgno(fechaActual) + " "
					+ Util.loadParametersValue("p.category.comb");

		actualOthersApplications.setPDescription(PDescription);

		// no veo este valor en la tabla accountig_parameters
		actualOthersApplications.setPNit(null);
		String pAttribute7 = vehiclesAssignation.getVhaCodigo().toString()
				.trim();

		actualOthersApplications.setPAttribute2(null);

		String pAttribute5 = parameters.getDocumentOne().getDocumentOneType()
				.getDotName();
		actualOthersApplications.setPAttribute5(pAttribute5);
		actualOthersApplications.setPAttribute7(pAttribute7);
		actualOthersApplications.setPAttribute8(null);
		String pAttribute9 = parameters.getDocumentTwo().getDocumentTwoType()
				.getDttName();
		actualOthersApplications.setPAttribute9(pAttribute9);

		String pAttribute10 = ManipulacionFechas.getMes(fechaActual)
				+ ManipulacionFechas.getDia(fechaActual);
		actualOthersApplications.setPAttribute10(pAttribute10);

		String pAttribute6 = null;

		if (tipoMovimiento.longValue() == DEBITO.longValue())
			pAttribute6 = placa;

		if (tipoMovimiento.longValue() == CREDITO.longValue())
			pAttribute6 = pAttribute10;

		actualOthersApplications.setPAttribute6(pAttribute6);
		actualOthersApplications.setAoaState("ACTIVO");

		// Se construye el PBName
		String pBname = null;
		String groupId = headerProof.getHepGroupId();

		if (tipoMovimiento.longValue() == DEBITO.longValue())
			pBname = pCompany + "-" + login + "-" + pSource + "-"
					+ Util.loadParametersValue("p.category.comb") + "-"
					+ formatoFecha + "-"
					+ Util.loadParametersValue("PARAM_PREPAGO") + "-" + groupId;

		if (tipoMovimiento.longValue() == CREDITO.longValue())
			pBname = pCompany + "-" + login + "-" + pSource + "-"
					+ Util.loadParametersValue("p.category.comb") + "-"
					+ formatoFecha + "-"
					+ Util.loadParametersValue("PARAM_PREPAGO") + "-" + groupId;

		actualOthersApplications.setPBname(pBname);
		actualOthersApplications.setHeaderProof(headerProof);

		new ActualOthersApplicationsDAO().save(actualOthersApplications);

		connection = ConsultsServiceImpl.insercionContableSinAutocommit(connection,
				tipoMoneda, fechaActual, pCurr, login.trim(), pCategory,
				pSource, null, null, null, pCompany, pAccount, pCcenter,
				pAuxiliary, pEntDr, pEntCr, pBname, PDescription, null,
				headerProof.getHepId().toString(), pAttribute5, pAttribute6,
				pAttribute7, null, pAttribute9, pAttribute10, headerProof
						.getHepGroupId());

		if (tipoMovimiento.longValue() == DEBITO.longValue()) {
			generarCompraPrepago(
					placa,
					period.getPerFechaIni(),
					period.getPerFechaFin(),
					valor,
					headerProof,
					centroCosto,
					vehiclesAssignation.getRequests().getRqsCarnetAsignatario(),
					costCentersFuel);

			// Se relacion la cabecera del comprobante con una asignacion
			// existente

			if (vehiclesAssignation != null)
				generarAsignacionComprobante(headerProof, vehiclesAssignation,
						period);
			else
				throw new GWorkException(Util
						.loadErrorMessageValue("PLACASINASIGNACION")
						+ placa.trim().toUpperCase());
		}
		return connection;

	}

	public void generarComprobante(Long tipoComprobante, String login,
			Long idPeriodo, List<VOPrepagoInicial> listaVOCargaPrepago)
			throws GWorkException {
		
		Connection connection = null;
		
		try {
			
			int flagHP = -1;
			int flagCredito = 0;
			Period periodo = new PeriodDAO().findById(idPeriodo);
			Double valorCredito = 0D;
			HeaderProof headerProof = null;
			
			connection = ConsultsServiceImpl.getConnection("jdbc/siscarFinanciero");
			
			headerProof = new PrepaidProofBoughtServiceImpl()
					.generarCabeceraComprobante(
							ParametersUtil.COMPRANTE_COMBUSTIBLE, periodo,
							ParametersUtil.TRASACCTION_TYPE, ParametersUtil.DOLAR);

			for (VOPrepagoInicial cargaPrepago : listaVOCargaPrepago) {

				generarComprobanteDetalle(connection, tipoComprobante, login, cargaPrepago
						.getValorPrepago(), DEBITO, cargaPrepago.getCentroCosto()
						.trim(), cargaPrepago.getPlaca().trim(), periodo,
						cargaPrepago.getTipoCombustible().trim(), headerProof,
						cargaPrepago.getCostCentersFuel(), cargaPrepago
								.getVehiclesAssignation());
				valorCredito = valorCredito + cargaPrepago.getValorPrepago();
				
				flagHP++;
				flagCredito++;
				if (listaVOCargaPrepago.size() == flagCredito) {

					connection = generarComprobanteDetalle(connection, tipoComprobante, login,
							valorCredito, CREDITO, cargaPrepago.getCentroCosto()
									.trim(), cargaPrepago.getPlaca().trim(),
							periodo, cargaPrepago.getTipoCombustible().trim(),
							headerProof, cargaPrepago.getCostCentersFuel(),
							cargaPrepago.getVehiclesAssignation());

				}

			}
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

	public static String generarGroupId() throws GWorkException {

		Long idHP = SearchParametersBilling.lastHeaderProof();
		return idHP.toString();
	}

	public void generarCompraPrepago(String placa, Date fechaIni,
			Date fechaFin, Double valorCompra, HeaderProof headerProof,
			String centroCosto, String carne, CostCentersFuel costCentersFuel)
			throws GWorkException {

		Prepaid prepaid = new Prepaid();
		prepaid.setPrePlaca(placa.trim());
		prepaid.setPreFechaFin(fechaFin);
		prepaid.setPreFechaini(fechaIni);
		prepaid.setHeaderProof(headerProof);
		prepaid.setPreAsignatario(carne);
		prepaid.setPreTotal(valorCompra.floatValue());
		prepaid.setCostCentersFuel(costCentersFuel);

		new PrepaidDAO().save(prepaid);

		CostsCenters costsCenters = SearchCostCenters
				.consultarCentroCosto(centroCosto.trim());

		Float valorPrepago = 0F;

		if (costsCenters.getValorPrepago() == null)
			valorPrepago = valorPrepago + new Float(valorCompra);
		else
			valorPrepago = costsCenters.getValorPrepago()
					+ new Float(valorCompra);

		costsCenters.setValorPrepago(valorPrepago);

		new CostsCentersDAO().update(costsCenters);
		new CostCentersFuelDAO().update(costCentersFuel);

	}

	public void generarAsignacionComprobante(HeaderProof headerProof,
			VehiclesAssignation vehiclesAssignation, Period periodo) {

		VhaAoaApp asignacionComprobante = new VhaAoaApp();
		asignacionComprobante.setHeaderProof(headerProof);
		asignacionComprobante.setVehiclesAssignation(vehiclesAssignation);
		asignacionComprobante.setAoaFechaIni(periodo.getPerFechaIni());
		asignacionComprobante.setAoaFechaFin(periodo.getPerFechaFin());
		new VhaAoaAppDAO().save(asignacionComprobante);

	}

	public void generarAsignacionComprobantePrepago(HeaderProof headerProof,
			VehiclesAssignation vehiclesAssignation, Long periodo) {

		Period period = new PeriodDAO().findById(periodo);
		VhaAoaApp asignacionComprobante = new VhaAoaApp();
		asignacionComprobante.setHeaderProof(headerProof);
		asignacionComprobante.setVehiclesAssignation(vehiclesAssignation);
		asignacionComprobante.setAoaFechaIni(period.getPerFechaIni());
		asignacionComprobante.setAoaFechaFin(period.getPerFechaFin());
		new VhaAoaAppDAO().save(asignacionComprobante);
	}

	public static void main(String[] args) {

	}
}
