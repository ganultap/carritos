package geniar.siscar.logic.security.serivice.impl;

import geniar.siscar.logic.consultas.SearchSecurity;
import geniar.siscar.logic.security.serivice.MenuBarService;
import geniar.siscar.model.Modules;
import gwork.exception.GWorkException;

import java.util.List;

public class MenuBarServiceImpl implements MenuBarService {

	public List<Modules> consultarOpcionesUsuario(String login) throws GWorkException {
		return SearchSecurity.consultarOpcionesUsuario(login);
	}
}
