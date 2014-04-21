package geniar.siscar.taskmanager;

//import geniar.siscar.logic.notification.services.impl.NotificationsServiceImpl;
import geniar.siscar.logic.billing.services.impl.BillingAccountServiceImpl;
import geniar.siscar.util.ManipulacionFechas;
import geniar.siscar.util.Util;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Clase en donde se programa la tarea automatica para ser ejecutada de cobro
 * ciat casa ciat
 * 
 * @FechaCreacion (dd-mm-aaaa) 11-06-2007
 */
public class NotificacionCobroCiatCasaCiat extends TimerTask {

	private Timer timer = null;
	private String repeticionHorasStr;
	private String factorConversionHorasMilisegs;
	private static final Log log = LogFactory
			.getLog(NotificacionCobroCiatCasaCiat.class);

	/**
	 * Constructor de la Tarea
	 * 
	 * @author Diego Humberto Bocanegra
	 * @FechaCreacion (dd-mm-aaaa) 07-02-2009
	 * @param time
	 */
	public NotificacionCobroCiatCasaCiat(Date time) {
		try {

			repeticionHorasStr = Util
					.loadParametersValue("REPETICION_TAREA_AUTOMATICA_COBRO_CIAT_CASA_CIAT_HORAS");

			factorConversionHorasMilisegs = Util
					.loadParametersValue("FACTOR_CONVERSION_HORAS_MILISEGS");

			long repeticionHoras = Long.parseLong(repeticionHorasStr);

			long factorConversion = Long
					.parseLong(factorConversionHorasMilisegs);

			long repeticionMilisegundos = repeticionHoras * factorConversion;

			setTimer(new Timer());

			getTimer().scheduleAtFixedRate(new NotificacionCobroCiatCasaCiat(),
					time, repeticionMilisegundos);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	// Constructor sin parametros
	public NotificacionCobroCiatCasaCiat() {

	}

	/**
	 * Metodo para terminar el hilo de la tarea
	 * 
	 * @author Diego Humberto Bocanegra
	 * @FechaCreacion (dd-mm-aaaa) 07-02-2009
	 * @return void
	 */
	public void killTarea() {
		super.cancel();
	}

	/**
	 * Metodo abstracto implementado de la super clase TimerTask para la
	 * ejecucion de la Tarea
	 * 
	 * @author Geniar S.A
	 * @FechaCreacion (dd-mm-aaaa) 11-06-2007
	 * @return void
	 */
	public void run() {
		Date fechaActual = new Date();
		try {
			// Actual
			int horaActual = Integer.parseInt(ManipulacionFechas
					.getHora(fechaActual));
			int minutosActual = Integer.parseInt(ManipulacionFechas
					.getMinutos(fechaActual));

			int horaInicialRangoEjecucion = Integer
					.parseInt(Util
							.loadParametersValue("HORA_INICIAL_RANGO_EJECUCION_COBRO_CIAT_CASA_CIAT"));
			int minutosInicialRangoEjecucion = Integer
					.parseInt(Util
							.loadParametersValue("MINUTOS_INICIAL_RANGO_EJECUCION_COBRO_CIAT_CASA_CIAT"));

			int horaFinalRangoEjecucion = Integer
					.parseInt(Util
							.loadParametersValue("HORA_FINAL_RANGO_EJECUCION_COBRO_CIAT_CASA_CIAT"));
			int minutosFinalRangoEjecucion = Integer
					.parseInt(Util
							.loadParametersValue("MINUTOS_FINAL_RANGO_EJECUCION_COBRO_CIAT_CASA_CIAT"));

			// Tiempo Actual en Minutos
			int tiempoActual = (horaActual * 60) + minutosActual;

			// Tiempos de Rango de Ejecucion en Minutos
			int rangoInicial = (horaInicialRangoEjecucion * 60)
					+ minutosInicialRangoEjecucion;
			int rangoFinal = (horaFinalRangoEjecucion * 60)
					+ minutosFinalRangoEjecucion;

			// Pregunta si la hora actual esta dentro del rango de ejecucion de
			// la tarea
			if ((tiempoActual >= rangoInicial) && (tiempoActual <= rangoFinal)) {
				Date start = new Date();
				log
						.info("--- Corriendo proceso automatico Cobro de Ciat Casa Ciat   : "
								+ start + "----");
				new BillingAccountServiceImpl().CobroCiatCasaCiat();

				Date end = new Date();
				log
						.info("--- Termina proceso Envio de notificaciones automaticas Cobro de Ciat Casa Ciat "
								+ start + "----");
				log
						.info(end.getTime()
								- start.getTime()
								+ " total milliseconds en realizar tarea automatica Cobro de Ciat Casa Ciat : ");

			}// Fin if
			else {
				log
						.info("--- Tarea Automatica de notificacion Cobro de Ciat Casa Ciat en espera... "
								+ new Date() + " ----");
			}
		} catch (Exception e) {
			log
					.error("Error RUN Tarea Automatica de notificacion Cobro de Ciat Casa Ciat: "
							+ e.getMessage());
		}

		fechaActual = null;

	}// fin del metodo run()

	// *********************************************** Metodos de Acceso
	// *********************************************/

	public Timer getTimer() {
		return timer;
	}

	public void setTimer(Timer timer) {
		this.timer = timer;
	}

	public String getRepeticionHorasStr() {
		return repeticionHorasStr;
	}

	public void setRepeticionHorasStr(String repeticionHorasStr) {
		this.repeticionHorasStr = repeticionHorasStr;
	}

	public String getFactorConversionHorasMilisegs() {
		return factorConversionHorasMilisegs;
	}

	public void setFactorConversionHorasMilisegs(
			String factorConversionHorasMilisegs) {
		this.factorConversionHorasMilisegs = factorConversionHorasMilisegs;
	}

}