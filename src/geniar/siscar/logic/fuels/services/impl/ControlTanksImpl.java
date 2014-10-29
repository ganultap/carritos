package geniar.siscar.logic.fuels.services.impl;

import geniar.siscar.logic.fuels.services.ControlTanksService;
import geniar.siscar.logic.fuels.services.FuelTankService;
import geniar.siscar.model.ControlsTanks;
import geniar.siscar.model.FuelTanks;
import geniar.siscar.persistence.ControlsTanksDAO;
import geniar.siscar.persistence.EntityManagerHelper;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

public class ControlTanksImpl implements ControlTanksService {

	/*
	 * (non-Javadoc)
	 * 
	 * @see geniar.siscar.logic.fuels.services.ControlTanksService#ControlTanques(java.lang.String,
	 *      java.lang.Long, java.lang.Long, java.util.Date, java.lang.Long)
	 */
	public void guardarControlTanques(Long idTanque, Date fechaTanqueada, BigDecimal cantidadGalones) throws GWorkException {
		
		@SuppressWarnings("unused")
		String cantidad = null;
		
		if (cantidadGalones != null)
			 cantidad =cantidadGalones.toString();
		
		FuelTankService fuelTankService = new FuelTankServiceImpl();
		FuelTanks fuelTanks = fuelTankService.consultarFuelTankPorID(idTanque);
		Float galonesAct = new Float(0);
		
		if (fuelTanks == null)
			throw new GWorkException(Util.loadErrorMessageValue(""));

		if (fuelTanks.getFtaCapacidadMaxima() < new Float(cantidadGalones.toString()))
			throw new GWorkException(Util.loadErrorMessageValue("CTRL.TAM.CAP"));

		ControlsTanks controlsTanks = new ControlsTanks();
		controlsTanks.setCotFechaCarga(fechaTanqueada);
		controlsTanks.setCotGalonesActuales(cantidadGalones);
		controlsTanks.setFuelTanks(fuelTanks);
		saveControl(controlsTanks);
		if (fuelTanks.getFtaGalonesActuales() != null) 
			galonesAct = fuelTanks.getFtaGalonesActuales();
		
		fuelTanks.setFtaGalonesActuales(galonesAct+ new Float(controlsTanks.getCotGalonesActuales().toString()));
		new FuelTankServiceImpl().actualizarTanque(fuelTanks);
	}

	/*
	 * @see geniar.siscar.logic.fuels.services.ControlTanksService#actualizarControl(java.lang.Long,
	 *      java.lang.Long, java.util.Date, java.lang.Long)
	 */
	public void actualizarControl(Long idControlTanques, Long idTanque, Date fechaTanqueada, Float suma,
			BigDecimal cantidadGalones) throws GWorkException {

		FuelTankService fuelTankService = new FuelTankServiceImpl();
		FuelTanks fuelTanks = fuelTankService.consultarFuelTankPorID(idTanque);

		if (fuelTanks == null)
			throw new GWorkException(Util.loadErrorMessageValue(""));

		if (fuelTanks.getFtaCapacidadMaxima() < new Float(cantidadGalones.toString()))
			throw new GWorkException(Util.loadErrorMessageValue("CTRL.TAM.CAP"));

		ControlsTanks controlsTanks = consultarControlTanquePorId(idControlTanques);
		controlsTanks.setCotFechaCarga(fechaTanqueada);
		controlsTanks.setCotGalonesActuales(cantidadGalones);
		controlsTanks.setFuelTanks(fuelTanks);

		updateControl(controlsTanks);

		fuelTanks.setFtaGalonesActuales(fuelTanks.getFtaGalonesActuales() + suma);
		new FuelTankServiceImpl().actualizarTanque(fuelTanks);

	}

	public void updateControl(ControlsTanks tanks) throws GWorkException {
		try {
			Util.validarSession();
			EntityManagerHelper.beginTransaction();
			new ControlsTanksDAO().update(tanks);
			EntityManagerHelper.commit();
//			Util.limpiarSession();
		} catch (Exception e) {
//			Util.limpiarSession();
			EntityManagerHelper.rollback();
			throw new GWorkException(e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	public ControlsTanks consultarControlTanquePorId(Long idControlTanque) throws GWorkException {
		try {
			List<ControlsTanks> list = null;
			ControlsTanks controlsTanks = null;
			final String queryString = "select model from ControlsTanks model where model.cotCodigo = :idControlTanque";
			Query query = EntityManagerHelper.getEntityManager().createQuery(queryString);
			query.setParameter("idControlTanque", idControlTanque);
			list = (List<ControlsTanks>) query.getResultList();

			if (list != null && list.size() > 0)
				controlsTanks = (ControlsTanks) list.get(0);

			return controlsTanks;

		} catch (Exception e) {
			throw new GWorkException(e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	public ControlsTanks consultarControlTanquePorIdTanque(Long idTanque) throws GWorkException {
		try {
			if (EntityManagerHelper.getEntityManager().isOpen())
				EntityManagerHelper.getEntityManager().clear();
			
			List<ControlsTanks> list = null;
			ControlsTanks controlsTanks = null;
			final String queryString = "select model from ControlsTanks model where model.fuelTanks.ftaCodigo = :propertyValue";
			Query query = EntityManagerHelper.getEntityManager().createQuery(queryString);
			query.setParameter("propertyValue", idTanque);
			list = (List<ControlsTanks>) query.getResultList();

			if (list != null && list.size() > 0)
				controlsTanks = (ControlsTanks) list.get(0);

			return controlsTanks;

		} catch (Exception e) {
			throw new GWorkException(e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	public Float consultarGalonesActuales(Float valor, Long idTanque) throws GWorkException {
		try {
			List<FuelTanks> list = null;
			FuelTanks fuelTanks = null;
			Float valorActual = null;
			final String queryString = "select model from FuelTanks model where model.ftaCodigo = :propertyValue";
			Query query = EntityManagerHelper.getEntityManager().createQuery(queryString);
			query.setParameter("propertyValue", idTanque);
			list = (List<FuelTanks>) query.getResultList();
			if (list != null && list.size() > 0)
				fuelTanks = (FuelTanks) list.get(0);

			if (fuelTanks != null)
				valorActual = fuelTanks.getFtaGalonesActuales() + valor;

			return valorActual;

		} catch (Exception e) {
			throw new GWorkException(e.getMessage());
		}
	}

	public String consultarValorActualTanques(Long idTanque) throws GWorkException {
		try {
			Object valorActual = null;
			StringBuffer buffer = new StringBuffer();
			buffer
					.append("select SUM(model.cotGalonesActuales) FROM ControlsTanks model where model.fuelTanks.ftaCodigo = :idTanque");
			Query query = EntityManagerHelper.getEntityManager().createQuery(buffer.toString());
			query.setParameter("idTanque", idTanque);
			valorActual = (Object) query.getSingleResult();
			if (valorActual != null)
				return valorActual.toString();

			return "0";

		} catch (Exception e) {
			throw new GWorkException(e.getMessage());
		}
	}

	public String consultarValorActualTanquesRegistry(Long idTanque) throws GWorkException {
		try {
			Object valorActual = null;
			StringBuffer buffer = new StringBuffer();
			buffer
					.append("select SUM(model.serNumeroGalones) FROM ServiceRegistry model where pumps.fuelTanks.ftaCodigo = :idTanque");
			Query query = EntityManagerHelper.getEntityManager().createQuery(buffer.toString());
			query.setParameter("idTanque", idTanque);
			valorActual = (Object) query.getSingleResult();
			if (valorActual != null)
				return valorActual.toString();

			return "0";

		} catch (Exception e) {
			throw new GWorkException(e.getMessage());
		}
	}

	public Double consultarValorActualControlTakns(Long idTanque) throws GWorkException {
		Double suma = new Double(0);
		Double valor1 = new Double(consultarValorActualTanques(idTanque).toString());
		Double valor2 = new Double(consultarValorActualTanquesRegistry(idTanque).toString());

		if (valor1 > valor2)
			suma = valor1 + valor2;
		else
			suma = valor2 + valor1;

		return suma;
	}

	public void saveControl(ControlsTanks tanks) throws GWorkException {
		try {
			Util.validarSession();
			EntityManagerHelper.beginTransaction();
			new ControlsTanksDAO().save(tanks);
			EntityManagerHelper.commit();
//			Util.limpiarSession();
		} catch (Exception e) {
			EntityManagerHelper.rollback();
//			Util.limpiarSession();
			throw new GWorkException(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see geniar.siscar.logic.fuels.services.ControlTanksService#eliminarControl(java.lang.Long)
	 */
	public void eliminarControl(Long idTanks) throws GWorkException {
		try {
			ControlsTanks tanks = consultarControlTanquePorIdTanque(idTanks);

			if (tanks != null) {
				Util.validarSession();
				EntityManagerHelper.beginTransaction();
				new ControlsTanksDAO().delete(tanks);
				EntityManagerHelper.commit();
//				Util.limpiarSession();
			}
		} catch (Exception e) {
			EntityManagerHelper.rollback();
//			Util.limpiarSession();
			throw new GWorkException(e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	public List<ControlsTanks> consultarTanques() throws GWorkException {
		try {
			List<ControlsTanks> listTanks = null;
			StringBuffer buffer = new StringBuffer();
			buffer
					.append("select model From ControlsTanks model ORDER BY model.cotFechaCarga,model.fuelTanks.ftaNombre DESC");
			Query query = EntityManagerHelper.getEntityManager().createQuery(buffer.toString());
			listTanks = (List<ControlsTanks>) query.getResultList();
			if (listTanks != null)
				return listTanks;

			return null;
		} catch (Exception e) {
			throw new GWorkException(e.getMessage());
		}
	}
}
