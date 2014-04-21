package geniar.siscar.logic.policies.services;

import geniar.siscar.model.Inconsistencies;
import geniar.siscar.model.PoliciesInvoice;
import geniar.siscar.model.PoliciesVehicles;
import geniar.siscar.model.VOPolicies;
import gwork.exception.GWorkException;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author Geniar
 * 
 */
public interface PoliciesInvoiceService extends Runnable {

	/**
	 * 
	 * @return int Total de inconsistencias que se solucionaron al hacer la
	 *         verificación.
	 */
	public int getSoluciones();

	public void setSoluciones(int soluciones);

	/**
	 * Crea una nueva factura sobre una poliza ya creada.
	 * 
	 * @param numeroPoliza
	 * @param numeroFactura
	 * @param fechaFactura
	 * @param conceptoFact
	 * @param login
	 * @throws GWorkException
	 *             Manejador de excepciones.
	 */
	public void crearFacturaPoliza(Long numeroPoliza, String numeroFactura,
			Date fechaFactura, String conceptoFact, String login)
			throws GWorkException;

	/**
	 * Crear una factura para polizas que son tipo SOAT
	 * 
	 * @param listPolicesVehicles
	 * @param numeroFactura
	 * @param fechaFactura
	 * @param conceptoFact
	 * @param login
	 */

	public void crearFacturaPolizaSOAT(List<VOPolicies> listPolicesVehicles,
			String numeroFactura, Date fechaFactura, String conceptoFact,
			String login) throws GWorkException;

	/**
	 * @param
	 * 
	 */
	public void modificarFacturaPolizaSOAT(
			List<VOPolicies> listPolicesVehicles, String numeroFactura,
			String nuevoNumeroFactura, Date fechaFactura, String conceptoFact,
			String login) throws GWorkException;

	/**
	 * Modifica una factura asociada a una poliza.
	 * 
	 * @param numeroPoliza
	 * @param numeroFactura
	 * @param numeroNuevaFactura
	 * @param fechaFactura
	 * @param conceptoFact
	 * @param login
	 * @throws GWorkException
	 *             Manejador de excepciones.
	 */
	public void modificarFacturaPoliza(Long numeroPoliza, String numeroFactura,
			String numeroNuevaFactura, Date fechaFactura, String conceptoFact,
			String login) throws GWorkException;

	/**
	 * Busca una factura de una poliza.
	 * 
	 * @param numeroFactura
	 *            Número de la poliza.
	 * @return Un objeto de tipo Policies.
	 * @throws GWorkException
	 *             Manejador de Excepciones.
	 */
	public PoliciesInvoice consultarFacturaPoliza(String numeroFactura)
			throws GWorkException;

	/**
	 * 
	 * @param placaVehiculo
	 * @return
	 * @throws GWorkException
	 *             Manejador de excepciones.
	 */
	public List<PoliciesVehicles> consultarPolizasSOATVehiculo(
			String placaVehiculo) throws GWorkException;

	/**
	 * 
	 * @param listaInconsistencias
	 *            Inconsistencias encontradas al cargar el archivo.
	 * @param listaPoliVehicles
	 *            Asociaciones de vehiculos a la poliza
	 * @param invoicePolicy
	 *            Factura involucrada.
	 * @param login
	 *            Login del usuario que realiza la acción.
	 * @throws GWorkException
	 *             Manejador de excepciones.
	 */
	public void guardarDetalle(List<Inconsistencies> listaInconsistencias,
			List<PoliciesVehicles> listaPoliVehicles,
			PoliciesInvoice invoicePolicy, String login, Long valorTotalFactura)
			throws GWorkException;

	/**
	 * 
	 * @param listaInconsistencias
	 * @param listaPoliVehicles
	 * @param invoicePolicy
	 * @param login
	 *            Login del usuario que realiza la acción.
	 * @throws GWorkException
	 *             Manejador de excepciones.
	 */
	public void guardarDetalleNovedad(
			List<Inconsistencies> listaInconsistencias,
			List<PoliciesVehicles> listaPoliVehicles,
			PoliciesInvoice invoicePolicy, String login,
			Long valorTotalFactura, Long tipoPoliza) throws GWorkException,
			SQLException;

	/**
	 * 
	 * @param listaInconsistencias
	 * @param listaPoliVehicles
	 * @param invoicePolicy
	 * @param login
	 *            Login del usuario que realiza la acción.
	 * @throws GWorkException
	 *             Manejador de excepciones.
	 */
	public void modificarDetalle(List<Inconsistencies> listaInconsistencias,
			List<PoliciesVehicles> listaPoliVehicles,
			PoliciesInvoice invoicePolicy, String login, Long valorTotalFactura)
			throws GWorkException;

	/**
	 * Verifica si se han solucionado inconsistencias y
	 * 
	 * @param idFactura
	 *            Identificador de la factura que registra la inconsistencia.
	 * @throws GWorkException
	 *             Manejador de excepciones.
	 */
	public void verificarInconsistenciasFacturas(Long idFactura)
			throws GWorkException;

	/**
	 * Consulta todas las facturas existentes en el sistema
	 * 
	 * @return Listado de facturas
	 * @throws GWorkException
	 *             Manejador de excepciones.
	 */
	public List<PoliciesInvoice> consultarFacturas() throws GWorkException;

	/**
	 * guarda todas las inconsistencias de la facturas
	 * 
	 * @param lista
	 *            de vehiculos en la factura
	 * @throws GWorkException
	 *             Manejador de excepciones.
	 */
	public void generarInconsitenciasFactura(
			List<PoliciesVehicles> listPoliciesVehicles, String Login,
			PoliciesInvoice policiesInvoice) throws GWorkException;

	/**
	 * guarda todas las inconsistencias de la facturas
	 * 
	 * @param lista
	 *            de vehiculos en la factura
	 * @throws GWorkException
	 *             Manejador de excepciones.
	 */

	public void GuardarFacturaAp(List<PoliciesVehicles> listaPoliVehicles,
			List<Inconsistencies> listaInconsistencias,
			PoliciesInvoice policiesInvoice, String Login,
			Long valorTotalFactura) throws GWorkException;

	/**
	 * Corrige las inconsistencias
	 * 
	 * @param Inconsitencia
	 * @param Observacion
	 * @throws GWorkException
	 *             Manejador de excepciones.
	 */
	public void CorregirInconsistencias(Inconsistencies inconsistencies,
			String incObservacion) throws GWorkException;

	public List<PoliciesInvoice> consultarFacturasSOAT() throws GWorkException;
}