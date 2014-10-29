package geniar.siscar.view.policies;

import geniar.siscar.logic.consultas.SearchMonthlyTransac;
import geniar.siscar.logic.consultas.SearchVehicles;
import geniar.siscar.logic.policies.services.MonthTransacTypeService;
import geniar.siscar.logic.policies.services.PoliciesService;
import geniar.siscar.logic.policies.services.PoliciesTransactionsService;
import geniar.siscar.model.LegateesTypes;
import geniar.siscar.model.Policies;
import geniar.siscar.model.PoliciesTypes;
import geniar.siscar.model.PoliciesVehicles;
import geniar.siscar.model.PoliciesVehiclesId;
import geniar.siscar.model.Vehicles;
import geniar.siscar.model.VehiclesAssignation;
import geniar.siscar.persistence.LegateesTypesDAO;
import geniar.siscar.util.FacesUtils;
import geniar.siscar.util.ManageBeanServices;
import geniar.siscar.util.ManipulacionFechas;
import geniar.siscar.util.ParametersUtil;
import geniar.siscar.util.PopupMessagePage;
import geniar.siscar.util.Util;
import geniar.siscar.view.autenticate.LoginPage;
import gwork.exception.GWorkException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import com.icesoft.faces.component.ext.HtmlCommandButton;
import com.icesoft.faces.component.ext.HtmlDataTable;
import com.icesoft.faces.component.ext.HtmlInputText;

/**
 * 
 * @author usuario
 */
public class PoliciesTransactionsPage {

	private String nombreTipoPoliza;
	private String placaVehiculo;
	private Long plsNumero;
	private Long idTipoPoliza;
	private Long idPoliza;
	/**
	 * 1 Traslado 2 Retiro 3 Adicion
	 */
	private Long idTipoTransacMensual;
	private Float valorAsegurado;
	private Float valorComercial;
	private Date plcFechaInicio;
	private Date plcFechaTerminacion;
	private Date plcFechaInicioPol;
	private Date plcFechaFinPol;
	private Date plcFechaInicioPolVhc;
	private Date plcFechaFinPolVhc;
	private Float plsValorPrima;
	private Float plsValorContribucion;
	private Float plsValorTotal;
	private Float plsValorAseg;
	private String polizaTraslado;

	private List<Policies> listaPolizas;
	private List<PoliciesVehicles> listaPolizasVHC;
	private List<Policies> listaPolizasSys;

	private HtmlInputText txtFechaIni;
	private HtmlInputText txtFechaIniPol;
	private HtmlInputText txtFechaIniPolVhc;
	private HtmlInputText txtFechaFinPolVhc;
	private HtmlInputText txtFechaFinPolizaVhc;
	private HtmlInputText txtFechaFin;
	private HtmlInputText txtNumeroPoliza;
	private HtmlInputText txtPlacaVehiculo;
	private HtmlSelectOneMenu selectTipoTransacMensual;
	private HtmlSelectOneMenu selectSeguro;
	private HtmlDataTable tblPolizas;
	private HtmlDataTable tblPolizasVHC;
	private HtmlDataTable tblPolizasSys;
	private HtmlCommandButton btnBuscarVHC;

	private boolean activarAdicion;
	private boolean activarRetiro;
	private boolean activarTraslado;

	private boolean showTablaPolizas;
	private boolean showTablaPolizasVHC;
	private boolean showTablaPolizasSys;
	private boolean showDetallePoliza;
	private boolean showPaginatorPolizas;
	private boolean showPaginatorPolizasVHC;
	private boolean showPaginatorPolizasSys;
	private boolean showBtnBuscarVHC;
	private boolean showSelectTP;
	private boolean showBtnAdicionar;
	private boolean showBtnTrasladar;
	private boolean showBtnRetirar;
	private boolean showBtnCargarRetirar;
	private boolean showBtnQuitarPol;
	private boolean showFechasNuevas;
	private boolean showFechNuevVHCenPoli;

	private int numeroFilas = 10;
	private int numeroFilasVHC = 10;
	private int numeroFilasPolizasSys = 10;

	private boolean activarConfirmacion;
	private Integer opcConfirmacion;
	private static Integer RETIRAR = 1;
	private static Integer TRASLADAR = 2;

	private Policies polizaRetirada;
	private Policies polizaAsociada;
	private PoliciesVehicles newPolVHC;

	// Servicios...
	private PoliciesService policiesService;
	private MonthTransacTypeService monthTransacTypeService;
	private PoliciesTransactionsService policiesTransactionsService;

	/**
	 * Oculta o despliega determinados campos dependiendo del tipo de
	 * transacción que se ingrese.
	 * 
	 * @param event
	 */
	public void listener_tipoTransaccion(ValueChangeEvent event) {
		if (event.getNewValue() != null) {

			idTipoTransacMensual = new Long("" + event.getNewValue());
			showSelectTP = false;
			activarAdicion = false;
			plsValorAseg = null;
			plsValorContribucion = null;
			plsValorPrima = null;
			plsValorTotal = null;

			if (idTipoTransacMensual.longValue() == 1L) {// Traslado
				showBtnBuscarVHC = true;
				showBtnTrasladar = true;
				showBtnAdicionar = false;
				showBtnRetirar = false;
				showTablaPolizasVHC = false;
				showDetallePoliza = false;
				showFechasNuevas = false;
				showTablaPolizas = false;
				showBtnQuitarPol = true;
				showBtnCargarRetirar = false;
				showTablaPolizasSys = false;
				showPaginatorPolizasSys = false;
				showFechNuevVHCenPoli = true;
				txtFechaIniPolVhc.setValue(null);
				txtFechaFinPolVhc.setValue(null);
				// txtFechaFinPolizaVhc.setValue(null);

			}

			if (idTipoTransacMensual.longValue() == 2L) {// Retiro
				showBtnBuscarVHC = true;
				showSelectTP = false;
				showBtnAdicionar = false;
				showBtnTrasladar = false;
				showDetallePoliza = false;
				showFechasNuevas = false;
				showTablaPolizas = false;
				showBtnQuitarPol = false;
				showBtnRetirar = true;
				showBtnCargarRetirar = true;
				showTablaPolizasSys = false;
				showPaginatorPolizasSys = false;
				showFechNuevVHCenPoli = false;
				txtFechaIniPolVhc.setValue(null);
				txtFechaFinPolVhc.setValue(null);
				txtFechaFinPolizaVhc.setValue(null);
			}

			if (idTipoTransacMensual.longValue() == 3L) {// Adiciones
				showBtnBuscarVHC = false;
				showBtnTrasladar = false;
				showSelectTP = true;
				showBtnAdicionar = true;
				showBtnRetirar = false;
				showBtnTrasladar = false;
				showTablaPolizasVHC = false;
				showTablaPolizasSys = false;
				showPaginatorPolizasSys = false;
				showFechNuevVHCenPoli = false;
				txtFechaIniPolVhc.setValue(null);
				txtFechaFinPolVhc.setValue(null);
				activarAdicion = true;
			}

			if (idTipoTransacMensual.longValue() == -1L) {
				limpiar();
			}
		}
	}

	public void listener_tipoPoliza(ValueChangeEvent event) {
		try {

			showDetallePoliza = false;
			showFechasNuevas = false;

			if (idTipoTransacMensual.longValue() == 3L) {
				showTablaPolizas = false;
				setNumeroFilas(5);
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
					}
				} else {
					showTablaPolizas = false;
					if (listaPolizas != null)
						listaPolizas.clear();
				}
			}
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void action_cargarPoliza(ActionEvent event) {
		try {
			if (tblPolizas != null && listaPolizas != null) {
				if (listaPolizas.size() == 0) {
					showTablaPolizas = false;
					return;
				}
				if (tblPolizas.getRowData() != null) {
					plsNumero = (((Policies) tblPolizas.getRowData())
							.getPlsNumero());
					plcFechaInicio = (((Policies) tblPolizas.getRowData())
							.getPlsFechainicioCobertura());
					plcFechaTerminacion = (((Policies) tblPolizas.getRowData())
							.getPlsFechafinCobertura());
					plcFechaFinPol = (((Policies) tblPolizas.getRowData())
							.getPlsFechafinCobertura());
					plcFechaFinPolVhc = (((Policies) tblPolizas.getRowData())
							.getPlsFechafinCobertura());
					showDetallePoliza = true;
					showFechasNuevas = true;

				}
			}
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void action_aceptar(ActionEvent event) {
		try {
			if (idTipoTransacMensual == null || idTipoTransacMensual == -1)
				throw new GWorkException(Util
						.loadErrorMessageValue("ERROR.SELTTMENSUAL"));

			if (idTipoTransacMensual.longValue() == 3L) {
				if (placaVehiculo != null && placaVehiculo.trim().length() == 0)
					throw new GWorkException(Util
							.loadErrorMessageValue("ERROR.PLACA"));

				if (!Util.validarPlaca(placaVehiculo)) {
					throw new GWorkException(Util
							.loadErrorMessageValue("ERROR.PLACA"));
				}

				Util.validarLimite(placaVehiculo, 20, 2, "ERROR.LIMPLACA");

				if (idTipoPoliza == null || idTipoPoliza == -1)
					throw new GWorkException(Util
							.loadErrorMessageValue("ERROR.SELTIPOPOLI"));

				if (!showDetallePoliza) {
					throw new GWorkException(Util
							.loadErrorMessageValue("ERROR.SELPOLIZATM"));
				}

				if (plsNumero == null)
					throw new GWorkException(Util
							.loadErrorMessageValue("ERROR.LIMNUMPOLTMENS"));

				if (plcFechaInicioPol == null)
					throw new GWorkException(Util
							.loadErrorMessageValue("ERROR.FECHAINIVEHPOLI"));
				ManipulacionFechas.validarAnhoFecha(plcFechaInicioPol, "");

				if (plcFechaInicioPol == null) {
					throw new GWorkException(Util
							.loadErrorMessageValue("ERROR.FECHAINIVEHPOLI"));
				}

				if (plcFechaInicioPol.before(plcFechaInicio)) {
					throw new GWorkException(Util
							.loadErrorMessageValue("ERROR.INICARMENORINIPOL"));
				}

				if (plcFechaInicioPol.after(plcFechaTerminacion)) {
					throw new GWorkException(Util
							.loadErrorMessageValue("ERROR.INICARMAYORFINPOL"));
				}

				// Validar Valores de la poliza
				validarValores();

				// new PoliciesTransactionsServiceImpl().adicionVehiculoPoliza(
				// idTipoTransacMensual, placaVehiculo.toUpperCase(),
				// idTipoPoliza, plcFechaInicioPol, plsNumero, 0F, 0F, 0F,
				// null, getLogin(), null, null, 3L);

				policiesTransactionsService.adicionVehiculoPoliza(
						idTipoTransacMensual, placaVehiculo.toUpperCase(),
						idTipoPoliza, plcFechaInicioPol, plsNumero,
						plsValorPrima, plsValorContribucion, plsValorAseg,
						null, getLogin(), null, null, 3L,
						ParametersUtil.ADICION_POLIZA);

				mostrarMensaje(Util.loadMessageValue("EXITO"), false);
				limpiar();
			}
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	/**
	 * Carga los datos de una póliza de un vehiculo
	 * 
	 * @param event
	 */
	public void action_cargarPolizaRetirar(ActionEvent event) {
		try {
			if (idTipoTransacMensual.longValue() == 2L) {
				if (tblPolizasVHC != null) {
					if (tblPolizasVHC.getRowData() != null) {
						plsNumero = (((PoliciesVehicles) tblPolizasVHC
								.getRowData()).getPolicies().getPlsNumero());
						plcFechaInicio = (((PoliciesVehicles) tblPolizasVHC
								.getRowData()).getPolicies()
								.getPlsFechainicioCobertura());
						plcFechaTerminacion = (((PoliciesVehicles) tblPolizasVHC
								.getRowData()).getPolicies()
								.getPlsFechafinCobertura());
						showDetallePoliza = true;
						showFechasNuevas = false;
					}
				}
			}
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	/**
	 * Activa el mensaje de confirmación para retirar un vehiculo.
	 * 
	 * @param event
	 *            Evento generado por el boton de retirar.
	 */
	public void action_retirarPolizaVHC(ActionEvent event) {
		try {
			if (placaVehiculo != null && placaVehiculo.trim().length() == 0)
				throw new GWorkException(Util
						.loadErrorMessageValue("ERROR.PLACA"));

			if (!Util.validarPlaca(placaVehiculo)) {
				throw new GWorkException(Util
						.loadErrorMessageValue("ERROR.PLACA"));
			}
			Util.validarLimite(placaVehiculo, 20, 2, "ERROR.LIMPLACA");

			if (plsNumero == null)
				throw new GWorkException(Util
						.loadErrorMessageValue("ERROR.SELPOLIRETIRO"));

			setActivarConfirmacion(true);
			setOpcConfirmacion(RETIRAR);
			mostrarMensaje(Util.loadMessageValue("MENSAJE.ALERTA"),
					activarConfirmacion);
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	/**
	 * Se llama cuando el {@link ManageBeanServices} lo requiere.
	 */
	public void retirarVehiculo() {
		try {

			if (placaVehiculo != null && placaVehiculo.trim().length() == 0)
				throw new GWorkException(Util
						.loadErrorMessageValue("ERROR.PLACA"));

			if (!Util.validarPlaca(placaVehiculo)) {
				throw new GWorkException(Util
						.loadErrorMessageValue("ERROR.PLACA"));
			}
			Util.validarLimite(placaVehiculo, 20, 2, "ERROR.LIMPLACA");

			if (plsNumero == null)
				throw new GWorkException(Util
						.loadErrorMessageValue("ERROR.SELPOLIRETIRO"));

			policiesTransactionsService.retiroVehiculoPoliza(plsNumero,
					placaVehiculo, idTipoTransacMensual, getLogin(),
					plcFechaFinPolVhc);
			mostrarMensaje(Util.loadMessageValue("EXITO.RETIRARPOLVHC"), false);
			limpiar();

		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	@SuppressWarnings("unused")
	public void buscarVehiculo(ActionEvent event) {
		try {
			if (idTipoTransacMensual == null || idTipoTransacMensual == -1)
				throw new GWorkException(Util
						.loadErrorMessageValue("ERROR.SELTTMENSUAL"));

			if (placaVehiculo != null && placaVehiculo.trim().length() == 0)
				throw new GWorkException(Util
						.loadErrorMessageValue("ERROR.PLACA"));

			if (!Util.validarPlaca(placaVehiculo)) {
				throw new GWorkException(Util
						.loadErrorMessageValue("ERROR.PLACA"));
			}

			Util.validarLimite(placaVehiculo, 20, 2, "ERROR.LIMPLACA");

			if (idTipoTransacMensual == 2L) {

				if (SearchVehicles
						.consultarVehiculosPorPlacaSinFiltros(placaVehiculo) == null) {
					throw new GWorkException(Util
							.loadErrorMessageValue("PLACA.NOEXISTE"));
				}

				listaPolizasVHC = policiesService
						.consultarPvsVHC(placaVehiculo);

				if (listaPolizasVHC == null || listaPolizasVHC.size() == 0) {
					throw new GWorkException(Util
							.loadErrorMessageValue("ERROR.NOPOLIASOCIADAS"));
				}

				for (PoliciesVehicles pv : listaPolizasVHC) {
					pv.getId();
					PoliciesTypes s = pv.getPolicies().getPoliciesTypes();
					String ntp = pv.getPolicies().getPoliciesTypes()
							.getPltNombre();
				}
				showTablaPolizasVHC = true;
				showPaginatorPolizasVHC = true;
			}

			if (idTipoTransacMensual == 1L)// Traslado de poliza
			{
				listaPolizasVHC = policiesService
						.consultarPvsVHC(placaVehiculo);

				if (listaPolizasVHC == null || listaPolizasVHC.size() == 0) {
					throw new GWorkException(Util
							.loadErrorMessageValue("ERROR.NOPOLIASOCIADAS"));
				}

				for (PoliciesVehicles pv : listaPolizasVHC) {
					pv.getId();
					PoliciesTypes s = pv.getPolicies().getPoliciesTypes();
					String ntp = pv.getPolicies().getPoliciesTypes()
							.getPltNombre();
					idPoliza = pv.getPolicies().getPlsCodigo();
				}

				showTablaPolizasVHC = true;
				showPaginatorPolizasVHC = true;
				showBtnQuitarPol = true;

				listaPolizasSys = policiesService
						.consultarTodasPolizasVigentesNOSOAT();

				if (listaPolizasSys == null || listaPolizasSys.size() == 0)
					throw new GWorkException(Util
							.loadErrorMessageValue("ERROR.POLIZAS.VENCIDAS"));

				for (Policies p : listaPolizasSys) {
					String pltNom = p.getPoliciesTypes().getPltNombre();
				}

				List<Policies> listPolvigentes = new ArrayList<Policies>();

				for (int i = 0; i < listaPolizasVHC.size(); i++) {

					Long numeroPolizaVHC = listaPolizasVHC.get(i).getPolicies()
							.getPlsNumero();

					for (int j = 0; j < listaPolizasSys.size(); j++) {
						Long numPolVigente = listaPolizasSys.get(j)
								.getPlsNumero();

						if (numeroPolizaVHC.longValue() != numPolVigente
								.longValue()) {

							if (!listPolvigentes.contains(listaPolizasSys
									.get(j))) {

								boolean esPolizaDeVHC = false;
								for (int k = 0; k < listaPolizasVHC.size(); k++) {
									Long tempPol = listaPolizasVHC.get(k)
											.getPolicies().getPlsNumero();
									if (numPolVigente.longValue() == tempPol
											.longValue()) {
										esPolizaDeVHC = true;
										break;
									}
								}
								if (!esPolizaDeVHC) {
									listPolvigentes.add(listaPolizasSys.get(j));
								}
							}
						}
					}
				}

				listaPolizasSys = listPolvigentes;

				showTablaPolizasSys = true;
				showPaginatorPolizasSys = true;
			}

		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void action_asociarPolizaVehiculo(ActionEvent event) {
		try {
			if (listaPolizasSys != null && listaPolizasSys.size() > 0) {
				if (tblPolizasSys != null) {

					Policies polizaSistema = (Policies) tblPolizasSys
							.getRowData();

					if (idPoliza.longValue() == polizaSistema.getPlsCodigo()
							.longValue())
						throw new GWorkException(Util
								.loadErrorMessageValue("ERROR.TRASLADO.EXISTE"));

					if (listaPolizasVHC.size() >= 1) {
						throw new GWorkException(Util
								.loadErrorMessageValue("ERROR.REMOVERPRIMERO"));
					}

					// Se busca en la lista de vehiculos que no hayan
					// polizas excluyentes
					for (int i = 0; i < listaPolizasVHC.size(); i++) {

						Policies ps = listaPolizasVHC.get(i).getPolicies();

						// Si polizaSistema es de tipo amparos básicos
						if (polizaSistema.getPoliciesTypes().getPltCodigo() == 1L
								// y la de la lista es full cobertura
								&& ps.getPoliciesTypes().getPltCodigo()
										.longValue() == 2L)
							// no se permite asociar la poliza al vehiculo
							throw new GWorkException(
									Util
											.loadErrorMessageValue("ERROR.POLIZASEXCLUY"));

						// Si polizaSistema es de tipo full cobertura
						if (polizaSistema.getPoliciesTypes().getPltCodigo()
								.longValue() == 2L
								// y la de la lista es full cobertura
								&& ps.getPoliciesTypes().getPltCodigo()
										.longValue() == 1L)
							// no se permite asociar la poliza al vehiculo
							throw new GWorkException(
									Util
											.loadErrorMessageValue("ERROR.POLIZASEXCLUY"));

						// Si el tipo de póliza que se va a asociar al
						// vehiculo
						if (polizaSistema.getPoliciesTypes().getPltCodigo()
								.longValue() ==
						// es del mismo tipo de una que tiene el vehiculo
						ps.getPoliciesTypes().getPltCodigo().longValue()) {
							// No se permite asociar la póliza
							throw new GWorkException(
									Util
											.loadErrorMessageValue("ERROR.POLIGUALNUEVA"));
						}

					}

					Vehicles vehicles = SearchVehicles
							.consultarVehiculosPorPlacaSinFiltros(placaVehiculo);

					VehiclesAssignation m = SearchVehicles
							.consultarAsignacionVehiculo(placaVehiculo);

					LegateesTypes legateesTypes = null;
					String pvsCarnetAsignatario = null;

					if (m != null) {
						legateesTypes = m.getRequests().getLegateesTypes();
						pvsCarnetAsignatario = m.getRequests()
								.getRqsCarnetAsignatario();
					}

					if (legateesTypes == null) {
						legateesTypes = new LegateesTypesDAO().findById(8L);
					}

					polizaAsociada = polizaSistema;
					listaPolizasSys.remove(polizaSistema);

					plcFechaFinPolVhc = polizaAsociada
							.getPlsFechafinCobertura();
					polizaTraslado = polizaAsociada.getPoliciesTypes()
							.getPltNombre();
					plcFechaInicioPolVhc = polizaAsociada
							.getPlsFechainicioCobertura();
					txtFechaIniPolVhc.setValue(plcFechaInicioPolVhc);
					txtFechaFinPolVhc.setValue(plcFechaFinPolVhc);
					showFechasNuevas = true;
					activarAdicion = true;
					plcFechaInicioPol = new Date();
					plcFechaFinPol = polizaAsociada.getPlsFechafinCobertura();

					PoliciesVehicles pv = new PoliciesVehicles();

					pv.setId(new PoliciesVehiclesId(vehicles.getVhcCodigo(),
							polizaSistema.getPlsCodigo()));

					pv.setLegateesTypes(legateesTypes);
					pv.setMonthTransacType(new SearchMonthlyTransac()
							.consultarMonthTransacType(3L));
					pv.setPolicies(polizaSistema);
					pv.setPvsCarnetAsignatario(pvsCarnetAsignatario);
					pv.setPvsEstado(1L);
					pv.setPvsFechafin(plcFechaFinPol);
					pv.setPvsFechaini(plcFechaInicioPol);
					pv.setPvsNumeroFactura(null);
					pv.setPvsValorAsegurado(plsValorAseg);
					pv.setPvsValorIva(plsValorContribucion);
					pv.setPvsValorPrima(plsValorPrima);
					pv.setPvsValorTotal(plsValorTotal);
					pv.setVehicles(vehicles);

					listaPolizasVHC.add(pv);
					newPolVHC = pv;

					// if (polizaRetirada != null) {
					// // si la poliza que se retiro
					// if (polizaRetirada.getPlsNumero().longValue() ==
					// // es la misma que se esta asociando
					// polizaSistema.getPlsNumero().longValue()) {
					// // Se indica que no se ha hecho ningun traslado
					// // de póliza
					//							
					// polizaRetirada = null;
					// polizaAsociada = null;
					// newPolVHC = null;
					// txtFechaIniPolVhc.setValue(null);
					// txtFechaFinPolVhc.setValue(null);
					// throw new GWorkException(
					// "No se ha realizado ningun traslado");
					// }
					// }

				}
			}
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	/**
	 * Retira una póliza específica del vehículo ingresado.
	 * 
	 * @param event
	 */
	public void action_quitarPolizaVehiculo(ActionEvent event) {
		if (listaPolizasVHC != null && listaPolizasVHC.size() > 0) {
			if (tblPolizasVHC != null) {
				// Si no se ha retirado ninguna poliza al vehiculo
				plcFechaInicioPolVhc = null;
				plcFechaFinPolVhc = null;
				polizaTraslado = "";

				PoliciesVehicles pvTabla = (PoliciesVehicles) tblPolizasVHC
						.getRowData();

				// Se indica que ya se selecciono una poliza para retirar

				// Se quita la póliza de la lista de vehiculos
				listaPolizasVHC.remove(pvTabla);

				boolean estaEnListaSistema = false;

				// Se recorre la lista de polizas del sistema
				for (int i = 0; i < listaPolizasSys.size(); i++) {
					Policies ps = listaPolizasSys.get(i);

					// Si el numero de la poliza de la lista del sistema
					// es el mismo que el de la poliza que se retiro...
					if (ps.getPlsNumero() == pvTabla.getPolicies()
							.getPlsNumero()) {
						// quiere decir que la lista de polizas del sistema
						// ya tiene esta póliza
						estaEnListaSistema = true;
					}
				}

				// Si la póliza no esta en la lista de pólizas de sistema
				if (!estaEnListaSistema) {
					// Se añade a la lista
					listaPolizasSys.add(pvTabla.getPolicies());
					// polizaRetirada = pvTabla.getPolicies();
				}
			}
		}
	}

	public void action_trasladar(ActionEvent event) {
		try {
			validarTraslado();
			newPolVHC.setPvsFechaini(plcFechaInicioPol);
			setActivarConfirmacion(true);
			setOpcConfirmacion(TRASLADAR);
			mostrarMensaje(Util.loadMessageValue("MENSAJE.ALERTA"),
					activarConfirmacion);

		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void trasladar() {
		try {
			policiesTransactionsService.trasladoTipoPoliza(
					idTipoTransacMensual, placaVehiculo, plcFechaInicioPolVhc,
					plcFechaFinPolVhc, idPoliza, polizaAsociada, getLogin(),
					newPolVHC, plsValorPrima, plsValorContribucion,
					plsValorTotal, plsValorAseg);
			mostrarMensaje(Util.loadMessageValue("EXITO"), false);
			limpiar();
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	private void validarTraslado() throws GWorkException {

		if (idTipoTransacMensual == null || idTipoTransacMensual == -1)
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.SELTTMENSUAL"));

		if (placaVehiculo != null && placaVehiculo.trim().length() == 0)
			throw new GWorkException(Util.loadErrorMessageValue("ERROR.PLACA"));

		if (!Util.validarPlaca(placaVehiculo))
			throw new GWorkException(Util.loadErrorMessageValue("ERROR.PLACA"));

		Util.validarLimite(placaVehiculo, 20, 2, "ERROR.LIMPLACA");

		if (plcFechaInicioPolVhc == null || plcFechaFinPolVhc == null)
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.NOINDICATRASLADO"));

		if (plcFechaInicioPol == null)
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.FECHAINIVEHPOLI"));

		if (plcFechaInicioPol.before(plcFechaInicioPolVhc))
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.INICARMENORINIPOL"));

		if (plcFechaInicioPol.after(plcFechaFinPolVhc))
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.INICARMAYORFINPOL"));

		plcFechaFinPol = plcFechaFinPolVhc;
		if (!plcFechaFinPol.after(plcFechaInicioPol))
			throw new GWorkException(Util.loadErrorMessageValue("ERROR.FECHAS"));

		// Validar valores de la poliza de traslado
		validarValores();

		ManipulacionFechas.validarAnhoFecha(plcFechaInicioPolVhc, "");
		ManipulacionFechas.validarAnhoFecha(plcFechaFinPolVhc, "");
		ManipulacionFechas.validarAnhoFecha(plcFechaInicioPol, "");
		ManipulacionFechas.validarAnhoFecha(plcFechaFinPol, "");
	}

	void validarValores() throws GWorkException {

		if (plsValorPrima == null || plsValorPrima.doubleValue() == 0.0)
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.VALPRIMAINGR"));

		if (plsValorContribucion == null
				|| plsValorContribucion.doubleValue() == 0.0)
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.VALIVAINGR"));

		if (plsValorTotal == null || plsValorTotal.doubleValue() == 0.0)
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.VALATOTINGR"));

	}

	public void action_limpiarForma(ActionEvent event) {
		limpiar();
	}

	private void limpiar() {
		txtNumeroPoliza.setValue("");
		placaVehiculo = null;
		txtFechaIni.setValue(null);
		txtFechaIni = null;
		txtFechaFin.setValue(null);
		txtFechaIniPol.setValue(null);
		plcFechaInicioPol = null;
		plcFechaFinPol = null;
		txtFechaIniPolVhc.setValue(null);
		txtFechaFinPolVhc.setValue(null);
		txtFechaFinPolizaVhc.setValue(null);
		txtFechaFinPolVhc = null;
		listaPolizas = null;
		selectTipoTransacMensual.setValue(-1);
		selectSeguro.setValue(-1);
		txtPlacaVehiculo.setValue(null);
		showDetallePoliza = false;
		showFechasNuevas = false;
		showPaginatorPolizas = false;
		showTablaPolizas = false;
		showBtnBuscarVHC = false;
		showBtnAdicionar = false;
		showBtnRetirar = false;
		showBtnTrasladar = false;
		showTablaPolizasVHC = false;
		showPaginatorPolizasVHC = false;
		showSelectTP = false;
		showTablaPolizasSys = false;
		showPaginatorPolizasSys = false;
		showFechNuevVHCenPoli = false;
		plsValorAseg = null;
		plsValorContribucion = null;
		plsValorPrima = null;
		plsValorTotal = null;
		activarAdicion = false;
		idPoliza = null;
		polizaTraslado = null;
	}

	public void mostrarMensaje(String mensaje, boolean buttonCancel) {
		PopupMessagePage message = (PopupMessagePage) FacesUtils
				.getManagedBean("popupMessagePage");
		if (message != null)
			message.showPopup(mensaje, buttonCancel);
	}

	public Long getIdTipoTransacMensual() {
		return idTipoTransacMensual;
	}

	public void setIdTipoTransacMensual(Long idTipoTransacMensual) {
		this.idTipoTransacMensual = idTipoTransacMensual;
	}

	public HtmlSelectOneMenu getSelectTipoTransacMensual() {
		return selectTipoTransacMensual;
	}

	public void setSelectTipoTransacMensual(
			HtmlSelectOneMenu selectTipoTransacMensual) {
		this.selectTipoTransacMensual = selectTipoTransacMensual;
	}

	public HtmlInputText getTxtPlacaVehiculo() {
		return txtPlacaVehiculo;
	}

	public void setTxtPlacaVehiculo(HtmlInputText txtPlacaVehiculo) {
		this.txtPlacaVehiculo = txtPlacaVehiculo;
	}

	public String getPlacaVehiculo() {
		return placaVehiculo;
	}

	public void setPlacaVehiculo(String placaVehiculo) {
		this.placaVehiculo = placaVehiculo.toUpperCase();
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

	public boolean isShowTablaPolizas() {
		return showTablaPolizas;
	}

	public void setShowTablaPolizas(boolean showTablaPolizas) {
		this.showTablaPolizas = showTablaPolizas;
	}

	public PoliciesService getPoliciesService() {
		return policiesService;
	}

	public void setPoliciesService(PoliciesService policiesService) {
		this.policiesService = policiesService;
	}

	public List<Policies> getListaPolizas() {
		return listaPolizas;
	}

	public void setListaPolizas(List<Policies> listaPolizas) {
		this.listaPolizas = listaPolizas;
	}

	public boolean isActivarAdicion() {
		return activarAdicion;
	}

	public void setActivarAdicion(boolean activarAdicion) {
		this.activarAdicion = activarAdicion;
	}

	public boolean isActivarRetiro() {
		return activarRetiro;
	}

	public void setActivarRetiro(boolean activarRetiro) {
		this.activarRetiro = activarRetiro;
	}

	public boolean isActivarTraslado() {
		return activarTraslado;
	}

	public void setActivarTraslado(boolean activarTraslado) {
		this.activarTraslado = activarTraslado;
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

	public boolean isShowDetallePoliza() {
		return showDetallePoliza;
	}

	public void setShowDetallePoliza(boolean showDetallePoliza) {
		this.showDetallePoliza = showDetallePoliza;
	}

	public Long getPlsNumero() {
		return plsNumero;
	}

	public void setPlsNumero(Long plsNumero) {
		this.plsNumero = plsNumero;
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

	public Float getValorAsegurado() {
		return valorAsegurado;
	}

	public void setValorAsegurado(Float valorAsegurado) {
		this.valorAsegurado = valorAsegurado;
	}

	public Float getValorComercial() {
		return valorComercial;
	}

	public void setValorComercial(Float valorComercial) {
		this.valorComercial = valorComercial;
	}

	public MonthTransacTypeService getMonthTransacTypeService() {
		return monthTransacTypeService;
	}

	public void setMonthTransacTypeService(
			MonthTransacTypeService monthTransacTypeService) {
		this.monthTransacTypeService = monthTransacTypeService;
	}

	public Date getPlcFechaInicioPol() {
		return plcFechaInicioPol;
	}

	public void setPlcFechaInicioPol(Date plcFechaInicioPol) {
		this.plcFechaInicioPol = plcFechaInicioPol;
	}

	public HtmlInputText getTxtFechaIniPol() {
		return txtFechaIniPol;
	}

	public void setTxtFechaIniPol(HtmlInputText txtFechaIniPol) {
		this.txtFechaIniPol = txtFechaIniPol;
	}

	public Date getPlcFechaFinPol() {
		return plcFechaFinPol;
	}

	public void setPlcFechaFinPol(Date plcFechaFinPol) {
		this.plcFechaFinPol = plcFechaFinPol;
	}

	public PoliciesTransactionsService getPoliciesTransactionsService() {
		return policiesTransactionsService;
	}

	public void setPoliciesTransactionsService(
			PoliciesTransactionsService policiesTransactionsService) {
		this.policiesTransactionsService = policiesTransactionsService;
	}

	public HtmlCommandButton getBtnBuscarVHC() {
		return btnBuscarVHC;
	}

	public void setBtnBuscarVHC(HtmlCommandButton btnBuscarVHC) {
		this.btnBuscarVHC = btnBuscarVHC;
	}

	public boolean isShowBtnBuscarVHC() {
		return showBtnBuscarVHC;
	}

	public void setShowBtnBuscarVHC(boolean showBtnBuscarVHC) {
		this.showBtnBuscarVHC = showBtnBuscarVHC;
	}

	public List<PoliciesVehicles> getListaPolizasVHC() {
		return listaPolizasVHC;
	}

	public void setListaPolizasVHC(List<PoliciesVehicles> listaPolizasVHC) {
		this.listaPolizasVHC = listaPolizasVHC;
	}

	public boolean isShowTablaPolizasVHC() {
		return showTablaPolizasVHC;
	}

	public void setShowTablaPolizasVHC(boolean showTablaPolizasVHC) {
		this.showTablaPolizasVHC = showTablaPolizasVHC;
	}

	public HtmlDataTable getTblPolizasVHC() {
		return tblPolizasVHC;
	}

	public void setTblPolizasVHC(HtmlDataTable tblPolizasVHC) {
		this.tblPolizasVHC = tblPolizasVHC;
	}

	public int getNumeroFilasVHC() {
		return numeroFilasVHC;
	}

	public void setNumeroFilasVHC(int numeroFilasVHC) {
		this.numeroFilasVHC = numeroFilasVHC;
	}

	public boolean isShowPaginatorPolizasVHC() {
		return showPaginatorPolizasVHC;
	}

	public void setShowPaginatorPolizasVHC(boolean showPaginatorPolizasVHC) {
		this.showPaginatorPolizasVHC = showPaginatorPolizasVHC;
	}

	public boolean isShowSelectTP() {
		return showSelectTP;
	}

	public void setShowSelectTP(boolean showSelectTP) {
		this.showSelectTP = showSelectTP;
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

	public boolean isShowBtnAdicionar() {
		return showBtnAdicionar;
	}

	public void setShowBtnAdicionar(boolean showBtnAdicionar) {
		this.showBtnAdicionar = showBtnAdicionar;
	}

	public boolean isShowBtnTrasladar() {
		return showBtnTrasladar;
	}

	public void setShowBtnTrasladar(boolean showBtnTrasladar) {
		this.showBtnTrasladar = showBtnTrasladar;
	}

	public List<Policies> getListaPolizasSys() {
		return listaPolizasSys;
	}

	public void setListaPolizasSys(List<Policies> listaPolizasSys) {
		this.listaPolizasSys = listaPolizasSys;
	}

	public HtmlDataTable getTblPolizasSys() {
		return tblPolizasSys;
	}

	public void setTblPolizasSys(HtmlDataTable tblPolizasSys) {
		this.tblPolizasSys = tblPolizasSys;
	}

	public boolean isShowTablaPolizasSys() {
		return showTablaPolizasSys;
	}

	public void setShowTablaPolizasSys(boolean showTablaPolizasSys) {
		this.showTablaPolizasSys = showTablaPolizasSys;
	}

	public int getNumeroFilasPolizasSys() {
		return numeroFilasPolizasSys;
	}

	public void setNumeroFilasPolizasSys(int numeroFilasPolizasSys) {
		this.numeroFilasPolizasSys = numeroFilasPolizasSys;
	}

	public boolean isShowPaginatorPolizasSys() {
		return showPaginatorPolizasSys;
	}

	public void setShowPaginatorPolizasSys(boolean showPaginatorPolizasSys) {
		this.showPaginatorPolizasSys = showPaginatorPolizasSys;
	}

	public boolean isShowBtnQuitarPol() {
		return showBtnQuitarPol;
	}

	public void setShowBtnQuitarPol(boolean showBtnQuitarPol) {
		this.showBtnQuitarPol = showBtnQuitarPol;
	}

	public boolean isShowBtnRetirar() {
		return showBtnRetirar;
	}

	public void setShowBtnRetirar(boolean showBtnRetirar) {
		this.showBtnRetirar = showBtnRetirar;
	}

	public boolean isShowBtnCargarRetirar() {
		return showBtnCargarRetirar;
	}

	public void setShowBtnCargarRetirar(boolean showBtnCargarRetirar) {
		this.showBtnCargarRetirar = showBtnCargarRetirar;
	}

	public boolean isShowFechasNuevas() {
		return showFechasNuevas;
	}

	public void setShowFechasNuevas(boolean showFechasNuevas) {
		this.showFechasNuevas = showFechasNuevas;
	}

	public HtmlInputText getTxtFechaIniPolVhc() {
		return txtFechaIniPolVhc;
	}

	public void setTxtFechaIniPolVhc(HtmlInputText txtFechaIniPolVhc) {
		this.txtFechaIniPolVhc = txtFechaIniPolVhc;
	}

	public HtmlInputText getTxtFechaFinPolVhc() {
		return txtFechaFinPolVhc;
	}

	public void setTxtFechaFinPolVhc(HtmlInputText txtFechaFinPolVhc) {
		this.txtFechaFinPolVhc = txtFechaFinPolVhc;
	}

	public Date getPlcFechaInicioPolVhc() {
		return plcFechaInicioPolVhc;
	}

	public void setPlcFechaInicioPolVhc(Date plcFechaInicioPolVhc) {
		this.plcFechaInicioPolVhc = plcFechaInicioPolVhc;
	}

	public Date getPlcFechaFinPolVhc() {
		return plcFechaFinPolVhc;
	}

	public void setPlcFechaFinPolVhc(Date plcFechaFinPolVhc) {
		this.plcFechaFinPolVhc = plcFechaFinPolVhc;
	}

	public boolean isShowFechNuevVHCenPoli() {
		return showFechNuevVHCenPoli;
	}

	public void setShowFechNuevVHCenPoli(boolean showFechNuevVHCenPoli) {
		this.showFechNuevVHCenPoli = showFechNuevVHCenPoli;
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

	public Float getPlsValorAseg() {
		return plsValorAseg;
	}

	public void setPlsValorAseg(Float plsValorAseg) {
		this.plsValorAseg = plsValorAseg;
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
	}

	public void listener_valorPrima(ValueChangeEvent changeEvent) {
		if (changeEvent.getNewValue() != null) {
			setPlsValorPrima(Util.convertirDecimal(
					changeEvent.getNewValue().toString()).floatValue());
			actualizarTotal();
		}
	}

	public String getPolizaTraslado() {
		return polizaTraslado;
	}

	public void setPolizaTraslado(String polizaTraslado) {
		this.polizaTraslado = polizaTraslado;
	}

	public HtmlInputText getTxtFechaFinPolizaVhc() {
		return txtFechaFinPolizaVhc;
	}

	public void setTxtFechaFinPolizaVhc(HtmlInputText txtFechaFinPolizaVhc) {
		this.txtFechaFinPolizaVhc = txtFechaFinPolizaVhc;
	}
}
