package geniar.siscar.view.parameters;

import geniar.siscar.model.PoliciesTypes;
import geniar.siscar.util.FacesUtils;

import java.util.List;

import geniar.siscar.logic.policies.services.PoliciesTypeService;

import javax.faces.model.SelectItem;

public class SelectItemPolicyTypePage {

	private SelectItem listPoliciesTypes[];
	private List<PoliciesTypes> listUtilPoliciesTypes;

	private SelectItem listPoliciesTypesParam[];
	private List<PoliciesTypes> listUtilPoliciesTypesParam;
	
	private SelectItem listPoliciesTypesTrans[];
	private List<PoliciesTypes> listUtilPoliciesTypesTrans;
	
	private PoliciesTypeService policiesTypeService;
	
	public SelectItem[] getListPoliciesTypes() {
		
		try {
			listUtilPoliciesTypes = policiesTypeService.listPoliciesTypes();
			listPoliciesTypes = new SelectItem[listUtilPoliciesTypes.size() + 1];
			listPoliciesTypes[0] = new SelectItem(-1, "--SELECCIONAR--");
			int i = 1;

			for (PoliciesTypes policiesTypes: listUtilPoliciesTypes) {
				listPoliciesTypes[i] = new SelectItem(policiesTypes.getPltCodigo(), policiesTypes.getPltNombre());
				i++;

			}
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
		
		return listPoliciesTypes;
	}

	public void setListPoliciesTypes(SelectItem[] listPoliciesTypes) {
		this.listPoliciesTypes = listPoliciesTypes;
	}

	public PoliciesTypeService getPoliciesTypeService() {
		return policiesTypeService;
	}

	public void setPoliciesTypeService(
			PoliciesTypeService policiesTypeService) {
		this.policiesTypeService = policiesTypeService;
	}

	public List<PoliciesTypes> getListUtilPoliciesTypes() {
		return listUtilPoliciesTypes;
	}

	public void setListUtilPoliciesTypes(List<PoliciesTypes> listUtilPoliciesTypes) {
		this.listUtilPoliciesTypes = listUtilPoliciesTypes;
	}

	public SelectItem[] getListPoliciesTypesParam() {
		try {
			listUtilPoliciesTypesParam = policiesTypeService.listPoliciesTypes();
			listPoliciesTypesParam = 
				new SelectItem[listUtilPoliciesTypesParam.size() + 1];
			listPoliciesTypesParam[0] = new SelectItem(-1, "--SELECCIONAR--");
			int i = 1;

			for (PoliciesTypes policiesTypes: listUtilPoliciesTypesParam) {
				listPoliciesTypesParam[i] = new SelectItem(
						policiesTypes.getPltCodigo(), policiesTypes.getPltNombre());
				i++;

			}
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
		return listPoliciesTypesParam;
	}

	public void setListPoliciesTypesParam(SelectItem[] listPoliciesTypesParam) {
		this.listPoliciesTypesParam = listPoliciesTypesParam;
	}

	public List<PoliciesTypes> getListUtilPoliciesTypesParam() {
		return listUtilPoliciesTypesParam;
	}

	public void setListUtilPoliciesTypesParam(
			List<PoliciesTypes> listUtilPoliciesTypesParam) {
		this.listUtilPoliciesTypesParam = listUtilPoliciesTypesParam;
	}

	public SelectItem[] getListPoliciesTypesTrans() {
		try {
			listUtilPoliciesTypesTrans = policiesTypeService.listPoliciesTypesTransac();
			listPoliciesTypesTrans = new SelectItem[listUtilPoliciesTypesTrans.size() + 1];
			listPoliciesTypesTrans[0] = new SelectItem(-1, "--SELECCIONAR--");
			int i = 1;

			for (PoliciesTypes policiesTypes: listUtilPoliciesTypesTrans) {
				listPoliciesTypesTrans[i] = new SelectItem(policiesTypes.getPltCodigo(), policiesTypes.getPltNombre());
				i++;

			}
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
		return listPoliciesTypesTrans;
	}

	public void setListPoliciesTypesTrans(SelectItem[] listPoliciesTypesTrans) {
		this.listPoliciesTypesTrans = listPoliciesTypesTrans;
	}

	public List<PoliciesTypes> getListUtilPoliciesTypesTrans() {
		return listUtilPoliciesTypesTrans;
	}

	public void setListUtilPoliciesTypesTrans(
			List<PoliciesTypes> listUtilPoliciesTypesTrans) {
		this.listUtilPoliciesTypesTrans = listUtilPoliciesTypesTrans;
	}
}
