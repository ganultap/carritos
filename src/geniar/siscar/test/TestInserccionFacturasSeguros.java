package geniar.siscar.test;

import geniar.siscar.consults.ConsultsService;
import geniar.siscar.consults.impl.ConsultsServiceImpl;
import geniar.siscar.model.InvoiceDetail;
import geniar.siscar.model.InvoiceHeader;
import geniar.siscar.persistence.EntityManagerHelper;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

public class TestInserccionFacturasSeguros {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		
		Connection connection = null;
		try {
			connection = ConsultsServiceImpl.getConnection("jdbc/siscarFinanciero");
		
			List<InvoiceHeader> listInvoiceHeader = new ArrayList<InvoiceHeader>();
			
			final String queryString = "select  model "
				+ "from InvoiceHeader model WHERE model.inhCodigo = 624";
	
			Query query = EntityManagerHelper.getEntityManager().createQuery(
					queryString);
	
			listInvoiceHeader = query.getResultList();
	
			ConsultsService consultsService = new ConsultsServiceImpl();
			
			for (InvoiceHeader invoiceHeader : listInvoiceHeader) {
	
				List<InvoiceDetail> listInvoiceDetail = new ArrayList<InvoiceDetail>();
	
				final String queryDetail = "select  model "
					+ "from InvoiceDetail model WHERE model.invoiceHeader.inhCodigo = 624";
		
				query = EntityManagerHelper.getEntityManager().createQuery(
						queryDetail);
				//query.setParameter("inhCodigo", "624");
		
				consultsService.insercionCabeceraFactura(connection,
						invoiceHeader.getPInvoiceNum(), invoiceHeader.getPTipoFactura(), invoiceHeader.getPInvoiceDate(), invoiceHeader.getPNit(),
						Long.valueOf(invoiceHeader.getPInvoiceAmount().longValue()), invoiceHeader.getPFechaRcvFactura(), invoiceHeader.getPMonedaFactura(),
						invoiceHeader.getPConvType(), invoiceHeader.getPConvDateDate(), invoiceHeader.getPConvRate(),
						invoiceHeader.getPDescription(), invoiceHeader.getPUser(), invoiceHeader.getPSource(), invoiceHeader.getInhCodigo());
								
//				consultsService.insercionCabeceraFactura(connection,
//						NumeroFactura, TipoFactura, FechaFactura, Nit,
//						TotalFactura, FechaRecibidoFactura, MonedaFactura,
//						TipoConversion, FechaCoversion, TasaConversion,
//						Descripcion, Login, OrigenVehiculo, inh_Codigo);
				
				listInvoiceDetail = query.getResultList();
				int i = 0;
				for (InvoiceDetail invoiceDetail : listInvoiceDetail) {
					i++;
					System.out.println(i);
					
					consultsService.insercionDetalleFactura(connection,
							invoiceDetail.getPInvoiceId(), invoiceDetail.getPLineNum(), invoiceDetail.getPInvoiceNum(), invoiceDetail.getPInvoiceDate(),
							invoiceDetail.getPInvoiceAmount(), invoiceDetail.getPDescription(), invoiceDetail.getPUserId(), invoiceDetail.getPPlacaVeh(),
							invoiceDetail.getPCompany(), invoiceDetail.getPAccount(), invoiceDetail.getPCcenter(), invoiceDetail.getPAuxiliary(), invoiceDetail.getPOrgId(),
							invoiceDetail.getPLocation(), invoiceDetail.getPNit(), invoiceDetail.getIndCodigo());
					
					//				consultsService.insercionDetalleFactura(connection,
					//							PInvoiceId, PLineNum, PInvoiceNum, PInvoiceDate,
					//							PInvoiceAmount, PDescription, PUserId, PPlacaVeh,
					//							PCompany, PAccount, PCCenter, PAuxiliary, POrgId,
					//							PLocation, Nit, codigoInvoiceDetail);
				}
			}
			
			if (connection != null)
				connection.commit();
			
		}catch (Exception e) {
			
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
			e.printStackTrace();
		}finally{
			try{
				if (connection!=null && !connection.isClosed()){
					connection.close();
				}
			}catch(Exception ex2){
				//log.error("Error: " + ex2.getMessage(), ex2);
				ex2.printStackTrace();
			}
		}

	}
}
