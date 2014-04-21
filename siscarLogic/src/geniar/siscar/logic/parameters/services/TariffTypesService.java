package geniar.siscar.logic.parameters.services;

import geniar.siscar.model.TariffsTypes;
import gwork.exception.GWorkException;

import java.util.List;

public interface TariffTypesService {
	
	public List<TariffsTypes> listTipoTarifas()throws GWorkException;

}
