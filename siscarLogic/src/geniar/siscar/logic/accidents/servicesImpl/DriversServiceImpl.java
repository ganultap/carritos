package geniar.siscar.logic.accidents.servicesImpl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import geniar.siscar.logic.accidents.services.DriversService;
import geniar.siscar.model.Driver;
import geniar.siscar.persistence.DriverDAO;
import geniar.siscar.persistence.EntityManagerHelper;
import geniar.siscar.persistence.IDriverDAO;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

public class DriversServiceImpl implements DriversService {

	private static final Log log = LogFactory.getLog(DriversServiceImpl.class);
	
	public Driver consultarConductor(String drvCedula) throws GWorkException {
		IDriverDAO driverDAO = new DriverDAO();

		if (driverDAO.findById(drvCedula) == null)
			throw new GWorkException(Util
					.loadErrorMessageValue("CONDUCTOREXISTEN"));
		return driverDAO.findById(drvCedula);
	}

	public void crearConductor(String drvCedula, String drvNombre,
			String drvNumeroCarne, String drvCargo, String drvDireccion,
			String drvTelefono) throws GWorkException {

		IDriverDAO driverDAO = new DriverDAO();
		Driver driver = new Driver();

		if (driverDAO.findById(drvCedula) != null)
			throw new GWorkException(Util
					.loadErrorMessageValue("CONDUCTOREXISTE"));

		if (driverDAO.findByDrvNumeroCarne(drvNumeroCarne).size() > 0
				|| !driverDAO.findByDrvNumeroCarne(drvNumeroCarne).isEmpty())
			throw new GWorkException(Util.loadErrorMessageValue("CARNEEXISTE"));

		driver.setDrvNumeroCarne(drvNumeroCarne);
		driver.setDrvCedula(drvCedula);
		driver.setDrvNombre(drvNombre);
		driver.setDrvCargo(drvCargo);
		driver.setDrvDireccion(drvDireccion);
		driver.setDrvTelefono(drvTelefono);

		try {
			EntityManagerHelper.getEntityManager().getTransaction().begin();
			driverDAO.save(driver);
			EntityManagerHelper.getEntityManager().getTransaction().commit();
		} catch (Exception e) {
			log.error("crearConductor", e);
			EntityManagerHelper.getEntityManager().getTransaction().rollback();
			throw new GWorkException(e.getMessage() + "\n"
					+ e.getLocalizedMessage(), e);
		}

	}

	public void eliminarConductor(String drvCedula) throws GWorkException {
		IDriverDAO driverDAO = new DriverDAO();
		Driver driver = new Driver();

		if (driverDAO.findById(drvCedula) == null)
			throw new GWorkException(Util
					.loadErrorMessageValue("CONDUCTOREXISTEN"));

		driver = driverDAO.findById(drvCedula);

		// Consultar si el conductor no se encuentra relacionado a un vehículo

		try {
			EntityManagerHelper.getEntityManager().getTransaction().begin();
			driverDAO.delete(driver);
			EntityManagerHelper.getEntityManager().getTransaction().commit();
		} catch (Exception e) {
			log.error("eliminarConductor", e);
			EntityManagerHelper.getEntityManager().getTransaction().rollback();
			throw new GWorkException(e.getMessage() + "\n"
					+ e.getLocalizedMessage(), e);
		}
	}

	public void modificarConductor(String drvCedula, String drvNombre,
			String drvNumeroCarne, String drvCargo, String drvDireccion,
			String drvTelefono) throws GWorkException {

		IDriverDAO driverDAO = new DriverDAO();
		Driver driver = new Driver();
		String carne = null;

		if (drvNumeroCarne.length() > 3)
			carne = drvNumeroCarne;

		if (driverDAO.findById(drvCedula) == null)
			throw new GWorkException(Util
					.loadErrorMessageValue("CONDUCTOREXISTEN"));

		driver = driverDAO.findById(drvCedula);

		if (driverDAO.findByDrvNumeroCarne(carne).size() > 0
				|| !driverDAO.findByDrvNumeroCarne(carne).isEmpty())
			throw new GWorkException(Util.loadErrorMessageValue("CARNEEXISTE"));

		driver.setDrvCedula(drvCedula);
		driver.setDrvNombre(drvNombre);
		driver.setDrvCargo(drvCargo);
		driver.setDrvDireccion(drvDireccion);
		driver.setDrvNumeroCarne(carne);
		driver.setDrvTelefono(drvTelefono);

		try {
			EntityManagerHelper.getEntityManager().getTransaction().begin();
			driverDAO.update(driver);
			EntityManagerHelper.getEntityManager().getTransaction().commit();
		} catch (Exception e) {
			log.error("modificarConductor", e);
			EntityManagerHelper.getEntityManager().getTransaction().rollback();
			throw new GWorkException(e.getMessage() + "\n"
					+ e.getLocalizedMessage(), e);
		}
	}

}
