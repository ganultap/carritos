package geniar.siscar.view.autenticate;

//import geniar.hibernate.log.util.LogUserInfo;

import geniar.hibernate.log.util.LogUserInfo;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class UserInfoFilter implements Filter {

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		if (request instanceof HttpServletRequest) {
			HttpServletRequest req = (HttpServletRequest) request;

			HttpSession session = req.getSession();

			if (session != null) {
				LoginPage page = (LoginPage) session.getAttribute("loginPage");
				if (page != null) {
					// System.out.println("==> usuario " + page.getLogin());
					LogUserInfo.setCurrentUser(page.getLogin());
				}
			}
		}

		// ejecuta la pagina
		chain.doFilter(request, response);

	}

	public void init(FilterConfig filterConfig) throws ServletException {

	}

}
