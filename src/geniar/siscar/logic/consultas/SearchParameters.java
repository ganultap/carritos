package geniar.siscar.logic.consultas;

import geniar.siscar.model.Brands;
import geniar.siscar.model.Cities;
import geniar.siscar.model.Countries;
import geniar.siscar.model.FuelsTypes;
import geniar.siscar.model.LegateesTypes;
import geniar.siscar.model.Lines;
import geniar.siscar.model.LocationsTypes;
import geniar.siscar.model.Models;
import geniar.siscar.model.Modules;
import geniar.siscar.model.NoveltyTypes;
import geniar.siscar.model.Options;
import geniar.siscar.model.RetirementsTypes;
import geniar.siscar.model.Rolls;
import geniar.siscar.model.TapestriesTypes;
import geniar.siscar.model.TractionsTypes;
import geniar.siscar.model.TransmissionsTypes;
import geniar.siscar.model.VehiclesTypes;
import geniar.siscar.model.Zones;
import geniar.siscar.persistence.EntityManagerHelper;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class SearchParameters {

	private static EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	@SuppressWarnings("unchecked")
	public static List<LocationsTypes> getAllLocationsTypes() {
		try {
			final String queryString = "select model from LocationsTypes model ORDER BY model.lctNombre ASC";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public static List<Countries> getAllCountries() {
		try {
			final String queryString = "select model from Countries model ORDER BY model.cntNombre ASC";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public static List<Cities> getAllCities() {
		try {
			final String queryString = "select model from Cities model ORDER BY model.ctsNombre ASC";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public static List<Models> getAllModels() {
		try {
			final String queryString = "select model from Models model ORDER BY model.mdlNombre ASC";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public static List<Brands> getAllBrands() throws GWorkException {
		try {
			
			final String queryString = "select model from Brands model ORDER BY model.brnNombre ASC";
			EntityManagerHelper.getEntityManager().clear();
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public static List<FuelsTypes> getAllFuels() throws GWorkException {
		try {
			final String queryString = "select model from FuelsTypes model ORDER BY model.futNombre ASC";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public static List<Lines> getAllLines() throws GWorkException {
		try {
			final String queryString = "select model from Lines model ORDER BY model.lnsNombre ASC";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public static List<LegateesTypes> getAllLegateesTypes() throws GWorkException {
		try {
			final String queryString = "select model from LegateesTypes model ORDER BY model.lgtNombre ASC";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public static List<VehiclesTypes> getAllVehicleTypes() {
		try {
			final String queryString = "select model from VehiclesTypes model ORDER BY model.vhtNombre ASC";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public static List<TractionsTypes> getAllTracctionTypes() {
		try {
			final String queryString = "select model from TractionsTypes model ORDER BY model.tctNombre ASC";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public static List<TapestriesTypes> getAllTapestriesTypes() {
		try {
			final String queryString = "select model from TapestriesTypes model ORDER BY model.tptpcNombre ASC";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public static List<TransmissionsTypes> getAllTractionTypes() {
		try {
			final String queryString = "select model from TransmissionsTypes model ORDER BY model.tntNombre ASC";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public static List<RetirementsTypes> getAllRetirementsTypes() {
		try {
			final String queryString = "select model from RetirementsTypes model ORDER BY model.retNombre ASC";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Zones> getZonesAllOrder() {
		EntityManagerHelper.log("finding all Zones instances", Level.INFO, null);
		try {
			final String queryString = "select model from Zones model ORDER BY model.znsNombre ASC";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public static List<Rolls> getAllRolesOrder() {
		try {
			final String queryString = "select model from Rolls model ORDER BY model.rlsNombre ASC";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public static List<Options> getAllOptionsOrder() {
		try {
			final String queryString = "select model from Options model ORDER BY model.optNombre ASC";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public static List<Modules> getAllModulesOrder() {
		try {
			final String queryString = "select model from Modules model ORDER BY model.mdlNombre ASC";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public static List<String> getAllEstados() {
		try {
			List<String> listEstados = new ArrayList<String>();
			String estado1 = Util.loadMessageValue("ESTADO.ACTIVO");
			String estado2 = Util.loadMessageValue("ESTADO.INNACTIVO");
			listEstados.add(estado1);
			listEstados.add(estado2);
			return listEstados;
		} catch (Exception e) {
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public static List<NoveltyTypes> getAllNovelty() throws GWorkException {
		try {
			EntityManagerHelper.getEntityManager().clear();
			final String queryString = "select model from NoveltyTypes model ORDER BY model.ntNombre ASC";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			throw re;
		}
	}
}
