package geniar.siscar.logic.billing.services.impl;

import geniar.siscar.consults.ConsultsService;
import geniar.siscar.consults.impl.ConsultsServiceImpl;
import geniar.siscar.logic.billing.services.GenerateProofService;
import geniar.siscar.logic.billing.services.RentProofService;
import geniar.siscar.logic.consultas.SearchPlainFileParameter;
import geniar.siscar.model.AccountingParameters;
import geniar.siscar.model.CostsCentersVehicles;
import geniar.siscar.model.FlatFile;
import geniar.siscar.model.HeaderProof;
import geniar.siscar.model.Period;
import geniar.siscar.model.PlainFileParameter;
import geniar.siscar.model.Tariffs;
import geniar.siscar.model.VehiclesAssignation;
import geniar.siscar.model.VhaFf;
import geniar.siscar.persistence.FlatFileDAO;
import geniar.siscar.persistence.NoveltyTypesDAO;
import geniar.siscar.persistence.VhaFfDAO;
import geniar.siscar.util.ManipulacionFechas;
import geniar.siscar.util.ParametersUtil;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * The Class RentProofServiceImpl.
 */
public class RentProofServiceImpl implements RentProofService {

	/*
	 * (non-Javadoc)
	 * 
	 * @seegeniar.siscar.logic.billing.services.RentProofService#
	 * generarComprobanteAlquiler(java.sql.Connection, java.lang.Long,
	 * java.lang.String, java.lang.Long, java.util.Date,
	 * geniar.siscar.model.VehiclesAssignation, java.util.Date,
	 * java.lang.Double, java.lang.String, geniar.siscar.model.HeaderProof,
	 * java.util.List)
	 */
	public Connection generarComprobanteAlquiler(Connection connection,
			Long tipoComprobante,
			String login, Long tipoMovimiento, Date fecha,
			VehiclesAssignation vehiclesAssignation,
			Double valorTarifa, String centroCostos, HeaderProof headerProof,
			List<CostsCentersVehicles> listaCostCenters,
			AccountingParameters parameters, String idMaster, Long idDetail) throws GWorkException {
		
		String validarPresupuesto = null;
		Long tipoMoneda = 1L;
		
		GenerateProofService generateProofService = new GenerateProofServiceImpl();
		
		ConsultsService consultsService = new ConsultsServiceImpl();
		Integer anho = new Integer(ManipulacionFechas.getAgno(fecha));

		if (listaCostCenters != null && listaCostCenters.size() > 0) {
			for (CostsCentersVehicles costsCentersVehicles : listaCostCenters) {
				Integer porcentaje = new Integer(costsCentersVehicles
						.getCcrPorcentaje());
				Double valor = (porcentaje / 100) * valorTarifa;
				validarPresupuesto = consultsService.validarPresupuesto(anho,
						costsCentersVehicles.getCostsCenters().getCocNumero()
								.trim().toUpperCase(), "", null, valor);

				if (validarPresupuesto.equalsIgnoreCase("N"))
					throw new GWorkException(Util
							.loadErrorMessageValue("DISPONIBILDADPRESUPUESTAL")
							+ costsCentersVehicles.getCostsCenters()
									.getCocNumero());
			}
		}

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		String placa = vehiclesAssignation.getVehicles()
				.getVhcPlacaDiplomatica();

		String aoaState = ParametersUtil.ESTADO_ACTIVO;
		Long pSob = tipoMoneda;
		Date pAccdate = fecha;
		String pCurr = Util.loadParametersValue("p.curr.dol");
		String pUser = login;
		String pCategory = Util.loadParametersValue("p.category.alq");
		String pSource = Util.loadParametersValue("p.source");
		String pCompany = parameters.getCompany().getCmpNombre();
		String pAccount = parameters.getAccount().getAccNumeroCuenta();
		String pCcenter = null;
		String pAuxiliary = null;
		Float PEntDr = null;
		Float PEntCr = null;
		String pBname = "";
		String PDescription = "";
		String pAttribute5 = "";
		String pAttribute6 = "";
		String pAttribute7 = "";
		String pAttribute9 = parameters.getDocumentTwo().getDocumentTwoType()
				.getDttName();
		String pAttribute10 = ManipulacionFechas.getMes(fecha)
				+ ManipulacionFechas.getDia(fecha);
		String periodo = dateFormat.format(vehiclesAssignation.getRequests()
				.getRqsFechaInicial())
				+ "-"
				+ dateFormat.format(vehiclesAssignation.getRequests().getRqsFechaFinal());
		
		pAuxiliary = vehiclesAssignation.getRequests().getRqsCarnetAsignatario();
		if (tipoMovimiento.longValue() == ParametersUtil.DEBITO.longValue()) {
			PEntDr = valorTarifa.floatValue();
			PDescription = parameters.getDescriptionType().getDstValor() + ":"
					+ placa + " " + Util.loadMessageValue("PERIODO") + periodo
					+ "-" + Util.loadMessageValue("ENTREGA");
			pAttribute5 = parameters.getDocumentOne().getDocumentOneType()
					.getDotName();
			pAttribute6 = placa;
			pAuxiliary = vehiclesAssignation.getRequests().getRqsCarnetAsignatario();
			if (vehiclesAssignation.getRequests().getRequestsClasses()
					.getRqcCodigo().longValue() == ParametersUtil.CLASE_ALQUILER) {
				//pAuxiliary = parameters.getAuxiliar().getAuxValor();
				if (vehiclesAssignation.getRequests().getLegateesTypes()
						.getLgtCodigo().longValue() == ParametersUtil.LGT_CONVENIO.longValue() 
					|| vehiclesAssignation.getRequests().getLegateesTypes()
						.getLgtCodigo().longValue() == ParametersUtil.LGT_PERSONAL.longValue()){
					if(parameters.getAuxiliar().getAuxId().longValue() == ParametersUtil.TipoAuxAsignatario.longValue())
						pAuxiliary = vehiclesAssignation.getRequests().getRqsCarnetAsignatario();
					pCcenter = parameters.getCostsCenters().getCocNumero();
				} else {
					pCcenter = centroCostos;
					//pAuxiliary = parameters.getAuxiliar().getAuxValor();
				}
			} else if (vehiclesAssignation.getRequests().getRequestsClasses()
					.getRqcCodigo().longValue() == ParametersUtil.CLASE_ALQUILER_TERCEROS) {
				pAuxiliary = vehiclesAssignation.getRequests().getRqsCarnetAsignatario();
				pCcenter = parameters.getCostsCenters().getCocNumero();
			}
		}

		if (tipoMovimiento.longValue() == ParametersUtil.CREDITO.longValue()) {
			PEntCr = valorTarifa.floatValue();
			PDescription = parameters.getDescriptionType().getDstValor() + ":"
					+ placa + " " + Util.loadMessageValue("PERIODO") + periodo
					+ "-" + Util.loadMessageValue("ENTREGA");
			pAttribute5 = parameters.getDocumentOne().getDocumentOneType()
					.getDotName();
			pAttribute6 = placa;
			pCcenter = parameters.getCostsCenters().getCocNumero();
			//pAuxiliary = parameters.getAuxiliar().getAuxValor();
		}

		pAttribute7 = vehiclesAssignation.getVhaCodigo().toString();
		
		pBname = pCompany + "-" + pUser + "-" + pCategory + "-"
				+ dateFormat.format(pAccdate) + "-"
				+ Util.loadMessageValue("MENSAJE.ALQUILER.PBNAME") + "-"
//				+ parameters.getDescriptionType().getDstValor() + "-"
				+ headerProof.getHepGroupId();

		connection = generateProofService.generarComprobanteDetalle(connection, aoaState,
				pSob, pAccdate, pCurr, pUser, pCategory, pSource, null, null,
				null, pCompany, pAccount.trim(), pCcenter.trim(), pAuxiliary,
				PEntDr, PEntCr, pBname, PDescription, null, headerProof
						.getHepId().toString(), pAttribute5,
				pAttribute6.trim(), pAttribute7.trim(), null, pAttribute9, pAttribute10
						.trim(), tipoMovimiento, tipoComprobante, parameters,
				headerProof, idMaster, idDetail);
		return connection;
	}

	/* (non-Javadoc)
	 * @see geniar.siscar.logic.billing.services.RentProofService#generarComprobanteDevolucionAlquiler(java.sql.Connection, java.lang.Long, java.lang.String, java.lang.Long, java.util.Date, geniar.siscar.model.VehiclesAssignation, java.lang.Float, java.lang.String, java.lang.String, geniar.siscar.model.HeaderProof)
	 */
	public Connection generarComprobanteDevolucionAlquiler(Connection connection,
			Long tipoComprobante, String login, Long tipoMovimiento,
			Date fecha, VehiclesAssignation vehiclesAssignation, Float valor,
			String CCenter, String msgKMAdicional, HeaderProof headerProof,
			AccountingParameters parameters, String idMaster, Long idDetail)
			throws GWorkException {

		String tipoMoneda = Util.loadParametersValue("p.sob.dolar");
		
		GenerateProofService generateProofService = new GenerateProofServiceImpl();

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		String placa = vehiclesAssignation.getVehicles()
				.getVhcPlacaDiplomatica();
		
		String aoaState = ParametersUtil.ESTADO_ACTIVO;
		Long pSob = new Long(tipoMoneda);
		Date pAccdate = new Date();
		String pCurr = Util.loadParametersValue("p.curr.dol");
		String pUser = login;
		String pCategory = Util.loadParametersValue("p.category.alq");
		String pSource = Util.loadParametersValue("p.source");
		String pCompany = parameters.getCompany().getCmpNombre();
		String pAccount = parameters.getAccount().getAccNumeroCuenta();
		String pCcenter = null;
		String pAuxiliary = null;
		Float PEntDr = null;
		Float PEntCr = null;
		String pBname = "";
		String PDescription = "";
		String pAttribute5 = "";
		String pAttribute6 = "";
		String pAttribute7 = "";
		String pAttribute9 = parameters.getDocumentTwo().getDocumentTwoType()
				.getDttName();
		String pAttribute10 = ManipulacionFechas.getMes(fecha)
				+ ManipulacionFechas.getDia(fecha);
		String periodo = dateFormat.format(vehiclesAssignation
				.getVhaFechaEntrega())
				+ "-" + dateFormat.format(fecha);

		pAuxiliary = vehiclesAssignation.getRequests().getRqsCarnetAsignatario();
		if (tipoMovimiento.longValue() == ParametersUtil.DEBITO.longValue()) {
			PEntDr = valor;
			PDescription = parameters.getDescriptionType().getDstValor() + ":"
					+ placa + " " + Util.loadMessageValue("PERIODO") + periodo
					+ "-" + Util.loadMessageValue("DEVOLUCION") + " "
					+ msgKMAdicional;
			pAttribute5 = parameters.getDocumentOne().getDocumentOneType().getDotName();
			pAttribute6 = placa;

			if (vehiclesAssignation.getRequests().getRequestsClasses().getRqcCodigo().longValue() == ParametersUtil.CLASE_ALQUILER) {
				if (vehiclesAssignation.getRequests().getLegateesTypes().getLgtCodigo().longValue() == ParametersUtil.LGT_CONVENIO.longValue()
						|| vehiclesAssignation.getRequests().getLegateesTypes().getLgtCodigo().longValue() == ParametersUtil.LGT_PERSONAL.longValue()){
					
					if(parameters.getAuxiliar().getAuxId().longValue() == ParametersUtil.TipoAuxAsignatario.longValue())//9 - Terceros
						pAuxiliary = vehiclesAssignation.getRequests().getRqsCarnetAsignatario();
					pCcenter = parameters.getCostsCenters().getCocNumero();
				} else {
					//pAuxiliary = parameters.getAuxiliar().getAuxValor();
					pCcenter = CCenter;
				}
			} else if (vehiclesAssignation.getRequests().getRequestsClasses().getRqcCodigo().longValue() == ParametersUtil.CLASE_ALQUILER_TERCEROS) {
				pAuxiliary = vehiclesAssignation.getRequests().getRqsCarnetAsignatario();
				pCcenter = parameters.getCostsCenters().getCocNumero();
			}
		}

		if (tipoMovimiento.longValue() == ParametersUtil.CREDITO.longValue()) {
			PEntCr = valor;
			PDescription = parameters.getDescriptionType().getDstValor() + ":"
					+ placa + " " + Util.loadMessageValue("PERIODO") + periodo
					+ "-" + Util.loadMessageValue("DEVOLUCION")
					+ msgKMAdicional;
			pAttribute5 = parameters.getDocumentOne().getDocumentOneType().getDotName();
			pAttribute6 = placa;
			pCcenter = parameters.getCostsCenters().getCocNumero();
			//pAuxiliary = parameters.getAuxiliar().getAuxValor();
		}

		pAttribute7 = vehiclesAssignation.getVhaCodigo().toString();
		
		pBname = pCompany + "-" + pUser + "-" + pCategory + "-"
				+ dateFormat.format(pAccdate) + "-"
				+  Util.loadMessageValue("MENSAJE.ALQUILER.PBNAME") + "-"
				+ headerProof.getHepGroupId();
//parameters.getDescriptionType().getDstValor()
		
		connection = generateProofService.generarComprobanteDetalle(connection, aoaState,
				pSob, pAccdate, pCurr, pUser, pCategory, pSource, null, null,
				null, pCompany, pAccount.trim(), pCcenter.trim(), pAuxiliary,
				PEntDr, PEntCr, pBname, PDescription, null, headerProof
						.getHepId().toString(), pAttribute5,
				pAttribute6.trim(), pAttribute7.trim(), null, pAttribute9, pAttribute10
						.trim(), tipoMovimiento, tipoComprobante, parameters,
				headerProof, idMaster, idDetail);

		return connection;
	}

	/**
	 * Generar alquiler nomina.
	 *
	 * @param vehiclesAssignation the vehicles assignation
	 * @param valor the valor
	 * @param fecha the fecha
	 * @param login the login
	 * @param tariffs the tariffs
	 * @throws GWorkException the g work exception
	 */
	public void generarAlquilerNomina(VehiclesAssignation vehiclesAssignation,
			Float valor, Date fecha, String login, Tariffs tariffs)
			throws GWorkException {

		String carne = null;
		Long concepto = null;
		Long moneda = null;
		Long unidades = 1L;
		String documento = null;
		String centroCosto = "";
		String descripcion = null;
		PlainFileParameter plainFileParameter = null;
		Period period = null;
		VhaFf vhaFf = new VhaFf();

		carne = vehiclesAssignation.getRequests().getRqsCarnetAsignatario();
		moneda = new ConsultsServiceImpl()
				.counsultarTipoMonedaAsignatario(carne.trim());

		if (moneda == null)
			throw new GWorkException(Util
					.loadErrorMessageValue("NOMINA.NEXISTE"));
		Float tasaConversion = new ConsultsServiceImpl()
				.consultarTasaConversion(fecha);
		if (moneda.longValue() == ParametersUtil.PESOS)
			valor = valor * tasaConversion;

		plainFileParameter = new SearchPlainFileParameter()
				.consultar_PFP_PorId(moneda, ParametersUtil.NOVEDAD_ALQU);

		concepto = plainFileParameter.getPfpConceptonomina();
		documento = vehiclesAssignation.getVehicles().getVhcPlacaDiplomatica();
		descripcion = plainFileParameter.getPfpDescripcion();
		period = FlatFileFuelServiceImpl.consultarPeriodoByfecha(fecha);
		FlatFile flatFile = new FlatFile();
		String formatoFecha = "yyyyMMdd";
		String ffFecha = new SimpleDateFormat(formatoFecha).format(fecha);

		flatFile.setFfCarne(carne);
		flatFile.setFfFecha(new Long(ffFecha));
		flatFile.setFfValor(valor.toString());
		flatFile.setFfCentroCosto(centroCosto);
		flatFile.setFfDescripcion(descripcion);
		flatFile.setFfConcepto(concepto);
		flatFile.setFfMoneda(moneda);
		flatFile.setFfUnidades(unidades);
		flatFile.setFfDocumento(documento);
		if (period != null)
			flatFile.setPeriod(period);
		flatFile.setNoveltyTypes(new NoveltyTypesDAO()
				.findById(ParametersUtil.NOVEDAD_ALQU));
		flatFile.setFfUsuario(login);
		flatFile.setTariff(tariffs);

		// Relacion del archivo plano con la asignacion
		vhaFf.setFlatFile(flatFile);
		vhaFf.setVehiclesAssignation(vehiclesAssignation);

		try {

			new FlatFileDAO().save(flatFile);
			new VhaFfDAO().save(vhaFf);

		} catch (Exception e) {
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.GUARDAR"));
		}
	}
}