package geniar.siscar.logic.billing.services;

import geniar.siscar.model.AccountingParameters;
import geniar.siscar.model.HeaderProof;
import gwork.exception.GWorkException;

import java.sql.Connection;
import java.util.Date;

/**
 * The Interface GenerateProofService.
 */
public interface GenerateProofService {
	
	/**
	 * Permite generar un detalle de comprobante contable.
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
	 * @return Connection conexion
	 * @throws GWorkException the g work exception
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
			AccountingParameters parameters, HeaderProof headerProof)
			throws GWorkException;
}