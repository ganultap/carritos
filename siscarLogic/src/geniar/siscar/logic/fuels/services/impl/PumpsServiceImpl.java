/**
 * 
 */
package geniar.siscar.logic.fuels.services.impl;

import geniar.siscar.logic.fuels.services.PumpsService;
import geniar.siscar.model.FuelTanks;
import geniar.siscar.model.Pumps;
import geniar.siscar.persistence.EntityManagerHelper;
import geniar.siscar.persistence.FuelsTanksDAO;
import geniar.siscar.persistence.PumpsDAO;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

import java.util.List;

import javax.persistence.Query;

/**
 * @author Mauricio Cuenca Narváez
 * 
 */
public class PumpsServiceImpl implements PumpsService {

	/*
	 * (non-Javadoc)
	 * 
	 * @see geniar.siscar.logic.parameters.services.
	 *      PumpsService#consultarPump(java.lang.String)
	 */
	public Pumps consultarPump(String pumNombre) throws GWorkException {
		validarSession();
		Pumps pumps = null;
		try {
			pumps = new PumpsDAO().findByPumNombre(
					pumNombre.trim().toUpperCase()).get(0);
		} catch (RuntimeException e) {
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.PUMPNOENCONTRADO"));
		}
		return pumps;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see geniar.siscar.logic.parameters.services.
	 *      PumpsService#consultarTodosPumps()
	 */
	@SuppressWarnings("unchecked")
	public List<Pumps> consultarTodosPumps() throws GWorkException {
		try {
			List<Pumps> listPumps = null;
			final String queryString = "select model  from Pumps model ORDER BY model.pumNombre ASC";
			Query query = EntityManagerHelper.getEntityManager().createQuery(
					queryString);
			listPumps = (List<Pumps>) query.getResultList();

			if (listPumps == null || listPumps.size() == 0)
				throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));

			return listPumps;

		} catch (Exception e) {
			throw new GWorkException(e.getMessage());
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see geniar.siscar.logic.parameters.services.
	 *      PumpsService#consultarTodosPumps()
	 */
	@SuppressWarnings("unchecked")
	public String consultPums() throws GWorkException {
		try {
			StringBuffer buffer = new StringBuffer();
			List<Pumps> listPumps = null;
			final String queryString = "select model  from Pumps model ORDER BY model.pumNombre ASC";
			Query query = EntityManagerHelper.getEntityManager().createQuery(
					queryString);
			listPumps = (List<Pumps>) query.getResultList();
			buffer.append("pm");
			if (listPumps.size() > 0) {
				for (Pumps pumps : listPumps) {
					buffer.append(pumps.getPumCodigo());
					buffer.append(" ");
					buffer.append(pumps.getPumNombre());
					buffer.append("/");
				}
			}
			return buffer.toString();
		} catch (Exception e) {
			throw new GWorkException(e.getMessage());
		}
	}

	public static void main(String[] args) throws GWorkException {
		System.out.println(new PumpsServiceImpl().consultPums());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see geniar.siscar.logic.parameters.services.PumpsService#eliminarPump
	 *      (java.lang.String)
	 */
	public void eliminarPump(Long idSurtidor) throws GWorkException {
		validarSession();
		Pumps pumps = null;
		try {
			pumps = consultarPumpPorId(idSurtidor);
		} catch (Exception e) {
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.PUMPNOENCONTRADO"));
		}
		if (pumps == null) {
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.PUMPNOEXISTE"));
		}

		FuelTanks fuelsTanks = pumps.getFuelTanks();
		fuelsTanks.setPumpses(null);
		pumps.setFuelTanks(null);

		try {
			validarSession();
			EntityManagerHelper.beginTransaction();
			new FuelsTanksDAO().update(fuelsTanks);
			new PumpsDAO().delete(pumps);
			EntityManagerHelper.commit();
			// limpiarSession();
		} catch (RuntimeException e) {
			EntityManagerHelper.rollback();
			// limpiarSession();
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.NOELIMINARSURT"));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see geniar.siscar.logic.fuels.services.
	 *      PumpsService#modificarPump(java.lang.Long, java.lang.Long,
	 *      java.lang.String)
	 */
	public void modificarPump(Long idFuelTank, Long id, String pumNuevoNombre)
			throws GWorkException {
		validarSession();
		String nombrePum = pumNuevoNombre.trim().toUpperCase();
		Pumps pumps = consultarPumpPorId(id);
		FuelTanks fuelsTanks = new FuelsTanksDAO().findById(idFuelTank);

		Pumps pumps2 = null;

		try {
			pumps2 = consultarPump(nombrePum);
		} catch (GWorkException e) {
		}

		if (pumps2 != null) {
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.PUMPYAEXISTE"));
		}

		pumps.setPumNombre(nombrePum);
		pumps.setFuelTanks(fuelsTanks);

		try {
			EntityManagerHelper.beginTransaction();
			new PumpsDAO().update(pumps);
			EntityManagerHelper.commit();
			// limpiarSession();
		} catch (RuntimeException e) {
			// limpiarSession();
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.GUARDARTANQUE"));
		}
	}

	public void crearPump(String pumNombre, Long idTanque)
			throws GWorkException {
		validarSession();
		String nombrePum = pumNombre.trim().toUpperCase();
		Pumps pumps = null;
		try {
			pumps = consultarPump(nombrePum);
		} catch (Exception e) {
		}

		if (pumps != null) {
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.PUMPYAEXISTE"));
		}

		FuelTanks fuelsTanks = new FuelsTanksDAO().findById(idTanque);

		if (fuelsTanks == null) {
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.TANQUENOEXISTE"));
		}

		pumps = new Pumps();

		pumps.setFuelTanks(fuelsTanks);
		pumps.setPumNombre(nombrePum);
		validarSession();
		EntityManagerHelper.beginTransaction();
		new PumpsDAO().save(pumps);
		EntityManagerHelper.commit();
		// limpiarSession();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see geniar.siscar.logic.fuels.services.
	 *      PumpsService#consultarPumpPorId(java.lang.Long)
	 */
	public Pumps consultarPumpPorId(Long idPump) throws GWorkException {
		validarSession();
		return new PumpsDAO().findById(idPump);
	}

	public void validarSession() {
		if (EntityManagerHelper.getEntityManager().isOpen())
			EntityManagerHelper.getEntityManager().close();
	}

	// public void limpiarSession() {
	// EntityManagerHelper.getEntityManager().clear();
	// EntityManagerHelper.closeEntityManager();
	// }
}
