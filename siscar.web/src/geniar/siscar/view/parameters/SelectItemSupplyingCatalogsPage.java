package geniar.siscar.view.parameters;

import geniar.siscar.logic.consultas.SearchParameters;
import geniar.siscar.logic.consultas.SearchVehicles;
import geniar.siscar.logic.vehicle.services.SupplyingService;
import geniar.siscar.model.Models;
import geniar.siscar.model.SupplyingCatalogs;
import geniar.siscar.persistence.ModelsDAO;
import gwork.exception.GWorkException;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

public class SelectItemSupplyingCatalogsPage {

	private SelectItem[] listSupplyingCatalogs;
	private SelectItem[] listModels;
	private SupplyingService supplyingService;
	private List<SupplyingCatalogs> listUtilSupplyingCatalogs;
	private List<Models> listUtilModels;
	private List<SupplyingCatalogs> setSupplyingCatalogs;

	
	public SelectItem[] getListSupplyingCatalogs() throws GWorkException {
						
		if (getSetSupplyingCatalogs() == null) 
			setSupplyingCatalogs = new ArrayList<SupplyingCatalogs>(1);
		
				listSupplyingCatalogs = new SelectItem[getSetSupplyingCatalogs().size() + 1];
				int i = 1;
				listSupplyingCatalogs[0] = new SelectItem("-1", "--SELECCIONAR--");
				for (SupplyingCatalogs supplyingCatalogs : getSetSupplyingCatalogs()) {
					listSupplyingCatalogs[i] = new SelectItem(supplyingCatalogs.getSupCodigo(), supplyingCatalogs
							.getSupNumCatalogo());
					i++;
				}			
		return listSupplyingCatalogs;
	}

	public void cargarCatalogoModelo(ValueChangeEvent event)throws Exception {
		if (event.getNewValue() != null) {
			String valor = event.getNewValue().toString();
			if (valor != null && !valor.equalsIgnoreCase("-1")) {
				Models models = new ModelsDAO().findById(new Long(valor));
				if (models != null) {
					setSupplyingCatalogs=SearchVehicles.consultarCatalogoPorIdModelo(new Long(valor));
					setSetSupplyingCatalogs(setSupplyingCatalogs);
				}
			}
			else
				setSetSupplyingCatalogs(null);	
		}
	}

	public SelectItem[] getListModels() throws GWorkException {
		listUtilModels = SearchParameters.getAllModels();		
			listModels = new SelectItem[listUtilModels.size() + 1];
			int i = 1;
			listModels[0] = new SelectItem("-1", "--SELECCIONAR--");
			for (Models models : listUtilModels) {
				listModels[i] = new SelectItem(models.getMdlId(), models.getMdlNombre());
				i++;
			}
		return listModels;		
	}

	public void setListSupplyingCatalogs(SelectItem[] listSupplyingCatalogs) {
		this.listSupplyingCatalogs = listSupplyingCatalogs;
	}

	public SupplyingService getSupplyingService() {
		return supplyingService;
	}

	public void setSupplyingService(SupplyingService supplyingService) {
		this.supplyingService = supplyingService;
	}

	public List<SupplyingCatalogs> getListUtilSupplyingCatalogs() {
		return listUtilSupplyingCatalogs;
	}

	public void setListUtilSupplyingCatalogs(List<SupplyingCatalogs> listUtilSupplyingCatalogs) {
		this.listUtilSupplyingCatalogs = listUtilSupplyingCatalogs;
	}

	public List<SupplyingCatalogs> getSetSupplyingCatalogs() {
		return setSupplyingCatalogs;
	}

	public void setSetSupplyingCatalogs(List<SupplyingCatalogs> setSupplyingCatalogs) {
		this.setSupplyingCatalogs = setSupplyingCatalogs;
	}

	public void setListModels(SelectItem[] listModels) {
		this.listModels = listModels;
	}

	public List<Models> getListUtilModels() {
		return listUtilModels;
	}

	public void setListUtilModels(List<Models> listUtilModels) {
		this.listUtilModels = listUtilModels;
	}
}
