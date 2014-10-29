package geniar.siscar.logic.vehicle.services;

import geniar.siscar.model.Lines;

import gwork.exception.GWorkException;

import java.util.List;


public interface LinesService {
   
	public void crearLineas(String lnsNombre, Long codigoMrca)
        throws GWorkException;

    public Lines consultarLineasByName(String lnsNombre) throws GWorkException;

    public void eliminarLineas(Long idLine) throws GWorkException;
    
    public void modificarLinea(Long idLine,String lnsNombre, Long idBrand)throws GWorkException;

    public List<Lines> listLineas() throws GWorkException;

    public Lines consultarLineasById(Long codigo) throws GWorkException;
}
