package geniar.siscar.logic.policies.services;

import geniar.siscar.model.PoliciesTypes;
import gwork.exception.GWorkException;

import java.util.List;

public interface PoliciesTypeService {
	
	/**
	 * Consulta todos los tipos de polizas.
	 * @return Listado de todas las polizas.
	 * @throws GWorkException Manejador de excepciones. 
	 */
	public List<PoliciesTypes> listPoliciesTypes() throws GWorkException;

	/**
	 * Consulta todos los tipos de polizas para la pagina de parametros.
	 * @return Listado de todas las polizas para la pagina de parametros.
	 * @throws GWorkException Manejador de excepciones.
	 */
	public List<PoliciesTypes> listPoliciesTypesParam() throws GWorkException;
	
	/**
	 * Consulta todos los tipos de polizas para la pagina de transacciones.
	 * @return Listado de todas las polizas para la pagina de transacciones.
	 * @throws GWorkException Manejador de excepciones.
	 */
	public List<PoliciesTypes> listPoliciesTypesTransac() throws GWorkException;
	
	/**
	 * Consulta un objeto de tipo {@link PoliciesTypes} por su identificador.
	 * @param idPolicyType Identificador del tipo de poliza.
	 * @return Un objeto de tipo {@link PoliciesTypes}.
	 * @throws GWorkException Manejador de excepciones.
	 */	
	public PoliciesTypes consultarPoliciesTypesById(Long idPolicyType) throws GWorkException;
	
	/**
	 * Consulta un tipo de poliza utilizando su nombre como parametro de busqueda.
	 * @param nombre nombre del tipo de poliza que se va a buscar.
	 * @return Un objeto de tipo {@link PoliciesTypes}.
	 * @throws GWorkException Manejador de excepciones.
	 */
	public PoliciesTypes consultarTipoPolizaPorNombre(String nombre) throws GWorkException;
}
