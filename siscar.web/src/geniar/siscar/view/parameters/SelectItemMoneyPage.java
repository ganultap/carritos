package geniar.siscar.view.parameters;

import geniar.siscar.logic.parameters.services.MoneyService;
import geniar.siscar.model.MoneyType;

import java.util.List;

import javax.faces.model.SelectItem;

public class SelectItemMoneyPage {

	private SelectItem listUtilMoney[];
	private List<MoneyType> listMoney;
	private MoneyService moneyService;

	public MoneyService getMoneyService() {
		return moneyService;
	}

	public void setMoneyService(MoneyService moneyService) {
		this.moneyService = moneyService;
	}

	public SelectItem[] getListUtilMoney() {
		try {
			listUtilMoney = null;
			listMoney = moneyService.listMoney();
			listUtilMoney = new SelectItem[listMoney.size() + 1];
			listUtilMoney[0] = new SelectItem("-1", "--SELECCIONAR--");
			int i = 1;

			for (MoneyType moneyType : listMoney) {
				listUtilMoney[i] = new SelectItem(moneyType.getMneyId(),
						moneyType.getMneyNombre());
				i++;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return listUtilMoney;
	}

	public void setListUtilMoney(SelectItem[] listUtilMoney) {
		
		this.listUtilMoney = listUtilMoney;
	}

	public List<MoneyType> getListMoney() {
		return listMoney;
	}

	public void setListMoney(List<MoneyType> listMoney) {
		this.listMoney = listMoney;
	}

}
