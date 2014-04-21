package geniar.siscar.logic.parameters.services;

import java.util.List;

import geniar.siscar.model.FuelsTypes;
import gwork.exception.GWorkException;

public interface FuelsTypesService {
  
	public FuelsTypes       consultarFuelsTypes(Long codeFuelsTypes)throws GWorkException;
	public List<FuelsTypes> listFuelsTypes()throws GWorkException; 
}
