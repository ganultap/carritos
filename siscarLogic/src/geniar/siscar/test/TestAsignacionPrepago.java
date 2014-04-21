package geniar.siscar.test;

import geniar.siscar.model.CostCentersFuel;
import geniar.siscar.model.Prepaid;
import geniar.siscar.persistence.EntityManagerHelper;
import geniar.siscar.persistence.PrepaidDAO;

import java.util.List;

import javax.persistence.Query;

public class TestAsignacionPrepago {

	public static void main(String[] args) {
		List<Prepaid> listPrepaid = new PrepaidDAO().findAll();

		for (Prepaid prepaid : listPrepaid) {
			List<CostCentersFuel> listC = TestAsignacionPrepago
					.ccByPlaca(prepaid.getPrePlaca());

			if (listC != null && listC.size() > 0) {
				prepaid.setCostCentersFuel(listC.get(0));
				EntityManagerHelper.getEntityManager().getTransaction().begin();
				new PrepaidDAO().update(prepaid);
				EntityManagerHelper.getEntityManager().getTransaction()
						.commit();
			}
		}
	}

	@SuppressWarnings("unchecked")
	public static List<CostCentersFuel> ccByPlaca(String placa) {
		final String queryString = "select model from CostCentersFuel model "
				+ "where model.vehiclesAssignation.vehicles.vhcPlacaDiplomatica = :placa";

		Query query = EntityManagerHelper.getEntityManager().createQuery(
				queryString);
		query.setParameter("placa", placa);
		if (query.getResultList() != null && query.getResultList().size() > 0)
			return query.getResultList();

		
		return null;
	}
}
