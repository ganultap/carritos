/**
 * 
 */
package geniar.siscar.logic.vehicle.services;

import geniar.siscar.model.LocationsTypes;
import gwork.exception.GWorkException;

import java.util.List;

/**
 * @author Jorge
 * 
 */
public interface TypesLocationService {
	public LocationsTypes consultarUbicacion(String lctNombre)throws GWorkException;
	public List<LocationsTypes> listUbicacion() throws GWorkException;
	public LocationsTypes consultarUbicacion (Long codigo) throws GWorkException;
}
