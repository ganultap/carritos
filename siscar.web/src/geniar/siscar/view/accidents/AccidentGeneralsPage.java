package geniar.siscar.view.accidents;

import geniar.siscar.util.FacesUtils;
import geniar.siscar.util.PopupMessagePage;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

public class AccidentGeneralsPage {

	public void mostrarMensaje(String mensaje, boolean buttonCancel) {
		PopupMessagePage message = (PopupMessagePage) FacesUtils
				.getManagedBean("popupMessagePage");
		if (message != null)
			message.showPopup(mensaje, buttonCancel);
	}


	public void validarTamanhoCaracteres(String campo, String etiqueta,
			Integer minValor, String panel) throws GWorkException {

		if (campo.trim().length() < minValor)
			throw new GWorkException(Util
					.loadErrorMessageValue("CAMPO.TAMANHO") + " "
					+ etiqueta + " "
					+ Util.loadErrorMessageValue("CAMPO.TAMANHO.NADECUADO") + " "
					+ Util.loadErrorMessageValue("PANEL") + panel);

	}

	public void validarTamanhoCaracteresMax(String campo, String etiqueta,
			Integer maxValor, String panel) throws GWorkException {

		if (campo.trim().length() > maxValor)
			throw new GWorkException(Util
					.loadErrorMessageValue("CAMPO.TAMANHO") + " "
					+ etiqueta + " "
					+ Util.loadErrorMessageValue("CAMPO.TAMANHO.NADECUADO") + " "
					+ Util.loadErrorMessageValue("PANEL") + panel);

	}
	
}
