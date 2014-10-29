package geniar.siscar.view.parameters;

import geniar.siscar.logic.consultas.SearchParameters;
import geniar.siscar.logic.security.serivice.OptionsService;
import geniar.siscar.model.Options;
import gwork.exception.GWorkException;

import java.util.List;

import javax.faces.model.SelectItem;

/**
 * 
 * @author JAM
 * 
 */
public class SelectItemOptionsPage {

	private SelectItem[] listOptions;
	private OptionsService optionsService;
	private List<Options> listUtilOptions;

	public List<Options> getListUtilRolls() throws GWorkException {
		try {
			listUtilOptions = optionsService.getOpciones();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return listUtilOptions;
	}

	public SelectItem[] getListOptions() {
		try {
			listUtilOptions = null;
			listUtilOptions = SearchParameters.getAllOptionsOrder();
			listOptions = new SelectItem[listUtilOptions.size() + 1];
			listOptions[0] = new SelectItem("-1", "--SELECCIONAR--");
			int i = 1;
			for (Options options : listUtilOptions) {
				listOptions[i] = new SelectItem(options.getOptCodigo(), options
						.getOptNombre());
				i++;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return listOptions;
	}

	public void setListOptions(SelectItem[] listOptions) {
		this.listOptions = listOptions;
	}

	public OptionsService getOptionsService() {
		return optionsService;
	}

	public void setOptionsService(OptionsService optionsService) {
		this.optionsService = optionsService;
	}

	public List<Options> getListUtilOptions() {
		return listUtilOptions;
	}

	public void setListUtilOptions(List<Options> listUtilOptions) {
		this.listUtilOptions = listUtilOptions;
	}
}
