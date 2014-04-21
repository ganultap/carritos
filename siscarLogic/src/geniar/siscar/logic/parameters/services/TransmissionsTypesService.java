package geniar.siscar.logic.parameters.services;

import java.util.List;

import geniar.siscar.model.TransmissionsTypes;
import gwork.exception.GWorkException;

public interface TransmissionsTypesService {

	public TransmissionsTypes consultarTransmissionsTypes(Long codeTransmissionsTypes)throws GWorkException;
    public List<TransmissionsTypes> listTransmissionsTypes()throws GWorkException;
}
