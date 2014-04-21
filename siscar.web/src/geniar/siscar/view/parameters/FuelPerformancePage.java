/**
 * 
 */
package geniar.siscar.view.parameters;

import geniar.siscar.logic.parameters.services.FuelPerformanceService;
import geniar.siscar.model.FuelPerformance;
import geniar.siscar.util.FacesUtils;
import geniar.siscar.util.PopupMessagePage;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import com.icesoft.faces.component.ext.HtmlCommandButton;

/**
 * @author Mauricio
 * FP = FuelPerformance
 */
public class FuelPerformancePage {

	private Long idLineaVehiculo;
	private Long idTipoCombustible;
	private Long idTipoTraccion;
	private Float valorRendimiento;
	private String tempRendimiento;

	private HtmlSelectOneMenu selectLineaVehiculo;
	private HtmlSelectOneMenu selectTipoCombustible;
	private HtmlSelectOneMenu selectTipoTraccion;

	private HtmlCommandButton btnCrear;

	private SelectItem[] listLineaVehiculos;
	private SelectItem[] listTipoCombustibles;
	private SelectItem[] listTipoTracciones;

	private FuelPerformanceService fuelPerformanceService;

	private boolean activarConfirmacion;
	private Integer opcConfirmacion;
	private static Integer MODIFICAR=1;

	public void action_crearFP(ActionEvent event) {
		try {
			//Si cualquiera de los datos es nulo se genera una excepción.
			if (idLineaVehiculo == null || idLineaVehiculo == -1) 
				throw new GWorkException(Util.loadErrorMessageValue("LINEA.SEL"));				

			if (idTipoCombustible == null || idTipoCombustible == -1)
				throw new GWorkException(Util.loadErrorMessageValue("TIPOCOM.SEL"));				

			if (idTipoTraccion == null || idTipoTraccion == -1)
				throw new GWorkException(Util.loadErrorMessageValue("TRACCION"));

			if (valorRendimiento == null || (""+valorRendimiento).length() == 0 || valorRendimiento == 0) 
				throw new GWorkException(Util.loadErrorMessageValue("ERROR.INGRRENDIMCOM"));

			validarLimiteRendimiento(""+valorRendimiento);

			if (!validarValor(valorRendimiento+"")) {
				throw new GWorkException(Util
						.loadErrorMessageValue("ERROR.CARACRENDIM"));
			}

			fuelPerformanceService.crearFuelPerformance(
					idLineaVehiculo, idTipoTraccion, idTipoCombustible, (valorRendimiento));

			mostrarMensaje(Util.loadMessageValue("EXITO"), false);
			limpiar();
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public boolean validarValor(String valor) throws GWorkException {
		if (!Util.validarNumerosParametros(valor))
			return false;

		return true;
	}

	private void validarLimiteRendimiento(String rendim) throws GWorkException {
		if (rendim.length() < 1 ) {
			throw new GWorkException(Util.loadErrorMessageValue("ERROR.LIMRENDIMCOMBS"));
		}
//		if (rendim.length() > 5 ) {
//			throw new GWorkException(Util.loadErrorMessageValue("ERROR.LIMRENDIMCOMBS"));
//		}
	}

	public void action_modificarFP(ActionEvent event) {
		try {
			activarConfirmacion = true;
			if (idLineaVehiculo == null || idLineaVehiculo == -1) 
				throw new GWorkException(Util.loadErrorMessageValue("LINEA.SEL"));				

			if (idTipoCombustible == null || idTipoCombustible == -1)
				throw new GWorkException(Util.loadErrorMessageValue("TIPOCOM.SEL"));				

			if (idTipoTraccion == null || idTipoTraccion == -1)
				throw new GWorkException(Util.loadErrorMessageValue("TRACCION"));

			if (valorRendimiento == null || (""+valorRendimiento).length() == 0 || valorRendimiento == 0) 
				throw new GWorkException(Util.loadErrorMessageValue("ERROR.INGRRENDIMCOM"));
			
			if (!validarValor(valorRendimiento+"")) {
				throw new GWorkException(Util
						.loadErrorMessageValue("CARACTER.NUMERO"));
			}

			validarLimiteRendimiento(""+valorRendimiento);

			setOpcConfirmacion(MODIFICAR);
			mostrarMensaje(Util.loadMessageValue("MENSAJE.ALERTA"), activarConfirmacion);

		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}


	public void action_modificarFP() {
		try {
			//Si cualquiera de los datos es nulo se genera una excepción.
			if (idLineaVehiculo == null || idLineaVehiculo == -1) 
				throw new GWorkException(Util.loadErrorMessageValue("LINEA.SEL"));				

			if (idTipoCombustible == null || idTipoCombustible == -1)
				throw new GWorkException(Util.loadErrorMessageValue("TIPOCOM.SEL"));				

			if (idTipoTraccion == null || idTipoTraccion == -1)
				throw new GWorkException(Util.loadErrorMessageValue("TRACCION"));

			if (!validarValor(valorRendimiento+"")) {
				throw new GWorkException(Util
						.loadErrorMessageValue("CARACTER.NUMERO"));
			}

			validarLimiteRendimiento(""+valorRendimiento);

			fuelPerformanceService.modificarFuelPerformance(
					idLineaVehiculo, idTipoTraccion, idTipoCombustible, 
					valorRendimiento.floatValue());

			mostrarMensaje(Util.loadMessageValue("EXITO.MODIFICAR"), false);
			limpiar();

		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void action_consultarFP(ActionEvent event) {
		try {
			if (idLineaVehiculo == null || idLineaVehiculo == -1) 
				throw new GWorkException(Util.loadErrorMessageValue("LINEA.SEL"));				

			if (idTipoCombustible == null || idTipoCombustible == -1)
				throw new GWorkException(Util.loadErrorMessageValue("TIPOCOM.SEL"));				

			if (idTipoTraccion == null || idTipoTraccion == -1)
				throw new GWorkException(Util.loadErrorMessageValue("TRACCION"));

			FuelPerformance fuelPerformance = null;

			try {
				fuelPerformance = fuelPerformanceService.consultarFuelPerformance(
						idLineaVehiculo, idTipoCombustible, idTipoTraccion);
			} catch (GWorkException e) {
				throw new GWorkException(Util.loadErrorMessageValue("ERROR.SEARCHEMPTY"));
			}

			if (fuelPerformance == null) {
				throw new GWorkException(Util.loadErrorMessageValue("ERROR.SEARCHEMPTY"));
			}

			selectLineaVehiculo.setDisabled(true);
			selectTipoCombustible.setDisabled(true);
			selectTipoTraccion.setDisabled(true);
			btnCrear.setDisabled(true);

			valorRendimiento = fuelPerformance.getValorRendimiento().floatValue();

		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void action_limpiarForma(ActionEvent event) {
		limpiar();
	}

	public void limpiar() {
		valorRendimiento = 0F;
		selectLineaVehiculo.setValue(new Long("-1"));
		selectTipoCombustible.setValue(new Long("-1"));
		selectTipoTraccion.setValue(new Long("-1"));
		selectLineaVehiculo.setDisabled(false);
		selectTipoCombustible.setDisabled(false);
		selectTipoTraccion.setDisabled(false);
		btnCrear.setDisabled(false);
	}

	public void mostrarMensaje(String mensaje, boolean buttonCancel) {
		PopupMessagePage message = (PopupMessagePage) FacesUtils.getManagedBean("popupMessagePage");
		if (message != null)
			message.showPopup(mensaje, buttonCancel);
	}

	public FuelPerformanceService getFuelPerformanceService() {
		return fuelPerformanceService;
	}

	public void setFuelPerformanceService(
			FuelPerformanceService fuelPerformanceService) {
		this.fuelPerformanceService = fuelPerformanceService;
	}

	public Float getValorRendimiento() {
		return valorRendimiento;
	}

	public void setValorRendimiento(Float valorRendimiento) {
		this.valorRendimiento = valorRendimiento;
	}

	public Long getIdLineaVehiculo() {
		return idLineaVehiculo;
	}

	public void setIdLineaVehiculo(Long idLineaVehiculo) {
		this.idLineaVehiculo = idLineaVehiculo;
	}

	public Long getIdTipoCombustible() {
		return idTipoCombustible;
	}

	public void setIdTipoCombustible(Long idTipoCombustible) {
		this.idTipoCombustible = idTipoCombustible;
	}

	public Long getIdTipoTraccion() {
		return idTipoTraccion;
	}

	public void setIdTipoTraccion(Long idTipoTraccion) {
		this.idTipoTraccion = idTipoTraccion;
	}

	public HtmlSelectOneMenu getSelectLineaVehiculo() {
		return selectLineaVehiculo;
	}

	public void setSelectLineaVehiculo(HtmlSelectOneMenu selectLineaVehiculo) {
		this.selectLineaVehiculo = selectLineaVehiculo;
	}

	public HtmlSelectOneMenu getSelectTipoCombustible() {
		return selectTipoCombustible;
	}

	public void setSelectTipoCombustible(HtmlSelectOneMenu selectTipoCombustible) {
		this.selectTipoCombustible = selectTipoCombustible;
	}

	public HtmlSelectOneMenu getSelectTipoTraccion() {
		return selectTipoTraccion;
	}

	public void setSelectTipoTraccion(HtmlSelectOneMenu selectTipoTraccion) {
		this.selectTipoTraccion = selectTipoTraccion;
	}

	public SelectItem[] getListLineaVehiculos() {
		return listLineaVehiculos;
	}

	public void setListLineaVehiculos(SelectItem[] listLineaVehiculos) {
		this.listLineaVehiculos = listLineaVehiculos;
	}

	public SelectItem[] getListTipoCombustibles() {
		return listTipoCombustibles;
	}

	public void setListTipoCombustibles(SelectItem[] listTipoCombustibles) {
		this.listTipoCombustibles = listTipoCombustibles;
	}

	public SelectItem[] getListTipoTracciones() {
		return listTipoTracciones;
	}

	public void setListTipoTracciones(SelectItem[] listTipoTracciones) {
		this.listTipoTracciones = listTipoTracciones;
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

	public Integer getOpcConfirmacion() {
		return opcConfirmacion;
	}

	public void setOpcConfirmacion(Integer opcConfirmacion) {
		this.opcConfirmacion = opcConfirmacion;
	}

	public String getTempRendimiento() {
		return tempRendimiento;
	}

	public void setTempRendimiento(String tempRendimiento) {
		this.tempRendimiento = tempRendimiento;
	}

}
