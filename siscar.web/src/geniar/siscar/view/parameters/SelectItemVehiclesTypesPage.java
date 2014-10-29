package geniar.siscar.view.parameters;

import geniar.siscar.logic.consultas.SearchParameters;
import geniar.siscar.logic.parameters.services.VehiclesTypesService;
import geniar.siscar.model.VehiclesTypes;
import geniar.siscar.util.FacesUtils;
import gwork.exception.GWorkException;

import java.util.List;

import javax.faces.model.SelectItem;

public class SelectItemVehiclesTypesPage {
	
	private SelectItem[]         listVehiclesTypes;
    private List<VehiclesTypes>  listUtilVehiclesTypes;
    private VehiclesTypesService vehiclesTypesService;
    
	public VehiclesTypesService getVehiclesTypesService() {
		return vehiclesTypesService;
	}

	public void setVehiclesTypesService(VehiclesTypesService vehiclesTypesService) {
		this.vehiclesTypesService = vehiclesTypesService;
	}

	public SelectItem[] getListVehiclesTypes() throws GWorkException{
	    try {
			listUtilVehiclesTypes = SearchParameters.getAllVehicleTypes();
			listVehiclesTypes     = new SelectItem[listUtilVehiclesTypes.size()+1];
			int i =1;
			listVehiclesTypes[0] = new SelectItem("-1", "--SELECCIONAR--");
			for (VehiclesTypes vehiclesTypes : listUtilVehiclesTypes) {
				listVehiclesTypes[i] = new SelectItem(vehiclesTypes.getVhtCodigo(),vehiclesTypes.getVhtNombre());
				i++;
			}
			setListVehiclesTypes(listVehiclesTypes);
		} catch (Exception e) {
		    FacesUtils.addErrorMessage(e.getMessage());
		}
		return listVehiclesTypes;
	}

	public void setListVehiclesTypes(SelectItem[] listVehiclesTypes) {
		this.listVehiclesTypes = listVehiclesTypes;
	}

}
