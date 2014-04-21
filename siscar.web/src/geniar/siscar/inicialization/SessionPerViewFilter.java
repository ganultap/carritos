package geniar.siscar.inicialization;

import geniar.siscar.persistence.EntityManagerHelper;
import java.io.IOException;
import javax.persistence.EntityManager;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.hibernate.classic.Session;
import org.hibernate.context.ManagedSessionContext;
import org.hibernate.ejb.EntityManagerImpl;

public class SessionPerViewFilter implements Filter {

	private static Logger log = Logger.getLogger(SessionPerViewFilter.class);

	private FilterConfig _filterConfig = null;

	public void init(FilterConfig filterConfig) throws ServletException {
		log.debug("Iniciando filtro de Sesion de datos para la interfaz");
		_filterConfig = filterConfig;
	}

	public void destroy() {
		log.debug("Cerrando filtro de Sesion de datos para la interfaz");
		_filterConfig = null;
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		log.debug("Ejecutando filtro de Sesion de datos para la interfaz");
		try {

			log.debug("cargando sesion de datos para la pantalla");

			HttpSession webSession = ((HttpServletRequest) request)
					.getSession(true);

			EntityManager em;

			// is no there a data session ??
			if (webSession.getAttribute("_entity_manager") == null) {

				// Set a data session in the thread
				// to be used for lazy objects
				em = EntityManagerHelper.getEntityManager();
				log.debug("entity manager : " + em + " "
						+ (em.isOpen() ? "(open)" : "(closed)"));
				webSession.setAttribute("_entity_manager", em);

				// there is a session
			} else {

				// get the data session from the web session
				em = (EntityManager) webSession.getAttribute("_entity_manager");
				log.debug("entity manager : " + em + " "
						+ (em.isOpen() ? "(open)" : "(closed)"));

				if (!em.isOpen()) {
					log.debug("getting a new entity manager");
					em = EntityManagerHelper.getEntityManager();
					log.debug("entity manager : " + em + " "
							+ (em.isOpen() ? "(open)" : "(closed)"));
					webSession.setAttribute("_entity_manager", em);
				}
			}

			// set other data session objects
			try {
				log.debug("getting current hibernate session");
				Session session = (Session) ((EntityManagerImpl) em)
						.getSession();

				log.debug("sesion : " + session + " "
						+ (session.isOpen() ? "(open)" : "(closed)"));

				log.debug("binding current session");
				ManagedSessionContext.bind(session);

			} catch (Exception e) {
				log.debug("error binding current session", e);
			}

			// run the page
			chain.doFilter(request, response);

		} catch (Exception e) {
			log.error("Error filtro Sesion de datos para la interfaz: "
					+ e.getMessage(), e);
		}

	}

}
