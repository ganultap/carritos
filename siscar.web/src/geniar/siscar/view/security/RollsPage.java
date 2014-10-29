package geniar.siscar.view.security;

import geniar.siscar.model.Rolls;
import geniar.siscar.util.FacesUtils;
import geniar.siscar.util.PopupMessagePage;
import geniar.siscar.util.Util;
import geniar.siscar.view.BaseBean;
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
 * @Descripción : PageBean para para la pantalla de roles
 * 
 */
public class RollsPage extends BaseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long rlsCodigo;
	private String rlsNombre;
	private String rlsNombreConsulta;
	private String rlsDescripcion;
	private String rlsMail;
	private List<VORollsPage> listRols;
	private boolean mostrarTabla;
	private boolean checkState;
	private boolean checkStateSelectAll;
	private boolean activarConfirmacion;
	private String ELIMINAR;
	private String MODIFICAR;
	private String opcion;
	private HtmlSelectBooleanCheckbox checkStates;
	private HtmlSelectBooleanCheckbox checkStatesSelectAll;
	private RollsBackingBean rollsBackingBean = (RollsBackingBean) FacesUtils.getManagedBean("bakingBeanRolls");;

	public Long getRlsCodigo() {
		return rlsCodigo;
	}

	public String getOpcion() {
		return opcion;
	}

	public void setOpcion(String opcion) {
		this.opcion = opcion;
	}

	public void setRlsCodigo(Long rlsCodigo) {
		this.rlsCodigo = rlsCodigo;
	}

	public String getRlsNombre() {
		return rlsNombre;
	}

	public void setRlsNombre(String rlsNombre) {
		this.rlsNombre = rlsNombre;
	}

	public String getRlsDescripcion() {
		return rlsDescripcion;
	}

	public void setRlsDescripcion(String rlsDescripcion) {
		this.rlsDescripcion = rlsDescripcion;
	}

	public String getRlsMail() {
		return rlsMail;
	}

	public void setRlsMail(String rlsMail) {
		this.rlsMail = rlsMail;
	}

	/**
	 * Metodo encargado de crear los roles
	 * 
	 * @param event
	 * @throws GWorkException
	 */
	public void action_crearRol(ActionEvent event) throws GWorkException {
		try {
			if (rollsBackingBean != null) {
				rollsBackingBean.guardarRol(rlsNombre, rlsDescripcion, rlsMail);
				mostrarMensaje(Util.loadMessageValue("EXITO"), false);
				limpiar();
			}
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	/**
	 * Metodo encargado de consultar los roles
	 * 
	 * @param actionEvent
	 * @throws GWorkException
	 */
	public void action_consultar(ActionEvent actionEvent) throws GWorkException {
		try {
			Rolls rolls = null;
			VORollsPage rollsPage = null;
			if (rollsBackingBean != null && rlsNombreConsulta.trim().length() != 0) {
				rollsPage = new VORollsPage();
				rolls = rollsBackingBean.consultarRolPorNombre(rlsNombreConsulta);

				if (rolls != null) {
					rollsPage.setIdRol(rolls.getRlsCodigo().toString());
					rollsPage.setDescripcion(rolls.getRlsDescripcion());
					rollsPage.setEmail(rolls.getRlsMail());
					rollsPage.setNombre(rolls.getRlsNombre());
					setListRols(null);
					listRols = new ArrayList<VORollsPage>();
					listRols.add(rollsPage);
					setListRols(listRols);
					setMostrarTabla(true);
				}
			} else if (consultarRoles() != null && consultarRoles().size() > 0) {
				setMostrarTabla(true);
				setListRols(consultarRoles());
			} else
				throw new GWorkException(Util.loadErrorMessageValue("search.not.found"));
		} catch (Exception e) {
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
				if (rollsBackingBean != null)
					setListRols(rollsBackingBean.actualizarLista(true));
			} else
				setListRols(rollsBackingBean.actualizarLista(false));
		}
	}

	/**
	 * Metodo encargado de mostrar el mensaje de confirmacion
	 * 
	 * @param event
	 * @throws GWorkException
	 */
	public void mostrarConfirmacion(ActionEvent event) throws GWorkException {
		try {
			if (rollsBackingBean != null && rollsBackingBean.validarChecks(getListRols())) {
				activarConfirmacion = true;
				setOpcion(getELIMINAR());
				mostrarMensaje(Util.loadMessageValue("MENSAJE.ALERTA"), activarConfirmacion);
			} else
				throw new GWorkException(Util.loadErrorMessageValue("ROL.SEL.CHEK"));
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
			if (rollsBackingBean != null) {
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
			if (rollsBackingBean != null) {
				rollsBackingBean.eliminarRoles(getListRols());
				setCheckStateSelectAll(false);
			}
			setListRols(consultarRoles());
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

	}

	/**
	 * Metodo encargado de crear los roles
	 * 
	 * @param event
	 * @throws GWorkException
	 */
	public void action_modificar() throws GWorkException {
		try {
			if (rollsBackingBean != null) {
				rollsBackingBean.modificarRol(rlsCodigo, rlsNombre, rlsDescripcion, rlsMail);
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
	public void action_mostrarRol(ActionEvent event) throws GWorkException {
		if (rollsBackingBean != null) {
			String param = FacesUtils.getRequestParameter("idRol");
			Rolls rolls = rollsBackingBean.consultarRol(param);
			setRlsNombre(rolls.getRlsNombre());
			setRlsMail(rolls.getRlsMail());
			setRlsDescripcion(rolls.getRlsDescripcion());
			setRlsCodigo(rolls.getRlsCodigo());
			desActivarBtnCrear();
		}
	}

	/**
	 * Metodo encargado de activar el boton de crear
	 */
	public void activarBtnCrear() {
		if (rollsBackingBean != null)
			rollsBackingBean.activarBtnCrear(false);
	}

	/**
	 * Metodo encargado de desActivar el boton de crear
	 */
	public void desActivarBtnCrear() {
		if (rollsBackingBean != null)
			rollsBackingBean.activarBtnCrear(true);
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
		setRlsNombre(null);
		setRlsMail(null);
		setRlsDescripcion(null);
		setListRols(null);
		setRlsNombreConsulta(null);
		setCheckStateSelectAll(false);
		activarBtnCrear();
		setMODIFICAR(null);
		setELIMINAR(null);
		setOpcion(null);
	}

	public List<VORollsPage> consultarRoles() {
		if (rollsBackingBean != null)
			return rollsBackingBean.actualizarLista(false);
		return null;
	}

	public void action_limpiar(ActionEvent event) {
		limpiar();
	}

	public RollsBackingBean getRollsBackingBean() {
		return rollsBackingBean;
	}

	public void setRollsBackingBean(RollsBackingBean rollsBackingBean) {
		this.rollsBackingBean = rollsBackingBean;
	}

	public List<VORollsPage> getListRols() {
		return listRols;
	}

	public void setListRols(List<VORollsPage> listRols) {
		this.listRols = listRols;
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

	public String getRlsNombreConsulta() {
		return rlsNombreConsulta;
	}

	public void setRlsNombreConsulta(String rlsNombreConsulta) {
		this.rlsNombreConsulta = rlsNombreConsulta;
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
}
