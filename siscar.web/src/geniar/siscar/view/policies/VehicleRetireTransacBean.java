package geniar.siscar.view.policies;

import geniar.siscar.util.FacesUtils;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import com.icesoft.faces.component.ext.HtmlInputText;

public class VehicleRetireTransacBean {

	private String vhcPlacaDiplomatica;
	
	private HtmlInputText txtPlaca;

	public void action_consultar(ActionEvent event) {
		try {
			if (vhcPlacaDiplomatica != null && vhcPlacaDiplomatica.trim().length() == 0)
				throw new GWorkException(Util.loadErrorMessageValue("ERROR.PLACA"));

			if (!Util.validarPlaca(vhcPlacaDiplomatica)) 
				throw new GWorkException(Util.loadErrorMessageValue("ERROR.PLACA"));
			
			Util.validarLimite(vhcPlacaDiplomatica, 20, 2, "ERROR.LIMPLACA");
			
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void listener_placa(ValueChangeEvent event) {
		if (event.getNewValue() != null) {
			txtPlaca.setValue(""+(
				event.getNewValue().toString().toUpperCase()));
		}
	}
	
	public void action_limpiarForma(ActionEvent event) {
		limpiar();
	}
	
	private void limpiar() {
		txtPlaca.setValue("");
	}

	public String getVhcPlacaDiplomatica() {
		return vhcPlacaDiplomatica;
	}

	public void setVhcPlacaDiplomatica(String vhcPlacaDiplomatica) {
		this.vhcPlacaDiplomatica = vhcPlacaDiplomatica;
	}

	public HtmlInputText getTxtPlaca() {
		return txtPlaca;
	}

	public void setTxtPlaca(HtmlInputText txtPlaca) {
		this.txtPlaca = txtPlaca;
	}
}
