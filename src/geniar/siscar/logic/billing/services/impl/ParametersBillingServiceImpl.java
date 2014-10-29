package geniar.siscar.logic.billing.services.impl;

import java.util.List;

import geniar.siscar.logic.billing.services.ParametersBillingService;
import geniar.siscar.model.Period;
import geniar.siscar.model.ProofType;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

public class ParametersBillingServiceImpl implements ParametersBillingService {

	public List<ProofType> listProofTypeOrder() throws GWorkException {

		List<ProofType> listProofType = new SearchParametersBilling()
				.listProofTypeOrder();

		if (listProofType == null || listProofType.size() == 0
				|| listProofType.isEmpty())
			throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));
		return listProofType;
	}

	public List<Period> listPeriodOrder() throws GWorkException {

		List<Period> listPeriodOrder = new SearchParametersBilling()
				.listPeriodOrder();

		if (listPeriodOrder == null || listPeriodOrder.size() == 0
				|| listPeriodOrder.isEmpty())
			throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));
		return listPeriodOrder;
	}

	public List<Period> listPeriodFuel() throws GWorkException {

		List<Period> listPeriodOrder = new SearchParametersBilling()
				.listPeriodFuel();

		if (listPeriodOrder == null || listPeriodOrder.size() == 0
				|| listPeriodOrder.isEmpty())
			throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));
		return listPeriodOrder;
	}

	public List<Period> listPeriodAssignation() throws GWorkException {

		List<Period> listPeriodOrder = new SearchParametersBilling()
				.listPeriodAssignation();

		if (listPeriodOrder == null || listPeriodOrder.size() == 0
				|| listPeriodOrder.isEmpty())
			throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));
		return listPeriodOrder;
	}
}
