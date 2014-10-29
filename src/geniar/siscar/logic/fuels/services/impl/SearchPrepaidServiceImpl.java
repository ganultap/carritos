package geniar.siscar.logic.fuels.services.impl;

import geniar.siscar.logic.fuels.services.SearchPrepaidService;
import geniar.siscar.model.CostCentersFuel;
import geniar.siscar.model.CostsCenters;
import geniar.siscar.model.Prepaid;
import geniar.siscar.model.PrepaidConsumption;
import geniar.siscar.model.ServiceRegistry;
import geniar.siscar.model.VOPrepagoInicial;
import geniar.siscar.persistence.EntityManagerHelper;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

import java.util.Date;
import java.util.List;

import javax.persistence.Query;

public class SearchPrepaidServiceImpl implements SearchPrepaidService {

	public List<Prepaid> consultarDisponibilidaPrepago(Date fechaIni,
			Date fechaFin, String placa) throws GWorkException {

		List<Prepaid> listaConsumoPrepago = new SearchParametersFuelServiceImp()
				.consultarDisponibilidaPrepago(fechaIni, fechaFin, placa);

		if (listaConsumoPrepago == null || listaConsumoPrepago.size() == 0
				|| listaConsumoPrepago.isEmpty())
			throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));

		return listaConsumoPrepago;
	}

	public List<Object[]> valorDisponiblePrepago(Date fechaIni, Date fechaFin,
			Long idPrepago, String centroCosto) throws GWorkException {

		List<Object[]> listaDisponiblePrepago = new SearchParametersFuelServiceImp()
				.valorDisponiblePrepago(fechaIni, fechaFin, idPrepago,
						centroCosto);

		if (listaDisponiblePrepago == null
				|| listaDisponiblePrepago.size() == 0)
			throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));

		return listaDisponiblePrepago;
	}

	public List<Prepaid> DisponibilidadPrepagoCCAsignatario(Date fechaIni,
			Date fechaFin, String centroCosto, String asignatario)
			throws GWorkException {
		List<Prepaid> listaPrepagoCCAsig = new SearchParametersFuelServiceImp()
				.DisponibilidadPrepagoCCAsignatario(fechaIni, fechaFin,
						centroCosto, asignatario);

		if (listaPrepagoCCAsig == null || listaPrepagoCCAsig.size() == 0
				|| listaPrepagoCCAsig.isEmpty())
			throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));

		return listaPrepagoCCAsig;
	}

	public List<PrepaidConsumption> detalleConsumo(Long idCentroCosto,
			Date fechaIni, Date fechaFin) throws GWorkException {
		List<PrepaidConsumption> listaConsumo = new SearchParametersFuelServiceImp()
				.detalleConsumo(idCentroCosto, fechaIni, fechaFin);

		if (listaConsumo == null || listaConsumo.size() == 0
				|| listaConsumo.isEmpty())
			throw new GWorkException(Util.loadErrorMessageValue("CONSUMO.NULO"));

		return listaConsumo;
	}

	public String disponibleCC(String idCentroCosto) {

		return new SearchParametersFuelServiceImp().disponibleCC(idCentroCosto);
	}

	@SuppressWarnings("unchecked")
	public static List<Object[]> listaCombustiblePremdio(Date fechaIni,
			Date fechaFin) throws GWorkException {

		final String queryString = "select model.serviceRegistry.vehicles.vhcPlacaDiplomatica, AVG(model.serviceRegistry.serNumeroGalones), "
				+ "model.costCentersFuel.costsCenters.cocNumero, AVG(model.serviceRegistry.serKilometrajeActual), SUM(model.serviceRegistry.serNumeroGalones), "
				+ "model.serviceRegistry.vehicles.fuelsTypes.futNombre "
				+ "from PrepaidConsumption model "
				+ "WHERE model.costCentersFuel.ccfEstado = :estado "
				+ "AND model.serviceRegistry.serFecha BETWEEN :fechaIni AND :fechaFin "
				+ "GROUP BY model.serviceRegistry.vehicles.vhcPlacaDiplomatica, "
				+ "model.costCentersFuel.costsCenters.cocNumero,model.serviceRegistry.vehicles.fuelsTypes.futNombre";

		Query query = EntityManagerHelper.getEntityManager().createQuery(
				queryString);
		query.setParameter("fechaIni", fechaIni);
		query.setParameter("fechaFin", fechaFin);
		query.setParameter("estado", Util.loadMessageValue("ESTADO_ACTIVO"));

		if (query.getResultList() != null && query.getResultList().size() > 0)
			return query.getResultList();
		else
			throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));

	}

	@SuppressWarnings("unchecked")
	public static List<ServiceRegistry> listaConsumoByVehiculo(String placa,
			Date fechaIni, Date fechaFin) throws GWorkException {

		final String queryString = "select model ServiceRegistry model "
				+ "WHERE model.vehicles.vhcPlacaDiplomatica= :placa "
				+ "AND model.serFecha BETWEEN :fechaIni AND :fechaFin"
				+ "ORDER BY model.serFecha ASC";

		Query query = EntityManagerHelper.getEntityManager().createQuery(
				queryString);
		query.setParameter("placa", placa);
		query.setParameter("fechaFin", fechaIni);
		query.setParameter("fechaFin", fechaFin);
		query.setParameter("estado", Util.loadMessageValue("ESTADO_ACTIVO"));

		if (query.getResultList() != null && query.getResultList().size() > 0)
			return query.getResultList();

		return null;

	}

	@SuppressWarnings("unchecked")
	public static VOPrepagoInicial consumoByVehicle(Date fechaIni,
			Date fechaFin, String placa) throws GWorkException {

		VOPrepagoInicial prepagoInicial = null;
		final String queryString = "select  AVG(model.serviceRegistry.serNumeroGalones), "
				+ "AVG(model.serviceRegistry.serKilometrajeActual), SUM(model.serviceRegistry.serNumeroGalones),SUM(model.serviceRegistry.serKilometrajeActual), "
				+ "MIN(model.serviceRegistry.serFecha), MAX(model.serviceRegistry.serFecha) "
				+ "from PrepaidConsumption model "
				+ "WHERE model.serviceRegistry.serFecha BETWEEN :fechaIni AND :fechaFin "
				+ "AND model.serviceRegistry.vehicles.vhcPlacaDiplomatica = :placa";

		Query query = EntityManagerHelper.getEntityManager().createQuery(
				queryString);
		query.setParameter("fechaIni", fechaIni);
		query.setParameter("fechaFin", fechaFin);
		query.setParameter("placa", placa);
		List<Object[]> listaVOPrepago = query.getResultList();

		if (listaVOPrepago.get(0)[0] != null) {
			prepagoInicial = new VOPrepagoInicial();

			for (Object[] prepagoObj : listaVOPrepago) {

				if (prepagoObj[0] != null)
					prepagoInicial.setConsumoPromedio(new Double(prepagoObj[0]
							.toString()));

				if (prepagoObj[1] != null)
					prepagoInicial.setKmPromedio(new Float(prepagoObj[1]
							.toString()).longValue());
				if (prepagoObj[2] != null)
					prepagoInicial.setGalonesAno(new Double(prepagoObj[2]
							.toString()));
				if (prepagoObj[3] != null)
					prepagoInicial.setKmAnual(new Double(prepagoObj[3]
							.toString()).longValue());

				if (prepagoObj[4] != null)
					prepagoInicial.setMinFechaServicio((Date) prepagoObj[4]);

				if (prepagoObj[5] != null)
					prepagoInicial.setMaxFechaServicio((Date) prepagoObj[5]);
			}

			return prepagoInicial;
		} else
			return null;

	}

	@SuppressWarnings("unchecked")
	public static List<CostCentersFuel> listaCostCenterFuel()
			throws GWorkException {

		final String queryString = "select model from CostCentersFuel model "
				+ "WHERE model.ccfEstado = :estado "
				+ "AND model.vehiclesAssignation.vhaFechaEntrega is not null "
				+ "AND model.vehiclesAssignation.vhaFechaDev is null ";

		Query query = EntityManagerHelper.getEntityManager().createQuery(
				queryString);
		query.setParameter("estado", Util.loadMessageValue("ESTADO_ACTIVO"));

		if (query.getResultList() != null && query.getResultList().size() > 0)
			return query.getResultList();
		else
			throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));

	}

	@SuppressWarnings("unchecked")
	public static List<CostCentersFuel> listaCostCenterFuelByPlaca(String placa)
			throws GWorkException {

		final String queryString = "select model from CostCentersFuel model "
				+ "WHERE model.ccfEstado = :estado "
				+ "AND model.vehiclesAssignation is not null "
				+ "AND model.vehiclesAssignation.vehicles.vhcPlacaDiplomatica = :placa " 
				+ "AND model.vehiclesAssignation.vhaFechaEntrega is not null "
				+ "AND model.vehiclesAssignation.vhaFechaDev is null ";

		Query query = EntityManagerHelper.getEntityManager().createQuery(
				queryString);
		query.setParameter("estado", Util.loadMessageValue("ESTADO_ACTIVO"));
		query.setParameter("placa", placa);

		if (query.getResultList() != null && query.getResultList().size() > 0)
			return query.getResultList();
		else
			throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));

	}

	@SuppressWarnings("unchecked")
	public static List<CostCentersFuel> listaCCFByPlaca(String placa)
			throws GWorkException {

		final String queryString = "select model from CostCentersFuel model "
				+ "WHERE model.ccfEstado = :estado "
				+ "AND model.vehiclesAssignation is not null "
				+ "AND model.vehiclesAssignation.vehicles.vhcPlacaDiplomatica = :placa";

		Query query = EntityManagerHelper.getEntityManager().createQuery(
				queryString);
		query.setParameter("estado", Util.loadMessageValue("ESTADO_ACTIVO"));
		query.setParameter("placa", placa);

		if (query.getResultList() != null && query.getResultList().size() > 0)
			return query.getResultList();
		else
			return null;

	}

	public List<PrepaidConsumption> consumoPrepagoByVehiculo(
			String idCentroCosto, String placa, Date fechaIni, Date fechaFin)
			throws GWorkException {
		List<PrepaidConsumption> listaConsumoByVehiculo = SearchParametersFuelServiceImp
				.consumoPrepagoByVehiculo(idCentroCosto, placa, fechaIni,
						fechaFin);

		if (listaConsumoByVehiculo == null
				|| listaConsumoByVehiculo.size() == 0
				|| listaConsumoByVehiculo.isEmpty())
			throw new GWorkException(Util.loadErrorMessageValue("CONSUMO.NULO")
					+ ": " + placa);

		return listaConsumoByVehiculo;
	}

	@SuppressWarnings("unchecked")
	public static List<CostsCenters> listDevolutionPrepaid()
			throws GWorkException {

		final String queryString = "select model from CostsCenters model "
				+ "WHERE model.valorPrepago > :valorMinimo "
				+ "ORDER BY model.cocNumero ASC";
		Query query = EntityManagerHelper.getEntityManager().createQuery(
				queryString);
		query.setParameter("valorMinimo", new Float(0));

		return query.getResultList();

	}

}
