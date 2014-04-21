package geniar.siscar.logic.billing.services;

import geniar.siscar.model.VOCostCenters;
import gwork.exception.GWorkException;

import java.util.List;

public interface PrepaidDevolutionService {

	public List<VOCostCenters> listaCostcenterFuelDevolution()
			throws GWorkException;

	public void generarComprobanteDevolucionPrepago(String login,
			List<VOCostCenters> listaVOCargaPrepago) throws GWorkException;

}
