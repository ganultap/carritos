/**
 * 
 */
package geniar.siscar.logic.billing.services;

import java.util.List;

import geniar.siscar.model.PlainFileParameter;
import gwork.exception.GWorkException;

/**
 * @author Mauricio Cuenca
 *
 */
public interface PlainFileParameterService {
	
	/**
	 * Crea un nuevo objeto de tipo {@link PlainFileParameter}.
	 * @param idTipoMoneda Identificador del tipo de moneda.
	 * @param idTipoNovedad Identificador del tipo de novedad.
	 * @param conceptoNomina Concepto por el cual se genera.
	 * @param descripcion Descrición del concepto.
	 * @throws GWorkException Manejador de Excepciones.
	 */
	public void crearPlainFileParameter(Long idTipoMoneda, Long idTipoNovedad,
			Long conceptoNomina, String descripcion) throws GWorkException;
	
	/**
	 * Consulta un objeto de tipo {@link PlainFileParameter}
	 * @param idTipoMoneda Identificador del tipo de moneda.
	 * @param idTipoNovedad Identificador del tipo de novedad.
	 * @throws GWorkException Manejador de Excepciones.
	 */
	public PlainFileParameter consultarPlainFileParameter( 
			Long idTipoMoneda, Long idTipoNovedad ) throws GWorkException;
	
	/**
	 * Modifica un objeto de tipo {@link PlainFileParameter}
	 * @param idTipoMoneda Identificador del tipo de moneda.
	 * @param idTipoNovedad Identificador del tipo de novedad.
	 * @param conceptoNomina Concepto por el cual se genera.
	 * @param descripcion Descrición del concepto.
	 * @throws GWorkException Manejador de Excepciones.
	 */
	public void modificarPlainFileParameter(Long idTipoMoneda,
		Long idTipoNovedad, Long conceptoNomina, String descripcion)
			throws GWorkException;
	
	/**
	 * @return Lista de objetos tipo {@link PlainFileParameter}.
	 * @throws GWorkException Manejador de Excepciones.
	 */
	public List<PlainFileParameter> listarTodosPlainFileParameters() 
		throws GWorkException;
}
