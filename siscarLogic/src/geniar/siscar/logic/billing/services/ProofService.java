package geniar.siscar.logic.billing.services;

import java.util.List;

import geniar.siscar.model.ActualOthersApplications;
import gwork.exception.GWorkException;

public interface ProofService {

	public java.util.List<Object[]> consultaProofByTypeAndPeriod(
			Long proofType, Long idPeriod) throws GWorkException;

	public void anularComprobanteVehiculosAsignados(List<Long> listHepId,
			String user);

	public List<ActualOthersApplications> consultarDetalleComprobante(
			String groupId);

}
