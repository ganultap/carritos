package geniar.siscar.view.autenticate;

import geniar.siscar.logic.security.serivice.UserService;
import geniar.siscar.model.Users;
import geniar.siscar.util.ControllerAuthentication;
import geniar.siscar.util.FacesUtils;
import geniar.siscar.util.NavigationResults;
import geniar.siscar.util.Util;
import geniar.siscar.view.BaseBean;
import geniar.siscar.view.newness.NewnessLegateeChangePage;
import geniar.siscar.view.security.MenuBarList;
import geniar.siscar.view.security.UserPage;
import gwork.exception.GWorkException;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 * The Class LoginPage.
 */
public class LoginPage extends BaseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** The login. */
	private String login = "";
	
	/** The password. */
	private String password = "";
	
	/** The user service. */
	private UserService userService;
	
	/** The nombre. */
	private String nombre;
	
	/** The carne. */
	private String carne;

	/**
	 * Gets the carne.
	 *
	 * @return the carne
	 */
	public String getCarne() {
		return carne;
	}

	/**
	 * Sets the carne.
	 *
	 * @param carne the new carne
	 */
	public void setCarne(String carne) {
		this.carne = carne;
	}

	/**
	 * Gets the login.
	 *
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * Sets the login.
	 *
	 * @param login the new login
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Action_login.
	 *
	 * @return the string
	 * @throws GWorkException the g work exception
	 */
	public String action_login() throws GWorkException {

		try {
			boolean enLDAP = false;

			if (login.trim().length() == 0) {
				throw new GWorkException(Util
						.loadErrorMessageValue("ERROR.LOGINEMPTY"));
			}
			
			if (login.trim().length() < 3)
				throw new GWorkException(Util
						.loadErrorMessageValue("LOGIN.MINIMO.MAXIMO"));

			if (login.trim().length() > 18)
				throw new GWorkException(Util
						.loadErrorMessageValue("LOGIN.MINIMO.MAXIMO"));

			if (password.trim().length() == 0)
				throw new GWorkException(Util
						.loadErrorMessageValue("ERROR.PASSWEMPTY"));

			if (!Util.validarCadenaCaracteresEspecialesNumLetrasGuion(password))
				throw new GWorkException(Util
						.loadErrorMessageValue("ERROR.PASSWCARACESP"));

			if (password.trim().length() < 6)
				throw new GWorkException(Util
						.loadErrorMessageValue("PASSWORD.MINIMO.MAXIMO"));

			if (password.trim().length() > 18)
				throw new GWorkException(Util
						.loadErrorMessageValue("ERROR.LIMPASSWORD"));

/*			enLDAP = ControllerAuthentication.getInstance().validateUser(login,
					password);

			 if (!enLDAP)
				 throw new GWorkException(Util.loadErrorMessageValue("ERROR.CREDENOUSUARIO"));
*/
			LoginPage loginPage = (LoginPage) FacesUtils
					.getManagedBean("loginPage");

			MenuBarList menuBarList = (MenuBarList) FacesUtils
					.getManagedBean("menuBarList");
			Users user = null;
			String loginTemp = "";
			loginTemp = login.trim().toUpperCase();
			user = userService.consultarUsuarioPorLogin(loginTemp);

			if (user.getUsrApellido() != null)
				setNombre(user.getUsrNombre() + " " + user.getUsrApellido());
			else
				setNombre(user.getUsrNombre());

			setCarne(user.getUsrIdentificacion());

			if (user.getRolls().getOptionsRollses().size() == 0)
				throw new GWorkException(Util
						.loadErrorMessageValue("USER.NOOPCIONES"));

			if (userService.consultaUsuarioPorLogin(loginTemp)) {
				menuBarList.cargarListaOpciones(loginTemp);
				loginPage.setLogin(loginTemp);
				FacesUtils.getSession().setAttribute("loginPage",
						(LoginPage) loginPage);

				return NavigationResults.HOME;
			} else {
				FacesUtils.resetManagedBean("loginPage");
				throw new GWorkException(Util
						.loadErrorMessageValue("ERROR.CREDENOUSUARIO"));
			}

		} catch (GWorkException e) {
			// FacesUtils.resetManagedBean("loginPage");
			FacesUtils.addErrorMessage(e.getMessage());
		}
		return "";
	}

	/**
	 * Cerrar session.
	 *
	 * @return the string
	 * @throws GWorkException the g work exception
	 */
	public String cerrarSession() throws GWorkException {
		setNombre("");
		setCarne("");
		FacesUtils.getSession().removeAttribute("loginPage");
		FacesUtils.getSession().removeAttribute("idVehiculo");
		FacesUtils.getSession().removeAttribute("idAsignacion");
		FacesUtils.getSession().removeAttribute("idSolicitud");
		FacesUtils.getSession().removeAttribute("porcentaje");
		FacesUtils.getSession().removeAttribute("idAsignacion");
		FacesUtils.getSession().removeAttribute("idVehiculo");
		FacesUtils.getSession().removeAttribute("idAsignacionLocation");
		FacesUtils.getSession().removeAttribute("idVehiculoLocation");
		FacesUtils.getSession().removeAttribute("fechaInicial");
		FacesUtils.getSession().removeAttribute("idAsignacionCostCenter");
		FacesUtils.getSession().removeAttribute("idVehiculoCostCenter");
		FacesUtils.getSession().removeAttribute("fechaInicial");
		FacesUtils.getSession().removeAttribute("fechaFinalAsignacion");
		FacesUtils.getSession().removeAttribute("fechaFinalAsig");
		FacesUtils.resetManagedBean("loginPage");
		NewnessLegateeChangePage costCenterPage = (NewnessLegateeChangePage) FacesUtils
				.getManagedBean("newnessLegateeChangePage");
		if (costCenterPage != null)
			FacesUtils.resetManagedBean("newnessLegateeChangePage");

		UserPage userPage = (UserPage) FacesUtils.getManagedBean("userPage");

		if (userPage != null)
			FacesUtils.resetManagedBean("userPage");

		MenuBarList menuBarList = (MenuBarList) FacesUtils
				.getManagedBean("menuBarList");

		if (menuBarList != null)
			FacesUtils.resetManagedBean("menuBarList");

		return NavigationResults.INICIO;
	}

	/**
	 * Request.
	 *
	 * @return the http servlet request
	 */
	public static HttpServletRequest request() {
		ExternalContext externalContext = FacesContext.getCurrentInstance()
				.getExternalContext();
		return (HttpServletRequest) externalContext.getRequest();
	}

	/**
	 * Gets the user service.
	 *
	 * @return the user service
	 */
	public UserService getUserService() {
		return userService;
	}

	/**
	 * Sets the user service.
	 *
	 * @param userService the new user service
	 */
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * Gets the nombre.
	 *
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Sets the nombre.
	 *
	 * @param nombre the new nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
