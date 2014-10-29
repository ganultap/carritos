package geniar.hibernate.log.listener;

import geniar.hibernate.log.listener.classic.DatabaseActionLogger;
import geniar.hibernate.log.listener.jpa.DatabaseJpaActionLogger;

import java.util.Properties;
import java.io.IOException;
import java.io.InputStream;

import org.apache.log4j.Logger;

public class ActionLoggerFactory {

	private static final String configFileName = "/META-INF/geniar.log.properties";
	private static Logger logger = Logger.getLogger(ActionLogger.class); 
	
	public static ActionLogger getActionLogger() {
	
		Properties properties = null;
		
		try {
			logger.debug("Cargando archivo de configuración de Log de Auditoria");
			InputStream configFile = ActionLoggerFactory.class.getResourceAsStream(configFileName); //Thread.currentThread().getClass().getResourceAsStream(configFileName);
			
			if (configFile != null) {
				logger.debug("procesando archivo de configuración");
				properties = new Properties();
				properties.load(configFile);
				
				logger.debug("revisando tipo de logger");
				String loggerType = properties.getProperty("logger.type");
				if (loggerType == null) {
					logger.debug("no se ha definido tipo. Cargando log clásico por defecto");
					return createDefaultActionLogger(properties);
				} else if (loggerType.trim().toLowerCase().equals("classic")) {
					logger.debug("Cargando log clásico");
					return createClassicActionLogger(properties);					
				} else if (loggerType.trim().toLowerCase().equals("jpa")) {
					logger.debug("no se ha definido tipo. Cargando log clásico por defecto");
					return createJpaActionLogger(properties);
				}
				
			} else {
				logger.debug("No existe archivo de configuración. Cargando log clásico por defecto");
				return createDefaultActionLogger(properties);
			}
			
		} catch (IOException e) {
			logger.error("error cargando configuración de log " + e.getMessage());
			e.printStackTrace();
		}
		
		return null;
	}
	
	private static ActionLogger createJpaActionLogger(Properties properties) {
		if (properties != null) {
			String persistenceUnit = properties.getProperty("persistence.unit");
			return new DatabaseJpaActionLogger(persistenceUnit);
		} else {
			return null;
		}
	}
	
	private static ActionLogger createClassicActionLogger(Properties properties) {
		if (properties == null) {
			logger.debug("No se tiene archivo de propiedades. Usando valores por defecto");			
			return new DatabaseActionLogger();
		}
		
		String configFile = properties.getProperty("config.file");
		if (configFile == null) {
			logger.debug("No se tiene definida la configuración de hibernate. Usando valor por defecto (hibernate.cfg.xml)");						
			return new DatabaseActionLogger();
		} else { 
			logger.debug("Usando la configuración de hibernate " + configFile); 						
			return new DatabaseActionLogger(configFile);
		}
	}
	
	private static ActionLogger createDefaultActionLogger(Properties properties) {
		return createClassicActionLogger(properties);
	}
		
}
