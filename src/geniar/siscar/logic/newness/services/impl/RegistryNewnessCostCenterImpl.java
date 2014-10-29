package geniar.siscar.logic.newness.services.impl;

import geniar.siscar.logic.consultas.SearchNewness;
import geniar.siscar.logic.newness.services.CostCentersService;
import geniar.siscar.logic.newness.services.RegistryNewnessCostCenterService;
import geniar.siscar.consults.ConsultsService;
import geniar.siscar.consults.impl.ConsultsServiceImpl;
import geniar.siscar.model.CostCentersFuel;
import geniar.siscar.model.CostsCentersNewness;
import geniar.siscar.model.CostsCentersVehicles;
import geniar.siscar.model.Vehicles;
import geniar.siscar.model.VehiclesAssignation;
import geniar.siscar.model.CostsCenters;
import geniar.siscar.persistence.CostsCentersDAO;
import geniar.siscar.persistence.EntityManagerHelper;
import geniar.siscar.persistence.VehiclesDAO;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RegistryNewnessCostCenterImpl implements
		RegistryNewnessCostCenterService {

	public static final String estNombre = "estNombre";
	private CostCentersService costCentersService = new CostCentersImpl();
	private ConsultsService consultsService = new ConsultsServiceImpl();

	public void RegistroNovedadesVehiculoCambioCentroCosto(String placa,
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

	public void actualizarCentrosDeCostos(List<CostsCentersVehicles> list,
			String login, Long idCodigoVehiculo, Date fechaIni,
			Date fechaAsignacion) throws GWorkException {
		costCentersService.actualizarCentrosDeCostos(list, login,
				idCodigoVehiculo, fechaIni, fechaAsignacion);
	}

	public void actualizarCentrosDeCostosCombustible(
			List<CostCentersFuel> list, String login, Long idCodigoVehiculo,
			Date fechaIni, Date fechaAsignacion, Integer estado_ccv)
			throws GWorkException {
		costCentersService.actualizarCentrosDeCostosCombustible(list, login,
				idCodigoVehiculo, fechaIni, fechaAsignacion);
	}

	public void crearNovedadCambioCentroCostos(String login, String porcentaje,
			Date fechaInicio, Date fechaFin, Long idCodigoVehiculo)
			throws GWorkException {
		costCentersService.crearNovedadCambioCentroCostos(login, porcentaje,
				fechaInicio, fechaFin, idCodigoVehiculo, null);
	}

	public void eliminarCentroCostosVehiculo(
			CostsCentersVehicles costsCentersVehicles) throws GWorkException {
		costCentersService.eliminarCentroCostosVehiculo(costsCentersVehicles);
	}

	public void guardarCentroCostos(String nombre, String login,
			String porcentaje, String idVehiculo, String idAsginacion,
			Date fechaAsignacion, Date fechaTermina) throws GWorkException {
		costCentersService.guardarCentroCostos(nombre, login, porcentaje,
				idVehiculo, idAsginacion, fechaAsignacion, fechaTermina);
	}

	public void guardarCentroCostosCombustible(CostsCenters costsCenters,
			String porcentaje, String idVehiculo, String idAsginacion,
			Date fechaAsignacion, Date fechaTermina) throws GWorkException {
		costCentersService.guardarCentroCostosCombustible(costsCenters,
				porcentaje, idVehiculo, idAsginacion, fechaAsignacion,
				fechaTermina);
	}

	/* Nuevos */
	public Integer guardarCentroCostos(CostsCenters costCenter, String login,
			String porcentaje, String idVehiculo, String idAsginacion,
			Date fechaAsignacion, Date fechaTermina) throws GWorkException {
		return costCentersService.guardarCentroCostos(costCenter, login,
				porcentaje, idVehiculo, idAsginacion, fechaAsignacion,
				fechaTermina);
	}

	public void validarFechaFinalAsignacion(Date fechaFinal, Date fechaInicial,
			Date fechaTermina) throws GWorkException {
		if (fechaFinal.compareTo(fechaInicial) < 0
				|| fechaFinal.compareTo(fechaTermina) > 0)
			throw new GWorkException(Util
					.loadErrorMessageValue("FECHA.ASIG.INVALIDA"));
	}

	public void ValidarCentrosDeCostosActuales(Long idVehiculo,
			Long idAsignacion, Date fechaAsignacion) throws GWorkException {
		try {
			if (SearchNewness.ValidarCentrosDeCostosActuales(idVehiculo,
					idAsignacion, fechaAsignacion) != null)
				throw new GWorkException(Util
						.loadErrorMessageValue("FECHA.FINASIG.INVALIDA"));

		} catch (Exception e) {
			throw new GWorkException(e.getMessage());
		}
	}

	public void validarSession() {
		if (EntityManagerHelper.getEntityManager().isOpen())
			EntityManagerHelper.getEntityManager().close();
	}

//	public void limpiarSession() {
//		EntityManagerHelper.getEntityManager().clear();
//		EntityManagerHelper.closeEntityManager();
//	}

	public List<CostsCenters> consultarCentroCostos(String centroCostos)
			throws GWorkException {
		try {
			CostsCentersDAO ccDAO = new CostsCentersDAO();

			/*
			 * es posible que el CC consultado no exista en la BD local por lo
			 * que se creará localmente, verificando que realmente existe en la
			 * bd del CIAT
			 */
			String msg_existe = getConsultsService().consultCostCenter(
					centroCostos.toUpperCase());

			if (msg_existe == null) {

				/* luego verifico que exista en la BD de siscar */
				List<CostsCenters> listCC_SISCAR = ccDAO
						.findByCocNumero(centroCostos.toUpperCase());
				if (listCC_SISCAR != null && listCC_SISCAR.size() > 0)
					return listCC_SISCAR;
				else {
					CostsCenters entity = new CostsCenters();
					entity.setCocNumero(centroCostos.toUpperCase());

					List<CostsCenters> list = new ArrayList<CostsCenters>();
					try {
						EntityManagerHelper.getEntityManager().getTransaction()
								.begin();
						ccDAO.save(entity);
						EntityManagerHelper.getEntityManager().getTransaction()
								.commit();
						EntityManagerHelper.getEntityManager().clear();
						list.add(entity);
					} catch (Exception internal) {
						EntityManagerHelper.getEntityManager().getTransaction()
								.rollback();
						EntityManagerHelper.getEntityManager().clear();
						throw new GWorkException(Util
								.loadErrorMessageValue("ERROR.ADDCOSTCENTER"));
					}
					return list;
				}
			} else {
				throw new GWorkException(msg_existe);
			}

		} catch (Exception e) {
			// + " / " +
			// Util.loadErrorMessageValue("ERROR.NOEXISTE.CENTROCOSTO")
			throw new GWorkException(e.getMessage());
		}
	}

	public ConsultsService getConsultsService() {
		return consultsService;
	}

	public void setConsultsService(ConsultsService consultsService) {
		this.consultsService = consultsService;
	}

	/* Metodo nuevo */
	public void actualizarCentrosDeCostos(List<CostsCentersVehicles> list,
			String login, Long idCodigoVehiculo, Date fechaIni,
			Date fechaAsignacion, Integer estado_ccv) throws GWorkException {
		costCentersService.actualizarCentrosDeCostos(list, login,
				idCodigoVehiculo, fechaIni, fechaAsignacion, estado_ccv);
	}
}
