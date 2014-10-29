package geniar.siscar.logic.fuels.services;

import geniar.siscar.model.FuelTanks;
import gwork.exception.GWorkException;

import java.util.List;

public interface FuelTankService {
	/**
	 * Elimina un objeto de tipo {@link FuelsTanks} con el nombre especificado.
	 * @param idFuelsTank Identificador del tanque.
	 * @throws GWorkException Manejador de Excepciones.
	 */
	public void eliminarFuelTanks(Long idFuelsTank) throws GWorkException;
	
	/**
	 * Consulta un tanque por nombre.
	 * @param ftaNombre Nombre del tanque.
	 * @return Un objeto de tipo {@link FuelTank}.
	 * @throws GWorkException Manejador de Excepciones.
	 */
	public List<FuelTanks> consultarFuelTank(String ftaNombre) throws GWorkException;

	/**
	 * Consulta un objeto de tipo {@link FuelsTanks}.
	 * @param idTanqueCombustible Identificador del tanque.
	 * @return un objeto de tipo {@link FuelsTanks}.
	 * @throws GWorkException Manejador de Excepciones.
	 */	
	public FuelTanks consultarFuelTankPorID(Long idTanqueCombustible) throws GWorkException;
	
	/**
	 * Consulta todos los objetos tipo {@link FuelTanks}.
	 * @return Lista de objetos {@link FuelTanks}.
	 * @throws GWorkException Manejador de Excepciones.
	 */
	public List<FuelTanks> consultarTodosFuelTanks() throws GWorkException;
	
	/**
	 * Crea un nuevo objeto de tipo {@link FuelTanks}.
	 * @param ftaNombre Nombre del Tanque de combustible.
	 * @param idFuelsTypes Identificador del tipo de combustible.
	 * @param capacidad Capacidad del tanque.
	 * @throws GWorkException Manejador de Excepciones.
	 */	
	public void crearFuelTanks(String ftaNombre, Long idFuelsTypes,
			Float capacidad) throws GWorkException;
	
	/**
	 * Modifica un objeto de tipo {@link FuelTanks}.
	 * @param idFuelsTank Identificador del tanque.
	 * @param ftaNombreNuevo Nuevo nombre del tanque.
	 * @param idFuelsTypes Identificador del tipo de combustible.
	 * @param capacidad Capacidad del tanque.
	 * @throws GWorkException Manejador de Excepciones.
	 */
	public void modifiarFuelTanks(Long idFuelsTank, String ftaNombreNuevo,
			Long idFuelsTypes, Float capacidad) throws GWorkException;
}
