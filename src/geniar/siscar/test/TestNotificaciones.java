package geniar.siscar.test;


import geniar.siscar.taskmanager.NotificationTaskAssignationEnd;
import geniar.siscar.taskmanager.NotificationTaskRentEnd;
import geniar.siscar.util.Util;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * 
 * @author - GeniarArq S.A
 * @version 1.0 Descripción : Clase encargada del manejo de las pruebas de las notificaciones
 */
public class TestNotificaciones {
	
	private static NotificationTaskAssignationEnd tareAssignationEnd;
	private static NotificationTaskRentEnd tareaRentEnd;
	private static final Log log = LogFactory.getLog(TestNotificaciones.class);

	/**
	 * Metodo para inicializar la Tarea automaticas
	 * 
	 * @author Jam - Geniar Architect S.A.
	 */
	public static void inicializarTarea() {
		try {
			String horaEjecucionTarea = Util.loadParametersValue("HORA_EJECUCION_TAREA");
			String minutosEjecucionTarea = Util.loadParametersValue("MINUTOS_EJECUCION_TAREA");

			// Si el hilo está corriendo
			if (tareAssignationEnd != null) {
				tareAssignationEnd.killTarea();

				Calendar calendar = Calendar.getInstance();

				calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(horaEjecucionTarea));
				calendar.set(Calendar.MINUTE, Integer.parseInt(minutosEjecucionTarea));

				Date time = calendar.getTime();

				tareAssignationEnd = new NotificationTaskAssignationEnd(time);
			} else // Si no hay ninguna tarea corriendo
			{
				Calendar calendar = Calendar.getInstance();

				calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(horaEjecucionTarea));
				calendar.set(Calendar.MINUTE, Integer.parseInt(minutosEjecucionTarea));

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
	 * Metodo para inicializar la Tarea automatica de notificacion de terminacion alquiler 
	 * 
	 * @author Geniar Architect S.A.
	 */
	public static void inicializarTarea_II() {
		try {

			String horaEjecucionTarea_II = Util.loadParametersValue("HORA_EJECUCION_TAREA_II");
			String minutosEjecucionTarea_II = Util.loadParametersValue("MINUTOS_EJECUCION_TAREA_II");

			// Si el hilo está corriendo
			if (tareaRentEnd != null) {
				tareaRentEnd.killTarea();

				Calendar calendar = Calendar.getInstance();

				calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(horaEjecucionTarea_II));
				calendar.set(Calendar.MINUTE, Integer.parseInt(minutosEjecucionTarea_II));

				Date time = calendar.getTime();

				tareaRentEnd = new NotificationTaskRentEnd(time);
			} else // Si no hay ninguna tarea corriendo
			{
				Calendar calendar = Calendar.getInstance();

				calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(horaEjecucionTarea_II));
				calendar.set(Calendar.MINUTE, Integer.parseInt(minutosEjecucionTarea_II));

				Date time = calendar.getTime();

				tareaRentEnd = new NotificationTaskRentEnd(time);
			}
		} catch (NumberFormatException e) {
			log.error("inicializarTarea: " + e.getMessage());
		} catch (Exception e) {
			log.error("inicializarTarea: " + e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		inicializarTarea();
		//inicializarTarea_II();
	}

}
