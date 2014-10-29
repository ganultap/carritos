package geniar.siscar.view.newness;

import geniar.siscar.consults.ConsultsService;
import geniar.siscar.consults.impl.ConsultsServiceImpl;
import geniar.siscar.logic.consultas.SearchNewness;
import geniar.siscar.logic.newness.services.NewnessLegateeChangeService;
import geniar.siscar.model.CostsCentersVehicles;
import geniar.siscar.model.VehiclesAssignation;
import geniar.siscar.util.FacesUtils;
import geniar.siscar.util.PopupMessagePage;
import geniar.siscar.util.Util;
import geniar.siscar.view.BaseBean;
import geniar.siscar.view.autenticate.LoginPage;
import gwork.exception.GWorkException;

import java.util.Date;
import java.util.List;

import javax.faces.component.html.HtmlCommandButton;
import javax.faces.event.ActionEvent;

import com.icesoft.faces.component.selectinputdate.SelectInputDate;

public class NewnessLegateeChangePage extends BaseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HtmlCommandButton buttonGuardar;
	private HtmlCommandButton buttonConsultar;
	private String placa;
	private String fechaFinalAsignacion;
	private Long idCentroCostos;
	private SelectInputDate fechaFinalAsiActual;
	private Date fechaFinal;
	private List<CostsCentersVehicles> valoresCentroCostosOld;
	private List<CostsCentersVehicles> valoresCentroCostosNew;
	private String usrLogin;
	private String descripcionNovedad;
	private String asignatario;
	private String tipoAsignacion;
	private String fechaInicialAsignacion;
	private String tipoVehiculo;
	private String nombreCentro;
	private String porcentaje;
	private String carneAsignatario;
	private String carneAsistente;
	private String mjsCentroActual;
	private String mjsCentroAnterior;
	private boolean notEmpty;
	private boolean isActiveConsult;
	private boolean isActiveSave;
	private boolean isActiveLabel1;
	private boolean isActiveLabel2;
	ConsultsService consultsService;
	String login = null;
	private NewnessLegateeChangeService newnessLegateeService;

	public void consultarCentroCostos_action(ActionEvent event) {

		try {

			boolean esValido = true;

			if (placa != null && placa.trim().length() == 0)
				throw new GWorkException(Util.loadErrorMessageValue("PLACA"));

			if (placa != null && placa.trim().length() != 0)
				esValido = Util.validarCadenaCaracteresEspecialesNumLetrasGuion(placa);

			if (!esValido)
				throw new GWorkException(Util.loadErrorMessageValue("CARACTER.ESPECIAL"));

			removeIdSessionValues();
			String placaVehiculo = placa.trim();
			String idVehiculo = null;
			String idAsignacion = null;
			String idSolicitud = null;
			VehiclesAssignation assignation = newnessLegateeService.consultarDatosAsignacion(placaVehiculo);

			if (assignation == null)
				throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));

			asignatario = assignation.getRequests().getUsersByUsrCodigo().getUsrNombre();
			tipoAsignacion = assignation.getRequests().getLegateesTypes().getLgtNombre();
			fechaInicialAsignacion = assignation.getVhaFechaInicio().toString();
			fechaFinalAsignacion = assignation.getVhaFechaInicio().toString();
			tipoVehiculo = assignation.getVehicles().getVehiclesTypes().getVhtNombre();

			idVehiculo = assignation.getVehicles().getVhcCodigo().toString();
			idAsignacion = assignation.getVhaCodigo().toString();
			idSolicitud = assignation.getRequests().getRqsCodigo().toString();

			valoresCentroCostosOld = SearchNewness.ConsultarCentrosDeCostosActuales(new Long(idVehiculo), new Long(
					idAsignacion));

			if (valoresCentroCostosOld != null && valoresCentroCostosOld.size() > 0)
				setNotEmpty(true);

			setValoresCentroCostosOld(valoresCentroCostosOld);

			FacesUtils.getSession().setAttribute("idVehiculo", idVehiculo);
			FacesUtils.getSession().setAttribute("idAsignacion", idAsignacion);
			FacesUtils.getSession().setAttribute("idSolicitud", idSolicitud);
			activarLabel1();

		} catch (GWorkException e) {
			mostrarMensaje(e.getMessage());
		}
	}

	public void mostrarMensaje(String mensaje) {
		PopupMessagePage message = (PopupMessagePage) FacesUtils.getManagedBean("popupMessagePage");
		if (message != null)
			message.showPopup(mensaje, false);
	}

	public void limpiarValoresCambioAsignatario() {
		setCarneAsignatario(null);
		setCarneAsistente(null);
	}

	public void guardarDatosAsignatario_action(ActionEvent event) throws GWorkException {
		try {
			LoginPage loginPage = (LoginPage) FacesUtils.getManagedBean("loginPage");
			if (loginPage != null)
				login = loginPage.getLogin();
			
			if (carneAsignatario != null && carneAsignatario.trim().length() == 0)
				throw new GWorkException(Util.loadErrorMessageValue("novedades.carneasig"));

			if (carneAsistente != null && carneAsistente.trim().length() == 0)
				throw new GWorkException(Util.loadErrorMessageValue("novedades.carneasi"));

			carneAsignatario = consultsService.consultEmpleoyeeName(carneAsignatario);
			carneAsistente = consultsService.consultEmpleoyeeName(carneAsistente);

			String idSolicitud = FacesUtils.getSession().getAttribute("idSolicitud").toString();
			newnessLegateeService.crearNovedadCambioAsignatario(login, "", carneAsignatario, carneAsistente,
					idSolicitud, fechaFinal);
			limpiarValoresCambioAsignatario();
			mostrarMensaje(Util.loadMessageValue("EXITO"));
		} catch (Exception e) {
			mostrarMensaje(e.getMessage());
		}
	}

	public void limpiarCampos(ActionEvent event) {
		setPlaca(null);
		setNombreCentro(null);
		setPorcentaje(null);
	}

	public void guardarCentroCostos_action(ActionEvent event) throws GWorkException {
		try {

			LoginPage loginPage = (LoginPage) FacesUtils.getManagedBean("loginPage");
			if (loginPage != null)
				login = loginPage.getLogin();

			consultsService = new ConsultsServiceImpl();
			boolean esValido = true;

			if (nombreCentro != null && nombreCentro.trim().length() == 0)
				throw new GWorkException(Util.loadErrorMessageValue("NOMBRE.CENTRO"));

			if (nombreCentro != null && nombreCentro.trim().length() != 0)
				esValido = Util.validarCadenaCaracteresEspecialesNumLetrasGuion(nombreCentro);

			if (!esValido)
				throw new GWorkException(Util.loadErrorMessageValue("CARACTER.ESPECIAL"));

			if (porcentaje != null && porcentaje.trim().length() == 0)
				throw new GWorkException(Util.loadErrorMessageValue("PORCENTAJE.CENTRO"));

			if (porcentaje != null && porcentaje.trim().length() != 0)
				esValido = Util.validarCadenaCaracteresEspecialesPorcentaje(porcentaje);

			if (!esValido)
				throw new GWorkException(Util.loadErrorMessageValue("CARACTER.ESPECIAL"));

			if (fechaFinal == null)
				throw new GWorkException(Util.loadErrorMessageValue("CAMPO.VACIO"));

			int porcentaj = Util.convertirCadena(porcentaje);

			if (porcentaj > 100)
				mostrarMensaje(Util.loadErrorMessageValue("PORCENTAJE.ERROR"));

			consultsService.consultCostCenter(nombreCentro);

			removePorcentaje();
			FacesUtils.getSession().setAttribute("porcentaje", porcentaje);
			desActivarLabel1();
			activarLabel2();
			String idVehiculo = FacesUtils.getSession().getAttribute("idVehiculo").toString();
			String idAsignacion = FacesUtils.getSession().getAttribute("idAsignacion").toString();
			
			if (valoresCentroCostosNew == null)
				guardarCentrosCostos(nombreCentro, idVehiculo, idAsignacion, login);
			else if (valoresCentroCostosNew != null && valoresCentroCostosNew.size() > 0) {
				if (validarCampos(valoresCentroCostosNew, Util.convertirCadena(porcentaje)))
					guardarCentrosCostos(nombreCentro, idVehiculo, idAsignacion, login);
			}
		} catch (GWorkException e) {
			mostrarMensaje(e.getMessage());
		}
	}

	public void guardarCentrosCostos(String nombreCentro, String idVehiculo, String idAsignacion, String login)
			throws GWorkException {

		newnessLegateeService.guardarCentroCostos(nombreCentro, porcentaje, idVehiculo, idAsignacion);

		newnessLegateeService.actualizarCentrosDeCostos(valoresCentroCostosOld, login, new Long(idVehiculo));
		List<CostsCentersVehicles> valoresCentroCostosNew = SearchNewness.ConsultarCentrosDeCostosActuales(new Long(
				idVehiculo), new Long(idAsignacion));
		setValoresCentroCostosNew(valoresCentroCostosNew);
		activarGuardado();
		limpiarCamposGuardar();
	}

	public void eliminarCentroCostos(ActionEvent event) throws GWorkException {

		String idCentroCostos = (String) FacesUtils.getRequestParameter("idCentroCostos");

		for (CostsCentersVehicles costsCentersVehicles : valoresCentroCostosNew) {
			if (idCentroCostos.equalsIgnoreCase(costsCentersVehicles.getCostsCenters().getCocCodigo().toString())) {
				newnessLegateeService.eliminarCentroCostosVehiculo(costsCentersVehicles);
			}
		}
		String idVehiculo = FacesUtils.getSession().getAttribute("idVehiculo").toString();
		String idAsignacion = FacesUtils.getSession().getAttribute("idAsignacion").toString();
		List<CostsCentersVehicles> valoresCentroCostosNew = SearchNewness.ConsultarCentrosDeCostosActuales(new Long(
				idVehiculo), new Long(idAsignacion));
		setValoresCentroCostosNew(valoresCentroCostosNew);
		// removeIdSessionValues();
	}

	public void removeIdSessionValues() {
		FacesUtils.getSession().removeAttribute("idAsignacion");
		FacesUtils.getSession().removeAttribute("idVehiculo");
	}

	public void removePorcentaje() {
		FacesUtils.getSession().removeAttribute("porcentaje");
	}

	public void activarGuardado() {
		buttonConsultar.setRendered(false);
		buttonGuardar.setRendered(true);
		setActiveSave(true);
	}

	public void limpiar_action(ActionEvent event) {
		limpiarDatosNovedadCentroCosto();
	}

	public void limpiarDatosNovedadCentroCosto() {
		NewnessLegateeChangePage costCenterPage = (NewnessLegateeChangePage) FacesUtils
				.getManagedBean("newnessLegateeChangePage");
		if (costCenterPage != null) {
			FacesUtils.resetManagedBean("newnessLegateeChangePage");
		}

		if (buttonConsultar != null) {
			buttonConsultar.setRendered(true);
		}
		limpiarValoresCambioAsignatario();
	}

	public boolean validarCampos(List<CostsCentersVehicles> list, int porcentaje) throws GWorkException {
		int sum = 0;
		for (CostsCentersVehicles costsCentersVehicles : list) {
			sum += Util.convertirCadena(costsCentersVehicles.getCcrPorcentaje());
		}

		if (porcentaje == -1) {
			if (sum < 100) {
				mostrarMensaje(Util.loadErrorMessageValue("PORCENTAJE.ERROR"));
				return false;
			} else
				return true;
		}

		if (sum + porcentaje > 100) {
			mostrarMensaje(Util.loadErrorMessageValue("PORCENTAJE.ERROR"));
			return false;
		}
		return true;
	}

	public void validarCamposCentroCosto(ActionEvent event) throws GWorkException {
		if (validarCampos(valoresCentroCostosNew, -1))
			limpiarDatosNovedadCentroCosto();
	}

	public void activarLabel1() {
		setActiveLabel1(true);
	}

	public void activarLabel2() {
		setActiveLabel2(true);
	}

	public void desActivarLabel1() {
		setActiveLabel1(false);
	}

	public void desActivarLabel2() {
		setActiveLabel2(false);
	}

	public void limpiarCamposGuardar() {
		setNombreCentro(null);
		setPorcentaje(null);
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getFechaFinalAsignacion() {
		return fechaFinalAsignacion;
	}

	public void setFechaFinalAsignacion(String fechaFinalAsignacion) {
		this.fechaFinalAsignacion = fechaFinalAsignacion;
	}

	public Long getIdCentroCostos() {
		return idCentroCostos;
	}

	public void setIdCentroCostos(Long idCentroCostos) {
		this.idCentroCostos = idCentroCostos;
	}

	public String getUsrLogin() {
		return usrLogin;
	}

	public void setUsrLogin(String usrLogin) {
		this.usrLogin = usrLogin;
	}

	public String getDescripcionNovedad() {
		return descripcionNovedad;
	}

	public void setDescripcionNovedad(String descripcionNovedad) {
		this.descripcionNovedad = descripcionNovedad;
	}

	public String getAsignatario() {
		return asignatario;
	}

	public void setAsignatario(String asignatario) {
		this.asignatario = asignatario;
	}

	public String getTipoAsignacion() {
		return tipoAsignacion;
	}

	public void setTipoAsignacion(String tipoAsignacion) {
		this.tipoAsignacion = tipoAsignacion;
	}

	public String getFechaInicialAsignacion() {
		return fechaInicialAsignacion;
	}

	public void setFechaInicialAsignacion(String fechaInicialAsignacion) {
		this.fechaInicialAsignacion = fechaInicialAsignacion;
	}

	public String getTipoVehiculo() {
		return tipoVehiculo;
	}

	public void setTipoVehiculo(String tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}

	public String getNombreCentro() {
		return nombreCentro;
	}

	public void setNombreCentro(String nombreCentro) {
		this.nombreCentro = nombreCentro;
	}

	public String getPorcentaje() {
		return porcentaje;
	}

	public void setPorcentaje(String porcentaje) {
		this.porcentaje = porcentaje;
	}

	public boolean isNotEmpty() {
		return notEmpty;
	}

	public void setNotEmpty(boolean notEmpty) {
		this.notEmpty = notEmpty;
	}

	public List<CostsCentersVehicles> getValoresCentroCostosOld() {
		return valoresCentroCostosOld;
	}

	public void setValoresCentroCostosOld(List<CostsCentersVehicles> valoresCentroCostosOld) {
		this.valoresCentroCostosOld = valoresCentroCostosOld;
	}

	public List<CostsCentersVehicles> getValoresCentroCostosNew() {
		return valoresCentroCostosNew;
	}

	public void setValoresCentroCostosNew(List<CostsCentersVehicles> valoresCentroCostosNew) {
		this.valoresCentroCostosNew = valoresCentroCostosNew;
	}

	public HtmlCommandButton getButtonGuardar() {
		return buttonGuardar;
	}

	public void setButtonGuardar(HtmlCommandButton buttonGuardar) {
		this.buttonGuardar = buttonGuardar;
	}

	public HtmlCommandButton getButtonConsultar() {
		return buttonConsultar;
	}

	public void setButtonConsultar(HtmlCommandButton buttonConsultar) {
		this.buttonConsultar = buttonConsultar;
	}

	public boolean isActiveConsult() {
		return isActiveConsult;
	}

	public void setActiveConsult(boolean isActiveConsult) {
		this.isActiveConsult = isActiveConsult;
	}

	public boolean isActiveSave() {
		return isActiveSave;
	}

	public void setActiveSave(boolean isActiveSave) {
		this.isActiveSave = isActiveSave;
	}

	public String getMjsCentroActual() throws GWorkException {
		return Util.loadMessageValue("CENTRO_ACTUAL");
	}

	public void setMjsCentroActual(String mjsCentroActual) {
		this.mjsCentroActual = mjsCentroActual;
	}

	public String getMjsCentroAnterior() throws GWorkException {
		return Util.loadMessageValue("CENTRO_ANTERIOR");
	}

	public void setMjsCentroAnterior(String mjsCentroAnterior) {
		this.mjsCentroAnterior = mjsCentroAnterior;
	}

	public boolean isActiveLabel1() {
		return isActiveLabel1;
	}

	public void setActiveLabel1(boolean isActiveLabel1) {
		this.isActiveLabel1 = isActiveLabel1;
	}

	public boolean isActiveLabel2() {
		return isActiveLabel2;
	}

	public void setActiveLabel2(boolean isActiveLabel2) {
		this.isActiveLabel2 = isActiveLabel2;
	}

	public String getCarneAsignatario() {
		return carneAsignatario;
	}

	public void setCarneAsignatario(String carneAsignatario) {
		this.carneAsignatario = carneAsignatario;
	}

	public String getCarneAsistente() {
		return carneAsistente;
	}

	public void setCarneAsistente(String carneAsistente) {
		this.carneAsistente = carneAsistente;
	}

	public NewnessLegateeChangeService getNewnessLegateeService() {
		return newnessLegateeService;
	}

	public void setNewnessLegateeService(NewnessLegateeChangeService newnessLegateeService) {
		this.newnessLegateeService = newnessLegateeService;
	}

	public ConsultsService getConsultsService() {
		return consultsService = new ConsultsServiceImpl();
	}

	public void setConsultsService(ConsultsService consultsService) {
		this.consultsService = consultsService;
	}

	public SelectInputDate getFechaFinalAsiActual() {
		return fechaFinalAsiActual;
	}

	public void setFechaFinalAsiActual(SelectInputDate fechaFinalAsiActual) {
		this.fechaFinalAsiActual = fechaFinalAsiActual;
	}

	public Date getFechaFinal() {
		return fechaFinal;
	}

	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}
}
