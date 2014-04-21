package geniar.siscar.view.parameters;

import javax.faces.model.SelectItem;

public class SelectItemOdometer {

	private static final String KM = "KM";
	private static final String MILLA = "MILLA";
	private SelectItem listOdometer[] = {new SelectItem("-1", "--SELECCIONAR--"), new SelectItem(KM, KM), new SelectItem(MILLA, MILLA) };

	public SelectItem[] getListOdometer() {
		return listOdometer;
	}
	
	public void setListOdometer(SelectItem[] listOdometer) {
		this.listOdometer = listOdometer;
	}

}
