package geniar.siscar.logic.accidents.services;

import geniar.siscar.model.Driver;
import gwork.exception.GWorkException;

public interface DriversService {

	public void crearConductor(String drvCedula, String drvNombre,
			String drvNumeroCarne, String drvCargo, String drvDireccion,
			String drvTelefono) throws GWorkException;

	public void modificarConductor(String drvCedula, String drvNombre,
			String drvNumeroCarne, String drvCargo, String drvDireccion,
			String drvTelefono) throws GWorkException;

	public void eliminarConductor(String drvCedula) throws GWorkException;

	public Driver consultarConductor(String drvCedula) throws GWorkException;
}
