package geniar.siscar.view.parameters;

import geniar.siscar.logic.consultas.SearchParameters;
import geniar.siscar.logic.parameters.services.FuelsTypesService;
import geniar.siscar.model.FuelsTypes;
import geniar.siscar.util.FacesUtils;
import gwork.exception.GWorkException;

import java.util.List;
import javax.faces.model.SelectItem;

public class SelectItemFuelsTypesPage {
	private SelectItem[] listFuelsTypes;
	private List<FuelsTypes> listUtilFuelsTypes;
	private FuelsTypesService fuelsTypesService;

	public FuelsTypesService getFuelsTypesService() {
		return fuelsTypesService;
	}

	public void setFuelsTypesService(FuelsTypesService fuelsTypesService) {
		this.fuelsTypesService = fuelsTypesService;
	}

	public SelectItem[] getListFuelsTypes() {
		try {
			listFuelsTypes=null;
			listUtilFuelsTypes = SearchParameters.getAllFuels();
			listFuelsTypes = new SelectItem[listUtilFuelsTypes.size()+1];
			int i = 1;
			listFuelsTypes[0] = new SelectItem("-1", "--SELECCIONAR--");
			for (FuelsTypes fuelsTypes : listUtilFuelsTypes) {
				listFuelsTypes[i] = new SelectItem(fuelsTypes.getFutCodigo(),
						fuelsTypes.getFutNombre());
				i++;

			}

		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
		return listFuelsTypes;
	}
	public void setListFuelsTypes(SelectItem[] listFuelsTypes) {
		this.listFuelsTypes = listFuelsTypes;
	}
}
