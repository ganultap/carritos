package geniar.siscar.logic.billing.services;

import geniar.siscar.model.AccountingParameters;
import geniar.siscar.model.VOAssignationProof;
import gwork.exception.GWorkException;

import java.util.Date;
import java.util.List;

/**
 * The Interface AssignationProofService.
 */
public interface AssignationProofService {

	/**
	 * List comprobante asignacion.
	 *
	 * @param periodo the periodo
	 * @return the list
	 * @throws GWorkException the g work exception
	 */
	public List<VOAssignationProof> listComprobanteAsignacion(Long periodo)
			throws GWorkException;

	/**
	 * Consultar vigencia centro costo.
	 *
	 * @param centroCosto the centro costo
	 * @param fechaIni the fecha ini
	 * @param fechaFin the fecha fin
	 * @return the string
	 * @throws GWorkException the g work exception
	 */
	public String consultarVigenciaCentroCosto(String centroCosto,
			Date fechaIni, Date fechaFin) throws GWorkException;

	/**
	 * Disponibilidad combustible cc.
	 *
	 * @param centroCosto the centro costo
	 * @param valorPorcentaje the valor porcentaje
	 * @param placa the placa
	 * @param parameters the parameters
	 * @return the string
	 * @throws GWorkException the g work exception
	 */
	public String disponibilidadCombustibleCC(String centroCosto,
			String valorPorcentaje, String placa,
			AccountingParameters parameters) throws GWorkException;

	/**
	 * Generar comprobante asignacion.
	 *
	 * @param idPerido the id perido
	 * @param listaAsignaciones the lista asignaciones
	 * @param login the login
	 * @throws GWorkException the g work exception
	 */
	public void generarComprobanteAsignacion(Long idPerido,
			List<VOAssignationProof> listaAsignaciones, String login)
			throws GWorkException;
}
