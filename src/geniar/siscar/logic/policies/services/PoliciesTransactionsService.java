package geniar.siscar.logic.policies.services;

import java.util.Date;
import java.util.List;

import geniar.siscar.model.Inconsistencies;
import geniar.siscar.model.LegateesTypes;
import geniar.siscar.model.Policies;
import geniar.siscar.model.PoliciesVehicles;
import gwork.exception.GWorkException;

public interface PoliciesTransactionsService {

	/**
	 * Retira un vehiculo de una póliza.
	 * 
	 * @param plsNumero
	 *            Numero de póliza de la cual se va retirar el vehiculo.
	 * @param placaVehiculo
	 *            placa del vehiculo que se va a retirar.
	 * @param idTipoTransac
	 *            Tipo de transaccion mensual.
	 * @throws GWorkException
	 *             Manejador de excepciones.
	 */
	public void retiroVehiculoPoliza(Long plsNumero, String placaVehiculo,
			Long idTipoTransac, String login, Date plcFechaFinPolVhc) throws GWorkException;

	/**
	 * Traslada un vehiculo de una poliza a otra.
	 * 
	 * @param idTipoTransacMensual
	 *            Tipo de transacción mensual.
	 * @param placaVehiculo
	 *            Placa del vehiculo involucrado.
	 * @param plcFechaInicioPolVhc
	 *            Fecha en la que el vehiculo<br>
	 *            inicia en la nueva póliza.
	 * @param plcFechaFinPolVhc
	 *            Fecha en la que el vehiculo<br>
	 *            termina en la nueva póliza.
	 * @param polizaRetirada
	 *            poliza de la cual se va a retirar el vehiculo.
	 * @param polizaAsociada
	 *            poliza a la cual se va a adicionar el vehiculo.
	 * @throws GWorkException
	 *             Manejador de excepciones.
	 */
	public void trasladoTipoPoliza(Long idTipoTransacMensual,
			String placaVehiculo, Date plcFechaInicioPolVhc,
			Date plcFechaFinPolVhc, Long idPolizaRetirada,
			Policies polizaAsociada, String login, PoliciesVehicles newPV,
			Float plsValorPrima, Float plsValorContribucion,
			Float plsValorTotal, Float plsValorAseg) throws GWorkException;

	/**
	 * Adiciona un vehiculo a una poliza.
	 * 
	 * @param idTipoTransacMensual
	 *            Identificador del tipo de transacción mensual.
	 * @param placaVehiculo
	 *            placa del vehiculo que se va a adicionar.
	 * @param idTipoPoliza
	 *            Identificador del tipo de poliza.
	 * @param plcFechaInicioPol
	 *            Fecha en la que inicia el vehiculo en la póliza.
	 * @param plsNumero
	 *            Número de la poliza a la que se va a adicionar.
	 * @throws GWorkException
	 *             Manejador de excepciones.
	 */
	public void adicionVehiculoPoliza(Long idTipoTransacMensual,
			String placaVehiculo, Long idTipoPoliza, Date plcFechaInicioPol,
			Long plsNumero, Float valorPrima, Float valorIva,
			Float valorAsegurado, String numeroFactura, String login,
			LegateesTypes legateesTypes, String pvsCarnetAsignatario,
			Long estado, Long transaccionPantalla) throws GWorkException;

	/**
	 * Consulta todas las pólizas asociadas a un vehiculo.
	 * 
	 * @param placaVehiculo
	 *            placa del vehiculo.
	 * @return List<Policies> Listado de las polizas asociadas a un vehiculo.
	 * @throws GWorkException
	 *             Manejador de excepciones.
	 */
	public List<Policies> consultarPolizasVehiculo(String placaVehiculo)
			throws GWorkException;

	/**
	 * Guarda la información encontrada al cargar el archivo de novedades.
	 * 
	 * @param lstIncs
	 *            Listado de inconsistencias.
	 * @param lstPvs
	 *            Listado de {@link PoliciesVehicles} (asociacion
	 *            Vehiculo-Poliza).
	 * @param login
	 *            Login del usuario que ejecuta la acción.
	 * @throws GWorkException
	 *             Manejador de excepciones.
	 */
	public void guardarNovedades(List<Inconsistencies> lstIncs,
			List<PoliciesVehicles> lstPvs, String login) throws GWorkException;
}
