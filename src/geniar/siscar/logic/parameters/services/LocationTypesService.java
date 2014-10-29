package geniar.siscar.logic.parameters.services;

import geniar.siscar.model.LocationsTypes;
import gwork.exception.GWorkException;

import java.util.List;

public interface LocationTypesService {

	public List<LocationsTypes> listLocationsTypes()throws GWorkException;
	public LocationsTypes consultarLocationTypesById(Long idLocationType) throws GWorkException;
}
