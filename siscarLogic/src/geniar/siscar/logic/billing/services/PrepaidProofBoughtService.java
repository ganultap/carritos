package geniar.siscar.logic.billing.services;

import java.sql.Connection;
import java.util.Date;

import geniar.siscar.model.HeaderProof;
import geniar.siscar.model.Period;
import geniar.siscar.model.VehiclesAssignation;
import gwork.exception.GWorkException;

public interface PrepaidProofBoughtService {

	/**
	 * Permite generar un detalle para un comprobante por compras de prepago
	 * 
	 * @param connection
	 * @param tipoComprobante
	 * @param login
	 * @param valor
	 * @param tipoMovimiento
	 * @param centroCosto
	 * @param placa
	 * @param fecha
	 * @param vehiclesAssignation
	 * @param period
	 * @param headerProof
	 * @return
	 * @throws GWorkException
	 */
	public Connection generarComprobanteDetalle(Connection connection, 
			Long tipoComprobante, String login,
			Float valor, Long tipoMovimiento, String centroCosto, String placa,
			Date fecha, VehiclesAssignation vehiclesAssignation, Period period,
			HeaderProof headerProof) throws GWorkException;
}
