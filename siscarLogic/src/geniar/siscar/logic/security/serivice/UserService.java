package geniar.siscar.logic.security.serivice;

import geniar.siscar.model.Users;
import geniar.siscar.model.VOUser;
import gwork.exception.GWorkException;

import java.util.List;

/**
 * 
 * @author JAM, - Geniar Arq S.A
 * @version 1.0
 * @Descripción : Interfaz para el Caso de Uso del manejo del CRUD de Usuarios
 * 
 */
public interface UserService {


	/**
	 * Crea un nuevo Usuario.
	 * @param rlsCodigo
	 * @param usrIdentificacion
	 * @param usrNombre
	 * @param usrApellido
	 * @param usrTelefono
	 * @param usrDireccion
	 * @param usrEmail
	 * @param usrLogin
	 * @throws GWorkException
	 */
	public void createUser(Long rlsCodigo,String estado, String usrIdentificacion, String usrNombre, String usrApellido, String usrTelefono,
			String usrDireccion, String usrEmail, String usrLogin) throws GWorkException;

	/**
	 * Modificar un usuario
	 * 
	 * @param usrCodigo
	 * @param rlsCodigo
	 * @param usrIdentificacion
	 * @param usrNombre
	 * @param usrApellido
	 * @param usrTelefono
	 * @param usrDireccion
	 * @param usrEmail
	 * @param usrLogin
	 * @throws GWorkException
	 */
	public void modificarUsuario(Long usrCodigo,String estado, Long rlsCodigo, String usrIdentificacion, String usrNombre, String usrApellido,
			String usrTelefono, String usrDireccion, String usrEmail, String usrLogin) throws GWorkException;
	
	/**
	 * Consulta un solo objeto de tipo User con el parametro:
	 * 
	 * @param login
	 * @return Un objeto de tipo {@link User}.
	 * @throws GWorkException
	 *             Manejador de excepciones.
	 */
	public Users consultarUsuario(String param);
	
	
	/**
	 * Consulta un usuario por el login
	 * @param login
	 * @return
	 */
	public Users consultarUsuarioPorLogin(String login)throws GWorkException;
	
	
	public VOUser consultUserByLogin(String login) throws GWorkException;
	
	/**
	 * Consulta un usuario por el login
	 * @param login
	 * @return
	 */
	public boolean consultaUsuarioPorLogin(String login) throws GWorkException;

	/**
	 * Encuentra todos los elementos tipo {@link Users}.
	 * 
	 * @return Una lista de elementos tipo User.
	 * @throws GWorkException
	 *             Manejador de excepciones.
	 */
	public List<Users> consultarUsuarios()throws GWorkException;
	
	/**
	 * Eliminar Usuario
	 * @param id
	 * @throws GWorkException
	 */
	public void eliminarUsuario(Long id) throws GWorkException;
	
}
