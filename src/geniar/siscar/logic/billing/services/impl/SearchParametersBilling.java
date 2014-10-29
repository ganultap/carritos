package geniar.siscar.logic.billing.services.impl;

import geniar.siscar.model.AccountingParameters;
import geniar.siscar.model.CargaPrepago;
import geniar.siscar.model.CostsCentersVehicles;
import geniar.siscar.model.HeaderProof;
import geniar.siscar.model.Period;
import geniar.siscar.model.ProofType;
import geniar.siscar.model.VehiclesAssignation;
import geniar.siscar.persistence.EntityManagerHelper;
import geniar.siscar.util.ParametersUtil;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

import java.util.List;

import javax.persistence.Query;

/**
 * The Class SearchParametersBilling.
 */
public class SearchParametersBilling {

	/**
	 * List proof type order.
	 *
	 * @return the list
	 * @throws GWorkException the g work exception
	 */
	@SuppressWarnings("unchecked")
	public List<ProofType> listProofTypeOrder() throws GWorkException {

		final String queryString = "select model from ProofType model ORDER BY model.prtNombre ASC";
		Query query = EntityManagerHelper.getEntityManager().createQuery(
				queryString);

		return query.getResultList();
	}

	/**
	 * List period order.
	 *
	 * @return the list
	 * @throws GWorkException the g work exception
	 */
	@SuppressWarnings("unchecked")
	public List<Period> listPeriodOrder() throws GWorkException {

		final String queryString = "select model from Period model ORDER BY model.perNombre ASC";
		Query query = EntityManagerHelper.getEntityManager().createQuery(
				queryString);

		return query.getResultList();
	}

	/**
	 * List period fuel.
	 *
	 * @return the list
	 * @throws GWorkException the g work exception
	 */
	@SuppressWarnings("unchecked")
	public List<Period> listPeriodFuel() throws GWorkException {

		final String queryString = "select model from Period model where model.noveltyTypes.ntId = :novedadCombustible "
				+ "ORDER BY model.perNombre ASC";
		Query query = EntityManagerHelper.getEntityManager().createQuery(
				queryString);
		query.setParameter("novedadCombustible", ParametersUtil.NOVEDAD_COMB);

		return query.getResultList();
	}

	/**
	 * List period assignation.
	 *
	 * @return the list
	 * @throws GWorkException the g work exception
	 */
	@SuppressWarnings("unchecked")
	public List<Period> listPeriodAssignation() throws GWorkException {

		final String queryString = "select model from Period model where model.noveltyTypes.ntId = :novedadCombustible "
			+ " ORDER BY model.perFechaFin, model.perNombre DESC";
		
		Query query = EntityManagerHelper.getEntityManager().createQuery(
				queryString);
		query.setParameter("novedadCombustible", ParametersUtil.NOVEDAD_ASIG);

		return query.getResultList();
	}

	/**
	 * Lista comprobante combustible.
	 *
	 * @return the list
	 */
	@SuppressWarnings("unchecked")
	public List<VehiclesAssignation> listaComprobanteCombustible() {

		final String queryString = "select model from VehiclesAssignation model where model.vhaFechaEntrega is not null and "
				+ "model.vhaFechaDev is null";
		Query query = EntityManagerHelper.getEntityManager().createQuery(
				queryString);
		return query.getResultList();
	}

	/**
	 * List asginaciones combustible.
	 *
	 * @return the list
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> listAsginacionesCombustible() {

		final String queryString = "select vha.vhaCodigo, vha.vehicles.vhcPlacaDiplomatica,ccVhc.costsCenters.cocNumero, "
				+ "vha.requests.usersByRqsUser.usrNombre, vha.requests.legateesTypes.lgtNombre,ccVhc.ccrPorcentaje "
				+ "from VehiclesAssignation vha, Requests rqs,CostsCentersVehicles ccVhc "
				+ "WHERE vha.requests.rqsCodigo = ccVhc.requests.rqsCodigo "
				+ "AND vha.vhaFechaEntrega is not null "
				+ "AND vha.vhaFechaDev is null "
				+ "AND vha.requests.legateesTypes.lgtCodigo IN (:OF,:OFS,:PROGRAMAS) "
				+ "GROUP BY vha.vhaCodigo,vha.vehicles.vhcPlacaDiplomatica,ccVhc.costsCenters.cocNumero,"
				+ "vha.requests.usersByRqsUser.usrNombre, vha.requests.legateesTypes.lgtNombre,ccVhc.ccrPorcentaje "
				+ "ORDER BY vha.vehicles.vhcPlacaDiplomatica ASC";
		Query query = EntityManagerHelper.getEntityManager().createQuery(
				queryString);
		query.setParameter("OF", ParametersUtil.LGT_OF);
		query.setParameter("OFS", ParametersUtil.LGT_OFS);
		query.setParameter("PROGRAMAS", ParametersUtil.LGT_PROGRAMAS);

		return query.getResultList();

	}

	/**
	 * List asginaciones by period.
	 *
	 * @return the list
	 * @throws GWorkException the g work exception
	 */
	@SuppressWarnings("unchecked")
	public static List<CostsCentersVehicles> listAsginacionesByPeriod()
			throws GWorkException {

		final String queryString = "select model from CostsCentersVehicles model "
				+ "WHERE  model.vehiclesAssignation.vhaFechaEntrega is not null "
				+ "AND model.vehiclesAssignation.vhaFechaDev is null "
				+ "AND model.vehiclesAssignation.requests.legateesTypes.lgtCodigo IN (:OF,:OFS,:PROGRAMAS) "
				+ "AND model.vehiclesAssignation.requests.requestsClasses.rqcCodigo = :CLASE_ASIGNACION "
				+ "AND model.ccrEstado = :activo "
				+ "ORDER BY model.vehiclesAssignation.vehicles.vhcPlacaDiplomatica ASC";
		Query query = EntityManagerHelper.getEntityManager().createQuery(
				queryString);
		query.setParameter("OF", ParametersUtil.LGT_OF);
		query.setParameter("OFS", ParametersUtil.LGT_OFS);
		query.setParameter("PROGRAMAS", ParametersUtil.LGT_PROGRAMAS);
		query.setParameter("CLASE_ASIGNACION", ParametersUtil.CLASE_ASIGNACION);
		query.setParameter("activo", Util.loadMessageValue("ESTADO.ACTIVO"));

		return query.getResultList();

	}

	/**
	 * Validar si ya se genero comprobante a una asignacion en un periodo.
	 *
	 * @param idPeiodo the id peiodo
	 * @param vhaCodigo the vha codigo
	 * @param tipoComprobante the tipo comprobante
	 * @return true, if successful
	 * @throws GWorkException the g work exception
	 */
	public static boolean validarPeriodoAsignacion(Long idPeiodo,
			Long vhaCodigo, Long tipoComprobante) throws GWorkException {

		final String queryString = "select model from VhaAoaApp model where model.vehiclesAssignation.vhaCodigo= :vhaCodigo "
				+ "AND model.headerProof.period.perId= :idPeiodo "
				+ "AND model.headerProof.proofType.prtCodigo= :tipoComprobante";
		Query query = EntityManagerHelper.getEntityManager().createQuery(
				queryString);

		query.setParameter("idPeiodo", idPeiodo);
		query.setParameter("vhaCodigo", vhaCodigo);
		query.setParameter("tipoComprobante", tipoComprobante);

		if (query.getResultList() != null && query.getResultList().size() > 0
				&& query.getResultList().get(0) != null)
			return true;
		else
			return false;
	}

	/**
	 * Lista carga prepago.
	 *
	 * @return the list
	 */
	@SuppressWarnings("unchecked")
	public List<CargaPrepago> listaCargaPrepago() {
		try {

			final String queryString = "SELECT model FROM CargaPrepago model ORDER BY model.placa";
			Query query = EntityManagerHelper.getEntityManager().createQuery(
					queryString);

			return query.getResultList();

		} catch (RuntimeException re) {
			throw re;
		}

	}

	/**
	 * Consultar cabecera cmprbnt by group id.
	 *
	 * @param hepId the hep id
	 * @return the header proof
	 * @throws GWorkException the g work exception
	 */
	public static HeaderProof consultarCabeceraCmprbntByGroupId(Long hepId)
			throws GWorkException {

		final String queryString = "SELECT model FROM HeaderProof model where model.hepId= :hepId";
		Query query = EntityManagerHelper.getEntityManager().createQuery(
				queryString);

		query.setParameter("hepId", hepId);

		if (query.getResultList() != null && query.getResultList().size() > 0
				&& !query.getResultList().isEmpty())
			return (HeaderProof) query.getSingleResult();
		else
			throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));
	}

	/**
	 * Last header proof.
	 *
	 * @return the long
	 * @throws GWorkException the g work exception
	 */
	public static Long lastHeaderProof() throws GWorkException {

		final String queryString = "SELECT max(model.hepId) FROM HeaderProof model";
//		EntityManagerHelper.getEntityManager().clear();
		Query query = EntityManagerHelper.getEntityManager().createQuery(
				queryString);
		return (Long) query.getSingleResult();

	}

	/**
	 * Find hp by id.
	 *
	 * @param idHeaderProof the id header proof
	 * @return the header proof
	 * @throws GWorkException the g work exception
	 */
	public static HeaderProof findHPById(Long idHeaderProof)
			throws GWorkException {

		final String queryString = "SELECT model FROM HeaderProof model "
				+ "WHERE model.hepId = :idHeaderProof";
		EntityManagerHelper.getEntityManager().clear();
		Query query = EntityManagerHelper.getEntityManager().createQuery(
				queryString);
		query.setParameter("idHeaderProof", idHeaderProof);
		if (query.getResultList() != null && query.getResultList().size() > 0
				&& !query.getResultList().isEmpty())
			return (HeaderProof) query.getSingleResult();

		else
			return null;

	}

	/**
	 * Validar existencia comprobante.
	 *
	 * @param periodo the periodo
	 * @return true, if successful
	 * @throws GWorkException the g work exception
	 */
	public static boolean validarExistenciaComprobante(Long periodo)
			throws GWorkException {

		final String queryString = "SELECT model FROM HeaderProof model WHERE model.period.perId=:periodo AND model.proofType.prtCodigo=3";
		Query query = EntityManagerHelper.getEntityManager().createQuery(
				queryString);
		query.setParameter("periodo", periodo);

		if (query.getResultList() != null && query.getResultList().size() > 0
				&& !query.getResultList().isEmpty())
			return true;
		else
			return false;

	}

	/**
	 * Consultar paremeter.
	 *
	 * @param tipoMovimiento the tipo movimiento
	 * @param cargo the cargo
	 * @param tipoTransaccion the tipo transaccion
	 * @param tipoAsignacion the tipo asignacion
	 * @param idTipoLocalizacion the id tipo localizacion
	 * @return the accounting parameters
	 * @throws GWorkException the g work exception
	 */
	public static AccountingParameters consultarParemeter(Long tipoMovimiento,
			Long cargo, Long tipoTransaccion, Long tipoAsignacion,
			Long idTipoLocalizacion) throws GWorkException {

		final String queryString = "select model from AccountingParameters model where model.chargeType.cgtId= :cargo "
				+ "AND model.movementType.mvmId= :tipoMovimiento and model.transactionType.tstId= :tipoTransaccion "
				+ "AND model.legateesTypes.lgtCodigo= :tipoAsignacion " 
				+ "AND model.locationsTypes.lctCodigo= :tipoLocalizacion ";
		Query query = EntityManagerHelper.createQuery(queryString);
		query.setParameter("cargo", cargo);
		query.setParameter("tipoMovimiento", tipoMovimiento);
		query.setParameter("tipoTransaccion", tipoTransaccion);
		query.setParameter("tipoAsignacion", tipoAsignacion);
		query.setParameter("tipoLocalizacion", idTipoLocalizacion);

		if (query.getResultList() == null || query.getResultList().size() == 0
				|| query.getResultList().isEmpty())
			throw new GWorkException("No existe parámetro contable");

		return (AccountingParameters) query.getSingleResult();
	}
	
	/**
	 * Consultar paremeter.
	 *
	 * @param tipoMovimiento the tipo movimiento
	 * @param cargo the cargo
	 * @param tipoTransaccion the tipo transaccion
	 * @param tipoAsignacion the tipo asignacion
	 * @return the accounting parameters
	 * @throws GWorkException the g work exception
	 */
	public static AccountingParameters consultarParemeter(Long tipoMovimiento,
			Long cargo, Long tipoTransaccion, Long tipoAsignacion)
			throws GWorkException {

		final String queryString = "select model from AccountingParameters model where model.chargeType.cgtId= :cargo "
				+ "AND model.movementType.mvmId= :tipoMovimiento and model.transactionType.tstId= :tipoTransaccion "
				+ "AND model.legateesTypes.lgtCodigo= :tipoAsignacion";
		Query query = EntityManagerHelper.createQuery(queryString);
		query.setParameter("cargo", cargo);
		query.setParameter("tipoMovimiento", tipoMovimiento);
		query.setParameter("tipoTransaccion", tipoTransaccion);
		query.setParameter("tipoAsignacion", tipoAsignacion);

		if (query.getResultList() == null || query.getResultList().size() == 0
				|| query.getResultList().isEmpty())
			throw new GWorkException("No existe parámetro contable");

		return (AccountingParameters) query.getSingleResult();
	}
}