package geniar.siscar.logic.parameters.services.impl;

import java.util.List;

import geniar.siscar.logic.parameters.services.TransmissionsTypesService;

import geniar.siscar.model.TransmissionsTypes;

import geniar.siscar.persistence.ITransmissionsTypesDAO;
import geniar.siscar.persistence.TransmissionsTypesDAO;

import gwork.exception.GWorkException;


public class TransmissionsTypesServiceImpl implements TransmissionsTypesService {
    public TransmissionsTypes consultarTransmissionsTypes(
        Long codeTransmissionsTypes) throws GWorkException {
        ITransmissionsTypesDAO transmissionsTypesDAO = new TransmissionsTypesDAO();
        TransmissionsTypes transmissionsTypes = transmissionsTypesDAO.findById(codeTransmissionsTypes);
        if (transmissionsTypes == null) {
            //TODO hacer exception
            throw new GWorkException("");
        }
        return transmissionsTypes;
    }

	public List<TransmissionsTypes> listTransmissionsTypes()
			throws GWorkException {
		    List<TransmissionsTypes> listTransmissionsTypes = new TransmissionsTypesDAO().findAll();
		    if(listTransmissionsTypes==null)
		    	//TODO hacer excepcion
		    	throw new GWorkException("");
		return listTransmissionsTypes;
	}
}
