/**
 * 
 */
package geniar.siscar.logic.allocation.services;

import geniar.siscar.model.CostsCentersVehicles;
import geniar.siscar.model.Requests;
import geniar.siscar.model.VehiclesAssignation;
import gwork.exception.GWorkException;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * @author Rodrigo Lopez Geniar Web
 * 
 */
public interface ServiceAllocation {
	public VehiclesAssignation asignacionVehiculoAsignacion(Long idRequests,
			Long idZones, Long idUbicacion, boolean cobro,
			boolean cobroCasaCIAT, String login) throws GWorkException;

	public void reservarVehiculo(Requests requests) throws GWorkException;

	public void entregaVehiculoAsignacion(Long numeroAsignacion,
			Date fechaEntrega, Long vhaKilomeActual, String observacion,
			String loginUser) throws GWorkException, SQLException;

	public List<VehiclesAssignation> listVehiclesAssignationByUser(
			String numeroCarne) throws GWorkException;

	public List<VehiclesAssignation> listRentAssignationByUser(
			String numeroCarne) throws GWorkException;

	public VehiclesAssignation consultarVehiculoAsignacion(Long idVhaAsignacion);

	public void entregaVehiculoAlquiler(Long numeroAsignacion,
			Date fechaEntrega, Long vhaKilomeActual, String observacion)
			throws GWorkException;

	public void reasignarReserva(Requests requests) throws GWorkException;

	public void envioNotificacion(String PlacaDiplomatica, Requests requests,
			Long idUbicacion, Long idTipoUbicacion, boolean cobro,
			Long KmEntrega,
			List<CostsCentersVehicles> listaCentroCostoVehiculos, String email,
			String From) throws GWorkException, RuntimeException;

	public void enviarNotificacionReserva(Requests requests, Boolean cobro,
			List<CostsCentersVehicles> listaCentroCostoVehiculos, String Email)
			throws GWorkException, RuntimeException;

}
