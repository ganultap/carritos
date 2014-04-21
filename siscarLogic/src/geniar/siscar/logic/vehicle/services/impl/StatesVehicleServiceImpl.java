package geniar.siscar.logic.vehicle.services.impl;

import geniar.siscar.logic.vehicle.services.StatesVehicleService;
import geniar.siscar.model.VehiclesStates;
import geniar.siscar.persistence.IVehiclesStatesDAO;
import geniar.siscar.persistence.VehiclesStatesDAO;
import geniar.siscar.util.Util;

import java.util.List;

public class StatesVehicleServiceImpl implements StatesVehicleService {

	public VehiclesStates ConsultarEstado(Long estCodigo) {
		VehiclesStates VehiclesStates = new VehiclesStates();
		IVehiclesStatesDAO VehiclesStatesDAO = new VehiclesStatesDAO();

		try {
			VehiclesStates = VehiclesStatesDAO.findById(estCodigo);

			if (VehiclesStates == null) {
				throw new Exception(Util.loadErrorMessageValue("CONSULTA"));
			}

			return VehiclesStates;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}

	public List<VehiclesStates> listadoEstadoVehiculos() {
		IVehiclesStatesDAO VehiclesStatesDAO = new VehiclesStatesDAO();

		try {
			List<VehiclesStates> listado = VehiclesStatesDAO.findAll();

			if (listado.size() < 1) {
				throw new Exception(Util.loadErrorMessageValue("CONSULTA"));
			} else {
				return listado;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
