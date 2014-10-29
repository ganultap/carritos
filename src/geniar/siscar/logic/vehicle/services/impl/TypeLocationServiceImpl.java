/**
 * 
 */
package geniar.siscar.logic.vehicle.services.impl;

import geniar.siscar.logic.vehicle.services.TypesLocationService;
import geniar.siscar.model.LocationsTypes;
import geniar.siscar.persistence.ILocationsTypesDAO;
import geniar.siscar.persistence.LocationsTypesDAO;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

import java.util.List;

/**
 * @author Jorge
 * 
 */
public class TypeLocationServiceImpl implements TypesLocationService {

	/*
	 * (non-Javadoc)
	 * 
	 * @see geniar.siscar.logic.vehicle.services.ServiceLacations#consultarUbicacion(java.lang.String)
	 */
	public LocationsTypes consultarUbicacion(String lctNombre) throws GWorkException{
		ILocationsTypesDAO LocationsTypesDAO = new LocationsTypesDAO();
			List<LocationsTypes> listUbicacion = LocationsTypesDAO.findByLctNombre(lctNombre);
			if (listUbicacion.size() >= 1)
				return listUbicacion.get(0);
			 else 
				throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see geniar.siscar.logic.vehicle.services.ServiceLacations#listUbicacion()
	 */
	//TODO:CORREGIR TRY CATCH
	public List<LocationsTypes> listUbicacion() {
		ILocationsTypesDAO LocationsTypesDAO = new LocationsTypesDAO();
		try {
			/** Se chequea que el nombre ingraso exista */
			List<LocationsTypes> listUbicacion = LocationsTypesDAO.findAll();
			if (listUbicacion.size() < 1) {
				throw new Exception(Util.loadErrorMessageValue("CONSULTA"));
			} else {
				return listUbicacion;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public LocationsTypes consultarUbicacion(Long codigo) throws GWorkException {
		if(codigo==null){
			//TODO:Realizar Exception
			throw new GWorkException("");
		}
		ILocationsTypesDAO locationsTypesDAO = new LocationsTypesDAO();
		LocationsTypes     locationsTypes    = locationsTypesDAO.findById(codigo);
		return locationsTypes;
	}

}
