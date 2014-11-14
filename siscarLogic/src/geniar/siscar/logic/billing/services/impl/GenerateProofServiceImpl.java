package geniar.siscar.logic.billing.services.impl;

import geniar.siscar.consults.impl.ConsultsServiceImpl;
import geniar.siscar.logic.billing.services.GenerateProofService;
import geniar.siscar.model.AccountingParameters;
import geniar.siscar.model.ActualOthersApplications;
import geniar.siscar.model.HeaderProof;
import geniar.siscar.persistence.ActualOthersApplicationsDAO;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

import java.sql.Connection;
import java.util.Date;

/**
 * The Class GenerateProofServiceImpl.
 */
public class GenerateProofServiceImpl implements GenerateProofService {

	/* (non-Javadoc)
	 * @see geniar.siscar.logic.billing.services.GenerateProofService#generarComprobanteDetalle(java.sql.Connection, java.lang.String, java.lang.Long, java.util.Date, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.util.Date, java.lang.String, java.lang.Long, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.Float, java.lang.Float, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.Long, java.lang.Long, geniar.siscar.model.AccountingParameters, geniar.siscar.model.HeaderProof)
	 */
	public Connection generarComprobanteDetalle(Connection connection, String aoaState, Long PSob,
			Date PAccdate, String PCurr, String PUser, String PCategory,
			String PSource, Date PConvDate, String PConvType, Long PConvRate,
			String PCompany, String PAccount, String PCcenter,
			String PAuxiliary, Float PEntDr, Float PEntCr, String PBname,
			String PDescription, String PNit, String PAttribute2,
			String PAttribute5, String PAttribute6, String PAttribute7,
			String PAttribute8, String PAttribute9, String PAttribute10,
			Long tipoMovimiento, Long tipoComprobante,
			AccountingParameters parameters, HeaderProof headerProof, 
			String idMaster, Long idDetail)
			throws GWorkException {

		ActualOthersApplications actualOthersApplications = new ActualOthersApplications();
		actualOthersApplications.setPSob(PSob);
		actualOthersApplications.setPCurr(PCurr);
		actualOthersApplications.setPCcenter(PCcenter);
		actualOthersApplications.setPAuxiliary(PAuxiliary);
		actualOthersApplications.setPAccdate(PAccdate);
		actualOthersApplications.setPUser(PUser);
		actualOthersApplications.setPCompany(PCompany);
		actualOthersApplications.setPAccount(PAccount);
		actualOthersApplications.setPEntDr(PEntDr);
		actualOthersApplications.setPEntCr(PEntCr);
		actualOthersApplications.setPCategory(PCategory);
		actualOthersApplications.setPSource(PSource);
		actualOthersApplications.setPConvDate(null);
		actualOthersApplications.setPConvRate(null);
		actualOthersApplications.setPConvType(null);
		actualOthersApplications.setPDescription(PDescription);
		actualOthersApplications.setPAttribute2(PAttribute2);
		actualOthersApplications.setPNit(null);
		actualOthersApplications.setPAttribute5(PAttribute5);
		actualOthersApplications.setPAttribute6(PAttribute6);
		actualOthersApplications.setPAttribute7(PAttribute7);
		actualOthersApplications.setPAttribute8(null);
		actualOthersApplications.setPAttribute9(PAttribute9);
		actualOthersApplications.setPAttribute10(PAttribute10);
		actualOthersApplications.setAoaState(Util
				.loadMessageValue("ESTADO.ACTIVO"));
		actualOthersApplications.setPBname(PBname);
		actualOthersApplications.setHeaderProof(headerProof);

		new ActualOthersApplicationsDAO().save(actualOthersApplications);

		connection = ConsultsServiceImpl.insercionContableSinAutocommit(connection, PSob,
				PAccdate, PCurr, PUser, PCategory, PSource, PConvDate,
				PConvType, PConvRate, PCompany, PAccount, PCcenter, PAuxiliary,
				PEntDr, PEntCr, PBname, PDescription, PNit, PAttribute2,
				PAttribute5, PAttribute6, PAttribute7, PAttribute8,
				PAttribute9, PAttribute10, headerProof.getHepGroupId(), idMaster, idDetail);
		return connection;

	}
	
	public Connection generarComprobanteDetalle2(Connection connection, String aoaState, Long PSob,
			Date PAccdate, String PCurr, String PUser, String PCategory,
			String PSource, Date PConvDate, String PConvType, Long PConvRate,
			String PCompany, String PAccount, String PCcenter,
			String PAuxiliary, Float PEntDr, Float PEntCr, String PBname,
			String PDescription, String PNit, String PAttribute2,
			String PAttribute5, String PAttribute6, String PAttribute7,
			String PAttribute8, String PAttribute9, String PAttribute10,
			Long tipoMovimiento, Long tipoComprobante,
			AccountingParameters parameters, HeaderProof headerProof)
			throws GWorkException {

		ActualOthersApplications actualOthersApplications = new ActualOthersApplications();
		actualOthersApplications.setPSob(PSob);
		actualOthersApplications.setPCurr(PCurr);
		actualOthersApplications.setPCcenter(PCcenter);
		actualOthersApplications.setPAuxiliary(PAuxiliary);
		actualOthersApplications.setPAccdate(PAccdate);
		actualOthersApplications.setPUser(PUser);
		actualOthersApplications.setPCompany(PCompany);
		actualOthersApplications.setPAccount(PAccount);
		actualOthersApplications.setPEntDr(PEntDr);
		actualOthersApplications.setPEntCr(PEntCr);
		actualOthersApplications.setPCategory(PCategory);
		actualOthersApplications.setPSource(PSource);
		actualOthersApplications.setPConvDate(null);
		actualOthersApplications.setPConvRate(null);
		actualOthersApplications.setPConvType(null);
		actualOthersApplications.setPDescription(PDescription);
		actualOthersApplications.setPAttribute2(PAttribute2);
		actualOthersApplications.setPNit(null);
		actualOthersApplications.setPAttribute5(PAttribute5);
		actualOthersApplications.setPAttribute6(PAttribute6);
		actualOthersApplications.setPAttribute7(PAttribute7);
		actualOthersApplications.setPAttribute8(null);
		actualOthersApplications.setPAttribute9(PAttribute9);
		actualOthersApplications.setPAttribute10(PAttribute10);
		actualOthersApplications.setAoaState(Util
				.loadMessageValue("ESTADO.ACTIVO"));
		actualOthersApplications.setPBname(PBname);
		actualOthersApplications.setHeaderProof(headerProof);

		new ActualOthersApplicationsDAO().save(actualOthersApplications);

		return connection;

	}
	
	public Connection generarComprobanteFinanciero(Connection connection, String aoaState, Long PSob,
			Date PAccdate, String PCurr, String PUser, String PCategory,
			String PSource, Date PConvDate, String PConvType, Long PConvRate,
			String PCompany, String PAccount, String PCcenter,
			String PAuxiliary, Float PEntDr, Float PEntCr, String PBname,
			String PDescription, String PNit, String PAttribute2,
			String PAttribute5, String PAttribute6, String PAttribute7,
			String PAttribute8, String PAttribute9, String PAttribute10,
			Long tipoMovimiento, Long tipoComprobante,
			AccountingParameters parameters, HeaderProof headerProof, String idMaster, Long idDetail)
			throws GWorkException {

		ActualOthersApplications actualOthersApplications = new ActualOthersApplications();
		actualOthersApplications.setPSob(PSob);
		actualOthersApplications.setPCurr(PCurr);
		actualOthersApplications.setPCcenter(PCcenter);
		actualOthersApplications.setPAuxiliary(PAuxiliary);
		actualOthersApplications.setPAccdate(PAccdate);
		actualOthersApplications.setPUser(PUser);
		actualOthersApplications.setPCompany(PCompany);
		actualOthersApplications.setPAccount(PAccount);
		actualOthersApplications.setPEntDr(PEntDr);
		actualOthersApplications.setPEntCr(PEntCr);
		actualOthersApplications.setPCategory(PCategory);
		actualOthersApplications.setPSource(PSource);
		actualOthersApplications.setPConvDate(null);
		actualOthersApplications.setPConvRate(null);
		actualOthersApplications.setPConvType(null);
		actualOthersApplications.setPDescription(PDescription);
		actualOthersApplications.setPAttribute2(PAttribute2);
		actualOthersApplications.setPNit(null);
		actualOthersApplications.setPAttribute5(PAttribute5);
		actualOthersApplications.setPAttribute6(PAttribute6);
		actualOthersApplications.setPAttribute7(PAttribute7);
		actualOthersApplications.setPAttribute8(null);
		actualOthersApplications.setPAttribute9(PAttribute9);
		actualOthersApplications.setPAttribute10(PAttribute10);
		actualOthersApplications.setAoaState(Util
				.loadMessageValue("ESTADO.ACTIVO"));
		actualOthersApplications.setPBname(PBname);
		actualOthersApplications.setHeaderProof(headerProof);

//		new ActualOthersApplicationsDAO().save(actualOthersApplications);

		//modificado 6 de marzo
		connection = ConsultsServiceImpl.insercionContableSinAutocommit(connection, PSob,
				PAccdate, PCurr, PUser, PCategory, PSource, PConvDate,
				PConvType, PConvRate, PCompany, PAccount, PCcenter, PAuxiliary,
				PEntDr, PEntCr, PBname, PDescription, PNit, PAttribute2,
				PAttribute5, PAttribute6, PAttribute7, PAttribute8,
				PAttribute9, PAttribute10, headerProof.getHepGroupId(), idMaster, idDetail);
		return connection;

	}
	
	
	/**
	 * Guarda los datos en un archivo
	 * 
	 * 
	 * 
	 */
	
	public void guardaArchivo(){
		
		
		
	}

	/**
	 * Generar comprobante detalle prepago.
	 *
	 * @param connection the connection
	 * @param aoaState the aoa state
	 * @param PSob the p sob
	 * @param PAccdate the p accdate
	 * @param PCurr the p curr
	 * @param PUser the p user
	 * @param PCategory the p category
	 * @param PSource the p source
	 * @param PConvDate the p conv date
	 * @param PConvType the p conv type
	 * @param PConvRate the p conv rate
	 * @param PCompany the p company
	 * @param PAccount the p account
	 * @param PCcenter the p ccenter
	 * @param PAuxiliary the p auxiliary
	 * @param PEntDr the p ent dr
	 * @param PEntCr the p ent cr
	 * @param PBname the p bname
	 * @param PDescription the p description
	 * @param PNit the p nit
	 * @param PAttribute2 the p attribute2
	 * @param PAttribute5 the p attribute5
	 * @param PAttribute6 the p attribute6
	 * @param PAttribute7 the p attribute7
	 * @param PAttribute8 the p attribute8
	 * @param PAttribute9 the p attribute9
	 * @param PAttribute10 the p attribute10
	 * @param tipoMovimiento the tipo movimiento
	 * @param tipoComprobante the tipo comprobante
	 * @param parameters the parameters
	 * @param headerProof the header proof
	 * @return the connection
	 * @throws GWorkException the g work exception
	 */
	public Connection generarComprobanteDetallePrepago(Connection connection, 
			String aoaState,
			Long PSob, Date PAccdate, String PCurr, String PUser,
			String PCategory, String PSource, Date PConvDate, String PConvType,
			Long PConvRate, String PCompany, String PAccount, String PCcenter,
			String PAuxiliary, Float PEntDr, Float PEntCr, String PBname,
			String PDescription, String PNit, String PAttribute2,
			String PAttribute5, String PAttribute6, String PAttribute7,
			String PAttribute8, String PAttribute9, String PAttribute10,
			Long tipoMovimiento, Long tipoComprobante,
			AccountingParameters parameters, HeaderProof headerProof,
			String idMaster, Long idDetail)
			throws GWorkException {

		ActualOthersApplications actualOthersApplications = new ActualOthersApplications();
		actualOthersApplications.setPSob(PSob);
		actualOthersApplications.setPCurr(PCurr);
		actualOthersApplications.setPCcenter(PCcenter);
		actualOthersApplications.setPAuxiliary(PAuxiliary);
		actualOthersApplications.setPAccdate(PAccdate);
		actualOthersApplications.setPUser(PUser);
		actualOthersApplications.setPCompany(PCompany);
		actualOthersApplications.setPAccount(PAccount);
		actualOthersApplications.setPEntDr(PEntDr);
		actualOthersApplications.setPEntCr(PEntCr);
		actualOthersApplications.setPCategory(PCategory);
		actualOthersApplications.setPSource(PSource);
		actualOthersApplications.setPConvDate(null);
		actualOthersApplications.setPConvRate(null);
		actualOthersApplications.setPConvType(null);
		actualOthersApplications.setPDescription(PDescription);
		actualOthersApplications.setPAttribute2(PAttribute2);
		actualOthersApplications.setPNit(null);
		actualOthersApplications.setPAttribute5(PAttribute5);
		actualOthersApplications.setPAttribute6(PAttribute6);
		actualOthersApplications.setPAttribute7(null);
		actualOthersApplications.setPAttribute8(null);
		actualOthersApplications.setPAttribute9(PAttribute9);
		actualOthersApplications.setPAttribute10(PAttribute10);
		actualOthersApplications.setAoaState(Util
				.loadMessageValue("ESTADO.ACTIVO"));
		actualOthersApplications.setPBname(PBname);
		actualOthersApplications.setHeaderProof(headerProof);

		//modificado 5 de marzo
		connection = ConsultsServiceImpl.insercionContableSinAutocommit(connection, PSob,
				PAccdate, PCurr, PUser, PCategory, PSource, PConvDate,
				PConvType, PConvRate, PCompany, PAccount, PCcenter, PAuxiliary,
				PEntDr, PEntCr, PBname, PDescription, PNit, PAttribute2,
				PAttribute5, PAttribute6, PAttribute7, PAttribute8,
				PAttribute9, PAttribute10, headerProof.getHepGroupId(),
				idMaster, idDetail);

		new ActualOthersApplicationsDAO().save(actualOthersApplications);

		return connection;

	}
}