/**
 * 
 */
package geniar.siscar.logic.policies.services;

import java.util.Date;
import java.util.List;

import geniar.siscar.model.Inconsistencies;
import geniar.siscar.model.PoliciesInvoice;
import gwork.exception.GWorkException;

/**
 * @author Geniar
 *
 */
public interface InconsistenciesService {
	
	/**
	 * Crea una nueva inconsistencias.
	 * @param estado de la inconsistencia.
	 * @param observacion de la inconsistencia.
	 * @param fechaCargue Fecha en la que se creo la inconsistencia.
	 * @param usrLogin Usuario que genero la operación.
	 * @param idTipoInconsistencia Tipo de inconsistencia.
	 * @param idPoliza Identificador de la poliza.
	 * @throws GWorkException Manejador de excepciones.
	 */
	public void crearInconsistencia(Long estado,
			String observacion, Date fechaCargue,
			String usrLogin, Long idTipoInconsistencia,
			Long idPoliza) throws GWorkException;
	
	/**
	 * Crea un conjunto de inconsistencias.
	 * @param lst listado de las inconsistencias que se van a crear.
	 * @throws GWorkException Manejador de excepciones.
	 */
	public void crearInconsistencias(List<Inconsistencies> lst)
		throws GWorkException;
	
	/**
	 * Modifica una inconsistencia.
	 * @param estado
	 * @param observacion
	 * @param fechaCargue
	 * @param usrLogin
	 * @param idTipoInconsistencia
	 * @param idPoliza
	 * @throws GWorkException
	 */
	public void modificarInconsistencia(Long estado,
			String observacion, Date fechaCargue,
			String usrLogin, Long idTipoInconsistencia,
			Long idPoliza) throws GWorkException;
	
	/**
	 * Modifica un conjunto de inconsistencias.
	 * @param lst listado de inconsistencias.
	 * @throws GWorkException Manejador de excepciones.
	 */
	public void modificarInconsistenciasFactura(PoliciesInvoice invoice)
		throws GWorkException;
	
	/**
	 * Elimina una inconsistencia
	 * @param inc Inconsistencia que se va a eliminar
	 * @throws GWorkException Manejador de excepciones.
	 */
	public void eliminarInconsistencia(Inconsistencies inc) throws GWorkException;
	
	/**
	 * Elimina una inconsistencia
	 * @param inc Inconsistencia que se va a eliminar
	 * @throws GWorkException Manejador de excepciones.
	 */
	public void eliminarInconsistencia(Long idInc) throws GWorkException;
		
}
