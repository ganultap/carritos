package geniar.siscar.logic.security.serivice;

import java.util.List;

import geniar.siscar.model.Rolls;
import gwork.exception.GWorkException;


/**
 * 
 * @author JAM, - Geniar Arq S.A
 * @version 1.0
 * @Descripción : Interfaz para el manejo del CRUD de Roles
 * 
 */
public interface RolService {

	public void crearRol(String strNombre, String strMail, String strDescripcion) throws GWorkException;

	public Rolls consultarRol(String param);
	
	public Rolls consultarRolPorNombre(String nombre) throws GWorkException;

	public List<Rolls> consultarRoles();

	public void eliminarRol(Long id) throws GWorkException;

	public void modificarRol(Long idRol, String strNombre, String strMail, String strDescripcion) throws GWorkException;

}
