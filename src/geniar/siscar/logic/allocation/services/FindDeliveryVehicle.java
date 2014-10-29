package geniar.siscar.logic.allocation.services;

import geniar.siscar.model.VehiclesAssignation;
import gwork.exception.GWorkException;

import java.util.List;

public interface FindDeliveryVehicle {
	
	public List<VehiclesAssignation> findByVhaNumeroCarne(Object vhaNumeroCarne)throws GWorkException;
	public List<VehiclesAssignation> findByRentAssinationCarne(Object vhaNumeroCarne) throws GWorkException;

}
