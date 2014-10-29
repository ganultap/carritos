package geniar.siscar.logic.consultas;

import geniar.siscar.model.Tariffs;
import geniar.siscar.model.TariffsTypes;
import geniar.siscar.persistence.EntityManagerHelper;
import geniar.siscar.util.ParametersUtil;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * The Class ConsultTariff.
 */
public class ConsultTariff {

	/** The Constant log. */
	private static final Log log = LogFactory.getLog(ConsultTariff.class);
		
	/** The CAS a_ cia t_ casa. */
	private static Long CASA_CIAT_CASA = 5L;

	/**
	 * Gets the entity manager.
	 *
	 * @return the entity manager
	 */
	private static EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Consult assignment tariff.
	 *
	 * @param codeLine the code line
	 * @param codetypeTariff the codetype tariff
	 * @param codeFuelType the code fuel type
	 * @return the tariffs
	 */
	public static Tariffs consultAssignmentTariff(Long codeLine,
			Long codetypeTariff, Long codeFuelType) {
		/**
		 * select * from tariffs where tariffs.LNS_ID =1 and tariffs.TQL_ID =1
		 * and TVCH_CODIGO =1 and TC_CODIGO =1 and TPTCR_CODIGO =1 and
		 * TPTR_CODIGO =1
		 */

		EntityManagerHelper.log(
				"finding Tariff instance with property: line , typeTariff ,FuelType"
						+ ", value: " + codeLine + codetypeTariff
						+ codeFuelType, Level.INFO, null);
		try {
			final String queryString = "select t from Tariffs t where t.lines.lnsId=:line and t.tariffTypes.tqlId=1 and t.vehiclesTypes.tvchCodigo=1";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("line", 1l);
			Tariffs tariffs = (Tariffs) query.getSingleResult();
			System.out.println("entra query "
					+ tariffs.getLines().getLnsNombre());

		} catch (RuntimeException re) {
			re.printStackTrace();
		}
		return null;

	}
	
	/**
	 * Consultar tarifa actual alquiler por tipo tarifa y tipo vehiculo.
	 *
	 * @param idTipoVehiculo the id tipo vehiculo
	 * @param idTipoTarifa the id tipo tarifa
	 * @return the list
	 * @throws GWorkException the g work exception
	 */
	@SuppressWarnings("unchecked")
	public List<Tariffs> consultarTarifaActualAlquilerPorTipoTarifaYTipoVehiculo(Long idTipoVehiculo, Long idTipoTarifa)
			throws GWorkException {

		final String queryString = "select model from Tariffs model" 
			+ " where model.tariffsTypes.trtId= :idTipoTarifa"
			+ " and model.vehiclesTypes.vhtCodigo= :idTipoVehiculo" 
			+ " and model.trfFechaFin is null";
		Query query = getEntityManager().createQuery(queryString);
		query.setParameter("idTipoTarifa", idTipoTarifa);
		query.setParameter("idTipoVehiculo", idTipoVehiculo);

		return query.getResultList();
	}
	

	/**
	 * Consultar tarifa actual casa ciat.
	 *
	 * @param idZona the id zona
	 * @param idMoneda the id moneda
	 * @return the list
	 * @throws GWorkException the g work exception
	 */
	@SuppressWarnings("unchecked")
	public List<Tariffs> consultarTarifaActualCasaCiat(Long idZona,
			Long idMoneda) throws GWorkException {

		final String queryString = "select model from Tariffs model where model.tariffsTypes.trtId= :casaCIAT"
				+ " and model.zones.znsId= :zona"
				+ " and model.moneyType.mneyId= :moneda"
				+ " and model.trfFechaFin is null";
		Query query = getEntityManager().createQuery(queryString);
		query.setParameter("casaCIAT", CASA_CIAT_CASA);
		query.setParameter("zona", idZona);
		query.setParameter("moneda", idMoneda);

		return query.getResultList();
	}

	/**
	 * Consultar tarifa asignacion.
	 *
	 * @param idLinea the id linea
	 * @param idTipoCombustible the id tipo combustible
	 * @param idTipoTraccion the id tipo traccion
	 * @param idTipoTarifa the id tipo tarifa
	 * @return the list
	 * @throws GWorkException the g work exception
	 */
	@SuppressWarnings("unchecked")
	public List<Tariffs> consultarTarifaAsignacion(Long idLinea,
			Long idTipoCombustible, Long idTipoTraccion, Long idTipoTarifa) throws GWorkException {
		
		final String queryString = "select model from Tariffs model " 
				+ "where model.tariffsTypes.trtId= :tipoTarifa "
				+ " and model.lines.lnsId= :linea " 
				+ "and model.fuelsTypes.futCodigo= :tipoCombustible " 
				+ "and model.tractionsTypes.tctCodigo= :tipoTraccion " 
				+ "and model.trfFechaFin is null";

		Query query = getEntityManager().createQuery(queryString);
		query.setParameter("linea", idLinea);
		query.setParameter("tipoCombustible", idTipoCombustible);
		query.setParameter("tipoTraccion", idTipoTraccion);
		query.setParameter("tipoTarifa", idTipoTarifa);

		return query.getResultList();
	}

	/**
	 * Consultar tarifa asignacion mantenimiento.
	 *
	 * @param idLinea the id linea
	 * @param idTipoCombustible the id tipo combustible
	 * @param idTipoTraccion the id tipo traccion
	 * @return the list
	 * @throws GWorkException the g work exception
	 */
	@SuppressWarnings("unchecked")
	public List<Tariffs> consultarTarifaAsignacionMantenimiento(Long idLinea,
			Long idTipoCombustible, Long idTipoTraccion) throws GWorkException {

		final String queryString = "select model from Tariffs model where model.tariffsTypes.trtId= 2 "
				+ "and model.lines.lnsId= :linea and model.fuelsTypes.futCodigo= :tipoCombustible and "
				+ "model.tractionsTypes.tctCodigo= :tipoTraccion and  model.trfFechaFin is null";
		Query query = getEntityManager().createQuery(queryString);
		query.setParameter("linea", idLinea);
		query.setParameter("tipoCombustible", idTipoCombustible);
		query.setParameter("tipoTraccion", idTipoTraccion);

		return query.getResultList();

	}

	/**
	 * Consultar tarifa asignacion espacio fisico.
	 *
	 * @param idLinea the id linea
	 * @param idTipoCombustible the id tipo combustible
	 * @param idTipoTraccion the id tipo traccion
	 * @return the list
	 * @throws GWorkException the g work exception
	 */
	@SuppressWarnings("unchecked")
	public List<Tariffs> consultarTarifaAsignacionEspacioFisico(Long idLinea,
			Long idTipoCombustible, Long idTipoTraccion) throws GWorkException {

		final String queryString = "select model from Tariffs model where model.tariffsTypes.trtId= " + 
				ParametersUtil.TARIFA_ASIGNACION_ESPACIO_FISICO  
				+ "and model.lines.lnsId= :linea and model.fuelsTypes.futCodigo= :tipoCombustible and "
				+ "model.tractionsTypes.tctCodigo= :tipoTraccion and  model.trfFechaFin is null";
		Query query = getEntityManager().createQuery(queryString);
		query.setParameter("linea", idLinea);
		query.setParameter("tipoCombustible", idTipoCombustible);
		query.setParameter("tipoTraccion", idTipoTraccion);

		return query.getResultList();
	}
	
	/**
	 * Consultar tarifa asignacion autoseguro.
	 *
	 * @param idLinea the id linea
	 * @param idTipoCombustible the id tipo combustible
	 * @param idTipoTraccion the id tipo traccion
	 * @return the list
	 * @throws GWorkException the g work exception
	 */
	@SuppressWarnings("unchecked")
	public List<Tariffs> consultarTarifaAsignacionAutoseguro(Long idLinea,
			Long idTipoCombustible, Long idTipoTraccion) throws GWorkException {

		final String queryString = "select model from Tariffs model where model.tariffsTypes.trtId= 3 "
				+ "and model.lines.lnsId= :linea and model.fuelsTypes.futCodigo= :tipoCombustible and "
				+ "model.tractionsTypes.tctCodigo= :tipoTraccion and  model.trfFechaFin is null";
		Query query = getEntityManager().createQuery(queryString);
		query.setParameter("linea", idLinea);
		query.setParameter("tipoCombustible", idTipoCombustible);
		query.setParameter("tipoTraccion", idTipoTraccion);

		return query.getResultList();

	}

	/**
	 * Consultar tarifa asignacion depreciacion.
	 *
	 * @param idLinea the id linea
	 * @param idTipoCombustible the id tipo combustible
	 * @param idTipoTraccion the id tipo traccion
	 * @return the list
	 * @throws GWorkException the g work exception
	 */
	@SuppressWarnings("unchecked")
	public List<Tariffs> consultarTarifaAsignacionDepreciacion(Long idLinea,
			Long idTipoCombustible, Long idTipoTraccion) throws GWorkException {

		final String queryString = "select model from Tariffs model where model.tariffsTypes.trtId= 1 "
				+ "and model.lines.lnsId= :linea and model.fuelsTypes.futCodigo= :tipoCombustible and "
				+ "model.tractionsTypes.tctCodigo= :tipoTraccion and model.trfFechaFin is null";

		Query query = getEntityManager().createQuery(queryString);
		query.setParameter("linea", idLinea);
		query.setParameter("tipoCombustible", idTipoCombustible);
		query.setParameter("tipoTraccion", idTipoTraccion);

		return query.getResultList();
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {

		ConsultTariff consultTariff = new ConsultTariff();
		try {

			String idFuel = consultTariff.consultLastKm(new Long("101"));
			// System.out.println(consultTariff.consultarTarifaActual(5L));
			// List<Tariffs> list = consultTariff.consultarTarifaActual(5L);
			System.out.println(idFuel);
		} catch (GWorkException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Consultar tarifa actual.
	 *
	 * @param idTipoCombustible the id tipo combustible
	 * @param idTipoTarifa the id tipo tarifa
	 * @param nombreTipoTarifa the nombre tipo tarifa
	 * @param fechaHasta the fecha hasta
	 * @return the tariffs
	 * @throws GWorkException the g work exception
	 */
	@SuppressWarnings("unchecked")
	public Tariffs consultarTarifaActual(Long idTipoCombustible,
			Long idTipoTarifa, String nombreTipoTarifa, Date fechaHasta)
			throws GWorkException {
		try {
			final String queryString = "select t from Tariffs t "
					+ "where t.tariffsTypes.trtId=" + idTipoTarifa
					+ " AND t.tariffsTypes.trtNombre='" + nombreTipoTarifa
					+ "' AND t.trfFechaFin is null"
					+ " AND t.fuelsTypes.futCodigo=" + idTipoCombustible;

			Query query = getEntityManager().createQuery(queryString);

			List<Tariffs> list = query.getResultList();
			if (list.size() > 0) {
				return (Tariffs) list.get(0);
			} else
				return null;
		} catch (RuntimeException re) {
			throw new GWorkException(Util
					.loadErrorMessageValue("search.not.found"));
		}
	}

	/**
	 * Consultar tarifas combustible.
	 *
	 * @return the list
	 * @throws GWorkException the g work exception
	 */
	@SuppressWarnings("unchecked")
	public List<TariffsTypes> consultarTarifasCombustible()
			throws GWorkException {
		try {
			final String queryString = "select t from TariffsTypes t where "
					+ "t.trtNombre='CIAT' OR t.trtNombre='CALI'";

			Query query = getEntityManager().createQuery(queryString);

			List<TariffsTypes> list = query.getResultList();
			if (list != null && list.size() > 0) {
				return list;
			} else
				return null;
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new GWorkException(Util
					.loadErrorMessageValue("search.not.found"));
		}
	}

	/**
	 * Consult last km.
	 *
	 * @param idVehicle the id vehicle
	 * @return the string
	 * @throws GWorkException the g work exception
	 */
	public String consultLastKm(Long idVehicle) throws GWorkException {
		try {

			String datos = "0";
			StringBuffer buffer = new StringBuffer();
			buffer
					.append("select model.serKilometrajeActual from ServiceRegistry model where model.vehicles.vhcCodigo = :idVehicle");
			buffer
					.append(" order by model.serCodigo desc model where rownum =1");

			Query query = getEntityManager().createQuery(buffer.toString());
			query.setParameter("idVehicle", idVehicle);

			if (query.getResultList() != null
					&& query.getResultList().size() > 0)
				datos = (String) query.getResultList().get(0);

			return datos;

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	/**
	 * Consultar valor tarifa combustible.
	 *
	 * @param tipoCombustible the tipo combustible
	 * @return the string
	 * @throws GWorkException the g work exception
	 */
	public String consultarValorTarifaCombustible(Long tipoCombustible)
			throws GWorkException {
		try {

			String valor = null;
			final String queryString = "select model.trfValor from Tariffs model where "
					+ "model.tariffsTypes.trtNombre='CIAT' AND model.fuelsTypes.futCodigo=:tipoCombustible "
					+ "AND model.trfFechaFin IS NULL";

			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("tipoCombustible", tipoCombustible);

			if (query.getResultList() != null
					&& query.getResultList().size() > 0)
				valor = query.getSingleResult().toString();

			return valor;
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new GWorkException(Util
					.loadErrorMessageValue("search.not.found"));
		}
	}

	/**
	 * Consultar tarifa tipo combustible.
	 *
	 * @param tipoCombustible the tipo combustible
	 * @return the tariffs
	 * @throws GWorkException the g work exception
	 */
	@SuppressWarnings("unchecked")
	public Tariffs consultarTarifaTipoCombustible(Long tipoCombustible)
			throws GWorkException {
		try {

			List<Tariffs> valor = null;
			Tariffs tariffs = null;
			final String queryString = "select model from Tariffs model where "
					+ "model.tariffsTypes.trtNombre='CIAT' AND model.fuelsTypes.futCodigo=:tipoCombustible "
					+ "AND model.trfFechaFin IS NULL";

			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("tipoCombustible", tipoCombustible);

			valor = query.getResultList();

			if (valor != null && valor.size() > 0)
				tariffs = valor.get(0);

			return tariffs;

		} catch (RuntimeException re) {
			log.error("consultarTarifaTipoCombustible", re);
			throw new GWorkException(Util
					.loadErrorMessageValue("search.not.found"), re);
		}
	}

	/**
	 * Consultar tarifa tipo combustible cali.
	 *
	 * @param tipoCombustible the tipo combustible
	 * @return the tariffs
	 * @throws GWorkException the g work exception
	 */
	@SuppressWarnings("unchecked")
	public Tariffs consultarTarifaTipoCombustibleCali(Long tipoCombustible)
			throws GWorkException {
		try {

			List<Tariffs> valor = null;
			Tariffs tariffs = null;
			final String queryString = "select model from Tariffs model where "
					+ "model.tariffsTypes.trtId=6 AND model.fuelsTypes.futCodigo=:tipoCombustible "
					+ "AND model.trfFechaFin IS NULL";

			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("tipoCombustible", tipoCombustible);

			valor = query.getResultList();

			if (valor != null && valor.size() > 0)
				tariffs = valor.get(0);

			return tariffs;

		} catch (RuntimeException re) {
			log.error("consultarTarifaTipoCombustibleCali", re);
			throw new GWorkException(Util
					.loadErrorMessageValue("search.not.found"), re);
		}
	}

	/**
	 * Consultar tarifa tipo cali.
	 *
	 * @param tipoCombustible the tipo combustible
	 * @return the string
	 * @throws GWorkException the g work exception
	 */
	@SuppressWarnings("unchecked")
	public String consultarTarifaTipoCali(Long tipoCombustible)
			throws GWorkException {
		try {

			List<Tariffs> valor = null;
			Tariffs tariffs = null;
			final String queryString = "select model from Tariffs model where "
					+ "model.tariffsTypes.trtId=6 AND model.fuelsTypes.futCodigo=:tipoCombustible "
					+ "AND model.trfFechaFin IS NULL";

			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("tipoCombustible", tipoCombustible);

			valor = query.getResultList();

			if (valor != null && valor.size() > 0) {
				tariffs = valor.get(0);

				return tariffs.getTrfValor().toString();
			}
			return null;

		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new GWorkException(Util
					.loadErrorMessageValue("search.not.found"));
		}
	}

	/**
	 * Consultar valor tarifa tipo combustible.
	 *
	 * @param tipoCombustible the tipo combustible
	 * @return the string
	 * @throws GWorkException the g work exception
	 */
	public String consultarValorTarifaTipoCombustible(Long tipoCombustible)
			throws GWorkException {
		try {
			String valor = null;
			final String queryString = "select model.trfValor from Tariffs model where "
					+ "model.tariffsTypes.trtId=7 AND model.fuelsTypes.futCodigo=:tipoCombustible "
					+ "AND model.trfFechaFin IS NULL";

			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("tipoCombustible", tipoCombustible);

			if (query.getResultList() != null
					&& query.getResultList().size() > 0)
				valor = query.getSingleResult().toString();

			return valor;
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new GWorkException(Util
					.loadErrorMessageValue("search.not.found"));
		}
	}

	/**
	 * Consultar tarifa actual ciat casa ciat.
	 *
	 * @param idZona the id zona
	 * @param idMoneda the id moneda
	 * @return the tariffs
	 * @throws GWorkException the g work exception
	 */
	@SuppressWarnings("unchecked")
	public Tariffs consultarTarifaActualCiatCasaCiat(Long idZona, Long idMoneda)
			throws GWorkException {
		List<Tariffs> ListaTarifa;
		Tariffs tariff = null;
		final String queryString = "select model from Tariffs model"
				+ " where model.tariffsTypes.trtId= :casaCIAT"
				+ " and model.zones.znsId= :zona"
				+ " and model.moneyType.mneyId= :moneda"
				+ " and model.trfFechaFin is null";

		Query query = getEntityManager().createQuery(queryString);
		query.setParameter("casaCIAT", CASA_CIAT_CASA);
		query.setParameter("zona", idZona);
		query.setParameter("moneda", idMoneda);

		ListaTarifa = query.getResultList();

		if (ListaTarifa != null && ListaTarifa.size() > 0)
			tariff = ListaTarifa.get(0);

		return tariff;
	}
}
