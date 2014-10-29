package geniar.siscar.logic.parameters.services.impl;

import geniar.siscar.logic.parameters.services.LegateesTypesService;
import geniar.siscar.model.LegateesTypes;
import geniar.siscar.persistence.LegateesTypesDAO;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

import java.util.List;

public class LegateesTypeServicieImpl implements LegateesTypesService {

	public LegateesTypes consultarLegateeTypesById(Long idLegateeType)
			throws GWorkException {
		LegateesTypes legateesTypes = (new LegateesTypesDAO()
		.findById(idLegateeType));

		if (legateesTypes == null) {
			throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));
		}
		return legateesTypes;
	}

	public List<LegateesTypes> listLegateesTypes() throws GWorkException {
		List<LegateesTypes> listLegateesTypes = new LegateesTypesDAO().findAll();
		if (listLegateesTypes == null) {
			throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));
		}
		return listLegateesTypes;
	}

}
