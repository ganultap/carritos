package geniar.siscar.logic.vehicle.services.impl;

import geniar.siscar.logic.consultas.SearchVehicles;
import geniar.siscar.logic.vehicle.services.ReservesService;
import geniar.siscar.model.Requests;
import geniar.siscar.persistence.EntityManagerHelper;
import geniar.siscar.persistence.RequestsDAO;
import gwork.exception.GWorkException;

public class ReservesServiceImpl implements ReservesService {

	public void CancelarReservaVehiculoUsuario(String numeroSolicitud, String estadoSolicitud, String descripcion)
			throws GWorkException {

		Requests requests = ConsultarSolicitudAsignacionAlquilerVehiculos(numeroSolicitud);

		if (requests != null) {

			if (descripcion != null && descripcion.trim().length() != 0) {
				EntityManagerHelper.beginTransaction();
				requests.getRequestsStates().setRqtNombre(estadoSolicitud);
				requests.setRqsDescripcion(descripcion);
				new RequestsDAO().update(requests);
				EntityManagerHelper.commit();
				// El sistema notificara al jefe del motor pool de la
				// cancelación realizada por el usuario
			}
		}
	}

	public Requests ConsultarSolicitudAsignacionAlquilerVehiculos(String numeroSolicitud) throws GWorkException {
		return SearchVehicles.ConsultarSolicitudAsignacionAlquilerVehiculosPorId(numeroSolicitud);
	}

}
