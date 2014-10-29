package geniar.siscar.logic.parameters.services.impl;

import java.util.List;

import geniar.siscar.logic.parameters.services.LocationTypesService;
import geniar.siscar.model.LocationsTypes;
import geniar.siscar.persistence.ILocationsTypesDAO;
import geniar.siscar.persistence.LocationsTypesDAO;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

public class LocationTypesServiceImpl implements LocationTypesService {

	public List<LocationsTypes> listLocationsTypes() throws GWorkException {

		ILocationsTypesDAO locationsTypesDAO = new LocationsTypesDAO();
		List<LocationsTypes> locationsTypes=locationsTypesDAO.findAll();

		if (locationsTypes==null||locationsTypes.isEmpty())
			throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));

		return locationsTypes;
	}

	public LocationsTypes consultarLocationTypesById(Long idLocationType) throws GWorkException {

		ILocationsTypesDAO locationsTypesDAO = new LocationsTypesDAO();
		String idLocation = null;
		if (idLocationType != null)
			idLocation = idLocationType.toString();

		if (!idLocation.equalsIgnoreCase("-1"))
			if (locationsTypesDAO.findById(idLocationType) == null)
				throw new GWorkException(Util.loadErrorMessageValue("TIPOUBICACIONEXISTEN"));
		if (!idLocation.equalsIgnoreCase("-1"))
			return locationsTypesDAO.findById(idLocationType);

		return null;
	}

}
