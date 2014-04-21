package geniar.hibernate.log.listener.classic;

import java.sql.Timestamp;
import java.util.Date;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import geniar.hibernate.log.data.dao.ActionLogDAO;
import geniar.hibernate.log.data.hibernate.LogSessionFactory;
import geniar.hibernate.log.data.model.ActionLog;
import geniar.hibernate.log.listener.ActionLogger;

public class DatabaseActionLogger implements ActionLogger {

	private static Logger logger = Logger.getLogger(DatabaseActionLogger.class); 

	private Configuration configuration;
	
	public DatabaseActionLogger() {
		logger.debug("usando la misma configuración del log (No es recomendable)");
		this.configuration = LogSessionFactory.getConfiguration();
	}
	
	public DatabaseActionLogger(Configuration configuration) {
		logger.debug("usando una configuración definida por el usuario " + configuration);
		this.configuration = configuration;
	}

	public DatabaseActionLogger(String configFile) {
		logger.debug("usando el archivo de configuración " + configFile);
		try {
			this.configuration = new Configuration();
			this.configuration.configure(configFile);
			this.configuration.buildSessionFactory();
		} catch (Exception e) {
			logger.error("No se pudo cargar configuración de " + configFile  + ", usando la configiuración del log (No es recomendable)");
			this.configuration = LogSessionFactory.getConfiguration();
		}
	}
		
	
	public void logThis(String command, Object entity, String[] properties,
			Long userId) {

		// la entidad ActionLog no se debe guardar !!
		if (!(entity instanceof ActionLog)) {

			try {
				
				logger.debug("Grabando Log de Transacción " + entity);
				
				// new actionlog
				ActionLog actionLog = new ActionLog();

				// parse object, get all the methods
				logger.debug("analizando objetos de la transacción");
				ParseHibEntity phe = new ParseHibEntity(entity, properties, configuration);

				// set command and table name
				logger.debug("definiendo valores del objeto de log");
				actionLog.setCommand(command);
				actionLog.setTableName(phe.extractTableName());

				// primary key column
				actionLog.setPrimaryKey(phe.extractPrimaryKeyColumn());

				// save all contact values
				actionLog.setConcatValues(phe.extractColumnValues());

				// set time stemp
				actionLog.setTimeStamp(new Timestamp(new Date().getTime()));
				actionLog.setUserId("" + userId);

				// save the actionlog
				logger.debug("almacenando el objeto de log");
				ActionLogDAO dao = new ActionLogDAO();
				Transaction tx = dao.getSession().beginTransaction();
				dao.save(actionLog);
				tx.commit();
				
			} catch (HibernateException e) {			
				logger.debug("No se pudo guardar log de transacción " + entity, e);
			}
		}

	}
}

