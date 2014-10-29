package geniar.siscar.view.billing;

import geniar.siscar.logic.billing.services.PeriodService;
import geniar.siscar.logic.billing.services.impl.PeriodServiceImpl;
import geniar.siscar.model.Period;
import geniar.siscar.util.FacesUtils;
import geniar.siscar.util.PopupMessagePage;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.icesoft.faces.component.ext.HtmlCommandButton;
import com.icesoft.faces.component.ext.HtmlDataTable;
import com.icesoft.faces.component.ext.HtmlInputText;
import com.icesoft.faces.component.ext.HtmlInputTextarea;
import com.icesoft.faces.component.ext.HtmlSelectOneMenu;
import com.icesoft.faces.component.selectinputdate.SelectInputDate;

public class PeriodPage {

	private PeriodService periodService;

	private HtmlInputText txtNombre;
	private HtmlInputTextarea txtDescripcion;
	private SelectInputDate txtFechaIni;
	private SelectInputDate txtFechaFin;

	private HtmlDataTable tblPRD;

	private HtmlSelectOneMenu cbxTipoNovedad;

	private HtmlCommandButton btnCrear;
	private HtmlCommandButton btnModificar;
	private HtmlCommandButton btnEliminar;

	private String descripcion;
	private String nombre;
	private Long idPeriodo;
	private Date fechaInicio;
	private Date fechaFin;
	private List<Period> listaPRD;
	private Long idTipoNovedad;

	private List<VOPeriodPage> listPeriods;
	private boolean mostrarTabla;
	private boolean checkState;

	private boolean disableCrear;
	private boolean disableModificar;
	private boolean disableEliminar;

	private boolean activarConfirmacion;
	private Integer opcConfirmacion;
	private static Integer MODIFICAR = 1;
	private static Integer ELIMINAR = 2;

	public void action_crearPeriodo(ActionEvent actionEvent) {
		try {
			validarDatos();

			periodService
					.crearPeriod(nombre.trim().toUpperCase(), descripcion
							.trim().toUpperCase(), fechaInicio, fechaFin,
							idTipoNovedad);

			mostrarMensaje(Util.loadMessageValue("EXITO"), false);
			limpiar();
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	private void validarDatos() throws GWorkException {

		boolean isValid = true;

		if (nombre != null && nombre.trim().length() != 0)
			isValid = Util
					.validarCadenaCaracteresEspecialesNumLetrasGuion(nombre);

		if (!isValid)
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.CARACTERNOMBREPERIODO"));

		if (nombre != null && nombre.trim().length() != 0
				&& nombre.trim().length() < 5)
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.MINIMONOMBREPERIODO"));

		if (nombre.toString().trim().length() == 0) {
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.INGRPERIODO"));
		}

		if (fechaInicio == null || fechaInicio.toString().trim().length() == 0) {
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.INGRFECHAINICIOPERIODO"));
		}

		if (fechaFin == null || fechaFin.toString().trim().length() == 0) {
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.INGRFECHAFINPERIODO"));
		}

		if (fechaFin.before(fechaInicio)) {
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.FECHASMINMAXPERIODO"));
		}

		if (descripcion != null && descripcion.trim().length() != 0)
			Util.validarLimite(descripcion, 200, 3,
					"ERROR.LIMDESCRIPCIONPERIODO");

		if (descripcion != null
				&& descripcion.toString().trim().length() >= 250) {
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.LIMDESCRIPCIONPERIODO"));
		}

		if (idTipoNovedad == -1)
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.TIPONOVEDAD"));
	}

	public void action_modificarPeriodo() {
		try {
			validarDatos();
			periodService.modificarPeriod(idPeriodo, nombre.trim()
					.toUpperCase(), descripcion.trim().toUpperCase(),
					fechaInicio, fechaFin, idTipoNovedad);

			mostrarMensaje(Util.loadMessageValue("EXITO.MODIFICAR"), false);
			limpiar();
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	/**
	 * Metodo encargado de eliminar los componentes seleccionados
	 * 
	 * @param event
	 */
	public void action_eliminarPeriodo() throws GWorkException {
		try {
			periodService = new PeriodServiceImpl();
			periodService.eliminarPeriodo(idPeriodo);

			mostrarMensaje(Util.loadMessageValue("EXITO.ELIMINAR"), false);
			limpiar();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

	}

	public void action_consultar(ActionEvent actionEvent) {
		try {
			// limpiar();
			Period period = null;
			VOPeriodPage periodPage = null;
			if (nombre.trim().length() != 0) {
				periodPage = new VOPeriodPage();
				period = consultarPeriodoPorNombre(nombre.trim().toUpperCase());
				if (period != null) {
					periodPage.setIdPeriodo(period.getPerId());
					periodPage.setNombre(period.getPerNombre());
					periodPage.setDescripcion(period.getPerObservacion());
					periodPage.setFechaInicio(period.getPerFechaIni());
					periodPage.setFechaFin(period.getPerFechaFin());
					setListPeriods(null);
					listPeriods = new ArrayList<VOPeriodPage>();
					listPeriods.add(periodPage);
					setListPeriods(listPeriods);
					setMostrarTabla(true);
				}
			} else if (consultarPeriodos() != null
					&& consultarPeriodos().size() > 0) {
				setMostrarTabla(true);
				this.setListPeriods(consultarPeriodos());
			} else
				throw new GWorkException(Util
						.loadErrorMessageValue("search.not.found"));
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	/**
	 * Metodo encargado de mostrar el mensaje de confirmacion
	 * 
	 * @param event
	 * @throws GWorkException
	 */
	public void mostrarConfirmacionModificar(ActionEvent event)
			throws GWorkException {
		try {
			activarConfirmacion = true;
			setOpcConfirmacion(getMODIFICAR());
			mostrarMensaje(Util.loadMessageValue("MENSAJE.ALERTA"),
					activarConfirmacion);
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
	public void mostrarConfirmacionEliminar(ActionEvent event)
			throws GWorkException {
		try {
			activarConfirmacion = true;
			setOpcConfirmacion(getELIMINAR());
			mostrarMensaje(Util.loadMessageValue("MENSAJE.ALERTA"),
					activarConfirmacion);
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void action_limpiar(ActionEvent actionEvent) {
		limpiar();
	}

	public void validateMinLenght(FacesContext context, UIComponent validate,
			Object value) throws GWorkException {
		String inputText = (String) value;

		if (inputText.length() <= 4) {
			((UIInput) validate).setValid(false);
			FacesUtils.addErrorMessage(Util
					.loadErrorMessageValue("ERROR.MINIMONOMBREPERIODO"));
		}
	}

	private void limpiar() {
		disableCrear = false;
		getBtnModificar().setDisabled(true);
		getBtnEliminar().setDisabled(true);
		getBtnCrear().setDisabled(false);
		nombre = null;
		descripcion = null;
		fechaInicio = null;
		fechaFin = null;
		listPeriods = null;
		idTipoNovedad = -1L;
	}

	/**
	 * Metodo encargado de mostrar el mensaje exito o confirmacion
	 * 
	 * @param mensaje
	 * @param buttonCancel
	 */
	public void mostrarMensaje(String mensaje, boolean buttonCancel) {
		PopupMessagePage message = (PopupMessagePage) FacesUtils
				.getManagedBean("popupMessagePage");
		if (message != null)
			message.showPopup(mensaje, buttonCancel);
	}

	/**
	 * Metodo encargado de actualizar la lista de periodos
	 * 
	 * @param list
	 * @param check
	 * @return
	 * @throws GWorkException
	 */
	public List<VOPeriodPage> actualizarLista(boolean check)
			throws GWorkException {
		List<Period> listPeriods = null;
		List<VOPeriodPage> listVo = new ArrayList<VOPeriodPage>();
		periodService = new PeriodServiceImpl();
		listPeriods = periodService.listarTodosPeriod();
		for (Period period : listPeriods) {
			VOPeriodPage periodPage = new VOPeriodPage();
			periodPage.setCheckState(check);
			periodPage.setIdPeriodo(period.getPerId());
			periodPage.setDescripcion(period.getPerObservacion());
			periodPage.setNombre(period.getPerNombre());
			periodPage.setFechaInicio(period.getPerFechaIni());
			periodPage.setFechaFin(period.getPerFechaFin());
			listVo.add(periodPage);
		}
		return listVo;
	}

	public List<VOPeriodPage> consultarPeriodos() {
		if (this != null)
			try {
				return actualizarLista(false);
			} catch (GWorkException e) {
				e.printStackTrace();
			}
		return null;
	}

	/**
	 * Metodo encargado de consultar un periodo por el nombre
	 * 
	 * @param param
	 * @return
	 */
	public Period consultarPeriodoPorNombre(String nombre)
			throws GWorkException {
		periodService = new PeriodServiceImpl();
		return periodService.consultarPeriod(nombre);
	}

	/**
	 * Metodo encargado de modificar los componentes seleccionados
	 * 
	 * @param event
	 */
	public void action_mostrarPeriodos(ActionEvent event) throws GWorkException {
		limpiar();
		// String dato = FacesUtils.getRequestParameter("idPeriodo");
		String param = (String) FacesUtils.getRequestParameter("idPeriodo");
		Period period = this.consultarPeriodos(new Long(param));

		if (period.getPerNombre() != null)
			nombre = period.getPerNombre();
		if (period.getPerFechaIni() != null)
			fechaInicio = period.getPerFechaIni();
		if (period.getPerFechaFin() != null)
			fechaFin = period.getPerFechaFin();
		if (period.getPerObservacion() != null)
			descripcion = period.getPerObservacion();
		if (period.getPerId() != null)
			idPeriodo = period.getPerId();
		if (period.getNoveltyTypes() != null)
			idTipoNovedad = period.getNoveltyTypes().getNtId();

		desActivarBtnCrear();
		activarBtnModificar();
		getBtnEliminar().setDisabled(false);

	}

	public void activarBtnModificar() {

		getBtnModificar().setDisabled(false);
	}

	public void desActivarBtnModificar() {

		getBtnModificar().setDisabled(true);
	}

	/**
	 * Metodo encargado de activar el boton de crear
	 */
	public void activarBtnCrear() {
		activarBtnCrear(false);
	}

	/**
	 * Metodo encargado de desActivar el boton de crear
	 */
	public void desActivarBtnCrear() {
		activarBtnCrear(true);
	}

	public void activarBtnCrear(boolean bandera) {
		if (btnCrear == null)
			btnCrear = new HtmlCommandButton();
		btnCrear.setDisabled(bandera);
	}

	/**
	 * Metodo encargado de consultar un Usuario por el id
	 * 
	 * @param param
	 * @return
	 * @throws GWorkException
	 */
	public Period consultarPeriodos(Long param) throws GWorkException {
		periodService = new PeriodServiceImpl();

		return periodService.consultarPeriodById(param);
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public boolean isDisableCrear() {
		return disableCrear;
	}

	public void setDisableCrear(boolean disableCrear) {
		this.disableCrear = disableCrear;
	}

	public boolean isActivarConfirmacion() {
		return activarConfirmacion;
	}

	public void setActivarConfirmacion(boolean activarConfirmacion) {
		this.activarConfirmacion = activarConfirmacion;
	}

	public Integer getOpcConfirmacion() {
		return opcConfirmacion;
	}

	public void setOpcConfirmacion(Integer opcConfirmacion) {
		this.opcConfirmacion = opcConfirmacion;
	}

	public PeriodService getPeriodService() {
		return periodService;
	}

	public void setPeriodService(PeriodService periodService) {
		this.periodService = periodService;
	}

	public HtmlDataTable getTblPRD() {
		return tblPRD;
	}

	public void setTblPRD(HtmlDataTable tblPRD) {
		this.tblPRD = tblPRD;
	}

	public List<Period> getListaPRD() {
		return listaPRD;
	}

	public void setListaPRD(List<Period> listaPRD) {
		this.listaPRD = listaPRD;
	}

	public HtmlInputText getTxtNombre() {
		return txtNombre;
	}

	public void setTxtNombre(HtmlInputText txtNombre) {
		this.txtNombre = txtNombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Long getIdPeriodo() {
		return idPeriodo;
	}

	public void setIdPeriodo(Long idPeriodo) {
		this.idPeriodo = idPeriodo;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public SelectInputDate getTxtFechaIni() {
		return txtFechaIni;
	}

	public void setTxtFechaIni(SelectInputDate txtFechaIni) {
		this.txtFechaIni = txtFechaIni;
	}

	public SelectInputDate getTxtFechaFin() {
		return txtFechaFin;
	}

	public void setTxtFechaFin(SelectInputDate txtFechaFin) {
		this.txtFechaFin = txtFechaFin;
	}

	public HtmlInputTextarea getTxtDescripcion() {
		return txtDescripcion;
	}

	public void setTxtDescripcion(HtmlInputTextarea txtDescripcion) {
		this.txtDescripcion = txtDescripcion;
	}

	public List<VOPeriodPage> getListPeriods() {
		return listPeriods;
	}

	public void setListPeriods(List<VOPeriodPage> listPeriods) {
		this.listPeriods = listPeriods;
	}

	public boolean isMostrarTabla() {
		return mostrarTabla;
	}

	public void setMostrarTabla(boolean mostrarTabla) {
		this.mostrarTabla = mostrarTabla;
	}

	public boolean isCheckState() {
		return checkState;
	}

	public void setCheckState(boolean checkState) {
		this.checkState = checkState;
	}

	public HtmlCommandButton getBtnCrear() {
		return btnCrear;
	}

	public void setBtnCrear(HtmlCommandButton btnCrear) {
		this.btnCrear = btnCrear;
	}

	public HtmlCommandButton getBtnModificar() {
		return btnModificar;
	}

	public void setBtnModificar(HtmlCommandButton btnModificar) {
		this.btnModificar = btnModificar;
	}

	public static Integer getMODIFICAR() {
		return 1;
	}

	public static void setMODIFICAR(Integer modificar) {
		MODIFICAR = modificar;
	}

	public static Integer getELIMINAR() {
		return 2;
	}

	public static void setELIMINAR(Integer eliminar) {
		ELIMINAR = eliminar;
	}

	public HtmlCommandButton getBtnEliminar() {
		return btnEliminar;
	}

	public void setBtnEliminar(HtmlCommandButton btnEliminar) {
		this.btnEliminar = btnEliminar;
	}

	public Long getidTipoNovedad() {
		return idTipoNovedad;
	}

	public void setidTipoNovedad(Long idTipoNovedad) {
		this.idTipoNovedad = idTipoNovedad;
	}
}
