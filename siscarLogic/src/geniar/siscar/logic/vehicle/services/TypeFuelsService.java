/**
 * 
 */
package geniar.siscar.logic.vehicle.services;

import geniar.siscar.model.FuelsTypes;
import gwork.exception.GWorkException;

import java.util.List;

/**
 * @author Jorge
 * 
 */
public interface TypeFuelsService {
	
	public void crearTipoCombustible(String tcNombre) throws GWorkException;
	public FuelsTypes consultarTipoCombustible(String tcNombre);
	public void eliminarTipoCombustible(String tcNombre)throws GWorkException;
	public void modificarTipoCombustible(Long idTipoCombustible,String tcNombre)throws GWorkException;
	public List<FuelsTypes> listTipoCombustible();
	public FuelsTypes consultarTipoCombustibleById(Long idCombustible);
}
