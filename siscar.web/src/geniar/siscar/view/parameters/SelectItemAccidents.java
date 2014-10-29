package geniar.siscar.view.parameters;

import geniar.siscar.util.Util;
import gwork.exception.GWorkException;
import geniar.siscar.logic.accidents.services.SearchAccidentsService;
import geniar.siscar.model.AccidentsResults;
import geniar.siscar.model.AccidentsStates;
import geniar.siscar.model.Cities;
import geniar.siscar.model.Responsibility;
import geniar.siscar.model.Severity;
import java.util.ArrayList;
import java.util.List;
import javax.faces.model.SelectItem;

public class SelectItemAccidents {

	private SelectItem listTransito[];
	private ArrayList<String> intervinoTransito;
	private ArrayList<String> reclamos;
	private ArrayList<String> uso;
	private ArrayList<String> cargado;
	private ArrayList<String> tipoVehiculo;
	private SelectItem listCargado[];
	private SelectItem listUso[];
	private SelectItem listReclamos[];
	private SelectItem listResposability[];
	private SelectItem listSeverity[];
	private SelectItem listCities[];
	private SelectItem listResults[];
	private SelectItem listAccidentsStates[];
	private SelectItem listTipoVehiculo[];
	private SearchAccidentsService searchAccidentsService;
	private SelectItem hours[];
	private SelectItem minutes[];

	public SelectItem[] getMinutes() {

		if (minutes == null) {
			minutes = new SelectItem[61];
			minutes[0] = new SelectItem("--");

			for (Integer i = 1; i < 61; i++) {
				Integer num = i - 1;
				if (num <= 9)
					minutes[i] = new SelectItem("0" + num.toString());
				else
					minutes[i] = new SelectItem(num.toString());
			}
		}
		return minutes;
	}

	public void setMinutes(SelectItem[] minutes) {
		this.minutes = minutes;
	}

	public SelectItem[] getListResposability() {
		try {

			listResposability = null;
			List<Responsibility> listResponsabilityUtil = searchAccidentsService
					.listResponsability();
			listResposability = new SelectItem[listResponsabilityUtil.size() + 1];
			listResposability[0] = new SelectItem(new Long("-1"), Util
					.loadMessageValue("SELECCIONAR"));
			int i = 1;

			for (Responsibility responsibility : listResponsabilityUtil) {
				listResposability[i] = new SelectItem(responsibility
						.getResCodigo(), responsibility.getResNombre());
				i++;
			}

		} catch (GWorkException e) {
			System.out.println(e.getMessage());
		}

		return listResposability;
	}

	public void setListResposability(SelectItem[] listResposability) {
		this.listResposability = listResposability;
	}

	public SelectItem[] getListTransito() {
		try {
			intervinoTransito = new ArrayList<String>();
			intervinoTransito.add(Util.loadMessageValue("SELECCIONAR"));
			intervinoTransito.add(Util.loadMessageValue("NO"));
			intervinoTransito.add(Util.loadMessageValue("SI"));

			listTransito = new SelectItem[intervinoTransito.size()];

			for (int j = 0; j < intervinoTransito.size(); j++) {

				listTransito[j] = new SelectItem(intervinoTransito.get(j));
			}

		} catch (GWorkException e) {
			System.out.println(e.getMessage());
		}
		return listTransito;
	}

	public void setListTransito(SelectItem[] listTransito) {
		this.listTransito = listTransito;
	}

	public SearchAccidentsService getSearchAccidentsService() {
		return searchAccidentsService;
	}

	public void setSearchAccidentsService(
			SearchAccidentsService searchAccidentsService) {
		this.searchAccidentsService = searchAccidentsService;
	}

	public SelectItem[] getListSeverity() {
		try {

			listSeverity = null;
			List<Severity> listSeverityUtil = searchAccidentsService
					.listSeverity();
			listSeverity = new SelectItem[listSeverityUtil.size() + 1];
			listSeverity[0] = new SelectItem(new Long("-1"), Util
					.loadMessageValue("SELECCIONAR"));
			int i = 1;

			for (Severity severity : listSeverityUtil) {
				listSeverity[i] = new SelectItem(severity.getSevCodigo(),
						severity.getSevNombre());
				i++;
			}

		} catch (GWorkException e) {
			System.out.println(e.getMessage());
		}
		return listSeverity;
	}

	public void setListSeverity(SelectItem[] listSeverity) {
		this.listSeverity = listSeverity;
	}

	public SelectItem[] getListReclamos() {

		try {
			reclamos = new ArrayList<String>();
			reclamos.add(Util.loadMessageValue("SELECCIONAR"));
			reclamos.add(Util.loadMessageValue("ASEGURADORA"));
			reclamos.add(Util.loadMessageValue("AUTOSEGURO"));

			listReclamos = new SelectItem[reclamos.size()];

			for (int i = 0; i < reclamos.size(); i++) {
				listReclamos[i] = new SelectItem(reclamos.get(i));
			}
		} catch (GWorkException e) {
			System.out.println(e.getMessage());
		}
		return listReclamos;
	}

	public void setListReclamos(SelectItem[] listReclamos) {
		this.listReclamos = listReclamos;
	}

	public SelectItem[] getListUso() {

		try {
			uso = new ArrayList<String>();
			uso.add(Util.loadMessageValue("SELECCIONAR"));
			uso.add(Util.loadMessageValue("OFICIAL"));
			uso.add(Util.loadMessageValue("PERSONAL"));

			listUso = new SelectItem[uso.size()];

			for (int i = 0; i < uso.size(); i++) {
				listUso[i] = new SelectItem(uso.get(i));
			}
		} catch (GWorkException e) {
			System.out.println(e.getMessage());
		}
		return listUso;
	}

	public void setListUso(SelectItem[] listUso) {
		this.listUso = listUso;
	}

	public SelectItem[] getListCities() {

		try {
			listCities = null;
			List<Cities> listCitiesUtil = searchAccidentsService.listCities();
			listCities = new SelectItem[listCitiesUtil.size() + 1];
			listCities[0] = new SelectItem(new Long("-1"), Util
					.loadMessageValue("SELECCIONAR"));
			int i = 1;

			for (Cities cities : listCitiesUtil) {
				listCities[i] = new SelectItem(cities.getCtsId(), cities
						.getCtsNombre());
				i++;
			}
		} catch (GWorkException e) {
			System.out.println(e.getMessage());
		}

		return listCities;
	}

	public void setListCities(SelectItem[] listCities) {
		this.listCities = listCities;
	}

	public SelectItem[] getListResults() {

		try {
			listResults = null;
			List<AccidentsResults> listResultsUtil = searchAccidentsService
					.listResults();
			listResults = new SelectItem[listResultsUtil.size() + 1];
			listResults[0] = new SelectItem(new Long("-1"), Util
					.loadMessageValue("SELECCIONAR"));
			int i = 1;

			for (AccidentsResults accidentsResults : listResultsUtil) {
				listResults[i] = new SelectItem(
						accidentsResults.getAclCodigo(), accidentsResults
								.getAclNombre());
				i++;
			}
		} catch (GWorkException e) {
			System.out.println(e.getMessage());
		}
		return listResults;
	}

	public void setListResults(SelectItem[] listResults) {
		this.listResults = listResults;
	}

	public SelectItem[] getListCargado() {
		try {
			cargado = new ArrayList<String>();
			cargado.add(Util.loadMessageValue("SELECCIONAR"));
			cargado.add(Util.loadMessageValue("CARNE"));
			cargado.add(Util.loadMessageValue("CENTROCOSTO"));
			cargado.add(Util.loadMessageValue("TERCERO"));

			listCargado = new SelectItem[cargado.size()];

			for (int i = 0; i < cargado.size(); i++) {
				listCargado[i] = new SelectItem(cargado.get(i));
			}
		} catch (GWorkException e) {
			System.out.println(e.getMessage());
		}
		return listCargado;
	}

	public void setListCargado(SelectItem[] listCargado) {
		this.listCargado = listCargado;
	}

	public SelectItem[] getListAccidentsStates() {

		try {
			listAccidentsStates = null;
			List<AccidentsStates> listAccidentsStatesUtil = searchAccidentsService
					.listAccidentsStates();
			listAccidentsStates = new SelectItem[listAccidentsStatesUtil.size() + 1];
			listAccidentsStates[0] = new SelectItem(new Long("-1"), Util
					.loadMessageValue("SELECCIONAR"));
			int i = 1;

			for (AccidentsStates accidentsStates : listAccidentsStatesUtil) {
				listAccidentsStates[i] = new SelectItem(accidentsStates
						.getAcsCode(), accidentsStates.getAcsNombre());
				i++;
			}

		} catch (GWorkException e) {
			System.out.println(e.getMessage());
		}

		return listAccidentsStates;
	}

	public void setListAccidentsStates(SelectItem[] listAccidentsStates) {
		this.listAccidentsStates = listAccidentsStates;
	}

	public SelectItem[] getListTipoVehiculo() {
		try {
			tipoVehiculo = new ArrayList<String>();
			tipoVehiculo.add(Util.loadMessageValue("SELECCIONAR"));
			tipoVehiculo.add(Util.loadMessageValue("CIAT"));
			tipoVehiculo.add(Util.loadMessageValue("TERCERO"));
			listTipoVehiculo = new SelectItem[tipoVehiculo.size()];

			for (int i = 0; i < tipoVehiculo.size(); i++) {
				listTipoVehiculo[i] = new SelectItem(tipoVehiculo.get(i));

			}

		} catch (GWorkException e) {
			System.out.println(e.getMessage());
		}
		return listTipoVehiculo;
	}

	public void setListTipoVehiculo(SelectItem[] listTipoVehiculo) {
		this.listTipoVehiculo = listTipoVehiculo;
	}

	public SelectItem[] getHours() {
		hours = new SelectItem[25];
		hours[0] = new SelectItem("--");
		for (Integer i = 1; i < 25; i++) {

			Integer num = i - 1;
			if (num.intValue() < 10)
				hours[i] = new SelectItem("0" + num.toString());
			else
				hours[i] = new SelectItem(num.toString());

		}
		return hours;
	}

	public void setHours(SelectItem[] hours) {
		this.hours = hours;
	}

}