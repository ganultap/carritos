package geniar.siscar.logic.parameters.services;

import geniar.siscar.model.Countries;
import gwork.exception.GWorkException;

import java.util.List;
/**
 * 
 * @author Rodrigo Lopez
 *
 */
public interface CountriesService {

	public List<Countries> listCountries() throws GWorkException;
}
