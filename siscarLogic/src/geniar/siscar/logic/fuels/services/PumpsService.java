package geniar.siscar.logic.fuels.services;

import geniar.siscar.model.Pumps;
import gwork.exception.GWorkException;

import java.util.List;

/**
 * 
 * @author Mauricio Cuenca Narváez
 * 
 */
public interface PumpsService {

	/**
	 * Crea un nuevo objeto de tipo {@link Pumps}.
	 * 
	 * @param pumNombre
	 *            Nombre del surtidor.
	 * @param idTanque
	 *            Identificador del tanque de combsutible.
	 * @throws GWorkException
	 *             Manejador de Excepciones.
	 */
	public void crearPump(String pumNombre, Long idTanque)
			throws GWorkException;

	/**
	 * Modifica un objeto de tipo {@link Pumps}.
	 * 
	 * @param idFuelTank
	 *            Identificador del Tanque.
	 * @param idPump
	 *            Identificador del surtidor.
	 * @param pumNuevoNombre
	 *            Nuevo nombre a asignar al surtidor.
	 * @throws GWorkException
	 *             Manejador de excepciones.
	 */
	public void modificarPump(Long idFuelTank, Long idPump,
			String pumNuevoNombre) throws GWorkException;

	/**
	 * Consulta un surtidor por nombre.
	 * 
	 * @param pumNombre
	 *            Nombre del surtidor.
	 * @return Un objeto de tipo {@link Pumps}.
	 * @throws GWorkException
	 *             Manejador de Excepciones.
	 */
	public Pumps consultarPump(String pumNombre) throws GWorkException;

	/**
	 * Consulta todos los objetos tipo {@link Pumps}.
	 * 
	 * @return Lista de objetos {@link Pumps}.
	 * @throws GWorkException
	 *             Manejador de Excepciones.
	 */
	public List<Pumps> consultarTodosPumps() throws GWorkException;

	/**
	 * Elimina un objeto de tipo {@link Pumps} con el nombre especificado.
	 * 
	 * @param idSurtidor
	 *            Identificador del surtidor.
	 * @throws GWorkException
	 *             Manejador de Excepciones.
	 */
	public void eliminarPump(Long idSurtidor) throws GWorkException;

	/**
	 * Consulta un objeto de tipo {@link Pumps} por su ID.
	 * 
	 * @param idPump
	 *            Identificador del Surtidor.
	 * @return un objeto de tipo {@link Pumps}
	 * @throws GWorkException
	 *             Manejador de Excepciones.
	 */
	public Pumps consultarPumpPorId(Long idPump) throws GWorkException;
	
	
	/**
	 * @return
	 * @throws GWorkException
	 */
	public String consultPums() throws GWorkException;

}