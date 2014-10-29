package geniar.siscar.view.security;

import geniar.siscar.consults.ConsultsService;
import geniar.siscar.consults.impl.ConsultsServiceImpl;
import geniar.siscar.model.ConsultUsers;
import geniar.siscar.model.Rolls;
import geniar.siscar.model.Users;
import geniar.siscar.persistence.UsersDAO;
import geniar.siscar.util.FacesUtils;
import geniar.siscar.util.PopupMessagePage;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

import java.util.Date;
import java.util.List;

import javax.faces.event.ActionEvent;

import com.icesoft.faces.component.ext.HtmlOutputText;
import com.icesoft.faces.component.ext.RowSelectorEvent;

public class ConsultUsersPage {

	private Long usrCodigo;
	private Long rlsCodigo;
	private Long codigoEstado;
	private Rolls rolls;
	private String usrIdentificacion;
	private String usrNombre;
	private String usrApellido;
	private String usrTelefono;
	private String usrDireccion;
	private String usrEmail;
	private String usrLogin;
	private String usrLoginConsulta;
	private Date fechaModificacion;

	private String criterio;
	private Long idType;
	public boolean showUsers;
	private boolean mostrarTabla;
	private HtmlOutputText txtIdUsers;
	private String nombre;
	private String apellido;
	private String nombreRol;
	private List<ConsultUsers> listUsers;

	/**
	 * Metodo encargado de consultar los Usuarios
	 * 
	 * @param actionEvent
	 * @throws GWorkException
	 */
	public void action_consultar(ActionEvent actionEvent) throws GWorkException {
		try {

			ConsultsService consultsService = new ConsultsServiceImpl();

			boolean esValido = true;

			if (criterio != null && criterio.trim().length() != 0)
				esValido = Util.validarPlaca(criterio);

			if (!esValido)
				throw new GWorkException(Util
						.loadErrorMessageValue("CRITERIO.CARACTER"));

			// List<Users> users = new UsersDAO().find
			listUsers = consultsService.usuariosCiat(criterio.toUpperCase(),
					criterio.toUpperCase(), criterio.toUpperCase());
			if (listUsers != null && listUsers.size() > 0) {
				setListUsers(listUsers);
				setMostrarTabla(true);
			} else
				throw new GWorkException(Util
						.loadErrorMessageValue("search.not.found"));

		} catch (Exception e) {
			setListUsers(null);
			mostrarMensaje(e.getMessage(), false);
		}
	}

	public void action_consultarPopup(ActionEvent event) {
		try {
			setShowUsers(true);
		} catch (Exception e) {
			criterio = "";
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	/**
	 * Metodo para la seleccion desde la tabla del vehiculo a reservar
	 * 
	 * @param event
	 */
	public void rowSelectionListenerUsers(RowSelectorEvent event)
			throws Exception {

		try {
			String codigoUsers = (String) txtIdUsers.getValue();
			UserPage userPage = (UserPage) FacesUtils
					.getManagedBean("userPage");
			UserBackingBean userBackingBean = (UserBackingBean) FacesUtils
					.getManagedBean("userBackingBean");

			String login = null;
			UsersDAO userDao = new UsersDAO();
			Users usersObj = null;

			if (userDao.findByUsrIdentificacion(codigoUsers) != null
					&& userDao.findByUsrIdentificacion(codigoUsers).size() > 0)
				usersObj = userDao.findByUsrIdentificacion(codigoUsers).get(0);

			if (usersObj != null)
				login = usersObj.getUsrLogin();

			for (ConsultUsers users : this.listUsers) {
				if (users.getUsrIdentificacion().equalsIgnoreCase(codigoUsers)) {

					if (userPage != null) {
						if (users.getRolls() != null
								&& users.getRolls().getRlsCodigo() != null) {
							userPage.setRlsCodigo(users.getRolls()
									.getRlsCodigo());
						}
						if (users.getUsrApellido() != null)
							userPage.setUsrApellido(users.getUsrApellido());
						if (users.getUsrDireccion() != null)
							userPage.setUsrDireccion(users.getUsrDireccion());
						if (users.getUsrTelefono() != null)
							userPage.setUsrTelefono(users.getUsrTelefono());
						if (users.getUsrEmail() != null)
							userPage.setUsrEmail(users.getUsrEmail());
						if (users.getUsrNombre() != null)
							userPage.setUsrNombre(users.getUsrNombre());
						if (users.getUsrIdentificacion() != null)
							userPage.setUsrIdentificacion(users
									.getUsrIdentificacion());
						if (login != null)
							userPage.setUsrLogin(login);
						if (users.getUsrCodigo() != null)
							userPage.setUsrCodigo(users.getUsrCodigo());

						if (userBackingBean != null) {
							userBackingBean.getBtnModificar().setDisabled(true);
							userBackingBean.getBtnCrear().setDisabled(false);
							userBackingBean.activarTxtCarnet(true);
						}
					}

					nombre = users.getUsrNombre();
					// nombreRol = users.getRolls().getRlsNombre();
					apellido = users.getUsrApellido();
					listUsers.clear();
					showUsers = false;
					return;
				}
			}
		} catch (Exception e) {
			mostrarMensaje(e.getMessage(), false);
		}
	}

	public void action_closeWindow(ActionEvent event) {
		setShowUsers(false);
	}

	public void mostrarMensaje(String mensaje, boolean buttonCancel) {
		PopupMessagePage message = (PopupMessagePage) FacesUtils
				.getManagedBean("popupMessagePage");
		if (message != null)
			message.showPopup(mensaje, buttonCancel);
	}

	public List<ConsultUsers> getListUsers() {
		return listUsers;
	}

	public void setListUsers(List<ConsultUsers> listUsers) {
		this.listUsers = listUsers;
	}

	public Long getIdType() {
		return idType;
	}

	public void setIdType(Long idType) {
		this.idType = idType;
	}

	public HtmlOutputText getTxtIdUsers() {
		return txtIdUsers;
	}

	public void setTxtIdUsers(HtmlOutputText txtIdUsers) {
		this.txtIdUsers = txtIdUsers;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNombreRol() {
		return nombreRol;
	}

	public void setNombreRol(String nombreRol) {
		this.nombreRol = nombreRol;
	}

	public String getCriterio() {
		return criterio;
	}

	public void setCriterio(String criterio) {
		this.criterio = criterio;
	}

	public boolean isShowUsers() {
		return showUsers;
	}

	public void setShowUsers(boolean showUsers) {
		this.showUsers = showUsers;
	}

	public boolean isMostrarTabla() {
		return mostrarTabla;
	}

	public void setMostrarTabla(boolean mostrarTabla) {
		this.mostrarTabla = mostrarTabla;
	}

	public Long getUsrCodigo() {
		return usrCodigo;
	}

	public void setUsrCodigo(Long usrCodigo) {
		this.usrCodigo = usrCodigo;
	}

	public Long getRlsCodigo() {
		return rlsCodigo;
	}

	public void setRlsCodigo(Long rlsCodigo) {
		this.rlsCodigo = rlsCodigo;
	}

	public Long getCodigoEstado() {
		return codigoEstado;
	}

	public void setCodigoEstado(Long codigoEstado) {
		this.codigoEstado = codigoEstado;
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

	public String getUsrLoginConsulta() {
		return usrLoginConsulta;
	}

	public void setUsrLoginConsulta(String usrLoginConsulta) {
		this.usrLoginConsulta = usrLoginConsulta;
	}

	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

}
