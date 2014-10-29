package geniar.siscar.view.security;

import geniar.siscar.model.Modules;

public class VOOptionsPage {

	private Long optCodigo;
	private Modules modules;
	private String optNombre;
	private boolean checkState;
	private boolean checkStateBD;

	public boolean isCheckState() {
		return checkState;
	}

	public void setCheckState(boolean checkState) {
		this.checkState = checkState;
	}

	public Long getOptCodigo() {
		return optCodigo;
	}

	public void setOptCodigo(Long optCodigo) {
		this.optCodigo = optCodigo;
	}

	public Modules getModules() {
		return modules;
	}

	public void setModules(Modules modules) {
		this.modules = modules;
	}

	public String getOptNombre() {
		return optNombre;
	}

	public void setOptNombre(String optNombre) {
		this.optNombre = optNombre;
	}

	public boolean isCheckStateBD() {
		return checkStateBD;
	}

	public void setCheckStateBD(boolean checkStateBD) {
		this.checkStateBD = checkStateBD;
	}

}
