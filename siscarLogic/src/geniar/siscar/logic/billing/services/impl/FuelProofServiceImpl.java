package geniar.siscar.logic.billing.services.impl;

import geniar.siscar.logic.billing.services.FuelProofService;
import geniar.siscar.logic.consultas.ConsultTariff;
import geniar.siscar.logic.fuels.services.impl.SearchPrepaidServiceImpl;
import geniar.siscar.model.AccountingParameters;
import geniar.siscar.model.CostCentersFuel;
import geniar.siscar.model.CostsCenters;
import geniar.siscar.model.Period;
import geniar.siscar.model.VOPrepagoInicial;
import geniar.siscar.model.Vehicles;
import geniar.siscar.persistence.PeriodDAO;
import geniar.siscar.util.ManipulacionFechas;
import geniar.siscar.util.ParametersUtil;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class FuelProofServiceImpl implements FuelProofService {
	private static final Log log = LogFactory
			.getLog(FuelProofServiceImpl.class);

	public List<VOPrepagoInicial> listaCargaPrepago(Long idPeriodo,
			Date fechaIniConsumo, Date fechaFinConsumo) throws GWorkException {

		Period periodo = new PeriodDAO().findById(idPeriodo);

		List<CostCentersFuel> listaCostCenterFuel = SearchPrepaidServiceImpl
				.listaCostCenterFuel();
		AccountingParameters parameters = null;
		List<VOPrepagoInicial> listCargaPrepago = new ArrayList<VOPrepagoInicial>();
		Long numMesesPeriodo = null;
		numMesesPeriodo = ManipulacionFechas.calculaTiempoTranascurridoMeses(
				periodo.getPerFechaIni(), periodo.getPerFechaFin());
		Double porcentajeAdicional = 1.10D;

		if (listaCostCenterFuel == null || listaCostCenterFuel.size() == 0
				|| listaCostCenterFuel.isEmpty())
			throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));

		else
			for (CostCentersFuel prepagoObj : listaCostCenterFuel) {

				if (SearchParametersBilling.validarPeriodoAsignacion(periodo
						.getPerId(), prepagoObj.getVehiclesAssignation()
						.getVhaCodigo(), ParametersUtil.PROOF_TYPE_COMBUSTIBLE) != true
						&& prepagoObj.getVehiclesAssignation().getVehicles()
								.getLocations().getLocationsTypes()
								.getLctCodigo().longValue() != ParametersUtil.EXTERIORES
								.longValue()) {

					VOPrepagoInicial cargaPrepago = new VOPrepagoInicial();
					Double valorTarifa = null;
					Double valorPrepago = null;
					Vehicles vehicles = null;
					VOPrepagoInicial consumoVehiculo = null;
					String observaciones = null;
					CostsCenters costsCenters = prepagoObj.getCostsCenters();
					Float valorCCos = costsCenters.getValorPrepago();
					boolean agregarPrepago = true;
					Float porcentaje = new Long(prepagoObj.getCcfPorcentaje()) / 100F;
					Long diferenciaDiasServicio;

					// Consulta el vehiculo a partir de la placa
					vehicles = prepagoObj.getVehiclesAssignation()
							.getVehicles();

					consumoVehiculo = SearchPrepaidServiceImpl
							.consumoByVehicle(fechaIniConsumo, fechaFinConsumo,
									vehicles.getVhcPlacaDiplomatica());

					// Se calcula la tarifa dependiendo del tipo de combustible
					// del1

					// vehículo
					valorTarifa = new Double(
							new ConsultTariff()
									.consultarValorTarifaTipoCombustible(vehicles
											.getFuelsTypes().getFutCodigo()
											.longValue()));

					if (valorTarifa == null)
						throw new GWorkException(
								Util
										.loadErrorMessageValue("TARIFA.COMBUSTIBLE.NULL"));

					if (consumoVehiculo != null) {

						cargaPrepago.setConsumoPromedio(Util
								.redondear(new Double(consumoVehiculo
										.getConsumoPromedio()), 3));
						cargaPrepago.setKmAnual(new Double(consumoVehiculo
								.getKmAnual()).longValue());
						cargaPrepago.setGalonesAno(Util.redondear(new Double(
								consumoVehiculo.getGalonesAno()), 3));
						cargaPrepago.setMinFechaServicio(consumoVehiculo
								.getMinFechaServicio());
						cargaPrepago.setMaxFechaServicio(consumoVehiculo
								.getMaxFechaServicio());

						diferenciaDiasServicio = ManipulacionFechas
								.calculaTiempoTranascurridoDias(cargaPrepago
										.getMaxFechaServicio(), cargaPrepago
										.getMinFechaServicio());

						// Si existe solo un tanqueo para el vehiculo
						if (diferenciaDiasServicio.longValue() == 0L)
							diferenciaDiasServicio = ManipulacionFechas
									.calculaTiempoTranascurridoDias(
											fechaFinConsumo, fechaIniConsumo);

						valorPrepago = ((cargaPrepago.getGalonesAno() / diferenciaDiasServicio) * 30L * valorTarifa)
								* numMesesPeriodo;
						valorPrepago = valorPrepago * porcentajeAdicional;

					}

					// Calcula el valor del prepago depiendo si hay o no saldos
					// en
					// los centros de costo
					if (valorCCos != null && valorCCos.longValue() > 0
							&& valorPrepago != null
							&& valorPrepago.longValue() > 0
							&& valorCCos.longValue() < valorPrepago.longValue()) {
						valorPrepago = valorPrepago - valorCCos;
					} else if ((valorPrepago == null || valorPrepago
							.longValue() == 0L)
							&& (valorCCos == null || valorCCos.longValue() == 0))
						valorPrepago = 500D;
					else if (valorPrepago == null) {
						agregarPrepago = false;
					}
					if (valorPrepago != null && valorPrepago.longValue() > 0)
						valorPrepago = valorPrepago * porcentaje;

					cargaPrepago.setPlaca(vehicles.getVhcPlacaDiplomatica());
					cargaPrepago.setCentroCosto(costsCenters.getCocNumero());
					cargaPrepago.setTipoCombustible(vehicles.getFuelsTypes()
							.getFutNombre());

					parameters = SearchParametersBilling.consultarParemeter(
							ParametersUtil.DEBITO, ParametersUtil.CARGO_CC,
							ParametersUtil.TRASACCTION_TYPE,
							ParametersUtil.LGT_NO_APLICA);

					if (agregarPrepago == true)
						observaciones = new AssignationProofServiceImpl()
								.validaCentroCosto(costsCenters.getCocNumero(),
										prepagoObj.getCcfPorcentaje(),
										prepagoObj.getVehiclesAssignation()
												.getVehicles()
												.getVhcPlacaDiplomatica(),
										periodo.getPerFechaIni(), periodo
												.getPerFechaFin(), parameters);

					if (observaciones != null
							&& observaciones.trim().length() > 0) {

						cargaPrepago.setObservacion(observaciones);
					}

					if (agregarPrepago == true
							&& new AssignationProofServiceImpl()
									.validarDisponibilidadPlacaCombustible(
											vehicles.getVhcPlacaDiplomatica(),
											periodo, parameters) == true) {
						cargaPrepago.setVisible(true);
						cargaPrepago.setSeleccion(true);
					} else {
						cargaPrepago.setVisible(false);
						cargaPrepago.setSeleccion(true);
					}

					if (valorPrepago != null)
						valorPrepago = Util.redondear(valorPrepago, 2);
					cargaPrepago.setValorPrepago(valorPrepago);
					cargaPrepago.setCostCentersFuel(prepagoObj);
					cargaPrepago.setVehiclesAssignation(prepagoObj
							.getVehiclesAssignation());

					if (agregarPrepago == true)
						listCargaPrepago.add(cargaPrepago);
					else
						log.info("PLACA ########: "
								+ prepagoObj.getVehiclesAssignation()
										.getVehicles().getVhcPlacaDiplomatica()
								+ " VALOR PREPAGO: " + valorPrepago);
				}
			}

		return listCargaPrepago;
	}
}
