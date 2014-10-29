package geniar.siscar.logic.accidents.services;

import java.util.List;

import geniar.siscar.model.Witnesses;
import gwork.exception.GWorkException;

public interface WitnessesService {

	public void registrarTestigo( Long idAccidents,
			String wtnIdentificacion, String wtnNombre, String wtnDireccion,
			String wtnTelefono) throws GWorkException;

	public void modificarTestigo(Long idTestigo, 
			Long idAccidents, String wtnIdentificacion, String wtnNombre,
			String wtnDireccion, String wtnTelefono) throws GWorkException;

	public void eliminarTestigo(Long idTestigo) throws GWorkException;

	public List<Witnesses> listarTestigos(Long idAccidente)
			throws GWorkException;

}
