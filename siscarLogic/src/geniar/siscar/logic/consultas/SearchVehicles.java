package geniar.siscar.logic.consultas;

import geniar.siscar.model.ActualOthersApplications;
import geniar.siscar.model.AsignationDevolution;
import geniar.siscar.model.Brands;
import geniar.siscar.model.Cities;
import geniar.siscar.model.ClientsSalesVehicles;
import geniar.siscar.model.Colors;
import geniar.siscar.model.CostsCentersVehicles;
import geniar.siscar.model.Countries;
import geniar.siscar.model.Lines;
import geniar.siscar.model.Locations;
import geniar.siscar.model.LocationsTypes;
import geniar.siscar.model.ParValoresparametros;
import geniar.siscar.model.PrepaidConsumption;
import geniar.siscar.model.Requests;
import geniar.siscar.model.RetirementsReasons;
import geniar.siscar.model.SupplyingCatalogs;
import geniar.siscar.model.Users;
import geniar.siscar.model.VOAssignation;
import geniar.siscar.model.Vehicles;
import geniar.siscar.model.VehiclesAssignation;
import geniar.siscar.model.VehiclesStates;
import geniar.siscar.model.VehiclesTypes;
import geniar.siscar.persistence.EntityManagerHelper;
import geniar.siscar.persistence.LocationsTypesDAO;
import geniar.siscar.persistence.VehiclesDAO;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * The Class SearchVehicles.
 */
public class SearchVehicles {

	/** The Constant log. */
	private static final Log log = LogFactory.getLog(SearchVehicles.class);
	
	/** The Constant estNombre. */
	public static final String estNombre = "estNombre";
	
	/** The Constant mtrtNombre. */
	public static final String mtrtNombre = "mtrtNombre";
	
	/** The Constant vhcPlacaDiplomatica. */
	public static final String vhcPlacaDiplomatica = "vhcPlacaDiplomatica";
	
	/** The Constant vhcCodigo. */
	public static final String vhcCodigo = "vhcCodigo";
	
	/** The Constant asnCodigo. */
	public static final String asnCodigo = "asnCodigo";
	
	/** The Constant alvCodigo. */
	public static final String alvCodigo = "alvCodigo";
	
	/** The Constant rqsCodigo. */
	public static final String rqsCodigo = "rqsCodigo";
	
	/** The Constant rqcCodigo. */
	public static final String rqcCodigo = "rqcCodigo";
	
	/** The Constant vhaFechaTermina. */
	public static final String vhaFechaTermina = "vhaFechaTermina";
	
	/** The Constant vhaFechaEntrega. */
	public static final String vhaFechaEntrega = "vhaFechaEntrega";
	
	/** The Constant vhaFechaInicio. */
	public static final String vhaFechaInicio = "vhaFechaInicio";
	
	/** The Constant nombre. */
	public static final String nombre = "nombre";
	
	/** The Constant supCodigo. */
	public static final String supCodigo = "supCodigo";
	
	/** The Constant lnsId. */
	public static final String lnsId = "lnsId";
	
	/** The Constant supModelo. */
	public static final String supModelo = "supModelo";
	
	/** The Constant supNumCatalogo. */
	public static final String supNumCatalogo = "supNumCatalogo";
	
	/** The Constant vhsCodigo. */
	public static final String vhsCodigo = "vhsCodigo";
	
	/** The Constant vhcPlacaActivoFijo. */
	public static final String vhcPlacaActivoFijo = "vhcPlacaActivoFijo";
	
	/** The Constant vhcNumeroTl. */
	public static final String vhcNumeroTl = "vhcNumeroTl";
	
	/** The Constant ctsNombre. */
	public static final String ctsNombre = "ctsNombre";
	
	/** The Constant ctsId. */
	public static final String ctsId = "ctsId";
	
	/** The Constant lctCodigo. */
	public static final String lctCodigo = "lctCodigo";
	
	/** The Constant vhcRemplazaA. */
	public static final String vhcRemplazaA = "vhcRemplazaA";
	
	/** The Constant mdlId. */
	public static final String mdlId = "mdlId";
	
	/** The Constant cntId. */
	public static final String cntId = "cntId";
	
	/** The Constant brnId. */
	public static final String brnId = "brnId";
	
	/** The Constant vhtCodigo. */
	public static final String vhtCodigo = "vhtCodigo";
	
	/** The Constant assCodigo. */
	public static final String assCodigo = "assCodigo";
	
	/** The Constant astCodigo. */
	public static final String astCodigo = "astCodigo";
	
	/** The Constant lcnCodigo. */
	public static final String lcnCodigo = "lcnCodigo";
	
	/** The Constant clNombre. */
	public static final String clNombre = "clNombre";
	
	/** The Constant usrNombre. */
	public static final String usrNombre = "usrNombre";
	
	/** The Constant vhaFechaDev. */
	public static final String vhaFechaDev = "vhaFechaDev";
	
	/** The Constant OF. */
	private static final Long OF = 1L;
	
	/** The Constant OFS. */
	private static final Long OFS = 2L;
	
	/** The Constant PROGRAMAS. */
	private static final Long PROGRAMAS = 3L;
	
	/** The Constant PROYECTO. */
	private static final Long PROYECTO = 4L;

	/**
	 * Gets the entity manager.
	 *
	 * @return the entity manager
	 */
	private static EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Consultar aprovisionamiento.
	 *
	 * @param nombre the nombre
	 * @return the list
	 * @throws GWorkException the g work exception
	 */
	@SuppressWarnings("unchecked")
	public static List<Vehicles> consultarAprovisionamiento(String nombre)
			throws GWorkException {
		try {
			List<Vehicles> list = null;
			final String queryString = "select model from Vehicles model where model.vehiclesStates."
					+ estNombre + "= :propertyValue";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("propertyValue", nombre);
			list = (List<Vehicles>) query.getResultList();
			if (list != null && list.size() > 0)
				return list;

		} catch (RuntimeException re) {
			log.error("ConsultarAprovisionamiento", re);
		}
		return null;
	}

	/**
	 * Consultar motivo retiro por nombre.
	 *
	 * @param nombre the nombre
	 * @return the retirements reasons
	 * @throws GWorkException the g work exception
	 */
	public static RetirementsReasons ConsultarMotivoRetiroPorNombre(
			String nombre) throws GWorkException {
		try {
			RetirementsReasons reasons = null;
			final String queryString = "select model from RetirementsReasons model where model."
					+ mtrtNombre + "= :propertyValue";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("propertyValue", nombre);
			reasons = (RetirementsReasons) query.getSingleResult();
			if (reasons != null)
				return reasons;

		} catch (RuntimeException re) {
			log.error("consultarEntregaVehiculoPorIdVehiculo", re);
		}
		return null;
	}

	/**
	 * Consultar solicitud asignacion alquiler vehiculos por id.
	 *
	 * @param codigo the codigo
	 * @return the requests
	 * @throws GWorkException the g work exception
	 */
	public static Requests ConsultarSolicitudAsignacionAlquilerVehiculosPorId(
			String codigo) throws GWorkException {
		try {
			Requests requests = null;
			final String queryString = "select model from Requests model where model."
					+ rqsCodigo + "= :propertyValue";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("propertyValue", codigo);
			requests = (Requests) query.getSingleResult();
			if (requests != null)
				return requests;

		} catch (RuntimeException re) {
			log.error("ConsultarSolicitudAsignacionAlquilerVehiculos", re);
		}
		return null;
	}

	/**
	 * Consultar motivo retiro por placa vehiculo.
	 *
	 * @param placa the placa
	 * @return the vehicles
	 * @throws GWorkException the g work exception
	 */
	public static Vehicles ConsultarMotivoRetiroPorPlacaVehiculo(String placa)
			throws GWorkException {
		try {
			Vehicles vehicles = null;
			final String queryString = "select model from Vehicles model where model."
					+ vhcPlacaDiplomatica + "= :placa";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("placa", placa);
			vehicles = (Vehicles) query.getSingleResult();

			if (vehicles != null)
				return vehicles;

		} catch (RuntimeException re) {
			log.error("consultarEntregaVehiculoPorIdVehiculo", re);
		}
		return null;
	}

	/**
	 * Consultar entrega vehiculo por numero carne asignacion.
	 *
	 * @param numeroCarne the numero carne
	 * @return the vehicles assignation
	 * @throws GWorkException the g work exception
	 */
	public static VehiclesAssignation consultarEntregaVehiculoPorNumeroCarneAsignacion(
			Long numeroCarne) throws GWorkException {
		try {
			VehiclesAssignation vehiclesAssignation = null;
			final String queryString = "select model from DeliveryVehicles model where model.assignVehicles."
					+ asnCodigo + "= :propertyValue";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("propertyValue", numeroCarne);

			vehiclesAssignation = (VehiclesAssignation) query.getSingleResult();
			if (vehiclesAssignation != null)
				return vehiclesAssignation;

		} catch (RuntimeException re) {
			log.error("consultarEntregaVehiculoPorNumeroCarneAsignacion", re);
		}
		return null;
	}

	/**
	 * Consultar entrega vehiculo por numero carne alquiler.
	 *
	 * @param numeroCarne the numero carne
	 * @return the vehicles assignation
	 */
	public static VehiclesAssignation consultarEntregaVehiculoPorNumeroCarneAlquiler(
			Long numeroCarne) {
		try {
			final String queryString = "select model from VehiclesAssignation model where model.carsRents."
					+ alvCodigo + "= :propertyValue";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("propertyValue", numeroCarne);
			return (VehiclesAssignation) query.getSingleResult();
		} catch (RuntimeException re) {
			log.error("consultarEntregaVehiculoPorNumeroCarneAlquiler", re);
		}
		return null;
	}

	/**
	 * Metodo para realizar la consulta de una solicitud por medio de la clase
	 * el estado, las fechas inicial y final.
	 *
	 * @param idClaseSolicitud the id clase solicitud
	 * @param fechaIni the fecha ini
	 * @param fechaFin the fecha fin
	 * @param idEstadoSolicitud the id estado solicitud
	 * @return the list
	 */
	@SuppressWarnings("unchecked")
	public static List<Requests> ConsultarSolicitudAsignacionAlquilerVehiculos(
			Long idClaseSolicitud, Date fechaIni, Date fechaFin,
			Long idEstadoSolicitud) {
		try {
			final String queryString = "select model from Requests model where model.requestsClasses."
					+ rqcCodigo
					+ "= :idClaseSolicitud "
					+ " and model.rqsFechaInicial >= :fechaIni "
					+ " and model.rqsFechaFinal >= :fechaFin"
					+ " and model.requestsStates."
					+ "rqtCodigo "
					+ "= :idEstadoSolicitud";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("idClaseSolicitud", idClaseSolicitud);
			query.setParameter("fechaIni", fechaIni);
			query.setParameter("fechaFin", fechaFin);
			query.setParameter("idEstadoSolicitud", idEstadoSolicitud);
			return (List<Requests>) query.getResultList();
		} catch (RuntimeException re) {
			log.error("ConsultarSolicitudAsignacionAlquilerVehiculos", re);
		}
		return null;
	}

	/**
	 * Metodo para realizar la consulta del parametros el estado, las fechas
	 * inicial y final.
	 *
	 * @param idCountry the id country
	 * @return the list
	 * @throws GWorkException the g work exception
	 */
	@SuppressWarnings("unchecked")
	public static List<Cities> ConsultarUbicacionCiudad(Long idCountry)
			throws GWorkException {
		try {
			Query query = null;
			if (idCountry != null) {
				final String queryString = "select city from Cities city "
						+ "where city.countries.cntId = :idparam ORDER BY  city.ctsNombre ASC";
				query = EntityManagerHelper.getEntityManager().createQuery(
						queryString);
				query.setParameter("idparam", idCountry);
				return (List<Cities>) query.getResultList();
			}

		} catch (RuntimeException re) {
			log.error("ConsultarUbicacionCiudad", re);
		}
		return null;
	}

	/**
	 * Metodo para realizar la consulta del parametros el estado, las fechas
	 * inicial y final.
	 *
	 * @param idparametro the idparametro
	 * @return the int
	 * @throws GWorkException the g work exception
	 */
	@SuppressWarnings("unchecked")
	public static int ConsultarParametroDiasTerminacion(Long idparametro)
			throws GWorkException {
		try {
			ParValoresparametros parValoresparametros = null;
			int valor = 0;
			final String queryString = "select valores from ParValoresparametros valores "
					+ "inner join valores.parParametros parametro "
					+ "where parametro.idparametro = :idparam and valores.fechafin is null";
			Query query = EntityManagerHelper.getEntityManager().createQuery(
					queryString);
			query.setParameter("idparam", idparametro);
			List<ParValoresparametros> valoresParametros = (List<ParValoresparametros>) query
					.getResultList();

			if (valoresParametros != null && valoresParametros.size() > 0) {
				parValoresparametros = valoresParametros.get(0);
				valor = parValoresparametros.getValorinicial().intValue();
			}

			if (valor != 0)
				return valor;

		} catch (RuntimeException re) {
			log.error("ConsultarParametroDiasTerminacionAsignacion", re);
		}
		return 0;
	}

	/* Obtener parámetro del autoseguro */
	/**
	 * Consultar parametro auto seguro.
	 *
	 * @param idparametro the idparametro
	 * @return the int
	 * @throws GWorkException the g work exception
	 */
	@SuppressWarnings("unchecked")
	public static int ConsultarParametroAutoSeguro(Long idparametro)
			throws GWorkException {
		try {
			ParValoresparametros parValoresparametros = null;
			int valor = 0;
			final String queryString = "select valores from ParValoresparametros valores "
					+ "inner join valores.parParametros parametro "
					+ "where parametro.idparametro = :idparam and valores.fechafin is null";
			Query query = EntityManagerHelper.getEntityManager().createQuery(
					queryString);
			query.setParameter("idparam", idparametro);
			List<ParValoresparametros> valoresParametros = (List<ParValoresparametros>) query
					.getResultList();

			if (valoresParametros != null && valoresParametros.size() > 0) {
				parValoresparametros = valoresParametros.get(0);
				valor = parValoresparametros.getValorinicial().intValue();
			}

			if (valor != 0)
				return valor;

		} catch (RuntimeException re) {
			log.error("ConsultarParametroDiasTerminacionAsignacion", re);
		}
		return 0;
	}

	/**
	 * Metodo para realizar la consulta de las fechas de terminación de una
	 * asignación.
	 *
	 * @return the list
	 * @throws GWorkException the g work exception
	 */
	@SuppressWarnings("unchecked")
	public static List<VehiclesAssignation> ConsultarFechasTerminacionAsignacion()
			throws GWorkException {
		try {

			String queryString = "select * from Vehicles_Assignation vha "
					+ "where cast((vha.vha_Fecha_Termina - sysdate)as number(30))  <= :diasTerminacion "
					+ "and vha.vha_Fecha_Dev is null "
					+ "and vha.vha_Fecha_Entrega is not null "
					+ "and vha.ast_codigo = 1 " + " and vha.ass_codigo = 5 "
					+ "order by  vha.vha_Fecha_Termina desc ";

			Query query = getEntityManager().createNativeQuery(queryString,
					VehiclesAssignation.class);
			query.setParameter("diasTerminacion", Util
					.loadParametersValue("dias.terminacion.asignacion"));

			if (query.getResultList() != null
					&& query.getResultList().size() > 0) {
				return (List<VehiclesAssignation>) query.getResultList();
			}

		} catch (RuntimeException re) {
			log.error("ConsultarSolicitudAsignacionVehiculos", re);
		}
		return null;
	}

	/**
	 * Metodo para realizar la consulta de las fechas de terminación de una
	 * asignación.
	 *
	 * @return the list
	 * @throws GWorkException the g work exception
	 */
	@SuppressWarnings("unchecked")
	public static List<VehiclesAssignation> ConsultarFechasTerminacionAlquiler()
			throws GWorkException {
		try {

			String queryString = "select * from Vehicles_Assignation vha "
					+ "where vha.vha_Fecha_Termina < sysdate "
					+ "and vha.vha_Fecha_Dev is  null  "
					+ "and vha.vha_Fecha_Entrega is not null "
					+ "and vha.ast_codigo = 3 " + "and vha.ass_codigo = 5 "
					+ "order by  vha.vha_Fecha_Termina desc";

			Query query = getEntityManager().createNativeQuery(queryString,
					VehiclesAssignation.class);

			if (query.getResultList() != null
					&& query.getResultList().size() > 0) {
				return (List<VehiclesAssignation>) query.getResultList();

			}

		} catch (RuntimeException re) {
			log.error("ConsultarSolicitudAsignacionAlquilerVehiculos", re);
		}
		return null;
	}

	/**
	 * Metodo para realizar la consulta de las reservas proximas a hacerse
	 * efectivas.
	 *
	 * @return the list
	 * @throws GWorkException the g work exception
	 */
	@SuppressWarnings("unchecked")
	public static List<VehiclesAssignation> ConsultarReservasVehiculosProximas()
			throws GWorkException {
		try {
			String queryString = "SELECT * FROM vehicles_assignation vha "
					+ "WHERE CAST((vha.vha_fecha_inicio - sysdate) AS NUMBER(30)) <= :diasTerminacion  "
					+ "AND vha.ass_codigo = 3  " + "AND vha.ast_codigo = 2";

			Query query = getEntityManager().createNativeQuery(queryString,
					VehiclesAssignation.class);
			query.setParameter("diasTerminacion", Util
					.loadParametersValue("dias.reservas.proximas"));

			if (query.getResultList() != null
					&& query.getResultList().size() > 0) {
				return (List<VehiclesAssignation>) query.getResultList();
			}

		} catch (RuntimeException re) {
			log.error("ConsultarReservasVehiculosProximas: ", re);
		}
		return null;
	}

	/**
	 * Metodo para realizar la consulta de los vehiculos reservados no recogidos.
	 *
	 * @return the list
	 * @throws GWorkException the g work exception
	 */
	@SuppressWarnings("unchecked")
	public static List<VehiclesAssignation> ConsultarVehiculosReservadosNoRecogidos()
			throws GWorkException {
		try {

			final String queryString = "select * from Vehicles_Assignation vha "
					+ "where vha.vha_Fecha_Inicio < sysdate "
					+ "and vha.vha_Fecha_Entrega is null "
					+ "and vha.ast_codigo in (1,3) "
					+ "and vha.ass_codigo in (2,4) "
					+ "order by  vha.vha_Fecha_Inicio desc";

			// Estado de la asignacion o reservado
			Query query = getEntityManager().createNativeQuery(queryString,
					VehiclesAssignation.class);

			if (query.getResultList() != null
					&& query.getResultList().size() > 0) {
				return query.getResultList();
			}

		} catch (RuntimeException re) {
			log
					.error(
							"ConsultarNotificacionVehiculosReservadosNoRecogidos",
							re);
		}
		return null;
	}

	/**
	 * Metodo para validar que no se repitan los registros de los catalogos
	 * inicial y final.
	 *
	 * @param numCatalogo the num catalogo
	 * @return true, if successful
	 * @throws GWorkException the g work exception
	 */
	public static boolean validarParametrosIngresoAprovisionamiento(
			String numCatalogo) throws GWorkException {
		try {
			final String queryString = "select model from SupplyingCatalogs model "
					+ "where and model.supNumCatalogo " + " = :catalogo";
			Query query = EntityManagerHelper.getEntityManager().createQuery(
					queryString);
			query.setParameter("catalogo", numCatalogo);

			if (query.getResultList() != null
					&& query.getResultList().size() > 0)
				return false;

		} catch (RuntimeException re) {
			log.error("validarParametrosIngresoAprovisionamiento", re);
		}
		return true;
	}

	/**
	 * Consultar catalogo por numero.
	 *
	 * @param valor the valor
	 * @return the list
	 * @throws GWorkException the g work exception
	 */
	@SuppressWarnings("unchecked")
	public static List<SupplyingCatalogs> consultarCatalogoPorNumero(
			String valor) throws GWorkException {
		try {
			List<SupplyingCatalogs> listCatalogs = null;
			final String queryString = "select model from SupplyingCatalogs model where model."
					+ supNumCatalogo + "= :numCatalogo";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("numCatalogo", valor);
			listCatalogs = (List<SupplyingCatalogs>) query.getResultList();
			if (listCatalogs != null && listCatalogs.size() > 0)
				return listCatalogs;

		} catch (RuntimeException re) {
			log.error("consultarCatalogoPorNumero", re);
		}
		return null;
	}

	/**
	 * Consultar catalogo.
	 *
	 * @param valor the valor
	 * @return the supplying catalogs
	 * @throws GWorkException the g work exception
	 */
	public static SupplyingCatalogs consultarCatalogo(Long valor)
			throws GWorkException {
		try {
			SupplyingCatalogs catalogs = null;
			final String queryString = "select model from SupplyingCatalogs model where model."
					+ supCodigo
					+ "= :idCatalogo ORDER BY model.supNumCatalogo ASC";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("idCatalogo", valor);
			catalogs = (SupplyingCatalogs) query.getSingleResult();
			if (catalogs != null)
				return catalogs;

		} catch (RuntimeException re) {
			log.error("consultarLineaCatalogo", re);
		}
		return null;
	}

	/**
	 * Consultar datos vehiculo asignacion.
	 *
	 * @param placa the placa
	 * @return the vehicles assignation
	 * @throws GWorkException the g work exception
	 */
	@SuppressWarnings("unchecked")
	public static VehiclesAssignation consultarDatosVehiculoAsignacion(
			String placa) throws GWorkException {
		try {
			Long estadoEntrega = new Long(Util.loadMessageValue("ESTADO.5"));

			List<VehiclesAssignation> listVehiclesAssignation = null;
			final String queryString = "select model from VehiclesAssignation model where model.vehicles."
					+ vhcPlacaDiplomatica + "= :placaVehiculo";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("placaVehiculo", placa);

			listVehiclesAssignation = (List<VehiclesAssignation>) query
					.getResultList();

			if (listVehiclesAssignation != null
					&& listVehiclesAssignation.size() > 0)
				for (VehiclesAssignation vehiclesAssignation2 : listVehiclesAssignation) {
					if (estadoEntrega.equals(vehiclesAssignation2
							.getAssignationsStates().getAssCodigo())
							&& vehiclesAssignation2.getVhaFechaDev() == null) {
						return listVehiclesAssignation.get(0);
					}
				}
		} catch (RuntimeException re) {
			log.error("consultarDatosVehiculoAsignacion", re);
		}
		return null;
	}

	/**
	 * Consultar datos vehiculo asignacion por placa.
	 *
	 * @param placa the placa
	 * @return the list
	 * @throws GWorkException the g work exception
	 */
	@SuppressWarnings("unchecked")
	public static List<VehiclesAssignation> consultarDatosVehiculoAsignacionPorPlaca(
			String placa) throws GWorkException {
		try {

			List<VehiclesAssignation> listVehiclesAssignation = null;
			final String queryString = "select model from VehiclesAssignation model where model.vehicles."
					+ vhcPlacaDiplomatica
					+ "= :placaVehiculo order by model.vhaFechaInicio DESC";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("placaVehiculo", placa);

			listVehiclesAssignation = (List<VehiclesAssignation>) query
					.getResultList();

			if (listVehiclesAssignation != null
					&& listVehiclesAssignation.size() > 0) {
				return listVehiclesAssignation;
			}
		} catch (RuntimeException re) {
			log.error("consultarDatosVehiculoAsignacion", re);
		}
		return null;
	}

	/**
	 * Consultar estado vehiculo.
	 *
	 * @param placa the placa
	 * @return the string
	 * @throws GWorkException the g work exception
	 */
	public static String consultarEstadoVehiculo(String placa)
			throws GWorkException {
		try {
			Vehicles vehicles = null;
			String estado = null;
			final String queryString = "select model from Vehicles model where model."
					+ vhcPlacaDiplomatica + " = :placaVehiculo";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("placaVehiculo", placa);

			vehicles = (Vehicles) query.getSingleResult();

			if (vehicles != null)
				estado = vehicles.getVehiclesStates().getVhsCodigo().toString();

			return estado;
		} catch (RuntimeException re) {
			log.error("consultarEstadoVehiculo", re);
		}
		return null;
	}

	/**
	 * Consultar vehiculos reemplazo placa diplomatica.
	 *
	 * @param placa the placa
	 * @param placaDiplomatica the placa diplomatica
	 * @throws GWorkException the g work exception
	 */
	@SuppressWarnings("unchecked")
	public static void consultarVehiculosReemplazoPlacaDiplomatica(
			String placa, String placaDiplomatica) throws GWorkException {
		try {
			List<Vehicles> listVehicles = null;
			Vehicles vehicles = null;
			VehiclesStates vehiclesStates = null;
			String estado = Util.loadMessageValue("ESTADO.5");
			final String queryString = "select model from Vehicles model where model."
					+ vhcPlacaDiplomatica + " = :placaVehiculo";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("placaVehiculo", placa);

			listVehicles = (List<Vehicles>) query.getResultList();

			if (listVehicles != null && listVehicles.size() > 0) {
				vehicles = listVehicles.get(0);
				vehiclesStates = vehicles.getVehiclesStates();

				if (!vehiclesStates.getVhsCodigo().equals(new Long(estado)))
					throw new GWorkException(Util
							.loadErrorMessageValue("VEHICULO.NORETIRADO"));

				if (!consultarEstadoVehiculo(placa, placaDiplomatica)) {
					if (consultarVehiculosReemplazo(placa))
						throw new GWorkException(
								Util
										.loadErrorMessageValue("PLACA.REEMPLAZO.EXISTE"));
				}
			} else
				throw new GWorkException(Util
						.loadErrorMessageValue("PLACA.ANTERIOR.NOEXISTE"));
		} catch (GWorkException re) {
			log.error("consultarEstadoVehiculoPorPlaca", re);
			throw new GWorkException(re.getMessage());
		}
	}

	/**
	 * Consultar estado vehiculo.
	 *
	 * @param placa the placa
	 * @param placaDiplomatica the placa diplomatica
	 * @return true, if successful
	 * @throws GWorkException the g work exception
	 */
	@SuppressWarnings("unchecked")
	public static boolean consultarEstadoVehiculo(String placa,
			String placaDiplomatica) throws GWorkException {
		try {
			List<Vehicles> listVehicles = null;

			final String queryString = "select model from Vehicles model where model."
					+ vhcRemplazaA
					+ " = :placaVehiculo and model."
					+ vhcPlacaDiplomatica + "= :placaDiplomatica";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("placaVehiculo", placa);
			query.setParameter("placaDiplomatica", placaDiplomatica);

			listVehicles = (List<Vehicles>) query.getResultList();

			if (listVehicles != null && listVehicles.size() > 0)
				return true;

		} catch (RuntimeException re) {
			log.error("consultarEstadoVehiculoPorPlaca", re);
		}
		return false;
	}

	/**
	 * Consultar vehiculos reemplazo.
	 *
	 * @param placa the placa
	 * @return true, if successful
	 * @throws GWorkException the g work exception
	 */
	@SuppressWarnings("unchecked")
	public static boolean consultarVehiculosReemplazo(String placa)
			throws GWorkException {
		try {
			List<Vehicles> listVehicles = null;

			final String queryString = "select model from Vehicles model where model."
					+ vhcRemplazaA + " = :placaVehiculo";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("placaVehiculo", placa);

			listVehicles = (List<Vehicles>) query.getResultList();

			if (listVehicles != null && listVehicles.size() > 0)
				return false;//15 de marzo 2012.carlos.guzman. se modifico porque el usuario 
			//presentaba problemas al agregar placas anteriores. return true

		} catch (RuntimeException re) {
			log.error("consultarEstadoVehiculoPorPlaca", re);
		}
		return false;
	}

	/**
	 * Consultar vehiculos no retirados.
	 *
	 * @param placa the placa
	 * @return the list
	 * @throws GWorkException the g work exception
	 */
	public static List<Vehicles> consultarVehiculosNoRetirados(String placa)
			throws GWorkException {

		String estadoPool = Util.loadMessageValue("ESTADO.6");
		String estadoRetirado = Util.loadMessageValue("ESTADO.5");
		String estadoAlquilado = Util.loadMessageValue("ESTADO.7");

		if (consultarEstadoVehiculo(placa).equalsIgnoreCase(estadoAlquilado))
			throw new GWorkException(Util
					.loadErrorMessageValue("VEHICULO.ALQUILADO"));

		if (consultarEstadoVehiculo(placa).equalsIgnoreCase(estadoRetirado))
			throw new GWorkException(Util
					.loadErrorMessageValue("VEHICULO.RETIRADO"));

		if (!consultarEstadoVehiculo(placa).equalsIgnoreCase(estadoPool))
			throw new GWorkException(Util
					.loadErrorMessageValue("VEHICULO.NOPOOL"));

		if (consultarDatosVehiculoAsignacion(placa) != null)
			throw new GWorkException(Util
					.loadErrorMessageValue("VEHICULO.NOASIGNADO"));

		return null;
	}

	/**
	 * Consultar estado asignacion.
	 *
	 * @param idEstado the id estado
	 * @return the int
	 */
	public static int consultarEstadoAsignacion(Long idEstado) {
		try {
			VehiclesStates vehiclesStates = null;
			Long estadoAsignacion = new Long(Util.loadMessageValue("ESTADO.1"));
			Long estadoAlquiler = new Long(Util.loadMessageValue("ESTADO.7"));
			final String queryString = "select model from VehiclesStates model where model."
					+ vhsCodigo + "= :codigoEstado";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("codigoEstado", idEstado);

			vehiclesStates = (VehiclesStates) query.getSingleResult();

			if (vehiclesStates != null) {
				if (vehiclesStates.getVhsCodigo().equals(estadoAsignacion))
					return 1;
				else if (vehiclesStates.getVhsCodigo().equals(estadoAlquiler))
					return 0;
			}
		} catch (Exception e) {
			log.error("consultarEstadoAsignacion");
		}
		return -1;
	}

	/**
	 * Consultar datos ubicacion por id ciudad.
	 *
	 * @param idCiudad the id ciudad
	 * @param idTipoUbicacion the id tipo ubicacion
	 * @return the locations
	 * @throws GWorkException the g work exception
	 */
	@SuppressWarnings("unchecked")
	public static Locations consultarDatosUbicacionPorIdCiudad(Long idCiudad,
			Long idTipoUbicacion) throws GWorkException {
		List<Locations> listLocations = null;
		final String queryString = "select model from Locations model where model.cities."
				+ ctsId
				+ "= :idCiudad and model.locationsTypes."
				+ lctCodigo
				+ "= :lctCodigo";
		Query query = getEntityManager().createQuery(queryString);
		query.setParameter("idCiudad", idCiudad);
		query.setParameter("lctCodigo", idTipoUbicacion);
		listLocations = (List<Locations>) query.getResultList();

		if (listLocations != null && !listLocations.isEmpty())
			return listLocations.get(0);
		return null;
	}

	/**
	 * Consultar datos ubicacion por tipo ubicacion.
	 *
	 * @param idTipoUbicacion the id tipo ubicacion
	 * @return the locations
	 * @throws GWorkException the g work exception
	 */
	@SuppressWarnings("unchecked")
	public static Locations consultarDatosUbicacionPorTipoUbicacion(
			Long idTipoUbicacion) throws GWorkException {
		try {
			List<Locations> listLocations = null;
			final String queryString = "select model from Locations model where model.locationsTypes."
					+ lctCodigo + "= :idTipo";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("idTipo", idTipoUbicacion);
			listLocations = (List<Locations>) query.getResultList();

			if (listLocations != null && !listLocations.isEmpty())
				return listLocations.get(0);

		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Consultar datos ubicacion por id ciudad.
	 *
	 * @param idCiudad the id ciudad
	 * @return the locations
	 * @throws GWorkException the g work exception
	 */
	@SuppressWarnings("unchecked")
	public static Locations consultarDatosUbicacionPorIdCiudad(Long idCiudad)
			throws GWorkException {
		try {
			List<Locations> listLocations = null;
			final String queryString = "select model from Locations model where model.cities."
					+ ctsId + "= :idCiudad";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("idCiudad", idCiudad);
			listLocations = (List<Locations>) query.getResultList();

			if (listLocations != null && !listLocations.isEmpty()) {
				return listLocations.get(0);
			}

		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Consultar datos ubicacion por id pais.
	 *
	 * @param idPais the id pais
	 * @return the countries
	 * @throws GWorkException the g work exception
	 */
	@SuppressWarnings("unchecked")
	public static Countries consultarDatosUbicacionPorIdPais(Long idPais)
			throws GWorkException {
		try {
			List<Countries> listCountries = null;
			final String queryString = "select model from Countries model where model."
					+ cntId + "= :idPais";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("idPais", idPais);
			listCountries = (List<Countries>) query.getResultList();

			if (listCountries != null && !listCountries.isEmpty())
				return listCountries.get(0);

		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Consultar todos paises.
	 *
	 * @return the list
	 * @throws GWorkException the g work exception
	 */
	@SuppressWarnings("unchecked")
	public static List<Countries> consultarTodosPaises() throws GWorkException {
		try {
			List<Countries> listCountries = null;
			final String queryString = "select model from Countries model ORDER BY model.cntNombre ASC";
			Query query = getEntityManager().createQuery(queryString);
			listCountries = (List<Countries>) query.getResultList();

			if (listCountries != null && !listCountries.isEmpty())
				return listCountries;

		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}

	/*
	 * Metodo para consultar los paises segun el tipo de ubicacion
	 */
	/**
	 * Consultar ubicacion.
	 *
	 * @param idTipoUbicacion the id tipo ubicacion
	 * @return the list
	 * @throws GWorkException the g work exception
	 */
	public static List<Countries> consultarUbicacion(Long idTipoUbicacion)
			throws GWorkException {
		try {

			String tipo1 = Util.loadMessageValue("TIPOUBICACION.SEDE");
			String tipo2 = Util.loadMessageValue("TIPOUBICACION.ESTACIONES");
			String tipo3 = Util.loadMessageValue("TIPOUBICACION.EXTERIORES");
			String codigoColommbia = Util.loadMessageValue("CODIGO.COLOMBIA");
			List<Countries> listCountries = new ArrayList<Countries>();
			Countries countries = null;
			LocationsTypes locationsTypes = new LocationsTypesDAO()
					.findById(idTipoUbicacion);
			if (locationsTypes != null) {

				if (locationsTypes.getLctCodigo().toString().equalsIgnoreCase(
						tipo1)
						|| locationsTypes.getLctCodigo().toString()
								.equalsIgnoreCase(tipo2)) {
					countries = SearchVehicles
							.consultarDatosUbicacionPorIdPais(new Long(
									codigoColommbia));
					if (countries != null)
						listCountries.add(countries);
					// return listCountries;
					return listCountries;
				} else if (locationsTypes.getLctCodigo().toString()
						.equalsIgnoreCase(tipo3)) {

					List<Countries> list = consultarTodosPaises();
					// for (Countries countries2 : list) {
					// listCountries.add(countries2);
					// }
					return list;
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Consultar catalogo por id modelo.
	 *
	 * @param idModelo the id modelo
	 * @return the list
	 * @throws GWorkException the g work exception
	 */
	@SuppressWarnings("unchecked")
	public static List<SupplyingCatalogs> consultarCatalogoPorIdModelo(
			Long idModelo) throws GWorkException {
		try {

			List<SupplyingCatalogs> listCountries = null;
			final String queryString = "select model from SupplyingCatalogs model where model.models."
					+ mdlId + "= :idModelo ORDER BY model.supNumCatalogo ASC";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("idModelo", idModelo);
			listCountries = (List<SupplyingCatalogs>) query.getResultList();

			if (listCountries != null && !listCountries.isEmpty())
				return listCountries;

		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Consultar ubicacion por id tipo.
	 *
	 * @param idLocations the id locations
	 * @return the list
	 * @throws GWorkException the g work exception
	 */
	@SuppressWarnings("unchecked")
	public static List<Locations> consultarUbicacionPorIdTipo(Long idLocations)
			throws GWorkException {
		try {
			List<Locations> listLocations = null;
			final String queryString = "select model from Locations model where model.locationsTypes."
					+ lctCodigo
					+ "= :idLocations ORDER BY model.cities."
					+ ctsNombre + " ASC";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("idLocations", idLocations);
			listLocations = (List<Locations>) query.getResultList();

			if (listLocations != null && !listLocations.isEmpty())
				return listLocations;

		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Consultar lineas de marca.
	 *
	 * @param idMarca the id marca
	 * @return the list
	 * @throws GWorkException the g work exception
	 */
	@SuppressWarnings("unchecked")
	public static List<Lines> consultarLineasDeMarca(Long idMarca)
			throws GWorkException {
		try {

			List<Lines> listLines = null;
			final String queryString = "select model from Lines model where model.brands."
					+ brnId + "= :idModelo ORDER BY model.lnsNombre ASC";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("idModelo", new Long(idMarca));
			listLines = (List<Lines>) query.getResultList();

			return listLines;

		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Consultar vehiculos por tipo vehiculo.
	 *
	 * @param idType the id type
	 * @return the list
	 * @throws GWorkException the g work exception
	 */
	@SuppressWarnings("unchecked")
	public static List<Vehicles> consultarVehiculosPorTipoVehiculo(Long idType)
			throws GWorkException {
		try {
			final String queryString = "select model from Vehicles model where model.vehiclesTypes."
					+ vhtCodigo
					+ "= :idType and model.vehiclesStates."
					+ vhsCodigo
					+ "= :estadoPool or model.vehiclesStates."
					+ vhsCodigo
					+ "= :estadoAlquiler and model.vehiclesTypes."
					+ vhtCodigo
					+ "= :idType ORDER BY model.vehiclesTypes.vhtNombre ASC ";

			Long estadoPool = new Long(Util.loadMessageValue("ESTADO.6"));
			Long estadoAlquiler = new Long(Util.loadMessageValue("ESTADO.7"));

			List<Vehicles> listVehicles = null;

			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("idType", idType);
			query.setParameter("estadoPool", estadoPool);
			query.setParameter("estadoAlquiler", estadoAlquiler);
			listVehicles = (List<Vehicles>) query.getResultList();

			if (listVehicles != null && !listVehicles.isEmpty())
				return listVehicles;

		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Consultar tipos de vehiculos.
	 *
	 * @return the list
	 * @throws GWorkException the g work exception
	 */
	@SuppressWarnings("unchecked")
	public static List<VehiclesTypes> consultarTiposDeVehiculos()
			throws GWorkException {
		try {

			List<VehiclesTypes> listVehiclesTypes = null;
			final String queryString = "select model from VehiclesTypes model ORDER BY model.vhtCodigo ASC";
			Query query = getEntityManager().createQuery(queryString);

			listVehiclesTypes = (List<VehiclesTypes>) query.getResultList();

			return listVehiclesTypes;

		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Consultar tipo vehiculos.
	 *
	 * @param nombreTipoVHC the nombre tipo vhc
	 * @return the vehicles types
	 * @throws GWorkException the g work exception
	 */
	@SuppressWarnings("unchecked")
	public static VehiclesTypes consultarTipoVehiculos(String nombreTipoVHC)
			throws GWorkException {
		try {

			List<VehiclesTypes> listVehiclesTypes = null;
			final String queryString = "select model from VehiclesTypes model where model.vhtNombre='"
					+ nombreTipoVHC + "'";
			Query query = getEntityManager().createQuery(queryString);

			listVehiclesTypes = (List<VehiclesTypes>) query.getResultList();

			if (listVehiclesTypes != null && !listVehiclesTypes.isEmpty()) {
				return listVehiclesTypes.get(0);
			}

		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Consultar historial vehiculos.
	 *
	 * @param idVehiculo the id vehiculo
	 * @param tipoVehiculo the tipo vehiculo
	 * @return the list
	 */
	@SuppressWarnings("unchecked")
	public static List<VehiclesAssignation> consultarHistorialVehiculos(
			Long idVehiculo, Long tipoVehiculo) {

		StringBuffer buffer = new StringBuffer();
		String placaTemp = null;
		List<VehiclesAssignation> listVehiclesAsignation = null;
		List<VehiclesAssignation> listVehiclesAsignationTemp = null;
		HashMap<String, List<VehiclesAssignation>> listSort = new HashMap<String, List<VehiclesAssignation>>();
		try {
			buffer
					.append("select model from VehiclesAssignation model where model."
							+ vhaFechaDev + " is null ");

			if (tipoVehiculo != null)
				buffer
						.append(" and model.vehicles.vehiclesTypes.vhtCodigo = :tipoVehiculo");

			if (idVehiculo != null && tipoVehiculo == null)
				buffer.append(" and ");

			if (idVehiculo != null && tipoVehiculo != null)
				buffer.append(" and ");

			if (idVehiculo != null)
				buffer.append(" model.vehicles.vhcCodigo = :idVehiculo and ");
			else
				buffer.append(" and ");
			buffer.append("model." + vhaFechaTermina + " >= :fechaActual"
					+ " ORDER BY model.vehicles.vhcCodigo ASC ,model."
					+ vhaFechaInicio + " ASC");

			System.out.println("Consulta vehiculos asignados:\n"
					+ buffer.toString());
			Query query = getEntityManager().createQuery(buffer.toString());
			query.setParameter("fechaActual", new Date());

			if (idVehiculo != null)
				query.setParameter("idVehiculo", idVehiculo);

			if (tipoVehiculo != null)
				query.setParameter("tipoVehiculo", tipoVehiculo);

			listVehiclesAsignation = (List<VehiclesAssignation>) query
					.getResultList();
			for (int i = 0; i < listVehiclesAsignation.size(); i++) {

				VehiclesAssignation vehiclesAssignation = listVehiclesAsignation
						.get(i);

				String strTemp = listVehiclesAsignation.get(i).getVehicles()
						.getVhcPlacaDiplomatica();

				listVehiclesAsignationTemp = new ArrayList<VehiclesAssignation>();
				for (int j = 0; j < listVehiclesAsignation.size(); j++) {
					placaTemp = listVehiclesAsignation.get(j).getVehicles()
							.getVhcPlacaDiplomatica();
					if (strTemp.equalsIgnoreCase(placaTemp))
						listVehiclesAsignationTemp.add(vehiclesAssignation);
				}
				if (listVehiclesAsignationTemp != null
						&& listVehiclesAsignationTemp.size() > 0) {
					listSort.put(strTemp, listVehiclesAsignationTemp);
					placaTemp = strTemp;
				}
			}
			return listVehiclesAsignation;

		} catch (Exception e) {
			log.error(e.getMessage());
		}

		return null;
	}

	/**
	 * Consultar vehiculos por estados tipo vehiculo.
	 *
	 * @param pagina the pagina
	 * @return the list
	 * @throws GWorkException the g work exception
	 */
	@SuppressWarnings("unchecked")
	public static List<Vehicles> consultarVehiculosPorEstadosTipoVehiculo(
			boolean pagina) throws GWorkException {
		try {
			List<VehiclesTypes> list = consultarTiposDeVehiculos();
			if (list != null && list.size() > 0) {
				int numero = list.size();

				StringBuffer buffer = new StringBuffer();
				buffer.append("select model from Vehicles model where ");
				buffer.append("model.vehiclesTypes." + vhtCodigo
						+ "= :estadoTipo1 and model.vehiclesStates."
						+ vhsCodigo + " = :estadoPool ");

				for (int i = 1; i < numero; i++)
					buffer.append("or model.vehiclesTypes." + vhtCodigo
							+ "= :estadoTipo" + list.get(i).getVhtCodigo()
							+ " and model.vehiclesStates." + vhsCodigo
							+ "= :estadoPool ");

				if (pagina) {
					for (int i = 1; i < numero; i++)
						buffer.append("or model.vehiclesTypes." + vhtCodigo
								+ "= :estadoTipo" + list.get(i).getVhtCodigo()
								+ " and model.vehiclesStates." + vhsCodigo
								+ "= :estadoAlquiler ");
				}

				buffer.append("ORDER BY model.vhcCodigo ASC");

				Long estadoPool = new Long(Util.loadMessageValue("ESTADO.6"));
				Long estadoAlquiler = new Long(Util
						.loadMessageValue("ESTADO.7"));

				List<Vehicles> listVehicles = null;

				Query query = getEntityManager().createQuery(buffer.toString());
				long num = 0;
				for (int i = 0; i < list.size(); i++) {
					num = list.get(i).getVhtCodigo();
					query.setParameter("estadoTipo" + num, num);
				}

				query.setParameter("estadoPool", estadoPool);

				if (pagina)
					query.setParameter("estadoAlquiler", estadoAlquiler);
				listVehicles = (List<Vehicles>) query.getResultList();

				if (listVehicles != null && !listVehicles.isEmpty())
					return listVehicles;
			}

		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Consultar vehiculos por placa.
	 *
	 * @param placa the placa
	 * @return the vehicles
	 * @throws GWorkException the g work exception
	 */
	@SuppressWarnings("unchecked")
	public static Vehicles consultarVehiculosPorPlaca(String placa)
			throws GWorkException {
		try {

			Long estadoPool = new Long(Util.loadMessageValue("ESTADO.6"));
			Long estadoAlquiler = new Long(Util.loadMessageValue("ESTADO.7"));

			StringBuffer buffer = new StringBuffer();
			buffer.append("select model from Vehicles model where model."
					+ vhcPlacaDiplomatica + "= :placa ");

			List<Vehicles> listVehicles = null;
			Vehicles vehicles = null;
			Query query = getEntityManager().createQuery(buffer.toString());
			query.setParameter("placa", placa);
			listVehicles = (List<Vehicles>) query.getResultList();

			if (listVehicles != null && !listVehicles.isEmpty()) {

				vehicles = listVehicles.get(0);

				if (vehicles.getVehiclesStates().getVhsCodigo().equals(
						estadoPool)
						|| vehicles.getVehiclesStates().getVhsCodigo().equals(
								estadoAlquiler))
					return vehicles;

				return null;
			}
			return null;

		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Consultar vehiculos por placa sin filtros.
	 *
	 * @param placa the placa
	 * @return the vehicles
	 * @throws GWorkException the g work exception
	 */
	@SuppressWarnings("unchecked")
	public static Vehicles consultarVehiculosPorPlacaSinFiltros(String placa)
			throws GWorkException {
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("select model from Vehicles model where model."
					+ vhcPlacaDiplomatica + "= :placa ");

			List<Vehicles> listVehicles = null;

			Query query = EntityManagerHelper.getEntityManager().createQuery(
					buffer.toString());
			query.setParameter("placa", placa);
			listVehicles = (List<Vehicles>) query.getResultList();
			listVehicles.size();

			if (listVehicles != null && !listVehicles.isEmpty()) {
				return listVehicles.get(listVehicles.size()-1);
			}
			return null;

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GWorkException(Util
					.loadErrorMessageValue("search.not.found"), e);
		}
	}

	/**
	 * Consultar vehiculos por placa y estado.
	 *
	 * @param placa the placa
	 * @param estado
	 * @return the vehicles
	 * @throws GWorkException the g work exception
	 */
	@SuppressWarnings("unchecked")
	public static Vehicles consultarVehiculosPorPlacaYEstado(String placa, Long estado)
			throws GWorkException {

		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("select model from Vehicles model where model."
					+ vhcPlacaDiplomatica + "= :placa " 
					+ " and model.vehiclesStates.vhsCodigo != :estado");

			List<Vehicles> listVehicles = null;

			Query query = EntityManagerHelper.getEntityManager().createQuery(
					buffer.toString());
			query.setParameter("placa", placa);
			query.setParameter("estado", estado);
			listVehicles = (List<Vehicles>) query.getResultList();
			listVehicles.size();

			if (listVehicles != null && !listVehicles.isEmpty()) {
				return listVehicles.get(listVehicles.size()-1);
			}
			return null;

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GWorkException(Util
					.loadErrorMessageValue("search.not.found"), e);
		}
	}

	
	/**
	 * Consultar vehiculos disponibles entrega.
	 *
	 * @param opcAsignacion the opc asignacion
	 * @return the list
	 * @throws GWorkException the g work exception
	 */
	@SuppressWarnings("unchecked")
	public static List<VehiclesAssignation> consultarVehiculosDisponiblesEntrega(
			Long opcAsignacion) throws GWorkException {

		List<VehiclesAssignation> listVehiclesAsignation = null;

		try {
			Long estadoAsignacion = null;
			Long estadoAlquiler = null;
			if (opcAsignacion != null && opcAsignacion.longValue() == 1L)
				estadoAsignacion = new Long(Util.loadMessageValue("ESTADO.2"));

			if (opcAsignacion != null && opcAsignacion.longValue() == 2L)
				estadoAlquiler = new Long(Util.loadMessageValue("ESTADO.4"));

			if (opcAsignacion != null && opcAsignacion.longValue() == 0L) {
				estadoAlquiler = new Long(Util.loadMessageValue("ESTADO.4"));
				estadoAsignacion = new Long(Util.loadMessageValue("ESTADO.2"));
			}

			StringBuffer buffer = new StringBuffer();
			buffer
					.append("select model from VehiclesAssignation model where model.assignationsStates.");

			if (estadoAsignacion != null)
				buffer.append(assCodigo + "= :estadoAsignacion ");

			else if (estadoAlquiler != null)
				buffer.append(assCodigo + "= :estadoAlquiler ");

			if (estadoAlquiler != null && estadoAsignacion != null)
				buffer.append("or model.assignationsStates." + assCodigo
						+ "= :estadoAlquiler ");

			EntityManagerHelper.getEntityManager().clear();
			Query query = EntityManagerHelper.getEntityManager().createQuery(
					buffer.toString());

			if (estadoAlquiler != null)
				query.setParameter("estadoAlquiler", estadoAlquiler);

			if (estadoAsignacion != null)
				query.setParameter("estadoAsignacion", estadoAsignacion);

			listVehiclesAsignation = (List<VehiclesAssignation>) query
					.getResultList();

			if (listVehiclesAsignation != null
					&& !listVehiclesAsignation.isEmpty())
				return listVehiclesAsignation;
			else
				throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));

		} catch (Exception e) {
			throw new GWorkException(e.getMessage());
		}
	}

	/**
	 * Validar reserva vehiculos no entregados.
	 *
	 * @param vhcIdVhiculo the vhc id vhiculo
	 * @return the list
	 */
	@SuppressWarnings("unchecked")
	public static List<VehiclesAssignation> validarReservaVehiculosNoEntregados(
			Long vhcIdVhiculo) {

		StringBuffer buffer = new StringBuffer();
		buffer
				.append("select model from VehiclesAssignation model where model.vehicles."
						+ vhcCodigo);
		buffer.append("= :codigoVehiculo and model." + vhaFechaEntrega
				+ " is null and model." + vhaFechaDev + " is null");

		Query query = getEntityManager().createQuery(buffer.toString());
		query.setParameter("codigoVehiculo", vhcIdVhiculo);

		List<VehiclesAssignation> list = query.getResultList();

		if (list != null && list.size() > 0)
			return list;

		return list;

	}

	/**
	 * Validar fechas reserva vehiculo.
	 *
	 * @param placa the placa
	 * @param fechaIni the fecha ini
	 * @param fechaFin the fecha fin
	 * @throws GWorkException the g work exception
	 */
	@SuppressWarnings("unchecked")
	public static void validarFechasReservaVehiculo(String placa,
			Date fechaIni, Date fechaFin) throws GWorkException {
		try {
			List<Vehicles> listVehicles = new VehiclesDAO()
					.findByVhcPlacaDiplomatica(placa);
			Vehicles vehicles = null;
			if (listVehicles != null && listVehicles.size() > 0) {
				vehicles = listVehicles.get(0);

				Date fecha1 = null;
				Date fecha2 = null;

				StringBuffer buffer = new StringBuffer();
				buffer
						.append("select model from VehiclesAssignation model where model.vehicles."
								+ vhcCodigo
								+ "= :codigoVehiculo and model."
								+ vhaFechaEntrega
								+ " is not null and model."
								+ vhaFechaDev + " is null");

				Query query = getEntityManager().createQuery(buffer.toString());
				query.setParameter("codigoVehiculo", vehicles.getVhcCodigo());

				List<VehiclesAssignation> list = query.getResultList();
				if (list != null && list.size() > 0) {
					for (VehiclesAssignation vehiclesAssignation : list) {

						if (vehiclesAssignation.getVhaFechaInicio() != null)
							fecha1 = vehiclesAssignation.getVhaFechaInicio();

						if (vehiclesAssignation.getVhaFechaTermina() != null)
							fecha2 = vehiclesAssignation.getVhaFechaTermina();

						if (fecha1 != null && fecha2 != null) {
							if (fechaIni.compareTo(fecha1) == 0
									|| fechaIni.compareTo(fecha1) > 0)
								if (fechaIni.compareTo(fecha2) == 0
										|| fechaIni.compareTo(fecha2) < 0) {
									throw new GWorkException(
											Util
													.loadErrorMessageValue("RESERVAFECHAS"));
								}

							if (fechaIni.compareTo(fecha1) < 0
									&& fechaFin.compareTo(fecha2) > 0) {
								throw new GWorkException(Util
										.loadErrorMessageValue("RESERVAFECHAS"));
							}
						}
					}
				} else {
					list = validarReservaVehiculosNoEntregados(vehicles
							.getVhcCodigo());
					for (VehiclesAssignation vehiclesAssignation : list) {

						if (vehiclesAssignation.getVhaFechaInicio() != null)
							fecha1 = vehiclesAssignation.getVhaFechaInicio();

						if (vehiclesAssignation.getVhaFechaTermina() != null)
							fecha2 = vehiclesAssignation.getVhaFechaTermina();

						if (fecha1 != null && fecha2 != null) {
							if (fechaIni.compareTo(fecha1) == 0
									|| fechaIni.compareTo(fecha1) > 0)
								if (fechaIni.compareTo(fecha2) == 0
										|| fechaIni.compareTo(fecha2) < 0) {
									throw new GWorkException(
											Util
													.loadErrorMessageValue("RESERVAFECHAS"));
								}

							if (fechaIni.compareTo(fecha1) < 0
									&& fechaFin.compareTo(fecha2) > 0) {
								throw new GWorkException(Util
										.loadErrorMessageValue("RESERVAFECHAS"));
							}
						}
					}
				}
			}
		} catch (Exception e) {
			throw new GWorkException(e.getMessage());
		}
	}

	/**
	 * Consultar asignacion.
	 *
	 * @param idVhiculo the id vhiculo
	 * @return the vehicles assignation
	 * @throws GWorkException the g work exception
	 */
	@SuppressWarnings("unchecked")
	public static VehiclesAssignation consultarAsignacion(Long idVhiculo)
			throws GWorkException {
		try {
			List<VehiclesAssignation> listVehicles = null;
			VehiclesAssignation assignation = null;
			final String queryString = "select model from VehiclesAssignation model where model.vehicles."
					+ vhcCodigo + "= :idVehiculo";

			Query query = EntityManagerHelper.createQuery(queryString);
			query.setParameter("idVehiculo", new Long(idVhiculo));
			listVehicles = (List<VehiclesAssignation>) query.getResultList();

			if (listVehicles != null && listVehicles.size() > 0)
				assignation = listVehicles.get(0);

			return assignation;

		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Consultar entrega vehiculo por placa vehiculo.
	 *
	 * @param placa the placa
	 * @return the list
	 * @throws GWorkException the g work exception
	 */
	public static List<AsignationDevolution> consultarEntregaVehiculoPorPlacaVehiculo(
			String placa) throws GWorkException {
		try {

			AsignationDevolution asignationDevolution = null;
			Long idVehiculo = null;

			Long estadoRetirado = Long.valueOf(Util.loadMessageValue("ESTADO.5")); 
			Vehicles vehicles = SearchVehicles.consultarVehiculosPorPlacaYEstado(placa, estadoRetirado);

			if (vehicles != null) {
				idVehiculo = vehicles.getVhcCodigo();

				VehiclesAssignation vehiclesAssignation = null;
				List<AsignationDevolution> listAsignationDevolution = null;
				StringBuffer buffer = new StringBuffer();
				buffer.append("select model from VehiclesAssignation model where model.vehicles."
								+ vhcCodigo + " = :idVehiculo");
				buffer.append(" and model." + vhaFechaDev
						+ " is null and model." + vhaFechaEntrega
						+ " is not null");
				EntityManagerHelper.getEntityManager().clear();
				Query query = EntityManagerHelper.createQuery(buffer.toString());
				query.setParameter("idVehiculo", Long.valueOf(idVehiculo));

				if (query.getResultList().size() > 0) {
					vehiclesAssignation = (VehiclesAssignation) query
							.getResultList().get(0);

					if (vehiclesAssignation != null) {

						listAsignationDevolution = new ArrayList<AsignationDevolution>();

						asignationDevolution = new AsignationDevolution();

						if (vehiclesAssignation.getVhaCodigo() != null)
							asignationDevolution
									.setVhaCodigo(vehiclesAssignation
											.getVhaCodigo());

						if (vehiclesAssignation.getVhaKilomeActual() != null)
							asignationDevolution
									.setVhaKilomeActual(vehiclesAssignation
											.getVhaKilomeActual());

						if (vehiclesAssignation.getAssignationsStates() != null)
							asignationDevolution
									.setAssignationsStates(vehiclesAssignation
											.getAssignationsStates());

						if (vehiclesAssignation.getVehicles()
								.getVehiclesStates() != null)
							asignationDevolution
									.setVehicles(vehiclesAssignation
											.getVehicles());

						if (vehiclesAssignation.getRequests() != null)
							asignationDevolution
									.setRequests(vehiclesAssignation
											.getRequests());

						if (vehiclesAssignation.getAssignationsTypes() != null)
							asignationDevolution
									.setAssignationsTypes(vehiclesAssignation
											.getAssignationsTypes());

						if (vehiclesAssignation.getVhaFechaInicio() != null)
							asignationDevolution
									.setVhaFechaIni(vehiclesAssignation
											.getVhaFechaInicio());

						if (vehiclesAssignation.getVhaFechaTermina() != null)
							asignationDevolution
									.setVhaFechaFin(vehiclesAssignation
											.getVhaFechaTermina());

						if (vehiclesAssignation.getVhaFechaEntrega() != null)
							asignationDevolution
									.setVhaFechaEntrega(vehiclesAssignation
											.getVhaFechaEntrega());

						listAsignationDevolution.add(asignationDevolution);
					}

				}
				return listAsignationDevolution;
			}
			return null;
		} catch (RuntimeException re) {
			log.error("consultarEntregaVehiculoPorIdVehiculo", re);
		}
		return null;
	}

	/**
	 * Consultar asignacion vehiculo por id vehiculo.
	 *
	 * @param idVehicle the id vehicle
	 * @return the vehicles assignation
	 * @throws GWorkException the g work exception
	 */
	public static VehiclesAssignation consultarAsignacionVehiculoPorIdVehiculo(
			Long idVehicle) throws GWorkException {
		try {

			StringBuffer buffer = new StringBuffer();
			buffer
					.append("select model from VehiclesAssignation model where model.vehicles."
							+ vhcCodigo + " = :idVehiculo");
			buffer.append(" and model." + vhaFechaDev + " is null and model."
					+ vhaFechaEntrega + " is not null");

			Query query = getEntityManager().createQuery(buffer.toString());
			query.setParameter("idVehiculo", idVehicle);

			if (query.getResultList().size() > 0)
				return (VehiclesAssignation) query.getResultList().get(0);

			return null;
		} catch (RuntimeException re) {
			log.error("consultarAsignacionVehiculoPorIdVehiculo", re);
		}
		return null;
	}

	/**
	 * Consultar asignacion vehiculo.
	 *
	 * @param placa the placa
	 * @return the vehicles assignation
	 * @throws GWorkException the g work exception
	 */
	public static VehiclesAssignation consultarAsignacionVehiculo(String placa)
			throws GWorkException {
		try {

			StringBuffer buffer = new StringBuffer();
			buffer.append("select model from VehiclesAssignation model "
					+ "where model.vehicles.vhcPlacaDiplomatica= :placa");
			buffer.append(" and model.vhaFechaDev is null "
					+ "and model.vhaFechaEntrega is not null");

			Query query = EntityManagerHelper.getEntityManager().createQuery(
					buffer.toString());
			query.setParameter("placa", placa);

			if (query.getResultList().size() > 0)
				return (VehiclesAssignation) query.getResultList().get(0);

			return null;
		} catch (RuntimeException re) {
			log.error("consultarAsignacionVehiculoPorIdVehiculo", re);
		}
		return null;
	}

	/**
	 * Consultar asignacion vehiculo.
	 *
	 * @param placa the placa
	 * @param lgtTypes the lgt types
	 * @return the vehicles assignation
	 * @throws GWorkException the g work exception
	 */
	public static VehiclesAssignation consultarAsignacionVehiculo(String placa,
			String lgtTypes[]) throws GWorkException {
		try {

			/* Agregar códigos del tipo de asignacion de los vehiculos */
			String codigosAsignacion = "(";
			for (int i = 0; i < lgtTypes.length; i++) {
				if ((i + 1) < lgtTypes.length)
					codigosAsignacion += lgtTypes[i] + ",";
				else
					codigosAsignacion += lgtTypes[i];
			}
			codigosAsignacion += ")";

			String sql = "select model from VehiclesAssignation model where model.vehicles."
					+ vhcPlacaDiplomatica
					+ " = :placa "
					+ "and model."
					+ vhaFechaDev
					+ " is null and model."
					+ vhaFechaEntrega
					+ " is not null "
					+ "and model.requests.legateesTypes.lgtCodigo IN "
					+ codigosAsignacion;

			javax.persistence.EntityManager entityAux = EntityManagerHelper
					.getEntityManager();
			entityAux.clear();

			Query query = entityAux.createQuery(sql);
			query.setParameter("placa", placa);

			if (query.getResultList().size() > 0)
				return (VehiclesAssignation) query.getResultList().get(0);

			return null;
		} catch (RuntimeException re) {
			log.error("consultarAsignacionVehiculoPorIdVehiculo", re);
		}
		return null;
	}

	/**
	 * Consultar asignacion servicio combustible.
	 *
	 * @param placa the placa
	 * @return the vO assignation
	 * @throws GWorkException the g work exception
	 */
	public static VOAssignation consultarAsignacionServicioCombustible(
			String placa) throws GWorkException {
		try {
			VOAssignation fuelService = null;
			List<String> listCencos = null;
			Long idFuel = null;

			VehiclesAssignation assignation = consultarAsignacionVehiculo(placa
					.toUpperCase().trim());

			if (assignation != null) {
				fuelService = new VOAssignation();

				fuelService.setCarneAsignatario(assignation.getRequests()
						.getRqsCarnetAsignatario());

				Set<CostsCentersVehicles> setCencos = assignation
						.getCostsCentersVehicleses();

				if (setCencos != null && setCencos.size() > 0) {
					listCencos = new ArrayList<String>();
					for (CostsCentersVehicles CostsCentersVehicles : setCencos) {
						listCencos.add(CostsCentersVehicles.getCostsCenters()
								.getCocNumero());
					}
					fuelService.setCencos(listCencos);
				} else
					fuelService.setCencos(new ArrayList<String>());
				if (assignation.getVehicles().getFuelsTypes() != null) {
					fuelService.setFutNombre(assignation.getVehicles()
							.getFuelsTypes().getFutNombre());
					if (assignation.getRequests().getUsersByRqsUser()
							.getUsrApellido() == null) {
						fuelService.setNombreAsignatario(assignation
								.getRequests().getUsersByRqsUser()
								.getUsrNombre());
					} else {
						fuelService.setNombreAsignatario(assignation
								.getRequests().getUsersByRqsUser()
								.getUsrNombre()
								+ " "
								+ assignation.getRequests().getUsersByRqsUser()
										.getUsrApellido());
					}

					fuelService.setPlaca(assignation.getVehicles()
							.getVhcPlacaDiplomatica());

					idFuel = assignation.getVehicles().getFuelsTypes()
							.getFutCodigo();
					fuelService.setTarifa(new Float(new ConsultTariff()
							.consultarValorTarifaTipoCombustible(idFuel)));

					if (assignation != null
							&& assignation.getRequests().getLegateesTypes() != null)
						fuelService.setTipoAsignacion(assignation.getRequests()
								.getLegateesTypes().getLgtNombre());
					else
						fuelService.setTipoAsignacion("");

					fuelService
							.setUltimoKilometrajeRegistrado(new ConsultTariff()
									.consultLastKm(assignation.getVehicles()
											.getVhcCodigo()));

					if (assignation.getVehicles().getVhcCapMaxTanq() != null)
						fuelService.setVhcCapMaxTanq(assignation.getVehicles()
								.getVhcCapMaxTanq());
					else
						fuelService.setVhcCapMaxTanq(10F);

					fuelService.setVhcCodigo(assignation.getVehicles()
							.getVhcCodigo());
					fuelService.setTipoCombustible(assignation.getVehicles()
							.getFuelsTypes().getFutCodigo());
				}
			} else {
				Vehicles vehicles = consultarVehiculosPorPlacaSinFiltros(placa
						.toUpperCase().trim());
				if (vehicles != null) {

					fuelService = new VOAssignation();
					if (vehicles.getFuelsTypes() != null) {
						idFuel = vehicles.getFuelsTypes().getFutCodigo();
						fuelService.setFutNombre(vehicles.getFuelsTypes()
								.getFutNombre());
					}

					fuelService.setTarifa(new Float(new ConsultTariff()
							.consultarValorTarifaTipoCombustible(idFuel)));
					fuelService.setPlaca(placa);
					fuelService.setTipoCombustible(idFuel);

					if (vehicles.getVhcCapMaxTanq() != null)
						fuelService.setVhcCapMaxTanq(vehicles
								.getVhcCapMaxTanq());
					else
						fuelService.setVhcCapMaxTanq(10F);

					fuelService.setVhcCodigo(vehicles.getVhcCodigo());
					fuelService
							.setUltimoKilometrajeRegistrado(new ConsultTariff()
									.consultLastKm(vehicles.getVhcCodigo()));
				}
			}

			return fuelService;
		} catch (RuntimeException re) {
			log.error("consultarAsignacionVehiculoPorIdVehiculo", re);
		}
		return null;
	}

	/**
	 * Consultar asignacion vehiculo prepago.
	 *
	 * @param placa the placa
	 * @return the vehicles assignation
	 * @throws GWorkException the g work exception
	 */
	public static VehiclesAssignation consultarAsignacionVehiculoPrepago(
			String placa) throws GWorkException {
		try {

			StringBuffer buffer = new StringBuffer();
			buffer
					.append("select model from VehiclesAssignation model where model.vehicles."
							+ vhcPlacaDiplomatica + " = :placa");
			buffer.append(" and model." + vhaFechaDev + " is null and model."
					+ vhaFechaEntrega + " is not null");
			buffer.append(" and (model.requests.legateesTypes.lgtCodigo= :OF "
					+ "OR model.requests.legateesTypes.lgtCodigo= :OFS "
					+ "OR model.requests.legateesTypes.lgtCodigo= :PROGRAMAS "
					+ "OR model.requests.legateesTypes.lgtCodigo= :PROYECTO)");

			Query query = getEntityManager().createQuery(buffer.toString());
			query.setParameter("placa", placa);
			query.setParameter("OF", OF);
			query.setParameter("OFS", OFS);
			query.setParameter("PROGRAMAS", PROGRAMAS);
			query.setParameter("PROYECTO", PROYECTO);

			if (query.getResultList().size() > 0)
				return (VehiclesAssignation) query.getResultList().get(0);

			return null;
		} catch (RuntimeException re) {
			log.error("consultarAsignacionVehiculoPorIdVehiculo", re);
		}
		return null;
	}

	/**
	 * Consultar asignacion vehiculo prepago km personal.
	 *
	 * @param placa the placa
	 * @return the vehicles assignation
	 * @throws GWorkException the g work exception
	 */
	public static VehiclesAssignation consultarAsignacionVehiculoPrepagoKmPersonal(
			String placa) throws GWorkException {
		try {

			StringBuffer buffer = new StringBuffer();
			buffer
					.append("select model from VehiclesAssignation model where model.vehicles."
							+ vhcPlacaDiplomatica + " = :placa");
			buffer.append(" and model." + vhaFechaDev + " is null and model."
					+ vhaFechaEntrega + " is not null");
			buffer.append(" and (model.requests.legateesTypes.lgtCodigo= :OF "
					+ "OR model.requests.legateesTypes.lgtCodigo= :OFS)");

			Query query = getEntityManager().createQuery(buffer.toString());
			query.setParameter("placa", placa);
			query.setParameter("OF", OF);
			query.setParameter("OFS", OFS);

			if (query.getResultList().size() > 0)
				return (VehiclesAssignation) query.getResultList().get(0);

			return null;
		} catch (RuntimeException re) {
			log.error("consultarAsignacionVehiculoPorIdVehiculo", re);
		}
		return null;
	}

	/**
	 * Consultar color.
	 *
	 * @param nombreColor the nombre color
	 * @return the list
	 * @throws GWorkException the g work exception
	 */
	@SuppressWarnings("unchecked")
	public static List<Colors> consultarColor(String nombreColor)
			throws GWorkException {
		try {
			List<Colors> listColors = null;
			final String queryString = "select model from Colors model where model."
					+ clNombre + " LIKE :nombreColor ";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("nombreColor", "'%" + nombreColor + "%'");
			listColors = (List<Colors>) query.getResultList();

			if (listColors != null && listColors.size() > 0)
				return listColors;

		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Consultar linea por id.
	 *
	 * @param idLinea the id linea
	 * @return the lines
	 * @throws GWorkException the g work exception
	 */
	@SuppressWarnings("unchecked")
	public static Lines consultarLineaPorId(Long idLinea) throws GWorkException {
		try {
			List<Lines> listLines = null;
			final String queryString = "select model from Lines model where model."
					+ lnsId + " = :lnsId ";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("lnsId", idLinea);
			listLines = (List<Lines>) query.getResultList();

			if (listLines != null && listLines.size() > 0)
				return listLines.get(0);

		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Consultar linea por nombre.
	 *
	 * @param nombreLinea the nombre linea
	 * @return the lines
	 * @throws GWorkException the g work exception
	 */
	@SuppressWarnings("unchecked")
	public static Lines consultarLineaPorNombre(String nombreLinea)
			throws GWorkException {
		try {
			List<Lines> listLines = null;
			final String queryString = "select model from Lines model where model."
					+ "lnsNombre='" + nombreLinea + "'";

			Query query = getEntityManager().createQuery(queryString);
			listLines = (List<Lines>) query.getResultList();

			if (listLines != null && listLines.size() > 0)
				return listLines.get(0);

		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Consultar marca por nombre.
	 *
	 * @param nombreMarca the nombre marca
	 * @return the brands
	 * @throws GWorkException the g work exception
	 */
	@SuppressWarnings("unchecked")
	public static Brands consultarMarcaPorNombre(String nombreMarca)
			throws GWorkException {
		try {
			List<Brands> listLines = null;
			final String queryString = "select model from Brands model where model."
					+ "brnNombre='" + nombreMarca + "'";

			Query query = getEntityManager().createQuery(queryString);
			listLines = (List<Brands>) query.getResultList();

			if (listLines != null && listLines.size() > 0)
				return listLines.get(0);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Consultar criterio usuarios.
	 *
	 * @param criterio the criterio
	 * @return the list
	 * @throws GWorkException the g work exception
	 */
	@SuppressWarnings("unchecked")
	public static List<Users> consultarCriterioUsuarios(String criterio)
			throws GWorkException {
		try {
			List<Users> listUsers = null;
			final String queryString = "select model from Users model where model."
					+ usrNombre + " LIKE " + "'%" + criterio + "%'";
			Query query = getEntityManager().createQuery(queryString);
			listUsers = (List<Users>) query.getResultList();

			if (listUsers != null && listUsers.size() > 0)
				return listUsers;

		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Se utiliza en la compra de prepago de combustible.
	 *
	 * @param idLegatee Identificador único del tipo de asignatario.
	 * @return Listado de Vehiculos
	 * @throws GWorkException the g work exception
	 */
	@SuppressWarnings("unchecked")
	public static List<Vehicles> consultarVehiculosPorTipoAsignatario(
			Long idLegatee) throws GWorkException {
		try {
			Util.validarSession();

			List<VehiclesAssignation> listVHAS = null;

			final String queryString = "select model from VehiclesAssignation model "
					+ "where model.requests.legateesTypes.lgtCodigo="
					+ idLegatee
					+ " ORDER BY model.vehicles.vhcPlacaDiplomatica ASC";

			Query query = EntityManagerHelper.getEntityManager().createQuery(
					queryString);

			listVHAS = (List<VehiclesAssignation>) query.getResultList();

			if (listVHAS != null && listVHAS.size() > 0) {
				List<Vehicles> lstVehiculos = new ArrayList<Vehicles>();

				for (VehiclesAssignation vhas : listVHAS) {
					lstVehiculos.add(vhas.getVehicles());
				}

				return lstVehiculos;
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new GWorkException(e.getMessage());
		}
		return null;
	}

	/**
	 * Consultar usuarios activos.
	 *
	 * @return the list
	 * @throws GWorkException the g work exception
	 */
	@SuppressWarnings("unchecked")
	public static List<Users> consultarUsuariosActivos() throws GWorkException {
		try {
			List<Users> listUsers = null;
			final String queryString = "select model from Users model where model.usrEstado = :estado";
			Query query = getEntityManager().createQuery(queryString);
			query
					.setParameter("estado", Util
							.loadMessageValue("ESTADO.ACTIVO"));
			listUsers = (List<Users>) query.getResultList();

			if (listUsers != null && listUsers.size() > 0)
				return listUsers;

		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Split.
	 *
	 * @param str the str
	 * @param separatorChar the separator char
	 * @return the string[]
	 */
	@SuppressWarnings("unchecked")
	public final static String[] split(String str, char separatorChar) {
		if (str == null) {
			return null;
		}
		int len = str.length();
		if (len == 0) {
			return null;
		}
		Vector list = new Vector();
		int i = 0;
		int start = 0;
		boolean match = false;
		while (i < len) {
			if (str.charAt(i) == separatorChar) {
				if (match) {
					list.addElement(str.substring(start, i).trim());
					match = false;
				}
				start = ++i;
				continue;
			}
			match = true;
			i++;
		}
		if (match) {
			list.addElement(str.substring(start, i).trim());
		}
		String[] arr = new String[list.size()];
		list.copyInto(arr);
		return arr;
	}

	/**
	 * Consultar reg servicios.
	 *
	 * @param idVehiculo the id vehiculo
	 * @return the list
	 * @throws GWorkException the g work exception
	 */
	@SuppressWarnings("unchecked")
	public static List<Object[]> consultarRegServicios(Long idVehiculo)
			throws GWorkException {
		List<Object[]> listUsers = null;

		try {
			final String queryString = "SELECT servReg.serCodigo, servReg.vehicles.vhcCodigo, "
					+ "servReg.serFecha, "
					+ "servReg.serNumeroGalones, servReg.serKilometrajeAnterior, "
					+ "servReg.serKilometrajeActual, servReg.serTotal, servReg.headerProof.hepId "
					+ "FROM ServiceRegistry servReg "
					+ "WHERE servReg.vehicles.vhcCodigo = :idVehiculo "
					+ "ORDER BY servReg.serFecha DESC";

			Query query = EntityManagerHelper.getEntityManager().createQuery(
					queryString);
			query.setParameter("idVehiculo", idVehiculo);

			// query.setParameter("estado",
			// Util.loadMessageValue("ESTADO.ACTIVO"));
			listUsers = query.getResultList();
			return listUsers;
		} catch (Exception e) {
			e.printStackTrace();
			throw new GWorkException(e.getMessage());
		}
	}

	/**
	 * Consultar prepaid consumption.
	 *
	 * @param sercodigo the sercodigo
	 * @return the prepaid consumption
	 * @throws GWorkException the g work exception
	 */
	public static PrepaidConsumption consultarPrepaidConsumption(Long sercodigo)
			throws GWorkException {

		try {
			PrepaidConsumption pc = null;

			final String queryString = "select model from PrepaidConsumption model where model.serviceRegistry.serCodigo = :propertyValue";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("propertyValue", sercodigo);
			Object result = query.getSingleResult();
			if (result!=null){ 
				pc = (PrepaidConsumption) result;
				return pc;
			}else{
				return null;
			}

		} catch(NoResultException nre){
			return null;
		} catch (RuntimeException re) {
			log.error("consultarPrepaidConsumption("+sercodigo+")", re);
			return null;
		}
	}

	/**
	 * Consultar actual others applications.
	 *
	 * @param hepid the hepid
	 * @return the actual others applications
	 * @throws GWorkException the g work exception
	 */
	public static ActualOthersApplications consultarActualOthersApplications(
			Long hepid) throws GWorkException {

		try {
			ActualOthersApplications aoa = null;

			final String queryString = "select model from ActualOthersApplications model where model.headerProof.hepId = :propertyValue and model.PEntCr is null";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("propertyValue", hepid);
			aoa = (ActualOthersApplications) query.getSingleResult();

			if (aoa != null)
				return aoa;

		} catch (RuntimeException re) {
			log.error("consultarActualOthersApplications", re);
		}
		return null;
	}

	/**
	 * Consultar cc combustible.
	 *
	 * @param idRegService the id reg service
	 * @return the list
	 * @throws GWorkException the g work exception
	 */
	@SuppressWarnings("unchecked")
	public static List<Object[]> consultarCcCombustible(Long idRegService)
			throws GWorkException {
		List<Object[]> listRegServices = null;

		try {
			final String queryString = "SELECT pc.prcCodigo,pc.prcValorConsumo "
					+ "FROM PrepaidConsumption pc "
					+ "WHERE pc.serviceRegistry.serCodigo = :idRegService";

			Query query = EntityManagerHelper.getEntityManager().createQuery(
					queryString);
			query.setParameter("idRegService", idRegService);

			listRegServices = query.getResultList();
			return listRegServices;
		} catch (Exception e) {
			e.printStackTrace();
			throw new GWorkException(e.getMessage());
		}
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws Exception the exception
	 */
	public static void main(String[] args) throws Exception {

		//System.out.println(new SearchVehicles().consultarPrepaidConsumption(123432L));

		/*
		 * VOAssignation fuelService =
		 * consultarAsignacionServicioCombustible("OI0165");
		 * System.out.println(fuelService.getCarneAsignatario());
		 * System.out.println(fuelService.getFutNombre());
		 * System.out.println(fuelService.getNombreAsignatario());
		 * System.out.println(fuelService.getPlaca());
		 * System.out.println(fuelService.getTipoAsignacion());
		 * System.out.println(fuelService.getUltimoKilometrajeRegistrado());
		 * System.out.println(fuelService.getCencos().size());
		 * System.out.println(fuelService.getTarifa());
		 * System.out.println(fuelService.getVhcCapMaxTanq());
		 * System.out.println(fuelService.getTarifa());
		 * System.out.println(fuelService.getVhcCodigo());
		 */
		/*
		 * List<VehiclesAssignation> list = consultarHistorialVehiculos(); for
		 * (VehiclesAssignation vehiclesAssignation : list) {
		 * System.out.println(vehiclesAssignation.getVehicles().getVhcPlacaDiplomatica());
		 * System.out.println(vehiclesAssignation.getVhaFechaInicio()); }
		 */

		// ConsultarReservasVehiculosProximas();
		// consultarColor("BLANCO");
		// List<Cities> list=ConsultarUbicacionCiudad(new Long(107));
		// String fecha1 = "8-22-2008";
		// String fecha2 = "10-24-2008";
		// SimpleDateFormat dateFormat = new SimpleDateFormat();
		// dateFormat.applyPattern("mm-dd-yyyy");
		// Date fechaIni = dateFormat.parse(fecha1);
		// Date fechaFin = dateFormat.parse(fecha2);
		// java.sql.Date fechaTemp1 = new java.sql.Date(fechaIni.getTime());
		// java.sql.Date fechaTemp2 = new java.sql.Date(fechaFin.getTime());
		// System.out.println(fechaIni.compareTo(fechaFin));
		// validarFechasReservaVehiculo("OI293", fechaIni, fechaFin);
		// System.out.println(consultarVehiculosPorPlaca("OI030").getVhcPlacaDiplomatica());
		// List<Vehicles> listVehiculo =
		// consultarVehiculosPorEstadosTipoVehiculo();
		// List<Vehicles> list = consultarVehiculosPorTipoVehiculo(new
		// Long("3"));
		// int i = 0;
		// for (Vehicles vehicles : listVehiculo) {
		// System.out.println(vehicles.getVhcPlacaDiplomatica() + " " + i);
		// i++;
		// }
		// System.out.println(consultarCatalogosPorModelo());
		// System.out.println(consultarDatosVehiculoAsignacion("JAM-506"));
		// cambiarEstadoVehiculosReservadosNoRecogidos();
		/*
		 * System.out.println(ConsultarFechasTerminacionAsignacion("ASIGNACION",
		 * "PARAMETRO_ASIGNACION")); String fechaCadena = "1/4/2008";
		 * SimpleDateFormat dateFormat = new SimpleDateFormat();
		 * dateFormat.applyPattern("dd/mm/yyyy"); Date fecha =
		 * dateFormat.parse(fechaCadena); List<Requests> list =
		 * ConsultarSolicitudAsignacionAlquilerVehiculos(new Long(1), fecha,
		 * fecha, new Long(1)); System.out.println("" + list.get(0)); Requests
		 * requests3 = list.get(0);
		 * System.out.println(requests3.getRqsNumeroSolicitud()); Requests
		 * requests = new RequestsDAO().findById(new Long(2)); List<Requests>
		 * requests2 = new RequestsDAO().findByRqsCarnetEmpleado("1234");
		 */
		// System.out.println(""+requests2);
	}

	/**
	 * Consultar asignacnio by request.
	 *
	 * @param idRequest the id request
	 * @return the vehicles assignation
	 */
	public static VehiclesAssignation consultarAsignacnioByRequest(
			Long idRequest) {
		final String queryString = "select model from VehiclesAssignation model "
				+ "where model.requests.rqsCodigo=:idRequest";
		Query query = EntityManagerHelper.createQuery(queryString);
		query.setParameter("idRequest", idRequest);

		if (query.getResultList() != null && query.getResultList().size() > 0
				&& !query.getResultList().isEmpty())
			return (VehiclesAssignation) query.getSingleResult();

		else
			return null;
	}

	/**
	 * Consultar asignacion por id vehiculo.
	 *
	 * @param idVehiculo the id vehiculo
	 * @return the vehicles assignation
	 * @throws GWorkException the g work exception
	 */
	@SuppressWarnings("unchecked")
	public static VehiclesAssignation consultarAsignacionPorIdVehiculo(
			Long idVehiculo) throws GWorkException {
		try {
			List<VehiclesAssignation> listVehicles = null;
			VehiclesAssignation assignation = null;
			final String queryString = "select model from VehiclesAssignation model where model.vehicles."
					+ vhcCodigo + "= :idVehiculo";

			Query query = EntityManagerHelper.createQuery(queryString);
			query.setParameter("idVehiculo", new Long(idVehiculo));
			listVehicles = (List<VehiclesAssignation>) query.getResultList();

			if (listVehicles != null && listVehicles.size() > 0)
				assignation = listVehicles.get(0);

			return assignation;

		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Permite consultar los catos de un vehiculo retirado que esta en proceso
	 * de venta.
	 *
	 * @param placa the placa
	 * @return the clients sales vehicles
	 * @throws GWorkException the g work exception
	 */
	@SuppressWarnings("unchecked")
	public static ClientsSalesVehicles consultarVehiculoRetirado(String placa)
			throws GWorkException {
		try {
			List<ClientsSalesVehicles> listVehicles = null;
			ClientsSalesVehicles clientsSalesVehicles = null;

			final String queryString = "select model from ClientsSalesVehicles model where model.vehicles.vhcPlacaDiplomatica"
					+ " = :placaVehiculo";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("placaVehiculo", placa);

			listVehicles = (List<ClientsSalesVehicles>) query.getResultList();

			if (listVehicles != null && listVehicles.size() > 0)
				clientsSalesVehicles = listVehicles.get(0);
			else
				return null;

			return clientsSalesVehicles;
		} catch (RuntimeException re) {
			log.error("consultarVehiculoRetiradoPorPlaca", re);
		}
		return null;
	}

	/**
	 * Consultar vehiculos por placa polizas.
	 *
	 * @param placa the placa
	 * @return the vehicles
	 * @throws GWorkException the g work exception
	 */
	@SuppressWarnings("unchecked")
	public static Vehicles consultarVehiculosPorPlacaPolizas(String placa)
			throws GWorkException {
		try {

			Long estadoPool = new Long(Util.loadMessageValue("ESTADO.6"));
			Long estadoAlquiler = new Long(Util.loadMessageValue("ESTADO.7"));
			Long estadoAsignado = new Long(Util.loadMessageValue("ESTADO.1"));

			StringBuffer buffer = new StringBuffer();
			buffer.append("select model from Vehicles model where model."
					+ vhcPlacaDiplomatica + "= :placa ");

			List<Vehicles> listVehicles = null;
			Vehicles vehicles = null;
			Query query = getEntityManager().createQuery(buffer.toString());
			query.setParameter("placa", placa);
			listVehicles = (List<Vehicles>) query.getResultList();

			if (listVehicles != null && !listVehicles.isEmpty()) {

				vehicles = listVehicles.get(0);

				if (vehicles.getVehiclesStates().getVhsCodigo().equals(
						estadoPool)
						|| vehicles.getVehiclesStates().getVhsCodigo().equals(
								estadoAlquiler)
						|| vehicles.getVehiclesStates().getVhsCodigo().equals(
								estadoAsignado))
					return vehicles;

				return null;
			}
			return null;

		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}

	/**
	 * List asignation by user.
	 *
	 * @param carneAsignatario the carne asignatario
	 * @return the list
	 * @throws GWorkException the g work exception
	 */
	@SuppressWarnings("unchecked")
	public static List<VehiclesAssignation> listAsignationByUser(
			String carneAsignatario) throws GWorkException {

		try {
			final String queryString = "select model from VehiclesAssignation model "
					+ "WHERE model.requests.usersByRqsUser.usrIdentificacion = :carneAsignatario "
					+ "ORDER BY model.vhaFechaEntrega DESC";

			Query query = EntityManagerHelper.getEntityManager().createQuery(
					queryString);
			query.setParameter("carneAsignatario", carneAsignatario);

			if (query.getResultList() != null
					&& query.getResultList().size() > 0)
				return query.getResultList();

		} catch (RuntimeException re) {
			log.error("ConsultarAprovisionamiento", re);
		}
		return null;

	}

	/**
	 * Gets the vehicles states.
	 *
	 * @param idVehiclesState the id vehicles state
	 * @return the vehicles states
	 * @throws GWorkException the g work exception
	 */
	public static VehiclesStates getVehiclesStates(Long idVehiclesState)
			throws GWorkException {

		try {
			final String queryString = "select model from VehiclesStates model "
					+ "WHERE model.vhsCodigo = :idVehiclesState ";

			Query query = EntityManagerHelper.getEntityManager().createQuery(
					queryString);
			query.setParameter("idVehiclesState", idVehiclesState);

			if (query.getResultList() != null
					&& query.getResultList().size() > 0)
				return (VehiclesStates) query.getResultList().get(0);

		} catch (RuntimeException re) {
			log.error("ConsultarAprovisionamiento", re);
		}
		return null;
	}

	/**
	 * Km personal inicial.
	 *
	 * @param vhc_codigo the vhc_codigo
	 * @param fechaInicial the fecha inicial
	 * @param fechaFinal the fecha final
	 * @return the string
	 * @throws GWorkException the g work exception
	 */
	public static String KmPersonalInicial(Long vhc_codigo, Date fechaInicial,
			Date fechaFinal) throws GWorkException {

		try {
			final String queryString = "SELECT MIN(TO_NUMBER(S.SER_KILOMETRAJE_ACTUAL)) "
					+ "FROM SERVICE_REGISTRY  S "
					+ "WHERE S.VHC_CODIGO = :p_placa_id "
					+ "AND S.SER_FECHA  BETWEEN  (:p_finicial) and (:p_ffinal)";

			Query query = EntityManagerHelper.getEntityManager()
					.createNativeQuery(queryString);

			query.setParameter("p_placa_id", vhc_codigo);
			query.setParameter("p_finicial", fechaInicial);
			query.setParameter("p_ffinal", fechaFinal);

			if (query.getSingleResult() != null
					&& query.getSingleResult().toString().trim().length() > 0)
				return query.getSingleResult().toString();

		} catch (RuntimeException re) {
			log.error("ConsultaKmPersonalInicial", re);
		}
		return null;
	}

	/**
	 * Km personal final.
	 *
	 * @param vhc_codigo the vhc_codigo
	 * @param fechaInicial the fecha inicial
	 * @param fechaFinal the fecha final
	 * @return the string
	 * @throws GWorkException the g work exception
	 */
	public static String KmPersonalFinal(Long vhc_codigo, Date fechaInicial,
			Date fechaFinal) throws GWorkException {

		try {
			final String queryString = "SELECT MAX(TO_NUMBER(S.SER_KILOMETRAJE_ACTUAL)) "
					+ "FROM SERVICE_REGISTRY  S "
					+ "WHERE S.VHC_CODIGO = :p_placa_id "
					+ "AND S.SER_FECHA  BETWEEN  (:p_finicial) and (:p_ffinal)";

			Query query = EntityManagerHelper.getEntityManager()
					.createNativeQuery(queryString);

			query.setParameter("p_placa_id", vhc_codigo);
			query.setParameter("p_finicial", fechaInicial);
			query.setParameter("p_ffinal", fechaFinal);

			if (query.getSingleResult() != null
					&& query.getSingleResult().toString().trim().length() > 0)
				return query.getSingleResult().toString();

		} catch (RuntimeException re) {
			log.error("ConsultaKmPersonalFinal", re);
		}
		return null;
	}
}