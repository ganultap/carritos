package geniar.siscar.logic.consultas;

import geniar.siscar.model.Users;
import geniar.siscar.persistence.EntityManagerHelper;
import geniar.siscar.persistence.IUsersDAO;
import geniar.siscar.persistence.UsersDAO;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;
import java.util.List;

import javax.persistence.Query;

public class SearchUser {

	/**
	 * Consulta un registro especifico con los parametros indicados.
	 * 
	 * @param login
	 *            login del usuario.
	 * @return objeto Users.
	 * @throws GWorkException
	 *             Manejador de Excepciones.
	 */
	public static Users getUserByLogin(String login) throws GWorkException {
		IUsersDAO usersDAO = (IUsersDAO) new UsersDAO();
		if (usersDAO.findByUsrLogin(login) == null
				|| usersDAO.findByUsrLogin(login).isEmpty()
				|| usersDAO.findByUsrLogin(login).size() <= 0) {
			throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));
		}
		Users users = usersDAO.findByUsrLogin(login).get(0);
		return users;
	}

	/**
	 * Consulta todos los usuarios que cumplan con algún parametro de búsqueda.
	 * 
	 * @param nombreUsuario
	 *            Parametro de busqueda
	 * @param carne
	 *            Parametro de busqueda.
	 * @param opcion
	 *            Indica si se filtra por el carné (2) o por el nombre de
	 *            usuario (1)
	 * @return Listado de usuarios que coinciden con el parametro de búsqueda.
	 * @throws GWorkException
	 *             Manejador de excepciones.
	 */
	@SuppressWarnings("unchecked")
	public static List<Users> consultarUsuarios(String nombreUsuario,
			String carne, int opcion) throws GWorkException {
		try {
			Util.validarSession();
			List<Users> listUsers = null;

			String queryString = "";

			if (opcion == 1) {
				queryString = "select model from Users model where model.usrNombre LIKE '%"
						+ nombreUsuario
						+ "%' ORDER BY model.usrNombre ASC";
			}

			if (opcion == 2) {
				queryString = "select model from Users model where model.usrIdentificacion LIKE '%"
						+ carne + "%' ORDER BY model.usrNombre ASC";
			}

			Query query = EntityManagerHelper.getEntityManager().createQuery(
					queryString);
			listUsers = (List<Users>) query.getResultList();

			if (listUsers != null && listUsers.size() > 0)
				return listUsers;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
