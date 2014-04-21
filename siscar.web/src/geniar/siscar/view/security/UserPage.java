package geniar.siscar.view.security;

import geniar.siscar.model.Rolls;
import geniar.siscar.model.Users;
import geniar.siscar.util.FacesUtils;
import geniar.siscar.util.PopupMessagePage;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import com.icesoft.faces.component.ext.HtmlSelectBooleanCheckbox;

/**
 * 
 * @author JAM, - Geniar Arq S.A
 * @version 1.0
 * @Descripción : PageBean para para la pantalla de usuarios
 * 
 */
public class UserPage {

	private Long usrCodigo;
	private Long rlsCodigo;
	private Long codigoEstado;
	private Rolls rolls;
	private String usrIdentificacion;
	private String usrNombre;
	private String usr_Segundo_Nombre;
	private String usrApellido;	
	private String usrTelefono;
	private String usrDireccion;
	private String usrEmail;
	private String usrLogin;
	private String usrLoginConsulta;
	private List<VOUsersPage> listUsers;
	private boolean mostrarTabla;
	private boolean checkState;
	private boolean checkStateSelectAll;
	private boolean activarConfirmacion;
	public boolean showUsers;
	private String ELIMINAR;
	private String MODIFICAR;
	private String opcion;
	private HtmlSelectBooleanCheckbox checkStates;
	private HtmlSelectBooleanCheckbox checkStatesSelectAll;
	private UserBackingBean userBackingBean = (UserBackingBean) FacesUtils.getManagedBean("userBackingBean");

	/**
	 * Metodo encargado de crear los Usuarios
	 * 
	 * @param event
	 * @throws GWorkException
	 */
	public void action_crearUsuario(ActionEvent event) throws GWorkException {
		try {
			if (userBackingBean != null) {
				userBackingBean.guardarUser(rlsCodigo, usrIdentificacion, usrNombre, usrApellido, usrTelefono,
						usrDireccion, usrEmail, usrLogin);
				mostrarMensaje(Util.loadMessageValue("EXITO"), false);
				limpiar();
				desActivarBtnCrear();
			}
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	/**
	 * Metodo encargado de consultar los Usuarios
	 * 
	 * @param actionEvent
	 * @throws GWorkException
	 */
	public void action_consultar(ActionEvent actionEvent) throws GWorkException {
		try {
			Users users = null;
			VOUsersPage usersPage = null;
			if (userBackingBean != null && usrLoginConsulta.trim().length() != 0) {

				userBackingBean.validarCamposConsultaLogin(usrLoginConsulta);

				usersPage = new VOUsersPage();
				users = userBackingBean.consultarUsuariosPorLogin(usrLoginConsulta.trim().toUpperCase());
				if (users != null) {
					usersPage.setUsrApellido(users.getUsrApellido());
					usersPage.setUsrEmail(users.getUsrEmail());
					usersPage.setUsrLogin(users.getUsrLogin());
					usersPage.setUsrIdentificacion(users.getUsrIdentificacion());
					usersPage.setUsrNombre(users.getUsrNombre());
					usersPage.setUsrEstado(users.getUsrEstado());
					usersPage.setUsrCodigo(users.getUsrCodigo());
					setListUsers(null);
					listUsers = new ArrayList<VOUsersPage>();
					listUsers.add(usersPage);
					setMostrarTabla(true);
					setListUsers(listUsers);
					//activarBtnModificar();
				}
			} else if (consultarUsuarios() != null && consultarUsuarios().size() > 0) {
				setMostrarTabla(true);
				setListUsers(consultarUsuarios());
				//activarBtnModificar();
			} else
				throw new GWorkException(Util.loadErrorMessageValue("USUARIO.NOEXISTE"));
		} catch (Exception e) {
			setListUsers(null);
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	/**
	 * Metodo engargado de seleccionar todos los checks
	 * 
	 * @param event
	 * @throws GWorkException
	 */
	public void seleccionarTodas(ValueChangeEvent event) throws GWorkException {
		if (event.getNewValue() != null) {
			String valor = event.getNewValue().toString();
			if (valor.equalsIgnoreCase("true")) {
				if (userBackingBean != null)
					setListUsers(userBackingBean.actualizarLista(true));
			} else
				setListUsers(userBackingBean.actualizarLista(false));
		}
	}

	/**
	 * Metodo encargado de mostrar el mensaje de confirmacion
	 * 
	 * @param event
	 * @throws GWorkException
	 */
	public void mostrarConfirmacionEliminar(ActionEvent event) throws GWorkException {
		try {
			if (userBackingBean != null && userBackingBean.validarChecks(getListUsers())) {
				activarConfirmacion = true;
				setOpcion(getELIMINAR());
				mostrarMensaje(Util.loadMessageValue("MENSAJE.ALERTA"), activarConfirmacion);
			} else
				throw new GWorkException(Util.loadErrorMessageValue("USUARIO.SEL.CHEK"));
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	/**
	 * Metodo encargado de mostrar el mensaje de confirmacion
	 * 
	 * @param event
	 * @throws GWorkException
	 */
	public void mostrarConfirmacionModificar(ActionEvent event) throws GWorkException {
		try {
			if (userBackingBean != null) {
				activarConfirmacion = true;
				setOpcion(getMODIFICAR());
				mostrarMensaje(Util.loadMessageValue("MENSAJE.ALERTA"), activarConfirmacion);
			}
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	/**
	 * Metodo encargado de eliminar los componentes seleccionados
	 * 
	 * @param event
	 */
	public void action_eliminar() throws GWorkException {
		try {
			if (userBackingBean != null) {
				userBackingBean.eliminarUsuarios(getListUsers());
				setCheckStateSelectAll(false);
				setMODIFICAR(null);
				setELIMINAR(null);
				setOpcion(null);
			}
			setListUsers(consultarUsuarios());			
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
		
	}

	/**
	 * Metodo encargado de modificar los usuarios
	 * 
	 * @param event
	 * @throws GWorkException
	 */
	public void action_modificar() throws GWorkException {
		try {
			if (userBackingBean != null) {
				userBackingBean.modificarUser(usrCodigo, rlsCodigo, usrIdentificacion, usrNombre, usrApellido,
						usrTelefono, usrDireccion, usrEmail, usrLogin);
				mostrarMensaje(Util.loadMessageValue("EXITO"), false);
				limpiar();
			}
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	/**
	 * Metodo encargado de modificar los componentes seleccionados
	 * 
	 * @param event
	 */
	public void action_mostrarUsuarios(ActionEvent event) throws GWorkException {
		limpiar();
		if (userBackingBean != null) {
			String param = FacesUtils.getRequestParameter("idUsuario");
			Users users = userBackingBean.consultarUsuarios(param);

			if (users.getUsrEstado() != null) {
				if (users.getUsrEstado().equalsIgnoreCase(Util.loadMessageValue("ESTADO.ACTIVO")))
					setCodigoEstado(1L);
				else if (users.getUsrEstado().equalsIgnoreCase(Util.loadMessageValue("ESTADO.INNACTIVO")))
					setCodigoEstado(2L);
			}

			setRlsCodigo(users.getRolls().getRlsCodigo());
			if (users.getUsrApellido() != null)
				setUsrApellido(users.getUsrApellido());
			if (users.getUsrDireccion() != null)
				setUsrDireccion(users.getUsrDireccion());
			if (users.getUsrTelefono() != null)
				setUsrTelefono(users.getUsrTelefono());
			if (users.getUsrEmail() != null)
				setUsrEmail(users.getUsrEmail());
			if (users.getUsrNombre() != null)
				setUsrNombre(users.getUsrNombre());
			if (users.getUsrIdentificacion() != null)
				setUsrIdentificacion(users.getUsrIdentificacion());
			if (users.getUsrLogin() != null)
				setUsrLogin(users.getUsrLogin());
			if (users.getUsrCodigo() != null)
				setUsrCodigo(users.getUsrCodigo());
			desActivarBtnCrear();
			activarBtnModificar();
		}
	}
	
	public void activarBtnModificar(){
		if (userBackingBean != null)
			userBackingBean.activarBtnModificar(false);
	}
	
	public void desActivarBtnModificar(){
		if (userBackingBean != null)
			userBackingBean.activarBtnModificar(true);
	}

	/**
	 * Metodo encargado de activar el boton de crear
	 */
	public void activarBtnCrear() {
		if (userBackingBean != null)
			userBackingBean.activarBtnCrear(false);
	}

	/**
	 * Metodo encargado de desActivar el boton de crear
	 */
	public void desActivarBtnCrear() {
		if (userBackingBean != null)
			userBackingBean.activarBtnCrear(true);
	}
	

	/**
	 * Metodo encargado de mostrar el mensaje exito o confirmacion
	 * 
	 * @param mensaje
	 * @param buttonCancel
	 */
	public void mostrarMensaje(String mensaje, boolean buttonCancel) {
		PopupMessagePage message = (PopupMessagePage) FacesUtils.getManagedBean("popupMessagePage");
		if (message != null)
			message.showPopup(mensaje, buttonCancel);
	}

	/**
	 * Metodo encargado de limpiar los valores en pantalla
	 * 
	 */
	public void limpiar() {
		setUsrApellido(null);
		setUsrEmail(null);
		setUsrCodigo(null);
		setUsrDireccion(null);
		setUsrEmail(null);
		setUsrIdentificacion(null);
		setUsrLogin(null);
		setUsrNombre(null);
		setUsrTelefono(null);
		setCheckStateSelectAll(false);
		setListUsers(null);
		setUsrLoginConsulta(null);	
		desActivarBtnModificar();
		setRlsCodigo(-1L);
		setCodigoEstado(-1L);
		setMODIFICAR(null);
		setELIMINAR(null);
		setOpcion(null);
		userBackingBean.activarTxtCarnet(false);
	}

	public List<VOUsersPage> consultarUsuarios() throws GWorkException{
		if (userBackingBean != null)
			return userBackingBean.actualizarLista(false);
		return null;
	}

	public void action_limpiar(ActionEvent event) {
		limpiar();
	}

	public boolean isMostrarTabla() {
		return mostrarTabla;
	}

	public void setMostrarTabla(boolean mostrarTabla) {
		this.mostrarTabla = mostrarTabla;
	}

	public HtmlSelectBooleanCheckbox getCheckStates() {
		return checkStates;
	}

	public void setCheckStates(HtmlSelectBooleanCheckbox checkStates) {
		this.checkStates = checkStates;
	}

	public boolean isCheckState() {
		return checkState;
	}

	public void setCheckState(boolean checkState) {
		this.checkState = checkState;
	}

	public HtmlSelectBooleanCheckbox getCheckStatesSelectAll() {
		return checkStatesSelectAll;
	}

	public void setCheckStatesSelectAll(HtmlSelectBooleanCheckbox checkStatesSelectAll) {
		this.checkStatesSelectAll = checkStatesSelectAll;
	}

	public boolean isCheckStateSelectAll() {
		return checkStateSelectAll;
	}

	public void setCheckStateSelectAll(boolean checkStateSelectAll) {
		this.checkStateSelectAll = checkStateSelectAll;
	}

	public boolean isActivarConfirmacion() {
		return activarConfirmacion;
	}

	public void setActivarConfirmacion(boolean activarConfirmacion) {
		this.activarConfirmacion = activarConfirmacion;
	}

	public Long getUsrCodigo() {
		return usrCodigo;
	}

	public void setUsrCodigo(Long usrCodigo) {
		this.usrCodigo = usrCodigo;
	}

	public Rolls getRolls() {
		return rolls;
	}

	public void setRolls(Rolls rolls) {
		this.rolls = rolls;
	}

	public String getUsrIdentificacion() {
		return usrIdentificacion;
	}

	public void setUsrIdentificacion(String usrIdentificacion) {
		this.usrIdentificacion = usrIdentificacion;
	}

	public String getUsrNombre() {
		return usrNombre;
	}

	public void setUsrNombre(String usrNombre) {
		this.usrNombre = usrNombre;
	}

	public String getUsrApellido() {
		return usrApellido;
	}

	public void setUsrApellido(String usrApellido) {
		this.usrApellido = usrApellido;
	}

	public String getUsrTelefono() {
		return usrTelefono;
	}

	public void setUsrTelefono(String usrTelefono) {
		this.usrTelefono = usrTelefono;
	}

	public String getUsrDireccion() {
		return usrDireccion;
	}

	public void setUsrDireccion(String usrDireccion) {
		this.usrDireccion = usrDireccion;
	}

	public String getUsrEmail() {
		return usrEmail;
	}

	public void setUsrEmail(String usrEmail) {
		this.usrEmail = usrEmail;
	}

	public String getUsrLogin() {
		return usrLogin;
	}

	public void setUsrLogin(String usrLogin) {
		this.usrLogin = usrLogin;
	}

	public Long getRlsCodigo() {
		return rlsCodigo;
	}

	public void setRlsCodigo(Long rlsCodigo) {
		this.rlsCodigo = rlsCodigo;
	}

	public UserBackingBean getUserBackingBean() {
		return userBackingBean;
	}

	public void setUserBackingBean(UserBackingBean userBackingBean) {
		this.userBackingBean = userBackingBean;
	}

	public List<VOUsersPage> getListUsers() {
		return listUsers;
	}

	public void setListUsers(List<VOUsersPage> listUsers) {
		this.listUsers = listUsers;
	}

	public String getUsrLoginConsulta() {
		return usrLoginConsulta;
	}

	public void setUsrLoginConsulta(String usrLoginConsulta) {
		this.usrLoginConsulta = usrLoginConsulta;
	}

	public Long getCodigoEstado() {
		return codigoEstado;
	}

	public void setCodigoEstado(Long codigoEstado) {
		this.codigoEstado = codigoEstado;
	}

	public boolean isShowUsers() {
		return showUsers;
	}

	public void setShowUsers(boolean showUsers) {
		this.showUsers = showUsers;
	}

	public String getUsr_Segundo_Nombre() {
		return usr_Segundo_Nombre;
	}

	public void setUsr_Segundo_Nombre(String usr_Segundo_Nombre) {
		this.usr_Segundo_Nombre = usr_Segundo_Nombre;
	}

	public String getELIMINAR() {
		return "2";
	}

	public void setELIMINAR(String eliminar) {
		ELIMINAR = eliminar;
	}

	public String getMODIFICAR() {
		return  "1";
	}

	public void setMODIFICAR(String modificar) {
		MODIFICAR = modificar;
	}

	public String getOpcion() {
		return opcion;
	}

	public void setOpcion(String opcion) {
		this.opcion = opcion;
	}
}
