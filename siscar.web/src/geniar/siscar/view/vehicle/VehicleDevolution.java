package geniar.siscar.view.vehicle;

import geniar.siscar.logic.consultas.SearchVehicles;
import geniar.siscar.logic.vehicle.services.AllocationReturnVehicleService;
import geniar.siscar.model.AsignationDevolution;
import geniar.siscar.model.Requests;
import geniar.siscar.model.Rolls;
import geniar.siscar.persistence.EntityManagerHelper;
import geniar.siscar.persistence.RollsDAO;
import geniar.siscar.util.FacesUtils;
import geniar.siscar.util.ParametersUtil;
import geniar.siscar.util.PopupMessagePage;
import geniar.siscar.util.Util;
import geniar.siscar.view.BaseBean;
import geniar.siscar.view.autenticate.LoginPage;
import gwork.exception.GWorkException;

import java.util.Date;
import java.util.List;

import javax.faces.event.ActionEvent;

import com.icesoft.faces.component.ext.HtmlInputText;
import com.icesoft.faces.component.ext.HtmlSelectOneMenu;

@SuppressWarnings("serial")
public class VehicleDevolution extends BaseBean {

	private String vhcPlacaDiplomatica;
	private Date fechaEntrega;
	private Long kilometraje;
	private Long kilometrajeActual;
	private Long estadoVehiculo;
	private Long tipoAsignatario;
	private String descripEstadoVehiculo;
	private String descripTipoAsignatario;
	private String vhaObservacionDev;
	private String seleccionarValor;
	private HtmlInputText inputPlaca;
	private HtmlSelectOneMenu selectEstados;
	private AllocationReturnVehicleService allocationReturnVehicleService;
	private List<AsignationDevolution> listVehiclesAssignation;
	private boolean mostrarTabla;

	public void action_crear(ActionEvent event) {
		try {
			String TipoAsignacion;
			String Nombre = null;
			String emailAsistente;
			String emailJefe;
			String emailAdminitrador;
			String errorNotificacion = "";
			LoginPage loginPage = (LoginPage) FacesUtils
					.getManagedBean("loginPage");

			if (listVehiclesAssignation == null
					|| listVehiclesAssignation.size() == 0)
				throw new GWorkException(Util
						.loadErrorMessageValue("CONSULTAR.ASIGNACION"));

			else if (listVehiclesAssignation != null
					&& listVehiclesAssignation.size() > 0) {
				for (AsignationDevolution assignation : getListVehiclesAssignation()) {

					if (assignation.getVhaObservacionDev() != null
							&& assignation.getVhaObservacionDev().trim()
									.length() == 0)
						throw new GWorkException(Util
								.loadErrorMessageValue("OBSERVACION.VACIO"));

					if (assignation.getVhaObservacionDev() != null
							&& assignation.getVhaObservacionDev().trim()
									.length() != 0)
						Util.validarLimite(assignation.getVhaObservacionDev(),
								200, 3, "OBSERVACION.MINMAX");

					// validarChecks(getListVehiclesAssignation());

					validarKilometraje(assignation.getVhaKilomDev(),
							assignation.getVhaKilomeActual(), assignation
									.isCheck());

					if (assignation.getVhaFechaDev() == null)
						throw new GWorkException(Util
								.loadErrorMessageValue("FECHA.DEV"));

					if (assignation.getVhaFechaDev().compareTo(
							assignation.getVhaFechaEntrega()) < 0)
						throw new GWorkException(Util
								.loadErrorMessageValue("FECHA.DEV.MENOR")
								+ " Fecha entrega: "
								+ assignation.getVhaFechaEntrega());

					if (assignation.getAssignationsTypes().getAstCodigo()
							.equals(new Long(Util.loadMessageValue("ESTADO.1")))) {

						if ((assignation.getVehicles().getVehiclesTypes()
								.getVhtCodigo().longValue() == ParametersUtil.TIPO_VHC_BUS
								|| assignation.getVehicles().getVehiclesTypes()
										.getVhtCodigo().longValue() == ParametersUtil.TIPO_VHC_VAN || assignation
								.getVehicles().getVehiclesTypes()
								.getVhtCodigo().longValue() == ParametersUtil.TIPO_VHC_CAMION)
								&& assignation.getVhaKilomDev().longValue() > new Long(
										Util.loadParametersValue("MAX_KM_BUS")))

							throw new GWorkException(
								Util.loadErrorMessageValue("LIMITE.KM.DEVOLUCION")
									+ ":"
									+ Util.loadParametersValue("MAX_KM_BUS"));

						else if ((assignation.getVehicles().getVehiclesTypes()
								.getVhtCodigo().longValue() == ParametersUtil.TIPO_VHC_SEDAN || 
								assignation.getVehicles().getVehiclesTypes().getVhtCodigo().longValue() == ParametersUtil.TIPO_VHC_PICK_UP || 
								assignation.getVehicles().getVehiclesTypes().getVhtCodigo().longValue() == ParametersUtil.TIPO_VHC_WAGON) && 
								assignation.getVhaKilomDev().longValue() > new Long(Util.loadParametersValue("MAX_KM_AUTOMOVIL")))
							throw new GWorkException(
									Util.loadErrorMessageValue("LIMITE.KM.DEVOLUCION")
											+ ":"
											+ Util.loadParametersValue("MAX_KM_AUTOMOVIL"));

						errorNotificacion = allocationReturnVehicleService
								.devolucionVehiculoAsignacion(assignation
										.getVhaCodigo(), vhcPlacaDiplomatica,
										assignation.getVhaFechaDev(),
										assignation.getVhaKilomDev(),
										assignation.getRequests()
												.getLegateesTypes()
												.getLgtCodigo(), assignation
												.getVhaObservacionDev(),
										loginPage.getLogin());
					} else if (assignation
							.getAssignationsTypes()
							.getAstCodigo()
							.equals(new Long(Util.loadMessageValue("ESTADO.3")))) {

						if ((assignation.getVehicles().getVehiclesTypes()
								.getVhtCodigo().longValue() == ParametersUtil.TIPO_VHC_BUS
								|| assignation.getVehicles().getVehiclesTypes()
										.getVhtCodigo().longValue() == ParametersUtil.TIPO_VHC_VAN || assignation
								.getVehicles().getVehiclesTypes()
								.getVhtCodigo().longValue() == ParametersUtil.TIPO_VHC_CAMION)
								&& assignation.getVhaKilomDev().longValue() > new Long(
										Util.loadParametersValue("MAX_KM_BUS")))

							throw new GWorkException(
									Util
											.loadErrorMessageValue("LIMITE.KM.DEVOLUCION")
											+ ":"
											+ Util
													.loadParametersValue("MAX_KM_BUS"));

						else if ((assignation.getVehicles().getVehiclesTypes()
								.getVhtCodigo().longValue() == ParametersUtil.TIPO_VHC_SEDAN
								|| assignation.getVehicles().getVehiclesTypes()
										.getVhtCodigo().longValue() == ParametersUtil.TIPO_VHC_PICK_UP || assignation
								.getVehicles().getVehiclesTypes()
								.getVhtCodigo().longValue() == ParametersUtil.TIPO_VHC_WAGON)
								&& assignation.getVhaKilomDev().longValue() > new Long(
										Util
												.loadParametersValue("MAX_KM_AUTOMOVIL")))
							throw new GWorkException(
									Util
											.loadErrorMessageValue("LIMITE.KM.DEVOLUCION")
											+ ":"
											+ Util
													.loadParametersValue("MAX_KM_AUTOMOVIL"));

						allocationReturnVehicleService
								.devolucionVehiculoAlquiler(assignation
										.getVhaCodigo(), vhcPlacaDiplomatica,
										assignation.getVhaFechaDev(),
										assignation.getVhaKilomDev(),
										assignation.getVhaObservacionDev(),
										loginPage.getLogin());
					}

					if (assignation.getRequests().getLegateesTypes() == null)
						TipoAsignacion = "";
					else
						TipoAsignacion = assignation.getRequests()
								.getLegateesTypes().getLgtNombre();

					Requests requests = EntityManagerHelper.getEntityManager()
							.merge(assignation.getRequests());

					EntityManagerHelper.getEntityManager().merge(
							assignation.getRequests());
					EntityManagerHelper.getEntityManager().merge(
							assignation.getRequests().getUsersByRqsUser());

					Nombre = requests.getUsersByRqsUser().getUsrNombre();
					if (requests.getUsersByRqsUser().getUsrApellido() != null)
						Nombre += " "
								+ requests.getUsersByUsrCodigo()
										.getUsrApellido();
					// metodo que envia la notificacion

					Rolls rolls = new RollsDAO().findById(new Long(Util
							.loadParametersValue("MAIL.ADMINISTRADOR")));

					emailAdminitrador = rolls.getRlsMail();

					try {

						allocationReturnVehicleService.envioNotificacion(
								vhcPlacaDiplomatica, TipoAsignacion, Nombre,
								requests.getRqsCarnetAsignatario(), assignation
										.getVhaFechaEntrega(), assignation
										.getVhaFechaDev(), assignation
										.getVhaKilomeActual(), assignation
										.getVhaKilomDev(), assignation
										.getVhaObservacionDev(),
								emailAdminitrador);
					} catch (RuntimeException e) {
						errorNotificacion = " - " + e.getMessage()
								+ " al ADMINISTRADOR del POOL";
					}

					try {
						if (requests.getUsersByRqsUser().getUsrEmail() != null) {
							emailJefe = requests.getUsersByRqsUser()
									.getUsrEmail();
							allocationReturnVehicleService.envioNotificacion(
									vhcPlacaDiplomatica, TipoAsignacion,
									Nombre, assignation.getRequests()
											.getRqsCarnetAsignatario(),
									assignation.getVhaFechaEntrega(),
									assignation.getVhaFechaDev(), assignation
											.getVhaKilomeActual(), assignation
											.getVhaKilomDev(), assignation
											.getVhaObservacionDev(), emailJefe);
						}
					} catch (RuntimeException e) {
						errorNotificacion += " - " + e.getMessage()
								+ " al ASIGNATARIO";
					}

					if (assignation.getRequests().getUsersByRqsUser() != assignation
							.getRequests().getUsersByUsrCodigo()
							&& assignation.getRequests().getUsersByUsrCodigo() != null) {
						try {
							emailAsistente = assignation.getRequests()
									.getUsersByUsrCodigo().getUsrEmail();

							allocationReturnVehicleService.envioNotificacion(
									vhcPlacaDiplomatica, TipoAsignacion,
									Nombre, assignation.getRequests()
											.getRqsCarnetAsignatario(),
									assignation.getVhaFechaEntrega(),
									assignation.getVhaFechaDev(), assignation
											.getVhaKilomeActual(), assignation
											.getVhaKilomDev(), assignation
											.getVhaObservacionDev(),
									emailAsistente);

						} catch (RuntimeException e) {
							errorNotificacion += " - " + e.getMessage()
									+ " al ASISTENTE";
						}
					}
				}

				listVehiclesAssignation = SearchVehicles
						.consultarEntregaVehiculoPorPlacaVehiculo(vhcPlacaDiplomatica
								.trim().toUpperCase());
				setListVehiclesAssignation(listVehiclesAssignation);
				transaccionExitosa(errorNotificacion);
			}
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void validarChecks(List<AsignationDevolution> list)
			throws GWorkException {
		int bandera = 1;
		for (AsignationDevolution assignation : list) {
			if (assignation.isCheck()) {
				bandera = 0;
			}
		}
		if (bandera == 1) {
			throw new GWorkException(Util
					.loadErrorMessageValue("SEL.DEVOLUCION"));
		}
	}

	public void validarKilometraje(Long kilometraje, Long kilometrajeActual,
			boolean check) throws GWorkException {

		String kilometrajeIngresado = kilometraje.toString();
		boolean esValido = true;
		if (kilometrajeIngresado != null
				&& kilometrajeIngresado.trim().length() != 0
				&& !kilometrajeIngresado.equalsIgnoreCase("0"))
			esValido = Util.validarNumerosConsulta(kilometrajeIngresado);
		else
			throw new GWorkException(Util.loadErrorMessageValue("KILOMETRAJE"));

		if (!esValido)
			throw new GWorkException(Util
					.loadErrorMessageValue("KIL.ACTUAL.NUM"));

		if (kilometrajeIngresado != null
				&& kilometrajeIngresado.trim().length() < 2)
			throw new GWorkException(Util
					.loadErrorMessageValue("KILOMETRAJE.MINIMO"));

		if (kilometrajeActual != null)
			if (kilometrajeActual > kilometraje)
				throw new GWorkException(Util
						.loadErrorMessageValue("KILOMETRAJE.MENOR"));

	}

	public void transaccionExitosa(String errorNotificacion)
			throws GWorkException {
		try {
			action_limpiar();
			mostrarMensaje(Util.loadMessageValue("EXITO") + errorNotificacion,
					false);
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void consultarDatosTabla(String placa) throws GWorkException {
		listVehiclesAssignation = SearchVehicles
				.consultarEntregaVehiculoPorPlacaVehiculo(placa.trim()
						.toUpperCase());
		if (listVehiclesAssignation != null
				&& listVehiclesAssignation.size() > 0) {
			setListVehiclesAssignation(listVehiclesAssignation);
			setMostrarTabla(true);
		} else
			throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));
	}

	public void consultarAsignation_action(ActionEvent event)
			throws GWorkException {
		try {

			boolean esValido = true;

			if (vhcPlacaDiplomatica != null
					&& vhcPlacaDiplomatica.trim().length() == 0)
				throw new GWorkException(Util.loadErrorMessageValue("PLACA"));

			if (vhcPlacaDiplomatica != null)
				esValido = Util.validarPlaca(vhcPlacaDiplomatica);

			if (!esValido)
				throw new GWorkException(Util
						.loadErrorMessageValue("PLACA.INCORRECTO"));

			if (vhcPlacaDiplomatica != null
					&& vhcPlacaDiplomatica.trim().length() != 0
					&& vhcPlacaDiplomatica.trim().length() < 2)
				throw new GWorkException(Util
						.loadErrorMessageValue("PLACA.MINIMO"));

			consultarDatosTabla(vhcPlacaDiplomatica);
			inputPlaca.setReadonly(true);
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void limpiar(ActionEvent event) {
		action_limpiar();
	}

	public void action_limpiar() {
		setVhcPlacaDiplomatica(null);
		setFechaEntrega(null);
		setKilometrajeActual(null);
		setEstadoVehiculo(null);
		setTipoAsignatario(null);
		setDescripEstadoVehiculo(null);
		setDescripTipoAsignatario(null);
		setKilometraje(null);
		if (inputPlaca != null)
			inputPlaca.setReadonly(false);
		setListVehiclesAssignation(null);
	}

	public void mostrarMensaje(String mensaje, boolean buttonCancel) {
		PopupMessagePage message = (PopupMessagePage) FacesUtils
				.getManagedBean("popupMessagePage");
		if (message != null)
			message.showPopup(mensaje, buttonCancel);
	}

	public String getVhcPlacaDiplomatica() {
		return vhcPlacaDiplomatica;
	}

	public void setVhcPlacaDiplomatica(String vhcPlacaDiplomatica) {
		this.vhcPlacaDiplomatica = vhcPlacaDiplomatica;
	}

	public Date getFechaEntrega() {
		return fechaEntrega;
	}

	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	public Long getEstadoVehiculo() {
		return estadoVehiculo;
	}

	public void setEstadoVehiculo(Long estadoVehiculo) {
		this.estadoVehiculo = estadoVehiculo;
	}

	public AllocationReturnVehicleService getAllocationReturnVehicleService() {
		return allocationReturnVehicleService;
	}

	public void setAllocationReturnVehicleService(
			AllocationReturnVehicleService allocationReturnVehicleService) {
		this.allocationReturnVehicleService = allocationReturnVehicleService;
	}

	public void setTipoAsignatario(Long tipoAsignatario) {
		this.tipoAsignatario = tipoAsignatario;
	}

	public Long getTipoAsignatario() {
		return tipoAsignatario;
	}

	public HtmlSelectOneMenu getSelectEstados() {
		return selectEstados;
	}

	public void setSelectEstados(HtmlSelectOneMenu selectEstados) {
		this.selectEstados = selectEstados;
	}

	public void setKilometraje(Long kilometraje) {
		this.kilometraje = kilometraje;
	}

	public Long getKilometraje() {
		return kilometraje;
	}

	public Long getKilometrajeActual() {
		return kilometrajeActual;
	}

	public void setKilometrajeActual(Long kilometrajeActual) {
		this.kilometrajeActual = kilometrajeActual;
	}

	public String getDescripEstadoVehiculo() {
		return descripEstadoVehiculo;
	}

	public void setDescripEstadoVehiculo(String descripEstadoVehiculo) {
		this.descripEstadoVehiculo = descripEstadoVehiculo;
	}

	public String getDescripTipoAsignatario() {
		return descripTipoAsignatario;
	}

	public void setDescripTipoAsignatario(String descripTipoAsignatario) {
		this.descripTipoAsignatario = descripTipoAsignatario;
	}

	public List<AsignationDevolution> getListVehiclesAssignation() {
		return listVehiclesAssignation;
	}

	public void setListVehiclesAssignation(
			List<AsignationDevolution> listVehiclesAssignation) {
		this.listVehiclesAssignation = listVehiclesAssignation;
	}

	public boolean isMostrarTabla() {
		return mostrarTabla;
	}

	public void setMostrarTabla(boolean mostrarTabla) {
		this.mostrarTabla = mostrarTabla;
	}

	public String getSeleccionarValor() {
		return seleccionarValor;
	}

	public void setSeleccionarValor(String seleccionarValor) {
		this.seleccionarValor = seleccionarValor;
	}

	public HtmlInputText getInputPlaca() {
		return inputPlaca;
	}

	public void setInputPlaca(HtmlInputText inputPlaca) {
		this.inputPlaca = inputPlaca;
	}

	public String getVhaObservacionDev() {
		return vhaObservacionDev;
	}

	public void setVhaObservacionDev(String vhaObservacionDev) {
		this.vhaObservacionDev = vhaObservacionDev;
	}
}
