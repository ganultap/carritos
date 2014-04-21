package geniar.siscar.logic.parameters.services.impl;

import java.util.List;

import geniar.siscar.logic.parameters.services.MoneyService;
import geniar.siscar.model.MoneyType;
import geniar.siscar.persistence.IMoneyTypeDAO;
import geniar.siscar.persistence.MoneyTypeDAO;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

public class MoneyServiceImpl implements MoneyService {

	public List<MoneyType> listMoney() throws GWorkException {
		IMoneyTypeDAO moneyTypeDAO = new MoneyTypeDAO();
		List<MoneyType> listMoney=moneyTypeDAO.findAll();
		
		if (listMoney.isEmpty())
			throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));
		
		return listMoney;
	}

}
