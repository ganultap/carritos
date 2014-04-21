package geniar.hibernate.log.listener.jpa;

import geniar.hibernate.log.data.dao.ActionLogDAO;
import geniar.hibernate.log.data.model.ActionLog;
import geniar.hibernate.log.listener.ActionLogger;
import geniar.hibernate.log.util.LogUserInfo;

import java.sql.Timestamp;
import java.util.Date;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import org.hibernate.ejb.Ejb3Configuration;

public class DatabaseJpaActionLogger implements ActionLogger {

	private static Logger logger = Logger.getLogger(DatabaseJpaActionLogger.class); 

	private Ejb3Configuration configuration;
	
	public DatabaseJpaActionLogger(String persistenceUnit) {
		logger.debug("usando la unidad de persistencia " + persistenceUnit);
		try {
			this.configuration = new Ejb3Configuration();
			this.configuration.configure(persistenceUnit, null);
		} catch (Exception e) {
			logger.error("No se pudo cargar configuración de unidad de persistencia " + persistenceUnit, e);
			this.configuration = null;
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
				ParseJpaEntity phe = new ParseJpaEntity(entity, properties, configuration);

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
				actionLog.setUserId("" + LogUserInfo.getCurrentUser());

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

