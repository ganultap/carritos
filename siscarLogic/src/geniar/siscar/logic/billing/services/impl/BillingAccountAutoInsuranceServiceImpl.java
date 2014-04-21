package geniar.siscar.logic.billing.services.impl;

import geniar.siscar.consults.impl.ConsultsServiceImpl;
import geniar.siscar.logic.billing.services.BillingAccountAutoInsuranceService;
import geniar.siscar.logic.billing.services.PrepaidFuelProofService;
import geniar.siscar.logic.consultas.SearchPeriod;
import geniar.siscar.logic.consultas.SearchPlainFileParameter;
import geniar.siscar.logic.consultas.SearchVehicles;
import geniar.siscar.model.BillingAccountAutoInsuranceVO;
import geniar.siscar.model.FlatFile;
import geniar.siscar.model.Period;
import geniar.siscar.model.PlainFileParameter;
import geniar.siscar.model.Tariffs;
import geniar.siscar.model.VehiclesAssignation;
import geniar.siscar.model.VhaFf;
import geniar.siscar.persistence.EntityManagerHelper;
import geniar.siscar.persistence.FlatFileDAO;
import geniar.siscar.persistence.NoveltyTypesDAO;
import geniar.siscar.persistence.PeriodDAO;
import geniar.siscar.persistence.VehiclesAssignationDAO;
import geniar.siscar.persistence.VhaFfDAO;
import geniar.siscar.util.ParametersUtil;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class BillingAccountAutoInsuranceServiceImpl implements
		BillingAccountAutoInsuranceService {

	private static final Log log = LogFactory.getLog(BillingAccountAutoInsuranceServiceImpl.class);
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see geniar.siscar.logic.parameters.services.
	 *      PlainFileParameterService#consultarPlainFileParameter
	 *      (java.lang.Long, java.lang.Long)
	 */
	public Period consultarPeriod(String nombre) throws GWorkException {
		return new SearchPeriod().consultar_PRD_PorNombre(nombre);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see geniar.siscar.logic.parameters.services.
	 *      PlainFileParameterService#crearPlainFileParameter( java.lang.Long,
	 *      java.lang.Long, java.lang.Long, java.lang.String)
	 */
	public void crearPeriod(String prdNombre, String prdDescripcion,
			Date prdFechainicio, Date prdFechaterminacion)
			throws GWorkException {

		if (new SearchPeriod().consultarPeriodoPorNombre(prdNombre) != null) {
			throw new GWorkException("El periodo ya existe en el sistema");
		}

		if (new SearchPeriod().consultarPeriodoPorFecha(prdFechainicio) != null) {
			throw new GWorkException(
					"El rango de fechas ingresado para este periodo está trocado con otro periodo");
		}

		Period period = new Period();

		period.setPerNombre(prdNombre);
		period.setPerObservacion(prdDescripcion);
		period.setPerFechaIni(prdFechainicio);
		period.setPerFechaFin(prdFechaterminacion);

		EntityManagerHelper.getEntityManager().getTransaction().begin();
		new PeriodDAO().save(period);
		EntityManagerHelper.getEntityManager().getTransaction().commit();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see geniar.siscar.logic.parameters.services.
	 *      PlainFileParameterService#listarTodosPlainFileParameters()
	 */
	public List<Period> listarTodosPeriod() throws GWorkException {
		return new PeriodDAO().findAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see geniar.siscar.logic.period.services. PeriodService#modificarPeriod(
	 *      java.lang.Long, java.lang.Long)
	 */
	public void modificarPeriod(Long prdPeriodo, String prdNombre,
			String prdDescripcion, Date prdFechainicio, Date prdFechaterminacion)
			throws GWorkException {
		try {
			Period period = new PeriodDAO().findById(prdPeriodo);

			if (period == null) {
				throw new GWorkException(
						"No existen datos para el periodo ingresado ");
			}

			if (new SearchPeriod().consultarPeriodoPorNombreNoId(prdNombre,
					prdPeriodo) != null) {
				throw new GWorkException("El periodo ya existe en el sistema");
			}

			if (new SearchPeriod().consultarPeriodoPorFechaNoId(prdFechainicio,
					prdPeriodo) != null) {
				throw new GWorkException(
						"El rango de fechas ingresado para este periodo está trocado con otro periodo");
			}

			period.setPerNombre(prdNombre.trim().toUpperCase());
			period.setPerObservacion(prdDescripcion.trim().toUpperCase());
			period.setPerFechaIni(prdFechainicio);
			period.setPerFechaFin(prdFechaterminacion);

			EntityManagerHelper.getEntityManager().getTransaction().begin();
			new PeriodDAO().update(period);
			EntityManagerHelper.getEntityManager().getTransaction().commit();
		} catch (Exception e) {
			EntityManagerHelper.getEntityManager().getTransaction().rollback();
			log.error("modificarPeriod", e);
			throw new GWorkException(e.getMessage(), e);
		}
	}

	/**
	 * Metodo encargado de ejecutar la eliminiacion de un periodo
	 * 
	 * @param id
	 * @throws GWorkException
	 */
	public void eliminarPeriodo(Long id) throws GWorkException {
		try {
			Util.validarSession();
			EntityManagerHelper.getEntityManager().getTransaction().begin();
			Period period = new PeriodDAO().findById(id);
			if (period != null) {
				new PeriodDAO().delete(period);
				EntityManagerHelper.getEntityManager().getTransaction()
						.commit();
				// Util.limpiarSession();
			}
		} catch (Exception e) {
			EntityManagerHelper.getEntityManager().getTransaction().rollback();
			// Util.limpiarSession();
			log.error("eliminarPeriodo", e);
			throw new GWorkException(e.getMessage(), e);
		}
	}

	public Period consultarPeriodById(Long idPeriodo) throws GWorkException {
		return new PeriodDAO().findById(idPeriodo);
	}

	/* Consulta nueva */
	public Period consultarPeriodByAno(String anoCobro) throws GWorkException {
		try {
			Date fecha_ini_actual;
			Date fecha_fin_actual;

			Calendar calendario = Calendar.getInstance();

			calendario.set(Integer.parseInt(anoCobro), 0, 1);
			fecha_ini_actual = calendario.getTime();

			calendario.set(Integer.parseInt(anoCobro), 11, 31);
			fecha_fin_actual = calendario.getTime();

			final String sql = "SELECT model FROM Period model"
					+ " WHERE model.perFechaIni = :fecha_ini_actual"
					+ " AND model.perFechaFin = :fecha_fin_actual";

			Query query = EntityManagerHelper.getEntityManager().createQuery(
					sql);

			query.setParameter("fecha_ini_actual", fecha_ini_actual);
			query.setParameter("fecha_fin_actual", fecha_fin_actual);

			Period objPeriod = (Period) query.getSingleResult();
			if (objPeriod != null)
				return objPeriod;
			else
				throw new GWorkException(Util
						.loadErrorMessageValue("ERROR.NOEXISTEPERIODO"));
		} catch (Exception e) {
			log.error("consultarPeriodByAno", e);
			throw new GWorkException(e.getMessage(), e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<BillingAccountAutoInsuranceVO> listVehiclesFlatFile(
			String anoCobro, Period periodo) throws GWorkException {
		try {

			List<Object[]> bilList = new ArrayList<Object[]>();
			List<BillingAccountAutoInsuranceVO> bilListVO = new ArrayList<BillingAccountAutoInsuranceVO>();

			/* define las fechas de inicio y fin del año actual */
			Date FechaInicial;

			FechaInicial = periodo.getPerFechaIni();

			final String sql = "SELECT va.vehicles.vhcCodigo, "
					+ "va.requests.rqsCarnetAsignatario, "
					+ "va.vehicles.vhcPlacaDiplomatica, "
					+ "va.requests.usersByRqsUser.usrNombre, "
					+ "va.requests.usersByRqsUser.usrApellido, va.vhaCodigo, "
					+ "va.requests.legateesTypes.lgtNombre, "
					+ "va.vhaFechaInicio, "
					+ "va.vhaFechaTermina from VehiclesAssignation va "
					+ "WHERE va.requests.legateesTypes.lgtCodigo IN (1,2) "
					+ "AND va.vehicles.locations.locationsTypes.lctCodigo = :lctSede "
					+ "AND	va.vhaFechaInicio <= :FechaInicial "
					+ "AND va.vhaFechaDev IS NULL "
					+ "AND va.assignationsStates.assCodigo in (5) order by va.requests.rqsCarnetAsignatario";

			Query query = EntityManagerHelper.getEntityManager().createQuery(
					sql);

			query.setParameter("FechaInicial", FechaInicial);
			query.setParameter("lctSede", ParametersUtil.SEDE);

			bilList = query.getResultList();

			if (bilList != null && bilList.size() > 0) {

				for (Object[] listObj : bilList) {

					String sql_vhaFf = "SELECT vhf.flatFile FROM VhaFf vhf"
							+ " WHERE vhf.vehiclesAssignation.vhaCodigo = :vhaCodigo "
							+ "AND vhf.flatFile.period.perId = :perId";

					query = null;
					query = EntityManagerHelper.getEntityManager().createQuery(
							sql_vhaFf);
					query.setParameter("vhaCodigo", Long.parseLong(listObj[5]
							.toString()));
					query.setParameter("perId", periodo.getPerId());

					List<FlatFile> billList_vhaFf = (List<FlatFile>) query
							.getResultList();

					if (billList_vhaFf == null || billList_vhaFf.size() <= 0) {
						BillingAccountAutoInsuranceVO billingAccountAutoInsurVO = new BillingAccountAutoInsuranceVO();
						billingAccountAutoInsurVO.setCarnet(listObj[1]
								.toString());
						billingAccountAutoInsurVO.setIdVehiculo(new Long(
								listObj[0].toString()));
						billingAccountAutoInsurVO
								.setPlacaDiplomatica(listObj[2].toString());
						if (listObj[3] == null)
							listObj[3] = "";
						if (listObj[4] == null)
							listObj[4] = "";
						billingAccountAutoInsurVO
								.setNombreAsignatario(listObj[3].toString()
										+ " " + listObj[4].toString());
						billingAccountAutoInsurVO.setVhaCodigo(new Long(
								listObj[5].toString()));
						billingAccountAutoInsurVO.setAsignacion(listObj[6]
								.toString());

						SimpleDateFormat formato = new SimpleDateFormat(
								"dd-MMM-yy");
						billingAccountAutoInsurVO.setFechaInicial(formato
								.parse(formato.format(listObj[7])));
						billingAccountAutoInsurVO.setFechaFinal(formato
								.parse(formato.format(listObj[8])));

						bilListVO.add(billingAccountAutoInsurVO);
					}
				}

				return bilListVO;
			} else
				return null;
		} catch (Exception e) {
			log.error("listVehiclesFlatFile", e);
			throw new GWorkException(e.getMessage() + "\n"
					+ e.getLocalizedMessage(), e);
		}
	}

	public List<FlatFile> action_aceptar(
			List<BillingAccountAutoInsuranceVO> vehiculos, String loginUsuario,
			String anoCobro, Period Periodo) throws GWorkException {
		try {
			// Util.limpiarSession();
			Long tipoNovedad = new Long(Util
					.loadParametersValue("BAC_TIPO_NOVEDAD"));

			// Obtener la tarifa actual, de acuerdo al año
			PrepaidFuelProofService ppfps = new PrepaidFuelProofServiceImpl();
			Tariffs tarifa = ppfps.getTarifaActual(Periodo);

			ConsultsServiceImpl consultsServiceImpl = new ConsultsServiceImpl();
			Long tipoNomina = 0L;
			Float tipoConversion = 0F;

			/* obtener valor del autoSeguro mensual */
			Double parametroAutoSeg = new Double(SearchVehicles
					.ConsultarParametroAutoSeguro(new Long(Util
							.loadParametersValue("BAC_ID_PARAM_AUTOSEG"))));
			Double mesesAno = new Double(12);
			Double valorAutoSeguro = parametroAutoSeg / mesesAno;

			/* list de objetos FlatFile */
			List<FlatFile> listaFlatFile = new ArrayList<FlatFile>();
			for (BillingAccountAutoInsuranceVO billingAccountAutoInsurVO : vehiculos) {

				String carnet = "";
				String concepto = "";
				String unidades = "";
				String valor = "";
				String fecha = "";
				String documento = "";
				String centroCosto = "";
				String moneda = "";
				String descripcion = "";

				/*
				 * Calcular el costo de los meses a los cuales el vehiculo fue
				 * asignado para el año correspondiente
				 */

				Calendar calendario_fechaIni = new java.util.GregorianCalendar();
				calendario_fechaIni.setTime(billingAccountAutoInsurVO
						.getFechaInicial());

				Calendar calendario_fechaFin = new java.util.GregorianCalendar();
				calendario_fechaFin.setTime(billingAccountAutoInsurVO
						.getFechaFinal());

				Double sumaAutoSeguro = new Double(0);

				/*
				 * determinar que la fecha inicial sea menor o igual al año
				 * actual
				 */
				if (calendario_fechaIni.get(Calendar.YEAR) <= Integer
						.parseInt(anoCobro)) {
					/*
					 * determinar que la fecha final sea mayor o igual al año
					 * actual
					 */
					if (calendario_fechaFin.get(Calendar.YEAR) >= Integer
							.parseInt(anoCobro)) {

						tipoNomina = consultsServiceImpl
								.counsultarTipoMonedaAsignatario(billingAccountAutoInsurVO
										.getCarnet());

						if (tipoNomina != null) {
							if (tipoNomina.equals(ParametersUtil.DOLAR))
								tipoConversion = 1.0f;
							else {
								tipoConversion = consultsServiceImpl
										.consultarTasaConversion(new Date());
							}
						} else{
							throw new GWorkException(Util
									.loadErrorMessageValue("DATOS.NULL")
									+ " Tipo Nomina para el usuario de carnét: " + 
									billingAccountAutoInsurVO.getCarnet() +
									" y nombre: " + billingAccountAutoInsurVO.getNombreAsignatario().trim());
						}
						
						SearchPlainFileParameter searchPlainFileParameter = new SearchPlainFileParameter();

						// 1L: plain_file_parameter (novelty types)
						PlainFileParameter plainFileParameter = searchPlainFileParameter
								.consultar_PFP_PorId(tipoNomina, tipoNovedad);

						if (plainFileParameter == null)
							throw new GWorkException(Util
									.loadErrorMessageValue("DATOS.NULL")
									+ "Parametros Archivos Planos");

						carnet = billingAccountAutoInsurVO.getCarnet().trim();
						concepto = plainFileParameter.getPfpConceptonomina()
								.toString();
						unidades = "1";

						/* ** Proceso de calculo de meses a pagar** */
						Integer mes_inicial = -1;
						Integer mes_final = -1;

						/*
						 * Si el año de la fecha inicial es menor al año actual,
						 * el mes inicial es igual a enero
						 */
						if (calendario_fechaIni.get(Calendar.YEAR) < Integer
								.parseInt(anoCobro))
							mes_inicial = 0;
						else
							/*
							 * si no (es decir, igual) comienza en el mes
							 * asignado
							 */
							mes_inicial = calendario_fechaIni
									.get(Calendar.MONTH);

						/*
						 * Si el año de la fecha final es mayor al año actual,
						 * el mes final es igual a diciembre
						 */
						if (calendario_fechaFin.get(Calendar.YEAR) > Integer
								.parseInt(anoCobro))
							mes_final = 11;
						else
							/*
							 * si no (es decir, igual) finaliza en el mes
							 * asignado
							 */
							mes_final = calendario_fechaFin.get(Calendar.MONTH);

						/* Se suma el valor del auto seguro (mensual) */
						for (int i = mes_inicial; i <= mes_final; i++)
							sumaAutoSeguro += valorAutoSeguro;

						/* Se obtiene el valor real del cobro del autoseguro */
						valor = Util.redondear((sumaAutoSeguro * Double
								.parseDouble(tipoConversion.toString())), 2)
								+ "";

						String formato = "yyyyMMdd";
						SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
								formato);
						fecha = simpleDateFormat.format(new Date()).toString();

						documento = billingAccountAutoInsurVO
								.getPlacaDiplomatica();
						moneda = tipoNomina.toString();
						descripcion = plainFileParameter.getPfpDescripcion();

						/* Crear Bean FlatFile */
						FlatFile flatFile = new FlatFile();
						flatFile.setFfCarne(carnet);
						flatFile.setFfCentroCosto(centroCosto);
						flatFile.setFfConcepto(new Long(concepto.trim()));
						flatFile.setFfDescripcion(descripcion);
						flatFile.setFfDocumento(documento);
						flatFile.setFfEstado(0L);

						flatFile.setFfFecha(new Long(fecha.trim()));
						flatFile.setFfMoneda(new Long(moneda.trim()));
						flatFile.setFfUnidades(new Long(unidades.trim()));
						if (loginUsuario == null || loginUsuario.length() <= 0)
							loginUsuario = "Cobro Auto Seguro";
						flatFile.setFfUsuario(loginUsuario);
						flatFile.setFfValor(valor);

						PeriodDAO periodDAO = new PeriodDAO();
						NoveltyTypesDAO noveltyTypesDAO = new NoveltyTypesDAO();
						flatFile.setNoveltyTypes(noveltyTypesDAO
								.findById(tipoNovedad));
						flatFile.setPeriod(periodDAO.findById(Periodo
								.getPerId()));
						flatFile.setTariff(tarifa);

						/* Flat File DAO */
						FlatFileDAO flatFileDAO = new FlatFileDAO();

						/* Creación de los datos de la tabla Vha_ff */
						String vhf_observacion = "Cobro Auto Seguro";

						VehiclesAssignationDAO vehiclesAssignationDAO = new VehiclesAssignationDAO();
						VehiclesAssignation vhc_assignation = vehiclesAssignationDAO
								.findById(billingAccountAutoInsurVO
										.getVhaCodigo());

						if (vhc_assignation != null) {

							/* Creación del Bean Vha_ff */
							VhaFf vha_Ff = new VhaFf();
							vha_Ff.setVhfObservacion(vhf_observacion);
							vha_Ff.setFlatFile(flatFile);
							vha_Ff.setVehiclesAssignation(vhc_assignation);

							/* Guardar el objeto en la BD */
							VhaFfDAO vhaFfDAO = new VhaFfDAO();
							flatFileDAO.save(flatFile);
							vhaFfDAO.save(vha_Ff);
						}
						listaFlatFile.add(flatFile);
					}
				}
			}
			EntityManagerHelper.getEntityManager().getTransaction().begin();
			EntityManagerHelper.getEntityManager().getTransaction().commit();
			// Util.limpiarSession();
			return listaFlatFile;
		} catch (GWorkException e) {
			log.error("action_aceptar", e);
			throw new GWorkException(e.getMessage(), e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<Object> consultarIdFF(FlatFile flatFile) throws GWorkException {
		List<Object> listflatFile;
		String sql = "SELECT ff.ffCodigo FROM FlatFile ff"
				+ " WHERE ff.ffCarne = :carne " + "AND ff.ffFecha = :fecha "
				+ "AND ff.ffDocumento = :placaDiplomatica";

		javax.persistence.EntityManager entityAux = EntityManagerHelper
				.getEntityManager();
		Query query = entityAux.createQuery(sql);

		query.setParameter("carne", flatFile.getFfCarne());
		query.setParameter("fecha", flatFile.getFfFecha());
		query.setParameter("placaDiplomatica", flatFile.getFfDocumento());

		listflatFile = query.getResultList();
		if (listflatFile != null && listflatFile.size() > 0)
			return listflatFile;
		else
			return null;
	}

	public Period consultarPeriodByAnoAutoSeguro(String anoCobro)
			throws GWorkException {
		try {
			Date fecha_ini_actual;
			Date fecha_fin_actual;

			Calendar calendario = Calendar.getInstance();

			calendario.set(Integer.parseInt(anoCobro), 0, 1);
			fecha_ini_actual = calendario.getTime();

			calendario.set(Integer.parseInt(anoCobro), 11, 31);
			fecha_fin_actual = calendario.getTime();

			final String sql = "SELECT model FROM Period model"
					+ " WHERE model.perFechaIni = :fecha_ini_actual"
					+ " AND model.perFechaFin = :fecha_fin_actual "
					+ "AND model.noveltyTypes.ntId = :noveltyAutoSure";

			Query query = EntityManagerHelper.getEntityManager().createQuery(
					sql);

			query.setParameter("fecha_ini_actual", fecha_ini_actual);
			query.setParameter("fecha_fin_actual", fecha_fin_actual);
			query.setParameter("noveltyAutoSure",
					ParametersUtil.NOVEDAD_AUTOSEGURO);

			Period objPeriod = (Period) query.getSingleResult();
			if (objPeriod != null)
				return objPeriod;
			else
				throw new GWorkException(Util
						.loadErrorMessageValue("ERROR.NOEXISTEPERIODO"));
		} catch (Exception e) {
			log.error("consultarPeriodByAnoAutoSeguro", e);
			throw new GWorkException(e.getMessage(), e);
		}
	}
}
