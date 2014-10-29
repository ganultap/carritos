package geniar.siscar.view.parameters;

import java.util.List;

import javax.faces.model.SelectItem;

import geniar.siscar.logic.parameters.services.ZoneService;
import geniar.siscar.model.Zones;
import gwork.exception.GWorkException;
public class SelectItemZonePage {

	private ZoneService zoneService;
	private List<Zones> listUtilZones;
	private SelectItem listZone[];

	public SelectItem[] getListZone() {
		
		try {
			listZone=null;
			listUtilZones=zoneService.listaZona();
			listZone=new SelectItem[listUtilZones.size()+1];
			listZone[0]=new SelectItem(new Long("-1"),"--SELECCIONAR--");
			int i = 1;
			
			for (Zones zones : listUtilZones) {
				listZone[i]=new SelectItem(zones.getZnsId(),zones.getZnsNombre());
				i++;
			}
			
		} catch (GWorkException e) {
			e.printStackTrace();
		}
		
		return listZone;
	}

	public void setListZone(SelectItem[] listZone) {
		this.listZone = listZone;
	}

	public ZoneService getZoneService() {
		
		return zoneService;
	}

	public void setZoneService(
			ZoneService zoneService) {
		this.zoneService = zoneService;
	}

}
