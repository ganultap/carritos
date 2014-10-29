package geniar.siscar.logic.parameters.services.impl;

import java.util.List;

import geniar.siscar.logic.parameters.services.FuelsTypesService;
import geniar.siscar.model.FuelsTypes;
import geniar.siscar.persistence.FuelsTypesDAO;
import geniar.siscar.persistence.IFuelsTypesDAO;
import gwork.exception.GWorkException;

public class FuelsTypesServiceImpl implements FuelsTypesService{

	public FuelsTypes consultarFuelsTypes(Long codeFuelsTypes) throws GWorkException{
		IFuelsTypesDAO fuelsTypesDAO = new FuelsTypesDAO();
		FuelsTypes fuelsTypes        = fuelsTypesDAO.findById(codeFuelsTypes);
		if(fuelsTypes==null){
			//TODO :Hacer Excepcion
			throw new GWorkException("");
		}
		return fuelsTypes;
	}

	public List<FuelsTypes> listFuelsTypes() throws GWorkException {
		List<FuelsTypes> listFuels = new FuelsTypesDAO().findAll();
		if(listFuels==null)
			//TODO Hacer la excepcion
			throw new GWorkException("");
		return listFuels;
	}
		
}
