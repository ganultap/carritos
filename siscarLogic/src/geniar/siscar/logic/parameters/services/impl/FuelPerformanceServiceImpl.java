package geniar.siscar.logic.parameters.services.impl;

import geniar.siscar.logic.consultas.SearchFuelPerformance;
import geniar.siscar.logic.parameters.services.FuelPerformanceService;
import geniar.siscar.model.FuelPerformance;
import geniar.siscar.model.FuelsTypes;
import geniar.siscar.model.Lines;
import geniar.siscar.model.TractionsTypes;
import geniar.siscar.persistence.EntityManagerHelper;
import geniar.siscar.persistence.FuelPerformanceDAO;
import geniar.siscar.persistence.FuelsTypesDAO;
import geniar.siscar.persistence.IFuelPerformanceDAO;
import geniar.siscar.persistence.LinesDAO;
import geniar.siscar.persistence.TractionsTypesDAO;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

import java.util.List;

public class FuelPerformanceServiceImpl implements FuelPerformanceService {

	/*
	 * (non-Javadoc)
	 * 
	 * @see geniar.siscar.logic.parameters.services.FuelPerformanceService
	 *      #consultarTodosFuelPerformance()
	 */
	public List<FuelPerformance> consultarTodosFuelPerformance()
			throws GWorkException {
		// limpiarSession();
		return new FuelPerformanceDAO().findAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see geniar.siscar.logic.parameters.services.FuelPerformanceService#
	 *      crearFuelPerformance(java.lang.Long, java.lang.Long, java.lang.Long,
	 *      java.lang.Long)
	 */
	public void crearFuelPerformance(Long idLinea, Long idTipoTraccion,
			Long idTipoCombust, Float valorRendim) throws GWorkException {

		IFuelPerformanceDAO fuelPerformanceDAO = new FuelPerformanceDAO();
		FuelPerformance fuelPerformance = new FuelPerformance();

		if (new SearchFuelPerformance()
				.consultarLlaveLineaTipoTraccionTipoCombustible(idLinea,
						idTipoTraccion, idTipoCombust)) {
			throw new GWorkException(Util
					.loadErrorMessageValue("ERR.LINEATRACCOMBUS"));
		}

		Lines lines = new LinesDAO().findById(idLinea);
		TractionsTypes tractionsTypes = new TractionsTypesDAO()
				.findById(idTipoTraccion);
		FuelsTypes fuelsTypes = new FuelsTypesDAO().findById(idTipoCombust);

		try {
			fuelPerformance.setLines(lines);
			fuelPerformance.setTractionsTypes(tractionsTypes);
			fuelPerformance.setFuelsTypes(fuelsTypes);
			fuelPerformance.setValorRendimiento(valorRendim.floatValue());

			EntityManagerHelper.beginTransaction();
			fuelPerformanceDAO.save(fuelPerformance);
			EntityManagerHelper.commit();
			// limpiarSession();
		} catch (Exception e) {
			// limpiarSession();
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.GUARDAR"));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see geniar.siscar.logic.parameters.services.FuelPerformanceService
	 *      #modificarFuelPerformance(java.lang.Long, java.lang.Long,
	 *      java.lang.Long, java.lang.Long)
	 */
	public void modificarFuelPerformance(Long idLinea, Long idTipoTraccion,
			Long idTipoCombust, Float valorRendim) throws GWorkException {
		validarSession();
		// Nuevo Dao
		IFuelPerformanceDAO assignationsPoliciesDAO = new FuelPerformanceDAO();

		// Se Busca el objeto al cual se quiere modificar sus atributos
		FuelPerformance fuelPerformance = new SearchFuelPerformance()
				.consultarFuelPerformance(idLinea, idTipoTraccion,
						idTipoCombust);

		// Se valida que el objeto exista
		if (fuelPerformance == null) {
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.GUARDARRENDIM"));
		}

		try {
			fuelPerformance.setValorRendimiento(valorRendim.floatValue());

			// Se pide el comienzo de la transacción y actualización.
			EntityManagerHelper.beginTransaction();
			assignationsPoliciesDAO.update(fuelPerformance);
			EntityManagerHelper.commit();
			// limpiarSession();
		} catch (Exception e) {
			// limpiarSession();
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.GUARDARRENDIM"));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see geniar.siscar.logic.parameters.services.
	 *      FuelPerformanceService#consultarFuelPerformance(java.lang.Long,
	 *      java.lang.Long, java.lang.Long)
	 */
	public FuelPerformance consultarFuelPerformance(Long idLineaVehiculo,
			Long idTipoCombustible, Long idTipoTraccion) throws GWorkException {
		validarSession();
		return new SearchFuelPerformance().consultarFuelPerformance(
				idLineaVehiculo, idTipoTraccion, idTipoCombustible);
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
