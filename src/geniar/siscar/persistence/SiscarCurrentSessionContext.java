package geniar.siscar.persistence;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.classic.Session;
import org.hibernate.context.CurrentSessionContext;
import org.hibernate.ejb.EntityManagerImpl;

public class SiscarCurrentSessionContext implements CurrentSessionContext {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(SiscarCurrentSessionContext.class);
	
	public Session currentSession() throws HibernateException {

		try {
			
			log.debug("setting current session");
			
			// establece en cualquier momento cual es la sesion actual			
			EntityManagerImpl em = (EntityManagerImpl) EntityManagerHelper.getEntityManager();			
			return (Session) em.getSession();
			
		} catch (RuntimeException e) {
			
			log.debug("error setting current session", e);
			return null;
		}
	}

}
