package geniar.siscar.logic.fuels.services;

import geniar.siscar.model.Prepaid;
import geniar.siscar.model.PrepaidConsumption;
import gwork.exception.GWorkException;

import java.util.Date;
import java.util.List;

public interface SearchPrepaidService {

	public List<Prepaid> consultarDisponibilidaPrepago(Date fechaIni,
			Date fechaFin, String placa) throws GWorkException;

	public List<Object[]> valorDisponiblePrepago(Date fechaIni, Date fechaFin,
			Long idPrepago, String centroCosto) throws GWorkException;

	public List<Prepaid> DisponibilidadPrepagoCCAsignatario(Date fechaIni,
			Date fechaFin, String centroCosto, String asignatario)
			throws GWorkException;

	public List<PrepaidConsumption> detalleConsumo(Long idCentroCosto,
			Date fechaIni, Date fechaFin) throws GWorkException;

	public List<PrepaidConsumption> consumoPrepagoByVehiculo(
			String idCentroCosto, String placa, Date fechaIni, Date fechaFin)
			throws GWorkException;

	public String disponibleCC(String idCentroCosto);
	
	
}
