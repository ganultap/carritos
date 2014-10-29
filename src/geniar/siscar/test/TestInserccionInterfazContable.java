package geniar.siscar.test;

import geniar.siscar.consults.impl.ConsultsServiceImpl;
import geniar.siscar.model.ActualOthersApplications;
import geniar.siscar.model.HeaderProof;
import geniar.siscar.model.ServiceRegistry;
import geniar.siscar.persistence.EntityManagerHelper;

import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Set;

import javax.persistence.Query;

public class TestInserccionInterfazContable {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		List<HeaderProof> listHeaderProof = null;
		Connection connection = null;
		try{
			connection = ConsultsServiceImpl.getConnection("jdbc/siscarFinanciero");
			final String queryString = "select model "
					+ "from headerProof model WHERE model.hepGroupId in (10712," +
							"10757,10904,10906,11002,11036,11159,11181,11427,11439," +
							"11513,12074,12090,12112,12113,12114,12115,12116,12117," +
							"12118,12175,12406,12701,12702,12703,201011121839862)";//12318,12319,
			
			Query query = EntityManagerHelper.getEntityManager().createQuery(
					queryString);
			
			listHeaderProof = query.getResultList();
	
			for (HeaderProof headerProof : listHeaderProof) {
	
				Set<ActualOthersApplications> listActual = headerProof.getActualOthersApplicationses();
	
				for (ActualOthersApplications actual : listActual) {
					System.err.println(actual.getPEntDr());
//						ConsultsServiceImpl.insercionContable(actual.getPSob(),
//								actual.getPAccdate(), actual.getPCurr(),
//								"GVALENCIA", actual.getPCategory(), actual
//										.getPSource(), null, null, null, actual
//										.getPCompany(), actual.getPAccount(),
//								actual.getPCcenter(), actual.getPAuxiliary(),
//								actual.getPEntDr(), actual.getPEntCr(), actual
//										.getPBname(), actual.getPDescription(),
//								null, actual.getPAttribute2(), actual
//										.getPAttribute5(), actual.getPAttribute6(),
//								null, null, actual.getPAttribute9(), actual
//										.getPAttribute10(), actual.getHeaderProof()
//										.getHepGroupId());
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}

	}
}
