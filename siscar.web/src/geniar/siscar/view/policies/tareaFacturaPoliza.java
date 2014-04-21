package geniar.siscar.view.policies;

import geniar.siscar.logic.consultas.SearchMonthlyTransac;
import geniar.siscar.logic.consultas.SearchVehicles;
import geniar.siscar.logic.policies.services.impl.InconsistenciesTypeServiceImpl;
import geniar.siscar.logic.policies.services.impl.PoliciesInvoiceServiceImpl;
import geniar.siscar.logic.policies.services.impl.PoliciesServiceImpl;
import geniar.siscar.logic.policies.services.impl.PoliciesTypesServiceImpl;
import geniar.siscar.logic.policies.services.impl.TiposInconsis;
import geniar.siscar.model.Brands;
import geniar.siscar.model.Inconsistencies;
import geniar.siscar.model.LegateesTypes;
import geniar.siscar.model.Policies;
import geniar.siscar.model.PoliciesInvoice;
import geniar.siscar.model.PoliciesTypes;
import geniar.siscar.model.PoliciesVehicles;
import geniar.siscar.model.PoliciesVehiclesId;
import geniar.siscar.model.Vehicles;
import geniar.siscar.model.VehiclesAssignation;
import geniar.siscar.persistence.LegateesTypesDAO;
import geniar.siscar.util.FacesUtils;
import geniar.siscar.util.ManipulacionFechas;
import geniar.siscar.util.PopupMessagePage;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class tareaFacturaPoliza extends TimerTask {
	private Timer timer = null;
	private static List<Inconsistencies> listaInconsistencias = null;
	private static List<PoliciesVehicles> listaPoliVehicles = null;
	private static PoliciesInvoice invoicePolicy = null;
	private static String Login = null;
	private static Long valorTotalFactura = 0L;
	private List<PoliciesVehicles> vhcsFactura;
	private static char SEPARADOR;
	private static char FIN_FILA;
	private Integer numeroFilas;
	private static Long numeroPoliza = null;
	private static StringBuffer datosArchivo = null;
	private static final Log log = LogFactory.getLog(tareaFacturaPoliza.class);

	/**
	 * Constructor de la Tarea
	 * 
	 * @FechaCreacion (dd-mm-aaaa) 27-07-2009
	 * @param datosArchivo,
	 * @param numeroPoliza,
	 * @param invoicePolicy,
	 * @param Login,
	 */
	public tareaFacturaPoliza(StringBuffer datosArchivo, Long numeroPoliza,
			PoliciesInvoice invoicePolicy, String login) {
		try {
			setTimer(new Timer());
			setDatosArchivo(datosArchivo);
			setNumeroPoliza(numeroPoliza);
			setInvoicePolicy(invoicePolicy);
			setLogin(login);
			SEPARADOR = Util.loadMessageValue("CARACTER_SEPARADOR_COLS")
					.charAt(0);
			FIN_FILA = Util.loadMessageValue("CARACTER_FIN_LINEA").charAt(0);

		} catch (NumberFormatException e) {
			log.error(e);
		} catch (Exception e) {
			log.error(e);
		}
	}

	// Constructor sin parametros
	public tareaFacturaPoliza() {

	}

	/**
	 * Metodo para terminar el hilo de la tarea
	 * 
	 * @author Diego Humberto Bocanegra
	 * @FechaCreacion (dd-mm-aaaa) 27-07-2009
	 */
	public void killTarea() {
		super.cancel();
	}

	/**
	 * Metodo abstracto implementado de la super clase TimerTask para la
	 * ejecucion de la Tarea
	 * 
	 * @author Diego Humberto Bocanegra
	 * @FechaCreacion (dd-mm-aaaa) 27-07-2009
	 */
	public void run() {
		try {
			Date start = new Date();
			log
					.info("--- Corriendo Proceso Automatico Verificar Archivo Plano Factura "
							+ start + "----");

			obtenerDatosArchivo(datosArchivo);

			new PoliciesInvoiceServiceImpl().guardarDetalle(
					listaInconsistencias, listaPoliVehicles, invoicePolicy,
					Login, valorTotalFactura);

			Date end = new Date();
			log
					.info("--- Termina Proceso Automatico Verificar Archivo Plano Factura "
							+ start + "----");
			log.info(end.getTime() - start.getTime()
					+ " total milisegundos en realizar tarea automatica ");

		} catch (Exception e) {
			log
			.error("Error RUN Tarea Automatica de notificacion fecha de terminacion: "
					+ e.getMessage(), e);
			mostrarMensaje(e.getMessage(), false);
		}
	}

	public void obtenerDatosArchivo(StringBuffer datosArchivo)
			throws GWorkException {

		listaInconsistencias = new ArrayList<Inconsistencies>();
		listaPoliVehicles = new ArrayList<PoliciesVehicles>();
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

		setNumeroFilas(numeroFilas);
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
				valor = "";
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
				e.printStackTrace();
				throw new GWorkException(e.getMessage(), e);
			}
		}

		// controla las placas que ya se han revisado
		List<String> listaControl = new ArrayList<String>();

		// Se determinan que vehículos estan repetidos en la factura
		for (int i = 0; i < vhcsFactura.size(); i++) {

			// Se toma una de las placas
			PoliciesVehicles data = vhcsFactura.get(i);

			String placa = "";
			if (data.getVehicles() != null) {
				placa = (data.getVehicles().getVhcPlacaDiplomatica());
			}

			if (!listaControl.contains(placa)) {
				// Sino esta en la lista de control
				listaControl.add(placa);// Se agrega la placa que se va a
				// revisar
				int cuentaVHC = 0;// guarda cuantos vehiculos se
				// encontraron el factura
				for (int j = 0; j < vhcsFactura.size(); j++) {
					PoliciesVehicles data2 = vhcsFactura.get(j);

					String placa2 = "";
					if (data2.getVehicles() != null) {
						placa2 = (data2.getVehicles().getVhcPlacaDiplomatica());
					}

					if (placa.equals(placa2)) {
						cuentaVHC++;
					}
				}
			}
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
			Long Linea = Long.parseLong(lstValores.get(6));
			Integer numeroPolizaArchivo = null;

			// Se valida el formato del numero de póliza
			try {
				numeroPolizaArchivo = Integer.parseInt(lstValores.get(2));
			} catch (RuntimeException e) {
				new PoliciesInvoiceServiceImpl()
						.enviarMailErrorArchivoPlano(Util
								.loadErrorMessageValue(
										"ERROR.DATONOVALIDOAPLANO").toString()
								+ " - Numero Poliza - Linea " + Linea);
				throw new GWorkException(Util
						.loadErrorMessageValue("ERROR.DATONOVALIDOAPLANO"));
			}

			Policies pol = new PoliciesServiceImpl().consultarPoliza(new Long(
					"" + numeroPolizaArchivo));

			if (pol == null) {
				new PoliciesInvoiceServiceImpl()
						.enviarMailErrorArchivoPlano(Util
								.loadErrorMessageValue("ERROR.POLIZANEXISTE")
								.toString()
								+ " - Linea " + Linea);
				throw new GWorkException(Util
						.loadErrorMessageValue("ERROR.POLIZANEXISTE"));
			}

			if (numeroPolizaArchivo.longValue() != numeroPoliza.longValue()) {
				new PoliciesInvoiceServiceImpl()
						.enviarMailErrorArchivoPlano(Util
								.loadErrorMessageValue(
										"ERROR.NUMPOLNOTMATCHENC").toString()
								+ " - Linea " + Linea);
				throw new GWorkException(Util
						.loadErrorMessageValue("ERROR.NUMPOLNOTMATCHENC"));
			}

			// Se valida el valor prima
			BigDecimal valorPrima = null;
			try {
				valorPrima = obtenerFloat(lstValores.get(15));
				valorTotalFactura += valorPrima.longValue();
			} catch (Exception e) {
				new PoliciesInvoiceServiceImpl()
						.enviarMailErrorArchivoPlano(Util
								.loadErrorMessageValue(
										"ERROR.DATONOVALIDOAPLANO").toString()
								+ " - Valor Prima - Linea " + Linea);
				throw new GWorkException(Util
						.loadErrorMessageValue("ERROR.DATONOVALIDOAPLANO"));
			}

			if (valorPrima == null) {
				new PoliciesInvoiceServiceImpl()
						.enviarMailErrorArchivoPlano(Util
								.loadErrorMessageValue(
										"ERROR.DATONOVALIDOAPLANO").toString()
								+ " - Valor Prima  - Linea " + Linea);
				throw new GWorkException(Util
						.loadErrorMessageValue("ERROR.DATONOVALIDOAPLANO"));
			}

			// Se valida el valor prima
			BigDecimal valorAsegurado = null;
			try {
				valorAsegurado = obtenerFloat(lstValores.get(7));
			} catch (Exception e) {
				new PoliciesInvoiceServiceImpl()
						.enviarMailErrorArchivoPlano(Util
								.loadErrorMessageValue(
										"ERROR.DATONOVALIDOAPLANO").toString()
								+ " - Valor Asegurado - Linea " + Linea);
				throw new GWorkException(Util
						.loadErrorMessageValue("ERROR.DATONOVALIDOAPLANO"));
			}

			if (valorAsegurado == null) {
				new PoliciesInvoiceServiceImpl()
						.enviarMailErrorArchivoPlano(Util
								.loadErrorMessageValue(
										"ERROR.DATONOVALIDOAPLANO").toString()
								+ " - Valor Asegurado - Linea " + Linea);
				throw new GWorkException(Util
						.loadErrorMessageValue("ERROR.DATONOVALIDOAPLANO"));
			}

			// Se valida el valor iva
			BigDecimal valorIva = null;
			try {
				valorIva = obtenerFloat(lstValores.get(16));
			} catch (Exception e) {
				new PoliciesInvoiceServiceImpl()
						.enviarMailErrorArchivoPlano(Util
								.loadErrorMessageValue(
										"ERROR.DATONOVALIDOAPLANO").toString()
								+ " - Valor Contribución - Linea " + Linea);
				throw new GWorkException(Util
						.loadErrorMessageValue("ERROR.DATONOVALIDOAPLANO"));
			}

			if (valorIva == null) {
				new PoliciesInvoiceServiceImpl()
						.enviarMailErrorArchivoPlano(Util
								.loadErrorMessageValue(
										"ERROR.DATONOVALIDOAPLANO").toString()
								+ " - Valor Contribución - Linea " + Linea);
				throw new GWorkException(Util
						.loadErrorMessageValue("ERROR.DATONOVALIDOAPLANO"));
			}

			// Se valida el valor total
			BigDecimal valorTotal = null;
			try {
				valorTotal = obtenerFloat(lstValores.get(17));
			} catch (Exception e) {
				new PoliciesInvoiceServiceImpl()
						.enviarMailErrorArchivoPlano(Util
								.loadErrorMessageValue(
										"ERROR.DATONOVALIDOAPLANO").toString()
								+ " - Valor Total Linea - Linea " + Linea);
				throw new GWorkException(Util
						.loadErrorMessageValue("ERROR.DATONOVALIDOAPLANO"));
			}

			if (valorTotal == null) {
				new PoliciesInvoiceServiceImpl()
						.enviarMailErrorArchivoPlano(Util
								.loadErrorMessageValue(
										"ERROR.DATONOVALIDOAPLANO").toString()
								+ " - Valor Total Linea - Linea " + Linea);
				throw new GWorkException(Util
						.loadErrorMessageValue("ERROR.DATONOVALIDOAPLANO"));
			}

			// Se valida que la suma del archivo
			if ((valorIva.add(valorPrima)).doubleValue() != valorTotal.doubleValue()) {
				new PoliciesInvoiceServiceImpl()
						.enviarMailErrorArchivoPlano(Util
								.loadErrorMessageValue("ERROR.SUMTOTALLINEA")
								.toString()
								+ " - Linea " + Linea + ". Valor Iva=" + valorIva.doubleValue() + 
									". Valor Prima = " + valorPrima.doubleValue() + ". " + "Valor Total = " + valorTotal.doubleValue());
				throw new GWorkException(Util
						.loadErrorMessageValue("ERROR.SUMTOTALLINEA"));
			}

			// Se valida el tipo de poliza que viene desde el archivo
			String tipoPoliza = "";

			try {
				tipoPoliza = lstValores.get(3).toUpperCase();
			} catch (RuntimeException e1) {
				new PoliciesInvoiceServiceImpl()
						.enviarMailErrorArchivoPlano(Util
								.loadErrorMessageValue("ERROR.TIPOPOLNOTVALID")
								.toString()
								+ " - Linea " + Linea);
				throw new GWorkException(Util
						.loadErrorMessageValue("ERROR.TIPOPOLNOTVALID"));
			}

			if (tipoPoliza == null || tipoPoliza.trim().length() == 0) {
				new PoliciesInvoiceServiceImpl()
						.enviarMailErrorArchivoPlano(Util
								.loadErrorMessageValue("ERROR.TIPOPOLNOTVALID")
								.toString()
								+ " - Linea " + Linea);
				throw new GWorkException(Util
						.loadErrorMessageValue("ERROR.TIPOPOLNOTVALID"));
			}

			if (!Util.validarCadenaCaracteres(tipoPoliza)) {
				new PoliciesInvoiceServiceImpl()
						.enviarMailErrorArchivoPlano(Util
								.loadErrorMessageValue("ERROR.TIPOPOLNOTVALID")
								.toString()
								+ " - Linea " + Linea);
				throw new GWorkException(Util
						.loadErrorMessageValue("ERROR.TIPOPOLNOTVALID"));
			}

			PoliciesTypes policiesTypes = new PoliciesTypesServiceImpl()
					.consultarTipoPolizaPorNombre(tipoPoliza);

			if (policiesTypes == null) {
				new PoliciesInvoiceServiceImpl()
						.enviarMailErrorArchivoPlano(Util
								.loadErrorMessageValue("ERROR.TIPOPOLIZANOF")
								.toString()
								+ " - Linea " + Linea);
				throw new GWorkException(Util
						.loadErrorMessageValue("ERROR.TIPOPOLIZANOF"));
			}

			if (!policiesTypes.getPltNombre().equals(tipoPoliza)) {
				new PoliciesInvoiceServiceImpl()
						.enviarMailErrorArchivoPlano(Util
								.loadErrorMessageValue("ERROR.TIPOPOLNOTMATCH")
								.toString()
								+ " - Linea " + Linea);
				throw new GWorkException(Util
						.loadErrorMessageValue("ERROR.TIPOPOLNOTMATCH"));
			}

			// Se validan los formatos de las fechas

			SimpleDateFormat dateFormat = new SimpleDateFormat();
			dateFormat.applyPattern("dd-MMM-yy");

			Date fechaIni = null;
			try {
				fechaIni = ManipulacionFechas.stringToDate(lstValores.get(4),
						"dd-MMM-yy");
			} catch (Exception e) {
				new PoliciesInvoiceServiceImpl()
						.enviarMailErrorArchivoPlano(Util
								.loadErrorMessageValue("ERROR.FORMTOFECHASINI")
								.toString()
								+ " - Linea " + Linea);
				throw new GWorkException(Util
						.loadErrorMessageValue("ERROR.FORMTOFECHASINI"));
			}

			if (fechaIni == null) {
				new PoliciesInvoiceServiceImpl()
						.enviarMailErrorArchivoPlano(Util
								.loadErrorMessageValue("ERROR.FORMTOFECHASINI")
								.toString()
								+ ". Recuerde, el formato CIAT es dd-MMM-yyyy"
								+ " - Linea " + Linea);
				throw new GWorkException(Util
						.loadErrorMessageValue("ERROR.FORMTOFECHASINI"));
			}

			Date fechaFin = null;
			try {
				fechaFin = ManipulacionFechas.stringToDate(lstValores.get(5),
						"dd-MMM-yyyy");

			} catch (Exception e) {
				new PoliciesInvoiceServiceImpl()
						.enviarMailErrorArchivoPlano(Util
								.loadErrorMessageValue("ERROR.FORMTOFECHASFIN")
								.toString()
								+ " - Linea " + Linea);
				throw new GWorkException(Util
						.loadErrorMessageValue("ERROR.FORMTOFECHASFIN"));
			}

			if (fechaFin == null) {
				new PoliciesInvoiceServiceImpl()
						.enviarMailErrorArchivoPlano(Util
								.loadErrorMessageValue("ERROR.FORMTOFECHASFIN")
								.toString()
								+ ". Recuerde, el formato CIAT es dd-MMM-yyyy"
								+ " - Linea " + Linea);
				throw new GWorkException(Util
						.loadErrorMessageValue("ERROR.FORMTOFECHASFIN"));
			}

			String FechaTemp = ManipulacionFechas.dateToString(pol
					.getPlsFechainicioCobertura());

			Date iniPol = ManipulacionFechas.stringToDate(FechaTemp);

			FechaTemp = ManipulacionFechas.dateToString(pol
					.getPlsFechafinCobertura());
			Date finPol = ManipulacionFechas.stringToDate(FechaTemp);

			// Se valida que las fechas de vigencia del archivo
			// coincidan con las de la poliza que exite en el sistema
			if (iniPol.compareTo(fechaIni) != 0) {
				new PoliciesInvoiceServiceImpl()
						.enviarMailErrorArchivoPlano(Util
								.loadErrorMessageValue("ERROR.FECHAINIVIGPOLI")
								.toString()
								+ " - Linea " + Linea);
				throw new GWorkException(Util
						.loadErrorMessageValue("ERROR.FECHAINIVIGPOLI"));
			}

			if (finPol.compareTo(fechaFin) != 0) {
				new PoliciesInvoiceServiceImpl()
						.enviarMailErrorArchivoPlano(Util
								.loadErrorMessageValue("ERROR.FECHAFINVIGPOLI")
								.toString()
								+ " - Linea " + Linea);
				throw new GWorkException(Util
						.loadErrorMessageValue("ERROR.FECHAFINVIGPOLI"));
			}

			// Se valida el formato de la placa
			String placaVehiculo = "";
			try {
				placaVehiculo = lstValores.get(11);
			} catch (Exception e) {
				new PoliciesInvoiceServiceImpl()
						.enviarMailErrorArchivoPlano(Util
								.loadErrorMessageValue("ERROR.PLACA")
								.toString()
								+ " - Linea " + Linea);
				throw new GWorkException(Util
						.loadErrorMessageValue("ERROR.PLACA"));
			}

			Util.validarLimite(placaVehiculo, 20, 2, "ERROR.LIMPLACA");

			if (placaVehiculo == null || placaVehiculo.trim().length() == 0
					|| placaVehiculo.equals("")) {
				new PoliciesInvoiceServiceImpl()
						.enviarMailErrorArchivoPlano(Util
								.loadErrorMessageValue("ERROR.PLACA")
								.toString()
								+ " - Linea " + Linea);
				throw new GWorkException(Util
						.loadErrorMessageValue("ERROR.PLACA"));
			}

			if (!Util.validarPlaca(placaVehiculo)) {
				new PoliciesInvoiceServiceImpl()
						.enviarMailErrorArchivoPlano(Util
								.loadErrorMessageValue("ERROR.PLACA")
								.toString()
								+ " - Linea " + Linea);
				throw new GWorkException(Util
						.loadErrorMessageValue("ERROR.PLACA"));
			}

			Vehicles vehicle = SearchVehicles
					.consultarVehiculosPorPlacaSinFiltros(placaVehiculo);

			LegateesTypes legateesTypes = null;
			String pvsCarnetAsignatario = null;

			VehiclesAssignation m = SearchVehicles
					.consultarAsignacionVehiculo(placaVehiculo);

			if (m != null) {
				legateesTypes = m.getRequests().getLegateesTypes();
				pvsCarnetAsignatario = m.getRequests()
						.getRqsCarnetAsignatario();
			}

			if (legateesTypes == null) {
				legateesTypes = new LegateesTypesDAO().findById(8L);
			}

			if (vehicle == null) {
				agregarInconsistenciaLista(pol, TiposInconsis
						.getVHC_NO_EXISTE_BD(), getLogin(), valorIva.floatValue(),
						valorPrima.floatValue(), valorTotal.floatValue(), valorAsegurado.floatValue(), placaVehiculo,
						legateesTypes, null);
			}

			if (vehicle != null) {
				if (vehicle.getPoliciesVehicleses() != null
						&& !vehicle.getPoliciesVehicleses().isEmpty()) {
					Iterator<PoliciesVehicles> polvehis = vehicle
							.getPoliciesVehicleses().iterator();

					while (polvehis.hasNext()) {
						PoliciesVehicles pv = (PoliciesVehicles) polvehis
								.next();
						PoliciesTypes k = pv.getPolicies().getPoliciesTypes();
						if (pv.getPvsEstado().longValue() == 1L) {
							if (k.getPltCodigo().longValue() == pol
									.getPoliciesTypes().getPltCodigo()
									.longValue()) {

								new PoliciesInvoiceServiceImpl()
										.enviarMailErrorArchivoPlano("El vehiculo "
												+ vehicle
														.getVhcPlacaDiplomatica()
												+ " ya esta incluido en una poliza - Linea "
												+ Linea);
								throw new GWorkException("El vehiculo "
										+ vehicle.getVhcPlacaDiplomatica()
										+ " ya esta incluido en una poliza - Linea " + Linea);
							}
						}
					}
				}

				// Se valida el numero de chasis
				String numSerie = null;
				try {
					numSerie = lstValores.get(14);
				} catch (Exception e) {
					new PoliciesInvoiceServiceImpl()
							.enviarMailErrorArchivoPlano(Util
									.loadErrorMessageValue(
											"ERROR.DATONOVALIDOAPLANO")
									.toString()
									+ " - Numero Serie Vehiculo "
									+ placaVehiculo + " - Linea " + Linea);
					throw new GWorkException(Util
							.loadErrorMessageValue("ERROR.DATONOVALIDOAPLANO"));
				}

				if (numSerie == null) {
					new PoliciesInvoiceServiceImpl()
							.enviarMailErrorArchivoPlano(Util
									.loadErrorMessageValue(
											"ERROR.VACIO_ARCH_PLANO")
									.toString()
									+ " - Numero Serie Vehiculo "
									+ placaVehiculo + " - Linea " + Linea);
					throw new GWorkException(Util
							.loadErrorMessageValue("ERROR.VACIO_ARCH_PLANO"));
				}

				if (!numSerie.equals(vehicle.getVhcNumeroSerie())) {
					agregarInconsistenciaLista(pol, TiposInconsis
							.getVHC_NUMERO_SERIAL_DIFERENTE(), getLogin(),
							valorIva.floatValue(), valorPrima.floatValue(), valorTotal.floatValue(), valorAsegurado.floatValue(),
							placaVehiculo, legateesTypes, pvsCarnetAsignatario);
				}

				// Se valida el numero de motor
				String numMotor = null;
				try {
					numMotor = lstValores.get(13);
				} catch (Exception e) {
					new PoliciesInvoiceServiceImpl()
							.enviarMailErrorArchivoPlano(Util
									.loadErrorMessageValue(
											"ERROR.DATONOVALIDOAPLANO")
									.toString()
									+ " - Numero Motor Vehiculo "
									+ placaVehiculo + " - Linea " + Linea);
					throw new GWorkException(Util
							.loadErrorMessageValue("ERROR.DATONOVALIDOAPLANO"));
				}

				if (numMotor == null) {
					new PoliciesInvoiceServiceImpl()
							.enviarMailErrorArchivoPlano(Util
									.loadErrorMessageValue(
											"ERROR.VACIO_ARCH_PLANO")
									.toString()
									+ " - Numero Motor Vehiculo "
									+ placaVehiculo + " - Linea " + Linea);
					throw new GWorkException(Util
							.loadErrorMessageValue("ERROR.VACIO_ARCH_PLANO"));
				}

				if (!numMotor.equals(vehicle.getVhcNumeroMotor())) {
					agregarInconsistenciaLista(pol, TiposInconsis
							.getVHC_NUMERO_MOTOR_DIFERENTE(), getLogin(),
							valorIva.floatValue(), valorPrima.floatValue(), valorTotal.floatValue(), valorAsegurado.floatValue(),
							placaVehiculo, legateesTypes, pvsCarnetAsignatario);
				}

				// Se valida la marca del vechiculo
				String marca = "";
				try {
					marca = lstValores.get(9).toUpperCase();
				} catch (Exception e) {
					new PoliciesInvoiceServiceImpl()
							.enviarMailErrorArchivoPlano(Util
									.loadErrorMessageValue(
											"ERROR.DATONOVALIDOAPLANO")
									.toString()
									+ " - Marca Vehiculo "
									+ placaVehiculo
									+ " - Linea " + Linea);
					throw new GWorkException(Util
							.loadErrorMessageValue("ERROR.DATONOVALIDOAPLANO"));
				}

				if (marca.equals("") || marca.length() == 0) {
					new PoliciesInvoiceServiceImpl()
							.enviarMailErrorArchivoPlano(Util
									.loadErrorMessageValue(
											"ERROR.VACIO_ARCH_PLANO")
									.toString()
									+ " - Marca Vehiculo "
									+ placaVehiculo
									+ " - Linea " + Linea);
					throw new GWorkException(Util
							.loadErrorMessageValue("ERROR.VACIO_ARCH_PLANO"));
				}

				if (!Util.validarCadenaCaracteres(marca)) {
					new PoliciesInvoiceServiceImpl()
							.enviarMailErrorArchivoPlano(Util
									.loadErrorMessageValue(
											"ERROR.DATONOVALIDOAPLANO")
									.toString()
									+ " - Marca Vehiculo "
									+ placaVehiculo
									+ " - Linea " + Linea);
					throw new GWorkException(Util
							.loadErrorMessageValue("ERROR.DATONOVALIDOAPLANO"));
				}

				Brands brand = SearchVehicles.consultarMarcaPorNombre(marca);
				if (brand == null) {
					new PoliciesInvoiceServiceImpl()
							.enviarMailErrorArchivoPlano(Util
									.loadErrorMessageValue(
											"ERROR.MARCANOTFOUNDARCH")
									.toString()
									+ " - Marca Vehiculo "
									+ placaVehiculo
									+ " - Linea " + Linea);

					throw new GWorkException(Util
							.loadErrorMessageValue("ERROR.MARCANOTFOUNDARCH")
							+ " Placa " + placaVehiculo);
				}

				// Se valida que la marca del vehiculo coincida con la
				// registrada en el archivo plano
				Long idMarca = vehicle.getLines().getBrands().getBrnId();
				if (idMarca != brand.getBrnId()) {
					agregarInconsistenciaLista(pol, TiposInconsis
							.getVHC_MARCA_DIFERENTE(), getLogin(), valorIva.floatValue(),
							valorPrima.floatValue(), valorTotal.floatValue(), valorAsegurado.floatValue(),
							placaVehiculo, legateesTypes, pvsCarnetAsignatario);
				}

				// Se valida el año
				Long anho = null;
				try {
					anho = new Long(lstValores.get(12));
				} catch (Exception e) {
					new PoliciesInvoiceServiceImpl()
							.enviarMailErrorArchivoPlano(Util
									.loadErrorMessageValue(
											"ERROR.DATONOVALIDOAPLANO")
									.toString()
									+ " - Modelo Vehiculo "
									+ placaVehiculo
									+ " - Linea " + Linea);
					throw new GWorkException(Util
							.loadErrorMessageValue("ERROR.DATONOVALIDOAPLANO"));
				}

				Util.validarLimite("" + anho, 4, 4, "ERROR.DATONOVALIDOAPLANO");

				if (!Util.validarNumerosConsulta("" + anho)) {
					new PoliciesInvoiceServiceImpl()
							.enviarMailErrorArchivoPlano(Util
									.loadErrorMessageValue(
											"ERROR.DATONOVALIDOAPLANO")
									.toString()
									+ " - Modelo Vehiculo "
									+ placaVehiculo
									+ " - Linea " + Linea);
					throw new GWorkException(Util
							.loadErrorMessageValue("ERROR.DATONOVALIDOAPLANO"));
				}
				// Se valida que el año registrado en el archivo para este
				// vehiculo coincide con el que se tiene registrado en la BD
				// para el mismo
				if (vehicle.getVhcAno().longValue() != anho) {
					agregarInconsistenciaLista(pol, TiposInconsis
							.getVHC_AÑO_DIFERENTE(), getLogin(), valorIva.floatValue(),
							valorPrima.floatValue(), valorTotal.floatValue(), valorAsegurado.floatValue(),
							placaVehiculo, legateesTypes, pvsCarnetAsignatario);

				}
				agregarPoliciesVehicle(pol, vehicle, valorIva.floatValue(), valorPrima.floatValue(),
						valorTotal.floatValue(), valorAsegurado.floatValue(), pvsCarnetAsignatario,
						legateesTypes);
			}

			PoliciesVehicles s = new PoliciesVehicles();
			vehicle = new Vehicles();
			vehicle.setVhcPlacaDiplomatica(placaVehiculo);
			s.setVehicles(vehicle);
			s.setPolicies(pol);
			s.setPvsValorIva(valorIva.floatValue());
			s.setPvsValorPrima(valorPrima.floatValue());
			s.setPvsValorTotal(valorTotal.floatValue());
			s.setPvsValorAsegurado(valorAsegurado.floatValue());
			s.setLegateesTypes(legateesTypes);
			s.setPvsCarnetAsignatario(pvsCarnetAsignatario);
			vhcsFactura.add(s);
		} catch (GWorkException e) {
			e.printStackTrace();
			throw new GWorkException(e.getMessage(), e); 
		}
	}

	public static BigDecimal obtenerFloat(String valor) {

		String temp = "";
		valor = valor.replace(',', '.');
		valor = valor.replace('$', ' ');

		for (int i = 0; i < valor.length(); i++) {
			char c = valor.charAt(i);
			if (c != ' ') {
				temp += c;
			}
		}

		return new BigDecimal(temp);
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
		inc.setInconsistenciesTypes(new InconsistenciesTypeServiceImpl()
				.consultarTipoInconsistenciaPorId(tipoInconsistencia));

		inc.setPoliciesInvoice(invoicePolicy);
		inc.setVhcPlaca(placa);
		inc.setIncValorIva(iva.floatValue());
		inc.setIncValorPrima(prima);
		inc.setIncValorTotal(total);
		inc.setIncValorAsegurado(valorAsegurado);
		inc.setLegateesTypes(legateesTypes);
		inc.setPvsCarnetAsignatario(pvsCarnet);

		listaInconsistencias.add(inc);
	}

	private void agregarPoliciesVehicle(Policies pol, Vehicles vehicle,
			Float valorIva, Float valorPrima, Float valorTotal,
			Float valorAsegurado, String pvsCarnetAsignatario,
			LegateesTypes legateesTypes) throws GWorkException {

		PoliciesVehicles pv = new PoliciesVehicles();
		PoliciesVehiclesId id = new PoliciesVehiclesId();

		id.setPlsCodigo(pol.getPlsCodigo());
		id.setVhcCodigo(vehicle.getVhcCodigo());

		pv.setId(id);
		pv.setMonthTransacType(new SearchMonthlyTransac()
				.consultarMonthTransacType(3L));

		pv.setPolicies(pol);
		pv.setPvsEstado(1L);
		pv.setPvsFechafin(pol.getPlsFechafinCobertura());
		pv.setPvsFechaini(new Date());
		pv.setPvsNumeroFactura(invoicePolicy.getPinNumeroFactura());
		pv.setPvsValorAsegurado(valorAsegurado);
		pv.setPvsValorIva(valorIva);
		pv.setPvsValorPrima(valorPrima);
		pv.setPvsValorTotal(valorTotal);
		pv.setVehicles(vehicle);
		pv.setLegateesTypes(legateesTypes);
		pv.setPvsCarnetAsignatario(pvsCarnetAsignatario);

		if (listaPoliVehicles == null) {
			listaPoliVehicles = new ArrayList<PoliciesVehicles>();
		}
		listaPoliVehicles.add(pv);

		System.out.println("ingreso:" + listaPoliVehicles.size());
	}

	/**
	 * Metodos de Acceso
	 */

	public Timer getTimer() {
		return timer;
	}

	public void setTimer(Timer timer) {
		this.timer = timer;
	}

	/**
	 * @return the listaInconsistencias
	 */
	public static List<Inconsistencies> getListaInconsistencias() {
		return listaInconsistencias;
	}

	/**
	 * @return the listaPoliVehicles
	 */
	public static List<PoliciesVehicles> getListaPoliVehicles() {
		return listaPoliVehicles;
	}

	/**
	 * @return the login
	 */
	public static String getLogin() {
		return Login;
	}

	/**
	 * @return the valorTotalFactura
	 */
	public static Long getValorTotalFactura() {
		return valorTotalFactura;
	}

	/**
	 * @return the invoicePolicy
	 */
	public static PoliciesInvoice getInvoicePolicy() {
		return invoicePolicy;
	}

	/**
	 * @param invoicePolicy
	 *            the invoicePolicy to set
	 */
	public static void setInvoicePolicy(PoliciesInvoice invoicePolicy) {
		tareaFacturaPoliza.invoicePolicy = invoicePolicy;
	}

	/**
	 * @param listaInconsistencias
	 *            the listaInconsistencias to set
	 */
	public static void setListaInconsistencias(
			List<Inconsistencies> listaInconsistencias) {
		tareaFacturaPoliza.listaInconsistencias = listaInconsistencias;
	}

	/**
	 * @param listaPoliVehicles
	 *            the listaPoliVehicles to set
	 */
	public static void setListaPoliVehicles(
			List<PoliciesVehicles> listaPoliVehicles) {
		tareaFacturaPoliza.listaPoliVehicles = listaPoliVehicles;
	}

	/**
	 * @param login
	 *            the login to set
	 */
	public static void setLogin(String login) {
		Login = login;
	}

	/**
	 * @param valorTotalFactura
	 *            the valorTotalFactura to set
	 */
	public static void setValorTotalFactura(Long valorTotalFactura) {
		tareaFacturaPoliza.valorTotalFactura = valorTotalFactura;
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
	 * @return the numeroPoliza
	 */
	public static Long getNumeroPoliza() {
		return numeroPoliza;
	}

	/**
	 * @param numeroPoliza
	 *            the numeroPoliza to set
	 */
	public static void setNumeroPoliza(Long numeroPoliza) {
		tareaFacturaPoliza.numeroPoliza = numeroPoliza;
	}

	/**
	 * @return the datosArchivo
	 */
	public static StringBuffer getDatosArchivo() {
		return datosArchivo;
	}

	/**
	 * @param datosArchivo
	 *            the datosArchivo to set
	 */
	public static void setDatosArchivo(StringBuffer datosArchivo) {
		tareaFacturaPoliza.datosArchivo = datosArchivo;
	}

	public void mostrarMensaje(String mensaje, boolean confirmar) {
		try{
			PopupMessagePage pmp = (PopupMessagePage) FacesUtils
					.getManagedBean("popupMessagePage");
			if (pmp != null) {
				pmp.showPopup(mensaje, confirmar);
			}
		}catch(Exception e){
			log.error(e.getMessage(), e);
		}
	}
}