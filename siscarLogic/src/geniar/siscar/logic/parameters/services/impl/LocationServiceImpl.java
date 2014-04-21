/**
 * 
 */
package geniar.siscar.logic.parameters.services.impl;

import geniar.siscar.logic.consultas.SearchVehicles;
import geniar.siscar.logic.parameters.services.LocationService;
import geniar.siscar.model.Cities;
import geniar.siscar.model.Locations;
import geniar.siscar.model.LocationsTypes;
import geniar.siscar.model.Vehicles;
import geniar.siscar.persistence.CitiesDAO;
import geniar.siscar.persistence.EntityManagerHelper;
import geniar.siscar.persistence.ILocationsDAO;
import geniar.siscar.persistence.ILocationsTypesDAO;
import geniar.siscar.persistence.LocationsDAO;
import geniar.siscar.persistence.LocationsTypesDAO;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

import java.util.List;

public class LocationServiceImpl implements LocationService {

	public void crearLugar(String descripcion, Long idCiudad,
			Long idTypeLocation) throws GWorkException {

		try {
			// validarSession();

			if (SearchVehicles.consultarDatosUbicacionPorIdCiudad(idCiudad,
					idTypeLocation) != null)
				throw new GWorkException(Util
						.loadErrorMessageValue("UBICACIONEXISTE"));

			Cities cities = null;
			Locations locations = new Locations();
			ILocationsDAO locationsDAO = new LocationsDAO();

			if (idCiudad == null)
				throw new GWorkException(Util
						.loadErrorMessageValue("IDCIUDAD.NULO"));

			cities = new CitiesDAO().findById(idCiudad);

			if (cities == null)
				throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));

			LocationsTypes locationsTypes = new LocationsTypes();
			ILocationsTypesDAO locationsTypesDAO = new LocationsTypesDAO();
			locationsTypes = locationsTypesDAO.findById(idTypeLocation);

			if (locationsTypes == null)
				throw new GWorkException(Util
						.loadErrorMessageValue("TIPOUBICACIONEXISTEN"));

			EntityManagerHelper.beginTransaction();
			if (descripcion != null)
				locations.setLcnDescripcion(descripcion);
			locations.setCities(cities);
			locations.setLocationsTypes(locationsTypes);
			locationsDAO.save(locations);
			EntityManagerHelper.commit();
			limpiarSession();
		} catch (Exception e) {
			throw new GWorkException(e.getMessage());
		}
	}

	public void eliminarLugar(Long idCiudad, Long idTipoUbicacion)
			throws GWorkException {
		try {
			Locations locations = null;

			locations = SearchVehicles.consultarDatosUbicacionPorIdCiudad(
					idCiudad, idTipoUbicacion);

			/** Verifico que la ubicacion exista */
			if (locations == null)
				throw new GWorkException(Util
						.loadErrorMessageValue("UBICACIONEXISTEN"));

			/** Verifico que la ubicaion no tenga elementos asociados */
			for (Vehicles vehicles : locations.getVehicleses()) {

				System.out.println(vehicles.getVhcPlacaDiplomatica());
			}
			if (!locations.getVehicleses().isEmpty())
				throw new GWorkException(Util
						.loadErrorMessageValue("UBICACIONELIMINARN"));
			// validarSession();
			EntityManagerHelper.beginTransaction();
			new LocationsDAO().delete(locations);
			EntityManagerHelper.commit();
			limpiarSession();
		} catch (Exception e) {
			throw new GWorkException(e.getMessage());
		}
	}

	public List<Locations> listaLugar() throws GWorkException {
		ILocationsDAO LocationsDAO = new LocationsDAO();
		List<Locations> listLugares = LocationsDAO.findAll();
		if (listLugares.isEmpty())
			throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));

		return listLugares;
	}

	public Locations consultarLugar(Long codigo, Long idTipoUbicacion)
			throws GWorkException {

		Locations locations = SearchVehicles
				.consultarDatosUbicacionPorIdCiudad(codigo, idTipoUbicacion);
		if (locations == null)
			throw new GWorkException(Util
					.loadErrorMessageValue("UBICACIONEXISTEN"));
		else
			return locations;
	}

	public void modificarLugar(Long idUbicacion, Long idCiudad,
			String descripcion) throws GWorkException {

		try {
			Cities cities = null;

			if (idCiudad == null)
				throw new GWorkException(Util
						.loadErrorMessageValue("IDCIUDAD.NULO"));

			cities = new CitiesDAO().findById(idCiudad);

			if (cities == null)
				throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));

			if (descripcion != null && descripcion.trim().length() == 0)
				throw new GWorkException(Util
						.loadErrorMessageValue("NOMBREUBICACION"));

			Locations locations = SearchVehicles
					.consultarDatosUbicacionPorIdCiudad(idCiudad, idUbicacion);

			/** Validar que exista la ubicacion */
			if (locations == null)
				throw new GWorkException(Util.loadErrorMessageValue(""));

			LocationsTypes locationsTypes = new LocationsTypes();
			ILocationsTypesDAO locationsTypesDAO = new LocationsTypesDAO();
			locationsTypes = locationsTypesDAO.findById(idUbicacion);

			if (locationsTypes == null)
				throw new GWorkException(Util
						.loadErrorMessageValue("TIPOUBICACIONEXISTEN"));

			// validarSession();
			EntityManagerHelper.beginTransaction();
			locations.setLcnDescripcion(descripcion);
			locations.setLocationsTypes(locationsTypes);
			locations.setCities(cities);
			new LocationsDAO().update(locations);
			EntityManagerHelper.commit();
			limpiarSession();
		} catch (Exception e) {
			throw new GWorkException(e.getMessage());
		}
	}

	// public void validarSession() {
	// if (EntityManagerHelper.getEntityManager().isOpen()) {
	// EntityManagerHelper.getEntityManager().close();
	// EntityManagerHelper.getEntityManager().clear();
	// }
	//
	// }

	public void limpiarSession() {
		EntityManagerHelper.getEntityManager().clear();
		EntityManagerHelper.closeEntityManager();
	}
}
