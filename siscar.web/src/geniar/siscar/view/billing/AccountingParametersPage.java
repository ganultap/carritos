/**
 * 
 */
package geniar.siscar.view.billing;

import geniar.siscar.logic.billing.services.AccountingParametersService;
import geniar.siscar.logic.consultas.SearchAccountingParameters;
import geniar.siscar.model.Account;
import geniar.siscar.model.AccountingParameters;
import geniar.siscar.model.Attribute;
import geniar.siscar.model.Auxiliar;
import geniar.siscar.model.ChargeType;
import geniar.siscar.model.Company;
import geniar.siscar.model.ControlType;
import geniar.siscar.model.CostsCenters;
import geniar.siscar.model.DescriptionType;
import geniar.siscar.model.DocumentOne;
import geniar.siscar.model.DocumentOneType;
import geniar.siscar.model.DocumentTwo;
import geniar.siscar.model.DocumentTwoType;
import geniar.siscar.model.Future;
import geniar.siscar.model.Line;
import geniar.siscar.model.LocationsTypes;
import geniar.siscar.model.MovementType;
import geniar.siscar.model.TransactionType;
import geniar.siscar.util.FacesUtils;
import geniar.siscar.util.PopupMessagePage;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import com.icesoft.faces.component.ext.HtmlDataTable;
import com.icesoft.faces.component.ext.HtmlSelectOneMenu;

/**
 * The Class AccountingParametersPage.
 *
 * @author geniar
 */
public class AccountingParametersPage {

	/** The accounting parameters service. */
	private AccountingParametersService accountingParametersService;

	/** The id parametro contable. */
	private Long idParametroContable;
	
	/** The descripcion. */
	private String descripcion;

	/** The id tipo asignacion. */
	private Long idTipoAsignacion;
	
	/** The id tipo transaccion. */
	private Long idTipoTransaccion;
	
	/** The id tipo movimiento. */
	private Long idTipoMovimiento;
	
	/** The id tipo cargo. */
	private Long idTipoCargo;
	
	/** The id company. */
	private Long idCompany;
	
	/** The id numero cuenta. */
	private Long idNumeroCuenta;
	
	/** The id centro costo. */
	private Long idCentroCosto;
	
	/** The id numero linea. */
	private Long idNumeroLinea;
	
	/** The id auxiliar. */
	private Long idAuxiliar;
	
	/** The id tipo control. */
	private Long idTipoControl;
	
	/** The id futuro. */
	private Long idFuturo;
	
	/** The id descripcion. */
	private Long idDescripcion;
	
	/** The id tipo doc uno. */
	private Long idTipoDocUno;
	
	/** The id num doc uno. */
	private Long idNumDocUno;
	
	/** The id tipo doc dos. */
	private Long idTipoDocDos;
	
	/** The id num doc dos. */
	private Long idNumDocDos;
	
	/** The id atributo. */
	private Long idAtributo;
	
	/** The id tipo localizacion. */
	private Long idTipoLocalizacion;

	private Long idState;
	
	private Boolean acpState;
	
	/** The disabled crear. */
	private boolean disabledCrear;

	/** The select tipo asignacion. */
	private HtmlSelectOneMenu selectTipoAsignacion;
	
	/** The select tipo transaccion. */
	private HtmlSelectOneMenu selectTipoTransaccion;
	
	/** The select tipo movimiento. */
	private HtmlSelectOneMenu selectTipoMovimiento;
	
	/** The select tipo cargo. */
	private HtmlSelectOneMenu selectTipoCargo;
	
	/** The select company. */
	private HtmlSelectOneMenu selectCompany;
	
	/** The select numero. */
	private HtmlSelectOneMenu selectNumero;
	
	/** The select centro costo. */
	private HtmlSelectOneMenu selectCentroCosto;
	
	/** The select linea. */
	private HtmlSelectOneMenu selectLinea;
	
	/** The select auxiliar. */
	private HtmlSelectOneMenu selectAuxiliar;
	
	/** The select tipo control. */
	private HtmlSelectOneMenu selectTipoControl;
	
	/** The select futuro. */
	private HtmlSelectOneMenu selectFuturo;
	
	/** The select descripcion. */
	private HtmlSelectOneMenu selectDescripcion;
	
	/** The select tipo doc uno. */
	private HtmlSelectOneMenu selectTipoDocUno;
	
	/** The select num doc uno. */
	private HtmlSelectOneMenu selectNumDocUno;
	
	/** The select tipo doc dos. */
	private HtmlSelectOneMenu selectTipoDocDos;
	
	/** The select num doc dos. */
	private HtmlSelectOneMenu selectNumDocDos;
	
	/** The select atributo. */
	private HtmlSelectOneMenu selectAtributo;
	
	/** The select tipo localizacion. */
	private HtmlSelectOneMenu selectTipoLocalizacion;

	private HtmlSelectOneMenu selectState;
	
	/** The items tipo transaccion. */
	private SelectItem[] itemsTipoTransaccion;
	
	/** The items tipo movimiento. */
	private SelectItem[] itemsTipoMovimiento;
	
	/** The items tipo cargo. */
	private SelectItem[] itemsTipoCargo;
	
	/** The items company. */
	private SelectItem[] itemsCompany;
	
	/** The items numero cuenta. */
	private SelectItem[] itemsNumeroCuenta;
	
	/** The items centro costo. */
	private SelectItem[] itemsCentroCosto;
	
	/** The items linea. */
	private SelectItem[] itemsLinea;
	
	/** The items auxiliar. */
	private SelectItem[] itemsAuxiliar;
	
	/** The items tipo control. */
	private SelectItem[] itemsTipoControl;
	
	/** The items futuro. */
	private SelectItem[] itemsFuturo;
	
	/** The items descripcion. */
	private SelectItem[] itemsDescripcion;
	
	/** The items tipo doc uno. */
	private SelectItem[] itemsTipoDocUno;
	
	/** The items num doc uno. */
	private SelectItem[] itemsNumDocUno;
	
	/** The items tipo doc dos. */
	private SelectItem[] itemsTipoDocDos;
	
	/** The items num doc dos. */
	private SelectItem[] itemsNumDocDos;
	
	/** The items atributo. */
	private SelectItem[] itemsAtributo;
	
	/** The items tipo localizacion. */
	private SelectItem[] itemsTipoLocalizacion;
	
	private SelectItem[] itemsState;
	
	/** The lista parametros. */
	private List<AccountingParameters> listaParametros;
	
	/** The tbl parametros contables. */
	private HtmlDataTable tblParametrosContables;
	
	/** The show table parameters. */
	private boolean showTableParameters;

	/** The activar confirmacion. */
	private boolean activarConfirmacion;
	
	/** The opc confirmacion. */
	private Integer opcConfirmacion;
	
	/** The MODIFICAR. */
	private static Integer MODIFICAR = 1;
	
	/** The ELIMINAR. */
	private static Integer ELIMINAR = 2;

	/**
	 * Instantiates a new accounting parameters page.
	 */
	public AccountingParametersPage() {
		listaParametros = new ArrayList<AccountingParameters>();
	}

	/**
	 * Action_crear ap.
	 *
	 * @param event the event
	 */
	public void action_crearAP(ActionEvent event) {
		try {
			validarDatos();
			
			if(idState.longValue() == 1)
				this.setAcpState(true);
			else
				this.setAcpState(false);
			
			accountingParametersService.crearParametroContable(
					idTipoAsignacion, idTipoTransaccion, idTipoMovimiento,
					idTipoCargo, idCompany, idNumeroCuenta, idCentroCosto,
					idNumeroLinea, idAuxiliar, idTipoControl, idFuturo,
					idDescripcion, idTipoDocUno, idNumDocUno, idTipoDocDos,
					idNumDocDos, idAtributo, descripcion, idTipoLocalizacion,
					acpState);
			
			mostrarMensaje(Util.loadMessageValue("EXITO"), false);
			limpiar();
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	/**
	 * Action_modificar ap.
	 *
	 * @param event the event
	 */
	public void action_modificarAP(ActionEvent event) {
		try {
			validarDatos();
			setOpcConfirmacion(MODIFICAR);
			setActivarConfirmacion(true);
			mostrarMensaje(Util.loadMessageValue("MENSAJE.ALERTA"),
					activarConfirmacion);
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	/**
	 * Modificar ap.
	 */
	public void modificarAP() {
		try {
			if(idState.longValue() == 1)
				this.setAcpState(true);
			else
				this.setAcpState(false);
			
			accountingParametersService.modificarParametroContable(
					idParametroContable, idTipoAsignacion, idTipoTransaccion,
					idTipoMovimiento, idTipoCargo, idCompany, idNumeroCuenta,
					idCentroCosto, idNumeroLinea, idAuxiliar, idTipoControl,
					idFuturo, idDescripcion, idTipoDocUno, idNumDocUno,
					idTipoDocDos, idNumDocDos, idAtributo, descripcion, 
					idTipoLocalizacion, acpState);
			
			mostrarMensaje(Util.loadMessageValue("EXITO.MODIFICAR"), false);
			limpiar();
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	/**
	 * Action_consultar ap.
	 *
	 * @param event the event
	 */
	public void action_consultarAP(ActionEvent event) {
		try {

//			if ((idTipoAsignacion == null || idTipoAsignacion == -1L)
//					&& (idTipoTransaccion == null || idTipoTransaccion == -1L)
//					&& (idNumeroCuenta == null || idNumeroCuenta == -1L)
//					&& (idTipoMovimiento == null || idTipoMovimiento == -1L)) {
//				throw new GWorkException(Util
//						.loadErrorMessageValue("ERROR.DATOSVACIOSPC"));
//			}

			// Se traen los nuevos valores para ser cargados
			List<AccountingParameters> list = accountingParametersService
					.consultarParametroContable(idTipoAsignacion,
							idTipoTransaccion, idTipoMovimiento, idNumeroCuenta, 
							idTipoLocalizacion);
			
			setListaParametros(list);
			
			if (list == null || list.size() == 0) {
				throw new GWorkException(Util
						.loadErrorMessageValue("search.not.found"));
			}
			showTableParameters = true;
			setListaParametros(list);
			disabledCrear = true;
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	/**
	 * Action_eliminar ap.
	 *
	 * @param event the event
	 */
	public void action_eliminarAP(ActionEvent event) {
		try {
			if (tblParametrosContables.getRowCount() <= 0)
				return;

			if (tblParametrosContables.getRowData() != null) {
				AccountingParameters parameters = (AccountingParameters) tblParametrosContables
						.getRowData();
				setIdParametroContable(parameters.getAcpId());
			}

			setOpcConfirmacion(ELIMINAR);
			setActivarConfirmacion(true);
			mostrarMensaje(Util.loadMessageValue("MENSAJE.ALERTA"),
					activarConfirmacion);

		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	/**
	 * Eliminar parametro contable.
	 */
	public void eliminarParametroContable() {
		try {
			accountingParametersService
					.eliminarParametroContable(getIdParametroContable());
			mostrarMensaje(Util.loadMessageValue("EXITO.ELIMINAR"), false);
			limpiar();
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

	}

	/**
	 * Action_limpiar forma.
	 *
	 * @param event the event
	 */
	public void action_limpiarForma(ActionEvent event) {
		limpiar();
	}

	/**
	 * Cargar datos tabla.
	 */
	private void cargarDatosTabla() {
		if (tblParametrosContables.getRowData() != null) {
			AccountingParameters parameters = (AccountingParameters) tblParametrosContables
					.getRowData();
			try {

				setIdTipoAsignacion(parameters.getLegateesTypes()
						.getLgtCodigo());
				setIdTipoTransaccion(parameters.getTransactionType().getTstId());
				idParametroContable = (parameters.getAcpId());
				idTipoMovimiento = (parameters.getMovementType().getMvmId());
				
				if(parameters.getLocationsTypes() != null){
					idTipoLocalizacion = parameters.getLocationsTypes().getLctCodigo();
				}
				idTipoCargo = (parameters.getChargeType().getCgtId());
				idCompany = (parameters.getCompany().getCmpId());
				idNumeroCuenta = (parameters.getAccount().getAccId());
				idCentroCosto = (parameters.getCostsCenters().getCocCodigo());
				setIdNumeroLinea(parameters.getLine().getLinId());
				idAuxiliar = (parameters.getAuxiliar().getAuxId());
				idTipoControl = (parameters.getControlType().getCttId());
				idFuturo = (parameters.getFuture().getFreId());
				idDescripcion = (parameters.getDescriptionType().getDstId());

				idTipoDocUno = (parameters.getDocumentOne()
						.getDocumentOneType().getDotId());
				idNumDocUno = (parameters.getDocumentOne().getDcoId());
				idTipoDocDos = (parameters.getDocumentTwo()
						.getDocumentTwoType().getDttId());
				idNumDocDos = (parameters.getDocumentTwo().getDctId());
				idAtributo = (parameters.getAttribute().getAtbId());
				
				if(parameters.getAcpState())
					idState = 1L;
				else
					idState = 0L;
				
				setDescripcion(parameters.getAccDescripcion());

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Action_dibujar.
	 *
	 * @param event the event
	 */
	public void action_dibujar(ActionEvent event) {
		cargarDatosTabla();
		listaParametros.clear();
		showTableParameters = false;
	}

	/**
	 * Validar datos.
	 *
	 * @throws GWorkException the g work exception
	 */
	private void validarDatos() throws GWorkException {
		// Valida que se hayan seleccionado todas las opciones.
		if (idTipoTransaccion == null || idTipoTransaccion == -1L) {
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.SELCOMPROTRANS"));
		}

		if (idTipoAsignacion == null || idTipoAsignacion == -1L) {
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.SELTIPOASIGNACIONP"));
		}

		if (idTipoMovimiento == null || idTipoMovimiento == -1L) {
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.SELTIPOMOVIP"));
		}

		if (idTipoLocalizacion == null || idTipoLocalizacion == -1L) {
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.SELTIPO_LOCALIZACION"));
		}
		
		if (idTipoCargo == null || idTipoCargo == -1L) {
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.SELTIPOCARGO"));
		}

		if (idCompany == null || idCompany == -1L) {
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.SELCOMPANIA"));
		}

		if (idNumeroCuenta == null || idNumeroCuenta == -1L) {
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.SELCUENTA"));
		}

		if (idCentroCosto == null || idCentroCosto == -1L) {
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.SELCCAP"));
		}

		if (idNumeroLinea == null || idNumeroLinea == -1L) {
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.SELLINEAAP"));
		}

		if (idAuxiliar == null || idAuxiliar == -1L) {
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.SELAUXAP"));
		}

		if (idTipoControl == null || idTipoControl == -1L) {
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.SELTIPOCONTROLAP"));
		}

		if (idFuturo == null || idFuturo == -1L) {
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.SELFUTUROAP"));
		}

		if (idDescripcion == null || idDescripcion == -1L) {
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.SELDESCRIPAP"));
		}

		if (idTipoDocUno == null || idTipoDocUno == -1L) {
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.SELTIPODOCUNO"));
		}

		if (idNumDocUno == null || idNumDocUno == -1L) {
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.SELNUMDOCUNO"));
		}

		if (idTipoDocDos == null || idTipoDocDos == -1L) {
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.SELTIPODOCDOS"));
		}

		if (idNumDocDos == null || idNumDocDos == -1L) {
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.SELNUMDOCDOS"));
		}

		if (idAtributo == null || idAtributo == -1L) {
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.SELATRIBBAP"));
		}

		if (idState == null || idState == -1L) {
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.SELSTATE"));
		}
		
		if (!Util.validarCadenaCaracteres(descripcion)) {
			throw new GWorkException(Util
					.loadErrorMessageValue("CARACTER.ESPECIAL"));
		}
		
		if (descripcion.trim().length() > 200) {
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.PARAMETROSCONTABLES.TAMANO_DESCRIPCION"));
		}
	}

	/**
	 * Limpiar.
	 */
	private void limpiar() {
		idParametroContable = null;
		selectTipoAsignacion.setValue("-1");
		selectTipoTransaccion.setValue("-1");
		selectTipoMovimiento.setValue("-1");
		selectTipoCargo.setValue("-1");
		selectCompany.setValue("-1");
		selectNumero.setValue("-1");
		selectCentroCosto.setValue("-1");
		selectLinea.setValue("-1");
		selectAuxiliar.setValue("-1");
		selectTipoControl.setValue("-1");
		selectFuturo.setValue("-1");
		selectDescripcion.setValue("-1");
		selectTipoDocUno.setValue("-1");
		selectNumDocUno.setValue("-1");
		selectTipoDocDos.setValue("-1");
		selectNumDocDos.setValue("-1");
		selectAtributo.setValue("-1");
		selectTipoLocalizacion.setValue("-1");
		selectState.setValue("-1");
		setDescripcion("");
		disabledCrear = false;
		showTableParameters = false;
	}

	/**
	 * Mostrar mensaje.
	 *
	 * @param mensaje the mensaje
	 * @param buttonCancel the button cancel
	 */
	public void mostrarMensaje(String mensaje, boolean buttonCancel) {
		PopupMessagePage messagePage = (PopupMessagePage) FacesUtils
				.getManagedBean("popupMessagePage");
		if (messagePage != null) {
			messagePage.showPopup(mensaje, buttonCancel);
		}
	}

	/**
	 * Gets the accounting parameters service.
	 *
	 * @return the accounting parameters service
	 */
	public AccountingParametersService getAccountingParametersService() {
		return accountingParametersService;
	}

	/**
	 * Sets the accounting parameters service.
	 *
	 * @param accountingParametersService the new accounting parameters service
	 */
	public void setAccountingParametersService(
			AccountingParametersService accountingParametersService) {
		this.accountingParametersService = accountingParametersService;
	}

	/**
	 * Gets the id tipo asignacion.
	 *
	 * @return the id tipo asignacion
	 */
	public Long getIdTipoAsignacion() {
		return idTipoAsignacion;
	}

	/**
	 * Sets the id tipo asignacion.
	 *
	 * @param idTipoAsignacion the new id tipo asignacion
	 */
	public void setIdTipoAsignacion(Long idTipoAsignacion) {
		this.idTipoAsignacion = idTipoAsignacion;
	}

	/**
	 * Gets the id tipo transaccion.
	 *
	 * @return the id tipo transaccion
	 */
	public Long getIdTipoTransaccion() {
		return idTipoTransaccion;
	}

	/**
	 * Sets the id tipo transaccion.
	 *
	 * @param idTipoTransaccion the new id tipo transaccion
	 */
	public void setIdTipoTransaccion(Long idTipoTransaccion) {
		this.idTipoTransaccion = idTipoTransaccion;
	}

	/**
	 * Gets the id tipo movimiento.
	 *
	 * @return the id tipo movimiento
	 */
	public Long getIdTipoMovimiento() {
		return idTipoMovimiento;
	}

	/**
	 * Sets the id tipo movimiento.
	 *
	 * @param idTipoMovimiento the new id tipo movimiento
	 */
	public void setIdTipoMovimiento(Long idTipoMovimiento) {
		this.idTipoMovimiento = idTipoMovimiento;
	}

	/**
	 * Gets the select tipo asignacion.
	 *
	 * @return the select tipo asignacion
	 */
	public HtmlSelectOneMenu getSelectTipoAsignacion() {
		return selectTipoAsignacion;
	}

	/**
	 * Sets the select tipo asignacion.
	 *
	 * @param selectTipoAsignacion the new select tipo asignacion
	 */
	public void setSelectTipoAsignacion(HtmlSelectOneMenu selectTipoAsignacion) {
		this.selectTipoAsignacion = selectTipoAsignacion;
	}

	/**
	 * Gets the select tipo transaccion.
	 *
	 * @return the select tipo transaccion
	 */
	public HtmlSelectOneMenu getSelectTipoTransaccion() {
		return selectTipoTransaccion;
	}

	/**
	 * Sets the select tipo transaccion.
	 *
	 * @param selectTipoTransaccion the new select tipo transaccion
	 */
	public void setSelectTipoTransaccion(HtmlSelectOneMenu selectTipoTransaccion) {
		this.selectTipoTransaccion = selectTipoTransaccion;
	}

	/**
	 * Gets the select tipo movimiento.
	 *
	 * @return the select tipo movimiento
	 */
	public HtmlSelectOneMenu getSelectTipoMovimiento() {
		return selectTipoMovimiento;
	}

	/**
	 * Sets the select tipo movimiento.
	 *
	 * @param selectTipoMovimiento the new select tipo movimiento
	 */
	public void setSelectTipoMovimiento(HtmlSelectOneMenu selectTipoMovimiento) {
		this.selectTipoMovimiento = selectTipoMovimiento;
	}

	/**
	 * Gets the items tipo transaccion.
	 *
	 * @return the items tipo transaccion
	 */
	public SelectItem[] getItemsTipoTransaccion() {

		try {
			List<TransactionType> list = new SearchAccountingParameters()
					.consultarTransactionType();

			if (list == null || list.size() == 0) {
				itemsTipoTransaccion = new SelectItem[1];
			} else
				itemsTipoTransaccion = new SelectItem[list.size() + 1];

			itemsTipoTransaccion[0] = new SelectItem("-1", "--SELECCIONAR--");

			int i = 1;

			if (list == null) {
				throw new GWorkException(Util
						.loadErrorMessageValue("search.not.found"));
			}

			for (TransactionType transactionType : list) {
				itemsTipoTransaccion[i] = new SelectItem(transactionType
						.getTstId(), transactionType.getTstNombre());
				i++;
			}

		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
		return itemsTipoTransaccion;
	}

	/**
	 * Sets the items tipo transaccion.
	 *
	 * @param itemsTipoTransaccion the new items tipo transaccion
	 */
	public void setItemsTipoTransaccion(SelectItem[] itemsTipoTransaccion) {
		this.itemsTipoTransaccion = itemsTipoTransaccion;
	}

	/**
	 * Consulta los tipos de movimiento y los carga en un arreglo.
	 * 
	 * @return arreglo de objetos selectItem
	 */
	public SelectItem[] getItemsTipoMovimiento() {

		try {
			List<MovementType> list = new SearchAccountingParameters()
					.consultarMovementTypes();

			if (list == null || list.size() == 0) {
				itemsTipoMovimiento = new SelectItem[1];
			} else
				itemsTipoMovimiento = new SelectItem[list.size() + 1];

			itemsTipoMovimiento[0] = new SelectItem("-1", "--SELECCIONAR--");

			int i = 1;

			if (list == null) {
				throw new GWorkException(Util
						.loadErrorMessageValue("search.not.found"));
			}
			for (MovementType movementType : list) {
				itemsTipoMovimiento[i] = new SelectItem(
						movementType.getMvmId(), movementType.getMvmNombre());
				i++;
			}

		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
		return itemsTipoMovimiento;
	}

	/**
	 * Sets the items tipo movimiento.
	 *
	 * @param itemsTipoMovimiento the new items tipo movimiento
	 */
	public void setItemsTipoMovimiento(SelectItem[] itemsTipoMovimiento) {
		this.itemsTipoMovimiento = itemsTipoMovimiento;
	}

	/**
	 * Checks if is disabled crear.
	 *
	 * @return true, if is disabled crear
	 */
	public boolean isDisabledCrear() {
		return disabledCrear;
	}

	/**
	 * Sets the disabled crear.
	 *
	 * @param disabledCrear the new disabled crear
	 */
	public void setDisabledCrear(boolean disabledCrear) {
		this.disabledCrear = disabledCrear;
	}

	/**
	 * Gets the lista parametros.
	 *
	 * @return the lista parametros
	 */
	public List<AccountingParameters> getListaParametros() {
		return listaParametros;
	}

	/**
	 * Sets the lista parametros.
	 *
	 * @param listaParametros the new lista parametros
	 */
	public void setListaParametros(List<AccountingParameters> listaParametros) {
		this.listaParametros = listaParametros;
	}

	/**
	 * Gets the tbl parametros contables.
	 *
	 * @return the tbl parametros contables
	 */
	public HtmlDataTable getTblParametrosContables() {
		return tblParametrosContables;
	}

	/**
	 * Sets the tbl parametros contables.
	 *
	 * @param tblParametrosContables the new tbl parametros contables
	 */
	public void setTblParametrosContables(HtmlDataTable tblParametrosContables) {
		this.tblParametrosContables = tblParametrosContables;
	}

	/**
	 * Gets the id tipo cargo.
	 *
	 * @return the id tipo cargo
	 */
	public Long getIdTipoCargo() {
		return idTipoCargo;
	}

	/**
	 * Sets the id tipo cargo.
	 *
	 * @param idTipoCargo the new id tipo cargo
	 */
	public void setIdTipoCargo(Long idTipoCargo) {
		this.idTipoCargo = idTipoCargo;
	}

	/**
	 * Gets the select tipo cargo.
	 *
	 * @return the select tipo cargo
	 */
	public HtmlSelectOneMenu getSelectTipoCargo() {
		return selectTipoCargo;
	}

	/**
	 * Sets the select tipo cargo.
	 *
	 * @param selectTipoCargo the new select tipo cargo
	 */
	public void setSelectTipoCargo(HtmlSelectOneMenu selectTipoCargo) {
		this.selectTipoCargo = selectTipoCargo;
	}

	/**
	 * Gets the items tipo cargo.
	 *
	 * @return the items tipo cargo
	 */
	public SelectItem[] getItemsTipoCargo() {
		try {
			List<ChargeType> list = new SearchAccountingParameters()
					.consultarChargeType();

			if (list == null || list.size() == 0) {
				itemsTipoCargo = new SelectItem[1];
			} else
				itemsTipoCargo = new SelectItem[list.size() + 1];

			itemsTipoCargo[0] = new SelectItem("-1", "--SELECCIONAR--");

			int i = 1;

			if (list == null) {
				throw new GWorkException(Util
						.loadErrorMessageValue("search.not.found"));
			}
			for (ChargeType chargeType : list) {
				itemsTipoCargo[i] = new SelectItem(chargeType.getCgtId(),
						chargeType.getCgtNombre());
				i++;
			}

		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
		return itemsTipoCargo;
	}

	/**
	 * Sets the items tipo cargo.
	 *
	 * @param itemsTipoCargo the new items tipo cargo
	 */
	public void setItemsTipoCargo(SelectItem[] itemsTipoCargo) {
		this.itemsTipoCargo = itemsTipoCargo;
	}

	/**
	 * Gets the id company.
	 *
	 * @return the id company
	 */
	public Long getIdCompany() {
		return idCompany;
	}

	/**
	 * Sets the id company.
	 *
	 * @param idCompany the new id company
	 */
	public void setIdCompany(Long idCompany) {
		this.idCompany = idCompany;
	}

	/**
	 * Gets the select company.
	 *
	 * @return the select company
	 */
	public HtmlSelectOneMenu getSelectCompany() {
		return selectCompany;
	}

	/**
	 * Sets the select company.
	 *
	 * @param selectCompany the new select company
	 */
	public void setSelectCompany(HtmlSelectOneMenu selectCompany) {
		this.selectCompany = selectCompany;
	}

	/**
	 * Gets the items company.
	 *
	 * @return the items company
	 */
	public SelectItem[] getItemsCompany() {
		try {
			List<Company> list = new SearchAccountingParameters()
					.consultarCompany();

			if (list == null || list.size() == 0) {
				itemsCompany = new SelectItem[1];
			} else
				itemsCompany = new SelectItem[list.size() + 1];

			itemsCompany[0] = new SelectItem("-1", "--SELECCIONAR--");

			int i = 1;

			if (list == null) {
				throw new GWorkException(Util
						.loadErrorMessageValue("search.not.found"));
			}
			for (Company company : list) {
				itemsCompany[i] = new SelectItem(company.getCmpId(), company
						.getCmpNombre());
				i++;
			}

		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
		return itemsCompany;
	}

	/**
	 * Sets the items company.
	 *
	 * @param itemsCompany the new items company
	 */
	public void setItemsCompany(SelectItem[] itemsCompany) {
		this.itemsCompany = itemsCompany;
	}

	/**
	 * Gets the id numero cuenta.
	 *
	 * @return the id numero cuenta
	 */
	public Long getIdNumeroCuenta() {
		return idNumeroCuenta;
	}

	/**
	 * Sets the id numero cuenta.
	 *
	 * @param idNumeroCuenta the new id numero cuenta
	 */
	public void setIdNumeroCuenta(Long idNumeroCuenta) {
		this.idNumeroCuenta = idNumeroCuenta;
	}

	/**
	 * Gets the select numero.
	 *
	 * @return the select numero
	 */
	public HtmlSelectOneMenu getSelectNumero() {
		return selectNumero;
	}

	/**
	 * Sets the select numero.
	 *
	 * @param selectNumero the new select numero
	 */
	public void setSelectNumero(HtmlSelectOneMenu selectNumero) {
		this.selectNumero = selectNumero;
	}

	/**
	 * Gets the items numero cuenta.
	 *
	 * @return the items numero cuenta
	 */
	public SelectItem[] getItemsNumeroCuenta() {
		try {
			List<Account> list = new SearchAccountingParameters()
					.consultarAccount();

			if (list == null || list.size() == 0) {
				itemsNumeroCuenta = new SelectItem[1];
			} else
				itemsNumeroCuenta = new SelectItem[list.size() + 1];

			itemsNumeroCuenta[0] = new SelectItem("-1", "--SELECCIONAR--");

			int i = 1;

			if (list == null) {
				throw new GWorkException(Util
						.loadErrorMessageValue("search.not.found"));
			}
			for (Account account : list) {
				itemsNumeroCuenta[i] = new SelectItem(account.getAccId(),
						account.getAccNumeroCuenta());
				i++;
			}

		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
		return itemsNumeroCuenta;
	}

	/**
	 * Sets the items numero cuenta.
	 *
	 * @param itemsNumeroCuenta the new items numero cuenta
	 */
	public void setItemsNumeroCuenta(SelectItem[] itemsNumeroCuenta) {
		this.itemsNumeroCuenta = itemsNumeroCuenta;
	}

	/**
	 * Gets the id numero linea.
	 *
	 * @return the id numero linea
	 */
	public Long getIdNumeroLinea() {
		return idNumeroLinea;
	}

	/**
	 * Sets the id numero linea.
	 *
	 * @param idNumeroLinea the new id numero linea
	 */
	public void setIdNumeroLinea(Long idNumeroLinea) {
		this.idNumeroLinea = idNumeroLinea;
	}

	/**
	 * Gets the select linea.
	 *
	 * @return the select linea
	 */
	public HtmlSelectOneMenu getSelectLinea() {
		return selectLinea;
	}

	/**
	 * Sets the select linea.
	 *
	 * @param selectLinea the new select linea
	 */
	public void setSelectLinea(HtmlSelectOneMenu selectLinea) {
		this.selectLinea = selectLinea;
	}

	/**
	 * Gets the items linea.
	 *
	 * @return the items linea
	 */
	public SelectItem[] getItemsLinea() {
		try {
			List<Line> list = new SearchAccountingParameters().consultarLine();

			if (list == null || list.size() == 0) {
				itemsLinea = new SelectItem[1];
			} else
				itemsLinea = new SelectItem[list.size() + 1];

			itemsLinea[0] = new SelectItem("-1", "--SELECCIONAR--");

			int i = 1;

			if (list == null) {
				throw new GWorkException(Util
						.loadErrorMessageValue("search.not.found"));
			}
			for (Line line : list) {
				itemsLinea[i] = new SelectItem(line.getLinId(), line
						.getLinValor());
				i++;
			}

		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
		return itemsLinea;
	}

	/**
	 * Sets the items linea.
	 *
	 * @param itemsLinea the new items linea
	 */
	public void setItemsLinea(SelectItem[] itemsLinea) {
		this.itemsLinea = itemsLinea;
	}

	/**
	 * Gets the id centro costo.
	 *
	 * @return the id centro costo
	 */
	public Long getIdCentroCosto() {
		return idCentroCosto;
	}

	/**
	 * Sets the id centro costo.
	 *
	 * @param idCentroCosto the new id centro costo
	 */
	public void setIdCentroCosto(Long idCentroCosto) {
		this.idCentroCosto = idCentroCosto;
	}

	/**
	 * Gets the select centro costo.
	 *
	 * @return the select centro costo
	 */
	public HtmlSelectOneMenu getSelectCentroCosto() {
		return selectCentroCosto;
	}

	/**
	 * Sets the select centro costo.
	 *
	 * @param selectCentroCosto the new select centro costo
	 */
	public void setSelectCentroCosto(HtmlSelectOneMenu selectCentroCosto) {
		this.selectCentroCosto = selectCentroCosto;
	}

	/**
	 * Gets the items centro costo.
	 *
	 * @return the items centro costo
	 */
	public SelectItem[] getItemsCentroCosto() {
		try {
			List<CostsCenters> list = new SearchAccountingParameters()
					.consultarCostCenters();

			if (list == null || list.size() == 0) {
				itemsCentroCosto = new SelectItem[1];
			} else
				itemsCentroCosto = new SelectItem[list.size() + 1];

			itemsCentroCosto[0] = new SelectItem("-1", "--SELECCIONAR--");

			int i = 1;

			if (list == null) {
				throw new GWorkException(Util
						.loadErrorMessageValue("search.not.found"));
			}
			for (CostsCenters costsCenters : list) {
				itemsCentroCosto[i] = new SelectItem(costsCenters
						.getCocCodigo(), costsCenters.getCocNumero());
				i++;
			}

		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
		return itemsCentroCosto;
	}

	/**
	 * Sets the items centro costo.
	 *
	 * @param itemsCentroCosto the new items centro costo
	 */
	public void setItemsCentroCosto(SelectItem[] itemsCentroCosto) {
		this.itemsCentroCosto = itemsCentroCosto;
	}

	/**
	 * Gets the id auxiliar.
	 *
	 * @return the id auxiliar
	 */
	public Long getIdAuxiliar() {
		return idAuxiliar;
	}

	/**
	 * Sets the id auxiliar.
	 *
	 * @param idAuxiliar the new id auxiliar
	 */
	public void setIdAuxiliar(Long idAuxiliar) {
		this.idAuxiliar = idAuxiliar;
	}

	/**
	 * Gets the select auxiliar.
	 *
	 * @return the select auxiliar
	 */
	public HtmlSelectOneMenu getSelectAuxiliar() {
		return selectAuxiliar;
	}

	/**
	 * Sets the select auxiliar.
	 *
	 * @param selectAuxiliar the new select auxiliar
	 */
	public void setSelectAuxiliar(HtmlSelectOneMenu selectAuxiliar) {
		this.selectAuxiliar = selectAuxiliar;
	}

	/**
	 * Gets the items auxiliar.
	 *
	 * @return the items auxiliar
	 */
	public SelectItem[] getItemsAuxiliar() {
		try {
			List<Auxiliar> list = new SearchAccountingParameters()
					.consultarAuxiliares();

			if (list == null || list.size() == 0) {
				itemsAuxiliar = new SelectItem[1];
			} else
				itemsAuxiliar = new SelectItem[list.size() + 1];

			itemsAuxiliar[0] = new SelectItem("-1", "--SELECCIONAR--");

			int i = 1;

			if (list == null) {
				throw new GWorkException(Util
						.loadErrorMessageValue("search.not.found"));
			}
			for (Auxiliar auxiliar : list) {
				itemsAuxiliar[i] = new SelectItem(auxiliar.getAuxId(), auxiliar
						.getAuxValor());
				i++;
			}

		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
		return itemsAuxiliar;
	}

	/**
	 * Sets the items auxiliar.
	 *
	 * @param itemsAuxiliar the new items auxiliar
	 */
	public void setItemsAuxiliar(SelectItem[] itemsAuxiliar) {
		this.itemsAuxiliar = itemsAuxiliar;
	}

	/**
	 * Gets the id tipo control.
	 *
	 * @return the id tipo control
	 */
	public Long getIdTipoControl() {
		return idTipoControl;
	}

	/**
	 * Sets the id tipo control.
	 *
	 * @param idTipoControl the new id tipo control
	 */
	public void setIdTipoControl(Long idTipoControl) {
		this.idTipoControl = idTipoControl;
	}

	/**
	 * Gets the id futuro.
	 *
	 * @return the id futuro
	 */
	public Long getIdFuturo() {
		return idFuturo;
	}

	/**
	 * Sets the id futuro.
	 *
	 * @param idFuturo the new id futuro
	 */
	public void setIdFuturo(Long idFuturo) {
		this.idFuturo = idFuturo;
	}

	/**
	 * Gets the id descripcion.
	 *
	 * @return the id descripcion
	 */
	public Long getIdDescripcion() {
		return idDescripcion;
	}

	/**
	 * Sets the id descripcion.
	 *
	 * @param idDescripcion the new id descripcion
	 */
	public void setIdDescripcion(Long idDescripcion) {
		this.idDescripcion = idDescripcion;
	}

	/**
	 * Gets the id tipo doc uno.
	 *
	 * @return the id tipo doc uno
	 */
	public Long getIdTipoDocUno() {
		return idTipoDocUno;
	}

	/**
	 * Sets the id tipo doc uno.
	 *
	 * @param idTipoDocUno the new id tipo doc uno
	 */
	public void setIdTipoDocUno(Long idTipoDocUno) {
		this.idTipoDocUno = idTipoDocUno;
	}

	/**
	 * Gets the id num doc uno.
	 *
	 * @return the id num doc uno
	 */
	public Long getIdNumDocUno() {
		return idNumDocUno;
	}

	/**
	 * Sets the id num doc uno.
	 *
	 * @param idNumDocUno the new id num doc uno
	 */
	public void setIdNumDocUno(Long idNumDocUno) {
		this.idNumDocUno = idNumDocUno;
	}

	/**
	 * Gets the id tipo doc dos.
	 *
	 * @return the id tipo doc dos
	 */
	public Long getIdTipoDocDos() {
		return idTipoDocDos;
	}

	/**
	 * Sets the id tipo doc dos.
	 *
	 * @param idTipoDocDos the new id tipo doc dos
	 */
	public void setIdTipoDocDos(Long idTipoDocDos) {
		this.idTipoDocDos = idTipoDocDos;
	}

	/**
	 * Gets the id num doc dos.
	 *
	 * @return the id num doc dos
	 */
	public Long getIdNumDocDos() {
		return idNumDocDos;
	}

	/**
	 * Sets the id num doc dos.
	 *
	 * @param idNumDocDos the new id num doc dos
	 */
	public void setIdNumDocDos(Long idNumDocDos) {
		this.idNumDocDos = idNumDocDos;
	}

	/**
	 * Gets the select tipo control.
	 *
	 * @return the select tipo control
	 */
	public HtmlSelectOneMenu getSelectTipoControl() {
		return selectTipoControl;
	}

	/**
	 * Sets the select tipo control.
	 *
	 * @param selectTipoControl the new select tipo control
	 */
	public void setSelectTipoControl(HtmlSelectOneMenu selectTipoControl) {
		this.selectTipoControl = selectTipoControl;
	}

	/**
	 * Gets the select futuro.
	 *
	 * @return the select futuro
	 */
	public HtmlSelectOneMenu getSelectFuturo() {
		return selectFuturo;
	}

	/**
	 * Sets the select futuro.
	 *
	 * @param selectFuturo the new select futuro
	 */
	public void setSelectFuturo(HtmlSelectOneMenu selectFuturo) {
		this.selectFuturo = selectFuturo;
	}

	/**
	 * Gets the select descripcion.
	 *
	 * @return the select descripcion
	 */
	public HtmlSelectOneMenu getSelectDescripcion() {
		return selectDescripcion;
	}

	/**
	 * Sets the select descripcion.
	 *
	 * @param selectDescripcion the new select descripcion
	 */
	public void setSelectDescripcion(HtmlSelectOneMenu selectDescripcion) {
		this.selectDescripcion = selectDescripcion;
	}

	/**
	 * Gets the select tipo doc uno.
	 *
	 * @return the select tipo doc uno
	 */
	public HtmlSelectOneMenu getSelectTipoDocUno() {
		return selectTipoDocUno;
	}

	/**
	 * Sets the select tipo doc uno.
	 *
	 * @param selectTipoDocUno the new select tipo doc uno
	 */
	public void setSelectTipoDocUno(HtmlSelectOneMenu selectTipoDocUno) {
		this.selectTipoDocUno = selectTipoDocUno;
	}

	/**
	 * Gets the select num doc uno.
	 *
	 * @return the select num doc uno
	 */
	public HtmlSelectOneMenu getSelectNumDocUno() {
		return selectNumDocUno;
	}

	/**
	 * Sets the select num doc uno.
	 *
	 * @param selectNumDocUno the new select num doc uno
	 */
	public void setSelectNumDocUno(HtmlSelectOneMenu selectNumDocUno) {
		this.selectNumDocUno = selectNumDocUno;
	}

	/**
	 * Gets the select tipo doc dos.
	 *
	 * @return the select tipo doc dos
	 */
	public HtmlSelectOneMenu getSelectTipoDocDos() {
		return selectTipoDocDos;
	}

	/**
	 * Sets the select tipo doc dos.
	 *
	 * @param selectTipoDocDos the new select tipo doc dos
	 */
	public void setSelectTipoDocDos(HtmlSelectOneMenu selectTipoDocDos) {
		this.selectTipoDocDos = selectTipoDocDos;
	}

	/**
	 * Gets the select num doc dos.
	 *
	 * @return the select num doc dos
	 */
	public HtmlSelectOneMenu getSelectNumDocDos() {
		return selectNumDocDos;
	}

	/**
	 * Sets the select num doc dos.
	 *
	 * @param selectNumDocDos the new select num doc dos
	 */
	public void setSelectNumDocDos(HtmlSelectOneMenu selectNumDocDos) {
		this.selectNumDocDos = selectNumDocDos;
	}

	/**
	 * Gets the items tipo control.
	 *
	 * @return the items tipo control
	 */
	public SelectItem[] getItemsTipoControl() {
		try {
			List<ControlType> list = new SearchAccountingParameters()
					.consultarControlType();

			if (list == null || list.size() == 0) {
				itemsTipoControl = new SelectItem[1];
			} else
				itemsTipoControl = new SelectItem[list.size() + 1];

			itemsTipoControl[0] = new SelectItem("-1", "--SELECCIONAR--");

			int i = 1;

			if (list == null) {
				throw new GWorkException(Util
						.loadErrorMessageValue("search.not.found"));
			}
			for (ControlType controlType : list) {
				itemsTipoControl[i] = new SelectItem(controlType.getCttId(),
						controlType.getCttValor());
				i++;
			}

		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
		return itemsTipoControl;
	}

	/**
	 * Sets the items tipo control.
	 *
	 * @param itemsTipoControl the new items tipo control
	 */
	public void setItemsTipoControl(SelectItem[] itemsTipoControl) {
		this.itemsTipoControl = itemsTipoControl;
	}

	/**
	 * Gets the items futuro.
	 *
	 * @return the items futuro
	 */
	public SelectItem[] getItemsFuturo() {
		try {
			List<Future> list = new SearchAccountingParameters()
					.consultarFuture();

			if (list == null || list.size() == 0) {
				itemsFuturo = new SelectItem[1];
			} else
				itemsFuturo = new SelectItem[list.size() + 1];

			itemsFuturo[0] = new SelectItem("-1", "--SELECCIONAR--");

			int i = 1;

			if (list == null) {
				throw new GWorkException(Util
						.loadErrorMessageValue("search.not.found"));
			}
			for (Future future : list) {
				itemsFuturo[i] = new SelectItem(future.getFreId(), future
						.getFreValor());
				i++;
			}

		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
		return itemsFuturo;
	}

	/**
	 * Sets the items futuro.
	 *
	 * @param itemsFuturo the new items futuro
	 */
	public void setItemsFuturo(SelectItem[] itemsFuturo) {
		this.itemsFuturo = itemsFuturo;
	}

	/**
	 * Gets the items descripcion.
	 *
	 * @return the items descripcion
	 */
	public SelectItem[] getItemsDescripcion() {
		try {
			List<DescriptionType> list = new SearchAccountingParameters()
					.consultarDescriptionType();

			if (list == null || list.size() == 0) {
				itemsDescripcion = new SelectItem[1];
			} else
				itemsDescripcion = new SelectItem[list.size() + 1];

			itemsDescripcion[0] = new SelectItem("-1", "--SELECCIONAR--");

			int i = 1;

			if (list == null) {
				throw new GWorkException(Util
						.loadErrorMessageValue("search.not.found"));
			}
			for (DescriptionType descriptionType : list) {
				itemsDescripcion[i] = new SelectItem(
						descriptionType.getDstId(), descriptionType
								.getDstValor());
				i++;
			}

		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
		return itemsDescripcion;
	}

	/**
	 * Sets the items descripcion.
	 *
	 * @param itemsDescripcion the new items descripcion
	 */
	public void setItemsDescripcion(SelectItem[] itemsDescripcion) {
		this.itemsDescripcion = itemsDescripcion;
	}

	/**
	 * Gets the items tipo doc uno.
	 *
	 * @return the items tipo doc uno
	 */
	public SelectItem[] getItemsTipoDocUno() {
		try {
			List<DocumentOneType> list = new SearchAccountingParameters()
					.consultarDocumentOneType();

			if (list == null || list.size() == 0) {
				itemsTipoDocUno = new SelectItem[1];
			} else
				itemsTipoDocUno = new SelectItem[list.size() + 1];
			itemsTipoDocUno[0] = new SelectItem("-1", "--SELECCIONAR--");

			int i = 1;

			if (list == null) {
				throw new GWorkException(Util
						.loadErrorMessageValue("search.not.found"));
			}
			for (DocumentOneType documentOneType : list) {
				itemsTipoDocUno[i] = new SelectItem(documentOneType.getDotId(),
						documentOneType.getDotName());
				i++;
			}

		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
		return itemsTipoDocUno;
	}

	/**
	 * Sets the items tipo doc uno.
	 *
	 * @param itemsTipoDocUno the new items tipo doc uno
	 */
	public void setItemsTipoDocUno(SelectItem[] itemsTipoDocUno) {
		this.itemsTipoDocUno = itemsTipoDocUno;
	}

	/**
	 * Gets the items num doc uno.
	 *
	 * @return the items num doc uno
	 */
	public SelectItem[] getItemsNumDocUno() {
		try {
			List<DocumentOne> list = new SearchAccountingParameters()
					.consultarDocumentOne();

			if (list == null || list.size() == 0) {
				itemsNumDocUno = new SelectItem[1];
			} else
				itemsNumDocUno = new SelectItem[list.size() + 1];

			itemsNumDocUno[0] = new SelectItem("-1", "--SELECCIONAR--");

			int i = 1;

			if (list == null) {
				throw new GWorkException(Util
						.loadErrorMessageValue("search.not.found"));
			}
			for (DocumentOne documentOne : list) {
				itemsNumDocUno[i] = new SelectItem(documentOne.getDcoId(),
						documentOne.getDcoNumero());
				i++;
			}

		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
		return itemsNumDocUno;
	}

	/**
	 * Sets the items num doc uno.
	 *
	 * @param itemsNumDocUno the new items num doc uno
	 */
	public void setItemsNumDocUno(SelectItem[] itemsNumDocUno) {
		this.itemsNumDocUno = itemsNumDocUno;
	}

	/**
	 * Gets the items tipo doc dos.
	 *
	 * @return the items tipo doc dos
	 */
	public SelectItem[] getItemsTipoDocDos() {
		try {
			List<DocumentTwoType> list = new SearchAccountingParameters()
					.consultarDocumentTwoType();

			if (list == null || list.size() == 0) {
				itemsTipoDocDos = new SelectItem[1];
			} else
				itemsTipoDocDos = new SelectItem[list.size() + 1];

			itemsTipoDocDos[0] = new SelectItem("-1", "--SELECCIONAR--");

			int i = 1;

			if (list == null) {
				throw new GWorkException(Util
						.loadErrorMessageValue("search.not.found"));
			}

			for (DocumentTwoType documentTwoType : list) {
				itemsTipoDocDos[i] = new SelectItem(documentTwoType.getDttId(),
						documentTwoType.getDttName());
				i++;
			}

		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
		return itemsTipoDocDos;
	}

	/**
	 * Sets the items tipo doc dos.
	 *
	 * @param itemsTipoDocDos the new items tipo doc dos
	 */
	public void setItemsTipoDocDos(SelectItem[] itemsTipoDocDos) {
		this.itemsTipoDocDos = itemsTipoDocDos;
	}

	/**
	 * Gets the items num doc dos.
	 *
	 * @return the items num doc dos
	 */
	public SelectItem[] getItemsNumDocDos() {

		try {
			List<DocumentTwo> list = new SearchAccountingParameters()
					.consultarDocumentTwo();

			if (list == null || list.size() == 0) {
				itemsNumDocDos = new SelectItem[1];
			} else
				itemsNumDocDos = new SelectItem[list.size() + 1];

			itemsNumDocDos[0] = new SelectItem("-1", "--SELECCIONAR--");

			int i = 1;

			if (list == null) {
				throw new GWorkException(Util
						.loadErrorMessageValue("search.not.found"));
			}

			for (DocumentTwo documentTwo : list) {
				itemsNumDocDos[i] = new SelectItem(documentTwo.getDctId(),
						documentTwo.getDctNumero());
				i++;
			}

		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return itemsNumDocDos;
	}

	/**
	 * Sets the items num doc dos.
	 *
	 * @param itemsNumDocDos the new items num doc dos
	 */
	public void setItemsNumDocDos(SelectItem[] itemsNumDocDos) {
		this.itemsNumDocDos = itemsNumDocDos;
	}

	/**
	 * Gets the id atributo.
	 *
	 * @return the id atributo
	 */
	public Long getIdAtributo() {
		return idAtributo;
	}

	/**
	 * Sets the id atributo.
	 *
	 * @param idAtributo the new id atributo
	 */
	public void setIdAtributo(Long idAtributo) {
		this.idAtributo = idAtributo;
	}

	/**
	 * Gets the select atributo.
	 *
	 * @return the select atributo
	 */
	public HtmlSelectOneMenu getSelectAtributo() {
		return selectAtributo;
	}

	/**
	 * Sets the select atributo.
	 *
	 * @param selectAtributo the new select atributo
	 */
	public void setSelectAtributo(HtmlSelectOneMenu selectAtributo) {
		this.selectAtributo = selectAtributo;
	}

	/**
	 * Gets the items atributo.
	 *
	 * @return the items atributo
	 */
	public SelectItem[] getItemsAtributo() {
		try {
			List<Attribute> list = new SearchAccountingParameters()
					.consultarAttribute();

			if (list == null || list.size() == 0) {
				itemsAtributo = new SelectItem[1];
			} else
				itemsAtributo = new SelectItem[list.size() + 1];

			itemsAtributo[0] = new SelectItem("-1", "--SELECCIONAR--");

			int i = 1;

			if (list == null) {
				throw new GWorkException(Util
						.loadErrorMessageValue("search.not.found"));
			}

			for (Attribute attribute : list) {
				itemsAtributo[i] = new SelectItem(attribute.getAtbId(),
						attribute.getAtbValor());
				i++;
			}

		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return itemsAtributo;
	}

	/**
	 * Sets the items atributo.
	 *
	 * @param itemsAtributo the new items atributo
	 */
	public void setItemsAtributo(SelectItem[] itemsAtributo) {
		this.itemsAtributo = itemsAtributo;
	}

	/**
	 * Checks if is show table parameters.
	 *
	 * @return true, if is show table parameters
	 */
	public boolean isShowTableParameters() {
		return showTableParameters;
	}

	/**
	 * Sets the show table parameters.
	 *
	 * @param showTableParameters the new show table parameters
	 */
	public void setShowTableParameters(boolean showTableParameters) {
		this.showTableParameters = showTableParameters;
	}

	/**
	 * Gets the descripcion.
	 *
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Sets the descripcion.
	 *
	 * @param descripcion the new descripcion
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Gets the id parametro contable.
	 *
	 * @return the id parametro contable
	 */
	public Long getIdParametroContable() {
		return idParametroContable;
	}

	/**
	 * Sets the id parametro contable.
	 *
	 * @param idParametroContable the new id parametro contable
	 */
	public void setIdParametroContable(Long idParametroContable) {
		this.idParametroContable = idParametroContable;
	}

	/**
	 * Checks if is activar confirmacion.
	 *
	 * @return true, if is activar confirmacion
	 */
	public boolean isActivarConfirmacion() {
		return activarConfirmacion;
	}

	/**
	 * Sets the activar confirmacion.
	 *
	 * @param activarConfirmacion the new activar confirmacion
	 */
	public void setActivarConfirmacion(boolean activarConfirmacion) {
		this.activarConfirmacion = activarConfirmacion;
	}

	/**
	 * Gets the opc confirmacion.
	 *
	 * @return the opc confirmacion
	 */
	public Integer getOpcConfirmacion() {
		return opcConfirmacion;
	}

	/**
	 * Sets the opc confirmacion.
	 *
	 * @param opcConfirmacion the new opc confirmacion
	 */
	public void setOpcConfirmacion(Integer opcConfirmacion) {
		this.opcConfirmacion = opcConfirmacion;
	}

	/**
	 * Gets the id tipo localizacion.
	 *
	 * @return the id tipo localizacion
	 */
	public Long getIdTipoLocalizacion() {
		return idTipoLocalizacion;
	}

	/**
	 * Sets the id tipo localizacion.
	 *
	 * @param idTipoLocalizacion the new id tipo localizacion
	 */
	public void setIdTipoLocalizacion(Long idTipoLocalizacion) {
		this.idTipoLocalizacion = idTipoLocalizacion;
	}

	/**
	 * Gets the select tipo localizacion.
	 *
	 * @return the select tipo localizacion
	 */
	public HtmlSelectOneMenu getSelectTipoLocalizacion() {
		return selectTipoLocalizacion;
	}

	/**
	 * Sets the select tipo localizacion.
	 *
	 * @param selectTipoLocalizacion the new select tipo localizacion
	 */
	public void setSelectTipoLocalizacion(HtmlSelectOneMenu selectTipoLocalizacion) {
		this.selectTipoLocalizacion = selectTipoLocalizacion;
	}

	/**
	 * Gets the items tipo localizacion.
	 *
	 * @return the items tipo localizacion
	 */
	public SelectItem[] getItemsTipoLocalizacion() {
		try {
			List<LocationsTypes> list = new SearchAccountingParameters()
					.consultarLocationsTypes();

			if (list == null || list.size() == 0) {
				itemsTipoLocalizacion = new SelectItem[1];
			} else
				itemsTipoLocalizacion = new SelectItem[list.size() + 1];

			itemsTipoLocalizacion[0] = new SelectItem("-1", "--SELECCIONAR--");

			int i = 1;

			if (list == null) {
				throw new GWorkException(Util
						.loadErrorMessageValue("search.not.found"));
			}

			for (LocationsTypes tipoLocalizacion : list) {
				itemsTipoLocalizacion[i] = new SelectItem(tipoLocalizacion.getLctCodigo(),
						tipoLocalizacion.getLctNombre());
				i++;
			}

		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return itemsTipoLocalizacion;
	}

	/**
	 * Sets the items tipo localizacion.
	 *
	 * @param itemsTipoLocalizacion the new items tipo localizacion
	 */
	public void setItemsTipoLocalizacion(SelectItem[] itemsTipoLocalizacion) {
		this.itemsTipoLocalizacion = itemsTipoLocalizacion;
	}

	public Long getIdState() {
		return idState;
	}

	public void setIdState(Long idState) {
		this.idState = idState;
	}

	public HtmlSelectOneMenu getSelectState() {
		return selectState;
	}

	public void setSelectState(HtmlSelectOneMenu selectState) {
		this.selectState = selectState;
	}

	public SelectItem[] getItemsState() {
		try {
			itemsState = new SelectItem[3];
			itemsState[0] = new SelectItem("-1", "--SELECCIONAR--");
			itemsState[1] = new SelectItem("1", "ACTIVO");
			itemsState[2] = new SelectItem("0", "INACTIVO");
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
		return itemsState;
	}

	public void setItemsState(SelectItem[] itemsState) {
		this.itemsState = itemsState;
	}

	public Boolean getAcpState() {
		return acpState;
	}

	public void setAcpState(Boolean acpState) {
		this.acpState = acpState;
	}
}
