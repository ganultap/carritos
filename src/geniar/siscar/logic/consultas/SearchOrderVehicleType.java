package geniar.siscar.logic.consultas;

import geniar.siscar.model.VehiclesTypes;
import geniar.siscar.persistence.EntityManagerHelper;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.Query;

public class SearchOrderVehicleType {
	
	@SuppressWarnings("unchecked")
	public List<VehiclesTypes> typeVehiclesOrder() {
		EntityManagerHelper.log("finding all VehiclesTypes instances", Level.INFO, null);
		try {
			final String queryString = "select model from VehiclesTypes model ORDER BY model.vhtNombre ASC";
			Query query = EntityManagerHelper.getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}
