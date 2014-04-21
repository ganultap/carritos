package geniar.siscar.logic.billing.services;

import geniar.siscar.model.AccountingParameters;
import geniar.siscar.model.Inconsistencies;
import geniar.siscar.model.InvoiceDetail;
import geniar.siscar.model.InvoiceHeader;
import geniar.siscar.model.InvoiceType;
import geniar.siscar.model.InvoicesHeaderPoliciesVO;
import geniar.siscar.model.PoliciesInvoice;
import geniar.siscar.model.PoliciesVehicles;
import gwork.exception.GWorkException;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

public interface GenerateInvoicesProofInsurance {

	public InvoiceHeader generarCabeceraComprobanteFacturas(String InvoiceNum,
			String TipoFactura, Date InvoiceDate, String Nit,
			Float InvoiceAmount, Date FechaRcvFactura, String MonedaFactura,
			String ConvType, Date ConvDateDate, Long ConvRate,
			String Description, String User, String Source,
			InvoiceType IntCodigo, Long id_PIinvoice) throws GWorkException;

	public InvoiceDetail generarDetalleComprobanteFacturas(
			InvoicesHeaderPoliciesVO InsercionContableVO, Long numeroFilas,
			String strFactura, Date dtFechaFactura, Long ValorTotalPoliza,
			String strPlacaDiplomatica,
			AccountingParameters accountingParameters,
			String codigoCentroCosto, InvoiceHeader invoiceHeader,
			String Carnet, String nit, String Descripcion)
			throws GWorkException;

	public Connection generarComprobanteDetalle(
			List<PoliciesVehicles> listaPoliciesVehicles,
			List<Inconsistencies> listaInconsistencias,
			PoliciesInvoice invoicePolicy, String login, InvoiceType IntCodigo,
			Long valorTotalFactura) throws GWorkException;

}
