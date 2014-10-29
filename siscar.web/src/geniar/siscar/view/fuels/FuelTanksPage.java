package geniar.siscar.view.fuels;

import geniar.siscar.logic.fuels.services.FuelTankService;
import geniar.siscar.model.FuelTanks;
import geniar.siscar.util.FacesUtils;
import geniar.siscar.util.PopupMessagePage;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

import javax.faces.event.ValueChangeEvent;

import com.icesoft.faces.component.ext.HtmlInputText;
import com.icesoft.faces.component.ext.HtmlSelectOneMenu;

public class FuelTanksPage {

	private FuelTankService fuelTankService;
	private Float capacidad;
	private Long idFuelsTypes;
	private Long idFuelsTank;
	private String ftaNombre;
	private String ftaNombreNuevo;
	private boolean disCrear;
	private HtmlSelectOneMenu selectFuelTypes;
	private HtmlInputText txtCapacidadMax;
	private HtmlSelectOneMenu selectNombreTanque;
	private HtmlInputText txtNewNombreTanque;

	private boolean activarConfirmacion;
	private Integer opcConfirmacion;
	private static Integer MODIFICAR = 1;
	private static Integer ELIMINAR = 2;

	public void action_crearFuelTanksPage(javax.faces.event.ActionEvent event) {
		try {
			validarDatos();
			String cap = null;

			if (capacidad != null)
				cap = capacidad.toString();

			if (cap != null && cap.trim().length() != 0 && !cap.equalsIgnoreCase("0.0"))

				fuelTankService.crearFuelTanks(ftaNombreNuevo, idFuelsTypes, capacidad);

			mostrarMensaje(Util.loadMessageValue("EXITO"), false);
			limpiar();
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void action_modificarFuelTanksPage(javax.faces.event.ActionEvent event) {
		try {
			setActivarConfirmacion(true);
			setOpcConfirmacion(MODIFICAR);
			mostrarMensaje(Util.loadMessageValue("MENSAJE.ALERTA"), activarConfirmacion);

		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void action_consultarFuelTanksPage(javax.faces.event.ActionEvent event) {
		try {

			consultar();
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void action_eliminarFuelTanksPage(javax.faces.event.ActionEvent event) {
		try {
			if (idFuelsTank == null || idFuelsTank == -1)
				throw new GWorkException(Util.loadErrorMessageValue("DATOSVACIOS"));

			setActivarConfirmacion(true);
			setOpcConfirmacion(ELIMINAR);
			mostrarMensaje(Util.loadMessageValue("MENSAJE.ALERTA"), activarConfirmacion);

		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void eliminarFuelTank() {
		try {
			if (idFuelsTank == null || idFuelsTank == -1) {
				throw new GWorkException(Util.loadErrorMessageValue("DATOSVACIOS"));
			}

			fuelTankService.eliminarFuelTanks(idFuelsTank);
			mostrarMensaje(Util.loadMessageValue("EXITO.ELIMINAR"), false);
			limpiar();
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void modificarFuelTanksPage() {
		try {
			validarDatos();

			fuelTankService.modifiarFuelTanks(idFuelsTank, ftaNombreNuevo, idFuelsTypes, capacidad);

			mostrarMensaje(Util.loadMessageValue("EXITO.MODIFICAR"), false);
			limpiar();

		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void action_limpiarFormaFuelTanksPage(javax.faces.event.ActionEvent event) {
		limpiar();
	}

	private void limpiar() {
		disCrear = false;
		selectFuelTypes.setValue("-1");
		txtCapacidadMax.setValue("");
		selectNombreTanque.setDisabled(false);
		selectNombreTanque.setValue(-1);
		txtNewNombreTanque.setValue("");
	}

	public void listener_FuelTank(ValueChangeEvent event) {

		try {
			if (event.getNewValue() != null) {
				setIdFuelsTank(new Long("" + event.getNewValue()));
				consultar();
			}
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

	}

	private void consultar() throws GWorkException {

		if (idFuelsTank == -1)
			return;

		FuelTanks tanks = fuelTankService.consultarFuelTankPorID(idFuelsTank);

		if (tanks == null)
			throw new GWorkException(Util.loadErrorMessageValue("search.not.found"));

		selectNombreTanque.setDisabled(true);
		txtNewNombreTanque.setValue(tanks.getFtaNombre());

		// setIdFuelsTypes(tanks.getFuelsTypes().getFutCodigo());
		selectFuelTypes.setValue(tanks.getFuelsTypes().getFutCodigo());

		txtCapacidadMax.setValue(tanks.getFtaCapacidadMaxima());

		disCrear = true;
	}

	public void mostrarMensaje(String mensaje, boolean buttonCancel) {
		PopupMessagePage message = (PopupMessagePage) FacesUtils.getManagedBean("popupMessagePage");
		if (message != null)
			message.showPopup(mensaje, buttonCancel);
	}

	private void validarDatos() throws GWorkException {

		boolean esValido = true;
		String capMax = null;
				
		if (ftaNombreNuevo != null && ftaNombreNuevo.trim().length() == 0)
			throw new GWorkException(Util.loadErrorMessageValue("ERROR.NOMBRETAN"));

		if (ftaNombreNuevo != null && ftaNombreNuevo.trim().length() == 0 && idFuelsTank != null && idFuelsTank == -1L)
			throw new GWorkException(Util.loadErrorMessageValue("ERROR.NOMBRETANQUE"));

		if (ftaNombreNuevo != null && ftaNombreNuevo.trim().length() != 0)
			if (!Util.validarCadenaCaracteresEspecialesNumLetrasGuion(ftaNombreNuevo))
				throw new GWorkException(Util.loadErrorMessageValue("CARACTER.TANNOMBRE"));

		if (ftaNombreNuevo != null && ftaNombreNuevo.trim().length() != 0)
			Util.validarLimite(ftaNombreNuevo, 20, 2, "ERROR.TAMNUEVONOMBRETANKCOMB");

		if (idFuelsTypes == null || idFuelsTypes == -1)
			throw new GWorkException(Util.loadErrorMessageValue("ERROR.TIPOCOMSELECC"));
		
		if (capacidad != null)
			capMax = capacidad.toString();

		if (capMax != null && capMax.trim().length() != 0 && !capMax.equalsIgnoreCase("0.0")) {
			Double val = new Double(capMax);
			String val2 = String.valueOf(val.longValue());
			esValido = Util.validarNumerosParametros(val2);
			
			if (!esValido)
				throw new GWorkException(Util.loadErrorMessageValue("CARACTER.TANCAP"));
		}
		
		if (capacidad != null && capacidad < 0)
			throw new GWorkException(Util.loadErrorMessageValue("ERROR.TAMCAPACIDAD"));
		
		if (capMax != null && capMax.trim().length() != 0 && capMax.equalsIgnoreCase("0.0")) 
			throw new GWorkException(Util.loadErrorMessageValue("ERROR.TAMCAPACIDAD.CERO"));
		
		if (capMax != null && capMax.trim().length() != 0 && capMax.trim().length() < 2 && !capMax.equalsIgnoreCase("0.0"))
			throw new GWorkException(Util.loadErrorMessageValue("TANCAP.ERROR"));
		
	}

	public FuelTankService getFuelTankService() {
		return fuelTankService;
	}

	public void setFuelTankService(FuelTankService fuelTankService) {
		this.fuelTankService = fuelTankService;
	}

	public Float getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(Float capacidad) {
		this.capacidad = capacidad;
	}

	public Long getIdFuelsTypes() {
		return idFuelsTypes;
	}

	public void setIdFuelsTypes(Long idFuelsTypes) {
		this.idFuelsTypes = idFuelsTypes;
	}

	public String getFtaNombre() {
		return ftaNombre;
	}

	public void setFtaNombre(String ftaNombre) {
		this.ftaNombre = ftaNombre;
	}

	public String getFtaNombreNuevo() {
		return ftaNombreNuevo;
	}

	public void setFtaNombreNuevo(String ftaNombreNuevo) {
		this.ftaNombreNuevo = ftaNombreNuevo;
	}

	public boolean isDisCrear() {
		return disCrear;
	}

	public void setDisCrear(boolean disCrear) {
		this.disCrear = disCrear;
	}

	public HtmlSelectOneMenu getSelectFuelTypes() {
		return selectFuelTypes;
	}

	public void setSelectFuelTypes(HtmlSelectOneMenu selectFuelTypes) {
		this.selectFuelTypes = selectFuelTypes;
	}

	public HtmlInputText getTxtCapacidadMax() {
		return txtCapacidadMax;
	}

	public void setTxtCapacidadMax(HtmlInputText txtCapacidadMax) {
		this.txtCapacidadMax = txtCapacidadMax;
	}

	public HtmlInputText getTxtNewNombreTanque() {
		return txtNewNombreTanque;
	}

	public void setTxtNewNombreTanque(HtmlInputText txtNewNombreTanque) {
		this.txtNewNombreTanque = txtNewNombreTanque;
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

	public HtmlSelectOneMenu getSelectNombreTanque() {
		return selectNombreTanque;
	}

	public void setSelectNombreTanque(HtmlSelectOneMenu selectNombreTanque) {
		this.selectNombreTanque = selectNombreTanque;
	}

	public Long getIdFuelsTank() {
		return idFuelsTank;
	}

	public void setIdFuelsTank(Long idFuelsTank) {
		this.idFuelsTank = idFuelsTank;
	}
}
