package geniar.siscar.test;

import geniar.siscar.model.CostCentersFuel;
import geniar.siscar.model.Prepaid;
import geniar.siscar.persistence.EntityManagerHelper;
import geniar.siscar.persistence.PrepaidDAO;

import java.util.List;

import javax.persistence.Query;

public class TestPrepaidCostCenterFuel {

	public static void main(String args[]) {

		List<Prepaid> listPrepaid = new PrepaidDAO().findAll();
		int iCount = 1;
		for (Prepaid prepaid : listPrepaid) {
			if (prepaid.getCostCentersFuel() == null) {
				CostCentersFuel centersFuel = null;

				centersFuel = TestPrepaidCostCenterFuel
						.getCostCenterFuel(prepaid.getPrePlaca());

				if (centersFuel != null) {
					System.out.println(iCount++ + "- PLACA: "
							+ prepaid.getPrePlaca() + " CCF_ID: "
							+ centersFuel.getCcfId());
					EntityManagerHelper.getEntityManager().getTransaction()
							.begin();
					prepaid.setCostCentersFuel(centersFuel);
					EntityManagerHelper.getEntityManager().getTransaction()
							.commit();

				} else
					System.out.println("PLACA SIN ASIGACION "
							+ prepaid.getPrePlaca());
			}
		}
	}

	static CostCentersFuel getCostCenterFuel(String placa) {

		String queryString = "select ccf from CostCentersFuel ccf "
				+ "where ccf.vehiclesAssignation.vehicles.vhcPlacaDiplomatica = :placa "
				+ "and ccf.ccfEstado = :estado "
				+ "and ccf.vehiclesAssignation.vhaFechaEntrega is not null "
				+ "and ccf.vehiclesAssignation.vhaFechaDev is null";

		Query query = EntityManagerHelper.getEntityManager().createQuery(
				queryString);
		query.setParameter("placa", placa.toUpperCase());
		query.setParameter("estado", "ACTIVO");

		if (query.getResultList() != null && query.getResultList().size() == 1
				&& query.getResultList().get(0) != null)
			return (CostCentersFuel) query.getSingleResult();
		else if (query.getResultList() != null
				&& query.getResultList().size() > 1
				&& query.getResultList().get(0) != null)
			System.out.println("DISTRUBUIDA PLACA: " + placa);

		return null;
	}
}
