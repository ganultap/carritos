package geniar.siscar.view.parameters;

import java.util.List;

import geniar.siscar.logic.vehicle.services.StatesVehicleService;
import geniar.siscar.model.VehiclesStates;

import javax.faces.model.SelectItem;

public class SelectItemVehiclesStatesPage {
	
	private SelectItem[]         listVehiclesStates;
	private StatesVehicleService statesVehicleService;
	private List<VehiclesStates> listUtil;



	public SelectItem[] getListVehiclesStates() {
		if(listUtil==null){
			listUtil = statesVehicleService.listadoEstadoVehiculos();
			listVehiclesStates = new SelectItem[listUtil.size()+1];
		    int i =1;
		    listVehiclesStates[0] = new SelectItem("-1","--SELECCIONAR--");
			for (VehiclesStates vehiclesStates : listUtil) {
				 listVehiclesStates[i]=new SelectItem(vehiclesStates.getVhsCodigo(),vehiclesStates.getVhsNombre());  
				i++;
			}
		}
		return listVehiclesStates;
	}

	public void setListVehiclesStates(SelectItem[] listVehiclesStates) {
		this.listVehiclesStates = listVehiclesStates;
	}

	public StatesVehicleService getStatesVehicleService() {
		 
		return statesVehicleService;
	}

	public void setStatesVehicleService(StatesVehicleService statesVehicleService) {
		this.statesVehicleService = statesVehicleService;
	}

}
