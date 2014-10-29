package geniar.siscar.logic.parameters.services.impl;

import java.util.List;

import geniar.siscar.logic.parameters.services.TractionsTypesService;
import geniar.siscar.model.TractionsTypes;
import geniar.siscar.persistence.ITractionsTypesDAO;
import geniar.siscar.persistence.TractionsTypesDAO;
import gwork.exception.GWorkException;

public class TractionsTypesServiceImpl implements TractionsTypesService {

	public TractionsTypes consultarTractionsTypes(Long codeTractionsTypes)
	throws GWorkException {
		ITractionsTypesDAO tractionsTypesDAO  = new TractionsTypesDAO();
		TractionsTypes tractionsTypes         = tractionsTypesDAO.findById(codeTractionsTypes);
		if(tractionsTypes==null){
			//TODO hacer Exception
			throw new GWorkException("");
		}
		return tractionsTypes;
	}

	public List<TractionsTypes> listTractionsTypes() throws GWorkException {
		List<TractionsTypes> listTractionsTypes = new TractionsTypesDAO().findAll();
		if(listTractionsTypes==null)
			//TODO hacer excepcion
			throw new GWorkException("");

		return listTractionsTypes;
	}

}
