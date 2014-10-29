package geniar.siscar.view.vehicle;

import geniar.siscar.logic.vehicle.services.RequestService;
import geniar.siscar.mail.util.SendEmail;
import geniar.siscar.model.Requests;
import geniar.siscar.model.Rolls;
import geniar.siscar.persistence.RollsDAO;
import geniar.siscar.util.FacesUtils;
import geniar.siscar.util.NavigationResults;
import geniar.siscar.util.PopupMessagePage;
import geniar.siscar.util.Util;
import geniar.siscar.util.ViewOptionUtil;
import geniar.siscar.view.beanSession.RequestBean;
import gwork.exception.GWorkException;

import java.util.Date;
import java.util.List;

import javax.faces.component.html.HtmlOutputText;
import javax.faces.event.ActionEvent;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class TrayAdminPage {
	private javax.faces.component.html.HtmlOutputText idRequest;
	private RequestService requestService;
	private boolean showDevolution = false;
	private boolean showCancelacion = false;
	private boolean showConfirmarCancelar = false;
	private boolean showConfirmDevolution = false;
	private String sbMotivoDevolucion;
	private String sbMotivoCancelacion;
	private PopupMessagePage messagePage;
	private Long codigoSolicitud;
	private Long claseSolicitud;
	private Long estadoSolicitud;
	private Date fechaHasta;
	private Date fechaDesde;
	private String carnetAsignatario;
	private List<Requests> listRequest;
	private RequestBean requestBean = (RequestBean) FacesUtils
			.getManagedBean("requestBean");
	
	private static final Log log = LogFactory.getLog(TrayAdminPage.class);

	public String getCarnetAsignatario() {
		return carnetAsignatario;
	}

	public void setCarnetAsignatario(String carnetAsignatario) {
		this.carnetAsignatario = carnetAsignatario;
	}

	/**
	 * 
	 * @param event
	 */
	public void action_filter(ActionEvent event) {

		try {

			if ((carnetAsignatario == null || carnetAsignatario.trim().length() == 0)
					&& fechaDesde == null && fechaHasta == null)
				throw new GWorkException(Util
						.loadErrorMessageValue("PARAMETROBUSQUEDAD"));
			if (carnetAsignatario != null
					&& carnetAsignatario.trim().length() != 0) {

				listRequest = requestService.listRequestUser(carnetAsignatario);
				setListRequest(listRequest);
			}

			else {
				validarDatos(claseSolicitud, estadoSolicitud, fechaDesde,
						fechaHasta);

				listRequest = requestService.listRequestFilter(claseSolicitud,
						estadoSolicitud, fechaDesde, fechaHasta);
				setListRequest(listRequest);

				if (listRequest == null || listRequest.isEmpty())
					throw new GWorkException(Util
							.loadErrorMessageValue("CONSULTA"));
			}

		} catch (GWorkException e) {
			carnetAsignatario = "";
			messagePage.showPopup(e.getMessage(), false);
		}
	}

	public void action_consultar(ActionEvent actionEvent) {

		try {

			if (carnetAsignatario == null
					|| carnetAsignatario.trim().length() == 0
					&& fechaDesde == null && fechaHasta == null)
				throw new GWorkException(Util
						.loadErrorMessageValue("PARAMETROBUSQUEDAD"));

			else if (carnetAsignatario != null
					&& carnetAsignatario.trim().length() > 0
					&& (fechaDesde == null || fechaHasta == null)) {

				setListRequest(null);
				setListRequest(requestService
						.findByUserRequest(carnetAsignatario));
			}

			else if ((fechaDesde == null && fechaHasta == null)
					&& (carnetAsignatario == null || carnetAsignatario.trim()
							.length() == 0))
				throw new GWorkException(Util
						.loadErrorMessageValue("FECHAS.CONSULTA.NULO"));

			if ((carnetAsignatario == null || carnetAsignatario.trim().length() == 0)
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
				setListRequest(requestService.consultarSolicitudByFechas(
						fechaDesde, fechaHasta));
			} else if (carnetAsignatario != null
					&& carnetAsignatario.trim().length() > 0
					&& fechaDesde != null && fechaHasta != null) {

				setListRequest(null);
				setListRequest(requestService.consultarSolicitudByFechasCarne(
						carnetAsignatario, fechaDesde, fechaHasta));
			}
		} catch (GWorkException e) {
			messagePage.showPopup(e.getMessage(), false);
		}
	}

	/**
	 * 
	 * @param event
	 */
	public void action_restaurar(ActionEvent event) {
		restaurarBandeja();
	}

	/**
	 * 
	 */
	public TrayAdminPage() {
		messagePage = (PopupMessagePage) FacesUtils
				.getManagedBean("popupMessagePage");
	}

	/**
	 * 
	 * @return
	 */
	public String getSbMotivoDevolucion() {
		return sbMotivoDevolucion;
	}

	/**
	 * 
	 */
	public void setSbMotivoDevolucion(String sbMotivoDevolucion) {
		this.sbMotivoDevolucion = sbMotivoDevolucion;
	}

	/**
	 * 
	 * @return
	 */
	public RequestService getRequestService() {
		return requestService;
	}

	/**
	 * 
	 * @param requestService
	 */
	public void setRequestService(RequestService requestService) {
		this.requestService = requestService;
	}

	/**
	 * 
	 * @return
	 */

	/**
	 * Metodo de navegacion que muestra las diferentes pantallas segun el estado
	 * de la solicitud
	 * 
	 * @return
	 */
	public String action_navigationRuleTray() {
		try {
			Long codigoSolicitud = (Long) idRequest.getValue();
			Requests requests = new Requests();
			requests = getRequests(codigoSolicitud);

			if (requests.getRequestsStates().getRqtCodigo().longValue() == ViewOptionUtil.ASIGNADO
					|| requests.getRequestsStates().getRqtCodigo().longValue() == ViewOptionUtil.ALQUILADO)
				throw new GWorkException(Util
						.loadErrorMessageValue("SOLICITUDASIGNADA"));

			if (requests.getRequestsStates().getRqtCodigo().longValue() == ViewOptionUtil.ENVIADA) {
				FacesUtils.getSession().setAttribute("solicitud", requests);
				return NavigationResults.RESERVE;
			}
			if (requests.getRequestsStates().getRqtCodigo().longValue() == ViewOptionUtil.RESERVADO) {
				// FacesUtils.getSession().setAttribute("assignment", requests);
				requestBean.setRequests(requests);
				return NavigationResults.ASIGNACION;
			}

		} catch (Exception e) {
			messagePage.showPopup(e.getMessage(), false);
		}
		return null;
	}

	/**
	 * 
	 * @param codigo
	 * @return
	 */
	private Requests getRequests(Long codigo) {
		for (Requests requests : listRequest) {
			if (requests.getRqsCodigo().equals(codigo)) {
				return requests;
			}
		}
		return null;
	}

	/**
	 * Muestra motivo de devolucion;
	 * 
	 * @param event
	 */
	public void action_showDevolution(ActionEvent event) {
		codigoSolicitud = (Long) idRequest.getValue();
		Requests requests = getRequests(codigoSolicitud);
		try {
			if (requests.getRequestsStates().getRqtCodigo().longValue() == ViewOptionUtil.ALQUILADO
					|| requests.getRequestsStates().getRqtCodigo().longValue() == ViewOptionUtil.ASIGNADO)
				throw new GWorkException(Util
						.loadErrorMessageValue("ERRORSOLICITUD.ASIGANADAS"));
			else
				showDevolution = true;
		} catch (GWorkException e) {
			messagePage.showPopup(e.getMessage(), false);
		}
	}

	/**
	 * Cierra motivo de devolucion
	 * 
	 * @param event
	 */
	public void action_closeDevolution(ActionEvent event) {
		codigoSolicitud = (Long) idRequest.getValue();
		showDevolution = false;
	}

	/**
	 * Muestra motivo de Cancelacion
	 * 
	 * @param event
	 */
	public void action_showCancelacion(ActionEvent event) {
		codigoSolicitud = (Long) idRequest.getValue();
		showCancelacion = true;
	}

	/**
	 * Cierra motivo de cancelacion
	 * 
	 * @param event
	 */
	public void action_closeCancelacion(ActionEvent event) {
		codigoSolicitud = (Long) idRequest.getValue();
		showCancelacion = false;
	}

	/**
	 * Se regitra el motivo de la devolucion
	 * 
	 * @param event
	 */
	public void action_devolution(ActionEvent event) {
		try {
			Requests requests = getRequests(codigoSolicitud);
			requestService.devolverSolicitud(requests, sbMotivoDevolucion);
			String errorNotificacion = "";
			
			if (requests.getUsersByRqsUser().getUsrEmail() != null) {
				Rolls rolls = new RollsDAO().findById(new Long(Util
						.loadParametersValue("MAIL.ADMINISTRADOR")));
				
				String server = Util.loadParametersValue("MAIL.HOST");
				String fromUser = rolls.getRlsMail();
				String toUser = requests.getUsersByRqsUser().getUsrEmail();
				String subject = Util
						.loadParametersValue("MSJ.DEVOLUCION.SOLICITUD.ADMIN");
				try{
					SendEmail mail = new SendEmail(toUser, fromUser, server,
							"false", subject, sbMotivoDevolucion);
	
					if (mail.SendMessage().equals("SUCCESS"))
						log.info("Mensaje enviado exitosamente");
					else {
						log.info("Error Enviando el mensaje");
						errorNotificacion = " - " + Util.loadErrorMessageValue("NOTIFICACION.ERROR") + " al ADMINISTRADOR - ";
					}
				}catch (RuntimeException re) {
					errorNotificacion = " - " + Util.loadErrorMessageValue("NOTIFICACION.ERROR") + " al ADMINISTRADOR - " + re.getMessage();
				}
			}
			
			restaurarBandeja();
			showDevolution = false;
			showConfirmDevolution = false;
			messagePage.showPopup("Solicitud devuelta " + errorNotificacion, false);
			sbMotivoDevolucion = null;
		} catch (GWorkException e) {
			messagePage.showPopup(e.getMessage(), false);
		}
	}

	/**
	 * Se registra el motivo de la cancelacion
	 * 
	 * @param event
	 */
	public void action_cancelacion(ActionEvent event) throws GWorkException {
		showCancelacion = false;
		showConfirmarCancelar = false;
		FacesUtils.getSession().removeAttribute("assignment");
		requestService.cancelarSolicitudAdministrador(codigoSolicitud,
				sbMotivoCancelacion);
		restaurarBandeja();
		messagePage.showPopup("Solicitud cancelada", false);
		sbMotivoCancelacion = null;
	}

	public void mostrarConfirmacionCancelar(ActionEvent actionEvent) {

		try {

			if (sbMotivoCancelacion == null
					|| sbMotivoCancelacion.trim().length() == 0)
				throw new GWorkException(Util
						.loadErrorMessageValue("MOTIVOCANCELACION"));

			showConfirmarCancelar = true;

		} catch (GWorkException e) {
			messagePage.showPopup(e.getMessage(), false);
		}
	}

	public void ocultarConfirmacionCancelar(ActionEvent actionEvent) {

		showConfirmarCancelar = false;
	}

	/**
	 * Se ejecuta la pantalla de reasignacion de reserva , si el estado de
	 * solicitud es reservado
	 * 
	 * @return
	 */
	public String action_reasignar() {
		try {
			Long codigoSolicitud = (Long) idRequest.getValue();
			Requests requests = getRequests(codigoSolicitud);
			if (requests.getRequestsStates().getRqtNombre().equals(
					Util.loadMessageValue("RESERVADO"))) {
				FacesUtils.getSession().setAttribute("solicitud", requests);
				return NavigationResults.RESERVE_REALLOCATION;
			}
			throw new GWorkException(
					"No se puede reasignar el estado de la solicitud  es  "
							+ requests.getRequestsStates().getRqtNombre());
		} catch (GWorkException e) {
			messagePage.showPopup(e.getMessage(), false);
		}
		return null;
	}

	/**
	 * @return
	 */
	public HtmlOutputText getIdRequest() {
		return idRequest;
	}

	/**
	 * @param idRequest
	 */
	public void setIdRequest(HtmlOutputText idRequest) {
		this.idRequest = idRequest;
	}

	/**
	 * @return
	 */
	public boolean isShowDevolution() {
		return showDevolution;
	}

	/**
	 * @param showDevolution
	 */
	public void setShowDevolution(boolean showDevolution) {
		this.showDevolution = showDevolution;
	}

	/**
	 * @return
	 */
	public boolean isShowCancelacion() {
		return showCancelacion;
	}

	/**
	 * @param showCancelacion
	 */
	public void setShowCancelacion(boolean showCancelacion) {
		this.showCancelacion = showCancelacion;
	}

	/**
	 * @return
	 */
	public String getSbMotivoCancelacion() {
		return sbMotivoCancelacion;
	}

	/**
	 * @param sbMotivoCancelacion
	 */
	public void setSbMotivoCancelacion(String sbMotivoCancelacion) {
		this.sbMotivoCancelacion = sbMotivoCancelacion;
	}

	/**
	 * @return
	 */
	public PopupMessagePage getMessagePage() {
		return messagePage;
	}

	/**
	 * @param messagePage
	 */
	public void setMessagePage(PopupMessagePage messagePage) {
		this.messagePage = messagePage;
	}

	/**
	 * @return
	 */
	public Long getClaseSolicitud() {
		return claseSolicitud;
	}

	/**
	 * 
	 * @param claseSolicitud
	 */
	public void setClaseSolicitud(Long claseSolicitud) {
		this.claseSolicitud = claseSolicitud;
	}

	/**
	 * 
	 * @return
	 */
	public Long getEstadoSolicitud() {
		return estadoSolicitud;
	}

	/**
	 * @param estadoSolicitud
	 */
	public void setEstadoSolicitud(Long estadoSolicitud) {
		this.estadoSolicitud = estadoSolicitud;
	}

	/**
	 * @return
	 */
	public Date getFechaHasta() {
		return fechaHasta;
	}

	/**
	 * @param fechaHasta
	 */
	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	/**
	 */
	public Date getFechaDesde() {
		return fechaDesde;
	}

	/**
	 * @param fechaDesde
	 */
	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	/**
	 * @return
	 */
	public String cancelar_action() {
		return NavigationResults.BANDEJA_ADMIN;
	}

	/**
	 * @return
	 */
	public Long getCodigoSolicitud() {
		return codigoSolicitud;
	}

	/**
	 * 
	 * @param codigoSolicitud
	 */
	public void setCodigoSolicitud(Long codigoSolicitud) {
		this.codigoSolicitud = codigoSolicitud;
	}

	public void validarDatos(Long claseSolicitud, Long estadoSolicitud,
			Date fechaDesde, Date fechaHasta) throws GWorkException {

		if (claseSolicitud == null || claseSolicitud.longValue() == 0)
			throw new GWorkException("Debe ingresar la clase de solicitud");
		if (estadoSolicitud == null || estadoSolicitud == 0)
			throw new GWorkException("Debe ingresar el estado de la solicitud");
		if (fechaDesde == null)
			throw new GWorkException("Debe ingresar la fecha inicial");
		if (fechaHasta == null)
			throw new GWorkException("Debe ingresar la fecha final");

	}

	public void action_limpiar(ActionEvent actionEvent) {

		carnetAsignatario = null;
		setCarnetAsignatario(null);
		fechaDesde = null;
		setFechaDesde(null);
		fechaHasta = null;
		setFechaHasta(null);

	}

	public void restaurarBandeja() {

		try {
			if (listRequest != null)
				listRequest.clear();
			listRequest = null;
			listRequest = requestService.listRequestsState();

		} catch (GWorkException e) {
			System.out.println(e.getMessage());
		}

	}

	public List<Requests> getListRequest() {

		try {
			if (listRequest == null)
				listRequest = requestService.listRequestsState();
		} catch (GWorkException e) {
			System.out.println(e.getMessage());
		}
		return listRequest;
	}

	public void setListRequest(List<Requests> listRequest) {
		this.listRequest = listRequest;
	}

	public boolean isShowConfirmarCancelar() {
		return showConfirmarCancelar;
	}

	public void setShowConfirmarCancelar(boolean showConfirmarCancelar) {
		this.showConfirmarCancelar = showConfirmarCancelar;
	}

	public boolean isShowConfirmDevolution() {
		return showConfirmDevolution;
	}

	public void setShowConfirmDevolution(boolean showConfirmDevolution) {
		this.showConfirmDevolution = showConfirmDevolution;
	}

	public void mostraConfirmacionDevolution(ActionEvent actionEvent) {

		try {
			if (sbMotivoDevolucion == null
					|| sbMotivoDevolucion.trim().length() == 0)
				throw new GWorkException(Util
						.loadErrorMessageValue("MOTIVODEVOLUCION"));
			showConfirmDevolution = true;

		} catch (GWorkException e) {
			messagePage.showPopup(e.getMessage(), false);
		}

	}

	public void ocultaMotivoDevolucion(ActionEvent actionEvent) {
		showConfirmDevolution = false;
	}

}