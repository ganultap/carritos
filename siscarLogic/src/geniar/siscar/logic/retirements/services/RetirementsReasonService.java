package geniar.siscar.logic.retirements.services;

import java.util.List;

import geniar.siscar.model.RetirementsReasons;
import geniar.siscar.model.Vehicles;
import gwork.exception.GWorkException;

public interface RetirementsReasonService {

	public RetirementsReasons ConsultarMotivoRetiroPorNombre(String nombre)
			throws GWorkException;

	public RetirementsReasons ConsultarMotivoRetiroPorId(String nombre)
			throws GWorkException;

	public Vehicles ConsultarMotivoRetiroPorPlacaVehiculo(String nombre)
			throws GWorkException;

	public List<RetirementsReasons> getListRetirementReasons()
			throws GWorkException;

}
