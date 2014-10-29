package geniar.siscar.view.billing;

import geniar.siscar.logic.billing.services.PersonalMileageService;
import geniar.siscar.logic.billing.services.impl.PersonalMileageImpl;
import geniar.siscar.logic.consultas.SearchVehicles;
import geniar.siscar.model.FlatFile;
import geniar.siscar.model.ParValoresparametros;
import geniar.siscar.model.VehiclesAssignation;
import geniar.siscar.persistence.ParValoresparametrosDAO;
import geniar.siscar.util.FacesUtils;
import geniar.siscar.util.ParametersUtil;
import geniar.siscar.util.PopupMessagePage;
import geniar.siscar.util.Util;
import geniar.siscar.view.autenticate.LoginPage;
import gwork.exception.GWorkException;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import com.icesoft.faces.component.ext.HtmlCommandButton;

public class PersonalMileage {

	// Servicios ...
	private PersonalMileageService personalMileageService;
	private ParValoresparametros parValoresparametros;
	private ParValoresparametros TarifaValoresparametros;
	private VehiclesAssignation assignation;

	// Variables de la pantalla
	private String placaVehiculo;
	private String nombreAsignatario;
	private String carneAsignatario;
	private String Asignacion;
	private String fechaInicialStr;
	private String fechaFinalStr;
	private String kmInicial;
	private String kmFinal;
	private String kmRecorrido;
	private String kmPolitica;
	private String kmReportado;
	private String kmPersonal;
	private Float TarifaKmPersonal;
	private Float ValorCobrar;
	FlatFile flatFile = null;
	private Date fechaInicial;
	private Date fechaFinal;

	private boolean disableCrearKmPersonal;
	private boolean showDatos;
	private boolean disableModificarKmPersonal;
	private static Integer MODIFICAR = 1;

	private HtmlCommandButton btnCrear;
	private HtmlCommandButton btnModificar;

	private boolean showFechas = true;
	// Variables para mensajes de confirmación
	private boolean activarConfirmacion;
	private Integer opcConfirmacion;

	private Integer Crear_Km_Personal = 1;

	LoginPage loginPage = (LoginPage) FacesUtils.getManagedBean("loginPage");

	public PersonalMileage() {
		showDatos = false;
		disableCrearKmPersonal = true;
		disableModificarKmPersonal = true;
	}

	/**
	 * Consulta Los datos del vehiculo que se van a mostrar en la pantalla
	 */
	public void action_ConsultarVehiculo(ActionEvent event) {
		try {
			validarDatos();
			personalMileageService = new PersonalMileageImpl();
			flatFile = personalMileageService.getKmPersonalByYear(
					placaVehiculo, fechaInicial);
			assignation = SearchVehicles
					.consultarAsignacionVehiculoPrepagoKmPersonal(placaVehiculo);

			if (flatFile != null) {
				btnCrear.setDisabled(true);
				btnModificar.setDisabled(false);

			} else {
				btnCrear.setDisabled(false);
				btnModificar.setDisabled(true);
			}

			if (assignation != null) {
				nombreAsignatario = assignation.getRequests()
						.getUsersByRqsUser().getUsrNombre();

				if (assignation.getRequests().getUsersByRqsUser()
						.getUsrApellido() != null) {
					nombreAsignatario += " "
							+ assignation.getRequests().getUsersByRqsUser()
									.getUsrApellido();
				}

				// En caso de que las fechas de las asginacion sea diferentes al
				// rango de fechas del cobro del km personal
				if (fechaInicial.compareTo(assignation.getVhaFechaEntrega()) < 0)
					fechaInicial = assignation.getVhaFechaEntrega();

				if (assignation.getVhaFechaDev() != null
						&& fechaFinal.compareTo(assignation.getVhaFechaDev()) > 0)
					fechaFinal = assignation.getVhaFechaDev();

				// Calendar calendar = Calendar.getInstance();
				// Date FechaActual = ManipulacionFechas.getFechaActual();
				// calendar.setTime(FechaActual);
				// calendar.set(Calendar.MONTH, 0);
				// calendar.set(Calendar.DAY_OF_MONTH, 1);
				// Año inicial de la asignacion para comprar la vigencia de las
				// fechas de consulta de los servicios
				// de combustible
				// int anhoAsignacion = new Integer(ManipulacionFechas
				// .getAgno(assignation.getVhaFechaEntrega()));
				// int anhoActual = new Integer(ManipulacionFechas
				// .getAgno(new Date()));
				// // Si el año de la asignacion es menor al año actual,
				// entonces
				// // se toma el primer dia de la fecha actual de lo contrario
				// se
				// // toma como fecha inicial el primer dia de la asignacion
				// if (anhoAsignacion < anhoActual)
				// fechaInicial = calendar.getTime();
				// else
				// fechaInicial = ManipulacionFechas.getFechaActual();
				// fechaFinal = ManipulacionFechas.getFechaActual();

				kmInicial = SearchVehicles
						.KmPersonalInicial(assignation.getVehicles()
								.getVhcCodigo(), fechaInicial, fechaFinal);

				if (kmInicial == null || kmInicial.trim().length() < 0)
					throw new GWorkException(
							"El vehiculo no tiene kilometraje inicial");

				kmFinal = SearchVehicles
						.KmPersonalFinal(assignation.getVehicles()
								.getVhcCodigo(), fechaInicial, fechaFinal);

				if (kmFinal == null || kmInicial.trim().length() < 0)
					throw new GWorkException(
							"El vehiculo no tiene kilometraje final");

				kmRecorrido = String.valueOf(new Long(kmFinal)
						- new Long(kmInicial));

				carneAsignatario = assignation.getRequests().getRqsCarneLogin();
				if (assignation.getRequests().getLegateesTypes() != null)
					Asignacion = assignation.getRequests().getLegateesTypes()
							.getLgtNombre();
				else
					throw new GWorkException(
							"El vehiculo no tiene un tipo de asignación");

				Long tipoAsignacion = assignation.getRequests()
						.getLegateesTypes().getLgtCodigo().longValue();

				// consulta el valor del Km por politica con tipo de asisnacion
				// oficial
				if (tipoAsignacion == ParametersUtil.LGT_OF) {
					parValoresparametros = new ParValoresparametrosDAO()
							.findByProperty("parParametros.idparametro",
									ParametersUtil.KmPersonalOF).get(0);

					TarifaValoresparametros = new ParValoresparametrosDAO()
							.findByProperty("parParametros.idparametro",
									ParametersUtil.valorKmPersonalOF).get(0);
				} else {
					parValoresparametros = new ParValoresparametrosDAO()
							.findByProperty("parParametros.idparametro",
									ParametersUtil.KmPersonalOFS).get(0);

					TarifaValoresparametros = new ParValoresparametrosDAO()
							.findByProperty("parParametros.idparametro",
									ParametersUtil.valorKmPersonalOFS).get(0);
				}

				int politicaTemp = parValoresparametros.getValorinicial()
						.intValue();
				kmPolitica = String.valueOf(politicaTemp);
				TarifaKmPersonal = TarifaValoresparametros.getValorinicial()
						.floatValue();
				String formato = "dd-MMM-yyyy";
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
						formato);

				fechaInicialStr = simpleDateFormat.format(fechaInicial)
						.toString();
				fechaFinalStr = simpleDateFormat.format(fechaFinal).toString();

				showFechas = false;

				showDatos = true;
			} else
				throw new GWorkException(Util
						.loadErrorMessageValue("ASIGNACION_KM_PERSONAL"));
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	/**
	 * Inicia el proceso de creación de una nueva póliza.
	 * 
	 * @param event
	 */
	public void action_crearKmPersonal(ActionEvent event) {
		try {
			validarDatos();
			validarKmReportado(kmReportado);
			// setOpcConfirmacion(Crear_Km_Personal);
			// activarConfirmacion = true;
			crearKmPersonal();
			// mostrarMensaje(Util.loadMessageValue("MENSAJE.ALERTA"),
			// activarConfirmacion);

		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void crearKmPersonal() {
		try {
			// Llamado al servicio Km Personal...
			personalMileageService = new PersonalMileageImpl();
			personalMileageService.GuardarFlatFileKmPersonal(assignation,
					kmRecorrido, kmReportado, kmPersonal, ValorCobrar,
					loginPage.getLogin(), fechaInicial, fechaFinal, kmInicial,
					kmFinal, kmPolitica);

			mostrarMensaje(Util.loadMessageValue("EXITO"), false);
			disableCrearKmPersonal = false;

			limpiar(); // Se limpia el formulario
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	private void validarDatos() throws GWorkException {

		if (placaVehiculo != null && placaVehiculo.trim().length() == 0)
			throw new GWorkException(Util.loadErrorMessageValue("ERROR.PLACA"));

		if (!Util.validarPlaca(placaVehiculo))
			throw new GWorkException(Util.loadErrorMessageValue("ERROR.PLACA"));

		if (fechaInicial == null)
			throw new GWorkException(Util
					.loadErrorMessageValue("FECHA.INICIO.NULO"));

		if (fechaFinal == null)
			throw new GWorkException(Util
					.loadErrorMessageValue("FECHA.FINAL.NULO"));

		if (fechaInicial.compareTo(fechaFinal) > 0)
			throw new GWorkException(Util
					.loadErrorMessageValue("FCH_INI_FCH_FIN"));

		Util.validarLimite(placaVehiculo, 20, 2, "ERROR.LIMPLACA");

	}

	public void listener_KmReportado(ValueChangeEvent event) {
		try {
			kmReportado = event.getNewValue().toString();
			if (kmReportado != null && kmReportado.trim().length() > 0) {
				validarKmReportado(kmReportado);

				kmPersonal = String.valueOf(new Long(kmRecorrido)
						- new Long(kmReportado) - new Long(kmPolitica));

				ValorCobrar = new Long(kmPersonal) * TarifaKmPersonal;

				disableCrearKmPersonal = false;
			}
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	void validarKmReportado(String kmReportado) throws GWorkException {
		if (kmReportado == null || kmReportado.trim().length() == 0)
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.KILOMETRAJE"));

		if (kmReportado != null && !Util.validarNumerosParametros(kmReportado))
			throw new GWorkException(Util
					.loadErrorMessageValue("CARACTERES.NUMERICOS")
					+ " Kilometraje oficial Reportado");

		Util.validarLimite(kmReportado, 20, 1, "ERROR.KILOMETRAJE");
	}

	public void action_limpiarForma(ActionEvent event) {
		limpiar();
	}

	private void limpiar() {
		placaVehiculo = null;
		kmReportado = null;
		nombreAsignatario = null;
		carneAsignatario = null;
		Asignacion = null;
		fechaInicial = null;
		fechaFinal = null;
		kmInicial = null;
		kmFinal = null;
		kmRecorrido = null;
		kmPolitica = null;
		kmReportado = null;
		kmPersonal = null;
		showDatos = false;
		btnCrear.setDisabled(true);
		btnModificar.setDisabled(true);
		flatFile = null;
		showFechas = true;

	}

	public void mostrarMensaje(String mensaje, boolean buttonCancel) {
		PopupMessagePage message = (PopupMessagePage) FacesUtils
				.getManagedBean("popupMessagePage");
		if (message != null)
			message.showPopup(mensaje, buttonCancel);
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

	/**
	 * @return the placaVehiculo
	 */
	public String getPlacaVehiculo() {
		return placaVehiculo;
	}

	/**
	 * @param placaVehiculo
	 *            the placaVehiculo to set
	 */
	public void setPlacaVehiculo(String placaVehiculo) {
		this.placaVehiculo = placaVehiculo;
	}

	/**
	 * @return the nombreAsignatario
	 */
	public String getNombreAsignatario() {
		return nombreAsignatario;
	}

	/**
	 * @param nombreAsignatario
	 *            the nombreAsignatario to set
	 */
	public void setNombreAsignatario(String nombreAsignatario) {
		this.nombreAsignatario = nombreAsignatario;
	}

	/**
	 * @return the carneAsignatario
	 */
	public String getCarneAsignatario() {
		return carneAsignatario;
	}

	/**
	 * @param carneAsignatario
	 *            the carneAsignatario to set
	 */
	public void setCarneAsignatario(String carneAsignatario) {
		this.carneAsignatario = carneAsignatario;
	}

	/**
	 * @return the asignacion
	 */
	public String getAsignacion() {
		return Asignacion;
	}

	/**
	 * @param asignacion
	 *            the asignacion to set
	 */
	public void setAsignacion(String asignacion) {
		Asignacion = asignacion;
	}

	/**
	 * @return the kmInicial
	 */
	public String getKmInicial() {
		return kmInicial;
	}

	/**
	 * @param kmInicial
	 *            the kmInicial to set
	 */
	public void setKmInicial(String kmInicial) {
		this.kmInicial = kmInicial;
	}

	/**
	 * @return the kmFinal
	 */
	public String getKmFinal() {
		return kmFinal;
	}

	/**
	 * @param kmFinal
	 *            the kmFinal to set
	 */
	public void setKmFinal(String kmFinal) {
		this.kmFinal = kmFinal;
	}

	/**
	 * @return the kmRecorrido
	 */
	public String getKmRecorrido() {
		return kmRecorrido;
	}

	/**
	 * @param kmRecorrido
	 *            the kmRecorrido to set
	 */
	public void setKmRecorrido(String kmRecorrido) {
		this.kmRecorrido = kmRecorrido;
	}

	/**
	 * @return the kmPolitica
	 */
	public String getKmPolitica() {
		return kmPolitica;
	}

	/**
	 * @param kmPolitica
	 *            the kmPolitica to set
	 */
	public void setKmPolitica(String kmPolitica) {
		this.kmPolitica = kmPolitica;
	}

	/**
	 * @return the kmReportado
	 */
	public String getKmReportado() {
		return kmReportado;
	}

	/**
	 * @param kmReportado
	 *            the kmReportado to set
	 */
	public void setKmReportado(String kmReportado) {
		this.kmReportado = kmReportado;
	}

	/**
	 * @return the kmPersonal
	 */
	public String getKmPersonal() {
		return kmPersonal;
	}

	/**
	 * @param kmPersonal
	 *            the kmPersonal to set
	 */
	public void setKmPersonal(String kmPersonal) {
		this.kmPersonal = kmPersonal;
	}

	/**
	 * @return the disableCrearKmPersonal
	 */
	public boolean isDisableCrearKmPersonal() {
		return disableCrearKmPersonal;
	}

	/**
	 * @param disableCrearKmPersonal
	 *            the disableCrearKmPersonal to set
	 */
	public void setDisableCrearKmPersonal(boolean disableCrearKmPersonal) {
		this.disableCrearKmPersonal = disableCrearKmPersonal;
	}

	/**
	 * @return the personalMileageService
	 */
	public PersonalMileageService getPersonalMileageService() {
		return personalMileageService;
	}

	/**
	 * @param personalMileageService
	 *            the personalMileageService to set
	 */
	public void setPersonalMileageService(
			PersonalMileageService personalMileageService) {
		this.personalMileageService = personalMileageService;
	}

	/**
	 * @return the crear_Km_Personal
	 */
	public Integer getCrear_Km_Personal() {
		return Crear_Km_Personal;
	}

	/**
	 * @param crear_Km_Personal
	 *            the crear_Km_Personal to set
	 */
	public void setCrear_Km_Personal(Integer crear_Km_Personal) {
		Crear_Km_Personal = crear_Km_Personal;
	}

	/**
	 * @return the showDatos
	 */
	public boolean isShowDatos() {
		return showDatos;
	}

	/**
	 * @param showDatos
	 *            the showDatos to set
	 */
	public void setShowDatos(boolean showDatos) {
		this.showDatos = showDatos;
	}

	/**
	 * @return the parValoresparametros
	 */
	public ParValoresparametros getParValoresparametros() {
		return parValoresparametros;
	}

	/**
	 * @param parValoresparametros
	 *            the parValoresparametros to set
	 */
	public void setParValoresparametros(
			ParValoresparametros parValoresparametros) {
		this.parValoresparametros = parValoresparametros;
	}

	/**
	 * @return the tarifaValoresparametros
	 */
	public ParValoresparametros getTarifaValoresparametros() {
		return TarifaValoresparametros;
	}

	/**
	 * @param tarifaValoresparametros
	 *            the tarifaValoresparametros to set
	 */
	public void setTarifaValoresparametros(
			ParValoresparametros tarifaValoresparametros) {
		TarifaValoresparametros = tarifaValoresparametros;
	}

	/**
	 * @return the tarifaKmPersonal
	 */
	public Float getTarifaKmPersonal() {
		return TarifaKmPersonal;
	}

	/**
	 * @param tarifaKmPersonal
	 *            the tarifaKmPersonal to set
	 */
	public void setTarifaKmPersonal(Float tarifaKmPersonal) {
		TarifaKmPersonal = tarifaKmPersonal;
	}

	public void action_modificarKmPersonal(ActionEvent actionEvent) {

		try {
			validarDatos();
			validarKmReportado(kmReportado);
			setOpcConfirmacion(MODIFICAR);
			activarConfirmacion = true;
			mostrarMensaje(Util.loadMessageValue("MENSAJE.ALERTA"),
					activarConfirmacion);
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

	}

	public void modificarKmPersonal() {
		try {
			// Llamado al servicio Km Personal...
			personalMileageService = new PersonalMileageImpl();

			personalMileageService.modificarFlatFileKmPersonal(assignation,
					kmRecorrido, kmReportado, kmPersonal, ValorCobrar,
					loginPage.getLogin(), flatFile.getFfCodigo(), fechaInicial,
					fechaFinal, kmInicial, kmFinal, kmPolitica);
			mostrarMensaje(Util.loadMessageValue("EXITO"), false);
			limpiar(); // Se limpia el formulario

		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

	}

	public boolean isDisableModificarKmPersonal() {
		return disableModificarKmPersonal;
	}

	public void setDisableModificarKmPersonal(boolean disableModificarKmPersonal) {
		this.disableModificarKmPersonal = disableModificarKmPersonal;
	}

	public HtmlCommandButton getBtnCrear() {
		return btnCrear;
	}

	public void setBtnCrear(HtmlCommandButton btnCrear) {
		this.btnCrear = btnCrear;
	}

	public HtmlCommandButton getBtnModificar() {
		return btnModificar;
	}

	public void setBtnModificar(HtmlCommandButton btnModificar) {
		this.btnModificar = btnModificar;
	}

	public void setFechaInicial(Date fechaInicial) {
		this.fechaInicial = fechaInicial;
	}

	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

	public Date getFechaInicial() {
		return fechaInicial;
	}

	public Date getFechaFinal() {
		return fechaFinal;
	}

	public String getFechaInicialStr() {
		return fechaInicialStr;
	}

	public void setFechaInicialStr(String fechaInicialStr) {
		this.fechaInicialStr = fechaInicialStr;
	}

	public String getFechaFinalStr() {
		return fechaFinalStr;
	}

	public void setFechaFinalStr(String fechaFinalStr) {
		this.fechaFinalStr = fechaFinalStr;
	}

	public boolean isShowFechas() {
		return showFechas;
	}

	public void setShowFechas(boolean showFechas) {
		this.showFechas = showFechas;
	}

	public void listener_periodoFecha(ValueChangeEvent event) {

		Date fechaFin = (Date) event.getNewValue();

		if (fechaFin != null) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(fechaFin);
			calendar.set(Calendar.MONTH, 11);
			calendar.set(Calendar.DAY_OF_MONTH, 31);
			fechaFin = calendar.getTime();
			setFechaFinal(fechaFin);
		}

	}
}