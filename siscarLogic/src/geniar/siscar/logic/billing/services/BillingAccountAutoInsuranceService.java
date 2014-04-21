/**
 * 
 */
package geniar.siscar.logic.billing.services;

import geniar.siscar.model.BillingAccountAutoInsuranceVO;
import geniar.siscar.model.FlatFile;
import geniar.siscar.model.Period;
import gwork.exception.GWorkException;
import java.util.Date;
import java.util.List;

/**
 * @author alvaro.hincapie
 * 
 */
public interface BillingAccountAutoInsuranceService {

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

	public List<BillingAccountAutoInsuranceVO> listVehiclesFlatFile(
			String anoCobro, Period periodo) throws GWorkException;

	public List<FlatFile> action_aceptar(
			List<BillingAccountAutoInsuranceVO> vehiculos, String loginPage,
			String anoCobro, Period Periodo) throws GWorkException;

	public Period consultarPeriodByAno(String Ano) throws GWorkException;
	
	public Period consultarPeriodByAnoAutoSeguro(String Ano) throws GWorkException;

	// public void imprimirRecibo(List<FlatFile> listaff) throws GWorkException;
}
