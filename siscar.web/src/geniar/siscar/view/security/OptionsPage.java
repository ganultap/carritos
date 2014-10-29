package geniar.siscar.view.security;

import geniar.siscar.logic.consultas.SearchSecurity;
import geniar.siscar.util.FacesUtils;
import geniar.siscar.util.PopupMessagePage;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

import java.util.List;

import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import com.icesoft.faces.component.ext.HtmlSelectBooleanCheckbox;

/**
 * 
 * @author JAM, - Geniar Arq S.A
 * @version 1.0
 * @Descripción : PageBean para para la pantalla de opciones
 * 
 */
public class OptionsPage {



	private List<VOOptionRollsPage> listOptionsRolls;
	private List<VOOptionsPage> listOptionsBD;
	private Long idModulo;
	private boolean mostrarTabla;
	private boolean checkStateBD;
	private boolean checkState;
	private boolean checkStateSelectAll;
	private boolean activarConfirmacion;
	private HtmlSelectBooleanCheckbox checkStatesBD;
	private HtmlSelectBooleanCheckbox checkStates;
	private HtmlSelectBooleanCheckbox checkStatesSelectAll;
	private OptionsBackingBean optionsBackingBean = (OptionsBackingBean) FacesUtils.getManagedBean("optionsBackingBean");
	
	
	public OptionsPage() {
		setListOptionsBD(consultarOpciones());
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
				if (optionsBackingBean != null){
					setListOptionsBD(null);
					setListOptionsBD(optionsBackingBean.actualizarLista(true));
				}
			} else{
				setListOptionsBD(null);
				setListOptionsBD(optionsBackingBean.actualizarLista(false));
			}
		}
	}

	public void action_agregarOpcionModulo(ActionEvent event) throws GWorkException {
		try {
			if (optionsBackingBean != null) {
				optionsBackingBean.agregarOpcionRol(listOptionsBD);
				actualizarListaOpcionesModulo();
				setListOptionsBD(null);
				setListOptionsBD(consultarOpciones());
			}
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void action_eliminarOpcionModulo(ActionEvent event) throws GWorkException {
		try {
			activarConfirmacion = true;
			mostrarMensaje(Util.loadMessageValue("MENSAJE.ALERTA"), activarConfirmacion);
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}
	
	public void action_eliminarOpcionModulo() throws GWorkException {
		try {
			if (optionsBackingBean != null) {
				setActivarConfirmacion(false);
				optionsBackingBean.eliminarOpcionRol(listOptionsRolls);
				actualizarListaOpcionesModulo();
				setListOptionsBD(null);
				setListOptionsBD(consultarOpciones());
			}
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}
	

	public List<VOOptionsPage> consultarOpciones() {
		if (optionsBackingBean != null)
			return optionsBackingBean.actualizarLista();
		return null;
	}

	public void consultarOpcionesRol(ValueChangeEvent event) throws GWorkException {
		try {
			if (event.getNewValue() != null) {
				String valor = event.getNewValue().toString();

				if (!valor.equalsIgnoreCase("-1"))
					setListOptionsRolls(optionsBackingBean.actualizarListaOpciones(SearchSecurity
							.consultarOpcionesDeRol(new Long(valor))));
				else
					setListOptionsRolls(null);
			}
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void actualizarListaOpcionesModulo() throws GWorkException {
		setListOptionsRolls(optionsBackingBean.actualizarListaOpciones(SearchSecurity.consultarOpcionesDeRol(new Long(
				idModulo))));
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

	public OptionsBackingBean getOptionsBackingBean() {
		return optionsBackingBean;
	}

	public void setOptionsBackingBean(OptionsBackingBean optionsBackingBean) {
		this.optionsBackingBean = optionsBackingBean;
	}

	public Long getIdModulo() {
		return idModulo;
	}

	public void setIdModulo(Long idModulo) {
		this.idModulo = idModulo;
	}

	public List<VOOptionsPage> getListOptionsBD() {
		return listOptionsBD;
	}

	public void setListOptionsBD(List<VOOptionsPage> listOptionsBD) {
		this.listOptionsBD = listOptionsBD;
	}

	public boolean isCheckStateBD() {
		return checkStateBD;
	}

	public void setCheckStateBD(boolean checkStateBD) {
		this.checkStateBD = checkStateBD;
	}

	public HtmlSelectBooleanCheckbox getCheckStatesBD() {
		return checkStatesBD;
	}

	public void setCheckStatesBD(HtmlSelectBooleanCheckbox checkStatesBD) {
		this.checkStatesBD = checkStatesBD;
	}

	public List<VOOptionRollsPage> getListOptionsRolls() {
		return listOptionsRolls;
	}

	public void setListOptionsRolls(List<VOOptionRollsPage> listOptionsRolls) {
		this.listOptionsRolls = listOptionsRolls;
	}
}
