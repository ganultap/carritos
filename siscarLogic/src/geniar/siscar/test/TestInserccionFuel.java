package geniar.siscar.test;

import geniar.siscar.consults.impl.ConsultsServiceImpl;
import geniar.siscar.model.ActualOthersApplications;
import geniar.siscar.model.ServiceRegistry;
import geniar.siscar.persistence.EntityManagerHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Set;

import javax.persistence.Query;

public class TestInserccionFuel {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		List<ServiceRegistry> list = null;

		String fecha = "27-APR-2009";
		java.util.Date fechaA= null;
		try{
		try {
			fechaA = new SimpleDateFormat("dd-MMM-yyyy").parse(fecha);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		final String queryString = "select  model "
				+ "from ServiceRegistry model WHERE model.serFecha = :fecha and model.headerProof.hepId is not null";//and model.headerProof.hepId in (1066,1067,1068,1069,1070,1073)

		Query query = EntityManagerHelper.getEntityManager().createQuery(
				queryString);
		query.setParameter("fecha", fechaA);

		list = query.getResultList();

		for (ServiceRegistry serviceRegistry : list) {

			Set<ActualOthersApplications> listActual = serviceRegistry
					.getHeaderProof().getActualOthersApplicationses();

			for (ActualOthersApplications actual : listActual) {

				  ConsultsServiceImpl.insercionContable(actual.getPSob(),	actual.getPAccdate(), actual.getPCurr(), "GVALENCIA",	actual.getPCategory(), actual.getPSource(), null, null, null,
				  actual.getPCompany(), actual.getPAccount(), actual.getPCcenter(), actual.getPAuxiliary(),	actual.getPEntDr(), actual.getPEntCr(), actual.getPBname(),
				  actual.getPDescription(), null, actual.getPAttribute2(),	actual.getPAttribute5(), actual.getPAttribute6(), null, null,	actual.getPAttribute9(), actual.getPAttribute10(),
				  actual.getHeaderProof().getHepGroupId());
			}
		}
		}catch (Exception e) {
			e.printStackTrace();
		}

	}
}
