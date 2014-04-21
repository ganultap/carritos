package geniar.siscar.logic.fuels.services;

import gwork.exception.GWorkException;

public interface ServiceRegistryService {

	/***************************************************************************
	 * @param fecha
	 * @param hora
	 * @param idFuelTypeRequest
	 * @param placa
	 * @param login
	 * @param nombreSolicitante
	 * @param carneAsignatario
	 * @param carneSolicitante
	 * @param numeroGalones
	 * @param total
	 * @param observaciones
	 * @param idPump
	 * @param kilometrajeActual
	 * @param numeroReciboPago
	 * @param idCentroCosto
	 * @param usoDisponible
	 * @throws GWorkException
	 **************************************************************************/
	public void serviceRegistryFuel(
			Long idFuelTypeRequest, String placa, String login,
			String nombreSolicitante, String carneAsignatario,
			String carneSolicitante, Float numeroGalones, Float total,
			String observaciones, Long idPump, Long kilometrajeActual,
			Long numeroReciboPago, String centroCosto, Long usoDisponible,
			Long fuelType, Float capacidadTanque, Long cargoA) throws GWorkException;
}
