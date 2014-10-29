package geniar.siscar.logic.policies.services.impl;

import geniar.siscar.logic.consultas.SearchPolicies;
import geniar.siscar.logic.consultas.SearchPoliciesTypes;
import geniar.siscar.logic.consultas.SearchPoliciesVehicles;
import geniar.siscar.logic.consultas.SearchVehicles;
import geniar.siscar.logic.policies.services.PoliciesService;
import geniar.siscar.model.Inconsistencies;
import geniar.siscar.model.Policies;
import geniar.siscar.model.PoliciesHistoric;
import geniar.siscar.model.PoliciesTypes;
import geniar.siscar.model.PoliciesVehicles;
import geniar.siscar.model.PoliciesVehiclesHistoric;
import geniar.siscar.model.PoliciesVehiclesId;
import geniar.siscar.model.VOPolicies;
import geniar.siscar.model.Vehicles;
import geniar.siscar.persistence.EntityManagerHelper;
import geniar.siscar.persistence.InconsistenciesDAO;
import geniar.siscar.persistence.PoliciesDAO;
import geniar.siscar.persistence.PoliciesHistoricDAO;
import geniar.siscar.persistence.PoliciesVehiclesDAO;
import geniar.siscar.persistence.PoliciesVehiclesHistoricDAO;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class PoliciesServiceImpl implements PoliciesService {

	public Policies consultarPoliza(Long numPoliza) throws GWorkException {
		Util.validarSession();
		return new SearchPolicies().consultarPoliza(numPoliza);
	}

	public Policies consultarPolizaActivaSOAT(Vehicles vehicles)
			throws GWorkException {
		Util.validarSession();
		return new SearchPolicies()
				.consultarSoatActivo(vehicles.getVhcCodigo());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see geniar.siscar.logic.policies.services.
	 *      PoliciesService#crearPoliza(java.lang.Long, java.lang.Long,
	 *      java.util.Date, java.util.Date, java.lang.String, java.lang.String,
	 *      java.lang.String)
	 */
	public void crearPoliza(String placaVehiculo, Long idTipoPoliza,
			Long numPoliza, Date fechaInicio, Date fechaTermino, String login,
			String plsDocumento1, String plsDocumento2, Float valorIVA,
			Float valorPrima, Float plsValorAseg) throws GWorkException {

		Util.validarSession();

		if (login == null || login.trim().length() == 0) {
			throw new GWorkException(Util
					.loadErrorMessageValue("USUARIO.SESIONEXP"));
		}

		Policies policies = consultarPoliza(numPoliza);

		if (policies != null) {
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.POLIZAEXISTE"));
		}

		policies = new Policies();

		PoliciesTypes policiesTypes = new PoliciesTypesServiceImpl()
				.consultarPoliciesTypesById(idTipoPoliza);

		if (idTipoPoliza == 10) {
			crearSoat(placaVehiculo, policiesTypes, numPoliza, fechaInicio,
					fechaTermino, login, plsDocumento1, plsDocumento2,
					valorIVA, valorPrima, plsValorAseg);
			return;
		}

		try {
			policies.setPoliciesTypes(policiesTypes);
			policies.setPlsNumero(numPoliza);
			policies.setPlsFechainicioCobertura(fechaInicio);
			policies.setPlsFechafinCobertura(fechaTermino);
			policies.setPlsEstado(1L);
			policies.setPlsDocUno(plsDocumento1);
			policies.setPlsDocDos(plsDocumento2);

			Util.validarSession();
			EntityManagerHelper.beginTransaction();
			new PoliciesDAO().save(policies);
			EntityManagerHelper.commit();
			// Util.limpiarSession();

			crearRegistroHistorico(policies, login);

		} catch (RuntimeException e) {
			// Util.limpiarSession();
			e.printStackTrace();
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.CREARPOLIZA"));
		}
	}

	public void crearSoat(String placaVehiculo, PoliciesTypes policiesTypes,
			Long numPoliza, Date fechaInicio, Date fechaTermino, String login,
			String plsDocumento1, String plsDocumento2, Float valorIVA,
			Float valorPrima, Float plsValorAseg) throws GWorkException {
		Util.validarSession();
		Vehicles vehicle = SearchVehicles
				.consultarVehiculosPorPlacaSinFiltros(placaVehiculo);

		Inconsistencies inconsistencies = null;

		inconsistencies = new SearchPoliciesVehicles()
				.consultarInconsistenciaPlacaSoat(placaVehiculo);
		// Se valida si el vehiculo existe
		if (vehicle == null) {
			throw new GWorkException(Util
					.loadErrorMessageValue("PLACA.NOEXISTE"));
		}

		// Se valida que el vehiculo este en la ubicación: REGIONAL
		if (vehicle.getLocations().getLocationsTypes().getLctCodigo()
				.longValue() == 3L) {
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.UBICACION.VHC"));
		}

		// Se valida que el vehiculo se encuentre en un estado permitido
		if (vehicle.getVehiclesStates().getVhsCodigo() == 2
				|| vehicle.getVehiclesStates().getVhsCodigo() == 4
				|| vehicle.getVehiclesStates().getVhsCodigo() == 5) {
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.ESTADOVHC"));
		}

		// Se busca el Soat activo
		Policies soatActivo = buscarPolizaActiva(placaVehiculo);

		if (soatActivo != null) {
			/*
			 * Se valida que las fechas de inicio y terminación no interfieran
			 * con el soat activo, es decir, si las fechas del nuevo soat (de
			 * Inicio y/o de fin), están dentro del rango de fechas del soat
			 * activo, no se permite crear el soat.
			 */
			if (fechaInicio.compareTo(soatActivo.getPlsFechafinCobertura()) <= 0) {
				throw new GWorkException(Util
						.loadErrorMessageValue("ERROR.FECHAS.CREAR"));
			}
			// Se desactiva el soat que ya no tiene vigencia
			cambiarEstadoSoat(soatActivo, true, login);
		}

		try {
			Policies soat = new Policies();

			soat.setPlsDocUno(plsDocumento1);
			soat.setPlsDocDos(plsDocumento2);
			soat.setPlsFechainicioCobertura(fechaInicio);
			soat.setPlsFechafinCobertura(fechaTermino);
			soat.setPlsNumero(numPoliza);
			soat.setPlsEstado(1L);
			soat.setPoliciesTypes(policiesTypes);

			// Se guarda el objeto poliza
			new PoliciesDAO().save(soat);

			crearRegistroHistorico(soat, login);

			PoliciesVehicles policiesVehicles = new PoliciesVehicles();
			policiesVehicles.setId(new PoliciesVehiclesId(vehicle
					.getVhcCodigo(), soat.getPlsCodigo()));
			policiesVehicles.setMonthTransacType(null);
			policiesVehicles.setPolicies(soat);
			policiesVehicles.setPvsEstado(1L);
			policiesVehicles.setPvsFechafin(fechaTermino);
			policiesVehicles.setPvsFechaini(fechaInicio);
			policiesVehicles.setPvsValorAsegurado(plsValorAseg.floatValue());
			policiesVehicles.setPvsValorIva(valorIVA.floatValue());
			policiesVehicles.setPvsValorPrima(valorPrima.floatValue());
			policiesVehicles
					.setPvsValorTotal((valorIVA.floatValue() + valorPrima
							.floatValue()));
			policiesVehicles.setVehicles(vehicle);

			// Se guarda el objeto policies Vehicles
			new PoliciesVehiclesDAO().save(policiesVehicles);

			// Util.limpiarSession();

			PoliciesVehiclesHistoric pvHistoric = new PoliciesVehiclesHistoric();

			pvHistoric.setMonthTransacType(null);
			pvHistoric.setPinNumeroFactura(null);
			pvHistoric.setPlsCodigo(soat.getPlsCodigo());
			pvHistoric.setPvhFecha(new Date());
			pvHistoric.setPvsEstado(1L);
			pvHistoric.setPvsFechafin(fechaTermino);
			pvHistoric.setPvsFechaini(fechaInicio);
			pvHistoric.setPvsValorAsegurado(plsValorAseg);
			pvHistoric.setPvsValorIva(valorIVA);
			pvHistoric.setPvsValorPrima(valorPrima);
			pvHistoric.setPvsValorTotal((valorIVA.floatValue() + valorPrima
					.floatValue()));
			pvHistoric.setUsrLogin(login);
			pvHistoric.setVhcCodigo(vehicle.getVhcCodigo());

			// Se guarda el historial de las polizas
			new PoliciesVehiclesHistoricDAO().save(pvHistoric);

			// Util.limpiarSession();

			if (inconsistencies != null) {
				inconsistencies.setIncEstado(0L);
				new InconsistenciesDAO().update(inconsistencies);
			}

			// Se confirma la transaccion para guarda cada uno de los objetos en
			// la BD
			EntityManagerHelper.getEntityManager().getTransaction().begin();
			EntityManagerHelper.getEntityManager().getTransaction().commit();

		} catch (RuntimeException e) {
			// Util.limpiarSession();
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.CONFIRMSOAT")
					+ ": " + e.getMessage());
		}
	}

	public void modificarPoliza(String placaVehiculo, Long idTipoPoliza,
			Long numPoliza, Long nuevoNumPoliza, Date fechaInicio,
			Date fechaTermino, String login, String plsDocumento1,
			String plsDocumento2, Float plsValorContribucion,
			Float plsValorPrima, Float plsValorAseg) throws GWorkException {

		Util.validarSession();

		if (login == null || login.trim().length() == 0) {
			throw new GWorkException(Util
					.loadErrorMessageValue("USUARIO.SESIONEXP"));
		}

		Policies policies = consultarPoliza(numPoliza);

		if (policies == null) {
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.POLIZANEXISTE"));
		}

		PoliciesTypes policiesTypes = new PoliciesTypesServiceImpl()
				.consultarPoliciesTypesById(idTipoPoliza);

		if (policiesTypes.getPltCodigo().longValue() == 10L) {
			modificarSOAT(placaVehiculo, idTipoPoliza, numPoliza,
					nuevoNumPoliza, fechaInicio, fechaTermino, login,
					plsDocumento1, plsDocumento2, plsValorContribucion,
					plsValorPrima, plsValorAseg);
			return;
		}

		try {
			policies.setPoliciesTypes(policiesTypes);
			if (nuevoNumPoliza != null)
				policies.setPlsNumero(nuevoNumPoliza);
			else
				policies.setPlsNumero(numPoliza);
			policies.setPlsFechainicioCobertura(fechaInicio);
			policies.setPlsFechafinCobertura(fechaTermino);
			policies.setPlsDocUno(plsDocumento1);
			policies.setPlsDocDos(plsDocumento2);

			EntityManagerHelper.beginTransaction();
			new PoliciesDAO().update(policies);
			EntityManagerHelper.commit();
			// Util.limpiarSession();

			crearRegistroHistorico(policies, login);

		} catch (RuntimeException e) {
			// Util.limpiarSession();
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.CREARPOLIZA"));
		}
	}

	private void modificarSOAT(String placaVehiculo, Long idTipoPoliza,
			Long numPoliza, Long nuevoNumPoliza, Date fechaInicio,
			Date fechaTermino, String login, String plsDocumento1,
			String plsDocumento2, Float plsValorContribucion,
			Float plsValorPrima, Float plsValorAseg) throws GWorkException {

		Util.validarSession();
		Vehicles vehicle = SearchVehicles
				.consultarVehiculosPorPlacaSinFiltros(placaVehiculo);

		// Se valida si el vehiculo existe
		if (vehicle == null) {
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.PLACANEXISTESOAT"));
		}

		// Se valida que el vehiculo este en la ubicación: REGIONALES
		if (vehicle.getLocations().getLocationsTypes().getLctCodigo()
				.longValue() == 3L) {
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.UBICACION.VHC"));
		}

		// Se valida que el vehiculo se encuentre en un estado permitido
		if (vehicle.getVehiclesStates().getVhsCodigo() == 2
				|| vehicle.getVehiclesStates().getVhsCodigo() == 4
				|| vehicle.getVehiclesStates().getVhsCodigo() == 5) {
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.ESTADOVHC"));
		}

		// Se pregunta si el ya exite el soat que se va a modificar
		if (consultarPoliza(numPoliza) == null) {
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.SOATNOEXISTE"));
		}

		// Se busca el Soat activo
		Policies soatActivo = buscarPolizaActiva(placaVehiculo);

		try {
			soatActivo.setPlsDocUno(plsDocumento1);
			soatActivo.setPlsDocDos(plsDocumento2);
			soatActivo.setPlsFechainicioCobertura(fechaInicio);
			soatActivo.setPlsFechafinCobertura(fechaTermino);
			if (nuevoNumPoliza != null)
				soatActivo.setPlsNumero(nuevoNumPoliza);
			else
				soatActivo.setPlsNumero(numPoliza);
			soatActivo.setPlsEstado(1L);

			Util.validarSession();
			EntityManagerHelper.beginTransaction();
			new PoliciesDAO().update(soatActivo);
			EntityManagerHelper.commit();
			// Util.limpiarSession();

			crearRegistroHistorico(soatActivo, login);

			vehicle = SearchVehicles
					.consultarVehiculosPorPlacaSinFiltros(placaVehiculo);

			Iterator<PoliciesVehicles> iter = vehicle.getPoliciesVehicleses()
					.iterator();

			PoliciesVehicles pvs = null;

			while (iter.hasNext()) {
				PoliciesVehicles policiesVehicles = (PoliciesVehicles) iter
						.next();

				Long idTipoPol = policiesVehicles.getPolicies()
						.getPoliciesTypes().getPltCodigo();

				Long estado = policiesVehicles.getPvsEstado();

				if (idTipoPol.longValue() == idTipoPoliza) {
					if (estado.longValue() == 1L) {
						pvs = policiesVehicles;
						break;
					}
				}
			}

			pvs.setPvsValorAsegurado(plsValorAseg.floatValue());
			pvs.setPvsValorIva(plsValorContribucion.floatValue());
			pvs.setPvsValorPrima(plsValorPrima.floatValue());
			pvs
					.setPvsValorTotal((plsValorContribucion.floatValue() + plsValorPrima
							.floatValue()));

			Util.validarSession();
			EntityManagerHelper.beginTransaction();
			new PoliciesVehiclesDAO().update(pvs);
			EntityManagerHelper.commit();
			// Util.limpiarSession();

			PoliciesVehiclesHistoric pvHistoric = new PoliciesVehiclesHistoric();

			pvHistoric.setMonthTransacType(null);
			pvHistoric.setPinNumeroFactura(null);
			pvHistoric.setPlsCodigo(soatActivo.getPlsCodigo());
			pvHistoric.setPvhFecha(new Date());
			pvHistoric.setPvsEstado(1L);
			pvHistoric.setPvsFechafin(fechaTermino);
			pvHistoric.setPvsFechaini(fechaInicio);
			pvHistoric.setPvsValorAsegurado(plsValorAseg);
			pvHistoric.setPvsValorIva(plsValorContribucion);
			pvHistoric.setPvsValorPrima(plsValorPrima);
			pvHistoric
					.setPvsValorTotal((plsValorContribucion.floatValue() + plsValorPrima
							.floatValue()));
			pvHistoric.setUsrLogin(login);
			pvHistoric.setVhcCodigo(vehicle.getVhcCodigo());

			Util.validarSession();
			EntityManagerHelper.beginTransaction();
			new PoliciesVehiclesHistoricDAO().save(pvHistoric);
			EntityManagerHelper.commit();
			// Util.limpiarSession();

		} catch (Exception e) {
			// Util.limpiarSession();
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.CONFIRMSOAT"));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see geniar.siscar.logic.policies.services.
	 *      PoliciesService#reemplazarPoliza(java.lang.Long, java.lang.Long,
	 *      java.lang.Long, java.util.Date, java.util.Date)
	 */
	public void reemplazarPoliza(Long idTipoPoliza, Long numeroPoliza,
			Long nuevoNumero, Date fechaIni, Date fechaFin, String login,
			String plsDocumento1, String plsDocumento2, String placaVehiculo,
			Float plsValorAseg, Float plsValorContribucion,
			Float plsValorPrima, Float plsValorTotal, Vehicles vehicles)
			throws GWorkException {
		Util.validarSession();

		if (login == null || login.trim().length() == 0) {
			throw new GWorkException(Util
					.loadErrorMessageValue("USUARIO.SESIONEXP"));
		}

		Policies policies = consultarPoliza(numeroPoliza);

		if (policies != null) {
			/*
			 * Se valida que las fechas de inicio y terminación no interfieran
			 * con la poliza, es decir, si las fechas de la nueva poliza (de
			 * Inicio y/o de fin), están dentro del rango de fechas de la poliza
			 * vigente, no se permite crear la poliza.
			 */
			if (fechaIni.before(policies.getPlsFechainicioCobertura())) {
				throw new GWorkException(Util
						.loadErrorMessageValue("ERROR.INTERFFECHASPOL"));
			}
			// Date fechaActual = new Date();
			//			
			// if (fechaActual.after(fechaIni)) {
			// throw new GWorkException(Util
			// .loadErrorMessageValue("ERROR.REEMPFECHAIN"));
			// }
		}

		if (consultarPoliza(nuevoNumero) != null) {
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.POLIZAEXISTE"));
		}

		PoliciesTypes policiesTypes = new PoliciesTypesServiceImpl()
				.consultarPoliciesTypesById(idTipoPoliza);

		if (idTipoPoliza.longValue() == 10L) {
			reemplazarSoat(vehicles, numeroPoliza, nuevoNumero, fechaIni,
					fechaFin, plsValorPrima, plsValorContribucion,
					plsValorAseg, plsDocumento1, plsDocumento2, idTipoPoliza,
					login);
			return;
		}

		try {
			// Se guarda la poliza de reemplazo
			Policies policiesNew = new Policies();
			policiesNew.setPoliciesTypes(policiesTypes);
			policiesNew.setPlsNumero(nuevoNumero);
			policiesNew.setPlsEstado(1L);
			policiesNew.setPlsFechainicioCobertura(fechaIni);
			policiesNew.setPlsFechafinCobertura(fechaFin);
			policiesNew.setPlsDocUno(plsDocumento1);
			policiesNew.setPlsDocDos(plsDocumento2);

			Util.validarSession();
			EntityManagerHelper.beginTransaction();
			new PoliciesDAO().save(policiesNew);
			EntityManagerHelper.commit();
			// Util.limpiarSession();

			// Se guarda el historico de la poliza de reemplazo
			crearRegistroHistorico(policiesNew, login);

			Util.validarSession();
			EntityManagerHelper.beginTransaction();
			policies = consultarPoliza(numeroPoliza);

			Iterator<PoliciesVehicles> i = policies.getPoliciesVehicleses()
					.iterator();

			while (i.hasNext()) {
				PoliciesVehicles policiesVehicles = (PoliciesVehicles) i.next();

				policiesVehicles.setPvsEstado(0L);

				Util.validarSession();
				EntityManagerHelper.beginTransaction();
				new PoliciesVehiclesDAO().update(policiesVehicles);

				Float pvsValorAsegurado = policiesVehicles
						.getPvsValorAsegurado();
				Float pvsValorPrima = policiesVehicles.getPvsValorPrima();
				Float pvsValorIVA = policiesVehicles.getPvsValorIva();
				Long vhcCodigo = policiesVehicles.getVehicles().getVhcCodigo();

				PoliciesVehiclesHistoric pvHistoric = new PoliciesVehiclesHistoric();

				pvHistoric.setMonthTransacType(null);
				pvHistoric.setPinNumeroFactura(null);
				pvHistoric.setPlsCodigo(policiesVehicles.getPolicies()
						.getPlsCodigo());
				pvHistoric.setPvhFecha(new Date());
				pvHistoric.setPvsEstado(1L);
				pvHistoric.setPvsFechaini(policiesVehicles.getPvsFechaini());
				pvHistoric.setPvsFechafin(policiesVehicles.getPvsFechafin());
				pvHistoric.setPvsValorAsegurado(pvsValorAsegurado);
				pvHistoric.setPvsValorIva(pvsValorIVA);
				pvHistoric.setPvsValorPrima(pvsValorPrima);
				pvHistoric.setPvsValorTotal(pvsValorIVA + pvsValorPrima);
				pvHistoric.setUsrLogin(login);
				pvHistoric.setVhcCodigo(vhcCodigo);

				new PoliciesVehiclesHistoricDAO().save(pvHistoric);
				EntityManagerHelper.commit();
				// Util.limpiarSession();

			}

			// Se cambia el estado de la poliza antigua
			Util.validarSession();
			EntityManagerHelper.beginTransaction();
			policies = consultarPoliza(numeroPoliza);
			policies.setPlsEstado(0L);

			Util.validarSession();
			EntityManagerHelper.beginTransaction();
			new PoliciesDAO().update(policies);
			EntityManagerHelper.commit();
			// Util.limpiarSession();

			// Se guarda el histórico de la póliza actualizada
			crearRegistroHistorico(policies, login);

		} catch (RuntimeException e) {
			// Util.limpiarSession();
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.CREARPOLIZA"));
		}
	}

	public List<Policies> consultarTodasPolizas(Long idTipoPoliza)
			throws GWorkException {
		Util.validarSession();
		return new SearchPolicies().consultarPolizasPorTipoPoliza(idTipoPoliza);
	}

	public List<Policies> consultarSOATsInactivosVehiculo(String placaVechiculo)
			throws GWorkException {

		Vehicles vehicles = SearchVehicles
				.consultarVehiculosPorPlacaSinFiltros(placaVechiculo);

		Iterator<PoliciesVehicles> iter = vehicles.getPoliciesVehicleses()
				.iterator();

		List<Policies> inactivas = new ArrayList<Policies>();

		while (iter.hasNext()) {
			PoliciesVehicles policiesVehicles = (PoliciesVehicles) iter.next();

			Long idTipoPol = policiesVehicles.getPolicies().getPoliciesTypes()
					.getPltCodigo();

			Long estado = policiesVehicles.getPvsEstado();

			if (idTipoPol.longValue() == 10L && estado.longValue() == 0L) {
				inactivas.add(policiesVehicles.getPolicies());
			}
		}

		return inactivas;
	}

	public List<Policies> consultarTodasPolizasVigentes() throws GWorkException {
		return new SearchPolicies().consultarTodasPolizasVigentes();
	}

	public List<Policies> consultarTodasPolizasVigentesNOSOAT()
			throws GWorkException {
		return new SearchPolicies().consultarPolizasVigentesNOSOAT();
	}

	public void cambiarEstadoSoat(Policies soat, boolean activo, String login)
			throws GWorkException {
		if (activo) {

			soat.setPlsEstado(0L);
		}
		try {
			Long id = soat.getPlsCodigo();
			// Util.validarSession();
			// EntityManagerHelper.beginTransaction();
			new PoliciesDAO().update(soat);
			// EntityManagerHelper.commit();
			// //Util.limpiarSession();

			// Util.validarSession();
			// EntityManagerHelper.beginTransaction();
			Policies ssssoat = new PoliciesDAO().findById(id);

			crearRegistroHistorico(ssssoat, login);

			Set<PoliciesVehicles> set = ssssoat.getPoliciesVehicleses();

			Iterator<PoliciesVehicles> iter = set.iterator();

			while (iter.hasNext()) {
				PoliciesVehicles policiesVehicles = (PoliciesVehicles) iter
						.next();

				Long idTipoPol = policiesVehicles.getPolicies()
						.getPoliciesTypes().getPltCodigo();

				Long numpol = policiesVehicles.getPolicies().getPlsNumero();

				if (idTipoPol.longValue() == 10L
						&& numpol.longValue() == soat.getPlsNumero()
								.longValue()) {
					policiesVehicles.setPvsEstado(0L);

					// Util.validarSession();
					// EntityManagerHelper.beginTransaction();
					new PoliciesVehiclesDAO().update(policiesVehicles);
					// EntityManagerHelper.commit();
					// //Util.limpiarSession();
				}
			}
		} catch (RuntimeException e) {
			// Util.limpiarSession();
			e.printStackTrace();
		}

	}

	public Policies buscarPolizaActiva(String placaVehiculo)
			throws GWorkException {

		Policies polizaActiva = null;

		Vehicles vehicle = SearchVehicles
				.consultarVehiculosPorPlacaSinFiltros(placaVehiculo);

		Set<PoliciesVehicles> pcTemp = vehicle.getPoliciesVehicleses();

		if (!pcTemp.isEmpty()) {
			// Se cargan las polizas asociadas al vehiculo.
			Iterator<PoliciesVehicles> iter = pcTemp.iterator();

			while (iter.hasNext()) {// Se pregunta si el vehiculo tiene otra
				// poliza asociada
				// Se trae la asociacion
				PoliciesVehicles policyVehicle = (PoliciesVehicles) iter.next();

				Policies tempPol = policyVehicle.getPolicies();

				// Se trae el tipo de poliza que tiene asociada
				PoliciesTypes policiyType = tempPol.getPoliciesTypes();

				Long idTipoPol = policiyType.getPltCodigo();

				if (idTipoPol.longValue() == 10L
						&& tempPol.getPlsEstado().longValue() == 1L) {
					polizaActiva = tempPol;
					break;
				}

			}
		}
		return polizaActiva;
	}

	private void crearRegistroHistorico(Policies policies, String login)
			throws GWorkException {
		PoliciesHistoric historic = new PoliciesHistoric();
		historic.setPlhFecha(new Date());
		historic.setUsrLogin(login);
		historic.setPlsDocDos(policies.getPlsDocDos());
		historic.setPlsDocUno(policies.getPlsDocUno());
		historic.setPlsEstado(policies.getPlsEstado());
		historic.setPlsFechainicioCobertura(policies
				.getPlsFechainicioCobertura());
		historic.setPlsFechafinCobertura(policies.getPlsFechafinCobertura());
		historic.setPlsNumero(policies.getPlsNumero());
		historic.setPoliciesTypes(policies.getPoliciesTypes());
		// Util.validarSession();
		// EntityManagerHelper.beginTransaction();
		new PoliciesHistoricDAO().save(historic);
		// EntityManagerHelper.commit();
		// //Util.limpiarSession();
	}

	public void reemplazarSoat(Vehicles vehicle, Long numeroSoat,
			Long nuevoNumero, Date soaFechaInicio, Date soaFechaTerminacion,
			Float soaValorPrima, Float soaValorContribucion, Float valorAseg,
			String soaDocumento1, String soaDocumento2, Long idTipoPoliza,
			String login) throws GWorkException {

		Util.validarSession();

		// Se valida si el vehiculo existe
		if (vehicle == null) {
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.PLACANEXISTESOAT"));
		}
/*
		vehicle = EntityManagerHelper.getEntityManager().merge(vehicle);

		vehicle.setLocations(EntityManagerHelper.getEntityManager().merge(
				vehicle.getLocations()));

		vehicle.getLocations().setLocationsTypes(
				EntityManagerHelper.getEntityManager().merge(
						vehicle.getLocations().getLocationsTypes()));
*/
		// Se valida que el vehiculo este en la ubicación: REGIONAL
		if (vehicle.getLocations().getLocationsTypes().getLctCodigo()
				.longValue() == 3L) {
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.UBICACION.VHC"));
		}

		// Se valida que el vehiculo se encuentre en un estado permitido
		if (vehicle.getVehiclesStates().getVhsCodigo().longValue() == 2L
				|| vehicle.getVehiclesStates().getVhsCodigo().longValue() == 4L
				|| vehicle.getVehiclesStates().getVhsCodigo().longValue() == 5L) {
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.ESTADOVHC"));
		}

		if (consultarPoliza(nuevoNumero) != null) {
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.SOATEXISTE"));
		}

		// Se busca el Soat activo
		Policies soatActivo = consultarPolizaActivaSOAT(vehicle);

		if (soatActivo != null) {
			/*
			 * Se valida que las fechas de inicio y terminación no interfieran
			 * con el soat activo, es decir, si las fechas del nuevo soat (de
			 * Inicio y/o de fin), están dentro del rango de fechas del soat
			 * activo, no se permite crear el soat.
			 */
			if (soatActivo.getPlsFechafinCobertura().before(soaFechaInicio)) {
				throw new GWorkException(Util
						.loadErrorMessageValue("ERROR.INTERF.FECHAS"));
			}
			// Se desactiva el soat que ya no tiene vigencia
			cambiarEstadoSoat(soatActivo, true, login);
		}

		PoliciesTypes policiesTypes = new SearchPoliciesTypes()
				.consultarPoliciesTypes(idTipoPoliza);

		// Se guarda el nuevo soat
		Policies soat = new Policies();

		soat.setPlsDocUno(soaDocumento1);
		soat.setPlsDocDos(soaDocumento2);
		soat.setPlsFechainicioCobertura(soaFechaInicio);
		soat.setPlsFechafinCobertura(soaFechaTerminacion);
		soat.setPlsNumero(nuevoNumero);
		soat.setPlsEstado(1L);
		soat.setPoliciesTypes(policiesTypes);

		// Util.validarSession();
		// EntityManagerHelper.beginTransaction();
		new PoliciesDAO().save(soat);
		// EntityManagerHelper.commit();
		// //Util.limpiarSession();

		// se buscan las asociaciones que habian con la poliza anterior
		// para cambiar su estado a INACTIVO (0).
		// Util.validarSession();
		// EntityManagerHelper.beginTransaction();
		List<PoliciesVehicles> iter = new SearchPolicies().consultarTodosPVs(
				vehicle.getVhcCodigo(), soatActivo.getPlsCodigo());

		if (iter != null) {
			for (int i = 0; i < iter.size(); i++) {
				PoliciesVehicles pv = (PoliciesVehicles) iter.get(i);

				Long idTipoPol = pv.getPolicies().getPoliciesTypes()
						.getPltCodigo();

				Long numpol = pv.getPolicies().getPlsNumero();

				if (idTipoPol.longValue() == 10L
						&& numpol.longValue() == soat.getPlsNumero()
								.longValue()) {
					pv.setPvsEstado(0L);

					// Util.validarSession();
					// EntityManagerHelper.beginTransaction();
					new PoliciesVehiclesDAO().update(pv);
					// EntityManagerHelper.commit();
					// //Util.limpiarSession();
				}
			}
		}

		// Se guarda la asociación de la poliza con el vehiculo
		PoliciesVehicles policiesVehicles = new PoliciesVehicles();
		policiesVehicles.setId(new PoliciesVehiclesId(vehicle.getVhcCodigo(),
				soat.getPlsCodigo()));
		policiesVehicles.setMonthTransacType(null);
		policiesVehicles.setPolicies(soat);
		policiesVehicles.setPvsEstado(1L);
		policiesVehicles.setPvsFechafin(soaFechaTerminacion);
		policiesVehicles.setPvsFechaini(soaFechaInicio);
		policiesVehicles.setPvsValorAsegurado(valorAseg.floatValue());
		policiesVehicles.setPvsValorIva(soaValorContribucion.floatValue());
		policiesVehicles.setPvsValorPrima(soaValorPrima.floatValue());
		policiesVehicles
				.setPvsValorTotal((soaValorContribucion.floatValue() + soaValorPrima
						.floatValue()));
		policiesVehicles.setVehicles(vehicle);

		// Util.validarSession();
		// EntityManagerHelper.beginTransaction();
		new PoliciesVehiclesDAO().save(policiesVehicles);
		// EntityManagerHelper.commit();
		// //Util.limpiarSession();

		// se guarda el historico de la asociación del vehiculo a la poliza.
		PoliciesVehiclesHistoric pvHistoric = new PoliciesVehiclesHistoric();

		Policies PolicesHistorico = new SearchPolicies()
				.consultarPoliza(numeroSoat);
		pvHistoric.setMonthTransacType(null);
		pvHistoric.setPinNumeroFactura(null);
		pvHistoric.setPlsCodigo(PolicesHistorico.getPlsCodigo());
		pvHistoric.setPvhFecha(new Date());
		pvHistoric.setPvsEstado(1L);
		pvHistoric.setPvsFechafin(soaFechaTerminacion);
		pvHistoric.setPvsFechaini(soaFechaInicio);
		pvHistoric.setPvsValorAsegurado(valorAseg.floatValue());
		pvHistoric.setPvsValorIva(soaValorContribucion);
		pvHistoric.setPvsValorPrima(soaValorPrima);
		pvHistoric
				.setPvsValorTotal((soaValorContribucion.floatValue() + soaValorPrima
						.floatValue()));
		pvHistoric.setUsrLogin(login);
		pvHistoric.setVhcCodigo(vehicle.getVhcCodigo());

		new PoliciesVehiclesHistoricDAO().save(pvHistoric);
		// Util.validarSession();
		EntityManagerHelper.getEntityManager().getTransaction().begin();
		EntityManagerHelper.getEntityManager().getTransaction().commit();
		// Util.limpiarSession();
	}

	public List<PoliciesVehicles> consultarPvsVHC(String placa)
			throws GWorkException {
		return new SearchPolicies().consultarPvsVHC(placa);
	}

	public List<VOPolicies> consultarTodasPolizasSOAT(Long idTipoPoliza)
			throws GWorkException {
		List<PoliciesVehicles> policiesSOAT = SearchPolicies
				.policesVehiclesBySOAT(idTipoPoliza);

		if (policiesSOAT == null || policiesSOAT.size() == 0
				|| policiesSOAT.isEmpty())
			throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));

		List<VOPolicies> listaVOPoliciesSOAT = new ArrayList<VOPolicies>();

		for (PoliciesVehicles policiesVehicles : policiesSOAT) {

			// Valida que la poliza no pertenezca a una factura ya creada
			if (policiesVehicles.getPoliciesInvoice() == null) {
				VOPolicies policiesVO = new VOPolicies();
				// Ingresar los valores de cada una de las polizas de vehiculos,
				// al
				// nuevo objeto de VOPolicies

				policiesVO.setVehicles(policiesVehicles.getVehicles());
				policiesVO.setPolicies(policiesVehicles.getPolicies());
				policiesVO.setPvsFechaini(policiesVehicles.getPvsFechaini());
				policiesVO.setPvsFechafin(policiesVehicles.getPvsFechafin());
				policiesVO.setPvsCarnetAsignatario(policiesVehicles
						.getPvsCarnetAsignatario());
				policiesVO.setPvsValorAsegurado(policiesVehicles
						.getPvsValorAsegurado());
				policiesVO.setPvsValorIva(policiesVehicles.getPvsValorIva());
				policiesVO
						.setPvsValorPrima(policiesVehicles.getPvsValorPrima());
				policiesVO
						.setPvsValorTotal(policiesVehicles.getPvsValorTotal());

				listaVOPoliciesSOAT.add(policiesVO);
			}

		}

		if (listaVOPoliciesSOAT != null && listaVOPoliciesSOAT.size() > 0
				&& !listaVOPoliciesSOAT.isEmpty())
			return listaVOPoliciesSOAT;
		else
			throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));
	}
}
