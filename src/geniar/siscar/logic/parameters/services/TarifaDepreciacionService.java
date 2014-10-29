package geniar.siscar.logic.parameters.services;

import geniar.siscar.model.FuelsTypes;
import geniar.siscar.model.Lines;

import geniar.siscar.model.Tariffs;
import geniar.siscar.model.TariffsTypes;
import geniar.siscar.model.TractionsTypes;
import gwork.exception.GWorkException;

import java.math.BigDecimal;
import java.util.Date;


public interface TarifaDepreciacionService {

	public void crearTarifa(Lines lines, TractionsTypes tipoTraccion,
			FuelsTypes fuelTypes, BigDecimal valor,
			Date anhoVigencia, Date fechaInicio) throws GWorkException;

	public void modificarTarifa(Long idLine, Long idTipoTraccion,
			Long idFuelTypes,Integer anhoVigencia,
			Date fechaInicio, BigDecimal valorDepreciacion) throws GWorkException;

	public TariffsTypes consultarTipoTarifa(Long codeTypeTariff)
			throws GWorkException;

	public Tariffs consultaAssignmentTariff(Long codeLine, Long codetypeTariff,
			Long codeFuelType);

	public Tariffs consultarTarifaAsignacion(Long idLinea,
			Long idTipoCombustible, Long idTipoTraccion)
			throws GWorkException;

	public Tariffs consultarTarifaAsignacionDepreciacion(Long idLinea,
			Long idTipoCombustible, Long idTipoTraccion)
			throws GWorkException;

}
