package geniar.siscar.logic.parameters.services;

import gwork.exception.GWorkException;

public interface FindParameterService {
	
	public Float findParameterByName(String nombreParametro) throws GWorkException;
	public Float findParameterById(Long idParametro)throws GWorkException;
}
