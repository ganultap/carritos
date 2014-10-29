package geniar.siscar.logic.consultas;

import geniar.siscar.model.Inconsistencies;
import geniar.siscar.model.Policies;
import geniar.siscar.model.PoliciesInvoice;
import geniar.siscar.model.PoliciesTypes;
import geniar.siscar.model.PoliciesVehicles;
import geniar.siscar.persistence.EntityManagerHelper;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.log4j.Logger;

/**
 * Maneja todas las consultas referentes a Seguros.
 * 
 * @author Geniar Intelligence.
 * 
 */
public class SearchPolicies {

	private static Logger log = Logger.getLogger(SearchPolicies.class);

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Consulta las facturas de una poliza.
	 * 
	 * @param numeroPoliza
	 *            Número de la poliza por la que se va a consultar.
	 * @return Listado de las facturas de una poliza
	 * @throws GWorkException
	 */
	@SuppressWarnings("unchecked")
	public List<PoliciesInvoice> consultarFacturasDePoliza(Long numeroPoliza)
			throws GWorkException {
		try {
			String queryString = "select model from PoliciesInvoice model "
					+ " AND model.policies.plsNumero=" + numeroPoliza;

			Query query = getEntityManager().createQuery(queryString);

			List<PoliciesInvoice> list = query.getResultList();

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
	 * 
	 * @param numeroPoliza
	 * @return
	 * @throws GWorkException
	 */
	@SuppressWarnings("unchecked")
	public List<PoliciesInvoice> consultarFacturas() throws GWorkException {
		try {
			String queryString = "select model from PoliciesInvoice model order by model.pinFechaFactura DESC";

			Query query = getEntityManager().createQuery(queryString);

			List<PoliciesInvoice> list = query.getResultList();

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

	@SuppressWarnings("unchecked")
	public List<PoliciesVehicles> consultarPvsVHC(String placa)
			throws GWorkException {
		try {
			String queryString = "SELECT model FROM PoliciesVehicles model "
					+ "WHERE model.vehicles.vhcPlacaDiplomatica = '" + placa
					+ "' AND model.pvsEstado > 0 "
					+ "AND model.policies.policiesTypes.pltCodigo > 10 "
					+ "ORDER BY model.pvsFechafin DESC";

			Query query = getEntityManager().createQuery(queryString);

			List<PoliciesVehicles> list = query.getResultList();

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
	 * Consulta el soat activo de un vehiculo.
	 * 
	 * @param idVHC
	 *            Identificador del vehiculo.
	 * @return Soat activo.
	 * @throws GWorkException
	 *             Manejador de excepciones.
	 */
	@SuppressWarnings("unchecked")
	public Policies consultarSoatActivo(Long idVHC) throws GWorkException {
		try {
			String queryString = "select model from PoliciesVehicles model "
					+ "where model.vehicles.vhcCodigo=" + idVHC
					+ " AND model.policies.policiesTypes.pltCodigo=10"
					+ " AND model.pvsEstado=1";

			Query query = getEntityManager().createQuery(queryString);

			List<PoliciesVehicles> list = query.getResultList();

			if (list != null && list.size() > 0) {
				return list.get(0).getPolicies();
			} else
				return null;

		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new GWorkException(Util
					.loadErrorMessageValue("search.not.found"));
		}
	}

	/**
	 * Consulta los {@link PoliciesVehicles} de un vehiculo
	 * 
	 * @param idVHC
	 *            Identificador del vehiculo.
	 * @param idPoliza
	 *            Identificador de la poliza
	 * @return Listado de polizas asociadas a un vehiculo
	 * @throws GWorkException
	 *             Manejador de excepciones.
	 */
	@SuppressWarnings("unchecked")
	public List<PoliciesVehicles> consultarTodosPVs(Long idVHC, Long idPoliza)
			throws GWorkException {
		try {
			String queryString = "select model from PoliciesVehicles model "
					+ "where model.vehicles.vhcCodigo=" + idVHC
					+ " AND model.policies.plsCodigo=" + idPoliza
					+ " AND model.pvsEstado=1";

			Query query = getEntityManager().createQuery(queryString);

			List<PoliciesVehicles> list = query.getResultList();

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
	 * Consulta todas las polizas vigentes en el sistema.
	 * 
	 * @return Listado de polizas activas en el sistema.
	 * @throws GWorkException
	 *             Manejador de excepciones.
	 */
	@SuppressWarnings("unchecked")
	public List<Policies> consultarTodasPolizasVigentes() throws GWorkException {
		try {
			String queryString = "select pol from Policies pol "
					+ "where pol.plsFechafinCobertura>= :fechaActual AND pol.plsEstado=1";

			Query query = getEntityManager().createQuery(queryString);

			query.setParameter("fechaActual", new Date());

			List<Policies> list = query.getResultList();

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
	 * Consulta todas las polizas vigentes en el sistema.
	 * 
	 * @return Listado de polizas activas en el sistema.
	 * @throws GWorkException
	 *             Manejador de excepciones.
	 */
	@SuppressWarnings("unchecked")
	public List<Policies> consultarPolizasVigentesNOSOAT()
			throws GWorkException {
		try {
			String queryString = "SELECT pol FROM Policies pol "
					+ "WHERE pol.plsFechafinCobertura >= :fechaActual "
					+ "AND pol.plsEstado = 1 "
					+ "AND pol.policiesTypes.pltCodigo > 10";

			Query query = getEntityManager().createQuery(queryString);

			query.setParameter("fechaActual", new Date());

			List<Policies> list = query.getResultList();

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
	 * Consulta una poliza por su numero de poliza.
	 * 
	 * @param numeroPoliza
	 *            Numero de la poliza.
	 * @return Poliza consultada.
	 * @throws GWorkException
	 */
	@SuppressWarnings("unchecked")
	public Policies consultarPoliza(Long numeroPoliza) throws GWorkException {
		try {
			String queryString = "select pol from Policies pol "
					+ "where pol.plsNumero=" + numeroPoliza;

			Query query = getEntityManager().createQuery(queryString);

			List<Policies> list = query.getResultList();
			if (list != null && list.size() > 0) {
				return list.get(0);
			} else
				return null;
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.POLNOENCONTRADA"));
		}
	}

	@SuppressWarnings("unchecked")
	public PoliciesInvoice consultarFacturaPorNumeroFactura(String numeroFactura)
			throws GWorkException {
		try {
			String queryString = "select model from PoliciesInvoice model "
					+ "where model.pinNumeroFactura='" + numeroFactura + "'";

			Query query = getEntityManager().createQuery(queryString);

			List<PoliciesInvoice> list = query.getResultList();
			if (list != null && list.size() > 0) {
				return list.get(0);
			} else
				return null;
		} catch (RuntimeException re) {
			throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));
		}
	}

	/**
	 * Consulta todas las polizas activas según su tipo.
	 * 
	 * @param idTipoPoliza
	 *            Identificador del tipo de poliza.
	 * @return Listado de polizas activas de tipo {@link PoliciesTypes}.
	 * @throws GWorkException
	 *             Manejador de excepciones.
	 */
	@SuppressWarnings("unchecked")
	public List<Policies> consultarPolizasPorTipoPoliza(Long idTipoPoliza)
			throws GWorkException {
		try {
			String queryString = "select pol from Policies pol "
					+ "where pol.policiesTypes.pltCodigo=" + idTipoPoliza
					+ " AND pol.plsEstado=1"
					+ " ORDER BY pol.plsFechainicioCobertura DESC AND";

			Query query = getEntityManager().createQuery(queryString);

			List<Policies> list = query.getResultList();
			if (list != null && list.size() > 0) {
				return list;
			} else
				return null;
		} catch (RuntimeException re) {
			throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));
		}
	}

	@SuppressWarnings("unchecked")
	public List<Policies> consultarPolizasSOAT(Long idTipoPoliza)
			throws GWorkException {
		try {
			String queryString = "select pol from Policies pol "
					+ "where pol.policiesTypes.pltCodigo=" + idTipoPoliza
					+ " AND pol.plsEstado=1"
					+ " ORDER BY pol.plsFechainicioCobertura DESC AND";

			Query query = getEntityManager().createQuery(queryString);

			List<Policies> list = query.getResultList();
			if (list != null && list.size() > 0) {
				return list;
			} else
				return null;
		} catch (RuntimeException re) {
			throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));
		}
	}

	@SuppressWarnings("unchecked")
	public static List<PoliciesVehicles> policesVehiclesBySOAT(Long idTipoPoliza)
			throws GWorkException {
		try {
			String queryString = "select polV from PoliciesVehicles polV "
					+ "where polV.policies.policiesTypes.pltCodigo="
					+ idTipoPoliza + " AND polV.policies.plsEstado=1 "
					+ " AND polV.policiesInvoice is null"
					+ " ORDER BY polV.vehicles.vhcPlacaDiplomatica ASC";

			Query query = EntityManagerHelper.getEntityManager().createQuery(
					queryString);

			List<PoliciesVehicles> list = query.getResultList();
			if (list != null && list.size() > 0) {
				return list;
			} else
				return null;
		} catch (RuntimeException re) {
			throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));
		}
	}

	/**
	 * Consulta los soat activos de un vehiculo
	 * 
	 * @param placaVehiculo
	 * @return Listado de soats de un vehiculo.
	 * @throws GWorkException
	 */
	@SuppressWarnings("unchecked")
	public List<PoliciesVehicles> consultarPolizasSOATVehiculo(
			String placaVehiculo) throws GWorkException {
		try {
			String queryString = "select model from PoliciesVehicles model "
					+ "where model.vehicles.vhcPlacaDiplomatica='"
					+ placaVehiculo + "' " + "AND "
					+ "model.policies.policiesTypes.pltCodigo=10 AND "
					+ "model.pvsEstado=1";

			Query query = getEntityManager().createQuery(queryString);

			List<PoliciesVehicles> list = query.getResultList();
			if (list != null && list.size() > 0) {
				return list;
			} else
				return null;
		} catch (RuntimeException re) {
			throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));
		}
	}

	/**
	 * Consulta los soat activos de un vehiculo
	 * 
	 * @param placaVehiculo
	 * @return Listado de soats de un vehiculo.
	 * @throws GWorkException
	 */
	public static PoliciesVehicles polizaSoatByPlaca(Long idVehiculo,
			Long idPoliza) throws GWorkException {
		try {
			String queryString = "select model from PoliciesVehicles model "
					+ "where model.vehicles.vhcCodigo= :idVehiculo "
					+ "AND model.policies.plsCodigo = :idPoliza "
					+ "AND model.policies.policiesTypes.pltCodigo=10 AND "
					+ "model.pvsEstado=1";

			Query query = EntityManagerHelper.getEntityManager().createQuery(
					queryString);
			query.setParameter("idVehiculo", idVehiculo);
			query.setParameter("idPoliza", idPoliza);

			if (query.getResultList() != null
					&& query.getResultList().size() > 0) {
				return (PoliciesVehicles) query.getSingleResult();
			} else
				return null;
		} catch (RuntimeException re) {
			throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));
		}
	}

	/**
	 * Consulta las inconsistencias de una factura.
	 * 
	 * @param Codigo
	 *            de la factura (PIN_ID)
	 * @return Listado de las Inconsistencias de la factura
	 * @throws GWorkException
	 */
	@SuppressWarnings("unchecked")
	public List<Inconsistencies> consultarInconsistenciasFacturas(
			Long CodigoFactura) throws GWorkException {
		try {
			String queryString = "SELECT model FROM Inconsistencies model "
					+ "WHERE model.policiesInvoice.pinId =:CodigoFactura "
					+ "AND model.incEstado = 1 "
					+ "ORDER BY model.vhcPlaca ASC";

			Query query = getEntityManager().createQuery(queryString);

			query.setParameter("CodigoFactura", CodigoFactura);

			List<Inconsistencies> list = query.getResultList();

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
	 * Consulta las inconsistencias de una factura.
	 * 
	 * @param Codigo
	 *            de la factura (PIN_ID)
	 * @return Listado de las Inconsistencias de la factura
	 * @throws GWorkException
	 */
	@SuppressWarnings("unchecked")
	public List<Inconsistencies> consultarInconsistenciasFacturasTipoInco(
			Long CodigoFactura) throws GWorkException {
		try {
			// PIN_ID = 123 and ICT_ID in (1,3)
			String queryString = "SELECT model FROM Inconsistencies model "
					+ "WHERE model.policiesInvoice.pinId =:CodigoFactura "
					+ "AND model.incEstado = 1 "
					+ "AND model.inconsistenciesTypes.ictId IN (1,3)"
					+ "ORDER BY model.vhcPlaca ASC";

			Query query = getEntityManager().createQuery(queryString);

			query.setParameter("CodigoFactura", CodigoFactura);

			List<Inconsistencies> list = query.getResultList();

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

	@SuppressWarnings("unchecked")
	public PoliciesInvoice consultarFacturaNumFacPoliza(String numeroFactura,
			Long Poliza) throws GWorkException {
		try {
			String queryString = "select model from PoliciesInvoice model "
					+ "where model.pinNumeroFactura='" + numeroFactura + "'"
					+ "and model.policies.plsNumero = " + Poliza;

			Query query = getEntityManager().createQuery(queryString);

			List<PoliciesInvoice> list = query.getResultList();
			if (list != null && list.size() > 0) {
				return list.get(0);
			} else
				return null;
		} catch (RuntimeException re) {
			throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));
		}
	}

	@SuppressWarnings("unchecked")
	public static List<PoliciesInvoice> consultarFacturasSOAT()
			throws GWorkException {
		try {
			String queryString = "select distinct(pInv) from PoliciesInvoice pInv,  PoliciesVehicles pVh "
					+ "WHERE pInv.pinId = pVh.policiesInvoice.pinId ";

			Query query = EntityManagerHelper.getEntityManager().createQuery(
					queryString);

			if (query.getResultList() != null
					&& query.getResultList().size() > 0
					&& !query.getResultList().isEmpty())
				return (List<PoliciesInvoice>) query.getResultList();
			else
				throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));

		} catch (Exception e) {
			log.error("Error consultando factiras SOAT: " + e.getMessage(), e);
			throw new GWorkException(e.getMessage());
		}

	}
}