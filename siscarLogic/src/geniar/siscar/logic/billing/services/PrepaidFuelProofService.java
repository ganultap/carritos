/**
 * 
 */
package geniar.siscar.logic.billing.services;

import geniar.siscar.model.AccountingParameters;
import geniar.siscar.model.HeaderProof;
import geniar.siscar.model.Period;
import geniar.siscar.model.Prepaid;
import geniar.siscar.model.Users;
import geniar.siscar.model.VOprepaidFuelProof;
import geniar.siscar.model.Tariffs;
import geniar.siscar.model.VehiclesAssignation;
import gwork.exception.GWorkException;
import java.util.List;

/**
 * @author Felipe.Dominguez
 * 
 */
public interface PrepaidFuelProofService {

	public List<VOprepaidFuelProof> listprepaidFuelVehicles(Period periodo)
			throws GWorkException;

	public void generatePrepaidFuelProof(String login,
			List<VOprepaidFuelProof> listVOprepaidFuelProof, Period periodo)
			throws GWorkException;

	/**
	 * Metodo encargado de obtener la tarifa, de acuerdo al año actual
	 * 
	 * @param AñoActual
	 * @throws GWorkException
	 */
	public Tariffs getTarifaActual(Period periodo) throws GWorkException;

	/**
	 * Metodo encargado de obtener disponibilidad de un Centro de Costo
	 * 
	 * @param String
	 *            centroCosto (nombre)
	 * @throws GWorkException
	 */
	public boolean disponibilidadCentrCosto(String centroCosto)
			throws GWorkException;

	/**
	 * Metodo encargado "setear" los datos del objeto VOprepaidFuelProof, y
	 * adicionarlos a un List
	 * 
	 * @param List
	 *            <Object[]> listCC
	 * @param Prepaid
	 *            objPrepaid
	 * @param List
	 *            <Users> usuario
	 * @param List
	 *            <VOprepaidFuelProof> listVOppfp
	 * @param Double
	 *            tarifaVigente
	 * @throws GWorkException
	 */
	public void setDataPrepaidFuelProof(List<Object[]> listCC,
			Prepaid objPrepaid, List<Users> usuario,
			List<VOprepaidFuelProof> listVOppfp, Double tarifaVigente,
			VehiclesAssignation vha);

	public boolean generarComprobanteDetalle(Long tipoComprobante,
			String login, Double valorDevolucion, Long tipoMovimiento,
			String centroCosto, Long tipoCargo, String placa, Long periodo,
			Double galones, Long flagHP, String carne, String idPrepago,
			Double tarifaActual) throws GWorkException;

	public HeaderProof generarCabeceraComprobante(Long tipoComprobante,
			Period period, Long tipoMovimiento, Long tipoMoneda,
			AccountingParameters parameters) throws GWorkException;

	public Period consultarPeriodByAno(String anoCobro) throws GWorkException;
}
