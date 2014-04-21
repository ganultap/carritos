package geniar.siscar.logic.notification.services.impl;

import geniar.siscar.consults.impl.ConsultsServiceImpl;
import geniar.siscar.logic.consultas.SearchVehicles;
import geniar.siscar.logic.notification.services.NotificationsServices;
import geniar.siscar.mail.util.SendEmail;
import geniar.siscar.model.VehiclesAssignation;
import geniar.siscar.persistence.EntityManagerHelper;
import geniar.siscar.persistence.RollsDAO;
import geniar.siscar.util.ParametersUtil;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

import java.text.SimpleDateFormat;
import java.util.List;
import javax.persistence.Query;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * @author -jam
 * @version 1.0 Descripción : Clase encargada del manejo de las pruebas de las
 *          notificaciones
 */

public class NotificationsServiceImpl implements NotificationsServices {

	private static final Log log = LogFactory.getLog(SendEmail.class);
	private String mailAdmin = null;
	private String AdminPool = null;
	private String emailAdminSeguros = null;

	public void notificacionFechaDevolucionAsignacion() throws GWorkException {

		String subjectUser = Util.loadParametersValue("MSJ.DEV.FECHA.ASIG");
		String subjectJefePool = Util.loadParametersValue("MSJ.DEV.FECHA.ASIG");
		String textmessage = Util
				.loadParametersValue("CUERPO.MSJ.DEV.JEFE.ASIG");
		String textmessageJefePool = Util
				.loadParametersValue("CUERPO.MSJ.DEV.JEFE.ASIG");

		List<VehiclesAssignation> list = SearchVehicles
				.ConsultarFechasTerminacionAsignacion();
		emailAdminSeguros = new RollsDAO().findById(
				Long.parseLong(Util.loadParametersValue("MAIL.ADMINSEGUROS")))
				.getRlsMail();
		sendMails(list, subjectUser, textmessage, subjectJefePool,
				textmessageJefePool,
				ParametersUtil.NOTIFICACIONFECHADEVOLUCIONASIGNACION);
	}

	public void notificacionReservasProximasAHacerseEfectivas()
			throws GWorkException {

		String subjectUser = Util.loadParametersValue("MSJ.DEV.FECHA.RPHE");
		String subjectJefePool = Util.loadParametersValue("MSJ.DEV.FECHA.RPHE");
		String textmessage = Util
				.loadParametersValue("CUERPO.MSJ.DEV.JEFE.RPHE");
		String textmessageJefePool = Util
				.loadParametersValue("CUERPO.MSJ.DEV.JEFE.RPHE");

		List<VehiclesAssignation> list = SearchVehicles
				.ConsultarReservasVehiculosProximas();
		sendMails(list, subjectUser, textmessage, subjectJefePool,
				textmessageJefePool,
				ParametersUtil.NOTIFICACIONRESERVPROXIMASEFECTIVAS);
	}

	public void notificacionFechaDevolucionAlquiler() throws GWorkException {

		String subjectUser = Util.loadParametersValue("MSJ.DEV.FECHA.ALQ");
		String subjectJefePool = Util.loadParametersValue("MSJ.DEV.FECHA.ALQ");
		String textmessage = Util
				.loadParametersValue("CUERPO.MSJ.DEV.JEFE.ALQ");
		String textmessageJefePool = Util
				.loadParametersValue("CUERPO.MSJ.DEV.JEFE.ALQ");

		List<VehiclesAssignation> list = SearchVehicles
				.ConsultarFechasTerminacionAlquiler();
		sendMails(list, subjectUser, textmessage, subjectJefePool,
				textmessageJefePool,
				ParametersUtil.NOTIFICACIONFECHADEVOLUCIONALQUILER);
	}

	public void notificacionVehiculosReservadosNoRecogidos()
			throws GWorkException {

		String subjectUser = Util.loadParametersValue("MSJ.DEV.FECHA.VRNR");
		String subjectJefePool = Util.loadParametersValue("MSJ.DEV.FECHA.VRNR");
		String textmessage = Util
				.loadParametersValue("CUERPO.MSJ.DEV.JEFE.VRNR");
		String textmessageJefePool = Util
				.loadParametersValue("CUERPO.MSJ.DEV.JEFE.VRNR");

		List<VehiclesAssignation> list = SearchVehicles
				.ConsultarVehiculosReservadosNoRecogidos();
		sendMails(list, subjectUser, textmessage, subjectJefePool,
				textmessageJefePool,
				ParametersUtil.NOTIFICACIONVEHICULORESERVADONORECOGIDO);
	}

	public void sendMails(List<VehiclesAssignation> list, String subjectUser,
			String textmessage, String subjectJefePool,
			String textmessageJefePool, Long TipoNotificacion)
			throws GWorkException {

		SendEmail mail = null;
		String server = Util.loadParametersValue("MAIL.HOST");
		String fromUser = getMailAdmin();
		String emailAdminPool = getAdminPool();

		for (VehiclesAssignation vehiclesAssignation : list) {

			String fechaIni = new SimpleDateFormat("dd-MMM-yyyy")
					.format(vehiclesAssignation.getVhaFechaInicio());
			String fechaFin = new SimpleDateFormat("dd-MMM-yyyy")
					.format(vehiclesAssignation.getVhaFechaTermina());
			String placa = vehiclesAssignation.getVehicles()
					.getVhcPlacaDiplomatica();
			String tipoAsignacion = (vehiclesAssignation.getRequests()
					.getLegateesTypes() == null) ? "Alquiler"
					: vehiclesAssignation.getRequests().getLegateesTypes()
							.getLgtNombre();
			String asistente = (vehiclesAssignation.getRequests()
					.getUsersByUsrCodigo() == null) ? "" : vehiclesAssignation
					.getRequests().getUsersByUsrCodigo().getUsrNombre();

			subjectUser = " Placa Diplomatica: " + placa
					+ ", Fecha terminación: " + fechaFin;
			subjectJefePool = " Placa Diplomatica: " + placa
					+ ", Fecha terminación: " + fechaFin;

			if (fromUser != null) {
				mail = new SendEmail(fromUser, fromUser, server, "false",
						subjectJefePool, textmessageJefePool
								+ "<br /><br />Fecha inicial: "
								+ fechaIni
								+ "<br /> Fecha fin: "
								+ fechaFin
								+ "<br /> Asignatario: "
								+ vehiclesAssignation.getRequests()
										.getUsersByRqsUser().getUsrNombre()
								+ "<br /> Asistente: " + asistente
								+ "<br /> Tipo de asignación: "
								+ tipoAsignacion + "<br />Placa Diplomatica: "
								+ placa);

				if (mail.SendMessage().equals("SUCCESS"))
					log.info("Mensaje enviado exitosamente");
				else
					log.info("Eror Enviando el mensaje");
			}

			if (emailAdminPool != null && fromUser != null) {
				mail = new SendEmail(emailAdminPool, fromUser, server, "false",
						subjectJefePool, textmessageJefePool
								+ "<br /><br />Fecha inicial: "
								+ fechaIni
								+ "<br /> Fecha fin: "
								+ fechaFin
								+ "<br /> Asignatario: "
								+ vehiclesAssignation.getRequests()
										.getUsersByRqsUser().getUsrNombre()
								+ "<br /> Asistente: " + asistente
								+ "<br /> Tipo de asignación: "
								+ tipoAsignacion + "<br />Placa Diplomatica: "
								+ placa);
				if (mail.SendMessage().equals("SUCCESS"))
					log.info("Mensaje enviado exitosamente");
				else
					log.info("Eror Enviando el mensaje");
			}

			if (emailAdminSeguros != null && fromUser != null) {

				mail = new SendEmail(emailAdminSeguros, fromUser, server,
						"false", subjectJefePool, textmessageJefePool
								+ "<br /><br />Fecha inicial: "
								+ fechaIni
								+ "<br /> Fecha fin: "
								+ fechaFin
								+ "<br /> Asignatario: "
								+ vehiclesAssignation.getRequests()
										.getUsersByRqsUser().getUsrNombre()
								+ "<br /> Asistente: " + asistente
								+ "<br /> Tipo de asignación: "
								+ tipoAsignacion + "<br />Placa Diplomatica: "
								+ placa);
				if (mail.SendMessage().equals("SUCCESS"))
					log.info("Mensaje enviado exitosamente");
				else
					log.info("Eror Enviando el mensaje");
			}
		}
	}

	public void notificacionSicarVsActivoFijo() throws GWorkException {

		try {
			List<VehiclesAssignation> listaVehiculos = null;
			listaVehiculos = consultaVehiculosSicar();

			ConsultsServiceImpl consultsServiceImpl = new ConsultsServiceImpl();

			String TablaVehiculos = "<table border='1'><tr>"
					+ "<td>Placa Diplomatica</td>"
					+ "<td>Placa Activo Fijo</td>"
					+ "<td>Carnet Asignatario SISCAR</td>"
					+ "<td>Nombre Asignatario SISCAR</td>"
					+ "<td>Carnet Asignatario Activo Fijo</td>"
					+ "<td>Nombre Asignatario Activo Fijo</td></tr>";
			if (listaVehiculos != null) {
				for (VehiclesAssignation vehiclesAssignation : listaVehiculos) {

					String strCarnetActivoFijo = "";
					String strPlacaDiplomatica = null;
					String strCarnetAsignatario = null;
					String strNombreAsignatario = null;
					String strPlacaActivoFijo = null;
					String strNombreAuxiliar = "";

					// carnet que me devuelve la funcion de claudia
					strPlacaActivoFijo = (vehiclesAssignation.getVehicles()
							.getVhcPlacaActivoFijo() == null) ? ""
							: vehiclesAssignation.getVehicles()
									.getVhcPlacaActivoFijo();
					strCarnetActivoFijo = consultsServiceImpl
							.carnetVehiculoActivoFijo(strPlacaActivoFijo.trim());
					strPlacaDiplomatica = vehiclesAssignation.getVehicles()
							.getVhcPlacaDiplomatica();
					strCarnetAsignatario = vehiclesAssignation
							.getVhaNumeroCarne();

					strNombreAsignatario = vehiclesAssignation.getRequests()
							.getUsersByRqsUser().getUsrNombre();

					if (vehiclesAssignation.getRequests().getUsersByRqsUser()
							.getUsrApellido() != null) {
						strNombreAsignatario += " "
								+ vehiclesAssignation.getRequests()
										.getUsersByRqsUser().getUsrApellido();
					}

					if (strCarnetActivoFijo != null) {

						strNombreAuxiliar = consultsServiceImpl
								.consultEmpleoyeeName(strCarnetActivoFijo);
					}

					if (strCarnetActivoFijo == null)
						strCarnetActivoFijo = "";
					if (strCarnetActivoFijo != null
							&& !strCarnetActivoFijo
									.equals(strCarnetAsignatario)) {
						TablaVehiculos += "<tr><td>" + strPlacaDiplomatica
								+ "</td>" + "<td>" + strPlacaActivoFijo
								+ "</td>" + "<td>" + strCarnetAsignatario
								+ "</td><td>" + strNombreAsignatario
								+ "</td><td>" + strCarnetActivoFijo
								+ "</td><td>" + strNombreAuxiliar + "</td>";
					}
				}
			}
			envioNotificacion(TablaVehiculos);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@SuppressWarnings("unchecked")
	public List<VehiclesAssignation> consultaVehiculosSicar()
			throws GWorkException {
		try {

			List<VehiclesAssignation> listaVehiculos;

			String sql = "SELECT va FROM VehiclesAssignation va "
					+ "JOIN va.requests r "
					+ "WHERE r.legateesTypes.lgtCodigo IN (1,2,3) "
					+ "AND va.vhaFechaDev IS NULL";

			Query query = EntityManagerHelper.getEntityManager().createQuery(
					sql);
			listaVehiculos = query.getResultList();

			if (listaVehiculos != null && listaVehiculos.size() > 0)
				return listaVehiculos;
			else
				return null;
		} catch (Exception e) {
			e.printStackTrace();
			throw new GWorkException(Util
					.loadErrorMessageValue("search.not.found"));
		}
	}

	public void envioNotificacion(String TablaVehiculos) throws GWorkException {
		try {

			SendEmail mail = null;
			// cuerpo de la notificacion
			String textmessage = "Señores Administración del POOL se le notifica que hay una inconsistencia "
					+ "del asignatario para los siguientes vehiculos.<br /><br /><br />"
					+ TablaVehiculos;

			String Asunto = "[SISCAR Vs ACTIVO FIJO] Lista vehículos con asignatario inconsistente entre SISCAR y ACTIVOS FIJOS";
			String server = Util.loadParametersValue("MAIL.HOST");
			String fromUser = getMailAdmin();
			String emailCC = Util.loadParametersValue("CORREO_CC_ACT_FIJOS");

			// se envia la notificacion al SuperUser getMailAdmin() - fromUser
			if (getMailAdmin() != null && fromUser != null)
				mail = new SendEmail(getMailAdmin(), emailCC, fromUser, server,
						"false", Asunto, textmessage);
			if (mail.SendMessage().equals("SUCCESS"))
				log.info("Mensaje enviado exitosamente");
			else
				log.info("Eror Enviando el mensaje");

			mail = null;
			// se envia la notificacion al administrador del pool
			if (getAdminPool() != null && fromUser != null)
				mail = new SendEmail(getAdminPool(), fromUser, server, "false",
						Asunto, textmessage);
			if (mail.SendMessage().equals("SUCCESS"))
				log.info("Mensaje enviado exitosamente");
			else
				log.info("Eror Enviando el mensaje");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getMailAdmin() throws GWorkException {
		return new RollsDAO().findById(
				new Long(Util.loadParametersValue("MAIL.ADMINISTRADOR")))
				.getRlsMail();
	}

	public void setMailAdmin(String mailAdmin) {
		this.mailAdmin = mailAdmin;
	}

	public String getAdminPool() throws GWorkException {
		return new RollsDAO().findById(
				new Long(Util.loadParametersValue("MAIL.ADMINPOOL")))
				.getRlsMail();
	}

	public void setAdminPool(String adminPool) {
		AdminPool = adminPool;
	}
}
