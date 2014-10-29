package geniar.siscar.view.parameters;


import javax.faces.model.SelectItem;
import geniar.siscar.logic.parameters.services.TariffTypesService;

public class SelectItemTariffTypesPage {

	private TariffTypesService tariffTypesService;
	
	private  SelectItem listTariffTypesUtil[];

	public SelectItem[] getListTariffTypesUtil() {
		return listTariffTypesUtil;
	}

	public void setListTariffTypesUtil(SelectItem[] listTariffTypesUtil) {
		this.listTariffTypesUtil = listTariffTypesUtil;
	}

	public TariffTypesService getTariffTypesService() {
		
		listTariffTypesUtil=new SelectItem[]{
				new SelectItem(1,"TARIFA DEPRECIACION"),new SelectItem(2,"TARIFA AUTOSEGURO"),
				new SelectItem(3,"TARIFA MANTENIMIENTO")};
		
		return tariffTypesService;
	}

	public void setTariffTypesService(
			TariffTypesService tariffTypesService) {
		this.tariffTypesService = tariffTypesService;
	}

}
