package geniar.siscar.logic.devolution.services.impl;

import geniar.siscar.logic.devolution.services.DevolutionService;
import geniar.siscar.model.Devolutions;
import geniar.siscar.model.Vehicles;
import geniar.siscar.persistence.DevolutionsDAO;
import gwork.exception.GWorkException;

import java.util.Date;

public class DevolutionServiceImpl implements DevolutionService {

	public void crearDevolucionVehiculo(Date fechaEntrega, Long kilometraje, Long estadoVehiculo,
			Float devolucionTarifaAsignacion, Float cobroTarifaAsignacion, Vehicles Vehiculo,
			String tipoAsignacion) throws GWorkException {		
		
		Devolutions devolutions = new Devolutions();
		devolutions.setDvlFechaEntrega(fechaEntrega);
		devolutions.setDvlKilometraje(kilometraje.toString());
		devolutions.setDvlEstadoVeh(estadoVehiculo.toString());
		devolutions.setDvlDevTarifAsig(devolucionTarifaAsignacion);
		devolutions.setDvlCobroTarifAsig(cobroTarifaAsignacion);
		devolutions.setVehicles(Vehiculo);
		devolutions.setDvlTipoAsignatario(tipoAsignacion);
		
		new DevolutionsDAO().save(devolutions);
	}	
}
