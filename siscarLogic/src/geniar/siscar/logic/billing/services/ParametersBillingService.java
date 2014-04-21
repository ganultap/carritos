package geniar.siscar.logic.billing.services;

import geniar.siscar.model.Period;
import geniar.siscar.model.ProofType;
import gwork.exception.GWorkException;

import java.util.List;

public interface ParametersBillingService {

	public List<ProofType> listProofTypeOrder() throws GWorkException;

	public List<Period> listPeriodOrder() throws GWorkException;

	public List<Period> listPeriodFuel() throws GWorkException;

	public List<Period> listPeriodAssignation() throws GWorkException;

}
