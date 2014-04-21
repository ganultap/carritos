package geniar.siscar.logic.billing.services;

import java.util.Date;

import geniar.siscar.model.FlatFile;
import geniar.siscar.model.VehiclesAssignation;
import gwork.exception.GWorkException;

public interface PersonalMileageService {

	public void GuardarFlatFileKmPersonal(
			VehiclesAssignation vehiclesAssignation, String Recorrido,
			String Reportado, String Personal, Float tariffs, String login,
			Date fechaIni, Date fechaFin, String kmInicial, String kmFinal,
			String kmPolitica) throws GWorkException;

	public void modificarFlatFileKmPersonal(
			VehiclesAssignation vehiclesAssignation, String Recorrido,
			String Reportado, String Personal, Float tariffs, String login,
			Long idFlatFile, Date fechaIni, Date fechaFin, String kmInicial,
			String kmFinal, String kmPolitica) throws GWorkException;

	public FlatFile getKmPersonalByYear(String placa,Date fechaInicial);
}
