package geniar.siscar.view.policies;

import geniar.siscar.logic.consultas.SearchPolicies;
import geniar.siscar.logic.consultas.SearchPoliciesVehicles;
import geniar.siscar.logic.policies.services.InconsistenciesService;
import geniar.siscar.logic.policies.services.InconsistenciesTypesService;
import geniar.siscar.logic.policies.services.PoliciesInvoiceService;
import geniar.siscar.logic.policies.services.PoliciesService;
import geniar.siscar.logic.policies.services.PoliciesTypeService;
import geniar.siscar.logic.policies.services.impl.PoliciesInvoiceServiceImpl;
import geniar.siscar.model.Inconsistencies;
import geniar.siscar.model.Policies;
import geniar.siscar.model.PoliciesInvoice;
import geniar.siscar.model.PoliciesVehicles;
import geniar.siscar.model.VOPolicieInvoice;
import geniar.siscar.model.VOPolicies;
import geniar.siscar.persistence.EntityManagerHelper;
import geniar.siscar.util.FacesUtils;
import geniar.siscar.util.FileUtils;
import geniar.siscar.util.ManipulacionFechas;
import geniar.siscar.util.ParametersUtil;
import geniar.siscar.util.PopupMessagePage;
import geniar.siscar.util.Util;
import geniar.siscar.view.autenticate.LoginPage;
import gwork.exception.GWorkException;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.EventObject;
import java.util.List;

import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import com.icesoft.faces.component.ext.HtmlDataTable;
import com.icesoft.faces.component.ext.HtmlInputText;
import com.icesoft.faces.component.ext.HtmlSelectOneMenu;
import com.icesoft.faces.component.inputfile.InputFile;
import com.icesoft.faces.webapp.xmlhttp.PersistentFacesState;
import com.icesoft.faces.webapp.xmlhttp.RenderingException;

/**
 * 
 * @author Diego Humberto Bocanegra
 * @fechaCreacion (dd-mm-yyyy) 27-07-2009
 * 
 */
public class PolicyInvoicePage {

	/* Servicios del bean */
	private PoliciesService policiesService;
	private PoliciesTypeService policiesTypeService;
	private PoliciesInvoiceService policiesInvoiceService;
	private InconsistenciesTypesService inconsistenciesTypesService;
	private InconsistenciesService inconsistenciesService;

	/* Bindings y otros */
	private HtmlSelectOneMenu selectSeguro;
	private HtmlInputText txtNumeroPoliza;
	private HtmlInputText txtNumeroFactura;
	private HtmlInputText txtNuevoNumeroFactura;
	private HtmlInputText txtFechaFactura;
	private HtmlInputText txtConcepto;
	private HtmlInputText txtObserv;
	private PersistentFacesState estadoCarga;
	private HtmlDataTable tblInconsistencias;
	private HtmlDataTable tblPvs;
	private HtmlDataTable tblPolizas;
	private HtmlDataTable tblPolizasSOAT;
	private HtmlDataTable tblPolizasSOATIncluidas;
	private HtmlDataTable tblFacturas;

	/* Datos */
	private String nombreTipoPoliza;
	private Long idTipoPoliza;
	private Long numeroPoliza;
	private String numeroFactura;
	private String numeroNuevaFactura;
	private Float valorPrima;
	private Date fechaFactura;
	private String conceptoFact;
	private Long valorAsegurado;
	private Long valorComercialCIAT;
	private Float valorIVA;
	private Float valorTotal;
	private Long valorTotalFactura = 0L;
	private Integer numeroFilas;

	/** Datos para SOAT */
	private boolean showSoat;
	private boolean showNumPoliza = true;

	/** Indica si se habilita o no el boton crear encabezado. */
	private boolean disableCrear;
	/** Indica si se habilita o no el boton ver datos de factura. */
	private boolean disableVerDatos;
	/** Indica si se habilita o no el boton modificar encabezado. */
	private boolean disableModificar;
	/** Indica si se habilita o no el boton cargar archivo. */
	private boolean disableCargarArchivo;
	/** Indica si se muestra o no la tabla de inconsistencias. */
	private boolean showInconsistencias;
	/** Indica si se muestra o no lo referente a las inconsistencias. */
	private boolean showDetalleFactura = false;
	/** Indica si se muestra o no el paginador de la tabla de polizas. */
	private boolean showPaginatorPolizas;
	/** Indica si se muestra o no la tabla de polizas. */
	private boolean showTablaPolizas;
	/** Indica si se habilita el campo nuevo número de factura. */
	private boolean roNuevoNumFactura;
	/** Indica cuando si se muestra la tabla de encabezados de facturas */
	private boolean showTablaFacturas;
	/** Indica si se habilita el boton de generar inconsistencias */
	private boolean disableDetalleInconsitencias;
	/** Indica si se habilita el boton de Guardar en las tablas de GL */
	private boolean disableGuardarAP;

	private boolean disableVerificar;

	/** Indica si se corrige una inconsistencia */
	// private boolean CorregirInconsitencia;
	/** Indica si se muestra o no la tabla de datos Correctos. */
	private boolean showDatosCorrectos;

	/* Control de mensajes de confirmación. */
	private boolean activarConfirmacion;
	private Integer opcConfirmacion;
	private static Integer MODIFICAR = 1;
	private static Integer CREAR_CABECERA = 4;
	private static Integer MODIFICAR_CABECERA_SOAT = -1;
	private static Integer GENERAR_INCOSITENCIAS = 5;
	private static Integer GUARDAR_FACTURA_AP = 6;

	/** Porcentaje de progreso de carga de archivo plano. */
	// private int porcentaje;
	/** Archivo cargado con la relacion polizas-vehiculos. */
	private InputFile file;
	/** Ubicación real del archivo. */
	private String fileLocation;

	/** Listado de polizas segun el tipo que se ingrese. */
	private List<Policies> listaPolizas;
	private List<VOPolicies> listaPolizasSoat = new ArrayList<VOPolicies>();
	private List<VOPolicies> listaPolizasSoatIncluidas = new ArrayList<VOPolicies>();
	private List<VOPolicieInvoice> listVOPolicieInvoice;

	/**
	 * Almacena temporalmente las inconsistencias que se van encontrando en el
	 * archivo plano.
	 */
	private List<Inconsistencies> listaInconsistencias;

	/**
	 * Listado de las asociaciones cargadas para guardar en la BD.
	 */
	private List<PoliciesVehicles> listaPoliVehicles;

	/**
	 * Poliza que queda activa cuando se crea o consulta una factura<br>
	 * para poder cargar un archivo
	 */
	private PoliciesInvoice invoicePolicy;

	/**
	 * Constructor basico.
	 */
	public PolicyInvoicePage() {
		try {

			estadoCarga = PersistentFacesState.getInstance();
			// porcentaje = -1;
			fechaFactura = new Date();
			listaInconsistencias = new ArrayList<Inconsistencies>();
			listaPoliVehicles = new ArrayList<PoliciesVehicles>();
			disableModificar = true;
			disableCargarArchivo = true;
			disableVerDatos = true;
			// disableDetalleInconsitencias = true;
			disableVerificar = true;
			roNuevoNumFactura = true;

		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void action_cargarFactura(ActionEvent event) {
		try {
			if (tblFacturas != null && tblFacturas.getRowData() != null) {

				VOPolicieInvoice policieInvoiceVO = (VOPolicieInvoice) tblFacturas
						.getRowData();
				invoicePolicy = policiesInvoiceService
						.consultarFacturaPoliza(policieInvoiceVO
								.getPinNumeroFactura());
			}

			showNumPoliza = true;

			// Pintar lo datos para facturas tipo soat
			if (invoicePolicy.getPolicies() == null) {
				pintarDatosFacturaSoat();
				// Pintar los datos para las facturas Full cobertura y Amparos
				// basicos
			} else if (invoicePolicy.getPolicies() != null
					&& invoicePolicy.getPolicies().getPoliciesTypes()
							.getPltCodigo().longValue() != ParametersUtil.SOAT) {
				pintarDatosFactura();
			}

			showTablaFacturas = false;
			listVOPolicieInvoice.clear();
			// tblPvs.setRendered(true);
			// roNuevoNumFactura = false;

		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	void pintarDatosFacturaSoat() throws GWorkException {
		showNumPoliza = false;
		txtFechaFactura.setValue(invoicePolicy.getPinFechaFactura());

		txtConcepto.setValue("" + invoicePolicy.getPinConcepto());
		disableCrear = false;
		selectSeguro.setValue(ParametersUtil.SOAT);
		txtNumeroFactura.setValue(invoicePolicy.getPinNumeroFactura());
		txtNumeroFactura.setReadonly(true);
		disableCargarArchivo = false;
		disableCrear = true;
		disableModificar = false;

		if (invoicePolicy.getInconsistencieses() != null
				&& !invoicePolicy.getInconsistencieses().isEmpty()) {

			listaInconsistencias = new SearchPolicies()
					.consultarInconsistenciasFacturas(invoicePolicy.getPinId());
		}

		// Muestra las polizas que existen en el sistema sin incluir las de la
		// factura
		List<VOPolicies> listaPoliciesVOSinIncluir = new ArrayList<VOPolicies>();

		listaPoliciesVOSinIncluir = policiesService
				.consultarTodasPolizasSOAT(ParametersUtil.SOAT);

		setListaPolizasSoat(listaPoliciesVOSinIncluir);

		// Ingresa las polizas que tiene asociada la factura
		List<VOPolicies> listaPoliciesVOIncluidas = new ArrayList<VOPolicies>();
		for (PoliciesVehicles policiesVehicles : invoicePolicy
				.getPoliciesVehicleses()) {

			VOPolicies policiesVO = new VOPolicies();
			// Ingresar los valores de cada una de las polizas de vehiculos,
			// al
			// nuevo objeto de VOPolicies

			policiesVO.setVehicles(policiesVehicles.getVehicles());
			policiesVO.setPolicies(policiesVehicles.getPolicies());
			policiesVO.setPvsFechaini(policiesVehicles.getPvsFechaini());
			policiesVO.setPvsFechafin(policiesVehicles.getPvsFechafin());
			policiesVO.setPvsCarnetAsignatario(policiesVehicles
					.getPvsCarnetAsignatario());
			policiesVO.setPvsValorAsegurado(policiesVehicles
					.getPvsValorAsegurado());
			policiesVO.setPvsValorIva(policiesVehicles.getPvsValorIva());
			policiesVO.setPvsValorPrima(policiesVehicles.getPvsValorPrima());
			policiesVO.setPvsValorTotal(policiesVehicles.getPvsValorTotal());

			listaPoliciesVOIncluidas.add(policiesVO);

		}
		setListaPolizasSoatIncluidas(listaPoliciesVOIncluidas);

		disableDetalleInconsitencias = false;
		// showInconsistencias = true;
		showSoat = true;
		disableGuardarAP = true;
		disableVerificar = false;
		roNuevoNumFactura = false;

	}

	public void verFacturasSistema(ActionEvent event) {
		try {
			String nombrePolizaSOAT = "";
			List<PoliciesInvoice> lstFacturas = policiesInvoiceService
					.consultarFacturas();
			List<VOPolicieInvoice> listVOPolicieInvoice = new ArrayList<VOPolicieInvoice>();

			for (PoliciesInvoice policiesInvoice : lstFacturas) {

				VOPolicieInvoice invoiceVO = new VOPolicieInvoice();

				invoiceVO.setPinCargado(policiesInvoice.getPinCargado());
				invoiceVO.setPinConcepto(policiesInvoice.getPinConcepto());
				invoiceVO.setPinFechaFactura(policiesInvoice
						.getPinFechaFactura());
				invoiceVO.setPinId(policiesInvoice.getPinId());
				invoiceVO.setPinNumeroFactura(policiesInvoice
						.getPinNumeroFactura());

				policiesInvoice = EntityManagerHelper.getEntityManager().merge(
						policiesInvoice);

				// Para polizas que son Full cobertura y Amparos basicos
				if (policiesInvoice.getPolicies() != null) {
					invoiceVO.setPolicies(policiesInvoice.getPolicies());
					invoiceVO.setNombrePoliza(policiesInvoice.getPolicies()
							.getPoliciesTypes().getPltNombre());
				}
				// Para polizas de tipo SOAT
				else if (policiesInvoice.getPoliciesVehicleses() != null
						&& policiesInvoice.getPoliciesVehicleses().size() > 0
						&& !policiesInvoice.getPoliciesVehicleses().isEmpty()) {

					nombrePolizaSOAT = new ArrayList<PoliciesVehicles>(
							policiesInvoice.getPoliciesVehicleses()).get(0)
							.getPolicies().getPoliciesTypes().getPltNombre();
					invoiceVO.setNombrePoliza(nombrePolizaSOAT);
				}
				
				if (policiesInvoice.getInconsistencieses() != null
						&& policiesInvoice.getInconsistencieses().size() > 0
						&& !policiesInvoice.getInconsistencieses().isEmpty()) {
					invoiceVO.setInconsistencia("Si");
				} else {
					invoiceVO.setInconsistencia("No");
				}				
				
				listVOPolicieInvoice.add(invoiceVO);

			}

			setListVOPolicieInvoice(listVOPolicieInvoice);

			if (listVOPolicieInvoice != null && !listVOPolicieInvoice.isEmpty()) {
				showTablaFacturas = true;
			} else {
				showTablaFacturas = false;
				throw new GWorkException(Util
						.loadErrorMessageValue("search.not.found"));
			}

		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	/**
	 * Crea el encabezado de la factura.
	 * 
	 * @param actionEvent
	 */
	public void action_crearFacturaPoliza(ActionEvent actionEvent) {

		try {

			if (idTipoPoliza.longValue() == ParametersUtil.SOAT) {

				validarDatosSOAT();
				crearFacturaSoat();

			} else if (idTipoPoliza.longValue() != ParametersUtil.SOAT) {
				validarDatos();
				setOpcConfirmacion(CREAR_CABECERA);
				activarConfirmacion = true;

				mostrarMensaje(Util.loadMessageValue("MENSAJE.ALERTA"),
						activarConfirmacion);
			}
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	/**
	 * Crea un factura un encabezado de factura para full cobertura y amparos
	 * basicos
	 */
	public void crearCabeceraFacturaPoliza() {
		try {
			policiesInvoiceService.crearFacturaPoliza(numeroPoliza, ""
					+ numeroFactura, fechaFactura, conceptoFact, getLogin());

			mostrarMensaje(Util.loadMessageValue("EXITO"), false);
			invoicePolicy = policiesInvoiceService
					.consultarFacturaPoliza(numeroFactura);
			limpiar();
			disableCargarArchivo = false;
			disableCrear = true;
			disableVerificar = false;

		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	/**
	 * Crear cabecera de factura para las polizas de tipo SOAT
	 * 
	 */

	void crearFacturaSoat() {
		try {
			if (listaPolizasSoatIncluidas == null
					|| listaPolizasSoatIncluidas.size() == 0
					|| listaPolizasSoatIncluidas.isEmpty())
				throw new GWorkException(Util
						.loadErrorMessageValue("POLIZAS.SOAT.VACIO"));

			policiesInvoiceService.crearFacturaPolizaSOAT(
					listaPolizasSoatIncluidas, numeroFactura, fechaFactura,
					conceptoFact, getLogin());

			mostrarMensaje(Util.loadMessageValue("EXITO"), false);
			limpiar();
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	/**
	 * Modifica el encabezado de una factura. Despliega el mensaje de
	 * confirmación.
	 * 
	 * @param actionEvent
	 */
	public void action_modificarFacturaPoliza(ActionEvent actionEvent) {
		// logica de confirmación
		try {
			activarConfirmacion = true;
			disableVerificar = true;

			if (idTipoPoliza.longValue() == ParametersUtil.SOAT) {
				validarDatosSOAT();
				setOpcConfirmacion(MODIFICAR_CABECERA_SOAT);

			} else {
				validarDatos();
				setOpcConfirmacion(MODIFICAR);
			}

			mostrarMensaje(Util.loadMessageValue("MENSAJE.ALERTA"),
					activarConfirmacion);

		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	/**
	 * Modifica el encabezado de una factura. Accede a la logica.
	 */
	public void action_modificarFacturaPoliza() {
		// logica de modificar
		try {
			validarDatos();
			if (numeroNuevaFactura != null
					&& numeroNuevaFactura.trim().length() != 0)
				Util.validarLimite("" + numeroNuevaFactura, 38, 2,
						"ERROR.LIMITENUMFACPOL");

			policiesInvoiceService.modificarFacturaPoliza(numeroPoliza,
					numeroFactura, numeroNuevaFactura, fechaFactura,
					conceptoFact, getLogin());
			mostrarMensaje(Util.loadMessageValue("EXITO.MODIFICAR"), false);
			limpiar();
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	/**
	 * Modifica el encabezado de la factura para las polizas tipo SOAT.
	 */
	public void action_modificarFacturaPolizaSOAT() {
		// logica de modificar
		try {

			if (numeroNuevaFactura != null
					&& numeroNuevaFactura.trim().length() != 0)
				Util.validarLimite("" + numeroNuevaFactura, 38, 2,
						"ERROR.LIMITENUMFACPOL");

			policiesInvoiceService.modificarFacturaPolizaSOAT(
					listaPolizasSoatIncluidas, numeroFactura,
					numeroNuevaFactura, fechaFactura, conceptoFact, getLogin());
			mostrarMensaje(Util.loadMessageValue("EXITO.MODIFICAR"), false);
			limpiar();
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	/**
	 * genera las inconsistencias de la factura. Despliega el mensaje de
	 * confirmación.
	 * 
	 * @param actionEvent
	 */
	public void action_GenerarInconsitencias(ActionEvent actionEvent) {
		try {
			activarConfirmacion = true;
			disableVerificar = true;
			validarDatos();

			setOpcConfirmacion(GENERAR_INCOSITENCIAS);
			mostrarMensaje(Util.loadMessageValue("MENSAJE.ALERTA"),
					activarConfirmacion);

		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	/**
	 * Inicia el proceso para guardar las inconsistencias y las nuevas
	 * asociaciones de vehiculos a la Poliza en cuestion.
	 */
	public void guardarInconsitenciasFactura() {
		try {
			policiesInvoiceService.generarInconsitenciasFactura(
					listaPoliVehicles, getLogin(), invoicePolicy);
			listaInconsistencias = new SearchPolicies()
					.consultarInconsistenciasFacturas(invoicePolicy.getPinId());
			mostrarMensaje(Util.loadMessageValue("EXITO"), false);
			disableDetalleInconsitencias = true;
			if (invoicePolicy.getPinCargado().longValue() != ParametersUtil.FacturaEnviadaAP
					.longValue())
				disableGuardarAP = false;

			// limpiar();
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	/**
	 * genera las inconsistencias de la factura. Despliega el mensaje de
	 * confirmación.
	 * 
	 * @param actionEvent
	 */
	public void action_GuardarFacturaAp(ActionEvent actionEvent) {
		try {
			activarConfirmacion = true;
			disableVerificar = true;
			validarDatos();

			setOpcConfirmacion(GUARDAR_FACTURA_AP);
			mostrarMensaje(Util.loadMessageValue("MENSAJE.ALERTA"),
					activarConfirmacion);

		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	/**
	 * Inicia el proceso para guardar las vehiculos que viene en la factura en
	 * las tablas de AP, invoce_header, invoice_detail.
	 */
	public void GuardarFacturaAp() {
		try {
			listaInconsistencias = new SearchPolicies()
					.consultarInconsistenciasFacturasTipoInco(invoicePolicy
							.getPinId());
			
//			PoliciesInvoiceService policiesInvoiceServiceOld = policiesInvoiceService;
			policiesInvoiceService = new PoliciesInvoiceServiceImpl(listaPoliVehicles,
					listaInconsistencias, invoicePolicy, getLogin(),
					valorTotalFactura);
			
//			policiesInvoiceService.GuardarFacturaAp(listaPoliVehicles,
//					listaInconsistencias, invoicePolicy, getLogin(),
//					valorTotalFactura);
			Thread hilo = new Thread(policiesInvoiceService);
			hilo.start();
			
			mostrarMensaje(Util.loadMessageValue("EXITO"), false);
			limpiar();
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	/**
	 * Inicia el proceso de verificación de las posibles inconsistencias que ya
	 * han sido corregidas.
	 * 
	 * @param event
	 */
	public void action_verificar(ActionEvent event) {
		try {
			// Para poder iniciar el proceso se debe haber consultado
			// una factura
			if (invoicePolicy == null) {
				throw new GWorkException(Util
						.loadErrorMessageValue("ERROR.CONSINVOICE"));
			}
			policiesInvoiceService
					.verificarInconsistenciasFacturas(invoicePolicy.getPinId());

			mostrarMensaje(Util.loadMessageValue("CORREGIDAS") + ""
					+ policiesInvoiceService.getSoluciones(), false);
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	/**
	 * Asigna la observación ingresada en la fila actual de la tabla a la
	 * inconsistencia de la misma fila.
	 * 
	 * @param event
	 */
	public void listenerObservaciones(ActionEvent event) throws GWorkException {
		try {
			if (tblInconsistencias.getRowData() != null) {
				Inconsistencies inconsistencies = (Inconsistencies) tblInconsistencias
						.getRowData();
				policiesInvoiceService.CorregirInconsistencias(inconsistencies,
						txtObserv.getValue().toString());

				for (Inconsistencies inconsistencias : listaInconsistencias) {
					if (inconsistencias.getIncId() == inconsistencies
							.getIncId()) {
						listaInconsistencias.remove(inconsistencies);
						break;
					}
				}
			}
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	/**
	 * Consulta el encabezado de una Factura.
	 * 
	 * @param actionEvent
	 */
	public void action_consultarFacturaPoliza(ActionEvent actionEvent) {
		// lógica para consultar una Factura de Póliza
		try {
			if (!Util
					.validarCadenaCaracteresEspecialesNumLetrasGuion(numeroFactura))
				throw new GWorkException(Util
						.loadErrorMessageValue("CARACTER.ESPECIAL"));

			Util.validarLimite(numeroFactura, 38, 2, "ERROR.LIMNUMFACTPOLI");

			invoicePolicy = policiesInvoiceService
					.consultarFacturaPoliza(numeroFactura);

			if (invoicePolicy == null) {
				throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));
			}

			showNumPoliza = true;

			// Pintar lo datos para facturas tipo soat
			if (invoicePolicy.getPolicies() == null) {
				pintarDatosFacturaSoat();
				// Pintar los datos para las facturas Full cobertura y Amparos
				// basicos
			} else if (invoicePolicy.getPolicies() != null
					&& invoicePolicy.getPolicies().getPoliciesTypes()
							.getPltCodigo().longValue() != ParametersUtil.SOAT) {
				pintarDatosFactura();
			}

		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	/**
	 * Es llamamdo al consultar por numero de factura y al seleccionar una
	 * factura de la tabla de facturas.
	 * 
	 * @throws GWorkException
	 *             Manejador de excepciones.
	 */
	public void pintarDatosFactura() throws GWorkException {

		txtFechaFactura.setValue(invoicePolicy.getPinFechaFactura());
		txtNumeroPoliza.setValue(""
				+ invoicePolicy.getPolicies().getPlsNumero().longValue());
		txtConcepto.setValue("" + invoicePolicy.getPinConcepto());
		disableCrear = false;
		selectSeguro.setValue(invoicePolicy.getPolicies().getPoliciesTypes()
				.getPltCodigo());
		txtNumeroFactura.setValue(invoicePolicy.getPinNumeroFactura());
		txtNumeroFactura.setReadonly(true);
		disableCargarArchivo = false;
		disableCrear = true;
		disableModificar = false;

		if (invoicePolicy.getInconsistencieses() != null
				&& !invoicePolicy.getInconsistencieses().isEmpty()) {

			listaInconsistencias = new SearchPolicies()
					.consultarInconsistenciasFacturas(invoicePolicy.getPinId());
		}

		Policies policies = invoicePolicy.getPolicies();

		if (policies.getPoliciesVehicleses() != null
				&& !policies.getPoliciesVehicleses().isEmpty()) {
			listaPoliVehicles = new SearchPoliciesVehicles()
					.consultarPoliciesVehiclesPoliza(policies.getPlsCodigo()
							.longValue());
		}

		disableDetalleInconsitencias = false;
		showInconsistencias = true;
		disableGuardarAP = true;
		disableVerificar = false;
		roNuevoNumFactura = false;
	}

	/**
	 * Activa la opcion de carga del archivo plano
	 * 
	 * @param event
	 */
	public void action_anhadirArchivo(ActionEvent event) {
		showDetalleFactura = true;
	}

	/**
	 * Carga el número de poliza para crear un nuevo encabezado de factura.
	 * 
	 * @param event
	 */
	public void action_cargarPoliza(ActionEvent event) {
		if (tblPolizas.getRowData() != null) {
			Policies policies = (Policies) tblPolizas.getRowData();
			
			setNumeroPoliza(policies.getPlsNumero());
			showTablaPolizas = false;
		}
	}

	/**
	 * Lista las polizas dependiendo del tipo de poliza seleccionada.
	 * 
	 * @param event
	 */
	public void listener_TipoPoliza(ValueChangeEvent event) {
		try {
			showDetalleFactura = false;
			showTablaPolizas = false;
			showSoat = false;
			showNumPoliza = true;

			if (event.getNewValue() == null) {
				return;
			}
			idTipoPoliza = new Long("" + event.getNewValue());
			if (idTipoPoliza != null && idTipoPoliza != -1L) {

				// Muestra en la pantalla el listado de polizas de tipo SOAT
				if (idTipoPoliza.longValue() == ParametersUtil.SOAT) {
					showSoat = true;
					listaPolizasSoat = policiesService
							.consultarTodasPolizasSOAT(idTipoPoliza);
					showNumPoliza = false;
					txtNumeroPoliza.setVisible(false);
				}
				// Muestra en pantalla la lista de polizas que no sean tipo
				// SOAT, como Full Cobertura y Amparos Basicos
				else if (idTipoPoliza.longValue() != ParametersUtil.SOAT) {
					listaPolizas = policiesService
							.consultarTodasPolizas(idTipoPoliza);
					if (listaPolizas != null && listaPolizas.size() > 0) {
						nombreTipoPoliza = ""
								+ listaPolizas.get(0).getPoliciesTypes()
										.getPltNombre();
						showTablaPolizas = true;
						showPaginatorPolizas = true;
					}
				}
			} else {

				setListaPolizasSoat(null);
				setListaPolizasSoatIncluidas(null);
				showTablaPolizas = false;
				if (listaPolizas != null)
					listaPolizas.clear();
			}
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	/**
	 * Limpia el formulario de facturas de polizas.
	 * 
	 * @param actionEvent
	 */
	public void action_limpiarForma(ActionEvent actionEvent) {
		limpiar();
	}

	/**
	 * Coloca las variables en sus valores iniciales.
	 */
	private void limpiar() {
		// porcentaje = -1;
		showTablaPolizas = false;
		showDetalleFactura = false;
		showSoat = false;
		conceptoFact = null;
		fechaFactura = null;
		file = null;
		fileLocation = null;
		numeroFactura = null;
		numeroNuevaFactura = null;
		numeroPoliza = null;
		txtFechaFactura.setValue(new Date());
		txtNumeroPoliza.setValue(null);
		txtNumeroFactura.setValue(null);
		txtNuevoNumeroFactura.setValue(null);
		txtConcepto.setValue(null);
		disableCrear = false;
		selectSeguro.setValue(-1);
		txtNumeroFactura.setReadonly(false);
		disableModificar = true;
		disableCargarArchivo = true;
		listaInconsistencias = new ArrayList<Inconsistencies>();
		listaPolizas = new ArrayList<Policies>();
		listaPoliVehicles = new ArrayList<PoliciesVehicles>();
		listaPolizasSoatIncluidas = new ArrayList<VOPolicies>();
		showInconsistencias = false;
		showNumPoliza = true;
		listaPolizasSoat = new ArrayList<VOPolicies>();

		if (txtObserv != null) {
			txtObserv.setReadonly(false);
		}

		disableVerDatos = true;
		disableDetalleInconsitencias = true;
		disableGuardarAP = true;
		disableVerificar = true;
		roNuevoNumFactura = true;
		showTablaFacturas = false;
	}

	/**
	 * Valida datos basicos.
	 * 
	 * @throws GWorkException
	 */
	private void validarDatos() throws GWorkException {

		if (fechaFactura == null)
			throw new GWorkException(Util.loadErrorMessageValue("date.empty"));

		if (numeroPoliza == null)
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.NUMPOLIZA"));

		if (numeroFactura == null || numeroFactura.length() <= 0)
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.NUMFACTURA"));

		if (!Util.validarCadenaCaracteresEspecialesNumLetrasGuion(""
				+ numeroPoliza))
			throw new GWorkException(Util
					.loadErrorMessageValue("CARACNUMPOLIZA"));

		if (!Util.validarCadenaCaracteresEspecialesNumLetrasGuion(""
				+ numeroFactura))
			throw new GWorkException(Util
					.loadErrorMessageValue("CARACTER.ESPECIAL")
					+ " en el campo 'No de la factura'");

		if (fechaFactura.compareTo(new Date()) >= 0) {
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.FECHAFACTURAFECHAACTUAL"));
		}

		Util.validarLimite("" + numeroPoliza, 38, 2, "ERROR.LIMITEFACPOLIZA");
		Util.validarLimite("" + numeroFactura, 38, 2, "ERROR.LIMNUMFACTPOLI");
		Util.validarLimite(conceptoFact, 100, 2, "ERROR.LIMCONCEPFAC");

		ManipulacionFechas.validarAnhoFecha(fechaFactura, "");

	}

	/**
	 * Despliega un mensaje infomativo.
	 * 
	 * @param mensaje
	 *            mensaje que se va a mostrar
	 * @param buttonCancel
	 *            Indica si se va a mostrar el boton cancelar.
	 */
	public void mostrarMensaje(String mensaje, boolean buttonCancel) {
		PopupMessagePage message = (PopupMessagePage) FacesUtils
				.getManagedBean("popupMessagePage");
		if (message != null)
			message.showPopup(mensaje, buttonCancel);
	}

	/**
	 * Muestra el progreso de carga del archivo.
	 */
	public void progresoCargaArchivo(EventObject event) {
		file = (InputFile) event.getSource();
		// porcentaje = file.getFileInfo().getPercent();
		try {
			if (estadoCarga != null) {
				estadoCarga.render();
			}
		} catch (RenderingException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	/**
	 * Inicia el proceso de lectura del archivo validando los tipos de datos y
	 * determinando inconsistencias y asociaciones correctas.
	 * 
	 * @param event
	 */
	public void action_verDatos(ActionEvent event) {
		try {
			if (file != null) {
				obtenerDatosFacturaPoliza(leerArchivo(file.getFile()));
				FileUtils.deleteFile(file.getFile());
				String Mensaje = "Se esta realizando el proceso de validacion "
						+ "del archivo plano de polizas cargado. "
						+ "El sistema le enviara una notificacion al correo cuando termine.";
				mostrarMensaje(Mensaje, false);
			} else {
				throw new GWorkException(Util
						.loadErrorMessageValue("ERROR.SELARCHIVOFAC"));
			}
		} catch (GWorkException e) {
			e.printStackTrace();
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	/**
	 * Se ejecuta cuando la carga del archivo cambia de estado.
	 * 
	 * @param event
	 */
	public void action_archivoEstado(ActionEvent event) {
		try {
			file = (InputFile) event.getSource();

			if (file.getStatus() == InputFile.SAVED) {
				fileLocation = file.getFileInfo().getPhysicalPath();

				disableVerDatos = false;
				mostrarMensaje(Util.loadMessageValue("EXITO.LOADFILE"), false);

				if (invoicePolicy != null) {
					if (invoicePolicy.getPinCargado().longValue() == 1L) {
						disableDetalleInconsitencias = false;
						disableGuardarAP = true;
					} else {
						disableDetalleInconsitencias = true;
						disableGuardarAP = true;
					}
				}
			}

			if (file.getStatus() == InputFile.INVALID) {
				throw new GWorkException(file.getFileInfo().getException()
						.getMessage());
			}
			if (file.getStatus() == InputFile.SIZE_LIMIT_EXCEEDED) {
				throw new GWorkException(file.getFileInfo().getException()
						.getMessage());
			}
			if (file.getStatus() == InputFile.UNKNOWN_SIZE) {
				throw new GWorkException(file.getFileInfo().getException()
						.getMessage());
			}
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	/**
	 * Carga en una cadena los datos del archivo plano.
	 * 
	 * @param file
	 *            Archivo cargado.
	 * @return
	 * @throws GWorkException
	 *             Manejador de excepciones.
	 */
	@SuppressWarnings("deprecation")
	public StringBuffer leerArchivo(File file) throws GWorkException {

		StringBuffer valores = new StringBuffer();
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		DataInputStream dis = null;

		try {
			fis = new FileInputStream(file);

			// Here BufferedInputStream is added for fast reading.
			bis = new BufferedInputStream(fis);
			dis = new DataInputStream(bis);

			// dis.available() returns 0 if the file does not have more lines.
			while (dis.available() != 0) {
				// this statement reads the line from the file and print it to
				// the console.
				valores = valores.append(dis.readLine());
				valores.append(Util.loadMessageValue("CARACTER_FIN_LINEA")
						.charAt(0));
			}

			// dispose all the resources after using them.
			fis.close();
			bis.close();
			dis.close();

		} catch (FileNotFoundException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		} catch (IOException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
		return valores;
	}

	/**
	 * 
	 * @param datosArchivo
	 * @throws GWorkException
	 */
	public void obtenerDatosFacturaPoliza(StringBuffer datosArchivo)
			throws GWorkException {
		try {
			Thread hiloArchivoPlano = new Thread(new tareaFacturaPoliza(
					datosArchivo, numeroPoliza, invoicePolicy, getLogin()));
			hiloArchivoPlano.setName("Hilo Seguros Archivo Plano - "
					+ invoicePolicy.getPinNumeroFactura());
			hiloArchivoPlano.start();
			// comienza la tarea para realizar la revision del archivo
			System.out.println(hiloArchivoPlano.isInterrupted());

			showInconsistencias = false;
			// showDatosCorrectos = false;
		} catch (Exception e) {
			throw new GWorkException(e.getMessage());
		}
	}

	/** ******************************************************************** */
	/** ************************ METODOS ACCESORES ************************* */
	/** ******************************************************************** */

	public PoliciesService getPoliciesService() {
		return policiesService;
	}

	public void setPoliciesService(PoliciesService policiesService) {
		this.policiesService = policiesService;
	}

	public Long getIdTipoPoliza() {
		return idTipoPoliza;
	}

	public void setIdTipoPoliza(Long idTipoPoliza) {
		this.idTipoPoliza = idTipoPoliza;
	}

	public HtmlSelectOneMenu getSelectSeguro() {
		return selectSeguro;
	}

	public void setSelectSeguro(HtmlSelectOneMenu selectSeguro) {
		this.selectSeguro = selectSeguro;
	}

	public Float getValorPrima() {
		return valorPrima;
	}

	public void setValorPrima(Float valorPrima) {
		this.valorPrima = valorPrima;
	}

	public Date getFechaFactura() {
		return fechaFactura;
	}

	public void setFechaFactura(Date fechaFactura) {
		this.fechaFactura = fechaFactura;
	}

	public String getConceptoFact() {
		return conceptoFact;
	}

	public void setConceptoFact(String conceptoFact) {
		this.conceptoFact = conceptoFact;
	}

	public boolean isDisableCrear() {
		return disableCrear;
	}

	public void setDisableCrear(boolean disableCrear) {
		this.disableCrear = disableCrear;
	}

	public HtmlInputText getTxtNumeroPoliza() {
		return txtNumeroPoliza;
	}

	public void setTxtNumeroPoliza(HtmlInputText txtNumeroPoliza) {
		this.txtNumeroPoliza = txtNumeroPoliza;
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

	public HtmlInputText getTxtFechaFactura() {
		return txtFechaFactura;
	}

	public void setTxtFechaFactura(HtmlInputText txtFechaFactura) {
		this.txtFechaFactura = txtFechaFactura;
	}

	public HtmlInputText getTxtConcepto() {
		return txtConcepto;
	}

	public void setTxtConcepto(HtmlInputText txtConcepto) {
		this.txtConcepto = txtConcepto;
	}

	// public int getPorcentaje() {
	// return porcentaje;
	// }
	//
	// public void setPorcentaje(int porcentaje) {
	// this.porcentaje = porcentaje;
	// }

	public PersistentFacesState getState() {
		return estadoCarga;
	}

	public void setState(PersistentFacesState state) {
		this.estadoCarga = state;
	}

	public InputFile getFile() {
		return file;
	}

	public void setFile(InputFile file) {
		this.file = file;
	}

	public String getFileLocation() {
		return fileLocation;
	}

	public void setFileLocation(String fileLocation) {
		this.fileLocation = fileLocation;
	}

	public HtmlInputText getTxtNumeroFactura() {
		return txtNumeroFactura;
	}

	public void setTxtNumeroFactura(HtmlInputText txtNumeroFactura) {
		this.txtNumeroFactura = txtNumeroFactura;
	}

	public Long getNumeroPoliza() {
		return numeroPoliza;
	}

	public void setNumeroPoliza(Long numeroPoliza) {
		this.numeroPoliza = numeroPoliza;
	}

	public String getNumeroFactura() {
		return numeroFactura;
	}

	public void setNumeroFactura(String numeroFactura) {
		this.numeroFactura = numeroFactura;
	}

	public Long getValorAsegurado() {
		return valorAsegurado;
	}

	public void setValorAsegurado(Long valorAsegurado) {
		this.valorAsegurado = valorAsegurado;
	}

	public Long getValorComercialCIAT() {
		return valorComercialCIAT;
	}

	public void setValorComercialCIAT(Long valorComercialCIAT) {
		this.valorComercialCIAT = valorComercialCIAT;
	}

	public Float getValorIVA() {
		return valorIVA;
	}

	public void setValorIVA(Float valorIVA) {
		this.valorIVA = valorIVA;
	}

	public Float getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Float valorTotal) {
		this.valorTotal = valorTotal;
	}

	public HtmlDataTable getTblInconsistencias() {
		return tblInconsistencias;
	}

	public void setTblInconsistencias(HtmlDataTable tblInconsistencias) {
		this.tblInconsistencias = tblInconsistencias;
	}

	public boolean isShowDetalleFactura() {
		return showDetalleFactura;
	}

	public void setShowDetalleFactura(boolean showDetalleFactura) {
		this.showDetalleFactura = showDetalleFactura;
	}

	public PoliciesTypeService getPoliciesTypeService() {
		return policiesTypeService;
	}

	public void setPoliciesTypeService(PoliciesTypeService policiesTypeService) {
		this.policiesTypeService = policiesTypeService;
	}

	public List<Inconsistencies> getListaInconsistencias() {
		return listaInconsistencias;
	}

	public void setListaInconsistencias(
			List<Inconsistencies> listaInconsistencias) {
		this.listaInconsistencias = listaInconsistencias;
	}

	public boolean isShowInconsistencias() {
		return showInconsistencias;
	}

	public void setShowInconsistencias(boolean showInconsistencias) {
		this.showInconsistencias = showInconsistencias;
	}

	public InconsistenciesService getInconsistenciesService() {
		return inconsistenciesService;
	}

	public void setInconsistenciesService(
			InconsistenciesService inconsistenciesService) {
		this.inconsistenciesService = inconsistenciesService;
	}

	public String getNombreTipoPoliza() {
		return nombreTipoPoliza;
	}

	public void setNombreTipoPoliza(String nombreTipoPoliza) {
		this.nombreTipoPoliza = nombreTipoPoliza;
	}

	public HtmlDataTable getTblPolizas() {
		return tblPolizas;
	}

	public void setTblPolizas(HtmlDataTable tblPolizas) {
		this.tblPolizas = tblPolizas;
	}

	public List<Policies> getListaPolizas() {
		return listaPolizas;
	}

	public void setListaPolizas(List<Policies> listaPolizas) {
		this.listaPolizas = listaPolizas;
	}

	public boolean isShowPaginatorPolizas() {
		return showPaginatorPolizas;
	}

	public void setShowPaginatorPolizas(boolean showPaginatorPolizas) {
		this.showPaginatorPolizas = showPaginatorPolizas;
	}

	public boolean isShowTablaPolizas() {
		return showTablaPolizas;
	}

	public void setShowTablaPolizas(boolean showTablaPolizas) {
		this.showTablaPolizas = showTablaPolizas;
	}

	public PoliciesInvoiceService getPoliciesInvoiceService() {
		return policiesInvoiceService;
	}

	public void setPoliciesInvoiceService(
			PoliciesInvoiceService policiesInvoiceService) {
		this.policiesInvoiceService = policiesInvoiceService;
	}

	public String getLogin() {
		LoginPage loginPage = (LoginPage) FacesUtils
				.getManagedBean("loginPage");
		if (loginPage != null) {
			return loginPage.getLogin();
		} else
			return "";
	}

	public String getNumeroNuevaFactura() {
		return numeroNuevaFactura;
	}

	public void setNumeroNuevaFactura(String numeroNuevaFactura) {
		this.numeroNuevaFactura = numeroNuevaFactura;
	}

	public HtmlInputText getTxtNuevoNumeroFactura() {
		return txtNuevoNumeroFactura;
	}

	public void setTxtNuevoNumeroFactura(HtmlInputText txtNuevoNumeroFactura) {
		this.txtNuevoNumeroFactura = txtNuevoNumeroFactura;
	}

	public InconsistenciesTypesService getInconsistenciesTypesService() {
		return inconsistenciesTypesService;
	}

	public void setInconsistenciesTypesService(
			InconsistenciesTypesService inconsistenciesTypesService) {
		this.inconsistenciesTypesService = inconsistenciesTypesService;
	}

	public boolean isDisableModificar() {
		return disableModificar;
	}

	public void setDisableModificar(boolean disableModificar) {
		this.disableModificar = disableModificar;
	}

	public boolean isDisableCargarArchivo() {
		return disableCargarArchivo;
	}

	public void setDisableCargarArchivo(boolean disableCargarArchivo) {
		this.disableCargarArchivo = disableCargarArchivo;
	}

	public List<PoliciesVehicles> getListaPoliVehicles() {
		return listaPoliVehicles;
	}

	public void setListaPoliVehicles(List<PoliciesVehicles> listaPoliVehicles) {
		this.listaPoliVehicles = listaPoliVehicles;
	}

	public HtmlDataTable getTblPvs() {
		return tblPvs;
	}

	public void setTblPvs(HtmlDataTable tblPvs) {
		this.tblPvs = tblPvs;
	}

	public HtmlInputText getTxtObserv() {
		return txtObserv;
	}

	public void setTxtObserv(HtmlInputText txtObserv) {
		this.txtObserv = txtObserv;
	}

	public boolean isDisableVerDatos() {
		return disableVerDatos;
	}

	public void setDisableVerDatos(boolean disableVerDatos) {
		this.disableVerDatos = disableVerDatos;
	}

	/**
	 * @return the disableVerificar
	 */
	public boolean isDisableVerificar() {
		return disableVerificar;
	}

	/**
	 * @param disableVerificar
	 *            the disableVerificar to set
	 */
	public void setDisableVerificar(boolean disableVerificar) {
		this.disableVerificar = disableVerificar;
	}

	/**
	 * @return the roNuevoNumFactura
	 */
	public boolean isRoNuevoNumFactura() {
		return roNuevoNumFactura;
	}

	/**
	 * @param roNuevoNumFactura
	 *            the roNuevoNumFactura to set
	 */
	public void setRoNuevoNumFactura(boolean roNuevoNumFactura) {
		this.roNuevoNumFactura = roNuevoNumFactura;
	}

	/**
	 * @return the tblFacturas
	 */
	public HtmlDataTable getTblFacturas() {
		return tblFacturas;
	}

	/**
	 * @param tblFacturas
	 *            the tblFacturas to set
	 */
	public void setTblFacturas(HtmlDataTable tblFacturas) {
		this.tblFacturas = tblFacturas;
	}

	/**
	 * @return the showTablaFacturas
	 */
	public boolean isShowTablaFacturas() {
		return showTablaFacturas;
	}

	/**
	 * @param showTablaFacturas
	 *            the showTablaFacturas to set
	 */
	public void setShowTablaFacturas(boolean showTablaFacturas) {
		this.showTablaFacturas = showTablaFacturas;
	}

	public Long getValorTotalFactura() {
		return valorTotalFactura;
	}

	public void setValorTotalFactura(Long valorTotalFactura) {
		this.valorTotalFactura = valorTotalFactura;
	}

	/**
	 * @return the gENERAR_INCOSITENCIAS
	 */
	public static Integer getGENERAR_INCOSITENCIAS() {
		return GENERAR_INCOSITENCIAS;
	}

	/**
	 * @param generar_incositencias
	 *            the gENERAR_INCOSITENCIAS to set
	 */
	public static void setGENERAR_INCOSITENCIAS(Integer generar_incositencias) {
		GENERAR_INCOSITENCIAS = generar_incositencias;
	}

	/**
	 * @return the disableDetalleInconsitencias
	 */
	public boolean isDisableDetalleInconsitencias() {
		return disableDetalleInconsitencias;
	}

	/**
	 * @param disableDetalleInconsitencias
	 *            the disableDetalleInconsitencias to set
	 */
	public void setDisableDetalleInconsitencias(
			boolean disableDetalleInconsitencias) {
		this.disableDetalleInconsitencias = disableDetalleInconsitencias;
	}

	/**
	 * @return the disableGuardarAP
	 */
	public boolean isDisableGuardarAP() {
		return disableGuardarAP;
	}

	/**
	 * @param disableGuardarAP
	 *            the disableGuardarAP to set
	 */
	public void setDisableGuardarAP(boolean disableGuardarAP) {
		this.disableGuardarAP = disableGuardarAP;
	}

	/**
	 * @return the gUARDAR_FACTURA_AP
	 */
	public static Integer getGUARDAR_FACTURA_AP() {
		return GUARDAR_FACTURA_AP;
	}

	/**
	 * @param guardar_factura_ap
	 *            the gUARDAR_FACTURA_AP to set
	 */
	public static void setGUARDAR_FACTURA_AP(Integer guardar_factura_ap) {
		GUARDAR_FACTURA_AP = guardar_factura_ap;
	}

	/**
	 * @return the numeroFilas
	 */
	public Integer getNumeroFilas() {
		return numeroFilas;
	}

	/**
	 * @param numeroFilas
	 *            the numeroFilas to set
	 */
	public void setNumeroFilas(Integer numeroFilas) {
		this.numeroFilas = numeroFilas;
	}

	/**
	 * @return the showDatosCorrectos
	 */
	public boolean isShowDatosCorrectos() {
		return showDatosCorrectos;
	}

	/**
	 * @param showDatosCorrectos
	 *            the showDatosCorrectos to set
	 */
	public void setShowDatosCorrectos(boolean showDatosCorrectos) {
		this.showDatosCorrectos = showDatosCorrectos;
	}

	public boolean isShowSoat() {
		return showSoat;
	}

	public void setShowSoat(boolean showSoat) {
		this.showSoat = showSoat;
	}

	public List<VOPolicies> getListaPolizasSoat() {
		return listaPolizasSoat;
	}

	public void setListaPolizasSoat(List<VOPolicies> listaPolizasSoat) {
		this.listaPolizasSoat = listaPolizasSoat;
	}

	/**
	 * @return the corregirInconsitencia
	 */
	// public boolean isCorregirInconsitencia() {
	// return CorregirInconsitencia;
	// }
	/**
	 * @param corregirInconsitencia
	 *            the corregirInconsitencia to set
	 */
	// public void setCorregirInconsitencia(boolean corregirInconsitencia) {
	// CorregirInconsitencia = corregirInconsitencia;
	// }
	void validarDatosSOAT() throws GWorkException {

		if (fechaFactura == null)
			throw new GWorkException(Util.loadErrorMessageValue("date.empty"));

		if (numeroFactura == null || numeroFactura.length() <= 0)
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.NUMFACTURA"));

		if (!Util.validarCadenaCaracteresEspecialesNumLetrasGuion(""
				+ numeroFactura))
			throw new GWorkException(Util
					.loadErrorMessageValue("CARACTER.ESPECIAL")
					+ " en el campo 'No de la factura'");

		if (fechaFactura.compareTo(new Date()) >= 0) {
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.FECHAFACTURAFECHAACTUAL"));
		}

		Util.validarLimite("" + numeroFactura, 38, 2, "ERROR.LIMNUMFACTPOLI");
		Util.validarLimite(conceptoFact, 100, 2, "ERROR.LIMCONCEPFAC");

		ManipulacionFechas.validarAnhoFecha(fechaFactura, "");

	}

	public boolean isShowNumPoliza() {
		return showNumPoliza;
	}

	public void setShowNumPoliza(boolean showNumPoliza) {
		this.showNumPoliza = showNumPoliza;
	}

	public List<VOPolicieInvoice> getListVOPolicieInvoice() {
		return listVOPolicieInvoice;
	}

	public void setListVOPolicieInvoice(
			List<VOPolicieInvoice> listVOPolicieInvoice) {
		this.listVOPolicieInvoice = listVOPolicieInvoice;
	}

	public List<VOPolicies> getListaPolizasSoatIncluidas() {
		return listaPolizasSoatIncluidas;
	}

	public void setListaPolizasSoatIncluidas(
			List<VOPolicies> listaPolizasSoatIncluidas) {
		this.listaPolizasSoatIncluidas = listaPolizasSoatIncluidas;
	}

	/**
	 * Evento que permite incluir las polizas de SOAT para la factura
	 */
	public void action_agregarPolizaSoat(ActionEvent event) {

		if (tblPolizasSOAT != null && tblPolizasSOAT.getRowData() != null) {
			VOPolicies policiesVO = (VOPolicies) tblPolizasSOAT.getRowData();

			listaPolizasSoatIncluidas.add(policiesVO);
			listaPolizasSoat.remove(policiesVO);
		}

	}

	public void action_eliminarPolizaSoat(ActionEvent event) {

		if (tblPolizasSOATIncluidas != null
				&& tblPolizasSOATIncluidas.getRowData() != null) {
			VOPolicies policiesVO = (VOPolicies) tblPolizasSOATIncluidas
					.getRowData();

			listaPolizasSoatIncluidas.remove(policiesVO);
			listaPolizasSoat.add(policiesVO);
		}

	}

	public HtmlDataTable getTblPolizasSOAT() {
		return tblPolizasSOAT;
	}

	public void setTblPolizasSOAT(HtmlDataTable tblPolizasSOAT) {
		this.tblPolizasSOAT = tblPolizasSOAT;
	}

	public HtmlDataTable getTblPolizasSOATIncluidas() {
		return tblPolizasSOATIncluidas;
	}

	public void setTblPolizasSOATIncluidas(HtmlDataTable tblPolizasSOATIncluidas) {
		this.tblPolizasSOATIncluidas = tblPolizasSOATIncluidas;
	}
}
