package geniar.siscar.view.fuels;

import geniar.siscar.logic.fuels.services.PumpsService;
import geniar.siscar.logic.fuels.services.SchedulePumpService;
import geniar.siscar.model.DialyMovementPumps;
import geniar.siscar.model.Pumps;
import geniar.siscar.util.FacesUtils;
import geniar.siscar.util.PopupMessagePage;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import com.icesoft.faces.component.ext.HtmlCommandButton;
import com.icesoft.faces.component.ext.HtmlOutputText;
import com.icesoft.faces.component.ext.RowSelectorEvent;

public class SchedulePumpsPage {

	private Long idPlanillaSurtidor;
	private String tanque;
	private String tipoCombustible;
	private Date fechaRevision;
	private Long surtidor;
	private Long horaRevision;
	private BigDecimal lectura;
	private boolean activarConfirmacion;

	private Long filtroSurtidor;
	private Date filtroFechaIni;
	private Date filtroFechaFin;

	private HtmlCommandButton btnCrear;
	private HtmlOutputText txtIdRegistroPlanilla;
	private SchedulePumpService schedulePumpService;

	private List<DialyMovementPumps> listaPlanillaSurtidor;

	private boolean showSchedulePump = false;

	private PumpsService pumpsService;

	public PumpsService getPumpsService() {
		return pumpsService;
	}

	public void setPumpsService(PumpsService pumpsService) {
		this.pumpsService = pumpsService;
	}

	public Date getFechaRevision() {

		return fechaRevision;
	}

	public void setFechaRevision(Date fechaRevision) {
		this.fechaRevision = fechaRevision;
	}

	public Long getSurtidor() {
		return surtidor;
	}

	public void setSurtidor(Long surtidor) {
		this.surtidor = surtidor;
	}

	public Long getHoraRevision() {
		return horaRevision;
	}

	public void setHoraRevision(Long horaRevision) {
		this.horaRevision = horaRevision;
	}

	public void validarDatosNulo(Long surtidor, Long horaRevision,
			Date fechaRevision, BigDecimal lectura) throws GWorkException {

		if (surtidor == null || surtidor.longValue() == 0
				|| surtidor.longValue() == -1L)
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.SURTINOSELECC"));

		if (horaRevision == null || horaRevision.longValue() == 0
				|| horaRevision.longValue() == -1L)
			throw new GWorkException(Util
					.loadErrorMessageValue("HORAREVISION.SEL"));

		if (fechaRevision == null)
			throw new GWorkException(Util
					.loadErrorMessageValue("FECHAREVISION.NULO"));

		if (lectura == null || lectura.toString().trim().length() == 0
				|| lectura.doubleValue() == 0.00)
			throw new GWorkException(Util
					.loadErrorMessageValue("LECTURASURTIDOR.NULO"));

		if (!Util.validarNumerosParametros(lectura.toString()))
			throw new GWorkException(Util
					.loadErrorMessageValue("CARACTERNUM.LECTURA.SURTIDOR"));
	}

	public void action_registrarPlanillaSurtidor(ActionEvent actionEvent) {

		try {
			validarDatosNulo(surtidor, horaRevision, fechaRevision, lectura);
			schedulePumpService.registrarPlanillaSurtidor(surtidor,
					horaRevision, fechaRevision, lectura.toString());
			mostrarMensaje(Util.loadMessageValue("EXITO"), false);
			limpiar();
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage("j_id96:j_id111", e.getMessage());
		}
	}

	public void action_modificarPlanillaSurtidor(ActionEvent actionEvent) {

		try {
			if (idPlanillaSurtidor == null
					|| idPlanillaSurtidor.longValue() == 0L)
				throw new GWorkException(Util
						.loadErrorMessageValue("REGISTRO.PLANILLATANQUE"));
			validarDatosNulo(surtidor, horaRevision, fechaRevision, lectura);

			activarConfirmacion = true;
			mostrarMensaje(Util.loadMessageValue("MENSAJE.ALERTA"),
					activarConfirmacion);

		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

	}

	public void modificarPlanillaSurtidor() {
		try {
			if (idPlanillaSurtidor != null) {

				schedulePumpService.modificarPlanillaSurtidor(
						idPlanillaSurtidor, surtidor, horaRevision,
						fechaRevision, lectura.toString());
				mostrarMensaje(Util.loadMessageValue("EXITO.MODIFICAR"), false);
				limpiar();
			}
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void limpiar() {

		tanque = null;
		surtidor = new Long("-1");
		horaRevision = new Long("-1");
		lectura = null;
		fechaRevision = null;
		tipoCombustible = null;
		idPlanillaSurtidor = null;
		btnCrear.setDisabled(false);
	}

	public void action_limpiar(ActionEvent actionEvent) {

		limpiar();
	}

	public String getTanque() {
		return tanque;
	}

	public void setTanque(String tanque) {
		this.tanque = tanque;
	}

	public String getTipoCombustible() {
		return tipoCombustible;
	}

	public void setTipoCombustible(String tipoCombustible) {
		this.tipoCombustible = tipoCombustible;
	}

	public void listener_surtidor(ValueChangeEvent changeEvent) {

		Long idSurtidor = (Long) changeEvent.getNewValue();
		Pumps pumps = null;
		if (idSurtidor != null) {
			try {
				pumps = pumpsService.consultarPumpPorId(idSurtidor);
				tanque = null;
				tipoCombustible = null;
				if (pumps != null) {
					tanque = pumps.getFuelTanks().getFtaNombre();
					tipoCombustible = pumps.getFuelTanks().getFuelsTypes()
							.getFutNombre();
					if (fechaRevision == null)
						fechaRevision = new Date();
				}

			} catch (GWorkException e) {
				System.out.println(e.getMessage());
			}

		}
	}

	public boolean isShowSchedulePump() {
		return showSchedulePump;
	}

	public void setShowSchedulePump(boolean showSchedulePump) {
		this.showSchedulePump = showSchedulePump;
	}

	public void action_closeSchedulePump(ActionEvent actionEvent) {
		showSchedulePump = false;
	}

	public void action_showSchedulePump(ActionEvent actionEvent) {
		showSchedulePump = true;
	}

	public Long getFiltroSurtidor() {
		return filtroSurtidor;
	}

	public void setFiltroSurtidor(Long filtroSurtidor) {
		this.filtroSurtidor = filtroSurtidor;
	}

	public Date getFiltroFechaIni() {
		return filtroFechaIni;
	}

	public void setFiltroFechaIni(Date filtroFechaIni) {
		this.filtroFechaIni = filtroFechaIni;
	}

	public Date getFiltroFechaFin() {
		return filtroFechaFin;
	}

	public void setFiltroFechaFin(Date filtroFechaFin) {
		this.filtroFechaFin = filtroFechaFin;
	}

	public void limpiarConsulta() {

		filtroFechaFin = null;
		filtroFechaIni = null;
		filtroSurtidor = null;
		setListaPlanillaSurtidor(null);
	}

	public void action_limpiarConsulta(ActionEvent actionEvent) {
		limpiarConsulta();
	}

	public void action_filtrarPlanillaSurtidor(ActionEvent actionEvent) {

		try {

			if ((filtroSurtidor == null || filtroSurtidor.longValue() == -1L)
					&& filtroFechaFin == null && filtroFechaIni == null)
				throw new GWorkException(Util
						.loadErrorMessageValue("PARAMETROBUSQUEDAD"));

			if (filtroFechaFin == null || filtroFechaIni == null)
				throw new GWorkException(Util
						.loadErrorMessageValue("FECHAS.CONSULTA.NULO"));

			if (filtroFechaFin != null && filtroFechaIni != null
					& filtroFechaFin.compareTo(filtroFechaIni) < 0)
				throw new GWorkException(Util
						.loadErrorMessageValue("FCH_INI_FCH_FIN"));

			if (filtroSurtidor != null && filtroSurtidor.longValue() == -1L
					&& filtroFechaFin != null && filtroFechaIni != null)
				throw new GWorkException(Util
						.loadErrorMessageValue("ERROR.SURTINOSELECC"));

			setListaPlanillaSurtidor(schedulePumpService.listaPlanillaSurtidor(
					filtroSurtidor, filtroFechaIni, filtroFechaFin));
		} catch (GWorkException e) {
			mostrarMensaje(e.getMessage(), false);
		}
	}

	public void rowSelectorPlanillaSurtidor(RowSelectorEvent event) {
		try {
			setListaPlanillaSurtidor(schedulePumpService.listaPlanillaSurtidor(
					filtroSurtidor, filtroFechaIni, filtroFechaFin));
			Long idRegistro = (Long) txtIdRegistroPlanilla.getValue();

			for (DialyMovementPumps dialyMovementPumps : listaPlanillaSurtidor) {

				if (idRegistro.longValue() == dialyMovementPumps.getDmpCodigo()
						.longValue()) {
					idPlanillaSurtidor = dialyMovementPumps.getDmpCodigo()
							.longValue();

					surtidor = dialyMovementPumps.getPumps().getPumCodigo()
							.longValue();
					tanque = dialyMovementPumps.getPumps().getFuelTanks()
							.getFtaNombre();
					tipoCombustible = dialyMovementPumps.getPumps()
							.getFuelTanks().getFuelsTypes().getFutNombre();
					horaRevision = dialyMovementPumps.getRevisionHour()
							.getRhoCodigo().longValue();

					fechaRevision = dialyMovementPumps.getDmpFecha();
					lectura = Util.convertirDecimalDouble(dialyMovementPumps
							.getDmpLectura().toString());
					btnCrear.setDisabled(true);
					limpiarConsulta();
					showSchedulePump = false;
					break;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<DialyMovementPumps> getListaPlanillaSurtidor() {
		return listaPlanillaSurtidor;
	}

	public void setListaPlanillaSurtidor(
			List<DialyMovementPumps> listaPlanillaSurtidor) {
		this.listaPlanillaSurtidor = listaPlanillaSurtidor;
	}

	public SchedulePumpService getSchedulePumpService() {
		return schedulePumpService;
	}

	public void setSchedulePumpService(SchedulePumpService schedulePumpService) {
		this.schedulePumpService = schedulePumpService;
	}

	public void mostrarMensaje(String mensaje, boolean buttonCancel) {
		PopupMessagePage message = (PopupMessagePage) FacesUtils
				.getManagedBean("popupMessagePage");
		if (message != null)
			message.showPopup(mensaje, buttonCancel);
	}

	public Long getIdPlanillaSurtidor() {
		return idPlanillaSurtidor;
	}

	public void setIdPlanillaSurtidor(Long idPlanillaSurtidor) {
		this.idPlanillaSurtidor = idPlanillaSurtidor;
	}

	public HtmlCommandButton getBtnCrear() {
		return btnCrear;
	}

	public void setBtnCrear(HtmlCommandButton btnCrear) {
		this.btnCrear = btnCrear;
	}

	public HtmlOutputText getTxtIdRegistroPlanilla() {
		return txtIdRegistroPlanilla;
	}

	public void setTxtIdRegistroPlanilla(HtmlOutputText txtIdRegistroPlanilla) {
		this.txtIdRegistroPlanilla = txtIdRegistroPlanilla;
	}

	public boolean isActivarConfirmacion() {
		return activarConfirmacion;
	}

	public void setActivarConfirmacion(boolean activarConfirmacion) {
		this.activarConfirmacion = activarConfirmacion;
	}

	public BigDecimal getLectura() {
		return lectura;
	}

	public void setLectura(BigDecimal lectura) {
		this.lectura = lectura;
	}

}
