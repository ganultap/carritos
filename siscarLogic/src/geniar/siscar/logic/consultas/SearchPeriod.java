package geniar.siscar.logic.consultas;

import geniar.siscar.model.Period;
import geniar.siscar.persistence.EntityManagerHelper;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class SearchPeriod {

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Consulta un objeto de tipo {@link Period} con la combina ción de
	 * identificadores especificadas en los parametros.
	 * 
	 * @param nombre
	 *            del periodo.
	 * @return Un objeto de tipo {@link Period}.
	 * @throws GWorkException
	 */
	@SuppressWarnings("unchecked")
	public Period consultar_PRD_PorNombre(String nombre) throws GWorkException {
		try {
			String queryString = "";

			queryString = "select prd from Period prd where prd.perNombre LIKE '%"
					+ nombre + "%' ORDER BY prd.fechaInicio, prd.perNombre ASC";

			Query query = getEntityManager().createQuery(queryString);
			// query.setParameter("nombre", nombre);

			List<Period> list = query.getResultList();
			if (list != null && list.size() > 0) {
				return list.get(0);
			} else
				return null;

		} catch (RuntimeException re) {
			throw new GWorkException(Util
					.loadErrorMessageValue("search.not.found"));
		}
	}

	/**
	 * Consulta un objeto de tipo {@link Period} con la combina ción de
	 * identificadores especificadas en los parametros.
	 * 
	 * @param nombre
	 *            del periodo.
	 * @return Un objeto de tipo {@link Period}.
	 * @throws GWorkException
	 */
	@SuppressWarnings("unchecked")
	public Period consultarPeriodoPorNombre(String nombre)
			throws GWorkException {
		try {
			String queryString = "";

			queryString = "select prd from Period prd where prd.perNombre = '"
					+ nombre + "' ORDER BY prd.perNombre ASC";

			Query query = getEntityManager().createQuery(queryString);
			// query.setParameter("nombre", nombre);

			List<Period> list = query.getResultList();
			if (list != null && list.size() > 0) {
				return list.get(0);
			} else
				return null;

		} catch (RuntimeException re) {
			throw new GWorkException(Util
					.loadErrorMessageValue("search.not.found"));
		}
	}

	/**
	 * Consulta un objeto de periodo {@link Period} para verificar si ya existe
	 * un periodo con ese nobre, si es así no se debe modificar
	 * 
	 * @param nombre
	 *            del periodo.
	 * @param id
	 *            del periodo.
	 * @return Un objeto de tipo {@link Period}.
	 * @throws GWorkException
	 */
	@SuppressWarnings("unchecked")
	public Period consultarPeriodoPorNombreNoId(String nombre, Long idPeriodo)
			throws GWorkException {
		try {
			String queryString = "";

			queryString = "select prd from Period prd where prd.perNombre = '"
					+ nombre + "' and prd.perId not in(" + idPeriodo + ") "
					+ "ORDER BY prd.perNombre ASC";

			Query query = getEntityManager().createQuery(queryString);
			// query.setParameter("nombre", nombre);

			List<Period> list = query.getResultList();
			if (list != null && list.size() > 0) {
				return list.get(0);
			} else
				return null;

		} catch (RuntimeException re) {
			throw new GWorkException(Util
					.loadErrorMessageValue("search.not.found"));
		}
	}

	/**
	 * Consulta un objeto de periodo {@link Period} para verificar si ya existe
	 * un periodo con ese nombre, si es así no se debe modificar
	 * 
	 * @param nombre
	 *            del periodo.
	 * @param id
	 *            del periodo.
	 * @return Un objeto de tipo {@link Period}.
	 * @throws GWorkException
	 */
	@SuppressWarnings("unchecked")
	public Period consultarPeriodoPorFecha(Date fechaInicio)
			throws GWorkException {
		try {
			String queryString = "";

			queryString = "select prd from Period prd where prd.perFechaFin > :fechaInicio "
					+ " ORDER BY prd.perFechaFin ASC";

			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("fechaInicio", fechaInicio);

			List<Period> list = query.getResultList();
			if (list != null && list.size() > 0) {
				return list.get(0);
			} else
				return null;

		} catch (RuntimeException re) {
			throw new GWorkException(Util
					.loadErrorMessageValue("search.not.found"));
		}
	}

	/**
	 * Consulta un objeto de periodo {@link Period} para verificar si ya existe
	 * un periodo con ese nobre, si es así no se debe modificar
	 * 
	 * @param nombre
	 *            del periodo.
	 * @param id
	 *            del periodo.
	 * @return Un objeto de tipo {@link Period}.
	 * @throws GWorkException
	 */
	@SuppressWarnings("unchecked")
	public Period consultarPeriodoPorFechaNoId(Date fechaInicio, Long idPeriodo)
			throws GWorkException {
		try {
			String queryString = "";

			queryString = "select prd from Period prd where prd.perFechaFin >  :fechaInicio"
					+ " and prd.perId not in("
					+ idPeriodo
					+ ") "
					+ "ORDER BY prd.perFechaFin ASC";

			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("fechaInicio", fechaInicio);

			List<Period> list = query.getResultList();
			if (list != null && list.size() > 0) {
				return list.get(0);
			} else
				return null;

		} catch (RuntimeException re) {
			throw new GWorkException(Util
					.loadErrorMessageValue("search.not.found"));
		}
	}

	@SuppressWarnings("unchecked")
	public static List<Period> periodsByeNoveltie(Long idNoveltyType) {

		final String queryString = "SELECT per FROM Period per "
				+ "WHERE per.noveltyTypes.ntId = :idNoveltyType"
				+ " ORDER BY per.perFechaFin, per.perNombre DESC";

		Query query = EntityManagerHelper.getEntityManager().createQuery(
				queryString);
		query.setParameter("idNoveltyType", idNoveltyType);

		if (query.getResultList() != null && query.getResultList().size() > 0
				&& !query.getResultList().isEmpty())
			return (List<Period>) query.getResultList();
		else
			return null;

	}
}
