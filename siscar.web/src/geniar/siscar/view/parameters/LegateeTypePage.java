package geniar.siscar.view.parameters;

import geniar.siscar.logic.parameters.services.LegateesTypesService;
import geniar.siscar.model.PoliciesAplications;
import geniar.siscar.model.Requests;
import geniar.siscar.model.TariffsLegateeTypes;

import java.util.Set;

public class LegateeTypePage {
	
	private Long lgtCodigo;
	private String lgtNombre;
	private Set<Requests> requestses;
	private Set<PoliciesAplications> policiesAplicationses;
	private Set<TariffsLegateeTypes> tariffsLegateeTypeses;
	private LegateesTypesService legateesTypeService;
	
	
	
	public Long getLgtCodigo() {
		return lgtCodigo;
	}
	public void setLgtCodigo(Long lgtCodigo) {
		this.lgtCodigo = lgtCodigo;
	}
	public String getLgtNombre() {
		return lgtNombre;
	}
	public void setLgtNombre(String lgtNombre) {
		this.lgtNombre = lgtNombre;
	}
	public Set<Requests> getRequestses() {
		return requestses;
	}
	public void setRequestses(Set<Requests> requestses) {
		this.requestses = requestses;
	}
	public Set<PoliciesAplications> getPoliciesAplicationses() {
		return policiesAplicationses;
	}
	public void setPoliciesAplicationses(
			Set<PoliciesAplications> policiesAplicationses) {
		this.policiesAplicationses = policiesAplicationses;
	}
	public Set<TariffsLegateeTypes> getTariffsLegateeTypeses() {
		return tariffsLegateeTypeses;
	}
	public void setTariffsLegateeTypeses(
			Set<TariffsLegateeTypes> tariffsLegateeTypeses) {
		this.tariffsLegateeTypeses = tariffsLegateeTypeses;
	}
	public LegateesTypesService getLegateesTypeService() {
		return legateesTypeService;
	}
	public void setLegateesTypeService(
			LegateesTypesService legateesTypeService) {
		this.legateesTypeService = legateesTypeService;
	}
}
