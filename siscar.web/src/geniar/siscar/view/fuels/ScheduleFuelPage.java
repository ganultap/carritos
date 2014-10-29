package geniar.siscar.view.fuels;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import com.icesoft.faces.component.ext.HtmlCommandButton;
import com.icesoft.faces.component.ext.HtmlInputText;
import com.icesoft.faces.component.ext.HtmlOutputText;
import com.icesoft.faces.component.ext.RowSelectorEvent;
import geniar.siscar.logic.fuels.services.FuelTankService;
import geniar.siscar.logic.fuels.services.ScheduleFuelService;
import geniar.siscar.model.DailyMovementTank;
import geniar.siscar.model.FuelTanks;
import geniar.siscar.util.FacesUtils;
import geniar.siscar.util.PopupMessagePage;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

public class ScheduleFuelPage {

	private Long idRegistroTanque;
	private Long tanque;
	private Long horaRevision;
	private String entrada;
	private BigDecimal lectura;
	private Date fechaRevision;
	private String tipoCombustible;
	private boolean activarConfirmacion;
	private String valorActuarTanque;

	private Long filtroTanque;
	private Date filtroFechaIni;
	private Date filtroFechaFin;
	private HtmlInputText txtEntrada;
	private HtmlInputText txtTipoCombustible;

	private HtmlCommandButton btnCrear;

	private HtmlOutputText txtIdRegistro;

	private List<DailyMovementTank> listaPlanillaTanque;

	private boolean showScheduleTank = false;

	private FuelTankService fuelTankService;

	private ScheduleFuelService scheduleFuelService;

	public ScheduleFuelService getScheduleFuelService() {
		return scheduleFuelService;
	}

	public void setScheduleFuelService(ScheduleFuelService scheduleFuelService) {
		this.scheduleFuelService = scheduleFuelService;
	}

	public Long getTanque() {
		return tanque;
	}

	public void setTanque(Long tanque) {
		this.tanque = tanque;
	}

	public Long getHoraRevision() {
		return horaRevision;
	}

	public void setHoraRevision(Long horaRevision) {
		this.horaRevision = horaRevision;
	}

	public String getEntrada() {
		return entrada;
	}

	public void setEntrada(String entrada) {
		this.entrada = entrada;
	}

	public Date getFechaRevision() {
		return fechaRevision;
	}

	public void setFechaRevision(Date fechaRevision) {
		this.fechaRevision = fechaRevision;
	}

	public void validarDatosNulo(Long tanque, Long horaRevision,
			Date fechaRevision, BigDecimal lectura) throws GWorkException {

		if (tanque == null || tanque.longValue() == 0
				|| tanque.longValue() == -1L)
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.TANQUENOSELECC"));

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
					.loadErrorMessageValue("LECTURATANQUE.NULO"));

		if (!Util.validarNumerosParametros(lectura.toString()))
			throw new GWorkException(Util
					.loadErrorMessageValue("CARACTERNUM.LECTURA.TANQUE"));
	}

	public void action_crearPlanilla(ActionEvent actionEvent) {

		try {
			validarDatosNulo(tanque, horaRevision, fechaRevision, lectura);
			scheduleFuelService.registrarMoviminetoDiarioTanque(tanque,
					horaRevision, entrada, lectura.toString(), fechaRevision);
			mostrarMensaje(Util.loadMessageValue("EXITO"), false);
			limpiar();

		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void action_modificarPlanillaTanque(ActionEvent actionEvent) {

		try {
			if (idRegistroTanque == null || idRegistroTanque.longValue() == 0)
				throw new GWorkException(Util
						.loadErrorMessageValue("REGISTRO.PLANILLATANQUE"));
			validarDatosNulo(tanque, horaRevision, fechaRevision, lectura);
			activarConfirmacion = true;
			mostrarMensaje(Util.loadMessageValue("MENSAJE.ALERTA"),
					activarConfirmacion);
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void modificarPlanillaTanque() {

		try {
			scheduleFuelService.modificarMoviminetoDiarioTanque(
					idRegistroTanque, tanque, horaRevision, entrada, lectura
							.toString(), fechaRevision);
			mostrarMensaje(Util.loadMessageValue("EXITO.MODIFICAR"), false);
			limpiar();
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void listener_tanque(ValueChangeEvent changeEvent) {

		Long idTanque = (Long) changeEvent.getNewValue();
		FuelTanks fuelTanks = null;
		String entradaTanque = null;
		if (idTanque != null) {
			try {
				txtTipoCombustible.setValue("");
				txtEntrada.setValue("");
				fuelTanks = fuelTankService.consultarFuelTankPorID(idTanque);
				entradaTanque = scheduleFuelService.entradaTanques(idTanque,
						new Date());

				if (fechaRevision == null)
					fechaRevision = new Date();

				if (fuelTanks != null)
					txtTipoCombustible.setValue(fuelTanks.getFuelsTypes()
							.getFutNombre());
				if (fuelTanks.getFtaGalonesActuales() != null)
					valorActuarTanque = fuelTanks.getFtaGalonesActuales()
							.toString();

				if (entradaTanque != null)
					txtEntrada.setValue(entradaTanque);
			} catch (GWorkException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	public FuelTankService getFuelTankService() {
		return fuelTankService;
	}

	public void setFuelTankService(FuelTankService fuelTankService) {
		this.fuelTankService = fuelTankService;
	}

	public String getTipoCombustible() {
		return tipoCombustible;
	}

	public void setTipoCombustible(String tipoCombustible) {
		this.tipoCombustible = tipoCombustible;
	}

	public void limpiar() {

		tanque = new Long("-1");
		horaRevision = new Long("-1");
		entrada = null;
		lectura = null;
		tipoCombustible = null;
		fechaRevision = null;
		btnCrear.setDisabled(false);
		idRegistroTanque = null;
		valorActuarTanque = null;
	}

	public void action_limpiar(ActionEvent actionEvent) {

		limpiar();
	}

	public boolean isShowScheduleTank() {
		return showScheduleTank;
	}

	public void setShowScheduleTank(boolean showScheduleTank) {
		this.showScheduleTank = showScheduleTank;
	}

	public void action_ocultarScheduleTanks(ActionEvent actionEvent) {
		showScheduleTank = false;
	}

	public void action_showScheduleTanks(ActionEvent actionEvent) {
		showScheduleTank = true;
	}

	public Long getFiltroTanque() {
		return filtroTanque;
	}

	public void setFiltroTanque(Long filtroTanque) {
		this.filtroTanque = filtroTanque;
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

	public void action_limpiarConsulta(ActionEvent actionEvent) {
		limpiarConsulta();
	}

	public void limpiarConsulta() {
		filtroTanque = new Long("-1");
		filtroFechaIni = null;
		filtroFechaFin = null;
		setListaPlanillaTanque(null);
	}

	public List<DailyMovementTank> getListaPlanillaTanque() {
		return listaPlanillaTanque;
	}

	public void setListaPlanillaTanque(
			List<DailyMovementTank> listaPlanillaTanque) {
		this.listaPlanillaTanque = listaPlanillaTanque;
	}

	public void rowSelectorPlanillaTanque(RowSelectorEvent event) {

		Long idRegistro = (Long) txtIdRegistro.getValue();

		for (DailyMovementTank dailyMovementTank : listaPlanillaTanque) {

			if (idRegistro.longValue() == dailyMovementTank.getDamId()
					.longValue()) {
				idRegistroTanque = dailyMovementTank.getDamId().longValue();
				tanque = dailyMovementTank.getFuelTanks().getFtaCodigo()
						.longValue();
				horaRevision = dailyMovementTank.getRevisionHour()
						.getRhoCodigo().longValue();
				entrada = dailyMovementTank.getDamEntrada();
				fechaRevision = dailyMovementTank.getDamFecha();
				lectura = Util.convertirDecimalDouble(dailyMovementTank
						.getDamLectura().toString());

				if (dailyMovementTank.getFuelTanks().getFuelsTypes() != null)
					tipoCombustible = dailyMovementTank.getFuelTanks()
							.getFuelsTypes().getFutNombre();

				if (dailyMovementTank.getFuelTanks().getFtaGalonesActuales() != null)
					valorActuarTanque = dailyMovementTank.getFuelTanks()
							.getFtaGalonesActuales().toString();
				btnCrear.setDisabled(true);
				limpiarConsulta();
				showScheduleTank = false;

				break;
			}
		}
	}

	public void mostrarMensaje(String mensaje, boolean buttonCancel) {
		PopupMessagePage message = (PopupMessagePage) FacesUtils
				.getManagedBean("popupMessagePage");
		if (message != null)
			message.showPopup(mensaje, buttonCancel);
	}

	public void action_filtrarPlanillaTanque(ActionEvent actionEvent) {

		try {
			if ((filtroTanque == null || filtroTanque.longValue() == -1L)
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

			if (filtroTanque != null && filtroTanque.longValue() == -1L
					&& filtroFechaFin != null && filtroFechaIni != null)
				throw new GWorkException(Util
						.loadErrorMessageValue("ERROR.TANQUENOSELECC"));

			setListaPlanillaTanque(null);
			setListaPlanillaTanque(scheduleFuelService.listaPlanillaTanque(
					filtroTanque, filtroFechaIni, filtroFechaFin));

		} catch (GWorkException e) {
			mostrarMensaje(e.getMessage(), false);
		}
	}

	public HtmlOutputText getTxtIdRegistro() {
		return txtIdRegistro;
	}

	public void setTxtIdRegistro(HtmlOutputText txtIdRegistro) {
		this.txtIdRegistro = txtIdRegistro;
	}

	public HtmlCommandButton getBtnCrear() {
		return btnCrear;
	}

	public void setBtnCrear(HtmlCommandButton btnCrear) {
		this.btnCrear = btnCrear;
	}

	public boolean isActivarConfirmacion() {
		return activarConfirmacion;
	}

	public void setActivarConfirmacion(boolean activarConfirmacion) {
		this.activarConfirmacion = activarConfirmacion;
	}

	public Long getIdRegistroTanque() {
		return idRegistroTanque;
	}

	public void setIdRegistroTanque(Long idRegistroTanque) {
		this.idRegistroTanque = idRegistroTanque;
	}

	public HtmlInputText getTxtEntrada() {
		return txtEntrada;
	}

	public void setTxtEntrada(HtmlInputText txtEntrada) {
		this.txtEntrada = txtEntrada;
	}

	public HtmlInputText getTxtTipoCombustible() {
		return txtTipoCombustible;
	}

	public void setTxtTipoCombustible(HtmlInputText txtTipoCombustible) {
		this.txtTipoCombustible = txtTipoCombustible;
	}

	public String getValorActuarTanque() {
		return valorActuarTanque;
	}

	public void setValorActuarTanque(String valorActuarTanque) {
		this.valorActuarTanque = valorActuarTanque;
	}

	public void setLectura(BigDecimal lectura) {
		this.lectura = lectura;
	}

	public BigDecimal getLectura() {
		return lectura;
	}

	public void listener_fechaEntrada(ValueChangeEvent event) {

		Date fechaLectura = (Date) event.getNewValue();
		String entradaTanque = null;
		try {
			if (tanque != null && fechaLectura != null
					&& tanque.longValue() != -1L) {

				entradaTanque = scheduleFuelService.entradaTanques(tanque,
						fechaLectura);
				if (entradaTanque != null)
					this.entrada = entradaTanque;
			}
		} catch (GWorkException e) {
			e.printStackTrace();
		}
	}
}
