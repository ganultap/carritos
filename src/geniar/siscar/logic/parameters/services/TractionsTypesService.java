package geniar.siscar.logic.parameters.services;

import java.util.List;

import geniar.siscar.model.TractionsTypes;
import gwork.exception.GWorkException;

public interface TractionsTypesService {

	public TractionsTypes consultarTractionsTypes(Long codeTractionsTypes) throws GWorkException;
	public List<TractionsTypes> listTractionsTypes() throws GWorkException;	
}
