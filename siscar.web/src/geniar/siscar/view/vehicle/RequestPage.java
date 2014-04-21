package geniar.siscar.view.vehicle;

import geniar.siscar.consults.ConsultsService;
import geniar.siscar.logic.vehicle.services.RequestService;
import geniar.siscar.model.CostsCenters;
import geniar.siscar.model.CostsCentersVehicles;
import geniar.siscar.model.Requests;
import geniar.siscar.model.Users;
import geniar.siscar.model.VehiclesAssignation;
import geniar.siscar.util.FacesUtils;
import geniar.siscar.util.NavigationResults;
import geniar.siscar.util.ParametersUtil;
import geniar.siscar.util.PopupMessagePage;
import geniar.siscar.util.Util;
import geniar.siscar.util.ViewOptionUtil;
import geniar.siscar.view.autenticate.LoginPage;
import geniar.siscar.view.newness.RegistryNewnessCostCenterPage;
import geniar.siscar.view.newness.RegistryNewnessCostCenterPageFuel;
import gwork.exception.GWorkException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.icesoft.faces.component.ext.HtmlInputText;
import com.icesoft.faces.component.ext.HtmlOutputText;
import com.icesoft.faces.component.ext.HtmlSelectOneMenu;
import com.icesoft.faces.component.ext.RowSelectorEvent;

/**
 * The Class RequestPage.
 */
public class RequestPage {

	/** The id requests. */
	private Long idRequests;

	/** The requests types. */
	private Long requestsTypes;

	/** The legatees types. */
	private Long legateesTypes;

	/** The vehicles types. */
	private Long vehiclesTypes;

	/** The requests classes. */
	private Long requestsClasses;

	/** The id zones. */
	private Long idZones;

	/** The nombre asig. */
	private String nombreAsig;

	/** The email asig. */
	private String emailAsig;

	/** The nombre asis. */
	private String nombreAsis;

	/** The email asis. */
	private String emailAsis;

	/** The nombre tercero. */
	private String nombreTercero;

	/** The rqs descripcion. */
	private String rqsDescripcion;

	/** The rqs carnet empleado. */
	private String rqsCarnetEmpleado;

	/** The rqs carnet asignatario. */
	private String rqsCarnetAsignatario;

	/** The rqs fecha inicial. */
	private Date rqsFechaInicial;

	/** The rqs fecha final. */
	private Date rqsFechaFinal;

	/** The rqs nit. */
	private String rqsNit;

	/** The coc numero. */
	private String cocNumero;

	/** The ccr porcentaje. */
	private String ccrPorcentaje;

	/** The ccr valor. */
	private Long ccrValor;

	/** The ccr fecha inicio. */
	private Date ccrFechaInicio;

	/** The ccr fecha fin. */
	private Date ccrFechaFin;

	/** The placa. */
	private String placa;

	/** The sb codigo centro costo. */
	private String sbCodigoCentroCosto;

	/** The l porcentaje centro costo. */
	private String lPorcentajeCentroCosto;

	/** The parametro busquedad. */
	private String parametroBusquedad;

	/** The parametro centro costos. */
	private String parametroCentroCostos;

	/** The opc find empleado. */
	private Long opcFindEmpleado;

	/** The id user. */
	private HtmlOutputText idUser;

	/** The id auxiliares. */
	private HtmlOutputText idAuxiliares;

	/** The id cost centers. */
	private HtmlOutputText idCostCenters;

	/** The txt placa. */
	private HtmlInputText txtPlaca;

	/** The request service. */
	private RequestService requestService;

	/** The consults service. */
	private ConsultsService consultsService;

	/** The txt zona. */
	private HtmlOutputText txtZona;

	/** The cbx zones. */
	private HtmlSelectOneMenu cbxZones;

	/** The component. */
	private String component;

	/** The activar confirmacion. */
	private boolean activarConfirmacion;

	/** The show confirm request. */
	private boolean showConfirmRequest = false;

	/** The show btn cancelar. */
	private boolean showBtnCancelar = false;

	/** The style class. */
	private String styleClass;

	/** The siscar not null. */
	private String siscarNotNull = "";

	/** The confirmar solicitud. */
	private String confirmarSolicitud;

	/** The advertencia alquiler. */
	private String advertenciaAlquiler;

	/** The txt cod centro costo. */
	private HtmlInputText txtCodCentroCosto;

	/** The txt porcentaje centro costo. */
	private HtmlInputText txtPorcentajeCentroCosto;

	/** The show no placa. */
	private boolean showNoPlaca = false;

	/** The show ofs. */
	private boolean showOFS = false;

	/** The show cost center. */
	private boolean showCostCenter = false;

	/** The show tercero. */
	private boolean showTercero = false;

	/** The show employees. */
	private boolean showEmployees = false;

	/** The show auxiliaries. */
	private boolean showAuxiliaries = false;

	/** The show cost centers table. */
	private boolean showCostCentersTable = false;

	/** The show zones. */
	private boolean showZones = false;

	/** The disabled asignacion. */
	private boolean disabledAsignacion = false;

	/** The html id request. */
	private HtmlOutputText htmlIdRequest;

	/** The html select tipo asig. */
	private HtmlSelectOneMenu htmlSelectTipoAsig;

	/** The html select request class. */
	private HtmlSelectOneMenu htmlSelectRequestClass;

	/** The cbx tip solicitud. */
	private HtmlSelectOneMenu cbxTipSolicitud;

	/** The list costs centers requests. */
	private List<CostsCentersVehicles> listCostsCentersRequests;

	/** The cost centers table. */
	private List<CostsCenters> costCentersTable;

	/** The list users. */
	private List<Users> listUsers;

	/** The message page. */
	private PopupMessagePage messagePage = (PopupMessagePage) FacesUtils
			.getManagedBean("popupMessagePage");

	/** The login page. */
	private LoginPage loginPage = (LoginPage) FacesUtils
			.getManagedBean("loginPage");

	/** The Constant log. */
	private static final Log log = LogFactory.getLog(RequestPage.class);

	/**
	 * Instantiates a new request page.
	 */
	public RequestPage() {

		modificarSolicitud();

	}

	/**
	 * Checks if is show no placa.
	 * 
	 * @return true, if is show no placa
	 */
	public boolean isShowNoPlaca() {
		return showNoPlaca;
	}

	/**
	 * Sets the show no placa.
	 * 
	 * @param showNoPlaca
	 *            the new show no placa
	 */
	public void setShowNoPlaca(boolean showNoPlaca) {
		this.showNoPlaca = showNoPlaca;
	}

	/**
	 * Action_clean_asignatario.
	 * 
	 * @param actionEvent
	 *            the action event
	 */
	public void action_clean_asignatario(ActionEvent actionEvent) {
		nombreAsig = "";
		rqsCarnetAsignatario = "";
		emailAsig = "";
	}

	/**
	 * Action_clean_asistente.
	 * 
	 * @param actionEvent
	 *            the action event
	 */
	public void action_clean_asistente(ActionEvent actionEvent) {
		nombreAsis = "";
		rqsCarnetEmpleado = "";
		emailAsis = "";
	}

	/**
	 * Action_open confirmation request.
	 * 
	 * @param actionEvent
	 *            the action event
	 */
	public void action_openConfirmationRequest(ActionEvent actionEvent) {
		component = actionEvent.getComponent().getId();
		try {
			validarDatos(requestsClasses, requestsTypes, vehiclesTypes,
					legateesTypes, rqsFechaInicial, rqsFechaFinal,
					rqsDescripcion);

			if (requestsClasses.longValue() == ParametersUtil.CLASE_ALQUILER
					.longValue()
					&& legateesTypes.longValue() != ParametersUtil.LGT_CONVENIO
							.longValue()
					&& (listCostsCentersRequests == null || listCostsCentersRequests
							.size() == 0))
				advertenciaAlquiler = "Alquileres sin centro de costo serán descontados a nómina ";
			else
				advertenciaAlquiler = "";

			confirmarSolicitud = advertenciaAlquiler + confirmarSolicitud;

			showConfirmRequest = true;
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

	}

	/**
	 * Action_close confirmation.
	 * 
	 * @param actionEvent
	 *            the action event
	 */
	public void action_closeConfirmation(ActionEvent actionEvent) {
		showConfirmRequest = false;
	}

	/**
	 * Action_crear solicitud.
	 * 
	 * @param actionEvent
	 *            the action event
	 */
	public void action_crearSolicitud(ActionEvent actionEvent) {

		Long state = null;
		String message = null;
		Long porcentajeCentroCosto = 0L;

		try {

			if (listCostsCentersRequests != null
					&& listCostsCentersRequests.size() > 0) {

				if (vehiclesTypes != null
						&& listCostsCentersRequests != null
						&& requestsClasses != null
						&& listCostsCentersRequests.size() > 0
						&& requestsClasses.longValue() == ViewOptionUtil.ALQUILER)
					consultsService.consultarDisponibilidadAlquilerCC(
							listCostsCentersRequests, vehiclesTypes,
							requestsClasses.longValue(), legateesTypes);

				for (CostsCentersVehicles centersVehicles : listCostsCentersRequests) {
					porcentajeCentroCosto += Long.parseLong(centersVehicles
							.getCcrPorcentaje());
				}

				if (porcentajeCentroCosto < 100
						|| porcentajeCentroCosto.longValue() == 0)
					throw new GWorkException(Util
							.loadErrorMessageValue("PORCENTAJE.ERROR"));
			}

			if (component.equals(ViewOptionUtil.CMD_ENVIAR)) {
				state = 2l;
				message = Util.loadMessageValue("SOLICITUD.ENVIADA");
			}

			if (component.equals(ViewOptionUtil.CMD_GUARDAR)) {
				state = 1l;
				message = Util.loadMessageValue("SOLICITUD.CREADA");
			}

			if (rqsCarnetAsignatario != null
					&& rqsCarnetAsignatario.trim().length() > 0) {
				validarCaracteresEspecial(rqsCarnetAsignatario);
				nombreAsig = consultsService
						.consultEmpleoyeeName(rqsCarnetAsignatario);

				if (emailAsig == null || emailAsig.trim().length() == 0)
					emailAsig = consultsService
							.consultEmpleoyeeEmail(rqsCarnetAsignatario);

				if (nombreAsig == null || nombreAsig.trim().length() == 0)
					throw new GWorkException(Util
							.loadErrorMessageValue("NOMBREASIG.INVALIDO"));
			}

			if (rqsCarnetEmpleado != null
					&& rqsCarnetEmpleado.trim().length() > 0) {

				validarCaracteresEspecial(rqsCarnetEmpleado);
				nombreAsis = consultsService
						.consultEmpleoyeeName(rqsCarnetEmpleado);
				emailAsis = consultsService
						.consultEmpleoyeeEmail(rqsCarnetEmpleado);

				if (nombreAsis == null || nombreAsis.trim().length() == 0)
					throw new GWorkException(Util
							.loadErrorMessageValue("NOMBREASIS.INVALIDO"));

			}
			if (rqsNit != null && rqsNit.trim().length() > 0) {

				validarCaracteresEspecial(rqsNit);
				nombreAsig = consultsService.consultEmpleoyeeName(rqsNit);
			}

			if (emailAsig == null
					|| emailAsig.trim().length() == 0
					&& requestsClasses.longValue() != ParametersUtil.CLASE_ALQUILER_TERCEROS
							.longValue()
					&& legateesTypes.longValue() != ParametersUtil.LGT_CONVENIO
							.longValue())
				throw new GWorkException(
						"Debe ingresar el mail del asignatario");

			if (idRequests == null) {
				requestService.vehicleRequest(requestsClasses, requestsTypes,
						placa.toUpperCase(), legateesTypes, rqsFechaInicial,
						rqsFechaFinal, vehiclesTypes, rqsDescripcion,
						rqsCarnetAsignatario, nombreAsig, emailAsig,
						rqsCarnetEmpleado, nombreAsis, emailAsis,
						listCostsCentersRequests, rqsNit, state, idZones,
						loginPage.getCarne());
				messagePage.showPopup(message, false);
			} else if (idRequests != null) {
				String tmpPlaca = placa;
				if (tmpPlaca != null)
					tmpPlaca = placa.toUpperCase();

				requestService.modificarSolicitud(idRequests, requestsClasses,
						requestsTypes, tmpPlaca, legateesTypes,
						rqsFechaInicial, rqsFechaFinal, vehiclesTypes,
						rqsDescripcion, rqsCarnetAsignatario, nombreAsig,
						emailAsig, rqsCarnetEmpleado, nombreAsis, emailAsis,
						listCostsCentersRequests, rqsNit, state, idZones,
						loginPage.getCarne());
				messagePage.showPopup(message, false);
			}

			clean_all();
			ocultar_asignaciones();
			showConfirmRequest = false;
		} catch (GWorkException e) {
			showConfirmRequest = false;
			messagePage.showPopup(e.getMessage(), false);
		}
	}

	/**
	 * Action_consultar_asignatario.
	 * 
	 * @param event
	 *            the event
	 * @throws GWorkException
	 *             the g work exception
	 */
	public void action_consultar_asignatario(ActionEvent event)
			throws GWorkException {

		showEmployees = true;
		setOpcFindEmpleado(ViewOptionUtil.FINDASIGNATARIO);

	}

	/**
	 * Action_consultar_cost center.
	 * 
	 * @param event
	 *            the event
	 * @throws GWorkException
	 *             the g work exception
	 */
	public void action_consultar_costCenter(ActionEvent event)
			throws GWorkException {

		showCostCentersTable = true;

	}

	/**
	 * Action_consultar_asistente.
	 * 
	 * @param event
	 *            the event
	 */
	public void action_consultar_asistente(ActionEvent event) {

		showEmployees = true;
		setOpcFindEmpleado(ViewOptionUtil.FINDASISTENTE);
	}

	/**
	 * Listener_clase_solicitud.
	 * 
	 * @param changeEvent
	 *            the change event
	 */
	public void listener_clase_solicitud(ValueChangeEvent changeEvent) {
		Long code = (Long) changeEvent.getNewValue();

		opcPantallaClaseSolicitud(code);

	}

	/**
	 * Listener_tipo_solicitud.
	 * 
	 * @param changeEvent
	 *            the change event
	 */
	public void listener_tipo_solicitud(ValueChangeEvent changeEvent) {
		Long code = (Long) changeEvent.getNewValue();
		if (code != null
				&& (code.equals(ViewOptionUtil.REEMPLAZO) || code
						.equals(ViewOptionUtil.EXTENSION))) {
			txtPlaca.setDisabled(false);
			txtPlaca.setStyleClass("siscarNotNull");
		} else {
			txtPlaca.setDisabled(true);
			txtPlaca.setValue(null);
			setPlaca(null);
			txtPlaca.setStyleClass("siscarNotNull");
		}
	}

	/**
	 * Listener_tipo_asignacion.
	 * 
	 * @param changeEvent
	 *            the change event
	 */
	public void listener_tipo_asignacion(ValueChangeEvent changeEvent) {
		Long code = (Long) changeEvent.getNewValue();

		opcPantallaTipoAsignacion(code);

	}

	/**
	 * Action_eliminar centro costo.
	 * 
	 * @param actionEvent
	 *            the action event
	 */
	public void action_eliminarCentroCosto(ActionEvent actionEvent) {
		String sbCentroCosto = (String) htmlIdRequest.getValue();

		try {
			for (CostsCentersVehicles centersVehicles : listCostsCentersRequests) {
				if (centersVehicles.getCostsCenters().getCocNumero().equals(
						sbCentroCosto)) {
					listCostsCentersRequests.remove(centersVehicles);

					messagePage.showPopup(Util
							.loadErrorMessageValue("CENTROCOSTO.ELIMINAR"),
							false);
					break;
				}

			}
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	/**
	 * Action_ingresar centro costo.
	 * 
	 * @param actionEvent
	 *            the action event
	 */
	public void action_ingresarCentroCosto(ActionEvent actionEvent) {
		try {

			if (listCostsCentersRequests == null)
				listCostsCentersRequests = new ArrayList<CostsCentersVehicles>();

			validarValor(lPorcentajeCentroCosto);
			if (sbCodigoCentroCosto != null
					&& (sbCodigoCentroCosto.trim().length() == 0))
				throw new GWorkException(Util
						.loadErrorMessageValue("CENTROCOSTO.NULO"));

			String centroCostoActivo = consultsService
					.consultCostCenter(sbCodigoCentroCosto.toUpperCase());

			if (centroCostoActivo != null && centroCostoActivo.length() > 0)
				throw new GWorkException(centroCostoActivo);

			if ((lPorcentajeCentroCosto == null || lPorcentajeCentroCosto
					.trim().length() == 0))
				throw new GWorkException(Util
						.loadErrorMessageValue("PORCENTAJE.CENTRO"));

			Long PorcentajeCentroCosto = Long.valueOf(lPorcentajeCentroCosto);

			if (PorcentajeCentroCosto > 101 || PorcentajeCentroCosto <= 0)
				throw new GWorkException(Util
						.loadErrorMessageValue("PORCENTAJE.INCORRECTO"));

			long por = validaPorcentajeCosto() + PorcentajeCentroCosto;

			if (por > 100)
				throw new GWorkException(Util
						.loadErrorMessageValue("CENTROCOSTO.CANTIDADN")
						+ " " + por);

			CostsCenters costsCenters = new CostsCenters();
			CostsCentersVehicles centersVehicles = new CostsCentersVehicles();
			costsCenters.setCocNumero(sbCodigoCentroCosto.toUpperCase());
			centersVehicles.setCostsCenters(costsCenters);
			centersVehicles.setCcrPorcentaje("" + PorcentajeCentroCosto);

			for (CostsCentersVehicles centersVehiclesObj : listCostsCentersRequests) {
				if (centersVehiclesObj.getCostsCenters().getCocNumero().equals(
						sbCodigoCentroCosto.toUpperCase()))
					throw new GWorkException(Util
							.loadErrorMessageValue("CENTROCOSTOEXISTE"));

			}

			listCostsCentersRequests.add(centersVehicles);
			sbCodigoCentroCosto = "";
			lPorcentajeCentroCosto = null;
		} catch (GWorkException e) {
			messagePage.showPopup(e.getMessage(), false);

		}
	}

	/**
	 * Valida porcentaje costo.
	 * 
	 * @return the long
	 * @throws GWorkException
	 *             the g work exception
	 */
	private long validaPorcentajeCosto() throws GWorkException {
		long porcentaje = 0;
		if (listCostsCentersRequests.size() != 0) {
			for (CostsCentersVehicles centersVehicles : listCostsCentersRequests) {

				porcentaje += Long
						.parseLong(centersVehicles.getCcrPorcentaje());

			}
		}
		return porcentaje;
	}

	/**
	 * Metodo que limpia todo los campos.
	 */
	private void clean_all() {
		requestsTypes = null;
		legateesTypes = null;
		vehiclesTypes = null;
		requestsClasses = null;
		nombreAsig = "";
		emailAsig = "";
		nombreAsis = "";
		emailAsis = "";
		rqsDescripcion = "";
		rqsCarnetEmpleado = "";
		rqsCarnetAsignatario = "";
		rqsFechaInicial = null;
		rqsFechaFinal = null;
		rqsNit = "";
		cocNumero = "";
		ccrPorcentaje = "";
		ccrFechaInicio = null;
		ccrFechaFin = null;
		placa = "";
		sbCodigoCentroCosto = "";
		lPorcentajeCentroCosto = null;
		idRequests = null;
		if (listCostsCentersRequests != null)
			listCostsCentersRequests.clear();
		setShowZones(false);
		showZones = false;
		parametroBusquedad = null;
		parametroCentroCostos = null;
		component = null;
		idZones = null;
		nombreTercero = null;
		txtPlaca.setDisabled(true);
		advertenciaAlquiler = "";
	}

	/**
	 * Checks if is show ofs.
	 * 
	 * @return true, if is show ofs
	 */
	public boolean isShowOFS() {
		return showOFS;
	}

	/**
	 * Sets the show ofs.
	 * 
	 * @param showOFS
	 *            the new show ofs
	 */
	public void setShowOFS(boolean showOFS) {
		this.showOFS = showOFS;
	}

	/**
	 * Checks if is show cost center.
	 * 
	 * @return true, if is show cost center
	 */
	public boolean isShowCostCenter() {
		return showCostCenter;
	}

	/**
	 * Sets the show cost center.
	 * 
	 * @param showCostCenter
	 *            the new show cost center
	 */
	public void setShowCostCenter(boolean showCostCenter) {
		this.showCostCenter = showCostCenter;
	}

	/**
	 * Checks if is show tercero.
	 * 
	 * @return true, if is show tercero
	 */
	public boolean isShowTercero() {
		return showTercero;
	}

	/**
	 * Sets the show tercero.
	 * 
	 * @param showTercero
	 *            the new show tercero
	 */
	public void setShowTercero(boolean showTercero) {
		this.showTercero = showTercero;
	}

	/**
	 * Gets the sb codigo centro costo.
	 * 
	 * @return the sb codigo centro costo
	 */
	public String getSbCodigoCentroCosto() {
		return sbCodigoCentroCosto;
	}

	/**
	 * Sets the sb codigo centro costo.
	 * 
	 * @param sbCodigoCentroCosto
	 *            the new sb codigo centro costo
	 */
	public void setSbCodigoCentroCosto(String sbCodigoCentroCosto) {
		this.sbCodigoCentroCosto = sbCodigoCentroCosto;
	}

	/**
	 * Gets the consults service.
	 * 
	 * @return the consults service
	 */
	public ConsultsService getConsultsService() {
		return consultsService;
	}

	/**
	 * Sets the consults service.
	 * 
	 * @param consultsService
	 *            the new consults service
	 */
	public void setConsultsService(ConsultsService consultsService) {
		this.consultsService = consultsService;
	}

	/**
	 * Gets the html id request.
	 * 
	 * @return the html id request
	 */
	public HtmlOutputText getHtmlIdRequest() {
		return htmlIdRequest;
	}

	/**
	 * Sets the html id request.
	 * 
	 * @param htmlIdRequest
	 *            the new html id request
	 */
	public void setHtmlIdRequest(HtmlOutputText htmlIdRequest) {
		this.htmlIdRequest = htmlIdRequest;
	}

	/**
	 * Gets the requests types.
	 * 
	 * @return the requests types
	 */
	public Long getRequestsTypes() {
		return requestsTypes;
	}

	/**
	 * Sets the requests types.
	 * 
	 * @param requestsTypes
	 *            the new requests types
	 */
	public void setRequestsTypes(Long requestsTypes) {
		this.requestsTypes = requestsTypes;
	}

	/**
	 * Gets the legatees types.
	 * 
	 * @return the legatees types
	 */
	public Long getLegateesTypes() {
		return legateesTypes;
	}

	/**
	 * Sets the legatees types.
	 * 
	 * @param legateesTypes
	 *            the new legatees types
	 */
	public void setLegateesTypes(Long legateesTypes) {
		this.legateesTypes = legateesTypes;
	}

	/**
	 * Gets the vehicles types.
	 * 
	 * @return the vehicles types
	 */
	public Long getVehiclesTypes() {
		return vehiclesTypes;
	}

	/**
	 * Sets the vehicles types.
	 * 
	 * @param vehiclesTypes
	 *            the new vehicles types
	 */
	public void setVehiclesTypes(Long vehiclesTypes) {
		this.vehiclesTypes = vehiclesTypes;
	}

	/**
	 * Gets the requests classes.
	 * 
	 * @return the requests classes
	 */
	public Long getRequestsClasses() {
		return requestsClasses;
	}

	/**
	 * Sets the requests classes.
	 * 
	 * @param requestsClasses
	 *            the new requests classes
	 */
	public void setRequestsClasses(Long requestsClasses) {
		this.requestsClasses = requestsClasses;
	}

	/**
	 * Gets the rqs descripcion.
	 * 
	 * @return the rqs descripcion
	 */
	public String getRqsDescripcion() {
		return rqsDescripcion;
	}

	/**
	 * Sets the rqs descripcion.
	 * 
	 * @param rqsDescripcion
	 *            the new rqs descripcion
	 */
	public void setRqsDescripcion(String rqsDescripcion) {
		this.rqsDescripcion = rqsDescripcion;
	}

	/**
	 * Gets the rqs carnet empleado.
	 * 
	 * @return the rqs carnet empleado
	 */
	public String getRqsCarnetEmpleado() {
		return rqsCarnetEmpleado;
	}

	/**
	 * Sets the rqs carnet empleado.
	 * 
	 * @param rqsCarnetEmpleado
	 *            the new rqs carnet empleado
	 */
	public void setRqsCarnetEmpleado(String rqsCarnetEmpleado) {
		this.rqsCarnetEmpleado = rqsCarnetEmpleado;
	}

	/**
	 * Gets the rqs carnet asignatario.
	 * 
	 * @return the rqs carnet asignatario
	 */
	public String getRqsCarnetAsignatario() {
		return rqsCarnetAsignatario;
	}

	/**
	 * Sets the rqs carnet asignatario.
	 * 
	 * @param rqsCarnetAsignatario
	 *            the new rqs carnet asignatario
	 */
	public void setRqsCarnetAsignatario(String rqsCarnetAsignatario) {
		this.rqsCarnetAsignatario = rqsCarnetAsignatario;
	}

	/**
	 * Gets the rqs fecha inicial.
	 * 
	 * @return the rqs fecha inicial
	 */
	public Date getRqsFechaInicial() {
		return rqsFechaInicial;
	}

	/**
	 * Sets the rqs fecha inicial.
	 * 
	 * @param rqsFechaInicial
	 *            the new rqs fecha inicial
	 */
	public void setRqsFechaInicial(Date rqsFechaInicial) {
		this.rqsFechaInicial = rqsFechaInicial;
	}

	/**
	 * Gets the rqs fecha final.
	 * 
	 * @return the rqs fecha final
	 */
	public Date getRqsFechaFinal() {
		return rqsFechaFinal;
	}

	/**
	 * Sets the rqs fecha final.
	 * 
	 * @param rqsFechaFinal
	 *            the new rqs fecha final
	 */
	public void setRqsFechaFinal(Date rqsFechaFinal) {
		this.rqsFechaFinal = rqsFechaFinal;
	}

	/**
	 * Gets the rqs nit.
	 * 
	 * @return the rqs nit
	 */
	public String getRqsNit() {
		return rqsNit;
	}

	/**
	 * Sets the rqs nit.
	 * 
	 * @param rqsNit
	 *            the new rqs nit
	 */
	public void setRqsNit(String rqsNit) {
		this.rqsNit = rqsNit;
	}

	/**
	 * Gets the coc numero.
	 * 
	 * @return the coc numero
	 */
	public String getCocNumero() {
		return cocNumero;
	}

	/**
	 * Sets the coc numero.
	 * 
	 * @param cocNumero
	 *            the new coc numero
	 */
	public void setCocNumero(String cocNumero) {
		this.cocNumero = cocNumero;
	}

	/**
	 * Gets the ccr porcentaje.
	 * 
	 * @return the ccr porcentaje
	 */
	public String getCcrPorcentaje() {
		return ccrPorcentaje;
	}

	/**
	 * Sets the ccr porcentaje.
	 * 
	 * @param ccrPorcentaje
	 *            the new ccr porcentaje
	 */
	public void setCcrPorcentaje(String ccrPorcentaje) {
		this.ccrPorcentaje = ccrPorcentaje;
	}

	/**
	 * Gets the ccr valor.
	 * 
	 * @return the ccr valor
	 */
	public Long getCcrValor() {
		return ccrValor;
	}

	/**
	 * Sets the ccr valor.
	 * 
	 * @param ccrValor
	 *            the new ccr valor
	 */
	public void setCcrValor(Long ccrValor) {
		this.ccrValor = ccrValor;
	}

	/**
	 * Gets the ccr fecha inicio.
	 * 
	 * @return the ccr fecha inicio
	 */
	public Date getCcrFechaInicio() {
		return ccrFechaInicio;
	}

	/**
	 * Sets the ccr fecha inicio.
	 * 
	 * @param ccrFechaInicio
	 *            the new ccr fecha inicio
	 */
	public void setCcrFechaInicio(Date ccrFechaInicio) {
		this.ccrFechaInicio = ccrFechaInicio;
	}

	/**
	 * Gets the ccr fecha fin.
	 * 
	 * @return the ccr fecha fin
	 */
	public Date getCcrFechaFin() {
		return ccrFechaFin;
	}

	/**
	 * Sets the ccr fecha fin.
	 * 
	 * @param ccrFechaFin
	 *            the new ccr fecha fin
	 */
	public void setCcrFechaFin(Date ccrFechaFin) {
		this.ccrFechaFin = ccrFechaFin;
	}

	/**
	 * Gets the message page.
	 * 
	 * @return the message page
	 */
	public PopupMessagePage getMessagePage() {
		return messagePage;
	}

	/**
	 * Sets the message page.
	 * 
	 * @param messagePage
	 *            the new message page
	 */
	public void setMessagePage(PopupMessagePage messagePage) {
		this.messagePage = messagePage;
	}

	/**
	 * Gets the placa.
	 * 
	 * @return the placa
	 */
	public String getPlaca() {
		return placa;
	}

	/**
	 * Sets the placa.
	 * 
	 * @param placa
	 *            the new placa
	 */
	public void setPlaca(String placa) {
		this.placa = placa;
	}

	/**
	 * Gets the email asis.
	 * 
	 * @return the email asis
	 */
	public String getEmailAsis() {
		return emailAsis;
	}

	/**
	 * Sets the email asis.
	 * 
	 * @param emailAsis
	 *            the new email asis
	 */
	public void setEmailAsis(String emailAsis) {
		this.emailAsis = emailAsis;
	}

	/**
	 * Gets the nombre asig.
	 * 
	 * @return the nombre asig
	 */
	public String getNombreAsig() {
		return nombreAsig;
	}

	/**
	 * Sets the nombre asig.
	 * 
	 * @param nombreAsig
	 *            the new nombre asig
	 */
	public void setNombreAsig(String nombreAsig) {
		this.nombreAsig = nombreAsig;
	}

	/**
	 * Gets the email asig.
	 * 
	 * @return the email asig
	 */
	public String getEmailAsig() {
		return emailAsig;
	}

	/**
	 * Sets the email asig.
	 * 
	 * @param emailAsig
	 *            the new email asig
	 */
	public void setEmailAsig(String emailAsig) {
		this.emailAsig = emailAsig;
	}

	/**
	 * Gets the nombre asis.
	 * 
	 * @return the nombre asis
	 */
	public String getNombreAsis() {
		return nombreAsis;
	}

	/**
	 * Sets the nombre asis.
	 * 
	 * @param nombreAsis
	 *            the new nombre asis
	 */
	public void setNombreAsis(String nombreAsis) {
		this.nombreAsis = nombreAsis;
	}

	/**
	 * Gets the request service.
	 * 
	 * @return the request service
	 */
	public RequestService getRequestService() {
		return requestService;
	}

	/**
	 * Sets the request service.
	 * 
	 * @param requestService
	 *            the new request service
	 */
	public void setRequestService(RequestService requestService) {
		this.requestService = requestService;
	}

	/**
	 * Gets the html select tipo asig.
	 * 
	 * @return the html select tipo asig
	 */
	public HtmlSelectOneMenu getHtmlSelectTipoAsig() {
		return htmlSelectTipoAsig;
	}

	/**
	 * Sets the html select tipo asig.
	 * 
	 * @param htmlSelectTipoAsig
	 *            the new html select tipo asig
	 */
	public void setHtmlSelectTipoAsig(HtmlSelectOneMenu htmlSelectTipoAsig) {
		this.htmlSelectTipoAsig = htmlSelectTipoAsig;
	}

	/**
	 * Validar valor.
	 * 
	 * @param valor
	 *            the valor
	 * @throws GWorkException
	 *             the g work exception
	 */
	public void validarValor(String valor) throws GWorkException {
		boolean esValido = true;

		esValido = Util.validarNumerosParametros(valor);

		if (!esValido)
			throw new GWorkException(Util
					.loadErrorMessageValue("CARACTER.NUMERO"));

	}

	/**
	 * Validar caracteres especial.
	 * 
	 * @param valor
	 *            the valor
	 * @throws GWorkException
	 *             the g work exception
	 */
	public void validarCaracteresEspecial(String valor) throws GWorkException {
		boolean esValido = true;

		esValido = Util.validarCadenaCaracteresEspecialesNumLetrasGuion(valor);

		if (!esValido)
			throw new GWorkException(Util
					.loadErrorMessageValue("CARACTER.ESPECIAL"));

	}

	/**
	 * Ocultar_asignaciones.
	 */
	public void ocultar_asignaciones() {

		showOFS = false;
		showCostCenter = false;
		showTercero = false;

	}

	/**
	 * Gets the nombre tercero.
	 * 
	 * @return the nombre tercero
	 */
	public String getNombreTercero() {
		return nombreTercero;
	}

	/**
	 * Sets the nombre tercero.
	 * 
	 * @param nombreTercero
	 *            the new nombre tercero
	 */
	public void setNombreTercero(String nombreTercero) {
		this.nombreTercero = nombreTercero;
	}

	/**
	 * Action_limpiar_tercero.
	 * 
	 * @param actionEvent
	 *            the action event
	 */
	public void action_limpiar_tercero(ActionEvent actionEvent) {

		setNombreTercero(null);
		setRqsNit(null);
	}

	/**
	 * Gets the txt placa.
	 * 
	 * @return the txt placa
	 */
	public HtmlInputText getTxtPlaca() {
		return txtPlaca;
	}

	/**
	 * Sets the txt placa.
	 * 
	 * @param txtPlaca
	 *            the new txt placa
	 */
	public void setTxtPlaca(HtmlInputText txtPlaca) {
		this.txtPlaca = txtPlaca;
	}

	/**
	 * Checks if is show employees.
	 * 
	 * @return true, if is show employees
	 */
	public boolean isShowEmployees() {
		return showEmployees;
	}

	/**
	 * Sets the show employees.
	 * 
	 * @param showEmployees
	 *            the new show employees
	 */
	public void setShowEmployees(boolean showEmployees) {
		this.showEmployees = showEmployees;
	}

	/**
	 * Close show employees.
	 * 
	 * @param event
	 *            the event
	 */
	public void closeShowEmployees(ActionEvent event) {
		showEmployees = false;

	}

	/**
	 * Close show cost centers table.
	 * 
	 * @param event
	 *            the event
	 */
	public void closeShowCostCentersTable(ActionEvent event) {
		showCostCentersTable = false;
	}

	/**
	 * Gets the list users.
	 * 
	 * @return the list users
	 */
	public List<Users> getListUsers() {
		return listUsers;
	}

	/**
	 * Sets the list users.
	 * 
	 * @param listUsers
	 *            the new list users
	 */
	public void setListUsers(List<Users> listUsers) {
		this.listUsers = listUsers;
	}

	/**
	 * Action_filtro busqueda empleado.
	 * 
	 * @param actionEvent
	 *            the action event
	 */
	public void action_filtroBusquedaEmpleado(ActionEvent actionEvent) {
		try {
			setListUsers(consultsService.employeesCIAT(parametroBusquedad
					.toUpperCase(), parametroBusquedad.toUpperCase(),
					parametroBusquedad));
		} catch (GWorkException e) {
			messagePage.showPopup(e.getMessage(), false);
		}
	}

	/**
	 * Action_filtro busqueda centro costos.
	 * 
	 * @param actionEvent
	 *            the action event
	 */
	public void action_filtroBusquedaCentroCostos(ActionEvent actionEvent) {
		try {
			setCostCentersTable(consultsService
					.centrosCosto(parametroCentroCostos.toUpperCase()));
		} catch (GWorkException e) {
			messagePage.showPopup(e.getMessage(), false);
		}
	}

	/**
	 * Gets the parametro busquedad.
	 * 
	 * @return the parametro busquedad
	 */
	public String getParametroBusquedad() {
		return parametroBusquedad;
	}

	/**
	 * Sets the parametro busquedad.
	 * 
	 * @param parametroBusquedad
	 *            the new parametro busquedad
	 */
	public void setParametroBusquedad(String parametroBusquedad) {
		this.parametroBusquedad = parametroBusquedad;
	}

	/**
	 * Row selection employee.
	 * 
	 * @param rowSelectorEvent
	 *            the row selector event
	 */
	public void rowSelectionEmployee(RowSelectorEvent rowSelectorEvent) {
		String idUsuario = (String) idUser.getValue();

		try {
			if (opcFindEmpleado != null
					&& opcFindEmpleado.longValue() == ViewOptionUtil.FINDASIGNATARIO) {
				for (Users users : listUsers) {

					if (idUsuario
							.equalsIgnoreCase(users.getUsrIdentificacion())) {
						setRqsCarnetAsignatario(users.getUsrIdentificacion());
						setNombreAsig(users.getUsrNombre());
						String email = consultsService
								.consultEmpleoyeeEmail(users
										.getUsrIdentificacion());
						if (email != null)
							setEmailAsig(email);
						showEmployees = false;
					}
				}
			}
			if (opcFindEmpleado != null
					&& opcFindEmpleado.longValue() == ViewOptionUtil.FINDASISTENTE) {

				for (Users users : listUsers) {
					if (idUsuario
							.equalsIgnoreCase(users.getUsrIdentificacion())) {
						setRqsCarnetEmpleado(users.getUsrIdentificacion());
						setNombreAsis(users.getUsrNombre());
						String email = consultsService
								.consultEmpleoyeeEmail(users
										.getUsrIdentificacion());
						if (email != null)
							setEmailAsis(email);
						showEmployees = false;
					}
				}

			}

		} catch (GWorkException e) {
			messagePage.showPopup(e.getMessage(), false);
		}

		setListUsers(null);
		setOpcFindEmpleado(null);

	}

	/**
	 * Row selector cost centers.
	 * 
	 * @param event
	 *            the event
	 */
	public void rowSelectorCostCenters(RowSelectorEvent event) {
		String idCostCenter = (String) idCostCenters.getValue();
		RegistryNewnessCostCenterPage centersNewness = (RegistryNewnessCostCenterPage) FacesUtils
				.getManagedBean("newnessCostCenterPage");
		RegistryNewnessCostCenterPageFuel centersNewnessFuel = (RegistryNewnessCostCenterPageFuel) FacesUtils
				.getManagedBean("newnessCostCenterPageFuel");
		for (CostsCenters costsCenters : costCentersTable) {
			if (idCostCenter.equalsIgnoreCase(costsCenters.getCocNumero())) {
				setSbCodigoCentroCosto(costsCenters.getCocNumero());
				showCostCentersTable = false;
				if (centersNewness != null)
					centersNewness.setNombreCentro(costsCenters.getCocNumero());
				if (centersNewnessFuel != null)
					centersNewnessFuel.setNombreCentro(costsCenters
							.getCocNumero());
			}
		}
		setCostCentersTable(null);

	}

	/**
	 * Gets the opc find empleado.
	 * 
	 * @return the opc find empleado
	 */
	public Long getOpcFindEmpleado() {
		return opcFindEmpleado;
	}

	/**
	 * Sets the opc find empleado.
	 * 
	 * @param opcFindEmpleado
	 *            the new opc find empleado
	 */
	public void setOpcFindEmpleado(Long opcFindEmpleado) {
		this.opcFindEmpleado = opcFindEmpleado;
	}

	/**
	 * Gets the id user.
	 * 
	 * @return the id user
	 */
	public HtmlOutputText getIdUser() {
		return idUser;
	}

	/**
	 * Sets the id user.
	 * 
	 * @param idUser
	 *            the new id user
	 */
	public void setIdUser(HtmlOutputText idUser) {
		this.idUser = idUser;
	}

	/**
	 * Gets the l porcentaje centro costo.
	 * 
	 * @return the l porcentaje centro costo
	 */
	public String getLPorcentajeCentroCosto() {
		return lPorcentajeCentroCosto;
	}

	/**
	 * Sets the l porcentaje centro costo.
	 * 
	 * @param porcentajeCentroCosto
	 *            the new l porcentaje centro costo
	 */
	public void setLPorcentajeCentroCosto(String porcentajeCentroCosto) {
		lPorcentajeCentroCosto = porcentajeCentroCosto;
	}

	/**
	 * Checks if is show cost centers table.
	 * 
	 * @return true, if is show cost centers table
	 */
	public boolean isShowCostCentersTable() {
		return showCostCentersTable;
	}

	/**
	 * Sets the show cost centers table.
	 * 
	 * @param showCostCentersTable
	 *            the new show cost centers table
	 */
	public void setShowCostCentersTable(boolean showCostCentersTable) {
		this.showCostCentersTable = showCostCentersTable;
	}

	/**
	 * Gets the cost centers table.
	 * 
	 * @return the cost centers table
	 */
	public List<CostsCenters> getCostCentersTable() {
		return costCentersTable;
	}

	/**
	 * Sets the cost centers table.
	 * 
	 * @param costCentersTable
	 *            the new cost centers table
	 */
	public void setCostCentersTable(List<CostsCenters> costCentersTable) {
		this.costCentersTable = costCentersTable;
	}

	/**
	 * Gets the parametro centro costos.
	 * 
	 * @return the parametro centro costos
	 */
	public String getParametroCentroCostos() {
		return parametroCentroCostos;
	}

	/**
	 * Sets the parametro centro costos.
	 * 
	 * @param parametroCentroCostos
	 *            the new parametro centro costos
	 */
	public void setParametroCentroCostos(String parametroCentroCostos) {
		this.parametroCentroCostos = parametroCentroCostos;
	}

	/**
	 * Gets the id cost centers.
	 * 
	 * @return the id cost centers
	 */
	public HtmlOutputText getIdCostCenters() {
		return idCostCenters;
	}

	/**
	 * Sets the id cost centers.
	 * 
	 * @param idCostCenters
	 *            the new id cost centers
	 */
	public void setIdCostCenters(HtmlOutputText idCostCenters) {
		this.idCostCenters = idCostCenters;
	}

	/**
	 * Modificar solicitud.
	 */
	@SuppressWarnings("null")
	public void modificarSolicitud() {

		Requests requests = (Requests) FacesUtils.getSession().getAttribute(
				"modificarSolicitud");

		if (requests != null) {

			showBtnCancelar = true;

			if ((requests.getLegateesTypes() != null && (requests
					.getLegateesTypes().getLgtCodigo().longValue() == ViewOptionUtil.OF
					|| requests.getLegateesTypes().getLgtCodigo().longValue() == ViewOptionUtil.OFS
					|| requests.getLegateesTypes().getLgtCodigo().longValue() == ViewOptionUtil.PROGRAMA || requests
					.getLegateesTypes().getLgtCodigo().longValue() == ViewOptionUtil.PROYECTO))
					|| requests.getRequestsClasses().getRqcCodigo().longValue() == ViewOptionUtil.ALQUILER) {

				if (requests.getRequestsClasses().getRqcCodigo().longValue() == ViewOptionUtil.ALQUILER) {
					siscarNotNull = "";
				}

				setRequestsClasses(requests.getRequestsClasses().getRqcCodigo()
						.longValue());
				if (requests.getLegateesTypes() != null)
					setLegateesTypes(requests.getLegateesTypes().getLgtCodigo()
							.longValue());
				setRequestsTypes(requests.getRequestsTypes().getRqyCodigo()
						.longValue());
				setVehiclesTypes(requests.getVehiclesTypes().getVhtCodigo()
						.longValue());
				setRqsFechaInicial(requests.getRqsFechaInicial());
				setRqsFechaFinal(requests.getRqsFechaFinal());
				if (requests.getRqsPlacaExtensionRemplazo() != null)
					setPlaca(requests.getRqsPlacaExtensionRemplazo());
				setRqsDescripcion(requests.getRqsDescripcion());
				setRqsCarnetAsignatario(requests.getRqsCarnetAsignatario());
				setNombreAsig(requests.getUsersByRqsUser().getUsrNombre());
				setEmailAsig(requests.getUsersByRqsUser().getUsrEmail());
				if (requests.getRqsCarnetEmpleado() != null)
					setRqsCarnetEmpleado(requests.getRqsCarnetEmpleado());
				if (requests.getUsersByUsrCodigo() != null)
					setNombreAsis(requests.getUsersByUsrCodigo().getUsrNombre());
				if (requests.getUsersByUsrCodigo() != null)
					setEmailAsis(requests.getUsersByUsrCodigo().getUsrEmail());

				if (requests.getZones() != null
						&& requests.getLegateesTypes() != null
						&& (requests.getLegateesTypes().getLgtCodigo()
								.longValue() == ViewOptionUtil.OF || requests
								.getLegateesTypes().getLgtCodigo().longValue() == ViewOptionUtil.OFS)) {

					setIdZones(requests.getZones().getZnsId().longValue());
					showZones = true;
				}

				List<CostsCentersVehicles> listCostCenters = new ArrayList<CostsCentersVehicles>(
						requests.getCostsCentersVehicleses());

				if (listCostCenters != null || !listCostCenters.isEmpty()) {
					listCostsCentersRequests = new ArrayList<CostsCentersVehicles>();
					for (CostsCentersVehicles centersRequests : listCostCenters) {

						CostsCenters costsCenters = new CostsCenters();
						CostsCentersVehicles centersVehicles = new CostsCentersVehicles();

						costsCenters.setCocNumero(centersRequests
								.getCostsCenters().getCocNumero());
						centersVehicles.setCostsCenters(costsCenters);
						centersVehicles.setCcrPorcentaje(centersRequests
								.getCcrPorcentaje());
						listCostsCentersRequests.add(centersVehicles);
					}
					setListCostsCentersRequests(listCostCenters);

				}

				setIdRequests(requests.getRqsCodigo());
				setShowOFS(true);
				setShowCostCenter(true);
				setShowTercero(false);

			} else if (requests.getRequestsClasses().getRqcCodigo().longValue() == ViewOptionUtil.ALQUILER_TERCERO
					|| requests.getLegateesTypes().getLgtCodigo().longValue() == ViewOptionUtil.CONVENIO) {

				if (requests.getRequestsClasses().getRqcCodigo().longValue() == ViewOptionUtil.ALQUILER_TERCERO)
					disabledAsignacion = true;

				setRequestsClasses(requests.getRequestsClasses().getRqcCodigo()
						.longValue());
				if (requests.getLegateesTypes() != null)
					setLegateesTypes(requests.getLegateesTypes().getLgtCodigo()
							.longValue());
				setRequestsTypes(requests.getRequestsTypes().getRqyCodigo()
						.longValue());
				setVehiclesTypes(requests.getVehiclesTypes().getVhtCodigo()
						.longValue());
				setRqsFechaInicial(requests.getRqsFechaInicial());
				setRqsFechaFinal(requests.getRqsFechaFinal());
				if (requests.getRqsPlacaExtensionRemplazo() != null)
					setPlaca(requests.getRqsPlacaExtensionRemplazo());
				setRqsDescripcion(requests.getRqsDescripcion());
				setRqsNit(requests.getRqsCarnetAsignatario());
				setNombreTercero(requests.getUsersByRqsUser().getUsrNombre());
				setEmailAsig(requests.getUsersByRqsUser().getUsrEmail());

				setIdRequests(requests.getRqsCodigo());
				setShowOFS(false);
				setShowCostCenter(false);
				setShowTercero(true);

			} else if (requests.getRequestsClasses().getRqcCodigo().longValue() == ViewOptionUtil.ASIGNACION
					|| requests.getLegateesTypes().getLgtCodigo().longValue() == ViewOptionUtil.PERSONAL) {

				setRequestsClasses(requests.getRequestsClasses().getRqcCodigo()
						.longValue());
				if (requests.getLegateesTypes() != null)
					setLegateesTypes(requests.getLegateesTypes().getLgtCodigo()
							.longValue());
				setRequestsTypes(requests.getRequestsTypes().getRqyCodigo()
						.longValue());
				setVehiclesTypes(requests.getVehiclesTypes().getVhtCodigo()
						.longValue());
				setRqsFechaInicial(requests.getRqsFechaInicial());
				setRqsFechaFinal(requests.getRqsFechaFinal());
				if (requests.getRqsPlacaExtensionRemplazo() != null)
					setPlaca(requests.getRqsPlacaExtensionRemplazo());
				setRqsDescripcion(requests.getRqsDescripcion());
				setRqsCarnetAsignatario(requests.getRqsCarnetAsignatario());
				setNombreAsig(requests.getUsersByRqsUser().getUsrNombre());
				setEmailAsig(requests.getUsersByRqsUser().getUsrEmail());
				if (requests.getRqsCarnetEmpleado() != null)
					setRqsCarnetEmpleado(requests.getRqsCarnetEmpleado());
				if (requests.getUsersByUsrCodigo() != null)
					setNombreAsis(requests.getUsersByUsrCodigo().getUsrNombre());
				if (requests.getUsersByUsrCodigo() != null)
					setEmailAsis(requests.getUsersByUsrCodigo().getUsrEmail());

				setIdRequests(requests.getRqsCodigo());
				setShowOFS(true);
				setShowCostCenter(false);
				setShowTercero(false);
			}

		}

		FacesUtils.getSession().removeAttribute("modificarSolicitud");
	}

	/**
	 * Gets the id requests.
	 * 
	 * @return the id requests
	 */
	public Long getIdRequests() {
		return idRequests;
	}

	/**
	 * Sets the id requests.
	 * 
	 * @param idRequests
	 *            the new id requests
	 */
	public void setIdRequests(Long idRequests) {
		this.idRequests = idRequests;
	}

	/**
	 * Gets the id zones.
	 * 
	 * @return the id zones
	 */
	public Long getIdZones() {
		return idZones;
	}

	/**
	 * Sets the id zones.
	 * 
	 * @param idZones
	 *            the new id zones
	 */
	public void setIdZones(Long idZones) {
		this.idZones = idZones;
	}

	/**
	 * Gets the txt zona.
	 * 
	 * @return the txt zona
	 */
	public HtmlOutputText getTxtZona() {
		return txtZona;
	}

	/**
	 * Sets the txt zona.
	 * 
	 * @param txtZona
	 *            the new txt zona
	 */
	public void setTxtZona(HtmlOutputText txtZona) {
		this.txtZona = txtZona;
	}

	/**
	 * Gets the cbx zones.
	 * 
	 * @return the cbx zones
	 */
	public HtmlSelectOneMenu getCbxZones() {
		return cbxZones;
	}

	/**
	 * Sets the cbx zones.
	 * 
	 * @param cbxZones
	 *            the new cbx zones
	 */
	public void setCbxZones(HtmlSelectOneMenu cbxZones) {
		this.cbxZones = cbxZones;
	}

	/**
	 * Validate min lenght asig.
	 * 
	 * @param context
	 *            the context
	 * @param validate
	 *            the validate
	 * @param value
	 *            the value
	 * @throws GWorkException
	 *             the g work exception
	 */
	public void validateMinLenghtAsig(FacesContext context,
			UIComponent validate, Object value) throws GWorkException {
		String inputText = (String) value;

		if (inputText.length() <= 1) {
			((UIInput) validate).setValid(false);
			FacesUtils.addErrorMessage(Util
					.loadErrorMessageValue("PLACA.MINIMO"));
		}
	}

	/**
	 * Gets the component.
	 * 
	 * @return the component
	 */
	public String getComponent() {
		return component;
	}

	/**
	 * Sets the component.
	 * 
	 * @param component
	 *            the new component
	 */
	public void setComponent(String component) {
		this.component = component;
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
	 * @param activarConfirmacion
	 *            the new activar confirmacion
	 */
	public void setActivarConfirmacion(boolean activarConfirmacion) {
		this.activarConfirmacion = activarConfirmacion;
	}

	/**
	 * Checks if is show confirm request.
	 * 
	 * @return true, if is show confirm request
	 */
	public boolean isShowConfirmRequest() {
		return showConfirmRequest;
	}

	/**
	 * Sets the show confirm request.
	 * 
	 * @param showConfirmRequest
	 *            the new show confirm request
	 */
	public void setShowConfirmRequest(boolean showConfirmRequest) {
		this.showConfirmRequest = showConfirmRequest;
	}

	/**
	 * Action_cancelar.
	 * 
	 * @return the string
	 */
	public String action_cancelar() {

		showBtnCancelar = false;
		setShowBtnCancelar(false);
		return NavigationResults.USER_TRAY;
	}

	/**
	 * Checks if is show btn cancelar.
	 * 
	 * @return true, if is show btn cancelar
	 */
	public boolean isShowBtnCancelar() {
		return showBtnCancelar;
	}

	/**
	 * Sets the show btn cancelar.
	 * 
	 * @param showBtnCancelar
	 *            the new show btn cancelar
	 */
	public void setShowBtnCancelar(boolean showBtnCancelar) {
		this.showBtnCancelar = showBtnCancelar;
	}

	/**
	 * Checks if is show zones.
	 * 
	 * @return true, if is show zones
	 */
	public boolean isShowZones() {
		return showZones;
	}

	/**
	 * Sets the show zones.
	 * 
	 * @param showZones
	 *            the new show zones
	 */
	public void setShowZones(boolean showZones) {
		this.showZones = showZones;
	}

	/**
	 * Gets the style class.
	 * 
	 * @return the style class
	 */
	public String getStyleClass() {
		return styleClass;
	}

	/**
	 * Sets the style class.
	 * 
	 * @param styleClass
	 *            the new style class
	 */
	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}

	/**
	 * Validar datos.
	 * 
	 * @param idRequestClasses
	 *            the id request classes
	 * @param idResquestTypes
	 *            the id resquest types
	 * @param idVehicleTypes
	 *            the id vehicle types
	 * @param idLegateeTypes
	 *            the id legatee types
	 * @param fechaDesde
	 *            the fecha desde
	 * @param fechaHasta
	 *            the fecha hasta
	 * @param descripcion
	 *            the descripcion
	 * @throws GWorkException
	 *             the g work exception
	 */
	void validarDatos(Long idRequestClasses, Long idResquestTypes,
			Long idVehicleTypes, Long idLegateeTypes, Date fechaDesde,
			Date fechaHasta, String descripcion) throws GWorkException {

		if (idResquestTypes == null || idResquestTypes.longValue() == -1L
				|| idResquestTypes.longValue() == 0L)
			throw new GWorkException(Util
					.loadErrorMessageValue("TIPOSOLICITUD.SEL"));

		if (idRequestClasses == null || idRequestClasses.longValue() == -1L
				|| idRequestClasses.longValue() == 0L)
			throw new GWorkException(Util
					.loadErrorMessageValue("CLASESOLICITUD"));

		if (idVehicleTypes == null || idVehicleTypes.longValue() == -1L
				|| idVehicleTypes.longValue() == 0L)
			throw new GWorkException(Util.loadErrorMessageValue("TIPO.VHC"));

		if (idResquestTypes == null || idResquestTypes.longValue() == -1L
				|| idResquestTypes.longValue() == 0L)
			throw new GWorkException(Util
					.loadErrorMessageValue("TIPOASIGNACION"));

		if (fechaDesde == null)
			throw new GWorkException(Util.loadErrorMessageValue("FECHAINICIO"));
		if (fechaHasta == null)
			throw new GWorkException(Util.loadErrorMessageValue("FECHAFIN"));

		if (descripcion == null || descripcion.trim().length() == 0)
			throw new GWorkException(Util.loadErrorMessageValue("DESCRIPCION"));

		if (descripcion != null
				&& descripcion.trim().length() != 0
				&& (descripcion.trim().length() < 2 || descripcion.length() > 200))
			throw new GWorkException(Util
					.loadErrorMessageValue("OBSERVACION.NOVALIDO"));

	}

	/**
	 * Listener_asignation placa.
	 * 
	 * @param event
	 *            the event
	 */
	@SuppressWarnings("null")
	public void listener_asignationPlaca(ValueChangeEvent event) {
		String placa = (String) event.getNewValue();
		VehiclesAssignation vehiclesAssignation = null;

		if (placa != null
				&& requestsTypes != null
				&& (requestsTypes.longValue() == ViewOptionUtil.REEMPLAZO || requestsTypes
						.longValue() == ViewOptionUtil.EXTENSION)) {

			Long claseSolicitud;
			try {
				if (placa != null || placa.trim().length() > 0)
					vehiclesAssignation = requestService.assignationByPlaca(
							placa.toUpperCase()).get(0);

				if (vehiclesAssignation != null
						&& vehiclesAssignation.getRequests() != null
						&& vehiclesAssignation.getRequests().getLegateesTypes() != null) {

					Long legateesType = vehiclesAssignation.getRequests()
							.getLegateesTypes().getLgtCodigo().longValue();

					htmlSelectTipoAsig.setValue(legateesType.longValue());
					htmlSelectRequestClass.setValue(vehiclesAssignation
							.getRequests().getRequestsClasses().getRqcCodigo()
							.longValue());
					htmlSelectTipoAsig.setDisabled(true);

					claseSolicitud = vehiclesAssignation.getRequests()
							.getRequestsClasses().getRqcCodigo().longValue();

					opcPantallaTipoAsignacion(legateesType);

				} else if (vehiclesAssignation.getAssignationsTypes()
						.getAstCodigo().longValue() == ViewOptionUtil.ASIGNACION_ALQUILER) {

					claseSolicitud = vehiclesAssignation.getRequests()
							.getRequestsClasses().getRqcCodigo().longValue();

					htmlSelectRequestClass.setValue(claseSolicitud);

					opcPantallaClaseSolicitud(claseSolicitud);

				}
			} catch (GWorkException e) {
				messagePage.showPopup(e.getMessage(), false);
				cbxTipSolicitud.setValue(ViewOptionUtil.TIPO_SOLICITUD);
				txtPlaca.setValue(null);
				txtPlaca.setDisabled(true);
				htmlSelectTipoAsig.setValue(new Long("-1"));
				htmlSelectTipoAsig.setDisabled(false);
				htmlSelectRequestClass.setValue(new Long("-1"));
				ocultar_asignaciones();
			}
		}
	}

	/**
	 * Gets the html select request class.
	 * 
	 * @return the html select request class
	 */
	public HtmlSelectOneMenu getHtmlSelectRequestClass() {
		return htmlSelectRequestClass;
	}

	/**
	 * Sets the html select request class.
	 * 
	 * @param htmlSelectRequestClass
	 *            the new html select request class
	 */
	public void setHtmlSelectRequestClass(
			HtmlSelectOneMenu htmlSelectRequestClass) {
		this.htmlSelectRequestClass = htmlSelectRequestClass;
	}

	/**
	 * Checks if is show auxiliaries.
	 * 
	 * @return true, if is show auxiliaries
	 */
	public boolean isShowAuxiliaries() {
		return showAuxiliaries;
	}

	/**
	 * Sets the show auxiliaries.
	 * 
	 * @param showAuxiliaries
	 *            the new show auxiliaries
	 */
	public void setShowAuxiliaries(boolean showAuxiliaries) {
		this.showAuxiliaries = showAuxiliaries;
	}

	/**
	 * Action_show auxiliares.
	 * 
	 * @param actionEvent
	 *            the action event
	 */
	public void action_showAuxiliares(ActionEvent actionEvent) {
		showAuxiliaries = true;
	}

	/**
	 * Action_close show auxiliaries.
	 * 
	 * @param actionEvent
	 *            the action event
	 */
	public void action_closeShowAuxiliaries(ActionEvent actionEvent) {

		showAuxiliaries = false;
	}

	/**
	 * Action_filtrar auxiliares.
	 * 
	 * @param actionEvent
	 *            the action event
	 */
	public void action_filtrarAuxiliares(ActionEvent actionEvent) {
		try {
			setListUsers(consultsService.auxiliaresCIAT(parametroBusquedad
					.toUpperCase(), parametroBusquedad));
		} catch (GWorkException e) {
			messagePage.showPopup(e.getMessage(), false);
		}
	}

	/**
	 * Row selector auxiliaries.
	 * 
	 * @param rowSelectorEvent
	 *            the row selector event
	 */
	public void rowSelectorAuxiliaries(RowSelectorEvent rowSelectorEvent) {
		String idUsuario = (String) idAuxiliares.getValue();

		for (Users users : listUsers) {
			if (idUsuario.equalsIgnoreCase(users.getUsrIdentificacion())) {
				setRqsNit(users.getUsrIdentificacion());
				setNombreTercero(users.getUsrNombre());
				showAuxiliaries = false;
			}
		}

		setListUsers(null);

	}

	/**
	 * Gets the id auxiliares.
	 * 
	 * @return the id auxiliares
	 */
	public HtmlOutputText getIdAuxiliares() {
		return idAuxiliares;
	}

	/**
	 * Sets the id auxiliares.
	 * 
	 * @param idAuxiliares
	 *            the new id auxiliares
	 */
	public void setIdAuxiliares(HtmlOutputText idAuxiliares) {
		this.idAuxiliares = idAuxiliares;
	}

	/**
	 * Gets the cbx tip solicitud.
	 * 
	 * @return the cbx tip solicitud
	 */
	public HtmlSelectOneMenu getCbxTipSolicitud() {
		return cbxTipSolicitud;
	}

	/**
	 * Sets the cbx tip solicitud.
	 * 
	 * @param cbxTipSolicitud
	 *            the new cbx tip solicitud
	 */
	public void setCbxTipSolicitud(HtmlSelectOneMenu cbxTipSolicitud) {
		this.cbxTipSolicitud = cbxTipSolicitud;
	}

	/**
	 * Opc pantalla clase solicitud.
	 * 
	 * @param code
	 *            the code
	 */
	public void opcPantallaClaseSolicitud(Long code) {

		if (code.equals(ViewOptionUtil.ALQUILER)) {
			showOFS = true;
			setShowOFS(true);
			showCostCenter = true;
			setShowCostCenter(true);
			showTercero = false;
			setShowTercero(false);
			htmlSelectTipoAsig.setDisabled(false);
			htmlSelectTipoAsig.setValue(-1L);
			showZones = false;
			siscarNotNull = "";
		} else if (code.equals(ViewOptionUtil.ALQUILER_TERCERO)) {

			showTercero = true;
			setShowTercero(true);
			showOFS = false;
			setShowOFS(false);
			showCostCenter = false;
			setShowCostCenter(false);
			htmlSelectTipoAsig.setValue(-1L);
			htmlSelectTipoAsig.setDisabled(true);
			showZones = false;
		} else {
			ocultar_asignaciones();
			htmlSelectTipoAsig.setDisabled(false);
			htmlSelectTipoAsig.setValue(-1L);
		}
	}

	/**
	 * Opc pantalla tipo asignacion.
	 * 
	 * @param code
	 *            the code
	 */
	public void opcPantallaTipoAsignacion(Long code) {

		if (code.equals(ViewOptionUtil.OF) || code.equals(ViewOptionUtil.OFS) || code.equals(ViewOptionUtil.OFNRS)) {
			showOFS = true;
			showCostCenter = true;
			showZones = true;
			setShowZones(true);
			showTercero = false;
			siscarNotNull = "siscarNotNull";
		} else if (code.equals(ViewOptionUtil.PROGRAMA)
				|| code.equals(ViewOptionUtil.PROYECTO)) {
			showOFS = true;
			showCostCenter = true;
			showTercero = false;
			showZones = false;
			siscarNotNull = "siscarNotNull";
		} else if (code.equals(ViewOptionUtil.CONVENIO)) {
			showOFS = false;
			showCostCenter = false;
			showTercero = true;
			showZones = false;
		} else if (code.equals(ViewOptionUtil.PERSONAL)) {
			showOFS = true;
			showCostCenter = false;
			showTercero = false;
			showZones = false;
		} else if (code != -1L) {
			showOFS = false;
			showCostCenter = false;
			showTercero = false;
			showZones = false;
		}
	}

	/**
	 * Checks if is disabled asignacion.
	 * 
	 * @return true, if is disabled asignacion
	 */
	public boolean isDisabledAsignacion() {
		return disabledAsignacion;
	}

	/**
	 * Sets the disabled asignacion.
	 * 
	 * @param disabledAsignacion
	 *            the new disabled asignacion
	 */
	public void setDisabledAsignacion(boolean disabledAsignacion) {
		this.disabledAsignacion = disabledAsignacion;
	}

	/**
	 * Gets the list costs centers requests.
	 * 
	 * @return the list costs centers requests
	 */
	public List<CostsCentersVehicles> getListCostsCentersRequests() {
		return listCostsCentersRequests;
	}

	/**
	 * Sets the list costs centers requests.
	 * 
	 * @param listCostsCentersRequests
	 *            the new list costs centers requests
	 */
	public void setListCostsCentersRequests(
			List<CostsCentersVehicles> listCostsCentersRequests) {
		this.listCostsCentersRequests = listCostsCentersRequests;
	}

	/**
	 * Gets the txt cod centro costo.
	 * 
	 * @return the txt cod centro costo
	 */
	public HtmlInputText getTxtCodCentroCosto() {
		return txtCodCentroCosto;
	}

	/**
	 * Sets the txt cod centro costo.
	 * 
	 * @param txtCodCentroCosto
	 *            the new txt cod centro costo
	 */
	public void setTxtCodCentroCosto(HtmlInputText txtCodCentroCosto) {
		this.txtCodCentroCosto = txtCodCentroCosto;
	}

	/**
	 * Gets the txt porcentaje centro costo.
	 * 
	 * @return the txt porcentaje centro costo
	 */
	public HtmlInputText getTxtPorcentajeCentroCosto() {
		return txtPorcentajeCentroCosto;
	}

	/**
	 * Sets the txt porcentaje centro costo.
	 * 
	 * @param txtPorcentajeCentroCosto
	 *            the new txt porcentaje centro costo
	 */
	public void setTxtPorcentajeCentroCosto(
			HtmlInputText txtPorcentajeCentroCosto) {
		this.txtPorcentajeCentroCosto = txtPorcentajeCentroCosto;
	}

	/**
	 * Gets the siscar not null.
	 * 
	 * @return the siscar not null
	 */
	public String getSiscarNotNull() {
		return siscarNotNull;
	}

	/**
	 * Sets the siscar not null.
	 * 
	 * @param siscarNotNull
	 *            the new siscar not null
	 */
	public void setSiscarNotNull(String siscarNotNull) {
		this.siscarNotNull = siscarNotNull;
	}

	/**
	 * Action_limpiar.
	 * 
	 * @param actionEvent
	 *            the action event
	 */
	public void action_limpiar(ActionEvent actionEvent) {
		clean_all();
		ocultar_asignaciones();
	}

	/**
	 * Gets the confirmar solicitud.
	 * 
	 * @return the confirmar solicitud
	 */
	public String getConfirmarSolicitud() {
		try {
			confirmarSolicitud = Util.loadMessageValue("CREARSOLICITUD");
		} catch (GWorkException e) {
			log.error(e.getMessage());
		}
		return confirmarSolicitud;
	}

	/**
	 * Sets the confirmar solicitud.
	 * 
	 * @param confirmarSolicitud
	 *            the new confirmar solicitud
	 */
	public void setConfirmarSolicitud(String confirmarSolicitud) {
		this.confirmarSolicitud = confirmarSolicitud;
	}

	/**
	 * Gets the advertencia alquiler.
	 * 
	 * @return the advertencia alquiler
	 */
	public String getAdvertenciaAlquiler() {

		return advertenciaAlquiler;
	}

	/**
	 * Sets the advertencia alquiler.
	 * 
	 * @param advertenciaAlquiler
	 *            the new advertencia alquiler
	 */
	public void setAdvertenciaAlquiler(String advertenciaAlquiler) {
		this.advertenciaAlquiler = advertenciaAlquiler;
	}
}