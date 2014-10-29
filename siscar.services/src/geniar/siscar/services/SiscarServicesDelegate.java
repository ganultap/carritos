package geniar.siscar.services;

import geniar.siscar.model.VOAssignation;
import geniar.siscar.model.VOCostCenters;
import geniar.siscar.model.VOCostCentersFuels;
import geniar.siscar.model.VOEmployee;
import geniar.siscar.model.VOUser;
import gwork.exception.GWorkException;

import java.util.List;

@javax.jws.WebService(targetNamespace = "http://services.siscar.geniar/", serviceName = "SiscarServicesService", portName = "SiscarServicesPort", wsdlLocation = "WEB-INF/wsdl/SiscarServicesService.wsdl")
public class SiscarServicesDelegate {

	geniar.siscar.services.SiscarServices siscarServices = new geniar.siscar.services.SiscarServices();

	public VOUser validarLogin(String login, String password)
			throws GWorkException {
		return siscarServices.validarLogin(login, password);
	}

	public VOAssignation validarVehiculo(String placa) throws GWorkException {
		return siscarServices.validarVehiculo(placa);
	}

	public String getListCostCenters() throws GWorkException {
		return siscarServices.getListCostCenters();
	}

	public String consultPums() throws GWorkException {
		return siscarServices.consultPums();
	}

	public VOEmployee consultEmpleoyeeName(String strCarne)
			throws GWorkException {
		return siscarServices.consultEmpleoyeeName(strCarne);
	}

	public VOCostCenters consultarCentroCostoVO(String centroCosto)
			throws GWorkException {
		return siscarServices.consultarCentroCostoVO(centroCosto);
	}

	public String consultarAuxiliarCiat(String strCarne) throws GWorkException {
		return siscarServices.consultarAuxiliarCiat(strCarne);
	}

	public String consultarValorTarifaCombustibleCIAT(Long idTipoCombustible)
			throws GWorkException {
		return siscarServices
				.consultarValorTarifaCombustibleCIAT(idTipoCombustible);
	}

	public String consultarValorTarifaTipoCombustibleCALI(Long idTipoCombustible)
			throws GWorkException {
		return siscarServices
				.consultarValorTarifaTipoCombustibleCALI(idTipoCombustible);
	}

	public boolean validateCostCenter(String costCenter) throws GWorkException {
		return siscarServices.validateCostCenter(costCenter);
	}

	public VOCostCentersFuels consultCostCenterFuelByPlaca(String placa)
			throws GWorkException {
		return siscarServices.consultCostCenterFuelByPlaca(placa);
	}

	public List<VOCostCentersFuels> consultarCostCenterFuelPorPlaca(String placa)
			throws GWorkException {
		return siscarServices.consultarCostCenterFuelPorPlaca(placa);
	}

	public String getListTypeRequest() throws GWorkException {
		return siscarServices.getListTypeRequest();
	}

	public void guardarServicioRegistro(Long idFuelTypeRequest, String placa,
			String login, String nombreSolicitante, String carneAsignatario,
			String carneSolicitante, Float numeroGalones, Float total,
			String observaciones, Long idPump, Long kilometrajeActual,
			Long numeroReciboPago, String NumeroCentroCosto,
			Long usoDisponible, Long fuelType, Float capacidadTanque,
			Long cargoA) throws GWorkException {
		siscarServices.guardarServicioRegistro(idFuelTypeRequest, placa, login,
				nombreSolicitante, carneAsignatario, carneSolicitante,
				numeroGalones, total, observaciones, idPump, kilometrajeActual,
				numeroReciboPago, NumeroCentroCosto, usoDisponible, fuelType,
				capacidadTanque, cargoA);
	}

}