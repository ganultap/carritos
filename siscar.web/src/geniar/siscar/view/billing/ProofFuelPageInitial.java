package geniar.siscar.view.billing;

import geniar.siscar.logic.billing.services.AssignationProofService;
import geniar.siscar.logic.billing.services.FuelProofInitialService;
import geniar.siscar.logic.billing.services.FuelProofService;
import geniar.siscar.logic.billing.services.PeriodService;
import geniar.siscar.model.AccountingParameters;
import geniar.siscar.model.Period;
import geniar.siscar.model.VOPrepagoInicial;
import geniar.siscar.model.VhaAoaApp;
import geniar.siscar.util.FacesUtils;
import geniar.siscar.util.PopupMessagePage;
import geniar.siscar.util.Util;
import geniar.siscar.view.autenticate.LoginPage;
import gwork.exception.GWorkException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

public class ProofFuelPageInitial {

	private Long comprobante;
	private Long periodo;
	private Date fechaIni;
	private Date fechaFin;
	private AssignationProofService assignationProofService;
	private List<VhaAoaApp> listaAsignaciones;
	private PeriodService periodService;
	private LoginPage loginPage = (LoginPage) FacesUtils
			.getManagedBean("loginPage");
	private FuelProofInitialService fuelProofInitialService;
	private boolean activarConfirmacion;
	private FuelProofService fuelProofService;
	private List<VOPrepagoInicial> listVOPrepagoInicial;
	private Date fechaIniConsumo;
	private Date fechaFinConsumo;
	private Double totalComprobante;

	public Double getTotalComprobante() {
		return totalComprobante;
	}

	public void setTotalComprobante(Double totalComprobante) {
		this.totalComprobante = totalComprobante;
	}

	public Date getFechaIniConsumo() {
		return fechaIniConsumo;
	}

	public void setFechaIniConsumo(Date fechaIniConsumo) {
		this.fechaIniConsumo = fechaIniConsumo;
	}

	public Date getFechaFinConsumo() {
		return fechaFinConsumo;
	}

	public void setFechaFinConsumo(Date fechaFinConsumo) {
		this.fechaFinConsumo = fechaFinConsumo;
	}

	public List<VOPrepagoInicial> getListVOPrepagoInicial() {
		return listVOPrepagoInicial;
	}

	public void setListVOPrepagoInicial(
			List<VOPrepagoInicial> listVOPrepagoInicial) {
		this.listVOPrepagoInicial = listVOPrepagoInicial;
	}

	public FuelProofInitialService getFuelProofInitialService() {
		return fuelProofInitialService;
	}

	public void setFuelProofInitialService(
			FuelProofInitialService fuelProofInitialService) {
		this.fuelProofInitialService = fuelProofInitialService;
	}

	public Long getComprobante() {
		this.comprobante = 3L;
		return comprobante;
	}

	public void setComprobante(Long comprobante) {
		this.comprobante = comprobante;
	}

	public Long getPeriodo() {
		return periodo;
	}

	public void setPeriodo(Long periodo) {
		this.periodo = periodo;
	}

	public PeriodService getPeriodService() {
		return periodService;
	}

	public void setPeriodService(PeriodService periodService) {
		this.periodService = periodService;
	}

	public Date getFechaIni() {
		return fechaIni;
	}

	public void setFechaIni(Date fechaIni) {
		this.fechaIni = fechaIni;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public void action_generarComprobante(ActionEvent event) {

		try {

			validarDatos(comprobante, periodo, fechaIni, fechaFin,
					fechaIniConsumo, fechaFinConsumo, listVOPrepagoInicial);

			activarConfirmacion = true;
			mostrarMensaje(Util.loadMessageValue("MENSAJE.ALERTA"),
					activarConfirmacion);

		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void generarComrobante() {

		try {
			List<VOPrepagoInicial> listaVOSelecciondos = new ArrayList<VOPrepagoInicial>();
			for (VOPrepagoInicial cargaPrepago : listVOPrepagoInicial) {

				if (cargaPrepago.isSeleccion())
					listaVOSelecciondos.add(cargaPrepago);
			}
			System.out.println("TAMANHO LISTA: " + listaVOSelecciondos.size());

			fuelProofInitialService.generarComprobante(comprobante, loginPage
					.getLogin(), periodo, listaVOSelecciondos);
			limpiar();
			mostrarMensaje(Util.loadMessageValue("EXITO"), false);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void listener_periodo(ValueChangeEvent event) {
		Long idPeriodo = (Long) event.getNewValue();
		Period period = null;
		if (idPeriodo != null) {
			try {
				period = periodService.consultarPeriodById(idPeriodo);

				if (period != null) {
					fechaIni = period.getPerFechaIni();
					fechaFin = period.getPerFechaFin();
				}

			} catch (GWorkException e) {
				FacesUtils.addErrorMessage(e.getMessage());
			}
		}
	}

	public void action_consultarPrepagoInicial(ActionEvent event) {

		try {
			Double combrobanteAcu = 0D;
			List<VOPrepagoInicial> listaPrepagoConsulata = null;
			if (periodo == null || periodo.longValue() == -1L)
				throw new GWorkException(Util
						.loadErrorMessageValue("ERROR.SELEPERIODO"));

			if (fechaIniConsumo == null || fechaFinConsumo == null)
				throw new GWorkException(
						"Debe ingresar el rango de fecha de consumo");

			listaPrepagoConsulata = fuelProofService.listaCargaPrepago(periodo,
					fechaIniConsumo, fechaFinConsumo);

			if (listaPrepagoConsulata == null
					|| listaPrepagoConsulata.size() == 0
					|| listaPrepagoConsulata.isEmpty())
				throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));

			setListVOPrepagoInicial(null);
			setListVOPrepagoInicial(listaPrepagoConsulata);

			for (VOPrepagoInicial voPrepagoInicial : getListVOPrepagoInicial()) {
				combrobanteAcu = combrobanteAcu
						+ voPrepagoInicial.getValorPrepago();
			}

			totalComprobante = combrobanteAcu;
			totalComprobante = Util.redondear(totalComprobante, 2);

		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public String disponibilidadCentrCosto(String centroCosto, String valor,
			String placa, AccountingParameters parameters)
			throws GWorkException {

		String centroCostoActivo = assignationProofService
				.consultarVigenciaCentroCosto(centroCosto.toUpperCase(),
						fechaIni, fechaFin);

		String centroCostoDisponible = assignationProofService
				.disponibilidadCombustibleCC(centroCosto, valor, placa
						.toUpperCase().trim(), parameters);

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

	public void limpiar() {
		comprobante = new Long("-1");
		periodo = new Long("-1");
		fechaFin = null;
		fechaIni = null;
		fechaFinConsumo = null;
		fechaIniConsumo = null;
		totalComprobante = null;
		setListVOPrepagoInicial(null);

	}

	public void action_limpiar(ActionEvent actionEvent) {

		limpiar();
	}

	public List<VhaAoaApp> getListaAsignaciones() {
		return listaAsignaciones;
	}

	public void setListaAsignaciones(List<VhaAoaApp> listaAsignaciones) {
		this.listaAsignaciones = listaAsignaciones;
	}

	public void validarDatos(Long comprobante, Long periodo, Date fechaIni,
			Date fechaFin, Date fechaIniConsumo, Date fehcaFinConsumo,
			List<VOPrepagoInicial> listaVOCargaPrepago) throws GWorkException {

		if (comprobante == null || comprobante.longValue() == -1L)
			throw new GWorkException("Debe seleccionar el tipo de comprobante");

		if (periodo == null || periodo.longValue() == -1L)
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.SELEPERIODO"));

		if (fechaIni == null)
			throw new GWorkException(Util.loadErrorMessageValue("FECHAINICIO"));

		if (fechaFin == null)
			throw new GWorkException(Util.loadErrorMessageValue("FECHAFIN"));

		if (fechaIniConsumo == null)
			throw new GWorkException(Util
					.loadErrorMessageValue("PERIODO.CONSUMO.INI"));

		if (fechaFinConsumo == null)
			throw new GWorkException(Util
					.loadErrorMessageValue("PERIODO.CONSUMO.FIN"));

		if (listaVOCargaPrepago == null || listaVOCargaPrepago.size() == 0
				|| listaVOCargaPrepago.isEmpty())
			throw new GWorkException(Util
					.loadErrorMessageValue("LISTA.PREPAGO.NULO"));
	}

	public boolean isActivarConfirmacion() {
		return activarConfirmacion;
	}

	public void setActivarConfirmacion(boolean activarConfirmacion) {
		this.activarConfirmacion = activarConfirmacion;
	}

	public void mostrarMensaje(String mensaje, boolean buttonCancel) {
		PopupMessagePage message = (PopupMessagePage) FacesUtils
				.getManagedBean("popupMessagePage");
		if (message != null)
			message.showPopup(mensaje, buttonCancel);
	}

	public AssignationProofService getAssignationProofService() {
		return assignationProofService;
	}

	public void setAssignationProofService(
			AssignationProofService assignationProofService) {
		this.assignationProofService = assignationProofService;
	}

	public FuelProofService getFuelProofService() {
		return fuelProofService;
	}

	public void setFuelProofService(FuelProofService fuelProofService) {
		this.fuelProofService = fuelProofService;
	}
}
