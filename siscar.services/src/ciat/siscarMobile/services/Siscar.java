package ciat.siscarMobile.services;

import java.util.List;

import geniar.siscar.logic.consultas.SearchCostCenters;
import geniar.siscar.model.VOAssignation;
import geniar.siscar.model.VOCostCenters;
import geniar.siscar.model.VOCostCentersFuels;
import geniar.siscar.model.VOEmployee;
import geniar.siscar.model.VOModel;
import geniar.siscar.model.VOUser;
import geniar.siscar.services.SiscarServices;
import gwork.exception.GWorkException;
import ciat.siscar.mobile.services.model.ValidacionSolicitante;
import ciat.siscar.mobile.services.model.ValidacionVehiculo;

public class Siscar {

	private SiscarServices servicio = new SiscarServices();

	/*
	 * (non-Javadoc)
	 * 
	 * @see ciat.siscarMobile.services.ISiscar#validarLogin(java.lang.String,
	 *      java.lang.String)
	 */
	public String validarLogin(String login, String password) throws SiscarException {

		try {
			VOUser user = servicio.validarLogin(login, password);
			return user.getUsrNombre() + " " + user.getUsrApellido();

		} catch (Exception e) {
			throw new SiscarException("No se pudo autenticar usuario");
		}
	}
	
	public String consultarAuxiliarCiat(String strCarne) throws SiscarException {
		try {
			return servicio.consultarAuxiliarCiat(strCarne);
		} catch (Exception e) {
			throw new SiscarException("La consulta no arrojo datos");
		}
	}

	public VOEmployee consultEmpleoyeeName(String strCarne) throws SiscarException {
		try {
			return servicio.consultEmpleoyeeName(strCarne);
		} catch (Exception e) {
			throw new SiscarException("La consulta no arrojo datos");
		}
	}
	
	public String consultarValorTarifaCombustibleCIAT(Long idTipoCombustible) throws SiscarException {
		try {
			return servicio.consultarValorTarifaCombustibleCIAT(idTipoCombustible);
		} catch (Exception e) {
			throw new SiscarException("La consulta tarifa combustible CIAT no arrojo datos");
		}
	}
	
	public VOModel consultCostCenters(String param) throws SiscarException {
		try {
			VOModel model = new VOModel();
			model.setParam(servicio.getListCostCenters());
			return model;
		} catch (Exception e) {
			throw new SiscarException("La consulta de los centros de costos no arrojo datos");
		}
	}
	
	public VOModel consultTypeRequest(String param) throws SiscarException {
		try {
			VOModel model = new VOModel();
			model.setParam(servicio.getListTypeRequest());
			return model;
		} catch (Exception e) {
			throw new SiscarException(e.getMessage());
		}
	}
	
	public VOModel consultPums(String param) throws SiscarException {
		try {
			VOModel model = new VOModel();
			model.setParam(servicio.consultPums());
			return model;
		} catch (Exception e) {
			throw new SiscarException("La consulta de surtidores no arrojo datos");
		}
	}
	
	public String consultarValorTarifaCombustibleCALI(Long idTipoCombustible) throws SiscarException {
		try {
			return servicio.consultarValorTarifaTipoCombustibleCALI(idTipoCombustible);
		} catch (Exception e) {
			throw new SiscarException("La consulta de la tarifa CALI no arrojo datos");
		}
	}
	
	public VOCostCenters consultarCentroCostoVO(String center) throws SiscarException {
		try {
			return servicio.consultarCentroCostoVO(center);
		} catch (Exception e) {
			throw new SiscarException("La consulta de los centros de costos no arrojo datos");
		}
	}
	
	public VOCostCentersFuels consultCostCenterFuelByPlaca(String placa) throws SiscarException {
		try {
			return servicio.consultCostCenterFuelByPlaca(placa);
		} catch (Exception e) {
			throw new SiscarException("La consulta de los centros de costos no arrojo datos");
		}
	}


	// ver constantes en TipoVehiculo
	/*
	 * (non-Javadoc)
	 * 
	 * @see ciat.siscarMobile.services.ISiscar#obtenerTarifa(int)
	 */
	public String obtenerTarifa(int tipoVehiculo) throws SiscarException {
		// por ejemplo... TipoVehiculo.VEHICULO_CIAT
		return "$1200";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ciat.siscarMobile.services.ISiscar#validarSolicitante(int,
	 *      java.lang.String)
	 */
	public ValidacionSolicitante validarSolicitante(int CargoA, String carne) throws SiscarException {
		return new ValidacionSolicitante("Alvaro", "100");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ciat.siscarMobile.services.ISiscar#validarVehiculo(java.lang.String)
	 */
	public ValidacionVehiculo validarVehiculo(String placa) throws SiscarException {

		try {

			VOAssignation validacion = servicio.validarVehiculo(placa);
			
			if (validacion != null) {
				// donde se obtiene el valor de la tanqueada ??
				return new ValidacionVehiculo(validacion.getTipoAsignacion(), // tipo
						// asignación
						validacion.getCarneAsignatario(), // carné usuario
						// asignado
						validacion.getNombreAsignatario(), // usuario asignado
						validacion.getVhcCapMaxTanq().toString(), // capacidad
						// tanque

						// ??
						Integer.parseInt(validacion.getTipoCombustible().toString()), // tipo combustible
						validacion.getUltimoKilometrajeRegistrado(), // ultimo km

						"??" // vlr taqueada
				);
			}

			return null;

		} catch (Exception e) {
			throw new SiscarException("no se pudo validar vehículo");
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ciat.siscarMobile.services.ISiscar#validarCentroCosto(java.lang.String)
	 */
	public boolean validarCentroCosto(String centroCosto) throws SiscarException, GWorkException {
		return servicio.validateCostCenter(centroCosto);
	}

	public void guardarServicioRegistro(Long idFuelTypeRequest, String placa, String login,
			String nombreSolicitante, String carneAsignatario, String carneSolicitante, Float numeroGalones,
			Float total, String observaciones, Long idPump, Long kilometrajeActual, Long numeroReciboPago,
			String NumeroCentroCosto, Long usoDisponible, Long fuelType, Float capacidadTanque,Long cargoA) throws GWorkException {
		servicio.guardarServicioRegistro(idFuelTypeRequest, placa, login, nombreSolicitante, carneAsignatario,
				carneSolicitante, numeroGalones, total, observaciones, idPump, kilometrajeActual, numeroReciboPago,
				NumeroCentroCosto, usoDisponible, fuelType, capacidadTanque,cargoA);
	}
	
	public List<VOCostCentersFuels> consultarCostCenterFuelPorPlaca(String placa)
	throws GWorkException {
		return SearchCostCenters.consultarCostCenterFuelPorPlaca(placa);
	}

}
