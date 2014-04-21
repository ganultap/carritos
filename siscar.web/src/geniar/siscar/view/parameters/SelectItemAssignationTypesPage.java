package geniar.siscar.view.parameters;

import geniar.siscar.logic.parameters.services.AssignationTypesService;
import geniar.siscar.model.AssignationsTypes;
import geniar.siscar.util.FacesUtils;
import gwork.exception.GWorkException;

import java.util.List;

import javax.faces.model.SelectItem;

public class SelectItemAssignationTypesPage {
	
	private List<AssignationsTypes> listUtilAssignationTypesPage;
	private SelectItem[]            listAssignationTypesPage; 
	private AssignationTypesService assignationTypesService;
	
	public SelectItem[] getListAssignationTypesPage() {
		if(listUtilAssignationTypesPage == null){
		    try {
				listUtilAssignationTypesPage = assignationTypesService.listAssignationTypes();
			    listAssignationTypesPage     = new SelectItem[listUtilAssignationTypesPage.size()+1];
			    listAssignationTypesPage[0] = new SelectItem("-1", "--SELECCIONAR--");
			    int i = 1;
			    for (AssignationsTypes assignationsTypes : listUtilAssignationTypesPage) {
					   listAssignationTypesPage[i] = new SelectItem(assignationsTypes.getAstCodigo(),assignationsTypes.getAstNombre());
					 i++;
				}
		    } catch (GWorkException e) {
				FacesUtils.addErrorMessage(e.getMessage());
			}
		}
		return listAssignationTypesPage;
	}
	public void setListAssignationTypesPage(SelectItem[] listAssignationTypesPage) {
		this.listAssignationTypesPage = listAssignationTypesPage;
	}
	public AssignationTypesService getAssignationTypesService() {
		return assignationTypesService;
	}
	public void setAssignationTypesService(
			AssignationTypesService assignationTypesService) {
		this.assignationTypesService = assignationTypesService;
	}
	

}
