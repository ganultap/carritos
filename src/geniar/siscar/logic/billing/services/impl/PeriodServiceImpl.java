package geniar.siscar.logic.billing.services.impl;

import geniar.siscar.logic.billing.services.PeriodService;
import geniar.siscar.logic.consultas.SearchPeriod;
import geniar.siscar.model.Period;
import geniar.siscar.persistence.EntityManagerHelper;
import geniar.siscar.persistence.NoveltyTypesDAO;
import geniar.siscar.persistence.PeriodDAO;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

import java.util.Date;
import java.util.List;

public class PeriodServiceImpl implements PeriodService {

	/*
	 * (non-Javadoc)
	 * 
	 * @see geniar.siscar.logic.parameters.services.
	 *      PlainFileParameterService#consultarPlainFileParameter
	 *      (java.lang.Long, java.lang.Long)
	 */
	public Period consultarPeriod(String nombre) throws GWorkException {
		return new SearchPeriod().consultar_PRD_PorNombre(nombre);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see geniar.siscar.logic.parameters.services.
	 *      PlainFileParameterService#crearPlainFileParameter( java.lang.Long,
	 *      java.lang.Long, java.lang.Long, java.lang.String)
	 */
	public void crearPeriod(String prdNombre, String prdDescripcion,
			Date prdFechainicio, Date prdFechaterminacion, Long TipoNovedad)
			throws GWorkException {

		if (new SearchPeriod().consultarPeriodoPorNombre(prdNombre) != null) {
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.EXISTEPERIODO"));
		}

		if (!FlatFileFuelServiceImpl.validarPeriodoByNovedad(prdFechainicio,
				TipoNovedad)) {
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.FECHASPERIODO"));
		}

		Period period = new Period();

		period.setPerNombre(prdNombre);
		period.setPerObservacion(prdDescripcion);
		period.setPerFechaIni(prdFechainicio);
		period.setPerFechaFin(prdFechaterminacion);
		period.setNoveltyTypes(new NoveltyTypesDAO().findById(TipoNovedad));

		EntityManagerHelper.beginTransaction();
		new PeriodDAO().save(period);
		EntityManagerHelper.commit();
		// Util.limpiarSession();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see geniar.siscar.logic.parameters.services.
	 *      PlainFileParameterService#listarTodosPlainFileParameters()
	 */
	public List<Period> listarTodosPeriod() throws GWorkException {
		return new PeriodDAO().findAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see geniar.siscar.logic.period.services. PeriodService#modificarPeriod(
	 *      java.lang.Long, java.lang.Long)
	 */
	public void modificarPeriod(Long prdPeriodo, String prdNombre,
			String prdDescripcion, Date prdFechainicio,
			Date prdFechaterminacion, Long TipoNovedad) throws GWorkException {
		try {
			Period period = new PeriodDAO().findById(prdPeriodo);

			if (period == null) {
				throw new GWorkException(Util
						.loadErrorMessageValue("ERROR.CONSULTAPERIODO"));
			}

			if (new SearchPeriod().consultarPeriodoPorNombreNoId(prdNombre,
					prdPeriodo) != null) {
				throw new GWorkException(Util
						.loadErrorMessageValue("ERROR.EXISTEPERIODO"));
			}

			if (new SearchPeriod().consultarPeriodoPorFechaNoId(prdFechainicio,
					prdPeriodo) != null) {
				throw new GWorkException(Util
						.loadErrorMessageValue("ERROR.FECHASPERIODO"));
			}

			period.setPerNombre(prdNombre.trim().toUpperCase());
			period.setPerObservacion(prdDescripcion.trim().toUpperCase());
			period.setPerFechaIni(prdFechainicio);
			period.setPerFechaFin(prdFechaterminacion);
			period.setNoveltyTypes(new NoveltyTypesDAO().findById(TipoNovedad));

			EntityManagerHelper.getEntityManager().getTransaction().begin();
			new PeriodDAO().update(period);
			EntityManagerHelper.getEntityManager().getTransaction().commit();
			// Util.limpiarSession();
		} catch (Exception e) {
			if (EntityManagerHelper.getEntityManager().getTransaction()
					.isActive())
				EntityManagerHelper.getEntityManager().getTransaction()
						.rollback();
			// Util.limpiarSession();
			throw new GWorkException(e.getMessage());
		}
	}

	/**
	 * Metodo encargado de ejecutar la eliminiacion de un periodo
	 * 
	 * @param id
	 * @throws GWorkException
	 */
	public void eliminarPeriodo(Long id) throws GWorkException {
		try {
			Util.validarSession();
			EntityManagerHelper.getEntityManager().getTransaction().begin();
			Period period = new PeriodDAO().findById(id);
			if (period != null) {
				new PeriodDAO().delete(period);
				EntityManagerHelper.getEntityManager().getTransaction()
						.commit();
				// Util.limpiarSession();
			}
		} catch (Exception e) {
			EntityManagerHelper.getEntityManager().getTransaction().rollback();
			// Util.limpiarSession();
			throw new GWorkException(e.getMessage());
		}
	}

	public Period consultarPeriodById(Long idPeriodo) throws GWorkException {
		return new PeriodDAO().findById(idPeriodo);
	}

	public List<Period> periodsByeNoveltie(Long idNoveltyType)
			throws RuntimeException {

		List<Period> listPeriod = null;
		listPeriod = SearchPeriod.periodsByeNoveltie(idNoveltyType);

		if (listPeriod != null && listPeriod.size() > 0
				&& !listPeriod.isEmpty())
			return listPeriod;
		else
			throw new RuntimeException(
					"No existen peridos contables para el tipo de novedad: "
							+ idNoveltyType);
	}
}
