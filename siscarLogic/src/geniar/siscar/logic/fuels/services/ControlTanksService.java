package geniar.siscar.logic.fuels.services;

import geniar.siscar.model.ControlsTanks;
import gwork.exception.GWorkException;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface ControlTanksService {

	/**
	 * Metodo encargado de la creacion de tanques
	 * 
	 * @param nombreTanque
	 * @param idTipoCombustible
	 * @param valorTanque
	 * @param fechaTanqueada
	 * @param cantidadGalones
	 * @throws GWorkException
	 */
	public void guardarControlTanques(Long idTanque, Date fechaTanqueada,
			BigDecimal cantidadGalones) throws GWorkException;

	/**
	 * Consulta los galones actuales
	 * 
	 * @param valor
	 * @param idTanque
	 * @return
	 * @throws GWorkException
	 */
	public Float consultarGalonesActuales(Float valor, Long idTanque)
			throws GWorkException;

	/**
	 * Elimina un control
	 * 
	 * @param idTanks
	 * @throws GWorkException
	 */
	public void eliminarControl(Long idTanks) throws GWorkException;

	/**
	 * consultarControlTanquePorIdTanque
	 * 
	 * @param idTanque
	 * @return
	 * @throws GWorkException
	 */
	public ControlsTanks consultarControlTanquePorIdTanque(Long idTanque)
			throws GWorkException;

	/**
	 * @param idControlTanques
	 * @param idTanque
	 * @param fechaTanqueada
	 * @param suma
	 * @param cantidadGalones
	 * @throws GWorkException
	 */
	public void actualizarControl(Long idControlTanques, Long idTanque,
			Date fechaTanqueada, Float suma, BigDecimal cantidadGalones)
			throws GWorkException;

	/**
	 * @param idTanque
	 * @return
	 * @throws GWorkException
	 */
	public Double consultarValorActualControlTakns(Long idTanque)
			throws GWorkException;

	/**
	 * @return
	 * @throws GWorkException
	 */
	public List<ControlsTanks> consultarTanques() throws GWorkException;

}
