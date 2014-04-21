package geniar.siscar.view.news;

import geniar.siscar.model.CostsCentersVehicles;
import geniar.siscar.model.Requests;
import geniar.siscar.model.Rolls;
import geniar.siscar.model.Vehicles;
import geniar.siscar.model.VehiclesAssignation;
import geniar.siscar.persistence.RollsDAO;
import geniar.siscar.persistence.VehiclesAssignationDAO;
import geniar.siscar.util.FacesUtils;
import geniar.siscar.util.PopupMessagePage;
import geniar.siscar.util.Util;
import geniar.siscar.view.autenticate.LoginPage;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import geniar.siscar.logic.allocation.services.ServiceAllocation;
import geniar.siscar.logic.consultas.SearchCostCenters;
import geniar.siscar.logic.consultas.SearchVehicles;
import gwork.exception.GWorkException;
import javax.faces.event.ActionEvent;
import com.icesoft.faces.component.ext.HtmlDataTable;
import com.icesoft.faces.component.ext.HtmlInputText;
import com.icesoft.faces.component.ext.HtmlInputTextarea;
import com.icesoft.faces.component.ext.HtmlOutputText;
import com.icesoft.faces.component.ext.RowSelectorEvent;
import com.icesoft.faces.component.selectinputdate.SelectInputDate;

public class DeliveryPage {

	private String carnet;
	private List<Requests> listRequests;
	private Requests requestsSeleccionada;
	private String vhaNumeroSolicitud;
	private String observacion;
	private String vhaNumeroCarne;
	private String placa;
	private Long vhaKilomeActual;
	private Long vhaKilometrajeInicial;
	private Date vhaFechaInicio;
	private Date vhaFechaTermina;
	private Date vhaFechaEntrega;
	private Long assignationsTypes;
	private Long assignationsStates;
	private Long vhaAssignation;
	private Long opcAsignacion;
	private ServiceAllocation serviceAllocation;
	public boolean showAvailability = false;
	private HtmlDataTable tblDelivery;

	private SelectInputDate txtFechaIni;
	private SelectInputDate txtFechaFin;
	private SelectInputDate txtFechaEntrega;
	private HtmlInputText txtKilometraje;
	private HtmlInputText txtKilometrajeInicial;
	private HtmlInputText txtConsulta;
	private HtmlInputText txtPlaca;
	private HtmlInputTextarea txtObservacion;
	private HtmlOutputText htmlIdAsignation;
	private List<VehiclesAssignation> listVehiclesAssignation;
	private boolean activarConfirmacion;

	public boolean isActivarConfirmacion() {
		return activarConfirmacion;
	}

	public void setActivarConfirmacion(boolean activarConfirmacion) {
		this.activarConfirmacion = activarConfirmacion;
	}

	public List<VehiclesAssignation> getListVehiclesAssignation() {
		return listVehiclesAssignation;
	}

	public void setListVehiclesAssignation(
			List<VehiclesAssignation> listVehiclesAssignation) {
		this.listVehiclesAssignation = listVehiclesAssignation;
	}

	public void action_consultar() {
		try {
			validarValor(carnet);
			listVehiclesAssignation = new ArrayList<VehiclesAssignation>();
			if (opcAsignacion != null && opcAsignacion.longValue() == 1L
					&& carnet != null && carnet.trim().length() != 0)
				listVehiclesAssignation = serviceAllocation
						.listVehiclesAssignationByUser(carnet);
			if (opcAsignacion != null && opcAsignacion.longValue() == 2L
					&& carnet != null && carnet.trim().length() != 0)
				listVehiclesAssignation = serviceAllocation
						.listRentAssignationByUser(carnet);

			if (listVehiclesAssignation != null
					&& listVehiclesAssignation.size() == 0)
				listVehiclesAssignation = SearchVehicles
						.consultarVehiculosDisponiblesEntrega(opcAsignacion);

			if (!listVehiclesAssignation.isEmpty()) {
				showAvailability = true;
			}

		} catch (GWorkException e) {
			mostrarMensaje(e.getMessage(), false);
		}
	}

	public void action_registrar(ActionEvent event) {

	}

	public String getCarnet() {
		return carnet;
	}

	public void setCarnet(String carnet) {
		this.carnet = carnet;
	}

	public List<Requests> getListRequests() {
		return listRequests;
	}

	public void setListRequests(List<Requests> listRequests) {
		this.listRequests = listRequests;
	}

	public Requests getRequestsSeleccionada() {
		return requestsSeleccionada;
	}

	public void setRequestsSeleccionada(Requests requestsSeleccionada) {
		this.requestsSeleccionada = requestsSeleccionada;
	}

	public String getVhaNumeroSolicitud() {
		return vhaNumeroSolicitud;
	}

	public void setVhaNumeroSolicitud(String vhaNumeroSolicitud) {
		this.vhaNumeroSolicitud = vhaNumeroSolicitud;
	}

	public String getVhaNumeroCarne() {
		return vhaNumeroCarne;
	}

	public void setVhaNumeroCarne(String vhaNumeroCarne) {
		this.vhaNumeroCarne = vhaNumeroCarne;
	}

	public Long getVhaKilomeActual() {
		return vhaKilomeActual;
	}

	public void setVhaKilomeActual(Long vhaKilomeActual) {
		this.vhaKilomeActual = vhaKilomeActual;
	}

	public Date getVhaFechaInicio() {
		return vhaFechaInicio;
	}

	public void setVhaFechaInicio(Date vhaFechaInicio) {
		this.vhaFechaInicio = vhaFechaInicio;
	}

	public Date getVhaFechaTermina() {
		return vhaFechaTermina;
	}

	public void setVhaFechaTermina(Date vhaFechaTermina) {
		this.vhaFechaTermina = vhaFechaTermina;
	}

	public Date getVhaFechaEntrega() {

		return vhaFechaEntrega;
	}

	public void setVhaFechaEntrega(Date vhaFechaEntrega) {
		this.vhaFechaEntrega = vhaFechaEntrega;
	}

	public Long getAssignationsTypes() {
		return assignationsTypes;
	}

	public void setAssignationsTypes(Long assignationsTypes) {
		this.assignationsTypes = assignationsTypes;
	}

	public Long getAssignationsStates() {
		return assignationsStates;
	}

	public void setAssignationsStates(Long assignationsStates) {
		this.assignationsStates = assignationsStates;
	}

	public ServiceAllocation getServiceAllocation() {
		return serviceAllocation;
	}

	public void setServiceAllocation(ServiceAllocation serviceAllocation) {
		this.serviceAllocation = serviceAllocation;
	}

	public HtmlDataTable getTblDelivery() {
		return tblDelivery;
	}

	public void setTblDelivery(HtmlDataTable tblDelivery) {
		this.tblDelivery = tblDelivery;
	}

	public void rowSelectionListener(RowSelectorEvent event) {
		Long id = (Long) htmlIdAsignation.getValue();

		for (VehiclesAssignation vehiclesAssignation : listVehiclesAssignation) {
			if (vehiclesAssignation.getVhaCodigo().longValue() == id
					.longValue()) {

				vhaAssignation = vehiclesAssignation.getVhaCodigo();
				vhaFechaInicio = vehiclesAssignation.getRequests()
						.getRqsFechaInicial();
				vhaFechaTermina = vehiclesAssignation.getRequests()
						.getRqsFechaFinal();
				placa = vehiclesAssignation.getVehicles()
						.getVhcPlacaDiplomatica();
				vhaKilometrajeInicial = vehiclesAssignation.getVehicles()
						.getVhcKilometrajeActual();
				showAvailability = false;

			}
		}

	}

	public HtmlInputText getTxtKilometraje() {
		return txtKilometraje;
	}

	public void setTxtKilometraje(HtmlInputText txtKilometraje) {
		this.txtKilometraje = txtKilometraje;
	}

	public void action_crearEntrega(ActionEvent actionEvent) {

		try {
			validarDatos(placa, vhaFechaEntrega, vhaAssignation,
					vhaKilomeActual);
			validarValor(vhaKilomeActual.toString());
			activarConfirmacion = true;
			mostrarMensaje(Util.loadMessageValue("MENSAJE.ALERTA"),
					activarConfirmacion);

		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());

		}
	}

	public void crearEntrega() {

		try {
			String login = null;
			String errorNotificacion = "";
			boolean cobro = false;
			LoginPage loginPage = (LoginPage) FacesUtils
					.getManagedBean("loginPage");
			if (loginPage != null)
				login = loginPage.getLogin();

			serviceAllocation.entregaVehiculoAsignacion(vhaAssignation,
					vhaFechaEntrega, vhaKilomeActual, observacion, login);

			Rolls rolls = new RollsDAO().findById(new Long(Util
					.loadParametersValue("MAIL.ADMINISTRADOR")));
			String strEmailAdminitrador = rolls.getRlsMail();
			VehiclesAssignation vehiclesAssignation = new VehiclesAssignation();
			vehiclesAssignation = new VehiclesAssignationDAO()
					.findById(vhaAssignation);
			List<CostsCentersVehicles> listaCentroCostoVehiculos = SearchCostCenters
					.consultarCCVehiculoEntrega(vhaAssignation);
			if (vehiclesAssignation.getVhaCobro().equals("S"))
				cobro = true;

			Requests requests = vehiclesAssignation.getRequests();
			Vehicles vehicles = vehiclesAssignation.getVehicles();
			try {
				serviceAllocation.envioNotificacion(placa, requests, vehicles
						.getLocations().getCities().getCtsId(), vehicles
						.getLocations().getLocationsTypes().getLctCodigo(),
						cobro, vhaKilomeActual, listaCentroCostoVehiculos,
						strEmailAdminitrador, strEmailAdminitrador);
			} catch (RuntimeException e) {
				errorNotificacion = " - " + e.getMessage()
						+ " al ADMINISTRADOR del POOL";
			}

			try {
				// envio de mensaje de notificacion para el asignatario
				if (requests.getUsersByRqsUser().getUsrEmail() != null) {

					String strEmailJefe = requests.getUsersByRqsUser()
							.getUsrEmail();

					serviceAllocation.envioNotificacion(placa, requests,
							vehicles.getLocations().getCities().getCtsId(),
							vehicles.getLocations().getLocationsTypes()
									.getLctCodigo(), cobro, vhaKilomeActual,
							listaCentroCostoVehiculos, strEmailJefe,
							strEmailAdminitrador);
				}
			} catch (RuntimeException e) {
				errorNotificacion += " - " + e.getMessage() + " al ASIGNATARIO";
			}

			if (requests.getUsersByRqsUser() != requests.getUsersByUsrCodigo()
					&& requests.getUsersByUsrCodigo() != null) {
				try {
					// envio de mensaje de notificacion para auxiliar

					String strEmailAsistente = requests.getUsersByUsrCodigo()
							.getUsrEmail();

					serviceAllocation.envioNotificacion(placa, requests,
							vehicles.getLocations().getCities().getCtsId(),
							vehicles.getLocations().getLocationsTypes()
									.getLctCodigo(), cobro, vhaKilomeActual,
							listaCentroCostoVehiculos, strEmailAsistente,
							strEmailAdminitrador);

				} catch (RuntimeException e) {
					errorNotificacion += " - " + e.getMessage()
							+ " al ASISTENTE";
				}
			}
			limpiar();
			mostrarMensaje(Util.loadMessageValue("VEHICULO.ENTREGADO")
					+ errorNotificacion, false);
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

	}

	public Long getVhaAssignation() {
		return vhaAssignation;
	}

	public void setVhaAssignation(Long vhaAssignation) {
		this.vhaAssignation = vhaAssignation;
	}

	public HtmlOutputText getHtmlIdAsignation() {
		return htmlIdAsignation;
	}

	public void setHtmlIdAsignation(HtmlOutputText htmlIdAsignation) {
		this.htmlIdAsignation = htmlIdAsignation;
	}

	public boolean isShowAvailability() {
		return showAvailability;
	}

	public void setShowAvailability(boolean showAvailability) {
		this.showAvailability = showAvailability;
	}

	public void action_closeAvailability(ActionEvent event) {

		showAvailability = false;
	}

	public void limpiar() {

		if (carnet != null)
			setCarnet(null);

		if (txtPlaca != null)
			txtPlaca.setValue(null);

		if (placa != null)
			setPlaca(null);

		if (vhaKilomeActual != null)
			setVhaKilomeActual(null);

		if (txtKilometraje != null)
			txtKilometraje.setValue(null);

		if (vhaKilometrajeInicial != null)
			setVhaKilometrajeInicial(null);

		if (txtKilometrajeInicial != null)
			txtKilometrajeInicial.setValue(null);

		if (txtFechaIni != null)
			txtFechaIni.setValue(null);

		if (vhaFechaInicio != null)
			setVhaFechaInicio(null);

		if (txtFechaFin != null)
			txtFechaFin.setValue(null);

		if (vhaFechaTermina != null)
			setVhaFechaTermina(null);

		if (txtFechaEntrega != null)
			txtFechaEntrega.setValue(null);

		if (vhaFechaEntrega != null)
			setVhaFechaEntrega(null);

		if (txtConsulta != null)
			txtConsulta.setValue(null);

		setListVehiclesAssignation(null);

		if (txtObservacion != null)
			txtObservacion.setValue(null);

		if (observacion != null)
			setObservacion(null);

		if (vhaAssignation != null)
			setVhaAssignation(null);

		if (opcAsignacion != null)
			setOpcAsignacion(null);
	}

	public HtmlInputText getTxtConsulta() {
		return txtConsulta;
	}

	public void setTxtConsulta(HtmlInputText txtConsulta) {
		this.txtConsulta = txtConsulta;
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

	public void action_limpiar(ActionEvent event) {

		limpiar();
	}

	public void mostrarMensaje(String mensaje, boolean buttonCancel) {
		PopupMessagePage message = (PopupMessagePage) FacesUtils
				.getManagedBean("popupMessagePage");
		if (message != null)
			message.showPopup(mensaje, buttonCancel);
	}

	public void mostrarMensajeNotificacion(String mensaje) {
		PopupMessagePage message = (PopupMessagePage) FacesUtils
				.getManagedBean("popupMessagePage");
		if (message != null)
			message.showBtnTrayAdmin(mensaje);
	}

	public SelectInputDate getTxtFechaEntrega() {
		return txtFechaEntrega;
	}

	public void setTxtFechaEntrega(SelectInputDate txtFechaEntrega) {
		this.txtFechaEntrega = txtFechaEntrega;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public HtmlInputTextarea getTxtObservacion() {
		return txtObservacion;
	}

	public void setTxtObservacion(HtmlInputTextarea txtObservacion) {
		this.txtObservacion = txtObservacion;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public HtmlInputText getTxtPlaca() {
		return txtPlaca;
	}

	public void setTxtPlaca(HtmlInputText txtPlaca) {
		this.txtPlaca = txtPlaca;
	}

	public Long getVhaKilometrajeInicial() {
		return vhaKilometrajeInicial;
	}

	public void setVhaKilometrajeInicial(Long vhaKilometrajeInicial) {
		this.vhaKilometrajeInicial = vhaKilometrajeInicial;
	}

	public Long getOpcAsignacion() {
		return opcAsignacion;
	}

	public void setOpcAsignacion(Long opcAsignacion) {
		this.opcAsignacion = opcAsignacion;
	}

	public HtmlInputText getTxtKilometrajeInicial() {
		return txtKilometrajeInicial;
	}

	public void setTxtKilometrajeInicial(HtmlInputText txtKilometrajeInicial) {
		this.txtKilometrajeInicial = txtKilometrajeInicial;
	}

	public void validarDatos(String placa, Date vhaFechaEntrega,
			Long vhaAssignation, Long vhaKilomeActual) throws GWorkException {

		if (placa == null || placa.trim().length() == 0)
			throw new GWorkException(Util.loadErrorMessageValue("PLACA"));
		if (vhaFechaEntrega == null)
			throw new GWorkException(Util
					.loadErrorMessageValue("FECHA_ENTREGA"));
		if (vhaAssignation == null || vhaAssignation.longValue() == 0)
			throw new GWorkException(Util
					.loadErrorMessageValue("ASIGNACIONNOEXISTE"));
		if (vhaKilomeActual == null || vhaKilomeActual.longValue() == 0L)
			throw new GWorkException(Util
					.loadErrorMessageValue("KILOMETRAJE.ENTREGA"));
	}

	public void validarValor(String valor) throws GWorkException {
		boolean esValido = true;

		esValido = Util.validarNumerosParametros(valor);

		if (!esValido)
			throw new GWorkException(Util
					.loadErrorMessageValue("CARACTER.NUMERO"));

	}

}
