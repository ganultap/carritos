package geniar.siscar.logic.vehicle.services;

import geniar.siscar.model.ClientsSalesVehicles;
import gwork.exception.GWorkException;

import java.util.Date;

public interface ClientSalesService {

	public void crearCompradorVehiculo(String placa, String numeroIdentificacion, String nombreComprador,
			String direccion, String telefono, String email, String valorVenta, String atFinal, Date fechaEntrega,
			Date fechaLicitacion, String numeroLicitacion, String placaIntra, String observaciones) throws GWorkException;
	
	public ClientsSalesVehicles consultarVentasVhc(String placaVhc)
											throws GWorkException;
	
	public void modificarVentasVhc(
			String placa, String numeroIdentificacion, String nombreComprador,
			String direccion, String telefono, String email, String valorVenta, String atFinal, Date fechaEntrega,
			Date fechaLicitacion, String numeroLicitacion, String placaIntra, String observaciones)
			throws GWorkException;

}
