package geniar.siscar.view.parameters;

import geniar.siscar.model.Colors;
import geniar.siscar.persistence.ColorsDAO;
import gwork.exception.GWorkException;

import java.util.List;

import javax.faces.model.SelectItem;

public class SelectItemTableColors {

	private SelectItem[] listColors;
	List<Colors> listUtilColors;


	public SelectItem[] getListColors() throws GWorkException {
		
			listUtilColors = new ColorsDAO().findAll();
			listColors = new SelectItem[listUtilColors.size() + 1];
			int i = 1;
			listColors[0] = new SelectItem("-1", "--SELECCIONAR--");
			for (Colors colors : listUtilColors) {
				listColors[i] = new SelectItem(colors.getClCodigo(), colors.getClNombre());
				i++;
			}
		return listColors;
	}

	public void setListColors(SelectItem[] listColors) {
		this.listColors = listColors;
	}

}
