package geniar.siscar.view.fuels;

import geniar.siscar.logic.fuels.services.ControlTanksService;
import geniar.siscar.logic.fuels.services.FuelTankService;
import geniar.siscar.logic.fuels.services.impl.FuelTankServiceImpl;
import geniar.siscar.model.ControlsTanks;
import geniar.siscar.model.FuelTanks;
import geniar.siscar.persistence.ControlsTanksDAO;
import geniar.siscar.util.FacesUtils;
import geniar.siscar.util.PopupMessagePage;
import geniar.siscar.util.Util;
import geniar.siscar.view.BaseBean;
import gwork.exception.GWorkException;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import com.icesoft.faces.component.ext.HtmlCommandButton;
import com.icesoft.faces.component.ext.HtmlSelectOneMenu;

public class ControlTanksPage extends BaseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long idTanque;
	private Long idFuelsTank;
	private Long idFuelsTypes;
	private Long idControlTanks;
	private Float suma;
	private Date fechaTanqueada;
	private BigDecimal cantidadGalones;
	private String valorActualTanque;
	private String galonesActuales;
	private HtmlSelectOneMenu selectNombreTanque;
	private HtmlSelectOneMenu selectFuelTypes;
	private HtmlCommandButton buttonMod;
	private boolean disCrear;
	private ControlTanksService fuelControlService;
	private FuelTankService fuelTankService;
	private boolean activarConfirmacion;
	private Integer opcConfirmacion;
	private static Integer MODIFICAR = 1;
	private static Integer ELIMINAR = 2;
	private List<ControlsTanks> listControlTnks;

	public void action_crearFuelTanksPage(javax.faces.event.ActionEvent event) {
		try {
			validarDatos();

			fuelControlService.guardarControlTanques(idTanque, fechaTanqueada,
					cantidadGalones);

			mostrarMensaje(Util.loadMessageValue("EXITO"), false);
			limpiarEvtBtn();
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void consultarControlTanks(ActionEvent event) throws GWorkException {
		try {
			listControlTnks = fuelControlService.consultarTanques();
			setListControlTnks(listControlTnks);
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void mostrarCtrlTanks(ActionEvent event) throws GWorkException {

		String idTanque = FacesUtils.getRequestParameter("idCtrlTkns");

		ControlsTanks tanks = new ControlsTanksDAO()
				.findById(new Long(idTanque));

		setGalonesActuales(tanks.getCotGalonesActuales().toString());

		setFechaTanqueada(null);
		setCantidadGalones(null);
		setValorActualTanque(null);

		if (tanks != null) {
			setIdTanque(tanks.getFuelTanks().getFtaCodigo());
			setIdFuelsTank(tanks.getFuelTanks().getFuelsTypes().getFutCodigo());
			setValorActualTanque(tanks.getFuelTanks().getFtaGalonesActuales()
					.toString());
			setIdFuelsTypes(tanks.getFuelTanks().getFuelsTypes().getFutCodigo());
			setIdControlTanks(tanks.getCotCodigo());
			desaActivarBoton();
		}
	}

	public void action_modificarControlTanksPage(
			javax.faces.event.ActionEvent event) {
		try {
			setActivarConfirmacion(true);
			setOpcConfirmacion(MODIFICAR);
			mostrarMensaje(Util.loadMessageValue("MENSAJE.ALERTA"),
					activarConfirmacion);

		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void action_eliminarFuelTanksPage(javax.faces.event.ActionEvent event) {
		try {
			if (idFuelsTank != null && idFuelsTank.equals(-1L))
				throw new GWorkException(Util
						.loadErrorMessageValue("CTRL.TANK.SEL"));

			setActivarConfirmacion(true);
			setOpcConfirmacion(ELIMINAR);
			mostrarMensaje(Util.loadMessageValue("MENSAJE.ALERTA"),
					activarConfirmacion);

		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void action_modificarFuelTanksPage() {
		try {

			String cantGalones = null;

			if (cantidadGalones != null)
				cantGalones = cantidadGalones.toString();

			if (cantGalones != null && cantGalones.trim().length() != 0
					&& getGalonesActuales() != null
					&& getGalonesActuales().trim().length() != 0) {

				Double valCantidad = new Double(cantidadGalones.toString());
				long valCantidadStr = valCantidad.longValue();

				Double valActuales = new Double(getGalonesActuales().toString());
				long valActualesStr = valActuales.longValue();

				long total = valCantidadStr - valActualesStr;

				suma = new Float(String.valueOf(total));

			}
			validarDatos();
			fuelControlService.actualizarControl(idControlTanks, idTanque,
					fechaTanqueada, suma, cantidadGalones);
			limpiarEvtBtn();
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void action_eliminarFuelTanksPage() {
		try {
			fuelControlService.eliminarControl(idTanque);
			limpiarEvtBtn();
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void listener_FuelTank(ValueChangeEvent event) {
		try {
			if (event.getNewValue() != null) {

				String value = event.getNewValue().toString();

				setIdFuelsTank(new Long(value));
				consultar();
			}
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void consultarCantidadGalones(ValueChangeEvent event)
			throws GWorkException {
		try {
			boolean isValid = true;
			FuelTanks fuelTanks = null;
			Float galonesActuales = null;
			Float valor = null;
			FuelTankService fuelTankService = null;
			if (event.getNewValue() != null
					&& event.getNewValue().toString().trim().length() != 0) {
				isValid = Util.validarNumerosConsulta(event.getNewValue()
						.toString().trim());
				if (!isValid)
					throw new GWorkException(Util
							.loadErrorMessageValue("CTRL.CAMP.MAX"));

				valor = new Float(event.getNewValue().toString());

				if (idTanque != null && !idTanque.equals(-1L)) {

					fuelTankService = new FuelTankServiceImpl();
					fuelTanks = fuelTankService
							.consultarFuelTankPorID(idTanque);
					galonesActuales = fuelControlService
							.consultarGalonesActuales(valor, idTanque);

					if (galonesActuales != null
							&& galonesActuales <= fuelTanks
									.getFtaCapacidadMaxima()) {
						setGalonesActuales(galonesActuales.toString());
						activarBoton();
					} else {
						setGalonesActuales(null);
						throw new GWorkException(Util
								.loadErrorMessageValue("CTRL.TAM.CAP"));
					}
				}
			} else
				setGalonesActuales(null);
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	private void consultar() throws GWorkException {
		ControlsTanks controlsTanks = null;
		FuelTanks fuelTanks = null;
		FuelTankService fuelTankService = null;
		if (idFuelsTank == -1)
			eventSel();

		fuelTankService = new FuelTankServiceImpl();
		if (idFuelsTank != -1) {
			controlsTanks = fuelControlService
					.consultarControlTanquePorIdTanque(idFuelsTank);

			if (controlsTanks != null) {
				selectFuelTypes.setValue(controlsTanks.getFuelTanks()
						.getFuelsTypes().getFutCodigo());

				setValorActualTanque(controlsTanks.getFuelTanks()
						.getFtaGalonesActuales().toString());

				setIdControlTanks(controlsTanks.getCotCodigo());
			} else {
				fuelTanks = fuelTankService.consultarFuelTankPorID(idFuelsTank);
				selectFuelTypes.setValue(fuelTanks.getFuelsTypes()
						.getFutCodigo());
				activarBoton();
				limpiarSelect();
			}
		}
	}

	public void eventSel() throws GWorkException {
		activarBoton();
		limpiar();
	}

	public void mostrarMensaje(String mensaje, boolean buttonCancel) {
		PopupMessagePage message = (PopupMessagePage) FacesUtils
				.getManagedBean("popupMessagePage");
		if (message != null)
			message.showPopup(mensaje, buttonCancel);
	}

	public void validarDatos() throws GWorkException {

		boolean isValid = true;
		Date date = new Date();
		String fechaTanq = null;
		String cantidadGal = null;
		String fechaActual = Util.formatDate(date, "dd-MMM-yyyy");

		if (cantidadGalones != null)
			cantidadGal = cantidadGalones.toString();

		// if (cantidadGalones < 0)
		// throw new
		// GWorkException(Util.loadErrorMessageValue("CTRL.CANTIDAD.NOVALIDO"));

		if (fechaTanqueada != null)
			fechaTanq = Util.formatDate(fechaTanqueada, "dd-MMM-yyyy");

		if (idFuelsTank != null && idFuelsTank.equals(-1L))
			throw new GWorkException(Util
					.loadErrorMessageValue("CTRL.TANK.SEL"));

		if (cantidadGal == null || cantidadGal.trim().length() == 0
				|| new Double(cantidadGal).doubleValue() == 0.00)
			throw new GWorkException(Util
					.loadErrorMessageValue("CTRL.CANTIDAD.VACIO"));

		if (fechaTanq != null && fechaTanq.compareTo(fechaActual) < 0)
			throw new GWorkException(Util
					.loadErrorMessageValue("CTRL.FECHATANQUEADA.ERROR"));

		if (fechaTanqueada == null)
			throw new GWorkException(Util
					.loadErrorMessageValue("CTRL.FECHA.VACIO"));

		if (cantidadGal != null && cantidadGal.trim().length() == 0)
			throw new GWorkException(Util
					.loadErrorMessageValue("CTRL.CANTIDAD.VACIO"));

		if (cantidadGal != null && cantidadGal.trim().length() != 0) {
			Double val = new Double(cantidadGal);
			String val2 = String.valueOf(val.longValue());
			isValid = Util.validarNumerosParametros(val2);
		}

		if (!isValid)
			throw new GWorkException(Util
					.loadErrorMessageValue("CTRL.CARACTER.ERROR"));

		if (cantidadGal != null && cantidadGal.trim().length() < 1)
			throw new GWorkException(Util
					.loadErrorMessageValue("CTRL.CANTINDAD.MAX"));
	}

	public void desaActivarBoton() throws GWorkException {
		if (buttonMod != null)
			buttonMod.setDisabled(false);

		disCrear = true;
	}

	public void activarBoton() throws GWorkException {
		disCrear = false;
	}

	public void limpiar_action(ActionEvent event) throws GWorkException {
		limpiarEvtBtn();
	}

	public void limpiarSelect() throws GWorkException {
		setFechaTanqueada(null);
		setCantidadGalones(null);
		setGalonesActuales(null);
		setValorActualTanque(null);
		setListControlTnks(null);
	}

	public void limpiarEvtBtn() throws GWorkException {
		if (selectNombreTanque != null)
			selectNombreTanque.setValue(-1L);

		setIdFuelsTank(-1L);
		setFechaTanqueada(null);
		setCantidadGalones(null);
		setGalonesActuales(null);
		setValorActualTanque(null);
		setIdFuelsTypes(-1L);
		setListControlTnks(null);
		activarBoton();
	}

	public void limpiar() throws GWorkException {
		setFechaTanqueada(null);
		setCantidadGalones(null);
		setGalonesActuales(null);
		setValorActualTanque(null);
		setIdFuelsTypes(-1L);
		setListControlTnks(null);
	}

	public Long getIdTanque() {
		return idTanque;
	}

	public void setIdTanque(Long idTanque) {
		this.idTanque = idTanque;
	}

	public Date getFechaTanqueada() {
		return fechaTanqueada;
	}

	public void setFechaTanqueada(Date fechaTanqueada) {
		this.fechaTanqueada = fechaTanqueada;
	}

	public BigDecimal getCantidadGalones() {
		return cantidadGalones;
	}

	public void setCantidadGalones(BigDecimal cantidadGalones) {
		this.cantidadGalones = cantidadGalones;
	}

	public HtmlSelectOneMenu getSelectNombreTanque() {
		return selectNombreTanque;
	}

	public void setSelectNombreTanque(HtmlSelectOneMenu selectNombreTanque) {
		this.selectNombreTanque = selectNombreTanque;
	}

	public String getValorActualTanque() {
		return valorActualTanque;
	}

	public void setValorActualTanque(String valorActualTanque) {
		this.valorActualTanque = valorActualTanque;
	}

	public boolean isDisCrear() {
		return disCrear;
	}

	public void setDisCrear(boolean disCrear) {
		this.disCrear = disCrear;
	}

	public ControlTanksService getFuelControlService() {
		return fuelControlService;
	}

	public void setFuelControlService(ControlTanksService fuelControlService) {
		this.fuelControlService = fuelControlService;
	}

	public FuelTankService getFuelTankService() {
		return fuelTankService;
	}

	public void setFuelTankService(FuelTankService fuelTankService) {
		this.fuelTankService = fuelTankService;
	}

	public Long getIdFuelsTank() {
		return idFuelsTank;
	}

	public void setIdFuelsTank(Long idFuelsTank) {
		this.idFuelsTank = idFuelsTank;
	}

	public HtmlSelectOneMenu getSelectFuelTypes() {
		return selectFuelTypes;
	}

	public void setSelectFuelTypes(HtmlSelectOneMenu selectFuelTypes) {
		this.selectFuelTypes = selectFuelTypes;
	}

	public Long getIdFuelsTypes() {
		return idFuelsTypes;
	}

	public void setIdFuelsTypes(Long idFuelsTypes) {
		this.idFuelsTypes = idFuelsTypes;
	}

	public String getGalonesActuales() {
		return galonesActuales;
	}

	public void setGalonesActuales(String galonesActuales) {
		this.galonesActuales = galonesActuales;
	}

	public Long getIdControlTanks() {
		return idControlTanks;
	}

	public void setIdControlTanks(Long idControlTanks) {
		this.idControlTanks = idControlTanks;
	}

	public boolean isActivarConfirmacion() {
		return activarConfirmacion;
	}

	public void setActivarConfirmacion(boolean activarConfirmacion) {
		this.activarConfirmacion = activarConfirmacion;
	}

	public Integer getOpcConfirmacion() {
		return opcConfirmacion;
	}

	public void setOpcConfirmacion(Integer opcConfirmacion) {
		this.opcConfirmacion = opcConfirmacion;
	}

	public static Integer getMODIFICAR() {
		return MODIFICAR;
	}

	public static void setMODIFICAR(Integer modificar) {
		MODIFICAR = modificar;
	}

	public static Integer getELIMINAR() {
		return ELIMINAR;
	}

	public static void setELIMINAR(Integer eliminar) {
		ELIMINAR = eliminar;
	}

	public List<ControlsTanks> getListControlTnks() {
		return listControlTnks;
	}

	public void setListControlTnks(List<ControlsTanks> listControlTnks) {
		this.listControlTnks = listControlTnks;
	}

	public Float getSuma() {
		return suma;
	}

	public void setSuma(Float suma) {
		this.suma = suma;
	}

	public HtmlCommandButton getButtonMod() {
		return buttonMod;
	}

	public void setButtonMod(HtmlCommandButton buttonMod) {
		this.buttonMod = buttonMod;
	}

}
