package geniar.siscar.logic.parameters.services;

import java.util.Date;
import java.util.List;
import geniar.siscar.model.MoneyType;
import geniar.siscar.model.Tariffs;
import geniar.siscar.model.TariffsTypes;
import geniar.siscar.model.Zones;
import gwork.exception.GWorkException;

public interface TariffsHouseCiatService {

	public void crearTarifaCasaCiat(Zones zona, MoneyType moneda,
			Float trfValor, Date fechaVigencia, Date periodo,
			TariffsTypes tipoTarifa) throws GWorkException;

	public void modificarTarifaCasaCiat(Long idZona, Long idMoneda,
			Date fechaVigencia, Integer periodo, Float trfValor)
			throws GWorkException;

	public void eliminarTarifaCasaCiat(Long idTarifa) throws GWorkException;

	public Tariffs consultarTarifaCasaCiat(Long idTarifa) throws GWorkException;

	public List<Tariffs> listTarifaCasaCiat() throws GWorkException;

	public Tariffs consultarTarifaActualCasaCiat(Long idZona, Long idMoneda)
			throws GWorkException;

}
