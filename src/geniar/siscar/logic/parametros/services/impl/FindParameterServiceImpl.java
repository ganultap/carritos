package geniar.siscar.logic.parametros.services.impl;

import geniar.siscar.logic.parameters.services.FindParameterService;
import geniar.siscar.model.ParValoresparametros;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

public class FindParameterServiceImpl implements FindParameterService {

	public Float findParameterByName(String nombreParametro) throws GWorkException {

		ActualParameter actualParameter = new ActualParameter();
		ParValoresparametros parValoresparametros = actualParameter.consultarUltimoParametroByName(nombreParametro);
		if (parValoresparametros == null)
			throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));
		return parValoresparametros.getValorinicial();
	}

	public Float findParameterById(Long idParametro) throws GWorkException {

		ActualParameter actualParameter = new ActualParameter();
		ParValoresparametros parValoresparametros = actualParameter.consultarUltimoParametro(idParametro);
		if (parValoresparametros == null)
			throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));
		return parValoresparametros.getValorinicial();

	}

}
