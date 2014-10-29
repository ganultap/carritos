package geniar.siscar.logic.accidents.services;

import java.util.List;

import geniar.siscar.model.AccidentsFiles;
import gwork.exception.GWorkException;

public interface AccidentFilesService {

	public void registrarArchivosAccidente(Long idAccidente, String acfRuta,
			String acfDescripcion, String acfNombre) throws GWorkException;

	public void modificarArchivosAccidente(Long idArchivoAccidente,
			Long idAccidente, String acfRuta, String acfDescripcion,
			String acfNombre) throws GWorkException;

	public void eliminarArchivosAccidente(Long idArchivoAccidente)
			throws GWorkException;

	public List<AccidentsFiles> listarArchivos(Long idAccidente)
			throws GWorkException;
}
