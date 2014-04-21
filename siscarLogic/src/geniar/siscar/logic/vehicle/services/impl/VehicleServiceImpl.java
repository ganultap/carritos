/**
 *
 */
package geniar.siscar.logic.vehicle.services.impl;

import geniar.siscar.logic.consultas.SearchVehicles;
import geniar.siscar.logic.vehicle.services.VehicleService;
import geniar.siscar.model.FuelsTypes;
import geniar.siscar.model.Lines;
import geniar.siscar.model.Locations;
import geniar.siscar.model.TapestriesTypes;
import geniar.siscar.model.TractionsTypes;
import geniar.siscar.model.TransmissionsTypes;
import geniar.siscar.model.Vehicles;
import geniar.siscar.model.VehiclesAssignation;
import geniar.siscar.model.VehiclesStates;
import geniar.siscar.model.VehiclesTypes;
import geniar.siscar.persistence.EntityManagerHelper;
import geniar.siscar.persistence.FuelsTypesDAO;
import geniar.siscar.persistence.IVehiclesDAO;
import geniar.siscar.persistence.LinesDAO;
import geniar.siscar.persistence.TapestriesTypesDAO;
import geniar.siscar.persistence.TractionsTypesDAO;
import geniar.siscar.persistence.TransmissionsTypesDAO;
import geniar.siscar.persistence.VehiclesDAO;
import geniar.siscar.persistence.VehiclesStatesDAO;
import geniar.siscar.persistence.VehiclesTypesDAO;
import geniar.siscar.util.IsNullOrEmpty;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

import java.util.Date;
import java.util.List;

/**
 * @author Julian Marin
 * 
 */
public class VehicleServiceImpl implements VehicleService {

	public void crearVehiculo(String vhcNumeroTl, String vhcPlacaDiplomatica,
			String vhcPlacaActivoFijo, Long Idlines, String vhcCatalogado,
			String vhcNumeroMotor, String vhcNumeroSerie, Long typesVehicles,
			Long vhcAno, String vhcColor, Long Idlocations, Long typesFuels,
			String vhcOdometro, Long vhcCapacidad, Float vhcCapacidadTanque,
			String vhcValorComercial, String vhcAnoValCom,
			Date vhcFechaProtocolo, String vhcNumeroManifiesto,
			Date vhcFechaManifiesto, String vhcNumDeclImpor,
			String vhcPlRemVehi, String vhcNumeroLevante, Date vhcFechaLevante,
			String vhcDocumentTrans, String vhcNumeroFactura,
			String vhcOrderCompra, String vhcProveedor, String vhcAtInicial,
			String vhcCiuAduan, String vhcVidaUtilNumber, Float vhcValorCif,
			Float vhcValorFob, Float vhcCargosImport, String vhcObservaciones,
			Long typesTapestries, Long IdtypesTractions,
			Long IdtypesTransmissions, String vhcCilindraje, String vhcModelo,
			String vhcRemplazaA, Long Idcities, Long vhcAnofabricacion,
			Long vhcMesfabricacion) throws GWorkException {

		Long countPlacas=0L;
		if (vhcRemplazaA != null && vhcRemplazaA.trim().length() != 0)
			SearchVehicles.consultarVehiculosReemplazoPlacaDiplomatica(
					vhcRemplazaA.trim().toUpperCase(), vhcPlacaDiplomatica
							.trim().toUpperCase());

		VehiclesStates vehiclesStates = null;
		Lines lines = new Lines();
		FuelsTypes fuels = null;
		VehiclesTypes vehiclesTypes = null;
		Locations locations = null;
		TapestriesTypes tapestries = null;
		TractionsTypes tractions = null;
		TransmissionsTypes transmissions = null;

		vehiclesStates = new VehiclesStatesDAO().findById(new Long(Util
				.loadMessageValue("ESTADO.6")));

		if (vehiclesStates == null)
			throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));
		if (Idlines != null)
			lines = new LinesDAO().findById(Idlines);

		if (lines != null)
			lines.setLnsId(lines.getLnsId());

		if (lines == null)
			throw new GWorkException(Util.loadErrorMessageValue("LINEA.SEL"));

		fuels = new FuelsTypesDAO().findById(typesFuels);

		if (fuels == null)
			throw new GWorkException(Util.loadErrorMessageValue("TIPOCOM.SEL"));

		if (typesVehicles != null)
			vehiclesTypes = new VehiclesTypesDAO().findById(typesVehicles);

		if (typesTapestries != null)
			tapestries = new TapestriesTypesDAO().findById(typesTapestries);

		if (IdtypesTractions != null)
			tractions = new TractionsTypesDAO().findById(IdtypesTractions);

		if (IdtypesTransmissions != null)
			transmissions = new TransmissionsTypesDAO()
					.findById(IdtypesTransmissions);

		if (Idlocations != null)
			locations = SearchVehicles
					.consultarDatosUbicacionPorIdCiudad(Idlocations);

		Vehicles vehicles = new Vehicles();
		IVehiclesDAO vehiclesDAO = new VehiclesDAO();

		/** Objetos que se necesitan para crear vehiculos */

		List<Vehicles> conditioNumeroTL = vehiclesDAO
				.findByVhcNumeroTl(vhcNumeroTl.trim().toUpperCase());
		if (conditioNumeroTL != null && conditioNumeroTL.size() > 0)
			throw new GWorkException(Util
					.loadErrorMessageValue("NUMEROTL.EXISTE"));

		List<Vehicles> conditioPlacaDiplomatica = vehiclesDAO
				.findByVhcPlacaDiplomatica(vhcPlacaDiplomatica.trim()
						.toUpperCase());
		List<Vehicles> conditioPlacaActivoFijo = vehiclesDAO
				.findByVhcPlacaActivoFijo(vhcPlacaActivoFijo.trim()
						.toUpperCase());
		
		
		//modificado el 9 marzo 2012-carlosguzman
		int tamaños=conditioPlacaDiplomatica.size();
		for(int i=0;i<tamaños;i++){
			if(conditioPlacaDiplomatica.get(i).getVehiclesStates().getVhsCodigo()!=5)
			countPlacas=1L;
		}
		//
		//modificado el 9 marzo 2012-carlosguzman
		if ((conditioPlacaDiplomatica != null && conditioPlacaDiplomatica
				.size() > 0) && countPlacas!=0)
			throw new GWorkException(Util.loadErrorMessageValue("PLACA.EXISTE"));
        //
		if (conditioPlacaActivoFijo != null
				&& conditioPlacaActivoFijo.size() > 0)
			throw new GWorkException(Util
					.loadErrorMessageValue("ACTIVO.EXISTE"));

		EntityManagerHelper.beginTransaction();

		if (vhcNumeroTl != null)
			vehicles.setVhcNumeroTl(vhcNumeroTl.trim().toUpperCase());

		if (vhcPlacaDiplomatica != null)
			vehicles.setVhcPlacaDiplomatica(vhcPlacaDiplomatica.trim()
					.toUpperCase());

		if (vhcPlacaActivoFijo != null)
			vehicles.setVhcPlacaActivoFijo(vhcPlacaActivoFijo.trim()
					.toUpperCase());

		if (vhcCatalogado != null)
			vehicles.setVhcNumReferCat(vhcCatalogado.trim().toUpperCase());

		if (vhcNumeroMotor != null)
			vehicles.setVhcNumeroMotor(vhcNumeroMotor.trim().toUpperCase());

		if (vhcNumeroSerie != null)
			vehicles.setVhcNumeroSerie(vhcNumeroSerie.trim().toUpperCase());

		if (vhcAno != null)
			vehicles.setVhcAno(vhcAno);

		if (vhcColor != null)
			vehicles.setVhcColor(vhcColor.trim().toUpperCase());

		if (vhcOdometro != null)
			vehicles.setVhcOdometro(vhcOdometro);

		if (vhcCapacidad != null)
			vehicles.setVhcCapacidad(vhcCapacidad);

		if (vhcCapacidadTanque != null)
			vehicles.setVhcCapMaxTanq(vhcCapacidadTanque);

		if (vhcValorComercial != null && vhcValorComercial.trim().length() != 0)
			vehicles.setVhcValorComercial(vhcValorComercial);

		if (vhcAnoValCom != null && vhcAnoValCom.trim().length() != 0)
			vehicles.setVhcAnoValCom(new Long(vhcAnoValCom));

		if (vhcFechaProtocolo != null)
			vehicles.setVhcFechaProtocolo(vhcFechaProtocolo);

		if (vhcNumeroManifiesto != null)
			vehicles.setVhcNumeroManifiesto(vhcNumeroManifiesto.trim()
					.toUpperCase());

		if (vhcFechaManifiesto != null)
			vehicles.setVhcFechaManifiesto(vhcFechaManifiesto);

		if (vhcNumDeclImpor != null)
			vehicles.setVhcNumDeclImpor(vhcNumDeclImpor.trim().toUpperCase());

		if (vhcPlRemVehi != null)
			vehicles.setVhcPlRemVehi(vhcPlRemVehi.trim().toUpperCase());

		if (vhcNumeroLevante != null)
			vehicles.setVhcNumeroLevante(vhcNumeroLevante.trim().toUpperCase());

		if (vhcFechaLevante != null)
			vehicles.setVhcFechaLevante(vhcFechaLevante);

		if (vhcDocumentTrans != null)
			vehicles.setVhcDocumentTrans(vhcDocumentTrans.trim().toUpperCase());

		if (vhcNumeroFactura != null)
			vehicles.setVhcNumeroFactura(vhcNumeroFactura.trim().toUpperCase());

		if (vhcOrderCompra != null)
			vehicles.setVhcOrderCompra(vhcOrderCompra.trim().toUpperCase());

		if (vhcProveedor != null)
			vehicles.setVhcProveedor(vhcProveedor.trim().toUpperCase());

		if (vhcAtInicial != null)
			vehicles.setVhcAtInicial(vhcAtInicial.trim().toUpperCase());

		if (vhcVidaUtilNumber != null && vhcVidaUtilNumber.trim().length() != 0)
			vehicles.setVhcVidaUtil(vhcVidaUtilNumber);

		if (vhcValorCif != null)
			vehicles.setVhcValorCif(vhcValorCif.toString());

		if (vhcValorFob != null)
			vehicles.setVhcValorFob(vhcValorFob.toString());

		if (vhcCiuAduan != null && vhcCiuAduan.trim().length() != 0)
			vehicles.setVhcCiuAduan(vhcCiuAduan);

		if (vhcCargosImport != null)
			vehicles.setVhcCargosImport(vhcCargosImport.toString());
		vehicles.setVhcObservaciones(vhcObservaciones);
		vehicles.setVhcCilindraje(vhcCilindraje);
		vehicles.setVhcModelo(vhcModelo);
		vehicles.setVhcAnofabricacion(vhcAnofabricacion);
		vehicles.setVhcMesfabricacion(vhcMesfabricacion);

		if (vhcRemplazaA != null)
			vehicles.setVhcRemplazaA(vhcRemplazaA.trim().toUpperCase());

		if (vhcOdometro != null)
			vehicles.setVhcUnidadTaquim(vhcOdometro);

		if (vhcRemplazaA != null)
			vehicles.setVhcRemplazaA(vhcRemplazaA.trim().toUpperCase());

		/** Se adiciona los objetos relacionadosal vehiculo */

		vehicles.setVehiclesStates(vehiclesStates);

		vehicles.setLines(lines);

		vehicles.setVehiclesTypes(vehiclesTypes);

		vehicles.setFuelsTypes(fuels);

		vehicles.setLocations(locations);

		vehicles.setTapestriesTypes(tapestries);

		vehicles.setTractionsTypes(tractions);

		vehicles.setTransmissionsTypes(transmissions);

		guardarVehiculo(vehicles);
	}

	public void guardarVehiculo(Vehicles vehicles) throws GWorkException {
		try {
			validarSession();
			EntityManagerHelper.beginTransaction();
			new VehiclesDAO().save(vehicles);
			EntityManagerHelper.commit();
			limpiarSession();
		} catch (Exception e) {
			EntityManagerHelper.rollback();
			validarSession();
			throw new GWorkException(e.getMessage());
		}
	}

	public void actualizarVehiculo(Vehicles vehicles) throws GWorkException {
		try {
			validarSession();
			EntityManagerHelper.beginTransaction();
			new VehiclesDAO().update(vehicles);
			EntityManagerHelper.commit();
			limpiarSession();
		} catch (Exception e) {
			EntityManagerHelper.rollback();
			validarSession();
			throw new GWorkException(e.getMessage());
		}
	}

	public void validarSession() {
		if (EntityManagerHelper.getEntityManager().isOpen())
			EntityManagerHelper.getEntityManager().close();
	}

	public void limpiarSession() {
		EntityManagerHelper.getEntityManager().clear();
		EntityManagerHelper.closeEntityManager();
	}

	public Vehicles validarDatosConsultaModificarVehiculo(String placaAnterior,
			
			String numeroTlAnterior, String placaActivoAnterior,
			String vhcNumeroTl, String vhcPlacaDiplomatica,
			String vhcPlacaActivoFijo) throws GWorkException {
		limpiarSession();
		List<Vehicles> listVehicles = null;
		int count=0;

		if (vhcPlacaDiplomatica != null
				&& vhcPlacaDiplomatica.trim().length() != 0
				&& placaAnterior != null && placaAnterior.trim().length() != 0) {

			if (!vhcPlacaDiplomatica.equalsIgnoreCase(placaAnterior)) {
				listVehicles = new VehiclesDAO()
						.findByVhcPlacaDiplomatica(vhcPlacaDiplomatica);
				
				//modificado 16 marzo.carlos guzman
				for(int i=0;i<listVehicles.size();i++){
					if(!listVehicles.get(i).getVehiclesStates().getVhsCodigo().equals(5))
					count++;
					
				}
				
				if (listVehicles != null && listVehicles.size() > 0 && count==0)
					throw new GWorkException(Util
							.loadErrorMessageValue("PLACA.EXISTE"));
			}
		}

		if (vhcPlacaActivoFijo != null
				&& vhcPlacaActivoFijo.trim().length() != 0
				&& placaActivoAnterior != null
				&& placaActivoAnterior.trim().length() != 0) {
			if (!vhcPlacaActivoFijo.equalsIgnoreCase(placaActivoAnterior)) {
				listVehicles = new VehiclesDAO()
						.findByVhcPlacaActivoFijo(vhcPlacaActivoFijo);
				if (listVehicles != null && listVehicles.size() > 0)
					throw new GWorkException(Util
							.loadErrorMessageValue("PLACA.EXISTE"));
			}
		}

		if (vhcNumeroTl != null && vhcNumeroTl.trim().length() != 0
				&& numeroTlAnterior != null
				&& numeroTlAnterior.trim().length() != 0) {
			if (!vhcNumeroTl.equalsIgnoreCase(numeroTlAnterior)) {
				listVehicles = new VehiclesDAO().findByVhcNumeroTl(vhcNumeroTl);
				if (listVehicles != null && listVehicles.size() > 0)
					throw new GWorkException(Util
							.loadErrorMessageValue("NUMEROTL.EXISTE"));
			}
		}
		return new VehiclesDAO().findByVhcNumeroTl(numeroTlAnterior).get(0);
	}

	/** Metodo de modificar un vehiculo */
	public void modificarVehiculo(String placaAnterior,
			String numeroTlAnterior, String placaActivoAnterior,
			String vhcNumeroTl, String vhcPlacaDiplomatica,
			String vhcPlacaActivoFijo, Long statesVehicles, String linesName,
			String vhcCatalogado, String vhcNumeroMotor, String vhcNumeroSerie,
			Long typesVehicles, Long vhcAno, String vhcColor, Long Idlocations,
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
			Long typesTapestries, Long IdtypesTractions,
			Long IdtypesTransmissions, String vhcCilindraje, String vhcModelo,
			String vhcRemplazaA, Long Idcities, Long vhcAnofabricacion,
			Long vhcMesfabricacion) throws GWorkException {

		Vehicles vehicles = validarDatosConsultaModificarVehiculo(
				placaAnterior, numeroTlAnterior, placaActivoAnterior,
				vhcNumeroTl, vhcPlacaDiplomatica, vhcPlacaActivoFijo);

		if (vhcRemplazaA != null && vhcRemplazaA.trim().length() != 0)
			SearchVehicles.consultarVehiculosReemplazoPlacaDiplomatica(
					vhcRemplazaA.trim().toUpperCase(), vhcPlacaDiplomatica);

		/** Objetos que se necesitan para crear vehiculos */
		List<Lines> listLines = null;
		Lines lines = null;
		FuelsTypes fuels = null;
		VehiclesTypes vehiclesTypes = null;
		Locations locations = null;
		TapestriesTypes tapestries = null;
		TractionsTypes tractions = null;
		TransmissionsTypes transmissions = null;
		
		listLines = new LinesDAO().findByLnsNombre(linesName.trim()
				.toUpperCase());

		if (listLines != null && listLines.size() > 0)
			lines = listLines.get(0);

		if (lines == null)
			throw new GWorkException(Util.loadErrorMessageValue("LINEA.SEL"));

		fuels = new FuelsTypesDAO().findById(typesFuels);

		if (fuels == null)
			throw new GWorkException(Util.loadErrorMessageValue("TIPOCOM.SEL"));

		if (typesVehicles != null)
			vehiclesTypes = new VehiclesTypesDAO().findById(typesVehicles);

		if (typesTapestries != null)
			tapestries = new TapestriesTypesDAO().findById(typesTapestries);

		if (IdtypesTractions != null)
			tractions = new TractionsTypesDAO().findById(IdtypesTractions);

		if (IdtypesTransmissions != null)
			transmissions = new TransmissionsTypesDAO()
					.findById(IdtypesTransmissions);

		if (Idlocations != null)
			locations = SearchVehicles
					.consultarDatosUbicacionPorIdCiudad(Idlocations);

		if (vhcNumeroTl != null)
			vehicles.setVhcNumeroTl(vhcNumeroTl.trim().toUpperCase());

		if (vhcPlacaDiplomatica != null)
			vehicles.setVhcPlacaDiplomatica(vhcPlacaDiplomatica.trim()
					.toUpperCase());

		if (vhcPlacaActivoFijo != null)
			vehicles.setVhcPlacaActivoFijo(vhcPlacaActivoFijo.trim()
					.toUpperCase());

		if (vhcCatalogado != null)
			vehicles.setVhcNumReferCat(vhcCatalogado.trim().toUpperCase());

		if (vhcNumeroMotor != null)
			vehicles.setVhcNumeroMotor(vhcNumeroMotor.trim().toUpperCase());

		if (vhcNumeroSerie != null)
			vehicles.setVhcNumeroSerie(vhcNumeroSerie.trim().toUpperCase());

		vehicles.setVhcAno(vhcAno);

		if (vhcColor != null)
			vehicles.setVhcColor(vhcColor.trim().toUpperCase());

		vehicles.setVhcOdometro(vhcOdometro);
		vehicles.setVhcCapacidad(vhcCapacidad);

		if (vhcCapacidadTanque != null)
			vehicles.setVhcCapMaxTanq(vhcCapacidadTanque);

		if (vhcValorComercial != null && vhcValorComercial.trim().length() != 0)
			vehicles.setVhcValorComercial(vhcValorComercial);

		if (vhcAnoValCom != null && vhcAnoValCom.trim().length() != 0)
			vehicles.setVhcAnoValCom(new Long(vhcAnoValCom));
		vehicles.setVhcFechaProtocolo(vhcFechaProtocolo);

		if (vhcNumeroManifiesto != null)
			vehicles.setVhcNumeroManifiesto(vhcNumeroManifiesto.trim()
					.toUpperCase());

		vehicles.setVhcFechaManifiesto(vhcFechaManifiesto);

		if (vhcNumDeclImpor != null)
			vehicles.setVhcNumDeclImpor(vhcNumDeclImpor.trim().toUpperCase());

		if (vhcPlRemVehi != null)
			vehicles.setVhcPlRemVehi(vhcPlRemVehi.trim().toUpperCase());

		if (vhcNumeroLevante != null)
			vehicles.setVhcNumeroLevante(vhcNumeroLevante.trim().toUpperCase());

		vehicles.setVhcFechaLevante(vhcFechaLevante);
		if (vhcDocumentTrans != null)
			vehicles.setVhcDocumentTrans(vhcDocumentTrans.trim().toUpperCase());
		if (vhcNumeroFactura != null)
			vehicles.setVhcNumeroFactura(vhcNumeroFactura.trim().toUpperCase());

		if (vhcOrderCompra != null)
			vehicles.setVhcOrderCompra(vhcOrderCompra.trim().toUpperCase());

		if (vhcProveedor != null)
			vehicles.setVhcProveedor(vhcProveedor.trim().toUpperCase());

		if (vhcAtInicial != null)
			vehicles.setVhcAtInicial(vhcAtInicial.trim().toUpperCase());

		if (vhcCiuAduan != null)
			vehicles.setVhcCiuAduan(vhcCiuAduan.trim().toUpperCase());

		if (vhcVidaUtilNumber != null && vhcVidaUtilNumber.trim().length() == 0)
			vehicles.setVhcVidaUtil("");
		else
			vehicles.setVhcVidaUtil(vhcVidaUtilNumber);

		if (vhcValorCif != null)
			vehicles.setVhcValorCif(vhcValorCif.toString());

		if (vhcValorFob != null)
			vehicles.setVhcValorFob(vhcValorFob.toString());

		if (vhcCargosImport != null)
			vehicles.setVhcCargosImport(vhcCargosImport.toString());
		vehicles.setVhcObservaciones(vhcObservaciones);
		vehicles.setVhcCilindraje(vhcCilindraje);
		vehicles.setVhcModelo(vhcModelo);

		if (vhcRemplazaA != null)
			vehicles.setVhcRemplazaA(vhcRemplazaA.trim().toUpperCase());

		vehicles.setVhcUnidadTaquim(vhcOdometro);
		vehicles.setVhcModelo(vhcModelo);
		vehicles.setVhcAnofabricacion(vhcAnofabricacion);
		vehicles.setVhcMesfabricacion(vhcMesfabricacion);

		/** Se adiciona los objetos relacionadosal vehiculo */

		vehicles.setLines(lines);
		vehicles.setVehiclesTypes(vehiclesTypes);
		vehicles.setFuelsTypes(fuels);
		vehicles.setLocations(locations);
		vehicles.setTapestriesTypes(tapestries);
		vehicles.setTractionsTypes(tractions);
		vehicles.setTransmissionsTypes(transmissions);

		/** se realiza el commit */
		actualizarVehiculo(vehicles);
	}

	public Vehicles consultarVehiculoPorIds(String vhcPlacaDiplomatica,
			String vhcPlacaActivoFijo, String vhcNumeroTl)
			throws GWorkException {
		limpiarSession();
		IVehiclesDAO vehiclesDAO = new VehiclesDAO();
		List<Vehicles> listVehicles = null;
		int bandera = -2;
		if (vhcPlacaDiplomatica != null
				&& vhcPlacaDiplomatica.trim().length() != 0) {
			listVehicles = vehiclesDAO
					.findByVhcPlacaDiplomatica(vhcPlacaDiplomatica);
			if (listVehicles != null && listVehicles.size() == 0)
				bandera = 1;
		}

		if (vhcPlacaActivoFijo != null
				&& vhcPlacaActivoFijo.trim().length() != 0) {
			listVehicles = vehiclesDAO
					.findByVhcPlacaActivoFijo(vhcPlacaActivoFijo);
			if (listVehicles != null && listVehicles.size() == 0)
				bandera = -1;
		}

		if (vhcNumeroTl != null && vhcNumeroTl.trim().length() != 0) {
			listVehicles = vehiclesDAO.findByVhcNumeroTl(vhcNumeroTl);
			if (listVehicles != null && listVehicles.size() == 0)
				bandera = 0;
		}

		if (bandera == 1)
			throw new GWorkException(Util
					.loadErrorMessageValue("PLACA.NOEXISTE"));

		if (bandera == -1)
			throw new GWorkException(Util
					.loadErrorMessageValue("ACTIVO.FIJO.NOEXISTE"));

		if (bandera == 0)
			throw new GWorkException(Util.loadErrorMessageValue("TL.NOEXISTE"));

		if (listVehicles != null)
			return listVehicles.get(listVehicles.size()-1);
//MODIFICADO EL 16 MARZO DE 2012 CARLOS GUZMAN
		return null;
	}

	public Vehicles consultarVehiculo(String vhcPlacaDiplomatica,
			String vhcPlacaActivoFijo, String vhcNumeroTl)
			throws GWorkException {
		if (!IsNullOrEmpty.isNullOrEmpty(vhcPlacaDiplomatica)
				&& !IsNullOrEmpty.isNullOrEmpty(vhcPlacaActivoFijo)
				&& !IsNullOrEmpty.isNullOrEmpty(vhcNumeroTl))
			throw new GWorkException("Debe digitar llaves primarias");

		IVehiclesDAO vehiclesDAO = new VehiclesDAO();
		List<Vehicles> listVehicles = null;
		if (IsNullOrEmpty.isNullOrEmpty(vhcPlacaDiplomatica) == false) {
			listVehicles = vehiclesDAO
					.findByVhcPlacaDiplomatica(vhcPlacaDiplomatica);
			if (listVehicles == null || listVehicles.size() == 0)
				// TODO arreglar excepcion
				throw new GWorkException(
						"No existe un vehiculo con esa placa diplomatica"
								+ vhcPlacaDiplomatica);
		}

		if (IsNullOrEmpty.isNullOrEmpty(vhcPlacaActivoFijo) == false) {
			listVehicles = vehiclesDAO
					.findByVhcPlacaActivoFijo(vhcPlacaActivoFijo);
			if (listVehicles == null || listVehicles.size() == 0)
				// TODO arreglar excepcion
				throw new GWorkException(
						"No existe un vehiculo con esa placa de activo fijo"
								+ vhcPlacaActivoFijo);
		}

		if (IsNullOrEmpty.isNullOrEmpty(vhcNumeroTl) == false) {
			listVehicles = vehiclesDAO.findByVhcNumeroTl(vhcNumeroTl);
			if (listVehicles == null || listVehicles.size() == 0)
				// TODO arreglar excepcion
				throw new GWorkException(
						"No existe un vehiculo con ese numero de tl "
								+ vhcNumeroTl);

		}
		return listVehicles.get(0);
	}

	/**
	 * Consulta de Vehiculos Por Tipo
	 */
	public List<Vehicles> consultVehiclesByType(Long idType)
			throws GWorkException {
		List<Vehicles> listVehicles = SearchVehicles
				.consultarVehiculosPorTipoVehiculo(idType);
		if (listVehicles == null)
			throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));
		return listVehicles;
	}

	/**
	 * Consulta de Vehiculos Por Tipo
	 */
	public List<Vehicles> consultVehiclesByTypes(boolean pagina)
			throws GWorkException {
		List<Vehicles> listVehicles = SearchVehicles
				.consultarVehiculosPorEstadosTipoVehiculo(pagina);
		if (listVehicles == null)
			throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));
		return listVehicles;
	}

	/**
	 * Consulta de Vehiculos Por Tipo
	 */
	public Vehicles consultVehiclesByPlacaDiplomatica(String placa)
			throws GWorkException {
		Vehicles vehicles = SearchVehicles.consultarVehiculosPorPlaca(placa);
		if (vehicles == null)
			throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));
		return vehicles;
	}

	/**
	 * Consulta de Vehiculos Por Tipo
	 */
	public Vehicles consultVehiclesByPlacaDiplomaticaSinFiltros(String placa)
			throws GWorkException {
		Vehicles vehicles = SearchVehicles
				.consultarVehiculosPorPlacaSinFiltros(placa);
		if (vehicles == null)
			throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));
		return vehicles;
	}

	public VehiclesAssignation consultarAsignacionVehiculo(Long IdVehicle)
			throws GWorkException {

		return SearchVehicles
				.consultarAsignacionVehiculoPorIdVehiculo(IdVehicle);

	}

	public List<VehiclesAssignation> consultarHistorialVehiculos(
			Long idVehiculo, Long TipoVehiculo) throws GWorkException {
		return SearchVehicles.consultarHistorialVehiculos(idVehiculo,
				TipoVehiculo);
	}

	public List<VehiclesAssignation> listAsignationByUser(
			String carneAsignatario) throws GWorkException {
		List<VehiclesAssignation> listAsignationByUser = null;
		listAsignationByUser = SearchVehicles
				.listAsignationByUser(carneAsignatario);

		if (listAsignationByUser == null || listAsignationByUser.size() == 0)
			throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));

		return listAsignationByUser;
	}

}