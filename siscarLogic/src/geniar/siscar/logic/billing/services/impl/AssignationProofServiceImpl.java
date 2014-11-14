package geniar.siscar.logic.billing.services.impl;

import geniar.siscar.consults.impl.ConsultsServiceImpl;
import geniar.siscar.logic.billing.services.AssignationProofService;
import geniar.siscar.logic.billing.services.GenerateProofService;
import geniar.siscar.logic.consultas.SearchAccountingParameters;
import geniar.siscar.logic.consultas.SearchCostCenters;
import geniar.siscar.logic.consultas.SearchVehicles;
import geniar.siscar.logic.fuels.services.impl.SearchPrepaidServiceImpl;
import geniar.siscar.logic.parameters.services.TariffService;
import geniar.siscar.logic.parameters.services.impl.TariffServiceImpl;
import geniar.siscar.model.AccountingParameters;
import geniar.siscar.model.ActualOthersApplications;
import geniar.siscar.model.CostCentersFuel;
import geniar.siscar.model.CostsCentersVehicles;
import geniar.siscar.model.HeaderProof;
import geniar.siscar.model.Period;
import geniar.siscar.model.Tariffs;
import geniar.siscar.model.VOAssignationProof;
import geniar.siscar.model.Vehicles;
import geniar.siscar.model.VehiclesAssignation;
import geniar.siscar.model.VhaAoaApp;
import geniar.siscar.persistence.ActualOthersApplicationsDAO;
import geniar.siscar.persistence.CostsCentersVehiclesDAO;
import geniar.siscar.persistence.EntityManagerHelper;
import geniar.siscar.persistence.PeriodDAO;
import geniar.siscar.persistence.VehiclesAssignationDAO;
import geniar.siscar.persistence.VhaAoaAppDAO;
import geniar.siscar.util.ManipulacionFechas;
import geniar.siscar.util.ParametersUtil;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

import java.math.BigDecimal;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * The Class AssignationProofServiceImpl.
 */
public class AssignationProofServiceImpl implements AssignationProofService {

	/** The Constant log. */
	private static final Log log = LogFactory.getLog(AssignationProofServiceImpl.class);
	
	/* (non-Javadoc)
	 * @see geniar.siscar.logic.billing.services.AssignationProofService#listComprobanteAsignacion(java.lang.Long)
	 */
	public List<VOAssignationProof> listComprobanteAsignacion(Long periodo)
			throws GWorkException {

		List<CostsCentersVehicles> listAssignations = SearchParametersBilling
				.listAsginacionesByPeriod();

		if (listAssignations == null || listAssignations.size() == 0)
			throw new GWorkException(Util
					.loadErrorMessageValue("CONTABILIZAR.NULO"));

		log.info("Lista Inicial: " + listAssignations.size());
		
		return asignacionesCombrobante(listAssignations, periodo);

	}

	/* (non-Javadoc)
	 * @see geniar.siscar.logic.billing.services.AssignationProofService#consultarVigenciaCentroCosto(java.lang.String, java.util.Date, java.util.Date)
	 */
	public String consultarVigenciaCentroCosto(String centroCosto,
			Date fechaIni, Date fechaFin) throws GWorkException {

		String centroCostoActivo = CheckCostCenterForce
				.consultarVigenciaCentroCosto(centroCosto, fechaIni, fechaFin);

		return centroCostoActivo;
	}

	/* (non-Javadoc)
	 * @see geniar.siscar.logic.billing.services.AssignationProofService#disponibilidadCombustibleCC(java.lang.String, java.lang.String, java.lang.String, geniar.siscar.model.AccountingParameters)
	 */
	public String disponibilidadCombustibleCC(String centroCosto,
			String valorPorcentaje, String placa,
			AccountingParameters parameters) throws GWorkException {

		String disponibilidadCC = CheckCostCenterForce
				.disponibilidadCombustibleCC(centroCosto, valorPorcentaje,
						placa, parameters);

		return disponibilidadCC;
	}

	/**
	 * *Metodo que convierte la consulta avanzada de las asginaciones pendientes
	 * para generar combrobante en una lista de asiganaciones para el
	 * comprobante de asignacion*.
	 *
	 * @param listAsignaciones the list asignaciones
	 * @param idPeriodo the id periodo
	 * @return the list
	 * @throws GWorkException the g work exception
	 */
	public List<VOAssignationProof> asignacionesCombrobante(
			List<CostsCentersVehicles> listAsignaciones, Long idPeriodo)
			throws GWorkException {

		try {

			List<VOAssignationProof> lisVOAssignation = new ArrayList<VOAssignationProof>();
			List<AccountingParameters> listAccountingParameters = new ArrayList<AccountingParameters>();
			CostsCentersVehiclesDAO objCostsCentersVehiclesDAO = new CostsCentersVehiclesDAO();
			Period period = new PeriodDAO().findById(idPeriodo);

			Date fechaPerIni = period.getPerFechaIni();
			Date fechaPerFin = period.getPerFechaFin();

			// calcula el numero de meses del periodo
			Long numMesesPeriodo = ManipulacionFechas
					.calculaTiempoTranascurridoMeses(fechaPerIni, fechaPerFin);
			
			for (CostsCentersVehicles costsCentersVehicles : listAsignaciones) {
				
				CostsCentersVehicles objCostsCentersVehicles = objCostsCentersVehiclesDAO.findById(costsCentersVehicles.getCcrCodigo()); 
				
				VehiclesAssignation objVehiclesAssignation = objCostsCentersVehicles.getVehiclesAssignation();
				
//				VehiclesAssignation objVehiclesAssignation = objVehiclesAssignationDAO
//						.findById(costsCentersVehicles.getVehiclesAssignation()
//								.getVhaCodigo());
				
				Date fechaEntrega = objVehiclesAssignation.getVhaFechaEntrega();
				Date fechaAsigFin = objVehiclesAssignation.getVhaFechaTermina();
				
//				Date fechaEntrega = costsCentersVehicles
//						.getVehiclesAssignation().getVhaFechaEntrega();
//				Date fechaAsigFin = costsCentersVehicles
//						.getVehiclesAssignation().getVhaFechaTermina();

				// valida que la asignacion tenga vigencia
				if (ManipulacionFechas.comparaFechas(fechaAsigFin, fechaPerIni) != true
						&& SearchParametersBilling.validarPeriodoAsignacion(
								period.getPerId(), costsCentersVehicles
										.getVehiclesAssignation().getVhaCodigo(),
								ParametersUtil.PROOF_TYPE_ASIGNACION) != true
						&& objVehiclesAssignation.getVhaCobro().equalsIgnoreCase("S")
						&& objVehiclesAssignation.getVhaFechaInicio().compareTo(
										period.getPerFechaFin()) < 0) {

					VOAssignationProof assignationProof = new VOAssignationProof();
					String observaciones = null;
					Double valorAsignacion = 0D;
					Double valorAutoseguro = 0D;
					Double valorDepreciacion = 0D;
					Double valorMantenimiento = 0D;
					Double valorEspacioFisco = 0D;
					Long diasAsignacion = null;
					BigDecimal valorCif = null;
					BigDecimal vidaUtil = null;
					Double valorOutposted = null;
					Double porcentajeCC = Double.valueOf(costsCentersVehicles.getCcrPorcentaje()) / 100D;
					AccountingParameters parameters = null;
					VehiclesAssignation vehiclesAssignation = null;

					Vehicles vehicles = SearchVehicles
							.consultarVehiculosPorPlacaSinFiltros(objVehiclesAssignation
									.getVehicles().getVhcPlacaDiplomatica());

					vehiclesAssignation = SearchVehicles
							.consultarAsignacionVehiculo(objVehiclesAssignation
									.getVehicles().getVhcPlacaDiplomatica());
					
					SearchAccountingParameters searchAccountingParameters = new SearchAccountingParameters();
					
					listAccountingParameters = searchAccountingParameters
							.consultarParametrizacionContableActivos(
									vehiclesAssignation.getRequests()
									.getLegateesTypes()
									.getLgtCodigo(), 
									ParametersUtil.COMPRANTE_ASIGNACION,
									ParametersUtil.CREDITO, 
									vehicles.getLocations().getLocationsTypes().getLctCodigo());
					
					if(listAccountingParameters != null && listAccountingParameters.size() > 0){
						for (AccountingParameters accountingParameters : listAccountingParameters) {
							if(accountingParameters.getChargeType().getCgtId().longValue() == 
								ParametersUtil.CARGO_AUTOSEGURO.longValue()){
								
								TariffService tariffService = new TariffServiceImpl();
								
								Tariffs tariffs = tariffService.consultarTarifaAsignacion(
										vehicles.getLines().getLnsId().longValue(),
										vehicles.getFuelsTypes().getFutCodigo().longValue(),
										vehicles.getTractionsTypes().getTctCodigo().longValue(),
										ParametersUtil.TARIFA_AUTOSEGURO);
								
								valorAutoseguro = Util.redondear(tariffs.getTrfValor().doubleValue(),2) * porcentajeCC;
								
							}else if(accountingParameters.getChargeType().getCgtId().longValue() == 
								ParametersUtil.CARGO_DEPRECIACION.longValue()){
								
								TariffService tariffService = new TariffServiceImpl();
								
								Tariffs tariffs = tariffService.consultarTarifaAsignacion(
										vehicles.getLines().getLnsId().longValue(),
										vehicles.getFuelsTypes().getFutCodigo().longValue(),
										vehicles.getTractionsTypes().getTctCodigo().longValue(),
										ParametersUtil.TARIFA_DEPRECIACION);
								
								valorDepreciacion = Util.redondear(tariffs.getTrfValor().doubleValue(),2) * porcentajeCC;
								
							}else if(accountingParameters.getChargeType().getCgtId().longValue() == 
								ParametersUtil.CARGO_MANTENIMIENTO.longValue()){
								
								TariffService tariffService = new TariffServiceImpl();
								
								Tariffs tariffs = tariffService.consultarTarifaAsignacion(
										vehicles.getLines().getLnsId().longValue(),
										vehicles.getFuelsTypes().getFutCodigo().longValue(),
										vehicles.getTractionsTypes().getTctCodigo().longValue(),
										ParametersUtil.TARIFA_MANTENIMIENTO);
								
								valorMantenimiento = Util.redondear(tariffs.getTrfValor().doubleValue(),2) * porcentajeCC;
								
							}else if(accountingParameters.getChargeType().getCgtId().longValue() == 
								ParametersUtil.CARGO_ESPACIO_FISICO.longValue()){
								
								TariffService tariffService = new TariffServiceImpl();
								
								Tariffs tariffs = tariffService.consultarTarifaAsignacion(
										vehicles.getLines().getLnsId().longValue(),
										vehicles.getFuelsTypes().getFutCodigo().longValue(),
										vehicles.getTractionsTypes().getTctCodigo().longValue(),
										ParametersUtil.TARIFA_ASIGNACION_ESPACIO_FISICO);
								
								valorEspacioFisco = Util.redondear(tariffs.getTrfValor().doubleValue(),2)* porcentajeCC;
							}
						}
					}
					
					valorAsignacion = valorAutoseguro + valorDepreciacion + valorMantenimiento + valorEspacioFisco;
					
					assignationProof.setIdAsignacion(objVehiclesAssignation.getVhaCodigo());
					assignationProof.setPlaca(objVehiclesAssignation.getVehicles().getVhcPlacaDiplomatica());
					assignationProof.setCentroCosto(costsCentersVehicles.getCostsCenters().getCocNumero());
					assignationProof.setNombreAsignatario(objVehiclesAssignation.getRequests().getUsersByRqsUser().getUsrNombre());
					assignationProof.setTipoAsignacion(objVehiclesAssignation.getRequests().getLegateesTypes().getLgtNombre());
					assignationProof.setFechaIni(fechaEntrega);
					assignationProof.setFechaFin(fechaAsigFin);

					// Verifica si la fecha de entrega de la asigancion es menor
					// a la fecha inicial del periodo y si la fecha final del period
					// es menor a la fecha final de la asignacion
					if (fechaEntrega.compareTo(fechaPerIni) <= 0
							&& fechaPerFin.compareTo(fechaAsigFin) <= 0) {

						if (vehicles.getLocations().getLocationsTypes()
								.getLctCodigo().longValue() == ParametersUtil.EXTERIORES.longValue()) {

							valorCif = new ConsultsServiceImpl().consultFuntions(vehicles.getVhcPlacaActivoFijo(), 1);

							if (valorCif == null)
								throw new GWorkException(Util.loadErrorMessageValue("VALORCIF.EXISTEN")
												+ vehicles.getVhcPlacaDiplomatica());

							vidaUtil = new ConsultsServiceImpl().consultFuntions(vehicles.getVhcPlacaActivoFijo(), 4);

							if (vidaUtil == null)
								throw new GWorkException(Util.loadErrorMessageValue("VIDAUTIL.EXISTEN")
											+ vehicles.getVhcPlacaDiplomatica());

							valorOutposted = valorCif.doubleValue() / (vidaUtil.longValue() * 12);
							valorOutposted = (valorOutposted * numMesesPeriodo) * porcentajeCC;

							assignationProof.setValorAsignacion(Util.redondear(valorOutposted, 2));
							assignationProof.setValorDepreciacion(Util.redondear(valorOutposted, 2));
						} else {
							valorAutoseguro = valorAutoseguro * numMesesPeriodo;
							valorDepreciacion = valorDepreciacion * numMesesPeriodo;
							valorMantenimiento = valorMantenimiento * numMesesPeriodo;
							valorEspacioFisco = valorEspacioFisco * numMesesPeriodo;
							valorAsignacion = valorAsignacion * numMesesPeriodo;

							assignationProof.setValorAsignacion(Util.redondear(valorAsignacion, 2));
							assignationProof.setValorAutoseguro(Util.redondear(valorAutoseguro, 2));
							assignationProof.setValorDepreciacion(Util.redondear(valorDepreciacion, 2));
							assignationProof.setValorEspacioFisico(Util.redondear(valorEspacioFisco, 2));
							assignationProof.setValorMantenimiento(Util.redondear(valorMantenimiento, 2));							
						}
					} else if (fechaEntrega.compareTo(fechaPerIni) >= 0
							&& fechaAsigFin.compareTo(fechaPerFin) <= 0) {
						diasAsignacion = (Util.compararNumeroDiasFechas(fechaAsigFin, fechaEntrega)) + 1L;

						if (vehicles.getLocations().getLocationsTypes()
								.getLctCodigo().longValue() == ParametersUtil.EXTERIORES.longValue()) {

							valorCif = new ConsultsServiceImpl().consultFuntions(
									vehicles.getVhcPlacaActivoFijo(), 1);

							if (valorCif == null)
								throw new GWorkException(
										Util.loadErrorMessageValue("VALORCIF.EXISTEN")
												+ vehicles.getVhcPlacaDiplomatica());

							vidaUtil = new ConsultsServiceImpl().consultFuntions(vehicles.getVhcPlacaActivoFijo(), 4);

							if (vidaUtil == null)
								throw new GWorkException(
										Util.loadErrorMessageValue("VIDAUTIL.EXISTEN")
												+ vehicles.getVhcPlacaDiplomatica());

							valorOutposted = valorCif.doubleValue() / (vidaUtil.longValue() * 12);
							valorOutposted = (valorOutposted / 30) * diasAsignacion * porcentajeCC;

							assignationProof.setValorAsignacion(Util.redondear(valorOutposted, 2));
							assignationProof.setValorDepreciacion(Util.redondear(valorOutposted, 2));
						} else {
							valorAutoseguro = (valorAutoseguro / 30) * diasAsignacion;
							valorDepreciacion = (valorDepreciacion / 30) * diasAsignacion;
							valorMantenimiento = (valorMantenimiento / 30) * diasAsignacion;
							valorEspacioFisco = (valorEspacioFisco / 30) * diasAsignacion;
							valorAsignacion = (valorAsignacion / 30) * diasAsignacion;

							assignationProof.setValorAsignacion(Util.redondear(valorAsignacion, 2));
							assignationProof.setValorAutoseguro(Util.redondear(valorAutoseguro, 2));
							assignationProof.setValorDepreciacion(Util.redondear(valorDepreciacion, 2));
							assignationProof.setValorEspacioFisico(Util.redondear(valorEspacioFisco, 2));
							assignationProof.setValorMantenimiento(Util.redondear(valorMantenimiento, 2));							
						}
					} else if (fechaEntrega.compareTo(fechaPerIni) <= 0
							&& fechaAsigFin.compareTo(fechaPerFin) < 0) {
						
						diasAsignacion = (Util.compararNumeroDiasFechas(fechaAsigFin, fechaPerIni)) + 1L;
						if (vehicles.getLocations().getLocationsTypes().getLctCodigo().longValue() == ParametersUtil.EXTERIORES.longValue()) {

							valorCif = new ConsultsServiceImpl().consultFuntions(vehicles.getVhcPlacaActivoFijo(), 1);

							if (valorCif == null)
								throw new GWorkException(Util.loadErrorMessageValue("VALORCIF.EXISTEN")
												+ vehicles.getVhcPlacaDiplomatica());

							vidaUtil = new ConsultsServiceImpl().consultFuntions(vehicles.getVhcPlacaActivoFijo(), 4);

							if (vidaUtil == null)
								throw new GWorkException(Util.loadErrorMessageValue("VIDAUTIL.EXISTEN")
											+ vehicles.getVhcPlacaDiplomatica());
							
							valorOutposted = valorCif.doubleValue() / (vidaUtil.longValue() * 12);
							valorOutposted = (valorOutposted / 30) * diasAsignacion * porcentajeCC;

							assignationProof.setValorAsignacion(Util.redondear(valorOutposted, 2));
							assignationProof.setValorDepreciacion(Util.redondear(valorOutposted, 2));
						} else {
							valorAutoseguro = (valorAutoseguro / 30) * diasAsignacion;
							valorDepreciacion = (valorDepreciacion / 30) * diasAsignacion;
							valorMantenimiento = (valorMantenimiento / 30) * diasAsignacion;
							valorEspacioFisco = (valorEspacioFisco / 30) * diasAsignacion;
							valorAsignacion = (valorAsignacion / 30) * diasAsignacion;
							
							assignationProof.setValorAsignacion(Util.redondear(valorAsignacion, 2));
							assignationProof.setValorAutoseguro(Util.redondear(valorAutoseguro, 2));
							assignationProof.setValorDepreciacion(Util.redondear(valorDepreciacion, 2));
							assignationProof.setValorEspacioFisico(Util.redondear(valorEspacioFisco, 2));
							assignationProof.setValorMantenimiento(Util.redondear(valorMantenimiento, 2));
						}						
					} else if (fechaEntrega.compareTo(fechaPerIni) > 0
							&& fechaAsigFin.compareTo(fechaPerFin) >= 0) {

						diasAsignacion = (Util.compararNumeroDiasFechas(fechaPerFin, fechaEntrega)) + 1L;

						if (vehicles.getLocations().getLocationsTypes()
								.getLctCodigo().longValue() == ParametersUtil.EXTERIORES
								.longValue()) {

							valorCif = new ConsultsServiceImpl().consultFuntions(vehicles.getVhcPlacaActivoFijo(), 1);

							if (valorCif == null)
								throw new GWorkException(Util.loadErrorMessageValue("VALORCIF.EXISTEN") + vehicles.getVhcPlacaDiplomatica());

							vidaUtil = new ConsultsServiceImpl().consultFuntions(vehicles.getVhcPlacaActivoFijo(), 4);

							if (vidaUtil == null)
								throw new GWorkException(Util.loadErrorMessageValue("VIDAUTIL.EXISTEN") + vehicles.getVhcPlacaDiplomatica());

							valorOutposted = valorCif.doubleValue() / (vidaUtil.longValue() * 12);
							valorOutposted = (valorOutposted / 30) * diasAsignacion * porcentajeCC;

							assignationProof.setValorAsignacion(Util.redondear(valorOutposted, 2));
							assignationProof.setValorDepreciacion(Util.redondear(valorOutposted, 2));

						} else {
							valorAutoseguro = (valorAutoseguro / 30) * diasAsignacion;
							valorDepreciacion = (valorDepreciacion / 30)  * diasAsignacion;
							valorMantenimiento = (valorMantenimiento / 30) * diasAsignacion;
							valorEspacioFisco = (valorEspacioFisco / 30) * diasAsignacion;
							valorAsignacion = (valorAsignacion / 30) * diasAsignacion;

							assignationProof.setValorAsignacion(Util.redondear(valorAsignacion, 2));
							assignationProof.setValorAutoseguro(Util.redondear(valorAutoseguro, 2));
							assignationProof.setValorDepreciacion(Util.redondear(valorDepreciacion, 2));
							assignationProof.setValorEspacioFisico(Util.redondear(valorEspacioFisco, 2));
							assignationProof.setValorMantenimiento(Util.redondear(valorMantenimiento, 2));
						}
					}

					assignationProof.setPorcentajeCC(new Float(costsCentersVehicles.getCcrPorcentaje()));
					
					assignationProof.setLgtCodigo(vehiclesAssignation.getRequests().getLegateesTypes().getLgtCodigo());
					assignationProof.setLctCodigo(vehicles.getLocations().getLocationsTypes().getLctCodigo());
					
					if (vehiclesAssignation.getRequests().getLegateesTypes().getLgtCodigo().longValue() == ParametersUtil.LGT_OF.longValue())
						parameters = SearchParametersBilling
								.consultarParemeter(
										ParametersUtil.DEBITO,
										ParametersUtil.CARGO_NO_APLICA,
										ParametersUtil.TRASACCTION_TYPE_ASSIGNATION,
										vehiclesAssignation.getRequests().getLegateesTypes().getLgtCodigo(),
										vehicles.getLocations().getLocationsTypes().getLctCodigo());
					else if (vehiclesAssignation.getRequests()
							.getLegateesTypes().getLgtCodigo().longValue() == 
								ParametersUtil.LGT_OFS.longValue())
						
						parameters = SearchParametersBilling
								.consultarParemeter(
										ParametersUtil.DEBITO,
										ParametersUtil.CARGO_NO_APLICA,
										ParametersUtil.TRASACCTION_TYPE_ASSIGNATION,
										vehiclesAssignation.getRequests().getLegateesTypes().getLgtCodigo(),
										vehicles.getLocations().getLocationsTypes().getLctCodigo());

					else if (vehiclesAssignation.getRequests()
							.getLegateesTypes().getLgtCodigo().longValue() == 
								ParametersUtil.LGT_PROGRAMAS.longValue())
						parameters = SearchParametersBilling
								.consultarParemeter(
										ParametersUtil.DEBITO,
										ParametersUtil.CARGO_NO_APLICA,
										ParametersUtil.TRASACCTION_TYPE_ASSIGNATION,
										vehiclesAssignation.getRequests().getLegateesTypes().getLgtCodigo(),
										vehicles.getLocations().getLocationsTypes().getLctCodigo());

					observaciones = validaCentroCosto(costsCentersVehicles
							.getCostsCenters().getCocNumero(),
							costsCentersVehicles.getCcrPorcentaje(),
							objVehiclesAssignation.getVehicles().getVhcPlacaDiplomatica(),
							period.getPerFechaIni(), period.getPerFechaFin(),
							parameters);

					if (observaciones != null
							&& observaciones.trim().length() > 0) {
						assignationProof.setObservaciones(observaciones);
					}

					if (validarDisponibilidadPlaca(assignationProof.getPlaca(),
							period, parameters) == true) {
						assignationProof.setVisible(true);
						assignationProof.setSeleccion(true);
					} else {
						assignationProof.setVisible(false);
						assignationProof.setSeleccion(true);
						
					}
					lisVOAssignation.add(assignationProof);
				}
			}

			return lisVOAssignation;
		} catch (Exception e) {
			log.error("asignacionesCombrobante", e);
			throw new GWorkException(e.getMessage(), e);
		}
	}

	/**
	 * Validad si el centro de costo esta activo o tiene disponibilidad
	 * presupuestal.
	 *
	 * @param centroCosto the centro costo
	 * @param valorPorcentaje the valor porcentaje
	 * @param placa the placa
	 * @param fechaIni the fecha ini
	 * @param fechaFin the fecha fin
	 * @param parameters the parameters
	 * @return the string
	 * @throws GWorkException the g work exception
	 */
	public String validaCentroCosto(String centroCosto, String valorPorcentaje,
			String placa, Date fechaIni, Date fechaFin,
			AccountingParameters parameters) throws GWorkException {

		String centroCostoActivo = consultarVigenciaCentroCosto(centroCosto
				.toUpperCase(), fechaIni, fechaFin);

		String centroCostoDisponible = disponibilidadCombustibleCC(centroCosto,
				valorPorcentaje, placa.toUpperCase().trim(), parameters);

		if (centroCostoActivo != null && centroCostoDisponible != null)
			return centroCostoActivo + "\n" + centroCostoDisponible;

		else if (centroCostoActivo != null
				&& centroCostoActivo.trim().length() > 0)
			return centroCostoActivo;

		else if (centroCostoDisponible != null
				&& centroCostoDisponible.trim().length() > 0)
			return centroCostoDisponible;

		return centroCostoActivo;
	}

	/**
	 * Validar lista asignacion cc.
	 *
	 * @param placa the placa
	 * @param fechaIni the fecha ini
	 * @param fechaFin the fecha fin
	 * @param parameters the parameters
	 * @return true, if successful
	 * @throws GWorkException the g work exception
	 */
	public boolean validarListaAsignacionCC(String placa, Date fechaIni,
			Date fechaFin, AccountingParameters parameters)
			throws GWorkException {
		List<CostsCentersVehicles> listCostCenters = SearchCostCenters
				.consultarCCVehiculo(placa.trim().toUpperCase());
		String centroCostoValidar = null;
		for (CostsCentersVehicles costsCentersVehicles : listCostCenters) {
			centroCostoValidar = validaCentroCosto(costsCentersVehicles
					.getCostsCenters().getCocNumero(), costsCentersVehicles
					.getCcrPorcentaje(), placa, fechaIni, fechaFin, parameters);
			if (centroCostoValidar != null && centroCostoValidar.length() > 0)
				return true;
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see geniar.siscar.logic.billing.services.AssignationProofService#generarComprobanteAsignacion(java.lang.Long, java.util.List, java.lang.String)
	 */
	public void generarComprobanteAsignacion(Long idPeriodo,
			List<VOAssignationProof> listaAsignaciones, String login)
			throws GWorkException {

		int flagHP = 0;
		Double valorMantenimiento = 0D;
		Double valorAutoseguro = 0D;
		Double valorDepreciacion = 0D;
		Double valorEspacioFisico = 0D;
		Double valorAsignacion = 0D;
		Double totalDebito = 0D;
		Double totalCredito = 0D;
		Double diferenciaCombrobante = 0D;
		List<AccountingParameters> listAccountingParameters = new ArrayList<AccountingParameters>();
		Connection connection = null;
		
		int tamaño=0;

		try {
			EntityManagerHelper.getEntityManager().getTransaction().begin();
			
			Period period = new PeriodDAO().findById(idPeriodo);
			
			HeaderProof headerProof = new PrepaidProofBoughtServiceImpl()
					.generarCabeceraComprobante(
							ParametersUtil.COMPRANTE_ASIGNACION, period,
							ParametersUtil.TRASACCTION_TYPE_ASSIGNATION,
							ParametersUtil.DOLAR);
	
			connection = ConsultsServiceImpl.getConnection(Util.loadParametersValue("DATASOURCE.FINANCIERO"));
						
			log.info("TAMANHO DE LA LISTA: " + listaAsignaciones.size());
			
			SearchAccountingParameters searchAccountingParameters = new SearchAccountingParameters();
			
			Long hepId = headerProof.getHepId();
			
			for (VOAssignationProof assignationProof : listaAsignaciones) {
				
				//prueba con la placa
				//listaAsignaciones.get(0).getIdAsignacion();
//				if(assignationProof.getPlaca().equals("OI0569"))
//						System.out.println("-----------------placa"+"OI0569");
				 valorMantenimiento = 0D;
				 valorAutoseguro = 0D;
				 valorDepreciacion = 0D;
				 valorEspacioFisico = 0D;
				 valorAsignacion = 0D;
				 totalDebito = 0D;
				 totalCredito = 0D;
				 diferenciaCombrobante = 0D;
//				
				
				valorAsignacion = Util.redondear(assignationProof
						.getValorAsignacion(), 2);
				// SE CARGAN LOS PARAMETROS CONTABLES CON LOS QUE SE VA HA LLENAR LA TABLA ACTUAL_OTHERS_APPLICATION
				listAccountingParameters = searchAccountingParameters
						.consultarParametrizacionContableActivos(
								assignationProof.getLgtCodigo(),
								ParametersUtil.COMPRANTE_ASIGNACION,
								ParametersUtil.DEBITO,
								assignationProof.getLctCodigo());
				
				if(listAccountingParameters != null && listAccountingParameters.size() > 0){
					for (AccountingParameters accountingParameters : listAccountingParameters) {
						//Guarda el registro
						connection = detallarActualOthersApplicationsAsignacion2(
								connection, headerProof, assignationProof,
								period, login, valorAsignacion,
								accountingParameters);
					}
				}
				
				flagHP++;
	
				totalDebito = totalDebito + valorAsignacion;
				
				if (assignationProof.getValorMantenimiento() != null)
					valorMantenimiento = Util.redondear(valorMantenimiento, 2)
							+ assignationProof.getValorMantenimiento();
	
				if (assignationProof.getValorAutoseguro() != null)
					valorAutoseguro = Util.redondear(valorAutoseguro, 2)
							+ assignationProof.getValorAutoseguro();
	
				if (assignationProof.getValorAsignacion() != null)
					valorDepreciacion = Util.redondear(valorDepreciacion, 2)
							+ assignationProof.getValorDepreciacion();

				if (assignationProof.getValorEspacioFisico() != null)
					valorEspacioFisico = Util.redondear(valorEspacioFisico, 2)
							+ assignationProof.getValorEspacioFisico();

				valorAutoseguro = Util.redondear(valorAutoseguro, 2);
				valorDepreciacion = Util.redondear(valorDepreciacion, 2);
				valorMantenimiento = Util.redondear(valorMantenimiento, 2);
				valorEspacioFisico = Util.redondear(valorEspacioFisico, 2);
			
	
					totalCredito = valorAutoseguro + valorDepreciacion
					+ valorMantenimiento + valorEspacioFisico;

					// Redondear totales
//					if(assignationProof.getPlaca().equals("OI0808"))
//						System.out.println("empieza prueba");
	
					totalCredito = Util.redondear(totalCredito, 2);
					totalDebito = Util.redondear(totalDebito, 2);
					diferenciaCombrobante = totalCredito - totalDebito;
					diferenciaCombrobante = Util
							.redondear(diferenciaCombrobante, 2);
					log.info("### Diferencia saldos: ###"
							+ diferenciaCombrobante);
	
					if (diferenciaCombrobante > 1D)
						throw new GWorkException(
								"Comprobante no balanceado, diferencia :"
										+ diferenciaCombrobante);
	
					if (diferenciaCombrobante < 0)
						valorDepreciacion = valorDepreciacion
								- diferenciaCombrobante;
					if (diferenciaCombrobante > 0)
						valorDepreciacion = valorDepreciacion
								- diferenciaCombrobante;
					
					//modificado el 7-marzo-2012
					valorDepreciacion=Util.redondear(valorDepreciacion, 2);
					
					assignationProof.setValorDepreciacion(valorDepreciacion);
					
					log.info("VALOR ASIGNACION CREDITO : " + valorAsignacion);
	
					listAccountingParameters = searchAccountingParameters
					.consultarParametrizacionContableActivos(
							assignationProof.getLgtCodigo(),
							ParametersUtil.COMPRANTE_ASIGNACION,
							ParametersUtil.CREDITO,
							assignationProof.getLctCodigo());

					
					if(listAccountingParameters != null && listAccountingParameters.size() > 0){
						for (AccountingParameters accountingParameters : listAccountingParameters) {
							if (accountingParameters.getChargeType().getCgtId()
									.longValue() == ParametersUtil.CARGO_MANTENIMIENTO.longValue()) {
								connection = detallarActualOthersApplicationsAsignacion2(
										connection, headerProof, assignationProof,
										period, login, assignationProof.getValorMantenimiento(),
										accountingParameters);
							} else if (accountingParameters.getChargeType().getCgtId()
									.longValue() == ParametersUtil.CARGO_DEPRECIACION.longValue()) {
							
							connection = detallarActualOthersApplicationsAsignacion2(
									connection, headerProof, assignationProof,
									period, login, assignationProof.getValorDepreciacion(),
									accountingParameters);
							} else if (accountingParameters.getChargeType().getCgtId()
									.longValue() == ParametersUtil.CARGO_AUTOSEGURO.longValue()) {
								connection = detallarActualOthersApplicationsAsignacion2(
										connection, headerProof, assignationProof,
										period, login, assignationProof.getValorAutoseguro(),
										accountingParameters);
							} else if (accountingParameters.getChargeType().getCgtId()
									.longValue() == ParametersUtil.CARGO_ESPACIO_FISICO.longValue()) {
								connection = detallarActualOthersApplicationsAsignacion2(
										connection, headerProof, assignationProof,
										period, login, assignationProof.getValorEspacioFisico(),
										accountingParameters);
							}
						}
					}
					
			}
			
			ActualOthersApplicationsDAO dao = new ActualOthersApplicationsDAO();
			//List<ActualOthersApplications> listActualOApp = dao.findByCriteria("HEP_ID = "+ hepId);
			List<ActualOthersApplications> listActualOApp = dao.findByCriteria(hepId);
			
			tamaño=listActualOApp.size();
			Long idDetail = Long.valueOf(tamaño);
			String idMaster = new ConsultsServiceImpl().getIdMaster();
			for(int i=0;i<tamaño;i++){
				connection = ConsultsServiceImpl.getConnection(Util.loadParametersValue("DATASOURCE.FINANCIERO"));
			//aqui va grabar en financiero
				ConsultsServiceImpl.insercionContableSinAutocommit(connection, listActualOApp.get(i).getPSob(), listActualOApp.get(i).getPAccdate(), 
					listActualOApp.get(i).getPCurr(), listActualOApp.get(i).getPUser(), listActualOApp.get(i).getPCategory(), listActualOApp.get(i).getPSource(), 
					listActualOApp.get(i).getPConvDate(), listActualOApp.get(i).getPConvType(), listActualOApp.get(i).getPConvRate(), 
					listActualOApp.get(i).getPCompany(), listActualOApp.get(i).getPAccount(), listActualOApp.get(i).getPCcenter(), 
					listActualOApp.get(i).getPAuxiliary(), listActualOApp.get(i).getPEntDr(), listActualOApp.get(i).getPEntCr(), listActualOApp.get(i).getPBname(), 
					listActualOApp.get(i).getPDescription(), listActualOApp.get(i).getPNit(), listActualOApp.get(i).getPAttribute2(), listActualOApp.get(i).getPAttribute5(), 
					listActualOApp.get(i).getPAttribute6(), listActualOApp.get(i).getPAttribute7(), listActualOApp.get(i).getPAttribute8(), listActualOApp.get(i).getPAttribute9(), 
					listActualOApp.get(i).getPAttribute10(),listActualOApp.get(i).getHeaderProof().getHepGroupId(),
					idMaster, idDetail);// ""+hepId
			connection.commit();
			connection.close();
			}

			EntityManagerHelper.getEntityManager().getTransaction().commit();

			if (connection != null){
				//connection.commit();
				log.info("Ok grabado en la interfaz...");
			}

		} catch (Exception e) {
			log.error("generarComprobanteAsignacion", e);
			throw new GWorkException(e.getMessage(), e);
		}finally{
			EntityManagerHelper.closeEntityManager();
			try{
				if (connection!=null && !connection.isClosed()){
					connection.close();
				}
			}catch(Exception ex2){
				log.error("Error: " + ex2.getMessage(), ex2);
				throw new GWorkException(ex2.getMessage(), ex2);
			}
		}

	}

	/**
	 * Generar comprobante devolucion asignacion credito.
	 *
	 * @param connection the connection
	 * @param parameters the parameters
	 * @param tipoComprobante the tipo comprobante
	 * @param login the login
	 * @param tipoCargo the tipo cargo
	 * @param fecha the fecha
	 * @param vehiclesAssignation the vehicles assignation
	 * @param valor the valor
	 * @param CCenter the c center
	 * @param headerProof the header proof
	 * @return the connection
	 * @throws GWorkException the g work exception
	 */
	public Connection generarComprobanteDevolucionAsignacionCredito(
			Connection connection, AccountingParameters parameters,
			Long tipoComprobante, String login, Date fecha,
			VehiclesAssignation vehiclesAssignation, Float valor,
			String CCenter, HeaderProof headerProof, String idMaster, Long idDetail) throws GWorkException {

		GenerateProofService generateProofService = new GenerateProofServiceImpl();

		String placa = vehiclesAssignation.getVehicles()
				.getVhcPlacaDiplomatica();
		String tipoMoneda = Util.loadParametersValue("p.sob.dolar");

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		String periodo = dateFormat.format(vehiclesAssignation
				.getVhaFechaEntrega())
				+ "-" + dateFormat.format(fecha);

		String aoaState = ParametersUtil.ESTADO_ACTIVO;
		Long pSob = new Long(tipoMoneda);
		Date pAccdate = new Date();
		String pCurr = Util.loadParametersValue("p.curr.dol");
		String pUser = login;
		String pCategory = Util.loadParametersValue("p.category.asig");
		String pSource = Util.loadParametersValue("p.source");
		String pCompany = "";
		String pAccount = "";
		String pCcenter = null;
		String pAuxiliary = null;
		Float PEntDr = null;
		Float PEntCr = null;
		String pBname = "";
		String PDescription = "";
		String pAttribute5 = "";
		String pAttribute6 = "";
		String pAttribute9 = "";
		String pAttribute10 = ManipulacionFechas.getMes(fecha)
				+ ManipulacionFechas.getDia(fecha);

		PEntCr = valor;
		pAttribute6 = placa;
		pCcenter = parameters.getCostsCenters().getCocNumero();

		pCompany = parameters.getCompany().getCmpNombre();

		long idAuxTemp = parameters.getAuxiliar().getAuxId();
		if (idAuxTemp == 9) {
			pAuxiliary = vehiclesAssignation.getVhaNumeroCarne();
		} else {
			pAuxiliary = parameters.getAuxiliar().getAuxValor();
		}

		pAccount = parameters.getAccount().getAccNumeroCuenta();
		pAttribute9 = parameters.getDocumentTwo().getDocumentTwoType()
				.getDttName();
		pAttribute5 = parameters.getDocumentOne().getDocumentOneType()
				.getDotName();
		PDescription = parameters.getDescriptionType().getDstValor() + ":"
				+ placa + " " + Util.loadMessageValue("PERIODO") + periodo
				+ "-" + Util.loadMessageValue("DEVOLUCION");

		pBname = pCompany + "-" + pUser + "-" + pCategory + "-"
				+ Util.loadMessageValue("DEVOLUCION.ASIG") + "-"
				+ headerProof.getHepGroupId();

		connection = generateProofService.generarComprobanteDetalle(connection, aoaState,
				pSob, pAccdate, pCurr, pUser, pCategory, pSource, null, null,
				null, pCompany, pAccount.trim(), pCcenter.trim(), pAuxiliary,
				PEntDr, PEntCr, pBname, PDescription, null, null, pAttribute5,
				pAttribute6.trim(), null, null, pAttribute9, pAttribute10
						.trim(), ParametersUtil.CREDITO, tipoComprobante,
				parameters, headerProof, idMaster, idDetail);
		return connection;
	}

	/**
	 * Generar comprobante devolucion asignacion debito.
	 *
	 * @param connection the connection
	 * @param parameters the parameters
	 * @param tipoComprobante the tipo comprobante
	 * @param login the login
	 * @param fecha the fecha
	 * @param vehiclesAssignation the vehicles assignation
	 * @param valor the valor
	 * @param CCenter the c center
	 * @param headerProof the header proof
	 * @return the connection
	 * @throws GWorkException the g work exception
	 */
	public Connection generarComprobanteDevolucionAsignacionDebito(Connection connection,
			AccountingParameters parameters,
			Long tipoComprobante, String login, Date fecha,
			VehiclesAssignation vehiclesAssignation, Float valor,
			String CCenter, HeaderProof headerProof,
			String idMaster, Long idDetail) throws GWorkException {

		long codigoAsignatario = vehiclesAssignation.getRequests()
				.getLegateesTypes().getLgtCodigo().longValue();
		
		GenerateProofService generateProofService = new GenerateProofServiceImpl();

		String placa = vehiclesAssignation.getVehicles()
				.getVhcPlacaDiplomatica();
		String tipoMoneda = Util.loadParametersValue("p.sob.dolar");

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		String periodo = dateFormat.format(vehiclesAssignation
				.getVhaFechaEntrega())
				+ "-" + dateFormat.format(fecha);

		String aoaState = ParametersUtil.ESTADO_ACTIVO;
		Long pSob = new Long(tipoMoneda);
		Date pAccdate = new Date();
		String pCurr = Util.loadParametersValue("p.curr.dol");
		String pUser = login;
		String pCategory = Util.loadParametersValue("p.category.asig");
		String pSource = Util.loadParametersValue("p.source");
		String pCompany = "";
		String pAccount = "";
		String pCcenter = null;
		String pAuxiliary = null;
		Float PEntDr = null;
		Float PEntCr = null;
		String pBname = "";
		String PDescription = "";
		String pAttribute5 = "";
		String pAttribute6 = "";
		String pAttribute9 = "";
		String pAttribute10 = ManipulacionFechas.getMes(fecha)
				+ ManipulacionFechas.getDia(fecha);
		
		if (codigoAsignatario == ParametersUtil.LGT_OF
				|| codigoAsignatario == ParametersUtil.LGT_OFS
				|| codigoAsignatario == ParametersUtil.LGT_PROGRAMAS) {

			PEntDr = valor;
			pAttribute6 = placa;
			pCcenter = CCenter;

			pCompany = parameters.getCompany().getCmpNombre();

			long idAuxTemp = parameters.getAuxiliar().getAuxId();
			if (idAuxTemp == 9) {
				pAuxiliary = vehiclesAssignation.getVhaNumeroCarne();
			} else {
				pAuxiliary = parameters.getAuxiliar().getAuxValor();
			}

			pAccount = parameters.getAccount().getAccNumeroCuenta();
			pAttribute9 = parameters.getDocumentTwo().getDocumentTwoType()
					.getDttName();
			pAttribute5 = parameters.getDocumentOne().getDocumentOneType()
					.getDotName();
			PDescription = parameters.getDescriptionType().getDstValor() + ":"
					+ placa + " " + Util.loadMessageValue("PERIODO") + periodo
					+ "-" + Util.loadMessageValue("DEVOLUCION") + " ";

			List<Period> periodos = FlatFileFuelServiceImpl
					.consultarListaPeriodosByfecha(pAccdate);
			Period period = null;
			if (periodos.size() == 1) {
				period = periodos.get(0);

				pBname = pCompany + "-" + pUser + "-" + pCategory + "-"
						+ Util.loadMessageValue("DEVOLUCION.ASIG") + "-"
						+ headerProof.getHepGroupId();

				connection = generateProofService.generarComprobanteDetalle(connection, 
						aoaState, pSob, pAccdate, pCurr, pUser, pCategory,
						pSource, null, null, null, pCompany, pAccount.trim(),
						pCcenter.trim(), pAuxiliary, PEntDr, PEntCr, pBname,
						PDescription, null, null, pAttribute5, pAttribute6
								.trim(), null, null, pAttribute9, pAttribute10
								.trim(), ParametersUtil.DEBITO,
						tipoComprobante, parameters, headerProof,
						idMaster, idDetail);

				VhaAoaApp vhaAoaApp = new VhaAoaApp();
				vhaAoaApp.setAoaFechaIni(period.getPerFechaIni());
				vhaAoaApp.setAoaFechaFin(period.getPerFechaFin());
				vhaAoaApp.setHeaderProof(headerProof);
				vhaAoaApp.setVehiclesAssignation(vehiclesAssignation);

				new VhaAoaAppDAO().save(vhaAoaApp);

			}
		}
		return connection;
	}

	/**
	 * Detallar actual others applications asignacion.
	 *
	 * @param connection the connection
	 * @param headerProof the header proof
	 * @param assignationProof the assignation proof
	 * @param period the period
	 * @param login the login
	 * @param valor the valor
	 * @param parameters the parameters
	 * @return the connection
	 * @throws GWorkException the g work exception
	 */
	public Connection detallarActualOthersApplicationsAsignacion(
			Connection connection, HeaderProof headerProof,
			VOAssignationProof assignationProof, Period period, String login,
			Double valor, AccountingParameters parameters,
			String idMaster, Long idDetail)
			throws GWorkException {
		
		VehiclesAssignation vehiclesAssignation = new VehiclesAssignationDAO()
			.findById(assignationProof.getIdAsignacion());
		String tipoMoneda = Util.loadParametersValue("p.sob.dolar");
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		String aoaState = ParametersUtil.ESTADO_ACTIVO;
		Long pSob = new Long(tipoMoneda);
		Date pAccdate = new Date();
		String pCurr = Util.loadParametersValue("p.curr.dol");
		String pUser = login;
		String pCategory = Util.loadParametersValue("p.category.asig");
		String pSource = Util.loadParametersValue("p.source");
		String pCompany = parameters.getCompany().getCmpNombre();
		String pAccount = parameters.getAccount().getAccNumeroCuenta();
		String pCcenter = null;
		String pAuxiliary = null;
		Float PEntDr = null;
		Float PEntCr = null;
		String pBname = "";
		String PDescription = "";
		String pAttribute5 = "";
		String pAttribute6 = "";
		String pAttribute9 = parameters.getDocumentTwo().getDocumentTwoType()
				.getDttName();
		String pAttribute10 = ManipulacionFechas.getMes(period
				.getPerFechaIni())
				+ ManipulacionFechas.getDia(period.getPerFechaIni());

		String periodoDesc = dateFormat.format(period.getPerFechaIni()) + "-"
				+ dateFormat.format(period.getPerFechaFin());

		if (parameters.getMovementType().getMvmId().longValue() == ParametersUtil.DEBITO) {

			pCcenter = assignationProof.getCentroCosto();
			PEntDr = valor.floatValue();
			pAttribute5 = parameters.getDocumentOne().getDocumentOneType()
					.getDotName();
			pAttribute6 = assignationProof.getPlaca();

			PDescription = parameters.getDescriptionType().getDstValor() + ":"
					+ assignationProof.getPlaca() + " "
					+ Util.loadMessageValue("PERIODO") + periodoDesc;

			if (vehiclesAssignation.getRequests().getLegateesTypes()
					.getLgtCodigo().longValue() == ParametersUtil.LGT_OFS)
				pAuxiliary = vehiclesAssignation.getRequests()
						.getUsersByRqsUser().getUsrIdentificacion();
			else
				pAuxiliary = parameters.getAuxiliar().getAuxValor();

		} else if (parameters.getMovementType().getMvmId().longValue() == ParametersUtil.CREDITO) {
			PEntCr = valor.floatValue();
			pCcenter = parameters.getCostsCenters().getCocNumero();
			pAttribute5 = parameters.getDocumentTwo().getDocumentTwoType()
					.getDttName();
			pAttribute6 = pAttribute10;
			
			if (parameters.getAuxiliar().getAuxId().longValue() == ParametersUtil.TipoAuxAsignatario.longValue()){
				pAuxiliary = vehiclesAssignation.getRequests()
						.getUsersByRqsUser().getUsrIdentificacion();
			}else{
				pAuxiliary = parameters.getAuxiliar().getAuxValor();
			}

			PDescription = parameters.getDescriptionType().getDstValor() + ":"
					+ " " + Util.loadMessageValue("PERIODO") + periodoDesc + " "+assignationProof.getPlaca();
		}

		pBname = pCompany + "-" + pUser + "-" + pCategory + "-"
				+ Util.loadMessageValue("PERIODO") + periodoDesc + "-"
				+ headerProof.getHepGroupId();
		
	
		//Graba el registro en la tabla ACTUAL_OTHERS_APPLICATION
		
		connection = new GenerateProofServiceImpl().generarComprobanteDetalle(
				connection, aoaState, pSob, pAccdate, pCurr, pUser, pCategory,
				pSource, null, null, null, pCompany, pAccount.trim(), pCcenter
						.trim(), pAuxiliary, PEntDr, PEntCr, pBname,
				PDescription, null, headerProof.getHepId().toString(),
				pAttribute5, pAttribute6.trim(), null, null, pAttribute9,
				pAttribute10.trim(), parameters.getMovementType().getMvmId(),
				ParametersUtil.TRASACCTION_TYPE_ASSIGNATION,
				parameters, headerProof, idMaster, idDetail);

		if (parameters.getMovementType().getMvmId().longValue() == ParametersUtil.DEBITO) {
			VhaAoaApp vhaAoaApp = new VhaAoaApp();
			vhaAoaApp.setAoaFechaIni(period.getPerFechaIni());
			vhaAoaApp.setAoaFechaFin(period.getPerFechaFin());
			vhaAoaApp.setHeaderProof(headerProof);
			vhaAoaApp.setVehiclesAssignation(vehiclesAssignation);

			new VhaAoaAppDAO().save(vhaAoaApp);
		}

		return connection;
	}
	
	public Connection detallarActualOthersApplicationsAsignacion2(
			Connection connection, HeaderProof headerProof,
			VOAssignationProof assignationProof, Period period, String login,
			Double valor, AccountingParameters parameters)
			throws GWorkException {
		
		VehiclesAssignation vehiclesAssignation = new VehiclesAssignationDAO()
			.findById(assignationProof.getIdAsignacion());
		String tipoMoneda = Util.loadParametersValue("p.sob.dolar");
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		String aoaState = ParametersUtil.ESTADO_ACTIVO;
		Long pSob = new Long(tipoMoneda);
		Date pAccdate = new Date();
		String pCurr = Util.loadParametersValue("p.curr.dol");
		String pUser = login;
		String pCategory = Util.loadParametersValue("p.category.asig");
		String pSource = Util.loadParametersValue("p.source");
		String pCompany = parameters.getCompany().getCmpNombre();
		String pAccount = parameters.getAccount().getAccNumeroCuenta();
		String pCcenter = null;
		String pAuxiliary = null;
		Float PEntDr = null;
		Float PEntCr = null;
		String pBname = "";
		String PDescription = "";
		String pAttribute5 = "";
		String pAttribute6 = "";
		String pAttribute9 = parameters.getDocumentTwo().getDocumentTwoType()
				.getDttName();
		String pAttribute10 = ManipulacionFechas.getMes(period
				.getPerFechaIni())
				+ ManipulacionFechas.getDia(period.getPerFechaIni());

		String periodoDesc = dateFormat.format(period.getPerFechaIni()) + "-"
				+ dateFormat.format(period.getPerFechaFin());

		if (parameters.getMovementType().getMvmId().longValue() == ParametersUtil.DEBITO) {

			pCcenter = assignationProof.getCentroCosto();
			PEntDr = valor.floatValue();
			pAttribute5 = parameters.getDocumentOne().getDocumentOneType()
					.getDotName();
			pAttribute6 = assignationProof.getPlaca();

			PDescription = parameters.getDescriptionType().getDstValor() + ":"
					+ assignationProof.getPlaca() + " "
					+ Util.loadMessageValue("PERIODO") + periodoDesc;

			if (vehiclesAssignation.getRequests().getLegateesTypes()
					.getLgtCodigo().longValue() == ParametersUtil.LGT_OFS)
				pAuxiliary = vehiclesAssignation.getRequests()
						.getUsersByRqsUser().getUsrIdentificacion();
			else
				pAuxiliary = parameters.getAuxiliar().getAuxValor();

		} else if (parameters.getMovementType().getMvmId().longValue() == ParametersUtil.CREDITO) {
			PEntCr = valor.floatValue();
			pCcenter = parameters.getCostsCenters().getCocNumero();
			pAttribute5 = parameters.getDocumentTwo().getDocumentTwoType()
					.getDttName();
			pAttribute6 = pAttribute10;
			
			if (parameters.getAuxiliar().getAuxId().longValue() == ParametersUtil.TipoAuxAsignatario.longValue()){
				pAuxiliary = vehiclesAssignation.getRequests()
						.getUsersByRqsUser().getUsrIdentificacion();
			}else{
				pAuxiliary = parameters.getAuxiliar().getAuxValor();
			}

			PDescription = parameters.getDescriptionType().getDstValor() + ":"
					+ " " + Util.loadMessageValue("PERIODO") + periodoDesc + " "+assignationProof.getPlaca();
		}

		pBname = pCompany + "-" + pUser + "-" + pCategory + "-"
				+ Util.loadMessageValue("PERIODO") + periodoDesc + "-"
				+ headerProof.getHepGroupId();
		
	
		//Graba el registro en la tabla ACTUAL_OTHERS_APPLICATION
		
		connection = new GenerateProofServiceImpl().generarComprobanteDetalle2(
				connection, aoaState, pSob, pAccdate, pCurr, pUser, pCategory,
				pSource, null, null, null, pCompany, pAccount.trim(), pCcenter
						.trim(), pAuxiliary, PEntDr, PEntCr, pBname,
				PDescription, null, headerProof.getHepId().toString(),
				pAttribute5, pAttribute6.trim(), null, null, pAttribute9,
				pAttribute10.trim(), parameters.getMovementType().getMvmId(),
				ParametersUtil.TRASACCTION_TYPE_ASSIGNATION,
				parameters, headerProof);

		if (parameters.getMovementType().getMvmId().longValue() == ParametersUtil.DEBITO) {
			VhaAoaApp vhaAoaApp = new VhaAoaApp();
			vhaAoaApp.setAoaFechaIni(period.getPerFechaIni());
			vhaAoaApp.setAoaFechaFin(period.getPerFechaFin());
			vhaAoaApp.setHeaderProof(headerProof);
			vhaAoaApp.setVehiclesAssignation(vehiclesAssignation);

			new VhaAoaAppDAO().save(vhaAoaApp);
		}

		return connection;
	}

	
	/**
	 * Validar disponibilidad placa.
	 *
	 * @param placa the placa
	 * @param period the period
	 * @param parameters the parameters
	 * @return true, if successful
	 * @throws GWorkException the g work exception
	 */
	public boolean validarDisponibilidadPlaca(String placa, Period period,
			AccountingParameters parameters) throws GWorkException {

		List<CostsCentersVehicles> listCostCenters = SearchCostCenters
				.consultarCCVehiculo(placa);
		String validarCC = "";

		for (CostsCentersVehicles costsCentersVehicles : listCostCenters) {

			validarCC = validaCentroCosto(costsCentersVehicles
					.getCostsCenters().getCocNumero(), costsCentersVehicles
					.getCcrPorcentaje(), costsCentersVehicles
					.getVehiclesAssignation().getVehicles()
					.getVhcPlacaDiplomatica(), period.getPerFechaIni(), period
					.getPerFechaFin(), parameters);

			if (validarCC != null && validarCC.trim().length() > 0)
				return true;

		}

		return false;

	}

	/**
	 * Validar disponibilidad placa combustible.
	 *
	 * @param placa the placa
	 * @param period the period
	 * @param parameters the parameters
	 * @return true, if successful
	 * @throws GWorkException the g work exception
	 */
	public boolean validarDisponibilidadPlacaCombustible(String placa,
			Period period, AccountingParameters parameters)
			throws GWorkException {

		List<CostCentersFuel> listCostCenters = SearchPrepaidServiceImpl
				.listaCostCenterFuelByPlaca(placa);
		String validarCC = "";

		for (CostCentersFuel costsCentersFuel : listCostCenters) {

			validarCC = validaCentroCosto(costsCentersFuel.getCostsCenters()
					.getCocNumero(), costsCentersFuel.getCcfPorcentaje(),
					costsCentersFuel.getVehiclesAssignation().getVehicles()
							.getVhcPlacaDiplomatica(), period.getPerFechaIni(),
					period.getPerFechaFin(), parameters);

			if (validarCC != null && validarCC.trim().length() > 0)
				return true;

		}
		return false;
	}
}