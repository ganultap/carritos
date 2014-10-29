/**
 * 
 */
package geniar.siscar.logic.billing.services;

import geniar.siscar.model.BillingAccountVO;
import geniar.siscar.model.Period;
import gwork.exception.GWorkException;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * @author alvaro.hincapie
 * 
 */
public interface BillingAccountService {

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
			Date fechaInicio, Date fechaFin) throws GWorkException;

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
	 * @param descripcions
	 *            Descrición del periodo.
	 * @throws GWorkException
	 *             Manejador de Excepciones.
	 */
	public void modificarPeriod(Long idPeriodo, String nombre,
			String descripcion, Date fechaIni, Date fechaFin)
			throws GWorkException;

	/**
	 * @return Lista de objetos tipo {@link PlainFileParameter}.
	 * @throws GWorkException
	 *             Manejador de Excepciones.
	 */
	public List<Period> listarTodosPeriod() throws GWorkException;

	public List<Long> listVehicles() throws GWorkException;

	public List<BillingAccountVO> listVehiclesFlatFile(Long per)
			throws GWorkException;

	/**
	 * Eliminar Periodo
	 * 
	 * @param id
	 * @throws GWorkException
	 */
	public void eliminarPeriodo(Long id) throws GWorkException;

	public void action_aceptar(List<BillingAccountVO> vehiculos, Long idPeriodo)
			throws GWorkException, ParseException;

	public void CobroCiatCasaCiat() throws GWorkException;

	public void ListarVehiculosCiatCasaCiat() throws GWorkException;

	public List<BillingAccountVO> listVehiclesFlatFileCiatCasaCiat(
			Date dtfechaInicio, Date dtfechaFin) throws GWorkException;

	public void ReporteCobroCiatCasaCiat(List<BillingAccountVO> vehiculos,
			Long idPeriodo) throws GWorkException;
}
