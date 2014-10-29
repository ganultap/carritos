package geniar.siscar.view.security;

import geniar.siscar.logic.security.serivice.UserService;
import geniar.siscar.logic.security.serivice.impl.UserServiceImpl;
import geniar.siscar.model.Users;
import geniar.siscar.util.Util;
import geniar.siscar.view.BaseBean;
import gwork.exception.GWorkException;

import java.util.ArrayList;
import java.util.List;

import com.icesoft.faces.component.ext.HtmlCommandButton;
import com.icesoft.faces.component.ext.HtmlInputText;
import com.icesoft.faces.component.ext.HtmlSelectOneMenu;

/**
 * 
 * @author JAM, - Geniar Arq S.A
 * @version 1.0
 * @Descripción : BackingBean para el manej2o de los eventos del page de Usuario
 * 
 */
public class UserBackingBean extends BaseBean {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private UserService userService;
	private HtmlCommandButton selectAllBtn;
	private HtmlCommandButton btnCrear;
	private HtmlCommandButton btnModificar;
	private HtmlSelectOneMenu selectRoles;
	private HtmlSelectOneMenu selectEstados;
	private HtmlInputText TxtCarnet;

	/**
	 * Metodo encargado de guardar un Usuario
	 * 
	 * @param rlsCodigo
	 * @param usrIdentificacion
	 * @param usrNombre
	 * @param usrApellido
	 * @param usrTelefono
	 * @param usrDireccion
	 * @param usrEmail
	 * @param usrLogin
	 * @throws GWorkException
	 */
	public void guardarUser(Long rlsCodigo, String usrIdentificacion, String usrNombre, String usrApellido,
			String usrTelefono, String usrDireccion, String usrEmail, String usrLogin) throws GWorkException {

		String codigoTemp = Util.loadMessageValue("ESTADO.ACTIVO");

		validarCampos(rlsCodigo, codigoTemp, usrIdentificacion, usrNombre, usrApellido, usrTelefono, usrDireccion,
				usrEmail, usrLogin);
		userService = new UserServiceImpl();
		userService.createUser(rlsCodigo, codigoTemp, usrIdentificacion, usrNombre, usrApellido, usrTelefono,
				usrDireccion, usrEmail, usrLogin);
	}

	public void validarCamposConsultaLogin(String usrLogin) throws GWorkException {

		boolean isValid = true;

		if (usrLogin != null && usrLogin.trim().length() != 0)
			isValid = Util.validarCadenaCaracteresEspecialesNumLetrasGuion(usrLogin);

		if (!isValid)
			throw new GWorkException(Util.loadErrorMessageValue("LOGIN.CARACTER"));

		if (usrLogin != null && usrLogin.trim().length() != 0 && usrLogin.trim().length() < 2)
			throw new GWorkException(Util.loadErrorMessageValue("LOGIN.MINIMO"));

	}

	/**
	 * Metodo encargado de consultar un Usuario por el id
	 * 
	 * @param param
	 * @return
	 */
	public Users consultarUsuarios(String param) {
		userService = new UserServiceImpl();
		return userService.consultarUsuario(param);
	}

	/**
	 * Metodo encargado de consultar un Usuario por el login
	 * 
	 * @param param
	 * @return
	 */
	public Users consultarUsuariosPorLogin(String login) throws GWorkException {
		userService = new UserServiceImpl();
		return userService.consultarUsuarioPorLogin(login);
	}

	/**
	 * Metodo encargado de modificar un Usuario
	 * 
	 * @param usrCodigo
	 * @param rlsCodigo
	 * @param usrIdentificacion
	 * @param usrNombre
	 * @param usrApellido
	 * @param usrTelefono
	 * @param usrDireccion
	 * @param usrEmail
	 * @param usrLogin
	 * @throws GWorkException
	 */
	public void modificarUser(Long usrCodigo, Long rlsCodigo, String usrIdentificacion, String usrNombre,
			String usrApellido, String usrTelefono, String usrDireccion, String usrEmail, String usrLogin)
			throws GWorkException {

		String codigoTemp = Util.loadMessageValue("ESTADO.ACTIVO");
		
		validarCampos(rlsCodigo, codigoTemp, usrIdentificacion, usrNombre, usrApellido, usrTelefono, usrDireccion,
				usrEmail, usrLogin);
		
		/*Validar si el codigo del usuario es enviado*/
		if (usrCodigo == null || usrCodigo.equals(-1L))
			throw new GWorkException(Util.loadErrorMessageValue("USRCOD.VACIO"));

		userService = new UserServiceImpl();
		userService.modificarUsuario(usrCodigo, codigoTemp, rlsCodigo, usrIdentificacion, usrNombre, usrApellido,
				usrTelefono, usrDireccion, usrEmail, usrLogin);
	}

	/**
	 * Metodo encargado de validar los campos del formulario
	 * 
	 * @param rlsCodigo
	 * @param usrIdentificacion
	 * @param usrNombre
	 * @param usrApellido
	 * @param usrTelefono
	 * @param usrDireccion
	 * @param usrEmail
	 * @param usrLogin
	 * @throws GWorkException
	 */
	public void validarCampos(Long rlsCodigo, String codigoEstado, String usrIdentificacion, String usrNombre,
			String usrApellido, String usrTelefono, String usrDireccion, String usrEmail, String usrLogin)
			throws GWorkException {

		boolean isValid = true;

		if (rlsCodigo != null && rlsCodigo.equals(-1L))
			throw new GWorkException(Util.loadErrorMessageValue("ROL.SEL"));

		if (codigoEstado != null && codigoEstado.equalsIgnoreCase("-1"))
			throw new GWorkException(Util.loadErrorMessageValue("USUARIO.ESTADO"));

		if (usrLogin != null && usrLogin.trim().length() == 0)
			throw new GWorkException(Util.loadErrorMessageValue("LOGIN.VACIO"));

		if (usrLogin != null && usrLogin.trim().length() != 0)
			isValid = Util.validarCadenaCaracteresEspecialesNumLetrasGuion(usrLogin);

		if (!isValid)
			throw new GWorkException(Util.loadErrorMessageValue("LOGIN.CARACTER"));
//here
		if (usrLogin != null && usrLogin.trim().length() != 0 && usrLogin.trim().length() < 6)
			throw new GWorkException(Util.loadErrorMessageValue("LOGIN.MINIMO.MAXIMO"));

		if (usrLogin != null && usrLogin.trim().length() > 18)
			throw new GWorkException(Util.loadErrorMessageValue("LOGIN.MINIMO.MAXIMO"));
		
		if (usrIdentificacion != null && usrIdentificacion.trim().length() == 0)
			throw new GWorkException(Util.loadErrorMessageValue("IDENTIFICACION.VACIO"));

		if (usrIdentificacion != null && usrIdentificacion.trim().length() != 0)
			isValid = Util.validarNumerosParametros(usrIdentificacion);

		if (!isValid)
			throw new GWorkException(Util.loadErrorMessageValue("IDENTIFICACION.CARACTER"));

		if (usrIdentificacion != null && usrIdentificacion.trim().length() != 0
				&& usrIdentificacion.trim().length() < 2)
			throw new GWorkException(Util.loadErrorMessageValue("IDENTIFICACION.MINIMO"));

		if (usrNombre != null && usrNombre.trim().length() == 0)
			throw new GWorkException(Util.loadErrorMessageValue("NOMBRE.VACIO"));

		if (usrNombre != null && usrNombre.trim().length() != 0)
			isValid = Util.validarCadenaCaracteres(usrNombre);

		if (!isValid)
			throw new GWorkException(Util.loadErrorMessageValue("NOMBRE.CARACTER"));

		if (usrNombre != null && usrNombre.trim().length() != 0 && usrNombre.trim().length() < 2)
			throw new GWorkException(Util.loadErrorMessageValue("NOMBRE.MINIMO"));

		if (usrApellido != null && usrApellido.trim().length() != 0)
			isValid = Util.validarCadenaCaracteres(usrApellido);

		if (!isValid)
			throw new GWorkException(Util.loadErrorMessageValue("APELLIDO.CARACTER"));

		if (usrApellido != null && usrApellido.trim().length() != 0 && usrApellido.trim().length() < 2)
			throw new GWorkException(Util.loadErrorMessageValue("APELLIDO.MINIMO"));

		if (usrTelefono != null && usrTelefono.trim().length() != 0)
			isValid = Util.validarNumerosParametros(usrTelefono);

		if (!isValid)
			throw new GWorkException(Util.loadErrorMessageValue("TELEFONO.CARACTER"));

		if (usrTelefono != null && usrTelefono.trim().length() != 0 && usrTelefono.trim().length() < 7)
			throw new GWorkException(Util.loadErrorMessageValue("TELEFONO.MINIMO"));

		if (usrDireccion != null && usrDireccion.trim().length() != 0)
			isValid = Util.validarCadenaCaracteresEspecialesNumLetrasGuion(usrDireccion);

		if (!isValid)
			throw new GWorkException(Util.loadErrorMessageValue("USUARIO.DIRECCION"));

		if (usrDireccion != null && usrDireccion.trim().length() != 0 && usrDireccion.trim().length() < 2)
			throw new GWorkException(Util.loadErrorMessageValue("DIRECCION.MINIMO"));
		
		if (usrEmail != null && usrEmail.trim().length() != 0) 
			if (!Util.validarEmail(usrEmail))
			 throw new GWorkException(Util.loadErrorMessageValue("EMAIL.NOVALIDO"));

		if (usrEmail != null && usrEmail.trim().length() != 0 && usrEmail.trim().length() < 2)
			throw new GWorkException(Util.loadErrorMessageValue("EMAIL.MINIMO"));
	}

	/**
	 * Metodo encargado de actualizar la lista de usuarios
	 * 
	 * @param list
	 * @param check
	 * @return
	 */
	public List<VOUsersPage> actualizarLista(boolean check)throws GWorkException {
		List<Users> listUsers = null;
		List<VOUsersPage> listVo = new ArrayList<VOUsersPage>();
try {
	
		userService = new UserServiceImpl();
		listUsers = userService.consultarUsuarios();
		for (Users user : listUsers) {
			VOUsersPage userPage = new VOUsersPage();
			userPage.setCheckState(check);
			userPage.setUsrCodigo(user.getUsrCodigo());
			userPage.setUsrApellido(user.getUsrApellido());
			userPage.setUsrDireccion(user.getUsrDireccion());
			userPage.setUsrEmail(user.getUsrEmail());
			userPage.setUsrLogin(user.getUsrLogin());
			userPage.setUsrNombre(user.getUsrNombre());
			userPage.setUsrEstado(user.getUsrEstado());
			userPage.setUsrIdentificacion(user.getUsrIdentificacion());
			if (user != null && user.getUsrFecha() != null) 
				userPage.setUsrFecha(user.getUsrFecha().toString());
			
			if (user != null && user.getRolls() != null &&
					user.getRolls().getRlsNombre() != null) 
				userPage.setNombreRol(user.getRolls().getRlsNombre());
			else
				userPage.setNombreRol("Ninguno");
			
			listVo.add(userPage);
		}
	} catch (Exception e) {
		System.err.println();
		System.out.println();		
	}

		return listVo;
	}

	/**
	 * Metodo encargado de eliminar los componentes seleccionados
	 * 
	 * @param list
	 * @param check
	 * @return
	 */
	public void eliminarUsuarios(List<VOUsersPage> list) throws GWorkException {
		userService = new UserServiceImpl();
		if (list != null && list.size() > 0) {
			for (VOUsersPage usersVo : list) {
				if (usersVo.isCheckState())
					userService.eliminarUsuario(usersVo.getUsrCodigo());
			}
		}
	}

	/**
	 * Metodo encargado de validar si alguno de los checks de la lista estan
	 * seleccionados
	 * 
	 * @throws GWorkException
	 */
	public boolean validarChecks(List<VOUsersPage> list) throws GWorkException {
		userService = new UserServiceImpl();
		if (list != null && list.size() > 0) {
			for (VOUsersPage rollsVo : list) {
				if (rollsVo.isCheckState())
					return true;
			}
		}
		return false;
	}

	public void activarBtnCrear(boolean bandera) {
		if (btnCrear == null)
			btnCrear = new HtmlCommandButton();
		btnCrear.setDisabled(bandera);
	}
	
	public void activarBtnModificar(boolean bandera) {
		if (btnModificar == null)
			btnModificar = new HtmlCommandButton();
		btnModificar.setDisabled(bandera);
	}

	public void activarTxtCarnet(boolean readonly) {
		if (TxtCarnet == null)
			TxtCarnet = new HtmlInputText();
		TxtCarnet.setReadonly(readonly);
	}
	
	public HtmlCommandButton getSelectAllBtn() {
		return selectAllBtn;
	}

	public void setSelectAllBtn(HtmlCommandButton selectAllBtn) {
		this.selectAllBtn = selectAllBtn;
	}
	
	public HtmlCommandButton getBtnCrear() {
		return btnCrear;
	}

	public void setBtnCrear(HtmlCommandButton btnCrear) {
		this.btnCrear = btnCrear;
	}

	public HtmlInputText getTxtCarnet() {
		return TxtCarnet;
	}

	public void setTxtCarnet(HtmlInputText txtCarnet) {
		this.TxtCarnet = txtCarnet;
	}
	
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public HtmlSelectOneMenu getSelectRoles() {
		return selectRoles;
	}

	public void setSelectRoles(HtmlSelectOneMenu selectRoles) {
		this.selectRoles = selectRoles;
	}

	public HtmlSelectOneMenu getSelectEstados() {
		return selectEstados;
	}

	public void setSelectEstados(HtmlSelectOneMenu selectEstados) {
		this.selectEstados = selectEstados;
	}

	public HtmlCommandButton getBtnModificar() {
		return btnModificar;
	}

	public void setBtnModificar(HtmlCommandButton btnModificar) {
		this.btnModificar = btnModificar;
	}
}
