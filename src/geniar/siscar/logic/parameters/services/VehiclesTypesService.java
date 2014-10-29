package geniar.siscar.logic.parameters.services;

import java.util.List;

import geniar.siscar.model.VehiclesTypes;
import gwork.exception.GWorkException;

public interface VehiclesTypesService {

	public List<VehiclesTypes> listVehiclesTypes()throws GWorkException;
	
}
