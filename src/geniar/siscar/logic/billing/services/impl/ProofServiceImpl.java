package geniar.siscar.logic.billing.services.impl;

import geniar.siscar.logic.billing.services.ProofService;
import geniar.siscar.logic.consultas.SearchBilling;
import geniar.siscar.model.ActualOthersApplications;
import geniar.siscar.model.HeaderProof;
import geniar.siscar.model.ProofState;
import geniar.siscar.model.VhaAoaApp;
import geniar.siscar.persistence.ActualOthersApplicationsDAO;
import geniar.siscar.persistence.EntityManagerHelper;
import geniar.siscar.persistence.HeaderProofDAO;
import geniar.siscar.persistence.ProofStateDAO;
import geniar.siscar.persistence.VhaAoaAppDAO;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

import java.util.Date;
import java.util.List;

public class ProofServiceImpl implements ProofService {

	public List<Object[]> consultaProofByTypeAndPeriod(Long proofType,
			Long idPeriod) throws GWorkException {
		List<Object[]> result = null;

		result = new SearchBilling().consultaProofByTypeAndPeriod(proofType,
				idPeriod);

		if (result == null || result.size() == 0 || result.isEmpty())
			throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));

		return result;
	}

	public void anularComprobanteVehiculosAsignados(List<Long> listHepId,
			String user) {
		try {

			for (Long hepId : listHepId) {

				// Valida que el tipo de comprobante sea de asignación

				// Consulta estado anulado
				ProofStateDAO proofStateDAO = new ProofStateDAO();
				ProofState proofState = proofStateDAO.findById(new Long(Util
						.loadParametersValue("COMPROBANTE.ANULADO")));

				// Consulta comprobante según el group id
				HeaderProof headerProof = null;
				HeaderProofDAO headerProofDAO = new HeaderProofDAO();
				List<HeaderProof> listHeaderProof = headerProofDAO
						.findByProperty("hepId", hepId);
				if (listHeaderProof != null && !listHeaderProof.isEmpty()) {
					headerProof = listHeaderProof.get(0);
				}

				EntityManagerHelper.getEntityManager().refresh(headerProof);
				// Cambia el estado del comprobante a anulado y asigna fecha de
				// anulación y usuario que anula el comprobante
				headerProof.setProofState(proofState);
				headerProof.setFechaAnul(new Date());
				headerProof.setUserAnul(user);

				// Consulta los vehiculos asociados al comprobante
				VhaAoaAppDAO vhaAoaAppDAO = new VhaAoaAppDAO();
				List<VhaAoaApp> listVehiculosAsociados = vhaAoaAppDAO
						.findByProperty("headerProof.hepId", hepId);

				// Actualiza el comprobante
				headerProofDAO.update(headerProof);

				// Desasocia los vehiculos al comprobante
				for (VhaAoaApp vhaAoaApp : listVehiculosAsociados) {
					vhaAoaAppDAO.delete(vhaAoaApp);
				}

			}

			// Se confirma la transaccion
			EntityManagerHelper.getEntityManager().getTransaction().begin();
			EntityManagerHelper.getEntityManager().getTransaction().commit();
//			EntityManagerHelper.getEntityManager().clear();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<ActualOthersApplications> consultarDetalleComprobante(
			String groupId) {
		List<ActualOthersApplications> result = null;
		try {

			ActualOthersApplicationsDAO actualOthersApplicationsDAO = new ActualOthersApplicationsDAO();
			result = actualOthersApplicationsDAO.findByProperty(
					"headerProof.hepGroupId", groupId);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
}
