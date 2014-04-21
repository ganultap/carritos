package geniar.siscar.logic.vehicle.services;

import geniar.siscar.model.VehiclesTypes;
import gwork.exception.GWorkException;

import java.util.List;

public interface TypeVehicleService {
	public void crearTipoVehiculo(String tvchNombre)throws GWorkException;

	public VehiclesTypes consultarTipoVehiculo(String tvchNombre)throws GWorkException;
	
	public VehiclesTypes consultarTipoVehiculoById(Long vhtCodigo)throws GWorkException;

	public void eliminarTipoVehiculo(String tvchNombre)throws GWorkException;
	
	public void modificarTipoVehiculo(Long vhtCodigo, String vhtNombreNew)throws GWorkException;

	List<VehiclesTypes> listTipoVehiculo()throws GWorkException;
}
