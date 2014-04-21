package geniar.siscar.logic.parameters.services;

import geniar.siscar.model.Locations;
import gwork.exception.GWorkException;

import java.util.List;

public interface LocationService {

	public void crearLugar(String descripcion, Long idCiudad, Long idTypeLocation) throws GWorkException;

	public void modificarLugar(Long idUbicacion, Long idCiudad, String descripcion) throws GWorkException;

	public void eliminarLugar(Long idCiudad, Long idTipoUbicacion) throws GWorkException;

	public List<Locations> listaLugar() throws GWorkException;

	public Locations consultarLugar(Long codigo, Long idTipoUbicacion) throws GWorkException;

}
