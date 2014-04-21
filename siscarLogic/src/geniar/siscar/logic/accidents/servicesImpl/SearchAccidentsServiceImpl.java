package geniar.siscar.logic.accidents.servicesImpl;

import geniar.siscar.logic.accidents.services.SearchAccidentsService;
import geniar.siscar.model.AccidentsResults;
import geniar.siscar.model.AccidentsStates;
import geniar.siscar.model.Cities;
import geniar.siscar.model.Responsibility;
import geniar.siscar.model.Severity;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

import java.util.List;

public class SearchAccidentsServiceImpl implements SearchAccidentsService {

	public List<Responsibility> listResponsability() throws GWorkException {

		SearchParametersAccidents SearchParametersAccidents = new SearchParametersAccidents();

		if (SearchParametersAccidents.responsabilityOrder() == null
				|| SearchParametersAccidents.responsabilityOrder().isEmpty()
				|| SearchParametersAccidents.responsabilityOrder().size() == 0)
			throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));

		return SearchParametersAccidents.responsabilityOrder();
	}

	public List<Severity> listSeverity() throws GWorkException {

		SearchParametersAccidents searchParametersAccidents = new SearchParametersAccidents();

		if (searchParametersAccidents.severityOrder() == null
				|| searchParametersAccidents.severityOrder().isEmpty()
				|| searchParametersAccidents.severityOrder().size() == 0)
			throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));

		return searchParametersAccidents.severityOrder();
	}

	public List<Cities> listCities() throws GWorkException {

		SearchParametersAccidents searchParametersAccidents = new SearchParametersAccidents();
		if (searchParametersAccidents.ciudadesOrder() == null
				|| searchParametersAccidents.ciudadesOrder().isEmpty()
				|| searchParametersAccidents.ciudadesOrder().size() == 0)
			throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));

		return searchParametersAccidents.ciudadesOrder();
	}

	public List<AccidentsResults> listResults() throws GWorkException {

		SearchParametersAccidents searchParametersAccidents = new SearchParametersAccidents();

		if (searchParametersAccidents.resultsOrder() == null
				|| searchParametersAccidents.resultsOrder().isEmpty()
				|| searchParametersAccidents.resultsOrder().size() == 0)
			throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));

		return searchParametersAccidents.resultsOrder();
	}

	public List<AccidentsStates> listAccidentsStates() throws GWorkException {

		SearchParametersAccidents searchParametersAccidents = new SearchParametersAccidents();

		if (searchParametersAccidents.accidentsStatesOrder() == null
				|| searchParametersAccidents.accidentsStatesOrder().isEmpty()
				|| searchParametersAccidents.accidentsStatesOrder().size() == 0)
			throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));

		return searchParametersAccidents.accidentsStatesOrder();
	}

}
