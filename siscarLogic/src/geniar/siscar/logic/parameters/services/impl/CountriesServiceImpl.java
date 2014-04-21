package geniar.siscar.logic.parameters.services.impl;

import java.util.List;

import geniar.siscar.logic.parameters.services.CountriesService;
import geniar.siscar.model.Countries;
import geniar.siscar.persistence.CountriesDAO;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

public class CountriesServiceImpl implements CountriesService{

	public List<Countries> listCountries() throws GWorkException {
        List<Countries> listCountries = new CountriesDAO().findAll();
        if(listCountries==null)
        	//TODO Realizar Excepcion
        	throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));
		return listCountries;
	}

}
