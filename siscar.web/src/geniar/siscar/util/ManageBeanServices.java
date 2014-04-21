package geniar.siscar.util;

import geniar.siscar.logic.consultas.SearchVehicles;
import geniar.siscar.logic.vehicle.services.SupplyingService;
import geniar.siscar.logic.vehicle.services.VehicleService;
import geniar.siscar.model.SupplyingCatalogs;
import geniar.siscar.view.accidents.AccidentActsPage;
import geniar.siscar.view.accidents.AccidentFilesPage;
import geniar.siscar.view.accidents.InvolvedVehiclesPage;
import geniar.siscar.view.billing.AccountingParametersPage;
import geniar.siscar.view.billing.BillingAccountAutoInsurancePage;
import geniar.siscar.view.billing.PrepaidFuelProofPage;
import geniar.siscar.view.billing.GeneratorPlainFile;
import geniar.siscar.view.billing.PeriodPage;
import geniar.siscar.view.billing.PlainFileParameterPage;
import geniar.siscar.view.fuels.ControlTanksPage;
import geniar.siscar.view.fuels.FuelTanksPage;
import geniar.siscar.view.fuels.PumpsPage;
import geniar.siscar.view.parameters.FuelPerformancePage;
import geniar.siscar.view.parameters.ParametersPage;
import geniar.siscar.view.parameters.PolicyAssignementTypeControlPage;
import geniar.siscar.view.parameters.TariffsFuelPage;
import geniar.siscar.view.policies.NoveltiesPage;
import geniar.siscar.view.policies.PoliciesPage;
import geniar.siscar.view.policies.PoliciesTransactionsPage;
import geniar.siscar.view.policies.PolicyInvoicePage;
import geniar.siscar.view.security.OptionsPage;
import geniar.siscar.view.security.RollsPage;
import geniar.siscar.view.security.UserPage;
import geniar.siscar.view.vehicle.BrandPage;
import geniar.siscar.view.vehicle.LinesPage;
import geniar.siscar.view.vehicle.VehiclePage;
import geniar.siscar.view.vehicle.VehicleRetirementPage;
import geniar.siscar.view.vehicle.VehicleSupplyingPage;
import geniar.siscar.view.vehicle.VehicleTypePage;
import gwork.exception.GWorkException;

import java.util.List;

public class ManageBeanServices {

	private VehicleService vehicleService;
	private SupplyingService supplyingService;
	private LinesPage linesPage;
	private VehicleTypePage vehicleTypePage;
	private PolicyAssignementTypeControlPage capPage;
	private FuelPerformancePage fuelPerformancePage;
	private PoliciesPage policiesPage;
	private NoveltiesPage noveltiesPage;
	private ParametersPage parametersPage;
	private BrandPage brandPage;
	private PolicyInvoicePage policyInvoicePage;
	private TariffsFuelPage tariffsFuelPage;
	private PlainFileParameterPage fileParameterPage;
	private VehiclePage vehiclePage;
	private FuelTanksPage fuelTanksPage;
	private ControlTanksPage controlTanksPage;
	private PumpsPage pumpsPage;
	private VehicleRetirementPage vehicleRetirementPage;
	private AccountingParametersPage accountingParametersPage;
	private RollsPage rollsPage;
	private UserPage userPage;
	private PoliciesTransactionsPage policiesTransactionsPage;
	private OptionsPage optionsPage;
	private PeriodPage periodPage;
	private GeneratorPlainFile GeneratorPlainFile;
	private BillingAccountAutoInsurancePage BillingAccountAutoInsurancePage;
	private PrepaidFuelProofPage prepaidFuelProofPage;
	private AccidentFilesPage accidentFilesPage;
	private AccidentActsPage accidentActsPage;

	private InvolvedVehiclesPage involvedVehiclesPage;

	public void setInvolvedVehiclesPage(
			InvolvedVehiclesPage involvedVehiclesPage) {
		this.involvedVehiclesPage = involvedVehiclesPage;
	}

	public InvolvedVehiclesPage getInvolvedVehiclesPage() {
		return involvedVehiclesPage;
	}

	public void eliminacionVehiculoInvolucradoTabla() throws GWorkException {
		involvedVehiclesPage = (InvolvedVehiclesPage) FacesUtils
				.getManagedBean("involvedVehiclesPage");
		if (involvedVehiclesPage != null) {
			involvedVehiclesPage.eliminarVehiculoInvolucradoTabla();
		}
	}

	public void eliminacionVehiculoInvolucradoForm() throws GWorkException {
		involvedVehiclesPage = (InvolvedVehiclesPage) FacesUtils
				.getManagedBean("involvedVehiclesPage");
		if (involvedVehiclesPage != null) {
			involvedVehiclesPage.eliminarVehiculoInvolucradoForm();
		}
	}

	public VehicleService getVehicleService() {
		return vehicleService;
	}

	public void setVehicleService(VehicleService vehicleService) {
		this.vehicleService = vehicleService;
	}

	public void eliminarCatalogo(Long idCatalogo, Long idModelo)
			throws GWorkException {
		try {
			VehicleSupplyingPage vehicleSupplyingPage = (VehicleSupplyingPage) FacesUtils
					.getManagedBean("vehicleSupplyingPage");

			if (vehicleSupplyingPage != null) {
				List<SupplyingCatalogs> listSupplyingCatalogs = null;
				supplyingService.EliminarCatalogo(idCatalogo, idModelo);
				if (idModelo != null) {
					listSupplyingCatalogs = SearchVehicles
							.consultarCatalogoPorIdModelo(idModelo);
					vehicleSupplyingPage
							.setListSupplyingCatalogs(listSupplyingCatalogs);
					if (listSupplyingCatalogs != null
							&& listSupplyingCatalogs.size() > 0) {
						for (SupplyingCatalogs supplyingCatalogs : listSupplyingCatalogs) {
							supplyingCatalogs.getLines().getBrands()
									.getBrnNombre();
							supplyingCatalogs.getLines().getLnsNombre();
						}
					}
				}
				FacesUtils.getSession().removeAttribute("idCatalogo");
				FacesUtils.getSession().removeAttribute("idModelo");

				if (vehicleSupplyingPage.getListSupplyingCatalogs() == null)
					vehicleSupplyingPage.limpiar();
			}
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public SupplyingService getSupplyingService() {
		return supplyingService;
	}

	public void setSupplyingService(SupplyingService supplyingService) {
		this.supplyingService = supplyingService;
	}

	public LinesPage getLinesPage() {
		return linesPage;
	}

	public void setLinesPage(LinesPage linesPage) {
		this.linesPage = linesPage;
	}

	public void retirarVehiculo() throws GWorkException {
		vehicleRetirementPage = (VehicleRetirementPage) FacesUtils
				.getManagedBean("vehicleRetirementPage");
		if (vehicleRetirementPage != null)
			vehicleRetirementPage.retirarVehiculo();
	}

	public void eliminarLinea() throws GWorkException {
		linesPage = (LinesPage) FacesUtils.getManagedBean("linesPage");
		if (linesPage != null)
			linesPage.eliminarLinea();
	}

	public void modificarLinea() throws GWorkException {
		linesPage = (LinesPage) FacesUtils.getManagedBean("linesPage");
		if (linesPage != null)
			linesPage.action_modificarLine();
	}

	public void modificarTipoVehiculo() {
		vehicleTypePage = (VehicleTypePage) FacesUtils
				.getManagedBean("vehicleTypePage");

		if (vehicleTypePage != null) {
			vehicleTypePage.modificarTipoVehiculo();
		}
	}

	public void eliminarTipoVehiculo() {
		vehicleTypePage = (VehicleTypePage) FacesUtils
				.getManagedBean("vehicleTypePage");

		if (vehicleTypePage != null) {
			vehicleTypePage.eliminarTipoVehiculo();
		}
	}

	/**
	 * Indica al bean que modifique el registro de ControlAssignationPolicy.
	 */
	public void modificarCAP() {
		capPage = (PolicyAssignementTypeControlPage) FacesUtils
				.getManagedBean("policyAssignementTypeControlPage");
		if (capPage != null) {
			capPage.action_modificarCAP();
		}
	}

	/**
	 * Indica al bean que modifique el registro de Policies.
	 */
	public void modificarPolicies() {
		policiesPage = (PoliciesPage) FacesUtils.getManagedBean("policiesPage");
		if (policiesPage != null) {
			policiesPage.action_modificarPolicies();
		}
	}

	public void guardarNovedades() {
		noveltiesPage = (NoveltiesPage) FacesUtils
				.getManagedBean("noveltiesPage");
		if (noveltiesPage != null) {
			noveltiesPage.guardarDetalleNovedad();
		}
	}

	/**
	 * Indica al bean que modifique el registro FuelPerformance.
	 */
	public void modificarFuelPerformance() {
		fuelPerformancePage = (FuelPerformancePage) FacesUtils
				.getManagedBean("fuelPerformancePage");
		if (fuelPerformancePage != null) {
			fuelPerformancePage.action_modificarFP();
		}
	}

	public void modificarParametros() {
		parametersPage = (ParametersPage) FacesUtils
				.getManagedBean("parametersPage");
		if (parametersPage != null) {
			parametersPage.modificarParametro();
		}
	}

	public void modificarBrand() {
		brandPage = (BrandPage) FacesUtils.getManagedBean("brandPage");
		if (brandPage != null) {
			brandPage.modificarBrand();
		}
	}

	public void eliminarBrand() {
		brandPage = (BrandPage) FacesUtils.getManagedBean("brandPage");
		if (brandPage != null) {
			brandPage.eliminarBrand();
		}
	}

	public void modificarPolicyInvoice() {
		policyInvoicePage = (PolicyInvoicePage) FacesUtils
				.getManagedBean("policyInvoicePage");
		if (policyInvoicePage != null) {
			policyInvoicePage.action_modificarFacturaPoliza();
		}
	}

	public void modificarVehiculo() throws GWorkException {
		vehiclePage = (VehiclePage) FacesUtils.getManagedBean("vehiclePage");
		if (vehiclePage != null)
			vehiclePage.action_modificar();
	}

	public void modificarTariffsFuelPage() {
		tariffsFuelPage = (TariffsFuelPage) FacesUtils
				.getManagedBean("tariffsFuelPage");
		if (tariffsFuelPage != null) {
			tariffsFuelPage.action_modificarTarifaCombustible();
		}
	}

	public void modificarPFP() {
		fileParameterPage = (PlainFileParameterPage) FacesUtils
				.getManagedBean("plainFileParameterPage");
		if (fileParameterPage != null) {
			fileParameterPage.modificarPFP();
		}
	}

	public void modificarFuelTank() {
		fuelTanksPage = (FuelTanksPage) FacesUtils
				.getManagedBean("fuelTanksPage");
		if (fuelTanksPage != null) {
			fuelTanksPage.modificarFuelTanksPage();
		}
	}

	public void eliminarFuelTank() {
		fuelTanksPage = (FuelTanksPage) FacesUtils
				.getManagedBean("fuelTanksPage");
		if (fuelTanksPage != null) {
			fuelTanksPage.eliminarFuelTank();
		}
	}

	public void modificarControlFuelTank() {
		controlTanksPage = (ControlTanksPage) FacesUtils
				.getManagedBean("fuelControlPage");
		if (controlTanksPage != null) {
			controlTanksPage.action_modificarFuelTanksPage();
		}
	}

	public void eliminarControlFuelTank() {
		controlTanksPage = (ControlTanksPage) FacesUtils
				.getManagedBean("fuelControlPage");
		if (controlTanksPage != null) {
			controlTanksPage.action_eliminarFuelTanksPage();
		}
	}

	public void eliminarSurtidor() {
		pumpsPage = (PumpsPage) FacesUtils.getManagedBean("pumpsPage");
		if (pumpsPage != null) {
			pumpsPage.eliminarSurtidor();
		}
	}

	public void modificarSurtidor() {
		pumpsPage = (PumpsPage) FacesUtils.getManagedBean("pumpsPage");
		if (pumpsPage != null) {
			pumpsPage.modificarPumpsPage();
		}
	}

	public void eliminarParametroContable() {
		accountingParametersPage = (AccountingParametersPage) FacesUtils
				.getManagedBean("accountingParametersPage");
		if (accountingParametersPage != null) {
			accountingParametersPage.eliminarParametroContable();
		}

	}

	public void modificarParametroContable() {
		accountingParametersPage = (AccountingParametersPage) FacesUtils
				.getManagedBean("accountingParametersPage");
		if (accountingParametersPage != null) {
			accountingParametersPage.modificarAP();
		}

	}

	public void reemplazarPolicies() {
		policiesPage = (PoliciesPage) FacesUtils.getManagedBean("policiesPage");
		if (policiesPage != null) {
			policiesPage.reemplazarPoliza();
		}
	}

	public void eliminarRoles() throws GWorkException {
		rollsPage = (RollsPage) FacesUtils.getManagedBean("rollsPage");
		if (rollsPage != null) {
			rollsPage.action_eliminar();
		}
	}

	public void modificarRoles() throws GWorkException {
		rollsPage = (RollsPage) FacesUtils.getManagedBean("rollsPage");
		if (rollsPage != null) {
			rollsPage.action_modificar();
		}
	}

	public void eliminarUsuarios() throws GWorkException {
		userPage = (UserPage) FacesUtils.getManagedBean("userPage");
		if (userPage != null) {
			userPage.action_eliminar();
		}
	}

	public void modificarUsuarios() throws GWorkException {
		userPage = (UserPage) FacesUtils.getManagedBean("userPage");
		if (userPage != null) {
			userPage.action_modificar();
		}
	}

	public void eliminarOpciones() throws GWorkException {
		optionsPage = (OptionsPage) FacesUtils.getManagedBean("optionsPage");
		if (optionsPage != null) {
			optionsPage.action_eliminarOpcionModulo();
		}
	}

	public void retirarVehiculoPoliza() throws GWorkException {
		policiesTransactionsPage = (PoliciesTransactionsPage) FacesUtils
				.getManagedBean("policiesTransactionsPage");
		if (policiesTransactionsPage != null)
			policiesTransactionsPage.retirarVehiculo();
	}

	public void trasladarVehiculoPoliza() throws GWorkException {
		policiesTransactionsPage = (PoliciesTransactionsPage) FacesUtils
				.getManagedBean("policiesTransactionsPage");
		if (policiesTransactionsPage != null)
			policiesTransactionsPage.trasladar();
	}

	public OptionsPage getOptionsPage() {
		return optionsPage;
	}

	public void setOptionsPage(OptionsPage optionsPage) {
		this.optionsPage = optionsPage;
	}

	public void eliminarPeriodos() throws GWorkException {
		periodPage = (PeriodPage) FacesUtils.getManagedBean("periodPage");
		if (periodPage != null) {
			periodPage.action_eliminarPeriodo();
		}
	}

	public void modificarPeriodos() throws GWorkException {
		periodPage = (PeriodPage) FacesUtils.getManagedBean("periodPage");
		if (periodPage != null) {
			periodPage.action_modificarPeriodo();
		}
	}

	/*
	 * Inicializacion de metodos para popup del modulo de generacion de archivo
	 * plano
	 */

	/* metodo para generar el popup de generar archivo plano */
	public void GenerarArchivoPlano() throws GWorkException {
		GeneratorPlainFile = (GeneratorPlainFile) FacesUtils
				.getManagedBean("GeneratorPlainFile");
		if (GeneratorPlainFile != null) {
			GeneratorPlainFile.action_Generar();
		}
	}

	/* metodo para generar el popup de generar los datos la el archivo plano */
	public void ReGenerarArchivoPlano() throws GWorkException {
		GeneratorPlainFile = (GeneratorPlainFile) FacesUtils
				.getManagedBean("GeneratorPlainFile");
		if (GeneratorPlainFile != null) {
			GeneratorPlainFile.action_ReGenerarArchivo();
		}
	}

	/*
	 * Finalizacion de metodos para popup del modulo de generacion de archivo
	 * plano
	 */

	public void GuardarDatosAutoSeguro() throws GWorkException {
		BillingAccountAutoInsurancePage = (BillingAccountAutoInsurancePage) FacesUtils
				.getManagedBean("cobroAutoSeguro");
		if (BillingAccountAutoInsurancePage != null) {
			BillingAccountAutoInsurancePage.aceptar();
		}
	}

	public void GuardarCombustiblePrepago() throws GWorkException {
		prepaidFuelProofPage = (PrepaidFuelProofPage) FacesUtils
				.getManagedBean("prepaidFuelProofPage");
		if (prepaidFuelProofPage != null) {
			prepaidFuelProofPage.aceptar();
		}
	}

	public void EliminarArchivo() throws GWorkException {
		accidentFilesPage = (AccidentFilesPage) FacesUtils
				.getManagedBean("accidentFilesPage");
		if (accidentFilesPage != null) {
			accidentFilesPage.eliminar_archivo();
		}
	}

	public void EliminarArchivo2() throws GWorkException {
		accidentFilesPage = (AccidentFilesPage) FacesUtils
				.getManagedBean("accidentFilesPage");
		if (accidentFilesPage != null) {
			accidentFilesPage.eliminarArchivoTabla();
		}
	}

	/* metodo para generar el popup de confirmacion de enviar notificacion */
	public void enviarNotificacion() throws GWorkException {
		accidentActsPage = (AccidentActsPage) FacesUtils
				.getManagedBean("accidentActsPage");
		if (accidentActsPage != null) {
			accidentActsPage.enviarNotificacion();
		}
	}
}
