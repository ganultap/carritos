package geniar.siscar.logic.vehicle.services.impl;

import geniar.siscar.logic.consultas.SearchVehicles;
import geniar.siscar.logic.vehicle.services.VehicleService;
import geniar.siscar.logic.vehicle.services.VehiclesRetirementService;
import geniar.siscar.model.RetirementsReasons;
import geniar.siscar.model.RetirementsTypes;
import geniar.siscar.model.Vehicles;
import geniar.siscar.model.VehiclesStates;
import geniar.siscar.persistence.EntityManagerHelper;
import geniar.siscar.persistence.RetirementsReasonsDAO;
import geniar.siscar.persistence.RetirementsTypesDAO;
import geniar.siscar.persistence.VehiclesDAO;
import geniar.siscar.persistence.VehiclesStatesDAO;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

import java.util.Date;
import java.util.List;

public class VehiclesRetirementServiceImpl implements VehiclesRetirementService {

	public void RetiroVehiculo(String vhcPlacaDiplomatica, Long idMotivoRetiro,
			String descripcion,  String login) throws GWorkException {

		List<Vehicles> listVehicles = null;
		Vehicles vehicles = null;
		List<VehiclesStates> listVehiclesStates = null;
		VehiclesStates vehiclesStates = null;
		VehicleService vehicleService = null;
		RetirementsTypes retirementsTypes = null;
		RetirementsReasons retirementsReasons = null;
		listVehicles = new VehiclesDAO()
				.findByVhcPlacaDiplomatica(vhcPlacaDiplomatica.toUpperCase()
						.trim());
String temp="";
		if (listVehicles != null && listVehicles.size() > 0) {
			vehicles = listVehicles.get(listVehicles.size()-1);
temp=""+vehicles.getVhcCodigo();
			if (vehicles == null)
				throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));

			//modificado el 13 de marzo 2012
//			SearchVehicles.consultarVehiculosNoRetirados(vhcPlacaDiplomatica
//					.toUpperCase().trim());

			listVehiclesStates = new VehiclesStatesDAO().findByVhsNombre(Util
					.loadMessageValue("ESTADO_RETIRADO"));

			if (listVehiclesStates != null && listVehiclesStates.size() > 0)
				vehiclesStates = listVehiclesStates.get(0);
			temp=""+vehiclesStates.getVhsNombre();

			if (vehiclesStates == null)
				throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));

			retirementsTypes = ConsultarMotivoRetiro(idMotivoRetiro);

			if (retirementsTypes == null)
				throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));

			retirementsReasons = new RetirementsReasons();
			retirementsReasons.setRerDescripcion(descripcion);
			retirementsReasons.setRerFechaRetiro(new Date());
			retirementsReasons.setRetirementsTypes(retirementsTypes);
			retirementsReasons.setRerLoginUsr(login);
			guardarRazonRetiro(retirementsReasons);
			vehicles.setVehiclesStates(vehiclesStates);
			vehicles.setRetirementsReasons(retirementsReasons);
			
			//new RetirementsReasonsDAO().save(retirementsReasons);
			//new VehiclesDAO().update(vehicles);
			
			vehicleService = new VehicleServiceImpl();
			vehicleService.actualizarVehiculo(vehicles);

		} else
			throw new GWorkException(Util
					.loadErrorMessageValue("PLACA.EXISTEN"));
	}

	public void guardarRazonRetiro(RetirementsReasons reasons)
			throws GWorkException {
		try {
			validarSession();
			EntityManagerHelper.beginTransaction();
			new RetirementsReasonsDAO().save(reasons);
			EntityManagerHelper.commit();
			limpiarSession();
		} catch (Exception e) {
			EntityManagerHelper.rollback();
			validarSession();
			limpiarSession();
			throw new GWorkException(e.getMessage());
		}
	}

	public void validarSession() {
		if (EntityManagerHelper.getEntityManager().isOpen())
			EntityManagerHelper.getEntityManager().close();
	}

	public void limpiarSession() {
		EntityManagerHelper.getEntityManager().clear();
		EntityManagerHelper.closeEntityManager();
	}

	public RetirementsTypes ConsultarMotivoRetiro(Long idMotivo) {
		return new RetirementsTypesDAO().findById(idMotivo);
	}
}
