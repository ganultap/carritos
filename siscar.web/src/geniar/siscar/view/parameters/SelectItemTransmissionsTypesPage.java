package geniar.siscar.view.parameters;

import java.util.List;

import javax.faces.model.SelectItem;

import geniar.siscar.logic.parameters.services.TransmissionsTypesService;
import geniar.siscar.model.TransmissionsTypes;
import geniar.siscar.util.FacesUtils;
import gwork.exception.GWorkException;

public class SelectItemTransmissionsTypesPage {

	private TransmissionsTypesService transmissionsTypesService;
	private SelectItem[] listTransmissionsTypes;
	private List<TransmissionsTypes> listUtilTransmissionsTypes;
	
	
	public TransmissionsTypesService getTransmissionsTypesService() {
		return transmissionsTypesService;
	}
	public void setTransmissionsTypesService(
			TransmissionsTypesService transmissionsTypesService) {
		this.transmissionsTypesService = transmissionsTypesService;
	}
	public SelectItem[] getListTransmissionsTypes() {
		 try {
			listUtilTransmissionsTypes = transmissionsTypesService.listTransmissionsTypes();
		    listTransmissionsTypes     = new SelectItem[listUtilTransmissionsTypes.size()+1];
		    int i =1;
		    listTransmissionsTypes[0] = new SelectItem("-1", "--SELECCIONAR--");
		    for (TransmissionsTypes transmissionsTypes : listUtilTransmissionsTypes) {
		    	listTransmissionsTypes[i] = new SelectItem(transmissionsTypes.getTntCodigo(),transmissionsTypes.getTntNombre());
		    	i++;
			}
		 
		 } catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
		
		return listTransmissionsTypes;
	}
	public void setListTransmissionsTypes(SelectItem[] listTransmissionsTypes) {
		this.listTransmissionsTypes = listTransmissionsTypes;
	}
	
}
