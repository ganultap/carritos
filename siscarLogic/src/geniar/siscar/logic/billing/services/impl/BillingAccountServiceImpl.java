package geniar.siscar.logic.billing.services.impl;

import geniar.siscar.consults.impl.ConsultsServiceImpl;
import geniar.siscar.logic.billing.services.BillingAccountService;
import geniar.siscar.logic.consultas.ConsultTariff;
import geniar.siscar.logic.consultas.SearchPeriod;
import geniar.siscar.logic.consultas.SearchPlainFileParameter;
import geniar.siscar.logic.consultas.SearchVehicles;
import geniar.siscar.logic.vehicle.services.AllocationReturnVehicleService;
import geniar.siscar.logic.vehicle.services.impl.AllocationReturnVehicleServiceImpl;
import geniar.siscar.mail.util.SendEmail;
import geniar.siscar.model.BillingAccountVO;
import geniar.siscar.model.FlatFile;
import geniar.siscar.model.Period;
import geniar.siscar.model.PlainFileParameter;
import geniar.siscar.model.Rolls;
import geniar.siscar.model.Tariffs;
import geniar.siscar.model.Vehicles;
import geniar.siscar.model.VehiclesAssignation;
import geniar.siscar.model.VhaFf;
import geniar.siscar.persistence.EntityManagerHelper;
import geniar.siscar.persistence.FlatFileDAO;
import geniar.siscar.persistence.NoveltyTypesDAO;
import geniar.siscar.persistence.PeriodDAO;
import geniar.siscar.persistence.RollsDAO;
import geniar.siscar.persistence.VehiclesAssignationDAO;
import geniar.siscar.persistence.VehiclesDAO;
import geniar.siscar.persistence.VhaFfDAO;
import geniar.siscar.util.ManipulacionFechas;
import geniar.siscar.util.ParametersUtil;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;
import gwork.exception.GworkRuntimeException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class BillingAccountServiceImpl implements BillingAccountService {

	private static final Log log = LogFactory.getLog(BillingAccountServiceImpl.class);
	
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

		EntityManagerHelper.beginTransaction();
		new PeriodDAO().save(period);
		EntityManagerHelper.commit();
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

			EntityManagerHelper.beginTransaction();
			new PeriodDAO().update(period);
			EntityManagerHelper.commit();
		} catch (Exception e) {
			log.error("modificarPeriod",e);
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
			EntityManagerHelper.beginTransaction();
			Period period = new PeriodDAO().findById(id);
			if (period != null) {
				new PeriodDAO().delete(period);
				EntityManagerHelper.commit();
				// Util.limpiarSession();
			}
		} catch (Exception e) {
			EntityManagerHelper.rollback();
			// Util.limpiarSession();
			log.error("eliminarPeriodo",e);
			throw new GWorkException(e.getMessage(), e);
		}
	}

	public Period consultarPeriodById(Long idPeriodo) throws GWorkException {
		return new PeriodDAO().findById(idPeriodo);
	}

	@SuppressWarnings("unchecked")
	public List<Long> listVehicles() throws GWorkException {

		final String sql = "";
		Query query = EntityManagerHelper.getEntityManager().createQuery(sql);

		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<BillingAccountVO> listVehiclesFlatFile(Long per)
			throws GWorkException {
		List<Object[]> bilList = new ArrayList<Object[]>();
		List<BillingAccountVO> bilListVO = new ArrayList<BillingAccountVO>();
		final String sql = "SELECT va.vehicles.vhcCodigo, "
				+ "r.rqsCarnetEmpleado, "
				+ "va.vehicles.vhcPlacaDiplomatica "
				+ "from VehiclesAssignation va "
				+ "JOIN va.requests r "
				+ "WHERE r.legateesTypes.lgtCodigo = 6 "
				+ "AND va.vhaFechaDev IS NULL "
				+ "AND va.vehicles.vhcCodigo NOT IN (SELECT va.vehicles.vhcCodigo "
				+ "from VehiclesAssignation va " + "JOIN va.vhaFfs vff "
				+ "JOIN vff.flatFile ff JOIN ff.period p WHERE p.perId=" + per
				+ ")";

		Query query = EntityManagerHelper.getEntityManager().createQuery(sql);
		bilList = query.getResultList();
		for (Object[] listObj : bilList) {
			BillingAccountVO billingAccountVO = new BillingAccountVO();
			billingAccountVO.setCarnet(listObj[1].toString());
			billingAccountVO.setIdVehiculo(new Long(listObj[0].toString()));
			billingAccountVO.setPlacaDiplomatica(listObj[2].toString());
			bilListVO.add(billingAccountVO);
		}

		return bilListVO;
	}

	public void action_aceptar(List<BillingAccountVO> vehiculos, Long idPerido)
			throws GWorkException, ParseException {

		Long tipoNomina = 0L;
		Float totalAsignacion = 0F;

		for (BillingAccountVO billingAccountVO : vehiculos) {

			String carnet = "";
			String concepto = "234";
			String unidades = "";
			String valor = "";
			String fecha = "";
			String documento = "";
			String centroCosto = "";
			String moneda = "";
			String descripcion = "";

			tipoNomina = 1l;/*
							 * consultsServiceImpl
							 * .counsultarTipoMonedaAsignatario(billingAccountVO
							 * .getCarnet());
							 */

			/*
			 * tipoConversion = 567.8f; consultsServiceImpl
			 * .consultarTasaConversion(new Date());
			 */

			/*
			 * SearchPlainFileParameter searchPlainFileParameter = new
			 * SearchPlainFileParameter();
			 */

			/*
			 * PlainFileParameter plainFileParameter = searchPlainFileParameter
			 * .consultar_PFP_PorId(tipoNomina, 3L);
			 */

			AllocationReturnVehicleService allocationReturnVehicleService = new AllocationReturnVehicleServiceImpl();
			VehiclesDAO vehiclesDAO = new VehiclesDAO();

			Vehicles vehicles = vehiclesDAO.findById(billingAccountVO
					.getIdVehiculo());

			totalAsignacion = allocationReturnVehicleService
					.consultarTarifaAsignacion(vehicles, vehicles
							.getLocations().getLcnCodigo().longValue());

			// Creacion de la estructura del archivo plano
			// Carnet 11
			carnet = billingAccountVO.getCarnet().trim();
			int tam = carnet.length();
			for (int i = 11; i > (11 - tam); i--) {
				carnet += " ";
			}

			// Concepto 4
			// concepto = plainFileParameter.getPfpConceptonomina().toString();
			tam = concepto.length();
			for (int i = 4; i > (4 - tam); i--) {
				concepto += " ";
			}
			// Unidades 4
			unidades = "1 ";

			// Valor 12
			valor = totalAsignacion.toString();
			tam = valor.length();
			for (int i = 12; i > (12 - tam); i--) {
				valor += " ";
			}

			// Fecha 8
			String formato = "yyyyMMdd";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formato);
			fecha = simpleDateFormat.format(new Date()).toString();

			// fecha="20090908";
			tam = fecha.length();
			for (int i = 8; i > (8 - tam); i--) {
				fecha += " ";
			}

			// Documento 10
			documento = billingAccountVO.getPlacaDiplomatica();
			tam = documento.length();
			for (int i = 10; i > (10 - tam); i--) {
				documento += " ";
			}
			// Centro Costo 15
			centroCosto = " ";

			// Moneda 2
			moneda = billingAccountVO.getPlacaDiplomatica();
			tam = moneda.length();
			for (int i = 2; i > (2 - tam); i--) {
				moneda += " ";
			}

			// Descripcion 35
			descripcion = billingAccountVO.getPlacaDiplomatica();
			tam = descripcion.length();
			for (int i = 35; i > (35 - tam); i--) {
				descripcion += " ";
			}

			FlatFile flatFile = new FlatFile();
			flatFile.setFfCarne(carnet);
			flatFile.setFfCentroCosto(centroCosto);
			flatFile.setFfConcepto(new Long(concepto.trim()));
			flatFile.setFfDescripcion(descripcion.trim());
			flatFile.setFfDocumento(documento.trim());

			flatFile.setFfFecha(new Long(fecha.trim()));
			// flatFile.setFfMoneda(new Long(moneda.trim()));
			flatFile.setFfMoneda(10L);
			flatFile.setFfUnidades(new Long(unidades.trim()));
			flatFile.setFfUsuario("usuario");
			flatFile.setFfValor(valor);

			PeriodDAO periodDAO = new PeriodDAO();
			NoveltyTypesDAO noveltyTypesDAO = new NoveltyTypesDAO();
			flatFile.setNoveltyTypes(noveltyTypesDAO.findById(tipoNomina));
			flatFile.setPeriod(periodDAO.findById(idPerido));

			// Flat File DAO
			FlatFileDAO flatFileDAO = new FlatFileDAO();

			// flag que indica que se puede continuar con el proceso
			boolean procesoCompletado = false;
			try {
				// Util.validarSession();
				EntityManagerHelper.beginTransaction();
				flatFileDAO.save(flatFile);
				EntityManagerHelper.commit();
				// Util.limpiarSession();

				/*
				 * Crea la nueva fila para el archivo plano. Se ejecuta la
				 * creacion de la linea dentro del bloque try, con el fin de que
				 * si existe problema con el registro flatFile, la línea no se
				 * adjunte en el archivo
				 */
				/*
				 * linea += carnet + concepto + unidades + valor + fecha +
				 * documento + centroCosto + moneda + descripcion + "\n";
				 */

				procesoCompletado = true;
			} catch (Exception e) {
				log.error("action_aceptar",e);
				EntityManagerHelper.rollback();
				// Util.limpiarSession();
			}

			if (procesoCompletado) {
				/* Creación de los datos de la tabla Vha_ff */
				String vhf_observacion = "Observacion de prueba";
				Long vhf_codigo = new Long(1);

				// Obtener la asignacion del vehiculo
				VehiclesAssignationDAO vhcAssigDAO = new VehiclesAssignationDAO();
				VehiclesAssignation vhc_assignation = vhcAssigDAO
						.findById(billingAccountVO.getIdVehiculo());

				if (vhc_assignation != null) {
					// Creación del Bean Vha_ff
					VhaFf vha_Ff = new VhaFf();
					vha_Ff.setVhfCodigo(vhf_codigo);
					vha_Ff.setVhfObservacion(vhf_observacion);
					vha_Ff.setFlatFile(flatFile);
					vha_Ff.setVehiclesAssignation(vhc_assignation);

					// Guardar el objeto en la BD
					VhaFfDAO vhaFfDAO = new VhaFfDAO();
					try {
						// Util.validarSession();
						EntityManagerHelper.beginTransaction();
						vhaFfDAO.save(vha_Ff);
						EntityManagerHelper.commit();
						// Util.limpiarSession();
					} catch (Exception e) {
						// Limpiar la anterior operación
						log.error("action_aceptar",e);
						EntityManagerHelper.rollback();

						// Se revierte la inserción del flatFile en caso de
						// error
						try {
							// Util.validarSession();
							EntityManagerHelper.beginTransaction();
							flatFileDAO.delete(flatFile);
							EntityManagerHelper.commit();
							// Util.limpiarSession();
						} catch (Exception exe) {
							// Util.limpiarSession();
							log.error("action_aceptar",exe);
							EntityManagerHelper.rollback();
						}
					}
				} else {
					// borrado del registro en la tabla flatfile
					try {
						Util.validarSession();
						EntityManagerHelper.beginTransaction();
						flatFileDAO.delete(flatFile);
						EntityManagerHelper.commit();
						// Util.limpiarSession();
					} catch (Exception exe) {
						// Util.limpiarSession();
						log.error("action_aceptar",exe);
						EntityManagerHelper.rollback();
					}
				}
			}

		}
	}

	// COBRO CIAT CASA CIAT

	/*
	 * este metodo es el que hace el llamado el metodo que consulta el listado
	 * de vehiculos para crear el archivo de CiatCasaCiat
	 */

	public void CobroCiatCasaCiat() throws GWorkException {
		ListarVehiculosCiatCasaCiat();
	}

	/*
	 * este metodo es el que muestra el listado de vehiculos que esten en un
	 * periodo mensual que tengan como parametro de tipo ciat casa ciat
	 */

	public void ListarVehiculosCiatCasaCiat() throws GWorkException {
		try {
			Date dtFechaInicio;
			Date dtFechaFin;
			Long idPeriodo = 1L;

			dtFechaInicio = ManipulacionFechas
					.getMesAnterior(ManipulacionFechas.getFechaActual());

			// Integer mes = Integer.valueOf(ManipulacionFechas
			// .getMes(ManipulacionFechas.getFechaActual()));
			Calendar calendario = Calendar.getInstance();

			calendario.setTime(dtFechaInicio);
			calendario.set(Calendar.DAY_OF_MONTH, 5);
			// calendario.set(Calendar.MONTH, mes - 2);

			dtFechaInicio = calendario.getTime();

			dtFechaFin = ManipulacionFechas.getFechaActual();
			calendario.setTime(dtFechaFin);
			calendario.set(Calendar.DAY_OF_MONTH, 4);

			dtFechaFin = calendario.getTime();

			List<BillingAccountVO> vehiculos = listVehiclesFlatFileCiatCasaCiat(
					dtFechaInicio, dtFechaFin);

			ReporteCobroCiatCasaCiat(vehiculos, idPeriodo);
			EntityManagerHelper.getEntityManager().getTransaction().begin();
			EntityManagerHelper.getEntityManager().getTransaction().commit();
		} catch (RuntimeException re) {
			log.error("ListarVehiculosCiatCasaCiat",re);
			throw new GworkRuntimeException("[INFO] - " + re.getMessage(), re);
		} catch (Exception e) {
			log.error("ListarVehiculosCiatCasaCiat",e);
			throw new GWorkException(
					"No se pudo generar el comprobante [ERROR] - "
							+ e.getMessage(), e);

		}
	}

	@SuppressWarnings("unchecked")
	public List<BillingAccountVO> listVehiclesFlatFileCiatCasaCiat(
			Date dtfechaInicio, Date dtfechaFin) throws GWorkException {
		try {
			List<Object[]> bilList = new ArrayList<Object[]>();
			List<BillingAccountVO> bilListVO = new ArrayList<BillingAccountVO>();

			String sql = "SELECT va.vehicles.vhcCodigo, "
					+ "r.rqsCarnetEmpleado, "
					+ "va.vehicles.vhcPlacaDiplomatica "
					+ "FROM VehiclesAssignation va "
					+ "JOIN va.requests r "
					+ "WHERE r.legateesTypes.lgtCodigo IN (1,2) "
					+ "AND va.vhaCobroCasaciat = 'S' "
					+ "AND r.requestsClasses.rqcCodigo = :codigo "
					+ "AND va.vhaFechaDev IS NULL "
					+ "AND va.vehicles.vhcCodigo NOT IN (SELECT va.vehicles.vhcCodigo "
					+ "FROM VehiclesAssignation va JOIN va.vhaFfs vff "
					+ "JOIN vff.flatFile ff JOIN ff.period p "
					+ "WHERE p.perFechaIni = :dtfechaInicio "
					+ "AND p.perFechaFin = :dtfechaFin )";

			Query query = EntityManagerHelper.getEntityManager().createQuery(
					sql);
			query.setParameter("codigo", new Long(1));
			query.setParameter("dtfechaInicio", dtfechaInicio);
			query.setParameter("dtfechaFin", dtfechaFin);
			bilList = query.getResultList();

			for (Object[] listObj : bilList) {

				BillingAccountVO billingAccountVO = new BillingAccountVO();
				billingAccountVO.setCarnet(listObj[1].toString());
				billingAccountVO.setIdVehiculo(new Long(listObj[0].toString()));
				billingAccountVO.setPlacaDiplomatica(listObj[2].toString());
				bilListVO.add(billingAccountVO);
			}

			return bilListVO;
		} catch (RuntimeException e) {
			log.error("listVehiclesFlatFileCiatCasaCiat",e);
			throw new GWorkException(Util
					.loadErrorMessageValue("search.not.found"), e);
		}
	}

	public void ReporteCobroCiatCasaCiat(List<BillingAccountVO> vehiculos,
			Long idPerido) throws GWorkException {

		String ffCarne = null;
		Long ffConcepto = null;
		Long ffUnidades = null;
		String ffValor = null;
		String ffFecha = null;
		String ffDocumento = null;
		String ffDescripcion = null;
		String ffCentroCosto = null;
		Period period = null;
		Long TipoNomina = 0L;
		String PlacaDiplomatica;
		String Carnet;
		Long ffEstado = 0L;

		PlainFileParameter plainFileParameter = new PlainFileParameter();
		PeriodDAO periodDAO = new PeriodDAO();
		NoveltyTypesDAO noveltyTypesDAO = new NoveltyTypesDAO();
		ConsultsServiceImpl consultsServiceImpl = new ConsultsServiceImpl();
		ConsultTariff consultaTarifa = new ConsultTariff();
		String formato = "yyyyMMdd";
		Tariffs tariff = null;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formato);
		Long zona = null;
		for (BillingAccountVO billingAccountVO : vehiculos) {
			FlatFile flatFile = new FlatFile();
			/*
			 * se consulta a la tabla de vehiculos asignacion por placa
			 * diplomatica para obtener la tarifa de ciat casa ciat
			 */

			PlacaDiplomatica = billingAccountVO.getPlacaDiplomatica()
					.toUpperCase();
			VehiclesAssignation vehiclesAssignation = SearchVehicles
					.consultarAsignacionVehiculo(PlacaDiplomatica);
			
			
			if (vehiclesAssignation != null
					&& vehiclesAssignation.getRequests() != null) {

				Carnet = vehiclesAssignation.getVhaNumeroCarne();
				ffUnidades = 1L;

				//TODO Esta es la consulta que trae el tipo de nomina de la persona
				TipoNomina = consultsServiceImpl
						.counsultarTipoMonedaAsignatario(Carnet);

				/*
				 * se consultan los parametros del archivo plano para ciat casa
				 * ciat
				 */
				plainFileParameter = new SearchPlainFileParameter()
						.consultar_PFP_PorId(TipoNomina,
								ParametersUtil.TRASACCTION_TYPE_CIAT_CASA_CIAT);

				ffCarne = Carnet;
				if (plainFileParameter != null)
					ffConcepto = plainFileParameter.getPfpConceptonomina();
				else
					ffConcepto = 0L;

				if (vehiclesAssignation.getRequests().getZones() != null) {
					zona = vehiclesAssignation.getRequests().getZones()
							.getZnsId();

					/* se consulta el valor de la tarifa de ciat casa ciat */
					tariff = consultaTarifa.consultarTarifaActualCiatCasaCiat(
							zona, TipoNomina);
					if (tariff != null) {
						if (tariff.getTrfValor() != null) {
							ffValor = tariff.getTrfValor().toString();
						} else {
							ffValor = "0";
						}
					}
					
					//TODO Se consulta el tipo de nomina de la persona, en caso de no tener se genera una notificación
					String emailAsignatario = vehiclesAssignation.getRequests()
							.getUsersByRqsUser().getUsrEmail();
					if (TipoNomina == null || TipoNomina == 0L)
						notificacionUsuarioSinNomina(ffCarne, PlacaDiplomatica,
								new Date(), zona, emailAsignatario);
					
					ffFecha = simpleDateFormat.format(new Date()).toString();
					ffDocumento = PlacaDiplomatica;
					if (plainFileParameter != null)
						ffDescripcion = plainFileParameter.getPfpDescripcion();
					else
						ffDescripcion = "";

					period = periodDAO.findById(idPerido);
					flatFile.setFfCarne(ffCarne);
					flatFile.setFfConcepto(new Long(ffConcepto));
					flatFile.setFfUnidades(ffUnidades);
					flatFile.setFfValor(ffValor);
					flatFile.setFfFecha(Long.valueOf(ffFecha));
					flatFile.setFfDocumento(ffDocumento);
					flatFile.setFfMoneda(TipoNomina);
					flatFile.setFfDescripcion(ffDescripcion);
					flatFile.setFfCentroCosto(ffCentroCosto);
					flatFile.setFfUsuario("SISCAR CIAT CASA CIAT");
					flatFile
							.setNoveltyTypes(noveltyTypesDAO
									.findById(ParametersUtil.TRASACCTION_TYPE_CIAT_CASA_CIAT));
					flatFile.setPeriod(period);
					flatFile.setFfEstado(ffEstado);
					flatFile.setTariff(tariff);

					if (flatFile.getFfMoneda() != null) {
						FlatFileDAO FlatFileDao = new FlatFileDAO();
						try {

							FlatFileDao.save(flatFile);
						} catch (Exception e) {
							e.printStackTrace();
							throw new GWorkException(Util
									.loadErrorMessageValue("ERROR.GUARDAR"));
						}

						/* Creación de los datos de la tabla Vha_ff */
						String vhf_observacion = "Cobro Ciat Casa Ciat";

						/* Creación del Bean Vha_ff */
						VhaFf vha_Ff = new VhaFf();
						vha_Ff.setVhfObservacion(vhf_observacion);
						vha_Ff.setFlatFile(flatFile);
						vha_Ff.setVehiclesAssignation(vehiclesAssignation);

						/* Guardar el objeto en la BD */
						try {
							new VhaFfDAO().save(vha_Ff);
						} catch (Exception e) {
							log.error("ReporteCobroCiatCasaCiat",e);
							throw new GWorkException(Util
									.loadErrorMessageValue("ERROR.GUARDAR"), e);
						}
					}
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	public boolean ConsultarFlatFileCiatCasaCiat(Long FechaInicio, Long FechaFin)
			throws GWorkException {
		try {
			List<Object[]> bilList = null;

			String sql = "SELECT ff FROM VehiclesAssignation va JOIN va.vhaFfs vff "
					+ "JOIN vff.flatFile ff "
					+ "WHERE ff.ffFecha BETWEEN "
					+ FechaInicio
					+ " AND "
					+ FechaFin
					+ " AND ff.ffEstado = 1 AND ff.noveltyTypes.ntId = 4";

			Query query = EntityManagerHelper.getEntityManager().createQuery(
					sql);

			bilList = query.getResultList();

			if (bilList != null && bilList.size() > 0) {
				return true;
			} else
				return false;
		} catch (RuntimeException e) {
			log.error("ConsultarFlatFileCiatCasaCiat",e);
			throw new GWorkException(Util
					.loadErrorMessageValue("search.not.found"), e);
		}
	}

	public SendEmail notificacionUsuarioSinNomina(String carne, String placa,
			Date fecha, Long zona, String emailAsignatario)
			throws GWorkException {

		try {

			// /**
			// * notificacion para el administrador del POOL
			// */
			Rolls rolls = new RollsDAO().findById(new Long(Util
					.loadParametersValue("MAIL.ADMINISTRADOR")));

			String server = Util.loadParametersValue("MAIL.HOST");
			String fechaFormato = new SimpleDateFormat("dd-MMM-yyyy")
					.format(fecha);
			String periodo = ManipulacionFechas.getMes(fecha);

			/* se consulta el valor de la tarifa de ciat casa ciat */
			Tariffs tariffPeso = new ConsultTariff()
					.consultarTarifaActualCiatCasaCiat(zona,
							ParametersUtil.PESOS);

			Tariffs tariffDolar = new ConsultTariff()
					.consultarTarifaActualCiatCasaCiat(zona,
							ParametersUtil.DOLAR);

			Double valorPeso = (tariffPeso.getTrfValor() == null) ? 0D
					: tariffPeso.getTrfValor();
			Double valorDolar = (tariffDolar.getTrfValor() == null) ? 0D
					: tariffDolar.getTrfValor();

			String fromUser = rolls.getRlsMail();
			String toUser = fromUser;
			String emailCC = Util.loadParametersValue("EMAIL_CC_CXC") + ";"
					+ emailAsignatario.toLowerCase();
			String subject = Util.loadParametersValue("SUBJECT_KM_CIAT");
			String nombre = new ConsultsServiceImpl()
					.consultEmpleoyeeName(carne);

			StringBuffer mensaje = new StringBuffer();
			mensaje = mensaje.append(
					Util.loadParametersValue("BODY_MSG_KM_CIAT")).append(
					"<br><br>").append("Placa: ").append(placa).append("<br>")
					.append("Nombre: ").append(nombre).append("<br>").append(
							"Carné: ").append(carne).append("<br>").append(
							"Valor en pesos (COP): ").append(
							Util.doubleToStringLocale(valorPeso, 2)).append(
							"<br>").append("Valor en dólares (USD): ").append(
							Util.doubleToStringLocale(valorDolar, 2)).append(
							"<br>").append("Fecha: ").append(fechaFormato)
					.append("<br>").append("Período: ").append(periodo).append(
							"<br><br><br>").append("Nota: ").append(
							Util.loadParametersValue("BODY_MSG_KM_CIAT_NOTA"));

			SendEmail mail = new SendEmail(toUser, emailCC, fromUser, server,
					"false", subject, mensaje.toString());
			mail.SendMessage();
			return mail;

		} catch (Exception e) {
			log.error("ConsultarFlatFileCiatCasaCiat", e);
			throw new GWorkException(Util
					.loadErrorMessageValue("NOTIFICACION.ERROR")
					+ " " + e.getMessage());
		}
	}
}
