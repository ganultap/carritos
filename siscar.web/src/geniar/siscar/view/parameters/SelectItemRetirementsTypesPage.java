package geniar.siscar.view.parameters;

import geniar.siscar.logic.consultas.SearchParameters;
import geniar.siscar.model.RetirementsTypes;

import java.util.List;

import javax.faces.model.SelectItem;

public class SelectItemRetirementsTypesPage {

	private SelectItem listRetirementTypes[];
	private List<RetirementsTypes> listUTilRetirementTypes;

	public SelectItem[] getListRetirementTypes() {
		try {
			listUTilRetirementTypes = SearchParameters.getAllRetirementsTypes();
			listRetirementTypes = new SelectItem[listUTilRetirementTypes.size() + 1];
			int i = 1;
			listRetirementTypes[0] = new SelectItem("-1", "--SELECCIONAR--");
			for (RetirementsTypes retirementsTypes : listUTilRetirementTypes) {
				listRetirementTypes[i] = new SelectItem(retirementsTypes.getRetCodigo(), retirementsTypes.getRetNombre());
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listRetirementTypes;
	}

	public void setListRetirementTypes(SelectItem[] listRetirementTypes) {
		this.listRetirementTypes = listRetirementTypes;
	}

}
