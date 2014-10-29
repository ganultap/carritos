package geniar.siscar.logic.vehicle.services;

import geniar.siscar.model.VehiclesAssignation;
import gwork.exception.GWorkException;

public interface AssignmentChangeService {

	public void modificarAsignatarioAsignacion(
			VehiclesAssignation vehiclesAssignation, String EmailAsignatario,
			Long idZone, String CarnetAsistente, String EmailAsistente,
			String NombreAsistente) throws GWorkException;
}
