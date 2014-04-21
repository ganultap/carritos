package geniar.siscar.logic.vehicle.services.impl;

import geniar.siscar.logic.consultas.SearchVehicles;
import geniar.siscar.logic.vehicle.services.VehiclesAssignationService;
import geniar.siscar.model.AsignationDevolution;
import gwork.exception.GWorkException;

import java.util.List;

public class DeliveryVehiclesServiceImpl implements VehiclesAssignationService {

	public List<AsignationDevolution> consultarEntregaVehiculoPorPlacaVehiculo(String placa)throws GWorkException {
		return SearchVehicles.consultarEntregaVehiculoPorPlacaVehiculo(placa);
	}
}
