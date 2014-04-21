package geniar.siscar.logic.parameters.services.impl;

import java.util.List;

import geniar.siscar.logic.consultas.SearchParameters;
import geniar.siscar.logic.parameters.services.ZoneService;
import geniar.siscar.model.Zones;
import geniar.siscar.persistence.EntityManagerHelper;
import geniar.siscar.persistence.IZonesDAO;
import geniar.siscar.persistence.ZonesDAO;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

public class ZoneServiceImpl implements ZoneService {

	public Zones consultarZone(Long codeZone) throws GWorkException {
		IZonesDAO zonesDAO = new ZonesDAO();
		Zones zones = zonesDAO.findById(codeZone);
		if (zones == null) {
			// TODO hacer exception
			throw new GWorkException("");
		}
		return zones;
	}

	public void crearZona(String znsNombre, String znsDescripcion, String znsKilometraje) throws GWorkException {

		if (EntityManagerHelper.getEntityManager().isOpen())
			EntityManagerHelper.getEntityManager().close();

		Zones zones = new Zones();
		IZonesDAO zonesDAO = new ZonesDAO();
		List<Zones> listZonas = zonesDAO.findByZnsNombre(znsNombre.trim());

		/** Verificar que no exista una zona con el mismo nombre */
		if (!listZonas.isEmpty())
			throw new GWorkException(Util.loadErrorMessageValue("ZONAEXISTE"));

		EntityManagerHelper.getEntityManager().getTransaction().begin();
		zones.setZnsNombre(znsNombre.toUpperCase().trim());
		zones.setZnsDescripcion(znsDescripcion.toUpperCase());
		zones.setZnsKilometraje(znsKilometraje);
		zonesDAO.save(zones);
		EntityManagerHelper.commit();

	}

	public void eliminarZona(String znsNombre) throws GWorkException {

		if (EntityManagerHelper.getEntityManager().isOpen())
			EntityManagerHelper.getEntityManager().close();

		if (znsNombre.trim().length() == 0)
			throw new GWorkException(Util.loadErrorMessageValue("NOMBREZONA"));

		Zones zones = new Zones();
		IZonesDAO zonesDAO = new ZonesDAO();
		List<Zones> listZonas = zonesDAO.findByZnsNombre(znsNombre);

		/** Verificar si existe la zona */
		if (listZonas.isEmpty())
			throw new GWorkException(Util.loadErrorMessageValue("ZONAEXISTEN"));
		zones = listZonas.get(0);

		/** Verificar que no tenga tarifas asociadas */
		if (!zones.getTariffses().isEmpty())
			throw new GWorkException(Util.loadErrorMessageValue("ZONAELIMINAR"));

		EntityManagerHelper.getEntityManager().getTransaction().begin();
		zonesDAO.delete(zones);
		EntityManagerHelper.commit();

	}

	public List<Zones> listaZona() throws GWorkException {
		return new SearchParameters().getZonesAllOrder();
	}

	public void modificarZona(Long znsId, String znsNombre, String znsDescripcion, String znsKilometraje)
			throws GWorkException {

		if (EntityManagerHelper.getEntityManager().isOpen())
			EntityManagerHelper.getEntityManager().close();

		Zones zones = new Zones();
		IZonesDAO zonesDAO = new ZonesDAO();
		zones = zonesDAO.findById(znsId);

		/** Verificar si existe la zona */
		if (zones == null)
			throw new GWorkException(Util.loadErrorMessageValue("ZONAEXISTEN"));

		if (zones.getZnsNombre().equals(znsNombre)) {

			zones.setZnsDescripcion(znsDescripcion);
			zones.setZnsKilometraje(znsKilometraje);
		} else {
			if (!zonesDAO.findByZnsNombre(znsNombre.trim()).isEmpty()) {
				throw new GWorkException(Util.loadErrorMessageValue("ZONAEXISTE"));
			}

			zones.setZnsNombre(znsNombre.toUpperCase().trim());
			zones.setZnsDescripcion(znsDescripcion);
			zones.setZnsKilometraje(znsKilometraje);
		}

		EntityManagerHelper.getEntityManager().getTransaction().begin();
		zonesDAO.update(zones);
		EntityManagerHelper.commit();

	}

	public Zones consultarZoneByName(String znsNombre) throws GWorkException {

		IZonesDAO zonesDAO = new ZonesDAO();
		List<Zones> listZones = zonesDAO.findByZnsNombre(znsNombre);

		if (listZones.isEmpty())
			throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));

		return listZones.get(0);
	}

}
