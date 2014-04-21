package geniar.siscar.services;

import geniar.siscar.consults.ConsultsService;
import geniar.siscar.consults.impl.ConsultsServiceImpl;
import geniar.siscar.logic.consultas.ConsultTariff;
import geniar.siscar.logic.consultas.SearchBilling;
import geniar.siscar.logic.consultas.SearchCostCenters;
import geniar.siscar.logic.consultas.SearchVehicles;
import geniar.siscar.logic.fuels.services.PumpsService;
import geniar.siscar.logic.fuels.services.impl.PumpsServiceImpl;
import geniar.siscar.logic.fuels.services.impl.ServiceRegistryImp;
import geniar.siscar.logic.security.serivice.UserService;
import geniar.siscar.logic.security.serivice.impl.UserServiceImpl;
import geniar.siscar.model.VOAssignation;
import geniar.siscar.model.VOCostCenters;
import geniar.siscar.model.VOCostCentersFuels;
import geniar.siscar.model.VOEmployee;
import geniar.siscar.model.VOUser;
import gwork.exception.GWorkException;

import java.util.List;

public class SiscarServices {

	/**
	 * metodo encargado de validar el usuario en siscar y en el CIATs
	 * 
	 * @param login
	 * @param password
	 * @return
	 * @throws GWorkException
	 */
	public VOUser validarLogin(String login, String password)
			throws GWorkException {
		boolean enLDAP = true;
		String loginTemp = null;
		UserService service = new UserServiceImpl();

		// enLDAP = ControllerAuthentication.getInstance().validateUser(login,
		// password);

		VOUser user = null;

		loginTemp = login.trim().toUpperCase();
		user = service.consultUserByLogin(loginTemp);

		if (user != null && enLDAP)
			return user;

		return null;
	}

	/**
	 * Metodo encargado de consulta un vehiculo por la placa
	 * 
	 * @param placa
	 * @return
	 * @throws GWorkException
	 */
	public VOAssignation validarVehiculo(String placa) throws GWorkException {
		return SearchVehicles.consultarAsignacionServicioCombustible(placa);
	}

	/**
	 * Consulta una lista de centros de costo
	 * 
	 * @return
	 * @throws GWorkException
	 */
	public String getListCostCenters() throws GWorkException {
		return new SearchCostCenters().consultarCentrosDeCosto();
	}

	/**
	 * Consulta una lista de surtidores
	 * 
	 * @return
	 * @throws GWorkException
	 */
	public String consultPums() throws GWorkException {
		PumpsService pumpsService = new PumpsServiceImpl();
		return pumpsService.consultPums();
	}

	public VOEmployee consultEmpleoyeeName(String strCarne)
			throws GWorkException {
		ConsultsService consultsService = new ConsultsServiceImpl();
		return consultsService.consultEmployee(strCarne);
	}

	public VOCostCenters consultarCentroCostoVO(String centroCosto)
			throws GWorkException {
		return SearchCostCenters.consultarCentroCostoVO(centroCosto);
	}

	public String consultarAuxiliarCiat(String strCarne) throws GWorkException {
		ConsultsService consultsService = new ConsultsServiceImpl();
		return consultsService.consultarAuxiliarCiat(strCarne);
	}

	public String consultarValorTarifaCombustibleCIAT(Long idTipoCombustible)
			throws GWorkException {
		return new ConsultTariff()
				.consultarValorTarifaTipoCombustible(idTipoCombustible);
	}

	public String consultarValorTarifaTipoCombustibleCALI(Long idTipoCombustible)
			throws GWorkException {
		return new ConsultTariff().consultarTarifaTipoCali(idTipoCombustible);
	}

	public boolean validateCostCenter(String costCenter) throws GWorkException {
		ConsultsService consultsService = new ConsultsServiceImpl();
		return consultsService.validateCostCenter(costCenter);
	}

	public VOCostCentersFuels consultCostCenterFuelByPlaca(String placa)
			throws GWorkException {
		return new ServiceRegistryImp().consultCostCenterFuelByPlaca(placa);
	}

	public List<VOCostCentersFuels> consultarCostCenterFuelPorPlaca(String placa)
			throws GWorkException {
		return SearchCostCenters.consultarCostCenterFuelPorPlaca(placa);
	}

	/**
	 * Consulta una lista de tipo de servicio de solicitud
	 * 
	 * @return
	 * @throws GWorkException
	 */
	public String getListTypeRequest() throws GWorkException {
		return new SearchBilling().getListTypeRequest();
	}

	public void guardarServicioRegistro(Long idFuelTypeRequest, String placa,
			String login, String nombreSolicitante, String carneAsignatario,
			String carneSolicitante, Float numeroGalones, Float total,
			String observaciones, Long idPump, Long kilometrajeActual,
			Long numeroReciboPago, String NumeroCentroCosto,
			Long usoDisponible, Long fuelType, Float capacidadTanque,
			Long cargoA) throws GWorkException {

		new ServiceRegistryImp().serviceRegistryFuel(idFuelTypeRequest, placa,
				login, nombreSolicitante, carneAsignatario, carneSolicitante,
				numeroGalones, total, observaciones, idPump, kilometrajeActual,
				numeroReciboPago, NumeroCentroCosto, usoDisponible, fuelType,
				capacidadTanque, cargoA);
	}

}
