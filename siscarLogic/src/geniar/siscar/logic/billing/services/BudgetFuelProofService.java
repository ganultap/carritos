package geniar.siscar.logic.billing.services;

import geniar.siscar.model.FuelTypeRequest;
import geniar.siscar.model.HeaderProof;
import geniar.siscar.model.Period;
import gwork.exception.GWorkException;

import java.sql.Connection;
import java.util.Date;

public interface BudgetFuelProofService {

	/**
	 * Permite generar un registro detalle de un comprobante
     *
	 * @param connection
	 * @param carne
	 * @param tipoComprobante
	 * @param login
	 * @param valor
	 * @param tipoMovimiento
	 * @param centroCosto
	 * @param tipoCargo
	 * @param placa
	 * @param fecha
	 * @param galones
	 * @param fuelTypeRequest
	 * @param periodo
	 * @param headerProof
	 * @return
	 * @throws GWorkException
	 */
	public Connection generarComprobanteDetalle(Connection connection, String carne, Long tipoComprobante,
			String login, Float valor, Long tipoMovimiento, String centroCosto,
			Long tipoCargo, String placa, Date fecha, Float galones,
			FuelTypeRequest fuelTypeRequest, Period periodo,
			HeaderProof headerProof) throws GWorkException;
}
