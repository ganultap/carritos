/**
 * 
 */
package geniar.siscar.logic.fuels.services.impl;

import geniar.siscar.logic.consultas.SearchFuelTariff;
import geniar.siscar.logic.fuels.services.FuelTankService;
import geniar.siscar.model.FuelTanks;
import geniar.siscar.model.FuelsTypes;
import geniar.siscar.persistence.EntityManagerHelper;
import geniar.siscar.persistence.FuelsTanksDAO;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

import java.util.List;

import javax.persistence.Query;

/**
 * @author Mauricio Cuenca Narváez
 * 
 */
public class FuelTankServiceImpl implements FuelTankService {

	@SuppressWarnings("unchecked")
	public List<FuelTanks> consultarFuelTank(String ftaNombre)
			throws GWorkException {
		try {
			List<FuelTanks> listTanks = null;
			StringBuffer buffer = new StringBuffer();
			buffer
					.append("select model From FuelTanks model where model.ftaNombre = :ftaNombre");
			Query query = EntityManagerHelper.getEntityManager().createQuery(
					buffer.toString());
			query.setParameter("ftaNombre", ftaNombre);
			listTanks = (List<FuelTanks>) query.getResultList();
			if (listTanks != null)
				return listTanks;

			return null;
		} catch (Exception e) {
			throw new GWorkException(e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	public FuelTanks consultarFuelTankPorID(Long idTanqueCombustible)
			throws GWorkException {
		List<FuelTanks> listTanks = null;
		StringBuffer buffer = new StringBuffer();
		buffer
				.append("select model From FuelTanks model where model.ftaCodigo = :idTanque");
		Query query = EntityManagerHelper.getEntityManager().createQuery(
				buffer.toString());
		query.setParameter("idTanque", idTanqueCombustible);
		listTanks = (List<FuelTanks>) query.getResultList();
		if (listTanks != null && listTanks.size() > 0 && !listTanks.isEmpty())
			return listTanks.get(0);

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see geniar.siscar.logic.parameters.services.
	 *      FuelTankService#consultarTodosFuelTanks()
	 */
	@SuppressWarnings("unchecked")
	public List<FuelTanks> consultarTodosFuelTanks() throws GWorkException {
		try {
			List<FuelTanks> listTanks = null;
			StringBuffer buffer = new StringBuffer();
			buffer.append("select model From FuelTanks model");
			Query query = EntityManagerHelper.getEntityManager().createQuery(
					buffer.toString());
			listTanks = (List<FuelTanks>) query.getResultList();
			if (listTanks != null)
				return listTanks;

			return null;
		} catch (Exception e) {
			throw new GWorkException(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see geniar.siscar.logic.parameters.services.
	 *      FuelTankService#crearFuelTanks(java.lang.String, java.lang.Long,
	 *      java.lang.Long)
	 */
	public void crearFuelTanks(String ftaNombre, Long idFuelsTypes,
			Float capacidad) throws GWorkException {

		String nombre = ftaNombre.toUpperCase().trim();

		List<FuelTanks> lst = consultarFuelTank(nombre);

		if (lst.size() != 0)
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.NOMBRETANQEXISTE"));

		FuelsTypes fuelsTypes = new SearchFuelTariff()
				.consultarFuelType(idFuelsTypes);

		if (fuelsTypes == null)
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.COMBUSNOEXISTE"));

		FuelTanks fuelsTanks = null;

		fuelsTanks = new FuelTanks();
		fuelsTanks.setFtaNombre(nombre);
		fuelsTanks.setFtaCapacidadMaxima(capacidad);
		fuelsTanks.setFuelsTypes(fuelsTypes);
		fuelsTanks.setFtaGalonesActuales(0F);

		try {
			Util.validarSession();
			EntityManagerHelper.beginTransaction();
			new FuelsTanksDAO().save(fuelsTanks);
			EntityManagerHelper.commit();
//			Util.limpiarSession();
		} catch (RuntimeException e) {
			EntityManagerHelper.rollback();
//			Util.limpiarSession();
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.GUARDARTANQUE"));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see geniar.siscar.logic.parameters.services.
	 *      FuelTankService#eliminarFuelTanks(java.lang.String)
	 */
	public void eliminarFuelTanks(Long id) throws GWorkException {
		FuelTanks fuelsTanks = consultarFuelTankPorID(id);

		if (fuelsTanks == null)
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.TANQUENOEXISTE"));

		// Se busca si el tanque tiene un surtidor
		if (fuelsTanks.getPumpses().size() > 0)
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.TANQUE.PUMP.ASOC"));

		try {
			Util.validarSession();
			EntityManagerHelper.beginTransaction();
			new FuelsTanksDAO().delete(fuelsTanks);
			EntityManagerHelper.commit();
//			Util.limpiarSession();
		} catch (Exception e) {
			EntityManagerHelper.rollback();
//			Util.limpiarSession();
			throw new GWorkException(Util.loadErrorMessageValue(e.getMessage()));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see geniar.siscar.logic.parameters.services.
	 *      FuelTankService#modifiarFuelTanks(java.lang.String,
	 *      java.lang.String, java.lang.Long, java.lang.Long)
	 */
	public void modifiarFuelTanks(Long idTanqueCombustible,
			String ftaNombreNuevo, Long idFuelsTypes, Float capacidad)
			throws GWorkException {

		FuelTanks fuelsTanks = consultarFuelTankPorID(idTanqueCombustible);

		if (fuelsTanks == null)
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.TANQUENOEXISTE"));

		String nombre = ftaNombreNuevo.toUpperCase().trim();

		if (!fuelsTanks.getFtaNombre().equalsIgnoreCase(nombre)) {
			List<FuelTanks> lst = consultarFuelTank(nombre);

			if (lst.size() != 0)
				throw new GWorkException(Util
						.loadErrorMessageValue("ERROR.NOMBRETANQEXISTE"));
		}

		FuelsTypes fuelsTypes = new SearchFuelTariff()
				.consultarFuelType(idFuelsTypes);

		if (fuelsTypes == null)
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.COMBUSNOEXISTE"));

		fuelsTanks.setFtaNombre(nombre);
		fuelsTanks.setFtaCapacidadMaxima(capacidad);
		fuelsTanks.setFuelsTypes(fuelsTypes);

		try {
			Util.validarSession();
			EntityManagerHelper.beginTransaction();
			new FuelsTanksDAO().update(fuelsTanks);
			EntityManagerHelper.commit();
//			Util.limpiarSession();
		} catch (RuntimeException e) {
			EntityManagerHelper.rollback();
//			Util.limpiarSession();
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.GUARDARTANQUE"));
		}
	}

	public void actualizarTanque(FuelTanks fuelTanks) throws GWorkException {
		try {
			Util.validarSession();
			EntityManagerHelper.beginTransaction();
			new FuelsTanksDAO().update(fuelTanks);
			EntityManagerHelper.commit();
//			Util.limpiarSession();
		} catch (RuntimeException e) {
			EntityManagerHelper.rollback();
			// Util.limpiarSession();
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.GUARDARTANQUE"));
		}
	}
}
