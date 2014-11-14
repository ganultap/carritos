package geniar.siscar.logic.billing.services;

import geniar.siscar.model.AccountingParameters;
import geniar.siscar.model.CostsCentersVehicles;
import geniar.siscar.model.HeaderProof;
import geniar.siscar.model.VehiclesAssignation;
import gwork.exception.GWorkException;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

/**
 * The Interface RentProofService.
 */
public interface RentProofService {

	/**
	 * Permite generar un comprobante de un alquiler.
	 *
	 * @param connection the connection
	 * @param tipoComprobante the tipo comprobante
	 * @param login the login
	 * @param tipoMovimiento the tipo movimiento
	 * @param fecha the fecha
	 * @param vehiclesAssignation the vehicles assignation
	 * @param valorTarifa the valor tarifa
	 * @param centroCostos the centro costos
	 * @param headerProof the header proof
	 * @param listaCostCenters the lista cost centers
	 * @param parameters the parameters
	 * @return the connection
	 * @throws GWorkException the g work exception
	 */
	public Connection generarComprobanteAlquiler(Connection connection, Long tipoComprobante,
			String login, Long tipoMovimiento, Date fecha,
			VehiclesAssignation vehiclesAssignation,
			Double valorTarifa, String centroCostos, HeaderProof headerProof,
			List<CostsCentersVehicles> listaCostCenters,
			AccountingParameters parameters, String idMaster, Long idDetail) throws GWorkException;

	/**
	 * Permite generar un comprobante por la devolucion de un alquiler.
	 *
	 * @param connection the connection
	 * @param tipoComprobante the tipo comprobante
	 * @param login the login
	 * @param tipoMovimiento the tipo movimiento
	 * @param fecha the fecha
	 * @param vehiclesAssignation the vehicles assignation
	 * @param valor the valor
	 * @param centroCosto the centro costo
	 * @param msgKMAdicional the msg km adicional
	 * @param headerProof the header proof
	 * @param parameters the parameters
	 * @return the connection
	 * @throws GWorkException the g work exception
	 */
	public Connection generarComprobanteDevolucionAlquiler(Connection connection, 
			Long tipoComprobante, String login, Long tipoMovimiento,
			Date fecha, VehiclesAssignation vehiclesAssignation, Float valor,
			String centroCosto, String msgKMAdicional, HeaderProof headerProof,
			AccountingParameters parameters, String idMaster, Long idDetail)
			throws GWorkException;
}