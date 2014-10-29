package geniar.siscar.logic.parameters.services;

import geniar.siscar.model.ControlAssignationPolicies;
import gwork.exception.GWorkException;

import java.util.List;

/**
 * 
 * @author Mauricio
 *
 */
public interface PolicyAssignementTypeControlService {

	/**
	 * Crea un nuevo ControlAssignationPolicies.
	 * @param idTipoAsignatario Identificador del tipo de Asignatario.
	 * @param idTipoUbicacion Identificador del tipo de Ubicación
	 * @param idTipoPoliza Identificador del tipo de poliza.
	 * @param soatRequerido indica si el soat es requerido.
	 * @param responsabCivil indica si hay responasbilidad civil.
	 * @throws GWorkException Manejador de excepciones.
	 */
	public void crearPolicyAssignementTypeControl(
			Long idTipoAsignatario, Long idTipoUbicacion,
			Long idTipoPoliza, boolean soatRequerido,
			boolean responsabCivil)throws GWorkException;

	/**
	 * Encuentra un Objeto ControlAssignationPolicies
	 * @param idPolicyAssignementTypeControl identificador
	 *  de PolicyAssignementTypeControl.
	 * @return objeto PolicyAssignementTypeControl.
	 * @throws GWorkException Manejador de excepciones.
	 */
	public ControlAssignationPolicies consultarCtrlTipoAsignacion(
			Long idPolicyAssignementTypeControl)
	throws GWorkException;
	
	/**
	 * Cambia los valores de los atributos de un objeto
	 * ControlAssignationPolicies.
	 * @param idTipoAsignatario Identificador del tipo de Asignatario.
	 * @param idTipoUbicacion Identificador del tipo de Ubicación
	 * @param idTipoPoliza Identificador del tipo de poliza.
	 * @param soatRequerido indica si el soat es requerido.
	 * @param responsabCivil indica si hay responasbilidad civil.
	 * @throws GWorkException Manejador de excepciones.
	 */
	public void modificarPolicyAssignmentControl(Long idTipoAsignatario,
			Long idTipoUbicacion,Long idTipoPoliza, boolean soatRequerido,
			boolean responsabCivil)throws GWorkException;

	/**
	 * Obtiene una lista de objetos {@link ControlAssignationPolicies}.
	 * @param idTipoAsignatario Identificador del tipo de asignatario.
	 * @param idTipoUbicacion Identificador del tipo de ubicación.
	 * @return lista de objetos PolicyAssignementTypeControl.
	 * @throws GWorkException Manejador de excepciones.
	 */
	public List<ControlAssignationPolicies> listaControlAssignationsPolicies(
			Long idTipoAsignatario, Long idTipoUbicacion)
	throws GWorkException;

	/**
	 * Elimina un objeto ControlAssignationPolicies.
	 * @param id identificador de PolicyAssignementTypeControl.
	 * @throws GWorkException Manejador de excepciones.
	 */
	public void eliminarControlAssignationsPolicies(Long id)
	throws GWorkException;

	/**
	 * Consulta un ControlAssignationPolicies 
	 * utlizando los parametros que se listan a continuacion.
	 * @param lgtCodigo identificador de LegateeType
	 * @param lctCodigo identificador de LocationType
	 * @return un objeto de tipo ControlAssignationsPolicies
	 * @throws GWorkException 
	 */
	public ControlAssignationPolicies consultarCtrlTipoAsignacion(
			Long lgtCodigo, Long lctCodigo) throws GWorkException;
	
	/**
	 * Consulta un ControlAssignationPolicies 
	 * utlizando los parametros que se listan a continuacion.
	 * @param lgtCodigo identificador de LegateeType
	 * @param lctCodigo identificador de LocationType
	 * @param pltCodigo identificador de PoliciesTypes
	 * @return un objeto de tipo ControlAssignationsPolicies
	 * @throws GWorkException 
	 */
	public ControlAssignationPolicies consultarCAP(
			Long lgtCodigo, Long lctCodigo, Long pltCodigo) throws GWorkException;

	
}
