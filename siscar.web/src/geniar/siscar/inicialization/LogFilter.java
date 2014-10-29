package geniar.siscar.inicialization;

import geniar.hibernate.log.util.LogUserInfo;
import geniar.siscar.view.autenticate.LoginPage;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

public class LogFilter implements Filter {

	private static Logger log = Logger.getLogger(LogFilter.class);

	private FilterConfig _filterConfig = null;

	public void init(FilterConfig filterConfig) throws ServletException {
		log.debug("Iniciando filtro de Login");
		_filterConfig = filterConfig;
	}

	public void destroy() {
		log.debug("Cerrando filtro de Login");
		_filterConfig = null;
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		log.debug("Ejecutando filtro de Login");
		try {

			// Get the existing session.
			HttpServletRequest httpRequest = (HttpServletRequest) request;
			HttpSession session = httpRequest.getSession(true);
			LoginPage loginPage = (LoginPage) session.getAttribute("loginPage");
			httpRequest.getServletPath();

			// with session, but'not logged
			// ends with faces, but is not /login.faces
			if ((loginPage == null || loginPage.getLogin() == null || loginPage
					.getLogin().trim().length() == 0)
					&& (httpRequest.getRequestURI().endsWith(".iface") || httpRequest
							.getRequestURI().endsWith(".jspx"))
					&& !httpRequest.getRequestURI().endsWith("/login.iface")
					&& !httpRequest.getRequestURI().endsWith(
							"/Contactenos.jspx")
					&& !httpRequest.getRequestURI().endsWith(
							"/PersonalMileage.jspx")) {

				log
						.debug("No ha iniciado sesion, redirigiendo a pagina de login");

				// redirect to login.faces
				HttpServletResponse httpResponse = (HttpServletResponse) response;
				httpResponse.sendRedirect(getBasePath(request) + "index.jsp");

			} else {

				log.debug("Ha iniciado sesion, ejecutando la pagina");

				// run the page
				chain.doFilter(httpRequest, response);
				if (loginPage != null && loginPage.getLogin() != null
						&& loginPage.getLogin().length() > 0)
					LogUserInfo.setCurrentUser(loginPage.getLogin());

			}

		} catch (Exception e) {
			log.error("Error filtro de sesion : " + e.getMessage(), e);
		}

	}

	private String getBasePath(ServletRequest request) {

		// establece el URL base del XHTML
		String basePath = "";

		if (request instanceof HttpServletRequest) {

			// logger.debug("calculating basePath from request");
			HttpServletRequest httpRequest = (HttpServletRequest) request;

			String path = httpRequest.getContextPath();
			basePath = request.getScheme() + "://" + request.getServerName()
					+ ":" + request.getServerPort() + path + "/";
		}
		return basePath;
	}
}
