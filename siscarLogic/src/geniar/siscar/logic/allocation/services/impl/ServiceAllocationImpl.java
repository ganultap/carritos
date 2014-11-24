/**
 * 
 */
package geniar.siscar.logic.allocation.services.impl;

import geniar.siscar.consults.impl.ConsultsServiceImpl;
import geniar.siscar.logic.allocation.services.FindDeliveryVehicle;
import geniar.siscar.logic.allocation.services.ServiceAllocation;
import geniar.siscar.logic.billing.services.PrepaidFuelProofService;
import geniar.siscar.logic.billing.services.impl.FlatFileFuelServiceImpl;
import geniar.siscar.logic.billing.services.impl.PrepaidFuelProofServiceImpl;
import geniar.siscar.logic.billing.services.impl.PrepaidProofBoughtServiceImpl;
import geniar.siscar.logic.billing.services.impl.RentProofServiceImpl;
import geniar.siscar.logic.consultas.SearchAccountingParameters;
import geniar.siscar.logic.consultas.SearchCostCenters;
import geniar.siscar.logic.consultas.SearchPlainFileParameter;
import geniar.siscar.logic.consultas.SearchVehicles;
import geniar.siscar.logic.parameters.services.TariffRentService;
import geniar.siscar.logic.parameters.services.impl.TariffRentServiceImpl;
import geniar.siscar.mail.util.SendEmail;
import geniar.siscar.model.AccountingParameters;
import geniar.siscar.model.AssignationsStates;
import geniar.siscar.model.Cities;
import geniar.siscar.model.CostCentersFuel;
import geniar.siscar.model.CostsCentersVehicles;
import geniar.siscar.model.FlatFile;
import geniar.siscar.model.HeaderProof;
import geniar.siscar.model.Locations;
import geniar.siscar.model.LocationsNewness;
import geniar.siscar.model.LocationsTypes;
import geniar.siscar.model.Period;
import geniar.siscar.model.PlainFileParameter;
import geniar.siscar.model.Requests;
import geniar.siscar.model.Rolls;
import geniar.siscar.model.Tariffs;
import geniar.siscar.model.Vehicles;
import geniar.siscar.model.VehiclesAssignation;
import geniar.siscar.model.VhaFf;
import geniar.siscar.persistence.AssignationsStatesDAO;
import geniar.siscar.persistence.AssignationsTypesDAO;
import geniar.siscar.persistence.CitiesDAO;
import geniar.siscar.persistence.CostCenterTypeFuelDAO;
import geniar.siscar.persistence.CostCentersFuelDAO;
import geniar.siscar.persistence.CostsCentersVehiclesDAO;
import geniar.siscar.persistence.EntityManagerHelper;
import geniar.siscar.persistence.FlatFileDAO;
import geniar.siscar.persistence.IAssignationsStatesDAO;
import geniar.siscar.persistence.IRequestsDAO;
import geniar.siscar.persistence.IVehiclesAssignationDAO;
import geniar.siscar.persistence.IVehiclesDAO;
import geniar.siscar.persistence.IVehiclesStatesDAO;
import geniar.siscar.persistence.LocationsNewnessDAO;
import geniar.siscar.persistence.LocationsTypesDAO;
import geniar.siscar.persistence.NoveltyTypesDAO;
import geniar.siscar.persistence.RequestsDAO;
import geniar.siscar.persistence.RequestsStatesDAO;
import geniar.siscar.persistence.RollsDAO;
import geniar.siscar.persistence.VehiclesAssignationDAO;
import geniar.siscar.persistence.VehiclesDAO;
import geniar.siscar.persistence.VehiclesStatesDAO;
import geniar.siscar.persistence.VhaFfDAO;
import geniar.siscar.persistence.ZonesDAO;
import geniar.siscar.util.ManipulacionFechas;
import geniar.siscar.util.ParametersUtil;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * The Class ServiceAllocationImpl.
 *
 * @author Geniar
 */
public class ServiceAllocationImpl implements ServiceAllocation {

	/** The Constant log. */
	private static final Log log = LogFactory.getLog(ServiceAllocationImpl.class);

	/**
	 * Entrega de vehiculos asignados o en alquiler pendiente.
	 *
	 * @param numeroAsignacion the numero asignacion
	 * @param fechaEntrega the fecha entrega
	 * @param vhaKilomeActual the vha kilome actual
	 * @param observacion the observacion
	 * @param loginUser the login user
	 * @throws GWorkException the g work exception
	 * @throws SQLException the sQL exception
	 */
	public void entregaVehiculoAsignacion(Long numeroAsignacion,
			Date fechaEntrega, Long vhaKilomeActual, String observacion,
			String loginUser) throws GWorkException, SQLException {
		
		Connection connection = null;
		try {

			connection = ConsultsServiceImpl.getConnection(Util.loadParametersValue("DATASOURCE.FINANCIERO"));

			String idMaster = new ConsultsServiceImpl().getIdMaster();
			
			VehiclesAssignation vehiclesAssignation = new VehiclesAssignation();
			IVehiclesAssignationDAO vehiclesAssignationDAO = new VehiclesAssignationDAO();
			vehiclesAssignation = vehiclesAssignationDAO.findById(numeroAsignacion);
			Requests requests = vehiclesAssignation.getRequests();

			List<CostsCentersVehicles> listaCostCenters = null;
			EntityManagerHelper.getEntityManager().refresh(requests);
			EntityManagerHelper.getEntityManager().refresh(vehiclesAssignation);
			/** Validar que la asignacion del vehiculo exista */
			if (vehiclesAssignation == null)
				throw new GWorkException(Util.loadErrorMessageValue("ASIGNACIONNOEXISTE"));

			if (fechaEntrega.compareTo(requests.getRqsFechaInicial()) < 0)
				throw new GWorkException(Util
						.loadErrorMessageValue("FECHAENTREGA.SOLICITUDINI"));
			if (fechaEntrega.compareTo(requests.getRqsFechaFinal()) == 1)
				throw new GWorkException(Util
						.loadErrorMessageValue("FECHAENTREGA.SOLICITUDFIN"));

			/** Cambiar el estado a la asignacion del vehiculo a entregado */
			AssignationsStates assignationsStates = new AssignationsStates();
			IAssignationsStatesDAO assignationsStatesDAO = new AssignationsStatesDAO();
			String msgEstadoAsignacion = Util.loadMessageValue("ENTREGADO");
			List<AssignationsStates> listAssignationsStates = assignationsStatesDAO
					.findByAssNombre(msgEstadoAsignacion);
			assignationsStates = listAssignationsStates.get(0);

			Long kilometrajeInicial = null;

			if (vehiclesAssignation.getRequests().getVehicles()
					.getVhcKilometrajeActual() != null)
				kilometrajeInicial = vehiclesAssignation.getRequests()
						.getVehicles().getVhcKilometrajeActual();

			if (kilometrajeInicial == null)
				kilometrajeInicial = -1L;
			if (vhaKilomeActual < kilometrajeInicial)
				throw new GWorkException(Util
						.loadErrorMessageValue("KILOMETRAJE.MENOR"));

			Vehicles vehicles = vehiclesAssignation.getVehicles();
			IVehiclesDAO vehiclesDAO = new VehiclesDAO();

			vehiclesAssignation.setVhaFechaEntrega(fechaEntrega);
			vehiclesAssignation.setVhaKilomeActual(vhaKilomeActual);
			vehiclesAssignation.setAssignationsStates(assignationsStates);
			vehiclesAssignation.setVhaObservacion(observacion);

			Long tipoVehiculo = vehiclesAssignation.getVehicles()
					.getVehiclesTypes().getVhtCodigo().longValue();
			String nomTipoVehiculo = vehiclesAssignation.getVehicles()
					.getVehiclesTypes().getVhtNombre();

			if (vehiclesAssignation.getAssignationsTypes().getAstCodigo()
					.longValue() == ParametersUtil.ASIG_ALQUILER.longValue()
					&& vehiclesAssignation.getVhaCobro().equalsIgnoreCase(
							Util.loadMessageValue("ESTADO_COBRO_SI"))) {
				
				// cantidad de dias del alquiler registrados en la solicitud
				Long cantDiasAlquiler = Util.compararNumeroDiasFechas(
						vehiclesAssignation.getRequests().getRqsFechaFinal(),
						fechaEntrega) + 1;

				SearchAccountingParameters searchAccountingParameters = new SearchAccountingParameters();
				TariffRentService tariffRentService = new TariffRentServiceImpl(); 
				
				List<AccountingParameters> listAccountingParameters = new ArrayList<AccountingParameters>();
				
				Double valorMantenimiento = 0D;
				Double valorDepreciacion = 0D;
				Double valorAutoSeguro = 0D;
				Double valorEspacioFisico = 0D;
				Double valorTarifa = 0D;
				Double valorTarifaDebito = 0D;
				
				listAccountingParameters = searchAccountingParameters
					.consultarParametrizacionContableActivos(
					vehiclesAssignation.getRequests().getLegateesTypes().getLgtCodigo(), 
					ParametersUtil.COMPRANTE_ALQUILER,
					ParametersUtil.CREDITO,
					vehicles.getLocations().getLocationsTypes().getLctCodigo());

				if(listAccountingParameters == null || listAccountingParameters.size() == 0){
					throw new GWorkException(Util.loadErrorMessageValue(
							"ERROR.ALQUILER.NO_EXISTEN_PARAMETROS_CREDITO") 
							+ " Tipo de localización: " + vehicles.getLocations().getLocationsTypes().getLctNombre()
							+ " Tipo de asignatario: " + vehiclesAssignation.getRequests().getLegateesTypes().getLgtNombre()
							+ ", Por favor verificar.");
				}else{						
					for (AccountingParameters accountingParameters : listAccountingParameters) {
						if(accountingParameters.getChargeType().getCgtId().longValue() == 
							ParametersUtil.CARGO_AUTOSEGURO.longValue()){
							
							Tariffs tariffsAutoSeguro = tariffRentService
									.consultarTarifaActualAlquilerPorTipoTarifaYTipoVehiculo(
											tipoVehiculo,
											ParametersUtil.TARIFA_ALQUILER_AUTOSEGURO);
							
							if (tariffsAutoSeguro == null)
								throw new GWorkException(Util
									.loadErrorMessageValue("TARIFADISPONIBILIDAD")
										+ ":" + nomTipoVehiculo);
							
							valorAutoSeguro = Util.redondear(tariffsAutoSeguro.getTrfValor().doubleValue(),2);
							
						}else if(accountingParameters.getChargeType().getCgtId().longValue() == 
							ParametersUtil.CARGO_DEPRECIACION.longValue()){
							
							Tariffs tariffsDepreciacion = tariffRentService
							.consultarTarifaActualAlquilerPorTipoTarifaYTipoVehiculo(
									tipoVehiculo,
									ParametersUtil.TARIFA_ALQUILER_DEPRECIACION);
							
							if (tariffsDepreciacion == null)
								throw new GWorkException(Util
										.loadErrorMessageValue("TARIFADISPONIBILIDAD")
										+ ":" + nomTipoVehiculo);
							
							valorDepreciacion = Util.redondear(tariffsDepreciacion.getTrfValor(),2);
							
						}else if(accountingParameters.getChargeType().getCgtId().longValue() == 
							ParametersUtil.CARGO_MANTENIMIENTO.longValue()){
							
							Tariffs tariffsMantenimiento = tariffRentService
							.consultarTarifaActualAlquilerPorTipoTarifaYTipoVehiculo(
									tipoVehiculo,
									ParametersUtil.TARIFA_ALQUILER_MANTENIMIENTO);
							
							if (tariffsMantenimiento == null)
								throw new GWorkException(Util
										.loadErrorMessageValue("TARIFADISPONIBILIDAD")
										+ ":" + nomTipoVehiculo);
							
							valorMantenimiento = Util.redondear(tariffsMantenimiento.getTrfValor(),2);
							
						}else if(accountingParameters.getChargeType().getCgtId().longValue() == 
							ParametersUtil.CARGO_ESPACIO_FISICO.longValue()){
							
							Tariffs tariffsEspacioFisco = tariffRentService
							.consultarTarifaActualAlquilerPorTipoTarifaYTipoVehiculo(
									tipoVehiculo,
									ParametersUtil.TARIFA_ALQUILER_ESPACIO_FISICO);
							
							if (tariffsEspacioFisco == null)
								throw new GWorkException(Util
										.loadErrorMessageValue("TARIFADISPONIBILIDAD")
										+ ":" + nomTipoVehiculo);
							
							valorEspacioFisico = Util.redondear(tariffsEspacioFisco.getTrfValor(),2);
						}
					}
				}
				
				if ((vehiclesAssignation.getRequests().getRequestsClasses()
						.getRqcCodigo().longValue() == ParametersUtil.CLASE_ALQUILER_TERCEROS
						.longValue())
						|| (vehiclesAssignation.getRequests().getRequestsClasses()
								.getRqcCodigo().longValue() == ParametersUtil.CLASE_ALQUILER
								.longValue() 
								&& (vehiclesAssignation
								.getRequests().getLegateesTypes()
								.getLgtCodigo().longValue() == ParametersUtil.LGT_CONVENIO)
									|| (vehiclesAssignation
								.getRequests().getLegateesTypes()
								.getLgtCodigo().longValue() == ParametersUtil.LGT_PERSONAL
								.longValue()))
						|| (vehiclesAssignation.getAssignationsTypes()
								.getAstCodigo().longValue() == ParametersUtil.ASIG_ALQUILER.longValue()
								&& vehiclesAssignation.getCostsCentersVehicleses() != null
								&& vehiclesAssignation.getCostsCentersVehicleses().size() > 0)) {

					listaCostCenters = SearchCostCenters.consultarCCVehiculoEntrega(vehiclesAssignation.getVhaCodigo().longValue());
					
					Period period = FlatFileFuelServiceImpl.consultarPeriodoByfecha(fechaEntrega);

					HeaderProof headerProof = new PrepaidProofBoughtServiceImpl().generarCabeceraComprobante(
									ParametersUtil.COMPRANTE_ALQUILER, period,
									ParametersUtil.TRASACCTION_TYPE_RENT,
									ParametersUtil.DOLAR);

					Long idDetail = Long.valueOf(0);
					
					if (listaCostCenters != null && listaCostCenters.size() > 0) {
						for (CostsCentersVehicles costsCentersVehicles : listaCostCenters) {

							// Consulta la tarifa de alquiler actual para el tipo de
							// vehiculo asociado
							BigDecimal valorCif = null;
							BigDecimal vidaUtil = null;
							Double valorOutposted = null;
							valorTarifa = 0D;
							valorTarifaDebito = 0D;
							
							Double porcentajeCC = Double.valueOf(costsCentersVehicles.getCcrPorcentaje()) / 100D;
					
							valorMantenimiento = valorMantenimiento * porcentajeCC;
							valorDepreciacion = valorDepreciacion * porcentajeCC;
							valorAutoSeguro = valorAutoSeguro * porcentajeCC;
							valorEspacioFisico = valorEspacioFisico * porcentajeCC;

							valorTarifa = valorMantenimiento + valorDepreciacion 
								+ valorAutoSeguro + valorEspacioFisico;

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
								valorOutposted = valorOutposted * cantDiasAlquiler * porcentajeCC;
								
								valorDepreciacion = valorOutposted;
								valorTarifa = valorOutposted;
								
							} else {
								valorDepreciacion = valorDepreciacion * cantDiasAlquiler;
								valorAutoSeguro = valorAutoSeguro * cantDiasAlquiler;
								valorMantenimiento = valorMantenimiento * cantDiasAlquiler;
								valorEspacioFisico = valorEspacioFisico * cantDiasAlquiler;

								valorTarifa = valorMantenimiento + valorDepreciacion 
								+ valorAutoSeguro + valorEspacioFisico;
							}

							valorTarifaDebito = valorTarifaDebito + valorTarifa;

							listAccountingParameters = searchAccountingParameters
								.consultarParametrizacionContableActivos(
										vehiclesAssignation.getRequests().getLegateesTypes().getLgtCodigo(),
										ParametersUtil.COMPRANTE_ALQUILER,
										ParametersUtil.CREDITO,
										vehicles.getLocations().getLocationsTypes().getLctCodigo());

							if(listAccountingParameters != null && listAccountingParameters.size() > 0){
								for (AccountingParameters accountingParameters : listAccountingParameters) {
									if (accountingParameters.getChargeType().getCgtId()
											.longValue() == ParametersUtil.CARGO_MANTENIMIENTO.longValue()) {
										idDetail++;
										connection = new RentProofServiceImpl().generarComprobanteAlquiler(connection,
											ParametersUtil.COMPRANTE_ALQUILER,
											loginUser, ParametersUtil.CREDITO,
											new Date(), vehiclesAssignation,
											valorMantenimiento.doubleValue(),
											"", headerProof, listaCostCenters,
											accountingParameters, idMaster, idDetail);
								
									} else if (accountingParameters.getChargeType().getCgtId().longValue() == 
										ParametersUtil.CARGO_DEPRECIACION.longValue()) {
										idDetail++;
										connection = new RentProofServiceImpl().generarComprobanteAlquiler(connection,
											ParametersUtil.COMPRANTE_ALQUILER,
											loginUser, ParametersUtil.CREDITO,
											new Date(), vehiclesAssignation,
											valorDepreciacion.doubleValue(),
											"", headerProof, listaCostCenters,
											accountingParameters, idMaster, idDetail);
								
									} else if (accountingParameters.getChargeType().getCgtId().longValue() == 
										ParametersUtil.CARGO_AUTOSEGURO.longValue()) {
										idDetail++;
										connection = new RentProofServiceImpl().generarComprobanteAlquiler(connection,
											ParametersUtil.COMPRANTE_ALQUILER,
											loginUser, ParametersUtil.CREDITO,
											new Date(), vehiclesAssignation,
											valorAutoSeguro.doubleValue(),
											"", headerProof, listaCostCenters,
											accountingParameters, idMaster, idDetail);

									} else if (accountingParameters.getChargeType().getCgtId().longValue() == 
										ParametersUtil.CARGO_ESPACIO_FISICO.longValue()) {
										idDetail++;
										connection = new RentProofServiceImpl().generarComprobanteAlquiler(connection,
											ParametersUtil.COMPRANTE_ALQUILER,
											loginUser, ParametersUtil.CREDITO,
											new Date(), vehiclesAssignation,
											valorEspacioFisico.doubleValue(),
											"", headerProof, listaCostCenters,
											accountingParameters, idMaster,idDetail);
									}
								}
							}

							listAccountingParameters = searchAccountingParameters
								.consultarParametrizacionContableActivos(
									vehiclesAssignation.getRequests().getLegateesTypes().getLgtCodigo(),
									ParametersUtil.COMPRANTE_ALQUILER,
									ParametersUtil.DEBITO,
									vehicles.getLocations().getLocationsTypes().getLctCodigo());

							if(listAccountingParameters != null && listAccountingParameters.size() > 0){
								for (AccountingParameters accountingParameters : listAccountingParameters) {
									idDetail++;
									connection = new RentProofServiceImpl().generarComprobanteAlquiler(connection,
											ParametersUtil.COMPRANTE_ALQUILER,
											loginUser, ParametersUtil.DEBITO,
											new Date(), vehiclesAssignation,
											valorTarifaDebito.doubleValue(),
											costsCentersVehicles.getCostsCenters().getCocNumero(),
											headerProof, listaCostCenters, accountingParameters, idMaster,idDetail);
								}
							} else {
								throw new GWorkException(Util.loadErrorMessageValue(
								"ERROR.ALQUILER.NO_EXISTEN_PARAMETROS_DEBITO") 
								+ " Tipo de localización: " + vehicles.getLocations().getLocationsTypes().getLctNombre()
								+ " Tipo de asignatario: " + vehiclesAssignation.getRequests().getLegateesTypes().getLgtNombre()
								+ ", Por favor verificar.");
							}
							connection = ConsultsServiceImpl.insertTMaster(connection, idMaster, "P", idDetail.intValue());
						}
					}else{
						valorDepreciacion = valorDepreciacion * cantDiasAlquiler;
						valorAutoSeguro = valorAutoSeguro * cantDiasAlquiler;
						valorMantenimiento = valorMantenimiento * cantDiasAlquiler;
						valorEspacioFisico = valorEspacioFisico * cantDiasAlquiler;

						valorTarifa = valorMantenimiento + valorDepreciacion 
						+ valorAutoSeguro + valorEspacioFisico;
						
						listAccountingParameters = searchAccountingParameters
						.consultarParametrizacionContableActivos(
								vehiclesAssignation.getRequests().getLegateesTypes().getLgtCodigo(),
								ParametersUtil.COMPRANTE_ALQUILER,
								ParametersUtil.CREDITO,
								vehicles.getLocations().getLocationsTypes().getLctCodigo());

						if(listAccountingParameters != null && listAccountingParameters.size() > 0){
							for (AccountingParameters accountingParameters : listAccountingParameters) {
								if (accountingParameters.getChargeType().getCgtId()
										.longValue() == ParametersUtil.CARGO_MANTENIMIENTO.longValue()) {
									idDetail++;
									connection = new RentProofServiceImpl().generarComprobanteAlquiler(connection,
										ParametersUtil.COMPRANTE_ALQUILER,
										loginUser, ParametersUtil.CREDITO,
										new Date(), vehiclesAssignation,
										valorMantenimiento.doubleValue(),
										"", headerProof, listaCostCenters,
										accountingParameters, idMaster,idDetail);
							
								} else if (accountingParameters.getChargeType().getCgtId().longValue() == 
									ParametersUtil.CARGO_DEPRECIACION.longValue()) {
									idDetail++;
									connection = new RentProofServiceImpl().generarComprobanteAlquiler(connection,
										ParametersUtil.COMPRANTE_ALQUILER,
										loginUser, ParametersUtil.CREDITO,
										new Date(), vehiclesAssignation,
										valorDepreciacion.doubleValue(),
										"", headerProof, listaCostCenters,
										accountingParameters, idMaster,idDetail);
							
								} else if (accountingParameters.getChargeType().getCgtId().longValue() == 
									ParametersUtil.CARGO_AUTOSEGURO.longValue()) {
									idDetail++;
									connection = new RentProofServiceImpl().generarComprobanteAlquiler(connection,
										ParametersUtil.COMPRANTE_ALQUILER,
										loginUser, ParametersUtil.CREDITO,
										new Date(), vehiclesAssignation,
										valorAutoSeguro.doubleValue(),
										"", headerProof, listaCostCenters,
										accountingParameters, idMaster,idDetail);
	
								} else if (accountingParameters.getChargeType().getCgtId().longValue() == 
									ParametersUtil.CARGO_ESPACIO_FISICO.longValue()) {
									idDetail++;
									connection = new RentProofServiceImpl().generarComprobanteAlquiler(connection,
										ParametersUtil.COMPRANTE_ALQUILER,
										loginUser, ParametersUtil.CREDITO,
										new Date(), vehiclesAssignation,
										valorEspacioFisico.doubleValue(),
										"", headerProof, listaCostCenters,
										accountingParameters, idMaster,idDetail);
								}
							}
						}
	
						listAccountingParameters = searchAccountingParameters
							.consultarParametrizacionContableActivos(
								vehiclesAssignation.getRequests().getLegateesTypes().getLgtCodigo(),
								ParametersUtil.COMPRANTE_ALQUILER,
								ParametersUtil.DEBITO,
								vehicles.getLocations().getLocationsTypes().getLctCodigo());

						if(listAccountingParameters != null && listAccountingParameters.size() > 0){
							for (AccountingParameters accountingParameters : listAccountingParameters) {
								idDetail++;
								connection = new RentProofServiceImpl().generarComprobanteAlquiler(connection,
										ParametersUtil.COMPRANTE_ALQUILER,
										loginUser, ParametersUtil.DEBITO,
										new Date(), vehiclesAssignation,
										valorTarifa.doubleValue(),
										"", headerProof, listaCostCenters, 
										accountingParameters, idMaster,idDetail);
							}
						}else{
							throw new GWorkException(Util.loadErrorMessageValue(
							"ERROR.ALQUILER.NO_EXISTEN_PARAMETROS_DEBITO") 
							+ " Tipo de localización: " + vehicles.getLocations().getLocationsTypes().getLctNombre()
							+ " Tipo de asignatario: " + vehiclesAssignation.getRequests().getLegateesTypes().getLgtNombre()
							+ ", Por favor verificar.");
						}

						connection = ConsultsServiceImpl.insertTMaster(connection, idMaster, "P", idDetail.intValue());

						if (vehiclesAssignation.getRequests().getLegateesTypes().getLgtCodigo().longValue() == 
								ParametersUtil.LGT_PERSONAL.longValue()) {

							Tariffs tariff = tariffRentService.consultarTarifaActualAlquilerPorTipoTarifaYTipoVehiculo(
									tipoVehiculo,
									ParametersUtil.TARIFA_ALQUILER);
							
							if (tariff == null)
								throw new GWorkException(Util.loadErrorMessageValue("TARIFADISPONIBILIDAD")
										+ ":" + nomTipoVehiculo);
							
							valorTarifa = Util.redondear((valorTarifa.doubleValue()),2);
							
							new RentProofServiceImpl().generarAlquilerNomina(
									vehiclesAssignation, valorTarifa.floatValue(), 
									fechaEntrega, loginUser, tariff);
						}
					}
				}else if (vehiclesAssignation.getCostsCentersVehicleses() == null 
						|| vehiclesAssignation.getCostsCentersVehicleses().size() == 0) {
					Tariffs tariff = tariffRentService
					.consultarTarifaActualAlquilerPorTipoTarifaYTipoVehiculo(
							tipoVehiculo,
							ParametersUtil.TARIFA_ALQUILER);
					
					if (tariff == null)
						throw new GWorkException(Util
								.loadErrorMessageValue("TARIFADISPONIBILIDAD")
								+ ":" + nomTipoVehiculo);
					
					valorTarifa = Util.redondear((valorMantenimiento + valorDepreciacion
							+ valorAutoSeguro + valorEspacioFisico) * cantDiasAlquiler,2);
					
					new RentProofServiceImpl().generarAlquilerNomina(
							vehiclesAssignation, valorTarifa.floatValue(), 
							fechaEntrega, loginUser, tariff);
				}

				if (vehiclesAssignation.getAssignationsTypes()
						.getAstCodigo().longValue() == ParametersUtil.ASIG_ALQUILER) {
					
					vehiclesAssignation.setVhaDiasAlquiler(cantDiasAlquiler);
					
					if(valorTarifaDebito > 0){
						vehiclesAssignation.setVhaValorAlquiler(valorTarifaDebito.floatValue());
					}else{
						vehiclesAssignation.setVhaValorAlquiler(valorTarifa.floatValue());
					}
				}
			}

			// Se actualiza el estado de la asignacion
			vehiclesAssignationDAO.update(vehiclesAssignation);

			// Cambiar el estado de la solicitud a cumplida
			IRequestsDAO requestsDAO = new RequestsDAO();
			requests.setRequestsStates(new RequestsStatesDAO().findById(ParametersUtil.RQS_CUMPLIDA));

			requestsDAO.update(requests);
			vehicles.setVhcKilometrajeActual(vhaKilomeActual);
			vehiclesDAO.update(vehicles);

			EntityManagerHelper.getEntityManager().getTransaction().begin();
			EntityManagerHelper.getEntityManager().getTransaction().commit();

			if (connection != null){
				log.debug("Haciendo commit en Financiero");
				connection.commit();
				log.debug("Commit realizado");
			}

			if (vehiclesAssignation != null
					&& vehiclesAssignation.getRequests().getLegateesTypes() != null
					&& (vehiclesAssignation.getRequests().getLegateesTypes()
							.getLgtCodigo().longValue() == ParametersUtil.LGT_OF || vehiclesAssignation
							.getRequests().getLegateesTypes().getLgtCodigo()
							.longValue() == ParametersUtil.LGT_OFS)
					&& vehiclesAssignation.getVehicles().getLocations()
							.getLocationsTypes().getLctCodigo().longValue() == ParametersUtil.SEDE
					&& validarCobroAutoSeguroCarne(vehiclesAssignation.getRequests().getUsersByRqsUser()
							.getUsrIdentificacion(), ManipulacionFechas.getAgno(fechaEntrega)) != true)
				insercionFlatFileAutoseguroNuevo(vehiclesAssignation, loginUser);
		} catch (Exception e) {
			if (connection != null && !connection.isClosed())
				connection.rollback();

			if (EntityManagerHelper.getEntityManager().getTransaction().isActive())
				EntityManagerHelper.getEntityManager().getTransaction().rollback();
			throw new GWorkException(e.getMessage());
		} finally{
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
	 * Metodo para actualizar una solicitud.
	 *
	 * @param requests the requests
	 * @throws GWorkException the g work exception
	 * @throws RuntimeException the runtime exception
	 */
	public void reservarVehiculo(Requests requests) throws GWorkException,
			RuntimeException {

		try {

			VehiclesAssignation vehiclesAssignation = new VehiclesAssignation();
			vehiclesAssignation.setVehicles(requests.getVehicles());
			vehiclesAssignation
					.setVhaFechaInicio(requests.getRqsFechaInicial());
			vehiclesAssignation.setVhaFechaTermina(requests.getRqsFechaFinal());
			vehiclesAssignation.setVhaNumeroCarne(requests
					.getRqsCarnetAsignatario());
			vehiclesAssignation.setVhaNumeroSolicitud(requests.getRqsCodigo()
					.toString());
			vehiclesAssignation
					.setAssignationsStates(new AssignationsStatesDAO()
							.findById(3L));

			if (requests.getRequestsClasses().getRqcCodigo().longValue() == 1L)
				vehiclesAssignation
						.setAssignationsTypes(new AssignationsTypesDAO()
								.findById(1L));
			if (requests.getRequestsClasses().getRqcCodigo().longValue() == 2L)
				vehiclesAssignation
						.setAssignationsTypes(new AssignationsTypesDAO()
								.findById(3L));

			if (requests.getRequestsClasses().getRqcCodigo().longValue() == 3L)
				vehiclesAssignation
						.setAssignationsTypes(new AssignationsTypesDAO()
								.findById(3L));

			vehiclesAssignation.setRequests(requests);
			requests.setRequestsStates(new RequestsStatesDAO().findById(3l));

			EntityManagerHelper.getEntityManager().getTransaction().begin();
			new RequestsDAO().update(requests);
			new VehiclesAssignationDAO().save(vehiclesAssignation);
			EntityManagerHelper.getEntityManager().getTransaction().commit();

		} catch (Exception e) {
			throw new GWorkException(e.getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see geniar.siscar.logic.allocation.services.ServiceAllocation#enviarNotificacionReserva(geniar.siscar.model.Requests, java.lang.Boolean, java.util.List, java.lang.String)
	 */
	public void enviarNotificacionReserva(Requests requests, Boolean cobro,
			List<CostsCentersVehicles> listaCentroCostoVehiculos, String toUser)
			throws GWorkException, RuntimeException {
		SendEmail mail = null;
		String Nombre = null;
		String Descripcion = "";

		Rolls rolls = new RollsDAO().findById(new Long(Util
				.loadParametersValue("MAIL.ADMINISTRADOR")));

		String server = Util.loadParametersValue("MAIL.HOST");
		String fromUser = rolls.getRlsMail();

		requests = EntityManagerHelper.getEntityManager().merge(requests);

		String subject = Util
				.loadParametersValue("MSJ.RESERVA.SOLICITUD.ADMIN")
				+ requests.getRequestsClasses().getRqcNombre()
				+ " con fecha de inicio " + requests.getRqsFechaInicial();
		String body = Util.loadParametersValue("MSJ.RESERVA.SOLICITUD.BODY")
				+ requests.getRequestsClasses().getRqcNombre() + ": ";

		Nombre = requests.getUsersByRqsUser().getUsrNombre();

		if (requests.getUsersByRqsUser().getUsrApellido() != null)
			Nombre += " " + requests.getUsersByRqsUser().getUsrApellido();

		if (requests.getRqsDescripcion() != null)
			Descripcion = requests.getRqsDescripcion();

		body += "<br />Numero Reserva: " + requests.getRqsCodigo()
				+ "<br />Fecha Inicial: " + requests.getRqsFechaInicial()
				+ "<br />Fecha Final: " + requests.getRqsFechaFinal()
				+ "<br />Motivo de la reserva: " + Descripcion
				+ "<br />Tipo de Asignación: "
				+ requests.getRequestsClasses().getRqcNombre()
				+ "<br />Carnet Asignatario: "
				+ requests.getRqsCarnetAsignatario()
				+ "<br />Nombre Asignatario: " + Nombre;

		if (!cobro)
			body += "<br /><br /><br />SERVICIO SIN COSTO";
		else {
			body += "<br /><br /><br />Cargo a: ";
			if (listaCentroCostoVehiculos != null
					&& listaCentroCostoVehiculos.size() > 0) {
				body += "<table border='0'><tr><td>Centro Costo</td><td>Porcentaje</td></tr>";
				for (CostsCentersVehicles costsCentersVehicles : listaCentroCostoVehiculos) {
					body += "<tr><td>"
							+ costsCentersVehicles.getCostsCenters()
									.getCocNumero() + "</td>" + "<td>"
							+ costsCentersVehicles.getCcrPorcentaje()
							+ " %</td></tr>";
				}
				body += "</table>";
			} else {
				if (requests.getUsersByRqsUser().getUsrIdentificacion() == null)
					body += "<br />" + requests.getRqsCarnetAsignatario() + " "
							+ Nombre;
			}
		}

		if (toUser != null)
			mail = new SendEmail(toUser, fromUser, server, "false", subject,
					body + requests.getVehicles().getVhcPlacaDiplomatica());

		if (mail.SendMessage().equals("SUCCESS"))
			log.info("Mensaje enviado exitosamente");
		else {
			log.info("Error Enviando el mensaje");
			throw new RuntimeException(Util
					.loadErrorMessageValue("NOTIFICACION.ERROR"));
		}
	}

	/* (non-Javadoc)
	 * @see geniar.siscar.logic.allocation.services.ServiceAllocation#reasignarReserva(geniar.siscar.model.Requests)
	 */
	public void reasignarReserva(Requests requests) throws GWorkException {

		String toUser = null;
		SendEmail mail = null;
		String codigoSolicitud = requests.getRqsCodigo().toString();
		IVehiclesAssignationDAO vehiclesAssignationDAO = new VehiclesAssignationDAO();
		VehiclesAssignation vehiclesAssignation = vehiclesAssignationDAO
				.findByVhaNumeroSolicitud(codigoSolicitud).get(0);
		vehiclesAssignation.setVehicles(requests.getVehicles());

		EntityManagerHelper.beginTransaction();
		new RequestsDAO().update(requests);
		vehiclesAssignationDAO.update(vehiclesAssignation);
		EntityManagerHelper.commit();

		Rolls rolls = new RollsDAO().findById(new Long(Util
				.loadParametersValue("MAIL.ADMINISTRADOR")));

		String server = Util.loadParametersValue("MAIL.HOST");
		String fromUser = rolls.getRlsMail();
		if (requests.getUsersByRqsUser() != null)
			toUser = requests.getUsersByRqsUser().getUsrEmail();
		String subject = Util
				.loadParametersValue("MSJ.RESERVA.SOLICITUD.ADMIN");
		String body = Util.loadParametersValue("MSJ.RESERVA.SOLICITUD.BODY");

		EntityManagerHelper.beginTransaction();
		new RequestsDAO().update(requests);
		EntityManagerHelper.commit();

		if (toUser != null)
			mail = new SendEmail(toUser, fromUser, server, "false", subject,
					body + requests.getVehicles().getVhcPlacaDiplomatica());

		if (mail.SendMessage().equals("SUCCESS"))
			log.info("Mensaje enviado exitosamente");
		else {
			log.info("Error Enviando el mensaje");
			throw new RuntimeException(Util
					.loadErrorMessageValue("NOTIFICACION.ERROR"));
		}
	}

	/* (non-Javadoc)
	 * @see geniar.siscar.logic.allocation.services.ServiceAllocation#asignacionVehiculoAsignacion(java.lang.Long, java.lang.Long, java.lang.Long, boolean, boolean, java.lang.String)
	 */
	public VehiclesAssignation asignacionVehiculoAsignacion(Long idRequests,
			Long idZones, Long idUbicacion, boolean cobro,
			boolean cobroCasaCIAT, String login) throws GWorkException {

		Set<CostsCentersVehicles> listCostCenters = null;

		Requests requestsObj = updateRequest(idRequests, idZones);
		VehiclesAssignation vehiclesAssignation = updateAssignation(requestsObj
				.getRqsCodigo(), cobro, cobroCasaCIAT);

		Vehicles vehicles = updateVehicles(vehiclesAssignation.getVehicles()
				.getVhcPlacaDiplomatica(), idUbicacion, vehiclesAssignation
				.getAssignationsTypes().getAstCodigo().longValue());
		LocationsNewness locationsNewness = updateLocation(idUbicacion,
				vehicles.getVhcCodigo().longValue());

		try {

			// Crear relación del objeto de vehiculo actualizado
			vehiclesAssignation.setVehicles(vehicles);
			requestsObj.setVehicles(vehicles);

			// Guardar los objetos consultados
			new RequestsDAO().update(requestsObj);
			new LocationsNewnessDAO().save(locationsNewness);
			new VehiclesDAO().update(vehicles);

			new VehiclesAssignationDAO().update(vehiclesAssignation);

			if (requestsObj.getCostsCentersVehicleses() != null
					&& requestsObj.getCostsCentersVehicleses().size() > 0) {
				listCostCenters = requestsObj.getCostsCentersVehicleses();

				for (CostsCentersVehicles costsCentersVehicles : listCostCenters) {
					costsCentersVehicles
							.setVehiclesAssignation(vehiclesAssignation);
					costsCentersVehicles.setCcrEstado(Util
							.loadMessageValue("ESTADO.ACTIVO"));

					if (vehiclesAssignation.getRequests().getRequestsClasses()
							.getRqcCodigo().longValue() == ParametersUtil.CLASE_ASIGNACION)
						copyCostCenterFuel(costsCentersVehicles,
								vehiclesAssignation);

					new CostsCentersVehiclesDAO().update(costsCentersVehicles);

				}
			}
			EntityManagerHelper.getEntityManager().getTransaction().begin();
			EntityManagerHelper.getEntityManager().getTransaction().commit();
			// EntityManagerHelper.getEntityManager().clear();

			return vehiclesAssignation;
		} catch (Exception e) {
			throw new GWorkException(e.getMessage());
		}
	}

	/**
	 * Copy cost center fuel.
	 *
	 * @param centersVehicles the centers vehicles
	 * @param vehiclesAssignation the vehicles assignation
	 * @throws GWorkException the g work exception
	 */
	public void copyCostCenterFuel(CostsCentersVehicles centersVehicles,
			VehiclesAssignation vehiclesAssignation) throws GWorkException {

		CostCentersFuel costCentersFuel = new CostCentersFuel();
		costCentersFuel.setCcfEstado(Util.loadMessageValue("ESTADO_ACTIVO"));
		costCentersFuel.setCcfFechaFin(centersVehicles.getCcrFechaFin());
		costCentersFuel.setCcfFechaInicio(centersVehicles.getCcrFechaInicio());
		costCentersFuel.setCcfPorcentaje(centersVehicles.getCcrPorcentaje());
		costCentersFuel.setCostsCenters(centersVehicles.getCostsCenters());
		costCentersFuel.setVehiclesAssignation(vehiclesAssignation);
		costCentersFuel.setCostCenterTypeFuel(new CostCenterTypeFuelDAO()
				.findById(1L));

		new CostCentersFuelDAO().save(costCentersFuel);

	}

	/**
	 * Update request.
	 *
	 * @param idRequests the id requests
	 * @param idZones the id zones
	 * @return the requests
	 * @throws GWorkException the g work exception
	 */
	public Requests updateRequest(Long idRequests, Long idZones)
			throws GWorkException {
		Requests requests = new RequestsDAO().findById(idRequests);
		EntityManagerHelper.getEntityManager().refresh(requests);

		if (requests.getRequestsClasses().getRqcCodigo().longValue() == ParametersUtil.CLASE_ASIGNACION.longValue())
			requests.setRequestsStates(new RequestsStatesDAO().findById(ParametersUtil.RQS_ASIGNADO));

		if (requests.getRequestsClasses().getRqcCodigo().longValue() == ParametersUtil.CLASE_ALQUILER.longValue()
				|| requests.getRequestsClasses().getRqcCodigo().longValue() == ParametersUtil.CLASE_ALQUILER_TERCEROS.longValue())
			requests.setRequestsStates(new RequestsStatesDAO().findById(ParametersUtil.RQS_ALQUILADO));

		/** Validar la zona por si un asignatario a cambiado de zona */
		if (requests.getZones() != null && idZones != null)
			requests.setZones(new ZonesDAO().findById(idZones));

		return requests;

	}

	/**
	 * Update assignation.
	 *
	 * @param idRequest the id request
	 * @param cobro the cobro
	 * @param cobroCasaCIAT the cobro casa ciat
	 * @return the vehicles assignation
	 * @throws GWorkException the g work exception
	 */
	public VehiclesAssignation updateAssignation(Long idRequest, boolean cobro,
			boolean cobroCasaCIAT) throws GWorkException {

		VehiclesAssignation vehiclesAssignation = SearchVehicles
				.consultarAsignacnioByRequest(idRequest.longValue());
		EntityManagerHelper.getEntityManager().refresh(vehiclesAssignation);

		if (vehiclesAssignation == null)
			throw new GWorkException(Util
					.loadErrorMessageValue("ASIGNACION.EXISTEN"));

		/** Cambiar el estado a la asignacion del vehiculo a Asignado */

		if (vehiclesAssignation.getAssignationsTypes().getAstCodigo()
				.longValue() == 1L)
			vehiclesAssignation
					.setAssignationsStates(new AssignationsStatesDAO()
							.findById(2L));

		if (vehiclesAssignation.getAssignationsTypes().getAstCodigo()
				.longValue() == 3L)
			vehiclesAssignation
					.setAssignationsStates(new AssignationsStatesDAO()
							.findById(4L));

		/** si se aplica el cobro de tarifas* */
		if (cobro == true)
			vehiclesAssignation.setVhaCobro("S");
		else
			vehiclesAssignation.setVhaCobro("N");

		if (cobroCasaCIAT == true)
			vehiclesAssignation.setVhaCobroCasaciat("S");
		else
			vehiclesAssignation.setVhaCobroCasaciat("N");

		return vehiclesAssignation;
	}

	/**
	 * Update location.
	 *
	 * @param idLocation the id location
	 * @param idVehicles the id vehicles
	 * @return the locations newness
	 */
	public LocationsNewness updateLocation(Long idLocation, Long idVehicles) {

		EntityManagerHelper.getEntityManager().clear();
		LocationsNewness locationsNewness = new LocationsNewness();
		locationsNewness.setVehicles(new VehiclesDAO().findById(idVehicles));
		locationsNewness.setLcnFechaActual(new Date());
		locationsNewness.setLcnUbicacion(new CitiesDAO().findById(idLocation).getCtsNombre());

		return locationsNewness;
	}

	/**
	 * Update vehicles.
	 *
	 * @param placa the placa
	 * @param idLocation the id location
	 * @param idAssignationType the id assignation type
	 * @return the vehicles
	 * @throws GWorkException the g work exception
	 */
	public Vehicles updateVehicles(String placa, Long idLocation,
			Long idAssignationType) throws GWorkException {
		Vehicles vehicles = null;
		vehicles = SearchVehicles.consultarVehiculosPorPlacaSinFiltros(placa
				.toUpperCase().trim());

		if (vehicles == null)
			throw new GWorkException(Util
					.loadErrorMessageValue("VEHICULO.SIN.REGISTRO")
					+ ": " + placa);

		else if (vehicles != null
				&& vehicles.getVehiclesStates().getVhsCodigo().longValue() != ParametersUtil.VHS_POOL
						.longValue())
			throw new GWorkException(Util
					.loadErrorMessageValue("VEHICULO.POOL"));

		EntityManagerHelper.getEntityManager().refresh(vehicles);

		Locations locations = SearchVehicles
				.consultarDatosUbicacionPorIdCiudad(idLocation);
		vehicles.setLocations(locations);

		if (vehicles.getVehiclesStates().getVhsCodigo().longValue() != ParametersUtil.VHS_POOL
				.longValue())
			throw new GWorkException(Util
					.loadErrorMessageValue("VEHICULO.POOL"));

		/** Cambiar el estado del vehiculo a asignado */

		System.out.println("******* Id assignation type" + idAssignationType);

		if (idAssignationType.longValue() == 1L)
			vehicles.setVehiclesStates(SearchVehicles.getVehiclesStates(1L));

		if (idAssignationType.longValue() == 3L)
			vehicles.setVehiclesStates(SearchVehicles.getVehiclesStates(7L));

		return vehicles;
	}

	/* (non-Javadoc)
	 * @see geniar.siscar.logic.allocation.services.ServiceAllocation#listVehiclesAssignationByUser(java.lang.String)
	 */
	public List<VehiclesAssignation> listVehiclesAssignationByUser(
			String numeroCarne) throws GWorkException {
		FindDeliveryVehicle findDeliveryVehicle = new FindDeliveryVehicleImpl();
		List<VehiclesAssignation> listVehiclesAssignation = findDeliveryVehicle
				.findByVhaNumeroCarne(numeroCarne);
		if (listVehiclesAssignation == null)
			throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));

		return listVehiclesAssignation;

	}

	/* (non-Javadoc)
	 * @see geniar.siscar.logic.allocation.services.ServiceAllocation#consultarVehiculoAsignacion(java.lang.Long)
	 */
	public VehiclesAssignation consultarVehiculoAsignacion(Long idVhaAsignacion) {
		IVehiclesAssignationDAO assignationDAO = new VehiclesAssignationDAO();
		return assignationDAO.findById(idVhaAsignacion);
	}

	/* (non-Javadoc)
	 * @see geniar.siscar.logic.allocation.services.ServiceAllocation#entregaVehiculoAlquiler(java.lang.Long, java.util.Date, java.lang.Long, java.lang.String)
	 */
	public void entregaVehiculoAlquiler(Long numeroAsignacion,
			Date fechaEntrega, Long vhaKilomeActual, String observacion)
			throws GWorkException {

		if (fechaEntrega == null)
			throw new GWorkException(Util
					.loadErrorMessageValue("FECHA_ENTREGA"));
		if (numeroAsignacion == null || numeroAsignacion.longValue() == 0)
			throw new GWorkException(Util
					.loadErrorMessageValue("ASIGNACIONNOEXISTE"));
		if (vhaKilomeActual == null)
			throw new GWorkException(Util.loadErrorMessageValue("KILOMETRAJE"));

		VehiclesAssignation vehiclesAssignation = new VehiclesAssignation();
		IVehiclesAssignationDAO vehiclesAssignationDAO = new VehiclesAssignationDAO();
		vehiclesAssignation = vehiclesAssignationDAO.findById(numeroAsignacion);
		/** Validar que la asignacion del vehiculo exista */
		if (vehiclesAssignation == null)
			throw new GWorkException(Util
					.loadErrorMessageValue("ASIGNACIONNOEXISTE"));

		/** Cambiar el estado a la asignacion del vehiculo a entregado */
		AssignationsStates assignationsStates = new AssignationsStates();
		IAssignationsStatesDAO assignationsStatesDAO = new AssignationsStatesDAO();
		String msgEstadoAsignacion = Util.loadMessageValue("ENTREGADO");
		List<AssignationsStates> listAssignationsStates = assignationsStatesDAO
				.findByAssNombre(msgEstadoAsignacion);
		assignationsStates = listAssignationsStates.get(0);

		if (fechaEntrega.compareTo(new Date()) < -1)
			throw new GWorkException(Util
					.loadErrorMessageValue("FECHAENTREGAVEHICULO"));
		Long kilometrajeInicial = vehiclesAssignation.getRequests()
				.getVehicles().getVhcKilometrajeActual();
		if (kilometrajeInicial == null)
			kilometrajeInicial = -1L;
		if (vhaKilomeActual < kilometrajeInicial)
			throw new GWorkException(Util
					.loadErrorMessageValue("KILOMETRAJE.MENOR"));

		vehiclesAssignation.setVhaFechaEntrega(fechaEntrega);
		vehiclesAssignation.setVhaKilomeActual(vhaKilomeActual);
		vehiclesAssignation.setAssignationsStates(assignationsStates);
		vehiclesAssignation.setVhaObservacion(observacion);
		EntityManagerHelper.beginTransaction();
		vehiclesAssignationDAO.update(vehiclesAssignation);
		EntityManagerHelper.commit();

		/** Cambiar el estado de la solicitud a cumplida */
		Requests requests = vehiclesAssignation.getRequests();
		IRequestsDAO requestsDAO = new RequestsDAO();
		requests.setRequestsStates(new RequestsStatesDAO().findById(5L));

		EntityManagerHelper.beginTransaction();
		requestsDAO.update(requests);
		EntityManagerHelper.commit();

		/** Cambiar el estado del vehiculo a alquilado */
		Vehicles vehicles = vehiclesAssignation.getVehicles();
		IVehiclesDAO vehiclesDAO = new VehiclesDAO();
		IVehiclesStatesDAO vehiclesStatesDAO = new VehiclesStatesDAO();
		vehicles.setVehiclesStates(vehiclesStatesDAO.findById(7L));
		EntityManagerHelper.beginTransaction();
		vehiclesDAO.update(vehicles);
		EntityManagerHelper.commit();

	}

	/* (non-Javadoc)
	 * @see geniar.siscar.logic.allocation.services.ServiceAllocation#listRentAssignationByUser(java.lang.String)
	 */
	public List<VehiclesAssignation> listRentAssignationByUser(
			String numeroCarne) throws GWorkException {

		FindDeliveryVehicle findDeliveryVehicle = new FindDeliveryVehicleImpl();
		List<VehiclesAssignation> listVehiclesAssignation = findDeliveryVehicle
				.findByRentAssinationCarne(numeroCarne);
		if (listVehiclesAssignation == null)
			throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));

		return listVehiclesAssignation;

	}

	/**
	 * Validar solicitud vehiculo.
	 *
	 * @param idVehiculo the id vehiculo
	 */
	public void validarSolicitudVehiculo(Long idVehiculo) {

	}

	/**
	 * Validar session.
	 */
	public void validarSession() {
		if (EntityManagerHelper.getEntityManager().isOpen()) {
			EntityManagerHelper.getEntityManager().close();
			EntityManagerHelper.getEntityManager().clear();
		}

	}

	/* (non-Javadoc)
	 * @see geniar.siscar.logic.allocation.services.ServiceAllocation#envioNotificacion(java.lang.String, geniar.siscar.model.Requests, java.lang.Long, java.lang.Long, boolean, java.lang.Long, java.util.List, java.lang.String, java.lang.String)
	 */
	public void envioNotificacion(String PlacaDiplomatica, Requests requests,
			Long idUbicacion, Long idTipoUbicacion, boolean cobro,
			Long KmEntrega,
			List<CostsCentersVehicles> listaCentroCostoVehiculos, String email,
			String From) throws GWorkException, RuntimeException {

		String textmessage = null;
		String strTipoAsignacion = null;
		String strApellido = null;
		String strNombre = null;
		LocationsTypes TipoUbicacion = null;
		Cities Ciudad = null;

		if (requests.getUsersByRqsUser().getUsrApellido() == null)
			strApellido = "";
		else
			strApellido = requests.getUsersByRqsUser().getUsrApellido();

		strNombre = requests.getUsersByRqsUser().getUsrNombre() + strApellido;

		TipoUbicacion = new LocationsTypesDAO().findById(idTipoUbicacion);
		Ciudad = new CitiesDAO().findById(idUbicacion);

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		String strFechaInicio = simpleDateFormat.format(requests
				.getRqsFechaInicial());
		String strFechaFinal = simpleDateFormat.format(requests
				.getRqsFechaFinal());

		// cuerpo de la notificacion
		textmessage = "<br />Cordial saludo, se le informa que ha sido entregado el vehículo "
				+ PlacaDiplomatica
				+ " para su solicitud de "
				+ requests.getRequestsClasses().getRqcNombre()
				+ ": "
				+ "<br /><br />Solicitud de "
				+ requests.getRequestsClasses().getRqcNombre()
				+ " No."
				+ requests.getRqsCodigo()
				+ "<br />Fecha Inicio: "
				+ strFechaInicio + "<br />Fecha Final: " + strFechaFinal;
		if (requests.getLegateesTypes() != null) {
			strTipoAsignacion = requests.getLegateesTypes().getLgtNombre();
			textmessage += "<br /><br /><br />Tipo Asignación: "
					+ strTipoAsignacion;
		}
		textmessage += "<br /><br />Carnet Asignatario: "
				+ requests.getRqsCarnetAsignatario()
				+ "<br />Nombre Asignatario: " + strNombre
				+ "<br>Kilometraje de entrega: " + KmEntrega
				+ "<br /><br /><br />Tipo Ubicación: "
				+ TipoUbicacion.getLctNombre() + "<br />Ubicación: "
				+ Ciudad.getCtsNombre();
		if (requests.getZones() != null)
			textmessage += "<br>Zona: "
					+ requests.getZones().getZnsDescripcion();

		if (!cobro)
			textmessage += "<br /><br /><br />SERVICIO SIN COSTO";
		else {
			textmessage += "<br /><br /><br />Cargo a: ";
			if (listaCentroCostoVehiculos != null
					&& listaCentroCostoVehiculos.size() > 0) {
				textmessage += "<table border='0'><tr><td>Centro Costo</td><td>Porcentaje</td></tr>";
				for (CostsCentersVehicles costsCentersVehicles : listaCentroCostoVehiculos) {
					textmessage += "<tr><td>"
							+ costsCentersVehicles.getCostsCenters()
									.getCocNumero() + "</td>" + "<td>"
							+ costsCentersVehicles.getCcrPorcentaje()
							+ " %</td></tr>";
				}
				textmessage += "</table>";
			} else {
				if (requests.getUsersByRqsUser().getUsrIdentificacion() == null)
					textmessage += "<br />"
							+ requests.getRqsCarnetAsignatario() + " "
							+ strNombre;
			}
		}

		String Asunto = "[SISCAR - ENTREGA] Entrega del vehiculo "
				+ PlacaDiplomatica + " de la Solicitud de "
				+ requests.getRequestsClasses().getRqcNombre()
				+ " para el día " + strFechaInicio + " - " + strNombre;
		SendEmail mail = null;

		String fromUser = From;
		String server = Util.loadParametersValue("MAIL.HOST");

		mail = new SendEmail(email, fromUser, server, "false", Asunto,
				textmessage);

		if (mail.SendMessage().equals("SUCCESS"))
			log.info("Mensaje enviado exitosamente");
		else {
			log.info("Error Enviando el mensaje");
			throw new RuntimeException(Util
					.loadErrorMessageValue("NOTIFICACION.ERROR"));
		}

	}

	/**
	 * Insercion flat file autoseguro nuevo.
	 *
	 * @param vehiclesAssignation the vehicles assignation
	 * @param login the login
	 * @throws GWorkException the g work exception
	 */
	public static void insercionFlatFileAutoseguroNuevo(
			VehiclesAssignation vehiclesAssignation, String login)
			throws GWorkException {
		String ffCarne = null;
		Long ffConcepto = null;
		Long ffUnidades = null;
		String ffValor = null;
		String ffFecha = null;
		String ffDocumento = null;
		String ffDescripcion = null;
		String ffCentroCosto = null;
		Period period = null;
		Integer ValorAutoseguro = null;
		Integer mes_inicial = null;
		Integer mes_final = null;
		String tipoConversion = null;
		Long TipoNomina = 0L;
		Long ffEstado = 0L;
		Double valorAutoSeguroMensual = null;
		Double sumaAutoSeguro = 0D;

		PlainFileParameter plainFileParameter = new PlainFileParameter();
		NoveltyTypesDAO noveltyTypesDAO = new NoveltyTypesDAO();
		ConsultsServiceImpl consultsServiceImpl = new ConsultsServiceImpl();
		String formato = "yyyyMMdd";
		Tariffs tariff = null;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formato);
		Calendar calendario = Calendar.getInstance();

		FlatFile flatFile = new FlatFile();
		/*
		 * se consulta a la tabla de vehiculos asignacion por placa diplomatica
		 * para obtener la tarifa de ciat casa ciat
		 */
		if (vehiclesAssignation != null
				&& vehiclesAssignation.getRequests() != null) {

			ffCarne = vehiclesAssignation.getRequests()
					.getRqsCarnetAsignatario();
			ffUnidades = 1L;

			TipoNomina = consultsServiceImpl
					.counsultarTipoMonedaAsignatario(ffCarne);

			/*
			 * se consultan los parametros del archivo plano para ciat casa ciat
			 */
			plainFileParameter = new SearchPlainFileParameter()
					.consultar_PFP_PorId(TipoNomina,
							ParametersUtil.TRASACCTION_TYPE_AUTOSEGURO);

			if (plainFileParameter != null)
				ffConcepto = plainFileParameter.getPfpConceptonomina();
			else
				ffConcepto = 0L;

			/* se consulta el valor de la tarifa de AutoSeguro */
			ValorAutoseguro = SearchVehicles
					.ConsultarParametroAutoSeguro(ParametersUtil.TRASACCTION_TYPE_AUTOSEGURO);

			/* valor mensual del autoseguro */
			valorAutoSeguroMensual = ValorAutoseguro.doubleValue() / 12;

			mes_inicial = ManipulacionFechas.getNumeroMes(vehiclesAssignation
					.getRequests().getRqsFechaInicial());

			calendario.set(Calendar.MONTH, 11);
			calendario.set(Calendar.DATE, 31);
			Date FinalAnho = calendario.getTime();

			// se comparan las fecha del fin de año con la fecha final de
			// entrega del vehiculo para saber si se cobra
			// hasta diciembre o antes de finalizar el año
			if (ManipulacionFechas.compararSiFechaMenor(FinalAnho,
					vehiclesAssignation.getRequests().getRqsFechaFinal())) {
				mes_final = 11;
			} else {
				mes_final = ManipulacionFechas.getNumeroMes(vehiclesAssignation
						.getRequests().getRqsFechaFinal());
			}

			if (TipoNomina != null) {
				if (TipoNomina.equals(ParametersUtil.DOLAR))
					tipoConversion = "1.0";
				else {
					tipoConversion = consultsServiceImpl
							.consultarTasaConversion(new Date()).toString();
				}

				for (int i = mes_inicial; i <= mes_final; i++)
					sumaAutoSeguro += valorAutoSeguroMensual;

				/* Se obtiene el valor real del cobro del autoseguro */
				ffValor = Double.toString(Util.redondear(
						(sumaAutoSeguro * Double.valueOf(tipoConversion)), 2));

				ffFecha = simpleDateFormat.format(new Date()).toString();
				ffDocumento = vehiclesAssignation.getVehicles()
						.getVhcPlacaDiplomatica();
				if (plainFileParameter != null)
					ffDescripcion = plainFileParameter.getPfpDescripcion();
				else
					ffDescripcion = "";

				period = FlatFileFuelServiceImpl.consultarPeriodoByNovedad(
						new Date(), ParametersUtil.TRASACCTION_TYPE_AUTOSEGURO);

				PrepaidFuelProofService ppfps = new PrepaidFuelProofServiceImpl();
				tariff = ppfps.getTarifaActual(period);

				flatFile.setFfCarne(ffCarne);
				flatFile.setFfConcepto(new Long(ffConcepto));
				flatFile.setFfUnidades(ffUnidades);
				flatFile.setFfValor(ffValor);
				flatFile.setFfFecha(Long.valueOf(ffFecha));
				flatFile.setFfDocumento(ffDocumento);
				flatFile.setFfMoneda(TipoNomina);
				flatFile.setFfDescripcion(ffDescripcion);
				flatFile.setFfCentroCosto(ffCentroCosto);
				flatFile.setFfUsuario(login);
				flatFile.setNoveltyTypes(noveltyTypesDAO
						.findById(ParametersUtil.TRASACCTION_TYPE_AUTOSEGURO));
				flatFile.setPeriod(period);
				flatFile.setFfEstado(ffEstado);
				flatFile.setTariff(tariff);

				FlatFileDAO FlatFileDao = new FlatFileDAO();
				try {
					FlatFileDao.save(flatFile);
				} catch (Exception e) {
					e.printStackTrace();
					throw new GWorkException(Util
							.loadErrorMessageValue("ERROR.GUARDAR"));
				}

				/* Creación de los datos de la tabla Vha_ff */
				String vhf_observacion = "Cobro AutoSeguro";

				/* Creación del Bean Vha_ff */
				VhaFf vha_Ff = new VhaFf();
				vha_Ff.setVhfObservacion(vhf_observacion);
				vha_Ff.setFlatFile(flatFile);
				vha_Ff.setVehiclesAssignation(vehiclesAssignation);

				/* Guardar el objeto en la BD */
				try {
					new VhaFfDAO().save(vha_Ff);
				} catch (Exception e) {
					e.printStackTrace();
					throw new GWorkException(Util
							.loadErrorMessageValue("ERROR.GUARDAR"));
				}
				EntityManagerHelper.getEntityManager().getTransaction().begin();
				EntityManagerHelper.getEntityManager().getTransaction()
						.commit();
				EntityManagerHelper.getEntityManager().clear();
			}
		}
	}

	/**
	 * Validar cobro auto seguro carne.
	 *
	 * @param carne the carne
	 * @param anho the anho
	 * @return true, if successful
	 */
	public boolean validarCobroAutoSeguroCarne(String carne, String anho) {

		final String queryString = "SELECT ff FROM FlatFile ff WHERE ff.noveltyTypes.ntId = :iDNovelty "
				+ "AND ff.ffCarne = :carne "
				+ "AND SUBSTR(ff.ffFecha,1,4) = :anho";
		Query query = EntityManagerHelper.getEntityManager().createQuery(
				queryString);

		query.setParameter("iDNovelty", ParametersUtil.NOVEDAD_AUTOSEGURO);
		query.setParameter("carne", carne);
		query.setParameter("anho", anho);

		if (query.getResultList() != null && query.getResultList().size() > 0
				&& !query.getResultList().isEmpty())
			return true;

		return false;
	}
}
