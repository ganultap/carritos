package geniar.siscar.view.vehicle;

import java.util.List;
import javax.faces.event.ActionEvent;
import com.icesoft.faces.component.ext.HtmlOutputText;
import com.icesoft.faces.component.ext.HtmlSelectOneMenu;
import com.icesoft.faces.component.ext.RowSelectorEvent;
import geniar.siscar.consults.ConsultsService;
import geniar.siscar.logic.consultas.SearchVehicles;
import geniar.siscar.logic.vehicle.services.AssignmentChangeService;
import geniar.siscar.model.Users;
import geniar.siscar.model.VehiclesAssignation;
import geniar.siscar.util.FacesUtils;
import geniar.siscar.util.PopupMessagePage;
import geniar.siscar.util.Util;
import geniar.siscar.util.ViewOptionUtil;
import gwork.exception.GWorkException;

/**
 * @author Diego Humberto Bocanegra
 * 
 */
public class AssignmentChangePage {

	private String placa;
	private String asignatario;
	private String fechaInicialAsignacion;
	private String fechaFinalAsignacion;
	private String tipoAsignacion;
	private String idTipoAsignacion;
	private String tipoVehiculo;
	private String correoAsistente;
	private String correoAsignatario;
	private String asistente;
	private Long idZone;
	private String parametroBusquedad;
	private boolean showEmployees = false;
	private Long opcFindEmpleado;
	private List<Users> listUsers;
	private String rqsCarnetEmpleado;
	private String rqsCarnetAsignatario;
	private boolean showConfirmarNovedadAsignacion = false;
	private Integer opcConfirmacion;
	private Integer GuardarAsignatario = 1;
	private HtmlOutputText idUser;
	private HtmlSelectOneMenu cbxZones;
	private AssignmentChangeService assignmentChangeService;
	private String idUsuario = null;

	private static VehiclesAssignation vehiclesAssignation = null;
	private ConsultsService consultsService;

	public void mostrarMensaje(String mensaje, boolean buttonCancel) {
		PopupMessagePage message = (PopupMessagePage) FacesUtils
				.getManagedBean("popupMessagePage");
		if (message != null)
			message.showPopup(mensaje, buttonCancel);
	}

	/**
	 * Metodo para consultar el tipo de ubicacion ciudad y pais de un vehiculo
	 * 
	 * @param evento
	 */
	public void action_consultar(ActionEvent event) {
		try {
			if (placa != null && placa.trim().length() != 0
					&& placa.trim().length() < 2)
				throw new GWorkException(Util
						.loadErrorMessageValue("PLACA.MINIMO"));

			if (placa.trim().length() > 15)
				throw new GWorkException(Util
						.loadErrorMessageValue("PLACA.MINIMO"));

			vehiclesAssignation = SearchVehicles
					.consultarAsignacionVehiculo(placa.toUpperCase().trim());

			if (vehiclesAssignation == null) {
				limpiarDatos();
				throw new GWorkException(Util
						.loadErrorMessageValue("DATOS.NULL"));
			}

			String nombreAsignatario = vehiclesAssignation.getRequests()
					.getUsersByRqsUser().getUsrNombre();
			if (vehiclesAssignation.getRequests().getUsersByRqsUser()
					.getUsrApellido() != null)
				nombreAsignatario += " "
						+ vehiclesAssignation.getRequests().getUsersByRqsUser()
								.getUsrApellido();

			asignatario = nombreAsignatario;
			correoAsignatario = vehiclesAssignation.getRequests()
					.getUsersByRqsUser().getUsrEmail();

			if (vehiclesAssignation.getRequests().getUsersByUsrCodigo() != null) {
				String nombreAsistente = vehiclesAssignation.getRequests()
						.getUsersByUsrCodigo().getUsrNombre();
				if (vehiclesAssignation.getRequests().getUsersByUsrCodigo()
						.getUsrApellido() != null)
					nombreAsistente += " "
							+ vehiclesAssignation.getRequests()
									.getUsersByUsrCodigo().getUsrApellido();

				asistente = nombreAsistente;

				correoAsistente = vehiclesAssignation.getRequests()
						.getUsersByUsrCodigo().getUsrEmail();
			}

			if (vehiclesAssignation.getRequests().getLegateesTypes() != null) {
				tipoAsignacion = vehiclesAssignation.getRequests()
						.getLegateesTypes().getLgtNombre();
				
				idTipoAsignacion = vehiclesAssignation.getRequests()
				.getLegateesTypes().getLgtCodigo().toString();
			}

			fechaInicialAsignacion = vehiclesAssignation.getRequests()
					.getRqsFechaInicial().toString();
			fechaFinalAsignacion = vehiclesAssignation.getRequests()
					.getRqsFechaFinal().toString();

			if (vehiclesAssignation.getRequests().getZones() != null)
				cbxZones.setValue(vehiclesAssignation.getRequests().getZones()
						.getZnsId());

			if (vehiclesAssignation.getVehicles().getVehiclesTypes() != null)
				tipoVehiculo = vehiclesAssignation.getVehicles()
						.getVehiclesTypes().getVhtNombre();

		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	/**
	 * @param event
	 * 
	 */
	public void action_consultar_asistente(ActionEvent event) {

		showEmployees = true;
		setOpcFindEmpleado(ViewOptionUtil.FINDASISTENTE);
	}

	/**
	 * 
	 * @param actionEvent
	 */
	public void action_clean_asignatario(ActionEvent actionEvent) {
		asignatario = "";
		correoAsignatario = "";
	}

	/**
	 * 
	 * @param actionEvent
	 */
	public void action_clean_asistente(ActionEvent actionEvent) {
		asistente = "";
		correoAsistente = "";
	}

	public void rowSelectionEmployee(RowSelectorEvent rowSelectorEvent) {
		idUsuario = (String) idUser.getValue();

		try {
			if (opcFindEmpleado != null
					&& opcFindEmpleado.longValue() == ViewOptionUtil.FINDASIGNATARIO) {
				for (Users users : listUsers) {

					if (idUsuario
							.equalsIgnoreCase(users.getUsrIdentificacion())) {
						setRqsCarnetAsignatario(users.getUsrIdentificacion());
						setAsignatario(users.getUsrNombre());
						String email = consultsService
								.consultEmpleoyeeEmail(users
										.getUsrIdentificacion());
						if (email != null)
							setCorreoAsignatario(email.toUpperCase());
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
						setAsistente(users.getUsrNombre());
						String email = consultsService
								.consultEmpleoyeeEmail(users
										.getUsrIdentificacion());
						if (email != null)
							setCorreoAsistente(email.toUpperCase());
						showEmployees = false;
					}
				}
			}

		} catch (GWorkException e) {
			mostrarMensaje(e.getMessage(), false);
		}

		setListUsers(null);
		setOpcFindEmpleado(null);
	}

	public void action_filtroBusquedaEmpleado(ActionEvent actionEvent) {
		try {
			setListUsers(consultsService.employeesCIAT(parametroBusquedad
					.toUpperCase(), parametroBusquedad.toUpperCase(),
					parametroBusquedad));
		} catch (GWorkException e) {
			mostrarMensaje(e.getMessage(), false);
		}
	}

	public void action_closeConfirmation(ActionEvent actionEvent) {
		showConfirmarNovedadAsignacion = false;
	}

	public void validarDatos() throws GWorkException {

		if ((idZone == null || idZone.longValue() == -1L
				|| idZone.longValue() == 0L) && (idTipoAsignacion == "1" || idTipoAsignacion == "2"))
			throw new GWorkException(Util.loadErrorMessageValue("ZONES.SEL"));

		if (placa != null && placa.trim().length() != 0
				&& placa.trim().length() < 2)
			throw new GWorkException(Util.loadErrorMessageValue("PLACA.MINIMO"));

		if (placa.trim().length() > 6)
			throw new GWorkException(Util.loadErrorMessageValue("PLACA.MINIMO"));

		if (!Util.validarPlaca(placa.toString()))
			throw new GWorkException(Util
					.loadErrorMessageValue("PLACA.FORMATO"));

		if (correoAsignatario == null || correoAsignatario.trim().length() == 0)
			throw new GWorkException(Util
					.loadErrorMessageValue("EMAILASIG.NULO"));

		if (!Util.validarEmail(correoAsignatario))
			throw new GWorkException(Util
					.loadErrorMessageValue("EMAIL.NOVALIDO"));
	}

	public void action_guardar(ActionEvent actionEvent) throws GWorkException {
		try {
			validarDatos();
			showConfirmarNovedadAsignacion = true;
			setOpcConfirmacion(getGuardarAsignatario());
			mostrarMensaje(Util.loadMessageValue("MENSAJE.ALERTA"),
					showConfirmarNovedadAsignacion);
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void GuardarAsignatario() throws GWorkException {
		try {
			assignmentChangeService.modificarAsignatarioAsignacion(
					vehiclesAssignation, correoAsignatario, idZone, idUsuario,
					correoAsistente, asistente);
			limpiarDatos();
			mostrarMensaje(Util.loadMessageValue("EXITO"), false);
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void action_limpiar(ActionEvent actionEvent) throws GWorkException {
		try {
			limpiarDatos();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void limpiarDatos() {
		placa = null;
		tipoAsignacion = null;
		tipoVehiculo = null;
		idZone = null;
		asignatario = null;
		correoAsignatario = null;
		asistente = null;
		correoAsistente = null;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getAsignatario() {
		return asignatario;
	}

	public void setAsignatario(String asignatario) {
		this.asignatario = asignatario;
	}

	public String getFechaInicialAsignacion() {
		return fechaInicialAsignacion;
	}

	public void setFechaInicialAsignacion(String fechaInicialAsignacion) {
		this.fechaInicialAsignacion = fechaInicialAsignacion;
	}

	public String getFechaFinalAsignacion() {
		return fechaFinalAsignacion;
	}

	public void setFechaFinalAsignacion(String fechaFinalAsignacion) {
		this.fechaFinalAsignacion = fechaFinalAsignacion;
	}

	public String getTipoAsignacion() {
		return tipoAsignacion;
	}

	public void setTipoAsignacion(String tipoAsignacion) {
		this.tipoAsignacion = tipoAsignacion;
	}

	public String getTipoVehiculo() {
		return tipoVehiculo;
	}

	public void setTipoVehiculo(String tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}

	public String getCorreoAsistente() {
		return correoAsistente;
	}

	public void setCorreoAsistente(String correoAsistente) {
		this.correoAsistente = correoAsistente;
	}

	public String getAsistente() {
		return asistente;
	}

	public void setAsistente(String asistente) {
		this.asistente = asistente;
	}

	public String getCorreoAsignatario() {
		return correoAsignatario;
	}

	public void setCorreoAsignatario(String correoAsignatario) {
		this.correoAsignatario = correoAsignatario;
	}

	public AssignmentChangeService getAssignmentChangeService() {
		return assignmentChangeService;
	}

	public void setAssignmentChangeService(
			AssignmentChangeService assignmentChangeService) {
		this.assignmentChangeService = assignmentChangeService;
	}

	public void closeShowEmployees(ActionEvent actionEvent) {
		showEmployees = false;
	}

	public boolean isShowEmployees() {
		return showEmployees;
	}

	public void setShowEmployees(boolean showEmployees) {
		this.showEmployees = showEmployees;
	}

	public Long getOpcFindEmpleado() {
		return opcFindEmpleado;
	}

	public void setOpcFindEmpleado(Long opcFindEmpleado) {
		this.opcFindEmpleado = opcFindEmpleado;
	}

	public List<Users> getListUsers() {
		return listUsers;
	}

	public void setListUsers(List<Users> listUsers) {
		this.listUsers = listUsers;
	}

	public String getRqsCarnetEmpleado() {
		return rqsCarnetEmpleado;
	}

	public void setRqsCarnetEmpleado(String rqsCarnetEmpleado) {
		this.rqsCarnetEmpleado = rqsCarnetEmpleado;
	}

	public String getRqsCarnetAsignatario() {
		return rqsCarnetAsignatario;
	}

	public void setRqsCarnetAsignatario(String rqsCarnetAsignatario) {
		this.rqsCarnetAsignatario = rqsCarnetAsignatario;
	}

	public HtmlOutputText getIdUser() {
		return idUser;
	}

	public void setIdUser(HtmlOutputText idUser) {
		this.idUser = idUser;
	}

	public String getParametroBusquedad() {
		return parametroBusquedad;
	}

	public void setParametroBusquedad(String parametroBusquedad) {
		this.parametroBusquedad = parametroBusquedad;
	}

	public boolean isShowConfirmarNovedadAsignacion() {
		return showConfirmarNovedadAsignacion;
	}

	public void setShowConfirmarNovedadAsignacion(
			boolean showConfirmarNovedadAsignacion) {
		this.showConfirmarNovedadAsignacion = showConfirmarNovedadAsignacion;
	}

	public Long getIdZone() {
		return idZone;
	}

	public void setIdZone(Long idZone) {
		this.idZone = idZone;
	}

	public HtmlSelectOneMenu getCbxZones() {
		return cbxZones;
	}

	public void setCbxZones(HtmlSelectOneMenu cbxZones) {
		this.cbxZones = cbxZones;
	}

	public Integer getOpcConfirmacion() {
		return opcConfirmacion;
	}

	public void setOpcConfirmacion(Integer opcConfirmacion) {
		this.opcConfirmacion = opcConfirmacion;
	}

	public Integer getGuardarAsignatario() {
		return GuardarAsignatario;
	}

	public void setGuardarAsignatario(Integer guardarAsignatario) {
		GuardarAsignatario = guardarAsignatario;
	}

	public ConsultsService getConsultsService() {
		return consultsService;
	}

	public void setConsultsService(ConsultsService consultsService) {
		this.consultsService = consultsService;
	}

	public String getIdTipoAsignacion() {
		return idTipoAsignacion;
	}

	public void setIdTipoAsignacion(String idTipoAsignacion) {
		this.idTipoAsignacion = idTipoAsignacion;
	}
}
