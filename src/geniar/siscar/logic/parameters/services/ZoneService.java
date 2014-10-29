package geniar.siscar.logic.parameters.services;

import java.util.List;

import geniar.siscar.model.Zones;
import gwork.exception.GWorkException;

public interface ZoneService {

	 public Zones consultarZone(Long codeZone)throws GWorkException;
	 
	 public Zones consultarZoneByName(String znsNombre)throws GWorkException;
	 
	 public void crearZona(String znsNombre,String znsDescripcion,
			 String znsKilometraje)throws GWorkException;
	 
	 public void modificarZona(Long znsId, String znsNombre,String znsDescripcion,
			 String znsKilometraje)throws GWorkException;
	 
	 public List<Zones> listaZona()throws GWorkException;
	 
	 public void eliminarZona(String znsNombre)throws GWorkException;
	 
	
}
