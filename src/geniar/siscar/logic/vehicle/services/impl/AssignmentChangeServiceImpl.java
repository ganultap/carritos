package geniar.siscar.logic.vehicle.services.impl;

import java.util.Date;
import java.util.List;

import geniar.siscar.logic.vehicle.services.AssignmentChangeService;
import geniar.siscar.model.Requests;
import geniar.siscar.model.Users;
import geniar.siscar.model.VehiclesAssignation;
import geniar.siscar.model.Zones;
import geniar.siscar.persistence.EntityManagerHelper;
import geniar.siscar.persistence.RequestsDAO;
import geniar.siscar.persistence.UsersDAO;
import geniar.siscar.persistence.ZonesDAO;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

/**
 * @author Diego Bocanegra
 */
public class AssignmentChangeServiceImpl implements AssignmentChangeService {

	public void modificarAsignatarioAsignacion(
			VehiclesAssignation vehiclesAssignation, String EmailAsignatario,
			Long idZone, String CarnetAsistente, String EmailAsistente,
			String NombreAsistente) throws GWorkException {
		try {
			Users userAsignatario = new Users();
			Requests requests = new Requests();

			Zones zones = new ZonesDAO().findById(idZone);

			requests = vehiclesAssignation.getRequests();
			Long CodigoAsignatario = requests.getUsersByRqsUser()
					.getUsrCodigo();

			if (requests.getUsersByUsrCodigo().getUsrCodigo() == null)
				throw new GWorkException("El asignatario no tiene asistente");

			userAsignatario = new UsersDAO().findById(CodigoAsignatario);

			// EntityManagerHelper.getEntityManager().refresh(userAsignatario);

			if (EmailAsignatario != null)
				userAsignatario.setUsrEmail(EmailAsignatario);

			if (requests.getZones() != null
					&& requests.getZones().getZnsId().longValue() != idZone
							.longValue()) {
				requests.setZones(zones);
			}

			new UsersDAO().update(userAsignatario);
			new RequestsDAO().update(requests);

			if (CarnetAsistente != null)
				modificarAsistenteAsignacion(vehiclesAssignation,
						CarnetAsistente, EmailAsistente, NombreAsistente);

			EntityManagerHelper.getEntityManager().getTransaction().begin();
			EntityManagerHelper.getEntityManager().getTransaction().commit();
		} catch (Exception e) {
			//Util.limpiarSession();
			throw new GWorkException(e.getMessage());
		}
	}

	public void modificarAsistenteAsignacion(
			VehiclesAssignation vehiclesAssignation, String CarnetAsistente,
			String EmailAsistente, String NombreAsistente)
			throws GWorkException {
		try {
			Users userAsistente = null;
			Requests requests = new Requests();

			requests = vehiclesAssignation.getRequests();
			Long CodigoAsistente = null;
			if (requests.getUsersByUsrCodigo().getUsrCodigo() == null)
				throw new GWorkException("El asignatario no tiene asistente");

			CodigoAsistente = requests.getUsersByUsrCodigo().getUsrCodigo()
					.longValue();

			List<Users> listaUsuarios = new UsersDAO()
					.findByUsrIdentificacion(CarnetAsistente);

			if (listaUsuarios != null && listaUsuarios.size() > 0)
				userAsistente = listaUsuarios.get(0);

			// EntityManagerHelper.getEntityManager().refresh(userAsistente);

			if (userAsistente == null) {
				userAsistente = new Users();
				userAsistente.setUsrEmail(EmailAsistente.toUpperCase());
				userAsistente.setUsrNombre(NombreAsistente);
				userAsistente.setUsrEstado("ACTIVO");
				userAsistente.setUsrFecha(new Date());
				userAsistente.setUsrIdentificacion(CarnetAsistente);

				new UsersDAO().save(userAsistente);
				requests.setUsersByUsrCodigo(userAsistente);
			}

			userAsistente.setUsrEmail(EmailAsistente.toUpperCase());

			if (CodigoAsistente != userAsistente.getUsrCodigo().longValue()) {
				requests.setUsersByUsrCodigo(userAsistente);
			}

			new UsersDAO().update(userAsistente);
			new RequestsDAO().update(requests);
		} catch (Exception e) {
			e.printStackTrace();
			throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));
		}
	}
}