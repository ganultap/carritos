package geniar.siscar.util;
import gwork.exception.GWorkException;

import org.cgiar.ciat.auth.ADConexion;
import org.cgiar.ciat.auth.LDAPUser;

public class ControllerAuthentication 
{	
	private ControllerAuthentication() {}

	private static ControllerAuthentication INSTANCE;
	private ADConexion conexion;
	
	public static ControllerAuthentication getInstance() 
	{	
		if(INSTANCE == null) {
			INSTANCE = new ControllerAuthentication();
		}
		return INSTANCE;
	}

	public boolean validateUser(String login, String password) throws GWorkException {
		conexion = new ADConexion(login, password);
		return conexion.isConnected();
//		return true;
	}
 
	public ADConexion getConexion() {
		return conexion;
	}

	public void setConexion(ADConexion conexion) {
		this.conexion = conexion;
	}

	public LDAPUser getUsuario() {
		return conexion.getUserData();
	}	
}
