package geniar.siscar.logic.accidents.servicesImpl;

import geniar.siscar.logic.accidents.services.AccidentActsService;
import geniar.siscar.mail.util.SendEmail;
import geniar.siscar.model.Accidents;
import geniar.siscar.model.Acts;
import geniar.siscar.model.ActsAccidents;
import geniar.siscar.model.ActsTypes;
import geniar.siscar.model.Assistants;
import geniar.siscar.model.Responsibility;
import geniar.siscar.model.Rolls;
import geniar.siscar.model.Severity;
import geniar.siscar.persistence.AccidentsDAO;
import geniar.siscar.persistence.ActsAccidentsDAO;
import geniar.siscar.persistence.ActsDAO;
import geniar.siscar.persistence.ActsTypesDAO;
import geniar.siscar.persistence.AssistantsDAO;
import geniar.siscar.persistence.EntityManagerHelper;
import geniar.siscar.persistence.IAccidentsDAO;
import geniar.siscar.persistence.IActsAccidentsDAO;
import geniar.siscar.persistence.IActsDAO;
import geniar.siscar.persistence.ResponsibilityDAO;
import geniar.siscar.persistence.RollsDAO;
import geniar.siscar.persistence.SeverityDAO;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class AccidentActsServiceImpl implements AccidentActsService {

	private static final Log log = LogFactory.getLog(SendEmail.class);

	public void generarActaSubcomite(Date actFechaReunion,
			String actDescripcion, String actObservaciones,
			List<Accidents> listaAccidentes, List<Assistants> listaAsistentes)
			throws GWorkException {
		Acts acta = new Acts();
		IActsDAO actsDAO = new ActsDAO();
		ActsTypesDAO actsTypeDAO = new ActsTypesDAO();

		acta.setActFechaReunion(actFechaReunion);
		acta.setActDescripcion(actDescripcion);
		acta.setActObservaciones(actObservaciones);

		ActsTypes actsTypes = actsTypeDAO.findById(1L);
		if (actsTypes != null && actsTypes.getAtyNombre() != null
				&& actsTypes.getAtyNombre().length() > 0) {
			acta.setActsTypes(actsTypes);
		} else {
			actsTypes = new ActsTypes();
			actsTypes.setAtyCodigo(new Long(1));
			actsTypes.setAtyNombre("PRUEBA");
			acta.setActsTypes(actsTypes);
		}

		EntityManagerHelper.beginTransaction();
		actsTypeDAO.save(actsTypes);
		actsDAO.save(acta);
		EntityManagerHelper.commit();

		registraAccidenteByActa(acta, listaAccidentes);
		registraAsistente(listaAsistentes, acta, actsTypes.getAtyCodigo());
	}

	public List<Acts> consultarActa(Long numeroActa, Date fechaIni,
			Date fechaFin) throws GWorkException {

		List<Acts> listaActa = new SearchParametersAccidents().consultarActa(
				numeroActa, fechaIni, fechaFin);

		if (listaActa == null || listaActa.size() == 0 || listaActa.isEmpty())
			throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));

		return listaActa;
	}

	public void modificarActaSubcomite(Long actCodigo, Date actFechaReunion,
			String actDescripcion, String actObservaciones,
			String actAprobacion, Date actFechaAprobacion,
			List<Accidents> listaAccidentes, List<Assistants> listaAsistentes)
			throws GWorkException {

		Acts acts = new Acts();
		IActsDAO actsDAO = new ActsDAO();

		acts = actsDAO.findById(actCodigo);

		if (acts == null)
			throw new GWorkException(Util.loadErrorMessageValue("ACTA.EXISTEN"));

		acts.setActFechaAprobacion(actFechaAprobacion);
		acts.setActDescripcion(actDescripcion);
		acts.setActFechaAprobacion(actFechaAprobacion);
		acts.setActFechaReunion(actFechaReunion);
		acts.setActObservaciones(actObservaciones);
		acts.setActAprobacion(actAprobacion);

		try {
			EntityManagerHelper.getEntityManager().getTransaction().begin();
			actsDAO.update(acts);
			EntityManagerHelper.getEntityManager().getTransaction().commit();
		} catch (Exception e) {
			EntityManagerHelper.getEntityManager().getTransaction().rollback();
			log.error("modificarActaSubcomite", e);
			throw new GWorkException(e.getMessage(), e);
		}

		eliminarAccidenteActa(acts.getActCodigo());
		registraAccidenteByActa(acts, listaAccidentes);

		registraAsistente(listaAsistentes, acts, actCodigo);
	}

	public void registraAccidenteByActa(Acts acts,
			List<Accidents> listaAccidentes) {

		if (listaAccidentes != null && listaAccidentes.size() > 0) {

			for (Accidents accidents : listaAccidentes) {
				ActsAccidents actsAccidents = new ActsAccidents();
				IActsAccidentsDAO actsAccidentsDAO = new ActsAccidentsDAO();

				actsAccidents.setActs(acts);
				actsAccidents.setAccidents(accidents);
				actsAccidents.setFechaActaRegistro(new Date());

				EntityManagerHelper.getEntityManager().getTransaction().begin();
				actsAccidentsDAO.update(actsAccidents);
				EntityManagerHelper.getEntityManager().getTransaction()
						.commit();

			}
		}
	}

	public List<Accidents> accidentesByActa(Long numeroActa)
			throws GWorkException {

		List<ActsAccidents> listaActsAccidents = new ActsAccidentsDAO()
				.findByProperty("acts.actCodigo", numeroActa);

		List<Accidents> listAccidents = null;

		if (listaActsAccidents != null && listaActsAccidents.size() > 0) {
			listAccidents = new ArrayList<Accidents>();
			for (ActsAccidents actsAccidents : listaActsAccidents) {
				listAccidents.add(actsAccidents.getAccidents());
			}
		}

		return listAccidents;
	}

	public void actualizarActaAccidente(Long idAccidente) throws GWorkException {

		Accidents accidents = new Accidents();
		IAccidentsDAO accidentsDAO = new AccidentsDAO();

		accidents = accidentsDAO.findById(idAccidente);

		if (accidents == null)
			throw new GWorkException(Util
					.loadErrorMessageValue("ACCIDENTE.EXISTEN"));

		accidents.setActs(null);

		EntityManagerHelper.getEntityManager().getTransaction().begin();
		accidentsDAO.update(accidents);
		EntityManagerHelper.getEntityManager().getTransaction().commit();
	}

	@SuppressWarnings("null")
	public void registraAsistente(List<Assistants> listaAsistentes, Acts acts,
			Long idActa) throws GWorkException {

		if (idActa != null)
			eliminarAsistentes(idActa.longValue());

		if (listaAsistentes != null || listaAsistentes.size() > 0
				|| !listaAsistentes.isEmpty()) {
			for (Assistants assistants : listaAsistentes) {

				Assistants assistantsObj = null;

				List<Assistants> asistentesConsulta = new AssistantsDAO()
						.findByAsiCodigoCiat(assistants.getAsiCodigoCiat());

				if (asistentesConsulta != null && asistentesConsulta.size() > 0
						&& !asistentesConsulta.isEmpty())
					assistantsObj = new AssistantsDAO().findByAsiCodigoCiat(
							assistants.getAsiCodigoCiat()).get(0);

				if (assistantsObj == null) {
					assistantsObj = new Assistants();
					assistantsObj.setActs(acts);
					assistantsObj.setAsiCodigoCiat(assistants
							.getAsiCodigoCiat());
					assistantsObj.setAsiNombre(assistants.getAsiNombre());
					assistantsObj.setAsiEmail(assistants.getAsiEmail());

					EntityManagerHelper.getEntityManager().getTransaction()
							.begin();
					new AssistantsDAO().save(assistantsObj);
					EntityManagerHelper.getEntityManager().getTransaction()
							.commit();

				} else if (assistants != null) {

					assistantsObj.setActs(new ActsDAO().findById(acts
							.getActCodigo().longValue()));
					assistantsObj.setAsiCodigoCiat(assistants
							.getAsiCodigoCiat());
					assistantsObj.setAsiNombre(assistants.getAsiNombre());
					assistantsObj.setAsiEmail(assistants.getAsiEmail());

					EntityManagerHelper.getEntityManager().getTransaction()
							.begin();
					new AssistantsDAO().update(assistantsObj);
					EntityManagerHelper.getEntityManager().getTransaction()
							.commit();
				}
			}
		}
	}

	public void modificarActaAccidente(Long numAccidente,
			String deduciblesPesos, String deduciblesDolar,
			Long responsibility, Long severity, String ordenTrabajo,
			String sanciones, String observaciones, String descripcion,
			String accRecomendaciones) throws GWorkException {

		Accidents accidents = null;
		IAccidentsDAO accidentsDAO = new AccidentsDAO();

		accidents = accidentsDAO.findById(numAccidente);

		if (accidents == null)
			throw new GWorkException(Util
					.loadErrorMessageValue("ACCIDENTE.EXISTEN"));

		accidents.setAccDeduciblesDolar(new Float(deduciblesDolar));
		accidents.setAccDeduciblesPesos(new Float(deduciblesPesos));

		Responsibility rsp = (new ResponsibilityDAO()).findById(responsibility);
		if (rsp != null)
			accidents.setResponsibility(rsp);

		Severity svr = (new SeverityDAO()).findById(severity);
		if (svr != null)
			accidents.setSeverity(svr);

		accidents.setAccOrdenTrabajoActa(ordenTrabajo);
		accidents.setAccSancInterActa(sanciones);
		accidents.setAccObservaciones(observaciones);
		accidents.setAccDescripcion(descripcion);
		accidents.setAccRecomendaciones(accRecomendaciones);

		try {
			EntityManagerHelper.getEntityManager().getTransaction().begin();
			accidentsDAO.update(accidents);
			EntityManagerHelper.getEntityManager().getTransaction().commit();

			notificacionRegistroAccidente(accidents);
		} catch (Exception e) {
			
			log.error("modificarActaAccidente", e);
			EntityManagerHelper.getEntityManager().getTransaction().rollback();
			throw new GWorkException(Util
					.loadErrorMessageValue("NO.MODIFICACION.ACCIDENTE"), e);
			
		}
	}

	public void enviarNotificacionAsistente(String email, String CuerpoMensaje)
			throws GWorkException {
		try {
			SendEmail mail = null;

			Rolls rolls = new RollsDAO().findById(new Long(Util
					.loadParametersValue("MAIL.ADMINISTRADOR")));

			String strEmailAdminitrador = rolls.getRlsMail();

			String server = Util.loadParametersValue("MAIL.HOST");
			String fromUser = strEmailAdminitrador;
			String toUser = email;
			String Asunto = Util.loadParametersValue("ASUNTO_ACTA_ACCIDENTE");

			String textmessage = Util
					.loadParametersValue("MENSAJE_ACTA_ACCIDENTE");

			textmessage += "<br /><br/><br />" + CuerpoMensaje;

			mail = new SendEmail(toUser, fromUser, server, "false", Asunto,
					textmessage);

			String enviado = mail.SendMessage();
			if (enviado.equals("ERROR")) {
				log.info("No se pudo enviar el mensaje");
				throw new GWorkException("No se pudo enviar la notificación");
			} else {
				log.info("Mensaje enviado exitosamente");
				throw new GWorkException("Mensaje enviado exitosamente");
			}
		} catch (Exception e) {
			log.error("enviarNotificacionAsistente", e);
			throw new GWorkException(e.getMessage(), e);
		}
	}

	public void notificacionRegistroAccidente(Accidents accidents)
			throws GWorkException {
		try {

			if (accidents.getAccEmailConductor() == null
					|| accidents.getAccEmailConductor().trim().length() == 0)
				throw new GWorkException(Util
						.loadErrorMessageValue("EMAIL.ACC.NULO"));

			Rolls rolls = new RollsDAO().findById(new Long(Util
					.loadParametersValue("MAIL.ADMINISTRADOR")));

			String server = Util.loadParametersValue("MAIL.HOST");
			String fromUser = rolls.getRlsMail();
			String toUser = accidents.getAccEmailConductor().toLowerCase();
			String subject = Util.loadParametersValue("ASUNTO_ACTA_ACCIDENTE");
			StringBuffer mensaje;

			mensaje = new StringBuffer().append(
					Util.loadMessageValue("ACCIDENTE")).append(
					accidents.getAccCodigo().toString()).append("<br>").append(
					Util.loadMessageValue("CONDUCTOR")).append(
					accidents.getAccNombreConduct()).append("<br>").append(
					Util.loadMessageValue("PLACA")).append(
					accidents.getVehicles().getVhcPlacaDiplomatica()).append(
					"<br>").append(Util.loadMessageValue("ASIGNACION")).append(
					accidents.getLegateesTypes().getLgtNombre()).append("<br>")
					.append(Util.loadMessageValue("FECHA")).append(
							accidents.getAccFechaAccidente()).append("<br>")
					.append(Util.loadMessageValue("DESCRIPCION")).append(
							accidents.getAccDescripcion());

			if (accidents.getSeverity() != null) {
				mensaje.append("<br>").append(
						Util.loadMessageValue("SEVERIDAD")).append(
						accidents.getSeverity().getSevNombre());
			}

			if (accidents.getResponsibility() != null) {
				mensaje.append("<br>").append(
						Util.loadMessageValue("RESPONSABILIDAD")).append(
						accidents.getResponsibility().getResNombre());
			}

			mensaje.append("<br>").append(Util.loadMessageValue("VALOR"))
					.append(accidents.getAccValorDano());

			mensaje.append("<br>").append(Util.loadMessageValue("DEDUCIBLE"))
					.append(accidents.getAccDeduciblesPesos()).append("<br>")
					.append(Util.loadMessageValue("OBSERVACIONES")).append(
							accidents.getAccObservaciones()).append("<br>")
					.append(Util.loadMessageValue("RECOMENDACION")).append(
							accidents.getAccRecomendaciones());

			SendEmail se = new SendEmail(toUser, fromUser, server, "false",
					subject, mensaje.toString());
			try {
				String enviado = se.SendMessage();
				if (enviado.equals("ERROR")) {
					log.info("No se pudo enviar el mensaje");
					throw new GWorkException(
							"No se pudo enviar la notificación");
				} else
					log.info("Mensaje enviado exitosamente");
			} catch (Exception e) {
				throw new GWorkException(e.getMessage() + "\n"
						+ e.getLocalizedMessage());
			}

		} catch (Exception e) {
			log.error("notificacionRegistroAccidente", e);
			throw new GWorkException(Util
					.loadErrorMessageValue("NOTIFICACION.ERROR")
					+ " " + e.getMessage(), e);
		}
	}

	private static void eliminarAsistentes(Long idActa) {

		try {

			EntityManagerHelper.beginTransaction();
			final String queryString = "delete from Assistants model where model.acts.actCodigo= :idActa";
			javax.persistence.Query query = EntityManagerHelper
					.getEntityManager().createQuery(queryString);
			query.setParameter("idActa", idActa);
			query.executeUpdate();
			EntityManagerHelper.commit();

		} catch (RuntimeException re) {
			log.error("eliminarAsistentes", re);
		}

	}

	public List<Assistants> cosultarAssistenteActa(Long idActa)
			throws GWorkException {

		List<Assistants> listaAsistante = new SearchParametersAccidents()
				.cosultarAssistenteActa(idActa);

		if (listaAsistante == null || listaAsistante.size() == 0
				|| listaAsistante.isEmpty())
			throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));

		return listaAsistante;
	}

	public void eliminarAccidenteActa(Long numeroActa) {
		IActsAccidentsDAO actsAccidentsDAO = new ActsAccidentsDAO();
		List<ActsAccidents> listActsAccidents = actsAccidentsDAO
				.findByProperty("acts.actCodigo", numeroActa);

		if (listActsAccidents != null && listActsAccidents.size() > 0) {
			for (ActsAccidents actsAccidents : listActsAccidents) {
				actsAccidentsDAO.delete(actsAccidents);
			}
		}
	}

	public static void main(String[] args) {

		try {
			List<Acts> listActs = new ActsDAO().findAll();
			for (Acts acts : listActs) {
				List<Accidents> listAccidents = new AccidentsDAO()
						.findByProperty("acts.actCodigo", acts.getActCodigo());
				if (listAccidents != null && listAccidents.size() > 0) {
					new AccidentActsServiceImpl().registraAccidenteByActa(acts,
							listAccidents);
				}
			}
		} catch (Exception e) {
			log.error("main", e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<Accidents> filtroActaAccidente(String placa,
			Long numeroSiniestro, Long idEstado, Date fechaIni,
			Date fechaFin) throws GWorkException {

		String queryString = "select acc from Accidents acc "
				+ "where acc.vehicles.vhcPlacaDiplomatica = :placa "
				+ "or acc.accNumeroSiniestro = :numeroSiniestro "
				+ "or acc.accidentsStates.acsCode = :idEstado "
				+ "or (acc.accFechaAccidente >= :fechaIni and acc.accFechaAccidente <= :fechaFin)";
		Query query = EntityManagerHelper.getEntityManager().createQuery(
				queryString);

		query.setParameter("placa", placa);
		query.setParameter("numeroSiniestro", numeroSiniestro);
		query.setParameter("idEstado", idEstado);
		query.setParameter("fechaIni", fechaIni);
		query.setParameter("fechaFin", fechaFin);

		if (query.getResultList() != null && query.getResultList().size() > 0) {
			return (List<Accidents>) query.getResultList();
		} else {
			throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));
		}

	}
}
