package geniar.siscar.view.parameters;

import java.util.List;

import geniar.siscar.logic.consultas.SearchParameters;
import geniar.siscar.logic.parameters.services.TractionsTypesService;
import geniar.siscar.model.TractionsTypes;
import geniar.siscar.util.FacesUtils;
import gwork.exception.GWorkException;

import javax.faces.model.SelectItem;

public class SelectItemTractionsTypesPage {

	private SelectItem[]  listTractionsTypes;
	private TractionsTypesService tractionsTypesService;
    private List<TractionsTypes> listUtilTractionsTypes;
	
	public SelectItem[] getListTractionsTypes()throws GWorkException {
		if(listUtilTractionsTypes==null){
			try {
				listUtilTractionsTypes = SearchParameters.getAllTracctionTypes();
				listTractionsTypes     = new SelectItem[listUtilTractionsTypes.size()+1];
				int i =1;
				listTractionsTypes[0] = new SelectItem("-1", "--SELECCIONAR--");
				for (TractionsTypes tractionsTypes: listUtilTractionsTypes) {
					listTractionsTypes[i] = new SelectItem(tractionsTypes.getTctCodigo(),tractionsTypes.getTctNombre());
					i++;
				}
			} catch (Exception e) {
				 FacesUtils.addErrorMessage(e.getMessage());
			}			
		}
		return listTractionsTypes;
    }

	public void setListTractionsTypes(SelectItem[] listTractionsTypes) {
		this.listTractionsTypes = listTractionsTypes;
	}

	public TractionsTypesService getTractionsTypesService() {
		return tractionsTypesService;
	}

	public void setTractionsTypesService(TractionsTypesService tractionsTypesService) {
		this.tractionsTypesService = tractionsTypesService;
	}
	
}
