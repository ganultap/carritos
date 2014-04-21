package geniar.siscar.logic.consultas;

import geniar.siscar.model.Account;
import geniar.siscar.model.AccountingParameters;
import geniar.siscar.model.Attribute;
import geniar.siscar.model.Auxiliar;
import geniar.siscar.model.ChargeType;
import geniar.siscar.model.Company;
import geniar.siscar.model.ControlType;
import geniar.siscar.model.CostsCenters;
import geniar.siscar.model.DescriptionType;
import geniar.siscar.model.DocumentOne;
import geniar.siscar.model.DocumentOneType;
import geniar.siscar.model.DocumentTwo;
import geniar.siscar.model.DocumentTwoType;
import geniar.siscar.model.Future;
import geniar.siscar.model.LegateesTypes;
import geniar.siscar.model.Line;
import geniar.siscar.model.MovementType;
import geniar.siscar.model.TransactionType;
import geniar.siscar.model.LocationsTypes;
import geniar.siscar.persistence.EntityManagerHelper;
import geniar.siscar.persistence.LegateesTypesDAO;
import geniar.siscar.persistence.LocationsTypesDAO;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * The Class SearchAccountingParameters.
 */
public class SearchAccountingParameters {
	
	/**
	 * Gets the entity manager.
	 *
	 * @return the entity manager
	 */
	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Consultar parametro contable.
	 *
	 * @param idParametro the id parametro
	 * @return the accounting parameters
	 * @throws GWorkException the g work exception
	 */
	@SuppressWarnings("unchecked")
	public AccountingParameters consultarParametroContable(Long idParametro)
			throws GWorkException {
		try {
			final String queryString = "select model from AccountingParameters"
					+ " model where model.acpId=" + idParametro
					+ " ORDER BY model.acpId ASC";
			Query query = getEntityManager().createQuery(queryString);

			List<AccountingParameters> list = query.getResultList();
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
	 * Consultar parametro contable.
	 *
	 * @param idTipoAsignacion the id tipo asignacion
	 * @param idTipoTransaccion the id tipo transaccion
	 * @param idTipoMovimiento the id tipo movimiento
	 * @return the list
	 * @throws GWorkException the g work exception
	 */
	@SuppressWarnings("unchecked")
	public List<AccountingParameters> consultarParametroContable(
			Long idTipoAsignacion, Long idTipoTransaccion, Long idTipoMovimiento)
			throws GWorkException {
		try {
			final String queryString = "select model from AccountingParameters model "
					+ "where model.legateesTypes.lgtCodigo="
					+ idTipoAsignacion
					+ " AND "
					+ "model.transactionType.tstId="
					+ idTipoTransaccion
					+ " AND "
					+ "model.movementType.mvmId="
					+ idTipoMovimiento + " ORDER BY model.acpId ASC";
			Query query = getEntityManager().createQuery(queryString);

			List<AccountingParameters> list = query.getResultList();
			if (list != null && list.size() > 0) {
				return list;
			} else
				return null;
		} catch (RuntimeException re) {
			throw new GWorkException(Util
					.loadErrorMessageValue("search.not.found"));
		}
	}

	/**
	 * Consultar parametro contable4 parametros.
	 *
	 * @param idTipoAsignacion the id tipo asignacion
	 * @param idTipoTransaccion the id tipo transaccion
	 * @param idTipoMovimiento the id tipo movimiento
	 * @param idCuentaContable the id cuenta contable
	 * @return the list
	 * @throws GWorkException the g work exception
	 */
	@SuppressWarnings("unchecked")
	public List<AccountingParameters> consultarParametroContable4Parametros(
			Long idTipoAsignacion, Long idTipoTransaccion,
			Long idTipoMovimiento, Long idCuentaContable) throws GWorkException {
		try {
			final String queryString = "select model from AccountingParameters model "
					+ "where model.legateesTypes.lgtCodigo="
					+ idTipoAsignacion
					+ " AND "
					+ "model.transactionType.tstId="
					+ idTipoTransaccion
					+ " AND "
					+ "model.account.accId="
					+ idCuentaContable
					+ " AND "
					+ "model.movementType.mvmId="
					+ idTipoMovimiento + " ORDER BY model.acpId ASC";
			Query query = getEntityManager().createQuery(queryString);

			List<AccountingParameters> list = query.getResultList();
			if (list != null && list.size() > 0) {
				return list;
			} else
				return null;
		} catch (RuntimeException re) {
			throw new GWorkException(Util
					.loadErrorMessageValue("search.not.found"));
		}
	}

	/**
	 * Consultar parametro contable por asignacion.
	 *
	 * @param idTipoAsignacion the id tipo asignacion
	 * @return the list
	 * @throws GWorkException the g work exception
	 */
	public List<AccountingParameters> consultarParametroContablePorAsignacion(
			Long idTipoAsignacion) throws GWorkException {
		try {
			LegateesTypes legateesTypes = new LegateesTypesDAO()
					.findById(idTipoAsignacion);

			if (legateesTypes != null
					&& legateesTypes.getAccountingParameterses() != null
					&& legateesTypes.getAccountingParameterses().size() > 0) {

				List<AccountingParameters> myList = new ArrayList<AccountingParameters>();

				Iterator<AccountingParameters> iterator = legateesTypes
						.getAccountingParameterses().iterator();

				while (iterator.hasNext()) {
					myList.add(iterator.next());
				}
				return myList;
			} else {
				throw new GWorkException(Util
						.loadErrorMessageValue("search.not.found"));
			}
		} catch (RuntimeException re) {
			throw new GWorkException(Util
					.loadErrorMessageValue("search.not.found"));
		}
	}

	/**
	 * Consultar parametro contable por transaccion.
	 *
	 * @param idTipoTransaccion the id tipo transaccion
	 * @return the list
	 * @throws GWorkException the g work exception
	 */
	public List<AccountingParameters> consultarParametroContablePorTransaccion(
			Long idTipoTransaccion) throws GWorkException {
		try {
			TransactionType transactionType = consultarTransactionType(idTipoTransaccion);

			if (transactionType != null
					&& transactionType.getAccountingParameterses() != null
					&& transactionType.getAccountingParameterses().size() > 0) {

				List<AccountingParameters> myList = new ArrayList<AccountingParameters>();

				Iterator<AccountingParameters> iterator = transactionType
						.getAccountingParameterses().iterator();

				while (iterator.hasNext()) {
					myList.add(iterator.next());
				}
				return myList;
			} else {
				throw new GWorkException(Util
						.loadErrorMessageValue("search.not.found"));
			}
		} catch (RuntimeException re) {
			throw new GWorkException(Util
					.loadErrorMessageValue("search.not.found"));
		}
	}

	/**
	 * Consultar parametro contable por movimiento.
	 *
	 * @param idTipoMovimiento the id tipo movimiento
	 * @return the list
	 * @throws GWorkException the g work exception
	 */
	public List<AccountingParameters> consultarParametroContablePorMovimiento(
			Long idTipoMovimiento) throws GWorkException {
		try {

			MovementType movementType = consultarMovementTypes(idTipoMovimiento);

			if (movementType != null
					&& movementType.getAccountingParameterses() != null
					&& movementType.getAccountingParameterses().size() > 0) {

				List<AccountingParameters> myList = new ArrayList<AccountingParameters>();

				Iterator<AccountingParameters> iterator = movementType
						.getAccountingParameterses().iterator();

				while (iterator.hasNext()) {
					myList.add(iterator.next());
				}
				return myList;
			} else {
				throw new GWorkException(Util
						.loadErrorMessageValue("search.not.found"));
			}
		} catch (RuntimeException re) {
			throw new GWorkException(Util
					.loadErrorMessageValue("search.not.found"));
		}
	}

	/**
	 * Consultar parametro contable por cuenta contable.
	 *
	 * @param idCuentaContable the id cuenta contable
	 * @return the list
	 * @throws GWorkException the g work exception
	 */
	public List<AccountingParameters> consultarParametroContablePorCuentaContable(
			Long idCuentaContable) throws GWorkException {
		try {

			Account account = consultarCuentaContable(idCuentaContable);

			if (account != null && account.getAccountingParameterses() != null
					&& account.getAccountingParameterses().size() > 0) {

				List<AccountingParameters> myList = new ArrayList<AccountingParameters>();

				Iterator<AccountingParameters> iterator = account
						.getAccountingParameterses().iterator();

				while (iterator.hasNext()) {
					myList.add(iterator.next());
				}
				return myList;
			} else {
				throw new GWorkException(Util
						.loadErrorMessageValue("search.not.found"));
			}
		} catch (RuntimeException re) {
			throw new GWorkException(Util
					.loadErrorMessageValue("search.not.found"));
		}
	}

	/**
	 * Consultar transaction type.
	 *
	 * @return the list
	 * @throws GWorkException the g work exception
	 */
	@SuppressWarnings("unchecked")
	public List<TransactionType> consultarTransactionType()
			throws GWorkException {
		try {
			final String queryString = "select model from TransactionType model"
					+ " ORDER BY model.tstNombre ASC";
			Query query = getEntityManager().createQuery(queryString);

			List<TransactionType> list = query.getResultList();
			if (list.size() > 0) {
				return list;
			} else
				return null;
		} catch (RuntimeException re) {
			throw new GWorkException(Util
					.loadErrorMessageValue("search.not.found"));
		}
	}

	/**
	 * Consultar transaction type.
	 *
	 * @param idTipoTransaccion the id tipo transaccion
	 * @return the transaction type
	 * @throws GWorkException the g work exception
	 */
	@SuppressWarnings("unchecked")
	public TransactionType consultarTransactionType(Long idTipoTransaccion)
			throws GWorkException {
		try {
			final String queryString = "select model from TransactionType model "
					+ "where model.tstId=" + idTipoTransaccion;
			Query query = getEntityManager().createQuery(queryString);

			List<TransactionType> list = query.getResultList();
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
	 * Consultar movement types.
	 *
	 * @return the list
	 * @throws GWorkException the g work exception
	 */
	@SuppressWarnings("unchecked")
	public List<MovementType> consultarMovementTypes() throws GWorkException {
		try {
			final String queryString = "select model from MovementType model"
					+ " ORDER BY model.mvmNombre ASC";
			Query query = getEntityManager().createQuery(queryString);

			List<MovementType> list = query.getResultList();
			if (list.size() > 0) {
				return list;
			} else
				return null;
		} catch (RuntimeException re) {
			throw new GWorkException(Util
					.loadErrorMessageValue("search.not.found"));
		}
	}

	/**
	 * Consultar movement types.
	 *
	 * @param idTipoMovimiento the id tipo movimiento
	 * @return the movement type
	 * @throws GWorkException the g work exception
	 */
	@SuppressWarnings("unchecked")
	public MovementType consultarMovementTypes(Long idTipoMovimiento)
			throws GWorkException {
		try {
			final String queryString = "select model from MovementType model where model.mvmId="
					+ idTipoMovimiento;
			Query query = getEntityManager().createQuery(queryString);

			List<MovementType> list = query.getResultList();
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
	 * Consultar cuenta contable.
	 *
	 * @param idCuentaContable the id cuenta contable
	 * @return the account
	 * @throws GWorkException the g work exception
	 */
	@SuppressWarnings("unchecked")
	public Account consultarCuentaContable(Long idCuentaContable)
			throws GWorkException {
		try {
			final String queryString = "select model from Account model where model.accId="
					+ idCuentaContable;
			Query query = getEntityManager().createQuery(queryString);

			List<Account> list = query.getResultList();
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
	 * Consultar company.
	 *
	 * @return the list
	 * @throws GWorkException the g work exception
	 */
	@SuppressWarnings("unchecked")
	public List<Company> consultarCompany() throws GWorkException {
		try {
			final String queryString = "select model from Company model ORDER BY model.cmpNombre ASC";
			Query query = getEntityManager().createQuery(queryString);

			List<Company> list = query.getResultList();
			if (list.size() > 0) {
				return list;
			} else
				return null;
		} catch (RuntimeException re) {
			throw new GWorkException(Util
					.loadErrorMessageValue("search.not.found"));
		}
	}

	/**
	 * Consultar charge type.
	 *
	 * @return the list
	 * @throws GWorkException the g work exception
	 */
	@SuppressWarnings("unchecked")
	public List<ChargeType> consultarChargeType() throws GWorkException {
		try {
			final String queryString = "select model from ChargeType model ORDER BY model.cgtNombre ASC";
			Query query = getEntityManager().createQuery(queryString);

			List<ChargeType> list = query.getResultList();
			if (list.size() > 0) {
				return list;
			} else
				return null;
		} catch (RuntimeException re) {
			throw new GWorkException(Util
					.loadErrorMessageValue("search.not.found"));
		}
	}

	/**
	 * Consultar account.
	 *
	 * @return the list
	 * @throws GWorkException the g work exception
	 */
	@SuppressWarnings("unchecked")
	public List<Account> consultarAccount() throws GWorkException {
		try {
			final String queryString = "select model from Account model ORDER BY model.accNumeroCuenta ASC";
			Query query = getEntityManager().createQuery(queryString);

			List<Account> list = query.getResultList();
			if (list.size() > 0) {
				return list;
			} else
				return null;
		} catch (RuntimeException re) {
			throw new GWorkException(Util
					.loadErrorMessageValue("search.not.found"));
		}
	}

	/**
	 * Consultar line.
	 *
	 * @return the list
	 * @throws GWorkException the g work exception
	 */
	@SuppressWarnings("unchecked")
	public List<Line> consultarLine() throws GWorkException {
		try {
			final String queryString = "select model from Line model ORDER BY model.linValor ASC";
			Query query = getEntityManager().createQuery(queryString);

			List<Line> list = query.getResultList();
			if (list.size() > 0) {
				return list;
			} else
				return null;
		} catch (RuntimeException re) {
			throw new GWorkException(Util
					.loadErrorMessageValue("search.not.found"));
		}
	}

	/**
	 * Consultar cost centers.
	 *
	 * @return the list
	 * @throws GWorkException the g work exception
	 */
	@SuppressWarnings("unchecked")
	public List<CostsCenters> consultarCostCenters() throws GWorkException {
		try {
			final String queryString = "select model from CostsCenters model ORDER BY model.cocNumero ASC";
			Query query = getEntityManager().createQuery(queryString);

			List<CostsCenters> list = query.getResultList();
			if (list.size() > 0) {
				return list;
			} else
				return null;
		} catch (RuntimeException re) {
			throw new GWorkException(Util
					.loadErrorMessageValue("search.not.found"));
		}
	}

	/**
	 * Consultar auxiliares.
	 *
	 * @return the list
	 * @throws GWorkException the g work exception
	 */
	@SuppressWarnings("unchecked")
	public List<Auxiliar> consultarAuxiliares() throws GWorkException {
		try {
			final String queryString = "select model from Auxiliar model ORDER BY model.auxValor ASC";
			Query query = getEntityManager().createQuery(queryString);

			List<Auxiliar> list = query.getResultList();
			if (list.size() > 0) {
				return list;
			} else
				return null;
		} catch (RuntimeException re) {
			throw new GWorkException(Util
					.loadErrorMessageValue("search.not.found"));
		}
	}

	/**
	 * Consultar future.
	 *
	 * @return the list
	 * @throws GWorkException the g work exception
	 */
	@SuppressWarnings("unchecked")
	public List<Future> consultarFuture() throws GWorkException {
		try {
			final String queryString = "select model from Future model ORDER BY model.freValor ASC";
			Query query = getEntityManager().createQuery(queryString);

			List<Future> list = query.getResultList();
			if (list.size() > 0) {
				return list;
			} else
				return null;
		} catch (RuntimeException re) {
			throw new GWorkException(Util
					.loadErrorMessageValue("search.not.found"));
		}
	}

	/**
	 * Consultar control type.
	 *
	 * @return the list
	 * @throws GWorkException the g work exception
	 */
	@SuppressWarnings("unchecked")
	public List<ControlType> consultarControlType() throws GWorkException {
		try {
			final String queryString = "select model from ControlType model ORDER BY model.cttValor ASC";
			Query query = getEntityManager().createQuery(queryString);

			List<ControlType> list = query.getResultList();
			if (list.size() > 0) {
				return list;
			} else
				return null;
		} catch (RuntimeException re) {
			throw new GWorkException(Util
					.loadErrorMessageValue("search.not.found"));
		}
	}

	/**
	 * Consultar document one type.
	 *
	 * @return the list
	 * @throws GWorkException the g work exception
	 */
	@SuppressWarnings("unchecked")
	public List<DocumentOneType> consultarDocumentOneType()
			throws GWorkException {
		try {
			final String queryString = "select model from DocumentOneType model ORDER BY model.dotName ASC";
			Query query = getEntityManager().createQuery(queryString);

			List<DocumentOneType> list = query.getResultList();
			if (list.size() > 0) {
				return list;
			} else
				return null;
		} catch (RuntimeException re) {
			throw new GWorkException(Util
					.loadErrorMessageValue("search.not.found"));
		}
	}

	/**
	 * Consultar document two type.
	 *
	 * @return the list
	 * @throws GWorkException the g work exception
	 */
	@SuppressWarnings("unchecked")
	public List<DocumentTwoType> consultarDocumentTwoType()
			throws GWorkException {
		try {
			final String queryString = "select model from DocumentTwoType model ORDER BY model.dttName ASC";
			Query query = getEntityManager().createQuery(queryString);

			List<DocumentTwoType> list = query.getResultList();
			if (list.size() > 0) {
				return list;
			} else
				return null;
		} catch (RuntimeException re) {
			throw new GWorkException(Util
					.loadErrorMessageValue("search.not.found"));
		}
	}

	/**
	 * Consultar document one.
	 *
	 * @return the list
	 * @throws GWorkException the g work exception
	 */
	@SuppressWarnings("unchecked")
	public List<DocumentOne> consultarDocumentOne() throws GWorkException {
		try {
			final String queryString = "select model from DocumentOne model ORDER BY model.dcoNumero ASC";
			Query query = getEntityManager().createQuery(queryString);

			List<DocumentOne> list = query.getResultList();
			if (list.size() > 0) {
				return list;
			} else
				return null;
		} catch (RuntimeException re) {
			throw new GWorkException(Util
					.loadErrorMessageValue("search.not.found"));
		}
	}
	
	/**
	 * Consultar locations types.
	 *
	 * @return the list
	 * @throws GWorkException the g work exception
	 */
	public List<LocationsTypes> consultarLocationsTypes() throws GWorkException {
		try {
			List<LocationsTypes> list = new LocationsTypesDAO().findAll();
			if (list.size() > 0) {
				return list;
			} else
				return null;
		} catch (RuntimeException re) {
			throw new GWorkException(Util.
					loadErrorMessageValue("search.not.found"));
		}
	}
	
	/**
	 * Consultar document two.
	 *
	 * @return the list
	 * @throws GWorkException the g work exception
	 */
	@SuppressWarnings("unchecked")
	public List<DocumentTwo> consultarDocumentTwo() throws GWorkException {
		try {
			final String queryString = "select model from DocumentTwo model ORDER BY model.dctNumero ASC";
			Query query = getEntityManager().createQuery(queryString);

			List<DocumentTwo> list = query.getResultList();
			if (list.size() > 0) {
				return list;
			} else
				return null;
		} catch (RuntimeException re) {
			throw new GWorkException(Util
					.loadErrorMessageValue("search.not.found"));
		}
	}

	/**
	 * Consultar description type.
	 *
	 * @return the list
	 * @throws GWorkException the g work exception
	 */
	@SuppressWarnings("unchecked")
	public List<DescriptionType> consultarDescriptionType()
			throws GWorkException {
		try {
			final String queryString = "select model from DescriptionType model ORDER BY model.dstValor ASC";
			Query query = getEntityManager().createQuery(queryString);

			List<DescriptionType> list = query.getResultList();
			if (list.size() > 0) {
				return list;
			} else
				return null;
		} catch (RuntimeException re) {
			throw new GWorkException(Util
					.loadErrorMessageValue("search.not.found"));
		}
	}

	/**
	 * Consultar attribute.
	 *
	 * @return the list
	 * @throws GWorkException the g work exception
	 */
	@SuppressWarnings("unchecked")
	public List<Attribute> consultarAttribute() throws GWorkException {
		try {
			final String queryString = "select model from Attribute model";
			Query query = getEntityManager().createQuery(queryString);

			List<Attribute> list = query.getResultList();
			if (list.size() > 0) {
				return list;
			} else
				return null;
		} catch (RuntimeException re) {
			throw new GWorkException(Util
					.loadErrorMessageValue("search.not.found"));
		}
	}

	/**
	 * Consultar parametro contable por transaccion movimiento.
	 *
	 * @param idTipoTransaccion the id tipo transaccion
	 * @param idTipoMovimiento the id tipo movimiento
	 * @return the list
	 * @throws GWorkException the g work exception
	 */
	@SuppressWarnings("unchecked")
	public List<AccountingParameters> consultarParametroContablePorTransaccionMovimiento(
			Long idTipoTransaccion, Long idTipoMovimiento)
			throws GWorkException {
		try {
			final String queryString = "select model from AccountingParameters model "
					+ "where model.transactionType.tstId="
					+ idTipoTransaccion
					+ " AND "
					+ "model.movementType.mvmId="
					+ idTipoMovimiento
					+ " ORDER BY model.acpId ASC";
			Query query = getEntityManager().createQuery(queryString);

			List<AccountingParameters> list = query.getResultList();
			if (list != null && list.size() > 0) {
				return list;
			} else
				return null;
		} catch (RuntimeException re) {
			throw new GWorkException(Util
					.loadErrorMessageValue("search.not.found"));
		}
	}

	/**
	 * Consultar parametro contable por asignacion movimiento.
	 *
	 * @param idTipoAsignacion the id tipo asignacion
	 * @param idTipoMovimiento the id tipo movimiento
	 * @return the list
	 * @throws GWorkException the g work exception
	 */
	@SuppressWarnings("unchecked")
	public List<AccountingParameters> consultarParametroContablePorAsignacionMovimiento(
			Long idTipoAsignacion, Long idTipoMovimiento) throws GWorkException {
		try {
			final String queryString = "select model from AccountingParameters model "
					+ "where model.legateesTypes.lgtCodigo="
					+ idTipoAsignacion
					+ " AND "
					+ "model.movementType.mvmId="
					+ idTipoMovimiento
					+ " ORDER BY model.acpId ASC";
			Query query = getEntityManager().createQuery(queryString);

			List<AccountingParameters> list = query.getResultList();
			if (list != null && list.size() > 0) {
				return list;
			} else
				return null;
		} catch (RuntimeException re) {
			throw new GWorkException(Util
					.loadErrorMessageValue("search.not.found"));
		}
	}

	/**
	 * Consultar parametro contable por transaccion asignacion.
	 *
	 * @param idTipoTransaccion the id tipo transaccion
	 * @param idTipoAsignacion the id tipo asignacion
	 * @return the list
	 * @throws GWorkException the g work exception
	 */
	@SuppressWarnings("unchecked")
	public List<AccountingParameters> consultarParametroContablePorTransaccionAsignacion(
			Long idTipoTransaccion, Long idTipoAsignacion)
			throws GWorkException {
		try {
			final String queryString = "select model from AccountingParameters model "
					+ "where model.legateesTypes.lgtCodigo="
					+ idTipoAsignacion
					+ " AND "
					+ "model.transactionType.tstId="
					+ idTipoTransaccion + " ORDER BY model.acpId ASC";
			Query query = getEntityManager().createQuery(queryString);

			List<AccountingParameters> list = query.getResultList();
			if (list != null && list.size() > 0) {
				return list;
			} else
				return null;
		} catch (RuntimeException re) {
			throw new GWorkException(Util
					.loadErrorMessageValue("search.not.found"));
		}
	}

	/**
	 * Consultar parametrizacion contable por parametros.
	 *
	 * @param idTipoAsignacion the id tipo asignacion
	 * @param idTipoTransaccion the id tipo transaccion
	 * @param idTipoMovimiento the id tipo movimiento
	 * @param idCuentaContable the id cuenta contable
	 * @param idTipoLocalizacion the id tipo localizacion
	 * @return the list
	 * @throws GWorkException the g work exception
	 */
	@SuppressWarnings("unchecked")
	public List<AccountingParameters> consultarParametrizacionContablePorParametros(
			Long idTipoAsignacion, Long idTipoTransaccion,
			Long idTipoMovimiento, Long idCuentaContable, Long idTipoLocalizacion)
			throws GWorkException {
		try {
			String queryString = "select model from AccountingParameters model " +
					"where (model.acpState = 0 or model.acpState = 1) ";
			
			if (idTipoAsignacion != null && idTipoAsignacion != -1) {
				queryString = queryString + " and model.legateesTypes.lgtCodigo=" + idTipoAsignacion;
			}
			if (idTipoTransaccion != null && idTipoTransaccion != -1) {
				queryString = queryString + " and model.transactionType.tstId=" + idTipoTransaccion;
			}
			if (idTipoMovimiento != null && idTipoMovimiento != -1) {
				queryString = queryString + " and model.movementType.mvmId=" + idTipoMovimiento;
			}
			if (idTipoLocalizacion != null && idTipoLocalizacion != -1) {
				queryString = queryString + " and model.locationsTypes.lctCodigo=" + idTipoLocalizacion;
			}
			if (idCuentaContable != null && idCuentaContable != -1) {
				queryString = queryString + " and model.account.accId=" + idCuentaContable;
			}
			
			queryString = queryString
			+ " ORDER BY model.locationsTypes.lctNombre, " 
			+ "model.transactionType.tstNombre, " 
			+ "model.movementType.mvmNombre, " 
			+ "model.legateesTypes.lgtNombre ASC";
			
			Query query = getEntityManager().createQuery(queryString);

			List<AccountingParameters> list = query.getResultList();
			if (list != null && list.size() > 0) {
				return list;
			} else
				return null;
		} catch (RuntimeException re) {
			throw new GWorkException(Util
					.loadErrorMessageValue("search.not.found"));
		}
	}

	@SuppressWarnings("unchecked")
	public List<AccountingParameters> consultarParametrizacionContableActivos(
			Long idTipoAsignacion, Long idTipoTransaccion,
			Long idTipoMovimiento, Long idTipoLocalizacion)
			throws GWorkException {
		try {
			String queryString = "select model from AccountingParameters model " +
					"where model.acpState = 1 ";
			
			if (idTipoAsignacion != null && idTipoAsignacion != -1) {
				queryString = queryString + " and model.legateesTypes.lgtCodigo=" + idTipoAsignacion;
			}
			if (idTipoTransaccion != null && idTipoTransaccion != -1) {
				queryString = queryString + " and model.transactionType.tstId=" + idTipoTransaccion;
			}
			if (idTipoMovimiento != null && idTipoMovimiento != -1) {
				queryString = queryString + " and model.movementType.mvmId=" + idTipoMovimiento;
			}
			if (idTipoLocalizacion != null && idTipoLocalizacion != -1) {
				queryString = queryString + " and model.locationsTypes.lctCodigo=" + idTipoLocalizacion;
			}
			
			queryString = queryString + " ORDER BY model.acpId ASC";
			
			Query query = getEntityManager().createQuery(queryString);

			List<AccountingParameters> list = query.getResultList();
			if (list != null && list.size() > 0) {
				return list;
			} else
				return null;
		} catch (RuntimeException re) {
			throw new GWorkException(Util
					.loadErrorMessageValue("search.not.found"));
		}
	}
}
