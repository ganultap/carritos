package geniar.siscar.logic.parameters.services.impl;

import geniar.siscar.logic.consultas.SearchOrderVehicleType;
import geniar.siscar.logic.parameters.services.VehiclesTypesService;
import geniar.siscar.model.VehiclesTypes;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

import java.util.List;

public class VehiclesTypesServiceImpl implements VehiclesTypesService {
	public List<VehiclesTypes> listVehiclesTypes() throws GWorkException {
		
		List<VehiclesTypes> listVehiclesTypes = new SearchOrderVehicleType().typeVehiclesOrder();

		if (listVehiclesTypes == null)
			// TODO realizar excepcion
			throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));

		return listVehiclesTypes;
	}
}
