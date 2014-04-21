package geniar.siscar.view.parameters;

import java.util.List;

import javax.faces.model.SelectItem;
import geniar.siscar.logic.parameters.services.ParametrosService;
import geniar.siscar.model.ParParametros;
import gwork.exception.GWorkException;

public class SelectItemParametersPage {

	private SelectItem[] listParameters;
	private ParametrosService parametrosService;

	public SelectItem[] getListParameters() {

		try {
			listParameters = null;
			List<ParParametros> losParametros;
			losParametros = parametrosService.getParParametros();

			listParameters = new SelectItem[losParametros.size()];
			int i = 0;

			for (ParParametros parParametros : losParametros) {
				listParameters[i] = new SelectItem(parParametros.getIdparametro(), parParametros.getNombre());
				i++;
			}

		} catch (GWorkException e) {
			System.out.println(e.getMessage());
		}

		return listParameters;
	}

	public void setListParameters(SelectItem[] listParameters) {
		this.listParameters = listParameters;
	}

	public ParametrosService getParametrosService() {
		return parametrosService;
	}

	public void setParametrosService(ParametrosService parametrosService) {
		this.parametrosService = parametrosService;
	}

}
