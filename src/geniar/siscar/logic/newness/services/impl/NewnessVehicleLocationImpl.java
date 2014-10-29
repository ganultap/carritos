package geniar.siscar.logic.newness.services.impl;

import geniar.siscar.logic.consultas.SearchNewness;
import geniar.siscar.logic.consultas.SearchVehicles;
import geniar.siscar.logic.newness.services.NewnessVehicleLocationService;
import geniar.siscar.mail.util.SendEmail;
import geniar.siscar.model.CostsCentersNewness;
import geniar.siscar.model.Locations;
import geniar.siscar.model.LocationsNewness;
import geniar.siscar.model.Rolls;
import geniar.siscar.model.Vehicles;
import geniar.siscar.model.VehiclesAssignation;
import geniar.siscar.persistence.EntityManagerHelper;
import geniar.siscar.persistence.LocationsNewnessDAO;
import geniar.siscar.persistence.RollsDAO;
import geniar.siscar.persistence.VehiclesDAO;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class NewnessVehicleLocationImpl implements
		NewnessVehicleLocationService {

	public static final String estNombre = "estNombre";
	private static final Log log = LogFactory.getLog(SendEmail.class);

	public void RegistroNovedadesVehiculoCambioUbicacion(String placa,
			Date fechaFinalAsignacion, Long idCentroCostos,
			List<List<String>> valoresCentroCostos, String usrLogin,
			String descripcionNovedad) throws GWorkException, Exception {

		List<Vehicles> listVehicles = new VehiclesDAO()
				.findByVhcPlacaDiplomatica(placa);

		if (listVehicles != null && listVehicles.size() > 0) {

			int i = 0;
			CostsCentersNewness costsCentersNewness = null;
			for (List<String> listValores : valoresCentroCostos) {
				String porcentaje = listValores.get(i + 1);
				Long valor = new Long(listValores.get(i + 2));
				i++;
				costsCentersNewness = new CostsCentersNewness();
				costsCentersNewness.setCcnDescripcion(descripcionNovedad);
				costsCentersNewness.setCcnFechaActual(new Date());
				costsCentersNewness.setCcrPorcentaje(porcentaje);
				costsCentersNewness.setCcrValor(valor);
				costsCentersNewness.setUsrLogin(usrLogin);
				costsCentersNewness.setCcrFechaInicio(fechaFinalAsignacion);
			}
		}
	}

	public VehiclesAssignation consultarDatosAsignacion(String placa)
			throws GWorkException {
		return SearchNewness.consultarDatosAsignacion(placa);
	}

	public void actualizarTipoUbicacionVehiculo(String login,
			String descripcion, Long idCodigoVehiculo, Long idUbicacion,
			Long idTipoUbicacion, Long idAsignacion) throws GWorkException {

		try {
			Rolls rolls = new RollsDAO().findById(new Long(Util
					.loadParametersValue("MAIL.ADMINISTRADOR")));
			Locations locations = null;
			String ubicacion = null;
			String server = Util.loadParametersValue("MAIL.HOST");
			String fromUser = rolls.getRlsMail();
			Vehicles vehicles = new VehiclesDAO().findById(idCodigoVehiculo);
//			String emailAsistente = Util.loadParametersValue("MAIL.ASISTENTE.ADMIN");
			String emailAsistente = rolls.getRlsMail();
			
			String textmessageAsistente = Util
					.loadParametersValue("CUERPO.MSJ.NOV.VEH.ASIS");
			String subjectAsistente = Util
					.loadParametersValue("MSJ.NOV.VEH.ASIS");

			if (vehicles != null) {
				locations = SearchVehicles.consultarDatosUbicacionPorIdCiudad(
						idUbicacion, idTipoUbicacion);
				if (locations != null) {
					validarSession();
					EntityManagerHelper.beginTransaction();
					vehicles.setLocations(locations);
					new VehiclesDAO().update(vehicles);
					EntityManagerHelper.commit();
					// limpiarSession();
				}

				locations = SearchVehicles.consultarDatosUbicacionPorIdCiudad(
						idUbicacion, idTipoUbicacion);

				if (locations.getCities() != null
						&& locations.getCities().getCtsNombre() != null)
					ubicacion = locations.getCities().getCtsNombre();

				guardarNovedadCambioUbicacionVehiculo(login, ubicacion,
						descripcion, idCodigoVehiculo);

				try {

					SendEmail mail = new SendEmail(emailAsistente, fromUser,
							server, "false", subjectAsistente + " Fecha: "
									+ new Date(), textmessageAsistente
									+ "<br />Placa Vehiculo:"
									+ vehicles.getVhcPlacaDiplomatica());

					if (mail.SendMessage().equals("SUCCESS"))
						log.info("Mensaje enviado exitosamente");

				} catch (RuntimeException e) {
					log.info("Eror Enviando el mensaje");
				}
			}
		} catch (Exception e) {
			// limpiarSession();
			EntityManagerHelper.rollback();
			throw new GWorkException(Util
					.loadErrorMessageValue("REGISTRO.NOEXITOSO")
					+ " " + e.getMessage());
		}
	}

	public void guardarNovedadCambioUbicacionVehiculo(String login,
			String ubicacion, String descripcion, Long idVehiculo)
			throws GWorkException {
		try {
			Vehicles vehicles = new VehiclesDAO().findById(idVehiculo);
			if (vehicles != null) {
				validarSession();
				EntityManagerHelper.beginTransaction();
				LocationsNewness locationsNewness = new LocationsNewness();
				locationsNewness.setLcnDescripcion(descripcion);
				locationsNewness.setLcnFechaActual(new Date());
				locationsNewness.setLcnUbicacion(ubicacion);
				locationsNewness.setUsrLogin(login);
				locationsNewness.setVehicles(vehicles);
				new LocationsNewnessDAO().save(locationsNewness);
				EntityManagerHelper.commit();
				// limpiarSession();
			}
		} catch (Exception e) {
			EntityManagerHelper.rollback();
			throw new GWorkException(Util
					.loadErrorMessageValue("GUARDADO.NOEXITOSO")
					+ " " + e.getMessage());
		}
	}

	public void validarSession() {
		if (EntityManagerHelper.getEntityManager().isOpen())
			EntityManagerHelper.getEntityManager().close();
	}

	// public void limpiarSession() {
	// EntityManagerHelper.getEntityManager().clear();
	// EntityManagerHelper.closeEntityManager();
	// }
}
