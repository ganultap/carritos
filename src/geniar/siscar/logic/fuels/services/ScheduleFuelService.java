/**
 * 
 */
package geniar.siscar.logic.fuels.services;

import java.util.Date;
import java.util.List;

import geniar.siscar.model.DailyMovementTank;
import gwork.exception.GWorkException;

/**
 * @author usuario
 * 
 */
public interface ScheduleFuelService {

	public void registrarMoviminetoDiarioTanque(Long tanque, Long horaRevision,
			String entrada, String Lectura, Date fecharRevision)
			throws GWorkException;

	public void modificarMoviminetoDiarioTanque(Long registroPlanillaTanque,
			Long tanque, Long horaRevision, String entrada, String Lectura,
			Date fecharRevision) throws GWorkException;

	public String entradaTanques(Long idTanque, Date fechaCarga)
			throws GWorkException;

	public List<DailyMovementTank> listaPlanillaTanque(Long idTanque,
			Date fechaIni, Date fechaFin) throws GWorkException;
}
