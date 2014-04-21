package geniar.siscar.view.parameters;

import geniar.siscar.logic.consultas.SearchParameters;
import geniar.siscar.model.LegateesTypes;
import geniar.siscar.util.FacesUtils;

import java.util.List;

import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.model.SelectItem;

public class SelectItemLegateesTypePage {

	private Long idTipoAsignatario;
	private String lgtNombre;
	private SelectItem listLegateeTypes[];
	private HtmlSelectOneMenu selectAsignatario;
	private List<LegateesTypes> listUtilLegateesTypes;
	private geniar.siscar.logic.parameters.services.LegateesTypesService legateesTypeService;
	
	public SelectItem[] getListLegateesTypes() {

		try {
			List<LegateesTypes> listUtilLegateesTypes = SearchParameters.getAllLegateesTypes();
			listLegateeTypes = new SelectItem[listUtilLegateesTypes.size() + 1];
			listLegateeTypes[0] = new SelectItem("-1", "--SELECCIONAR--");
			int i = 1;

			for (LegateesTypes legateeType : listUtilLegateesTypes) {
				listLegateeTypes[i] = new SelectItem(legateeType.getLgtCodigo(), legateeType.getLgtNombre());
				i++;

			}
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return listLegateeTypes;
	}	

	public void setListLegateeTypes(SelectItem[] listLegateeTypes) {
		this.listLegateeTypes = listLegateeTypes;
	}

	public List<LegateesTypes> getListUtilLegateesTypes() {
		return listUtilLegateesTypes;
	}

	public void setListUtilLegateesTypes(List<LegateesTypes> listUtilLegateesTypes) {
		this.listUtilLegateesTypes = listUtilLegateesTypes;
	}

	public Long getIdTipoAsignatario() {
		return idTipoAsignatario;
	}

	public void setIdTipoAsignatario(Long idTipoAsignatario) {
		this.idTipoAsignatario = idTipoAsignatario;
	}

	public String getLgtNombre() {
		return lgtNombre;
	}

	public void setLgtNombre(String lgtNombre) {
		this.lgtNombre = lgtNombre;
	}

	public HtmlSelectOneMenu getSelectAsignatario() {
		return selectAsignatario;
	}

	public void setSelectAsignatario(HtmlSelectOneMenu selectAsignatario) {
		this.selectAsignatario = selectAsignatario;
	}

	public geniar.siscar.logic.parameters.services.LegateesTypesService getLegateesTypeService() {
		return legateesTypeService;
	}

	public void setLegateesTypeService(
			geniar.siscar.logic.parameters.services.LegateesTypesService legateesTypeService) {
		this.legateesTypeService = legateesTypeService;
	}
}
