package geniar.siscar.logic.security.serivice;

import geniar.siscar.model.Modules;
import gwork.exception.GWorkException;

import java.util.List;

public interface MenuBarService {

	public List<Modules>  consultarOpcionesUsuario(String login)throws GWorkException;
	
}
