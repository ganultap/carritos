package geniar.siscar.consults;

import geniar.siscar.model.ConsultUsers;
import geniar.siscar.model.CostsCenters;
import geniar.siscar.model.CostsCentersVehicles;
import geniar.siscar.model.Driver;
import geniar.siscar.model.InvoicesHeaderPoliciesVO;
import geniar.siscar.model.Users;
import geniar.siscar.model.VOEmployee;
import gwork.exception.GWorkException;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.Date;
import java.util.List;

/**
 * The Interface ConsultsService.
 */
public interface ConsultsService {

	/**
	 * Usuarios ciat.
	 *
	 * @param paramNombre the param nombre
	 * @param paramApellido the param apellido
	 * @param carne the carne
	 * @return the list
	 * @throws GWorkException the g work exception
	 */
	public List<ConsultUsers> usuariosCiat(String paramNombre,
			String paramApellido, String carne) throws GWorkException;

	/**
	 * Consult empleoyee email.
	 *
	 * @param carnet the carnet
	 * @return the string
	 * @throws GWorkException the g work exception
	 */
	public String consultEmpleoyeeEmail(String carnet) throws GWorkException;

	/**
	 * Consult empleoyee name.
	 *
	 * @param carnet the carnet
	 * @return the string
	 * @throws GWorkException the g work exception
	 */
	public String consultEmpleoyeeName(String carnet) throws GWorkException;

	/**
	 * Consult employee.
	 *
	 * @param carne the carne
	 * @return the vO employee
	 * @throws GWorkException the g work exception
	 */
	public VOEmployee consultEmployee(String carne) throws GWorkException;

	/**
	 * Consult cost center.
	 *
	 * @param costCenter the cost center
	 * @return the string
	 * @throws GWorkException the g work exception
	 */
	public String consultCostCenter(String costCenter) throws GWorkException;

	/**
	 * Validate empleooyee.
	 *
	 * @param carnet the carnet
	 * @return true, if successful
	 * @throws GWorkException the g work exception
	 */
	public boolean validateEmpleooyee(String carnet) throws GWorkException;

	/**
	 * Centros costo.
	 *
	 * @param parametroCentroCosto the parametro centro costo
	 * @return the list
	 * @throws GWorkException the g work exception
	 */
	public List<CostsCenters> centrosCosto(String parametroCentroCosto)
			throws GWorkException;

	/**
	 * Consult funtions.
	 *
	 * @param placa the placa
	 * @param numero the numero
	 * @return the big decimal
	 * @throws GWorkException the g work exception
	 */
	public BigDecimal consultFuntions(String placa, int numero)
			throws GWorkException;

	/**
	 * Consult supplier.
	 *
	 * @param supplier the supplier
	 * @return the string
	 * @throws GWorkException the g work exception
	 */
	public String consultSupplier(String supplier) throws GWorkException;

	/**
	 * Consult buy order.
	 *
	 * @param buyOrder the buy order
	 * @return the string
	 * @throws GWorkException the g work exception
	 */
	public String consultBuyOrder(String buyOrder) throws GWorkException;

	/**
	 * Employees ciat.
	 *
	 * @param paramNombre the param nombre
	 * @param paramApellido the param apellido
	 * @param carne the carne
	 * @return the list
	 * @throws GWorkException the g work exception
	 */
	public List<Users> employeesCIAT(String paramNombre, String paramApellido,
			String carne) throws GWorkException;

	/**
	 * Drivers ciat.
	 *
	 * @param paramNombre the param nombre
	 * @param paramCedula the param cedula
	 * @param carne the carne
	 * @return the list
	 * @throws GWorkException the g work exception
	 */
	public List<Driver> driversCIAT(String paramNombre, String paramCedula,
			String carne) throws GWorkException;

	/**
	 * Validate cost center.
	 *
	 * @param costCenter the cost center
	 * @return true, if successful
	 * @throws GWorkException the g work exception
	 */
	public boolean validateCostCenter(String costCenter) throws GWorkException;

	/**
	 * Consultar auxiliar ciat.
	 *
	 * @param codigo the codigo
	 * @return the string
	 * @throws GWorkException the g work exception
	 */
	public String consultarAuxiliarCiat(String codigo) throws GWorkException;

	/**
	 * consulta una compañia.
	 *
	 * @param codigoCompañia the codigo compañia
	 * @return the string
	 * @throws GWorkException the g work exception
	 */
	public String consultCompany(String codigoCompañia) throws GWorkException;

	/**
	 * consulta una cuenta.
	 *
	 * @param codigoCuenta the codigo cuenta
	 * @return the string
	 * @throws GWorkException the g work exception
	 */
	public String consultAccount(String codigoCuenta) throws GWorkException;

	/**
	 * consulta el carne del auxiliar.
	 *
	 * @param carneAuxiliar the carne auxiliar
	 * @return the string
	 * @throws GWorkException the g work exception
	 */
	public String validarCarneAuxiliar(String carneAuxiliar)
			throws GWorkException;

	/**
	 * valida la distribucion presupuestal de un centro de costos.
	 *
	 * @param anho the anho
	 * @param codigoCentroCosto the codigo centro costo
	 * @param cuenta the cuenta
	 * @param auxiliar the auxiliar
	 * @param valor the valor
	 * @return true, if successful
	 * @throws GWorkException the g work exception
	 */
	public boolean consultarDisponibiladaPresupuestal(Integer anho,
			String codigoCentroCosto, String cuenta, String auxiliar,
			Double valor) throws GWorkException;

	/**
	 * Consulta el tipo de moneda asociado a un asignatario.
	 * 
	 * @param carnet
	 *            del asignatario.
	 * @return Identificador del tipo de moneda.
	 * @throws GWorkException
	 *             Manejador de excepciones.
	 * @author Mauricio Cuenca Narváez
	 */
	public Long counsultarTipoMonedaAsignatario(String carnet)
			throws GWorkException;

	/**
	 * Consulta la tasa de conversión con la fecha actual.
	 * 
	 * @param fechaActual
	 *            Fecha del día en que se hace la consulta.
	 * @return Valor de la tasa de conversión.
	 * @throws GWorkException
	 *             Manejador de Excepciones.
	 */
	public Float consultarTasaConversion(Date fechaActual)
			throws GWorkException;

	/**
	 * Consulta la disponibilidad presupuestal de una solicitud que tenga centro
	 * de costos asociados para es.
	 *
	 * @param listCostsCenters Centros de costos a consultar disponibilidad
	 * @param idTipoVehiculo tipo vehiculo para consultar la tarifa
	 * @param claseSoliicitud the clase soliicitud
	 * @param legateesTypes the legatees types
	 * @throws GWorkException the g work exception
	 */
	public void consultarDisponibilidadAlquilerCC(
			List<CostsCentersVehicles> listCostsCenters, Long idTipoVehiculo,
			Long claseSoliicitud, Long legateesTypes) throws GWorkException;

	/**
	 * Auxiliares ciat.
	 *
	 * @param nombreAuxiliar the nombre auxiliar
	 * @param codigo the codigo
	 * @return the list
	 * @throws GWorkException the g work exception
	 */
	public List<Users> auxiliaresCIAT(String nombreAuxiliar, String codigo)
			throws GWorkException;

	/**
	 * Ordenes trabajo.
	 *
	 * @param nombreOrden the nombre orden
	 * @return the list
	 * @throws GWorkException the g work exception
	 */
	public List<Object[]> ordenesTrabajo(String nombreOrden)
			throws GWorkException;

	/**
	 * Validar presupuesto.
	 *
	 * @param anho the anho
	 * @param codigoCentroCosto the codigo centro costo
	 * @param cuenta the cuenta
	 * @param auxiliar the auxiliar
	 * @param valor the valor
	 * @return the string
	 */
	public boolean validarPresupuesto(Integer anho, String codigoCentroCosto,
			String cuenta, String auxiliar, Double valor) throws GWorkException;

	/**
	 * Consult cost center periodo.
	 *
	 * @param costCenter the cost center
	 * @param fechaIni the fecha ini
	 * @param fechaFin the fecha fin
	 * @return the string
	 * @throws GWorkException the g work exception
	 */
	public String consultCostCenterPeriodo(String costCenter, Date fechaIni,
			Date fechaFin) throws GWorkException;

	// Consulta el nit y el nombre del proveedor por medio del nombre
	/**
	 * Nit proveedor.
	 *
	 * @param Parametro the parametro
	 * @return the list
	 * @throws GWorkException the g work exception
	 */
	public List<Users> nitProveedor(String Parametro) throws GWorkException;

	// Consulta el codigo del proveedor por medio del nit
	/**
	 * Codigo proveedor by nit.
	 *
	 * @param Parametro the parametro
	 * @return the string
	 * @throws GWorkException the g work exception
	 */
	public String CodigoProveedorByNit(String Parametro) throws GWorkException;

	/**
	 * Realiza la inserccion de la cabecera de la factura del modulo seguros.
	 *
	 * @param connection the connection
	 * @param NumeroFactura the numero factura
	 * @param TipoFactura the tipo factura
	 * @param FechaFactura the fecha factura
	 * @param Nit the nit
	 * @param TotalFactura the total factura
	 * @param FechaRecibidoFactura the fecha recibido factura
	 * @param MonedaFactura the moneda factura
	 * @param TipoConversion the tipo conversion
	 * @param FechaCoversion the fecha coversion
	 * @param TasaConversion the tasa conversion
	 * @param Descripcion the descripcion
	 * @param Login the login
	 * @param OrigenVehiculo the origen vehiculo
	 * @param inh_Codigo the inh_ codigo
	 * @return the list
	 * @throws GWorkException the g work exception
	 */
	public List<InvoicesHeaderPoliciesVO> insercionCabeceraFactura(
			Connection connection, String NumeroFactura, String TipoFactura,
			Date FechaFactura, String Nit, Long TotalFactura,
			Date FechaRecibidoFactura, String MonedaFactura,
			String TipoConversion, Date FechaCoversion, Long TasaConversion,
			String Descripcion, String Login, String OrigenVehiculo,
			Long inh_Codigo) throws GWorkException;

	/**
	 * Realiza la inserccion del detalle de la factura del modulo seguros.
	 *
	 * @param connection the connection
	 * @param PInvoiceId the p invoice id
	 * @param PLineNum the p line num
	 * @param PInvoiceNum the p invoice num
	 * @param PInvoiceDate the p invoice date
	 * @param PInvoiceAmount the p invoice amount
	 * @param PDescription the p description
	 * @param PUserId the p user id
	 * @param PPlacaVeh the p placa veh
	 * @param PCompany the p company
	 * @param PAccount the p account
	 * @param PCCenter the pC center
	 * @param PAuxiliary the p auxiliary
	 * @param POrgId the p org id
	 * @param PLocation the p location
	 * @param Nit the nit
	 * @param codigoInvoiceDetail the codigo invoice detail
	 * @return the connection
	 * @throws GWorkException the g work exception
	 */
	public Connection insercionDetalleFactura(Connection connection,
			Long PInvoiceId, Long PLineNum, String PInvoiceNum,
			Date PInvoiceDate, Long PInvoiceAmount, String PDescription,
			Long PUserId, String PPlacaVeh, String PCompany, String PAccount,
			String PCCenter, String PAuxiliary, Long POrgId, Long PLocation,
			String Nit, Long codigoInvoiceDetail) throws GWorkException;
}