package geniar.siscar.inicialization;

import geniar.siscar.logic.parameters.services.FindParameterService;
import geniar.siscar.logic.parametros.services.impl.FindParameterServiceImpl;
import geniar.siscar.taskmanager.NotificacionCobroCiatCasaCiat;
import geniar.siscar.taskmanager.NotificacionSiscarVsActivoFijo;
import geniar.siscar.taskmanager.NotificationRentEffectiveNext;
import geniar.siscar.taskmanager.NotificationReservVehiclesNotGathered;
import geniar.siscar.taskmanager.NotificationTaskAssignationEnd;
import geniar.siscar.taskmanager.NotificationTaskRentEnd;
import geniar.siscar.util.ManipulacionFechas;
import geniar.siscar.util.ParametersUtil;
import geniar.siscar.util.Util;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * The Class InicializacionComponentes.
 */
public class InicializacionComponentes extends HttpServlet {

	/** The tare assignation end. */
	private static NotificationTaskAssignationEnd tareAssignationEnd;
	
	/** The tarea rent end. */
	private static NotificationTaskRentEnd tareaRentEnd;
	
	/** The tarea vehicle reserv not gathered. */
	private static NotificationReservVehiclesNotGathered tareaVehicleReservNotGathered;
	
	/** The notification rent effective next. */
	private static NotificationRentEffectiveNext notificationRentEffectiveNext;
	
	/** The notificacion ciat casa ciat. */
	private static NotificacionCobroCiatCasaCiat notificacionCiatCasaCiat;
	
	/** The notificacion siscar vs activo fijo. */
	private static NotificacionSiscarVsActivoFijo notificacionSiscarVsActivoFijo;

	/** The Constant log. */
	private static final Log log = LogFactory
			.getLog(InicializacionComponentes.class);

	/** The fecha actual. */
	private static Date fechaActual;
	
	/** The str dia cobro ciat casa ciat. */
	private static String strDiaCobroCiatCasaCiat = null;
	
	/** The str dia notificacion activos fijos. */
	private static String strDiaNotificacionActivosFijos = null;
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public InicializacionComponentes() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occure
	 */
	public void init() throws ServletException {
		try {
			FindParameterService param = new FindParameterServiceImpl();
			
			// Parametro de activacion de notificaciones
			if (param.findParameterById(ParametersUtil.PARAM_GEN_NOTIFICACIONES_AUTOMATICAS).longValue() == 1L) {

				/*log.info("INICIALIZANDO COMPONENTES");

				log.info("############################################################");
				log.info("         COMPONENTES siscar INCIALIZADOS CORRECTAMENTE");
				log.info("############################################################");

				log.info("         [INICIALIZANDO TAREAS AUTOMATICAS....]");
				log.info("         [TAREA AUTOMATICA NOTIFICACION DE TERMINACION DE ASIGNACION  INICIALIZADA....]");

				inicializarTarea();

				log.info("         [TAREA AUTOMATICA NOTIFICACION DE TERMINACION DE ALQUILER INICIALIZADA....]");

				inicializarTarea_II();

				log.info("         [TAREA AUTOMATICA NOTIFICACION DE VEHÍCULOS RESERVADOS NO RECOGIDOS INIZIALIZADA....]");

				inicializarTarea_III();

				log.info("         [TAREA AUTOMATICA NOTIFICACION DE RESERVAS PROXIMA A HACERSE EFECTIVAS INIZIALIZADA....]");

				inicializarTarea_IIII();

				fechaActual = new Date();

				strDiaCobroCiatCasaCiat = ManipulacionFechas.getDia(fechaActual);

				if (strDiaCobroCiatCasaCiat.equals("04")) {
					log.info("         [TAREA AUTOMATICA COBRO DE CIAT CASA CIAT....]");
					inicializarTareaCobroCiatCasaCiat();
				}

				strDiaNotificacionActivosFijos = ManipulacionFechas.getDia(fechaActual);

				if (strDiaNotificacionActivosFijos.equals("11")) {
					log.info("         [TAREA AUTOMATICA NOTIFICACION VEHICULOS CIAT VS ACTIVO FIJO....]");
					inicializarTareaSiscarVsActivosFijos();
				}*/
			}
		} catch (Exception e) {
			log.error("Error SISCAR: ", e);
		}
	}

	/**
	 * Metodo para inicializar la Tarea automaticas notificacion terminacion de
	 * asignacion.
	 *
	 * @author Jam - Geniar Architect S.A.
	 */
	public static void inicializarTarea() {
		try {
			String horaEjecucionTarea = Util
					.loadParametersValue("HORA_EJECUCION_TAREA");
			String minutosEjecucionTarea = Util
					.loadParametersValue("MINUTOS_EJECUCION_TAREA");

			// Si el hilo está corriendo
			if (tareAssignationEnd != null) {
				tareAssignationEnd.killTarea();

				Calendar calendar = Calendar.getInstance();

				calendar.set(Calendar.HOUR_OF_DAY, Integer
						.parseInt(horaEjecucionTarea));
				calendar.set(Calendar.MINUTE, Integer
						.parseInt(minutosEjecucionTarea));

				Date time = calendar.getTime();

				tareAssignationEnd = new NotificationTaskAssignationEnd(time);
			} else // Si no hay ninguna tarea corriendo
			{
				Calendar calendar = Calendar.getInstance();

				calendar.set(Calendar.HOUR_OF_DAY, Integer
						.parseInt(horaEjecucionTarea));
				calendar.set(Calendar.MINUTE, Integer
						.parseInt(minutosEjecucionTarea));

				Date time = calendar.getTime();

				tareAssignationEnd = new NotificationTaskAssignationEnd(time);
			}
		} catch (NumberFormatException e) {
			log.error("inicializarTarea: " + e.getMessage());
		} catch (Exception e) {
			log.error("inicializarTarea: " + e.getMessage());
		}
	}

	/**
	 * Metodo para inicializar la Tarea automaticas notificacion terminacion de
	 * alquiler.
	 *
	 * @author Jam - Geniar Architect S.A.
	 */
	public static void inicializarTarea_II() {
		try {

			String horaEjecucionTarea_II = Util
					.loadParametersValue("HORA_EJECUCION_TAREA_II");
			String minutosEjecucionTarea_II = Util
					.loadParametersValue("MINUTOS_EJECUCION_TAREA_II");

			// Si el hilo está corriendo
			if (tareaRentEnd != null) {
				tareaRentEnd.killTarea();

				Calendar calendar = Calendar.getInstance();

				calendar.set(Calendar.HOUR_OF_DAY, Integer
						.parseInt(horaEjecucionTarea_II));
				calendar.set(Calendar.MINUTE, Integer
						.parseInt(minutosEjecucionTarea_II));

				Date time = calendar.getTime();

				tareaRentEnd = new NotificationTaskRentEnd(time);
			} else // Si no hay ninguna tarea corriendo
			{
				Calendar calendar = Calendar.getInstance();

				calendar.set(Calendar.HOUR_OF_DAY, Integer
						.parseInt(horaEjecucionTarea_II));
				calendar.set(Calendar.MINUTE, Integer
						.parseInt(minutosEjecucionTarea_II));

				Date time = calendar.getTime();

				tareaRentEnd = new NotificationTaskRentEnd(time);
			}
		} catch (NumberFormatException e) {
			log.error("inicializarTarea: " + e.getMessage());
		} catch (Exception e) {
			log.error("inicializarTarea: " + e.getMessage());
		}
	}

	/**
	 * Metodo para inicializar la Tarea automatica de notificacion de vehículos
	 * reservados no recogidos.
	 *
	 * @author Geniar Architect S.A.
	 */
	public static void inicializarTarea_III() {
		try {

			String horaEjecucionTarea_III = Util
					.loadParametersValue("HORA_EJECUCION_TAREA_III");
			String minutosEjecucionTarea_III = Util
					.loadParametersValue("MINUTOS_EJECUCION_TAREA_III");

			// Si el hilo está corriendo
			if (tareaVehicleReservNotGathered != null) {
				tareaVehicleReservNotGathered.killTarea();

				Calendar calendar = Calendar.getInstance();

				calendar.set(Calendar.HOUR_OF_DAY, Integer
						.parseInt(horaEjecucionTarea_III));
				calendar.set(Calendar.MINUTE, Integer
						.parseInt(minutosEjecucionTarea_III));

				Date time = calendar.getTime();

				tareaVehicleReservNotGathered = new NotificationReservVehiclesNotGathered(
						time);
			} else // Si no hay ninguna tarea corriendo
			{
				Calendar calendar = Calendar.getInstance();

				calendar.set(Calendar.HOUR_OF_DAY, Integer
						.parseInt(horaEjecucionTarea_III));
				calendar.set(Calendar.MINUTE, Integer
						.parseInt(minutosEjecucionTarea_III));

				Date time = calendar.getTime();

				tareaVehicleReservNotGathered = new NotificationReservVehiclesNotGathered(
						time);
			}
		} catch (NumberFormatException e) {
			log.error("inicializarTarea: " + e.getMessage());
		} catch (Exception e) {
			log.error("inicializarTarea: " + e.getMessage());
		}
	}

	/**
	 * Metodo para inicializar la Tarea automatica de notificacion de reservas
	 * proximas a hacerse efectivas.
	 *
	 * @author Geniar Architect S.A.
	 */
	public static void inicializarTarea_IIII() {
		try {

			String horaEjecucionTarea_III = Util
					.loadParametersValue("HORA_EJECUCION_TAREA_IIII");
			String minutosEjecucionTarea_III = Util
					.loadParametersValue("MINUTOS_EJECUCION_TAREA_IIII");

			// Si el hilo está corriendo
			if (notificationRentEffectiveNext != null) {
				notificationRentEffectiveNext.killTarea();

				Calendar calendar = Calendar.getInstance();

				calendar.set(Calendar.HOUR_OF_DAY, Integer
						.parseInt(horaEjecucionTarea_III));
				calendar.set(Calendar.MINUTE, Integer
						.parseInt(minutosEjecucionTarea_III));

				Date time = calendar.getTime();

				notificationRentEffectiveNext = new NotificationRentEffectiveNext(
						time);
			} else // Si no hay ninguna tarea corriendo
			{
				Calendar calendar = Calendar.getInstance();

				calendar.set(Calendar.HOUR_OF_DAY, Integer
						.parseInt(horaEjecucionTarea_III));
				calendar.set(Calendar.MINUTE, Integer
						.parseInt(minutosEjecucionTarea_III));

				Date time = calendar.getTime();

				notificationRentEffectiveNext = new NotificationRentEffectiveNext(
						time);
			}
		} catch (NumberFormatException e) {
			log.error("inicializarTarea: " + e.getMessage());
		} catch (Exception e) {
			log.error("inicializarTarea: " + e.getMessage());
		}
	}

	/**
	 * Metodo para inicializar la Tarea automatica de cobro ciat casa ciat.
	 *
	 * @author Diego Humberto Bocanegra
	 */
	public static void inicializarTareaCobroCiatCasaCiat() {
		try {

			String horaEjecucionTareaCobroCiatCasaCiat = Util
					.loadParametersValue("HORA_EJECUCION_TAREA_COBRO_CIAT_CASA_CIAT");
			String minutosEjecucionTareaCobroCiatCasaCiat = Util
					.loadParametersValue("MINUTOS_EJECUCION_COBRO_CIAT_CASA_CIAT");

			// Si el hilo está corriendo
			if (notificacionCiatCasaCiat != null) {
				notificacionCiatCasaCiat.killTarea();

				Calendar calendar = Calendar.getInstance();

				calendar.set(Calendar.HOUR_OF_DAY, Integer
						.parseInt(horaEjecucionTareaCobroCiatCasaCiat));
				calendar.set(Calendar.MINUTE, Integer
						.parseInt(minutosEjecucionTareaCobroCiatCasaCiat));

				Date time = calendar.getTime();

				notificacionCiatCasaCiat = new NotificacionCobroCiatCasaCiat(
						time);
			} else // Si no hay ninguna tarea corriendo
			{
				Calendar calendar = Calendar.getInstance();

				calendar.set(Calendar.HOUR_OF_DAY, Integer
						.parseInt(horaEjecucionTareaCobroCiatCasaCiat));
				calendar.set(Calendar.MINUTE, Integer
						.parseInt(minutosEjecucionTareaCobroCiatCasaCiat));

				Date time = calendar.getTime();

				notificacionCiatCasaCiat = new NotificacionCobroCiatCasaCiat(
						time);
			}
		} catch (NumberFormatException e) {
			log.error("inicializarTarea: " + e.getMessage());
		} catch (Exception e) {
			log.error("inicializarTarea: " + e.getMessage());
		}
	}

	/**
	 * Metodo para inicializar la Tarea automatica de notificación de Siscar Vs
	 * Activos fijos.
	 *
	 * @author Diego Humberto Bocanegra
	 */
	public static void inicializarTareaSiscarVsActivosFijos() {
		try {

			String horaEjecucionTareaSicarVsActivoFijo = Util
					.loadParametersValue("HORA_EJECUCION_TAREA_SISCAR_VS_ACTIVO_FIJO");
			String minutosEjecucionTareaSiscarVsActivoFijo = Util
					.loadParametersValue("MINUTOS_EJECUCION_SISCAR_VS_ACTIVO_FIJO");

			// Si el hilo está corriendo
			if (notificacionSiscarVsActivoFijo != null) {
				notificacionSiscarVsActivoFijo.killTarea();

				Calendar calendar = Calendar.getInstance();

				calendar.set(Calendar.HOUR_OF_DAY, Integer
						.parseInt(horaEjecucionTareaSicarVsActivoFijo));
				calendar.set(Calendar.MINUTE, Integer
						.parseInt(minutosEjecucionTareaSiscarVsActivoFijo));

				Date time = calendar.getTime();

				notificacionSiscarVsActivoFijo = new NotificacionSiscarVsActivoFijo(
						time);
			} else // Si no hay ninguna tarea corriendo
			{
				Calendar calendar = Calendar.getInstance();

				calendar.set(Calendar.HOUR_OF_DAY, Integer
						.parseInt(horaEjecucionTareaSicarVsActivoFijo));
				calendar.set(Calendar.MINUTE, Integer
						.parseInt(minutosEjecucionTareaSiscarVsActivoFijo));

				Date time = calendar.getTime();

				notificacionSiscarVsActivoFijo = new NotificacionSiscarVsActivoFijo(
						time);
			}
		} catch (NumberFormatException e) {
			log.error("inicializarTarea: " + e.getMessage());
		} catch (Exception e) {
			log.error("inicializarTarea: " + e.getMessage());
		}
	}

}