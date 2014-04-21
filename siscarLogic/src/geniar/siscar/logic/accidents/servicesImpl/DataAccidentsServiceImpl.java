package geniar.siscar.logic.accidents.servicesImpl;

import java.util.Date;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import geniar.siscar.logic.accidents.services.DataAccidentsService;
import geniar.siscar.logic.consultas.SearchVehicles;
import geniar.siscar.mail.util.SendEmail;
import geniar.siscar.model.Accidents;
import geniar.siscar.model.AccidentsResults;
import geniar.siscar.model.Cities;
import geniar.siscar.model.Driver;
import geniar.siscar.model.InjuredPersons;
import geniar.siscar.model.InvolvedVehicles;
import geniar.siscar.model.LegateesTypes;
import geniar.siscar.model.Responsibility;
import geniar.siscar.model.Rolls;
import geniar.siscar.model.Severity;
import geniar.siscar.model.Users;
import geniar.siscar.model.Vehicles;
import geniar.siscar.model.VehiclesAssignation;
import geniar.siscar.persistence.AccidentsDAO;
import geniar.siscar.persistence.AccidentsResultsDAO;
import geniar.siscar.persistence.AccidentsStatesDAO;
import geniar.siscar.persistence.CitiesDAO;
import geniar.siscar.persistence.EntityManagerHelper;
import geniar.siscar.persistence.IAccidentsDAO;
import geniar.siscar.persistence.IInjuredPersonsDAO;
import geniar.siscar.persistence.IInvolvedVehiclesDAO;
import geniar.siscar.persistence.IVehiclesDAO;
import geniar.siscar.persistence.InjuredPersonsDAO;
import geniar.siscar.persistence.InvolvedVehiclesDAO;
import geniar.siscar.persistence.LegateesTypesDAO;
import geniar.siscar.persistence.ResponsibilityDAO;
import geniar.siscar.persistence.RollsDAO;
import geniar.siscar.persistence.SeverityDAO;
import geniar.siscar.persistence.VehiclesDAO;
import geniar.siscar.persistence.UsersDAO;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

/**
 * The Class DataAccidentsServiceImpl.
 */
public class DataAccidentsServiceImpl implements DataAccidentsService {

	/** The Constant log. */
	private static final Log log = LogFactory.getLog(SendEmail.class);
	
	/** The ACCIDENT e_ e n_ proceso. */
	private static Long ACCIDENTE_EN_PROCESO = 1L;

	/* (non-Javadoc)
	 * @see geniar.siscar.logic.accidents.services.DataAccidentsService#registroAccidente(java.lang.String, java.util.Date, java.lang.Long, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.Long, java.lang.Long, java.lang.Long, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.Long, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public void registroAccidente(String placa, Date accFechaAccidente,
			Long idCities, String accSitioAccidente, String accNumeroSiniestro,
			String accVehiculosInvolucrados, String accTotalMuertos,
			String accTotalHeridos, String accTotalTestigos,
			String accIntervinoTransito, String accNombreConduct,
			String accCargoConduct, String accCodCargoAcc,
			String accDescripcion, Long resultados, Long responsibility,
			Long severity, String accUso, String accReclamo,
			String accValorDano, String accDeduciblesPesos,
			String accDeduciblesDolar, String accDeducible,
			String accCargoDeducible, Long accTipoAsignacion,
			String accNombreAsignatario, String accCedulaConduc,
			String sanciones, String ordenTrabajo, String accRecomendaciones,
			String accObservaciones, String accEmailConductor, String accHora)
			throws GWorkException {

		Accidents accidents = new Accidents();
		IAccidentsDAO accidentsDAO = new AccidentsDAO();
		Vehicles vehiclesObj = new Vehicles();
		Cities citiesObj = new Cities();
		AccidentsResults accidentsResultsObj = new AccidentsResults();
		Responsibility responsibilityObj = new Responsibility();
		Severity severityObj = new Severity();
		LegateesTypes legateesTypesObj = new LegateesTypes();

		vehiclesObj = findVehicleByPlaca(placa);
		citiesObj = new CitiesDAO().findById(idCities);
		accidentsResultsObj = new AccidentsResultsDAO().findById(resultados);
		responsibilityObj = new ResponsibilityDAO().findById(responsibility);
		severityObj = new SeverityDAO().findById(severity);

		if (accTipoAsignacion != null)
			legateesTypesObj = new LegateesTypesDAO()
					.findById(accTipoAsignacion);

		accidents.setVehicles(vehiclesObj);
		accidents.setAccFechaAccidente(accFechaAccidente);
		accidents.setCities(citiesObj);
		accidents.setAccSitioAccidente(accSitioAccidente);
		if (accNumeroSiniestro != null
				&& accNumeroSiniestro.trim().length() > 3)
			accidents.setAccNumeroSiniestro(new Long(accNumeroSiniestro));
		accidents
				.setAccVehiculosInvolucrados(new Long(accVehiculosInvolucrados));
		accidents.setAccTotalMuertos(accTotalMuertos);
		accidents.setAccTotalHeridos(accTotalHeridos);
		accidents.setAccTotalTestigos(new Long(accTotalTestigos));
		accidents.setAccIntervinoTransito(accIntervinoTransito);
		accidents.setAccNombreConduct(accNombreConduct);
		accidents.setAccCargoConduct(accCargoConduct);
		accidents.setAccCodCargoAcc(accCodCargoAcc);
		accidents.setAccDescripcion(accDescripcion);
		accidents.setAccidentsResults(accidentsResultsObj);
		accidents.setResponsibility(responsibilityObj);
		accidents.setSeverity(severityObj);
		if (accTipoAsignacion != null)
			accidents.setLegateesTypes(legateesTypesObj);
		accidents.setAccUso(accUso);
		accidents.setAccReclamo(accReclamo);

		if (accValorDano == null || accValorDano.trim().length() == 0)
			accidents.setAccValorDano(null);
		else if (accValorDano != null
				&& accValorDano.toString().trim().length() > 0)
			accidents.setAccValorDano(new Float(Util
					.convertirCadenaEntero(accValorDano)));

		if (accDeduciblesPesos == null
				|| accDeduciblesPesos.trim().length() == 0)
			accidents.setAccDeduciblesPesos(null);
		else if (accDeduciblesPesos != null
				&& accDeduciblesPesos.trim().length() > 0)
			accidents.setAccDeduciblesPesos(new Float(Util
					.convertirCadenaEntero(accDeduciblesPesos)));
		// accidents.setAccDeduciblesDolar(accDeduciblesDolar);
		accidents.setAccidentsStates(new AccidentsStatesDAO()
				.findById(ACCIDENTE_EN_PROCESO));
		accidents.setAccDeducible(accDeducible);
		accidents.setAccCargoDeducible(accCargoDeducible);
		accidents.setAccNombreAsignatario(accNombreAsignatario);
		accidents.setAccCedulaConduc(accCedulaConduc);
		accidents.setAccDescripcion(accDescripcion);
		accidents.setAccOrdenTrabajoActa(ordenTrabajo);
		accidents.setAccSancInterActa(sanciones);
		accidents.setAccRecomendaciones(accRecomendaciones);
		accidents.setAccEmailConductor(accEmailConductor);
		accidents.setAccHora(accHora);
		accidents.setAccObservaciones(accObservaciones);

		EntityManagerHelper.beginTransaction();
		accidentsDAO.save(accidents);
		EntityManagerHelper.commit();

		// accidents.setAccEmailConductor("raven_mantilla@yahoo.com");

		// envioNotificacion(accidents, accidents.getAccEmailConductor());
		// envioNotificacion(accidents, Util
		// .loadParametersValue("CORREO.NOTI.ACC.RRHH"));
		// envioNotificacion(accidents, Util
		// .loadParametersValue("CORREO.NOTI.ACC.CxC"));

	}

	/**
	 * Envio notificacion.
	 *
	 * @param accidente the accidente
	 * @param correoDestino the correo destino
	 * @throws GWorkException the g work exception
	 */
	public void envioNotificacion(Accidents accidente, String correoDestino)
			throws GWorkException {

		Rolls rolls = new RollsDAO().findById(new Long(Util
				.loadParametersValue("MAIL.RECURSOSHUMANOS")));

		// cuerpo de la notificacion
		String textmessage = "Accidente:\t" + accidente.getAccNumeroSiniestro()
				+ "\n";
		textmessage += "Conductor:\t" + accidente.getAccNombreConduct()
				+ " ( asig. " + accidente.getAccNombreAsignatario() + ")\n";
		textmessage += "Placa:\t"
				+ accidente.getVehicles().getVhcPlacaDiplomatica() + "\n";
		textmessage += "Asignacion:\t"
				+ accidente.getLegateesTypes().getLgtNombre() + "\n";
		textmessage += "Fecha, Hora, Lugar:\t"
				+ accidente.getAccFechaAccidente() + ", "
				+ accidente.getAccHora() + ", "
				+ accidente.getAccSitioAccidente() + "\n";
		textmessage += "Uso:\t" + accidente.getAccUso() + "\n";
		textmessage += "Causas:\t" + accidente.getAccDescripcion() + "\n";
		textmessage += "Severidad:\t" + accidente.getSeverity() + "\n";
		textmessage += "Responsabilidad:\t" + accidente.getResponsibility()
				+ "\n";
		textmessage += "Valor:\t" + accidente.getAccValorDano()
				+ " Valor Deducible: " + accidente.getAccCargoDeducible()
				+ "Mediante O.T: " + accidente.getAccOrdenTrabajoActa();
		textmessage += "Observaciones:\t" + accidente.getAccObservaciones()
				+ "\n";
		textmessage += "Recomendaciones:\t" + accidente.getAccRecomendaciones()
				+ "\n";
	}

	/* (non-Javadoc)
	 * @see geniar.siscar.logic.accidents.services.DataAccidentsService#findVehicleByPlaca(java.lang.String)
	 */
	public Vehicles findVehicleByPlaca(String placa) throws GWorkException {

		IVehiclesDAO vehiclesDAO = new VehiclesDAO();

		if (vehiclesDAO.findByVhcPlacaDiplomatica(placa) == null
				|| vehiclesDAO.findByVhcPlacaDiplomatica(placa).isEmpty()
				|| vehiclesDAO.findByVhcPlacaDiplomatica(placa).size() == 0)
			throw new GWorkException(Util
					.loadErrorMessageValue("PLACA.EXISTEN"));

		return vehiclesDAO.findByVhcPlacaDiplomatica(placa).get(0);
	}

	/* (non-Javadoc)
	 * @see geniar.siscar.logic.accidents.services.DataAccidentsService#consultarAsignacionVehiculo(java.lang.String)
	 */
	public VehiclesAssignation consultarAsignacionVehiculo(String placa)
			throws GWorkException {
		return SearchVehicles.consultarAsignacionVehiculo(placa);
	}

	/* (non-Javadoc)
	 * @see geniar.siscar.logic.accidents.services.DataAccidentsService#findAccidentByEstado(java.lang.Long, java.util.Date, java.util.Date)
	 */
	public List<Accidents> findAccidentByEstado(Long idEstado,
			Date fechaInicia, Date fechaFinal) throws GWorkException {

		List<Accidents> lisAccidents = new SearchParametersAccidents()
				.findAccidentByEstado(idEstado, fechaInicia, fechaFinal);

		if (lisAccidents == null || lisAccidents.size() == 0
				|| lisAccidents.isEmpty())
			throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));

		return lisAccidents;
	}

	/* (non-Javadoc)
	 * @see geniar.siscar.logic.accidents.services.DataAccidentsService#findAccidentByNumeroSiniestro(java.lang.Long, java.util.Date, java.util.Date)
	 */
	public List<Accidents> findAccidentByNumeroSiniestro(Long numeroSiniestro,
			Date fechaInicia, Date fechaFinal) throws GWorkException {

		List<Accidents> lisAccidents = new SearchParametersAccidents()
				.findAccidentByNumeroSiniestro(numeroSiniestro, fechaInicia,
						fechaFinal);

		if (lisAccidents == null || lisAccidents.size() == 0
				|| lisAccidents.isEmpty())
			throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));

		return lisAccidents;
	}

	/* (non-Javadoc)
	 * @see geniar.siscar.logic.accidents.services.DataAccidentsService#findAccidentByPlaca(java.lang.String, java.util.Date, java.util.Date)
	 */
	public List<Accidents> findAccidentByPlaca(String placa, Date fechaInicia,
			Date fechaFinal) throws GWorkException {

		List<Accidents> lisAccidents = new SearchParametersAccidents()
				.findAccidentByPlaca(placa, fechaInicia, fechaFinal);

		if (lisAccidents == null || lisAccidents.size() == 0
				|| lisAccidents.isEmpty())
			throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));

		return lisAccidents;

	}

	/* (non-Javadoc)
	 * @see geniar.siscar.logic.accidents.services.DataAccidentsService#modificarAccidente(java.lang.Long, java.lang.String, java.util.Date, java.lang.Long, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.Long, java.lang.Long, java.lang.Long, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.Long, java.lang.String, java.lang.String, java.lang.Long, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public void modificarAccidente(Long idAccidente, String placa,
			Date accFechaAccidente, Long idCities, String accSitioAccidente,
			String accNumeroSiniestro, String accVehiculosInvolucrados,
			String accTotalMuertos, String accTotalHeridos,
			String accTotalTestigos, String accIntervinoTransito,
			String accNombreConduct, String accCargoConduct,
			String accCodCargoAcc, String accDescripcion, Long resultados,
			Long responsibility, Long severity, String accUso,
			String accReclamo, String accValorDano, String accDeduciblesPesos,
			String accDeduciblesDolar, String accDeducible,
			String accCargoDeducible, Long accTipoAsignacion,
			String accNombreAsignatario, String accCedulaConduc,
			Long estadoAccidente, String sanciones, String ordenTrabajo,
			String accRecomendaciones, String accObservaciones,
			String accEmailConductor, String accHora) throws GWorkException {

		Accidents accidents = new Accidents();

		if (idAccidente != null)
			accidents = new AccidentsDAO().findById(idAccidente);

		IAccidentsDAO accidentsDAO = new AccidentsDAO();
		Vehicles vehiclesObj = new Vehicles();
		Cities citiesObj = new Cities();
		AccidentsResults accidentsResultsObj = new AccidentsResults();
		Responsibility responsibilityObj = new Responsibility();
		Severity severityObj = new Severity();
		LegateesTypes legateesTypesObj = new LegateesTypes();

		vehiclesObj = findVehicleByPlaca(placa);
		citiesObj = new CitiesDAO().findById(idCities);
		accidentsResultsObj = new AccidentsResultsDAO().findById(resultados);
		responsibilityObj = new ResponsibilityDAO().findById(responsibility);
		severityObj = new SeverityDAO().findById(severity);

		if (accTipoAsignacion != null)
			legateesTypesObj = new LegateesTypesDAO()
					.findById(accTipoAsignacion);

		accidents.setVehicles(vehiclesObj);
		accidents.setAccFechaAccidente(accFechaAccidente);
		accidents.setCities(citiesObj);
		accidents.setAccSitioAccidente(accSitioAccidente);
		if (accNumeroSiniestro != null
				&& accNumeroSiniestro.trim().length() > 3)
			accidents.setAccNumeroSiniestro(new Long(accNumeroSiniestro));
		accidents
				.setAccVehiculosInvolucrados(new Long(accVehiculosInvolucrados));
		accidents.setAccTotalMuertos(accTotalMuertos);
		accidents.setAccTotalHeridos(accTotalHeridos);
		accidents.setAccTotalTestigos(new Long(accTotalTestigos));
		accidents.setAccIntervinoTransito(accIntervinoTransito);
		accidents.setAccNombreConduct(accNombreConduct);
		accidents.setAccCargoConduct(accCargoConduct);
		accidents.setAccCodCargoAcc(accCodCargoAcc);
		accidents.setAccDescripcion(accDescripcion);
		accidents.setAccidentsResults(accidentsResultsObj);
		accidents.setResponsibility(responsibilityObj);
		accidents.setSeverity(severityObj);
		if (accTipoAsignacion != null)
			accidents.setLegateesTypes(legateesTypesObj);
		accidents.setAccUso(accUso);
		accidents.setAccReclamo(accReclamo);

		if (accValorDano == null || accValorDano.trim().length() == 0)
			accidents.setAccValorDano(null);
		else if (accValorDano != null
				&& accValorDano.toString().trim().length() > 0)
			accidents.setAccValorDano(new Float(Util
					.convertirCadenaEntero(accValorDano)));

		if (accDeduciblesPesos == null
				|| accDeduciblesPesos.trim().length() == 0)
			accidents.setAccDeduciblesPesos(null);
		else if (accDeduciblesPesos != null
				&& accDeduciblesPesos.trim().length() > 0)
			accidents.setAccDeduciblesPesos(new Float(Util
					.convertirCadenaEntero(accDeduciblesPesos)));
		// accidents.setAccDeduciblesDolar(accDeduciblesDolar);
		accidents.setAccidentsStates(new AccidentsStatesDAO()
				.findById(estadoAccidente));
		accidents.setAccDeducible(accDeducible);
		accidents.setAccCargoDeducible(accCargoDeducible);
		accidents.setAccNombreAsignatario(accNombreAsignatario);
		accidents.setAccCedulaConduc(accCedulaConduc);
		accidents.setAccDescripcion(accDescripcion);
		accidents.setAccOrdenTrabajoActa(ordenTrabajo);
		accidents.setAccSancInterActa(sanciones);
		accidents.setAccRecomendaciones(accRecomendaciones);
		accidents.setAccObservaciones(accObservaciones);
		accidents.setAccEmailConductor(accEmailConductor);
		accidents.setAccHora(accHora);

		try {
			EntityManagerHelper.getEntityManager().getTransaction().begin();
			accidentsDAO.update(accidents);
			EntityManagerHelper.getEntityManager().getTransaction().commit();
		} catch (Exception e) {
			log.error("modificarAccidente", e);
			EntityManagerHelper.getEntityManager().getTransaction().rollback();
			throw new GWorkException(e.getMessage(), e);
		}

		// enviar notificación si la modificación se realizo cargándo el
		// costo del accidente al empleado

		if (accCargoDeducible
				.equals(Util.loadParametersValue("CARGADOA.CARNE"))
				|| accCargoDeducible.equals(Util
						.loadParametersValue("CARGADOA.TERCERO"))) {

			String server = Util.loadParametersValue("MAIL.HOST");
			String fromUser = Util.loadParametersValue("CORREO.MOTORPOOL");

			// definir los mails de los entes a enviar el correo (asig =
			// asignatario,
			// RH = recursos humanos, ACC = Área de cuentas por cobrar)
			String toUserAsig = "";
			List<Users> users = (new UsersDAO())
					.findByUsrNombre(accNombreAsignatario);
			if (users != null && users.size() > 0)
				toUserAsig = users.get(0).getUsrEmail();

			String toUserRH = Util.loadParametersValue("CORREO.NOTI.ACC.RRHH");
			String toUserACC = Util.loadParametersValue("CORREO.NOTI.ACC.CxC");

			String subject = Util.loadParametersValue("ASUNTO_ACTA_ACCIDENTE");

			StringBuffer mensaje = new StringBuffer();

			SendEmail mailAsig = new SendEmail(toUserAsig, fromUser, server,
					"false", subject, mensaje.toString());
			SendEmail mailRH = new SendEmail(toUserRH, fromUser, server,
					"false", subject, mensaje.toString());
			SendEmail mailACC = new SendEmail(toUserACC, fromUser, server,
					"false", subject, mensaje.toString());

			// try {
			// String enviado = mailAsig.SendMessage();
			// if (enviado.equals("ERROR"))
			// throw new GWorkException(
			// "No se pudo enviar la notificación al asignatario");
			//
			// enviado = null;
			// enviado = mailRH.SendMessage();
			// if (enviado.equals("ERROR"))
			// throw new GWorkException(
			// "No se pudo enviar la notificación al área de recursos humanos");
			//
			// enviado = null;
			// enviado = mailACC.SendMessage();
			// if (enviado.equals("ERROR"))
			// throw new GWorkException(
			// "No se pudo enviar la notificación al área de cuentas por
			// cobrar");
			// } catch (Exception e) {
			// throw new GWorkException(e.getMessage() + "\n"
			// + e.getLocalizedMessage());
			// }

		}
	}

	/* (non-Javadoc)
	 * @see geniar.siscar.logic.accidents.services.DataAccidentsService#registrarVehiculosInvolucrados(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.Long, java.lang.Long)
	 */
	public void registrarVehiculosInvolucrados(String hnvTipoVehiculo,
			String hnvPlaca, String hnvMarca, String hnvModelo,
			String hnvIdentifConduc, String hnvConductor,
			String hnvDireccionconductor, String hnvPropietario,
			String hnvTelefono, String hnvIdentifProp, Long idAccidente,
			Long idCiudad) throws GWorkException {

		InvolvedVehicles involvedVehicles = new InvolvedVehicles();
		IInvolvedVehiclesDAO involvedVehiclesDAO = new InvolvedVehiclesDAO();
		List<InvolvedVehicles> listInvolvedVehicles = new InvolvedVehiclesDAO()
				.findByHnvPlaca(hnvPlaca);

		for (int i = 0; i < listInvolvedVehicles.size(); i++) {

			if (listInvolvedVehicles != null
					&& listInvolvedVehicles.size() > 0
					&& !listInvolvedVehicles.isEmpty()
					&& listInvolvedVehicles.get(i).getAccidents()
							.getAccCodigo().longValue() == idAccidente
							.longValue())
				throw new GWorkException(Util
						.loadErrorMessageValue("VEHICULOINVOLUCRADO.EXISTE"));
		}
		involvedVehicles.setCities(new CitiesDAO().findById(idCiudad));
		involvedVehicles.setAccidents(new AccidentsDAO().findById(idAccidente));
		involvedVehicles.setHnvConductor(hnvConductor);
		involvedVehicles.setHnvDireccionconductor(hnvDireccionconductor);
		involvedVehicles.setHnvIdentifConduc(hnvIdentifConduc);
		involvedVehicles.setHnvIdentifProp(hnvIdentifProp);
		involvedVehicles.setHnvMarca(hnvMarca);
		involvedVehicles.setHnvModelo(hnvModelo);
		involvedVehicles.setHnvPlaca(hnvPlaca);
		involvedVehicles.setHnvPropietario(hnvPropietario);
		involvedVehicles.setHnvTelefConduc(hnvTelefono);
		involvedVehicles.setHnvTipoVehiculo(hnvTipoVehiculo);

		// Consultar si el vehiculo no ha sido ingresado con anterioridad
		List<InvolvedVehicles> ListInvolvedVehicles = involvedVehiclesDAO
				.findByHnvPlaca(involvedVehicles.getHnvPlaca());

		if (ListInvolvedVehicles == null || ListInvolvedVehicles.size() <= 0) {
			EntityManagerHelper.getEntityManager().getTransaction().begin();
			involvedVehiclesDAO.save(involvedVehicles);
			EntityManagerHelper.getEntityManager().getTransaction().commit();
		} else
			throw new GWorkException(Util
					.loadErrorMessageValue("VEHICULOINVOLUCRADO.EXISTE"));
	}

	/**
	 * Registro list involved vehicles.
	 *
	 * @param listInvolvedVehicles the list involved vehicles
	 * @param accidents the accidents
	 * @param idAccidente the id accidente
	 */
	static void registroListInvolvedVehicles(
			List<InvolvedVehicles> listInvolvedVehicles, Accidents accidents,
			Long idAccidente) {

		if (idAccidente != null)
			eliminarVehiculosInvolucrados(idAccidente);

		if (listInvolvedVehicles != null && listInvolvedVehicles.size() > 0
				&& !listInvolvedVehicles.isEmpty()) {

			for (InvolvedVehicles involvedVehicles : listInvolvedVehicles) {

				InvolvedVehicles involvedVehiclesObj = new InvolvedVehicles();

				involvedVehiclesObj.setAccidents(accidents);
				involvedVehiclesObj.setHnvConductor(involvedVehicles
						.getHnvConductor());
				involvedVehiclesObj.setHnvDireccionconductor(involvedVehicles
						.getHnvDireccionconductor());
				involvedVehiclesObj.setHnvIdentifConduc(involvedVehicles
						.getHnvIdentifConduc());
				involvedVehiclesObj.setHnvIdentifProp(involvedVehicles
						.getHnvIdentifProp());
				involvedVehiclesObj.setHnvMarca(involvedVehicles.getHnvMarca());
				involvedVehiclesObj.setHnvModelo(involvedVehicles
						.getHnvModelo());
				involvedVehiclesObj.setHnvPlaca(involvedVehicles.getHnvPlaca());
				involvedVehiclesObj.setHnvPropietario(involvedVehicles
						.getHnvPropietario());
				involvedVehiclesObj.setHnvTelefConduc(involvedVehicles
						.getHnvTelefConduc());
				involvedVehiclesObj.setHnvTipoVehiculo(involvedVehicles
						.getHnvTipoVehiculo());
				involvedVehiclesObj.setCities(new CitiesDAO().findById(88L));
				EntityManagerHelper.beginTransaction();
				new InvolvedVehiclesDAO().save(involvedVehiclesObj);
				EntityManagerHelper.commit();

			}
		}

	}

	/**
	 * Eliminar vehiculos involucrados.
	 *
	 * @param idAccidente the id accidente
	 */
	private static void eliminarVehiculosInvolucrados(Long idAccidente) {

		try {

			EntityManagerHelper.beginTransaction();
			final String queryString = "delete from InvolvedVehicles model where model.accidents.accCodigo= :idAccidente";
			javax.persistence.Query query = EntityManagerHelper
					.getEntityManager().createQuery(queryString);
			query.setParameter("idAccidente", idAccidente);
			query.executeUpdate();
			EntityManagerHelper.commit();

		} catch (RuntimeException re) {
			log.error("eliminarVehiculosInvolucrados", re);
		}

	}

	/* (non-Javadoc)
	 * @see geniar.siscar.logic.accidents.services.DataAccidentsService#registroLesionadosVehiculos(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.Long, java.lang.String, java.lang.String)
	 */
	public void registroLesionadosVehiculos(String pivId, String pivNombre,
			String pivDireccion, String pnvTelefono, Long pivEdad,
			String pivSitioatencion, String placa) throws GWorkException {

		InjuredPersons injuredPersons = new InjuredPersons();
		IInjuredPersonsDAO injuredPersonsDAO = new InjuredPersonsDAO();
		InvolvedVehicles involvedVehicles = new InvolvedVehicles();

		if (placa != null && placa.trim().length() > 0)
			involvedVehicles = new InvolvedVehiclesDAO().findByHnvPlaca(placa)
					.get(0);

		List<InjuredPersons> listInjuredPersons = injuredPersonsDAO
				.findByPivId(pivId);

		if (listInjuredPersons != null && listInjuredPersons.size() > 0
				&& !listInjuredPersons.isEmpty())
			throw new GWorkException(Util
					.loadErrorMessageValue("LESIONADO.EXISTE"));

		injuredPersons.setInvolvedVehicles(involvedVehicles);
		injuredPersons.setPivDireccion(pivDireccion);
		injuredPersons.setPivEdad(pivEdad);
		injuredPersons.setPivId(pivId);
		injuredPersons.setPivNombre(pivNombre);
		injuredPersons.setPivSitioatencion(pivSitioatencion);
		injuredPersons.setPnvTelefono(pnvTelefono);

		EntityManagerHelper.beginTransaction();
		injuredPersonsDAO.save(injuredPersons);
		EntityManagerHelper.commit();
		// Util.limpiarSession();

	}

	/* (non-Javadoc)
	 * @see geniar.siscar.logic.accidents.services.DataAccidentsService#lesionadosVehiculos(java.lang.String)
	 */
	public List<InjuredPersons> lesionadosVehiculos(String placa)
			throws GWorkException {

		List<InjuredPersons> listInjuredPersons = new SearchParametersAccidents()
				.lesionadosVehiculos(placa);

		if (listInjuredPersons == null || listInjuredPersons.size() == 0
				|| listInjuredPersons.isEmpty())
			throw new GWorkException(Util
					.loadErrorMessageValue("CONSULTA.LESIONADOS"));
		return listInjuredPersons;
	}

	/* (non-Javadoc)
	 * @see geniar.siscar.logic.accidents.services.DataAccidentsService#consultarLesionado(java.lang.String)
	 */
	public InjuredPersons consultarLesionado(String identificacion)
			throws GWorkException {

		List<InjuredPersons> listInjuredPersons = new InjuredPersonsDAO()
				.findByPivId(identificacion);

		if (listInjuredPersons == null || listInjuredPersons.size() == 0
				|| listInjuredPersons.isEmpty())
			throw new GWorkException(Util
					.loadErrorMessageValue("LESIONADO.EXISTEN"));

		return listInjuredPersons.get(0);
	}

	/* (non-Javadoc)
	 * @see geniar.siscar.logic.accidents.services.DataAccidentsService#modificarLesionadosVehiculos(java.lang.Long, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.Long, java.lang.String, java.lang.String)
	 */
	public void modificarLesionadosVehiculos(Long idLesionadoVehiculo,
			String pivId, String pivNombre, String pivDireccion,
			String pnvTelefono, Long pivEdad, String pivSitioatencion,
			String placa) throws GWorkException {

		InjuredPersons injuredPersons = new InjuredPersons();
		IInjuredPersonsDAO injuredPersonsDAO = new InjuredPersonsDAO();
		InvolvedVehicles involvedVehicles = new InvolvedVehicles();
		injuredPersons = injuredPersonsDAO.findById(idLesionadoVehiculo);

		if (placa != null && placa.trim().length() > 0)
			involvedVehicles = new InvolvedVehiclesDAO().findByHnvPlaca(placa)
					.get(0);

		List<InjuredPersons> listInjuredPersons = injuredPersonsDAO
				.findByPivId(pivId);

		if (listInjuredPersons != null && listInjuredPersons.size() > 0
				&& !listInjuredPersons.isEmpty()
				&& (!pivId.equalsIgnoreCase(injuredPersons.getPivId())))
			throw new GWorkException(Util
					.loadErrorMessageValue("LESIONADO.EXISTE"));

		injuredPersons.setInvolvedVehicles(involvedVehicles);
		injuredPersons.setPivDireccion(pivDireccion);
		injuredPersons.setPivEdad(pivEdad);
		injuredPersons.setPivId(pivId);
		injuredPersons.setPivNombre(pivNombre);
		injuredPersons.setPivSitioatencion(pivSitioatencion);
		injuredPersons.setPnvTelefono(pnvTelefono);

		EntityManagerHelper.beginTransaction();
		injuredPersonsDAO.update(injuredPersons);
		EntityManagerHelper.commit();
		// Util.limpiarSession();

	}

	/* (non-Javadoc)
	 * @see geniar.siscar.logic.accidents.services.DataAccidentsService#eliminarLesionado(java.lang.String)
	 */
	public void eliminarLesionado(String identificacion) throws GWorkException {

		InjuredPersons injuredPersons = new InjuredPersons();
		injuredPersons = consultarLesionado(identificacion);

		EntityManagerHelper.beginTransaction();
		new InjuredPersonsDAO().delete(injuredPersons);
		EntityManagerHelper.commit();
		// Util.limpiarSession();

	}

	/* (non-Javadoc)
	 * @see geniar.siscar.logic.accidents.services.DataAccidentsService#listarVehiculosInvolucrados(java.lang.Long)
	 */
	public List<InvolvedVehicles> listarVehiculosInvolucrados(Long idAccidente)
			throws GWorkException {

		List<InvolvedVehicles> listInvolvedVehicles = new SearchParametersAccidents()
				.listarVehiculosInvolucrados(idAccidente);

		return listInvolvedVehicles;
	}

	/* (non-Javadoc)
	 * @see geniar.siscar.logic.accidents.services.DataAccidentsService#modificarVehiculosInvolucrados(java.lang.Long, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.Long, java.lang.Long)
	 */
	public void modificarVehiculosInvolucrados(Long idVehiculo,
			String hnvTipoVehiculo, String hnvPlaca, String hnvMarca,
			String hnvModelo, String hnvIdentifConduc, String hnvConductor,
			String hnvDireccionconductor, String hnvPropietario,
			String hnvTelefono, String hnvIdentifProp, Long idAccidente,
			Long idCiudad) throws GWorkException {

		InvolvedVehicles involvedVehicles = null;
		IInvolvedVehiclesDAO involvedVehiclesDAO = new InvolvedVehiclesDAO();
		involvedVehicles = involvedVehiclesDAO.findById(idVehiculo);
		List<InvolvedVehicles> listInvolvedVehicles = new InvolvedVehiclesDAO()
				.findByHnvPlaca(hnvPlaca);

		if (involvedVehicles == null)
			throw new GWorkException(Util
					.loadErrorMessageValue("VEHICULOINVOLUCRADO.EXISTEN"));

		for (int i = 0; i < listInvolvedVehicles.size(); i++) {

			if (listInvolvedVehicles != null
					&& listInvolvedVehicles.size() > 0
					&& !listInvolvedVehicles.isEmpty()
					&& listInvolvedVehicles.get(i).getAccidents()
							.getAccCodigo().longValue() == idAccidente
							.longValue()
					&& listInvolvedVehicles.get(i).getHnvCodigo() != involvedVehicles
							.getHnvCodigo())
				throw new GWorkException(Util
						.loadErrorMessageValue("VEHICULOINVOLUCRADO.EXISTE"));
		}

		involvedVehicles.setCities(new CitiesDAO().findById(idCiudad));
		involvedVehicles.setAccidents(new AccidentsDAO().findById(idAccidente));
		involvedVehicles.setHnvConductor(hnvConductor);
		involvedVehicles.setHnvDireccionconductor(hnvDireccionconductor);
		involvedVehicles.setHnvIdentifConduc(hnvIdentifConduc);
		involvedVehicles.setHnvIdentifProp(hnvIdentifProp);
		involvedVehicles.setHnvMarca(hnvMarca);
		involvedVehicles.setHnvModelo(hnvModelo);
		involvedVehicles.setHnvPlaca(hnvPlaca);
		involvedVehicles.setHnvPropietario(hnvPropietario);
		involvedVehicles.setHnvTelefConduc(hnvTelefono);
		involvedVehicles.setHnvTipoVehiculo(hnvTipoVehiculo);

		EntityManagerHelper.beginTransaction();
		involvedVehiclesDAO.update(involvedVehicles);
		EntityManagerHelper.commit();
	}

	/* (non-Javadoc)
	 * @see geniar.siscar.logic.accidents.services.DataAccidentsService#eliminarVehiculoInvolucrado(java.lang.Long)
	 */
	public void eliminarVehiculoInvolucrado(Long idVehiculo)
			throws GWorkException {

		InvolvedVehicles involvedVehicles = null;
		IInvolvedVehiclesDAO involvedVehiclesDAO = new InvolvedVehiclesDAO();

		involvedVehicles = involvedVehiclesDAO.findById(idVehiculo);

		if (involvedVehicles == null) {
			throw new GWorkException(Util
					.loadErrorMessageValue("VEHICULOINVOLUCRADO.EXISTEN"));
		}

		EntityManagerHelper.getEntityManager().getTransaction().begin();
		involvedVehiclesDAO.delete(involvedVehicles);
		EntityManagerHelper.getEntityManager().getTransaction().commit();
	}

	/* (non-Javadoc)
	 * @see geniar.siscar.logic.accidents.services.DataAccidentsService#deleteVehiculoInvolucrado(java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	public void deleteVehiculoInvolucrado(Long idVehiculo)
			throws GWorkException {

		InvolvedVehicles involvedVehicles = null;
		IInvolvedVehiclesDAO involvedVehiclesDAO = new InvolvedVehiclesDAO();

		involvedVehicles = involvedVehiclesDAO.findById(idVehiculo);

		if (involvedVehicles != null) {

			/* verificar asociacion con lesionados */
			String queryString = "select model from InjuredPersons model "
					+ "where model.involvedVehicles.hnvCodigo= :hnvCodigo";

			javax.persistence.Query query = EntityManagerHelper
					.getEntityManager().createQuery(queryString);
			query.setParameter("hnvCodigo", involvedVehicles.getHnvCodigo());
			List<InjuredPersons> ListInjuredPersons = (List<InjuredPersons>) query
					.getResultList();

			if (ListInjuredPersons != null && ListInjuredPersons.size() > 0) {
				throw new GWorkException(Util
						.loadErrorMessageValue("VEHICULOINVOLUCRADO.RELACION")
						+ " " + "lesionados");
			}

			/* borrar registro en caso de no existir relaciones */
			EntityManagerHelper.getEntityManager().getTransaction().begin();
			involvedVehiclesDAO.delete(involvedVehicles);
			EntityManagerHelper.getEntityManager().getTransaction().commit();
		} else {
			throw new GWorkException(Util
					.loadErrorMessageValue("VEHICULOINVOLUCRADO.EXISTEN"));
		}

	}

	/* (non-Javadoc)
	 * @see geniar.siscar.logic.accidents.services.DataAccidentsService#consultarAccidente(java.lang.Long)
	 */
	public Accidents consultarAccidente(Long idAccidente) throws GWorkException {
		Accidents accidents = null;
		// EntityManagerHelper.getEntityManager().clear();
		accidents = new SearchParametersAccidents().consultarAccidente(
				idAccidente).get(0);

		if (accidents == null)
			throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));

		return accidents;
	}

	/* (non-Javadoc)
	 * @see geniar.siscar.logic.accidents.services.DataAccidentsService#cantidadLesionados(java.lang.Long)
	 */
	public List<InjuredPersons> cantidadLesionados(Long idAccidente)
			throws GWorkException {

		List<InjuredPersons> listaLesionados = new SearchParametersAccidents()
				.cantidadLesionados(idAccidente);
		return listaLesionados;
	}

	/* (non-Javadoc)
	 * @see geniar.siscar.logic.accidents.services.DataAccidentsService#filtroConductores(java.lang.String, java.lang.String)
	 */
	public List<Driver> filtroConductores(String cedula, String nombre)
			throws GWorkException {

		List<Driver> listaConductores = new SearchParametersAccidents()
				.filtroConductores(cedula, nombre);

		if (listaConductores == null || listaConductores.size() == 0
				|| listaConductores.isEmpty())
			throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));

		return listaConductores;
	}

	/* (non-Javadoc)
	 * @see geniar.siscar.logic.accidents.services.DataAccidentsService#GuardarTestigos(java.lang.String, java.lang.Long)
	 */
	public void GuardarTestigos(String CantidadTestigos, Long idAccidente)
			throws GWorkException {
		Accidents accidents = new Accidents();

		if (idAccidente != null && CantidadTestigos.length() > 0) {
			accidents = new AccidentsDAO().findById(idAccidente);

			EntityManagerHelper.getEntityManager().refresh(accidents);

			accidents.setAccTotalTestigos(new Long(CantidadTestigos));
			try {
				EntityManagerHelper.getEntityManager().getTransaction().begin();
				new AccidentsDAO().update(accidents);
				EntityManagerHelper.getEntityManager().getTransaction()
						.commit();
			} catch (Exception e) {
				log.error("GuardarTestigos", e);
				throw new GWorkException(e.getMessage(), e);
			}
		}
	}

	/* (non-Javadoc)
	 * @see geniar.siscar.logic.accidents.services.DataAccidentsService#GuardarLesionados(java.lang.String, java.lang.Long)
	 */
	public void GuardarLesionados(String CantidadLesionados, Long idAccidente)
			throws GWorkException {
		Accidents accidents = new Accidents();

		if (idAccidente != null && CantidadLesionados.length() > 0) {
			accidents = new AccidentsDAO().findById(idAccidente);

			EntityManagerHelper.getEntityManager().refresh(accidents);

			accidents.setAccTotalHeridos(CantidadLesionados);
			try {
				EntityManagerHelper.getEntityManager().getTransaction().begin();
				new AccidentsDAO().update(accidents);
				EntityManagerHelper.getEntityManager().getTransaction()
						.commit();
			} catch (Exception e) {
				log.error("GuardarLesionados", e);
				throw new GWorkException(e.getMessage(), e);
			}
		}
	}

	/* (non-Javadoc)
	 * @see geniar.siscar.logic.accidents.services.DataAccidentsService#GuardarVehiculosInvolucrados(java.lang.String, java.lang.Long)
	 */
	public void GuardarVehiculosInvolucrados(
			String CantidadVehiculosInvolucrados, Long idAccidente)
			throws GWorkException {
		Accidents accidents = new Accidents();

		if (idAccidente != null && CantidadVehiculosInvolucrados.length() > 0) {
			accidents = new AccidentsDAO().findById(idAccidente);

			EntityManagerHelper.getEntityManager().refresh(accidents);

			accidents.setAccVehiculosInvolucrados(new Long(
					CantidadVehiculosInvolucrados));
			try {
				EntityManagerHelper.getEntityManager().getTransaction().begin();
				new AccidentsDAO().update(accidents);
				EntityManagerHelper.getEntityManager().getTransaction()
						.commit();
			} catch (Exception e) {
				log.error("GuardarVehiculosInvolucrados", e);
				throw new GWorkException(e.getMessage(), e);
			}
		}
	}
}
