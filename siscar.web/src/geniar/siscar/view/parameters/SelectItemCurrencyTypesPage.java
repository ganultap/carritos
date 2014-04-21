package geniar.siscar.view.parameters;

import geniar.siscar.logic.parameters.services.CurrencyTypesService;
import geniar.siscar.model.CurrencyTypes;
import geniar.siscar.util.FacesUtils;
import gwork.exception.GWorkException;

import java.util.List;

import javax.faces.model.SelectItem;

public class SelectItemCurrencyTypesPage {

	private SelectItem[] itemsTipoMoneda;
	private CurrencyTypesService currencyTypesService;

	public SelectItem[] getItemsTipoMoneda() {

		List<CurrencyTypes> lstTipoMoneda = null;
		try {
			lstTipoMoneda = currencyTypesService.consultarTodosTiposMoneda();
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
		itemsTipoMoneda = new SelectItem[lstTipoMoneda.size()+1];

		itemsTipoMoneda[0] = new SelectItem(-1,"--SELECCIONAR--");

		int i = 1;
		for (CurrencyTypes currencyTypes : lstTipoMoneda) {
			itemsTipoMoneda[i] = new SelectItem(
					currencyTypes.getCtId(), currencyTypes.getCtNombre());
			i++;
		}

		return itemsTipoMoneda;
	}

	public void setItemsTipoMoneda(SelectItem[] itemsTipoMoneda) {
		this.itemsTipoMoneda = itemsTipoMoneda;
	}

	public CurrencyTypesService getCurrencyTypesService() {
		return currencyTypesService;
	}

	public void setCurrencyTypesService(
			CurrencyTypesService currencyTypesService) {
		this.currencyTypesService = currencyTypesService;
	}
}
