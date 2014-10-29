package geniar.siscar.view.accidents;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import com.icesoft.faces.component.ext.HtmlCommandButton;
import com.icesoft.faces.component.ext.HtmlInputText;
import com.icesoft.faces.component.ext.HtmlInputTextarea;
import com.icesoft.faces.component.ext.HtmlOutputText;
import com.icesoft.faces.component.ext.HtmlSelectOneMenu;
import com.icesoft.faces.component.ext.RowSelectorEvent;
import com.icesoft.faces.component.paneltabset.PanelTab;
import geniar.siscar.consults.ConsultsService;
import geniar.siscar.logic.accidents.services.DataAccidentsService;
import geniar.siscar.model.Accidents;
import geniar.siscar.model.CostsCenters;
import geniar.siscar.model.Driver;
import geniar.siscar.model.Users;
import geniar.siscar.model.VehiclesAssignation;
import geniar.siscar.util.FacesUtils;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

public class DataAccidentsPage extends AccidentGeneralsPage {

	private Long accCodigo;
	private String placa;
	private Date accFechaAccidente;
	private Long idCities;
	private String accSitioAccidente;
	private String accNumeroSiniestro;
	private String accVehiculosInvolucrados = null;
	private String accTotalMuertos;
	private String accTotalHeridos = null;
	private String accTotalTestigos = null;
	private String accNombreConduct;
	private String accCargoConduct;
	private String accCodCargoAcc;
	private String accDescripcion;
	private Long resultados;
	private Long responsibility;
	private Long severity;
	private Long estadoAccidente;
	private String accReclamo;
	private String accValorDano;
	private String accDeduciblesPesos;
	private DataAccidentsService dataAccidentsService;
	private String aplicaTransito;
	private String parametroBusquedad;
	private String accDeducible;
	private String accCargoDeducible;
	private Long accTipoAsignacion;
	private String nombreAsignacion;
	private String accNombreAsignatario;
	private String accCedulaConduc;
	private String filtroConductorTercero;
	private String sanciones;
	private String ordenTrabajo;
	private String accRecomendaciones;
	private String accObservaciones;
	private Long codigoAccConsulta;
	private String email;
	private String hora;

	private String placaConsulta;
	private Date fechaAccidenteConsulta;
	private String numSiniestroConsulta;
	private String nombreConsulta;

	private String accUso;
	private String parametroCentroCostos;
	private String parametroBusquedadTercero;
	private String filtroPlaca;
	private String filtroNumSiniestro;
	private Long filtroEstadoAccidente;
	private Date filtroFechaIni;
	private Date filtroFechaFin;
	private String parametroBusquedadAuxiliarAccidentes;

	private List<CostsCenters> listCostCenters;
	private List<Driver> listDrivers;
	private List<Users> listUsers;
	private List<Accidents> listAccidents;

	private HtmlOutputText idUser;
	private HtmlOutputText idTercero;
	private HtmlOutputText txtPlaca;
	private HtmlOutputText txtSitioAccidente;
	private HtmlOutputText txtNumeroSinietro;
	private HtmlOutputText txtTotalHeridos;
	private HtmlOutputText txtTotalMuertos;
	private HtmlOutputText txtTotalTestigos;
	private HtmlOutputText txtVehiculosInvolucrados;
	private HtmlOutputText txtDescripcion;
	private HtmlOutputText idCostCenters;
	private HtmlOutputText txtIdAccidente;
	private HtmlOutputText txtDeduciblePesos;
	private HtmlOutputText txtDeducibleDolar;
	private HtmlOutputText txtValorDanho;
	private HtmlOutputText txtSanciones;
	private HtmlOutputText txtRecomedacion;
	private HtmlOutputText txtObservacion;
	private HtmlOutputText lblHora;
	private HtmlOutputText idAuxiliaresAccidentes;
	private HtmlInputText txtCantidadTestigos;
	private HtmlInputText txtCantidadLesionados;
	private HtmlInputText txtCantidadVehiculosInvolucrados;

	private HtmlInputText placaVHC;

	private HtmlSelectOneMenu cbxHora;
	private HtmlSelectOneMenu cbxMinutos;

	private HtmlInputTextarea txtOrdenTrabajo;
	private HtmlInputText txtFiltroPlaca;
	private HtmlInputText txtFiltroNumSiniestro;
	private HtmlInputText txtEmail;

	private HtmlCommandButton btnCrear;

	private PanelTab panelDatosBasicos;

	private boolean showDrivenTable = false;
	private boolean showCostCenters = false;
	private boolean showTercero = false;
	private boolean showAccidentsTable = false;
	private boolean showEstadoAccidente = false;
	private boolean showCarnet = false;

	private boolean activarConfirmacion;
	private static Integer MODIFICAR = 1;
	private Integer opcConfirmacion;

	private InvolvedVehiclesPage involvedVehiclesPage = (InvolvedVehiclesPage) FacesUtils
			.getManagedBean("involvedVehiclesPage");

	private InjuredAccidentsPage injuredAccidentsPage = (InjuredAccidentsPage) FacesUtils
			.getManagedBean("injuredAccidentsPage");

	private WitnessesPage witnessesPage = (WitnessesPage) FacesUtils
			.getManagedBean("witnessesPage");

	private AccidentFilesPage accidentFilesPage = (AccidentFilesPage) FacesUtils
			.getManagedBean("accidentFilesPage");

	private ConsultsService consultsService;

	public boolean isShowDrivenTable() {
		return showDrivenTable;
	}

	public void setShowDrivenTable(boolean showDrivenTable) {
		this.showDrivenTable = showDrivenTable;
	}

	public PanelTab getPanelDatosBasicos() {
		return panelDatosBasicos;
	}

	public void setPanelDatosBasicos(PanelTab panelDatosBasicos) {
		this.panelDatosBasicos = panelDatosBasicos;
	}

	public HtmlOutputText getTxtPlaca() {
		return txtPlaca;
	}

	public void setTxtPlaca(HtmlOutputText txtPlaca) {
		this.txtPlaca = txtPlaca;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public Date getAccFechaAccidente() {
		return accFechaAccidente;
	}

	public void setAccFechaAccidente(Date accFechaAccidente) {
		this.accFechaAccidente = accFechaAccidente;
	}

	public Long getIdCities() {
		return idCities;
	}

	public void setIdCities(Long idCities) {
		this.idCities = idCities;
	}

	public String getAccSitioAccidente() {
		return accSitioAccidente;
	}

	public void setAccSitioAccidente(String accSitioAccidente) {
		this.accSitioAccidente = accSitioAccidente;
	}

	public String getAccTotalMuertos() {
		return accTotalMuertos;
	}

	public void setAccTotalMuertos(String accTotalMuertos) {
		this.accTotalMuertos = accTotalMuertos;
	}

	public String getAccTotalHeridos() {
		return accTotalHeridos;
	}

	public void setAccTotalHeridos(String accTotalHeridos) {
		this.accTotalHeridos = accTotalHeridos;
	}

	public String getAccNombreConduct() {
		return accNombreConduct;
	}

	public void setAccNombreConduct(String accNombreConduct) {
		this.accNombreConduct = accNombreConduct;
	}

	public String getAccCargoConduct() {
		return accCargoConduct;
	}

	public void setAccCargoConduct(String accCargoConduct) {
		this.accCargoConduct = accCargoConduct;
	}

	public String getAccCodCargoAcc() {
		return accCodCargoAcc;
	}

	public void setAccCodCargoAcc(String accCodCargoAcc) {
		this.accCodCargoAcc = accCodCargoAcc;
	}

	public String getAccDescripcion() {
		return accDescripcion;
	}

	public void setAccDescripcion(String accDescripcion) {
		this.accDescripcion = accDescripcion;
	}

	public Long getResultados() {
		return resultados;
	}

	public void setResultados(Long resultados) {
		this.resultados = resultados;
	}

	public Long getResponsibility() {
		return responsibility;
	}

	public void setResponsibility(Long responsibility) {
		this.responsibility = responsibility;
	}

	public Long getSeverity() {
		return severity;
	}

	public void setSeverity(Long severity) {
		this.severity = severity;
	}

	public String getAccReclamo() {
		return accReclamo;
	}

	public void setAccReclamo(String accReclamo) {
		this.accReclamo = accReclamo;
	}

	public String getAccValorDano() {
		return accValorDano;
	}

	public void setAccValorDano(String accValorDano) {
		this.accValorDano = accValorDano;
	}

	public String getAccDeduciblesPesos() {
		return accDeduciblesPesos;
	}

	public void setAccDeduciblesPesos(String accDeduciblesPesos) {
		this.accDeduciblesPesos = accDeduciblesPesos;
	}

	public DataAccidentsService getDataAccidentsService() {
		return dataAccidentsService;
	}

	public void setDataAccidentsService(
			DataAccidentsService dataAccidentsService) {
		this.dataAccidentsService = dataAccidentsService;
	}

	void validarDatosNulos(String placa, Date accFechaAccidente, Long idCities,
			String accSitioAccidente, String accVehiculosInvolucrados,
			String accTotalMuertos, String accTotalHeridos,
			String accTotalTestigos, String aplicaTransito,
			String accNombreConduct, String accCargoConduct, Long resultados,
			String accReclamo, String horas, String minutos, String accUso)
			throws GWorkException {

		if (placa == null || placa.trim().length() == 0)
			throw new GWorkException(Util.loadErrorMessageValue("PLACA") + " "
					+ Util.loadErrorMessageValue("PANEL") + " "
					+ panelDatosBasicos.getLabel());

		if (aplicaTransito.equalsIgnoreCase(Util
				.loadMessageValue("SELECCIONAR")))
			throw new GWorkException(Util
					.loadErrorMessageValue("TRANSITOAPLICA.NULO")
					+ " "
					+ Util.loadErrorMessageValue("PANEL")
					+ " "
					+ panelDatosBasicos.getLabel());

		if (accFechaAccidente == null)
			throw new GWorkException(Util
					.loadErrorMessageValue("FECHAACCIDENTE.NULO")
					+ " "
					+ Util.loadErrorMessageValue("PANEL")
					+ panelDatosBasicos.getLabel());

		if (horas.equalsIgnoreCase("--") || minutos.equalsIgnoreCase("--"))
			throw new GWorkException(Util.loadErrorMessageValue("HORA.SEL")
					+ " " + Util.loadErrorMessageValue("PANEL") + " "
					+ panelDatosBasicos.getLabel());

		if (idCities == null || idCities.longValue() == -1L
				|| idCities.longValue() == 0L)
			throw new GWorkException(Util
					.loadErrorMessageValue("CIUDAD.LOCATION")
					+ " "
					+ Util.loadErrorMessageValue("PANEL")
					+ " "
					+ panelDatosBasicos.getLabel());

		if (accSitioAccidente == null || accSitioAccidente.trim().length() == 0)
			throw new GWorkException(Util
					.loadErrorMessageValue("SITIOACCIDENTE.NULO")
					+ " "
					+ Util.loadErrorMessageValue("PANEL")
					+ " "
					+ panelDatosBasicos.getLabel());

		if (accTotalTestigos == null || accTotalTestigos.trim().length() == 0)
			throw new GWorkException(Util
					.loadErrorMessageValue("NUMTESTIGOS.NULO")
					+ " "
					+ Util.loadErrorMessageValue("PANEL")
					+ " "
					+ panelDatosBasicos.getLabel());

		if (accTotalMuertos == null || accTotalMuertos.trim().length() == 0)
			throw new GWorkException(Util
					.loadErrorMessageValue("NUMMUERTOS.NULO")
					+ " "
					+ Util.loadErrorMessageValue("PANEL")
					+ " "
					+ panelDatosBasicos.getLabel());

		if (accTotalHeridos == null || accTotalHeridos.trim().length() == 0)
			throw new GWorkException(Util
					.loadErrorMessageValue("NUMHERIDOS.NULO")
					+ " "
					+ Util.loadErrorMessageValue("PANEL")
					+ " "
					+ panelDatosBasicos.getLabel());

		if (accNombreConduct == null || accNombreConduct.trim().length() == 0)
			throw new GWorkException(Util
					.loadErrorMessageValue("NOMBRECONDUCTOR.NULO")
					+ " "
					+ Util.loadErrorMessageValue("PANEL")
					+ " "
					+ panelDatosBasicos.getLabel());

		if (resultados == null || resultados.longValue() == -1L
				|| resultados.longValue() == 0L)
			throw new GWorkException(Util
					.loadErrorMessageValue("RESULTADOS.SEL")
					+ " "
					+ Util.loadErrorMessageValue("PANEL")
					+ " "
					+ panelDatosBasicos.getLabel());

		if (accUso == null
				|| accUso.trim().length() == 0
				|| accUso
						.equalsIgnoreCase(Util.loadMessageValue("SELECCIONAR")))
			throw new GWorkException(Util.loadErrorMessageValue("USO.SEL")
					+ " " + Util.loadErrorMessageValue("PANEL")
					+ panelDatosBasicos.getLabel());

		if (accVehiculosInvolucrados == null
				|| accVehiculosInvolucrados.trim().length() == 0)
			throw new GWorkException(Util
					.loadErrorMessageValue("VEHICULOSINVOLUCRADOS.NULO")
					+ " "
					+ Util.loadErrorMessageValue("PANEL")
					+ " "
					+ panelDatosBasicos.getLabel());

		if (accReclamo == null
				|| accReclamo.equalsIgnoreCase(Util
						.loadMessageValue("SELECCIONAR")))
			throw new GWorkException(Util.loadErrorMessageValue("RECLAMO.NULO")
					+ " " + Util.loadErrorMessageValue("PANEL") + " "
					+ panelDatosBasicos.getLabel());
	}

	public String getAplicaTransito() {
		return aplicaTransito;
	}

	public void setAplicaTransito(String aplicaTransito) {
		this.aplicaTransito = aplicaTransito;
	}

	public void validarTamanhosCamposNulos(String accNumeroSiniestro,
			String accDescripcion, String sanciones, String accRecomendaciones,
			String accObservaciones) throws GWorkException {

		if (accNumeroSiniestro != null
				&& accNumeroSiniestro.trim().length() < 4
				&& accNumeroSiniestro.length() >= 1)
			throw new GWorkException(Util
					.loadErrorMessageValue("CAMPO.TAMANHO")
					+ txtNumeroSinietro.getValue().toString()
					+ Util.loadErrorMessageValue("CAMPO.TAMANHO.NADECUADO")
					+ Util.loadErrorMessageValue("PANEL")
					+ panelDatosBasicos.getLabel());

		if (accDescripcion != null
				&& accDescripcion.trim().length() != 0
				&& (accDescripcion.trim().length() < 2 || accDescripcion.trim()
						.length() > 2000))
			throw new GWorkException(Util
					.loadErrorMessageValue("CAMPO.TAMANHO")
					+ txtDescripcion.getValue().toString()
					+ Util.loadErrorMessageValue("CAMPO.TAMANHO.NADECUADO")
					+ Util.loadErrorMessageValue("PANEL")
					+ panelDatosBasicos.getLabel());

		if (sanciones != null
				&& sanciones.trim().length() != 0
				&& (sanciones.trim().length() < 2 || sanciones.trim().length() > 250))
			throw new GWorkException(Util
					.loadErrorMessageValue("CAMPO.TAMANHO")
					+ txtSanciones.getValue().toString()
					+ Util.loadErrorMessageValue("CAMPO.TAMANHO.NADECUADO")
					+ Util.loadErrorMessageValue("PANEL")
					+ panelDatosBasicos.getLabel());

		if (accRecomendaciones != null
				&& accRecomendaciones.trim().length() != 0
				&& (accRecomendaciones.trim().length() < 2 || accRecomendaciones
						.trim().length() > 1000))
			throw new GWorkException(Util
					.loadErrorMessageValue("CAMPO.TAMANHO")
					+ txtRecomedacion.getValue().toString()
					+ Util.loadErrorMessageValue("CAMPO.TAMANHO.NADECUADO")
					+ Util.loadErrorMessageValue("PANEL")
					+ panelDatosBasicos.getLabel());

		if (accObservaciones != null
				&& accObservaciones.trim().length() != 0
				&& (accObservaciones.trim().length() < 2 || accObservaciones
						.trim().length() > 2000))
			throw new GWorkException(Util
					.loadErrorMessageValue("CAMPO.TAMANHO")
					+ txtObservacion.getValue().toString()
					+ Util.loadErrorMessageValue("CAMPO.TAMANHO.NADECUADO")
					+ Util.loadErrorMessageValue("PANEL")
					+ panelDatosBasicos.getLabel());
	}

	public void action_crearAccidente(ActionEvent actionEvent) {

		try {

			validarDatosNulos(placa, accFechaAccidente, idCities,
					accSitioAccidente, accVehiculosInvolucrados,
					accTotalMuertos, accTotalHeridos, accTotalTestigos,
					aplicaTransito, accNombreConduct, accCargoConduct,
					resultados, accReclamo, cbxHora.getValue().toString(),
					cbxMinutos.getValue().toString(), accUso);

			validarTamanhoCaracteres(placa, txtPlaca.getValue().toString(), 2,
					panelDatosBasicos.getLabel());
			validarTamanhoCaracteres(accSitioAccidente, txtSitioAccidente
					.getValue().toString(), 4, panelDatosBasicos.getLabel());

			validarTamanhosCamposNulos(accNumeroSiniestro, accDescripcion,
					sanciones, accRecomendaciones, accObservaciones);

			validarCaracteres(placa, accSitioAccidente, accNumeroSiniestro,
					accTotalHeridos, accTotalMuertos, accTotalTestigos,
					accVehiculosInvolucrados, accDeduciblesPesos, accValorDano);

			hora = cbxHora.getValue() + ":" + cbxMinutos.getValue();
			hora.length();

			dataAccidentsService.registroAccidente(placa.toUpperCase(),
					accFechaAccidente, idCities, accSitioAccidente,
					accNumeroSiniestro, accVehiculosInvolucrados,
					accTotalMuertos, accTotalHeridos, accTotalTestigos,
					aplicaTransito, accNombreConduct, accCargoConduct,
					accCodCargoAcc, accDescripcion, resultados, responsibility,
					severity, accUso, accReclamo, accValorDano,
					accDeduciblesPesos, null, accDeducible, accCargoDeducible,
					accTipoAsignacion, accNombreAsignatario, accCedulaConduc,
					sanciones, ordenTrabajo, accRecomendaciones,
					accObservaciones, email, hora);
			mostrarMensaje(Util.loadMessageValue("EXITO"), false);
			limpiar();

		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public HtmlOutputText getTxtSitioAccidente() {
		return txtSitioAccidente;
	}

	public void setTxtSitioAccidente(HtmlOutputText txtSitioAccidente) {
		this.txtSitioAccidente = txtSitioAccidente;
	}

	public HtmlOutputText getTxtNumeroSinietro() {
		return txtNumeroSinietro;
	}

	public void setTxtNumeroSinietro(HtmlOutputText txtNumeroSinietro) {
		this.txtNumeroSinietro = txtNumeroSinietro;
	}

	public void showDriven(ActionEvent actionEvent) {

		showDrivenTable = true;
	}

	public void closeDriven(ActionEvent actionEvent) {
		showDrivenTable = false;
	}

	public String getParametroBusquedad() {
		return parametroBusquedad;
	}

	public void setParametroBusquedad(String parametroBusquedad) {
		this.parametroBusquedad = parametroBusquedad;
	}

	public void action_filtroBusquedaEmpleado(ActionEvent actionEvent) {
		try {

			if (parametroBusquedad == null
					|| parametroBusquedad.trim().length() == 0)
				throw new GWorkException(Util
						.loadErrorMessageValue("PARAMETROBUSQUEDAD"));
			setListDrivers(consultsService.driversCIAT(parametroBusquedad
					.toUpperCase(), parametroBusquedad, parametroBusquedad));
		} catch (GWorkException e) {
			mostrarMensaje(e.getMessage(), false);
		}
	}

	public void action_filtroConductorDesignado(ActionEvent actionEvent) {

		try {
			if (filtroConductorTercero == null
					|| filtroConductorTercero.trim().length() == 0)
				throw new GWorkException(Util
						.loadErrorMessageValue("PARAMETROBUSQUEDAD"));
			setListDrivers(dataAccidentsService.filtroConductores(
					filtroConductorTercero, filtroConductorTercero
							.toUpperCase()));
		} catch (GWorkException e) {
			mostrarMensaje(e.getMessage(), false);
		}
	}

	public void action_filtroBusquedaTercero(ActionEvent actionEvent) {
		try {
			setListUsers(consultsService.employeesCIAT(
					parametroBusquedadTercero.toUpperCase(),
					parametroBusquedadTercero, parametroBusquedadTercero));
		} catch (GWorkException e) {
			mostrarMensaje(e.getMessage(), false);
		}
	}

	public void action_filtrarAuxiliaresAccidentes(ActionEvent actionEvent) {
		try {
			setListUsers(consultsService.auxiliaresCIAT(
					parametroBusquedadAuxiliarAccidentes.toUpperCase(),
					parametroBusquedadAuxiliarAccidentes.toUpperCase()));
		} catch (GWorkException e) {
			mostrarMensaje(e.getMessage(), false);
		}
	}

	public ConsultsService getConsultsService() {
		return consultsService;
	}

	public void setConsultsService(ConsultsService consultsService) {
		this.consultsService = consultsService;
	}

	public void rowSelectionEmployee(RowSelectorEvent rowSelectorEvent) {
		String idUsuario = (String) idUser.getValue();
		for (Driver driver : listDrivers) {

			if (idUsuario.equals(driver.getDrvNumeroCarne())) {
				setAccNombreConduct(driver.getDrvNombre());
				setAccCodCargoAcc(driver.getDrvNumeroCarne().toString());
				setAccCargoConduct(driver.getDrvCargo());
				setAccCedulaConduc(driver.getDrvCedula());
				setShowDrivenTable(false);
			}
		}
		setListDrivers(null);
	}

	public void rowSelectionTerceros(RowSelectorEvent rowSelectorEvent) {
		String idUsuario = (String) idTercero.getValue();
		for (Users users : listUsers) {

			if (idUsuario.equalsIgnoreCase(users.getUsrIdentificacion())) {
				setAccDeducible(users.getUsrIdentificacion());
				showTercero = false;
			}
		}
		setListUsers(null);
	}

	public void rowSelectorAuxiliariesAccidentes(
			RowSelectorEvent rowSelectorEvent) {
		String idUsuario = (String) idAuxiliaresAccidentes.getValue();
		for (Users users : listUsers) {

			if (idUsuario.equalsIgnoreCase(users.getUsrIdentificacion())) {
				setAccDeducible(users.getUsrIdentificacion());
				showCarnet = false;
			}
		}
		setListUsers(null);
	}

	public HtmlOutputText getIdUser() {
		return idUser;
	}

	public void setIdUser(HtmlOutputText idUser) {
		this.idUser = idUser;
	}

	public String getAccUso() {
		return accUso;
	}

	public void setAccUso(String accUso) {
		this.accUso = accUso;
	}

	public List<Driver> getListDrivers() {
		return listDrivers;
	}

	public void setListDrivers(List<Driver> listDrivers) {
		this.listDrivers = listDrivers;
	}

	public void listener_validarPlaca(ValueChangeEvent event) {
		String placa = (String) event.getNewValue();

		if (placa != null && placa.trim().length() > 0) {
			try {

				if (!Util
						.validarCadenaCaracteresEspecialesNumLetrasGuion(placa))
					throw new GWorkException(Util
							.loadErrorMessageValue("PLACA.CARACTER"));

				/* Agregar código para análisis de tamaño */
				try {
					validarTamanhoCaracteres(placa, txtPlaca.getValue()
							.toString(), 4, panelDatosBasicos.getLabel());

				} catch (GWorkException e) {
					placaVHC.setValue(null);
					throw new GWorkException(e.getMessage());
				}

				try {
					dataAccidentsService
							.findVehicleByPlaca(placa.toUpperCase());
				} catch (GWorkException e) {
					placaVHC.setValue(null);
					throw new GWorkException(e.getMessage());
				}

				VehiclesAssignation vehiclesAssignation = dataAccidentsService
						.consultarAsignacionVehiculo(placa.toUpperCase());

				if (vehiclesAssignation != null) {

					setEmail(vehiclesAssignation.getRequests()
							.getUsersByRqsUser().getUsrEmail());

					txtEmail.setValue(vehiclesAssignation.getRequests()
							.getUsersByRqsUser().getUsrEmail());
					if (vehiclesAssignation.getRequests().getLegateesTypes() != null) {
						setNombreAsignacion(vehiclesAssignation.getRequests()
								.getLegateesTypes().getLgtNombre());
						setAccNombreAsignatario(vehiclesAssignation
								.getRequests().getUsersByRqsUser()
								.getUsrNombre());
						setAccTipoAsignacion(vehiclesAssignation.getRequests()
								.getLegateesTypes().getLgtCodigo().longValue());
					}
				}
			} catch (GWorkException e) {
				mostrarMensaje(e.getMessage(), false);
				accNombreAsignatario = null;
				nombreAsignacion = null;
				email = null;
				txtEmail.setValue(null);
			}
		}

	}

	public void validarCaracteres(String placa, String accSitioAccidente,
			String accNumeroSiniestro, String accTotalHeridos,
			String accTotalMuertos, String accTotalTestigos,
			String accVehiculosInvolucrados, String accDeduciblesPesos,
			String accValorDano) throws GWorkException {

		if (!Util.validarCadenaCaracteresEspecialesNumLetrasGuion(placa))
			throw new GWorkException(Util
					.loadErrorMessageValue("PLACA.CARACTER"));

		if (accNumeroSiniestro != null
				&& !Util.validarNumerosParametros(accNumeroSiniestro))
			throw new GWorkException(Util
					.loadErrorMessageValue("CARACTERES.NUMERICOS")
					+ txtNumeroSinietro.getValue());

		if (!Util.validarNumerosConsulta(accTotalHeridos))
			throw new GWorkException(Util
					.loadErrorMessageValue("CARACTERES.NUMERICOS")
					+ txtTotalHeridos.getValue());

		if (!Util.validarNumerosConsulta(accTotalMuertos))
			throw new GWorkException(Util
					.loadErrorMessageValue("CARACTERES.NUMERICOS")
					+ txtTotalMuertos.getValue());

		if (!Util.validarNumerosConsulta(accTotalTestigos))
			throw new GWorkException(Util
					.loadErrorMessageValue("CARACTERES.NUMERICOS")
					+ txtTotalTestigos.getValue());

		if (!Util.validarNumerosConsulta(accVehiculosInvolucrados))
			throw new GWorkException(Util
					.loadErrorMessageValue("CARACTERES.NUMERICOS")
					+ txtVehiculosInvolucrados.getValue());

		if (!Util.validarNumerosParametros(accDeduciblesPesos))
			throw new GWorkException(Util
					.loadErrorMessageValue("CARACTERES.NUMERICOS")
					+ txtDeduciblePesos.getValue());

		if (!Util.validarNumerosParametros(accValorDano))
			throw new GWorkException(Util
					.loadErrorMessageValue("CARACTERES.NUMERICOS")
					+ txtValorDanho.getValue());

	}

	public String getAccNumeroSiniestro() {
		return accNumeroSiniestro;
	}

	public void setAccNumeroSiniestro(String accNumeroSiniestro) {
		this.accNumeroSiniestro = accNumeroSiniestro;
	}

	public String getAccTotalTestigos() {
		return accTotalTestigos;
	}

	public void setAccTotalTestigos(String accTotalTestigos) {
		this.accTotalTestigos = accTotalTestigos;
	}

	public HtmlOutputText getTxtTotalHeridos() {
		return txtTotalHeridos;
	}

	public void setTxtTotalHeridos(HtmlOutputText txtTotalHeridos) {
		this.txtTotalHeridos = txtTotalHeridos;
	}

	public HtmlOutputText getTxtTotalMuertos() {
		return txtTotalMuertos;
	}

	public void setTxtTotalMuertos(HtmlOutputText txtTotalMuertos) {
		this.txtTotalMuertos = txtTotalMuertos;
	}

	public HtmlOutputText getTxtTotalTestigos() {
		return txtTotalTestigos;
	}

	public void setTxtTotalTestigos(HtmlOutputText txtTotalTestigos) {
		this.txtTotalTestigos = txtTotalTestigos;
	}

	public String getAccVehiculosInvolucrados() {
		return accVehiculosInvolucrados;
	}

	public void setAccVehiculosInvolucrados(String accVehiculosInvolucrados) {
		this.accVehiculosInvolucrados = accVehiculosInvolucrados;
	}

	public HtmlOutputText getTxtVehiculosInvolucrados() {
		return txtVehiculosInvolucrados;
	}

	public void setTxtVehiculosInvolucrados(
			HtmlOutputText txtVehiculosInvolucrados) {
		this.txtVehiculosInvolucrados = txtVehiculosInvolucrados;
	}

	public HtmlOutputText getTxtDescripcion() {
		return txtDescripcion;
	}

	public void setTxtDescripcion(HtmlOutputText txtDescripcion) {
		this.txtDescripcion = txtDescripcion;
	}

	public void action_limpiarFormularion(ActionEvent actionEvent) {

		limpiar();
	}

	public void limpiar() {
		try {
			placa = null;
			accFechaAccidente = null;
			aplicaTransito = Util.loadMessageValue("SELECCIONAR");
			idCities = new Long("-1");
			accSitioAccidente = null;
			accNumeroSiniestro = null;
			accTotalTestigos = null;
			accTotalMuertos = null;
			accTotalHeridos = null;
			accNombreConduct = null;
			accCargoConduct = null;
			accCodCargoAcc = null;
			resultados = new Long("-1");
			responsibility = new Long("-1");
			severity = new Long("-1");
			accUso = Util.loadMessageValue("SELECCIONAR");
			accReclamo = Util.loadMessageValue("SELECCIONAR");
			accVehiculosInvolucrados = null;
			accValorDano = null;
			accDeduciblesPesos = null;
			accCargoDeducible = Util.loadMessageValue("SELECCIONAR");
			accNombreAsignatario = null;
			accDeducible = null;
			nombreAsignacion = null;
			accTipoAsignacion = null;
			accCedulaConduc = null;
			parametroBusquedad = null;
			parametroBusquedadTercero = null;
			parametroCentroCostos = null;
			estadoAccidente = new Long("-1");
			accDescripcion = null;
			accCodigo = null;
			showEstadoAccidente = false;
			btnCrear.setDisabled(false);
			involvedVehiclesPage.setListInvolvedVehicles(null);
			involvedVehiclesPage.setIdAccidente(null);
			involvedVehiclesPage.limpiar();
			injuredAccidentsPage.limpiarPantalla();
			injuredAccidentsPage.setIdAccidente(null);
			witnessesPage.setIdAccidente(null);
			witnessesPage.limpiar();
			witnessesPage.setListWitnesses(null);
			accidentFilesPage.limpiar();
			accidentFilesPage.setListAccidentsFiles(null);
			accidentFilesPage.setIdAccidente(null);
			placaConsulta = null;
			numSiniestroConsulta = null;
			fechaAccidenteConsulta = null;
			nombreConsulta = null;
			sanciones = null;
			accRecomendaciones = null;
			accObservaciones = null;
			email = null;
			hora = null;
			cbxHora.setValue("--");
			cbxMinutos.setValue("--");
			ordenTrabajo = null;
			codigoAccConsulta = null;

		} catch (GWorkException e) {
			System.out.println(e.getMessage());
		}

	}

	public boolean isShowCostCenters() {
		return showCostCenters;
	}

	public void setShowCostCenters(boolean showCostCenters) {
		this.showCostCenters = showCostCenters;
	}

	public boolean isShowTercero() {
		return showTercero;
	}

	public void setShowTercero(boolean showTercero) {
		this.showTercero = showTercero;
	}

	public boolean isShowCarnet() {
		return showCarnet;
	}

	public void action_closeCostCenters(ActionEvent event) {

		showCostCenters = false;
	}

	public void action_showCostCenters(ActionEvent event) {

		try {
			if (accCargoDeducible != null
					&& accCargoDeducible.equals(Util
							.loadMessageValue("SELECCIONAR"))) {
				throw new GWorkException(Util
						.loadErrorMessageValue("CARGO.SEL"));

			} else if (accCargoDeducible != null
					&& accCargoDeducible.equalsIgnoreCase(Util
							.loadMessageValue("CENTROCOSTO"))
					&& accCargoDeducible != Util
							.loadMessageValue("SELECCIONAR")) {
				showCostCenters = true;
			} else if (accCargoDeducible != null
					&& !accCargoDeducible.equals(Util
							.loadMessageValue("SELECCIONAR"))
					&& accCargoDeducible.equalsIgnoreCase(Util
							.loadMessageValue("TERCERO"))) {
				listUsers = null;
				showCarnet = true;
			} else if (accCargoDeducible != null
					&& !accCargoDeducible.equals(Util
							.loadMessageValue("SELECCIONAR"))
					&& (accCargoDeducible.equalsIgnoreCase(Util
							.loadMessageValue("CARNE")))) {
				listUsers = null;
				showTercero = true;
			}
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public String getParametroCentroCostos() {
		return parametroCentroCostos;
	}

	public void setParametroCentroCostos(String parametroCentroCostos) {
		this.parametroCentroCostos = parametroCentroCostos;
	}

	public List<CostsCenters> getListCostCenters() {
		return listCostCenters;
	}

	public void setListCostCenters(List<CostsCenters> listCostCenters) {
		this.listCostCenters = listCostCenters;
	}

	public void action_filtroBusquedaCentroCostos(ActionEvent actionEvent) {

		try {
			setListCostCenters(consultsService
					.centrosCosto(parametroCentroCostos.toUpperCase()));
		} catch (GWorkException e) {
			mostrarMensaje(e.getMessage(), false);
		}
	}

	public void rowSelectorCostCenters(RowSelectorEvent event) {
		String idCostCenter = (String) idCostCenters.getValue();
		for (CostsCenters costsCenters : listCostCenters) {
			if (idCostCenter.equalsIgnoreCase(costsCenters.getCocNumero())) {
				setAccDeducible(costsCenters.getCocNumero());
				showCostCenters = false;
			}
		}
		setListCostCenters(null);

	}

	public HtmlOutputText getIdCostCenters() {
		return idCostCenters;
	}

	public void setIdCostCenters(HtmlOutputText idCostCenters) {
		this.idCostCenters = idCostCenters;
	}

	public String getAccDeducible() {
		return accDeducible;
	}

	public void setAccDeducible(String accDeducible) {
		this.accDeducible = accDeducible;
	}

	public String getAccCargoDeducible() {
		return accCargoDeducible;
	}

	public void setAccCargoDeducible(String accCargoDeducible) {
		this.accCargoDeducible = accCargoDeducible;
	}

	public String getAccNombreAsignatario() {
		return accNombreAsignatario;
	}

	public void setAccNombreAsignatario(String accNombreAsignatario) {
		this.accNombreAsignatario = accNombreAsignatario;
	}

	public Long getAccTipoAsignacion() {
		return accTipoAsignacion;
	}

	public void setAccTipoAsignacion(Long accTipoAsignacion) {
		this.accTipoAsignacion = accTipoAsignacion;
	}

	public String getNombreAsignacion() {
		return nombreAsignacion;
	}

	public void setNombreAsignacion(String nombreAsignacion) {
		this.nombreAsignacion = nombreAsignacion;
	}

	public void action_closeShowAuxiliariesAccidentes(ActionEvent actionEvent) {

		showCarnet = false;
	}

	public void action_closeTerceros(ActionEvent actionEvent) {

		showTercero = false;
	}

	public String getParametroBusquedadTercero() {
		return parametroBusquedadTercero;
	}

	public void setParametroBusquedadTercero(String parametroBusquedadTercero) {
		this.parametroBusquedadTercero = parametroBusquedadTercero;
	}

	public HtmlOutputText getIdTercero() {
		return idTercero;
	}

	public void setIdTercero(HtmlOutputText idTercero) {
		this.idTercero = idTercero;
	}

	public List<Users> getListUsers() {
		return listUsers;
	}

	public void setListUsers(List<Users> listUsers) {
		this.listUsers = listUsers;
	}

	public String getAccCedulaConduc() {
		return accCedulaConduc;
	}

	public void setAccCedulaConduc(String accCedulaConduc) {
		this.accCedulaConduc = accCedulaConduc;
	}

	public boolean isShowAccidentsTable() {
		return showAccidentsTable;
	}

	public void setShowAccidentsTable(boolean showAccidentsTable) {
		this.showAccidentsTable = showAccidentsTable;
	}

	public void action_closeAccidentsTable(ActionEvent actionEvent) {
		showAccidentsTable = false;
	}

	public void action_showAccidentsTable(ActionEvent actionEvent) {

		showAccidentsTable = true;
	}

	public List<Accidents> getListAccidents() {
		return listAccidents;
	}

	public void setListAccidents(List<Accidents> listAccidents) {
		this.listAccidents = listAccidents;
	}

	public void rowSelectionAccident(RowSelectorEvent rowSelectorEvent) {

		limpiar();
		Long idAccidente = (Long) txtIdAccidente.getValue();

		for (Accidents accidents : listAccidents) {

			if (idAccidente.longValue() == accidents.getAccCodigo().longValue()) {

				consultarAccidente(accidents.getAccCodigo().longValue());

				showEstadoAccidente = true;
				btnCrear.setDisabled(true);
				limpiarConsulta();
				txtFiltroPlaca.setValue(null);
				setShowAccidentsTable(false);

				involvedVehiclesPage.listarVehiculos();
				witnessesPage.listarTestigo();
				accidentFilesPage.listarArchivos();
				accidentFilesPage.limpiar();

			}

		}
		setListAccidents(null);

	}

	public void consultarAccidente(Long idAccidente) {

		try {
			Accidents accidents = dataAccidentsService
					.consultarAccidente(idAccidente);

			setAccCodigo(accidents.getAccCodigo());
			setPlaca(accidents.getVehicles().getVhcPlacaDiplomatica());
			setPlacaConsulta(accidents.getVehicles().getVhcPlacaDiplomatica());
			setAccFechaAccidente(accidents.getAccFechaAccidente());
			setFechaAccidenteConsulta(accidents.getAccFechaAccidente());
			setAplicaTransito(accidents.getAccIntervinoTransito());
			setIdCities(accidents.getCities().getCtsId());
			setAccSitioAccidente(accidents.getAccSitioAccidente());
			if (accidents.getAccNumeroSiniestro() != null) {
				setAccNumeroSiniestro(accidents.getAccNumeroSiniestro()
						.toString());
				setNumSiniestroConsulta(accidents.getAccNumeroSiniestro()
						.toString());
			}
			setAccTotalTestigos(accidents.getAccTotalTestigos().toString());
			setAccTotalMuertos(accidents.getAccTotalMuertos().toString());
			setAccTotalHeridos(accidents.getAccTotalHeridos().toString());
			setAccNombreConduct(accidents.getAccNombreConduct());
			setNombreConsulta(accidents.getAccNombreConduct());
			setAccCedulaConduc(accidents.getAccCedulaConduc());
			setAccCodCargoAcc(accidents.getAccCodCargoAcc());
			setAccCargoConduct(accidents.getAccCodigo().toString());
			setResultados(accidents.getAccidentsResults().getAclCodigo());
			if (accidents.getResponsibility() != null)
				setResponsibility(accidents.getResponsibility().getResCodigo());
			if (accidents.getSeverity() != null)
				setSeverity(accidents.getSeverity().getSevCodigo());
			setAccUso(accidents.getAccUso());
			setAccReclamo(accidents.getAccReclamo());
			setAccVehiculosInvolucrados(accidents.getAccVehiculosInvolucrados()
					.toString());
			if (accidents.getAccValorDano() != null)
				setAccValorDano(new BigDecimal(accidents.getAccValorDano())
						.toString());

			if (accidents.getAccDeduciblesPesos() != null)
				setAccDeduciblesPesos(new BigDecimal(accidents
						.getAccDeduciblesPesos()).toString());
			setAccDeducible(accidents.getAccDeducible());
			setAccDescripcion(accidents.getAccDescripcion());
			setAccCargoDeducible(accidents.getAccCargoDeducible());
			setAccNombreAsignatario(accidents.getAccNombreAsignatario());
			if (accidents.getLegateesTypes() != null)
				setNombreAsignacion(accidents.getLegateesTypes().getLgtNombre());
			setEstadoAccidente(accidents.getAccidentsStates().getAcsCode());
			if (accidents.getAccSancInterActa() != null)
				setSanciones(accidents.getAccSancInterActa());
			if (accidents.getAccObservaciones() != null)
				setAccObservaciones(accidents.getAccObservaciones());
			if (accidents.getAccRecomendaciones() != null)
				setAccRecomendaciones(accidents.getAccRecomendaciones());
			if (accidents.getAccOrdenTrabajoActa() != null) {
				ordenTrabajo = accidents.getAccOrdenTrabajoActa();

			}
			setCodigoAccConsulta(accidents.getAccCodigo().longValue());
			if (accidents.getAccEmailConductor() != null)
				setEmail(accidents.getAccEmailConductor());
			String hora;
			hora = accidents.getAccHora();
			cbxHora.setValue(hora.substring(0, 2));
			cbxMinutos.setValue(hora.substring(3, 5));
			/** Carga el id del accidente a los formularios */
			involvedVehiclesPage.setIdAccidente(accidents.getAccCodigo());
			injuredAccidentsPage.setIdAccidente(accidents.getAccCodigo());
			witnessesPage.setIdAccidente(accidents.getAccCodigo());
			accidentFilesPage.setIdAccidente(accidents.getAccCodigo());
		} catch (GWorkException e) {
			mostrarMensaje(e.getMessage(), false);
		}
	}

	public String getFiltroPlaca() {
		return filtroPlaca;
	}

	public void setFiltroPlaca(String filtroPlaca) {
		this.filtroPlaca = filtroPlaca;
	}

	public Long getFiltroEstadoAccidente() {
		return filtroEstadoAccidente;
	}

	public void setFiltroEstadoAccidente(Long filtroEstadoAccidente) {
		this.filtroEstadoAccidente = filtroEstadoAccidente;
	}

	public Date getFiltroFechaIni() {
		return filtroFechaIni;
	}

	public void setFiltroFechaIni(Date filtroFechaIni) {
		this.filtroFechaIni = filtroFechaIni;
	}

	public Date getFiltroFechaFin() {
		return filtroFechaFin;
	}

	public void setFiltroFechaFin(Date filtroFechaFin) {
		this.filtroFechaFin = filtroFechaFin;
	}

	public void action_consultarAccidente(ActionEvent actionEvent)
			throws GWorkException {
		try {

			if (((filtroEstadoAccidente == null
					|| filtroEstadoAccidente.longValue() == 0 || filtroEstadoAccidente
					.longValue() == -1L)
					&& filtroFechaFin == null
					&& filtroFechaIni == null
					&& (filtroNumSiniestro == null || filtroNumSiniestro.trim()
							.length() == 0) && (filtroPlaca == null || filtroPlaca
					.trim().length() == 0))
					|| ((filtroEstadoAccidente == null
							|| filtroEstadoAccidente.longValue() == 0 || filtroEstadoAccidente
							.longValue() == -1L))
					&& (filtroNumSiniestro == null || filtroNumSiniestro.trim()
							.length() == 0)
					&& (filtroPlaca == null || filtroPlaca.trim().length() == 0))
				throw new GWorkException(Util
						.loadErrorMessageValue("CONSULTAR.ACCIDENTE.NULO"));

			if (filtroFechaFin == null && filtroFechaIni == null)
				throw new GWorkException(Util
						.loadErrorMessageValue("FECHAS.CONSULTA.NULO"));

			if (filtroFechaFin == null)
				throw new GWorkException(Util
						.loadErrorMessageValue("FECHA.FINAL.NULO"));

			if (filtroFechaIni == null)
				throw new GWorkException(Util
						.loadErrorMessageValue("FECHA.INICIO.NULO"));

			if (!Util.validarNumerosParametros(filtroNumSiniestro))
				throw new GWorkException(
						Util
								.loadErrorMessageValue("CARACTERESPECIAL.FILTROSINIESTRO"));

			if (!Util
					.validarCadenaCaracteresEspecialesNumLetrasGuion(filtroPlaca))
				throw new GWorkException(Util
						.loadErrorMessageValue("PLACA.CARACTER"));

			if (filtroFechaFin.compareTo(filtroFechaIni) < 0)
				throw new GWorkException(Util
						.loadErrorMessageValue("FCH_INI_FCH_FIN"));

			if (((filtroPlaca != null && filtroPlaca.trim().length() > 0)
					&& (filtroEstadoAccidente != null && filtroEstadoAccidente
							.longValue() != -1L) && (filtroNumSiniestro != null && filtroNumSiniestro
					.trim().length() > 0))
					|| ((filtroPlaca != null && filtroPlaca.trim().length() > 0) && (filtroEstadoAccidente != null && filtroEstadoAccidente
							.longValue() != -1L))
					|| ((filtroPlaca != null && filtroPlaca.trim().length() > 0)
							&& (filtroNumSiniestro != null && filtroNumSiniestro
									.trim().length() > 0) || ((filtroEstadoAccidente != null && filtroEstadoAccidente
							.longValue() != -1L) && (filtroNumSiniestro != null && filtroNumSiniestro
							.trim().length() > 0))))
				throw new GWorkException(
						"Debe ingresar un solo parametro de busquedad");

			if (filtroPlaca != null && filtroPlaca.trim().length() > 0)
				setListAccidents(dataAccidentsService.findAccidentByPlaca(
						filtroPlaca.toUpperCase(), filtroFechaIni,
						filtroFechaFin));

			else if (filtroNumSiniestro != null
					&& filtroNumSiniestro.trim().length() > 0)
				setListAccidents(dataAccidentsService
						.findAccidentByNumeroSiniestro(new Long(
								filtroNumSiniestro), filtroFechaIni,
								filtroFechaFin));
			else if (filtroEstadoAccidente != null
					&& filtroEstadoAccidente.longValue() != 0
					&& filtroEstadoAccidente.longValue() != -1L)
				setListAccidents(dataAccidentsService.findAccidentByEstado(
						filtroEstadoAccidente, filtroFechaIni, filtroFechaFin));

		} catch (GWorkException e) {

			mostrarMensaje(e.getMessage(), false);
		}
	}

	public void action_limpiarConsulta(ActionEvent actionEvent) {

		limpiarConsulta();
	}

	public void limpiarConsulta() {
		txtFiltroPlaca.setValue("");
		txtFiltroNumSiniestro.setValue(null);
		filtroFechaFin = null;
		filtroFechaIni = null;
		filtroNumSiniestro = null;
		filtroEstadoAccidente = new Long("-1");
		setListAccidents(null);
	}

	public boolean isShowEstadoAccidente() {
		return showEstadoAccidente;
	}

	public void setShowEstadoAccidente(boolean showEstadoAccidente) {
		this.showEstadoAccidente = showEstadoAccidente;
	}

	public Long getEstadoAccidente() {
		return estadoAccidente;
	}

	public void setEstadoAccidente(Long estadoAccidente) {
		this.estadoAccidente = estadoAccidente;
	}

	public void action_modificarAccidente(ActionEvent actionEvent) {

		try {

			if (accCodigo == null || accCodigo.longValue() == 0)
				throw new GWorkException(Util
						.loadErrorMessageValue("ACCIDENTE.NULO"));

			// injuredAccidentsPage
			validarDatosNulos(placa, accFechaAccidente, idCities,
					accSitioAccidente, accVehiculosInvolucrados,
					accTotalMuertos, accTotalHeridos, accTotalTestigos,
					aplicaTransito, accNombreConduct, accCargoConduct,
					resultados, accReclamo, cbxHora.getValue().toString(),
					cbxMinutos.getValue().toString(), accUso);

			if (this.estadoAccidente == null
					|| this.estadoAccidente.longValue() == -1L)
				throw new GWorkException(Util
						.loadErrorMessageValue("ESTADO.NULO")
						+ " "
						+ Util.loadErrorMessageValue("PANEL")
						+ " "
						+ panelDatosBasicos.getLabel());

			validarTamanhoCaracteres(placa, txtPlaca.getValue().toString(), 2,
					panelDatosBasicos.getLabel());
			validarTamanhoCaracteres(accSitioAccidente, txtSitioAccidente
					.getValue().toString(), 4, panelDatosBasicos.getLabel());

			validarTamanhosCamposNulos(accNumeroSiniestro, accDescripcion,
					sanciones, accRecomendaciones, accObservaciones);

			validarCaracteres(placa, accSitioAccidente, accNumeroSiniestro,
					accTotalHeridos, accTotalMuertos, accTotalTestigos,
					accVehiculosInvolucrados, accDeduciblesPesos, accValorDano);

			activarConfirmacion = true;
			setOpcConfirmacion(MODIFICAR);
			mostrarMensaje(Util.loadMessageValue("MENSAJE.ALERTA"),
					activarConfirmacion);

		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

	}

	public void modificarAccidente() {

		try {
			if (accCodigo != null) {

				hora = cbxHora.getValue() + ":" + cbxMinutos.getValue();
				dataAccidentsService.modificarAccidente(accCodigo, placa,
						accFechaAccidente, idCities, accSitioAccidente,
						accNumeroSiniestro, accVehiculosInvolucrados,
						accTotalMuertos, accTotalHeridos, accTotalTestigos,
						aplicaTransito, accNombreConduct, accCargoConduct,
						accCodCargoAcc, accDescripcion, resultados,
						responsibility, severity, accUso, accReclamo,
						accValorDano, accDeduciblesPesos, null, accDeducible,
						accCargoDeducible, accTipoAsignacion,
						accNombreAsignatario, accCedulaConduc, estadoAccidente,
						sanciones, ordenTrabajo, accRecomendaciones,
						accObservaciones, email, hora);

				limpiar();
				mostrarMensaje(Util.loadMessageValue("EXITO.MODIFICAR"), false);
			}
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void guardarTestigos(ValueChangeEvent event) {
		try {
			String cantidadTestigos = txtCantidadTestigos.getValue().toString();
			if (accCodigo != null && cantidadTestigos.length() > 0) {
				dataAccidentsService.GuardarTestigos(cantidadTestigos,
						accCodigo);
			}
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void guardarLesionados(ValueChangeEvent event) {
		try {
			String TotalHeridos = txtCantidadLesionados.getValue().toString();
			if (accCodigo != null && TotalHeridos.length() > 0) {
				dataAccidentsService.GuardarLesionados(TotalHeridos, accCodigo);
			}
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void guardarVehiculosInvolucrados(ValueChangeEvent event) {
		try {
			String VehiculosInvolucrados = txtCantidadVehiculosInvolucrados
					.getValue().toString();
			if (accCodigo != null && VehiculosInvolucrados.length() > 0) {
				dataAccidentsService.GuardarVehiculosInvolucrados(
						VehiculosInvolucrados, accCodigo);
			}
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
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

	public HtmlOutputText getTxtIdAccidente() {
		return txtIdAccidente;
	}

	public void setTxtIdAccidente(HtmlOutputText txtIdAccidente) {
		this.txtIdAccidente = txtIdAccidente;
	}

	public Long getAccCodigo() {
		return accCodigo;
	}

	public void setAccCodigo(Long accCodigo) {
		this.accCodigo = accCodigo;
	}

	public HtmlCommandButton getBtnCrear() {
		return btnCrear;
	}

	public void setBtnCrear(HtmlCommandButton btnCrear) {
		this.btnCrear = btnCrear;
	}

	public String getFiltroNumSiniestro() {
		return filtroNumSiniestro;
	}

	public void setFiltroNumSiniestro(String filtroNumSiniestro) {
		this.filtroNumSiniestro = filtroNumSiniestro;
	}

	public HtmlInputText getTxtFiltroPlaca() {
		return txtFiltroPlaca;
	}

	public void setTxtFiltroPlaca(HtmlInputText txtFiltroPlaca) {
		this.txtFiltroPlaca = txtFiltroPlaca;
	}

	public HtmlInputText getTxtFiltroNumSiniestro() {
		return txtFiltroNumSiniestro;
	}

	public void setTxtFiltroNumSiniestro(HtmlInputText txtFiltroNumSiniestro) {
		this.txtFiltroNumSiniestro = txtFiltroNumSiniestro;
	}

	public String getPlacaConsulta() {
		return placaConsulta;
	}

	public void setPlacaConsulta(String placaConsulta) {
		this.placaConsulta = placaConsulta;
	}

	public Date getFechaAccidenteConsulta() {
		return fechaAccidenteConsulta;
	}

	public void setFechaAccidenteConsulta(Date fechaAccidenteConsulta) {
		this.fechaAccidenteConsulta = fechaAccidenteConsulta;
	}

	public String getNumSiniestroConsulta() {
		return numSiniestroConsulta;
	}

	public void setNumSiniestroConsulta(String numSiniestroConsulta) {
		this.numSiniestroConsulta = numSiniestroConsulta;
	}

	public String getNombreConsulta() {
		return nombreConsulta;
	}

	public void setNombreConsulta(String nombreConsulta) {
		this.nombreConsulta = nombreConsulta;
	}

	public String getFiltroConductorTercero() {
		return filtroConductorTercero;
	}

	public void setFiltroConductorTercero(String filtroConductorTercero) {
		this.filtroConductorTercero = filtroConductorTercero;
	}

	public HtmlOutputText getTxtDeduciblePesos() {
		return txtDeduciblePesos;
	}

	public void setTxtDeduciblePesos(HtmlOutputText txtDeduciblePesos) {
		this.txtDeduciblePesos = txtDeduciblePesos;
	}

	public HtmlOutputText getTxtDeducibleDolar() {
		return txtDeducibleDolar;
	}

	public void setTxtDeducibleDolar(HtmlOutputText txtDeducibleDolar) {
		this.txtDeducibleDolar = txtDeducibleDolar;
	}

	public HtmlOutputText getTxtValorDanho() {
		return txtValorDanho;
	}

	public void setTxtValorDanho(HtmlOutputText txtValorDanho) {
		this.txtValorDanho = txtValorDanho;
	}

	public HtmlOutputText getTxtSanciones() {
		return txtSanciones;
	}

	public void setTxtSanciones(HtmlOutputText txtSanciones) {
		this.txtSanciones = txtSanciones;
	}

	public String getSanciones() {
		return sanciones;
	}

	public void setSanciones(String sanciones) {
		this.sanciones = sanciones;
	}

	public String getOrdenTrabajo() {
		return ordenTrabajo;
	}

	public void setOrdenTrabajo(String ordenTrabajo) {
		this.ordenTrabajo = ordenTrabajo;
	}

	public String getAccRecomendaciones() {
		return accRecomendaciones;
	}

	public void setAccRecomendaciones(String accRecomendaciones) {
		this.accRecomendaciones = accRecomendaciones;
	}

	public HtmlOutputText getTxtRecomedacion() {
		return txtRecomedacion;
	}

	public void setTxtRecomedacion(HtmlOutputText txtRecomedacion) {
		this.txtRecomedacion = txtRecomedacion;
	}

	public String getAccObservaciones() {
		return accObservaciones;
	}

	public void setAccObservaciones(String accObservaciones) {
		this.accObservaciones = accObservaciones;
	}

	public HtmlOutputText getTxtObservacion() {
		return txtObservacion;
	}

	public void setTxtObservacion(HtmlOutputText txtObservacion) {
		this.txtObservacion = txtObservacion;
	}

	public Long getCodigoAccConsulta() {
		return codigoAccConsulta;
	}

	public void setCodigoAccConsulta(Long codigoAccConsulta) {
		this.codigoAccConsulta = codigoAccConsulta;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public HtmlInputText getTxtEmail() {
		return txtEmail;
	}

	public void setTxtEmail(HtmlInputText txtEmail) {
		this.txtEmail = txtEmail;
	}

	public HtmlSelectOneMenu getCbxHora() {
		return cbxHora;
	}

	public void setCbxHora(HtmlSelectOneMenu cbxHora) {
		this.cbxHora = cbxHora;
	}

	public HtmlSelectOneMenu getCbxMinutos() {
		return cbxMinutos;
	}

	public void setCbxMinutos(HtmlSelectOneMenu cbxMinutos) {
		this.cbxMinutos = cbxMinutos;
	}

	public HtmlOutputText getLblHora() {
		return lblHora;
	}

	public void setLblHora(HtmlOutputText lblHora) {
		this.lblHora = lblHora;
	}

	public HtmlInputTextarea getTxtOrdenTrabajo() {
		return txtOrdenTrabajo;
	}

	public void setTxtOrdenTrabajo(HtmlInputTextarea txtOrdenTrabajo) {
		this.txtOrdenTrabajo = txtOrdenTrabajo;
	}

	public HtmlInputText getPlacaVHC() {
		return placaVHC;
	}

	public void setPlacaVHC(HtmlInputText placaVHC) {
		this.placaVHC = placaVHC;
	}

	public String getParametroBusquedadAuxiliarAccidentes() {
		return parametroBusquedadAuxiliarAccidentes;
	}

	public void setParametroBusquedadAuxiliarAccidentes(
			String parametroBusquedadAuxiliarAccidentes) {
		this.parametroBusquedadAuxiliarAccidentes = parametroBusquedadAuxiliarAccidentes;
	}

	public HtmlOutputText getIdAuxiliaresAccidentes() {
		return idAuxiliaresAccidentes;
	}

	public void setIdAuxiliaresAccidentes(HtmlOutputText idAuxiliaresAccidentes) {
		this.idAuxiliaresAccidentes = idAuxiliaresAccidentes;
	}

	public void setShowCarnet(boolean showCarnet) {
		this.showCarnet = showCarnet;
	}

	public HtmlInputText getTxtCantidadTestigos() {
		return txtCantidadTestigos;
	}

	public void setTxtCantidadTestigos(HtmlInputText txtCantidadTestigos) {
		this.txtCantidadTestigos = txtCantidadTestigos;
	}

	public HtmlInputText getTxtCantidadLesionados() {
		return txtCantidadLesionados;
	}

	public void setTxtCantidadLesionados(HtmlInputText txtCantidadLesionados) {
		this.txtCantidadLesionados = txtCantidadLesionados;
	}

	public HtmlInputText getTxtCantidadVehiculosInvolucrados() {
		return txtCantidadVehiculosInvolucrados;
	}

	public void setTxtCantidadVehiculosInvolucrados(
			HtmlInputText txtCantidadVehiculosInvolucrados) {
		this.txtCantidadVehiculosInvolucrados = txtCantidadVehiculosInvolucrados;
	}
}
