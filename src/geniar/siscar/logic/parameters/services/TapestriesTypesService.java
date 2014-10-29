package geniar.siscar.logic.parameters.services;

import geniar.siscar.model.TapestriesTypes;
import gwork.exception.GWorkException;

import java.util.List;

public interface TapestriesTypesService {
	
	public List<TapestriesTypes> listTapestriesTypes() throws GWorkException;
	

}
