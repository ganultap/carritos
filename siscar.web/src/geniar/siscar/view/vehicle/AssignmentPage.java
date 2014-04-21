package geniar.siscar.view.vehicle;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.faces.event.ActionEvent;
import com.icesoft.faces.component.ext.HtmlOutputText;
import com.icesoft.faces.component.ext.HtmlSelectOneMenu;
import geniar.siscar.consults.ConsultsService;
import geniar.siscar.logic.allocation.services.ServiceAllocation;
import geniar.siscar.model.CostsCentersVehicles;
import geniar.siscar.model.Requests;
import geniar.siscar.model.Rolls;
import geniar.siscar.persistence.EntityManagerHelper;
import geniar.siscar.persistence.RollsDAO;
import geniar.siscar.util.FacesUtils;
import geniar.siscar.util.NavigationResults;
import geniar.siscar.util.PopupMessagePage;
import geniar.siscar.util.Util;
import geniar.siscar.util.ViewOptionUtil;
import geniar.siscar.view.autenticate.LoginPage;
import geniar.siscar.view.beanSession.RequestBean;
import gwork.exception.GWorkException;

public class AssignmentPage {

	private String placa;
	private String tipo_asignacion;
	private Date fecha_inicio;
	private Date fecha_final;
	private Long idTipoUbicacion;
	private Long idUbicacion;

	private Long idZona;
	private HtmlSelectOneMenu cbxZones;
	private HtmlOutputText txtZones;
	private boolean showZones = false;
	private boolean showCobroCasaCiat = false;
	private boolean showCobroTarifa = false;
	private boolean showCostCenter = false;

	private boolean cobro = false;
	private boolean cobroCasaCiat = false;
	private Requests requests;
	private boolean activarConfirmacion;
	private RequestBean requestBean = (RequestBean) FacesUtils
			.getManagedBean("requestBean");

	private List<CostsCentersVehicles> listCostsCentersRequests = new ArrayList<CostsCentersVehicles>();

	private ConsultsService consultsService;
	private ServiceAllocation serviceAllocation;

	public List<CostsCentersVehicles> getListCostsCentersRequests() {
		return listCostsCentersRequests;
	}

	public void setListCostsCentersRequests(
			List<CostsCentersVehicles> listCostsCentersRequests) {
		this.listCostsCentersRequests = listCostsCentersRequests;
	}

	public AssignmentPage() {

		Requests requests = requestBean.getRequests();

		if (requests != null) {
			setPlaca(requests.getVehicles().getVhcPlacaDiplomatica());

			if (requests.getRequestsClasses().getRqcCodigo().longValue() == 1L)
				setTipo_asignacion(requests.getLegateesTypes().getLgtNombre());

			setFecha_inicio(requests.getRqsFechaInicial());
			setFecha_final(requests.getRqsFechaFinal());

			this.requests = requests;

			if (requests.getRequestsClasses().getRqcCodigo().longValue() == ViewOptionUtil.ALQUILER) {
				showCostCenter = true;
				cobro = true;
				showCobroTarifa = true;

				Set<CostsCentersVehicles> set = requests
						.getCostsCentersVehicleses();
				for (CostsCentersVehicles costsCentersRequests : set) {
					listCostsCentersRequests.add(costsCentersRequests);
				}

			} else if (requests.getRequestsClasses().getRqcCodigo().longValue() == ViewOptionUtil.ALQUILER_TERCERO
					.longValue()) {

				cobro = true;
				showCobroTarifa = true;
			}
			if (requests.getRequestsClasses().getRqcCodigo().longValue() == ViewOptionUtil.ASIGNACION
					&& (requests.getLegateesTypes().getLgtCodigo().longValue() == ViewOptionUtil.OF
							|| requests.getLegateesTypes().getLgtCodigo()
									.longValue() == ViewOptionUtil.OFS
							|| requests.getLegateesTypes().getLgtCodigo()
									.longValue() == ViewOptionUtil.PROGRAMA || requests
							.getLegateesTypes().getLgtCodigo().longValue() == ViewOptionUtil.PROYECTO)) {
				showCostCenter = true;

				if (requests.getLegateesTypes().getLgtCodigo().longValue() == ViewOptionUtil.PROGRAMA)
					cobro = true;
				showCobroTarifa = true;

				if (requests.getLegateesTypes().getLgtCodigo().longValue() == ViewOptionUtil.OF
						|| requests.getLegateesTypes().getLgtCodigo()
								.longValue() == ViewOptionUtil.OFS) {
					showCobroTarifa = true;
					showCobroCasaCiat = true;
					cobro = true;
					cobroCasaCiat = true;
					showZones = true;

					if (requests.getZones() != null)
						setIdZona(requests.getZones().getZnsId().longValue());

				}

				Set<CostsCentersVehicles> set = requests
						.getCostsCentersVehicleses();
				for (CostsCentersVehicles costsCentersRequests : set) {
					listCostsCentersRequests.add(costsCentersRequests);
				}
			}
		}

	}

	public void action_reserva(ActionEvent actionEvent) {

		try {

			if (requests != null) {

				// if (requests.getRequestsClasses() != null
				// && requests.getRequestsClasses().getRqcCodigo().longValue()
				// == ViewOptionUtil.ASIGNACION
				// && requests.getCostsCentersRequestses() != null
				// && requests.getCostsCentersRequestses().size() > 0)
				// consultsService.consultarDisponibilidadCC(requests);

				if (requests.getRequestsClasses() != null
						&& requests.getRequestsClasses().getRqcCodigo()
								.longValue() == ViewOptionUtil.ALQUILER
						&& requests.getCostsCentersVehicleses() != null
						&& requests.getCostsCentersVehicleses().size() > 0)

					consultsService.consultarDisponibilidadAlquilerCC(
							listCostsCentersRequests, requests.getVehicles()
									.getVehiclesTypes().getVhtCodigo().longValue(), 
									requests.getRequestsClasses().getRqcCodigo().longValue(), 
									requests.getLegateesTypes().getLgtCodigo());

				validarUbicacion(idTipoUbicacion, idUbicacion);

				if(requests.getRequestsClasses().getRqcCodigo().longValue() == ViewOptionUtil.ASIGNACION){
					if (idZona != null)
						validarZona(idZona);
				}
				
				activarConfirmacion = true;

				mostrarMensaje(Util.loadMessageValue("MENSAJE.ALERTA"),
						activarConfirmacion);

			}
		} catch (GWorkException e) {
			mostrarMensaje(e.getMessage(), false);
		}

	}

	public void asignarSolicitud() {

		try {
			String login = null;
			String errorNotificacion = "";
			String EmailPool = "";
			String EmailJefe = "";
			LoginPage loginPage = (LoginPage) FacesUtils
					.getManagedBean("loginPage");
			if (loginPage != null)
				login = loginPage.getLogin();

			if (requests != null) {
				serviceAllocation.asignacionVehiculoAsignacion(requests
						.getRqsCodigo().longValue(), idZona, idUbicacion,
						cobro, cobroCasaCiat, login);

				requests = EntityManagerHelper.getEntityManager().merge(
						requests);

				if (requests.getUsersByRqsUser() != null)
					EmailJefe = requests.getUsersByRqsUser().getUsrEmail();

				Rolls rolls = new RollsDAO().findById(new Long(Util
						.loadParametersValue("MAIL.ADMINISTRADOR")));

				EmailPool = rolls.getRlsMail();

				try {
					serviceAllocation.enviarNotificacionReserva(requests,
							cobro, listCostsCentersRequests, EmailPool);
				} catch (RuntimeException e) {
					errorNotificacion = " - " + e.getMessage()
							+ " al ADMINISTRADOR del POOL";
				}

				try {
					serviceAllocation.enviarNotificacionReserva(requests,
							cobro, listCostsCentersRequests, EmailJefe);
				} catch (RuntimeException e) {
					errorNotificacion += " - " + e.getMessage()
							+ " al ASIGNATARIO";
				}

				if (requests.getUsersByRqsUser() != requests
						.getUsersByUsrCodigo()
						&& requests.getUsersByUsrCodigo() != null) {
					try {

						String strEmailAsistente = requests
								.getUsersByUsrCodigo().getUsrEmail();

						serviceAllocation.enviarNotificacionReserva(requests,
								cobro, listCostsCentersRequests,
								strEmailAsistente);

					} catch (RuntimeException e) {
						errorNotificacion += " - " + e.getMessage()
								+ " al ASISTENTE";
					}
				}
			}

			limpiar();
			mostrarMensajeTrayAdmin(Util.loadMessageValue("EXITO")
					+ errorNotificacion);

		} catch (GWorkException e) {
			mostrarMensaje(e.getMessage(), false);
		}

	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getTipo_asignacion() {
		return tipo_asignacion;
	}

	public void setTipo_asignacion(String tipo_asignacion) {
		this.tipo_asignacion = tipo_asignacion;
	}

	public boolean isShowCostCenter() {
		return showCostCenter;
	}

	public void setShowCostCenter(boolean showCostCenter) {
		this.showCostCenter = showCostCenter;
	}

	public ConsultsService getConsultsService() {
		return consultsService;
	}

	public void setConsultsService(ConsultsService consultsService) {
		this.consultsService = consultsService;
	}

	public boolean isCobro() {
		return cobro;
	}

	public void setCobro(boolean cobro) {
		this.cobro = cobro;
	}

	public boolean isCobroCasaCiat() {
		return cobroCasaCiat;
	}

	public void setCobroCasaCiat(boolean cobroCasaCiat) {
		this.cobroCasaCiat = cobroCasaCiat;
	}

	public ServiceAllocation getServiceAllocation() {
		return serviceAllocation;
	}

	public void setServiceAllocation(ServiceAllocation serviceAllocation) {
		this.serviceAllocation = serviceAllocation;
	}

	public Date getFecha_inicio() {
		return fecha_inicio;
	}

	public void setFecha_inicio(Date fecha_inicio) {
		this.fecha_inicio = fecha_inicio;
	}

	public Date getFecha_final() {
		return fecha_final;
	}

	public void setFecha_final(Date fecha_final) {
		this.fecha_final = fecha_final;
	}

	public String action_cancelar() {
		return NavigationResults.BANDEJA_ADMIN;
	}

	public Long getIdZona() {
		return idZona;
	}

	public void setIdZona(Long idZona) {
		this.idZona = idZona;
	}

	public HtmlSelectOneMenu getCbxZones() {
		return cbxZones;
	}

	public void setCbxZones(HtmlSelectOneMenu cbxZones) {
		this.cbxZones = cbxZones;
	}

	public HtmlOutputText getTxtZones() {
		return txtZones;
	}

	public void setTxtZones(HtmlOutputText txtZones) {
		this.txtZones = txtZones;
	}

	public boolean isShowZones() {
		return showZones;
	}

	public void setShowZones(boolean showZones) {
		this.showZones = showZones;
	}

	public void validarZona(Long idZona) throws GWorkException {

		if (requests.getZones() != null
				&& (idZona == null || idZona.longValue() == -1L || idZona
						.longValue() == 0L))
			throw new GWorkException(Util.loadErrorMessageValue("ZONES.SEL"));
	}

	public void validarDatos(Long idTipoUbicacion, Long idUbicacione) {

	}

	public void limpiar() {
		listCostsCentersRequests.clear();
		cobro = false;
		cobroCasaCiat = false;
		FacesUtils.getSession().removeAttribute("assignment");
	}

	public void mostrarMensaje(String mensaje, boolean buttonCancel) {
		PopupMessagePage message = (PopupMessagePage) FacesUtils
				.getManagedBean("popupMessagePage");
		if (message != null)
			message.showPopup(mensaje, buttonCancel);
	}

	public void mostrarMensajeTrayAdmin(String mensaje) {
		PopupMessagePage message = (PopupMessagePage) FacesUtils
				.getManagedBean("popupMessagePage");
		if (message != null)
			message.showBtnTrayAdmin(mensaje);
	}

	public boolean isActivarConfirmacion() {
		return activarConfirmacion;
	}

	public void setActivarConfirmacion(boolean activarConfirmacion) {
		this.activarConfirmacion = activarConfirmacion;
	}

	public Long getIdTipoUbicacion() {
		return idTipoUbicacion;
	}

	public void setIdTipoUbicacion(Long idTipoUbicacion) {
		this.idTipoUbicacion = idTipoUbicacion;
	}

	public Long getIdUbicacion() {
		return idUbicacion;
	}

	public void setIdUbicacion(Long idUbicacion) {
		this.idUbicacion = idUbicacion;
	}

	void validarUbicacion(Long tipo, Long ubicacion) throws GWorkException {

		if (tipo == null || tipo.longValue() == -1L || tipo.longValue() == 0l)
			throw new GWorkException(Util
					.loadErrorMessageValue("TIPO.UBICACION"));

		if (ubicacion == null || ubicacion.longValue() == -1L
				|| ubicacion.longValue() == 0l)
			throw new GWorkException(Util.loadErrorMessageValue("UBICACION"));
	}

	public boolean isShowCobroCasaCiat() {
		return showCobroCasaCiat;
	}

	public void setShowCobroCasaCiat(boolean showCobroCasaCiat) {
		this.showCobroCasaCiat = showCobroCasaCiat;
	}

	public boolean isShowCobroTarifa() {
		return showCobroTarifa;
	}

	public void setShowCobroTarifa(boolean showCobroTarifa) {
		this.showCobroTarifa = showCobroTarifa;
	}
}
