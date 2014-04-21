package geniar.siscar.view.parameters;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import com.icesoft.faces.component.ext.HtmlInputText;
import com.icesoft.faces.component.ext.HtmlOutputLabel;
import com.icesoft.faces.component.ext.HtmlSelectOneMenu;
import com.icesoft.faces.component.selectinputdate.SelectInputDate;
import geniar.siscar.logic.parameters.services.TariffService;
import geniar.siscar.util.FacesUtils;
import geniar.siscar.util.ManipulacionFechas;
import geniar.siscar.util.ParametersUtil;
import geniar.siscar.util.PopupMessagePage;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;
import geniar.siscar.logic.parameters.services.LocationService;
import geniar.siscar.model.Tariffs;
import geniar.siscar.logic.parameters.services.TarifaAutoSeguroService;
import geniar.siscar.logic.parameters.services.TarifaMantenimientoService;
import geniar.siscar.logic.parameters.services.TarifaDepreciacionService;

/**
 * The Class TarriffPage.
 */
public class TarriffPage {

	/** The id line. */
	private Long idLine;
	
	/** The id tipo tarifa. */
	private Long idTipoTarifa;
	
	/** The id tipo traccion. */
	private Long idTipoTraccion;
	
	/** The id fuel types. */
	private Long idFuelTypes;
	
	/** The id location. */
	private Long idLocation;
	
	/** The id marca. */
	private Long idMarca;
	
	/** The anho vigencia. */
	private Integer anhoVigencia;
	
	/** The fecha inicio. */
	private Date fechaInicio;
	
	/** The valor depreciacion. */
	private Float valorDepreciacion;
	
	/** The valor mantenimiento. */
	private Float valorMantenimiento;
	
	/** The valor autoseguro. */
	private Float valorAutoseguro;

	/** The valor espacio fisico. */
	private Float valorEspacioFisico;
	
	/** The valor total. */
	private Float valorTotal;
	
	/** The total valor anho. */
	private Float totalValorAnho;

	/** The txt valor depreciacio. */
	private HtmlInputText txtValorDepreciacio;
	
	/** The txt valor anho. */
	private HtmlInputText txtValorAnho;
	
	/** The txt valor autoseguro. */
	private HtmlInputText txtValorAutoseguro;
	
	/** The txt valor mantenimiento. */
	private HtmlInputText txtValorMantenimiento;
	
	/** The txt valor espacio fisico. */
	private HtmlInputText txtValorEspacioFisico;
	
	/** The txt valor total. */
	private HtmlInputText txtValorTotal;
	
	/** The txt periodo. */
	private HtmlInputText txtPeriodo;
	
	/** The txt valor tarifa. */
	private HtmlOutputLabel txtValorTarifa;
	
	/** The txt fecha ini. */
	private SelectInputDate txtFechaIni;
	
	/** The cbx line. */
	private HtmlSelectOneMenu cbxLine;
	
	/** The cbx tipo traccion. */
	private HtmlSelectOneMenu cbxTipoTraccion;
	
	/** The cbx fuel types. */
	private HtmlSelectOneMenu cbxFuelTypes;
	
	/** The cbx tipo tarifa. */
	private HtmlSelectOneMenu cbxTipoTarifa;
	
	/** The cbx marca. */
	private HtmlSelectOneMenu cbxMarca;
	
	/** The cbx tipo ubicacion. */
	private HtmlSelectOneMenu cbxTipoUbicacion;
	
	/** The tariff service. */
	private TariffService tariffService;
	
	/** The location service. */
	private LocationService locationService;
	
	/** The tarifa auto seguro service. */
	private TarifaAutoSeguroService tarifaAutoSeguroService;
	
	/** The tarifa depreciacion service. */
	private TarifaDepreciacionService tarifaDepreciacionService;
	
	/** The tarifa mantenimiento service. */
	private TarifaMantenimientoService tarifaMantenimientoService;
	
	/** The activar confirmacion. */
	private boolean activarConfirmacion;

	/**
	 * Gets the id line.
	 *
	 * @return the id line
	 */
	public Long getIdLine() {
		return idLine;
	}

	/**
	 * Sets the id line.
	 *
	 * @param idLine the new id line
	 */
	public void setIdLine(Long idLine) {
		this.idLine = idLine;
	}

	/**
	 * Gets the id tipo traccion.
	 *
	 * @return the id tipo traccion
	 */
	public Long getIdTipoTraccion() {
		return idTipoTraccion;
	}

	/**
	 * Sets the id tipo traccion.
	 *
	 * @param idTipoTraccion the new id tipo traccion
	 */
	public void setIdTipoTraccion(Long idTipoTraccion) {
		this.idTipoTraccion = idTipoTraccion;
	}

	/**
	 * Gets the id fuel types.
	 *
	 * @return the id fuel types
	 */
	public Long getIdFuelTypes() {
		return idFuelTypes;
	}

	/**
	 * Sets the id fuel types.
	 *
	 * @param idFuelTypes the new id fuel types
	 */
	public void setIdFuelTypes(Long idFuelTypes) {
		this.idFuelTypes = idFuelTypes;
	}

	/**
	 * Gets the id location.
	 *
	 * @return the id location
	 */
	public Long getIdLocation() {
		return idLocation;
	}

	/**
	 * Sets the id location.
	 *
	 * @param idLocation the new id location
	 */
	public void setIdLocation(Long idLocation) {
		this.idLocation = idLocation;
	}

	/**
	 * Gets the fecha inicio.
	 *
	 * @return the fecha inicio
	 */
	public Date getFechaInicio() {
		return fechaInicio;
	}

	/**
	 * Sets the fecha inicio.
	 *
	 * @param fechaInicio the new fecha inicio
	 */
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	/**
	 * Gets the txt fecha ini.
	 *
	 * @return the txt fecha ini
	 */
	public SelectInputDate getTxtFechaIni() {
		return txtFechaIni;
	}

	/**
	 * Sets the txt fecha ini.
	 *
	 * @param txtFechaIni the new txt fecha ini
	 */
	public void setTxtFechaIni(SelectInputDate txtFechaIni) {
		this.txtFechaIni = txtFechaIni;
	}

	/**
	 * Gets the cbx line.
	 *
	 * @return the cbx line
	 */
	public HtmlSelectOneMenu getCbxLine() {
		return cbxLine;
	}

	/**
	 * Sets the cbx line.
	 *
	 * @param cbxLine the new cbx line
	 */
	public void setCbxLine(HtmlSelectOneMenu cbxLine) {
		this.cbxLine = cbxLine;
	}

	/**
	 * Gets the cbx tipo traccion.
	 *
	 * @return the cbx tipo traccion
	 */
	public HtmlSelectOneMenu getCbxTipoTraccion() {
		return cbxTipoTraccion;
	}

	/**
	 * Sets the cbx tipo traccion.
	 *
	 * @param cbxTipoTraccion the new cbx tipo traccion
	 */
	public void setCbxTipoTraccion(HtmlSelectOneMenu cbxTipoTraccion) {
		this.cbxTipoTraccion = cbxTipoTraccion;
	}

	/**
	 * Gets the cbx fuel types.
	 *
	 * @return the cbx fuel types
	 */
	public HtmlSelectOneMenu getCbxFuelTypes() {
		return cbxFuelTypes;
	}

	/**
	 * Sets the cbx fuel types.
	 *
	 * @param cbxFuelTypes the new cbx fuel types
	 */
	public void setCbxFuelTypes(HtmlSelectOneMenu cbxFuelTypes) {
		this.cbxFuelTypes = cbxFuelTypes;
	}

	/**
	 * Gets the cbx tipo tarifa.
	 *
	 * @return the cbx tipo tarifa
	 */
	public HtmlSelectOneMenu getCbxTipoTarifa() {
		return cbxTipoTarifa;
	}

	/**
	 * Sets the cbx tipo tarifa.
	 *
	 * @param cbxTipoTarifa the new cbx tipo tarifa
	 */
	public void setCbxTipoTarifa(HtmlSelectOneMenu cbxTipoTarifa) {
		this.cbxTipoTarifa = cbxTipoTarifa;
	}

	/**
	 * Gets the tariff service.
	 *
	 * @return the tariff service
	 */
	public TariffService getTariffService() {
		return tariffService;
	}

	/**
	 * Sets the tariff service.
	 *
	 * @param tariffService the new tariff service
	 */
	public void setTariffService(TariffService tariffService) {
		this.tariffService = tariffService;
	}

	/**
	 * Gets the id marca.
	 *
	 * @return the id marca
	 */
	public Long getIdMarca() {
		return idMarca;
	}

	/**
	 * Sets the id marca.
	 *
	 * @param idMarca the new id marca
	 */
	public void setIdMarca(Long idMarca) {
		this.idMarca = idMarca;
	}

	/**
	 * Gets the cbx marca.
	 *
	 * @return the cbx marca
	 */
	public HtmlSelectOneMenu getCbxMarca() {
		return cbxMarca;
	}

	/**
	 * Sets the cbx marca.
	 *
	 * @param cbxMarca the new cbx marca
	 */
	public void setCbxMarca(HtmlSelectOneMenu cbxMarca) {
		this.cbxMarca = cbxMarca;
	}

	/**
	 * Gets the cbx tipo ubicacion.
	 *
	 * @return the cbx tipo ubicacion
	 */
	public HtmlSelectOneMenu getCbxTipoUbicacion() {
		return cbxTipoUbicacion;
	}

	/**
	 * Sets the cbx tipo ubicacion.
	 *
	 * @param cbxTipoUbicacion the new cbx tipo ubicacion
	 */
	public void setCbxTipoUbicacion(HtmlSelectOneMenu cbxTipoUbicacion) {
		this.cbxTipoUbicacion = cbxTipoUbicacion;
	}

	/**
	 * Gets the anho vigencia.
	 *
	 * @return the anho vigencia
	 */
	public Integer getAnhoVigencia() {
		return anhoVigencia;
	}

	/**
	 * Sets the anho vigencia.
	 *
	 * @param anhoVigencia the new anho vigencia
	 */
	public void setAnhoVigencia(Integer anhoVigencia) {
		this.anhoVigencia = anhoVigencia;
	}

	/**
	 * Gets the txt periodo.
	 *
	 * @return the txt periodo
	 */
	public HtmlInputText getTxtPeriodo() {
		return txtPeriodo;
	}

	/**
	 * Sets the txt periodo.
	 *
	 * @param txtPeriodo the new txt periodo
	 */
	public void setTxtPeriodo(HtmlInputText txtPeriodo) {
		this.txtPeriodo = txtPeriodo;
	}

	/**
	 * Limpiar.
	 */
	public void limpiar() {

		txtFechaIni.setValue(null);
		setFechaInicio(null);
		setValorTotal(null);
		valorTotal = null;
		valorAutoseguro = null;
		valorMantenimiento = null;
		valorDepreciacion = null;
		totalValorAnho = null;
		setTotalValorAnho(null);
		txtPeriodo.setValue("");
		txtValorAutoseguro.setValue("");
		txtValorDepreciacio.setValue("");
		txtValorMantenimiento.setValue("");
		txtValorEspacioFisico.setValue("");
		
		cbxFuelTypes.setValue(new Long("-1"));
		cbxLine.setValue(new Long("-1"));
		cbxMarca.setValue(new Long("-1"));
		cbxTipoTraccion.setValue(new Long("-1"));
	}

	/**
	 * Action_limpiar.
	 *
	 * @param event the event
	 */
	public void action_limpiar(ActionEvent event) {
		limpiar();
	}

	/**
	 * Action_crear tarifa asignacion.
	 *
	 * @param event the event
	 */
	public void action_crearTarifaAsignacion(ActionEvent event) {

		try {
			/** Validar que se un numero positivo */
			validateData(idFuelTypes, idTipoTraccion, idMarca, idLine,
					anhoVigencia, fechaInicio, valorAutoseguro,
					valorMantenimiento, valorDepreciacion, valorEspacioFisico);
			
			validarValor(valorDepreciacion.toString());
			validarValor(valorAutoseguro.toString());
			validarValor(valorMantenimiento.toString());
			validarValor(valorEspacioFisico.toString());
			validarTamanhoPeriodo(anhoVigencia.toString());
			ManipulacionFechas.validarAnhoFecha(fechaInicio, "");

			activarConfirmacion = true;
			mostrarMensaje(Util.loadMessageValue("MENSAJE.ALERTA"),
					activarConfirmacion);

		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	/**
	 * Modificar tarifa.
	 */
	public void modificarTarifa() {

		try {

			if (valorDepreciacion != null)
				tarifaDepreciacionService.modificarTarifa(idLine,
						idTipoTraccion, idFuelTypes, anhoVigencia, fechaInicio,
						new BigDecimal(valorDepreciacion));

			if (valorAutoseguro != null)
				tarifaAutoSeguroService.modificarTarifa(idLine, idTipoTraccion,
						idFuelTypes, anhoVigencia, fechaInicio, new BigDecimal(
								valorAutoseguro));

			if (valorMantenimiento != null)
				tarifaMantenimientoService.modificarTarifa(idLine,
						idTipoTraccion, idFuelTypes, anhoVigencia, fechaInicio,
						new BigDecimal(valorMantenimiento));
			
			if (valorEspacioFisico != null)
				tariffService.modificarTarifa(idLine,
						idTipoTraccion, idFuelTypes, anhoVigencia, fechaInicio,
						new BigDecimal(valorEspacioFisico), ParametersUtil.TARIFA_ASIGNACION_ESPACIO_FISICO);

			limpiar();
			mostrarMensaje(Util.loadMessageValue("EXITO"), false);
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

	}

	/**
	 * Mostrar mensaje.
	 *
	 * @param mensaje the mensaje
	 * @param buttonCancel the button cancel
	 */
	public void mostrarMensaje(String mensaje, boolean buttonCancel) {
		PopupMessagePage message = (PopupMessagePage) FacesUtils
				.getManagedBean("popupMessagePage");
		if (message != null)
			message.showPopup(mensaje, buttonCancel);
	}

	/**
	 * Action_consultar.
	 *
	 * @param event the event
	 */
	public void action_consultar(ActionEvent event) {

		try {

			validarDatosConsulta(idFuelTypes, idTipoTraccion, idLine);
			Tariffs tariffsDepreciacion = tarifaDepreciacionService
					.consultarTarifaAsignacionDepreciacion(idLine, idFuelTypes,
							idTipoTraccion);

			Tariffs tariffsAutoSeguro = tarifaAutoSeguroService
					.consultarTarifaAsignacionAutoseguro(idLine, idFuelTypes,
							idTipoTraccion);

			Tariffs tariffsMantenimiento = tarifaMantenimientoService
					.consultarTarifaAsignacionMantenimiento(idLine,
							idFuelTypes, idTipoTraccion);
			
			Tariffs tarifaEspacioFisico = tariffService.consultarTarifaAsignacionEspacioFisico(
					idLine,idFuelTypes, idTipoTraccion);

			/** Parametros generales de busqueda para las tarifas */
			Date periodo = tariffsDepreciacion.getTrfAñoVigencia();
			Calendar calendario = Calendar.getInstance();
			calendario.setTime(periodo); // fecha es el Date de antes.
			int anho = calendario.get(Calendar.YEAR);

			txtFechaIni.setValue(tariffsDepreciacion.getTrfFechaInicio());
			txtPeriodo.setValue(anho);

			/** Valores para cada una de las tarifas* */
			txtValorDepreciacio.setValue(new BigDecimal(tariffsDepreciacion
					.getTrfValor()));
			txtValorAutoseguro.setValue(new BigDecimal(tariffsAutoSeguro
					.getTrfValor()));
			txtValorMantenimiento.setValue(new BigDecimal(tariffsMantenimiento
					.getTrfValor()));
			
			if(tarifaEspacioFisico != null){
				txtValorEspacioFisico.setValue(
						new BigDecimal(tarifaEspacioFisico.getTrfValor()));
			}
			
			setValorTotal(totalTarifa());
			setTotalValorAnho(totalTarifa() * new Float(12));

		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	/**
	 * Gets the location service.
	 *
	 * @return the location service
	 */
	public LocationService getLocationService() {
		return locationService;
	}

	/**
	 * Sets the location service.
	 *
	 * @param locationService the new location service
	 */
	public void setLocationService(LocationService locationService) {
		this.locationService = locationService;
	}

	/**
	 * Gets the tarifa auto seguro service.
	 *
	 * @return the tarifa auto seguro service
	 */
	public TarifaAutoSeguroService getTarifaAutoSeguroService() {
		return tarifaAutoSeguroService;
	}

	/**
	 * Sets the tarifa auto seguro service.
	 *
	 * @param tarifaAutoSeguroService the new tarifa auto seguro service
	 */
	public void setTarifaAutoSeguroService(
			TarifaAutoSeguroService tarifaAutoSeguroService) {
		this.tarifaAutoSeguroService = tarifaAutoSeguroService;
	}

	/**
	 * Gets the tarifa depreciacion service.
	 *
	 * @return the tarifa depreciacion service
	 */
	public TarifaDepreciacionService getTarifaDepreciacionService() {
		return tarifaDepreciacionService;
	}

	/**
	 * Sets the tarifa depreciacion service.
	 *
	 * @param tarifaDepreciacionService the new tarifa depreciacion service
	 */
	public void setTarifaDepreciacionService(
			TarifaDepreciacionService tarifaDepreciacionService) {
		this.tarifaDepreciacionService = tarifaDepreciacionService;
	}

	/**
	 * Gets the tarifa mantenimiento service.
	 *
	 * @return the tarifa mantenimiento service
	 */
	public TarifaMantenimientoService getTarifaMantenimientoService() {
		return tarifaMantenimientoService;
	}

	/**
	 * Sets the tarifa mantenimiento service.
	 *
	 * @param tarifaMantenimientoService the new tarifa mantenimiento service
	 */
	public void setTarifaMantenimientoService(
			TarifaMantenimientoService tarifaMantenimientoService) {
		this.tarifaMantenimientoService = tarifaMantenimientoService;
	}

	/**
	 * Gets the id tipo tarifa.
	 *
	 * @return the id tipo tarifa
	 */
	public Long getIdTipoTarifa() {
		return idTipoTarifa;
	}

	/**
	 * Sets the id tipo tarifa.
	 *
	 * @param idTipoTarifa the new id tipo tarifa
	 */
	public void setIdTipoTarifa(Long idTipoTarifa) {
		this.idTipoTarifa = idTipoTarifa;
	}

	/**
	 * Gets the txt valor tarifa.
	 *
	 * @return the txt valor tarifa
	 */
	public HtmlOutputLabel getTxtValorTarifa() {
		return txtValorTarifa;
	}

	/**
	 * Sets the txt valor tarifa.
	 *
	 * @param txtValorTarifa the new txt valor tarifa
	 */
	public void setTxtValorTarifa(HtmlOutputLabel txtValorTarifa) {
		this.txtValorTarifa = txtValorTarifa;
	}

	/**
	 * Validar valor.
	 *
	 * @param valor the valor
	 * @throws GWorkException the g work exception
	 */
	public void validarValor(String valor) throws GWorkException {
		boolean esValido = true;

		esValido = Util.validarNumerosParametros(valor);

		if (!esValido)
			throw new GWorkException(Util
					.loadErrorMessageValue("CARACTER.NUMERO"));

	}

	/**
	 * Validate data.
	 *
	 * @param idTipoCombustible the id tipo combustible
	 * @param idTipoTraccion the id tipo traccion
	 * @param idMarca the id marca
	 * @param idLine the id line
	 * @param anhoVigencia the anho vigencia
	 * @param fechaInicio the fecha inicio
	 * @param valorAutoseguro the valor autoseguro
	 * @param valorMantimiento the valor mantimiento
	 * @param valorDepreciacion the valor depreciacion
	 * @param valorEspacioFisico the valor EspacioFisico 
	 * @throws GWorkException the g work exception
	 */
	void validateData(Long idTipoCombustible, Long idTipoTraccion,
			Long idMarca, Long idLine, Integer anhoVigencia, Date fechaInicio,
			Float valorAutoseguro, Float valorMantimiento,
			Float valorDepreciacion, Float valorEspacioFisico) throws GWorkException {

		if (idTipoCombustible == null
				|| idTipoCombustible.longValue() == new Long("-1"))
			throw new GWorkException(Util.loadErrorMessageValue("TIPOCOM.SEL"));

		if (idTipoTraccion == null
				|| idTipoTraccion.longValue() == new Long("-1"))
			throw new GWorkException(Util.loadErrorMessageValue("TRACCION"));

		if (idMarca == null || idMarca.longValue() == -1L
				|| idMarca.longValue() == 0L)
			throw new GWorkException(Util.loadErrorMessageValue("MARCA.SEL"));

		if (idLine == null || idLine.longValue() == new Long("-1")
				|| idLine.longValue() == 0L)
			throw new GWorkException(Util.loadErrorMessageValue("LINEA.SEL"));

		if (anhoVigencia == null || anhoVigencia.longValue() == 0)
			throw new GWorkException(Util.loadErrorMessageValue("FECHAVALIDAN"));

		if (fechaInicio == null)
			throw new GWorkException(Util
					.loadErrorMessageValue("FECHAVIGENCIA"));

		if (valorAutoseguro == null || valorAutoseguro.floatValue() == 0F)
			throw new GWorkException(Util
					.loadErrorMessageValue("VALORAUTOSEGURO"));

		if (valorDepreciacion == null || valorDepreciacion.floatValue() == 0F)
			throw new GWorkException(Util
					.loadErrorMessageValue("VALORDEPRECIACION"));

		if (valorMantimiento == null || valorMantimiento.floatValue() == 0F)
			throw new GWorkException(Util
					.loadErrorMessageValue("VALORMANTENIMIENTO"));
		
		if (valorEspacioFisico == null || valorEspacioFisico.floatValue() == 0F)
			throw new GWorkException(Util
					.loadErrorMessageValue("VALORESPACIOFISICO"));
	}

	/**
	 * Validar datos consulta.
	 *
	 * @param idTipoCombustible the id tipo combustible
	 * @param idTipoTraccion the id tipo traccion
	 * @param idLine the id line
	 * @throws GWorkException the g work exception
	 */
	public void validarDatosConsulta(Long idTipoCombustible,
			Long idTipoTraccion, Long idLine) throws GWorkException {

		if (idTipoCombustible == null
				|| idTipoCombustible.longValue() == new Long("-1"))
			throw new GWorkException(Util.loadErrorMessageValue("TIPOCOM.SEL"));

		if (idTipoTraccion == null
				|| idTipoTraccion.longValue() == new Long("-1"))
			throw new GWorkException(Util.loadErrorMessageValue("TRACCION"));

		if (idMarca == null || idMarca.longValue() == -1L
				|| idMarca.longValue() == 0L)
			throw new GWorkException(Util.loadErrorMessageValue("MARCA.SEL"));

		if (idLine == null || idLine.longValue() == new Long("-1")
				|| idLine.longValue() == 0L)
			throw new GWorkException(Util.loadErrorMessageValue("LINEA.SEL"));

	}

	/**
	 * Gets the txt valor depreciacio.
	 *
	 * @return the txt valor depreciacio
	 */
	public HtmlInputText getTxtValorDepreciacio() {
		return txtValorDepreciacio;
	}

	/**
	 * Sets the txt valor depreciacio.
	 *
	 * @param txtValorDepreciacio the new txt valor depreciacio
	 */
	public void setTxtValorDepreciacio(HtmlInputText txtValorDepreciacio) {
		this.txtValorDepreciacio = txtValorDepreciacio;
	}

	/**
	 * Gets the txt valor autoseguro.
	 *
	 * @return the txt valor autoseguro
	 */
	public HtmlInputText getTxtValorAutoseguro() {
		return txtValorAutoseguro;
	}

	/**
	 * Sets the txt valor autoseguro.
	 *
	 * @param txtValorAutoseguro the new txt valor autoseguro
	 */
	public void setTxtValorAutoseguro(HtmlInputText txtValorAutoseguro) {
		this.txtValorAutoseguro = txtValorAutoseguro;
	}

	/**
	 * Gets the txt valor mantenimiento.
	 *
	 * @return the txt valor mantenimiento
	 */
	public HtmlInputText getTxtValorMantenimiento() {
		return txtValorMantenimiento;
	}

	/**
	 * Sets the txt valor mantenimiento.
	 *
	 * @param txtValorMantenimiento the new txt valor mantenimiento
	 */
	public void setTxtValorMantenimiento(HtmlInputText txtValorMantenimiento) {
		this.txtValorMantenimiento = txtValorMantenimiento;
	}

	/**
	 * Checks if is activar confirmacion.
	 *
	 * @return true, if is activar confirmacion
	 */
	public boolean isActivarConfirmacion() {
		return activarConfirmacion;
	}

	/**
	 * Sets the activar confirmacion.
	 *
	 * @param activarConfirmacion the new activar confirmacion
	 */
	public void setActivarConfirmacion(boolean activarConfirmacion) {
		this.activarConfirmacion = activarConfirmacion;
	}

	/**
	 * Gets the txt valor total.
	 *
	 * @return the txt valor total
	 */
	public HtmlInputText getTxtValorTotal() {
		return txtValorTotal;
	}

	/**
	 * Sets the txt valor total.
	 *
	 * @param txtValorTotal the new txt valor total
	 */
	public void setTxtValorTotal(HtmlInputText txtValorTotal) {
		this.txtValorTotal = txtValorTotal;
	}

	/**
	 * Total tarifa.
	 *
	 * @return the float
	 */
	public Float totalTarifa() {

		Float valorMantenimiento = null;
		Float valorDepreciacion = null;
		Float valorAutoseguro = null;
		Float valorEspacioFisico = null;
		
		if (txtValorMantenimiento.getValue() != null)
			valorMantenimiento = Util.convertirDecimal(txtValorMantenimiento
					.getValue().toString());
		if (valorMantenimiento == null)
			valorMantenimiento = new Float(0);

		if (txtValorDepreciacio.getValue() != null)
			valorDepreciacion = Util.convertirDecimal(txtValorDepreciacio
					.getValue().toString());
		if (valorDepreciacion == null)
			valorDepreciacion = new Float(0);

		if (txtValorAutoseguro.getValue() != null)
			valorAutoseguro = Util.convertirDecimal(txtValorAutoseguro
					.getValue().toString());
		if (valorAutoseguro == null)
			valorAutoseguro = new Float(0);

		if (txtValorEspacioFisico.getValue() != null)
			valorEspacioFisico = Util.convertirDecimal(
					txtValorEspacioFisico.getValue().toString());
		if (valorEspacioFisico == null)
			valorEspacioFisico = new Float(0);
		
		Float totalTarifa = valorMantenimiento.floatValue()
				+ valorDepreciacion.floatValue() 
				+ valorAutoseguro.floatValue() 
				+ valorEspacioFisico.floatValue();

		return new Float(totalTarifa);
	}

	/**
	 * Listener valor autoseguro.
	 *
	 * @param event the event
	 */
	public void listenerValorAutoseguro(ValueChangeEvent event) {

		setValorTotal(totalTarifa());
		setTotalValorAnho(totalTarifa() * new Float(12));
	}

	/**
	 * Listener valor depreciacion.
	 *
	 * @param event the event
	 */
	public void listenerValorDepreciacion(ValueChangeEvent event) {

		setValorTotal(totalTarifa());
		setTotalValorAnho(totalTarifa() * new Float(12));
	}

	/**
	 * Listener valor mantenimiento.
	 *
	 * @param event the event
	 */
	public void listenerValorMantenimiento(ValueChangeEvent event) {

		setValorTotal(totalTarifa());
		setTotalValorAnho(totalTarifa() * new Float(12));
	}
	
	/**
	 * Listener valor espacio fisico.
	 *
	 * @param event the event
	 */
	public void listenerValorEspacioFisico(ValueChangeEvent event) {

		setValorTotal(totalTarifa());
		setTotalValorAnho(totalTarifa() * new Float(12));
	}

	/**
	 * Gets the txt valor anho.
	 *
	 * @return the txt valor anho
	 */
	public HtmlInputText getTxtValorAnho() {
		return txtValorAnho;
	}

	/**
	 * Sets the txt valor anho.
	 *
	 * @param txtValorAnho the new txt valor anho
	 */
	public void setTxtValorAnho(HtmlInputText txtValorAnho) {
		this.txtValorAnho = txtValorAnho;
	}

	/**
	 * Validar tamanho periodo.
	 *
	 * @param periodo the periodo
	 * @throws GWorkException the g work exception
	 */
	public void validarTamanhoPeriodo(String periodo) throws GWorkException {

		if (periodo.length() <= 3)
			throw new GWorkException(Util
					.loadErrorMessageValue("PERIODO.MINLENGTH"));

	}

	/**
	 * Gets the valor depreciacion.
	 *
	 * @return the valor depreciacion
	 */
	public Float getValorDepreciacion() {
		return valorDepreciacion;
	}

	/**
	 * Sets the valor depreciacion.
	 *
	 * @param valorDepreciacion the new valor depreciacion
	 */
	public void setValorDepreciacion(Float valorDepreciacion) {
		this.valorDepreciacion = valorDepreciacion;
	}

	/**
	 * Gets the valor mantenimiento.
	 *
	 * @return the valor mantenimiento
	 */
	public Float getValorMantenimiento() {
		return valorMantenimiento;
	}

	/**
	 * Sets the valor mantenimiento.
	 *
	 * @param valorMantenimiento the new valor mantenimiento
	 */
	public void setValorMantenimiento(Float valorMantenimiento) {
		this.valorMantenimiento = valorMantenimiento;
	}

	/**
	 * Gets the valor autoseguro.
	 *
	 * @return the valor autoseguro
	 */
	public Float getValorAutoseguro() {
		return valorAutoseguro;
	}

	/**
	 * Sets the valor autoseguro.
	 *
	 * @param valorAutoseguro the new valor autoseguro
	 */
	public void setValorAutoseguro(Float valorAutoseguro) {
		this.valorAutoseguro = valorAutoseguro;
	}

	/**
	 * Gets the valor total.
	 *
	 * @return the valor total
	 */
	public Float getValorTotal() {
		return valorTotal;
	}

	/**
	 * Sets the valor total.
	 *
	 * @param valorTotal the new valor total
	 */
	public void setValorTotal(Float valorTotal) {
		this.valorTotal = valorTotal;
	}

	/**
	 * Gets the total valor anho.
	 *
	 * @return the total valor anho
	 */
	public Float getTotalValorAnho() {
		return totalValorAnho;
	}

	/**
	 * Sets the total valor anho.
	 *
	 * @param totalValorAnho the new total valor anho
	 */
	public void setTotalValorAnho(Float totalValorAnho) {
		this.totalValorAnho = totalValorAnho;
	}

	/**
	 * Gets the valor espacio fisico.
	 *
	 * @return the valor espacio fisico
	 */
	public Float getValorEspacioFisico() {
		return valorEspacioFisico;
	}

	/**
	 * Sets the valor espacio fisico.
	 *
	 * @param valorEspacioFisico the new valor espacio fisico
	 */
	public void setValorEspacioFisico(Float valorEspacioFisico) {
		this.valorEspacioFisico = valorEspacioFisico;
	}

	/**
	 * Gets the txt valor espacio fisico.
	 *
	 * @return the txt valor espacio fisico
	 */
	public HtmlInputText getTxtValorEspacioFisico() {
		return txtValorEspacioFisico;
	}

	/**
	 * Sets the txt valor espacio fisico.
	 *
	 * @param txtValorEspacioFisico the new txt valor espacio fisico
	 */
	public void setTxtValorEspacioFisico(HtmlInputText txtValorEspacioFisico) {
		this.txtValorEspacioFisico = txtValorEspacioFisico;
	}
}
