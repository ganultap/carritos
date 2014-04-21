package geniar.siscar.logic.parameters.services;

import geniar.siscar.model.MoneyType;
import gwork.exception.GWorkException;

import java.util.List;

public interface MoneyService {

	public  List<MoneyType> listMoney()throws GWorkException;
}
