package geniar.siscar.logic.security.serivice.impl;

import geniar.siscar.logic.security.serivice.ModulesService;
import geniar.siscar.model.Rolls;
import geniar.siscar.persistence.RollsDAO;

import java.util.List;

public class RollsServiceImpl implements ModulesService {

	public List<Rolls> getRolls() {
		return new RollsDAO().findAll();
	}

}
