package geniar.siscar.logic.billing.services;

import geniar.siscar.model.FlatFile;
import geniar.siscar.model.Tariffs;
import gwork.exception.GWorkException;

import java.util.Date;

public interface FlatFileFuelService {

	/***************************************************************************
	 * @param carneAsignatario
	 * @param fecha
	 * @param valor
	 * @param numGalones
	 * @param centroCosto
	 * @param placa
	 * @param login
	 **************************************************************************/
	public FlatFile generarArchivoPlanoCombustible(String carneAsignatario,
			Date fecha, Float valor, Float numGalones, String centroCosto,
			String placa, String login, Tariffs tariffs) throws GWorkException;


}
