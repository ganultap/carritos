/**
 * 
 */
package geniar.siscar.logic.security.serivice.impl;

import geniar.siscar.logic.consultas.SearchVehicles;
import geniar.siscar.logic.security.serivice.UserService;
import geniar.siscar.model.Rolls;
import geniar.siscar.model.Users;
import geniar.siscar.model.VOUser;
import geniar.siscar.persistence.EntityManagerHelper;
import geniar.siscar.persistence.RollsDAO;
import geniar.siscar.persistence.UsersDAO;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

import java.util.Date;
import java.util.List;

/**
 * 
 * @author JAM, - Geniar Arq S.A
 * @version 1.0
 * @Descripción : Control de Caso de Uso para el manejo del CRUD de Usuarios
 * 
 */
public class UserServiceImpl implements UserService {

	/*
	 * (non-Javadoc)
	 * 
	 * @see geniar.siscar.logic.security.serivice.UserService#createUser(java.lang.Long,
	 *      java.lang.String, java.lang.String, java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.String,
	 *      java.lang.String)
	 */
	public void createUser(Long rlsCodigo, String codigoEstado,
			String usrIdentificacion, String usrNombre, String usrApellido,
			String usrTelefono, String usrDireccion, String usrEmail,
			String usrLogin) throws GWorkException {

		try {
			Users users = null;
			Rolls rolls = null;
			List<Users> listUsers = new UsersDAO().findByUsrLogin(usrLogin
					.trim().toUpperCase());

			if (listUsers != null && listUsers.size() > 0)
				throw new GWorkException(Util
						.loadErrorMessageValue("USUARIO.LOGIN"));

			listUsers = consultarUsuarioPorIdentificacion(usrIdentificacion);

			if (listUsers != null && listUsers.size() > 0) {
				users = listUsers.get(0);
				if (users.getUsrEstado() != null
						&& users.getUsrEstado().equalsIgnoreCase(
								Util.loadMessageValue("ESTADO.INNACTIVO"))) {
					modificarUsuario(users.getUsrCodigo(), Util
							.loadMessageValue("ESTADO.ACTIVO"), rlsCodigo,
							usrIdentificacion, usrNombre, usrApellido,
							usrTelefono, usrDireccion, usrEmail, usrLogin);
				}
				
				if (users.getUsrEstado() == null)
				{
					modificarUsuario(users.getUsrCodigo(), Util
							.loadMessageValue("ESTADO.ACTIVO"), rlsCodigo,
							usrIdentificacion, usrNombre, usrApellido,
							usrTelefono, usrDireccion, usrEmail, usrLogin);
				}
					
			} else {
				listUsers = new UsersDAO().findByUsrEmail(usrEmail.trim()
						.toUpperCase());

				if (listUsers != null && listUsers.size() > 0)
					throw new GWorkException(Util
							.loadErrorMessageValue("CORREO.EXISTE"));

				listUsers = consultarUsuarioPorIdentificacion(usrIdentificacion);

				if (listUsers != null && listUsers.size() > 0)
					throw new GWorkException(Util
							.loadErrorMessageValue("USUARIO.IDENTIFICACION"));

				rolls = new RollsDAO().findById(rlsCodigo);

				if (rolls == null)
					throw new GWorkException(Util
							.loadErrorMessageValue("ROL.NOEXISTE"));

				users = new Users();

				if (usrApellido != null)
					users.setUsrApellido(usrApellido.toUpperCase().trim());

				if (usrDireccion != null)
					users.setUsrDireccion(usrDireccion.trim());

				if (usrLogin != null)
					users.setUsrLogin(usrLogin.toUpperCase().trim());

				if (usrEmail != null)
					users.setUsrEmail(usrEmail.toUpperCase().trim());

				if (usrNombre != null)
					users.setUsrNombre(usrNombre.toUpperCase().trim());

				if (usrTelefono != null)
					users.setUsrTelefono(usrTelefono);

				if (usrIdentificacion != null)
					users.setUsrIdentificacion(usrIdentificacion);

				users.setUsrEstado(Util.loadMessageValue("ESTADO.ACTIVO"));

				users.setRolls(rolls);
				guardarUsuario(users);
			}
		} catch (Exception e) {
			throw new GWorkException(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see geniar.siscar.logic.security.serivice.UserService#modificarUsuario(java.lang.Long,
	 *      java.lang.Long, java.lang.String, java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.String,
	 *      java.lang.String, java.lang.String)
	 */
	public void modificarUsuario(Long usrCodigo, String codigoEstado,
			Long rlsCodigo, String usrIdentificacion, String usrNombre,
			String usrApellido, String usrTelefono, String usrDireccion,
			String usrEmail, String usrLogin) throws GWorkException {
		try {
			String loginTemp = usrLogin.trim().toUpperCase();
			Users users = new UsersDAO().findById(usrCodigo);
			List<Users> listUsers = null;
			Rolls rolls = null;

			if (users != null && users.getUsrLogin() != null
					&& !users.getUsrLogin().equalsIgnoreCase(loginTemp))
				listUsers = new UsersDAO().findByUsrLogin(loginTemp);

			if (listUsers != null && listUsers.size() > 0)
				throw new GWorkException(Util
						.loadErrorMessageValue("USUARIO.LOGIN"));

			if (users != null && users.getUsrEmail() != null
					&& !users.getUsrEmail().equalsIgnoreCase(usrEmail))
				listUsers = new UsersDAO().findByUsrEmail(usrEmail.trim()
						.toUpperCase());

			if (listUsers != null && listUsers.size() > 0)
				throw new GWorkException(Util
						.loadErrorMessageValue("CORREO.EXISTE"));

			if (users != null
					&& users.getUsrIdentificacion() != null
					&& !users.getUsrIdentificacion().equalsIgnoreCase(
							usrIdentificacion))
				listUsers = consultarUsuarioPorIdentificacion(usrIdentificacion);

			if (listUsers != null && listUsers.size() > 0)
				throw new GWorkException(Util
						.loadErrorMessageValue("USUARIO.IDENTIFICACION"));

			rolls = new RollsDAO().findById(rlsCodigo);

			if (rolls == null)
				throw new GWorkException(Util
						.loadErrorMessageValue("ROL.NOEXISTE"));

			if ( rlsCodigo != null )
				users.setRolls(rolls);
			
			if (usrApellido != null)
				users.setUsrApellido(usrApellido.toUpperCase().trim());

			if (usrDireccion != null)
				users.setUsrDireccion(usrDireccion.trim());

			if (loginTemp != null)
				users.setUsrLogin(loginTemp);

			if (usrEmail != null)
				users.setUsrEmail(usrEmail.toUpperCase().trim());

			if (usrNombre != null)
				users.setUsrNombre(usrNombre.toUpperCase().trim());

			if (usrTelefono != null)
				users.setUsrTelefono(usrTelefono);

			users.setUsrEstado(codigoEstado);
			users.setUsrFecha(new Date());

			modificarUsuario(users);
		} catch (Exception e) {
			throw new GWorkException(e.getMessage());
		}
	}

	/**
	 * Metodo encargado de ejecutar la modificacion de un usuario
	 * 
	 * @param users
	 * @throws GWorkException
	 */
	public void modificarUsuario(Users users) throws GWorkException {
		try {
			Util.validarSession();
			EntityManagerHelper.beginTransaction();

			if (users != null) {
				new UsersDAO().update(users);
				EntityManagerHelper.commit();
				//Util.limpiarSession();
			}

		} catch (Exception e) {
			EntityManagerHelper.rollback();
			//Util.limpiarSession();
			throw new GWorkException(e.getMessage());
		}
	}

	/**
	 * Metodo encargado de manejar la session y guardar el Usuario
	 * 
	 * @param users
	 * @throws GWorkException
	 */
	public void guardarUsuario(Users users) throws GWorkException {
		try {
			Util.validarSession();
			EntityManagerHelper.beginTransaction();
			new UsersDAO().save(users);
			EntityManagerHelper.commit();
			//Util.limpiarSession();
		} catch (Exception e) {
			EntityManagerHelper.rollback();
			Util.validarSession();
			throw new GWorkException(e.getMessage());
		}
	}

	/**
	 * Metodo encargado de ejecutar la eliminiacion de un usuario
	 * 
	 * @param id
	 * @throws GWorkException
	 */
	public void eliminarUsuario(Long id) throws GWorkException {
		try {
			Util.validarSession();
			EntityManagerHelper.beginTransaction();
			Users users = new UsersDAO().findById(id);

			if (users != null) {
				users.setUsrEstado(Util.loadMessageValue("ESTADO.INNACTIVO"));
				new UsersDAO().update(users);
				EntityManagerHelper.commit();
				//Util.limpiarSession();
			}
		} catch (Exception e) {
			EntityManagerHelper.rollback();
			//Util.limpiarSession();
			throw new GWorkException(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see geniar.siscar.logic.security.serivice.UserService#consultarUsuario(java.lang.String)
	 */
	public Users consultarUsuario(String param) {
		return new UsersDAO().findById(new Long(param));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see geniar.siscar.logic.security.serivice.UserService#consultarUsuarioPorLogin(java.lang.String)
	 */
	public Users consultarUsuarioPorLogin(String login) throws GWorkException {
		Users users = null;
		List<Users> listUsers = new UsersDAO().findByUsrLogin(login);
		if (listUsers != null && listUsers.size() > 0) {
			users = new UsersDAO().findByUsrLogin(login).get(0);
		} else{
			throw new GWorkException(Util
					.loadErrorMessageValue("USUARIO.NOEXISTE"));
		}
		return users;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see geniar.siscar.logic.security.serivice.UserService#consultarUsuarioPorLogin(java.lang.String)
	 */
	public VOUser consultUserByLogin(String login) throws GWorkException {
		VOUser usersVo = null;
		List<Users> listUsers = new UsersDAO().findByUsrLogin(login);
		if (listUsers != null && listUsers.size() > 0) {

			Users users = new UsersDAO().findByUsrLogin(login).get(0);

			usersVo = new VOUser();
			usersVo.setUsrCodigo(users.getUsrCodigo());
			usersVo.setUsrNombre(users.getUsrNombre());
			usersVo.setUsrLogin(users.getUsrLogin());
			usersVo.setUsrApellido(users.getUsrApellido());
			usersVo.setUsrDireccion(users.getUsrDireccion());
			usersVo.setUsrEstado(users.getUsrEstado());
			usersVo.setUsrTelefono(users.getUsrTelefono());
			if (users.getDescripcion() != null)
				usersVo.setDescripcion(users.getDescripcion());
			else
				usersVo.setDescripcion("");
			usersVo.setUsrFecha(users.getUsrFecha());
			usersVo.setUsrIdentificacion(users.getUsrIdentificacion());
			usersVo.setUsrEmail(users.getUsrEmail());
			usersVo.setRolls(users.getRolls().getRlsNombre());
			return usersVo;

		} else
			throw new GWorkException(Util
					.loadErrorMessageValue("USUARIO.NOEXISTE"));
	}

	public List<Users> consultarUsuarioPorIdentificacion(String numeroUsuario)
			throws GWorkException {
		List<Users> listUsers = new UsersDAO()
				.findByUsrIdentificacion(numeroUsuario);
		if (listUsers != null && listUsers.size() > 0)
			return listUsers;

		return null;
	}

	public boolean consultaUsuarioPorLogin(String login) throws GWorkException {
		List<Users> listUsers = new UsersDAO().findByUsrLogin(login);
		if (listUsers != null && listUsers.size() > 0)
			return true;
		else
			return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see geniar.siscar.logic.security.serivice.UserService#consultarUsuarios()
	 */
	public List<Users> consultarUsuarios() throws GWorkException {
		return SearchVehicles.consultarUsuariosActivos();
	}

	public static void main(String[] args) throws Exception {
		UserService service = new UserServiceImpl();
		service.consultUserByLogin("SISCARTEST");
	}

}
