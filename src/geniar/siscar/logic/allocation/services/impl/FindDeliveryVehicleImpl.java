package geniar.siscar.logic.allocation.services.impl;

import geniar.siscar.logic.allocation.services.FindDeliveryVehicle;
import geniar.siscar.model.VehiclesAssignation;
import geniar.siscar.persistence.EntityManagerHelper;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class FindDeliveryVehicleImpl implements FindDeliveryVehicle {

	private static final Log log = LogFactory.getLog(FindDeliveryVehicleImpl.class);
	
	public static final String VHA_NUMERO_CARNE = "vhaNumeroCarne";

	public List<VehiclesAssignation> findByVhaNumeroCarne(Object vhaNumeroCarne)
			throws GWorkException {

		List<VehiclesAssignation> listVehiclesAssignation = findVehiclesAssignationByUser(
				VHA_NUMERO_CARNE, vhaNumeroCarne);
		if (listVehiclesAssignation == null
				|| listVehiclesAssignation.isEmpty())
			throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));
		return listVehiclesAssignation;
	}

	@SuppressWarnings("unchecked")
	public List<VehiclesAssignation> findRentVehiclesByUser(
			String propertyName, final Object value) throws GWorkException {
		EntityManagerHelper.log(
				"finding VehiclesAssignation instance with property: "
						+ propertyName + ", value: " + value, Level.INFO, null);

		String estadoAsignacion = Util.loadMessageValue("ALQUILADO");
		final String queryString = "select model from VehiclesAssignation model where model."
				+ propertyName
				+ "= :propertyValue and model.assignationsStates.assCodigo=4";
		EntityManagerHelper.getEntityManager().clear();
		Query query = EntityManagerHelper.getEntityManager().createQuery(
				queryString);
		query.setParameter("propertyValue", value);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<VehiclesAssignation> findVehiclesAssignationByUser(
			String propertyName, final Object value) throws GWorkException {
		EntityManagerHelper.log(
				"finding VehiclesAssignation instance with property: "
						+ propertyName + ", value: " + value, Level.INFO, null);

		String estadoAsignacion = Util.loadMessageValue("ASIGNADO");
		final String queryString = "select model from VehiclesAssignation model where model."
				+ propertyName
				+ "= :propertyValue and model.assignationsStates.assNombre='"
				+ estadoAsignacion + "'";
		EntityManagerHelper.getEntityManager().clear();
		Query query = EntityManagerHelper.getEntityManager().createQuery(
				queryString);
		query.setParameter("propertyValue", value);
		return query.getResultList();
	}

	public List<VehiclesAssignation> findByRentAssinationCarne(
			Object vhaNumeroCarne) throws GWorkException {

		List<VehiclesAssignation> listVehiclesAssignation = findRentVehiclesByUser(
				VHA_NUMERO_CARNE, vhaNumeroCarne);
		if (listVehiclesAssignation == null
				|| listVehiclesAssignation.isEmpty())
			throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));
		return listVehiclesAssignation;
	}

	public static void main(String[] args) {
		FindDeliveryVehicleImpl deliveryVehicleImpl = new FindDeliveryVehicleImpl();

		try {
			for (VehiclesAssignation vehiclesAssignation : deliveryVehicleImpl
					.findByVhaNumeroCarne("01123")) {
				System.out.println(vehiclesAssignation.getVhaNumeroCarne());
			}
		} catch (GWorkException e) {
			log.error("findByRentAssinationCarne", e);
		}

	}
}
