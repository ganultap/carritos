package geniar.siscar.logic.billing.services;

import geniar.siscar.model.CargaPrepago;
import geniar.siscar.model.VOPrepagoInicial;
import gwork.exception.GWorkException;

import java.util.List;

public interface FuelProofInitialService {

	public List<CargaPrepago> listaCargaPrepago() throws GWorkException;

	public boolean validarDisponibilidadCCPrepago(String centroCosto,
			Float valor) throws GWorkException;

	public void generarComprobante(Long tipoComprobante, String login,
			Long periodo, List<VOPrepagoInicial> listaVOCargaPrepago)
			throws GWorkException;

}
