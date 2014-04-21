/**
 * 
 */
package geniar.siscar.logic.billing.services.impl;

import geniar.siscar.logic.billing.services.AccountingParametersService;
import geniar.siscar.logic.consultas.SearchAccountingParameters;
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
import geniar.siscar.model.LocationsTypes;
import geniar.siscar.model.MovementType;
import geniar.siscar.model.TransactionType;
import geniar.siscar.persistence.AccountDAO;
import geniar.siscar.persistence.AccountingParametersDAO;
import geniar.siscar.persistence.AttributeDAO;
import geniar.siscar.persistence.AuxiliarDAO;
import geniar.siscar.persistence.ChargeTypeDAO;
import geniar.siscar.persistence.CompanyDAO;
import geniar.siscar.persistence.ControlTypeDAO;
import geniar.siscar.persistence.CostsCentersDAO;
import geniar.siscar.persistence.DescriptionTypeDAO;
import geniar.siscar.persistence.DocumentOneDAO;
import geniar.siscar.persistence.DocumentOneTypeDAO;
import geniar.siscar.persistence.DocumentTwoDAO;
import geniar.siscar.persistence.DocumentTwoTypeDAO;
import geniar.siscar.persistence.EntityManagerHelper;
import geniar.siscar.persistence.FutureDAO;
import geniar.siscar.persistence.LegateesTypesDAO;
import geniar.siscar.persistence.LineDAO;
import geniar.siscar.persistence.LocationsTypesDAO;
import geniar.siscar.persistence.MovementTypeDAO;
import geniar.siscar.persistence.TransactionTypeDAO;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * The Class AccountingParametersServiceImpl.
 *
 * @author geniar
 */
public class AccountingParametersServiceImpl implements
		AccountingParametersService {

	/** The Constant log. */
	private static final Log log = LogFactory.getLog(AccountingParametersServiceImpl.class);
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see geniar.siscar.logic.billing.services.AccountingParametersService
	 *      #consultarParametroContable(java.lang.Long, java.lang.Long,
	 *      java.lang.Long, java.lang.Long)
	 */
	public List<AccountingParameters> consultarParametroContable(
			Long idTipoAsignacion, Long idTipoTransaccion,
			Long idTipoMovimiento, Long idCuentaContable, Long idTipoLocalizacion) throws GWorkException {

		if (EntityManagerHelper.getEntityManager().isOpen()) {
			EntityManagerHelper.getEntityManager().close();
		}

		List<AccountingParameters> lista = new ArrayList<AccountingParameters>();

		lista = new SearchAccountingParameters().
		consultarParametrizacionContablePorParametros(idTipoAsignacion, 
				idTipoTransaccion, 
				idTipoMovimiento, 
				idCuentaContable, 
				idTipoLocalizacion);
		
		return lista;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see geniar.siscar.logic.billing.services.
	 *      AccountingParametersService#crearParametroContable( java.lang.Long,
	 *      java.lang.Long, java.lang.Long, java.lang.Long, java.lang.Long,
	 *      java.lang.Long, java.lang.Long, java.lang.Long, java.lang.Long,
	 *      java.lang.Long, java.lang.Long, java.lang.Long, java.lang.Long,
	 *      java.lang.Long, java.lang.Long, java.lang.Long, java.lang.Long,
	 *      java.lang.Long)
	 */
	public void crearParametroContable(Long idTipoAsignacion,
			Long idTipoTransaccion, Long idTipoMovimiento, Long idTipoCargo,
			Long idCompany, Long idNumeroCuenta, Long idCentroCosto,
			Long idNumeroLinea, Long idAuxiliar, Long idTipoControl,
			Long idFuturo, Long idDescripcion, Long idTipoDocUno,
			Long idNumDocUno, Long idTipoDocDos, Long idNumDocDos,
			Long idAtributo, String descripcion, Long idTipoLocalizacion,
			Boolean acpState) throws GWorkException {

		try {
			if (consultarParametroContable(idTipoAsignacion, idTipoTransaccion,
					idTipoMovimiento, idNumeroCuenta, idTipoLocalizacion) != null) {
				throw new GWorkException(Util.loadErrorMessageValue("ERROR.PCYAEXISTE"));
			}
			
			LegateesTypes legateesTypes = new LegateesTypesDAO()
					.findById(idTipoAsignacion);
			TransactionType transactionType = new TransactionTypeDAO()
					.findById(idTipoTransaccion);
			MovementType movementType = new MovementTypeDAO()
					.findById(idTipoMovimiento);
			LocationsTypes locationsTypes = new LocationsTypesDAO()
					.findById(idTipoLocalizacion);
			ChargeType chargeType = new ChargeTypeDAO().findById(idTipoCargo);
			Company company = new CompanyDAO().findById(idCompany);
			Account account = new AccountDAO().findById(idNumeroCuenta);
			CostsCenters costsCenters = new CostsCentersDAO()
					.findById(idCentroCosto);
			Line line = new LineDAO().findById(idNumeroLinea);
			Auxiliar auxiliar = new AuxiliarDAO().findById(idAuxiliar);
			ControlType controlType = new ControlTypeDAO()
					.findById(idTipoControl);
			Future future = new FutureDAO().findById(idFuturo);
			DescriptionType descriptionType = new DescriptionTypeDAO()
					.findById(idDescripcion);
			DocumentOneType documentOneType = new DocumentOneTypeDAO()
					.findById(idTipoDocUno);
			DocumentOne documentOne = new DocumentOneDAO().findById(idNumDocUno);
			DocumentTwoType documentTwoType = new DocumentTwoTypeDAO()
					.findById(idTipoDocDos);
			DocumentTwo documentTwo = new DocumentTwoDAO().findById(idNumDocDos);
			Attribute attribute = new AttributeDAO().findById(idAtributo);

			AccountingParameters accountingParameters = new AccountingParameters();
			accountingParameters.setLegateesTypes(legateesTypes);
			accountingParameters.setTransactionType(transactionType);
			accountingParameters.setMovementType(movementType);
			accountingParameters.setLocationsTypes(locationsTypes);
			accountingParameters.setChargeType(chargeType);
			accountingParameters.setCompany(company);
			accountingParameters.setAccount(account);
			accountingParameters.setCostsCenters(costsCenters);
			accountingParameters.setLine(line);
			accountingParameters.setAuxiliar(auxiliar);
			accountingParameters.setControlType(controlType);
			accountingParameters.setFuture(future);
			accountingParameters.setDescriptionType(descriptionType);
			documentOne.setDocumentOneType(documentOneType);
			accountingParameters.setDocumentOne(documentOne);
			documentTwo.setDocumentTwoType(documentTwoType);
			accountingParameters.setDocumentTwo(documentTwo);
			accountingParameters.setAttribute(attribute);
			accountingParameters.setAccDescripcion(descripcion);
			accountingParameters.setAcpState(acpState);
			
			EntityManagerHelper.getEntityManager().getTransaction().begin();
			new AccountingParametersDAO().save(accountingParameters);
			EntityManagerHelper.getEntityManager().getTransaction().commit();
		} catch (Exception e) {
			log.error("crearParametroContable", e);
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.GUARDAR"), e);
		}

	}

	/* (non-Javadoc)
	 * @see geniar.siscar.logic.billing.services.AccountingParametersService#modificarParametroContable(java.lang.Long, java.lang.Long, java.lang.Long, java.lang.Long, java.lang.Long, java.lang.Long, java.lang.Long, java.lang.Long, java.lang.Long, java.lang.Long, java.lang.Long, java.lang.Long, java.lang.Long, java.lang.Long, java.lang.Long, java.lang.Long, java.lang.Long, java.lang.Long, java.lang.String)
	 */
	public void modificarParametroContable(Long idParametroContable,
			Long idTipoAsignacion, Long idTipoTransaccion,
			Long idTipoMovimiento, Long idTipoCargo, Long idCompany,
			Long idNumeroCuenta, Long idCentroCosto, Long idNumeroLinea,
			Long idAuxiliar, Long idTipoControl, Long idFuturo,
			Long idDescripcion, Long idTipoDocUno, Long idNumDocUno,
			Long idTipoDocDos, Long idNumDocDos, Long idAtributo,
			String descripcion, Long idTipoLocalizacion,
			Boolean acpState) throws GWorkException {
		try {
			if (EntityManagerHelper.getEntityManager().isOpen()) {
				EntityManagerHelper.getEntityManager().close();
			}

			AccountingParameters accountingParameters = new AccountingParametersDAO()
				.findById(idParametroContable);
			LegateesTypes legateesTypes = new LegateesTypesDAO()
				.findById(idTipoAsignacion);
			TransactionType transactionType = new TransactionTypeDAO()
				.findById(idTipoTransaccion);
			MovementType movementType = new MovementTypeDAO()
				.findById(idTipoMovimiento);
			LocationsTypes locationsTypes = new LocationsTypesDAO()
				.findById(idTipoLocalizacion);
			ChargeType chargeType = new ChargeTypeDAO().findById(idTipoCargo);
			Company company = new CompanyDAO().findById(idCompany);
			Account account = new AccountDAO().findById(idNumeroCuenta);
			CostsCenters costsCenters = new CostsCentersDAO()
					.findById(idCentroCosto);
			Line line = new LineDAO().findById(idNumeroLinea);
			Auxiliar auxiliar = new AuxiliarDAO().findById(idAuxiliar);
			ControlType controlType = new ControlTypeDAO()
					.findById(idTipoControl);
			Future future = new FutureDAO().findById(idFuturo);
			DescriptionType descriptionType = new DescriptionTypeDAO()
					.findById(idDescripcion);
			DocumentOneType documentOneType = new DocumentOneTypeDAO()
					.findById(idTipoDocUno);
			DocumentOne documentOne = new DocumentOneDAO()
					.findById(idNumDocUno);
			DocumentTwoType documentTwoType = new DocumentTwoTypeDAO()
					.findById(idTipoDocDos);
			DocumentTwo documentTwo = new DocumentTwoDAO()
					.findById(idNumDocDos);
			Attribute attribute = new AttributeDAO().findById(idAtributo);

			EntityManagerHelper.getEntityManager()
					.refresh(accountingParameters);

			accountingParameters.setLegateesTypes(legateesTypes);
			accountingParameters.setTransactionType(transactionType);
			accountingParameters.setMovementType(movementType);
			accountingParameters.setLocationsTypes(locationsTypes);
			accountingParameters.setChargeType(chargeType);
			accountingParameters.setCompany(company);
			accountingParameters.setAccount(account);
			accountingParameters.setCostsCenters(costsCenters);
			accountingParameters.setLine(line);
			accountingParameters.setAuxiliar(auxiliar);
			accountingParameters.setControlType(controlType);
			accountingParameters.setFuture(future);
			accountingParameters.setDescriptionType(descriptionType);
			documentOne.setDocumentOneType(documentOneType);
			accountingParameters.setDocumentOne(documentOne);
			documentTwo.setDocumentTwoType(documentTwoType);
			accountingParameters.setDocumentTwo(documentTwo);
			accountingParameters.setAttribute(attribute);
			accountingParameters.setAccDescripcion(descripcion);
			accountingParameters.setAcpState(acpState);
			
			EntityManagerHelper.getEntityManager().getTransaction().begin();
			new AccountingParametersDAO().update(accountingParameters);
			EntityManagerHelper.getEntityManager().getTransaction().commit();
		} catch (Exception e) {
			log.error("modificarParametroContable", e);
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.GUARDAR"), e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see geniar.siscar.logic.billing.services.
	 *      AccountingParametersService#eliminarParametroContable(java.lang.Long)
	 */
	public void eliminarParametroContable(Long idParametroContable)
			throws GWorkException {
		try {
			if (EntityManagerHelper.getEntityManager().isOpen()) {
				EntityManagerHelper.getEntityManager().close();
			}
			AccountingParameters accountingParameters = new SearchAccountingParameters()
					.consultarParametroContable(idParametroContable);

			if (accountingParameters == null) {
				throw new GWorkException(Util
						.loadErrorMessageValue("search.not.found"));
			}

			EntityManagerHelper.beginTransaction();
			new AccountingParametersDAO().delete(accountingParameters);
			EntityManagerHelper.commit();

		} catch (Exception e) {
			log.error("eliminarParametroContable", e);
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.ELIMINARPC"), e);
		}

	}
}