package geniar.siscar.logic.billing.services.impl;

import geniar.siscar.consults.ConsultsService;
import geniar.siscar.consults.impl.ConsultsServiceImpl;
import geniar.siscar.logic.billing.services.GenerateInvoicesProofInsurance;
import geniar.siscar.logic.consultas.SearchCostCenters;
import geniar.siscar.logic.consultas.SearchVehicles;
import geniar.siscar.model.AccountingParameters;
import geniar.siscar.model.CostsCentersVehicles;
import geniar.siscar.model.Inconsistencies;
import geniar.siscar.model.InvoiceDetail;
import geniar.siscar.model.InvoiceHeader;
import geniar.siscar.model.InvoiceType;
import geniar.siscar.model.InvoicesHeaderPoliciesVO;
import geniar.siscar.model.PoliciesInvoice;
import geniar.siscar.model.PoliciesVehicles;
import geniar.siscar.model.VehiclesAssignation;
import geniar.siscar.persistence.EntityManagerHelper;
import geniar.siscar.persistence.InvoiceDetailDAO;
import geniar.siscar.persistence.InvoiceHeaderDAO;
import geniar.siscar.persistence.PoliciesInvoiceDAO;
import geniar.siscar.util.ParametersUtil;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

import java.sql.Connection;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;

public class GenerateInvoicesProofInsuranceImpl implements
		GenerateInvoicesProofInsurance {

	private static String strFactura = null;
	private static String strDescripcionFactura = null;
	private static String strPlacaDiplomatica = null;
	private static String TipoFactura = null;
	private static String NitProveedor = null;
	private static String MonedaFactura = null;
	private static String codigoCentroCosto = null;
	private static String ConvType = null;
	private static String Source = null;
	private static String codigoProveedor = null;
	private static Long idAuxiliar = null;
	private static String Carnet = null;
	private static Date dtFechaFactura = null;
	private static Long ConvRate = null;
	private static Long idTipoAsignacion = null;
	private static Long idTipoTransaccion = null;
	private static Long idTipoMovimiento = null;
	private static Long Cargo = null;
	private static Long NumeroFilas = null;
	private static Long ValorTotalFactura = null;
	private static List<CostsCentersVehicles> listaCostCenters = null;
	private static ConsultsService consultsService = new ConsultsServiceImpl();
	private static InvoiceHeader invoiceHeader = new InvoiceHeader();
	private static InvoiceDetail invoiceDetail = new InvoiceDetail();
	private static InvoicesHeaderPoliciesVO InsercionContableVO = null;
	private static AccountingParameters accountingParameters = null;
	//private static Connection ConeccionInsercionDetalleFactura = null;

	public static void ResetearVariables() {
		strFactura = null;
		strDescripcionFactura = null;
		strPlacaDiplomatica = null;
		TipoFactura = null;
		NitProveedor = null;
		MonedaFactura = null;
		codigoCentroCosto = null;
		ConvType = null;
		Source = null;
		codigoProveedor = null;
		idAuxiliar = null;
		Carnet = null;
		dtFechaFactura = null;
		ConvRate = null;
		idTipoAsignacion = null;
		idTipoTransaccion = null;
		idTipoMovimiento = null;
		Cargo = null;
		NumeroFilas = null;
		ValorTotalFactura = null;
		listaCostCenters = null;
		consultsService = new ConsultsServiceImpl();
		invoiceHeader = new InvoiceHeader();
		invoiceDetail = new InvoiceDetail();
		InsercionContableVO = null;
		accountingParameters = null;
		//ConeccionInsercionDetalleFactura = null;
	}

	/**
	 * Genera la cabecera del comprobante de facturas
	 * 	 
	 * @param InvoiceNum
	 * @param TipoFactura
	 * @param InvoiceDate
	 * @param Nit
	 * @param InvoiceAmount
	 * @param FechaRcvFactura
	 * @param MonedaFactura
	 * @param ConvType
	 * @param ConvDateDate
	 * @param ConvRate
	 * @param Description
	 * @param User
	 * @param Source
	 * @param IntCodigo
	 * @param idPInvoice
	 * @return
	 * @throws GWorkException
	 */
	public InvoiceHeader generarCabeceraComprobanteFacturas(String InvoiceNum,
			String TipoFactura, Date InvoiceDate, String Nit,
			Float InvoiceAmount, Date FechaRcvFactura, String MonedaFactura,
			String ConvType, Date ConvDateDate, Long ConvRate,
			String Description, String User, String Source,
			InvoiceType IntCodigo, Long idPInvoice) throws GWorkException {

		InvoiceHeader invoiceHeader = new InvoiceHeader();
		PoliciesInvoice policiesInvoice = new PoliciesInvoice();
		policiesInvoice = new PoliciesInvoiceDAO().findById(idPInvoice);

		invoiceHeader.setPInvoiceNum(InvoiceNum);
		invoiceHeader.setPTipoFactura(TipoFactura);
		invoiceHeader.setPInvoiceDate(InvoiceDate);
		invoiceHeader.setPNit(Nit);
		invoiceHeader.setPInvoiceAmount(InvoiceAmount);
		invoiceHeader.setPFechaRcvFactura(FechaRcvFactura);
		invoiceHeader.setPMonedaFactura(MonedaFactura);
		invoiceHeader.setPConvType(ConvType);
		invoiceHeader.setPConvDateDate(ConvDateDate);
		invoiceHeader.setPConvRate(ConvRate);
		invoiceHeader.setPDescription(Description);
		invoiceHeader.setPUser(User);
		invoiceHeader.setPSource(Source);
		invoiceHeader.setInvoiceType(IntCodigo);
		invoiceHeader.setPoliciesInvoice(policiesInvoice);

		try {
			// EntityManagerHelper.getEntityManager().getTransaction().begin();
			new InvoiceHeaderDAO().save(invoiceHeader);
			// EntityManagerHelper.getEntityManager().getTransaction().commit();

		} catch (Exception e) {
			throw new GWorkException(e.getMessage(), e);
		}
		return invoiceHeader;
	}

	/*
	 * Genera el detalle del comprobante de facturas
	 */
	public InvoiceDetail generarDetalleComprobanteFacturas(
			InvoicesHeaderPoliciesVO InsercionContableVO, Long numeroFilas,
			String strFactura, Date dtFechaFactura, Long ValorTotalPoliza,
			String strPlacaDiplomatica,
			AccountingParameters accountingParameters,
			String codigoCentroCosto, InvoiceHeader invoiceHeader,
			String Carnet, String nit, String Descripcion)
			throws GWorkException {

		InvoiceDetail invoiceDetail = new InvoiceDetail();
		invoiceDetail
				.setPInvoiceId(InsercionContableVO.getLgSecuenciaFactura());
		invoiceDetail.setPLineNum(numeroFilas);
		invoiceDetail.setPInvoiceNum(strFactura);
		invoiceDetail.setPInvoiceDate(dtFechaFactura);
		invoiceDetail.setPInvoiceAmount(ValorTotalPoliza.longValue());
		invoiceDetail.setPDescription(Descripcion);
		invoiceDetail.setPUserId(InsercionContableVO.getLgCodigoUsuario());
		invoiceDetail.setPPlacaVeh(strPlacaDiplomatica);
		invoiceDetail.setPCompany(accountingParameters.getCompany()
				.getCmpNombre());
		invoiceDetail.setPAccount(accountingParameters.getAccount()
				.getAccNumeroCuenta());
		invoiceDetail.setPCcenter(codigoCentroCosto);
		invoiceDetail.setPAuxiliary(Carnet);
		invoiceDetail.setPOrgId(InsercionContableVO.getLgOrigenId());
		invoiceDetail.setPLocation(InsercionContableVO.getLgUbicacion());
		invoiceDetail.setInvoiceHeader(invoiceHeader);
		invoiceDetail.setPNit(nit);

		try {
			// EntityManagerHelper.getEntityManager().getTransaction().begin();
			new InvoiceDetailDAO().save(invoiceDetail);
			// EntityManagerHelper.getEntityManager().getTransaction().commit();

		} catch (Exception e) {
			throw new GWorkException(e.getMessage(), e);
		}
		return invoiceDetail;
	}

	/**
	 * Genera el detalle del comprobante de facturas
	 */
	public Connection generarComprobanteDetalle(
			List<PoliciesVehicles> listaPoliciesVehicles,
			List<Inconsistencies> listaInconsistencias,
			PoliciesInvoice invoicePolicy, String login, InvoiceType IntCodigo,
			Long valorTotalFactura) throws GWorkException {

		Connection connection = null;
		
		try {
			
			invoicePolicy = EntityManagerHelper.getEntityManager().merge(
					invoicePolicy);
			strFactura = invoicePolicy.getPinNumeroFactura();
			TipoFactura = IntCodigo.getIntNombre();
			dtFechaFactura = invoicePolicy.getPinFechaFactura();
			idTipoTransaccion = ParametersUtil.TRASACCTION_TYPE_SEGUROS;

			if (invoicePolicy.getPolicies() != null)
				NitProveedor = invoicePolicy.getPolicies().getPlsDocUno();
			else
				NitProveedor = invoicePolicy.getPoliciesVehicleses().iterator()
						.next().getPolicies().getPlsDocUno();

			ValorTotalFactura = valorTotalFactura;
			MonedaFactura = "COP";
			ConvType = "Corporate";
			ConvRate = null;

			if (invoicePolicy.getPolicies() != null)
				strDescripcionFactura = invoicePolicy.getPinConcepto()
						+ " POLIZA: " + invoicePolicy.getPolicies().getPlsNumero();
			else
				strDescripcionFactura = invoicePolicy.getPinConcepto()
						+ " FACTURA: " + invoicePolicy.getPinNumeroFactura();

			Source = "VEHICLES";

			Long porcentajeCentroCostoVehi = null;
			Long valorLineaCentroCosto = null;
			Float valorTotalIva = 0F;
			Float valorRecuperacionIvaTotal = 0F;

			connection = ConsultsServiceImpl.getConnection("jdbc/siscarFinanciero");
			
			// inserta en la tabla invoice header
			invoiceHeader = generarCabeceraComprobanteFacturas(strFactura,
					TipoFactura, dtFechaFactura, NitProveedor, ValorTotalFactura
							.floatValue(), dtFechaFactura, MonedaFactura, ConvType,
					dtFechaFactura, ConvRate, strDescripcionFactura, login, Source,
					IntCodigo, invoicePolicy.getPinId());

			// insercion en la tabla de ciat contable
			InsercionContableVO = consultsService.insercionCabeceraFactura(connection,
					strFactura, TipoFactura, dtFechaFactura, NitProveedor,
					ValorTotalFactura.longValue(), dtFechaFactura, MonedaFactura,
					ConvType, dtFechaFactura, ConvRate, strDescripcionFactura,
					login, Source, invoiceHeader.getInhCodigo().longValue()).get(0);

			if (InsercionContableVO.getStrError() != null)
				throw new GWorkException(InsercionContableVO.getStrError());
			else {
				NumeroFilas = 0L;
				boolean Asignacion = true;
				
				int i=-1;
				
				for (PoliciesVehicles policiesVehicles : listaPoliciesVehicles) {
					i++;
					
					EntityManagerHelper.log("Iterando la poliza No. " + i + " de la coleccion", Level.INFO, null);
					
					policiesVehicles = EntityManagerHelper.getEntityManager()
							.merge(policiesVehicles);
					Float valorIva = null;
					Float valorRecuperacionIva = null;
					valorTotalIva = valorTotalIva
							+ policiesVehicles.getPvsValorIva();

					Asignacion = true;
					codigoCentroCosto = null;
					strPlacaDiplomatica = policiesVehicles.getVehicles()
							.getVhcPlacaDiplomatica();

					strDescripcionFactura = strPlacaDiplomatica + " "
							+ invoicePolicy.getPinConcepto() + " POLIZA No "
							+ policiesVehicles.getPolicies().getPlsNumero();

					listaCostCenters = SearchCostCenters
							.consultarCCVehiculo(strPlacaDiplomatica);
					if (listaCostCenters != null && listaCostCenters.size() > 0) {
						for (CostsCentersVehicles costsCentersVehicles : listaCostCenters) {

							if (Asignacion) {
								NumeroFilas += 1L;
								if (costsCentersVehicles.getVehiclesAssignation()
										.getRequests().getLegateesTypes() != null) {
									idTipoAsignacion = costsCentersVehicles
											.getVehiclesAssignation().getRequests()
											.getLegateesTypes().getLgtCodigo()
											.longValue();
									Cargo = policiesVehicles.getPolicies()
											.getPoliciesTypes().getPltCodigo()
											.longValue();
									Carnet = costsCentersVehicles
											.getVehiclesAssignation().getRequests()
											.getRqsCarnetAsignatario().toString();
								}

								if (idTipoAsignacion == ParametersUtil.LGT_OF
										|| idTipoAsignacion == ParametersUtil.LGT_OFS
										|| idTipoAsignacion == ParametersUtil.LGT_PROGRAMAS
										|| idTipoAsignacion == ParametersUtil.LGT_NO_APLICA) {
									idTipoAsignacion = ParametersUtil.LGT_OF;
									Cargo = ParametersUtil.CARGO_FULL_AB_SOAT;
									codigoCentroCosto = "00000";
									valorLineaCentroCosto = policiesVehicles
											.getPvsValorPrima().longValue();
									valorIva = policiesVehicles.getPvsValorIva();
									Asignacion = false;
								} else if (idTipoAsignacion == ParametersUtil.LGT_PROYECTO) {
									idTipoAsignacion = ParametersUtil.LGT_OF;
									Cargo = ParametersUtil.CARGO_PROYECTOS;
									codigoCentroCosto = costsCentersVehicles
											.getCostsCenters().getCocNumero();
									porcentajeCentroCostoVehi = new Long(
											costsCentersVehicles.getCcrPorcentaje());
									valorLineaCentroCosto = (policiesVehicles
											.getPvsValorPrima().longValue() * porcentajeCentroCostoVehi) / 100;
									valorIva = (policiesVehicles.getPvsValorIva() * porcentajeCentroCostoVehi) / 100;
								}

								idTipoMovimiento = ParametersUtil.DEBITO;

								accountingParameters = SearchParametersBilling
										.consultarParemeter(idTipoMovimiento,
												Cargo, idTipoTransaccion,
												idTipoAsignacion);

								codigoProveedor = accountingParameters
										.getAuxiliar().getAuxValor().toString();
								idAuxiliar = accountingParameters.getAuxiliar()
										.getAuxId().longValue();

								if (idAuxiliar == ParametersUtil.TipoAuxAsignatario)
									codigoProveedor = Carnet;
								else if (idAuxiliar == ParametersUtil.TipoAuxDelima
										|| idAuxiliar == ParametersUtil.TipoAuxAseguradora)
									codigoProveedor = consultsService
											.CodigoProveedorByNit(NitProveedor);

								invoiceDetail = generarDetalleComprobanteFacturas(
										InsercionContableVO, NumeroFilas,
										strFactura, dtFechaFactura,
										valorLineaCentroCosto, strPlacaDiplomatica,
										accountingParameters, codigoCentroCosto,
										invoiceHeader, codigoProveedor,
										NitProveedor, strDescripcionFactura);

								if (codigoProveedor != null) {

									connection = consultsService
											.insercionDetalleFactura(connection, 
													InsercionContableVO
															.getLgSecuenciaFactura(),
													NumeroFilas.longValue(),
													strFactura, dtFechaFactura,
													valorLineaCentroCosto,
													strDescripcionFactura,
													InsercionContableVO
															.getLgCodigoUsuario(),
													strPlacaDiplomatica,
													accountingParameters
															.getCompany()
															.getCmpNombre(),
													accountingParameters
															.getAccount()
															.getAccNumeroCuenta(),
													codigoCentroCosto,
													codigoProveedor,
													InsercionContableVO
															.getLgOrigenId(),
													InsercionContableVO
															.getLgUbicacion(),
													NitProveedor, invoiceDetail
															.getIndCodigo()
															.longValue());

									if (connection == null) {
										throw new GWorkException(
												"Error al insertar en el detalle de la interface contable");
									}
								}

								idTipoMovimiento = ParametersUtil.DEBITO;
								Cargo = ParametersUtil.CARGO_IVA;
								idTipoAsignacion = ParametersUtil.LGT_NO_APLICA;

								accountingParameters = SearchParametersBilling
										.consultarParemeter(idTipoMovimiento,
												Cargo, idTipoTransaccion,
												idTipoAsignacion);

								codigoProveedor = accountingParameters
										.getAuxiliar().getAuxValor();
								idAuxiliar = accountingParameters.getAuxiliar()
										.getAuxId().longValue();

								if (idAuxiliar == ParametersUtil.TipoAuxAsignatario)
									codigoProveedor = Carnet;
								else if (idAuxiliar == ParametersUtil.TipoAuxDelima
										|| idAuxiliar == ParametersUtil.TipoAuxAseguradora)
									codigoProveedor = consultsService
											.CodigoProveedorByNit(NitProveedor);

								// Parametrizacion para el valor del iva 
								generarDetalleComprobanteFacturas(
										InsercionContableVO, NumeroFilas,
										strFactura, dtFechaFactura, valorIva
												.longValue(), strPlacaDiplomatica,
										accountingParameters, codigoCentroCosto,
										invoiceHeader, codigoProveedor,
										NitProveedor, strDescripcionFactura);

								idTipoMovimiento = ParametersUtil.CREDITO;
								Cargo = ParametersUtil.CARGO_RECUPERACION_IVA;

								accountingParameters = SearchParametersBilling
										.consultarParemeter(idTipoMovimiento,
												Cargo, idTipoTransaccion,
												idTipoAsignacion);

								codigoProveedor = accountingParameters
										.getAuxiliar().getAuxValor();
								idAuxiliar = accountingParameters.getAuxiliar()
										.getAuxId().longValue();

								if (idAuxiliar == ParametersUtil.TipoAuxAsignatario)
									codigoProveedor = Carnet;
								else if (idAuxiliar == ParametersUtil.TipoAuxDelima
										|| idAuxiliar == ParametersUtil.TipoAuxAseguradora)
									codigoProveedor = consultsService
											.CodigoProveedorByNit(NitProveedor);

								// Recuperacion del iva
								// generarDetalleComprobanteFacturas(
								// InsercionContableVO, NumeroFilas,
								// strFactura, dtFechaFactura,
								// valorLineaCentroCosto, strPlacaDiplomatica,
								// accountingParameters, codigoCentroCosto,
								// invoiceHeader, codigoProveedor,
								// NitProveedor, strDescripcionFactura);

								Cargo = ParametersUtil.CARGO_NO_APLICA;

								accountingParameters = SearchParametersBilling
										.consultarParemeter(idTipoMovimiento,
												Cargo, idTipoTransaccion,
												idTipoAsignacion);

								codigoProveedor = accountingParameters
										.getAuxiliar().getAuxValor();
								idAuxiliar = accountingParameters.getAuxiliar()
										.getAuxId().longValue();

								if (idAuxiliar == ParametersUtil.TipoAuxAsignatario)
									codigoProveedor = Carnet;
								else if (idAuxiliar == ParametersUtil.TipoAuxDelima
										|| idAuxiliar == ParametersUtil.TipoAuxAseguradora)
									codigoProveedor = consultsService
											.CodigoProveedorByNit(NitProveedor);

								// Valor de cuentas por cobrar, el cual no se
								// ingresa por el sistema
								// generarDetalleComprobanteFacturas(
								// InsercionContableVO, NumeroFilas,
								// strFactura, dtFechaFactura,
								// valorLineaCentroCosto, strPlacaDiplomatica,
								// accountingParameters, codigoCentroCosto,
								// invoiceHeader, Carnet, NitProveedor,
								// strDescripcionFactura);
							}
						}
					} else {
						// insercion cuando el tipo del vehiculo es convenio o
						// personal
						NumeroFilas += 1L;
						VehiclesAssignation vehiclesAssignation = SearchVehicles
								.consultarAsignacionVehiculo(strPlacaDiplomatica);

						if (vehiclesAssignation == null
								|| vehiclesAssignation.getRequests()
										.getLegateesTypes() == null) {
							idTipoAsignacion = ParametersUtil.LGT_OF;
							Cargo = ParametersUtil.CARGO_FULL_AB_SOAT;
						} else if (vehiclesAssignation.getRequests()
								.getLegateesTypes() != null) {
							if (vehiclesAssignation.getRequests()
									.getLegateesTypes().getLgtCodigo().longValue() == ParametersUtil.LGT_CONVENIO) {
								idTipoAsignacion = ParametersUtil.LGT_CONVENIO;
								Cargo = ParametersUtil.CARGO_PERSONAL;
							} else if (vehiclesAssignation.getRequests()
									.getLegateesTypes().getLgtCodigo().longValue() == ParametersUtil.LGT_PERSONAL) {
								idTipoAsignacion = ParametersUtil.LGT_PERSONAL;
								Cargo = ParametersUtil.CARGO_PERSONAL;
							}
						}

						idTipoMovimiento = ParametersUtil.DEBITO;

						accountingParameters = SearchParametersBilling
								.consultarParemeter(idTipoMovimiento, Cargo,
										idTipoTransaccion, idTipoAsignacion);

						codigoProveedor = accountingParameters.getAuxiliar()
								.getAuxValor();
						idAuxiliar = accountingParameters.getAuxiliar().getAuxId()
								.longValue();

						if (idAuxiliar == ParametersUtil.TipoAuxAsignatario)
							codigoProveedor = vehiclesAssignation
									.getVhaNumeroCarne().toString();
						else if (idAuxiliar == ParametersUtil.TipoAuxDelima
								|| idAuxiliar == ParametersUtil.TipoAuxAseguradora)
							codigoProveedor = consultsService
									.CodigoProveedorByNit(NitProveedor);

						codigoCentroCosto = accountingParameters.getCostsCenters()
								.getCocNumero();

						// Se obtiene el total del valor de la poliza para vehiculos
						// PE y PE sin nomina
						if (vehiclesAssignation != null
								&& vehiclesAssignation.getRequests()
										.getLegateesTypes() != null)
							valorLineaCentroCosto = policiesVehicles
									.getPvsValorTotal().longValue();
						else if (vehiclesAssignation == null
								|| vehiclesAssignation.getRequests()
										.getLegateesTypes() == null)
							valorLineaCentroCosto = policiesVehicles
									.getPvsValorPrima().longValue();

						invoiceDetail = generarDetalleComprobanteFacturas(
								InsercionContableVO, NumeroFilas, strFactura,
								dtFechaFactura, valorLineaCentroCosto.longValue(),
								strPlacaDiplomatica, accountingParameters,
								codigoCentroCosto, invoiceHeader, codigoProveedor,
								NitProveedor, strDescripcionFactura);

						if (codigoProveedor != null) {

							connection = consultsService
									.insercionDetalleFactura(connection, InsercionContableVO
											.getLgSecuenciaFactura(), NumeroFilas
											.longValue(), strFactura,
											dtFechaFactura, valorLineaCentroCosto
													.longValue(),
											strDescripcionFactura,
											InsercionContableVO
													.getLgCodigoUsuario(),
											strPlacaDiplomatica,
											accountingParameters.getCompany()
													.getCmpNombre(),
											accountingParameters.getAccount()
													.getAccNumeroCuenta(),
											codigoCentroCosto, codigoProveedor,
											InsercionContableVO.getLgOrigenId(),
											InsercionContableVO.getLgUbicacion(),
											NitProveedor, invoiceDetail
													.getIndCodigo().longValue());

							if (connection == null) {
								throw new GWorkException(
										"Error al insertar en el detalle de la interface contable");
							}
						}

						idTipoMovimiento = ParametersUtil.DEBITO;
						Cargo = ParametersUtil.CARGO_IVA;
						idTipoAsignacion = ParametersUtil.LGT_NO_APLICA;

						accountingParameters = SearchParametersBilling
								.consultarParemeter(idTipoMovimiento, Cargo,
										idTipoTransaccion, idTipoAsignacion);

						codigoCentroCosto = accountingParameters.getCostsCenters()
								.getCocNumero();

						codigoProveedor = accountingParameters.getAuxiliar()
								.getAuxValor();
						idAuxiliar = accountingParameters.getAuxiliar().getAuxId()
								.longValue();

						if (idAuxiliar == ParametersUtil.TipoAuxAsignatario)
							codigoProveedor = Carnet;
						else if (idAuxiliar == ParametersUtil.TipoAuxDelima
								|| idAuxiliar == ParametersUtil.TipoAuxAseguradora)
							codigoProveedor = consultsService
									.CodigoProveedorByNit(NitProveedor);

						// Parametrizacion para el valor del iva
						valorIva = policiesVehicles.getPvsValorIva();
						generarDetalleComprobanteFacturas(InsercionContableVO,
								NumeroFilas, strFactura, dtFechaFactura, valorIva
										.longValue(), strPlacaDiplomatica,
								accountingParameters, codigoCentroCosto,
								invoiceHeader, codigoProveedor, NitProveedor,
								strDescripcionFactura);

						idTipoMovimiento = ParametersUtil.CREDITO;
						Cargo = ParametersUtil.CARGO_RECUPERACION_IVA;

						accountingParameters = SearchParametersBilling
								.consultarParemeter(idTipoMovimiento, Cargo,
										idTipoTransaccion, idTipoAsignacion);

						codigoProveedor = accountingParameters.getAuxiliar()
								.getAuxValor();
						idAuxiliar = accountingParameters.getAuxiliar().getAuxId()
								.longValue();

						if (idAuxiliar == ParametersUtil.TipoAuxAsignatario)
							codigoProveedor = Carnet;
						else if (idAuxiliar == ParametersUtil.TipoAuxDelima
								|| idAuxiliar == ParametersUtil.TipoAuxAseguradora)
							codigoProveedor = consultsService
									.CodigoProveedorByNit(NitProveedor);

						// recuperacion del iva
						valorRecuperacionIva = policiesVehicles.getPvsValorIva();

						if (vehiclesAssignation != null
								&& vehiclesAssignation.getRequests()
										.getLegateesTypes() != null) {
							valorRecuperacionIvaTotal = valorRecuperacionIvaTotal
									+ policiesVehicles.getPvsValorIva();

							generarDetalleComprobanteFacturas(InsercionContableVO,
									NumeroFilas, strFactura, dtFechaFactura,
									valorRecuperacionIva.longValue() * -1L,
									strPlacaDiplomatica, accountingParameters,
									accountingParameters.getCostsCenters()
											.getCocNumero(), invoiceHeader,
									codigoProveedor, NitProveedor,
									strDescripcionFactura);
						}

						Cargo = ParametersUtil.CARGO_NO_APLICA;

						accountingParameters = SearchParametersBilling
								.consultarParemeter(idTipoMovimiento, Cargo,
										idTipoTransaccion, idTipoAsignacion);

						codigoProveedor = accountingParameters.getAuxiliar()
								.getAuxValor();
						idAuxiliar = accountingParameters.getAuxiliar().getAuxId()
								.longValue();

						if (idAuxiliar == ParametersUtil.TipoAuxAsignatario)
							codigoProveedor = Carnet;
						else if (idAuxiliar == ParametersUtil.TipoAuxDelima
								|| idAuxiliar == ParametersUtil.TipoAuxAseguradora)
							codigoProveedor = consultsService
									.CodigoProveedorByNit(NitProveedor);

						// valor de cuentas por cobrar el cual no se ingresa por el
						// sistema
						// generarDetalleComprobanteFacturas(InsercionContableVO,
						// NumeroFilas, strFactura, dtFechaFactura,
						// valorLineaCentroCosto.longValue(),
						// strPlacaDiplomatica, accountingParameters,
						// accountingParameters.getCostsCenters()
						// .getCocNumero(), invoiceHeader,
						// codigoProveedor, NitProveedor,
						// strDescripcionFactura);

					}
				}

				// Ingresar registro contable para el total del iva
				if ((codigoProveedor != null && valorTotalIva != null && valorTotalIva
						.doubleValue() != 0.0)
						|| (listaInconsistencias != null && listaInconsistencias
								.size() > 0)) {
					NumeroFilas++;
					for (Inconsistencies inconsistencies : listaInconsistencias) {
						valorTotalIva = valorTotalIva
								+ inconsistencies.getIncValorIva();
					}

					idTipoMovimiento = ParametersUtil.DEBITO;
					Cargo = ParametersUtil.CARGO_IVA;
					idTipoAsignacion = ParametersUtil.LGT_NO_APLICA;
					String descripcionIva = Util
							.loadMessageValue("DESCRIPCION.IVA");

					accountingParameters = SearchParametersBilling
							.consultarParemeter(idTipoMovimiento, Cargo,
									idTipoTransaccion, idTipoAsignacion);
					codigoProveedor = consultsService
							.CodigoProveedorByNit(NitProveedor);

					connection = consultsService
							.insercionDetalleFactura(connection, InsercionContableVO
									.getLgSecuenciaFactura(), NumeroFilas
									.longValue(), strFactura, dtFechaFactura,
									valorTotalIva.longValue(), descripcionIva,
									InsercionContableVO.getLgCodigoUsuario(), "",
									accountingParameters.getCompany()
											.getCmpNombre(), accountingParameters
											.getAccount().getAccNumeroCuenta(),
									accountingParameters.getCostsCenters()
											.getCocNumero(), codigoProveedor,
									InsercionContableVO.getLgOrigenId(),
									InsercionContableVO.getLgUbicacion(),
									NitProveedor, invoiceHeader.getInhCodigo()
											.longValue());

					if (connection == null) {
						throw new GWorkException(
								"Error al insertar en el detalle de la interface contable");
					}
				}

				// Ingresar registro contable para la recuperación del iva
				if (codigoProveedor != null && valorRecuperacionIvaTotal != null
						&& valorRecuperacionIvaTotal.doubleValue() != 0.0) {
					NumeroFilas++;
					System.out.println(valorRecuperacionIvaTotal);
					idTipoMovimiento = ParametersUtil.CREDITO;

					String descripcionRecupIva = Util
							.loadMessageValue("DESC.RECUPERACION.IVA");
					accountingParameters = SearchParametersBilling
							.consultarParemeter(idTipoMovimiento,
									ParametersUtil.CARGO_RECUPERACION_IVA,
									idTipoTransaccion, ParametersUtil.LGT_NO_APLICA);

					connection = consultsService
							.insercionDetalleFactura(connection, InsercionContableVO
									.getLgSecuenciaFactura(), NumeroFilas
									.longValue(), strFactura, dtFechaFactura,
									valorRecuperacionIvaTotal.longValue() * -1L,
									descripcionRecupIva, InsercionContableVO
											.getLgCodigoUsuario(), "",
									accountingParameters.getCompany()
											.getCmpNombre(), accountingParameters
											.getAccount().getAccNumeroCuenta(),
									accountingParameters.getCostsCenters()
											.getCocNumero(), accountingParameters
											.getAuxiliar().getAuxValor(),
									InsercionContableVO.getLgOrigenId(),
									InsercionContableVO.getLgUbicacion(),
									NitProveedor, invoiceHeader.getInhCodigo()
											.longValue());

					if (connection == null) {
						throw new GWorkException(
								"Error al insertar en el detalle de la interface contable");
					}
				}

				if (listaInconsistencias != null)
					insertarListaInconsistencias(connection, listaInconsistencias, NumeroFilas);
			}
			return connection;
			
		} catch (Exception e) {
			throw new GWorkException("Error al insertar en el detalle de la interface contable", e);
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

	/**
	 * 
	 * @param connection
	 * @param listaInconsistencias
	 * @param NumeroFilas
	 * @throws GWorkException
	 */
	public void insertarListaInconsistencias(Connection connection,
			List<Inconsistencies> listaInconsistencias, Long NumeroFilas)
			throws GWorkException {

		String strPlacaDiplomaticaTemporal = null;
		for (Inconsistencies inconsistencies : listaInconsistencias) {
			inconsistencies = EntityManagerHelper.getEntityManager().merge(
					inconsistencies);
			codigoCentroCosto = null;
			strDescripcionFactura = null;
			strPlacaDiplomatica = null;
			listaCostCenters = null;
			Long valorLineaCentroCosto = null;
			Long valorIva = null;
			Float valorTotalIva = 0F;
			NumeroFilas += 1L;
			strPlacaDiplomatica = inconsistencies.getVhcPlaca().toString();
			strDescripcionFactura = strPlacaDiplomatica + " "
					+ (inconsistencies.getPoliciesInvoice()!=null?(inconsistencies.getPoliciesInvoice().getPinConcepto()):"")
					+ " POLIZA No "
					+ (inconsistencies.getPolicies()!=null?(inconsistencies.getPolicies().getPlsNumero().toString()):"[NULL]");

			if (!strPlacaDiplomatica.equals(strPlacaDiplomaticaTemporal)) {
				idTipoAsignacion = ParametersUtil.LGT_OF;
				Cargo = ParametersUtil.CARGO_FULL_AB_SOAT;
				idTipoMovimiento = ParametersUtil.DEBITO;
				idTipoTransaccion = ParametersUtil.TRASACCTION_TYPE_SEGUROS;

				accountingParameters = SearchParametersBilling
						.consultarParemeter(idTipoMovimiento, Cargo,
								idTipoTransaccion, idTipoAsignacion);

				codigoCentroCosto = accountingParameters.getCostsCenters()
						.getCocNumero();
				codigoProveedor = accountingParameters.getAuxiliar()
						.getAuxValor().toString();
				idAuxiliar = accountingParameters.getAuxiliar().getAuxId()
						.longValue();
				valorLineaCentroCosto = inconsistencies.getIncValorPrima()
						.longValue();

				if (idAuxiliar == ParametersUtil.TipoAuxAsignatario)
					codigoProveedor = Carnet;
				else if (idAuxiliar == ParametersUtil.TipoAuxDelima
						|| idAuxiliar == ParametersUtil.TipoAuxAseguradora)
					codigoProveedor = consultsService
							.CodigoProveedorByNit(NitProveedor);

				invoiceDetail = generarDetalleComprobanteFacturas(
						InsercionContableVO, NumeroFilas, strFactura,
						dtFechaFactura, valorLineaCentroCosto,
						strPlacaDiplomatica, accountingParameters,
						codigoCentroCosto, invoiceHeader, codigoProveedor,
						NitProveedor, strDescripcionFactura);

				if (codigoProveedor != null) {

					connection = consultsService
							.insercionDetalleFactura(connection, InsercionContableVO
									.getLgSecuenciaFactura(), NumeroFilas
									.longValue(), strFactura, dtFechaFactura,
									valorLineaCentroCosto,
									strDescripcionFactura, InsercionContableVO
											.getLgCodigoUsuario(),
									strPlacaDiplomatica, accountingParameters
											.getCompany().getCmpNombre(),
									accountingParameters.getAccount()
											.getAccNumeroCuenta(),
									codigoCentroCosto, codigoProveedor,
									InsercionContableVO.getLgOrigenId(),
									InsercionContableVO.getLgUbicacion(),
									NitProveedor, invoiceDetail.getIndCodigo()
											.longValue());

					if (connection == null) {
						throw new GWorkException(
								"Error al insertar en el detalle de la interface contable");
					}
				}

				idTipoMovimiento = ParametersUtil.DEBITO;
				Cargo = ParametersUtil.CARGO_IVA;
				idTipoAsignacion = ParametersUtil.LGT_NO_APLICA;

				accountingParameters = SearchParametersBilling
						.consultarParemeter(idTipoMovimiento, Cargo,
								idTipoTransaccion, idTipoAsignacion);

				codigoProveedor = accountingParameters.getAuxiliar()
						.getAuxValor();
				idAuxiliar = accountingParameters.getAuxiliar().getAuxId()
						.longValue();

				if (idAuxiliar == ParametersUtil.TipoAuxAsignatario)
					codigoProveedor = Carnet;
				else if (idAuxiliar == ParametersUtil.TipoAuxDelima
						|| idAuxiliar == ParametersUtil.TipoAuxAseguradora)
					codigoProveedor = consultsService
							.CodigoProveedorByNit(NitProveedor);

				valorIva = inconsistencies.getIncValorIva().longValue();
				// Totaliza el valor del iva en las inconsistencias
				valorTotalIva = valorTotalIva + valorIva;

				generarDetalleComprobanteFacturas(InsercionContableVO,
						NumeroFilas, strFactura, dtFechaFactura, valorIva,
						strPlacaDiplomatica, accountingParameters,
						codigoCentroCosto, invoiceHeader, codigoProveedor,
						NitProveedor, strDescripcionFactura);

				idTipoMovimiento = ParametersUtil.CREDITO;
				Cargo = ParametersUtil.CARGO_RECUPERACION_IVA;

				accountingParameters = SearchParametersBilling
						.consultarParemeter(idTipoMovimiento, Cargo,
								idTipoTransaccion, idTipoAsignacion);

				codigoProveedor = accountingParameters.getAuxiliar()
						.getAuxValor();
				idAuxiliar = accountingParameters.getAuxiliar().getAuxId()
						.longValue();

				if (idAuxiliar == ParametersUtil.TipoAuxAsignatario)
					codigoProveedor = Carnet;
				else if (idAuxiliar == ParametersUtil.TipoAuxDelima
						|| idAuxiliar == ParametersUtil.TipoAuxAseguradora)
					codigoProveedor = consultsService
							.CodigoProveedorByNit(NitProveedor);

				// generarDetalleComprobanteFacturas(InsercionContableVO,
				// NumeroFilas, strFactura, dtFechaFactura,
				// valorLineaCentroCosto, strPlacaDiplomatica,
				// accountingParameters, codigoCentroCosto, invoiceHeader,
				// codigoProveedor, NitProveedor, strDescripcionFactura);

				Cargo = ParametersUtil.CARGO_NO_APLICA;

				accountingParameters = SearchParametersBilling
						.consultarParemeter(idTipoMovimiento, Cargo,
								idTipoTransaccion, idTipoAsignacion);

				codigoProveedor = accountingParameters.getAuxiliar()
						.getAuxValor();
				idAuxiliar = accountingParameters.getAuxiliar().getAuxId()
						.longValue();

				if (idAuxiliar == ParametersUtil.TipoAuxAsignatario)
					codigoProveedor = Carnet;
				else if (idAuxiliar == ParametersUtil.TipoAuxDelima
						|| idAuxiliar == ParametersUtil.TipoAuxAseguradora)
					codigoProveedor = consultsService
							.CodigoProveedorByNit(NitProveedor);

				// generarDetalleComprobanteFacturas(InsercionContableVO,
				// NumeroFilas, strFactura, dtFechaFactura,
				// valorLineaCentroCosto, strPlacaDiplomatica,
				// accountingParameters, codigoCentroCosto, invoiceHeader,
				// codigoProveedor, NitProveedor, strDescripcionFactura);
			}
			strPlacaDiplomaticaTemporal = strPlacaDiplomatica;
		}
	}
}