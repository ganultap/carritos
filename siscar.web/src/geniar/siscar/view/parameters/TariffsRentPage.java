package geniar.siscar.view.parameters;

import java.util.Calendar;
import java.util.Date;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import com.icesoft.faces.component.ext.HtmlInputText;
import com.icesoft.faces.component.ext.HtmlSelectOneMenu;
import com.icesoft.faces.component.selectinputdate.SelectInputDate;

import geniar.siscar.logic.parameters.services.TariffRentService;
import geniar.siscar.model.Tariffs;
import geniar.siscar.util.FacesUtils;
import geniar.siscar.util.ManipulacionFechas;
import geniar.siscar.util.ParametersUtil;
import geniar.siscar.util.PopupMessagePage;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

/**
 * The Class TariffsRentPage.
 */
public class TariffsRentPage {

	/** The Id tipo vehiculo. */
	private Long IdTipoVehiculo;
	
	/** The trf fecha inicio. */
	private Date trfFechaInicio;
	
	/** The trf km incluido. */
	private Float trfKmIncluido;
	
	/** The trf km valor adicional. */
	private Float trfKmValorAdicional;
	
	/** The trf valor. */
	private Float trfValor;

	/** The txt valor depreciacion dia. */
	private HtmlInputText txtValorDepreciacionDia = new HtmlInputText();
	
	/** The txt valor autoseguro dia. */
	private HtmlInputText txtValorAutoseguroDia = new HtmlInputText();
	
	/** The txt valor mantenimiento dia. */
	private HtmlInputText txtValorMantenimientoDia = new HtmlInputText();
	
	/** The txt valor espacio fisico dia. */
	private HtmlInputText txtValorEspacioFisicoDia = new HtmlInputText();
	
	/** The txt valor dia. */
	private HtmlInputText txtValorDia = new HtmlInputText();
	
	/** The txt kilometraje incluido. */
	private HtmlInputText txtKilometrajeIncluido = new HtmlInputText();
	
	/** The txt valor depreciacion km adicional. */
	private HtmlInputText txtValorDepreciacionKmAdicional = new HtmlInputText();
	
	/** The txt valor autoseguro km adicional. */
	private HtmlInputText txtValorAutoseguroKmAdicional = new HtmlInputText();
	
	/** The txt valor mantenimiento km adicional. */
	private HtmlInputText txtValorMantenimientoKmAdicional = new HtmlInputText();
	
	/** The txt valor espacio fisico km adicional. */
	private HtmlInputText txtValorEspacioFisicoKmAdicional = new HtmlInputText();
		
	/** The txt fecha inicio. */
	private SelectInputDate txtFechaInicio;
	
	/** The txt km incluido. */
	private HtmlInputText txtKmIncluido = new HtmlInputText();
	
	/** The txt km valor adicional. */
	private HtmlInputText txtKmValorAdicional;
	
	/** The cbx tipo vehiculo. */
	private HtmlSelectOneMenu cbxTipoVehiculo;
	
	/** The txt periodo. */
	private HtmlInputText txtPeriodo;
	
	/** The MODIFICAR. */
	private static Integer MODIFICAR = 1;
	
	/** The opc confirmacion. */
	private Integer opcConfirmacion;
	
	/** The activar confirmacion. */
	private boolean activarConfirmacion;

	/** The tariff rent service. */
	private TariffRentService tariffRentService;
	
	/** The periodo vigencia. */
	private Integer periodoVigencia;

	/**
	 * Gets the periodo vigencia.
	 *
	 * @return the periodo vigencia
	 */
	public Integer getPeriodoVigencia() {
		return periodoVigencia;
	}

	/**
	 * Sets the periodo vigencia.
	 *
	 * @param periodoVigencia the new periodo vigencia
	 */
	public void setPeriodoVigencia(Integer periodoVigencia) {
		this.periodoVigencia = periodoVigencia;
	}

	/**
	 * Gets the id tipo vehiculo.
	 *
	 * @return the id tipo vehiculo
	 */
	public Long getIdTipoVehiculo() {
		return IdTipoVehiculo;
	}

	/**
	 * Sets the id tipo vehiculo.
	 *
	 * @param idTipoVehiculo the new id tipo vehiculo
	 */
	public void setIdTipoVehiculo(Long idTipoVehiculo) {
		IdTipoVehiculo = idTipoVehiculo;
	}

	/**
	 * Gets the trf fecha inicio.
	 *
	 * @return the trf fecha inicio
	 */
	public Date getTrfFechaInicio() {
		return trfFechaInicio;
	}

	/**
	 * Sets the trf fecha inicio.
	 *
	 * @param trfFechaInicio the new trf fecha inicio
	 */
	public void setTrfFechaInicio(Date trfFechaInicio) {
		this.trfFechaInicio = trfFechaInicio;
	}

	/**
	 * Gets the trf km incluido.
	 *
	 * @return the trf km incluido
	 */
	public Float getTrfKmIncluido() {
		return trfKmIncluido;
	}

	/**
	 * Sets the trf km incluido.
	 *
	 * @param trfKmIncluido the new trf km incluido
	 */
	public void setTrfKmIncluido(Float trfKmIncluido) {
		this.trfKmIncluido = trfKmIncluido;
	}

	/**
	 * Gets the trf km valor adicional.
	 *
	 * @return the trf km valor adicional
	 */
	public Float getTrfKmValorAdicional() {
		return trfKmValorAdicional;
	}

	/**
	 * Sets the trf km valor adicional.
	 *
	 * @param trfKmValorAdicional the new trf km valor adicional
	 */
	public void setTrfKmValorAdicional(Float trfKmValorAdicional) {
		this.trfKmValorAdicional = trfKmValorAdicional;
	}

	/**
	 * Action_crear tarifa renta.
	 *
	 * @param event the event
	 */
	public void action_crearTarifaAlquiler(ActionEvent event) {
		try {
			validarDatos(IdTipoVehiculo, periodoVigencia, trfValor,
					trfFechaInicio, trfKmIncluido, trfKmValorAdicional);
			
			setOpcConfirmacion(MODIFICAR);
			activarConfirmacion = true;
			mostrarMensaje(Util.loadMessageValue("MENSAJE.ALERTA"),
					activarConfirmacion);

		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	/**
	 * Crear tarifa alquiler.
	 */
	public void crearTarifaAlquiler() {
		try {
			tariffRentService.modificarTarifaAlquiler(IdTipoVehiculo,
					periodoVigencia, trfFechaInicio, trfKmIncluido, 
					Util.convertirDecimal(txtValorAutoseguroDia.getValue().toString()), 
					Util.convertirDecimal(txtValorDepreciacionDia.getValue().toString()),
					Util.convertirDecimal(txtValorEspacioFisicoDia.getValue().toString()), 
					Util.convertirDecimal(txtValorMantenimientoDia.getValue().toString()),
					Util.convertirDecimal(txtValorAutoseguroKmAdicional.getValue().toString()), 
					Util.convertirDecimal(txtValorDepreciacionKmAdicional.getValue().toString()),
					Util.convertirDecimal(txtValorEspacioFisicoKmAdicional.getValue().toString()), 
					Util.convertirDecimal(txtValorMantenimientoKmAdicional.getValue().toString()));
			
			limpiar();
			mostrarMensaje(Util.loadMessageValue("EXITO"), false);
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

	}

	/**
	 * Limpiar.
	 */
	public void limpiar() {
		if (txtFechaInicio != null)
			txtFechaInicio.setValue(null);

		txtValorAutoseguroDia.setValue("");
		txtValorAutoseguroKmAdicional.setValue("");
		txtValorDepreciacionDia.setValue("");
		txtValorDepreciacionKmAdicional.setValue("");
		txtValorEspacioFisicoDia.setValue("");
		txtValorEspacioFisicoKmAdicional.setValue("");
		txtValorMantenimientoDia.setValue("");
		txtValorMantenimientoKmAdicional.setValue("");
		
		setTrfFechaInicio(null);
		txtValorDia.setValue("");
		txtKmIncluido.setValue("");
		txtKmValorAdicional.setValue("");
		txtPeriodo.setValue("");
		setIdTipoVehiculo(new Long("-1"));
	}

	/**
	 * Action_limpiar.
	 *
	 * @param event the event
	 */
	public void action_limpiar(ActionEvent event) {
		limpiar();
	}

	public void listenerValorDia(ValueChangeEvent event) {
		try {
			Float valorMantenimiento = null;
			Float valorDepreciacion = null;
			Float valorAutoseguro = null;
			Float valorEspacioFisico = null;
			
			if (txtValorMantenimientoDia.getValue() != null
					&& txtValorMantenimientoDia.getValue().toString().trim().length() > 0)
				valorMantenimiento = Util.convertirDecimal(txtValorMantenimientoDia
						.getValue().toString());
			if (valorMantenimiento == null)
				valorMantenimiento = new Float(0);

			if (txtValorDepreciacionDia.getValue() != null
					&& txtValorDepreciacionDia.getValue().toString().trim().length() > 0)
				valorDepreciacion = Util.convertirDecimal(txtValorDepreciacionDia
						.getValue().toString());
			if (valorDepreciacion == null)
				valorDepreciacion = new Float(0);

			if (txtValorAutoseguroDia.getValue() != null
					&& txtValorAutoseguroDia.getValue().toString().trim().length() > 0)
				valorAutoseguro = Util.convertirDecimal(txtValorAutoseguroDia
						.getValue().toString());
			if (valorAutoseguro == null)
				valorAutoseguro = new Float(0);

			if (txtValorEspacioFisicoDia.getValue() != null
					&& txtValorEspacioFisicoDia.getValue().toString().trim().length() > 0)
				valorEspacioFisico = Util.convertirDecimal(
						txtValorEspacioFisicoDia.getValue().toString());
			if (valorEspacioFisico == null)
				valorEspacioFisico = new Float(0);
			
			txtValorDia.setValue(valorMantenimiento.floatValue()
					+ valorDepreciacion.floatValue() 
					+ valorAutoseguro.floatValue() 
					+ valorEspacioFisico.floatValue());
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}
	
	public void listenerKmAdicional(ValueChangeEvent event) {
		try {
			Float valorMantenimiento = null;
			Float valorDepreciacion = null;
			Float valorAutoseguro = null;
			Float valorEspacioFisico = null;
			
			if (txtValorMantenimientoKmAdicional.getValue() != null
					&& txtValorMantenimientoKmAdicional.getValue().toString().trim().length() > 0)
				valorMantenimiento = Util.convertirDecimal(txtValorMantenimientoKmAdicional
						.getValue().toString());
			if (valorMantenimiento == null)
				valorMantenimiento = new Float(0);

			if (txtValorDepreciacionKmAdicional.getValue() != null
					&& txtValorDepreciacionKmAdicional.getValue().toString().trim().length() > 0)
				valorDepreciacion = Util.convertirDecimal(txtValorDepreciacionKmAdicional
						.getValue().toString());
			if (valorDepreciacion == null)
				valorDepreciacion = new Float(0);

			if (txtValorAutoseguroKmAdicional.getValue() != null
					&& txtValorAutoseguroKmAdicional.getValue().toString().trim().length() > 0)
				valorAutoseguro = Util.convertirDecimal(txtValorAutoseguroKmAdicional
						.getValue().toString());
			if (valorAutoseguro == null)
				valorAutoseguro = new Float(0);

			if (txtValorEspacioFisicoKmAdicional.getValue() != null
					&& txtValorEspacioFisicoKmAdicional.getValue().toString().trim().length() > 0)
				valorEspacioFisico = Util.convertirDecimal(
						txtValorEspacioFisicoKmAdicional.getValue().toString());
			if (valorEspacioFisico == null)
				valorEspacioFisico = new Float(0);
			
			txtKmValorAdicional.setValue(valorMantenimiento.floatValue()
					+ valorDepreciacion.floatValue() 
					+ valorAutoseguro.floatValue() 
					+ valorEspacioFisico.floatValue());			
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}
	
	
	/**
	 * Listener_tarifa alquiler.
	 *
	 * @param event the event
	 */
	public void listener_tarifaAlquiler(ValueChangeEvent event) {
		try {
			Long idTipoVehiculo = (Long) event.getNewValue();
			
			Float valorDia = 0F;
			Float valorKmAdicional = 0F;
			
			txtFechaInicio.setValue(null);
			setTrfFechaInicio(null);
			txtValorDia.setValue("");
			txtKmIncluido.setValue("");
			txtKmValorAdicional.setValue("");
			txtPeriodo.setValue("");
			
			txtValorAutoseguroDia.setValue("");
			txtValorAutoseguroKmAdicional.setValue("");
			txtValorDepreciacionDia.setValue("");
			txtValorDepreciacionKmAdicional.setValue("");
			txtValorEspacioFisicoDia.setValue("");
			txtValorEspacioFisicoKmAdicional.setValue("");
			txtValorMantenimientoDia.setValue("");
			txtValorMantenimientoKmAdicional.setValue("");
			
			if (idTipoVehiculo.longValue() != -1L) {
				Tariffs tariffs = null;
				Calendar calendario = Calendar.getInstance();
				
				tariffs = tariffRentService
						.consultarTarifaActualAlquilerPorTipoTarifaYTipoVehiculo(
								idTipoVehiculo,
								ParametersUtil.TARIFA_ALQUILER_AUTOSEGURO);
				
				if(tariffs != null){
					txtValorAutoseguroDia.setValue(tariffs.getTrfValor());
					txtValorAutoseguroKmAdicional.setValue(tariffs.getTrfKmValorAdicional());
					
					Date periodo = tariffs.getTrfAñoVigencia();
					calendario.setTime(periodo); // fecha es el Date de antes.
					Integer anho = calendario.get(Calendar.YEAR);
	
					txtPeriodo.setValue(anho);
					txtFechaInicio.setValue(tariffs.getTrfFechaInicio());
					txtKmIncluido.setValue(tariffs.getTrfKmIncluido());
					
					valorDia = valorDia + tariffs.getTrfValor();
					valorKmAdicional = valorKmAdicional + tariffs.getTrfKmValorAdicional();
				}
				
				tariffs = tariffRentService
						.consultarTarifaActualAlquilerPorTipoTarifaYTipoVehiculo(
								idTipoVehiculo,
								ParametersUtil.TARIFA_ALQUILER_ESPACIO_FISICO);
				
				if(tariffs != null){
					txtValorEspacioFisicoDia.setValue(tariffs.getTrfValor());
					txtValorEspacioFisicoKmAdicional.setValue(tariffs.getTrfKmValorAdicional());
					
					Date periodo = tariffs.getTrfAñoVigencia();
					calendario.setTime(periodo); // fecha es el Date de antes.
					Integer anho = calendario.get(Calendar.YEAR);
	
					txtPeriodo.setValue(anho);
					txtFechaInicio.setValue(tariffs.getTrfFechaInicio());
					txtKmIncluido.setValue(tariffs.getTrfKmIncluido());
					
					valorDia = valorDia + tariffs.getTrfValor();
					valorKmAdicional = valorKmAdicional + tariffs.getTrfKmValorAdicional();
				}
					
				tariffs = tariffRentService
						.consultarTarifaActualAlquilerPorTipoTarifaYTipoVehiculo(
								idTipoVehiculo,
								ParametersUtil.TARIFA_ALQUILER_MANTENIMIENTO);

				if(tariffs != null){
					txtValorMantenimientoDia.setValue(tariffs.getTrfValor());
					txtValorMantenimientoKmAdicional.setValue(tariffs.getTrfKmValorAdicional());

					Date periodo = tariffs.getTrfAñoVigencia();
					calendario.setTime(periodo); // fecha es el Date de antes.
					Integer anho = calendario.get(Calendar.YEAR);
	
					txtPeriodo.setValue(anho);
					txtFechaInicio.setValue(tariffs.getTrfFechaInicio());
					txtKmIncluido.setValue(tariffs.getTrfKmIncluido());
					
					valorDia = valorDia + tariffs.getTrfValor();
					valorKmAdicional = valorKmAdicional + tariffs.getTrfKmValorAdicional();
				}
				
				tariffs = tariffRentService
				.consultarTarifaActualAlquilerPorTipoTarifaYTipoVehiculo(
						idTipoVehiculo,
						ParametersUtil.TARIFA_ALQUILER_DEPRECIACION);
		
				if(tariffs != null){
					txtValorDepreciacionDia.setValue(tariffs.getTrfValor());
					txtValorDepreciacionKmAdicional.setValue(tariffs.getTrfKmValorAdicional());
				
					Date periodo = tariffs.getTrfAñoVigencia();
					calendario.setTime(periodo); // fecha es el Date de antes.
					Integer anho = calendario.get(Calendar.YEAR);
					
					txtPeriodo.setValue(anho);
					txtFechaInicio.setValue(tariffs.getTrfFechaInicio());
					txtKmIncluido.setValue(tariffs.getTrfKmIncluido());
					
					valorDia = valorDia + tariffs.getTrfValor();
					valorKmAdicional = valorKmAdicional + tariffs.getTrfKmValorAdicional();
				}
				
				txtValorDia.setValue(valorDia);
				txtKmValorAdicional.setValue(valorKmAdicional);
			}
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	/**
	 * Gets the txt fecha inicio.
	 *
	 * @return the txt fecha inicio
	 */
	public SelectInputDate getTxtFechaInicio() {
		return txtFechaInicio;
	}

	/**
	 * Sets the txt fecha inicio.
	 *
	 * @param txtFechaInicio the new txt fecha inicio
	 */
	public void setTxtFechaInicio(SelectInputDate txtFechaInicio) {
		this.txtFechaInicio = txtFechaInicio;
	}

	/**
	 * Gets the txt km incluido.
	 *
	 * @return the txt km incluido
	 */
	public HtmlInputText getTxtKmIncluido() {
		return txtKmIncluido;
	}

	/**
	 * Sets the txt km incluido.
	 *
	 * @param txtKmIncluido the new txt km incluido
	 */
	public void setTxtKmIncluido(HtmlInputText txtKmIncluido) {
		this.txtKmIncluido = txtKmIncluido;
	}

	/**
	 * Gets the txt km valor adicional.
	 *
	 * @return the txt km valor adicional
	 */
	public HtmlInputText getTxtKmValorAdicional() {
		return txtKmValorAdicional;
	}

	/**
	 * Sets the txt km valor adicional.
	 *
	 * @param txtKmValorAdicional the new txt km valor adicional
	 */
	public void setTxtKmValorAdicional(HtmlInputText txtKmValorAdicional) {
		this.txtKmValorAdicional = txtKmValorAdicional;
	}

	/**
	 * Gets the cbx tipo vehiculo.
	 *
	 * @return the cbx tipo vehiculo
	 */
	public HtmlSelectOneMenu getCbxTipoVehiculo() {
		return cbxTipoVehiculo;
	}

	/**
	 * Sets the cbx tipo vehiculo.
	 *
	 * @param cbxTipoVehiculo the new cbx tipo vehiculo
	 */
	public void setCbxTipoVehiculo(HtmlSelectOneMenu cbxTipoVehiculo) {
		this.cbxTipoVehiculo = cbxTipoVehiculo;
	}

	/**
	 * Gets the tariff rent service.
	 *
	 * @return the tariff rent service
	 */
	public TariffRentService getTariffRentService() {
		return tariffRentService;
	}

	/**
	 * Sets the tariff rent service.
	 *
	 * @param tariffRentService the new tariff rent service
	 */
	public void setTariffRentService(TariffRentService tariffRentService) {
		this.tariffRentService = tariffRentService;
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
	 * Gets the trf valor.
	 *
	 * @return the trf valor
	 */
	public Float getTrfValor() {
		return trfValor;
	}

	/**
	 * Sets the trf valor.
	 *
	 * @param trfValor the new trf valor
	 */
	public void setTrfValor(Float trfValor) {
		this.trfValor = trfValor;
	}

	/**
	 * Gets the opc confirmacion.
	 *
	 * @return the opc confirmacion
	 */
	public Integer getOpcConfirmacion() {
		return opcConfirmacion;
	}

	/**
	 * Sets the opc confirmacion.
	 *
	 * @param opcConfirmacion the new opc confirmacion
	 */
	public void setOpcConfirmacion(Integer opcConfirmacion) {
		this.opcConfirmacion = opcConfirmacion;
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
	 * Validar datos.
	 *
	 * @param IdTipoVehiculo the id tipo vehiculo
	 * @param periodoVigencia the periodo vigencia
	 * @param trfValor the trf valor
	 * @param trfFechaInicio the trf fecha inicio
	 * @param trfKmIncluido the trf km incluido
	 * @param trfKmValorAdicional the trf km valor adicional
	 * @throws GWorkException the g work exception
	 */
	public void validarDatos(Long IdTipoVehiculo, Integer periodoVigencia,
			Float trfValor, Date trfFechaInicio, Float trfKmIncluido,
			Float trfKmValorAdicional) throws GWorkException {
		
		if (IdTipoVehiculo == null || IdTipoVehiculo.longValue() == -1L)
			throw new GWorkException(Util.loadErrorMessageValue("TIPO.VHC"));
		
		if (periodoVigencia == null || periodoVigencia.intValue() == 0)
			throw new GWorkException(Util.loadErrorMessageValue("FECHATARIFA"));
				
		if (txtValorDepreciacionDia.getValue() == null || txtValorDepreciacionDia.getValue().toString().trim().length() == 0)
			throw new GWorkException(Util.loadErrorMessageValue("ERROR.TARIFAALQUILER.VALOR_DEPRECIACION_DIA"));
		
		if (txtValorAutoseguroDia.getValue() == null || txtValorAutoseguroDia.getValue().toString().trim().length() == 0)
			throw new GWorkException(Util.loadErrorMessageValue("ERROR.TARIFAALQUILER.VALOR_AUTOSEGURO_DIA"));

		if (txtValorEspacioFisicoDia.getValue() == null || txtValorEspacioFisicoDia.getValue().toString().trim().length() == 0)
			throw new GWorkException(Util.loadErrorMessageValue("ERROR.TARIFAALQUILER.VALOR_ESPACIO_FISICO_DIA"));
		
		if (txtValorMantenimientoDia.getValue() == null || txtValorMantenimientoDia.getValue().toString().trim().length() == 0)
			throw new GWorkException(Util.loadErrorMessageValue("ERROR.TARIFAALQUILER.VALOR_MANTENIMIENTO_DIA"));
		
		if (Util.convertirDecimal(txtValorAutoseguroDia.getValue().toString().trim()) < 0)
			throw new GWorkException(Util.loadErrorMessageValue("ERROR.TARIFAALQUILER.VALOR_AUTOSEGURO_DIA_MENOR"));
		
		if (Util.convertirDecimal(txtValorDepreciacionDia.getValue().toString().trim()) < 0)
			throw new GWorkException(Util.loadErrorMessageValue("ERROR.TARIFAALQUILER.VALOR_DEPRECIACION_DIA_MENOR"));
		
		if (Util.convertirDecimal(txtValorEspacioFisicoDia.getValue().toString().trim()) < 0)
			throw new GWorkException(Util.loadErrorMessageValue("ERROR.TARIFAALQUILER.VALOR_ESPACIO_FISICO_DIA_MENOR"));
		
		if (Util.convertirDecimal(txtValorMantenimientoDia.getValue().toString().trim()) < 0)
			throw new GWorkException(Util.loadErrorMessageValue("ERROR.TARIFAALQUILER.VALOR_MANTENIMIENTO_DIA_MENOR"));
		
		if (trfValor == null || trfValor.floatValue() == 0)
			throw new GWorkException(Util.loadErrorMessageValue("VALORTARIFADIA"));

		if (trfFechaInicio == null)
			throw new GWorkException(Util.loadErrorMessageValue("FECHAINICIO"));
		
		if (trfFechaInicio == null || trfKmIncluido.floatValue() == 0)
			throw new GWorkException(Util.loadErrorMessageValue("KMINCLUIDO"));

		if (txtValorDepreciacionKmAdicional.getValue() == null || txtValorDepreciacionKmAdicional.getValue().toString().trim().length() == 0)
			throw new GWorkException(Util.loadErrorMessageValue("ERROR.TARIFAALQUILER.VALOR_DEPRECIACION_KM"));

		if (txtValorAutoseguroKmAdicional.getValue() == null || txtValorAutoseguroKmAdicional.getValue().toString().trim().length() == 0)
			throw new GWorkException(Util.loadErrorMessageValue("ERROR.TARIFAALQUILER.VALOR_AUTOSEGURO_KM"));

		if (txtValorEspacioFisicoKmAdicional.getValue() == null || txtValorEspacioFisicoKmAdicional.getValue().toString().trim().length() == 0)
			throw new GWorkException(Util.loadErrorMessageValue("ERROR.TARIFAALQUILER.VALOR_ESPACIO_FISICO_KM"));

		if (txtValorMantenimientoKmAdicional.getValue() == null || txtValorMantenimientoKmAdicional.getValue().toString().trim().length() == 0)
			throw new GWorkException(Util.loadErrorMessageValue("ERROR.TARIFAALQUILER.VALOR_MANTENIMIENTO_KM"));

		if (Util.convertirDecimal(txtValorDepreciacionKmAdicional.getValue().toString().trim()) < 0)
			throw new GWorkException(Util.loadErrorMessageValue("ERROR.TARIFAALQUILER.VALOR_DEPRECIACION_KM_MENOR"));

		if (Util.convertirDecimal(txtValorAutoseguroKmAdicional.getValue().toString().trim()) < 0)
			throw new GWorkException(Util.loadErrorMessageValue("ERROR.TARIFAALQUILER.VALOR_AUTOSEGURO_KM_MENOR"));
				
		if (Util.convertirDecimal(txtValorEspacioFisicoKmAdicional.getValue().toString().trim()) < 0)
			throw new GWorkException(Util.loadErrorMessageValue("ERROR.TARIFAALQUILER.VALOR_ESPACIO_FISICO_KM_MENOR"));

		if (Util.convertirDecimal(txtValorMantenimientoKmAdicional.getValue().toString().trim()) < 0)
			throw new GWorkException(Util.loadErrorMessageValue("ERROR.TARIFAALQUILER.VALOR_MANTENIMIENTO_KM_MENOR"));
		
		if (trfKmValorAdicional == null || trfKmValorAdicional.floatValue() == 0)
			throw new GWorkException(Util.loadErrorMessageValue("KMADICIOAL"));

		if (periodoVigencia.toString().trim().length() <= 3)
			throw new GWorkException(Util
					.loadErrorMessageValue("PERIODO.MINLENGTH"));
		
		ManipulacionFechas.validarAnhoFecha(trfFechaInicio, "");
	}

	/**
	 * Gets the txt valor depreciacion dia.
	 *
	 * @return the txt valor depreciacion dia
	 */
	public HtmlInputText getTxtValorDepreciacionDia() {
		return txtValorDepreciacionDia;
	}

	/**
	 * Sets the txt valor depreciacion dia.
	 *
	 * @param txtValorDepreciacionDia the new txt valor depreciacion dia
	 */
	public void setTxtValorDepreciacionDia(HtmlInputText txtValorDepreciacionDia) {
		this.txtValorDepreciacionDia = txtValorDepreciacionDia;
	}

	/**
	 * Gets the txt valor autoseguro dia.
	 *
	 * @return the txt valor autoseguro dia
	 */
	public HtmlInputText getTxtValorAutoseguroDia() {
		return txtValorAutoseguroDia;
	}

	/**
	 * Sets the txt valor autoseguro dia.
	 *
	 * @param txtValorAutoseguroDia the new txt valor autoseguro dia
	 */
	public void setTxtValorAutoseguroDia(HtmlInputText txtValorAutoseguroDia) {
		this.txtValorAutoseguroDia = txtValorAutoseguroDia;
	}

	/**
	 * Gets the txt valor mantenimiento dia.
	 *
	 * @return the txt valor mantenimiento dia
	 */
	public HtmlInputText getTxtValorMantenimientoDia() {
		return txtValorMantenimientoDia;
	}

	/**
	 * Sets the txt valor mantenimiento dia.
	 *
	 * @param txtValorMantenimientoDia the new txt valor mantenimiento dia
	 */
	public void setTxtValorMantenimientoDia(HtmlInputText txtValorMantenimientoDia) {
		this.txtValorMantenimientoDia = txtValorMantenimientoDia;
	}

	/**
	 * Gets the txt valor espacio fisico dia.
	 *
	 * @return the txt valor espacio fisico dia
	 */
	public HtmlInputText getTxtValorEspacioFisicoDia() {
		return txtValorEspacioFisicoDia;
	}

	/**
	 * Sets the txt valor espacio fisico dia.
	 *
	 * @param txtValorEspacioFisicoDia the new txt valor espacio fisico dia
	 */
	public void setTxtValorEspacioFisicoDia(HtmlInputText txtValorEspacioFisicoDia) {
		this.txtValorEspacioFisicoDia = txtValorEspacioFisicoDia;
	}

	/**
	 * Gets the txt valor dia.
	 *
	 * @return the txt valor dia
	 */
	public HtmlInputText getTxtValorDia() {
		return txtValorDia;
	}

	/**
	 * Sets the txt valor dia.
	 *
	 * @param txtValorDia the new txt valor dia
	 */
	public void setTxtValorDia(HtmlInputText txtValorDia) {
		this.txtValorDia = txtValorDia;
	}

	/**
	 * Gets the txt kilometraje incluido.
	 *
	 * @return the txt kilometraje incluido
	 */
	public HtmlInputText getTxtKilometrajeIncluido() {
		return txtKilometrajeIncluido;
	}

	/**
	 * Sets the txt kilometraje incluido.
	 *
	 * @param txtKilometrajeIncluido the new txt kilometraje incluido
	 */
	public void setTxtKilometrajeIncluido(HtmlInputText txtKilometrajeIncluido) {
		this.txtKilometrajeIncluido = txtKilometrajeIncluido;
	}

	/**
	 * Gets the txt valor depreciacion km adicional.
	 *
	 * @return the txt valor depreciacion km adicional
	 */
	public HtmlInputText getTxtValorDepreciacionKmAdicional() {
		return txtValorDepreciacionKmAdicional;
	}

	/**
	 * Sets the txt valor depreciacion km adicional.
	 *
	 * @param txtValorDepreciacionKmAdicional the new txt valor depreciacion km adicional
	 */
	public void setTxtValorDepreciacionKmAdicional(
			HtmlInputText txtValorDepreciacionKmAdicional) {
		this.txtValorDepreciacionKmAdicional = txtValorDepreciacionKmAdicional;
	}

	/**
	 * Gets the txt valor autoseguro km adicional.
	 *
	 * @return the txt valor autoseguro km adicional
	 */
	public HtmlInputText getTxtValorAutoseguroKmAdicional() {
		return txtValorAutoseguroKmAdicional;
	}

	/**
	 * Sets the txt valor autoseguro km adicional.
	 *
	 * @param txtValorAutoseguroKmAdicional the new txt valor autoseguro km adicional
	 */
	public void setTxtValorAutoseguroKmAdicional(
			HtmlInputText txtValorAutoseguroKmAdicional) {
		this.txtValorAutoseguroKmAdicional = txtValorAutoseguroKmAdicional;
	}

	/**
	 * Gets the txt valor mantenimiento km adicional.
	 *
	 * @return the txt valor mantenimiento km adicional
	 */
	public HtmlInputText getTxtValorMantenimientoKmAdicional() {
		return txtValorMantenimientoKmAdicional;
	}

	/**
	 * Sets the txt valor mantenimiento km adicional.
	 *
	 * @param txtValorMantenimientoKmAdicional the new txt valor mantenimiento km adicional
	 */
	public void setTxtValorMantenimientoKmAdicional(
			HtmlInputText txtValorMantenimientoKmAdicional) {
		this.txtValorMantenimientoKmAdicional = txtValorMantenimientoKmAdicional;
	}

	/**
	 * Gets the txt valor espacio fisico km adicional.
	 *
	 * @return the txt valor espacio fisico km adicional
	 */
	public HtmlInputText getTxtValorEspacioFisicoKmAdicional() {
		return txtValorEspacioFisicoKmAdicional;
	}

	/**
	 * Sets the txt valor espacio fisico km adicional.
	 *
	 * @param txtValorEspacioFisicoKmAdicional the new txt valor espacio fisico km adicional
	 */
	public void setTxtValorEspacioFisicoKmAdicional(
			HtmlInputText txtValorEspacioFisicoKmAdicional) {
		this.txtValorEspacioFisicoKmAdicional = txtValorEspacioFisicoKmAdicional;
	}
}