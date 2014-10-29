package geniar.siscar.logic.parameters.services.impl;

import java.util.List;

import geniar.siscar.logic.parameters.services.TariffTypesService;
import geniar.siscar.model.TariffsTypes;
import geniar.siscar.persistence.TariffsTypesDAO;
import gwork.exception.GWorkException;

public class TariffTypesServiceImpl implements TariffTypesService {

	public List<TariffsTypes> listTipoTarifas() throws GWorkException {
		return new TariffsTypesDAO().findAll();
	}

}
