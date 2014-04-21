package geniar.siscar.logic.billing.services.impl;

import geniar.siscar.consults.ConsultsService;
import geniar.siscar.consults.impl.ConsultsServiceImpl;
import geniar.siscar.logic.billing.services.FlatFileFuelService;
import geniar.siscar.logic.consultas.SearchPlainFileParameter;
import geniar.siscar.model.FlatFile;
import geniar.siscar.model.Period;
import geniar.siscar.model.PlainFileParameter;
import geniar.siscar.model.Tariffs;
import geniar.siscar.persistence.EntityManagerHelper;
import geniar.siscar.persistence.NoveltyTypesDAO;
import geniar.siscar.util.ParametersUtil;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class FlatFileFuelServiceImpl implements FlatFileFuelService {

	private static final Log log = LogFactory.getLog(FlatFileFuelServiceImpl.class);
	
	public FlatFile generarArchivoPlanoCombustible(String carneAsignatario,
			Date fecha, Float valor, Float numGalones, String centroCosto,
			String placa, String login, Tariffs tariffs) throws GWorkException {

		try {

			ConsultsService consultsService = new ConsultsServiceImpl();
			Long concepto = null;
			Long tipoMoneda = null;
			String descripcion = null;
			Period period = null;
			PlainFileParameter plainFileParameter = null;
			Long unidades = 1L;

			// se consulta el tipo de modena del asignatario
			tipoMoneda = consultsService
					.counsultarTipoMonedaAsignatario(carneAsignatario);

			if (tipoMoneda == null)
				throw new GWorkException(Util
						.loadErrorMessageValue("EMPLEADO.SIN.NOMINA")
						+ ": " + carneAsignatario);

			Float tasaConversion = new ConsultsServiceImpl()
					.consultarTasaConversion(fecha);

			if (tipoMoneda.longValue() == ParametersUtil.PESOS)
				valor = valor * tasaConversion;

			plainFileParameter = new SearchPlainFileParameter()
					.consultar_PFP_PorId(tipoMoneda,
							ParametersUtil.NOVEDAD_COMB);

			SimpleDateFormat dateFormat = new SimpleDateFormat("MM/yyyy");
			concepto = plainFileParameter.getPfpConceptonomina();
			descripcion = plainFileParameter.getPfpDescripcion() + " "
					+ Util.loadMessageValue("GALONES") + numGalones + " "
					+ dateFormat.format(fecha);
			period = consultarPeriodoByfecha(fecha);
			FlatFile flatFile = new FlatFile();
			String formatoFecha = "yyyyMMdd";
			String ffFecha = new SimpleDateFormat(formatoFecha).format(fecha);

			flatFile.setFfCarne(carneAsignatario);
			flatFile.setFfFecha(new Long(ffFecha));
			flatFile.setFfValor(valor.toString());
			flatFile.setFfCentroCosto(centroCosto);
			flatFile.setFfDescripcion(descripcion);
			flatFile.setFfConcepto(concepto);
			flatFile.setFfMoneda(tipoMoneda);
			flatFile.setFfUnidades(unidades);
			flatFile.setFfDocumento(placa);
			flatFile.setTariff(tariffs);
			if (period != null)
				flatFile.setPeriod(period);
			flatFile.setNoveltyTypes(new NoveltyTypesDAO()
					.findById(ParametersUtil.NOVEDAD_COMB));
			flatFile.setFfUsuario(login);

			// EntityManagerHelper.beginTransaction();
			// new FlatFileDAO().save(flatFile);
			// EntityManagerHelper.commit();

			// generarArchivo(carneAsignatario, concepto.toString(), unidades
			// .toString(), valor.toString(), fecha, placa, centroCosto,
			// tipoMoneda.toString(), descripcion);
			return flatFile;

		} catch (Exception e) {
			log.error("generarArchivoPlanoCombustible", e);
			throw new GWorkException(e.getMessage(), e);
		}

	}
	
	public static Period consultarPeriodoByfecha(Date fecha)
			throws GWorkException {

		final StringBuffer queryString = new StringBuffer();
		queryString
				.append("SELECT model FROM Period model WHERE :fecha BETWEEN model.perFechaIni "
						+ "AND model.perFechaFin ORDER BY model.perFechaIni DESC");
		Query query = EntityManagerHelper.getEntityManager().createQuery(
				queryString.toString());
		query.setParameter("fecha", fecha);

		if (query.getResultList() == null || query.getResultList().size() == 0
				|| query.getResultList().isEmpty())
			throw new GWorkException(
					"Período no registrado para el tipo de novedad");
		else
			return (Period) query.getResultList().get(0);
	}

	public static Period consultarPeriodoByNovedad(Date fecha, Long tipoNovedad)
			throws GWorkException {

		final StringBuffer queryString = new StringBuffer();
		queryString
				.append("SELECT model FROM Period model WHERE :fecha BETWEEN model.perFechaIni "
						+ "AND model.perFechaFin AND model.noveltyTypes.ntId = :tipoNovedad "
						+ "ORDER BY model.perFechaIni DESC");
		Query query = EntityManagerHelper.getEntityManager().createQuery(
				queryString.toString());
		query.setParameter("fecha", fecha);
		query.setParameter("tipoNovedad", tipoNovedad);

		if (query.getResultList() == null || query.getResultList().size() == 0
				|| query.getResultList().isEmpty())
			throw new GWorkException(
					"Período no registrado para el tipo de novedad");
		else
			return (Period) query.getResultList().get(0);
	}

	
	public static boolean validarPeriodoByNovedad(Date fecha, Long tipoNovedad)
			throws GWorkException {

		try {
			final StringBuffer queryString = new StringBuffer();
			queryString
					.append("SELECT model FROM Period model WHERE :fecha BETWEEN model.perFechaIni "
							+ "AND model.perFechaFin AND model.noveltyTypes.ntId = :tipoNovedad "
							+ "ORDER BY model.perFechaIni DESC");
			Query query = EntityManagerHelper.getEntityManager().createQuery(
					queryString.toString());
			query.setParameter("fecha", fecha);
			query.setParameter("tipoNovedad", tipoNovedad);

			if (query.getResultList() != null
					&& query.getResultList().size() > 0
					&& !query.getResultList().isEmpty())
				return false;
			else
				return true;

		} catch (RuntimeException e) {
			throw new GWorkException(e.getMessage());
		}

	}

	@SuppressWarnings("unchecked")
	public static List<Period> consultarListaPeriodosByfecha(Date fecha)
			throws GWorkException {

		final StringBuffer queryString = new StringBuffer();
		queryString
				.append("SELECT model FROM Period model WHERE :fecha BETWEEN model.perFechaIni AND model.perFechaFin AND model.noveltyTypes.ntId=3 ORDER BY model.perFechaIni ASC");
		Query query = EntityManagerHelper.getEntityManager().createQuery(
				queryString.toString());
		query.setParameter("fecha", fecha);

		return query.getResultList();
	}

	public static void main(String[] args) {
		// try {
		// new FlatFileFuelServiceImpl().generarArchivoPlanoCombustible(
		// "02123", new Date(), 1200F, 10F, "IS01", "OI0120",
		// "SISCARTEST", new tarris);
	}
}
