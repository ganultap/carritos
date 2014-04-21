package ciat.siscarMobile.services;

import geniar.siscar.model.VOCostCenters;
import geniar.siscar.model.VOCostCentersFuels;
import geniar.siscar.model.VOEmployee;
import geniar.siscar.model.VOModel;
import gwork.exception.GWorkException;

import java.util.List;

import ciat.siscar.mobile.services.model.ValidacionSolicitante;
import ciat.siscar.mobile.services.model.ValidacionVehiculo;

@javax.jws.WebService(targetNamespace = "http://services.siscarMobile.ciat/", serviceName = "SiscarService", portName = "SiscarPort", wsdlLocation = "WEB-INF/wsdl/SiscarService.wsdl")
public class SiscarDelegate {

	ciat.siscarMobile.services.Siscar siscar = new ciat.siscarMobile.services.Siscar();

	public String validarLogin(String login, String password)
			throws SiscarException {
		return siscar.validarLogin(login, password);
	}

	public String consultarAuxiliarCiat(String strCarne) throws SiscarException {
		return siscar.consultarAuxiliarCiat(strCarne);
	}

	public VOEmployee consultEmpleoyeeName(String strCarne)
			throws SiscarException {
		return siscar.consultEmpleoyeeName(strCarne);
	}

	public String consultarValorTarifaCombustibleCIAT(Long idTipoCombustible)
			throws SiscarException {
		return siscar.consultarValorTarifaCombustibleCIAT(idTipoCombustible);
	}

	public VOModel consultCostCenters(String param) throws SiscarException {
		return siscar.consultCostCenters(param);
	}

	public VOModel consultTypeRequest(String param) throws SiscarException {
		return siscar.consultTypeRequest(param);
	}

	public VOModel consultPums(String param) throws SiscarException {
		return siscar.consultPums(param);
	}

	public String consultarValorTarifaCombustibleCALI(Long idTipoCombustible)
			throws SiscarException {
		return siscar.consultarValorTarifaCombustibleCALI(idTipoCombustible);
	}

	public VOCostCenters consultarCentroCostoVO(String center)
			throws SiscarException {
		return siscar.consultarCentroCostoVO(center);
	}

	public VOCostCentersFuels consultCostCenterFuelByPlaca(String placa)
			throws SiscarException {
		return siscar.consultCostCenterFuelByPlaca(placa);
	}

	public String obtenerTarifa(int tipoVehiculo) throws SiscarException {
		return siscar.obtenerTarifa(tipoVehiculo);
	}

	public ValidacionSolicitante validarSolicitante(int CargoA, String carne)
			throws SiscarException {
		return siscar.validarSolicitante(CargoA, carne);
	}

	public ValidacionVehiculo validarVehiculo(String placa)
			throws SiscarException {
		return siscar.validarVehiculo(placa);
	}

	public boolean validarCentroCosto(String centroCosto)
			throws SiscarException, GWorkException {
		return siscar.validarCentroCosto(centroCosto);
	}

	public void guardarServicioRegistro(Long idFuelTypeRequest, String placa,
			String login, String nombreSolicitante, String carneAsignatario,
			String carneSolicitante, Float numeroGalones, Float total,
			String observaciones, Long idPump, Long kilometrajeActual,
			Long numeroReciboPago, String NumeroCentroCosto,
			Long usoDisponible, Long fuelType, Float capacidadTanque,
			Long cargoA) throws GWorkException {
		siscar.guardarServicioRegistro(idFuelTypeRequest, placa, login,
				nombreSolicitante, carneAsignatario, carneSolicitante,
				numeroGalones, total, observaciones, idPump, kilometrajeActual,
				numeroReciboPago, NumeroCentroCosto, usoDisponible, fuelType,
				capacidadTanque, cargoA);
	}

	public List<VOCostCentersFuels> consultarCostCenterFuelPorPlaca(String placa)
			throws GWorkException {
		return siscar.consultarCostCenterFuelPorPlaca(placa);
	}

}