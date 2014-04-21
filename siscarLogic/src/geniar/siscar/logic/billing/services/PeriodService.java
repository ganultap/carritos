/**
 * 
 */
package geniar.siscar.logic.billing.services;

import geniar.siscar.model.Period;
import gwork.exception.GWorkException;

import java.util.Date;
import java.util.List;

/**
 * @author alvaro.hincapie
 * 
 */
public interface PeriodService {

	/**
	 * Crea un nuevo objeto de tipo {@link PlainFileParameter}.
	 * 
	 * @param idTipoMoneda
	 *            Identificador del tipo de moneda.
	 * @param idTipoNovedad
	 *            Identificador del tipo de novedad.
	 * @param conceptoNomina
	 *            Concepto por el cual se genera.
	 * @param descripcion
	 *            Descrición del concepto.
	 * @throws GWorkException
	 *             Manejador de Excepciones.
	 */
	public void crearPeriod(String nombre, String descripcion,
			Date fechaInicio, Date fechaFin, Long TipoNovedad)
			throws GWorkException;

	/**
	 * Consulta un objeto de tipo {@link Period}
	 * 
	 * @param nombre
	 *            del periodo.
	 * @throws GWorkException
	 *             Manejador de Excepciones.
	 */
	public Period consultarPeriod(String nombre) throws GWorkException;

	/**
	 * @param idPeriodo
	 *            id del periodo.
	 * @throws GWorkException
	 *             Manejador de Excepciones.
	 */
	public Period consultarPeriodById(Long idPeriodo) throws GWorkException;

	/**
	 * Modifica un objeto de tipo {@link PlainFileParameter}
	 * 
	 * @param fechaIni
	 *            fecha inicio del periodo.
	 * @param fechaFin
	 *            fecha fin del periodo.
	 * @param nombre
	 *            nombre del periodo.
	 * @param descripcion
	 *            Descrición del periodo.
	 * @throws GWorkException
	 *             Manejador de Excepciones.
	 */
	public void modificarPeriod(Long idPeriodo, String nombre,
			String descripcion, Date fechaIni, Date fechaFin, Long TipoNovedad)
			throws GWorkException;

	/**
	 * @return Lista de objetos tipo {@link PlainFileParameter}.
	 * @throws GWorkException
	 *             Manejador de Excepciones.
	 */
	public List<Period> listarTodosPeriod() throws GWorkException;

	/**
	 * Eliminar Periodo
	 * 
	 * @param id
	 * @throws GWorkException
	 */
	public void eliminarPeriodo(Long id) throws GWorkException;

	public List<Period> periodsByeNoveltie(Long idNoveltyType)
			throws RuntimeException;

}
