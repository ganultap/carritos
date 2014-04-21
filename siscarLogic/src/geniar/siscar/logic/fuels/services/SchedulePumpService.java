/**
 * 
 */
package geniar.siscar.logic.fuels.services;

import java.util.Date;
import java.util.List;

import geniar.siscar.model.DialyMovementPumps;
import gwork.exception.GWorkException;

/**
 * @author usuario
 * 
 */
public interface SchedulePumpService {

	public void registrarPlanillaSurtidor(Long surtidor, Long horaRevision,
			Date fechaRevision, String lectura) throws GWorkException;

	public void modificarPlanillaSurtidor(Long idPlanillaSurtidor,
			Long surtidor, Long horaRevision, Date fechaRevision, String lectura)
			throws GWorkException;

	public List<DialyMovementPumps> listaPlanillaSurtidor(Long idSurtidor,
			Date fechaIni, Date fechaFin) throws GWorkException;
}
