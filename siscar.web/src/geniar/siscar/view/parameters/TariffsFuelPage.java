package geniar.siscar.view.parameters;

import geniar.siscar.logic.parameters.services.FuelTariffService;
import geniar.siscar.model.Tariffs;
import geniar.siscar.model.TariffsTypes;
import geniar.siscar.util.FacesUtils;
import geniar.siscar.util.ManipulacionFechas;
import geniar.siscar.util.PopupMessagePage;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import com.icesoft.faces.component.ext.HtmlInputText;
import com.icesoft.faces.component.ext.HtmlSelectOneMenu;

/**
 * 
 * @author Mauricio Cuenca N Tarifas de combustible
 * 
 */
public class TariffsFuelPage {

	// Servicios
	private FuelTariffService fuelTariffService;

	// Datos
	private String nombreTipoTarifa;
	private Long idTipoCombustible;
	private Long idTipoTarifa;
	private Float valorTarifa;
	private Date fechaHasta;
	private Date fechaDesde;
	private Long vigencia;

	// Componentes
	private HtmlSelectOneMenu cbxTipoTarifa;
	private HtmlSelectOneMenu cbxFuelTypes;
	private HtmlInputText txtFechaDesde;
	private HtmlInputText txtVigencia;
	private HtmlInputText txtValor;
	private SelectItem[] listTipoTarifa;

	// Control sobre componentes
	private boolean disCrear;

	// Variables para manejo de mensajes de confirmación
	private boolean activarConfirmacion;
	private Integer opcConfirmacion;
	private static Integer MODIFICAR = 1;

	/**
	 * Constructor.
	 */
	public TariffsFuelPage() {
		vigencia = null;
	}

	/**
	 * Se ejecuta cuando se va a crear una nueva tarifa de combustible.
	 * 
	 * @param actionEvent
	 */
	@SuppressWarnings("static-access")
	public void action_crearTarifaCombustible(ActionEvent actionEvent) {
		try {
			validarDatos();

			Calendar calendarDesde = Calendar.getInstance();
			calendarDesde.setTime(fechaDesde);

			Calendar calendarVigencia = Calendar.getInstance();
			calendarVigencia
					.set(Calendar.YEAR, Integer.parseInt("" + vigencia));

			ManipulacionFechas.validarAnhoFecha(calendarVigencia.getTime(),
					"ERROR.LIMVIGENTARCOMB");

			if (calendarVigencia.YEAR < calendarDesde.YEAR) {
				throw new GWorkException(Util
						.loadErrorMessageValue("ERROR.VIGENCIAMENOR"));
			}

			setOpcConfirmacion(MODIFICAR);
			mostrarMensaje(Util.loadMessageValue("MENSAJE.ALERTA"),
					activarConfirmacion = true);
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	@SuppressWarnings("static-access")
	private void validarDatos() throws GWorkException {
		if (idTipoCombustible == null || idTipoCombustible == -1) {
			throw new GWorkException(Util.loadErrorMessageValue("TIPOCOM.SEL"));
		}

		if (idTipoTarifa == null || idTipoTarifa == -1) {
			throw new GWorkException(Util.loadErrorMessageValue("TARIFA.SEL"));
		}

		if (vigencia == null || vigencia.toString().trim().length() == 0
				|| vigencia == 0) {
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.INGRPERIODVIG"));
		}

		if (fechaDesde == null) {
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.DATEINITARIFACOMB"));
		}

		if (!Util
				.validarCadenaCaracteresEspecialesNumLetrasGuion("" + vigencia))
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.VIGENCIACARACESP"));

		if (valorTarifa == null || valorTarifa == 0) {
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.INGRTARIFCOMB"));
		}

		if (("" + valorTarifa).trim().length() == 0) {
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.LIMTARIFA"));
		}

		Util.validarLimite("" + vigencia, 4, 4, "ERROR.LIMTVIGENCIA");
		Util.validarLimite("" + valorTarifa, 7, 2, "ERROR.LIMTARIFA");

		Calendar calendarDesde = Calendar.getInstance();
		calendarDesde.setTime(fechaDesde);

		int periodoVigencia = Integer.parseInt(vigencia.toString());
		int agnoDesde = calendarDesde.get(calendarDesde.YEAR);

		if (periodoVigencia < agnoDesde) {
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.VIGENCIAMENOR"));
		}
	}

	public void action_modificarTarifaCombustible() {
		try {
			validarDatos();

			fuelTariffService.crearTarifaCombustible(idTipoCombustible,
					idTipoTarifa, nombreTipoTarifa, fechaDesde, fechaHasta,
					new Long("" + vigencia), valorTarifa);

			mostrarMensaje(Util.loadMessageValue("EXITO"), false);
			limpiar();
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void action_consultar(ActionEvent actionEvent) {
		try {
			if (idTipoCombustible == null || idTipoCombustible == -1) {
				throw new GWorkException(Util
						.loadErrorMessageValue("TIPOCOM.SEL"));
			}

			if (idTipoTarifa == null || idTipoTarifa == -1) {
				throw new GWorkException(Util
						.loadErrorMessageValue("TARIFA.SEL"));
			}

			Tariffs tariffs = fuelTariffService.consultarTarifaCombustible(
					idTipoCombustible, idTipoTarifa, nombreTipoTarifa);

			if (tariffs == null) {
				throw new GWorkException(Util
						.loadErrorMessageValue("search.not.found"));
			}

			txtVigencia.setValue(ManipulacionFechas.getAgno(tariffs
					.getTrfAñoVigencia()));
			txtValor.setValue(tariffs.getTrfValor().floatValue());
			txtFechaDesde.setValue(tariffs.getTrfFechaInicio());

			disCrear = true;

		} catch (GWorkException e) {
			limpiarConsulta();
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void listenerTipoCombustible(ValueChangeEvent event) {
		if (event.getNewValue() != null) {
			setIdTipoCombustible(new Long("" + event.getNewValue()));
		}
	}

	/**
	 * Limpia la forma.
	 * 
	 * @param actionEvent
	 */
	public void action_limpiar(ActionEvent actionEvent) {
		limpiar();
	}

	/**
	 * Coloca los valores iniciales a cada campo.
	 */
	private void limpiar() {
		txtValor.setValue("");
		txtVigencia.setValue("");
		cbxFuelTypes.setValue(-1);
		cbxTipoTarifa.setValue(-1);
		txtFechaDesde.setValue(null);
		fechaDesde = null;
		disCrear = false;
	}

	/**
	 * Limpia todos los campos menos el campo de tipo de combustible.
	 */
	private void limpiarConsulta() {
		txtValor.setValue("");
		txtVigencia.setValue("");
		txtFechaDesde.setValue(null);
		fechaDesde = null;
		disCrear = false;
	}

	/**
	 * Muestra un mensaje emergente.
	 * 
	 * @param mensaje
	 *            El mensaje que se va a mostrar.
	 * @param buttonCancel
	 *            indica si se activa la opción cancelar.
	 */
	public void mostrarMensaje(String mensaje, boolean buttonCancel) {
		PopupMessagePage message = (PopupMessagePage) FacesUtils
				.getManagedBean("popupMessagePage");
		if (message != null)
			message.showPopup(mensaje, buttonCancel);
	}

	// Métodos accesores
	public FuelTariffService getFuelTariffService() {
		return fuelTariffService;
	}

	public void setFuelTariffService(FuelTariffService fuelTariffService) {
		this.fuelTariffService = fuelTariffService;
	}

	public Float getValorTarifa() {
		return valorTarifa;
	}

	public void setValorTarifa(Float valorTarifa) {
		this.valorTarifa = Util.convertirDecimal("" + valorTarifa);
	}

	public String getNombreTipoTarifa() {
		return nombreTipoTarifa;
	}

	public void setNombreTipoTarifa(String nombreTipoTarifa) {
		this.nombreTipoTarifa = nombreTipoTarifa;
	}

	public Date getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	public Date getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public Long getVigencia() {
		return vigencia;
	}

	public void setVigencia(Long vigencia) {
		this.vigencia = vigencia;
	}

	public Long getIdTipoCombustible() {
		return idTipoCombustible;
	}

	public void setIdTipoCombustible(Long idTipoCombustible) {
		this.idTipoCombustible = idTipoCombustible;
	}

	public Long getIdTipoTarifa() {
		return idTipoTarifa;
	}

	public void setIdTipoTarifa(Long idTipoTarifa) {
		this.idTipoTarifa = idTipoTarifa;
	}

	public HtmlInputText getTxtValor() {
		return txtValor;
	}

	public void setTxtValor(HtmlInputText txtValor) {
		this.txtValor = txtValor;
	}

	public HtmlSelectOneMenu getCbxFuelTypes() {
		return cbxFuelTypes;
	}

	public void setCbxFuelTypes(HtmlSelectOneMenu cbxFuelTypes) {
		this.cbxFuelTypes = cbxFuelTypes;
	}

	public HtmlSelectOneMenu getCbxTipoTarifa() {
		return cbxTipoTarifa;
	}

	public void setCbxTipoTarifa(HtmlSelectOneMenu cbxTipoTarifa) {
		this.cbxTipoTarifa = cbxTipoTarifa;
	}

	public HtmlInputText getTxtVigencia() {
		return txtVigencia;
	}

	public void setTxtVigencia(HtmlInputText txtVigencia) {
		this.txtVigencia = txtVigencia;
	}

	public HtmlInputText getTxtFechaDesde() {
		return txtFechaDesde;
	}

	public void setTxtFechaDesde(HtmlInputText txtFechaDesde) {
		this.txtFechaDesde = txtFechaDesde;
	}

	/**
	 * Listado de tipos de tarifas.
	 * 
	 * @return SelectItem[] Listado de opciones.
	 */
	public SelectItem[] getListTipoTarifa() {
		try {
			List<TariffsTypes> list = null;
			// Se consultan los tipos de tarifa
			list = getFuelTariffService().consultarTiposTarifa();
			if (list != null) {
				listTipoTarifa = new SelectItem[list.size() + 1];
			} else
				listTipoTarifa = new SelectItem[1];

			listTipoTarifa[0] = new SelectItem(new Long("-1"), Util
					.loadMessageValue("SELECCIONAR"));

			if (list == null) {
				return listTipoTarifa;
			}
			int i = 1;

			for (TariffsTypes types : list) {
				listTipoTarifa[i] = new SelectItem(types.getTrtId(), types
						.getTrtNombre());
				i++;
			}
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
		return listTipoTarifa;
	}

	public void setListTipoTarifa(SelectItem[] listTipoTarifa) {
		this.listTipoTarifa = listTipoTarifa;
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

	public boolean isDisCrear() {
		return disCrear;
	}

	public void setDisCrear(boolean disCrear) {
		this.disCrear = disCrear;
	}
}
