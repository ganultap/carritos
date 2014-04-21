package geniar.siscar.test.log;

import geniar.siscar.model.InconsistenciesTypes;
import geniar.siscar.persistence.EntityManagerHelper;
import geniar.siscar.persistence.InconsistenciesTypesDAO;
import geniar.siscar.util.Util;

public class Demo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		System.out.println("Prueba del log de Hibernate");
		System.out.println("===========================");

		InconsistenciesTypesDAO dao = new InconsistenciesTypesDAO();
		InconsistenciesTypes types = new InconsistenciesTypes();

		types.setIctId(10L);
		types.setIctNombre("asdasdasda");

		Util.validarSession();
		EntityManagerHelper.beginTransaction();
		dao.save(types);
		EntityManagerHelper.commit();
		// Util.limpiarSession();

		// JobsDAO dao = new JobsDAO();
		// Jobs job = new Jobs();
		// Transaction tx = dao.getSession().getTransaction();
		// tx.begin();
		// job.setJobId("des");
		// job.setJobTitle("desarrollador");
		// job.setMaxSalary(5000L);
		// job.setMinSalary(100L);
		// dao.save(job);
		// tx.commit();
	}
}
