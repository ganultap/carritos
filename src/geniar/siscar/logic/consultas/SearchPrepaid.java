package geniar.siscar.logic.consultas;

import geniar.siscar.model.CostsCenters;
import geniar.siscar.model.LegateesTypes;
import geniar.siscar.model.ServiceRegistry;
import geniar.siscar.persistence.EntityManagerHelper;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class SearchPrepaid {

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Consulta todos los registros de servicio de un vehiculo que se encuentren
	 * en el rango de fechas: 1 enero del año actual hasta la fecha actual.
	 * 
	 * @param placa
	 *            Placa del vehiculo del cual se requieren los registros de
	 *            sevicio.
	 * @return Listado de registros de servicio de combustible de un vehiculo.
	 * @throws GWorkException
	 *             Manejador de excepciones.
	 */
	@SuppressWarnings( { "unchecked", "deprecation" })
	public List<ServiceRegistry> registrosServicioX_Vehiculo(String placa)
			throws GWorkException {

		try {

			SimpleDateFormat dateFormat = new SimpleDateFormat();
			dateFormat.applyPattern("dd-MMM-yyyy");

			Date fecha = new Date();

			String currentDate = dateFormat.format(fecha);
			String initDate = dateFormat
					.format(new Date(fecha.getYear(), 0, 1));

			final String queryString = "SELECT model FROM ServiceRegistry model WHERE "
					+ " model.serFecha <='"
					+ currentDate
					+ "' AND model.serFecha >='"
					+ initDate
					+ "' "
					+ "AND model.vehicles.vhcPlacaDiplomatica='" + placa + "'";
			Query query = getEntityManager().createQuery(queryString);

			List<ServiceRegistry> list = query.getResultList();

			if (list != null && !list.isEmpty()) {
				return list;
			} else
				return null;

		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new GWorkException(
					Util.loadErrorMessageValue("search.not.found"
							+ re.getMessage()));
		}
	}

	@SuppressWarnings( { "unchecked" })
	public List<LegateesTypes> asignatariosPrepago() throws GWorkException {
		try {
			final String queryString = "SELECT model FROM LegateesTypes model WHERE model.lgtCodigo<8";

			Query query = getEntityManager().createQuery(queryString);

			List<LegateesTypes> list = query.getResultList();

			if (list != null && !list.isEmpty()) {
				return list;
			} else
				return null;

		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new GWorkException(
					Util.loadErrorMessageValue("search.not.found"
							+ re.getMessage()));
		}
	}

	/**
	 * 
	 * @param numero parametro de filtrado.
	 * @return Listado de centros de costo.
	 * @throws GWorkException Manejador de excepciones.
	 */
	@SuppressWarnings( { "unchecked" })
	public List<CostsCenters> centrosCostoFiltradosXnumero(String numero)
			throws GWorkException {
		try {
			final String queryString = "SELECT model FROM CostsCenters model WHERE model.cocNumero LIKE '%"
					+ numero.toUpperCase() + "%' ORDER BY model.cocNumero ASC";

			Query query = getEntityManager().createQuery(queryString);

			List<CostsCenters> list = query.getResultList();

			if (list != null && !list.isEmpty()) {
				return list;
			} else
				return null;

		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new GWorkException(
					Util.loadErrorMessageValue("search.not.found"
							+ re.getMessage()));
		}
	}
}
