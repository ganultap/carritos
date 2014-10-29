package geniar.siscar.inicialization;

import geniar.siscar.persistence.EntityManagerHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;
import org.hibernate.ejb.HibernateEntityManager;

@SuppressWarnings("serial")
public class InitSessionHibernate extends HttpServlet {

	private static Logger log = Logger.getLogger(InitSessionHibernate.class);
	
	public void init() throws ServletException {
		
		log.debug("iniciando sesion de hibernate (??)");
		HibernateEntityManager entityManager = (HibernateEntityManager) EntityManagerHelper
				.getEntityManager();
		entityManager.getSession();

	}

}
