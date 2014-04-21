package geniar.siscar.logic.devolution.services;

import geniar.siscar.model.Vehicles;
import gwork.exception.GWorkException;

import java.util.Date;

public interface DevolutionService {

	public void crearDevolucionVehiculo(Date fechaEntrega, Long kilometraje, Long estadoVehiculo,
			Float devolucionTarifaAsignacion, Float cobroTarifaAsignacion, Vehicles Vehiculo,
			String tipoAsignacion) throws GWorkException;

	}
