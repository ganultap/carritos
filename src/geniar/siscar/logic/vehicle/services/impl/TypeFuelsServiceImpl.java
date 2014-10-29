/**
 * 
 */
package geniar.siscar.logic.vehicle.services.impl;

import geniar.siscar.logic.vehicle.services.TypeFuelsService;
import geniar.siscar.model.FuelsTypes;
import geniar.siscar.persistence.EntityManagerHelper;
import geniar.siscar.persistence.FuelsTypesDAO;
import geniar.siscar.persistence.IFuelsTypesDAO;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

import java.util.List;

/**
 * @author Jorge
 * 
 */
public class TypeFuelsServiceImpl implements TypeFuelsService {

	/*
	 * (non-Javadoc)
	 * 
	 * @see geniar.siscar.logic.vehicle.services.ServiceTypeFuels#consultarTipoCombustible(java.lang.String)
	 */
	public FuelsTypes consultarTipoCombustible(String tcNombre) {
		IFuelsTypesDAO FuelsTypesDAO = new FuelsTypesDAO();

		try {
			List<FuelsTypes> listTipoCombustible = FuelsTypesDAO
					.findByFutNombre(tcNombre);

			if (listTipoCombustible.size() >= 1) {
				return listTipoCombustible.get(0);
			} else {
				throw new Exception(Util.loadErrorMessageValue("CONSULTA"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see geniar.siscar.logic.vehicle.services.ServiceTypeFuels#crearTipoCombustible(java.lang.String)
	 */
	public void crearTipoCombustible(String tcNombre) throws GWorkException {
		try {
			FuelsTypes FuelsTypes = new FuelsTypes();

			IFuelsTypesDAO FuelsTypesDAO = new FuelsTypesDAO();

			if (tcNombre.trim().length() == 0)
				throw new GWorkException(Util
						.loadErrorMessageValue("NOMBRECOMBUSTIBLE"));

			/**
			 * Se chequea que no haya otro tipo de combustible con el nombre
			 * ingreso
			 */
			List<FuelsTypes> listTipoCombustible = FuelsTypesDAO
					.findByFutNombre(tcNombre.trim());
			if (listTipoCombustible.size() >= 1)
				throw new GWorkException(Util.loadErrorMessageValue("NOMBRE"));

			/** Si no existe pasamos a crear el tipo de combustible */
			Util.validarSession();
			FuelsTypes.setFutNombre(tcNombre.toUpperCase().trim());
			EntityManagerHelper.getEntityManager().getTransaction().begin();
			FuelsTypesDAO.save(FuelsTypes);
			EntityManagerHelper.commit();
			//Util.limpiarSession();
		} catch (Exception e) {
			//Util.limpiarSession();
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.GUARDAR.GENERAL"));
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see geniar.siscar.logic.vehicle.services.ServiceTypeFuels#eliminarTipoCombustible(java.lang.String)
	 */
	public void eliminarTipoCombustible(String tcNombre) throws GWorkException {
		FuelsTypes fuelsTypes = new FuelsTypes();
		IFuelsTypesDAO fuelsTypesDAO = new FuelsTypesDAO();

		if (EntityManagerHelper.getEntityManager().isOpen())
			EntityManagerHelper.getEntityManager().close();

		if (tcNombre.trim().length() == 0)
			throw new GWorkException(Util
					.loadErrorMessageValue("NOMBRECOMBUSTIBLE"));

		/** Se chequea que exista el nombre del tipo de combustible */
		List<FuelsTypes> listTipoCombustible = fuelsTypesDAO
				.findByFutNombre(tcNombre);
		if (listTipoCombustible.isEmpty())
			throw new GWorkException(Util
					.loadErrorMessageValue("TIPOCOMBUSTIBLEEXISTEN"));
		/**
		 * Se chequea que no tenga vehiculos ni tafiras asociadas para eliminar
		 */
		fuelsTypes = listTipoCombustible.get(0);
		if (!fuelsTypes.getVehicleses().isEmpty())
			throw new GWorkException(Util
					.loadErrorMessageValue("ELIMINARTIPOCOMBUSTIBLE"));

		EntityManagerHelper.getEntityManager().getTransaction().begin();
		fuelsTypes.setFutCodigo(listTipoCombustible.get(0).getFutCodigo());
		fuelsTypesDAO.delete(fuelsTypes);

		EntityManagerHelper.commit();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see geniar.siscar.logic.vehicle.services.ServiceTypeFuels#listTipoCombustible()
	 */
	public List<FuelsTypes> listTipoCombustible() {
		IFuelsTypesDAO FuelsTypesDAO = new FuelsTypesDAO();

		try {
			/** Se chequea que existan tipos de combustible */
			List<FuelsTypes> listTipoCombustible = FuelsTypesDAO.findAll();
			if (listTipoCombustible.size() < 1) {
				throw new Exception(Util.loadErrorMessageValue("CONSULTA"));
			} else {
				return listTipoCombustible;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void modificarTipoCombustible(Long idTipoCombustible, String tcNombre)
			throws GWorkException {

		if (EntityManagerHelper.getEntityManager().isOpen())
			EntityManagerHelper.getEntityManager().close();

		FuelsTypes fuelsTypes = new FuelsTypes();
		IFuelsTypesDAO fuelsTypesDAO = new FuelsTypesDAO();

		if (tcNombre.trim().length() == 0)
			throw new GWorkException(Util
					.loadErrorMessageValue("NOMBRECOMBUSTIBLE"));

		/** Consultar si existe e el tipo de combustible */
		fuelsTypes = fuelsTypesDAO.findById(idTipoCombustible);
		if (fuelsTypes == null)
			throw new GWorkException(Util
					.loadErrorMessageValue("TIPOCOMBUSTIBLEEXISTEN"));

		/** Se chequea que no exista el nombre del tipo de combustible */
		List<FuelsTypes> listTipoCombustible = fuelsTypesDAO
				.findByFutNombre(tcNombre.trim());
		if (!listTipoCombustible.isEmpty())
			throw new GWorkException(Util.loadErrorMessageValue("NOMBRE"));

		fuelsTypes.setFutNombre(tcNombre.toUpperCase().trim());
		EntityManagerHelper.getEntityManager().getTransaction().begin();
		fuelsTypesDAO.update(fuelsTypes);
		EntityManagerHelper.commit();

	}

	public FuelsTypes consultarTipoCombustibleById(Long idCombustible) {
		IFuelsTypesDAO fuelsTypesDAO = new FuelsTypesDAO();
		return fuelsTypesDAO.findById(idCombustible);

	}

}
