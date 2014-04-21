package geniar.siscar.logic.vehicle.services;

import geniar.siscar.model.Vehicles;
import geniar.siscar.model.VehiclesAssignation;
import gwork.exception.GWorkException;

import java.util.Date;
import java.util.List;

public interface VehicleService {
	public void crearVehiculo(String vhcNumeroTl, String vhcPlacaDiplomatica,
			String vhcPlacaActivoFijo, Long lines, String vhcCatalogado,
			String vhcNumeroMotor, String vhcNumeroSerie, Long typesVehicles,
			Long vhcAno, String vhcColor, Long locations, Long typesFuels,
			String vhcOdometro, Long vhcCapacidad, Float vhcCapacidadTanque,
			String vhcValorComercial, String vhcAnoValCom,
			Date vhcFechaProtocolo, String vhcNumeroManifiesto,
			Date vhcFechaManifiesto, String vhcNumDeclImpor,
			String vhcPlRemVehi, String vhcNumeroLevante, Date vhcFechaLevante,
			String vhcDocumentTrans, String vhcNumeroFactura,
			String vhcOrderCompra, String vhcProveedor, String vhcAtInicial,
			String vhcCiuAduan, String vhcVidaUtilNumber, Float vhcValorCif,
			Float vhcValorFob, Float vhcCargosImport, String vhcObservaciones,
			Long typesTapestries, Long typesTractions, Long typesTransmissions,
			String vhcCilindraje, String vhcModelo, String vhcRemplazaA,
			Long cities, Long vhcAnofabricacion, Long vhcMesfabricacion)
			throws GWorkException;

	public void modificarVehiculo(String placaAnterior,
			String numeroTlAnterior, String placaActivoAnterior,
			String vhcNumeroTl, String vhcPlacaDiplomatica,
			String vhcPlacaActivoFijo, Long statesVehicles, String lines,
			String vhcCatalogado, String vhcNumeroMotor, String vhcNumeroSerie,
			Long typesVehicles, Long vhcAno, String vhcColor, Long locations,
			Long typesFuels, String vhcOdometro, Long vhcCapacidad,
			Float vhcCapacidadTanque, String vhcValorComercial,
			String vhcAnoValCom, Date vhcFechaProtocolo,
			String vhcNumeroManifiesto, Date vhcFechaManifiesto,
			String vhcNumDeclImpor, String vhcPlRemVehi,
			String vhcNumeroLevante, Date vhcFechaLevante,
			String vhcDocumentTrans, String vhcNumeroFactura,
			String vhcOrderCompra, String vhcProveedor, String vhcAtInicial,
			String vhcCiuAduan, String vhcVidaUtilNumber, Float vhcValorCif,
			Float vhcValorFob, Float vhcCargosImport, String vhcObservaciones,
			Long typesTapestries, Long typesTractions, Long typesTransmissions,
			String vhcCilindraje, String vhcModelo, String vhcRemplazaA,
			Long cities, Long vhcAnofabricacion, Long vhcMesfabricacion)
			throws GWorkException;

	public Vehicles consultarVehiculo(String vhcPlacaDiplomatica,
			String vhcPlacaActivoFijo, String vhcNumeroTl)
			throws GWorkException;

	public Vehicles consultarVehiculoPorIds(String vhcPlacaDiplomatica,
			String vhcPlacaActivoFijo, String vhcNumeroTl)
			throws GWorkException;

	public void actualizarVehiculo(Vehicles vehicles) throws GWorkException;

	public List<Vehicles> consultVehiclesByType(Long idType)
			throws GWorkException;

	public List<Vehicles> consultVehiclesByTypes(boolean pagina)
			throws GWorkException;

	public Vehicles consultVehiclesByPlacaDiplomatica(String placa)
			throws GWorkException;

	public Vehicles consultVehiclesByPlacaDiplomaticaSinFiltros(String placa)
			throws GWorkException;

	public VehiclesAssignation consultarAsignacionVehiculo(Long IdVehicle)
			throws GWorkException;

	public List<VehiclesAssignation> consultarHistorialVehiculos(
			Long idVehiculo, Long tipoVehiculo) throws GWorkException;

	public List<VehiclesAssignation> listAsignationByUser(
			String carneAsignatario) throws GWorkException;
}
