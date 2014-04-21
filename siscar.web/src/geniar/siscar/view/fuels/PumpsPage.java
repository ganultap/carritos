package geniar.siscar.view.fuels;

import java.math.BigDecimal;

import geniar.siscar.logic.fuels.services.FuelTankService;
import geniar.siscar.logic.fuels.services.PumpsService;
import geniar.siscar.model.FuelTanks;
import geniar.siscar.model.Pumps;
import geniar.siscar.util.FacesUtils;
import geniar.siscar.util.PopupMessagePage;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

import javax.faces.event.ValueChangeEvent;

import com.icesoft.faces.component.ext.HtmlInputText;
import com.icesoft.faces.component.ext.HtmlSelectOneMenu;

public class PumpsPage {

	// variables generales
	private Long idFuelsTypes;
	private Long idTanque;
	private BigDecimal idPump;
	private Long capacidad;
	private String tipoCombustibleNombre;
	private String pumNombre;

	// bindings de la vista
	private HtmlSelectOneMenu selectTanque;
	private HtmlSelectOneMenu selectPumps;
	private HtmlInputText txtFuelTypes;
	private HtmlInputText txtNombreSurti;
	private HtmlInputText txtCapacidadMax;
	private boolean disCrear;
	private boolean disEliminar;
	private boolean disModificar;

	// variables para los mensajes de confirmación
	private boolean activarConfirmacion;
	private Integer opcConfirmacion;
	private static Integer MODIFICAR = 1;
	private static Integer ELIMINAR = 2;

	// servicios
	private PumpsService pumpsService;
	private FuelTankService fuelTankService;

	public PumpsPage() {
		disEliminar = true;
		disModificar = true;
	}

	public void action_crearPumpsPage(javax.faces.event.ActionEvent event) {
		try {
			validarDatosCrear();
			pumpsService.crearPump(pumNombre, idTanque);

			mostrarMensaje(Util.loadMessageValue("EXITO"), false);
			limpiar();
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void validarDatosCrear() throws GWorkException {

		if (pumNombre == null)
			throw new GWorkException(Util.loadErrorMessageValue("DATOSVACIOS"));
		
		if (pumNombre != null && pumNombre.trim().length() == 0)
			throw new GWorkException(Util.loadErrorMessageValue("NUEVO.NOMBRE.SURTIDOR"));

		if (pumNombre != null && pumNombre.trim().length() != 0)
			if (!Util.validarCadenaCaracteresEspecialesNumLetrasGuion(pumNombre))
				throw new GWorkException(Util.loadErrorMessageValue("NOMBRE.TANQUE.CARACTER"));

		if (pumNombre != null && pumNombre.trim().length() != 0)
			Util.validarLimite(pumNombre, 30, 8, "ERROR.LIMNOMBRESURTIDOR");

		if (idTanque == -1)
			throw new GWorkException(Util.loadErrorMessageValue("ERROR.TANQUENOSELECC"));

	}

	public void validarDatos() throws GWorkException {
		Long idPumps = null;
		if (idPump != null) 
			idPumps = idPump.longValue();
		
		if (pumNombre == null)
			throw new GWorkException(Util.loadErrorMessageValue("DATOSVACIOS"));			

		if (pumNombre != null && pumNombre.trim().length() != 0)
			if (!Util.validarCadenaCaracteresEspecialesNumLetrasGuion(pumNombre))
				throw new GWorkException(Util.loadErrorMessageValue("CARACTER.ESPECIAL"));

		if (pumNombre != null && pumNombre.trim().length() != 0)
			Util.validarLimite(pumNombre, 30, 8, "ERROR.LIMNOMBRESURTIDOR");

		if (idTanque == -1)
			throw new GWorkException(Util.loadErrorMessageValue("ERROR.TANQUENOSELECC"));

		if (idPumps == -1)
			throw new GWorkException(Util.loadErrorMessageValue("ERROR.SURTINOSELECC"));
	}

	public void listener_FuelTank(ValueChangeEvent event) {

		try {
			if (event.getNewValue() != null) {
				setIdTanque(new Long("" + event.getNewValue()));
				consultarTanque();
			}
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

	}

	private void consultarTanque() throws GWorkException {

		// selectPumps.setValue("-1");

		if (idTanque == -1)
			return;

		FuelTanks tanks = fuelTankService.consultarFuelTankPorID(idTanque);

		if (tanks == null)
			throw new GWorkException(Util.loadErrorMessageValue("search.not.found"));

		txtFuelTypes.setValue(tanks.getFuelsTypes().getFutNombre());
		txtCapacidadMax.setValue(tanks.getFtaCapacidadMaxima());
		selectTanque.setValue(getIdTanque());
	}

	private void consultarSurtidor() throws GWorkException {
		Long idPumps = null;
		if (idPump != null) 
			idPumps = idPump.longValue();
		
		if (idPumps == -1)
			return;

		Pumps pumps = pumpsService.consultarPumpPorId(idPumps);

		if (pumps == null)
			throw new GWorkException(Util.loadErrorMessageValue("search.not.found"));

		setIdTanque(pumps.getFuelTanks().getFtaCodigo());

		consultarTanque();

		selectPumps.setValue(pumps.getPumCodigo());
		txtNombreSurti.setValue(pumps.getPumNombre());
		disCrear = true;
		disEliminar = false;
		disModificar = false;
	}

	public void action_modificarPumpsPage(javax.faces.event.ActionEvent event) {
		try {
			setActivarConfirmacion(true);
			setOpcConfirmacion(MODIFICAR);
			mostrarMensaje(Util.loadMessageValue("MENSAJE.ALERTA"), activarConfirmacion);

		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void action_eliminarPumpsPage(javax.faces.event.ActionEvent event) {
		try {
			Long idPumps = null;
			if (idPump != null) 
				idPumps = idPump.longValue();
			
			if (idPumps == null || idPumps == -1L)
				throw new GWorkException(Util.loadErrorMessageValue("ERROR.SURTINOSELECC"));

			setActivarConfirmacion(true);
			setOpcConfirmacion(ELIMINAR);
			mostrarMensaje(Util.loadMessageValue("MENSAJE.ALERTA"), activarConfirmacion);

		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void eliminarSurtidor() {
		try {
			Long idPumps = null;
			if (idPump != null) 
				idPumps = idPump.longValue();
						
			if (idPumps == null || idPumps == -1L)
				throw new GWorkException(Util.loadErrorMessageValue("ERROR.SURTINOSELECC"));

			pumpsService.eliminarPump(idPumps);
			mostrarMensaje(Util.loadMessageValue("EXITO.ELIMINAR"), false);
			limpiar();
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void modificarPumpsPage() {
		try {
			Long idPumps = null;
			if (idPump != null) 
				idPumps = idPump.longValue();
			
			if (pumNombre == null)
				throw new GWorkException(Util.loadErrorMessageValue("DATOSVACIOS"));

			if (pumNombre != null && pumNombre.trim().length() != 0) {
				if (!Util.validarCadenaCaracteresEspecialesNumLetrasGuion(pumNombre))
					throw new GWorkException(Util.loadErrorMessageValue("NOMBRE.TANQUE.CARACTER"));

				Util.validarLimite(pumNombre, 30, 8, "ERROR.LIMNOMBRESURTIDOR");
			}
			
			if (pumNombre != null && pumNombre.trim().length() == 0)
				throw new GWorkException(Util.loadErrorMessageValue("NUEVO.NOMBRE.SURTIDOR"));
			
			if (idPumps == -1)
				throw new GWorkException(Util.loadErrorMessageValue("ERROR.SURTINOSELECC"));

			if (idTanque == -1)
				throw new GWorkException(Util.loadErrorMessageValue("ERROR.TANQUENOSELECC"));

			pumpsService.modificarPump(idTanque, idPumps, pumNombre);
			mostrarMensaje(Util.loadMessageValue("EXITO.MODIFICAR"), false);
			limpiar();
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void action_limpiarFormaPumpsPage(javax.faces.event.ActionEvent event) {
		limpiar();
	}

	private void limpiar() {
		disCrear = false;
		disEliminar = true;
		disModificar = true;
		selectTanque.setDisabled(false);
		txtFuelTypes.setValue("");
		selectTanque.setValue("-1");
		selectPumps.setValue("-1");
		txtNombreSurti.setValue("");
		txtCapacidadMax.setValue("");
		txtNombreSurti.setValue("");
	}

	public void listener_Pumps(ValueChangeEvent event) {
		try {
	
			if (event.getNewValue() != null) {
				setIdPump(new BigDecimal("" + event.getNewValue()));
				consultarSurtidor();
			}
			if (event.getNewValue().toString().equalsIgnoreCase("-1")) {
				disCrear = false;
				limpiar();
			}
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void mostrarMensaje(String mensaje, boolean buttonCancel) {
		PopupMessagePage message = (PopupMessagePage) FacesUtils.getManagedBean("popupMessagePage");
		if (message != null)
			message.showPopup(mensaje, buttonCancel);
	}

	public PumpsService getPumpsService() {
		return pumpsService;
	}

	public void setPumpsService(PumpsService pumpsService) {
		this.pumpsService = pumpsService;
	}

	public HtmlSelectOneMenu getSelectTanque() {
		return selectTanque;
	}

	public void setSelectTanque(HtmlSelectOneMenu selectTanque) {
		this.selectTanque = selectTanque;
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

	public Long getIdTanque() {
		return idTanque;
	}

	public void setIdTanque(Long idTanque) {
		this.idTanque = idTanque;
	}

	public Long getIdFuelsTypes() {
		return idFuelsTypes;
	}

	public void setIdFuelsTypes(Long idFuelsTypes) {
		this.idFuelsTypes = idFuelsTypes;
	}

	public String getPumNombre() {
		return pumNombre;
	}

	public void setPumNombre(String pumNombre) {
		this.pumNombre = pumNombre;
	}

	public HtmlInputText getTxtNombreSurti() {
		return txtNombreSurti;
	}

	public void setTxtNombreSurti(HtmlInputText txtNombreSurti) {
		this.txtNombreSurti = txtNombreSurti;
	}

	public boolean isDisCrear() {
		return disCrear;
	}

	public void setDisCrear(boolean disCrear) {
		this.disCrear = disCrear;
	}

	public FuelTankService getFuelTankService() {
		return fuelTankService;
	}

	public void setFuelTankService(FuelTankService fuelTankService) {
		this.fuelTankService = fuelTankService;
	}

	public HtmlInputText getTxtCapacidadMax() {
		return txtCapacidadMax;
	}

	public void setTxtCapacidadMax(HtmlInputText txtCapacidadMax) {
		this.txtCapacidadMax = txtCapacidadMax;
	}

	public Long getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(Long capacidad) {
		this.capacidad = capacidad;
	}

	public HtmlInputText getTxtFuelTypes() {
		return txtFuelTypes;
	}

	public void setTxtFuelTypes(HtmlInputText txtFuelTypes) {
		this.txtFuelTypes = txtFuelTypes;
	}

	public String getTipoCombustibleNombre() {
		return tipoCombustibleNombre;
	}

	public void setTipoCombustibleNombre(String tipoCombustibleNombre) {
		this.tipoCombustibleNombre = tipoCombustibleNombre;
	}

	public HtmlSelectOneMenu getSelectPumps() {
		return selectPumps;
	}

	public void setSelectPumps(HtmlSelectOneMenu selectPumps) {
		this.selectPumps = selectPumps;
	}

	public BigDecimal getIdPump() {
		return idPump;
	}

	public void setIdPump(BigDecimal idPump) {
		this.idPump = idPump;
	}

	public boolean isDisEliminar() {
		return disEliminar;
	}

	public void setDisEliminar(boolean disEliminar) {
		this.disEliminar = disEliminar;
	}

	public boolean isDisModificar() {
		return disModificar;
	}

	public void setDisModificar(boolean disModificar) {
		this.disModificar = disModificar;
	}

}
