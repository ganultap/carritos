package geniar.siscar.logic.billing.services;

import geniar.siscar.model.VOPrepagoInicial;
import gwork.exception.GWorkException;

import java.util.Date;
import java.util.List;

public interface FuelProofService {

	public List<VOPrepagoInicial> listaCargaPrepago(Long idPeriodo,
			Date fechaIniConsumo, Date fechaFinConsumo) throws GWorkException;
}
