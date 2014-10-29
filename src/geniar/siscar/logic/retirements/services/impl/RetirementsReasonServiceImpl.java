package geniar.siscar.logic.retirements.services.impl;

import geniar.siscar.logic.consultas.SearchVehicles;
import geniar.siscar.logic.retirements.services.RetirementsReasonService;
import geniar.siscar.model.RetirementsReasons;
import geniar.siscar.model.Vehicles;
import geniar.siscar.persistence.RetirementsReasonsDAO;
import gwork.exception.GWorkException;

import java.util.List;

public class RetirementsReasonServiceImpl implements RetirementsReasonService {

	public RetirementsReasons ConsultarMotivoRetiroPorId(String nombre) throws GWorkException {
		return null;
	}

	public RetirementsReasons ConsultarMotivoRetiroPorNombre(String nombre)throws GWorkException {
		return SearchVehicles.ConsultarMotivoRetiroPorNombre(nombre);
	}

	public Vehicles ConsultarMotivoRetiroPorPlacaVehiculo(String placa) throws GWorkException {
		return SearchVehicles.ConsultarMotivoRetiroPorPlacaVehiculo(placa);
	}

	public List<RetirementsReasons> getListRetirementReasons() throws GWorkException {
		return new RetirementsReasonsDAO().findAll();
	}

}
