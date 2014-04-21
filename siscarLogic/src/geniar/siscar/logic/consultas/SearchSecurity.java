package geniar.siscar.logic.consultas;

import geniar.siscar.model.Modules;
import geniar.siscar.model.Options;
import geniar.siscar.model.OptionsRolls;
import geniar.siscar.model.Users;
import geniar.siscar.persistence.EntityManagerHelper;
import gwork.exception.GWorkException;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SearchSecurity {

	private static final Log log = LogFactory.getLog(SearchSecurity.class);
	private static String rlsCodigo = "rlsCodigo";
	private static String optCodigo = "optCodigo";
	private static String usrLogin = "usrLogin";

	private static EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	@SuppressWarnings("unchecked")
	public static List<OptionsRolls> consultarOpcionesDeRol(Long idRol)
			throws GWorkException {
		try {
			List<OptionsRolls> list = null;
			final String queryString = "select model from OptionsRolls model where model.rolls."
					+ rlsCodigo + " = :idRol";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("idRol", idRol);
			list = (List<OptionsRolls>) query.getResultList();

			if (list != null && list.size() > 0)
				return list;

		} catch (RuntimeException re) {
			log.error("consultarOpcionesDeRol", re);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public static List<Modules> consultarOpcionesUsuario(String login)
			throws GWorkException {
		try {
			List<Modules> listModules = null;
			List<Users> listUsers = null;
			Users users = null;
			Options options = null;
			List<OptionsRolls> listOptionsRolls = null;
			final String queryString = "select model from Users model where model."
					+ usrLogin + " = :login";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("login", login);
			listUsers = (List<Users>) query.getResultList();

			if (listUsers != null && listUsers.size() > 0) {
				users = listUsers.get(0);
				listOptionsRolls = consultarOpcionesDeRol(users.getRolls()
						.getRlsCodigo());
				listModules = new ArrayList<Modules>();
				for (OptionsRolls optionsRolls : listOptionsRolls) {
					options = optionsRolls.getOptions();
					if (listModules != null
							&& !listModules.contains(options.getModules())) {
						listModules.add(options.getModules());
					}
				}
				return listModules;
			}

		} catch (RuntimeException re) {
			log.error("consultarOpcionesUsuario", re);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public static List<Modules> getAllModulesOrder() throws GWorkException {
		try {
			List<Modules> list = null;
			final String queryString = "select model from Modules model ORDER BY model.mdlNombre AS";
			Query query = getEntityManager().createQuery(queryString);
			list = (List<Modules>) query.getResultList();
			if (list != null && list.size() > 0)
				return list;

		} catch (RuntimeException re) {
			log.error("getAllModulesOrder", re);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public static OptionsRolls consultarOpcionesDeRol(Long idRol, Long idOpcion)
			throws GWorkException {
		try {
			List<OptionsRolls> list = null;
			final String queryString = "select model from OptionsRolls model where model.rolls."
					+ rlsCodigo
					+ "= :idRol and model.options."
					+ optCodigo
					+ " = :idOpcion";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("idRol", idRol);
			query.setParameter("idOpcion", idOpcion);
			list = (List<OptionsRolls>) query.getResultList();
			if (list != null && list.size() > 0)
				return list.get(0);

		} catch (RuntimeException re) {
			log.error("consultarOpcionesDeRol", re);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public static List<OptionsRolls> consultarOpcionesDeRolModulo(Long idRol,
			Long idModulo) throws GWorkException {
		try {
			List<OptionsRolls> list = null;
			final String queryString = "SELECT model FROM OptionsRolls model "
					+ "WHERE model.rolls.rlsCodigo = :idRol "
					+ "AND model.options.modules.mdlCodigo = :idModulo order by model.options.optNombre ASC";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("idRol", idRol);
			query.setParameter("idModulo", idModulo);
			list = (List<OptionsRolls>) query.getResultList();

			if (list != null && list.size() > 0)
				return list;

		} catch (RuntimeException re) {
			log.error("consultarOpcionesDeRol", re);
		}
		return null;
	}
}
