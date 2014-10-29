package geniar.siscar.logic.parameters.services.impl;

import java.util.List;

import geniar.siscar.logic.parameters.services.TapestriesTypesService;
import geniar.siscar.model.TapestriesTypes;
import geniar.siscar.persistence.TapestriesTypesDAO;
import gwork.exception.GWorkException;

public class TapestriesTypesServiceImpl implements TapestriesTypesService {

	public List<TapestriesTypes> listTapestriesTypes() throws GWorkException {
	    List<TapestriesTypes> listTapestriesTypes = new TapestriesTypesDAO().findAll();
	    if(listTapestriesTypes==null)
	    	//TODO : Hacer Excepcion
	    	 throw new GWorkException("");
		return listTapestriesTypes;
	}

}
