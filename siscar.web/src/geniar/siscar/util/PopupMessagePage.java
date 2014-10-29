package geniar.siscar.util;

import geniar.siscar.view.accidents.AccidentActsPage;
import geniar.siscar.view.accidents.DataAccidentsPage;
import geniar.siscar.view.accidents.DriverPage;
import geniar.siscar.view.accidents.InvolvedVehiclesPage;
import geniar.siscar.view.accidents.AccidentFilesPage;
import geniar.siscar.view.billing.AccountingParametersPage;
import geniar.siscar.view.billing.DevolutionPrepaidPage;
import geniar.siscar.view.billing.PersonalMileage;
import geniar.siscar.view.billing.PrepaidFuelProofPage;
import geniar.siscar.view.billing.BillingAccountAutoInsurancePage;
import geniar.siscar.view.billing.GeneratorPlainFile;
import geniar.siscar.view.billing.PlainFileParameterPage;
import geniar.siscar.view.billing.PeriodPage;
import geniar.siscar.view.billing.ProofFuelPageInitial;
import geniar.siscar.view.billing.ProofVehicleAssignationPage;
import geniar.siscar.view.fuels.BuyingPrepaymentFuelPage;
import geniar.siscar.view.fuels.ControlTanksPage;
import geniar.siscar.view.fuels.FuelTanksPage;
import geniar.siscar.view.fuels.PumpsPage;
import geniar.siscar.view.fuels.ScheduleFuelPage;
import geniar.siscar.view.fuels.SchedulePumpsPage;
import geniar.siscar.view.news.DeliveryPage;
import geniar.siscar.view.parameters.FuelPerformancePage;
import geniar.siscar.view.parameters.LocationPage;
import geniar.siscar.view.parameters.ParametersPage;
import geniar.siscar.view.parameters.PolicyAssignementTypeControlPage;
import geniar.siscar.view.parameters.TariffsFuelPage;
import geniar.siscar.view.parameters.TariffsHouseCiatPage;
import geniar.siscar.view.parameters.TariffsRentPage;
import geniar.siscar.view.parameters.TarriffPage;
import geniar.siscar.view.parameters.ZonesPage;
import geniar.siscar.view.policies.NoveltiesPage;
import geniar.siscar.view.policies.PoliciesPage;
import geniar.siscar.view.policies.PoliciesTransactionsPage;
import geniar.siscar.view.policies.PolicyInvoicePage;
import geniar.siscar.view.security.OptionsPage;
import geniar.siscar.view.security.RollsPage;
import geniar.siscar.view.security.UserPage;
import geniar.siscar.view.vehicle.AssignmentChangePage;
import geniar.siscar.view.vehicle.AssignmentPage;
import geniar.siscar.view.vehicle.BrandPage;
import geniar.siscar.view.vehicle.FuelsTypesPage;
import geniar.siscar.view.vehicle.LinesPage;
import geniar.siscar.view.vehicle.ReservePage;
import geniar.siscar.view.vehicle.VehiclePage;
import geniar.siscar.view.vehicle.VehicleRetirementPage;
import geniar.siscar.view.vehicle.VehicleTypePage;
import gwork.exception.GWorkException;

import javax.faces.event.ActionEvent;

public class PopupMessagePage extends Object {

	private String mensaje;
	private boolean visible;
	private boolean buttonVisible;
	private boolean btnTrayAdmin = false;
	private static int RETIRAR = 1;
	private static int TRASLADAR = 2;
	private static int REEMPLAZAR = 3;
	private static int ELIMINAR = 2;
	private static int MODIFICAR = 1;
	private static Integer MODIFICAR_CABECERA_SOAT = -1;
	private static Integer RESERVAR = 1;
	private static Integer REASIGNARESERVA = 2;
	private static Integer GUARDAR_DETALLE = 2;
	private static Integer MODIFICARACC = 1;
	private static int GENERAR_ARCHIVO_PLANO = 1;
	private static int REGENERAR_DATOS = 2;
	private static int GUARDAR_DATOS = 1;
	private static Integer EnviarNotificacion = 2;
	private static Integer CREAR_CABECERA = 4;
	private static Integer CREAR_POLIZA = 4;
	private static Integer GUARDARASIGNATARIO = 1;
	private static Integer GENERAR_INCOSITENCIAS = 5;
	private static Integer GUARDAR_FACTURA_AP = 6;
	private static Integer Crear_Km_Personal = 1;

	private static int ELIMINAR_VEHICULO_INVOLUCRADO_TABLA = 1;
	private static int ELIMINAR_VEHICULO_INVOLUCRADO_FORM = 2;

	private LinesPage linesPage = (LinesPage) FacesUtils
			.getManagedBean("linesPage");
	private VehicleTypePage vehicleTypePage = (VehicleTypePage) FacesUtils
			.getManagedBean("vehicleTypePage");
	private PolicyAssignementTypeControlPage capPage = (PolicyAssignementTypeControlPage) FacesUtils
			.getManagedBean("policyAssignementTypeControlPage");
	private PoliciesPage policiesPage = (PoliciesPage) FacesUtils
			.getManagedBean("policiesPage");
	private PolicyInvoicePage policyInvoicePage = (PolicyInvoicePage) FacesUtils
			.getManagedBean("policyInvoicePage");
	private FuelPerformancePage fuelPerformancePage = (FuelPerformancePage) FacesUtils
			.getManagedBean("fuelPerformancePage");
	private ParametersPage parametersPage = (ParametersPage) FacesUtils
			.getManagedBean("parametersPage");
	private ZonesPage zonesPage = (ZonesPage) FacesUtils
			.getManagedBean("zonesPage");
	private BrandPage brandPage = (BrandPage) FacesUtils
			.getManagedBean("brandPage");
	private TariffsRentPage tariffsRentPage = (TariffsRentPage) FacesUtils
			.getManagedBean("tariffsRentPage");
	private TariffsHouseCiatPage tariffsHouseCiatPage = (TariffsHouseCiatPage) FacesUtils
			.getManagedBean("tariffsHouseCiatPage");
	private LocationPage locationPage = (LocationPage) FacesUtils
			.getManagedBean("locationPage");
	private TarriffPage tarriffPage = (TarriffPage) FacesUtils
			.getManagedBean("tarriffPage");
	private TariffsFuelPage tariffsFuelPage = (TariffsFuelPage) FacesUtils
			.getManagedBean("tariffsFuelPage");
	private PlainFileParameterPage fileParameterPage = (PlainFileParameterPage) FacesUtils
			.getManagedBean("plainFileParameterPage");
	private DeliveryPage deliveryPage = (DeliveryPage) FacesUtils
			.getManagedBean("deliveryPage");

	private FuelTanksPage fuelTanksPage = (FuelTanksPage) FacesUtils
			.getManagedBean("fuelTanksPage");

	private ControlTanksPage controlTanksPage = (ControlTanksPage) FacesUtils
			.getManagedBean("fuelControlPage");

	private PumpsPage pumpsPage = (PumpsPage) FacesUtils
			.getManagedBean("pumpsPage");

	private VehiclePage vehiclePage = (VehiclePage) FacesUtils
			.getManagedBean("vehiclePage");
	private VehicleRetirementPage vehicleRetirementPage = (VehicleRetirementPage) FacesUtils
			.getManagedBean("vehicleRetirementPage");
	private ReservePage reservePage = (ReservePage) FacesUtils
			.getManagedBean("reservePage");
	private AssignmentPage assignmentPage = (AssignmentPage) FacesUtils
			.getManagedBean("assignmentPage");
	private AccountingParametersPage accountingParametersPage = (AccountingParametersPage) FacesUtils
			.getManagedBean("accountingParametersPage");
	private FuelsTypesPage fuelsTypesPage = (FuelsTypesPage) FacesUtils
			.getManagedBean("fuelsTypesPage");
	private RollsPage rollsPage = (RollsPage) FacesUtils
			.getManagedBean("rollsPage");
	private DriverPage driverPage = (DriverPage) FacesUtils
			.getManagedBean("driverPage");

	private PoliciesTransactionsPage policiesTransactionsPage = (PoliciesTransactionsPage) FacesUtils
			.getManagedBean("policiesTransactionsPage");

	private UserPage userPage = (UserPage) FacesUtils
			.getManagedBean("userPage");

	private DataAccidentsPage dataAccidentsPage = (DataAccidentsPage) FacesUtils
			.getManagedBean("dataAccidentsPage");

	private AccidentActsPage accidentActsPage = (AccidentActsPage) FacesUtils
			.getManagedBean("accidentActsPage");

	private NoveltiesPage noveltiesPage = (NoveltiesPage) FacesUtils
			.getManagedBean("noveltiesPage");

	private OptionsPage optionsPage = (OptionsPage) FacesUtils
			.getManagedBean("optionsPage");

	private ScheduleFuelPage scheduleFuelPage = (ScheduleFuelPage) FacesUtils
			.getManagedBean("scheduleFuelPage");

	private SchedulePumpsPage schedulePumpsPage = (SchedulePumpsPage) FacesUtils
			.getManagedBean("schedulePumpsPage");

	private PeriodPage periodPage = (PeriodPage) FacesUtils
			.getManagedBean("periodPage");

	private BuyingPrepaymentFuelPage buyingPrepaymentFuelPage = (BuyingPrepaymentFuelPage) FacesUtils
			.getManagedBean("buyingPrepaymentFuelPage");

	private ProofFuelPageInitial proofFuelPageInitial = (ProofFuelPageInitial) FacesUtils
			.getManagedBean("proofFuelPageInitial");

	private GeneratorPlainFile GeneradorArchivoPlano = (GeneratorPlainFile) FacesUtils
			.getManagedBean("GeneratorPlainFile");

	private BillingAccountAutoInsurancePage BillingAccountAutoInsurancePage = (BillingAccountAutoInsurancePage) FacesUtils
			.getManagedBean("cobroAutoSeguro");

	private ProofVehicleAssignationPage proofVehicleAssignationPage = (ProofVehicleAssignationPage) FacesUtils
			.getManagedBean("proofVehicleAssignationPage");

	private PrepaidFuelProofPage prepaidFuelProofPage = (PrepaidFuelProofPage) FacesUtils
			.getManagedBean("prepaidFuelProofPage");

	private InvolvedVehiclesPage involvedVehiclesPage = (InvolvedVehiclesPage) FacesUtils
			.getManagedBean("involvedVehiclesPage");

	private AccidentFilesPage accidentFilesPage = (AccidentFilesPage) FacesUtils
			.getManagedBean("accidentFilesPage");

	private AccidentFilesPage accidentFilesPage2 = (AccidentFilesPage) FacesUtils
			.getManagedBean("accidentFilesPage");

	private DevolutionPrepaidPage devolutionPrepaidPage = (DevolutionPrepaidPage) FacesUtils
			.getManagedBean("devolutionPrepaidPage");

	private AssignmentChangePage assignmentChangePage = (AssignmentChangePage) FacesUtils
			.getManagedBean("assignmentChangePage");

	private PersonalMileage personalMileage = (PersonalMileage) FacesUtils
			.getManagedBean("personalMileage");

	public PopupMessagePage() {
	}

	// /GETTERS AND SETTERS
	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	// //EVENTOS//////

	public void aceptar_onclick(ActionEvent e) throws GWorkException {
		hidePopup();
		validarServicios();
	}

	public void validarServicios() throws GWorkException {
		validarEliminarCatalogo();

		if (optionsPage != null && optionsPage.isActivarConfirmacion()) {
			validarEliminarOpciones();
			optionsPage.setActivarConfirmacion(false);
		}

		if (userPage != null && userPage.isActivarConfirmacion()
				&& userPage.getOpcion() != null
				&& userPage.getOpcion().equalsIgnoreCase("1")) {
			validarModificarUsuario();
			userPage.setActivarConfirmacion(false);
		}

		if (userPage != null && userPage.isActivarConfirmacion()
				&& userPage.getOpcion() != null
				&& userPage.getOpcion().equalsIgnoreCase("2")) {
			validarEliminarUsuario();
			userPage.setActivarConfirmacion(false);
		}

		if (vehiclePage != null && vehiclePage.isActivarConfirmacion()) {
			validarModificarVehiculo();

			vehiclePage.setActivarConfirmacion(false);
		}

		if (rollsPage != null && rollsPage.isActivarConfirmacion()
				&& rollsPage.getOpcion() != null
				&& rollsPage.getOpcion().equalsIgnoreCase("2")) {
			validarEliminarRol();
			rollsPage.setActivarConfirmacion(false);
		}

		if (rollsPage != null && rollsPage.isActivarConfirmacion()
				&& rollsPage.getOpcion() != null
				&& rollsPage.getOpcion().equalsIgnoreCase("1")) {
			validarModificarRol();
			rollsPage.setActivarConfirmacion(false);
		}

		if (vehicleRetirementPage != null
				&& vehicleRetirementPage.isConfirmacion()) {
			validarRetirarVehiculo();
			vehicleRetirementPage.setConfirmacion(false);
		}

		if (linesPage != null && linesPage.getOpcConfirmacion() != null
				&& linesPage.getOpcConfirmacion() == ELIMINAR
				&& linesPage.isActivarConfirmacion()) {
			validarEliminarLinea();
			linesPage.setActivarConfirmacion(false);
		}

		if (linesPage != null && linesPage.getOpcConfirmacion() != null
				&& linesPage.getOpcConfirmacion() == MODIFICAR
				&& linesPage.isActivarConfirmacion()) {
			validarModificarLinea();
			linesPage.setActivarConfirmacion(false);
		}

		if (fuelPerformancePage != null
				&& fuelPerformancePage.getOpcConfirmacion() != null
				&& fuelPerformancePage.getOpcConfirmacion() == MODIFICAR
				&& fuelPerformancePage.isActivarConfirmacion()) {
			validarModificarFP();
			fuelPerformancePage.setActivarConfirmacion(false);
		}

		if (noveltiesPage != null && noveltiesPage.getOpcConfirmacion() != null
				&& noveltiesPage.getOpcConfirmacion() == GUARDAR_DETALLE
				&& noveltiesPage.isActivarConfirmacion()) {
			validarGuardarNovedades();
			noveltiesPage.setActivarConfirmacion(false);
		}

		if (policiesPage != null && policiesPage.getOpcConfirmacion() != null
				&& policiesPage.getOpcConfirmacion() == MODIFICAR
				&& policiesPage.isActivarConfirmacion()) {
			validarModificarPolicies();
			policiesPage.setActivarConfirmacion(false);
		}

		if (policiesPage != null && policiesPage.getOpcConfirmacion() != null
				&& policiesPage.getOpcConfirmacion() == REEMPLAZAR
				&& policiesPage.isActivarConfirmacion()) {
			validarReemplazarPolicies();
			policiesPage.setActivarConfirmacion(false);
		}

		if (policiesPage != null && policiesPage.getOpcConfirmacion() != null
				&& policiesPage.getOpcConfirmacion() == CREAR_POLIZA
				&& policiesPage.isActivarConfirmacion()) {
			crearPoliza();
			policiesPage.setActivarConfirmacion(false);
		}

		if (policyInvoicePage != null
				&& policyInvoicePage.getOpcConfirmacion() != null
				&& policyInvoicePage.getOpcConfirmacion() == MODIFICAR
				&& policyInvoicePage.isActivarConfirmacion()) {
			validarModificarPolicyInvoicePage();
			policyInvoicePage.setActivarConfirmacion(false);
		}

		if (policyInvoicePage != null
				&& policyInvoicePage.getOpcConfirmacion() != null
				&& policyInvoicePage.getOpcConfirmacion() == MODIFICAR_CABECERA_SOAT
				&& policyInvoicePage.isActivarConfirmacion()) {
			validarModificarPolicyInvoicePageSOAT();
			policyInvoicePage.setActivarConfirmacion(false);
		}

		if (policyInvoicePage != null
				&& policyInvoicePage.getOpcConfirmacion() != null
				&& policyInvoicePage.getOpcConfirmacion() == CREAR_CABECERA
				&& policyInvoicePage.isActivarConfirmacion()) {
			crearCabeceraFactura();
			policyInvoicePage.setActivarConfirmacion(false);
		}

		if (policyInvoicePage != null
				&& policyInvoicePage.getOpcConfirmacion() != null
				&& policyInvoicePage.getOpcConfirmacion() == GENERAR_INCOSITENCIAS
				&& policyInvoicePage.isActivarConfirmacion()) {
			generarInconsitenciasFactura();
			policyInvoicePage.setActivarConfirmacion(false);
		}

		if (policyInvoicePage != null
				&& policyInvoicePage.getOpcConfirmacion() != null
				&& policyInvoicePage.getOpcConfirmacion() == GUARDAR_FACTURA_AP
				&& policyInvoicePage.isActivarConfirmacion()) {
			GuardarFacturaAp();
			policyInvoicePage.setActivarConfirmacion(false);
		}

		if (capPage != null && capPage.getOpcConfirmacion() != null
				&& capPage.getOpcConfirmacion() == MODIFICAR
				&& capPage.isActivarConfirmacion()) {
			validarModificarCAP();
			capPage.setActivarConfirmacion(false);
		}

		if (fileParameterPage != null
				&& fileParameterPage.getOpcConfirmacion() != null
				&& fileParameterPage.getOpcConfirmacion() == MODIFICAR
				&& fileParameterPage.isActivarConfirmacion()) {
			validarModificarPFP();
			fileParameterPage.setActivarConfirmacion(false);
		}

		if (fuelTanksPage != null && fuelTanksPage.getOpcConfirmacion() != null
				&& fuelTanksPage.getOpcConfirmacion() == MODIFICAR
				&& fuelTanksPage.isActivarConfirmacion()) {
			validarModificarTanque();
			fuelTanksPage.setActivarConfirmacion(false);
		}

		if (fuelTanksPage != null && fuelTanksPage.getOpcConfirmacion() != null
				&& fuelTanksPage.getOpcConfirmacion() == ELIMINAR
				&& fuelTanksPage.isActivarConfirmacion()) {
			validarEliminarTanque();
			fuelTanksPage.setActivarConfirmacion(false);
		}

		if (controlTanksPage != null
				&& controlTanksPage.getOpcConfirmacion() != null
				&& controlTanksPage.getOpcConfirmacion() == MODIFICAR
				&& controlTanksPage.isActivarConfirmacion()) {
			validarModificarControlTanque();
			controlTanksPage.setActivarConfirmacion(false);
		}

		if (controlTanksPage != null
				&& controlTanksPage.getOpcConfirmacion() != null
				&& controlTanksPage.getOpcConfirmacion() == ELIMINAR
				&& controlTanksPage.isActivarConfirmacion()) {
			validarEliminarControlTanque();
			controlTanksPage.setActivarConfirmacion(false);
		}

		if (pumpsPage != null && pumpsPage.getOpcConfirmacion() != null
				&& pumpsPage.getOpcConfirmacion() == MODIFICAR
				&& pumpsPage.isActivarConfirmacion()) {
			validarModificarSurtidor();
			pumpsPage.setActivarConfirmacion(false);
		}

		if (pumpsPage != null && pumpsPage.getOpcConfirmacion() != null
				&& pumpsPage.getOpcConfirmacion() == ELIMINAR
				&& pumpsPage.isActivarConfirmacion()) {
			validarEliminarSurtidor();
			pumpsPage.setActivarConfirmacion(false);
		}

		if (accountingParametersPage != null
				&& accountingParametersPage.getOpcConfirmacion() != null
				&& accountingParametersPage.getOpcConfirmacion() == MODIFICAR
				&& accountingParametersPage.isActivarConfirmacion()) {
			validarModificarParametroContable();
			accountingParametersPage.setActivarConfirmacion(false);
		}

		if (accountingParametersPage != null
				&& accountingParametersPage.getOpcConfirmacion() != null
				&& accountingParametersPage.getOpcConfirmacion() == ELIMINAR
				&& accountingParametersPage.isActivarConfirmacion()) {
			validarEliminarParametroContable();
			accountingParametersPage.setActivarConfirmacion(false);
		}

		if (tariffsFuelPage != null
				&& tariffsFuelPage.getOpcConfirmacion() != null
				&& tariffsFuelPage.getOpcConfirmacion() == MODIFICAR
				&& tariffsFuelPage.isActivarConfirmacion()) {
			validarModificarTariffsFuelPage();
			tariffsFuelPage.setActivarConfirmacion(false);
		}

		if (vehicleTypePage != null
				&& vehicleTypePage.getOpcConfirmacion() != null
				&& vehicleTypePage.getOpcConfirmacion() == MODIFICAR) {

			validarModificarTipoVehiculo();
			vehicleTypePage.setActivarConfirmacion(false);
		}

		if (vehicleTypePage != null
				&& vehicleTypePage.getOpcConfirmacion() != null
				&& vehicleTypePage.getOpcConfirmacion() == ELIMINAR) {

			validarEliminarTipoVehiculo();
			vehicleTypePage.setActivarConfirmacion(false);
		}

		if (brandPage != null && brandPage.getOpcConfirmacion() != null
				&& brandPage.getOpcConfirmacion() == MODIFICAR
				&& brandPage.isActivarConfirmacion() == true) {

			validarModificarBrand();
			brandPage.setActivarConfirmacion(false);

		}

		if (brandPage != null && brandPage.getOpcConfirmacion() != null
				&& brandPage.getOpcConfirmacion() == ELIMINAR
				&& brandPage.isActivarConfirmacion() == true) {

			validarEliminarBrand();
			brandPage.setActivarConfirmacion(false);
		}

		if (parametersPage != null
				&& parametersPage.isActivarConfirmacion() == true) {
			validarModificarParametro();
			parametersPage.setActivarConfirmacion(false);
		}

		if (zonesPage != null && zonesPage.isActivarConfirmacion()
				&& zonesPage.getOpcConfirmacion() != null
				&& zonesPage.getOpcConfirmacion() == MODIFICAR) {

			validarModificarZonas();
			zonesPage.setActivarConfirmacion(false);
		}

		if (zonesPage != null && zonesPage.isActivarConfirmacion()
				&& zonesPage.getOpcConfirmacion() != null
				&& zonesPage.getOpcConfirmacion() == ELIMINAR) {

			validarEliminarZonas();
			zonesPage.setActivarConfirmacion(false);
		}

		if (tariffsRentPage != null && tariffsRentPage.isActivarConfirmacion()
				&& tariffsRentPage.getOpcConfirmacion() == MODIFICAR) {

			modificarTarifaAlquiler();
			tariffsRentPage.setActivarConfirmacion(false);
		}

		if (tariffsHouseCiatPage != null
				&& tariffsHouseCiatPage.isActivarConfirmacion()
				&& tariffsHouseCiatPage.getOpcConfirmacion() == MODIFICAR) {

			modificarTarifaCasaCIAT();
			tariffsHouseCiatPage.setActivarConfirmacion(false);

		}

		if (locationPage != null && locationPage.isActivarConfirmacion()
				&& locationPage.getOpcConfirmacion() == MODIFICAR) {
			modifcarLocation();
			locationPage.setActivarConfirmacion(false);
		}
		if (locationPage != null && locationPage.isActivarConfirmacion()
				&& locationPage.getOpcConfirmacion() == ELIMINAR) {
			eliminarLocation();
			locationPage.setActivarConfirmacion(false);
		}

		if (tarriffPage != null && tarriffPage.isActivarConfirmacion()) {

			modificarTarifaAsifnacion();
			tarriffPage.setActivarConfirmacion(false);
		}

		if (deliveryPage != null && deliveryPage.isActivarConfirmacion()) {
			entregarVehiculo();
			deliveryPage.setActivarConfirmacion(false);
		}

		if (reservePage != null && reservePage.getOpcConfirmacion() != null
				&& reservePage.isActivarConfirmacion()
				&& reservePage.getOpcConfirmacion() == RESERVAR) {

			reservar();
			reservePage.setActivarConfirmacion(false);
		}

		if (reservePage != null && reservePage.getOpcConfirmacion() != null
				&& reservePage.isActivarConfirmacion()
				&& reservePage.getOpcConfirmacion() == REASIGNARESERVA) {

			reasignarReserva();
			reservePage.setActivarConfirmacion(false);
		}

		if (assignmentPage != null && assignmentPage.isActivarConfirmacion()) {

			asignarSolicitud();
			assignmentPage.setActivarConfirmacion(false);

		}

		if (fuelsTypesPage != null
				&& fuelsTypesPage.getOpcConfirmacion() != null
				&& fuelsTypesPage.isActivarConfirmacion()
				&& fuelsTypesPage.getOpcConfirmacion() == MODIFICAR) {

			modificarFuelType();
			fuelsTypesPage.setActivarConfirmacion(false);
		}

		if (fuelsTypesPage != null
				&& fuelsTypesPage.getOpcConfirmacion() != null
				&& fuelsTypesPage.isActivarConfirmacion()
				&& fuelsTypesPage.getOpcConfirmacion() == ELIMINAR) {

			eliminarFuelType();
			fuelsTypesPage.setActivarConfirmacion(false);
		}

		if (policiesTransactionsPage != null
				&& policiesTransactionsPage.getOpcConfirmacion() != null
				&& policiesTransactionsPage.isActivarConfirmacion()
				&& policiesTransactionsPage.getOpcConfirmacion() == RETIRAR) {

			validarRetirarVehiculoPoliza();
			policiesTransactionsPage.setActivarConfirmacion(false);
		}

		if (policiesTransactionsPage != null
				&& policiesTransactionsPage.getOpcConfirmacion() != null
				&& policiesTransactionsPage.isActivarConfirmacion()
				&& policiesTransactionsPage.getOpcConfirmacion() == TRASLADAR) {

			validarTrasladarVehiculoPoliza();
			policiesTransactionsPage.setActivarConfirmacion(false);
		}

		if (driverPage != null && driverPage.getOpcConfirmacion() != null
				&& driverPage.isActivarConfirmacion()
				&& driverPage.getOpcConfirmacion() == MODIFICAR) {

			validarModificarConductor();
			driverPage.setActivarConfirmacion(false);
		}

		if (driverPage != null && driverPage.getOpcConfirmacion() != null
				&& driverPage.isActivarConfirmacion()
				&& driverPage.getOpcConfirmacion() == ELIMINAR) {

			validarEliminarConductor();
			driverPage.setActivarConfirmacion(false);
		}

		if (dataAccidentsPage != null
				&& dataAccidentsPage.getOpcConfirmacion() != null
				&& dataAccidentsPage.isActivarConfirmacion()
				&& dataAccidentsPage.getOpcConfirmacion() == MODIFICAR) {
			validarModificarDatosAccidente();
			dataAccidentsPage.setActivarConfirmacion(false);
		}

		if (accidentActsPage != null
				&& accidentActsPage.isActivarConfirmacion()
				&& accidentActsPage.getOpcModificarAcc() == null) {

			validarModificarActa();
			accidentActsPage.setActivarConfirmacion(false);
		}
		if (accidentActsPage != null
				&& accidentActsPage.isActivarConfirmacion()
				&& accidentActsPage.getOpcModificarAcc() != null
				&& accidentActsPage.getOpcModificarAcc() == MODIFICARACC) {

			modificarActaAcc();
			accidentActsPage.setActivarConfirmacion(false);
			accidentActsPage.setOpcModificarAcc(null);
		}

		if (scheduleFuelPage != null
				&& scheduleFuelPage.isActivarConfirmacion()) {

			modificarPlanillaTanque();
			scheduleFuelPage.setActivarConfirmacion(false);
		}

		if (schedulePumpsPage != null
				&& schedulePumpsPage.isActivarConfirmacion()) {

			modificarPlanillaSurtidor();
			schedulePumpsPage.setActivarConfirmacion(false);
		}

		if (periodPage != null && periodPage.isActivarConfirmacion()
				&& periodPage.getOpcConfirmacion() != null
				&& periodPage.getOpcConfirmacion() == 1) {

			validarModificarPeriodo();
			periodPage.setActivarConfirmacion(false);
		}

		if (periodPage != null && periodPage.isActivarConfirmacion()
				&& periodPage.getOpcConfirmacion() != null
				&& periodPage.getOpcConfirmacion() == 2) {

			validarEliminarPeriodo();
			periodPage.setActivarConfirmacion(false);
		}

		if (buyingPrepaymentFuelPage != null
				&& buyingPrepaymentFuelPage.isActivarConfirmacion()) {
			validarCompraPrepago();
			buyingPrepaymentFuelPage.setActivarConfirmacion(false);
		}

		if (proofFuelPageInitial != null
				&& proofFuelPageInitial.isActivarConfirmacion()) {
			validarComprobanteIniPrepago();
			proofFuelPageInitial.setActivarConfirmacion(false);
		}

		if (GeneradorArchivoPlano != null
				&& GeneradorArchivoPlano.isActivarConfirmacion()
				&& GeneradorArchivoPlano.getOpcConfirmacion() != null
				&& GeneradorArchivoPlano.getOpcConfirmacion() == GENERAR_ARCHIVO_PLANO) {

			validarGenerarArchivoPlano();
			GeneradorArchivoPlano.setActivarConfirmacion(false);
		}

		if (GeneradorArchivoPlano != null
				&& GeneradorArchivoPlano.isActivarConfirmacion()
				&& GeneradorArchivoPlano.getOpcConfirmacion() != null
				&& GeneradorArchivoPlano.getOpcConfirmacion() == REGENERAR_DATOS) {

			validarReGenerarDatos();
			GeneradorArchivoPlano.setActivarConfirmacion(false);
		}

		if (accidentActsPage != null
				&& accidentActsPage.isActivarConfirmacion()
				&& accidentActsPage.getOpcModificarAcc() != null
				&& accidentActsPage.getOpcModificarAcc() == EnviarNotificacion) {

			validarEnvioNotificacion();
			accidentActsPage.setActivarConfirmacion(false);
		}

		if (BillingAccountAutoInsurancePage != null
				&& BillingAccountAutoInsurancePage.isActivarConfirmacion()
				&& BillingAccountAutoInsurancePage.getOpcConfirmacion() != null
				&& BillingAccountAutoInsurancePage.getOpcConfirmacion() == GUARDAR_DATOS) {

			GuardarDatosCobroAutoSeguro();
			BillingAccountAutoInsurancePage.setActivarConfirmacion(false);
		}

		if (proofVehicleAssignationPage != null
				&& proofVehicleAssignationPage.isActivarConfirmacion() == true) {
			validarComprobanteAsignacion();
			proofVehicleAssignationPage.setActivarConfirmacion(false);
		}

		if (prepaidFuelProofPage != null
				&& prepaidFuelProofPage.isActivarConfirmacion()
				&& prepaidFuelProofPage.getOpcConfirmacion() != null
				&& prepaidFuelProofPage.getOpcConfirmacion() == GUARDAR_DATOS) {

			GuardarCompCombustiblePrepago();
			prepaidFuelProofPage.setActivarConfirmacion(false);
		}

		if (involvedVehiclesPage != null
				&& involvedVehiclesPage.isActivarConfirmacion() == true
				&& involvedVehiclesPage.getOpcConfirmacion() != null
				&& involvedVehiclesPage.getOpcConfirmacion() == ELIMINAR_VEHICULO_INVOLUCRADO_TABLA) {
			validarEliminacionVehiculoInvolucradoTabla();
			involvedVehiclesPage.setActivarConfirmacion(true);
		}
		if (involvedVehiclesPage != null
				&& involvedVehiclesPage.isActivarConfirmacion() == true
				&& involvedVehiclesPage.getOpcConfirmacion() != null
				&& involvedVehiclesPage.getOpcConfirmacion() == ELIMINAR_VEHICULO_INVOLUCRADO_FORM) {
			validarEliminacionVehiculoInvolucradoForm();
			involvedVehiclesPage.setActivarConfirmacion(true);
		}

		if (accidentFilesPage != null
				&& accidentFilesPage.isActivarConfirmacion()
				&& accidentFilesPage.getOpcEliminarArchivo() != null
				&& accidentFilesPage.getOpcEliminarArchivo() == ELIMINAR) {

			EliminarArchivo();
			accidentFilesPage.setActivarConfirmacion(false);
		} else if (accidentFilesPage2 != null
				&& accidentFilesPage2.getActivarConfirmacion2()
				&& accidentFilesPage2.getOpcEliminarArchivo2() != null
				&& accidentFilesPage2.getOpcEliminarArchivo2() == ELIMINAR) {

			EliminarArchivo2();
			accidentFilesPage2.setActivarConfirmacion2(false);
		}

		if (devolutionPrepaidPage != null
				&& devolutionPrepaidPage.isActivarConfirmacion() == true) {
			validarComprobanteDevolucionPrepago();
			devolutionPrepaidPage.setActivarConfirmacion(false);
		}

		if (assignmentChangePage != null
				&& assignmentChangePage.isShowConfirmarNovedadAsignacion() == true
				&& assignmentChangePage.getOpcConfirmacion() == GUARDARASIGNATARIO) {
			GuardarAsignatario();
			assignmentChangePage.setShowConfirmarNovedadAsignacion(false);
		}

		if (personalMileage != null
				&& personalMileage.getOpcConfirmacion() != null
				&& personalMileage.getOpcConfirmacion() == MODIFICAR
				&& personalMileage.isActivarConfirmacion()) {
			modificarFlatFileKmPersonal();
			personalMileage.setActivarConfirmacion(false);
		}

	}

	public void validarEliminacionVehiculoInvolucradoTabla()
			throws GWorkException {
		ManageBeanServices beanService = (ManageBeanServices) FacesUtils
				.getManagedBean("beanService");
		if (beanService != null) {
			beanService.eliminacionVehiculoInvolucradoTabla();
		}
	}

	public void validarEliminacionVehiculoInvolucradoForm()
			throws GWorkException {
		ManageBeanServices beanService = (ManageBeanServices) FacesUtils
				.getManagedBean("beanService");
		if (beanService != null) {
			beanService.eliminacionVehiculoInvolucradoForm();
		}
	}

	private void validarTrasladarVehiculoPoliza() throws GWorkException {
		ManageBeanServices beanService = (ManageBeanServices) FacesUtils
				.getManagedBean("beanService");
		if (beanService != null) {
			beanService.trasladarVehiculoPoliza();
		}
	}

	private void validarRetirarVehiculoPoliza() throws GWorkException {
		ManageBeanServices beanService = (ManageBeanServices) FacesUtils
				.getManagedBean("beanService");
		if (beanService != null) {
			beanService.retirarVehiculoPoliza();
		}
	}

	private void validarRetirarVehiculo() throws GWorkException {
		ManageBeanServices beanService = (ManageBeanServices) FacesUtils
				.getManagedBean("beanService");
		if (beanService != null) {
			beanService.retirarVehiculo();
		}
	}

	private void validarEliminarParametroContable() {
		ManageBeanServices beanService = (ManageBeanServices) FacesUtils
				.getManagedBean("beanService");
		if (beanService != null) {
			beanService.eliminarParametroContable();
		}
	}

	private void validarModificarParametroContable() {
		ManageBeanServices beanService = (ManageBeanServices) FacesUtils
				.getManagedBean("beanService");
		if (beanService != null) {
			beanService.modificarParametroContable();
		}
	}

	private void validarEliminarSurtidor() {
		ManageBeanServices beanService = (ManageBeanServices) FacesUtils
				.getManagedBean("beanService");
		if (beanService != null) {
			beanService.eliminarSurtidor();
		}
	}

	private void validarModificarSurtidor() {
		ManageBeanServices beanService = (ManageBeanServices) FacesUtils
				.getManagedBean("beanService");
		if (beanService != null) {
			beanService.modificarSurtidor();
		}
	}

	private void validarEliminarTanque() {
		ManageBeanServices beanService = (ManageBeanServices) FacesUtils
				.getManagedBean("beanService");
		if (beanService != null) {
			beanService.eliminarFuelTank();
		}
	}

	private void validarModificarTanque() {
		ManageBeanServices beanService = (ManageBeanServices) FacesUtils
				.getManagedBean("beanService");
		if (beanService != null) {
			beanService.modificarFuelTank();
		}
	}

	private void validarEliminarControlTanque() {
		ManageBeanServices beanService = (ManageBeanServices) FacesUtils
				.getManagedBean("beanService");
		if (beanService != null) {
			beanService.eliminarControlFuelTank();
		}
	}

	private void validarModificarControlTanque() {
		ManageBeanServices beanService = (ManageBeanServices) FacesUtils
				.getManagedBean("beanService");
		if (beanService != null) {
			beanService.modificarControlFuelTank();
		}
	}

	public void validarModificarVehiculo() throws GWorkException {

		ManageBeanServices beanService = (ManageBeanServices) FacesUtils
				.getManagedBean("beanService");
		if (beanService != null) {
			beanService.modificarVehiculo();
		}
	}

	public void validarEliminarRol() throws GWorkException {

		ManageBeanServices beanService = (ManageBeanServices) FacesUtils
				.getManagedBean("beanService");
		if (beanService != null)
			beanService.eliminarRoles();
	}

	public void validarModificarRol() throws GWorkException {

		ManageBeanServices beanService = (ManageBeanServices) FacesUtils
				.getManagedBean("beanService");
		if (beanService != null)
			beanService.modificarRoles();
	}

	public void validarEliminarUsuario() throws GWorkException {

		ManageBeanServices beanService = (ManageBeanServices) FacesUtils
				.getManagedBean("beanService");
		if (beanService != null)
			beanService.eliminarUsuarios();
	}

	public void validarModificarUsuario() throws GWorkException {

		ManageBeanServices beanService = (ManageBeanServices) FacesUtils
				.getManagedBean("beanService");
		if (beanService != null)
			beanService.modificarUsuarios();
	}

	public void validarEliminarOpciones() throws GWorkException {

		ManageBeanServices beanService = (ManageBeanServices) FacesUtils
				.getManagedBean("beanService");
		if (beanService != null)
			beanService.eliminarOpciones();
	}

	private void validarModificarPFP() {
		ManageBeanServices beanService = (ManageBeanServices) FacesUtils
				.getManagedBean("beanService");
		if (beanService != null) {
			beanService.modificarPFP();
		}
	}

	private void validarModificarTariffsFuelPage() {
		ManageBeanServices beanService = (ManageBeanServices) FacesUtils
				.getManagedBean("beanService");
		if (beanService != null) {
			beanService.modificarTariffsFuelPage();
		}
	}

	private void crearCabeceraFactura() {

		if (policyInvoicePage != null) {
			policyInvoicePage.crearCabeceraFacturaPoliza();
		}
	}

	private void generarInconsitenciasFactura() {

		if (policyInvoicePage != null) {
			policyInvoicePage.guardarInconsitenciasFactura();
		}
	}

	private void GuardarFacturaAp() {

		if (policyInvoicePage != null) {
			policyInvoicePage.GuardarFacturaAp();
		}
	}

	private void validarModificarPolicyInvoicePage() {
		ManageBeanServices beanService = (ManageBeanServices) FacesUtils
				.getManagedBean("beanService");
		if (beanService != null) {
			beanService.modificarPolicyInvoice();
		}
	}

	void validarModificarPolicyInvoicePageSOAT() {

		if (policyInvoicePage != null) {
			policyInvoicePage.action_modificarFacturaPolizaSOAT();
		}
	}

	public void validarEliminarLinea() throws GWorkException {
		ManageBeanServices beanService = (ManageBeanServices) FacesUtils
				.getManagedBean("beanService");
		if (beanService != null) {
			beanService.eliminarLinea();
		}
	}

	public void validarModificarLinea() throws GWorkException {

		ManageBeanServices beanService = (ManageBeanServices) FacesUtils
				.getManagedBean("beanService");
		if (beanService != null) {
			beanService.modificarLinea();
		}
	}

	/**
	 * Inicia el proceso de modificación de un objeto ControlAssignationPolicy.
	 * 
	 * @throws GWorkException
	 *             Manejador de excepciones
	 */
	public void validarModificarCAP() throws GWorkException {

		ManageBeanServices beanService = (ManageBeanServices) FacesUtils
				.getManagedBean("beanService");
		if (beanService != null) {
			beanService.modificarCAP();
		}
	}

	/**
	 * Inicia el proceso de modificación de un objeto FuelPerformance.
	 * 
	 * @throws GWorkException
	 *             Manejador de excepciones
	 */
	public void validarModificarFP() throws GWorkException {

		ManageBeanServices beanService = (ManageBeanServices) FacesUtils
				.getManagedBean("beanService");
		if (beanService != null) {
			beanService.modificarFuelPerformance();
		}
	}

	/**
	 * Inicia el proceso de reemplazo de un objeto Policies.
	 * 
	 * @throws GWorkException
	 *             Manejador de excepciones
	 */
	public void validarReemplazarPolicies() throws GWorkException {

		ManageBeanServices beanService = (ManageBeanServices) FacesUtils
				.getManagedBean("beanService");
		if (beanService != null) {
			beanService.reemplazarPolicies();
		}
	}

	/**
	 * Inicia el proceso de Creacion de poliza.
	 * 
	 * @throws GWorkException
	 *             Manejador de excepciones
	 */
	public void crearPoliza() throws GWorkException {

		if (policiesPage != null) {
			policiesPage.crearPoliza();
		}
	}

	/**
	 * Inicia el proceso de modificación de un objeto Policies.
	 * 
	 * @throws GWorkException
	 *             Manejador de excepciones
	 */
	public void validarModificarPolicies() throws GWorkException {
		ManageBeanServices beanService = (ManageBeanServices) FacesUtils
				.getManagedBean("beanService");
		if (beanService != null) {
			beanService.modificarPolicies();
		}
	}

	public void validarGuardarNovedades() throws GWorkException {
		ManageBeanServices beanService = (ManageBeanServices) FacesUtils
				.getManagedBean("beanService");
		if (beanService != null) {
			beanService.guardarNovedades();
		}
	}

	public void validarEliminarCatalogo() throws GWorkException {
		String idCatalogo = (String) FacesUtils.getSession().getAttribute(
				"idCatalogo");
		String idModelo = (String) FacesUtils.getSession().getAttribute(
				"idModelo");
		if (idCatalogo != null && idModelo != null) {
			ManageBeanServices beanService = (ManageBeanServices) FacesUtils
					.getManagedBean("beanService");
			if (beanService != null) {
				beanService.eliminarCatalogo(new Long(idCatalogo), new Long(
						idModelo));
			}
		}
	}

	public void cancelar_onclick(ActionEvent e) {
		hidePopup();
	}

	// //METODOS
	public void showPopup(String mensaje, boolean buttonCancel) {
		setMensaje(mensaje);
		setVisible(true);
		setButtonVisible(buttonCancel);
	}

	public void hidePopup() {
		setVisible(false);
	}

	public boolean isButtonVisible() {
		return buttonVisible;
	}

	public void setButtonVisible(boolean buttonVisible) {
		this.buttonVisible = buttonVisible;
	}

	public void validarEliminarTipoVehiculo() throws GWorkException {
		ManageBeanServices beanService = (ManageBeanServices) FacesUtils
				.getManagedBean("beanService");
		if (beanService != null) {
			beanService.eliminarTipoVehiculo();
		}
	}

	public void validarModificarTipoVehiculo() {
		ManageBeanServices beanService = (ManageBeanServices) FacesUtils
				.getManagedBean("beanService");
		if (beanService != null) {
			beanService.modificarTipoVehiculo();
		}
	}

	public void validarModificarParametro() {
		ManageBeanServices beanServices = (ManageBeanServices) FacesUtils
				.getManagedBean("beanService");
		if (beanServices != null) {
			beanServices.modificarParametros();
		}
	}

	public void validarModificarBrand() {
		ManageBeanServices beanServices = (ManageBeanServices) FacesUtils
				.getManagedBean("beanService");
		if (beanServices != null) {
			beanServices.modificarBrand();
		}
	}

	public void validarEliminarBrand() {
		ManageBeanServices beanServices = (ManageBeanServices) FacesUtils
				.getManagedBean("beanService");
		if (beanServices != null) {
			beanServices.eliminarBrand();
		}
	}

	public void validarModificarZonas() {
		if (zonesPage != null) {
			zonesPage.modificarZona();
		}
	}

	public void validarEliminarZonas() {
		if (zonesPage != null) {
			zonesPage.eliminarZona();
		}
	}

	public void modificarTarifaAlquiler() {
		if (tariffsRentPage != null) {
			tariffsRentPage.crearTarifaAlquiler();
		}

	}

	public void modificarTarifaCasaCIAT() {
		if (tariffsHouseCiatPage != null) {
			tariffsHouseCiatPage.crearTarifaCasaCIAT();
		}

	}

	public void modifcarLocation() {
		if (locationPage != null) {
			locationPage.modificarLugar();
		}
	}

	public void eliminarLocation() {
		if (locationPage != null) {
			locationPage.elminarLugar();
		}
	}

	public void modificarTarifaAsifnacion() {

		if (tarriffPage != null) {
			tarriffPage.modificarTarifa();
		}
	}

	public void entregarVehiculo() {

		if (deliveryPage != null) {
			deliveryPage.crearEntrega();
		}
	}

	public void reservar() {
		if (reservePage != null) {
			reservePage.reservar();
		}
	}

	public void reasignarReserva() {
		if (reservePage != null) {
			reservePage.reasignarReserva();
		}
	}

	public boolean isBtnTrayAdmin() {
		return btnTrayAdmin;
	}

	public void setBtnTrayAdmin(boolean btnTrayAdmin) {
		this.btnTrayAdmin = btnTrayAdmin;
	}

	public void showBtnTrayAdmin(String mensaje) {

		btnTrayAdmin = true;
		setMensaje(mensaje);

	}

	public String btnTrayAdmin_aceptar() {

		btnTrayAdmin = false;
		return NavigationResults.BANDEJA_ADMIN;

	}

	public void asignarSolicitud() {

		if (assignmentPage != null) {
			assignmentPage.asignarSolicitud();
		}

	}

	public void modificarFuelType() {
		if (fuelsTypesPage != null) {
			fuelsTypesPage.modificarFuelTypes();
		}
	}

	public void eliminarFuelType() {
		if (fuelsTypesPage != null) {
			fuelsTypesPage.elminarFuelTypes();
		}
	}

	public void validarModificarConductor() {

		if (driverPage != null) {
			driverPage.modificarConductor();
		}
	}

	public void validarEliminarConductor() {

		if (driverPage != null) {
			driverPage.elminarConductor();
		}
	}

	public void validarModificarDatosAccidente() {
		if (dataAccidentsPage != null) {
			dataAccidentsPage.modificarAccidente();
		}
	}

	public void validarModificarActa() {
		if (accidentActsPage != null) {
			accidentActsPage.modicarActa();
		}
	}

	public void modificarActaAcc() {
		if (accidentActsPage != null) {
			accidentActsPage.modificarAccidente();
		}
	}

	public void modificarPlanillaTanque() {
		if (scheduleFuelPage != null) {
			scheduleFuelPage.modificarPlanillaTanque();
		}
	}

	public void modificarPlanillaSurtidor() {
		if (schedulePumpsPage != null) {
			schedulePumpsPage.modificarPlanillaSurtidor();
		}
	}

	public void validarEliminarPeriodo() throws GWorkException {

		ManageBeanServices beanService = (ManageBeanServices) FacesUtils
				.getManagedBean("beanService");
		if (beanService != null)
			beanService.eliminarPeriodos();
	}

	public void validarModificarPeriodo() throws GWorkException {

		ManageBeanServices beanService = (ManageBeanServices) FacesUtils
				.getManagedBean("beanService");
		if (beanService != null)
			beanService.modificarPeriodos();
	}

	public void validarCompraPrepago() {
		if (buyingPrepaymentFuelPage != null) {
			buyingPrepaymentFuelPage.crearCompra();
		}
	}

	public void validarComprobanteIniPrepago() {
		if (proofFuelPageInitial != null) {

			proofFuelPageInitial.generarComrobante();
		}
	}

	public void validarGenerarArchivoPlano() throws GWorkException {

		ManageBeanServices beanService = (ManageBeanServices) FacesUtils
				.getManagedBean("beanService");
		if (beanService != null)
			beanService.GenerarArchivoPlano();
	}

	public void validarReGenerarDatos() throws GWorkException {

		ManageBeanServices beanService = (ManageBeanServices) FacesUtils
				.getManagedBean("beanService");
		if (beanService != null)
			beanService.ReGenerarArchivoPlano();
	}

	public void GuardarDatosCobroAutoSeguro() throws GWorkException {

		ManageBeanServices beanService = (ManageBeanServices) FacesUtils
				.getManagedBean("beanService");
		if (beanService != null)
			beanService.GuardarDatosAutoSeguro();
	}

	public void validarComprobanteAsignacion() {

		if (proofVehicleAssignationPage != null)
			proofVehicleAssignationPage.genearComprobanteAsignacion();
	}

	public void GuardarCompCombustiblePrepago() throws GWorkException {
		ManageBeanServices beanService = (ManageBeanServices) FacesUtils
				.getManagedBean("beanService");
		if (beanService != null)
			beanService.GuardarCombustiblePrepago();
	}

	public void EliminarArchivo() throws GWorkException {
		ManageBeanServices beanService = (ManageBeanServices) FacesUtils
				.getManagedBean("beanService");
		if (beanService != null)
			beanService.EliminarArchivo();
	}

	public void EliminarArchivo2() throws GWorkException {
		ManageBeanServices beanService = (ManageBeanServices) FacesUtils
				.getManagedBean("beanService");
		if (beanService != null)
			beanService.EliminarArchivo2();
	}

	public void validarEnvioNotificacion() throws GWorkException {

		ManageBeanServices beanService = (ManageBeanServices) FacesUtils
				.getManagedBean("beanService");
		if (beanService != null)
			beanService.enviarNotificacion();
	}

	public void validarComprobanteDevolucionPrepago() {

		if (devolutionPrepaidPage != null)
			devolutionPrepaidPage.generarComrobante();
	}

	public void GuardarAsignatario() throws GWorkException {
		if (assignmentChangePage != null)
			assignmentChangePage.GuardarAsignatario();
	}

	@SuppressWarnings("unused")
	private void GuardarFlatFileKmPersonal() {
		if (personalMileage != null) {
			personalMileage.crearKmPersonal();
		}
	}

	private void modificarFlatFileKmPersonal() {
		if (personalMileage != null) {
			personalMileage.modificarKmPersonal();
		}
	}
}
