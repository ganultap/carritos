package geniar.siscar.view.parameters;

import javax.faces.model.SelectItem;

/**
 * 
 * @author Geniar
 * 
 */
public class SelectItemDetailTypePage {

	private SelectItem[] itemsDetailType;

	public SelectItem[] getItemsDetailType() {

		itemsDetailType = new SelectItem[6];
		itemsDetailType[0] = new SelectItem("-1", "--SELECCIONAR--");
		itemsDetailType[1] = new SelectItem("1", "ASIGNACIONES");
		itemsDetailType[2] = new SelectItem("2", "CAMBIOS CENTRO DE COSTOS");
		itemsDetailType[3] = new SelectItem("3", "CAMBIOS UBICACIÓN");
		itemsDetailType[4] = new SelectItem("4", "REGISTROS COMBUSTIBLE");
		itemsDetailType[5] = new SelectItem("5", "RETIRADOS");

		return itemsDetailType;
	}

	public void setItemsDetailType(SelectItem[] itemsDetailType) {
		this.itemsDetailType = itemsDetailType;
	}

}
