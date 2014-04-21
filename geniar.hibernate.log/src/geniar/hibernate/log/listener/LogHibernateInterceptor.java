package geniar.hibernate.log.listener;

import java.io.Serializable;
import org.hibernate.CallbackException;
import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

public class LogHibernateInterceptor extends EmptyInterceptor {

	private static final long serialVersionUID = -3073850856829239762L;

	private ActionLogger theLogger;
		
	/**
	* Constructor
	*/
	public LogHibernateInterceptor() {
		theLogger = ActionLoggerFactory.getActionLogger();
	}

	
	public LogHibernateInterceptor(ActionLogger actionLogger) {
		theLogger = actionLogger;
	}
	
	/**
	 * al momento de cargar un dato 
	 * (no hace nada)
	 */
	public boolean onLoad (Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) throws CallbackException {
		return false;
	}

	/**
	 * al momento de actualizar un objeto
	 */
	public boolean onFlushDirty(Object entity, Serializable id, Object[] newValues, Object[] oldValues, String[] propertyNames, Type[] types) throws CallbackException {
		theLogger.logThis ("Update",entity,propertyNames,getUserId());
		return false;
	}

	private Long getUserId() {
		return -1L;
	}


	public boolean onSave (Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) throws CallbackException {
		theLogger.logThis ("Create",entity,propertyNames,-getUserId());
		return false;
	}


	public void onDelete (Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) throws CallbackException {
		theLogger.logThis ("Delete",entity,propertyNames,getUserId());
	}
	
}
