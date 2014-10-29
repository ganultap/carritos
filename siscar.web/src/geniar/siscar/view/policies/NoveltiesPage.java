package geniar.siscar.view.policies;

import geniar.siscar.logic.consultas.SearchMonthlyTransac;
import geniar.siscar.logic.consultas.SearchPolicies;
import geniar.siscar.logic.consultas.SearchPolicyAssignementTypeControl;
import geniar.siscar.logic.consultas.SearchVehicles;
import geniar.siscar.logic.policies.services.InconsistenciesTypesService;
import geniar.siscar.logic.policies.services.PoliciesInvoiceService;
import geniar.siscar.logic.policies.services.PoliciesService;
import geniar.siscar.logic.policies.services.PoliciesTransactionsService;
import geniar.siscar.logic.policies.services.PoliciesTypeService;
import geniar.siscar.logic.policies.services.impl.TiposInconsis;
import geniar.siscar.model.Brands;
import geniar.siscar.model.ControlAssignationPolicies;
import geniar.siscar.model.Inconsistencies;
import geniar.siscar.model.LegateesTypes;
import geniar.siscar.model.LocationsTypes;
import geniar.siscar.model.Policies;
import geniar.siscar.model.PoliciesInvoice;
import geniar.siscar.model.PoliciesTypes;
import geniar.siscar.model.PoliciesVehicles;
import geniar.siscar.model.PoliciesVehiclesId;
import geniar.siscar.model.Vehicles;
import geniar.siscar.model.VehiclesAssignation;
import geniar.siscar.persistence.EntityManagerHelper;
import geniar.siscar.persistence.LegateesTypesDAO;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.EventObject;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import com.icesoft.faces.component.ext.HtmlDataTable;
import com.icesoft.faces.component.inputfile.InputFile;
import com.icesoft.faces.webapp.xmlhttp.PersistentFacesState;
import com.icesoft.faces.webapp.xmlhttp.RenderingException;

/**
 * @author Geniar Intelligence
 * @version 1.0, 20/11/2008 Managed Bean de Novedades de polizas
 */
public class NoveltiesPage {

	// Servicios...
	/** Servicio para la gestion de las novedades. */
	private PoliciesTransactionsService policiesTransactionsService;
	/** Servicio para la gestion de tipos de inconsistencias. */
	private InconsistenciesTypesService inconsistenciesTypesService;
	/** Servicio para la gestion de las facturas de polizas. */
	private PoliciesInvoiceService policiesInvoiceService;
	/** Servicio para los tipos de polizas. */
	private PoliciesTypeService policiesTypeService;
	/** Servicio para las polizas. */
	private PoliciesService policiesService;

	// Componentes visuales...
	/** {@link InputFile} componente para carga de archivo plano. */
	private InputFile inputFile;

	/** {@link HtmlDataTable} Tabla con las inconsistencias */
	private HtmlDataTable tblIncons;

	/**
	 * {@link HtmlDataTable} Tabla con los datos correctos (asociación
	 * vehiculo-póliza)
	 */
	private HtmlDataTable tblPvs;

	// Variables de control...
	/** Caracter que indica cuando hay un dato completo. */
	private static char SEPARADOR;

	/** Caracter que indica cuando hay un registro completo. */
	private static char FIN_FILA;

	/**
	 * Listado de asociaciones. Usada por la tabla tblPvs para listar todas las
	 * asociaciones
	 */
	private List<PoliciesVehicles> lstPvs;

	/**
	 * Listado de inconsistencias. Usada por la tabla tblIncons para lista todas
	 * las inconsistencias encontradas al cargar el archivo.
	 */
	private List<Inconsistencies> lstIncons;

	/** Indica si se debe habilitar el botón para ver los datoscargados. */
	private boolean disableVerDatos;
	private boolean disabledGuardarDetalle;

	private boolean showTblInconsis;
	private boolean showTblPvs;

	/** Valores para el tipo de polizas */
	private Long idTipoPoliza;
	private boolean showNovedadesSoat;
	private boolean showFacturasSOAT;
	private boolean showNovedadesFA;
	private HtmlDataTable tblFacturaSOAT;

	/** Datos del formulario de factura SOAT consultada */
	private String numeroFacutra;
	private Date fechaFactura;
	private String conceptoFactura;

	/** Lista de facturas de tipo SOAT */
	private List<PoliciesInvoice> listaPolicieInvoiceSOAT;

	/** Indica el porcentaje cargado del archivo. */
	private int porcentaje;

	/* Control de mensajes de confirmación. */
	private boolean activarConfirmacion;
	private Integer opcConfirmacion;
	private static Integer GUARDAR_DETALLE = 2;
	private Long valorTotalFactura = 0L;

	/**
	 * {@link http://www.icesoft.com/developer_guides/icefaces/api/com/icesoft/faces/webapp/xmlhttp/PersistentFacesState.html}
	 * "The PersistentFacesState class allows the application to initiate
	 * rendering asynchronously and independently of user interaction."
	 */
	private PersistentFacesState estadoCarga;

	/** Objeto factura asociada en el archivo. */
	private PoliciesInvoice invoicePolicy;

	/** Vehiculos presentes en la factura */
	private ArrayList<PoliciesVehicles> vhcsFactura;

	public NoveltiesPage() {
		try {
			SEPARADOR = Util.loadMessageValue("CARACTER_SEPARADOR_COLS")
					.charAt(0);

			FIN_FILA = Util.loadMessageValue("CARACTER_FIN_LINEA").charAt(0);

			estadoCarga = PersistentFacesState.getInstance();
			disabledGuardarDetalle = true;
			disableVerDatos = true;
			porcentaje = -1;
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	// Métodos de lógica...

	/**
	 * Inicia el proceso de lectura del archivo y validación de los datos
	 * contenidos en él.
	 */
	public void action_verDatos(ActionEvent event) {
		try {
			if (inputFile != null) {
				obtenerDatosNovedades(leerArchivo(inputFile.getFile()));
				FileUtils.deleteFile(inputFile.getFile());
				if (invoicePolicy.getPinCargado().longValue() != ParametersUtil.FacturaEnviadaAP
						.longValue())
					disabledGuardarDetalle = false;
			} else {
				throw new GWorkException(Util
						.loadErrorMessageValue("ERROR.SELARCHIVOFAC"));
			}
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	/**
	 * Lee cada dato de la cadena y aplica la logica de negocio.
	 * 
	 * @param datosArchivo
	 *            Cadena que con los datos del archivo.
	 * @throws GWorkException
	 *             Manejador de excepciones.
	 */
	public void obtenerDatosNovedades(StringBuffer datosArchivo)
			throws GWorkException {
		try {
			lstIncons = new ArrayList<Inconsistencies>();
			lstPvs = new ArrayList<PoliciesVehicles>();
			vhcsFactura = new ArrayList<PoliciesVehicles>();

			String valor = "";

			Vector<String> lstValores = null;

			int numeroFilas = 0;

			for (int i = 0; i < datosArchivo.length(); i++) {
				if (datosArchivo.charAt(i) == FIN_FILA) {
					numeroFilas++;
				}
			}

			if (numeroFilas == 0) {
				throw new GWorkException(Util
						.loadErrorMessageValue("ERROR.ARCHIVOVACIO"));
			}

			List<Vector<String>> registros = new ArrayList<Vector<String>>();

			lstValores = new Vector<String>();
			// Se busca en los datos del archivo cada valor
			for (int j = 0; j < datosArchivo.length(); j++) {
				char c = datosArchivo.charAt(j);

				// si no se a terminado de leer el archivo
				if (c != FIN_FILA) {
					// pregunta si el caracter actual no es un separador de
					// columna
					if (c != SEPARADOR) {
						// se añade el caracter al valor
						valor += c;
					} else {
						if (valor.trim().length() != 0) {
							// si el caracter es el separador
							// se agrega el valor a la lista de valores
							lstValores.add(valor);
							// y se reinicia la variable de valor actual
							valor = "";
						} else {
							throw new GWorkException(
									Util
											.loadErrorMessageValue("ERROR.VACIO_ARCH_PLANO"));
						}
					}
				} else {
					lstValores.add(valor);
					registros.add(lstValores);
					lstValores = new Vector<String>();
				}
			}

			// Para cada una de las filas...
			for (int i = 0; i < registros.size(); i++) {
				// Se extrae un registro que tiene sus datos
				List<String> row = registros.get(i);
				// y se valida que los datos tenga el formato adecuado
				try {
					validarDatosArchivo(row);
				} catch (GWorkException e) {
					throw new GWorkException(e.getMessage());
				}
			}

			// controla las placas que ya se han revisado
			List<String> listaControl = new ArrayList<String>();

			// Se determinan que vehículos estan repetidos en la factura
			for (int i = 0; i < vhcsFactura.size(); i++) {
				PoliciesVehicles data = vhcsFactura.get(i);// Se toma una
				// de las placas
				String placa = (data.getVehicles().getVhcPlacaDiplomatica());
				if (!listaControl.contains(placa)) {// Sino esta en la lista de
					// control
					listaControl.add(placa);// Se agrega la placa que se va a
					// revisar
					int cuentaVHC = 0;// guarda cuantos vehiculos se
					// encontraron el factura
					for (int j = 0; j < vhcsFactura.size(); j++) {
						PoliciesVehicles data2 = vhcsFactura.get(j);
						String placa2 = (data2.getVehicles()
								.getVhcPlacaDiplomatica());
						if (placa.equals(placa2)) {
							cuentaVHC++;
						}
					}
					if (cuentaVHC > 1) {
						agregarInconsistenciaLista(data.getPolicies(),
								TiposInconsis.getVHC_DOBLE_POL(), getLogin(),
								data.getPvsValorIva(), data.getPvsValorPrima(),
								data.getPvsValorTotal(), data
										.getPvsValorAsegurado(), placa, data
										.getLegateesTypes(), data
										.getPvsCarnetAsignatario());
					}
				}
			}

			List<Inconsistencies> lstTemp = lstIncons;

			for (int i = 0; i < lstIncons.size(); i++) {
				Inconsistencies m = (Inconsistencies) lstIncons.get(i);// se
				// toma
				// una
				// inconsistencia
				String placa = m.getVhcPlaca();// se toma la placa involucrada

				List<Inconsistencies> lstInc = new ArrayList<Inconsistencies>();
				for (Inconsistencies inc : lstTemp) {
					if (inc.getVhcPlaca().equals(placa)) {
						lstInc.add(inc);
					}
				}

				if (lstInc.size() > 1) {
					for (int h = 0; h < lstInc.size(); h++) {
						Inconsistencies inc = lstInc.get(h);
						List<Inconsistencies> lstInc2 = lstInc;

						int o = 0;

						for (int j = 0; j < lstInc2.size(); j++) {

							if (inc.getInconsistenciesTypes().getIctId()
									.longValue() == lstInc2.get(j)
									.getInconsistenciesTypes().getIctId()
									.longValue()) {
								o++;
							}
							if (o > 1) {
								lstInc2.remove(j);
								lstInc.remove(inc);
								lstTemp.remove(inc);
							}
						}
					}
				}
			}

			// lista temporal
			List<PoliciesVehicles> lstPv = lstPvs;

			// PoliciesVehicles que tienen inconsistencias
			List<PoliciesVehicles> lstPvRes = new ArrayList<PoliciesVehicles>();

			for (int i = 0; i < lstPvs.size(); i++) {
				PoliciesVehicles pv = lstPvs.get(i);
				String placa = pv.getVehicles().getVhcPlacaDiplomatica();
				for (int j = 0; j < lstTemp.size(); j++) {
					// Si en la lista de inconsistencias encontradas
					// se encuentra registrada alguna de las placas
					// de la lista de policiesVehicles
					if (lstTemp.get(j).getVhcPlaca().equals(placa)) {
						// se agrega el poli... inconsistente a la lista
						// temporal
						lstPvRes.add(pv);
						// se termina el ciclo para tomar otro policiesVehicle
						// (pv)
						break;
					}
				}
			}

			lstPvs = new ArrayList<PoliciesVehicles>();

			// Agrega las polizas de vehiculos que se encuentren en el archivo
			// plano
			for (PoliciesVehicles policiesVehicles : lstPv) {

				PoliciesVehicles objPV = EntityManagerHelper.getEntityManager()
						.merge(policiesVehicles);

				// objPV.setPoliciesInvoice(new po)
				lstPvs.add(objPV);

			}

			// Remueve de la poliza de vehiculos aquellas polizas que ya se
			// encuentra como incosistencias de tipo 1 y 3
			for (Inconsistencies inconsistencies : lstTemp) {
				for (int i = 0; i < lstPvs.size(); i++) {
					PoliciesVehicles pvRemove = lstPvs.get(i);
					if ((inconsistencies.getInconsistenciesTypes().getIctId()
							.longValue() == 1L || inconsistencies
							.getInconsistenciesTypes().getIctId().longValue() == 3L)
							&& inconsistencies.getVhcPlaca().equalsIgnoreCase(
									pvRemove.getVehicles()
											.getVhcPlacaDiplomatica()))
						lstPvs.remove(i);
				}
			}

			// if (lstPvs.isEmpty()) {
			// // tblPvs.setRendered(false);
			// showTblPvs = false;
			// } else {
			// // tblPvs.setRendered(true);
			// showTblPvs = true;
			// }

			lstIncons = new ArrayList<Inconsistencies>();

			for (Inconsistencies inconsistencies : lstTemp) {
				Inconsistencies objInc = EntityManagerHelper.getEntityManager()
						.merge(inconsistencies);
				lstIncons.add(objInc);
			}
			// lstIncons = lstTemp;

			// if (lstIncons.isEmpty()) {
			// // tblIncons.setRendered(false);
			// showTblInconsis = false;
			// } else {
			// // tblIncons.setRendered(true);
			//
			// showTblInconsis = true;
			// }
			showTblInconsis = true;
			showTblPvs = true;

		} catch (RuntimeException e) {
			throw new GWorkException(e.getMessage());
		} catch (GWorkException e) {
			throw new GWorkException(e.getMessage());
		} catch (Exception e) {
			throw new GWorkException(e.getMessage());
		}
	}

	/**
	 * Valida que cada uno de los datos del archivo plano<br>
	 * tengan el formato correcto.
	 * 
	 * @param lstValores
	 *            Listado de valores que se van a validar.
	 * @throws GWorkException
	 *             Manejador de excepciones.
	 */
	private void validarDatosArchivo(List<String> lstValores)
			throws GWorkException {
		try {

			// Se valida el tipo de poliza que viene desde el archivo
			String tipoPoliza = "";
			try {
				tipoPoliza = lstValores.get(5).toUpperCase();
			} catch (RuntimeException e1) {
				throw new GWorkException(Util
						.loadErrorMessageValue("ERROR.TIPOPOLNOTVALID"));
			}

			if (tipoPoliza == null || tipoPoliza.trim().length() <= 0) {
				throw new GWorkException(Util
						.loadErrorMessageValue("ERROR.TIPOPOLNOTVALID"));
			}

			if (!Util.validarCadenaCaracteres(tipoPoliza)) {
				throw new GWorkException(Util
						.loadErrorMessageValue("ERROR.TIPOPOLNOTVALID"));
			}

			PoliciesTypes policiesTypes = policiesTypeService
					.consultarTipoPolizaPorNombre(tipoPoliza);

			if (policiesTypes == null) {
				throw new GWorkException(Util
						.loadErrorMessageValue("ERROR.TIPOPOLIZANOF"));
			}

			if (!policiesTypes.getPltNombre().equals(tipoPoliza)) {
				throw new GWorkException(Util
						.loadErrorMessageValue("ERROR.TIPOPOLNOTMATCH"));
			}

			Date fechafact = null;
			try {
				// fechafact =
				// ManipulacionFechas.stringToDate(lstValores.get(3),
				// "dd-MMM-yyyy");
				DateFormat fechaFormato = new SimpleDateFormat("dd-MMM-yyyy");
				fechafact = fechaFormato.parse(lstValores.get(3));
				// Fechafact = dateFormat.format(fechafact);
			} catch (Exception e) {
				throw new GWorkException(Util
						.loadErrorMessageValue("ERROR.FORMTOFECHASFACT"));
			}

			String numeroFactura = null;
			try {
				numeroFactura = lstValores.get(2);
			} catch (RuntimeException e) {
				throw new GWorkException("ERROR.DATONOVALIDOAPLANO");
			}

			if (numeroFactura == null || numeroFactura.trim().length() <= 0) {
				throw new GWorkException(Util
						.loadErrorMessageValue("ERROR.DATONOVALIDOAPLANO"));
			}

			Integer numeroPoliza = null;
			try {
				numeroPoliza = Integer.valueOf(lstValores.get(4));
			} catch (RuntimeException e) {
				throw new GWorkException("ERROR.DATONOVALIDOAPLANO");
			}

			Policies pol = policiesService.consultarPoliza(new Long(""
					+ numeroPoliza));

			if (pol == null) {
				throw new GWorkException(Util
						.loadErrorMessageValue("ERROR.POLIZANEXISTE")
						+ ": " + numeroPoliza);
			}

			// if (policiesTypes.getPltCodigo().longValue() ==
			// ParametersUtil.SOAT) {
			// String conceptoFact = "Factura de Soat Numero " + numeroFactura;
			// policiesInvoiceService.crearFacturaPoliza(numeroPoliza
			// .longValue(), numeroFactura, fechafact, conceptoFact,
			// getLogin());
			// }

			if (policiesTypes.getPltCodigo().longValue() == ParametersUtil.SOAT)
				invoicePolicy = new SearchPolicies()
						.consultarFacturaPorNumeroFactura(numeroFactura
								.toString());
			else
				invoicePolicy = new SearchPolicies()
						.consultarFacturaNumFacPoliza(numeroFactura,
								numeroPoliza.longValue());

			if (invoicePolicy == null) {
				throw new GWorkException(Util
						.loadErrorMessageValue("ERROR.NUMFACTNOTFOUND"));
			}

			// Valida que esten relacionados los vehiculos soat en el archivo
			// plano de la factura
			if (policiesTypes.getPltCodigo().longValue() == ParametersUtil.SOAT) {
				invoicePolicy = EntityManagerHelper.getEntityManager().merge(
						invoicePolicy);

				String placaVehiculo = "";
				int contienePlaca = 0;

				for (PoliciesVehicles pvSoat : invoicePolicy
						.getPoliciesVehicleses()) {

					placaVehiculo = lstValores.get(13);

					if (placaVehiculo.equalsIgnoreCase(pvSoat.getVehicles()
							.getVhcPlacaDiplomatica()))
						contienePlaca++;

				}

				if (contienePlaca == 0)
					throw new GWorkException("El vehiculo: " + placaVehiculo
							+ " no esta relacionada a la factura");

			}

			if (policiesTypes.getPltCodigo().longValue() != ParametersUtil.SOAT) {
				Policies Poliza = invoicePolicy.getPolicies();

				if (Poliza.getPlsNumero().longValue() != pol.getPlsNumero()
						.longValue())
					throw new GWorkException(Util
							.loadErrorMessageValue("ERROR.POLIZASDIFERENTES"));
			}

			if (fechafact.compareTo(invoicePolicy.getPinFechaFactura()) != 0) {
				throw new GWorkException(Util
						.loadErrorMessageValue("ERROR.FECHAFACTFACT"));
			}

			// Se valida el valor prima
			Float valorPrima = null;
			try {
				valorPrima = obtenerFloat(lstValores.get(17));
				valorTotalFactura += valorPrima.longValue();
			} catch (Exception e) {
				throw new GWorkException(Util
						.loadErrorMessageValue("ERROR.DATONOVALIDOAPLANO"));
			}

			if (valorPrima == null) {
				throw new GWorkException(Util
						.loadErrorMessageValue("ERROR.DATONOVALIDOAPLANO"));
			}

			// Se valida el valor asegurado
			Float valorAsegurado = null;
			try {
				valorAsegurado = obtenerFloat(lstValores.get(8));
			} catch (Exception e) {
				throw new GWorkException(Util
						.loadErrorMessageValue("ERROR.DATONOVALIDOAPLANO"));
			}

			if (valorAsegurado == null) {
				throw new GWorkException(Util
						.loadErrorMessageValue("ERROR.DATONOVALIDOAPLANO"));
			}

			// Se valida el valor iva
			Float valorIva = null;
			try {
				valorIva = obtenerFloat(lstValores.get(18));
			} catch (Exception e) {
				throw new GWorkException(Util
						.loadErrorMessageValue("ERROR.DATONOVALIDOAPLANO"));
			}

			if (valorIva == null) {
				throw new GWorkException(Util
						.loadErrorMessageValue("ERROR.DATONOVALIDOAPLANO"));
			}

			// Se valida el valor total
			Float valorTotal = null;
			try {
				valorTotal = obtenerFloat(lstValores.get(19));
			} catch (Exception e) {
				throw new GWorkException(Util
						.loadErrorMessageValue("ERROR.DATONOVALIDOAPLANO"));
			}

			if (valorTotal == null) {
				throw new GWorkException(Util
						.loadErrorMessageValue("ERROR.DATONOVALIDOAPLANO"));
			}

			// Se valida que la suma del archivo
			Float valorCalculado = valorIva + valorPrima;
			if (valorCalculado.floatValue() != valorTotal.floatValue()) {
				throw new GWorkException(Util
						.loadErrorMessageValue("ERROR.DATONOVALIDOAPLANO")
						+ ": Valor calculado: "
						+ valorCalculado
						+ " Valor total archivo: " + valorTotal);
			}

			if (!policiesTypes.getPltNombre().equals(
					pol.getPoliciesTypes().getPltNombre())) {
				throw new GWorkException(Util
						.loadErrorMessageValue("ERROR.TIPOPOLNOTMATCH"));
			}

			// Se validan los formatos de las fechas
			Date fechaIni = null;
			try {
				fechaIni = ManipulacionFechas.stringToDate(lstValores.get(6),
						"dd-MMM-yyyy");
				if (fechaIni == null) {
					throw new GWorkException(Util
							.loadErrorMessageValue("ERROR.FORMTOFECHASINI"));
				}
			} catch (Exception e) {
				throw new GWorkException(Util
						.loadErrorMessageValue("ERROR.FORMTOFECHASINI"));
			}

			Date fechaFin = null;
			try {
				fechaFin = ManipulacionFechas.stringToDate(lstValores.get(7),
						"dd-MMM-yyyy");
				if (fechaFin == null) {
					throw new GWorkException(Util
							.loadErrorMessageValue("ERROR.FORMTOFECHASFIN"));
				}

			} catch (Exception e) {
				throw new GWorkException(Util
						.loadErrorMessageValue("ERROR.FORMTOFECHASFIN"));
			}

			// Se valida que las fechas de vigencia del archivo
			// coincidan con las de la poliza que exite en el sistema
			// if (iniPol.compareTo(fechaIni) != 0) {
			// throw new GWorkException(Util
			// .loadErrorMessageValue("ERROR.FECHAINIVIGPOLI"));
			// }

			/*
			 * @author: alvaro.hincapie@geniar.net @date: 15-feb-2010
			 * 
			 * @description: Se cambio este bloque debido a que no siempre van a
			 * coincidir dichas fechas
			 * 
			 * if (finPol.compareTo(fechaFin) != 0) { throw new
			 * GWorkException(Util
			 * .loadErrorMessageValue("ERROR.FECHAFINVIGPOLI")); }
			 */

			/*
			 * if (finPol.after(fechaFin)) { throw new GWorkException(Util
			 * .loadErrorMessageValue("ERROR.FECHAFINMAYORFINPOL")); }
			 */

			// Se valida el formato de la placa
			String placaVehiculo = "";
			try {
				placaVehiculo = lstValores.get(13);
			} catch (Exception e) {
				throw new GWorkException(Util
						.loadErrorMessageValue("ERROR.PLACA"));
			}

			Util.validarLimite(placaVehiculo, 20, 2, "ERROR.LIMPLACA");

			if (placaVehiculo != null && placaVehiculo.trim().length() == 0)
				throw new GWorkException(Util
						.loadErrorMessageValue("ERROR.PLACA"));

			if (!Util.validarPlaca(placaVehiculo)) {
				throw new GWorkException(Util
						.loadErrorMessageValue("ERROR.PLACA"));
			}

			Vehicles vehicle = SearchVehicles
					.consultarVehiculosPorPlacaSinFiltros(placaVehiculo);

			LegateesTypes legateesTypes = null;
			String pvsCarnetAsignatario = null;

			VehiclesAssignation vehiclesAssignation = SearchVehicles
					.consultarAsignacionVehiculo(placaVehiculo);

			if (vehiclesAssignation != null) {
				legateesTypes = vehiclesAssignation.getRequests()
						.getLegateesTypes();
				pvsCarnetAsignatario = vehiclesAssignation.getRequests()
						.getRqsCarnetAsignatario();
			}

			if (legateesTypes == null) {
				legateesTypes = new LegateesTypesDAO().findById(8L);
			}

			legateesTypes = EntityManagerHelper.getEntityManager().merge(
					legateesTypes);

			if (vehicle == null) {
				agregarInconsistenciaLista(pol, TiposInconsis
						.getVHC_NO_EXISTE_BD(), getLogin(), valorIva,
						valorPrima, valorTotal, valorAsegurado, placaVehiculo,
						legateesTypes, pvsCarnetAsignatario);
			}

			LocationsTypes locationsTypes;

			if (vehicle != null) {
				Long estado = vehicle.getVehiclesStates().getVhsCodigo();
				if (estado.longValue() == 2L || estado.longValue() == 5L) {
					agregarInconsistenciaLista(pol, TiposInconsis
							.getVHC_INACTIVO(), getLogin(), valorIva,
							valorPrima, valorTotal, valorAsegurado,
							placaVehiculo, legateesTypes, pvsCarnetAsignatario);
				}

				locationsTypes = vehicle.getLocations().getLocationsTypes();

				List<PoliciesVehicles> pvs = policiesInvoiceService
						.consultarPolizasSOATVehiculo(placaVehiculo);

				if (locationsTypes.getLctCodigo().longValue() != 3L
						&& vehicle.getVehiclesStates().getVhsCodigo() != 5
						&& vehicle.getVehiclesStates().getVhsCodigo() != 2) {
					if (pvs == null || pvs.isEmpty()) {
						agregarInconsistenciaLista(pol, TiposInconsis
								.getVHC_NOSOAT_EXTERIOR(), getLogin(),
								valorIva, valorPrima, valorTotal,
								valorAsegurado, placaVehiculo, legateesTypes,
								pvsCarnetAsignatario);
					} else

					// Si se encuentran soats asociados
					if (pvs != null && pvs.size() > 0) {
						PoliciesVehicles pv = null;
						for (PoliciesVehicles policiesVehicles : pvs) {
							pv = policiesVehicles;
							// Se busca cual es el soat activo
							if (pv.getPvsEstado() == 1) {
								break;
							}
						}
						// Si no se encuentra un soat activo
						if (pv == null) {
							// Se genera una inconsistencia
							agregarInconsistenciaLista(pol, TiposInconsis
									.getVHC_NOSOAT_EXTERIOR(), getLogin(),
									valorIva, valorPrima, valorTotal,
									valorAsegurado, placaVehiculo,
									legateesTypes, pvsCarnetAsignatario);
						}
					}
				}

				Long tipoPolizaId = pol.getPoliciesTypes().getPltCodigo();
				if (locationsTypes.getLctCodigo().longValue() != 1L
						&& tipoPolizaId.longValue() == 12L) {
					agregarInconsistenciaLista(pol, TiposInconsis
							.getVHC_SEDE_POL_BASIC(), getLogin(), valorIva,
							valorPrima, valorTotal, valorAsegurado,
							placaVehiculo, legateesTypes, pvsCarnetAsignatario);
				}

				if (pol.getPlsCodigo().longValue() == 12L) {

					Long codLegatee = legateesTypes.getLgtCodigo();
					if (codLegatee.longValue() == 6L) {
						agregarInconsistenciaLista(pol, TiposInconsis
								.getASIG_PER_AMP_BASIC(), getLogin(), valorIva,
								valorPrima, valorTotal, valorAsegurado,
								placaVehiculo, legateesTypes,
								pvsCarnetAsignatario);
					}
				}

				if (vehicle.getPoliciesVehicleses() != null
						&& !vehicle.getPoliciesVehicleses().isEmpty()
						&& vehicle.getPoliciesVehicleses().size() >= 0) {
					Iterator<PoliciesVehicles> polvehis = vehicle
							.getPoliciesVehicleses().iterator();

					while (polvehis.hasNext()) {
						PoliciesVehicles pv = (PoliciesVehicles) polvehis
								.next();
						System.out.println("Tipo de poliza 1: "
								+ pv.getPolicies().getPoliciesTypes()
										.getPltNombre());

						System.out.println("Tipo de poliza 2: "
								+ pol.getPoliciesTypes().getPltNombre());

						PoliciesTypes k = pv.getPolicies().getPoliciesTypes();

						if (pv.getPvsEstado().longValue() == 1L
								&& pol.getPoliciesTypes().getPltCodigo()
										.longValue() != ParametersUtil.SOAT) {
							if (k.getPltCodigo().longValue() != pol
									.getPoliciesTypes().getPltCodigo()
									.longValue()
									&& k.getPltCodigo().longValue() != ParametersUtil.SOAT) {
								agregarInconsistenciaLista(pol, TiposInconsis
										.getVHC_DOBLE_POL(), getLogin(),
										valorIva, valorPrima, valorTotal,
										valorAsegurado, placaVehiculo,
										legateesTypes, pvsCarnetAsignatario);
							}
						}
					}
				}

				// Se valida el numero de chasis
				String numChasis = null;
				try {
					numChasis = lstValores.get(16);
				} catch (Exception e) {
					throw new GWorkException(Util
							.loadErrorMessageValue("ERROR.DATONOVALIDOAPLANO"));
				}

				if (numChasis == null) {
					throw new GWorkException(Util
							.loadErrorMessageValue("ERROR.VACIO_ARCH_PLANO"));
				}

				if (!numChasis.trim().equals(vehicle.getVhcNumeroSerie())) {
					agregarInconsistenciaLista(pol, TiposInconsis
							.getVHC_NUMERO_SERIAL_DIFERENTE(), getLogin(),
							valorIva, valorPrima, valorTotal, valorAsegurado,
							placaVehiculo, legateesTypes, pvsCarnetAsignatario);
				}

				// Se valida el numero de motor
				String numMotor = null;
				try {
					numMotor = lstValores.get(15);
				} catch (Exception e) {
					throw new GWorkException(Util
							.loadErrorMessageValue("ERROR.DATONOVALIDOAPLANO"));
				}

				if (numMotor == null) {
					throw new GWorkException(Util
							.loadErrorMessageValue("ERROR.VACIO_ARCH_PLANO"));
				}

				if (!numMotor.trim().equals(vehicle.getVhcNumeroMotor())) {
					agregarInconsistenciaLista(pol, TiposInconsis
							.getVHC_NUMERO_MOTOR_DIFERENTE(), getLogin(),
							valorIva, valorPrima, valorTotal, valorAsegurado,
							placaVehiculo, legateesTypes, pvsCarnetAsignatario);
				}

				// // Se valida la linea del vehiculo
				// String lineaArchivo = "";
				// try {
				// lineaArchivo = lstValores.get(14).toUpperCase();
				// } catch (Exception e) {
				// throw new GWorkException(Util
				// .loadErrorMessageValue("ERROR.DATONOVALIDOAPLANO"));
				// }
				// if (lineaArchivo.equals("") || lineaArchivo.length() == 0) {
				// throw new GWorkException(Util
				// .loadErrorMessageValue("ERROR.DATONOVALIDOAPLANO"));
				// }

				// Lines lines = SearchVehicles
				// .consultarLineaPorNombre(lineaArchivo);
				// if (lines == null) {
				// throw new GWorkException(Util
				// .loadErrorMessageValue("ERROR.LINEAVHCARCHPLAN"));
				// }
				//
				// // Se valida que la línea de vehiculo que viene
				// // asociada a un vehiculo en el archivo coincida
				// // con el registro en la bd
				// Long idLinea = vehicle.getLines().getLnsId();
				// if (lines.getLnsId().longValue() != idLinea.longValue()) {
				// throw new GWorkException(Util
				// .loadErrorMessageValue("ERROR.LINEANOTMATCHARCH"));
				// }

				// Se valida la marca del vechiculo
				String marca = "";
				try {
					marca = lstValores.get(11).toUpperCase();
				} catch (Exception e) {
					throw new GWorkException(Util
							.loadErrorMessageValue("ERROR.DATONOVALIDOAPLANO"));
				}
				if (marca.equals("") || marca.length() == 0) {
					throw new GWorkException(Util
							.loadErrorMessageValue("ERROR.DATONOVALIDOAPLANO"));
				}

				if (!Util.validarCadenaCaracteres(marca)) {
					throw new GWorkException(Util
							.loadErrorMessageValue("ERROR.DATONOVALIDOAPLANO"));
				}

				Brands brand = SearchVehicles.consultarMarcaPorNombre(marca);
				if (brand == null) {
					agregarInconsistenciaLista(pol, TiposInconsis
							.getVHC_MARCA_DIFERENTE(), getLogin(), valorIva,
							valorPrima, valorTotal, valorAsegurado,
							placaVehiculo, legateesTypes, pvsCarnetAsignatario);
				}

				// Se valida que la marca del vehiculo coincida con la
				// registrada en el archivo plano
				Long idMarca = vehicle.getLines().getBrands().getBrnId();
				if ((brand == null) || (idMarca != brand.getBrnId())) {
					agregarInconsistenciaLista(pol, TiposInconsis
							.getVHC_MARCA_DIFERENTE(), getLogin(), valorIva,
							valorPrima, valorTotal, valorAsegurado,
							placaVehiculo, legateesTypes, pvsCarnetAsignatario);
				}

				// Se valida el año
				Long anho = null;
				try {
					anho = new Long(lstValores.get(14));
				} catch (Exception e) {
					throw new GWorkException(Util
							.loadErrorMessageValue("ERROR.DATONOVALIDOAPLANO"));
				}

				Util.validarLimite("" + anho, 4, 4, "ERROR.DATONOVALIDOAPLANO");

				if (!Util.validarNumerosConsulta("" + anho)) {
					throw new GWorkException(Util
							.loadErrorMessageValue("ERROR.DATONOVALIDOAPLANO"));
				}

				// Se valida que el año registrado en el archivo para este
				// vehiculo coincide con el que se tiene registrado en la BD
				// para el mismo
				if (vehicle.getVhcAno().longValue() != anho) {
					agregarInconsistenciaLista(pol, TiposInconsis
							.getVHC_AÑO_DIFERENTE(), getLogin(), valorIva,
							valorPrima, valorTotal, valorAsegurado,
							placaVehiculo, legateesTypes, pvsCarnetAsignatario);
				}

				// se valida contra la tabla de parametros para ver si aplica o
				// no la poliza segun en tipo de ubicación del vehiculo y el
				// asignatario correspondiente

				// Se determina cual es la ubicación del vehiculo
				locationsTypes = vehicle.getLocations().getLocationsTypes();

				legateesTypes = null;

				// Se determina el estado del vehiculo
				Long vehicleState = vehicle.getVehiclesStates().getVhsCodigo();

				if (vehicleState.longValue() == 1L || // Asignacion
						vehicleState.longValue() == 7L || // Alquilado
						vehicleState.longValue() == 8L)// Prestado
				{
					Iterator<VehiclesAssignation> assignations = vehicle
							.getVehiclesAssignations().iterator();

					VehiclesAssignation assign = null;

					if (assignations != null) {
						// Se busca cual es la asignacion actual/vigente
						while (assignations.hasNext()) {
							VehiclesAssignation TempAssign = (VehiclesAssignation) assignations
									.next();

							Date fechaActual = new Date();

							// Se valida que la asignacion de turno sea la
							// activa en terminos de las fechas de inicio y fin
							if (fechaActual.after(TempAssign
									.getVhaFechaInicio())
									&& fechaActual.before(TempAssign
											.getVhaFechaTermina())) {// Asignacion
																		// devuelta
								assign = TempAssign;
								break;
							} else {
								/*
								 * agregarInconsistenciaLista(pol, TiposInconsis
								 * .getVHC_ASIGNACION_VENCIDA(), getLogin(),
								 * valorIva, valorPrima, valorTotal,
								 * valorAsegurado, placaVehiculo, legateesTypes,
								 * pvsCarnetAsignatario); break;
								 */
							}

						}

						if (assign != null) {
							// Si se encuentra una asignación
							// se trae el tipo de asignatario
							if (assign.getRequests().getLegateesTypes() != null) {
								legateesTypes = assign.getRequests()
										.getLegateesTypes();

								Long legateeTypeId = legateesTypes
										.getLgtCodigo();
								Long locationTypeId = locationsTypes
										.getLctCodigo();

								// se consulta la tabla de parametros donde se
								// especifica que tipos de seguros aplican segun
								// el tipo de ubicación el tipo de asignatario

								if (locationTypeId != 1L && legateeTypeId >= 5L) {
									List<ControlAssignationPolicies> caps = new SearchPolicyAssignementTypeControl()
											.consultarTodosCAPPorFiltro(
													legateeTypeId,
													locationTypeId);

									if (caps == null || caps.size() == 0) {
										throw new GWorkException(
												Util
														.loadErrorMessageValue("ERROR.CAPTRANSNF"));
									}

									boolean paramAplica = false;

									// Se busca si para el tipo de asignatario y
									// de ubicacion se encuentra registrada el
									// tipo de póliza al que se va a adicionar
									// el vehiculo
									for (ControlAssignationPolicies cap : caps) {
										// id del tipo de poliza que está
										// registrada en el parametro
										Long pltCodigo = cap.getPoliciesTypes()
												.getPltCodigo();

										// id del tipo de poliza que fue
										// seleccionada desde la vista
										Long pltCodigoVista = policiesTypes
												.getPltCodigo();

										if (pltCodigo.longValue() == pltCodigoVista
												.longValue()) {
											paramAplica = true;
										}
									}
									if (!paramAplica) {
										agregarInconsistenciaLista(
												pol,
												TiposInconsis
														.getVHC_POLIZA_DIFERENTE(),
												getLogin(), valorIva,
												valorPrima, valorTotal,
												valorAsegurado, placaVehiculo,
												legateesTypes,
												pvsCarnetAsignatario);
									}
								}
							}
						} else {
							agregarInconsistenciaLista(pol, TiposInconsis
									.getVHC_ASIGNACION_VENCIDA(), getLogin(),
									valorIva, valorPrima, valorTotal,
									valorAsegurado, placaVehiculo,
									legateesTypes, pvsCarnetAsignatario);
						}
					}
				}// El vehiculo no tiene estados que permitan determinar el
				// legatee type

				agregarPoliciesVehicle(pol, vehicle, valorIva, valorPrima,
						valorTotal, valorAsegurado, pvsCarnetAsignatario,
						legateesTypes, fechaIni);
			}

			PoliciesVehicles s = new PoliciesVehicles();
			vehicle = new Vehicles();
			vehicle.setVhcPlacaDiplomatica(placaVehiculo);
			s.setVehicles(vehicle);
			s.setPolicies(pol);
			s.setPvsValorIva(valorIva);
			s.setPvsValorPrima(valorPrima);
			s.setPvsValorTotal(valorTotal);
			s.setPvsValorAsegurado(valorAsegurado);
			s.setPvsCarnetAsignatario(pvsCarnetAsignatario);
			s.setLegateesTypes(legateesTypes);
			vhcsFactura.add(s);

		} catch (GWorkException e) {
			setLstIncons(null);
			setLstPvs(null);
			e.printStackTrace();
			throw new GWorkException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new GWorkException(e.getMessage());
		}
	}

	private void agregarPoliciesVehicle(Policies pol, Vehicles vehicle,
			Float valorIva, Float valorPrima, Float valorTotal,
			Float valorAsegurado, String pvsCarnetAsignatario,
			LegateesTypes legateesTypes, Date fechaIni) throws GWorkException {

		PoliciesVehicles pv = new PoliciesVehicles();
		PoliciesVehiclesId id = new PoliciesVehiclesId();

		id.setPlsCodigo(pol.getPlsCodigo());
		id.setVhcCodigo(vehicle.getVhcCodigo());

		pv.setId(id);

		if (valorTotal.longValue() < 0)
			pv.setMonthTransacType(new SearchMonthlyTransac()
					.consultarMonthTransacType(2L));
		else
			pv.setMonthTransacType(new SearchMonthlyTransac()
					.consultarMonthTransacType(3L));

		pv.setPolicies(pol);
		pv.setPvsEstado(1L);
		pv.setPvsFechafin(pol.getPlsFechafinCobertura());
		pv.setPvsFechaini(fechaIni);
		pv.setPvsNumeroFactura(invoicePolicy.getPinNumeroFactura());
		pv.setPoliciesInvoice(invoicePolicy);
		pv.setPvsValorAsegurado(valorAsegurado);
		pv.setPvsValorIva(valorIva);
		pv.setPvsValorPrima(valorPrima);
		pv.setPvsValorTotal(valorTotal);
		pv.setVehicles(vehicle);
		pv.setLegateesTypes(legateesTypes);
		pv.setPvsCarnetAsignatario(pvsCarnetAsignatario);

		if (lstPvs == null) {
			lstPvs = new ArrayList<PoliciesVehicles>();
		}
		lstPvs.add(pv);

	}

	/**
	 * Agrega una nueva inconsistencia a la lista.
	 * 
	 * @param poliza
	 *            Es la poliza que tiene la inconsistencia.
	 * @param tipoInconsistencia
	 *            Es el tipo de inconsistencia encontrada
	 * @param usrLogin
	 *            Es el usuario que ejecuto la acción
	 * @throws GWorkException
	 *             Manejador de excepciones.
	 */
	public void agregarInconsistenciaLista(Policies poliza,
			Long tipoInconsistencia, String usrLogin, Float iva, Float prima,
			Float total, Float valorAsegurado, String placa,
			LegateesTypes legateesTypes, String pvsCarnet)
			throws GWorkException {
		Inconsistencies inc = new Inconsistencies();
		inc.setIncEstado(1L);
		inc.setIncFechaCargue(new Date());
		inc.setUsrLogin(usrLogin);
		inc.setMonthTransacType(new SearchMonthlyTransac()
				.consultarMonthTransacType(3L));
		inc.setInconsistenciesTypes(inconsistenciesTypesService
				.consultarTipoInconsistenciaPorId(tipoInconsistencia));

		inc.setPoliciesInvoice(invoicePolicy);
		inc.setVhcPlaca(placa);
		inc.setIncValorIva(iva.floatValue());
		inc.setIncValorPrima(prima);
		inc.setIncValorTotal(total);
		inc.setIncValorAsegurado(valorAsegurado);
		inc.setLegateesTypes(legateesTypes);
		inc.setPvsCarnetAsignatario(pvsCarnet);
		inc.setPolicies(poliza);

		lstIncons.add(inc);
	}

	/**
	 * Retorna el login del usuario que esta utilizando la app.
	 */
	public String getLogin() {
		LoginPage loginPage = (LoginPage) FacesUtils
				.getManagedBean("loginPage");
		if (loginPage != null) {
			return loginPage.getLogin();
		} else
			return "";
	}

	/**
	 * Almacena las inconsistencias y asociaciones de vehiculos a poliza.
	 * 
	 * @param actionEvent
	 */
	public void guardarDetalleNovedad(ActionEvent actionEvent) {
		try {
			setOpcConfirmacion(GUARDAR_DETALLE);
			activarConfirmacion = true;
			if (invoicePolicy != null) {
				mostrarMensaje(Util.loadMessageValue("MENSAJE.ALERTA"),
						activarConfirmacion);
			}
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void guardarDetalleNovedad() {
		try {
			policiesInvoiceService.guardarDetalleNovedad(lstIncons, lstPvs,
					invoicePolicy, getLogin(), valorTotalFactura, idTipoPoliza);
			mostrarMensaje(Util.loadMessageValue("EXITO"), false);
			mostrarMensaje(Util.loadMessageValue("CORREGIDAS") + ""
					+ policiesInvoiceService.getSoluciones(), false);
			limpiar();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	// Métodos de utilidad...

	/**
	 * Cambia el formato de un valor moneda a float.
	 */
	public static Float obtenerFloat(String valor) {

		String temp = "";
		valor = valor.replace(',', '.');
		valor = valor.replace('$', ' ');

		for (int i = 0; i < valor.length(); i++) {
			char c = valor.charAt(i);
			if (c != ' ') {
				temp += c;
			}
		}

		return Float.parseFloat(temp);
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
				valores.append(FIN_FILA);
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
	 * Es llamado cada vez que se carga una parte del archivo.
	 */
	public void progresoCargaArchivo(EventObject event) {
		inputFile = (InputFile) event.getSource();
		porcentaje = inputFile.getFileInfo().getPercent();
		try {
			if (estadoCarga != null) {
				estadoCarga.render();
			}
		} catch (RenderingException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	/**
	 * Es llamado cuando el archivo cargado cambia de estado.
	 */
	public void action_archivoEstado(ActionEvent event) {
		try {
			inputFile = (InputFile) event.getSource();
			// Si el archivo se carga exitosamente.
			if (inputFile.getStatus() == InputFile.SAVED) {
				mostrarMensaje(Util.loadMessageValue("EXITO.LOADFILE"), false);
				disableVerDatos = false;
			}

			// Si el archivo no es valido.
			if (inputFile.getStatus() == InputFile.INVALID) {
				throw new GWorkException(inputFile.getFileInfo().getException()
						.getMessage());
			}

			// Si se excede el tamaño limite del archivo.
			if (inputFile.getStatus() == InputFile.SIZE_LIMIT_EXCEEDED) {
				throw new GWorkException(inputFile.getFileInfo().getException()
						.getMessage());
			}

			// Si se desconoce el tamaño del archivo.
			if (inputFile.getStatus() == InputFile.UNKNOWN_SIZE) {
				throw new GWorkException(inputFile.getFileInfo().getException()
						.getMessage());
			}
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	/**
	 * Llama al ManagedBean encargado de desplegar un popUp.
	 */
	public void mostrarMensaje(String mensaje, boolean confirmar) {
		PopupMessagePage pmp = (PopupMessagePage) FacesUtils
				.getManagedBean("popupMessagePage");
		if (pmp != null) {
			pmp.showPopup(mensaje, confirmar);
		}
	}

	public void action_limpiar(ActionEvent event) {
		limpiar();
	}

	private void limpiar() {
		disableVerDatos = true;
		disabledGuardarDetalle = true;
		showTblInconsis = false;
		showTblPvs = false;
		lstIncons = null;
		lstPvs = null;
		showNovedadesSoat = false;
		showNovedadesFA = false;
		showFacturasSOAT = false;
		numeroFacutra = null;
		fechaFactura = null;
		conceptoFactura = null;
		idTipoPoliza = -1L;

	}

	// Métodos accesores...
	/**
	 * @return policiesTransactionsService servicio actual.
	 */
	public PoliciesTransactionsService getPoliciesTransactionsService() {
		return policiesTransactionsService;
	}

	/**
	 * @param policiesTransactionsService
	 *            el nuevo servicio.
	 */
	public void setPoliciesTransactionsService(
			PoliciesTransactionsService policiesTransactionsService) {
		this.policiesTransactionsService = policiesTransactionsService;
	}

	/**
	 * @return inputFile
	 */
	public InputFile getInputFile() {
		return inputFile;
	}

	/**
	 * @param inputFile
	 *            el nuevo inputFile.
	 */
	public void setInputFile(InputFile inputFile) {
		this.inputFile = inputFile;
	}

	/**
	 * @return porcentaje cargado del archivo.
	 */
	public int getPorcentaje() {
		return porcentaje;
	}

	/**
	 * @param porcentaje
	 *            El nuevo porcentaje.
	 */
	public void setPorcentaje(int porcentaje) {
		this.porcentaje = porcentaje;
	}

	/**
	 * @return el estadoCarga.
	 */
	public PersistentFacesState getEstadoCarga() {
		return estadoCarga;
	}

	/**
	 * @param estadoCarga
	 *            El nuevo estado de carga.
	 */
	public void setEstadoCarga(PersistentFacesState estadoCarga) {
		this.estadoCarga = estadoCarga;
	}

	/**
	 * @return true si esta deshabilitado.
	 */
	public boolean isDisableVerDatos() {
		return disableVerDatos;
	}

	/**
	 * @param true
	 *            si se debe deshabilitar.
	 */
	public void setDisableVerDatos(boolean disableVerDatos) {
		this.disableVerDatos = disableVerDatos;
	}

	/**
	 * @return tblIncons Tabla de inconsistencias.
	 */
	public HtmlDataTable getTblIncons() {
		return tblIncons;
	}

	/**
	 * Cambia la tabla de inconsistencias.
	 * 
	 * @param tblIncons
	 *            Tabla de inconsistencias
	 */
	public void setTblIncons(HtmlDataTable tblIncons) {
		this.tblIncons = tblIncons;
	}

	/**
	 * @return tblPvs Tabla de asociaciones de vehiculos-polizas.
	 */
	public HtmlDataTable getTblPvs() {
		return tblPvs;
	}

	/**
	 * @param tblPvs
	 *            Tabla de asociaciones de vehiculos-polizas.
	 */
	public void setTblPvs(HtmlDataTable tblPvs) {
		this.tblPvs = tblPvs;
	}

	/**
	 * @return Listado de asociaciones Vehiculos-Polizas.
	 */
	public List<PoliciesVehicles> getLstPvs() {
		return lstPvs;
	}

	/**
	 * @param lstPvs
	 *            La nueva lista de asociciones vehiculo-poliza.
	 */
	public void setLstPvs(List<PoliciesVehicles> lstPvs) {
		this.lstPvs = lstPvs;
	}

	/**
	 * @return Listado de inconsistencias.
	 */
	public List<Inconsistencies> getLstIncons() {
		return lstIncons;
	}

	/**
	 * @param lstIncons
	 *            La nueva lista de inconsistencias.
	 */
	public void setLstIncons(List<Inconsistencies> lstIncons) {
		this.lstIncons = lstIncons;
	}

	/**
	 * @return the inconsistenciesTypesService
	 */
	public InconsistenciesTypesService getInconsistenciesTypesService() {
		return inconsistenciesTypesService;
	}

	/**
	 * @param inconsistenciesTypesService
	 *            the inconsistenciesTypesService to set
	 */
	public void setInconsistenciesTypesService(
			InconsistenciesTypesService inconsistenciesTypesService) {
		this.inconsistenciesTypesService = inconsistenciesTypesService;
	}

	/**
	 * @return the policiesInvoiceService
	 */
	public PoliciesInvoiceService getPoliciesInvoiceService() {
		return policiesInvoiceService;
	}

	/**
	 * @param policiesInvoiceService
	 *            the policiesInvoiceService to set
	 */
	public void setPoliciesInvoiceService(
			PoliciesInvoiceService policiesInvoiceService) {
		this.policiesInvoiceService = policiesInvoiceService;
	}

	/**
	 * @return the policiesTypeService
	 */
	public PoliciesTypeService getPoliciesTypeService() {
		return policiesTypeService;
	}

	/**
	 * @param policiesTypeService
	 *            the policiesTypeService to set
	 */
	public void setPoliciesTypeService(PoliciesTypeService policiesTypeService) {
		this.policiesTypeService = policiesTypeService;
	}

	/**
	 * @return the invoicePolicy
	 */
	public PoliciesInvoice getInvoicePolicy() {
		return invoicePolicy;
	}

	/**
	 * @param invoicePolicy
	 *            the invoicePolicy to set
	 */
	public void setInvoicePolicy(PoliciesInvoice invoicePolicy) {
		this.invoicePolicy = invoicePolicy;
	}

	/**
	 * @return the policiesService
	 */
	public PoliciesService getPoliciesService() {
		return policiesService;
	}

	/**
	 * @param policiesService
	 *            the policiesService to set
	 */
	public void setPoliciesService(PoliciesService policiesService) {
		this.policiesService = policiesService;
	}

	/**
	 * @return the disabledGuardarDetalle
	 */
	public boolean isDisabledGuardarDetalle() {
		return disabledGuardarDetalle;
	}

	/**
	 * @param disabledGuardarDetalle
	 *            the disabledGuardarDetalle to set
	 */
	public void setDisabledGuardarDetalle(boolean disabledGuardarDetalle) {
		this.disabledGuardarDetalle = disabledGuardarDetalle;
	}

	/**
	 * @return the showTblInconsis
	 */
	public boolean isShowTblInconsis() {
		return showTblInconsis;
	}

	/**
	 * @param showTblInconsis
	 *            the showTblInconsis to set
	 */
	public void setShowTblInconsis(boolean showTblInconsis) {
		this.showTblInconsis = showTblInconsis;
	}

	/**
	 * @return the showTblPvs
	 */
	public boolean isShowTblPvs() {
		return showTblPvs;
	}

	/**
	 * @param showTblPvs
	 *            the showTblPvs to set
	 */
	public void setShowTblPvs(boolean showTblPvs) {
		this.showTblPvs = showTblPvs;
	}

	/**
	 * @return the opcConfirmacion
	 */
	public Integer getOpcConfirmacion() {
		return opcConfirmacion;
	}

	/**
	 * @param opcConfirmacion
	 *            the opcConfirmacion to set
	 */
	public void setOpcConfirmacion(Integer opcConfirmacion) {
		this.opcConfirmacion = opcConfirmacion;
	}

	/**
	 * @return the activarConfirmacion
	 */
	public boolean isActivarConfirmacion() {
		return activarConfirmacion;
	}

	/**
	 * @param activarConfirmacion
	 *            the activarConfirmacion to set
	 */
	public void setActivarConfirmacion(boolean activarConfirmacion) {
		this.activarConfirmacion = activarConfirmacion;
	}

	public Long getValorTotalFactura() {
		return valorTotalFactura;
	}

	public void setValorTotalFactura(Long valorTotalFactura) {
		this.valorTotalFactura = valorTotalFactura;
	}

	/** Evento de la pantalla dependiente de la novedad a registrar */
	public void listener_tipoPolizas(ValueChangeEvent event) {
		Long idTipoPoliza = (Long) event.getNewValue();

		try {

			showNovedadesSoat = false;
			showFacturasSOAT = false;
			showNovedadesFA = false;
			showTblPvs = false;
			showTblInconsis = false;

			if (idTipoPoliza != null
					&& idTipoPoliza.longValue() == ParametersUtil.SOAT) {
				// showNovedadesSoat = true;
				// showFacturasSOAT = true;
				// listaPolicieInvoiceSOAT = policiesInvoiceService
				// .consultarFacturasSOAT();

				showNovedadesFA = true;

			} else if (idTipoPoliza != null && idTipoPoliza != -1L
					&& idTipoPoliza.longValue() != ParametersUtil.SOAT) {
				showNovedadesFA = true;

			} else {

				limpiar();
			}

		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

	}

	public Long getIdTipoPoliza() {
		return idTipoPoliza;
	}

	public void setIdTipoPoliza(Long idTipoPoliza) {
		this.idTipoPoliza = idTipoPoliza;
	}

	public boolean isShowNovedadesSoat() {
		return showNovedadesSoat;
	}

	public void setShowNovedadesSoat(boolean showNovedadesSoat) {
		this.showNovedadesSoat = showNovedadesSoat;
	}

	public boolean isShowNovedadesFA() {
		return showNovedadesFA;
	}

	public void setShowNovedadesFA(boolean showNovedadesFA) {
		this.showNovedadesFA = showNovedadesFA;
	}

	public List<PoliciesInvoice> getListaPolicieInvoiceSOAT() {
		return listaPolicieInvoiceSOAT;
	}

	public void setListaPolicieInvoiceSOAT(
			List<PoliciesInvoice> listaPolicieInvoiceSOAT) {
		this.listaPolicieInvoiceSOAT = listaPolicieInvoiceSOAT;
	}

	/** Carga la factura de soat seleccionada */
	public void action_cargarFacturaSOAT(ActionEvent event) {
		PoliciesInvoice facturaPoliza = (PoliciesInvoice) tblFacturaSOAT
				.getRowData();

		// Carga los datos de la factura para ser mostrados en pantalla
		if (facturaPoliza != null) {

			numeroFacutra = facturaPoliza.getPinNumeroFactura();
			fechaFactura = facturaPoliza.getPinFechaFactura();
			conceptoFactura = facturaPoliza.getPinConcepto();
			showFacturasSOAT = false;
			showNovedadesFA = true;
			lstPvs = new ArrayList<PoliciesVehicles>(facturaPoliza
					.getPoliciesVehicleses());
			showTblPvs = true;
			showTblInconsis = true;

		}

	}

	public HtmlDataTable getTblFacturaSOAT() {
		return tblFacturaSOAT;
	}

	public void setTblFacturaSOAT(HtmlDataTable tblFacturaSOAT) {
		this.tblFacturaSOAT = tblFacturaSOAT;
	}

	public String getNumeroFacutra() {
		return numeroFacutra;
	}

	public void setNumeroFacutra(String numeroFacutra) {
		this.numeroFacutra = numeroFacutra;
	}

	public Date getFechaFactura() {
		return fechaFactura;
	}

	public void setFechaFactura(Date fechaFactura) {
		this.fechaFactura = fechaFactura;
	}

	public String getConceptoFactura() {
		return conceptoFactura;
	}

	public void setConceptoFactura(String conceptoFactura) {
		this.conceptoFactura = conceptoFactura;
	}

	public boolean isShowFacturasSOAT() {
		return showFacturasSOAT;
	}

	public void setShowFacturasSOAT(boolean showFacturasSOAT) {
		this.showFacturasSOAT = showFacturasSOAT;
	}

	public void action_consultarPolizasSOAT(ActionEvent actionEvent) {
		showNovedadesFA = true;

	}

}
