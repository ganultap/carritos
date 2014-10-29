package geniar.siscar.view.parameters;

import geniar.siscar.logic.fuels.services.PumpsService;
import geniar.siscar.model.Pumps;
import gwork.exception.GWorkException;

import java.util.List;

import javax.faces.model.SelectItem;

public class SelectItemPumpsPage {

	private SelectItem[] items;
	private PumpsService pumpsService;

	public SelectItem[] getItems() {

		try {

			List<Pumps> list = pumpsService.consultarTodosPumps();
			items = new SelectItem[list.size() + 1];
			items[0] = new SelectItem("-1", "--SELECCIONAR--");

			int i = 1;

			for (Pumps pumps : list) {
				items[i] = new SelectItem(pumps.getPumCodigo(), pumps
						.getPumNombre());
				i++;
			}

		} catch (GWorkException e) {
			System.out.println(e.getMessage());
		}

		return items;
	}

	public void setItems(SelectItem[] items) {
		this.items = items;
	}

	public PumpsService getPumpsService() {
		return pumpsService;
	}

	public void setPumpsService(PumpsService pumpsService) {
		this.pumpsService = pumpsService;
	}
}
