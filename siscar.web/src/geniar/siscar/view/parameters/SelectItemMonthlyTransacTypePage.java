package geniar.siscar.view.parameters;

import geniar.siscar.logic.policies.services.MonthTransacTypeService;
import geniar.siscar.model.MonthTransacType;
import geniar.siscar.util.FacesUtils;
import gwork.exception.GWorkException;

import java.util.List;

import javax.faces.model.SelectItem;

/**
 * 
 * @author Geniar
 *
 */
public class SelectItemMonthlyTransacTypePage {

	private SelectItem[] items;
	private MonthTransacTypeService monthTransacTypeService;
	private List<MonthTransacType> list;

	public SelectItem[] getItems() {

		try {
			list = monthTransacTypeService.
			consultarTodosTiposTransacMensual();

			if (list == null || list.size() == 0) {
				items = new SelectItem[1];			
			} else items = new SelectItem[list.size()+1];
			
			items [0] = new SelectItem(-1,"--SELECCIONAR--");
			
			int i = 1;
			
			if (list == null || list.size() == 0) {
				return items;
			}
			
			for (MonthTransacType monthTransacType : list) {
				items [i] = new SelectItem(monthTransacType.getMttId(),
						monthTransacType.getMttNombre());
				i++;
			}

		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return items;
	}

	public void setItems(SelectItem[] items) {
		this.items = items;
	}

	public MonthTransacTypeService getMonthTransacTypeService() {
		return monthTransacTypeService;
	}

	public void setMonthTransacTypeService(
			MonthTransacTypeService monthTransacTypeService) {
		this.monthTransacTypeService = monthTransacTypeService;
	}

	public List<MonthTransacType> getList() {
		return list;
	}

	public void setList(List<MonthTransacType> list) {
		this.list = list;
	}

}
