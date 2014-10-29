/**
 * 
 */
package geniar.siscar.logic.vehicle.services.impl;

import geniar.siscar.logic.vehicle.services.TypeVehicleService;
import geniar.siscar.model.VehiclesTypes;
import geniar.siscar.persistence.EntityManagerHelper;
import geniar.siscar.persistence.IVehiclesTypesDAO;
import geniar.siscar.persistence.VehiclesTypesDAO;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

import java.util.List;

/**
 * @author Jorge - geniar
 * 
 */
public class TypeVehicleServiceImpl implements TypeVehicleService {

	/*
	 * (non-Javadoc)
	 * 
	 * @see geniar.siscar.logic.vehicle.services.ServiceTypeVehicle#agregarTipoVehiculo(java.lang.String)
	 */
	public void crearTipoVehiculo(String tvchNombre) throws GWorkException {

		try {
			if (tvchNombre.trim().length() == 0)
				throw new GWorkException(Util
						.loadErrorMessageValue("NOMBRETIPOVEHICULO"));

			VehiclesTypes vehiclesTypes = new VehiclesTypes();
			IVehiclesTypesDAO vehiclesTypesDAO = new VehiclesTypesDAO();
			List<VehiclesTypes> listVehicleTypes = vehiclesTypesDAO
					.findByVhtNombre(tvchNombre.trim());

			/** Valido que no exista un tipo de vehiculo con el mismo nombre */
			if (!listVehicleTypes.isEmpty())
				throw new GWorkException(Util
						.loadErrorMessageValue("TIPOVEHICULOEXISTE"));

			/** Si no exite otro nombre igual pasa a guardar */

			vehiclesTypes.setVhtNombre(tvchNombre.toUpperCase().trim());

			vehiclesTypesDAO.save(vehiclesTypes);

			EntityManagerHelper.getEntityManager().getTransaction().begin();
			EntityManagerHelper.getEntityManager().getTransaction().commit();
		} catch (Exception e) {
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.GUARDAR")
					+ e.getMessage());
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see geniar.siscar.logic.vehicle.services.ServiceTypeVehicle#consultarTipoVehiculo(java.lang.String)
	 */
	public VehiclesTypes consultarTipoVehiculo(String tvchNombre)
			throws GWorkException {
		IVehiclesTypesDAO VehiclesTypesDAO = new VehiclesTypesDAO();

		List<VehiclesTypes> listTipoVehiculo = VehiclesTypesDAO
				.findByVhtNombre(tvchNombre);
		if (listTipoVehiculo.size() >= 1) {
			return listTipoVehiculo.get(0);
		} else {
			throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see geniar.siscar.logic.vehicle.services.ServiceTypeVehicle#eliminarTipoVehiculo(java.lang.String)
	 */
	public void eliminarTipoVehiculo(String tvchNombre) throws GWorkException {

		if (EntityManagerHelper.getEntityManager().isOpen())
			EntityManagerHelper.getEntityManager().close();

		if (tvchNombre.trim().length() == 0)
			throw new GWorkException(Util
					.loadErrorMessageValue("NOMBRETIPOVEHICULO"));

		VehiclesTypes VehiclesTypes = new VehiclesTypes();
		IVehiclesTypesDAO VehiclesTypesDAO = new VehiclesTypesDAO();

		/** Se chequea que exista el nombre ingresado */
		List<VehiclesTypes> listTipoVehiculo = VehiclesTypesDAO
				.findByVhtNombre(tvchNombre.trim());
		if (listTipoVehiculo.isEmpty())
			throw new GWorkException(Util
					.loadErrorMessageValue("TIPOVEHICULOEXISTEN"));
		VehiclesTypes = listTipoVehiculo.get(0);

		/** se chequea que no tenga vehiculos asociados */
		if (!VehiclesTypes.getRequestses().isEmpty()
				|| !VehiclesTypes.getTariffses().isEmpty()
				|| !VehiclesTypes.getVehicleses().isEmpty())
			throw new GWorkException(Util
					.loadErrorMessageValue("ELIMINARTIPOVEHICULO"));

		VehiclesTypes = listTipoVehiculo.get(0);
		EntityManagerHelper.getEntityManager().getTransaction().begin();
		VehiclesTypesDAO.delete(VehiclesTypes);
		EntityManagerHelper.commit();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see geniar.siscar.logic.vehicle.services.ServiceTypeVehicle#listTipoVehiculo()
	 */
	public List<VehiclesTypes> listTipoVehiculo() throws GWorkException {
		IVehiclesTypesDAO VehiclesTypesDAO = new VehiclesTypesDAO();

		List<VehiclesTypes> listTipoVehiculo = VehiclesTypesDAO.findAll();

		if (listTipoVehiculo.size() < 1) {
			throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));
		} else {
			return listTipoVehiculo;
		}
	}

	public void modificarTipoVehiculo(Long vhtCodigo, String vhtNombre)
			throws GWorkException {

		if (EntityManagerHelper.getEntityManager().isOpen())
			EntityManagerHelper.getEntityManager().close();

		if (vhtNombre.trim().length() == 0)
			throw new GWorkException(Util
					.loadErrorMessageValue("NOMBRETIPOVEHICULO"));

		VehiclesTypes vehiclesTypes = new VehiclesTypes();
		IVehiclesTypesDAO vehiclesTypesDAO = new VehiclesTypesDAO();
		vehiclesTypes = vehiclesTypesDAO.findById(vhtCodigo);

		/** Consulto que el tipo de vehiculo exista */
		if (vehiclesTypes == null)
			throw new GWorkException(Util
					.loadErrorMessageValue("TIPOVEHICULOEXISTEN"));

		if (!vehiclesTypesDAO.findByVhtNombre(vhtNombre.trim()).isEmpty()
				&& vehiclesTypes.getVhtNombre().trim().equals(vhtNombre))
			throw new GWorkException(Util
					.loadErrorMessageValue("TIPOVEHICULOEXISTE"));

		vehiclesTypes.setVhtNombre(vhtNombre.toUpperCase().trim());
		EntityManagerHelper.beginTransaction();
		vehiclesTypesDAO.update(vehiclesTypes);
		EntityManagerHelper.commit();

	}

	public VehiclesTypes consultarTipoVehiculoById(Long vhtCodigo)
			throws GWorkException {
		return new VehiclesTypesDAO().findById(vhtCodigo);
	}

}
