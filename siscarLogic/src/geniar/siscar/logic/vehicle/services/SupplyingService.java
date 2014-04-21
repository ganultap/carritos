package geniar.siscar.logic.vehicle.services;

import geniar.siscar.model.SupplyingCatalogs;
import gwork.exception.GWorkException;

import java.util.List;

public interface SupplyingService {

	public void crearCatalogoAprovisionamientoVehiculo(String num_modelo, String catalogo, Long idMarca, Long idLinea,
			boolean bandera) throws GWorkException;

	public List<SupplyingCatalogs> consultarAprovisionamiento(String numCatalogo, Long marca, Long linea, Long modelo)
			throws GWorkException;

	public List<SupplyingCatalogs> getListCatalogs() throws GWorkException;

	public void EliminarCatalogo(Long idCatalogo,Long idModelo) throws GWorkException;

}
