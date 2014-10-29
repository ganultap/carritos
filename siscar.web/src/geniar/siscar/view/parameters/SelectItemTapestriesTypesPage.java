package geniar.siscar.view.parameters;

import java.util.List;

import javax.faces.model.SelectItem;

import geniar.siscar.logic.consultas.SearchParameters;
import geniar.siscar.logic.parameters.services.TapestriesTypesService;
import geniar.siscar.model.TapestriesTypes;
import gwork.exception.GWorkException;

public class SelectItemTapestriesTypesPage {
	
    private TapestriesTypesService tapestriesTypesService;
    private SelectItem[] listTapestriesTypes;
    private List<TapestriesTypes> listUtilTapestriesTypes;
   
	public TapestriesTypesService getTapestriesTypesService() {
		return tapestriesTypesService;
	}
	public void setTapestriesTypesService(
			TapestriesTypesService tapestriesTypesService) {
		this.tapestriesTypesService = tapestriesTypesService;
	}
	public SelectItem[] getListTapestriesTypes() throws GWorkException{
		try {
			listUtilTapestriesTypes = SearchParameters.getAllTapestriesTypes();
			listTapestriesTypes     = new SelectItem[listUtilTapestriesTypes.size()+1];
			int i=1;
			listTapestriesTypes[0] = new SelectItem("-1", "--SELECCIONAR--");
		    for (TapestriesTypes  tapestriesTypes: listUtilTapestriesTypes) {
				    listTapestriesTypes[i] = new SelectItem(tapestriesTypes.getTptpcCodigo(),tapestriesTypes.getTptpcNombre());
		    	i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return listTapestriesTypes;
	}
	public void setListTapestriesTypes(SelectItem[] listTapestriesTypes) {
		this.listTapestriesTypes = listTapestriesTypes;
	}
    

}
