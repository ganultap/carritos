package geniar.siscar.view.policies;

import geniar.siscar.consults.ConsultsService;
import geniar.siscar.logic.consultas.SearchPoliciesVehicles;
import geniar.siscar.logic.policies.services.PoliciesService;
import geniar.siscar.model.Policies;
import geniar.siscar.model.PoliciesInvoice;
import geniar.siscar.model.PoliciesVehicles;
import geniar.siscar.model.Users;
import geniar.siscar.model.Vehicles;
import geniar.siscar.util.FacesUtils;
import geniar.siscar.util.ManipulacionFechas;
import geniar.siscar.util.PopupMessagePage;
import geniar.siscar.util.Util;
import geniar.siscar.view.autenticate.LoginPage;
import gwork.exception.GWorkException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import com.icesoft.faces.component.ext.HtmlDataTable;
import com.icesoft.faces.component.ext.HtmlInputText;
import com.icesoft.faces.component.ext.HtmlOutputText;
import com.icesoft.faces.component.ext.HtmlSelectOneMenu;
import com.icesoft.faces.component.ext.RowSelectorEvent;

public class PoliciesPage {

	// Servicios ...
	private PoliciesService policiesService;

	// Variables de negocio...
	private Long idTipoPoliza;
	private Long plsNumero;
	private Long plsNuevoNumero;
	private Float plsValorPrima;
	private Float plsValorContribucion;
	private Float plsValorTotal;
	private Float plsValorAseg;
	private String plsDocumento1;
	private String plsDocumento2;
	private String nombreTipoPoliza;
	private String placaVehiculo;
	private String parametroBusquedad;
	private List<Policies> listaPolizas;
	private List<Policies> listaSoatsInactivos;
	private List<PoliciesInvoice> listaPolizaDetalle;
	private List<PoliciesVehicles> listaPoliciesVhc;
	private List<Users> listNitProveedor;
	private Date plcFechaInicio;
	private Date plcFechaTerminacion;
	private int numeroFilas;

	// Bindings y otros...
	private SelectItem[] listPolicyTypes;
	private HtmlInputText txtFechaIni;
	private HtmlInputText txtFechaFin;
	private HtmlInputText txtNumeroPoliza;
	private HtmlInputText txtNuevoNumeroPoliza;
	private HtmlInputText txtValorPrima;
	private HtmlInputText txtValorContrib;
	private HtmlInputText txtDoc1;
	private HtmlInputText txtDoc2;
	private HtmlInputText txtValorTotal;
	private HtmlInputText txtPlacaVehiculo;
	private HtmlInputText txtValorAseg;
	private HtmlSelectOneMenu selectSeguro;
	private HtmlDataTable tblPolizas;
	private HtmlDataTable tblVehiculosPolizas;
	private HtmlDataTable tblPlacas;
	private HtmlDataTable tblInactiveSoats;
	private HtmlOutputText txtNitProveedor;

	// Habilitadores...
	private boolean fechaFinAble;
	private boolean disableCrear;
	private boolean disablemodificar;
	private boolean disableReemplazar;
	private boolean showTablaPolizas;
	private boolean showTablaVehiculosPolizas;
	private boolean showPaginatorPolizas;
	private boolean showPlacaVHC;
	private boolean showTableSoats;
	private boolean roNuevoNumPoliza;
	private boolean boNumeroPoliza;
	private boolean showAuxiliaries = false;

	// Variables para mensajes de confirmación
	private boolean activarConfirmacion;
	private Integer opcConfirmacion;
	private static Integer MODIFICAR = 1;
	private static Integer REEMPLAZAR = 3;
	private ConsultsService consultsService;
	private static Integer CREAR_POLIZA = 4;

	private Vehicles vehicles = null;

	/**
	 * Constructor basico.
	 */
	public PoliciesPage() {
		disableCrear = false;
		disableReemplazar = true;
		disablemodificar = true;
		plcFechaInicio = new Date();
		plcFechaTerminacion = Util.obtenerFechaAnhoAdelante(plcFechaInicio,
				false);
		showTablaPolizas = false;
		roNuevoNumPoliza = true;
		// setNumeroFilas(10);
	}

	/**
	 * Se utiliza cuando se cambia la fecha y calcula la fecha de finalización.
	 * Se calcula porque siempre las polizas se crean con vigencia de un año.
	 * 
	 * @param changeEvent
	 */
	public void listener_fechaIni(ValueChangeEvent changeEvent) {
		if (changeEvent.getNewValue() != null) {
			Date dateTemp = (Date) changeEvent.getNewValue();
			setPlcFechaInicio(dateTemp);
			Date date = Util.obtenerFechaAnhoAdelante(getPlcFechaInicio(),
					false);
			setPlcFechaTerminacion(date);
			txtFechaFin.setValue(plcFechaTerminacion);
		}
	}

	/**
	 * Inicia el proceso de creación de una nueva póliza.
	 * 
	 * @param event
	 */
	public void action_crearPoliza(ActionEvent event) {
		try {
			validarDatos(true);

			validarBasicosSOAT();

			setOpcConfirmacion(CREAR_POLIZA);
			activarConfirmacion = true;

			mostrarMensaje(Util.loadMessageValue("MENSAJE.ALERTA"),
					activarConfirmacion);

		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void crearPoliza() {
		try {
			// Llamado al servicio Polizas...
			policiesService.crearPoliza(placaVehiculo, idTipoPoliza, Long
					.valueOf(plsNumero), plcFechaInicio, plcFechaTerminacion,
					getLogin(), plsDocumento1, plsDocumento2,
					plsValorContribucion, plsValorPrima, plsValorAseg);

			mostrarMensaje(Util.loadMessageValue("EXITO"), false);
			disableCrear = false;

			limpiar(); // Se limpia el formulario
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	/**
	 * Consulta una poliza por número de poliza o por la placa cuando es de tipo
	 * soat.
	 * 
	 * @param event
	 */
	public void action_consultarPoliza(ActionEvent event) {
		try {
			if (idTipoPoliza != null || idTipoPoliza.longValue() >= 0) {

				String plsNumeroPrueba = txtNumeroPoliza.getValue().toString()
						.trim();
				if (!plsNumeroPrueba.equals("")
						|| plsNumeroPrueba.trim().length() > 0) {

					if (!Util.validarNumerosConsulta("" + plsNumeroPrueba))
						throw new GWorkException(Util
								.loadErrorMessageValue("ERROR.CARACNUMPOLIZA"));

					plsNumero = Long.valueOf(plsNumeroPrueba.toString());
				}
				// Cuando no ingresa ningun parametro de busqueda...
				if (plsNumero == null
						&& (placaVehiculo == null || placaVehiculo.trim()
								.length() < 1)) {
					// Se genera una excepción
					throw new GWorkException(Util
							.loadErrorMessageValue("ERROR.PARCONSULTAPOL"));
				}

				// Cuando no ingresa un numero de poliza
				if (plsNumero == null) {
					// Pero si la placa del vehiculo ...
					if (placaVehiculo != null
							&& placaVehiculo.trim().length() > 0) {
						// Se consulta utilizando la placa
						consultarPorPlaca();
						return;
					}
				}

				// Cuando no ingresa una placa...
				if (placaVehiculo == null || placaVehiculo.trim().length() < 1) {
					// Pero si un numero de poliza...
					if (plsNumero != null) {
						// Se consulta con el número de póliza.
						consultarPorNumeroPoliza();
					}
				}
			}
			boNumeroPoliza = true;
			txtPlacaVehiculo.setReadonly(false);
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	/**
	 * Consulta una poliza por su numero.
	 * 
	 * @throws GWorkException
	 */
	private void consultarPorNumeroPoliza() throws GWorkException {
		if (plsNumero == null) {
			throw new GWorkException(Util.loadErrorMessageValue("DATOSVACIOS"));
		}

		if (!Util.validarNumerosConsulta("" + plsNumero))
			throw new GWorkException(Util
					.loadErrorMessageValue("CARACNUMPOLIZA"));

		Util.validarLimite("" + plsNumero, 38, 2, "ERROR.LIMITENUMPOL");

		Policies policies = policiesService.consultarPoliza(Long
				.valueOf(plsNumero));

		if (policies == null)
			throw new GWorkException(Util
					.loadErrorMessageValue("search.not.found"));

		setPlcFechaInicio(policies.getPlsFechainicioCobertura());
		setPlcFechaTerminacion(policies.getPlsFechafinCobertura());
		setIdTipoPoliza(policies.getPoliciesTypes().getPltCodigo());
		setPlsDocumento1(policies.getPlsDocUno());
		setPlsDocumento2(policies.getPlsDocDos());

		Long tipoPol = policies.getPoliciesTypes().getPltCodigo();

		if (tipoPol.longValue() == 10L) {
			Iterator<PoliciesVehicles> iter = policies.getPoliciesVehicleses()
					.iterator();

			PoliciesVehicles pvs = null;

			while (iter.hasNext()) {
				PoliciesVehicles policiesVehicles = (PoliciesVehicles) iter
						.next();

				Long numpol = policiesVehicles.getPolicies().getPlsNumero();
				Long estado = policiesVehicles.getPolicies().getPlsEstado();

				if (numpol.longValue() == policies.getPlsNumero().longValue()
						&& estado.longValue() == 1L) {
					pvs = policiesVehicles;
					break;
				}
			}

			if (pvs != null) {
				setPlacaVehiculo(pvs.getVehicles().getVhcPlacaDiplomatica());
				setPlsValorAseg(pvs.getPvsValorAsegurado());
				setPlsValorContribucion(pvs.getPvsValorIva());
				setPlsValorPrima(pvs.getPvsValorPrima());
				setPlsValorTotal(pvs.getPvsValorTotal());
				showPlacaVHC = true;
			}
			showTableSoats = true;
		}

		disableCrear = true;
		disableReemplazar = false;
		disablemodificar = false;
		roNuevoNumPoliza = false;

		cargarDatosDetalladosPoliza(policies);
	}

	/**
	 * Consulta una poliza de vehiculo (SOAT) utilizando la placa del vehiculo
	 * 
	 * @throws GWorkException
	 *             Manejador de Excepciones.
	 */
	private void consultarPorPlaca() throws GWorkException {

		if (placaVehiculo != null && placaVehiculo.trim().length() == 0)
			throw new GWorkException(Util.loadErrorMessageValue("ERROR.PLACA"));

		if (!Util.validarPlaca(placaVehiculo)) {
			throw new GWorkException(Util.loadErrorMessageValue("ERROR.PLACA"));
		}

		List<PoliciesVehicles> listPoliciesVehicles = new SearchPoliciesVehicles()
				.consultarPoliciesVehiclesVhc(placaVehiculo);

		Policies polVhcSOAT = null;
		PoliciesVehicles pvs = null;

		listaPolizas = new ArrayList<Policies>();
		listaSoatsInactivos = new ArrayList<Policies>();

		for (PoliciesVehicles policiesVehicles : listPoliciesVehicles) {
			Long idTipoPol = policiesVehicles.getPolicies().getPoliciesTypes()
					.getPltCodigo();
			vehicles = policiesVehicles.getVehicles();
			if (idTipoPol.longValue() == idTipoPoliza) {
				if (policiesVehicles.getPvsEstado().longValue() == 1L) {
					polVhcSOAT = policiesVehicles.getPolicies();
					pvs = policiesVehicles;
					listaPolizas.add(policiesVehicles.getPolicies());
				} else {
					listaSoatsInactivos.add(policiesVehicles.getPolicies());
				}
			}
		}

		if (polVhcSOAT == null || pvs == null) {
			throw new GWorkException(Util
					.loadErrorMessageValue("search.not.found"));
		}

		showPlacaVHC = true;
		roNuevoNumPoliza = false;
		showTableSoats = true;
		disableReemplazar = false;
		disablemodificar = false;

		if (listaPolizas.isEmpty()) {
			showTablaPolizas = false;
			showPaginatorPolizas = false;
		}

	}

	public void action_modificarPoliza(ActionEvent event) {
		try {
			String NuevoNumeroPoliza = txtNuevoNumeroPoliza.getValue()
					.toString();
			if (NuevoNumeroPoliza.trim().length() > 0
					&& NuevoNumeroPoliza.trim() != null)
				throw new GWorkException(Util
						.loadErrorMessageValue("ERROR.NUEVOPOLIZA"));

			validarDatos(true);
			validarBasicosSOAT();

			setActivarConfirmacion(true);
			setOpcConfirmacion(MODIFICAR);
			mostrarMensaje(Util.loadMessageValue("MENSAJE.ALERTA"),
					activarConfirmacion);

		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());

		}
	}

	public void action_modificarPolicies() {
		try {
			policiesService.modificarPoliza(placaVehiculo, idTipoPoliza,
					plsNumero, plsNuevoNumero, plcFechaInicio,
					plcFechaTerminacion, getLogin(), plsDocumento1,
					plsDocumento2, plsValorContribucion, plsValorPrima,
					plsValorAseg);
			mostrarMensaje(Util.loadMessageValue("EXITO.MODIFICAR"), false);
			limpiar();
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	private void validarBasicosSOAT() throws GWorkException {
		if (idTipoPoliza.longValue() == 10L) {

			// if (plsValorAseg == null)
			// throw new GWorkException(Util
			// .loadErrorMessageValue("ERROR.VALASEGINGR"));

			if (plsValorContribucion == null)
				throw new GWorkException(Util
						.loadErrorMessageValue("ERROR.VALIVAINGR"));

			if (plsValorPrima == null)
				throw new GWorkException(Util
						.loadErrorMessageValue("ERROR.VALPRIMAINGR"));

			if (plsValorTotal == null)
				throw new GWorkException(Util
						.loadErrorMessageValue("ERROR.VALATOTINGR"));

			if (placaVehiculo != null && placaVehiculo.trim().length() == 0)
				throw new GWorkException(Util
						.loadErrorMessageValue("ERROR.PLACA"));

			if (!Util.validarPlaca(placaVehiculo)) {
				throw new GWorkException(Util
						.loadErrorMessageValue("ERROR.PLACA"));
			}

			if (plsValorAseg != null)
				Util.validarLimite("" + plsValorAseg, 12, 2,
						"ERROR.LIMITEVALASEGPOL");

			Util.validarLimite("" + plsValorContribucion, 12, 2,
					"ERROR.LIMIVAPOL");
			Util.validarLimite("" + plsValorPrima, 12, 2, "ERROR.LIMPRIMPOL");
			Util.validarLimite(placaVehiculo, 20, 2, "ERROR.LIMPLACA");
			Util.validarLimite("" + plsValorTotal, 12, 2, "ERROR.LIMTOTPOL");
		}
	}

	private void validarDatos(boolean deshabilitarNuevonumero)
			throws GWorkException {
		String plsNumeroPrueba = txtNumeroPoliza.getValue().toString();

		if (idTipoPoliza == null || idTipoPoliza == -1L)
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.SELTIPOPOLIZA"));

		if (plsNumeroPrueba.trim() == null
				|| plsNumeroPrueba.trim().length() <= 0)
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.NUMPOLINGR"));

		if (!Util.validarNumerosConsulta("" + plsNumeroPrueba))
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.CARACNUMPOLIZA"));

		plsNumero = Long.valueOf(plsNumeroPrueba.toString().trim());

		if (!deshabilitarNuevonumero) {
			String strNuevoNumero = txtNuevoNumeroPoliza.getValue().toString();

			if (strNuevoNumero.trim() == null
					|| strNuevoNumero.trim().length() <= 0)
				throw new GWorkException(Util
						.loadErrorMessageValue("ERROR.NEWNUMPOLINGR"));

			if (!Util.validarNumerosConsulta("" + strNuevoNumero))
				throw new GWorkException(Util
						.loadErrorMessageValue("ERROR.CARACNEWNUMPOLIZA"));

			plsNuevoNumero = new Long(strNuevoNumero.toString().trim());

			Util.validarLimite("" + plsNuevoNumero, 38, 2,
					"ERROR.LIMITENEWNUMPOL");
		}
		if (plcFechaInicio == null)
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.FECHAINPOLI"));

		if (plcFechaTerminacion == null)
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.FECHAFNPOLI"));

		if (!plcFechaTerminacion.after(plcFechaInicio)) {
			throw new GWorkException(Util.loadErrorMessageValue("ERROR.FECHAS"));
		}

		if (plsDocumento1 == null || plsDocumento1.trim().length() <= 0)
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.NITPROVEEDORPOLIZA"));

		if (!Util.validarNumerosGuion("" + plsDocumento1))
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.FORMATONIT"));

		if (plsDocumento1 != null && plsDocumento1.trim().length() > 0)
			Util.validarLimite("" + plsDocumento1, 100, 2, "ERROR.LIMDOC1POL");

		ManipulacionFechas.validarAnhoFecha(plcFechaInicio, "");

		Util.validarLimite("" + plsNumero, 38, 2, "ERROR.LIMITENUMPOL");

		if (plsDocumento2 != null && plsDocumento2.trim().length() > 0)
			if (!Util
					.validarCadenaCaracteresEspecialesNumLetrasGuion(plsDocumento2))
				throw new GWorkException(Util
						.loadErrorMessageValue("ERROR.PASSWCARACESP"));

		if (plsDocumento2 != null && plsDocumento2.trim().length() > 0)
			Util.validarLimite("" + plsDocumento2, 40, 2, "ERROR.LIMDOC2POL");

		if (plcFechaInicio == null || plcFechaTerminacion == null)
			throw new GWorkException(Util.loadErrorMessageValue("date.empty"));

		ManipulacionFechas.validarAnhoFecha(plcFechaInicio, "");

		if (!plcFechaTerminacion.after(plcFechaInicio)) {
			throw new GWorkException(Util.loadErrorMessageValue("ERROR.FECHAS"));
		}

	}

	public void action_reemplazarPoliza(ActionEvent event) {
		try {

			validarDatos(false);
			validarBasicosSOAT();

			List<PoliciesVehicles> listPoliciesVehicles = new SearchPoliciesVehicles()
					.consultarPoliciesVehiclesVhc(placaVehiculo);

			for (PoliciesVehicles policiesVehicles : listPoliciesVehicles) {
				vehicles = policiesVehicles.getVehicles();
			}

			setActivarConfirmacion(true);
			setOpcConfirmacion(REEMPLAZAR);
			mostrarMensaje(Util.loadMessageValue("MENSAJE.ALERTA"),
					activarConfirmacion);
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void reemplazarPoliza() {
		try {
			policiesService.reemplazarPoliza(idTipoPoliza, Long
					.valueOf(plsNumero), Long.valueOf(plsNuevoNumero),
					plcFechaInicio, plcFechaTerminacion, getLogin(),
					plsDocumento1, plsDocumento2, placaVehiculo, plsValorAseg,
					plsValorContribucion, plsValorPrima, plsValorTotal,
					vehicles);
			limpiar();
			mostrarMensaje(Util.loadMessageValue("EXITO.REEMPLAZAR"), false);
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void listener_tipoPoliza(ValueChangeEvent event) {
		try {
			txtPlacaVehiculo.setReadonly(false);
			showTableSoats = false;
			showTablaPolizas = false;
			showTablaVehiculosPolizas = false;
			// setNumeroFilas(10);

			if (event.getNewValue() == null) {
				return;
			}

			idTipoPoliza = new Long("" + event.getNewValue());

			if (idTipoPoliza != null && idTipoPoliza != -1L) {

				listaPolizas = policiesService
						.consultarTodasPolizas(idTipoPoliza);

				if (listaPolizas != null && listaPolizas.size() > 0) {
					nombreTipoPoliza = ""
							+ listaPolizas.get(0).getPoliciesTypes()
									.getPltNombre();
					showTablaPolizas = true;
					showPaginatorPolizas = true;
					showPlacaVHC = true;
				}
			} else {
				showTablaPolizas = false;
				if (listaPolizas != null)
					listaPolizas.clear();
			}

			if (idTipoPoliza.longValue() != 10L) {
				showPlacaVHC = false;
			} else {
				showPlacaVHC = true;
			}
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void action_cargarPoliza(ActionEvent event) {
		try {
			if (tblPolizas.getRowData() != null) {

				Policies policies = (Policies) tblPolizas.getRowData();
				if (policies.getPoliciesTypes().getPltCodigo().longValue() != 10L) {
					setPlcFechaInicio(policies.getPlsFechainicioCobertura());
					setPlcFechaTerminacion(policies.getPlsFechafinCobertura());
					setIdTipoPoliza(policies.getPoliciesTypes().getPltCodigo());
					setPlsDocumento1(policies.getPlsDocUno());
					setPlsDocumento2(policies.getPlsDocDos());
					setPlsNumero(policies.getPlsNumero());
					disableCrear = true;
					disableReemplazar = false;
					disablemodificar = false;
					roNuevoNumPoliza = false;
					boNumeroPoliza = true;
					showTablaPolizas = false;
					return;
				}

				Iterator<PoliciesVehicles> iter = policies
						.getPoliciesVehicleses().iterator();

				Policies polVhcSOAT = null;
				PoliciesVehicles pvs = null;

				listaPolizas = new ArrayList<Policies>();

				while (iter.hasNext()) {
					PoliciesVehicles policiesVehicles = (PoliciesVehicles) iter
							.next();

					Long idTipoPol = policiesVehicles.getPolicies()
							.getPoliciesTypes().getPltCodigo();

					if (idTipoPol.longValue() == idTipoPoliza) {
						if (policiesVehicles.getPvsEstado().longValue() == 1L) {
							polVhcSOAT = policiesVehicles.getPolicies();
							pvs = policiesVehicles;
						} else {
							listaPolizas.add(policiesVehicles.getPolicies());
						}

					}
				}

				if (polVhcSOAT == null || pvs == null) {
					throw new GWorkException(Util
							.loadErrorMessageValue("search.not.found"));
				}

				if (policies.getPoliciesVehicleses().size() - 1 <= 0) {
					showTablaPolizas = false;
				}

				setPlcFechaInicio(polVhcSOAT.getPlsFechainicioCobertura());
				setPlcFechaTerminacion(polVhcSOAT.getPlsFechafinCobertura());
				setIdTipoPoliza(polVhcSOAT.getPoliciesTypes().getPltCodigo());
				setPlsDocumento1(polVhcSOAT.getPlsDocUno());
				setPlsDocumento2(polVhcSOAT.getPlsDocDos());
				setPlsNumero(polVhcSOAT.getPlsNumero());
				setPlsValorAseg(pvs.getPvsValorAsegurado());
				setPlsValorContribucion(pvs.getPvsValorIva());
				setPlsValorPrima(pvs.getPvsValorPrima());
				setPlsValorTotal(pvs.getPvsValorTotal());
				setPlacaVehiculo(pvs.getVehicles().getVhcPlacaDiplomatica());
				txtPlacaVehiculo.setReadonly(true);

				listaSoatsInactivos = policiesService
						.consultarSOATsInactivosVehiculo(pvs.getVehicles()
								.getVhcPlacaDiplomatica());

				showTableSoats = true;

				disableCrear = true;
				disableReemplazar = false;
				disablemodificar = false;
				boNumeroPoliza = true;
				roNuevoNumPoliza = false;
			}
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void cargarVehiculosPoliza(ActionEvent event) {
		cargarDatosDetalladosPoliza(null);
	}

	/**
	 * Carga los datos especificos de una poliza, tales como número de factura,
	 * fecha de factura, etc.
	 * 
	 * @param param
	 *            Poliza
	 */
	public void cargarDatosDetalladosPoliza(Policies param) {
		try {

			try {
				// aplica cuando se selecciona una poliza de la tabla de polizas
				if (tblPolizas != null) {
					if (tblPolizas.getRowCount() > 0) {
						if (tblPolizas.getRowData() != null && param == null) {
							param = (Policies) tblPolizas.getRowData();
						}
					}
				}
			} catch (RuntimeException e) {
				e.printStackTrace();
			}

			// si la lista con el detalle de la poliza es nula..
			if (listaPolizaDetalle == null) {
				// se crea una nueva
				listaPolizaDetalle = new ArrayList<PoliciesInvoice>();
			} else { // si no se limpia
				listaPolizaDetalle.clear();
			}

			// si la lista de polizas no tiene nada
			if (listaPolizas == null || listaPolizas.isEmpty()) {
				// se cancela la operacion
				return;
			}

			if ((param.getPoliciesInvoices() == null || param
					.getPoliciesInvoices().size() == 0)
					&& (param.getPoliciesVehicleses() == null || param
							.getPoliciesVehicleses().size() == 0)) {
				throw new GWorkException(Util
						.loadErrorMessageValue("ERROR.POLIZANODETALLE"));
			}

			// Se quitan de la tabla las polizas no seleccionadas
			listaPolizas = new ArrayList<Policies>();
			listaPolizas.add(param);

			showPaginatorPolizas = false;

			Iterator<PoliciesInvoice> iterFacturas = param
					.getPoliciesInvoices().iterator();

			// Se agregan todas las facturas de una poliza a la lista
			// que el datatable interpreta siempre y cuando es poliza
			// tenga facturas
			if (param.getPoliciesInvoices() != null
					&& !param.getPoliciesInvoices().isEmpty()) {
				while (iterFacturas.hasNext()) {
					listaPolizaDetalle.add((PoliciesInvoice) iterFacturas
							.next());
				}
			}
			// else
			// return;

			setListaPolizaDetalle(listaPolizaDetalle);

			Iterator<PoliciesVehicles> iterator = param.getPoliciesVehicleses()
					.iterator();

			listaPoliciesVhc = new ArrayList<PoliciesVehicles>();
			if (param.getPoliciesVehicleses() != null
					&& !param.getPoliciesVehicleses().isEmpty()) {
				while (iterator.hasNext()) {
					PoliciesVehicles pv = (PoliciesVehicles) iterator.next();
					listaPoliciesVhc.add(pv);
				}
			}

			showTablaVehiculosPolizas = true;

		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void listener_valorPrima(ValueChangeEvent changeEvent) {
		if (changeEvent.getNewValue() != null) {
			setPlsValorPrima(Util.convertirDecimal(
					changeEvent.getNewValue().toString()).floatValue());
			actualizarTotal();
		}
	}

	public void listener_valorContrib(ValueChangeEvent changeEvent) {
		if (changeEvent.getNewValue() != null) {
			setPlsValorContribucion(Util.convertirDecimal(
					changeEvent.getNewValue().toString()).floatValue());
			actualizarTotal();
		}
	}

	public void actualizarTotal() {

		if (getPlsValorContribucion() == null) {
			setPlsValorContribucion(0F);
		}
		if (getPlsValorPrima() == null) {
			setPlsValorPrima(0F);
		}
		Float soaValorTotal = getPlsValorPrima().floatValue()
				+ getPlsValorContribucion().floatValue();
		setPlsValorTotal(soaValorTotal.floatValue());
		txtValorTotal.setValue(getPlsValorTotal().floatValue());
	}

	public void action_limpiarForma(ActionEvent event) {
		limpiar();
	}

	private void limpiar() {
		// setNumeroFilas(1);
		disableCrear = false;
		disableReemplazar = true;
		disablemodificar = true;
		showTablaPolizas = false;
		showTablaVehiculosPolizas = false;
		showPaginatorPolizas = false;
		showPlacaVHC = false;
		plsNumero = null;
		plsNuevoNumero = null;
		plsDocumento1 = null;
		plsDocumento2 = null;

		setPlcFechaInicio(new Date());
		setPlcFechaTerminacion(Util.obtenerFechaAnhoAdelante(new Date(), false));
		selectSeguro.setValue(-1);
		txtPlacaVehiculo.setValue("");
		txtValorContrib.setValue("");
		txtValorPrima.setValue("");
		txtValorTotal.setValue("");
		txtValorAseg.setValue("");
		plsValorPrima = null;
		plsValorContribucion = null;
		plsValorTotal = null;
		plsValorAseg = null;
		txtValorAseg.setValue(null);
		txtValorContrib.setValue(null);
		txtValorPrima.setValue(null);
		txtValorTotal.setValue(null);
		txtPlacaVehiculo.setReadonly(false);
		boNumeroPoliza = false;
		showTableSoats = false;
		roNuevoNumPoliza = true;
		txtNumeroPoliza.setValue(null);
		txtNuevoNumeroPoliza.setValue(null);
	}

	public void mostrarMensaje(String mensaje, boolean buttonCancel) {
		PopupMessagePage message = (PopupMessagePage) FacesUtils
				.getManagedBean("popupMessagePage");
		if (message != null)
			message.showPopup(mensaje, buttonCancel);
	}

	public Long getIdTipoPoliza() {
		return idTipoPoliza;
	}

	public void setIdTipoPoliza(Long idTipoPoliza) {
		this.idTipoPoliza = idTipoPoliza;
	}

	public Date getPlcFechaInicio() {
		return plcFechaInicio;
	}

	public void setPlcFechaInicio(Date plcFechaInicio) {
		this.plcFechaInicio = plcFechaInicio;
	}

	public Date getPlcFechaTerminacion() {
		return plcFechaTerminacion;
	}

	public void setPlcFechaTerminacion(Date plcFechaTerminacion) {
		this.plcFechaTerminacion = plcFechaTerminacion;
	}

	public HtmlInputText getTxtFechaIni() {
		return txtFechaIni;
	}

	public void setTxtFechaIni(HtmlInputText txtFechaIni) {
		this.txtFechaIni = txtFechaIni;
	}

	public HtmlInputText getTxtFechaFin() {
		return txtFechaFin;
	}

	public void setTxtFechaFin(HtmlInputText txtFechaFin) {
		this.txtFechaFin = txtFechaFin;
	}

	public HtmlInputText getTxtNumeroPoliza() {
		return txtNumeroPoliza;
	}

	public void setTxtNumeroPoliza(HtmlInputText txtNumeroPoliza) {
		this.txtNumeroPoliza = txtNumeroPoliza;
	}

	public SelectItem[] getListPolicyTypes() {
		return listPolicyTypes;
	}

	public void setListPolicyTypes(SelectItem[] listPolicyTypes) {
		this.listPolicyTypes = listPolicyTypes;
	}

	public boolean isFechaFinAble() {
		return fechaFinAble;
	}

	public void setFechaFinAble(boolean fechaFinAble) {
		this.fechaFinAble = fechaFinAble;
	}

	public HtmlSelectOneMenu getSelectSeguro() {
		return selectSeguro;
	}

	public void setSelectSeguro(HtmlSelectOneMenu selectSeguro) {
		this.selectSeguro = selectSeguro;
	}

	public boolean isDisableCrear() {
		return disableCrear;
	}

	public void setDisableCrear(boolean disableCrear) {
		this.disableCrear = disableCrear;
	}

	public PoliciesService getPoliciesService() {
		return policiesService;
	}

	public void setPoliciesService(PoliciesService policiesService) {
		this.policiesService = policiesService;
	}

	public Long getPlsNumero() {
		return plsNumero;
	}

	public void setPlsNumero(Long plsNumero) {
		txtNumeroPoliza.setValue(plsNumero.toString());
	}

	public HtmlInputText getTxtNuevoNumeroPoliza() {
		return txtNuevoNumeroPoliza;
	}

	public void setTxtNuevoNumeroPoliza(HtmlInputText txtNuevoNumeroPoliza) {
		this.txtNuevoNumeroPoliza = txtNuevoNumeroPoliza;
	}

	public Long getPlsNuevoNumero() {
		return plsNuevoNumero;
	}

	public void setPlsNuevoNumero(Long plsNuevoNumero) {
		this.txtNuevoNumeroPoliza.setValue(plsNuevoNumero.toString());
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

	public List<Policies> getListaPolizas() {
		return listaPolizas;
	}

	public void setListaPolizas(List<Policies> listaPolizas) {
		this.listaPolizas = listaPolizas;
	}

	public HtmlDataTable getTblPolizas() {
		return tblPolizas;
	}

	public void setTblPolizas(HtmlDataTable tblPolizas) {
		this.tblPolizas = tblPolizas;
	}

	public boolean isShowTablaPolizas() {
		return showTablaPolizas;
	}

	public void setShowTablaPolizas(boolean showTablaPolizas) {
		this.showTablaPolizas = showTablaPolizas;
	}

	public HtmlDataTable getTblVehiculosPolizas() {
		return tblVehiculosPolizas;
	}

	public void setTblVehiculosPolizas(HtmlDataTable tblVehiculosPolizas) {
		this.tblVehiculosPolizas = tblVehiculosPolizas;
	}

	public boolean isShowTablaVehiculosPolizas() {
		return showTablaVehiculosPolizas;
	}

	public void setShowTablaVehiculosPolizas(boolean showTablaVehiculosPolizas) {
		this.showTablaVehiculosPolizas = showTablaVehiculosPolizas;
	}

	public HtmlDataTable getTblPlacas() {
		return tblPlacas;
	}

	public void setTblPlacas(HtmlDataTable tblPlacas) {
		this.tblPlacas = tblPlacas;
	}

	public List<PoliciesVehicles> getListaPoliciesVhc() {
		return listaPoliciesVhc;
	}

	public void setListaPoliciesVhc(List<PoliciesVehicles> listaPoliciesVhc) {
		this.listaPoliciesVhc = listaPoliciesVhc;
	}

	public List<PoliciesInvoice> getListaPolizaDetalle() {
		return listaPolizaDetalle;
	}

	public void setListaPolizaDetalle(List<PoliciesInvoice> listaPolizaDetalle) {
		this.listaPolizaDetalle = listaPolizaDetalle;
	}

	public boolean isDisableReemplazar() {
		return disableReemplazar;
	}

	public void setDisableReemplazar(boolean disableReemplazar) {
		this.disableReemplazar = disableReemplazar;
	}

	public boolean isDisablemodificar() {
		return disablemodificar;
	}

	public void setDisablemodificar(boolean disablemodificar) {
		this.disablemodificar = disablemodificar;
	}

	public String getNombreTipoPoliza() {
		return nombreTipoPoliza;
	}

	public void setNombreTipoPoliza(String nombreTipoPoliza) {
		this.nombreTipoPoliza = nombreTipoPoliza;
	}

	public boolean isShowPaginatorPolizas() {
		return showPaginatorPolizas;
	}

	public void setShowPaginatorPolizas(boolean showPaginatorPolizas) {
		this.showPaginatorPolizas = showPaginatorPolizas;
	}

	public int getNumeroFilas() {
		return numeroFilas;
	}

	public void setNumeroFilas(int numeroFilas) {
		this.numeroFilas = numeroFilas;
	}

	public String getLogin() {
		LoginPage login = (LoginPage) FacesUtils.getManagedBean("loginPage");
		if (login != null) {
			return login.getLogin();
		} else
			return "";
	}

	public Float getPlsValorPrima() {
		return plsValorPrima;
	}

	public void setPlsValorPrima(Float plsValorPrima) {
		this.plsValorPrima = plsValorPrima;
	}

	public Float getPlsValorContribucion() {
		return plsValorContribucion;
	}

	public void setPlsValorContribucion(Float plsValorContribucion) {
		this.plsValorContribucion = plsValorContribucion;
	}

	public Float getPlsValorTotal() {
		return plsValorTotal;
	}

	public void setPlsValorTotal(Float plsValorTotal) {
		this.plsValorTotal = plsValorTotal;
	}

	public String getPlsDocumento1() {
		return plsDocumento1;
	}

	public void setPlsDocumento1(String plsDocumento1) {
		this.plsDocumento1 = plsDocumento1;
	}

	public String getPlsDocumento2() {
		return plsDocumento2;
	}

	public void setPlsDocumento2(String plsDocumento2) {
		this.plsDocumento2 = plsDocumento2;
	}

	public HtmlInputText getTxtValorPrima() {
		return txtValorPrima;
	}

	public void setTxtValorPrima(HtmlInputText txtValorPrima) {
		this.txtValorPrima = txtValorPrima;
	}

	public HtmlInputText getTxtValorContrib() {
		return txtValorContrib;
	}

	public void setTxtValorContrib(HtmlInputText txtValorContrib) {
		this.txtValorContrib = txtValorContrib;
	}

	public HtmlInputText getTxtDoc1() {
		return txtDoc1;
	}

	public void setTxtDoc1(HtmlInputText txtDoc1) {
		this.txtDoc1 = txtDoc1;
	}

	public HtmlInputText getTxtDoc2() {
		return txtDoc2;
	}

	public void setTxtDoc2(HtmlInputText txtDoc2) {
		this.txtDoc2 = txtDoc2;
	}

	public HtmlInputText getTxtValorTotal() {
		return txtValorTotal;
	}

	public void setTxtValorTotal(HtmlInputText txtValorTotal) {
		this.txtValorTotal = txtValorTotal;
	}

	public boolean isShowPlacaVHC() {
		return showPlacaVHC;
	}

	public void setShowPlacaVHC(boolean showPlacaVHC) {
		this.showPlacaVHC = showPlacaVHC;
	}

	public String getPlacaVehiculo() {
		return placaVehiculo;
	}

	public void setPlacaVehiculo(String placaVehiculo) {
		this.placaVehiculo = placaVehiculo.toUpperCase();
	}

	public HtmlInputText getTxtPlacaVehiculo() {
		return txtPlacaVehiculo;
	}

	public void setTxtPlacaVehiculo(HtmlInputText txtPlacaVehiculo) {
		this.txtPlacaVehiculo = txtPlacaVehiculo;
	}

	public Float getPlsValorAseg() {
		return plsValorAseg;
	}

	public void setPlsValorAseg(Float plsValorAseg) {
		this.plsValorAseg = plsValorAseg;
	}

	public HtmlInputText getTxtValorAseg() {
		return txtValorAseg;
	}

	public void setTxtValorAseg(HtmlInputText txtValorAseg) {
		this.txtValorAseg = txtValorAseg;
	}

	public List<Policies> getListaSoatsInactivos() {
		return listaSoatsInactivos;
	}

	public void setListaSoatsInactivos(List<Policies> listaSoatsInactivos) {
		this.listaSoatsInactivos = listaSoatsInactivos;
	}

	public HtmlDataTable getTblInactiveSoats() {
		return tblInactiveSoats;
	}

	public void setTblInactiveSoats(HtmlDataTable tblInactiveSoats) {
		this.tblInactiveSoats = tblInactiveSoats;
	}

	public boolean isShowTableSoats() {
		return showTableSoats;
	}

	public void setShowTableSoats(boolean showTableSoats) {
		this.showTableSoats = showTableSoats;
	}

	/**
	 * @return the roNuevoNumPoliza
	 */
	public boolean isRoNuevoNumPoliza() {
		return roNuevoNumPoliza;
	}

	/**
	 * @param roNuevoNumPoliza
	 *            the roNuevoNumPoliza to set
	 */
	public void setRoNuevoNumPoliza(boolean roNuevoNumPoliza) {
		this.roNuevoNumPoliza = roNuevoNumPoliza;
	}

	public boolean isShowAuxiliaries() {
		return showAuxiliaries;
	}

	public void setShowAuxiliaries(boolean showAuxiliaries) {
		this.showAuxiliaries = showAuxiliaries;
	}

	public void action_showAuxiliares(ActionEvent actionEvent) {
		showAuxiliaries = true;
	}

	public void action_closeShowAuxiliaries(ActionEvent actionEvent) {

		showAuxiliaries = false;
	}

	public String getParametroBusquedad() {
		return parametroBusquedad;
	}

	public void setParametroBusquedad(String parametroBusquedad) {
		this.parametroBusquedad = parametroBusquedad;
	}

	public List<Users> getListNitProveedor() {
		return listNitProveedor;
	}

	public void setListNitProveedor(List<Users> listNitProveedor) {
		this.listNitProveedor = listNitProveedor;
	}

	public void action_filtrarAuxiliares(ActionEvent actionEvent) {
		try {
			this.listNitProveedor = consultsService
					.nitProveedor(parametroBusquedad);
			// setListNitProveedor();
		} catch (GWorkException e) {
			mostrarMensaje(e.getMessage(), false);
		}
	}

	public void rowSelectorAuxiliaries(RowSelectorEvent rowSelectorEvent) {
		String NitProveedor = (String) txtNitProveedor.getValue();

		for (Users users : listNitProveedor) {
			if (NitProveedor.equalsIgnoreCase(users.getUsrIdentificacion())) {
				setPlsDocumento1(users.getUsrIdentificacion());
				showAuxiliaries = false;
			}
		}
		setListNitProveedor(null);
	}

	public ConsultsService getConsultsService() {
		return consultsService;
	}

	public void setConsultsService(ConsultsService consultsService) {
		this.consultsService = consultsService;
	}

	public HtmlOutputText getTxtNitProveedor() {
		return txtNitProveedor;
	}

	public void setTxtNitProveedor(HtmlOutputText txtNitProveedor) {
		this.txtNitProveedor = txtNitProveedor;
	}

	public boolean isBoNumeroPoliza() {
		return boNumeroPoliza;
	}

	public void setBoNumeroPoliza(boolean boNumeroPoliza) {
		this.boNumeroPoliza = boNumeroPoliza;
	}

	/**
	 * @return the vehicles
	 */
	public Vehicles getVehicles() {
		return vehicles;
	}

	/**
	 * @param vehicles
	 *            the vehicles to set
	 */
	public void setVehicles(Vehicles vehicles) {
		this.vehicles = vehicles;
	}
}