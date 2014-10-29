package geniar.siscar.logic.vehicle.services.impl;

import geniar.siscar.logic.vehicle.services.ClientSalesService;
import geniar.siscar.logic.vehicle.services.VehicleService;
import geniar.siscar.model.ClientsSalesVehicles;
import geniar.siscar.model.Vehicles;
import geniar.siscar.model.VehiclesStates;
import geniar.siscar.persistence.ClientsSalesVehiclesDAO;
import geniar.siscar.persistence.EntityManagerHelper;
import geniar.siscar.persistence.VehiclesDAO;
import geniar.siscar.persistence.VehiclesStatesDAO;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

import java.util.Date;
import java.util.List;

import javax.persistence.Query;

public class ClientSalesServiceImpl implements ClientSalesService {

	public void crearCompradorVehiculo(String placa, String numeroIdentificacion, String nombreComprador,
			String direccion, String telefono, String email, String valorVenta, String atFinal, Date fechaEntrega,
			Date fechaLicitacion, String numeroLicitacion, String placaIntra, String observaciones)
			throws GWorkException {

		Long stateVehicle = new Long(Util.loadMessageValue("ESTADO.2"));
		VehiclesStates vehiclesStates = null;
		List<Vehicles> listVehicles = new VehiclesDAO().findByVhcPlacaDiplomatica(placa.trim().toUpperCase());

		ClientsSalesVehicles clientsSalesVehicles = null;

		if (listVehicles != null && listVehicles.size() > 0) {

			Vehicles vehicles = listVehicles.get(0);

			if (!vehicles.getVehiclesStates().getVhsCodigo().equals(5L))
				throw new GWorkException(Util.loadErrorMessageValue("VEHICULO.NORETIRADO"));

			clientsSalesVehicles = new ClientsSalesVehicles();

			if (numeroIdentificacion != null)
				clientsSalesVehicles.setCsvIdentificacacion(numeroIdentificacion);

			if (nombreComprador != null)
				clientsSalesVehicles.setCsvNombre(nombreComprador.trim().toUpperCase());

			if (telefono != null)
				clientsSalesVehicles.setCsvTelefono(telefono.toString());

			if (direccion != null)
				clientsSalesVehicles.setCsvDireccion(direccion.trim().toUpperCase());

			if (email != null)
				clientsSalesVehicles.setCsvMail(email.trim());

			if (valorVenta != null)
				clientsSalesVehicles.setCsvValorVenta(valorVenta);

			if (atFinal != null)
				clientsSalesVehicles.setCsvAtFinal(atFinal.trim().toUpperCase());

			if (numeroLicitacion != null && numeroLicitacion.trim().length() !=0)
				clientsSalesVehicles.setCsvNumeroLicitacion(new Long(numeroLicitacion));

			if (placaIntra != null && placaIntra.trim().length() != 0)
				clientsSalesVehicles.setCsvPlacaIntra(placaIntra.trim());

			if (observaciones != null && observaciones.trim().length() !=0)
				clientsSalesVehicles.setCsvObservaciones(observaciones.trim());
			
			if (fechaEntrega != null)
				clientsSalesVehicles.setCsvFechaEntrega(fechaEntrega);

			if (fechaLicitacion != null)
				clientsSalesVehicles.setCsvFechaLicitacion(fechaLicitacion);
			
			if (vehicles != null){
				if(atFinal != null && atFinal.length()>0){
					Long idVhcStates = 0l;
					try{
						idVhcStates = Long.parseLong(Util.loadParametersValue("VHC.STATE.RET"));
					}
					catch(Exception e){
						throw new GWorkException(e.getMessage());
					}
					
					vehiclesStates = (new VehiclesStatesDAO()).findById(idVhcStates);
					if(vehiclesStates!=null)
					vehicles.setVehiclesStates(vehiclesStates);
				}
				else{
					if (stateVehicle != null){
						vehiclesStates = new VehiclesStatesDAO().findById(stateVehicle);
						if (vehiclesStates != null)
							vehicles.setVehiclesStates(vehiclesStates);
					}
				}
				clientsSalesVehicles.setVehicles(vehicles);
			}

			guardarClienteComprador(clientsSalesVehicles);
		} else
			throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));
	}

	public void guardarClienteComprador(ClientsSalesVehicles clientsSalesVehicles) throws GWorkException {
		try {
			EntityManagerHelper.getEntityManager().getTransaction().begin();
			(new ClientsSalesVehiclesDAO()).save(clientsSalesVehicles);
			if(clientsSalesVehicles.getVehicles()!=null)
				(new VehiclesDAO()).update(clientsSalesVehicles.getVehicles());
			EntityManagerHelper.getEntityManager().getTransaction().commit();
			EntityManagerHelper.getEntityManager().clear();
		} catch (Exception e) {
			EntityManagerHelper.getEntityManager().getTransaction().rollback();
			throw new GWorkException(e.getMessage());
		}
	}

	public void limpiarSession() {
		if (EntityManagerHelper.getEntityManager().isOpen())
			EntityManagerHelper.getEntityManager().clear();
		EntityManagerHelper.getEntityManager().close();
	}
	
	public ClientsSalesVehicles consultarVentasVhc(String placaVhc)
										throws GWorkException{
		
		List<ClientsSalesVehicles> clientSalesVehicle;
			
		/*Consulta para obtener el registro de la tabla client_sales_service*/
		String queryString = "SELECT csv FROM ClientsSalesVehicles csv" +
							 " WHERE csv.vehicles.vhcPlacaDiplomatica = :placaVehiculo";
		
		Query query = EntityManagerHelper.getEntityManager().createQuery(
				queryString);
		query.setParameter("placaVehiculo", placaVhc.toUpperCase());
		//query.setParameter("vhsCodigo", Long.parseLong(Util.loadMessageValue("ESTADO.2")));
		
		clientSalesVehicle = (List<ClientsSalesVehicles>)query.getResultList();
		
		if(clientSalesVehicle!=null && clientSalesVehicle.size()>0){
			ClientsSalesVehicles clientSaleVhc = clientSalesVehicle.get(0);
			return clientSaleVhc;
		}
		else
			throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));
	}
	
	public void modificarVentasVhc(
		String placa, String numeroIdentificacion, String nombreComprador,
		String direccion, String telefono, String email, String valorVenta, String atFinal, Date fechaEntrega,
		Date fechaLicitacion, String numeroLicitacion, String placaIntra, String observaciones)
		throws GWorkException {
		
		List<Vehicles> listVehicles = new VehiclesDAO().findByVhcPlacaDiplomatica(placa.trim().toUpperCase());
	
		ClientsSalesVehicles clientsSalesVehicles = null;
	
		if (listVehicles != null && listVehicles.size() > 0) {
	
			Vehicles vehicles = listVehicles.get(0);
			
			Long codigoCss = null;
			codigoCss = consultarVentasVhc(placa).getCvsCodigo();
			
			if(codigoCss!= null && codigoCss>0){
				clientsSalesVehicles = new ClientsSalesVehicles();
				
				if (codigoCss != null && codigoCss > 0)
					clientsSalesVehicles.setCvsCodigo(codigoCss);
		
				if (numeroIdentificacion != null)
					clientsSalesVehicles.setCsvIdentificacacion(numeroIdentificacion);
		
				if (nombreComprador != null)
					clientsSalesVehicles.setCsvNombre(nombreComprador.trim().toUpperCase());
		
				if (telefono != null)
					clientsSalesVehicles.setCsvTelefono(telefono.toString());
		
				if (direccion != null)
					clientsSalesVehicles.setCsvDireccion(direccion.trim().toUpperCase());
		
				if (email != null)
					clientsSalesVehicles.setCsvMail(email.trim());
		
				if (valorVenta != null)
					clientsSalesVehicles.setCsvValorVenta(valorVenta);
		
				if (atFinal != null)
					clientsSalesVehicles.setCsvAtFinal(atFinal.trim().toUpperCase());
		
				if (numeroLicitacion != null && numeroLicitacion.trim().length() !=0)
					clientsSalesVehicles.setCsvNumeroLicitacion(new Long(numeroLicitacion));
		
				if (placaIntra != null && placaIntra.trim().length() != 0)
					clientsSalesVehicles.setCsvPlacaIntra(placaIntra.trim());
		
				if (observaciones != null && observaciones.trim().length() !=0)
					clientsSalesVehicles.setCsvObservaciones(observaciones.trim());
		
				if (vehicles != null){
					if(atFinal != null && atFinal.length()>0){
						Long idVhcStates = 0l;
						try{
							idVhcStates = Long.parseLong(Util.loadParametersValue("VHC.STATE.RET"));
						}
						catch(Exception e){
							throw new GWorkException(e.getMessage());
						}
						
						VehiclesStates vehiclesStates = (new VehiclesStatesDAO()).findById(idVhcStates);
						if(vehiclesStates!=null)
						vehicles.setVehiclesStates(vehiclesStates);
					}
					clientsSalesVehicles.setVehicles(vehicles);
				}
		
				if (fechaEntrega != null)
					clientsSalesVehicles.setCsvFechaEntrega(fechaEntrega);
		
				if (fechaLicitacion != null)
					clientsSalesVehicles.setCsvFechaLicitacion(fechaLicitacion);
				
				ModificarClienteComprador(clientsSalesVehicles);
			}
			else
				throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));
		} else
			throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));
	}
	
	public void ModificarClienteComprador(ClientsSalesVehicles clientsSalesVehicles) throws GWorkException {
		try {
			//EntityManagerHelper.getEntityManager().refresh(clientsSalesVehicles);
			EntityManagerHelper.getEntityManager().getTransaction().begin();
			(new ClientsSalesVehiclesDAO()).update(clientsSalesVehicles);
			if(clientsSalesVehicles.getVehicles()!=null)
				(new VehiclesDAO()).update(clientsSalesVehicles.getVehicles());
			EntityManagerHelper.getEntityManager().getTransaction().commit();
			EntityManagerHelper.getEntityManager().clear();
		} 
		catch (Exception e) {
			EntityManagerHelper.getEntityManager().getTransaction().rollback();
			throw new GWorkException(e.getMessage());
		}
	}
}
