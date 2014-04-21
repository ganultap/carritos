package geniar.siscar.logic.vehicle.services.impl;

import geniar.siscar.logic.consultas.SearchVehicles;
import geniar.siscar.logic.vehicle.services.ReturnRequestService;
import geniar.siscar.mail.util.SendEmail;
import geniar.siscar.model.Requests;
import geniar.siscar.model.RequestsClasses;
import geniar.siscar.model.RequestsStates;
import geniar.siscar.model.Rolls;
import geniar.siscar.persistence.EntityManagerHelper;
import geniar.siscar.persistence.RequestsClassesDAO;
import geniar.siscar.persistence.RequestsDAO;
import geniar.siscar.persistence.RequestsStatesDAO;
import geniar.siscar.persistence.RollsDAO;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ReturnRequestServiceImpl implements ReturnRequestService {

	private static final Log log = LogFactory.getLog(SendEmail.class);

	public void enviarNotificacionUsuarios(String email, String subject,
			String textMessage) throws GWorkException {

		Rolls rolls = new RollsDAO().findById(new Long(Util
				.loadParametersValue("MAIL.ADMINISTRADOR")));
		
		String server = Util.loadParametersValue("MAIL.HOST");
		String fromUser = rolls.getRlsMail();

		SendEmail mail = new SendEmail(email, fromUser, server, "false",
				subject + " : " + new Date(), textMessage);

		if (mail.SendMessage().equals("SUCCESS"))
			log.info("Mensaje enviado exitosamente");
		else {
			log.info("Eror Enviando el mensaje");
			throw new GWorkException(Util
					.loadErrorMessageValue("NOTIFICACION.ERROR"));
		}
	}

	public void devolucionSolicitudVehiculo(String idClaseSolicitud,
			Date fechaIni, Date fechaFin, String descripcion)
			throws GWorkException {

		String emailUsuarioSolicitante = Util
				.loadParametersValue("MAIL.ASISTENTE.ADMIN");
		String emailUsuarioMotorPool = Util
				.loadParametersValue("MAIL.ASISTENTE.ADMIN");

		String textmessageUsuarioSolicitante = Util
				.loadParametersValue("CUERPO.MSJ.NOV.VEH.ASIS");
		String subjectUsuarioSolicitante = Util
				.loadParametersValue("MSJ.NOV.VEH.ASIS");

		String textmessageUsuarioMotorPool = Util
				.loadParametersValue("CUERPO.MSJ.NOV.VEH.ASIS");
		String subjectUsuarioMotorPool = Util
				.loadParametersValue("MSJ.NOV.VEH.ASIS");

		List<Requests> listRequests = null;
		List<RequestsStates> listRequestsStates = null;
		RequestsStates requestsStates = null;
		Requests requests = null;
		String estado = null;
		RequestsClasses requestsClasses = new RequestsClassesDAO()
				.findById(new Long(idClaseSolicitud));

		if (requestsClasses != null) {

			listRequestsStates = new RequestsStatesDAO().findByRqtNombre(Util
					.loadMessageValue("ESTADO_DEVUELTA"));

			if (listRequestsStates != null && listRequestsStates.size() > 0) {
				requestsStates = listRequestsStates.get(0);

				if (fechaIni.compareTo(fechaFin) < 0) {

					listRequests = SearchVehicles
							.ConsultarSolicitudAsignacionAlquilerVehiculos(
									new Long(idClaseSolicitud), fechaIni,
									fechaFin, new Long(estado));

					if (listRequests != null && listRequests.size() > 0) {
						EntityManagerHelper.beginTransaction();
						requests = listRequests.get(0);
						requests.setRequestsStates(requestsStates);
						requests.setRqsDescripcion(descripcion);
						new RequestsDAO().update(requests);

						enviarNotificacionUsuarios(emailUsuarioSolicitante,
								subjectUsuarioSolicitante,
								textmessageUsuarioSolicitante);
						enviarNotificacionUsuarios(emailUsuarioMotorPool,
								subjectUsuarioMotorPool,
								textmessageUsuarioMotorPool);
						EntityManagerHelper.commit();
					} else
						throw new GWorkException(Util
								.loadErrorMessageValue("CONSULTA"));
				} else
					throw new GWorkException(Util
							.loadErrorMessageValue("FCH_INI_FCH_FIN"));
			}
		}
	}
}
