package geniar.siscar.view.vehicle;

import geniar.siscar.logic.vehicle.services.RequestService;
import geniar.siscar.model.Requests;
import geniar.siscar.model.VehiclesAssignation;
import geniar.siscar.util.FacesUtils;
import geniar.siscar.util.NavigationResults;
import geniar.siscar.util.ParametersUtil;
import geniar.siscar.util.PopupMessagePage;
import geniar.siscar.util.Util;
import geniar.siscar.util.ViewOptionUtil;
import geniar.siscar.view.autenticate.LoginPage;
import gwork.exception.GWorkException;

import java.util.Date;
import java.util.List;

import javax.faces.component.html.HtmlOutputText;
import javax.faces.event.ActionEvent;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class TrayUserPage {

	private List<Requests> listRequest;
	private PopupMessagePage messagePage = (PopupMessagePage) FacesUtils
			.getManagedBean("popupMessagePage");
	private LoginPage loginPage = (LoginPage) FacesUtils
			.getManagedBean("loginPage");
	private RequestService requestService;
	private Long codigoSolicitud;
	private boolean showCancelacion = false;
	private boolean showDeliveryRequest = false;
	private boolean showModifyRequest = false;
	private boolean showCancelRequest = false;
	private HtmlOutputText idRequest;
	private String sbMotivoCancelacion;
	private String carnetAsignatario;
	private Date fechaDesde;
	private Date fechaHasta;
	private Long claseSolicitud;
	private boolean showRequestDetail = false;
	private Long codigoDetalleRQS;
	private String placa;
	private Date fechaEntrega;
	private Date fechaDevolucion;
	private Long diasAlquiler;
	private Float valorAlquiler;
	private Long kmEntrega;
	private Long kmDevolucion;
	private Long kmRecorrido;
	private Long kmAdicional;
	private Float valorKmAdicional;
	private String tipoVehiculo;
	private String obsEntrega;
	private String obsDevolucion;
	private static Log log = LogFactory.getLog(TrayUserPage.class);

	public boolean isShowRequestDetail() {
		return showRequestDetail;
	}

	public void setShowRequestDetail(boolean showRequestDetail) {
		this.showRequestDetail = showRequestDetail;
	}

	public Long getClaseSolicitud() {
		return claseSolicitud;
	}

	public void setClaseSolicitud(Long claseSolicitud) {
		this.claseSolicitud = claseSolicitud;
	}

	public Date getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public Date getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	public HtmlOutputText getIdRequest() {
		return idRequest;
	}

	public void setIdRequest(HtmlOutputText idRequest) {
		this.idRequest = idRequest;
	}

	public RequestService getRequestService() {
		return requestService;
	}

	public void setRequestService(RequestService requestService) {

		this.requestService = requestService;
	}

	public List<Requests> getListRequest() {
		try {

			if (listRequest == null) {
				if (loginPage != null && loginPage.getCarne() != null)
					listRequest = requestService
							.findByPropertyStatesCreate(loginPage.getCarne());
			}
		} catch (GWorkException e) {
			log.info(e.getMessage());
		}

		return listRequest;
	}

	public void setListRequest(List<Requests> listRequest) {
		this.listRequest = listRequest;
	}

	public void action_cancelacion(ActionEvent event) throws GWorkException {
		showCancelacion = false;
		showCancelRequest = false;
		Requests requests = getRequests(codigoSolicitud);
		requestService.cancelarSolicitudUsuario(requests, sbMotivoCancelacion);
		restaurarBandeja();
		messagePage.showPopup("Solicitud cancelada", false);
		sbMotivoCancelacion = null;
	}

	public void mostarConfirmacionCancelar(ActionEvent actionEvent) {
		try {

			if (sbMotivoCancelacion == null
					|| sbMotivoCancelacion.trim().length() == 0)
				throw new GWorkException(Util
						.loadErrorMessageValue("MOTIVOCANCELACION"));
			showCancelRequest = true;

		} catch (GWorkException e) {
			messagePage.showPopup(e.getMessage(), false);
		}
	}

	public void ocultarConfirmacionCancelar(ActionEvent actionEvent) {
		showCancelRequest = false;
	}

	public void action_deliveryRequest(ActionEvent event) throws GWorkException {
		showDeliveryRequest = false;
		Requests requests = getRequests(codigoSolicitud);
		requestService.enviarSolicitud(requests);
		restaurarBandeja();
		messagePage.showPopup("Solicitud enviada", false);
	}

	public void action_showCancelacion(ActionEvent event) {
		codigoSolicitud = (Long) idRequest.getValue();
		showCancelacion = true;
	}

	public void action_showDeliveryRequest(ActionEvent event) {
		codigoSolicitud = (Long) idRequest.getValue();
		Requests requests = getRequests(codigoSolicitud);
		try {
			if (requests.getRequestsStates().getRqtCodigo().longValue() == ViewOptionUtil.ENVIADA) {
				messagePage.showPopup(Util
						.loadErrorMessageValue("SOLICITUDENVIDA"), false);
			} else if (requests.getRequestsStates().getRqtCodigo().longValue() == ViewOptionUtil.ASIGNADO
					|| requests.getRequestsStates().getRqtCodigo().longValue() == ViewOptionUtil.ALQUILADO
					|| requests.getRequestsStates().getRqtCodigo().longValue() == ViewOptionUtil.RESERVADO) {
				throw new GWorkException(Util
						.loadErrorMessageValue("SOLICITUD.ENVIADAS"));

			} else {
				showDeliveryRequest = true;
			}
		} catch (GWorkException e) {
			messagePage.showPopup(e.getMessage(), false);
		}

	}

	public void action_closeDeliveryRequest(ActionEvent event) {
		codigoSolicitud = (Long) idRequest.getValue();
		showDeliveryRequest = false;
	}

	public void action_closeCancelacion(ActionEvent event) {
		codigoSolicitud = (Long) idRequest.getValue();
		showCancelacion = false;
	}

	public boolean isShowCancelacion() {
		return showCancelacion;
	}

	/**
	 * @param showCancelacion
	 */
	public void setShowDeliveryRequest(boolean showDeliveryRequest) {
		this.showDeliveryRequest = showDeliveryRequest;
	}

	public boolean isShowDeliveryRequest() {
		return showDeliveryRequest;
	}

	/**
	 * @param showCancelacion
	 */
	public void setShowCancelacion(boolean showCancelacion) {
		this.showCancelacion = showCancelacion;
	}

	public String getSbMotivoCancelacion() {
		return sbMotivoCancelacion;
	}

	public void setSbMotivoCancelacion(String sbMotivoCancelacion) {
		this.sbMotivoCancelacion = sbMotivoCancelacion;
	}

	private Requests getRequests(Long codigo) {
		for (Requests requests : listRequest) {
			if (requests.getRqsCodigo().equals(codigo)) {
				return requests;
			}
		}
		return null;
	}

	public PopupMessagePage getMessagePage() {
		return messagePage;
	}

	/**
	 * @param messagePage
	 */
	public void setMessagePage(PopupMessagePage messagePage) {
		this.messagePage = messagePage;
	}

	public String cancelar_action() {
		return NavigationResults.BANDEJA_ADMIN;
	}

	public void action_filter(ActionEvent event) {

		try {

			if (claseSolicitud != null
					&& claseSolicitud.longValue() == ParametersUtil.RQS_PENDIENTES) {
				setListRequest(null);
				setListRequest(requestService
						.findByPropertyStatesCreate(loginPage.getCarne()));
			}

			else if (claseSolicitud != null
					&& claseSolicitud.longValue() == ParametersUtil.RQS_CUMPLIDAS) {

				setListRequest(null);
				setListRequest(requestService
						.findByPropertyStatesCumplidas(loginPage.getCarne()));

			}

			else if (carnetAsignatario == null
					|| carnetAsignatario.trim().length() == 0
					&& fechaDesde == null && fechaHasta == null)
				throw new GWorkException(Util
						.loadErrorMessageValue("PARAMETROBUSQUEDAD"));

			else if (carnetAsignatario != null
					&& carnetAsignatario.trim().length() > 0
					&& (fechaDesde == null || fechaHasta == null)) {

				setListRequest(null);
				setListRequest(requestService.findByUserStateCreate(loginPage
						.getCarne(), carnetAsignatario));
			}

			else if ((fechaDesde == null && fechaHasta == null)
					&& (carnetAsignatario == null || carnetAsignatario.trim()
							.length() == 0))
				throw new GWorkException(Util
						.loadErrorMessageValue("FECHAS.CONSULTA.NULO"));

			else if ((carnetAsignatario == null || carnetAsignatario.trim()
					.length() == 0)
					&& (fechaDesde == null || fechaHasta == null))
				throw new GWorkException(Util
						.loadErrorMessageValue("PARAMETROBUSQUEDAD"));

			else if (fechaHasta != null && fechaDesde != null
					&& fechaHasta.compareTo(fechaDesde) < 0)
				throw new GWorkException(Util
						.loadErrorMessageValue("FCH_INI_FCH_FIN"));

			else if (carnetAsignatario == null
					|| carnetAsignatario.trim().length() == 0
					&& (fechaDesde != null && fechaHasta != null)) {

				setListRequest(null);
				setListRequest(requestService.consultarSolicitudByFechasUser(
						fechaDesde, fechaHasta, loginPage.getCarne()));
			} else if (carnetAsignatario != null
					&& carnetAsignatario.trim().length() > 0
					&& fechaDesde != null && fechaHasta != null) {

				setListRequest(null);
				setListRequest(requestService
						.consultarSolicitudByFechasCarneUser(carnetAsignatario,
								loginPage.getCarne(), fechaDesde, fechaHasta));
			}
		} catch (GWorkException e) {
			messagePage.showPopup(e.getMessage(), false);
		}

	}

	public String getCarnetAsignatario() {
		return carnetAsignatario;
	}

	public void setCarnetAsignatario(String carnetAsignatario) {
		this.carnetAsignatario = carnetAsignatario;
	}

	public void action_restart(ActionEvent actionEvent) {
		restaurarBandeja();
	}

	public void action_limpiar(ActionEvent actionEvent) {

		fechaDesde = null;
		setFechaDesde(null);
		fechaHasta = null;
		setFechaHasta(null);
		carnetAsignatario = null;
		setCarnetAsignatario(null);
		claseSolicitud = -1L;

	}

	public void restaurarBandeja() {
		try {

			setListRequest(null);
			if (loginPage != null && loginPage.getCarne() != null)
				listRequest = requestService
						.findByPropertyStatesCreate(loginPage.getCarne());
			System.out.println(loginPage.getCarne());

		} catch (GWorkException e) {
			System.out.println(e.getMessage());

		}
	}

	public void open_modifyRequest(ActionEvent actionEvent) {

		codigoSolicitud = (Long) idRequest.getValue();
		System.out.println(codigoSolicitud);
		showModifyRequest = true;

	}

	public void close_modifyRequest(ActionEvent actionEvent) {
		showModifyRequest = false;
	}

	public String action_navigationRuleSolicitud() {

		try {

			Requests requests = new Requests();
			requests = getRequests(codigoSolicitud);

			if (requests != null
					&& requests.getRequestsStates().getRqtCodigo().longValue() == ViewOptionUtil.CREADA
					|| requests != null
					&& requests.getRequestsStates().getRqtCodigo().longValue() == ViewOptionUtil.DEVUELTA) {
				FacesUtils.getSession().setAttribute("modificarSolicitud",
						requests);
				showModifyRequest = false;
				return NavigationResults.REQUESTS;

			} else {
				showModifyRequest = false;
				throw new GWorkException(Util
						.loadErrorMessageValue("SOLICITUD.MODIFICAR"));

			}

		} catch (GWorkException e) {
			messagePage.showPopup(e.getMessage(), false);
		}

		return null;
	}

	public boolean isShowModifyRequest() {
		return showModifyRequest;
	}

	public void setShowModifyRequest(boolean showModifyRequest) {
		this.showModifyRequest = showModifyRequest;
	}

	public boolean isShowCancelRequest() {
		return showCancelRequest;
	}

	public void setShowCancelRequest(boolean showCancelRequest) {
		this.showCancelRequest = showCancelRequest;
	}

	public void action_closeRequestDetail(ActionEvent actionEvent) {
		showRequestDetail = false;
	}

	public void action_openRequestDetail(ActionEvent actionEvent) {

		try {
			Long idRequest = (Long) this.idRequest.getValue();
			Requests requests = null;
			VehiclesAssignation vehiclesAssignation = null;
			if (idRequest != null) {
				requests = requestService.consultarRequest(idRequest);

				if (requests != null) {
					codigoDetalleRQS = requests.getRqsCodigo();

					vehiclesAssignation = requestService
							.findRequestByVHA(idRequest);

					if (vehiclesAssignation != null) {

						if (vehiclesAssignation.getVehicles()
								.getVhcPlacaDiplomatica() != null)
							placa = vehiclesAssignation.getVehicles()
									.getVhcPlacaDiplomatica();

						if (vehiclesAssignation.getVhaFechaEntrega() != null)
							fechaEntrega = vehiclesAssignation
									.getVhaFechaEntrega();
						if (vehiclesAssignation.getVhaFechaDev() != null)
							fechaDevolucion = vehiclesAssignation
									.getVhaFechaDev();

						if (vehiclesAssignation.getVhaDiasAlquiler() != null)
							diasAlquiler = vehiclesAssignation
									.getVhaDiasAlquiler();

						if (vehiclesAssignation.getVhaValorAlquiler() != null)
							valorAlquiler = vehiclesAssignation
									.getVhaValorAlquiler();

						if (vehiclesAssignation.getVhaKilomeActual() != null)
							kmEntrega = vehiclesAssignation
									.getVhaKilomeActual();

						if (vehiclesAssignation.getVhaKilomDev() != null)
							kmDevolucion = vehiclesAssignation.getVhaKilomDev();

						if (kmEntrega != null && kmDevolucion != null)
							kmRecorrido = kmDevolucion - kmEntrega;

						if (vehiclesAssignation.getVhaKmAdicional() != null)
							kmAdicional = vehiclesAssignation
									.getVhaKmAdicional();

						if (vehiclesAssignation.getVhaValorKmAdicional() != null)
							valorKmAdicional = vehiclesAssignation
									.getVhaValorKmAdicional();

						if (vehiclesAssignation.getVehicles() != null)
							tipoVehiculo = vehiclesAssignation.getVehicles()
									.getVehiclesTypes().getVhtNombre();

						if (vehiclesAssignation.getVhaObservacion() != null)
							obsEntrega = vehiclesAssignation
									.getVhaObservacion();

						if (vehiclesAssignation.getVhaObservacionDev() != null)
							obsDevolucion = vehiclesAssignation
									.getVhaObservacionDev();

					}

				}

				showRequestDetail = true;
			}

		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public Date getFechaEntrega() {
		return fechaEntrega;
	}

	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	public Date getFechaDevolucion() {
		return fechaDevolucion;
	}

	public void setFechaDevolucion(Date fechaDevolucion) {
		this.fechaDevolucion = fechaDevolucion;
	}

	public Float getValorAlquiler() {
		return valorAlquiler;
	}

	public void setValorAlquiler(Float valorAlquiler) {
		this.valorAlquiler = valorAlquiler;
	}

	public Long getKmEntrega() {
		return kmEntrega;
	}

	public void setKmEntrega(Long kmEntrega) {
		this.kmEntrega = kmEntrega;
	}

	public Long getKmDevolucion() {
		return kmDevolucion;
	}

	public void setKmDevolucion(Long kmDevolucion) {
		this.kmDevolucion = kmDevolucion;
	}

	public Long getKmRecorrido() {
		return kmRecorrido;
	}

	public void setKmRecorrido(Long kmRecorrido) {
		this.kmRecorrido = kmRecorrido;
	}

	public Long getKmAdicional() {
		return kmAdicional;
	}

	public void setKmAdicional(Long kmAdicional) {
		this.kmAdicional = kmAdicional;
	}

	public Float getValorKmAdicional() {
		return valorKmAdicional;
	}

	public void setValorKmAdicional(Float valorKmAdicional) {
		this.valorKmAdicional = valorKmAdicional;
	}

	public String getTipoVehiculo() {
		return tipoVehiculo;
	}

	public void setTipoVehiculo(String tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}

	public String getObsEntrega() {
		return obsEntrega;
	}

	public void setObsEntrega(String obsEntrega) {
		this.obsEntrega = obsEntrega;
	}

	public String getObsDevolucion() {
		return obsDevolucion;
	}

	public void setObsDevolucion(String obsDevolucion) {
		this.obsDevolucion = obsDevolucion;
	}

	public Long getCodigoDetalleRQS() {
		return codigoDetalleRQS;
	}

	public void setCodigoDetalleRQS(Long codigoDetalleRQS) {
		this.codigoDetalleRQS = codigoDetalleRQS;
	}

	public Long getDiasAlquiler() {
		return diasAlquiler;
	}

	public void setDiasAlquiler(Long diasAlquiler) {
		this.diasAlquiler = diasAlquiler;
	}

}
