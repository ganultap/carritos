package geniar.siscar.view.parameters;

import geniar.siscar.logic.fuels.services.FuelTankService;
import geniar.siscar.logic.fuels.services.SearchParametersFuelService;
import geniar.siscar.model.FuelTanks;
import geniar.siscar.model.RevisionHour;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

public class SelectItemFuelTanksPage {

	private FuelTankService fuelTankService;
	private SearchParametersFuelService searchParametersFuelService;

	private SelectItem[] items;
	private SelectItem[] horasRevision;

	public SelectItem[] getHorasRevision() {

		if (horasRevision == null) {
			try {
				List<RevisionHour> listaHoraVision = new ArrayList<RevisionHour>();

				listaHoraVision = searchParametersFuelService
						.listaHoraRevision();
				horasRevision = new SelectItem[listaHoraVision.size() + 1];
				horasRevision[0] = new SelectItem(new Long("-1"), Util
						.loadMessageValue("SELECCIONAR"));
				int i = 1;

				for (RevisionHour revisionHour : listaHoraVision) {
					horasRevision[i] = new SelectItem(revisionHour
							.getRhoCodigo(), revisionHour.getRhoHora());
					i++;
				}
			} catch (GWorkException e) {
				System.out.println(e.getMessage());
			}

		}
		return horasRevision;
	}

	public void setHorasRevision(SelectItem[] horasRevision) {
		this.horasRevision = horasRevision;
	}

	public FuelTankService getFuelTankService() {
		return fuelTankService;
	}

	public void setFuelTankService(FuelTankService fuelTankService) {
		this.fuelTankService = fuelTankService;
	}

	public SelectItem[] getItems() {

		try {
			List<FuelTanks> list = fuelTankService.consultarTodosFuelTanks();

			items = new SelectItem[list.size() + 1];
			items[0] = new SelectItem("-1", "--SELECCIONAR--");
			int i = 1;
			for (FuelTanks fuelsTanks : list) {
				items[i] = new SelectItem(fuelsTanks.getFtaCodigo(), fuelsTanks
						.getFtaNombre());
				i++;
			}

		} catch (GWorkException e) {
			System.out.println(e.getMessage());
		}

		return items;
	}

	public void setItems(SelectItem[] items) {
		this.items = items;
	}

	public SearchParametersFuelService getSearchParametersFuelService() {
		return searchParametersFuelService;
	}

	public void setSearchParametersFuelService(
			SearchParametersFuelService searchParametersFuelService) {
		this.searchParametersFuelService = searchParametersFuelService;
	}

}
