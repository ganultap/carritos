package geniar.siscar.logic.logging;

import geniar.siscar.logic.policies.services.impl.PoliciesTypesServiceImpl;
import geniar.siscar.model.Policies;
import geniar.siscar.model.PoliciesTypes;
import geniar.siscar.persistence.EntityManagerHelper;
import geniar.siscar.persistence.PoliciesDAO;
import gwork.exception.GWorkException;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Types;
import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.log4j.jdbcplus.JDBCAppender;
import org.apache.log4j.jdbcplus.JDBCLogType;
import org.apache.log4j.xml.DOMConfigurator;

public class LogManager extends JDBCAppender {

	public static Logger logger;

	/**Se inicializa toda la configuración*/
	static {
		URL url = null;
		try {
			//Con este URL se indica la ubicación del 
			//xml donde están configurados los 
			//Appenders para log
			url = new URL("http://localhost:8081/siscar.web/logApp/UtilLog4j.xml");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		DOMConfigurator.configure(url);
		logger = Logger.getLogger(LogManager.class);
	}

	/**
	 * Genera el log de auditoria en la base de datos.
	 * @param tipoTransaccion Tipo de transacción realizada <br>
	 * 			(Guardar, Modificar, Eliminar)<br>
	 * @param PK Llave primaría del registro afectado.
	 * @param nombreTabla Nombre de la tabla afectada. 
	 */
	public static void generarAuditoria(String tipoTransaccion, Long PK, String nombreTabla) {
		if (true) {
			return;
		}
//		try {
//		//ControllerAuthentication.getInstance().validateUser("siscartest", "siscar2008");
//		} catch (GWorkException e) {
//		e.printStackTrace();
//		}

		JDBCAppender appender = (JDBCAppender)logger.getAppender("JDBCAppender");


		String nombreUsuario = "Mauricio Cuenca N";
		//ControllerAuthentication.getInstance().getUsuario().getFirstName();
		String apellidoUsuario = "Cuenca";
		//ControllerAuthentication.getInstance().getUsuario().getLastName();

		try {
			
			appender.setColumn("LOG_ID",				JDBCLogType.ORACLE_SEQUENCE, 	"SQ_LOG.NEXTVAL", 	"NUMBER", 	Types.NUMERIC);			
			appender.setColumn("LOG_LOGIN", 			JDBCLogType.MSG,			 	nombreUsuario,		"VARCHAR",	Types.VARCHAR);			
			appender.setColumn("LOG_DATE", 				JDBCLogType.MSG, 				new Date(), 		"DATE", 	Types.DATE);			
			appender.setColumn("LOG_TIPO_TRANSACCION", 	JDBCLogType.MSG, 				tipoTransaccion,	"VARCHAR", 	Types.VARCHAR);			
			appender.setColumn("LOG_NOMBRE_TABLA", 		JDBCLogType.MSG, 				nombreTabla,		"VARCHAR", 	Types.VARCHAR);			
			appender.setColumn("LOG_LLAVE_TABLA", 		JDBCLogType.ID, 				PK, 				"NUMBER", 	Types.NUMERIC);			

			logger.info("info");
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		try {
			Policies policies = new Policies();

			PoliciesTypes policiesTypes;
			policiesTypes = new PoliciesTypesServiceImpl().consultarPoliciesTypesById(1L);

			policies.setPoliciesTypes(policiesTypes);
			policies.setPlsFechainicioCobertura(new Date());
			policies.setPlsFechafinCobertura(new Date());
			policies.setPlsNumero(145676767631L);


			EntityManagerHelper.beginTransaction();
			Policies s = new PoliciesDAO().update(policies);
			EntityManagerHelper.commit();

			LogManager.generarAuditoria("Guardar", s.getPlsCodigo(),"Policies"+Policies.class);

		} catch (GWorkException e) {
			e.printStackTrace();
		}
	}
}
