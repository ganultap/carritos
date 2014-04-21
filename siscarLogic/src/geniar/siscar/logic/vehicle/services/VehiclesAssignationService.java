package geniar.siscar.logic.vehicle.services;

import geniar.siscar.model.AsignationDevolution;
import gwork.exception.GWorkException;

import java.util.List;

public interface VehiclesAssignationService {

	public List<AsignationDevolution> consultarEntregaVehiculoPorPlacaVehiculo(String placa)throws GWorkException;

}
