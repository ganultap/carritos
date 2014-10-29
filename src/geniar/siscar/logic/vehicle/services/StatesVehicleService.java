package geniar.siscar.logic.vehicle.services;

import geniar.siscar.model.VehiclesStates;

import java.util.List;

public interface StatesVehicleService {
	public VehiclesStates ConsultarEstado(Long estCodigo);

	public List<VehiclesStates> listadoEstadoVehiculos();
}
