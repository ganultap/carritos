package geniar.siscar.view.vehicle;

import geniar.siscar.consults.ConsultsService;
import geniar.siscar.logic.consultas.SearchCostCenters;
import geniar.siscar.logic.consultas.SearchNewness;
import geniar.siscar.logic.consultas.SearchVehicles;
import geniar.siscar.logic.fuels.services.impl.ServiceRegistryImp;
import geniar.siscar.logic.vehicle.services.VehicleService;
import geniar.siscar.model.ActualOthersApplications;
import geniar.siscar.model.Cities;
import geniar.siscar.model.ClientsSalesVehicles;
import geniar.siscar.model.CostsCenters;
import geniar.siscar.model.CostsCentersVehicles;
import geniar.siscar.model.Locations;
import geniar.siscar.model.LocationsNewness;
import geniar.siscar.model.Models;
import geniar.siscar.model.PrepaidConsumption;
import geniar.siscar.model.RetirementsReasons;
import geniar.siscar.model.ServiceRegistry;
import geniar.siscar.model.SupplyingCatalogs;
import geniar.siscar.model.Users;
import geniar.siscar.model.VOAssignation;
import geniar.siscar.model.VONovedadCambioUbicacion;
import geniar.siscar.model.VONovedadCentroCostos;
import geniar.siscar.model.VORegistryService;
import geniar.siscar.model.Vehicles;
import geniar.siscar.model.VehiclesAssignation;
import geniar.siscar.persistence.CostsCentersDAO;
import geniar.siscar.persistence.ModelsDAO;
import geniar.siscar.persistence.SupplyingCatalogsDAO;
import geniar.siscar.util.FacesUtils;
import geniar.siscar.util.PopupMessagePage;
import geniar.siscar.util.Util;
import geniar.siscar.view.BaseBean;
import geniar.siscar.view.parameters.SelectItemCountryPage;
import geniar.siscar.view.parameters.SelectItemSupplyingCatalogsPage;
import gwork.exception.GWorkException;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.faces.event.ActionEvent;

import com.icesoft.faces.component.ext.HtmlOutputText;
import com.icesoft.faces.component.ext.RowSelectorEvent;

public class ConsultVehiclePage extends BaseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long idTipoDetalle;

	private String vhcPlacaDiplomatica;
	private String vhcPlacaActivoFijo;
	private String vhcNumeroTl;
	private Long idBrand;
	private Long idCountry;
	private Long idLocation;
	private Long idLocationType;

	private Long idNumCatalogo;
	private Long idModelo;
	private String numCatalogo;
	private String vhcCatalogado;
	private String vhcNumeroMotor;
	private String vhcNumeroSerie;
	private Long idCuidad;
	private String vhcAno;
	private String vhcColor;
	private String vhcCapacidad;
	private String vhcOdometro;
	private String vhcValorComercial;
	private String vhcAnoValCom;
	private String vhcNumeroManifiesto;
	private String vhcNumDeclImpor;
	private String vhcRemplazaA;
	private String vhcRemplazaPor;
	private String vhcNumeroLevante;
	private String vhcDocumentTrans;
	private String vhcNumeroFactura;
	private String vhcOrderCompra;
	private String vhcProveedor;
	private String vhcAtInicial;
	private String vhcCiuAduan;
	private String vhcObservaciones;
	private String lineaCatalogo;
	private String modeloCatalogo;
	private String tipoVehiculo;
	private String tipoTapiceria;
	private String tipoTrasmision;
	private String tipoCombustible;
	private String tipoUbicacion;
	private String ubicacion;
	private String fechaProtocolo;
	private String fechaManifiesto;
	private String fechaLevante;
	private String marcaCatalogo;
	private Float vhcCargosImportacion;
	private String tipoTraccion;
	private String vhcClilindraje;
	private String vhcVidaUtil;
	private Float vhcValorCIF;
	private Float vhcValorFOB;
	private String vhcAnofabricacion;
	private String vhcMesfabricacion;
	private Long vhcPais;
	private boolean locationVisible;

	// Datos para consulta por asignatario
	private String carneAsignatario;
	private String nombreAsignatario;
	private boolean showAsignatioByCode;
	private boolean showFilterEmployee;
	private String parametroBusqueda;
	private List<Users> listUsers;
	private HtmlOutputText idUser;
	private HtmlOutputText idAsignacion;
	private List<VehiclesAssignation> listAsignacionesUser;

	private String placa;
	private String countryName;

	// datos asignacion
	String estadoVehiculo;
	String estadoAsignacion;
	String asignatario;
	String tipoAsignacion;
	String fechaInicioSolicitud;
	String fechaFinSolicitud;
	String zona;

	String fechaUltimaTanqueada;
	String kilometrajeUltimaTanqueada;

	// Service
	private VehicleService vehicleService;

	private ConsultsService consultService;

	private List<Vehicles> listVehicles;

	private String centrosCostos;
	private String centrosCostosCombustible;

	// vehiculos asignados
	public boolean showVehiculosAsignados = false;
	private List<VOAssignation> listVehiclesAssignation = new ArrayList<VOAssignation>();
	private Long vhaCodigo;
	private Date fechaInicio;
	private Date fechaTerminacion;
	private Date fechaEntrega;
	private Date fechaDevolucion;
	private String observacionAsignacion;
	private Long kilometrajeEntrega;
	private Long kilometrajeDevolucion;
	private String observacionDevolucion;
	private String carnet;
	private String asignatarioVehiculo;
	private String centrosCostosAsignaciones;

	// novedad de centros de costos
	public boolean showNovedadCentroCostos = false;
	private List<VONovedadCentroCostos> listVONovedadCentroCosto = new ArrayList<VONovedadCentroCostos>();
	private Long centroCostoNovedad;

	// Registro de Combustible
	public boolean showRegistroServicios = false;
	private SearchVehicles serVhc;
	public List<VORegistryService> listRegService;

	// Registro de vehiculo retirado
	public boolean showVehiculoRetirado = false;
	// private SearchVehicles serVhc;
	private String nombreComprador;
	private String numeroIdentificacion;
	private String direccion;
	private String telefono;
	private String valorATfinal;
	// private kilometrajeVenta;
	private String placaIntra;
	private String precioVenta;
	private Date fechaEntregaVenta;
	private Long numeroLicitacion;
	private String observaciones;

	private String motivoRetiro;
	private Date rerFechaRetiro;
	private String rerDescripcion;

	// novedad de cambio de ubicacion
	public boolean showNovedadCambioUbicacion = false;
	private List<VONovedadCambioUbicacion> listVONovedadCambioUbicacion;
	private Long cambioUbicacionNovedad;

	private ConsultsService consultsService;

	public boolean isShowNovedadCentroCostos() {
		return showNovedadCentroCostos;
	}

	public void setShowNovedadCentroCostos(boolean showNovedadCentroCostos) {
		this.showNovedadCentroCostos = showNovedadCentroCostos;
	}

	public List<VONovedadCentroCostos> getListVONovedadCentroCosto() {
		return listVONovedadCentroCosto;
	}

	public void setListVONovedadCentroCosto(
			List<VONovedadCentroCostos> listVONovedadCentroCosto) {
		this.listVONovedadCentroCosto = listVONovedadCentroCosto;
	}

	public void validarCamposVehiculo(String vhcPlacaDiplomatica)
			throws GWorkException {

		boolean esValido = true;
		if (vhcPlacaDiplomatica != null
				&& vhcPlacaDiplomatica.trim().length() == 0)
			throw new GWorkException(Util.loadErrorMessageValue("PLACA"));

		if (vhcPlacaDiplomatica != null)
			esValido = Util.validarPlaca(vhcPlacaDiplomatica);

		if (!esValido)
			throw new GWorkException(Util
					.loadErrorMessageValue("PLACA.INCORRECTO"));

		if (vhcPlacaDiplomatica != null
				&& vhcPlacaDiplomatica.trim().length() < 2)
			throw new GWorkException(Util.loadErrorMessageValue("PLACA.MINIMO"));
	}

	public void limpiarDatosSessionConsulta() {
		if (FacesUtils.getSession().getAttribute("placaDiplomatica") != null)
			FacesUtils.getSession().removeAttribute("placaDiplomatica");
	}

	/**
	 * Metodo para consultar el tipo de ubicacion ciudad y pais de un vehiculo
	 * 
	 * @param evento
	 */
	public void action_consultar(ActionEvent event) {
		consultar();
	}

	public void consultar() {
		try {
			boolean esValido = true;
			limpiarDatosSessionConsulta();
			Cities cities = null;
			SupplyingCatalogs catalogs = null;
			String tempPlaca = placa;
			limpiar();
			placa = tempPlaca;
			if (placa.equals("") && placa.trim().length() == 0)
				throw new GWorkException(Util.loadErrorMessageValue("PLACA"));
			if (placa != null)
				esValido = Util.validarPlaca(placa);

			if (!esValido)
				throw new GWorkException(Util
						.loadErrorMessageValue("PLACA.INCORRECTO"));

			if (placa != null && placa.trim().length() != 0
					&& placa.trim().length() < 2 || placa.trim().length() > 15)
				throw new GWorkException(Util
						.loadErrorMessageValue("PLACA.MINIMO"));

			Vehicles vehicles = SearchVehicles
					.consultarVehiculosPorPlacaSinFiltros(placa.toUpperCase()
							.trim());
			if (vehicles != null) {
				if (vehicles.getVhcPlacaDiplomatica() != null) {
					vhcPlacaDiplomatica = vehicles.getVhcPlacaDiplomatica();
				}

				if (vehicles.getVhcPlacaActivoFijo() != null) {
					vhcPlacaActivoFijo = vehicles.getVhcPlacaActivoFijo();
				}

				if (vehicles.getVhcModelo() != null) {
					List<Models> listModels = new ModelsDAO()
							.findByMdlNombre(vehicles.getVhcModelo());
					Models models = null;
					if (listModels != null && listModels.size() > 0) {
						models = listModels.get(0);
						SelectItemSupplyingCatalogsPage catalogsPage = (SelectItemSupplyingCatalogsPage) FacesUtils
								.getManagedBean("selectItemSuppliyngPage");
						if (catalogsPage != null)
							catalogsPage.setSetSupplyingCatalogs(SearchVehicles
									.consultarCatalogoPorIdModelo(new Long(
											models.getMdlId())));

						List<SupplyingCatalogs> supplyingCatalogs = new SupplyingCatalogsDAO()
								.findBySupNumCatalogo(vehicles
										.getVhcNumReferCat());
						if (supplyingCatalogs != null
								&& supplyingCatalogs.size() > 0) {
							catalogs = supplyingCatalogs.get(0);
						}
					}
				}

				if (vehicles.getVhcNumeroTl() != null) {
					vhcNumeroTl = vehicles.getVhcNumeroTl();
				}
				if (vehicles.getLines().getBrands() != null)
					setMarcaCatalogo(vehicles.getLines().getBrands()
							.getBrnNombre());

				if (vehicles.getLines() != null) {
					setLineaCatalogo(vehicles.getLines().getLnsNombre());
				}

				if (vehicles.getLocations() != null) {

					idLocationType = vehicles.getLocations()
							.getLocationsTypes().getLctCodigo();
					tipoUbicacion = vehicles.getLocations().getLocationsTypes()
							.getLctNombre();
					setTipoUbicacion(tipoUbicacion);

					List<Locations> setLocations = SearchVehicles
							.consultarUbicacionPorIdTipo(new Long(
									idLocationType));

					cities = vehicles.getLocations().getCities();
					if (cities != null && cities.getCountries() != null)
						setCountryName(cities.getCountries().getCntNombre());

					setIdLocation(cities.getCtsId());

					setUbicacion(cities.getCtsNombre());
				}

				if (vehicles.getVehiclesStates() != null)
					estadoVehiculo = vehicles.getVehiclesStates()
							.getVhsNombre();

				if (vehicles.getVhcNumeroMotor() != null)
					vhcNumeroMotor = vehicles.getVhcNumeroMotor();

				if (vehicles.getVhcNumeroSerie() != null)
					vhcNumeroSerie = vehicles.getVhcNumeroSerie();

				if (vehicles.getVehiclesTypes() != null)
					tipoVehiculo = vehicles.getVehiclesTypes().getVhtNombre();

				if (vehicles.getLocations().getCities() != null) {
					idCuidad = vehicles.getLocations().getCities().getCtsId();

					List<Cities> setCities = new ArrayList<Cities>();
					Iterator<Cities> s = vehicles.getLocations().getCities()
							.getCountries().getCitieses().iterator();

					while (s.hasNext()) {
						setCities.add(s.next());
					}

					SelectItemCountryPage countryPage = (SelectItemCountryPage) FacesUtils
							.getManagedBean("selectItemCountryPage");
					if (countryPage != null)
						countryPage.setSetCities(setCities);

					vhcPais = vehicles.getLocations().getCities()
							.getCountries().getCntId();
					countryName = vehicles.getLocations().getCities()
							.getCountries().getCntNombre();
				}

				if (vehicles.getVhcAno() != null)
					vhcAno = vehicles.getVhcAno().toString();

				if (vehicles.getFuelsTypes() != null)
					tipoCombustible = vehicles.getFuelsTypes().getFutNombre();

				if (vehicles.getVhcColor() != null)
					vhcColor = vehicles.getVhcColor();

				if (vehicles.getVhcCapacidad() != null)
					vhcCapacidad = vehicles.getVhcCapacidad().toString();

				if (vehicles.getVhcOdometro() != null)
					vhcOdometro = vehicles.getVhcOdometro();

				if (vehicles.getVhcValorComercial() != null)
					vhcValorComercial = vehicles.getVhcValorComercial()
							.toString();

				if (vehicles.getVhcAnoValCom() != null)
					vhcAnoValCom = vehicles.getVhcAnoValCom().toString();

				if (vehicles.getVhcFechaProtocolo() != null)
					fechaProtocolo = vehicles.getVhcFechaProtocolo().toString();

				if (vehicles.getVhcFechaManifiesto() != null)
					fechaManifiesto = vehicles.getVhcFechaManifiesto()
							.toString();

				if (vehicles.getVhcNumeroManifiesto() != null)
					vhcNumeroManifiesto = vehicles.getVhcNumeroManifiesto();

				if (vehicles.getVhcNumDeclImpor() != null)
					vhcNumDeclImpor = vehicles.getVhcNumDeclImpor();

				if (vehicles.getVhcRemplazaA() != null)
					vhcRemplazaA = vehicles.getVhcRemplazaA();

				if (vehicles.getVhcNumeroLevante() != null)
					vhcNumeroLevante = vehicles.getVhcNumeroLevante();

				if (vehicles.getVhcFechaLevante() != null)
					fechaLevante = vehicles.getVhcFechaLevante().toString();

				if (vehicles.getVhcDocumentTrans() != null)
					vhcDocumentTrans = vehicles.getVhcDocumentTrans();

				if (vehicles.getVhcNumeroFactura() != null)
					vhcNumeroFactura = vehicles.getVhcNumeroFactura();

				if (vehicles.getVhcOrderCompra() != null)
					vhcOrderCompra = vehicles.getVhcOrderCompra();

				if (vehicles.getVhcProveedor() != null)
					vhcProveedor = vehicles.getVhcProveedor();

				if (vehicles.getVhcAtInicial() != null)
					vhcAtInicial = vehicles.getVhcAtInicial();

				if (vehicles.getVhcObservaciones() != null)
					vhcObservaciones = vehicles.getVhcObservaciones();

				if (vehicles.getTapestriesTypes() != null)
					tipoTapiceria = vehicles.getTapestriesTypes()
							.getTptpcNombre();

				if (vehicles.getTractionsTypes() != null)
					tipoTraccion = vehicles.getTractionsTypes().getTctNombre();

				if (vehicles.getTransmissionsTypes() != null)
					tipoTrasmision = vehicles.getTransmissionsTypes()
							.getTntNombre();

				if (vehicles.getVhcCilindraje() != null)
					vhcClilindraje = vehicles.getVhcCilindraje();

				if (vehicles.getVhcModelo() != null)
					modeloCatalogo = vehicles.getVhcModelo();

				if (vehicles.getVhcNumReferCat() != null)
					numCatalogo = vehicles.getVhcNumReferCat();

				if (vehicles.getVhcValorCif() != null)
					vhcValorCIF = new Float(vehicles.getVhcValorCif());

				if (vehicles.getVhcValorFob() != null)
					vhcValorFOB = new Float(vehicles.getVhcValorFob());

				if (vehicles.getVhcVidaUtil() != null)
					vhcVidaUtil = vehicles.getVhcVidaUtil().toString();

				if (vehicles.getVhcCargosImport() != null)
					vhcCargosImportacion = new Float(vehicles
							.getVhcCargosImport());

				if (vehicles.getVhcCiuAduan() != null)
					vhcCiuAduan = vehicles.getVhcCiuAduan();

				if (vehicles.getVhcObservaciones() != null)
					vhcObservaciones = vehicles.getVhcObservaciones();

				if (vehicles.getVhcMesfabricacion() != null)
					vhcMesfabricacion = vehicles.getVhcMesfabricacion()
							.toString();

				if (vehicles.getVhcAnofabricacion() != null)
					vhcAnofabricacion = vehicles.getVhcAnofabricacion()
							.toString();

				VehiclesAssignation vehiclesAssignation = SearchVehicles
						.consultarAsignacionVehiculo(placa.toUpperCase().trim());
				if (vehiclesAssignation != null) {
					estadoAsignacion = vehiclesAssignation
							.getAssignationsStates().getAssNombre();
					asignatario = vehiclesAssignation.getRequests()
							.getUsersByRqsUser().getUsrNombre()
							+ " "
							+ vehiclesAssignation.getRequests()
									.getUsersByRqsUser().getUsrApellido();

					if (vehiclesAssignation.getRequests().getLegateesTypes() != null) {
						tipoAsignacion = vehiclesAssignation.getRequests()
								.getLegateesTypes().getLgtNombre();
					}

					fechaInicioSolicitud = vehiclesAssignation.getRequests()
							.getRqsFechaInicial().toString();
					fechaFinSolicitud = vehiclesAssignation.getRequests()
							.getRqsFechaFinal().toString();

					if (vehiclesAssignation.getRequests().getZones() != null) {
						zona = vehiclesAssignation.getRequests().getZones()
								.getZnsNombre();
					}

					// List<VOCostCenters> costsCentersVehicles =
					// SearchCostCenters.consultarCostCenterAsignacion(vehiclesAssignation.getVhaCodigo());
					centrosCostos = SearchCostCenters
							.consultarCostCenterAsignacion(vehiclesAssignation
									.getVhaCodigo());

					centrosCostosCombustible = SearchCostCenters
							.consultarCostCenterFuel(vehiclesAssignation
									.getVhaCodigo());
				}

				ServiceRegistry serviceRegistry = new ServiceRegistryImp()
						.lastServiceRegistryPlaca(placa.toUpperCase().trim());
				if (serviceRegistry != null) {
					if (serviceRegistry.getSerFecha() != null) {
						fechaUltimaTanqueada = serviceRegistry.getSerFecha()
								.toString();
					}
					if (serviceRegistry.getSerKilometrajeActual() != null) {
						kilometrajeUltimaTanqueada = serviceRegistry
								.getSerKilometrajeActual().toString();
					}
				}
			} else {
				limpiar();
				throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));
			}

		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	/**
	 * 
	 * @param event
	 */
	public void action_consultarDetalle(ActionEvent event) {
		try {
			boolean esValido = true;
			if (placa.equals("") && placa.trim().length() == 0)
				throw new GWorkException(Util.loadErrorMessageValue("PLACA"));

			if (placa != null && placa.trim().length() != 0)
				esValido = Util.validarPlaca(placa);

			if (!esValido)
				throw new GWorkException(Util
						.loadErrorMessageValue("PLACA.INCORRECTO"));

			if (placa.trim().length() < 2 || placa.trim().length() > 8)
				throw new GWorkException(Util
						.loadErrorMessageValue("PLACA.MINIMO"));

			Vehicles vehicles = SearchVehicles
					.consultarVehiculosPorPlacaSinFiltros(placa.toUpperCase()
							.trim());

			if (vehicles == null
					|| vehicles.getVhcPlacaDiplomatica().length() < 2)
				throw new GWorkException(Util
						.loadErrorMessageValue("PLACADIPLOMATICAEXISTEN"));

			// VehiclesAssignation vehiclesAssignation = null;
			if (placa != null && placa.trim().length() != 0) {
				if (idTipoDetalle == 1) {
					List<VehiclesAssignation> vehiclesAssignation = SearchVehicles
							.consultarDatosVehiculoAsignacionPorPlaca(placa
									.trim().toUpperCase());

					if (vehiclesAssignation == null
							|| vehiclesAssignation.size() == 0)
						throw new GWorkException(Util
								.loadErrorMessageValue("CONSULTA"));
					for (VehiclesAssignation asignaciones : vehiclesAssignation) {

						VOAssignation voAssignation = new VOAssignation();
						if (asignaciones.getRequests().getLegateesTypes() != null)
							voAssignation.setTipoAsignacion(asignaciones
									.getRequests().getLegateesTypes()
									.getLgtNombre());
						else
							voAssignation.setTipoAsignacion(" ");

						voAssignation.setCarneAsignatario(asignaciones
								.getVhaNumeroCarne());

						if (asignaciones.getRequests().getUsersByRqsUser()
								.getUsrApellido() != null) {
							voAssignation
									.setNombreAsignatario(asignaciones
											.getRequests().getUsersByRqsUser()
											.getUsrApellido()
											+ " "
											+ asignaciones.getRequests()
													.getUsersByRqsUser()
													.getUsrNombre());
						} else {
							voAssignation.setNombreAsignatario(asignaciones
									.getRequests().getUsersByRqsUser()
									.getUsrNombre());
						}

						voAssignation.setFechaDevolucion(asignaciones
								.getVhaFechaDev());
						voAssignation.setFechaEntrega(asignaciones
								.getVhaFechaEntrega());
						voAssignation.setFechaInicio(asignaciones
								.getVhaFechaInicio());
						voAssignation.setFechaTerminacion(asignaciones
								.getVhaFechaTermina());
						voAssignation.setKilometrajeDevolucion(asignaciones
								.getVhaKilomDev());
						voAssignation.setKilometrajeEntrega(asignaciones
								.getVhaKilomeActual());
						voAssignation.setObservacionAsignacion(asignaciones
								.getVhaObservacion());
						voAssignation.setObservacionDevolucion(asignaciones
								.getVhaObservacionDev());
						voAssignation.setKilometrajeRecorrido(voAssignation
								.calcKmRecorridos(voAssignation
										.getKilometrajeDevolucion(),
										voAssignation.getKilometrajeEntrega()));
						voAssignation.setDiasAlquilados(voAssignation
								.calcDiasAlquilados(voAssignation
										.getFechaDevolucion(), voAssignation
										.getFechaEntrega()));

						centrosCostosAsignaciones = SearchCostCenters
								.consultarCostCenterAsignacion(asignaciones
										.getVhaCodigo());

						voAssignation
								.setCencosAsignaciones(centrosCostosAsignaciones);

						listVehiclesAssignation.add(voAssignation);
					}
					showVehiculosAsignados = true;
				} else if (idTipoDetalle == 2) {
					List<CostsCentersVehicles> costsCentersNewness = SearchCostCenters
							.consultarDatosNovedadCentroCostosPorPlaca(placa
									.trim().toUpperCase());
					if (costsCentersNewness != null) {
						for (CostsCentersVehicles novedadCentroCostos : costsCentersNewness) {

							VONovedadCentroCostos voNovedadCentroCostos = new VONovedadCentroCostos();

							voNovedadCentroCostos
									.setCocCodigo(novedadCentroCostos
											.getCostsCenters().getCocCodigo());

							CostsCenters costsCenters = new CostsCenters();
							costsCenters = (new CostsCentersDAO())
									.findById(novedadCentroCostos
											.getCostsCenters().getCocCodigo());

							if (costsCenters != null)
								voNovedadCentroCostos.setCocNumero(costsCenters
										.getCocNumero());

							voNovedadCentroCostos
									.setCocPorcentaje(novedadCentroCostos
											.getCcrPorcentaje());
							voNovedadCentroCostos
									.setFechaInicio(novedadCentroCostos
											.getCcrFechaInicio());
							voNovedadCentroCostos
									.setFechaFin(novedadCentroCostos
											.getCcrFechaFin());

							listVONovedadCentroCosto.add(voNovedadCentroCostos);
						}
						showNovedadCentroCostos = true;
					} else {
						throw new GWorkException(
								Util
										.loadErrorMessageValue("RESUMENVEHICULO.NODATOS"));
					}
				} else if (idTipoDetalle == 3) {
					List<VONovedadCambioUbicacion> ListNovedadCambioUbicacion = new ArrayList<VONovedadCambioUbicacion>();

					List<LocationsNewness> listLocationsNewness = SearchNewness
							.consultarDatosNovedadCambioUbicacionPorPlaca(placa
									.trim().toUpperCase());
					if (listLocationsNewness != null
							&& listLocationsNewness.size() > 0) {
						for (LocationsNewness novedadCambioUbicacion : listLocationsNewness) {
							VONovedadCambioUbicacion voNovedadCambioUbicacion = new VONovedadCambioUbicacion();

							// transformar y formatear fecha
							java.text.DateFormat formatDate = new java.text.SimpleDateFormat(
									"dd-MMM-yy");
							voNovedadCambioUbicacion.setFechaInicio(formatDate
									.parse(formatDate
											.format(novedadCambioUbicacion
													.getLcnFechaActual())));

							// Obtener ciudad & país
							// List<Cities> ciudades =
							// SearchNewness.consultarCiudadporUbicacion(novedadCambioUbicacion.getLcnUbicacion());
							List<Object[]> ciudades = SearchNewness
									.consultarCiudadporUbicacion(novedadCambioUbicacion
											.getLcnUbicacion());
							if (ciudades != null && ciudades.size() > 0) {
								Object[] city = ciudades.get(0);
								voNovedadCambioUbicacion.setCiudad(String
										.valueOf(city[0]));
								voNovedadCambioUbicacion.setPais(String
										.valueOf(city[1]));
							}

							// Obtener ubicación
							List<Locations> ubicaciones = SearchNewness
									.consultarDatosUbicacion(novedadCambioUbicacion
											.getLcnUbicacion());
							if (ubicaciones != null && ubicaciones.size() > 0) {
								Locations aux = ubicaciones.get(0);
								voNovedadCambioUbicacion.setLocalizacion(aux
										.getLocationsTypes().getLctNombre());
							}

							ListNovedadCambioUbicacion
									.add(voNovedadCambioUbicacion);
						}

						if (ListNovedadCambioUbicacion.size() <= 0)
							FacesUtils.addErrorMessage(Util
									.loadErrorMessageValue("search.not.found"));
						else {
							if (getListVONovedadCambioUbicacion() != null
									&& getListVONovedadCambioUbicacion().size() > 0)
								getListVONovedadCambioUbicacion().clear();
							setListVONovedadCambioUbicacion(ListNovedadCambioUbicacion);
						}

						showNovedadCambioUbicacion = true;
					} else
						FacesUtils.addErrorMessage(Util
								.loadErrorMessageValue("search.not.found"));
				} else if (idTipoDetalle == 4) {
					// Código del serviceRegistry
					try {

						if (vehicles != null) {
							List<VORegistryService> listRegServiceTmp = new ArrayList<VORegistryService>();

							/* Metodos nuevos */
							serVhc = new SearchVehicles();
							List<Object[]> regServicios = serVhc
									.consultarRegServicios(vehicles
											.getVhcCodigo());
							Float promedioKm = new Float(0.0f);
							VORegistryService regService;
							PrepaidConsumption pc;
							ActualOthersApplications aoa;

							for (Object[] objRegServ : regServicios) {
								regService = null;
								regService = new VORegistryService();

								pc = null;
								pc = new PrepaidConsumption();

								aoa = null;

								promedioKm = 0f;// reiniciar el valor de la
								// variable

								promedioKm = Float.parseFloat(objRegServ[5]
										.toString())
										- Float.parseFloat(objRegServ[4]
												.toString());
								promedioKm = promedioKm
										/ Float.parseFloat(objRegServ[3]
												.toString());

								// transformar y formatear fecha
								java.text.DateFormat formatDate = new java.text.SimpleDateFormat(
										"dd-MMM-yy");

								regService
										.setFechaRecibo(formatDate
												.parse(formatDate
														.format(objRegServ[2])));
								regService.setSercodigo(Long
										.parseLong(objRegServ[0].toString()));
								regService.setKmPromedio(promedioKm);
								regService.setGalones(Float
										.parseFloat(objRegServ[3].toString()));
								regService.setValor(Float
										.parseFloat(objRegServ[6].toString()));
								regService
										.setSerKilometrajeActual(objRegServ[5]
												.toString());

								pc = serVhc.consultarPrepaidConsumption(Long
										.parseLong(objRegServ[0].toString()));

								if (pc != null) {
									regService.setCentroCosto(pc
											.getCostCentersFuel()
											.getCostsCenters().getCocNumero());
								} else {
									if (objRegServ[7] != null
											&& objRegServ[7].toString().trim() != "") {

										aoa = new ActualOthersApplications();
										aoa = serVhc.consultarActualOthersApplications(
												Long.parseLong(objRegServ[7].toString()));
										if (aoa != null) {
											regService.setCentroCosto(aoa
													.getPCcenter().toString());
										} else {
											regService.setCentroCosto("");
										}
									} else {
										regService.setCentroCosto("");
									}
								}

								// regService. -> como se obtiene el cc que se
								// debe mostrar?

								listRegServiceTmp.add(regService);
							}

							if (listRegServiceTmp.size() <= 0)
								FacesUtils
										.addErrorMessage(Util
												.loadErrorMessageValue("search.not.found"));
							else {
								if (getListRegService() != null
										&& getListRegService().size() > 0)
									getListRegService().clear();
								setListRegService(listRegServiceTmp);
							}

						} else
							throw new GWorkException(Util
									.loadErrorMessageValue("search.not.found"));
					} catch (GWorkException e) {
						FacesUtils.addErrorMessage(e.getMessage());
					}
					setShowRegistroServicios(true);
				} else if (idTipoDetalle == 5) {
					ClientsSalesVehicles clientsSalesVehicles = new ClientsSalesVehicles();
					Vehicles vehiclesRet = null;
					RetirementsReasons retirementsReasons = null;

					vehiclesRet = SearchVehicles
							.ConsultarMotivoRetiroPorPlacaVehiculo(placa.trim()
									.toUpperCase());

					if (vehiclesRet != null
							&& vehiclesRet.getRetirementsReasons() != null)
						retirementsReasons = vehiclesRet
								.getRetirementsReasons();

					clientsSalesVehicles = SearchVehicles
							.consultarVehiculoRetirado(placa.trim()
									.toUpperCase());

					if (retirementsReasons != null) {
						setMotivoRetiro(retirementsReasons
								.getRetirementsTypes().getRetNombre());
						setRerFechaRetiro(retirementsReasons
								.getRerFechaRetiro());
						setRerDescripcion(retirementsReasons
								.getRerDescripcion());

						if (clientsSalesVehicles != null) {

							setNombreComprador(clientsSalesVehicles
									.getCsvNombre());
							setNumeroIdentificacion(clientsSalesVehicles
									.getCsvIdentificacacion());
							setDireccion(clientsSalesVehicles.getCsvDireccion());
							setTelefono(clientsSalesVehicles.getCsvTelefono());
							setValorATfinal(clientsSalesVehicles
									.getCsvAtFinal());
							// kilometrajeVenta;
							setPlacaIntra(clientsSalesVehicles
									.getCsvPlacaIntra());
							setPrecioVenta(clientsSalesVehicles
									.getCsvValorVenta());
							setFechaEntregaVenta(clientsSalesVehicles
									.getCsvFechaEntrega());
							setNumeroLicitacion(clientsSalesVehicles
									.getCsvNumeroLicitacion());
							setObservaciones(clientsSalesVehicles
									.getCsvObservaciones());

						}
						showVehiculoRetirado = true;
					} else {
						throw new GWorkException(
								Util
										.loadErrorMessageValue("RESUMENVEHICULO.NODATOS"));
					}

				} else if (idTipoDetalle == 6) {

				} else if (idTipoDetalle == -1) {
					throw new GWorkException(Util
							.loadErrorMessageValue("RESUMENVEHICULO.DETALLE"));
				}
			} else {
				throw new GWorkException(Util
						.loadErrorMessageValue("RESUMENVEHICULO.NODATOS"));
			}

		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	/**
	 * 
	 * @param event
	 */
	public void action_limpiar(ActionEvent event) {
		limpiar();
	}

	/**
	 * 
	 */
	public void limpiar() {

		setVhcMesfabricacion(null);
		setVhcAnofabricacion(null);

		placa = null;
		estadoAsignacion = null;
		asignatario = null;
		tipoAsignacion = null;
		fechaInicioSolicitud = null;
		fechaFinSolicitud = null;
		zona = null;

		marcaCatalogo = null;
		vhcPlacaDiplomatica = "";
		vhcPlacaActivoFijo = "";
		vhcNumeroTl = "";
		idBrand = new Long("-1");
		lineaCatalogo = null;
		idLocation = new Long("-1");
		estadoVehiculo = null;
		vhcCatalogado = "";
		vhcNumeroMotor = "";
		vhcNumeroSerie = "";
		tipoVehiculo = null;
		idCuidad = new Long("-1");
		vhcAno = null;
		tipoCombustible = null;
		vhcColor = "";
		vhcCapacidad = null;
		vhcOdometro = "";
		vhcValorComercial = null;
		vhcAnoValCom = null;
		fechaProtocolo = null;
		fechaManifiesto = null;
		vhcNumeroManifiesto = "";
		vhcNumDeclImpor = "";
		vhcRemplazaA = "";
		vhcNumeroLevante = "";
		fechaLevante = null;
		vhcDocumentTrans = "";
		vhcNumeroFactura = "";
		vhcOrderCompra = "";
		vhcProveedor = "";
		vhcAtInicial = "";
		vhcCiuAduan = "";
		vhcObservaciones = "";
		tipoTapiceria = null;
		tipoTraccion = null;
		tipoTrasmision = null;
		tipoUbicacion = null;
		ubicacion = null;
		vhcClilindraje = null;
		modeloCatalogo = null;
		idCountry = null;
		idCuidad = null;
		numCatalogo = null;
		vhcVidaUtil = null;
		vhcValorFOB = null;
		vhcValorCIF = null;
		vhcCargosImportacion = null;
		countryName = null;
		centrosCostos = null;
		kilometrajeUltimaTanqueada = null;
		fechaUltimaTanqueada = null;
		centrosCostosCombustible = null;
		if (listRegService != null)
			listRegService = null;
		limpiarDatosSessionConsulta();
		setListUsers(null);
		carneAsignatario = null;
		nombreAsignatario = null;
		parametroBusqueda = null;
	}

	public VehicleService getVehicleService() {
		return vehicleService;
	}

	public void setVehicleService(VehicleService vehicleService) {
		this.vehicleService = vehicleService;
	}

	public String getVhcPlacaDiplomatica() {
		return vhcPlacaDiplomatica;
	}

	public void setVhcPlacaDiplomatica(String vhcPlacaDiplomatica) {
		this.vhcPlacaDiplomatica = vhcPlacaDiplomatica;
	}

	public String getVhcPlacaActivoFijo() {
		return vhcPlacaActivoFijo;
	}

	public void setVhcPlacaActivoFijo(String vhcPlacaActivoFijo) {
		this.vhcPlacaActivoFijo = vhcPlacaActivoFijo;
	}

	public String getVhcNumeroTl() {
		return vhcNumeroTl;
	}

	public void setVhcNumeroTl(String vhcNumeroTl) {
		this.vhcNumeroTl = vhcNumeroTl;
	}

	public Long getIdBrand() {
		return idBrand;
	}

	public void setIdBrand(Long idBrand) {
		this.idBrand = idBrand;
	}

	public String getVhcCatalogado() {
		return vhcCatalogado;
	}

	public void setVhcCatalogado(String vhcCatalogado) {
		this.vhcCatalogado = vhcCatalogado;
	}

	public String getVhcNumeroMotor() {
		return vhcNumeroMotor;
	}

	public void setVhcNumeroMotor(String vhcNumeroMotor) {
		this.vhcNumeroMotor = vhcNumeroMotor;
	}

	public String getVhcNumeroSerie() {
		return vhcNumeroSerie;
	}

	public void setVhcNumeroSerie(String vhcNumeroSerie) {
		this.vhcNumeroSerie = vhcNumeroSerie;
	}

	public Long getIdCuidad() {
		return idCuidad;
	}

	public void setIdCuidad(Long idCuidad) {
		this.idCuidad = idCuidad;
	}

	public String getVhcAno() {
		return vhcAno;
	}

	public void setVhcAno(String vhcAno) {
		this.vhcAno = vhcAno;
	}

	public String getVhcColor() {
		return vhcColor;
	}

	public void setVhcColor(String vhcColor) {
		this.vhcColor = vhcColor;
	}

	public String getVhcCapacidad() {
		return vhcCapacidad;
	}

	public void setVhcCapacidad(String vhcCapacidad) {
		this.vhcCapacidad = vhcCapacidad;
	}

	public String getVhcOdometro() {
		return vhcOdometro;
	}

	public void setVhcOdometro(String vhcOdometro) {
		this.vhcOdometro = vhcOdometro;
	}

	public String getVhcValorComercial() {
		return vhcValorComercial;
	}

	public void setVhcValorComercial(String vhcValorComercial) {
		this.vhcValorComercial = vhcValorComercial;
	}

	public String getVhcAnoValCom() {
		return vhcAnoValCom;
	}

	public void setVhcAnoValCom(String vhcAnoValCom) {
		this.vhcAnoValCom = vhcAnoValCom;
	}

	public String getVhcNumeroManifiesto() {
		return vhcNumeroManifiesto;
	}

	public void setVhcNumeroManifiesto(String vhcNumeroManifiesto) {
		this.vhcNumeroManifiesto = vhcNumeroManifiesto;
	}

	public String getVhcNumDeclImpor() {
		return vhcNumDeclImpor;
	}

	public void setVhcNumDeclImpor(String vhcNumDeclImpor) {
		this.vhcNumDeclImpor = vhcNumDeclImpor;
	}

	public String getVhcRemplazaA() {
		return vhcRemplazaA;
	}

	public void setVhcRemplazaA(String vhcRemplazaA) {
		this.vhcRemplazaA = vhcRemplazaA;
	}

	public String getVhcNumeroLevante() {
		return vhcNumeroLevante;
	}

	public void setVhcNumeroLevante(String vhcNumeroLevante) {
		this.vhcNumeroLevante = vhcNumeroLevante;
	}

	public String getVhcDocumentTrans() {
		return vhcDocumentTrans;
	}

	public void setVhcDocumentTrans(String vhcDocumentTrans) {
		this.vhcDocumentTrans = vhcDocumentTrans;
	}

	public String getVhcNumeroFactura() {
		return vhcNumeroFactura;
	}

	public void setVhcNumeroFactura(String vhcNumeroFactura) {
		this.vhcNumeroFactura = vhcNumeroFactura;
	}

	public String getVhcOrderCompra() {
		return vhcOrderCompra;
	}

	public void setVhcOrderCompra(String vhcOrderCompra) {
		this.vhcOrderCompra = vhcOrderCompra;
	}

	public String getVhcProveedor() {
		return vhcProveedor;
	}

	public void setVhcProveedor(String vhcProveedor) {
		this.vhcProveedor = vhcProveedor;
	}

	public String getVhcAtInicial() {
		return vhcAtInicial;
	}

	public void setVhcAtInicial(String vhcAtInicial) {
		this.vhcAtInicial = vhcAtInicial;
	}

	public String getVhcCiuAduan() {
		return vhcCiuAduan;
	}

	public void setVhcCiuAduan(String vhcCiuAduan) {
		this.vhcCiuAduan = vhcCiuAduan;
	}

	public String getVhcObservaciones() {
		return vhcObservaciones;
	}

	public void setVhcObservaciones(String vhcObservaciones) {
		this.vhcObservaciones = vhcObservaciones;
	}

	public Long getIdLocation() {
		return idLocation;
	}

	public void setIdLocation(Long idLocation) {
		this.idLocation = idLocation;
	}

	public String getVhcClilindraje() {
		return vhcClilindraje;
	}

	public void setVhcClilindraje(String vhcClilindraje) {
		this.vhcClilindraje = vhcClilindraje;
	}

	public Long getIdCountry() {
		return idCountry;
	}

	public void setIdCountry(Long idCountry) {
		this.idCountry = idCountry;
	}

	public String getLineaCatalogo() {
		return lineaCatalogo;
	}

	public void setLineaCatalogo(String lineaCatalogo) {
		this.lineaCatalogo = lineaCatalogo;
	}

	public String getModeloCatalogo() {
		return modeloCatalogo;
	}

	public void setModeloCatalogo(String modeloCatalogo) {
		this.modeloCatalogo = modeloCatalogo;
	}

	public String getMarcaCatalogo() {
		return marcaCatalogo;
	}

	public void setMarcaCatalogo(String marcaCatalogo) {
		this.marcaCatalogo = marcaCatalogo;
	}

	public Long getIdNumCatalogo() {
		return idNumCatalogo;
	}

	public void setIdNumCatalogo(Long idNumCatalogo) {
		this.idNumCatalogo = idNumCatalogo;
	}

	public Long getVhcPais() {
		return vhcPais;
	}

	public void setVhcPais(Long vhcPais) {
		this.vhcPais = vhcPais;
	}

	public Long getIdLocationType() {
		return idLocationType;
	}

	public void setIdLocationType(Long idLocationType) {
		this.idLocationType = idLocationType;
	}

	public String getNumCatalogo() {
		return numCatalogo;
	}

	public void setNumCatalogo(String numCatalogo) {
		this.numCatalogo = numCatalogo;
	}

	public String getVhcRemplazaPor() {
		return vhcRemplazaPor;
	}

	public void setVhcRemplazaPor(String vhcRemplazaPor) {
		this.vhcRemplazaPor = vhcRemplazaPor;
	}

	public Long getIdModelo() {
		return idModelo;
	}

	public void setIdModelo(Long idModelo) {
		this.idModelo = idModelo;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public ConsultsService getConsultService() {
		return consultService;
	}

	public void setConsultService(ConsultsService consultService) {
		this.consultService = consultService;
	}

	public String getVhcVidaUtil() {
		return vhcVidaUtil;
	}

	public void setVhcVidaUtil(String vhcVidaUtil) {
		this.vhcVidaUtil = vhcVidaUtil;
	}

	public Float getVhcValorCIF() {
		return vhcValorCIF;
	}

	public void setVhcValorCIF(Float vhcValorCIF) {
		this.vhcValorCIF = vhcValorCIF;
	}

	public Float getVhcValorFOB() {
		return vhcValorFOB;
	}

	public void setVhcValorFOB(Float vhcValorFOB) {
		this.vhcValorFOB = vhcValorFOB;
	}

	public Float getVhcCargosImportacion() {
		return vhcCargosImportacion;
	}

	public void setVhcCargosImportacion(Float vhcCargosImportacion) {
		this.vhcCargosImportacion = vhcCargosImportacion;
	}

	public String getVhcAnofabricacion() {
		return vhcAnofabricacion;
	}

	public void setVhcAnofabricacion(String vhcAnofabricacion) {
		this.vhcAnofabricacion = vhcAnofabricacion;
	}

	public String getVhcMesfabricacion() {
		return vhcMesfabricacion;
	}

	public void setVhcMesfabricacion(String vhcMesfabricacion) {
		this.vhcMesfabricacion = vhcMesfabricacion;
	}

	public boolean isLocationVisible() {
		return locationVisible;
	}

	public void setLocationVisible(boolean locationVisible) {
		this.locationVisible = locationVisible;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getTipoVehiculo() {
		return tipoVehiculo;
	}

	public void setTipoVehiculo(String tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}

	public String getTipoTapiceria() {
		return tipoTapiceria;
	}

	public void setTipoTapiceria(String tipoTapiceria) {
		this.tipoTapiceria = tipoTapiceria;
	}

	public String getTipoTrasmision() {
		return tipoTrasmision;
	}

	public void setTipoTrasmision(String tipoTrasmision) {
		this.tipoTrasmision = tipoTrasmision;
	}

	public String getTipoCombustible() {
		return tipoCombustible;
	}

	public void setTipoCombustible(String tipoCombustible) {
		this.tipoCombustible = tipoCombustible;
	}

	public String getTipoUbicacion() {
		return tipoUbicacion;
	}

	public void setTipoUbicacion(String tipoUbicacion) {
		this.tipoUbicacion = tipoUbicacion;
	}

	public String getFechaProtocolo() {
		return fechaProtocolo;
	}

	public void setFechaProtocolo(String fechaProtocolo) {
		this.fechaProtocolo = fechaProtocolo;
	}

	public String getFechaManifiesto() {
		return fechaManifiesto;
	}

	public void setFechaManifiesto(String fechaManifiesto) {
		this.fechaManifiesto = fechaManifiesto;
	}

	public String getFechaLevante() {
		return fechaLevante;
	}

	public void setFechaLevante(String fechaLevante) {
		this.fechaLevante = fechaLevante;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public String getTipoTraccion() {
		return tipoTraccion;
	}

	public void setTipoTraccion(String tipoTraccion) {
		this.tipoTraccion = tipoTraccion;
	}

	public String getEstadoVehiculo() {
		return estadoVehiculo;
	}

	public void setEstadoVehiculo(String estadoVehiculo) {
		this.estadoVehiculo = estadoVehiculo;
	}

	public String getEstadoAsignacion() {
		return estadoAsignacion;
	}

	public void setEstadoAsignacion(String estadoAsignacion) {
		this.estadoAsignacion = estadoAsignacion;
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

	public String getZona() {
		return zona;
	}

	public void setZona(String zona) {
		this.zona = zona;
	}

	public String getFechaInicioSolicitud() {
		return fechaInicioSolicitud;
	}

	public void setFechaInicioSolicitud(String fechaInicioSolicitud) {
		this.fechaInicioSolicitud = fechaInicioSolicitud;
	}

	public String getFechaFinSolicitud() {
		return fechaFinSolicitud;
	}

	public void setFechaFinSolicitud(String fechaFinSolicitud) {
		this.fechaFinSolicitud = fechaFinSolicitud;
	}

	public String getFechaUltimaTanqueada() {
		return fechaUltimaTanqueada;
	}

	public void setFechaUltimaTanqueada(String fechaUltimaTanqueada) {
		this.fechaUltimaTanqueada = fechaUltimaTanqueada;
	}

	public String getKilometrajeUltimaTanqueada() {
		return kilometrajeUltimaTanqueada;
	}

	public void setKilometrajeUltimaTanqueada(String kilometrajeUltimaTanqueada) {
		this.kilometrajeUltimaTanqueada = kilometrajeUltimaTanqueada;
	}

	public Long getIdTipoDetalle() {
		return idTipoDetalle;
	}

	public void setIdTipoDetalle(Long idTipoDetalle) {
		this.idTipoDetalle = idTipoDetalle;
	}

	public List<Vehicles> getListVehicles() {
		return listVehicles;
	}

	public void setListVehicles(List<Vehicles> listVehicles) {
		this.listVehicles = listVehicles;
	}

	public String getCentrosCostos() {
		return centrosCostos;
	}

	public void setCentrosCostos(String centrosCostos) {
		this.centrosCostos = centrosCostos;
	}

	public boolean isShowVehiculosAsignados() {
		return showVehiculosAsignados;
	}

	public void setShowVehiculosAsignados(boolean showVehiculosAsignados) {
		this.showVehiculosAsignados = showVehiculosAsignados;
	}

	/**
	 * 
	 * @param event
	 */
	public void action_closeVehiculosAsignados(ActionEvent event) {
		listVehiclesAssignation.clear();
		showVehiculosAsignados = false;
	}

	public void action_closeVehiculoRetirado(ActionEvent event) {
		// listVehiclesAssignation.clear();
		showVehiculoRetirado = false;
	}

	/**
	 * 
	 * @param event
	 */
	public void action_closeNovedadCambioUbicacion(ActionEvent event) {
		listVONovedadCentroCosto.clear();
		showNovedadCambioUbicacion = false;
	}

	/**
	 * 
	 * @param event
	 */
	public void action_closeNovedadCentroCostos(ActionEvent event) {
		listVONovedadCentroCosto.clear();
		showNovedadCentroCostos = false;
	}

	public Long getVhaCodigo() {
		return vhaCodigo;
	}

	public void setVhaCodigo(Long vhaCodigo) {
		this.vhaCodigo = vhaCodigo;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaTerminacion() {
		return fechaTerminacion;
	}

	public void setFechaTerminacion(Date fechaTerminacion) {
		this.fechaTerminacion = fechaTerminacion;
	}

	public Date getFechaEntrega() {
		return fechaEntrega;
	}

	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	public Date getFechaDevolucion() {
		return fechaDevolucion;
	}

	public void setFechaDevolucion(Date fechaDevolucion) {
		this.fechaDevolucion = fechaDevolucion;
	}

	public String getObservacionAsignacion() {
		return observacionAsignacion;
	}

	public void setObservacionAsignacion(String observacionAsignacion) {
		this.observacionAsignacion = observacionAsignacion;
	}

	public Long getKilometrajeEntrega() {
		return kilometrajeEntrega;
	}

	public void setKilometrajeEntrega(Long kilometrajeEntrega) {
		this.kilometrajeEntrega = kilometrajeEntrega;
	}

	public Long getKilometrajeDevolucion() {
		return kilometrajeDevolucion;
	}

	public void setKilometrajeDevolucion(Long kilometrajeDevolucion) {
		this.kilometrajeDevolucion = kilometrajeDevolucion;
	}

	public String getObservacionDevolucion() {
		return observacionDevolucion;
	}

	public void setObservacionDevolucion(String observacionDevolucion) {
		this.observacionDevolucion = observacionDevolucion;
	}

	public String getCarnet() {
		return carnet;
	}

	public void setCarnet(String carnet) {
		this.carnet = carnet;
	}

	public String getAsignatarioVehiculo() {
		return asignatarioVehiculo;
	}

	public void setAsignatarioVehiculo(String asignatarioVehiculo) {
		this.asignatarioVehiculo = asignatarioVehiculo;
	}

	public List<VOAssignation> getListVehiclesAssignation() {
		return listVehiclesAssignation;
	}

	public void setListVehiclesAssignation(
			List<VOAssignation> listVehiclesAssignation) {
		this.listVehiclesAssignation = listVehiclesAssignation;
	}

	public String getCentrosCostosAsignaciones() {
		return centrosCostosAsignaciones;
	}

	public void setCentrosCostosAsignaciones(String centrosCostosAsignaciones) {
		this.centrosCostosAsignaciones = centrosCostosAsignaciones;
	}

	public Long getCentroCostoNovedad() {
		return centroCostoNovedad;
	}

	public void setCentroCostoNovedad(Long centroCostoNovedad) {
		this.centroCostoNovedad = centroCostoNovedad;
	}

	public boolean isShowRegistroServicios() {
		return showRegistroServicios;
	}

	public void setShowRegistroServicios(boolean showRegistroServicios) {
		this.showRegistroServicios = showRegistroServicios;
	}

	/* Codigo nuevo */
	public void action_closeRegistroServicios(ActionEvent event) {
		showRegistroServicios = false;
	}

	public SearchVehicles getSerVhc() {
		return serVhc;
	}

	public void setSerVhc(SearchVehicles serVhc) {
		this.serVhc = serVhc;
	}

	public List<VORegistryService> getListRegService() {
		return listRegService;
	}

	public void setListRegService(List<VORegistryService> listRegService) {
		this.listRegService = listRegService;

		/*
		 * for(VORegistryService regService: listRegService){
		 * System.out.println("regService:\n" + "getCosto_cmb:" +
		 * regService.getCosto_cmb() + "}\n" + "getFechaRecibo:" +
		 * regService.getFechaRecibo() + "}\n" + "getGalones:" +
		 * regService.getGalones() + "}\n" + "getKmPromedio:" +
		 * regService.getKmPromedio() + "}\n" + "getValor:" +
		 * regService.getValor() + "}\n" ); }
		 */
	}

	public boolean isShowVehiculoRetirado() {
		return showVehiculoRetirado;
	}

	public void setShowVehiculoRetirado(boolean showVehiculoRetirado) {
		this.showVehiculoRetirado = showVehiculoRetirado;
	}

	public String getNombreComprador() {
		return nombreComprador;
	}

	public void setNombreComprador(String nombreComprador) {
		this.nombreComprador = nombreComprador;
	}

	public String getNumeroIdentificacion() {
		return numeroIdentificacion;
	}

	public void setNumeroIdentificacion(String numeroIdentificacion) {
		this.numeroIdentificacion = numeroIdentificacion;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getPlacaIntra() {
		return placaIntra;
	}

	public void setPlacaIntra(String placaIntra) {
		this.placaIntra = placaIntra;
	}

	public String getPrecioVenta() {
		return precioVenta;
	}

	public void setPrecioVenta(String precioVenta) {
		this.precioVenta = precioVenta;
	}

	public Date getFechaEntregaVenta() {
		return fechaEntregaVenta;
	}

	public void setFechaEntregaVenta(Date fechaEntregaVenta) {
		this.fechaEntregaVenta = fechaEntregaVenta;
	}

	public Long getNumeroLicitacion() {
		return numeroLicitacion;
	}

	public void setNumeroLicitacion(Long numeroLicitacion) {
		this.numeroLicitacion = numeroLicitacion;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String getValorATfinal() {
		return valorATfinal;
	}

	public void setValorATfinal(String valorATfinal) {
		this.valorATfinal = valorATfinal;
	}

	public boolean isShowNovedadCambioUbicacion() {
		return showNovedadCambioUbicacion;
	}

	public void setShowNovedadCambioUbicacion(boolean showNovedadCambioUbicacion) {
		this.showNovedadCambioUbicacion = showNovedadCambioUbicacion;
	}

	public Long getCambioUbicacionNovedad() {
		return cambioUbicacionNovedad;
	}

	public void setCambioUbicacionNovedad(Long cambioUbicacionNovedad) {
		this.cambioUbicacionNovedad = cambioUbicacionNovedad;
	}

	public List<VONovedadCambioUbicacion> getListVONovedadCambioUbicacion() {
		return listVONovedadCambioUbicacion;
	}

	public void setListVONovedadCambioUbicacion(
			List<VONovedadCambioUbicacion> listVONovedadCambioUbicacion) {
		this.listVONovedadCambioUbicacion = listVONovedadCambioUbicacion;
	}

	public String getCentrosCostosCombustible() {
		return centrosCostosCombustible;
	}

	public void setCentrosCostosCombustible(String centrosCostosCombustible) {
		this.centrosCostosCombustible = centrosCostosCombustible;
	}

	public String getCarneAsignatario() {
		return carneAsignatario;
	}

	public void setCarneAsignatario(String carneAsignatario) {
		this.carneAsignatario = carneAsignatario;
	}

	public String getNombreAsignatario() {
		return nombreAsignatario;
	}

	public void setNombreAsignatario(String nombreAsignatario) {
		this.nombreAsignatario = nombreAsignatario;
	}

	public boolean isShowAsignatioByCode() {
		return showAsignatioByCode;
	}

	public void setShowAsignatioByCode(boolean showAsignatioByCode) {
		this.showAsignatioByCode = showAsignatioByCode;
	}

	public void action_showAssignationByCode(ActionEvent actionEvent) {
		try {
			if (carneAsignatario == null
					|| carneAsignatario.trim().length() == 0)
				throw new GWorkException(Util
						.loadErrorMessageValue("CARNEASIGNATARIO"));

			setListAsignaciones(null);
			setListAsignaciones(vehicleService
					.listAsignationByUser(carneAsignatario));

			showAsignatioByCode = true;

		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

	}

	public void action_closeAssignationByCode(ActionEvent actionEvent) {

		showAsignatioByCode = false;
	}

	public boolean isShowFilterEmployee() {
		return showFilterEmployee;
	}

	public void setShowFilterEmployee(boolean showFilterEmployee) {
		this.showFilterEmployee = showFilterEmployee;
	}

	public void action_showFilterEmployee(ActionEvent actionEvent) {
		showFilterEmployee = true;
	}

	public void action_closeFilterEmployee(ActionEvent actionEvent) {
		showFilterEmployee = false;
	}

	public void action_filtroBusquedaEmpleado(ActionEvent actionEvent) {
		try {
			setListUsers(consultsService.employeesCIAT(parametroBusqueda
					.toUpperCase(), parametroBusqueda.toUpperCase(),
					parametroBusqueda));
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

	public String getParametroBusqueda() {
		return parametroBusqueda;
	}

	public void setParametroBusqueda(String parametroBusqueda) {
		this.parametroBusqueda = parametroBusqueda;
	}

	public List<Users> getListUsers() {
		return listUsers;
	}

	public void setListUsers(List<Users> listUsers) {
		this.listUsers = listUsers;
	}

	public void mostrarMensaje(String mensaje, boolean buttonCancel) {
		PopupMessagePage message = (PopupMessagePage) FacesUtils
				.getManagedBean("popupMessagePage");
		if (message != null)
			message.showPopup(mensaje, buttonCancel);
	}

	public void rowSelectionEmployee(RowSelectorEvent rowSelectorEvent) {
		String idUsuario = (String) idUser.getValue();

		for (Users users : listUsers) {

			if (idUsuario.equalsIgnoreCase(users.getUsrIdentificacion())) {

				carneAsignatario = users.getUsrIdentificacion();
				nombreAsignatario = users.getUsrNombre();
				showFilterEmployee = false;
				break;
			}
		}

	}

	public void rowSelectionAsignacion(RowSelectorEvent rowSelectorEvent) {
		Long idAsignacion = (Long) this.idAsignacion.getValue();

		for (VehiclesAssignation vehiclesAssignation : listAsignacionesUser) {

			if (idAsignacion.longValue() == vehiclesAssignation.getVhaCodigo()
					.longValue()) {
				placa = vehiclesAssignation.getVehicles()
						.getVhcPlacaDiplomatica();
				showAsignatioByCode = false;
				break;
			}
		}

	}

	public HtmlOutputText getIdUser() {
		return idUser;
	}

	public void setIdUser(HtmlOutputText idUser) {
		this.idUser = idUser;
	}

	public List<VehiclesAssignation> getListAsignaciones() {
		return listAsignacionesUser;
	}

	public void setListAsignaciones(List<VehiclesAssignation> listAsignaciones) {
		this.listAsignacionesUser = listAsignaciones;
	}

	public HtmlOutputText getIdAsignacion() {
		return idAsignacion;
	}

	public void setIdAsignacion(HtmlOutputText idAsignacion) {
		this.idAsignacion = idAsignacion;
	}

	public String getMotivoRetiro() {
		return motivoRetiro;
	}

	public void setMotivoRetiro(String motivoRetiro) {
		this.motivoRetiro = motivoRetiro;
	}

	public Date getRerFechaRetiro() {
		return rerFechaRetiro;
	}

	public void setRerFechaRetiro(Date rerFechaRetiro) {
		this.rerFechaRetiro = rerFechaRetiro;
	}

	public String getRerDescripcion() {
		return rerDescripcion;
	}

	public void setRerDescripcion(String rerDescripcion) {
		this.rerDescripcion = rerDescripcion;
	}
}
