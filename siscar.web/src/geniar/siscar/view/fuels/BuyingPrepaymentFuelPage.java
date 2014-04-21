package geniar.siscar.view.fuels;

import geniar.siscar.consults.impl.ConsultsServiceImpl;
import geniar.siscar.logic.consultas.SearchPrepaid;
import geniar.siscar.logic.consultas.SearchUser;
import geniar.siscar.logic.consultas.SearchVehicles;
import geniar.siscar.logic.fuels.services.PrepaymentService;
import geniar.siscar.logic.fuels.services.SearchPrepaidService;
import geniar.siscar.logic.fuels.services.impl.SearchPrepaidServiceImpl;
import geniar.siscar.logic.parameters.services.FuelTariffService;
import geniar.siscar.model.CostCenterTypeFuel;
import geniar.siscar.model.CostCentersFuel;
import geniar.siscar.model.CostsCenters;
import geniar.siscar.model.LegateesTypes;
import geniar.siscar.model.Tariffs;
import geniar.siscar.model.Users;
import geniar.siscar.model.Vehicles;
import geniar.siscar.model.VehiclesAssignation;
import geniar.siscar.persistence.AccountingParametersDAO;
import geniar.siscar.persistence.CostCenterTypeFuelDAO;
import geniar.siscar.persistence.EntityManagerHelper;
import geniar.siscar.util.FacesUtils;
import geniar.siscar.util.PopupMessagePage;
import geniar.siscar.util.Util;
import geniar.siscar.util.ViewOptionUtil;
import geniar.siscar.view.autenticate.LoginPage;
import gwork.exception.GWorkException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import com.icesoft.faces.component.ext.HtmlDataTable;
import com.icesoft.faces.component.ext.HtmlInputText;
import com.icesoft.faces.component.ext.HtmlOutputLabel;
import com.icesoft.faces.component.ext.HtmlSelectOneMenu;
import com.icesoft.faces.component.ext.RowSelectorEvent;

/**
 * Esta clase maneja la lógica visual para la compra de combustible modalidad
 * prepago.
 * 
 * @author Mauricio Cuenca Narváez
 * @version 0.1.0
 * 
 */
public class BuyingPrepaymentFuelPage {

	// Servicios

	// Componentes
	private HtmlInputText txtFechaIni;
	private HtmlOutputLabel txtUsrIdentification;
	private HtmlDataTable tblVehiculos;
	private HtmlDataTable tblFiltroEmpleados;
	private HtmlDataTable tblCostCenters;
	private HtmlDataTable tblCostCentersUsers;
	private HtmlSelectOneMenu selectAsignatario;
	private SelectItem listLegateeTypes[];

	// Variables de negocio
	private String paramCC;
	private Date fechaInicio;
	private String carneEmpleado;
	private String placaVehiculo;
	private String nombreEmpleado;
	private String nombreEmpleadoFiltro;
	private String carneEmpleadoFiltro;
	private String emailEmpleado;
	private String centroCosto;
	private String asignacion;
	private Long lgtCodigo;
	private Float valorCompraCC;
	private float totalCompra;
	private float promedio;
	private float valorCompra;
	private float capMaximaTanque;
	private Float compraMinima;
	private List<Vehicles> listaVehiculos;
	private List<Users> listaUsuarios;
	private List<CostsCenters> listCostsCenters;
	private List<CostCentersFuel> listCostsCentersUser;
	private List<CostCentersFuel> listCostsCentersSys;
	private boolean activarConfirmacion;
	private LoginPage loginPage = (LoginPage) FacesUtils
			.getManagedBean("loginPage");

	// Variables de mensajes de confirmación

	// Variables para visibilidad y habilitacion de campos y botones
	private boolean disabledNombreEmpleado;
	private boolean disabledCarneEmpleado;
	private boolean showFiltroUsuarios;
	private boolean showCentroCosto;
	private PrepaymentService prepaymentService;
	private FuelTariffService fuelTariffService;

	/**
	 * Constructor básico.
	 */
	public BuyingPrepaymentFuelPage() {
		fechaInicio = new Date();
		setFechaInicio(fechaInicio);
	}

	// Métodos acceso a logica de negocio

	/**
	 * Despliega el popUp de búsqueda de Centros de costo.
	 */
	public void showFiltroCC(ActionEvent event) {
		setShowCentroCosto(true);
	}

	/**
	 * Oculta el popUp de búsqueda de centros de costo.
	 * 
	 * @param event
	 */
	public void hideFiltroCC(ActionEvent event) {
		setShowCentroCosto(false);
	}

	/**
	 * Cambia el monto cargado a un centro de costos.
	 */
	public void listenerValorCompra(ValueChangeEvent event) {
		try {

			if (tblCostCentersUsers != null
					&& tblCostCentersUsers.getRowData() != null) {
				CostCentersFuel costCentersFuel = (CostCentersFuel) tblCostCentersUsers
						.getRowData();
				costCentersFuel.setCcfValor(valorCompraCC);

				for (int i = 0; i < listCostsCentersUser.size(); i++) {

					CostCentersFuel centersFuel = listCostsCentersUser.get(i);

					if (centersFuel.getCostsCenters().getCocNumero().equals(
							costCentersFuel.getCostsCenters().getCocNumero())) {
						listCostsCentersUser.set(i, costCentersFuel);
						break;
					}

				}

				totalCompra = 0f;

				for (CostCentersFuel centersFuel : listCostsCentersUser) {
					totalCompra = totalCompra + centersFuel.getCcfValor();
				}
				setTotalCompra(totalCompra);

				valorCompraCC = 0f;
			}

		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	/**
	 * Copia el numero del centro de costos seleccionado en el campo de centro
	 * de costo
	 * 
	 * @param event
	 */
	public void action_seleccionarCC(ActionEvent event) {
		try {
			if (tblCostCenters != null && tblCostCenters.getRowData() != null) {
				// Se carga el centro de costos que seleccionó el usuario
				CostCentersFuel ccf = (CostCentersFuel) tblCostCenters
						.getRowData();

				setCentroCosto(ccf.getCostsCenters().getCocNumero());
				setShowCentroCosto(false);
			}
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	/**
	 * Agrega el centro de costos seleccionado por el usuario a la lista de
	 * centros de costo del usuario.
	 */
	public void action_adicionarCentroCosto(ActionEvent event) {
		try {

			if (centroCosto == null || centroCosto.trim().length() == 0)
				throw new GWorkException(Util
						.loadErrorMessageValue("ERROR.CCADICIONPRE"));

			System.out.println(valorCompra);
			if (valorCompraCC == null
					|| valorCompraCC.toString().trim().length() == 0)
				throw new GWorkException(Util
						.loadErrorMessageValue("VALOR.COMPRA.NULL"));

			if (valorCompraCC == 0F)
				throw new GWorkException(Util
						.loadErrorMessageValue("VALOR.COMPRA.NULL"));

			if (valorCompraCC < compraMinima) {
				throw new GWorkException(Util
						.loadErrorMessageValue("ERROR.VALCOMPREMAY"));
			}

			// Se carga el centro de costos que seleccionó el usuario
			CostCentersFuel ccf = null;
			CostsCenters cc = null;

			for (CostCentersFuel ccfTemp : listCostsCentersSys) {
				if (ccfTemp.getCostsCenters().getCocNumero()
						.equals(centroCosto)) {
					ccf = ccfTemp;
					cc = ccf.getCostsCenters();
					break;
				}
			}

			if (ccf == null) {
				throw new GWorkException(Util
						.loadErrorMessageValue("ERROR.ADDCOSTCENTER"));
			}

			ccf.setCcfValor(getValorCompraCC());
			CostCenterTypeFuel ccft = new CostCenterTypeFuelDAO().findById(1L);
			ccf.setCostCenterTypeFuel(ccft);
			ccf.setCostsCenters(cc);
			// Se consulta si el centro de costo tiene disponibilidad
			// presupuestal
			String cuenta = new AccountingParametersDAO().findById(12L)
					.getAccount().getAccNumeroCuenta();
			System.out.println("CUENTA--> " + cuenta);

			int agno = new Date().getYear();

			// if (!new
			// ConsultsServiceImpl().consultarDisponibiladaPresupuestal(
			// agno + 1900, ccf // TODO
			// .getCostsCenters().getCocNumero(), cuenta, null,
			// new Double(ccf.getCcfValor())))
			// throw new GWorkException(Util
			// .loadErrorMessageValue("ERROR.NODISPPPTO"));

			// Si no había agregado ningun centro de costos
			if (listCostsCentersUser == null) {
				// Se inicializa la lista de centros de costo del usuario
				listCostsCentersUser = new ArrayList<CostCentersFuel>();
			}

			// Si la lista de centros de costo del usuario ya tiene centros
			// de costo se debe validar que aquel que se va a agregar no se
			// repita en la lista
			if (!listCostsCentersUser.isEmpty()) {

				boolean yaLoTiene = false;

				for (CostCentersFuel centersFuel : listCostsCentersUser) {
					if (centersFuel.getCostsCenters().getCocNumero().equals(
							ccf.getCostsCenters().getCocNumero())) {
						yaLoTiene = true;
						break;
					}
				}
				if (yaLoTiene)
					throw new GWorkException(Util
							.loadErrorMessageValue("CENTROCOSTOEXISTE"));

				if (!yaLoTiene) {
					listCostsCentersUser.add(ccf);
					contabilizarTotal();
				}
			} else {
				listCostsCentersUser.add(ccf);
				contabilizarTotal();
			}

			fechaInicio = new Date();
			setCentroCosto("");
			setValorCompraCC(0f);
		} catch (GWorkException e) {

			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	private void contabilizarTotal() {
		totalCompra = 0f;

		for (CostCentersFuel centersFuel : listCostsCentersUser) {
			totalCompra = totalCompra + centersFuel.getCcfValor();
		}
		setTotalCompra(totalCompra);
	}

	/**
	 * Quita el centro de costo seleccionado de la lista con los centros de
	 * costos a los cuales el usuario quiere cargar la compra del prepago de
	 * combustible
	 * 
	 * @param event
	 */
	public void action_quitarCentroCosto(ActionEvent event) {
		try {
			if (tblCostCentersUsers != null
					&& tblCostCentersUsers.getRowData() != null) {
				CostCentersFuel cc = (CostCentersFuel) tblCostCentersUsers
						.getRowData();
				if (listCostsCentersUser != null
						&& listCostsCentersUser.contains(cc)) {
					listCostsCentersUser.remove(cc);
				}
				contabilizarTotal();
			}
		} catch (Exception e) {
			e.printStackTrace();
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	/**
	 * Consulta los empleados que coincidan con el nombre de empleado o carnè
	 * ingresado
	 */
	public void actionBuscarEmpleado(ActionEvent event) {
		try {
			// Si el usuario no ingresó ni el nombre del empleado
			// ni el carné
			if ((nombreEmpleadoFiltro == null || nombreEmpleadoFiltro.trim()
					.length() == 0)
					&& (carneEmpleadoFiltro == null || carneEmpleadoFiltro
							.trim().length() == 0)) {

				// Se genera una excepción solicitando el ingreso de un
				// parametro de búsqueda.
				mostrarMensaje(
						Util.loadErrorMessageValue("ERROR.INGRPARAMPRE"), false);
				return;
			}

			// Si el usuario no ingresó el carné del empleado
			if (carneEmpleadoFiltro == null
					|| carneEmpleadoFiltro.trim().length() == 0) {

				// ... pero si ingresó el nombre del empleado
				if (nombreEmpleadoFiltro != null
						&& nombreEmpleadoFiltro.trim().length() > 0) {

					if (!Util.validarCadenaCaracteres(nombreEmpleadoFiltro)) {
						mostrarMensaje(
								Util
										.loadErrorMessageValue("ERROR.INGRCARACNOMEMP"),
								false);
						return;
					}

					try {
						Util.validarLimite(nombreEmpleadoFiltro, 20, 1,
								"ERROR.LIMNOMEMPPRE");
					} catch (Exception e) {
						mostrarMensaje(e.getMessage(), false);
						return;
					}

					// Se llama a la consulta de usuarios con la opción 1
					// la cual indica que debe hacer la búsqueda por el nombre
					// del empleado
					consultarUsuarios(nombreEmpleadoFiltro,
							carneEmpleadoFiltro, 1);
				}
			} else {
				if (nombreEmpleadoFiltro == null
						|| nombreEmpleadoFiltro.trim().length() == 0) {

					// ... pero si ingresó el nombre del empleado
					if (carneEmpleadoFiltro != null
							&& carneEmpleadoFiltro.trim().length() > 0) {

						if (!Util.validarNumerosConsulta(carneEmpleadoFiltro)) {
							mostrarMensaje(
									Util
											.loadErrorMessageValue("ERROR.INGRNUMCARPRE"),
									false);
							return;
						}

						try {
							Util.validarLimite(carneEmpleadoFiltro, 20, 1,
									"ERROR.LIMNUNCARNEPRE");
						} catch (Exception e) {
							mostrarMensaje(e.getMessage(), false);
							return;
						}

						// Se llama a la consulta de usuarios con la opción 1
						// la cual indica que debe hacer la búsqueda por el
						// nombre
						// del empleado
						consultarUsuarios(nombreEmpleadoFiltro,
								carneEmpleadoFiltro, 2);
					}
				}
			}

			// Si ingresó los dos parametros de búsqueda
			if ((nombreEmpleadoFiltro != null && nombreEmpleadoFiltro.trim()
					.length() > 0)
					&& (carneEmpleadoFiltro != null && carneEmpleadoFiltro
							.trim().length() > 0)) {
				// ... por defecto utiliza el carné del empleado.
				consultarUsuarios(nombreEmpleadoFiltro, carneEmpleadoFiltro, 2);
			}

		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	/**
	 * Accede a la consulta para el filtro de usuarios.
	 */
	private void consultarUsuarios(String nombreUsuario, String carne,
			int opcion) throws GWorkException {

		listaUsuarios = (List<Users>) SearchUser.consultarUsuarios(
				nombreEmpleadoFiltro, carneEmpleadoFiltro, opcion);

		// Si la consulta no arroja resultados se genera una
		// excepción
		if (listaUsuarios == null || listaUsuarios.isEmpty()) {
			mostrarMensaje(Util.loadErrorMessageValue("search.not.found"),
					false);
			return;
		}

		setListaUsuarios(listaUsuarios);

	}

	/**
	 * Se ejecuta cada vez que se selecciona un elemento de la tabla
	 * 
	 * @param event
	 */
	public void listenerRowSelector(RowSelectorEvent event) {
		if (tblFiltroEmpleados != null
				&& tblFiltroEmpleados.getRowData() != null) {
			Users user = (Users) tblFiltroEmpleados.getRowData();

			setCarneEmpleado(user.getUsrIdentificacion());
			setEmailEmpleado(user.getUsrEmail());
			setNombreEmpleado(user.getUsrNombre());

			limpiarConsulta();
			showFiltroUsuarios = false;
		}
	}

	public void listenerTipoAsignatario(ValueChangeEvent event) {
		try {
			if (event.getNewValue() != null) {
				Long idLegatee = new Long("" + event.getNewValue());

				listaVehiculos = SearchVehicles
						.consultarVehiculosPorTipoAsignatario(idLegatee);

			}
		} catch (Exception e) {
			e.printStackTrace();
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	@SuppressWarnings("static-access")
	public void listener_consultarAsignacionVehiculo(ValueChangeEvent event) {
		String placa = (String) event.getNewValue();
		VehiclesAssignation vehiclesAssignation = null;
		Vehicles vehicles = null;
		SearchPrepaidService prepaidService = new SearchPrepaidServiceImpl();
		if (placa != null && placa.trim().length() > 0) {
			try {
				if (!Util
						.validarCadenaCaracteresEspecialesNumLetrasGuion(placa))
					throw new GWorkException(Util
							.loadErrorMessageValue("PLACA.CARACTER"));
				if (placa != null && placa.trim().length() < 2
						&& placa.trim().length() != 0)
					throw new GWorkException(Util
							.loadErrorMessageValue("PLACA.CONSULTA"));

				vehicles = new SearchVehicles()
						.consultarVehiculosPorPlacaSinFiltros(placa
								.toUpperCase());
				vehiclesAssignation = new SearchVehicles()
						.consultarAsignacionVehiculo(placa.toUpperCase());
				if (vehicles == null)
					throw new GWorkException(Util
							.loadErrorMessageValue("ERROR.NUMPLACANEXIST"));
				if (vehiclesAssignation == null)
					throw new GWorkException(Util
							.loadErrorMessageValue("SOLICITUD.ASIGNACION"));

				vehiclesAssignation = EntityManagerHelper.getEntityManager()
						.merge(vehiclesAssignation);

				if (vehiclesAssignation != null
						&& vehiclesAssignation.getRequests().getLegateesTypes() == null)
					throw new GWorkException(Util
							.loadErrorMessageValue("PREPAGO.ASIGNACION"));

				if (vehiclesAssignation != null
						&& vehiclesAssignation.getRequests().getLegateesTypes() != null
						&& (vehiclesAssignation.getRequests()
								.getLegateesTypes().getLgtCodigo().longValue() == ViewOptionUtil.CONVENIO
								.longValue() || vehiclesAssignation
								.getRequests().getLegateesTypes()
								.getLgtCodigo().longValue() == ViewOptionUtil.PERSONAL
								.longValue()))
					throw new GWorkException(Util
							.loadErrorMessageValue("PREPAGO.ASIGNACION"));

				nombreEmpleado = vehiclesAssignation.getRequests()
						.getUsersByRqsUser().getUsrNombre();
				carneEmpleado = vehiclesAssignation.getRequests()
						.getUsersByRqsUser().getUsrIdentificacion();
				emailEmpleado = vehiclesAssignation.getRequests()
						.getUsersByRqsUser().getUsrEmail();
				calcularTarifa(vehiclesAssignation.getVehicles());
				vehiclesAssignation = EntityManagerHelper.getEntityManager()
						.merge(vehiclesAssignation);
				listCostsCentersSys = SearchPrepaidServiceImpl
						.listaCostCenterFuelByPlaca(placa.toUpperCase());

				if (vehiclesAssignation.getRequests().getLegateesTypes() != null) {
					asignacion = vehiclesAssignation.getRequests()
							.getLegateesTypes().getLgtNombre();
					lgtCodigo = vehiclesAssignation.getRequests()
							.getLegateesTypes().getLgtCodigo().longValue();
				}

			} catch (GWorkException e) {
				FacesUtils.addErrorMessage(e.getMessage());
			}
		}
	}

	/**
	 * Limpia el formulario
	 * 
	 * @param event
	 */
	public void action_limpiarConsulta(ActionEvent event) {
		limpiarConsulta();
	}

	/**
	 * Coloca los valores por defecto a los campos del filtro
	 */
	private void limpiarConsulta() {
		setCarneEmpleadoFiltro("");
		setNombreEmpleadoFiltro("");
		listaUsuarios = null;
		setListaUsuarios(listaUsuarios);
	}

	/**
	 * Carga la placa del vehiculo en el formulario de compra de prepago
	 * 
	 * @param event
	 */
	public void action_cargarVehiculo(ActionEvent event) {
		try {
			if (tblVehiculos != null) {
				if (tblVehiculos.getRowData() != null) {
					Vehicles vehicles = (Vehicles) tblVehiculos.getRowData();
					setCapMaximaTanque(vehicles.getVhcCapMaxTanq());
					setPlacaVehiculo(vehicles.getVhcPlacaDiplomatica());
					showFiltroUsuarios = false;
					calcularPromedioConsumo(vehicles.getVhcPlacaDiplomatica());
					calcularTarifa(vehicles);
				}
			}
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void calcularTarifa(Vehicles vehicles) throws GWorkException {

		Tariffs tariffs = fuelTariffService.consultarTarifaCombustible(vehicles
				.getFuelsTypes().getFutCodigo(), 7L, "CIAT");
		if (tariffs.getTrfValor() == null)
			throw new GWorkException(Util
					.loadErrorMessageValue("TARIFA.COMBUSTIBLE.NULL"));
		else
			compraMinima = tariffs.getTrfValor() * vehicles.getVhcCapMaxTanq();

	}

	/**
	 * 
	 * @param placa
	 */
	@SuppressWarnings("deprecation")
	private void calcularPromedioConsumo(String placa) throws Exception {
		try {
			promedio = prepaymentService.obtenerPromedio(placa);
			setPromedio(promedio);

			Date currentDate = new Date();

			int mesesRestantes = 12 - (currentDate.getMonth() + 1);

			valorCompra = promedio * mesesRestantes;
			setValorCompra(valorCompra);
		} catch (GWorkException e) {
			throw new Exception(e.getMessage());
		}
	}

	/**
	 * Despliega el filtro de busqueda del empleado
	 * 
	 * @param event
	 */
	public void action_abrirFiltroEmpleados(ActionEvent event) {
		try {
			showFiltroUsuarios = true;
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	/**
	 * Cierra el filtro de busqueda del empleado
	 * 
	 * @param event
	 */
	public void action_cerrarFiltroEmpleados(ActionEvent event) {
		try {
			showFiltroUsuarios = false;
			limpiarConsulta();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	/**
	 * Inicia el proceso de compra del prepago de combustible
	 * 
	 * @param event
	 */
	public void action_comprar(ActionEvent event) {

		try {

			if (placaVehiculo == null || placaVehiculo.trim().length() == 0) {
				throw new GWorkException(Util
						.loadErrorMessageValue("ERROR.INGRPLACA"));
			}

			if (lgtCodigo == null || lgtCodigo == -1) {
				throw new GWorkException(Util
						.loadErrorMessageValue("TIPOASIGNACION.NULO"));
			}

			if (nombreEmpleado == null || nombreEmpleado.trim().length() == 0) {
				throw new GWorkException(Util
						.loadErrorMessageValue("ERROR.INGRNAMEEMPL"));
			}

			if (listCostsCentersUser == null || listCostsCentersUser.isEmpty()) {
				throw new GWorkException(Util
						.loadErrorMessageValue("ERROR.SELUNCC"));
			}
			activarConfirmacion = true;
			mostrarMensaje(Util.loadMessageValue("MENSAJE.ALERTA"),
					activarConfirmacion);

		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

	}

	public void crearCompra() {

		try {
			prepaymentService.comprarPrepago(fechaInicio, listCostsCentersUser,
					placaVehiculo.toUpperCase(), carneEmpleado, loginPage
							.getLogin());

			limpiar();
			mostrarMensaje(Util.loadMessageValue("EXITO"), false);

		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void mostrarMensaje(String mensaje, boolean buttonCancel) {
		PopupMessagePage message = (PopupMessagePage) FacesUtils
				.getManagedBean("popupMessagePage");
		if (message != null)
			message.showPopup(mensaje, buttonCancel);
	}

	/**
	 * Limpia el formulario.
	 * 
	 * @param event
	 */
	public void action_limpiar(ActionEvent event) {
		limpiar();
	}

	/**
	 * Coloca los valores por defecto para el formulario de compra de prepago
	 */
	private void limpiar() {
		placaVehiculo = null;
		setPlacaVehiculo(null);
		fechaInicio = null;
		carneEmpleado = null;
		placaVehiculo = null;
		nombreEmpleado = null;
		emailEmpleado = null;
		listaVehiculos = new ArrayList<Vehicles>();
		listCostsCentersUser = new ArrayList<CostCentersFuel>();
		lgtCodigo = -1L;
		fechaInicio = new Date();
		setFechaInicio(fechaInicio);
		totalCompra = 0;
		promedio = 0;
		asignacion = null;
		compraMinima = null;
		setListCostsCentersSys(null);
		centroCosto = null;
	}

	/**
	 * Carga todos los centros de costo del sistema.
	 */
	public void buscarCC(ActionEvent event) {
		try {

			if (getParamCC() == null || getParamCC().trim().length() == 0) {
				mostrarMensaje(
						Util.loadErrorMessageValue("ERROR.INGRPARAMPRE"), false);
				return;
			}

			if (!Util
					.validarCadenaCaracteresEspecialesNumLetrasGuion(getParamCC())) {
				mostrarMensaje(Util
						.loadErrorMessageValue("ERROR.CARACFILTCCPRE"), false);
				return;
			}

			// Se consultan todos los centros de costos del sistema
			try {
				listCostsCenters = new ConsultsServiceImpl()
						.centrosCosto(getParamCC());// CIAT
			} catch (GWorkException e) {
				mostrarMensaje(e.getMessage(), false);
				return;
			}

			// Se utilizo para probar cuando no habia VPN
			// if (listCostsCenters == null) {
			// listCostsCenters = new SearchPrepaid()
			// .centrosCostoFiltradosXnumero(getParamCC()); // Local
			// if (listCostsCenters == null) {
			// mostrarMensaje(Util
			// .loadErrorMessageValue("ERROR.NOCCFOUND"), false);
			// return;
			// }
			// }

			listCostsCentersSys = new ArrayList<CostCentersFuel>();

			for (CostsCenters costsCenter : listCostsCenters) {

				CostCentersFuel costCentersFuel = new CostCentersFuel();
				costCentersFuel.setCostsCenters(costsCenter);

				if (!costsCenter.getCocNumero().equals("ccosto")) {
					listCostsCentersSys.add(costCentersFuel);
				}
			}

		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	// Métodos accesores
	/**
	 * @return the txtFechaIni
	 */
	public HtmlInputText getTxtFechaIni() {
		return txtFechaIni;
	}

	/**
	 * @param txtFechaIni
	 *            the txtFechaIni to set
	 */
	public void setTxtFechaIni(HtmlInputText txtFechaIni) {
		this.txtFechaIni = txtFechaIni;
	}

	/**
	 * @return the fechaInicio
	 */
	public Date getFechaInicio() {
		return fechaInicio;
	}

	/**
	 * @param fechaInicio
	 *            the fechaInicio to set
	 */
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = new Date();
	}

	/**
	 * @return the nombreEmpleado
	 */
	public String getNombreEmpleado() {
		return nombreEmpleado;
	}

	/**
	 * @param nombreEmpleado
	 *            the nombreEmpleado to set
	 */
	public void setNombreEmpleado(String nombreEmpleado) {
		this.nombreEmpleado = nombreEmpleado;
	}

	/**
	 * @return the emailEmpleado
	 */
	public String getEmailEmpleado() {
		return emailEmpleado;
	}

	/**
	 * @param emailEmpleado
	 *            the emailEmpleado to set
	 */
	public void setEmailEmpleado(String emailEmpleado) {
		this.emailEmpleado = emailEmpleado;
	}

	/**
	 * @return the carneEmpleado
	 */
	public String getCarneEmpleado() {
		return carneEmpleado;
	}

	/**
	 * @param carneEmpleado
	 *            the carneEmpleado to set
	 */
	public void setCarneEmpleado(String carneEmpleado) {
		this.carneEmpleado = carneEmpleado;
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
	 * @return the disabledNombreEmpleado
	 */
	public boolean isDisabledNombreEmpleado() {
		return disabledNombreEmpleado;
	}

	/**
	 * @param disabledNombreEmpleado
	 *            the disabledNombreEmpleado to set
	 */
	public void setDisabledNombreEmpleado(boolean disabledNombreEmpleado) {
		this.disabledNombreEmpleado = disabledNombreEmpleado;
	}

	/**
	 * @return the disabledCarneEmpleado
	 */
	public boolean isDisabledCarneEmpleado() {
		return disabledCarneEmpleado;
	}

	/**
	 * @param disabledCarneEmpleado
	 *            the disabledCarneEmpleado to set
	 */
	public void setDisabledCarneEmpleado(boolean disabledCarneEmpleado) {
		this.disabledCarneEmpleado = disabledCarneEmpleado;
	}

	/**
	 * @return the tblVehiculos
	 */
	public HtmlDataTable getTblVehiculos() {
		return tblVehiculos;
	}

	/**
	 * @param tblVehiculos
	 *            the tblVehiculos to set
	 */
	public void setTblVehiculos(HtmlDataTable tblVehiculos) {
		this.tblVehiculos = tblVehiculos;
	}

	/**
	 * @return the listaVehiculos
	 */
	public List<Vehicles> getListaVehiculos() {
		return listaVehiculos;
	}

	/**
	 * @param listaVehiculos
	 *            the listaVehiculos to set
	 */
	public void setListaVehiculos(List<Vehicles> listaVehiculos) {
		this.listaVehiculos = listaVehiculos;
	}

	/**
	 * @return the selectAsignatario
	 */
	public HtmlSelectOneMenu getSelectAsignatario() {
		return selectAsignatario;
	}

	/**
	 * @param selectAsignatario
	 *            the selectAsignatario to set
	 */
	public void setSelectAsignatario(HtmlSelectOneMenu selectAsignatario) {
		this.selectAsignatario = selectAsignatario;
	}

	/**
	 * @return the lgtCodigo
	 */
	public Long getLgtCodigo() {
		return lgtCodigo;
	}

	/**
	 * @param lgtCodigo
	 *            the lgtCodigo to set
	 */
	public void setLgtCodigo(Long lgtCodigo) {
		this.lgtCodigo = lgtCodigo;
	}

	/**
	 * @return the showFiltroUsuarios
	 */
	public boolean isShowFiltroUsuarios() {
		return showFiltroUsuarios;
	}

	/**
	 * @param showFiltroUsuarios
	 *            the showFiltroUsuarios to set
	 */
	public void setShowFiltroUsuarios(boolean showFiltroUsuarios) {
		this.showFiltroUsuarios = showFiltroUsuarios;
	}

	/**
	 * @return the listaUsuarios
	 */
	public List<Users> getListaUsuarios() {
		return listaUsuarios;
	}

	/**
	 * @param listaUsuarios
	 *            the listaUsuarios to set
	 */
	public void setListaUsuarios(List<Users> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}

	/**
	 * @return the nombreEmpleadoFiltro
	 */
	public String getNombreEmpleadoFiltro() {
		return nombreEmpleadoFiltro;
	}

	/**
	 * @param nombreEmpleadoFiltro
	 *            the nombreEmpleadoFiltro to set
	 */
	public void setNombreEmpleadoFiltro(String nombreEmpleadoFiltro) {
		this.nombreEmpleadoFiltro = nombreEmpleadoFiltro.toUpperCase();
	}

	/**
	 * @return the carneEmpleadoFiltro
	 */
	public String getCarneEmpleadoFiltro() {
		return carneEmpleadoFiltro;
	}

	/**
	 * @param carneEmpleadoFiltro
	 *            the carneEmpleadoFiltro to set
	 */
	public void setCarneEmpleadoFiltro(String carneEmpleadoFiltro) {
		this.carneEmpleadoFiltro = carneEmpleadoFiltro;
	}

	/**
	 * @return the txtUsrIdentification
	 */
	public HtmlOutputLabel getTxtUsrIdentification() {
		return txtUsrIdentification;
	}

	/**
	 * @param txtUsrIdentification
	 *            the txtUsrIdentification to set
	 */
	public void setTxtUsrIdentification(HtmlOutputLabel txtUsrIdentification) {
		this.txtUsrIdentification = txtUsrIdentification;
	}

	/**
	 * @return the tblFiltroEmpleados
	 */
	public HtmlDataTable getTblFiltroEmpleados() {
		return tblFiltroEmpleados;
	}

	/**
	 * @param tblFiltroEmpleados
	 *            the tblFiltroEmpleados to set
	 */
	public void setTblFiltroEmpleados(HtmlDataTable tblFiltroEmpleados) {
		this.tblFiltroEmpleados = tblFiltroEmpleados;
	}

	/**
	 * @return the listCostsCenters
	 */
	public List<CostsCenters> getListCostsCenters() {
		return listCostsCenters;
	}

	/**
	 * @param listCostsCenters
	 *            the listCostsCenters to set
	 */
	public void setListCostsCenters(List<CostsCenters> listCostsCenters) {
		this.listCostsCenters = listCostsCenters;
	}

	/**
	 * @return the listCostsCentersUser
	 */
	public List<CostCentersFuel> getListCostsCentersUser() {
		return listCostsCentersUser;
	}

	/**
	 * @param listCostsCentersUser
	 *            the listCostsCentersUser to set
	 */
	public void setListCostsCentersUser(
			List<CostCentersFuel> listCostsCentersUser) {
		this.listCostsCentersUser = listCostsCentersUser;
	}

	/**
	 * @return the tblCostCenters
	 */
	public HtmlDataTable getTblCostCenters() {
		return tblCostCenters;
	}

	/**
	 * @param tblCostCenters
	 *            the tblCostCenters to set
	 */
	public void setTblCostCenters(HtmlDataTable tblCostCenters) {
		this.tblCostCenters = tblCostCenters;
	}

	/**
	 * @return the tblCostCentersUsers
	 */
	public HtmlDataTable getTblCostCentersUsers() {
		return tblCostCentersUsers;
	}

	/**
	 * @param tblCostCentersUsers
	 *            the tblCostCentersUsers to set
	 */
	public void setTblCostCentersUsers(HtmlDataTable tblCostCentersUsers) {
		this.tblCostCentersUsers = tblCostCentersUsers;
	}

	/**
	 * @return the totalCompra
	 */
	public float getTotalCompra() {
		return totalCompra;
	}

	/**
	 * @param totalCompra
	 *            the totalCompra to set
	 */
	public void setTotalCompra(float totalCompra) {
		this.totalCompra = totalCompra;
	}

	public List<CostCentersFuel> getListCostsCentersSys() {
		return listCostsCentersSys;
	}

	public void setListCostsCentersSys(List<CostCentersFuel> listCostsCentersSys) {
		this.listCostsCentersSys = listCostsCentersSys;
	}

	public String getCentroCosto() {
		return centroCosto;
	}

	public void setCentroCosto(String centroCosto) {
		this.centroCosto = centroCosto;
	}

	public PrepaymentService getPrepaymentService() {
		return prepaymentService;
	}

	public void setPrepaymentService(PrepaymentService prepaymentService) {
		this.prepaymentService = prepaymentService;
	}

	public float getPromedio() {
		return promedio;
	}

	public void setPromedio(float promedio) {
		this.promedio = promedio;
	}

	public float getValorCompra() {
		return valorCompra;
	}

	public void setValorCompra(float valorCompra) {
		this.valorCompra = valorCompra;
	}

	public SelectItem[] getListLegateeTypes() {

		try {
			List<LegateesTypes> listUtilLegateesTypes = new SearchPrepaid()
					.asignatariosPrepago();
			listLegateeTypes = new SelectItem[listUtilLegateesTypes.size() + 1];
			listLegateeTypes[0] = new SelectItem("-1", "--SELECCIONAR--");
			int i = 1;

			for (LegateesTypes legateeType : listUtilLegateesTypes) {
				listLegateeTypes[i] = new SelectItem(
						legateeType.getLgtCodigo(), legateeType.getLgtNombre());
				i++;

			}
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return listLegateeTypes;
	}

	public void setListLegateeTypes(SelectItem[] listLegateeTypes) {
		this.listLegateeTypes = listLegateeTypes;
	}

	public boolean isShowCentroCosto() {
		return showCentroCosto;
	}

	public void setShowCentroCosto(boolean showCentroCosto) {
		this.showCentroCosto = showCentroCosto;
	}

	public String getParamCC() {
		return paramCC;
	}

	public void setParamCC(String paramCC) {
		this.paramCC = paramCC.toUpperCase();
	}

	public float getCapMaximaTanque() {
		return capMaximaTanque;
	}

	public void setCapMaximaTanque(float capMaximaTanque) {
		this.capMaximaTanque = capMaximaTanque;
	}

	public FuelTariffService getFuelTariffService() {
		return fuelTariffService;
	}

	public void setFuelTariffService(FuelTariffService fuelTariffService) {
		this.fuelTariffService = fuelTariffService;
	}

	public String getAsignacion() {
		return asignacion;
	}

	public void setAsignacion(String asignacion) {
		this.asignacion = asignacion;
	}

	public boolean isActivarConfirmacion() {
		return activarConfirmacion;
	}

	public void setActivarConfirmacion(boolean activarConfirmacion) {
		this.activarConfirmacion = activarConfirmacion;
	}

	public Float getValorCompraCC() {
		return valorCompraCC;
	}

	public void setValorCompraCC(Float valorCompraCC) {
		this.valorCompraCC = valorCompraCC;
	}

	public Float getCompraMinima() {
		return compraMinima;
	}

	public void setCompraMinima(Float compraMinima) {
		this.compraMinima = compraMinima;
	}
}
