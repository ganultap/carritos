package geniar.siscar.logic.vehicle.services;

import geniar.siscar.model.RetirementsTypes;
import gwork.exception.GWorkException;

public interface VehiclesRetirementService {

	public void RetiroVehiculo(String placa, Long idMotivoRetiro, String descripcion,  String login)
			throws GWorkException;

	public RetirementsTypes ConsultarMotivoRetiro(Long idMotivo);

}
