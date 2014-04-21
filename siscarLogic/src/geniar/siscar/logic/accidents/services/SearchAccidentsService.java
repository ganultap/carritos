package geniar.siscar.logic.accidents.services;

import geniar.siscar.model.AccidentsResults;
import geniar.siscar.model.AccidentsStates;
import geniar.siscar.model.Cities;
import geniar.siscar.model.Responsibility;
import geniar.siscar.model.Severity;
import gwork.exception.GWorkException;

import java.util.List;

public interface SearchAccidentsService {

	public List<Responsibility> listResponsability() throws GWorkException;

	public List<Severity> listSeverity() throws GWorkException;

	public List<Cities> listCities() throws GWorkException;

	public List<AccidentsResults> listResults() throws GWorkException;
	
	public List<AccidentsStates> listAccidentsStates() throws GWorkException;

}
