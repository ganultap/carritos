package geniar.siscar.logic.security.serivice;

import geniar.siscar.model.Options;
import gwork.exception.GWorkException;

import java.util.List;

public interface OptionsService {

	public Options consultarOpcion(Long id);

	public List<Options> getOpciones();
	
	public void crearOpcionRol(Long idOpcion, Long idRol)throws GWorkException;
	
	public void eliminarOpcionRol(Long idOpcion, Long idRol)throws GWorkException;

}
