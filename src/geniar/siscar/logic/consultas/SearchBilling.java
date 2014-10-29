package geniar.siscar.logic.consultas;

import geniar.siscar.model.FuelTypeRequest;
import geniar.siscar.persistence.EntityManagerHelper;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author alvaro.hincapie
 * 
 */
public class SearchBilling {

	private static final Log log = LogFactory.getLog(SearchBilling.class);

	private static EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Consulta los comprobantes por su tipo y periodo.
	 * 
	 * @param proofType:
	 *            Tipo de comprobante
	 * @param idPeriod:
	 *            Identificador del Periodo
	 * @return List<Object[]>
	 * @throws GWorkException
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> consultaProofByTypeAndPeriod(Long proofType,
			Long idPeriod) throws GWorkException {
		List<Object[]> result = null;
		try {
			StringBuffer queryString = new StringBuffer();
			queryString
					.append("SELECT model.headerProof.hepGroupId, model.headerProof.proofType.prtNombre, "
							+ "model.headerProof.period.perFechaIni, model.headerProof.period.perFechaFin, "
							+ "sum(model.PEntDr), model.PAccdate,  "
							+ "model.headerProof.proofType.prtCodigo, model.headerProof.hepId "
							+ "FROM ActualOthersApplications "
							+ "model JOIN model.headerProof header ");// '0'
			// as
			// anulado,

			if ((proofType != null && proofType >= 0)
					|| (idPeriod != null && idPeriod >= 0)) {
				queryString.append("WHERE ");
			}

			if (proofType != null && proofType >= 0) {
				queryString
						.append("model.headerProof.proofType.prtCodigo = :proofType ");
			}

			if ((proofType != null && proofType >= 0)
					&& (idPeriod != null && idPeriod >= 0)) {
				queryString.append("AND ");
			}

			if (idPeriod != null && idPeriod >= 0) {
				queryString.append("model.headerProof.period.perId = :period ");
			}

			queryString
					.append("AND model.headerProof.proofState.prsCodigo = 1");
			queryString.append(" GROUP BY model.headerProof.hepGroupId, "
					+ "model.headerProof.proofType.prtNombre, "
					+ "model.headerProof.period.perFechaIni, "
					+ "model.headerProof.period.perFechaFin, "
					+ "model.PAccdate, " + "model.headerProof.hepId, "
					+ "model.headerProof.proofType.prtCodigo");

			Query query = getEntityManager()
					.createQuery(queryString.toString());
			if (proofType != null && proofType >= 0) {
				query.setParameter("proofType", proofType);
			}

			if (idPeriod != null && idPeriod >= 0) {
				query.setParameter("period", idPeriod);
			}

			// List<ActualOthersApplications> result2 =
			// (List<ActualOthersApplications>) query.getResultList();
			// result2.size();

			result = (List<Object[]>) query.getResultList();
			result.size(); // ??

			// for (int i = result.size() - 1; i >= 0; i--) {
			// Object[] aux = result.get(i);
			//
			// // consultar
			// VhaAoaAppDAO vhaAoaAppDAO = new VhaAoaAppDAO();
			// List<VhaAoaApp> listVehiculosAsociados = vhaAoaAppDAO
			// .findByProperty("headerProof.hepId", Long
			// .parseLong(String.valueOf(aux[7])));
			//
			// if (listVehiculosAsociados !=null
			// && listVehiculosAsociados.size() > 0)
			// result.remove(i);
			// }

		} catch (RuntimeException e) {
			log.error("ConsultarComprobantes", e);
		}

		return result;
	}

	@SuppressWarnings("unchecked")
	public String getListTypeRequest() throws GWorkException {
		try {
			StringBuffer buffer = new StringBuffer();
			final String queryString = "SELECT model FROM FuelTypeRequest model";
			Query query = getEntityManager().createQuery(queryString);

			List<FuelTypeRequest> list = query.getResultList();
			if (list != null && !list.isEmpty()) {
				buffer.append("tr");
				int i = 0;
				for (FuelTypeRequest fuelTypeRequest : list) {
					buffer.append(fuelTypeRequest.getFtrCodigo());
					buffer.append(" ");
					buffer.append(fuelTypeRequest.getFtrNombre());
					buffer.append(" ");
					buffer.append("'");
					buffer.append(i++);
					buffer.append("'");
					buffer.append(" ");
				}
				System.out.println(buffer.toString());
				return buffer.toString();
			} else
				return null;
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.CAPNOENCONTRAD"));
		}
	}

	public static void main(String[] args) {
		SearchBilling searchProof = new SearchBilling();
		try {
			System.out.println(searchProof.getListTypeRequest());
		} catch (GWorkException e) {
			e.printStackTrace();
		}
	}

}
