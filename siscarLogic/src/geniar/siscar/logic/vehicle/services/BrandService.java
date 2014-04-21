package geniar.siscar.logic.vehicle.services;

import geniar.siscar.model.Brands;
import geniar.siscar.model.Lines;

import gwork.exception.GWorkException;

import java.util.List;

/**
 * The Interface BrandService.
 *
 * @author Rodrigo Lopez
 */
public interface BrandService {
    
    /**
     * Consultar marca por nombre.
     *
     * @param mrcNombre the mrc nombre
     * @return the brands
     * @throws GWorkException the g work exception
     */
    public Brands consultarMarcaPorNombre(String mrcNombre)
        throws GWorkException;

    /**
     * Consultar marca por id.
     *
     * @param id the id
     * @return the brands
     * @throws GWorkException the g work exception
     */
    public Brands consultarMarcaPorId(Long id)throws GWorkException;

    /**
     * Crear marcas.
     *
     * @param mrcNombre the mrc nombre
     * @throws GWorkException the g work exception
     */
    public void crearMarcas(String mrcNombre) throws GWorkException;

    /**
     * Modificar marca.
     *
     * @param idBrand the id brand
     * @param mrcNombre the mrc nombre
     * @throws GWorkException the g work exception
     */
    public void modificarMarca(Long idBrand, String mrcNombre)
        throws GWorkException;

    /**
     * Eliminar marca.
     *
     * @param idBrand the id brand
     * @throws GWorkException the g work exception
     */
    public void eliminarMarca(Long idBrand) throws GWorkException;

    /**
     * Lista marcas.
     *
     * @return the list
     * @throws GWorkException the g work exception
     */
    public List<Brands> listaMarcas() throws GWorkException;
    
    /**
     * List lines.
     *
     * @return the list
     * @throws GWorkException the g work exception
     */
    public List<Lines>  listLines()   throws GWorkException;
    
    /**
     * Pruebas contables.
     *
     * @throws GWorkException the g work exception
     */
    public void pruebasContables() throws GWorkException;
}
