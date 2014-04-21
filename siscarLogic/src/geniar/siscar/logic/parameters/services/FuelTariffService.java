package geniar.siscar.logic.parameters.services;

import geniar.siscar.model.Tariffs;
import geniar.siscar.model.TariffsTypes;
import gwork.exception.GWorkException;

import java.util.Date;
import java.util.List;

public interface FuelTariffService {
	
	/**
	 * Crea una nueva tarifa de combustible.
	 * @param idTipoCombustible Identificador del tipo de combustible.
	 * @param idTipoTarifa Identificador del tipo de tarifa.
	 * @param fechaDesde Fecha a partir de la cual está vigente la tarifa.
	 * @param fechaHasta Fecha a partir de la cual no está vigente la tarifa.
	 * @param valorTarifa Valor de la nueva tarifa de combustible.
	 * @param nombreTipoTarifa Nombre del tipo de tarifa.
	 * @param vigencia Año de vigencia que tiene la tarifa.
	 * @throws GWorkException Manejador de Excepciones.
	 */
	public void crearTarifaCombustible(Long idTipoCombustible, Long idTipoTarifa,
			String nombreTipoTarifa, Date fechaDesde, Date fechaHasta,
			Long vigencia, Float valorTarifa) throws GWorkException;
	
	
	/**
	 * Consulta una tarifa de combustible ya existente.
	 * @param idTipoCombustible Identificador del tipo de combustible.
	 * @param idTipoTarifa Identificador del tipo de tarifa.
	 * @throws GWorkException Manejador de Excepciones.
	 */
	public Tariffs consultarTarifaCombustible(Long idTipoCombustible, Long idTipoTarifa,
			String nombreTipoTarifa) throws GWorkException;


	/**
	 * Consulta los tipos de tarifa CIAT y CALI, que son los que aplican en este caso. 
	 * @return Lista de los tipos de tarifa asociados a tarifas de combustible.
	 * @throws GWorkException Manejador de Excepciones.
	 */
	public List<TariffsTypes> consultarTiposTarifa() throws GWorkException;
}
