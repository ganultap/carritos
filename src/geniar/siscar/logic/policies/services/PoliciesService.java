/**
 * 
 */
package geniar.siscar.logic.policies.services;

import java.util.Date;
import java.util.List;

import geniar.siscar.model.Policies;
import geniar.siscar.model.PoliciesVehicles;
import geniar.siscar.model.VOPolicies;
import geniar.siscar.model.Vehicles;
import gwork.exception.GWorkException;

/**
 * @author Mauricio
 * 
 */
public interface PoliciesService {

	/**
	 * Crea un nuevo registro de Policies
	 * 
	 * @param idTipoPoliza
	 *            Identificador del Tipo de Poliza.
	 * @param numPoliza
	 *            Número de la poliza.
	 * @param fechaInicio
	 *            Fecha de inicio de la vigencia de la poliza.
	 * @param fechaTermino
	 *            Fecha de termino de la vigencia de la poliza.
	 * @param plsDocumento2
	 * @param plsDocumento1
	 * @param plsValorAseg
	 * @param plsValorTotal
	 * @param plsValorContribucion
	 * @param plsValorPrima
	 * @throws GWorkException
	 *             Manejador de Excepciones.
	 */
	public void crearPoliza(String placaVehiculo, Long idTipoPoliza,
			Long numPoliza, Date fechaInicio, Date fechaTermino, String login,
			String plsDocumento1, String plsDocumento2, Float valorIVA,
			Float valorPrima, Float plsValorAseg) throws GWorkException;

	/**
	 * Cambia un registro de Policies
	 * 
	 * @param idTipoPoliza
	 *            Identificador del Tipo de Poliza.
	 * @param numPoliza
	 *            Número de la poliza.
	 * @param fechaInicio
	 *            Fecha de inicio de la vigencia de la poliza.
	 * @param fechaTermino
	 *            Fecha de termino de la vigencia de la poliza.
	 * @param plsDocumento2
	 * @param plsDocumento1
	 * @param plsValorAseg
	 * @param plsValorPrima
	 * @param plsValorContribucion
	 * @throws GWorkException
	 *             Manejador de Excepciones.
	 */
	public void modificarPoliza(String placaVehiculo, Long idTipoPoliza,
			Long numPoliza, Long nuevoNumPoliza, Date fechaInicio,
			Date fechaTermino, String login, String plsDocumento1,
			String plsDocumento2, Float plsValorContribucion,
			Float plsValorPrima, Float plsValorAseg) throws GWorkException;

	/**
	 * Busca un registro de tipo Policies
	 * 
	 * @param numPoliza
	 *            Número de la poliza.
	 * @return Un objeto de tipo Policies.
	 * @throws GWorkException
	 *             Manejador de Excepciones.
	 */
	public Policies consultarPoliza(Long numPoliza) throws GWorkException;

	/**
	 * Consulta todas las polizas asociadas al tipo de poliza especifico.
	 * 
	 * @param idTipoPoliza
	 *            Identificador único del tipo de poliza.
	 * @return Listado de todas las polizas.
	 * @throws GWorkException
	 *             Manejador de Excepciones.
	 */
	public List<Policies> consultarTodasPolizas(Long idTipoPoliza)
			throws GWorkException;

	public List<Policies> consultarSOATsInactivosVehiculo(String placaVechiculo)
			throws GWorkException;

	/**
	 * Reemplaza una poliza.
	 * 
	 * @param idTipoPoliza
	 *            Identificador del tipo de poliza.
	 * @param numeroPoliza
	 *            Numero de la poliza que se va reemplazar.
	 * @param nuevoNumero
	 *            Nuevo numero para la poliza.
	 * @param fechaIni
	 *            Fecha de inicio de vigencia de la poliza.
	 * @param fechaFin
	 *            Fecha de finalización de la vigencia.
	 * @throws GWorkException
	 *             Manejador de Excepciones.
	 */
	public void reemplazarPoliza(Long idTipoPoliza, Long numeroPoliza,
			Long nuevoNumero, Date fechaIni, Date fechaFin, String login,
			String plsDocumento1, String plsDocumento2, String placaVehiculo,
			Float plsValorAseg, Float plsValorContribucion,
			Float plsValorPrima, Float plsValorTotal, Vehicles vehicles)
			throws GWorkException;

	/**
	 * Consulta todas las polizas vigentes.
	 * 
	 * @return Listado de todas las polizas vigentes.
	 * @throws GWorkException
	 *             Manejador de Excepciones.
	 */
	public List<Policies> consultarTodasPolizasVigentesNOSOAT()
			throws GWorkException;

	/**
	 * Consulta todas las polizas vigentes.
	 * 
	 * @return Listado de todas las polizas vigentes.
	 * @throws GWorkException
	 *             Manejador de Excepciones.
	 */
	public List<Policies> consultarTodasPolizasVigentes() throws GWorkException;

	/**
	 * 
	 * @param placa
	 * @return Listado de Polizas de un VHC
	 * @throws GWorkException
	 */
	public List<PoliciesVehicles> consultarPvsVHC(String placa)
			throws GWorkException;

	/**
	 * @param idTipoPoliza
	 * @return Listado de todas la polizas de Soat
	 * @throws GWorkException
	 * 
	 */
	public List<VOPolicies> consultarTodasPolizasSOAT(Long idTipoPoliza)
			throws GWorkException;

}
